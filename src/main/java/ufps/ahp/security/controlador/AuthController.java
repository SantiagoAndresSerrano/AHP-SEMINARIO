/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.ahp.security.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ufps.ahp.model.PasswordResetToken;
import ufps.ahp.security.dto.JwtDto;
import ufps.ahp.security.dto.LoginUsuario;
import ufps.ahp.security.dto.NuevoUsuario;
import ufps.ahp.security.jwt.JwtProvider;
import ufps.ahp.security.model.Rol;
import ufps.ahp.security.model.Usuario;
import ufps.ahp.security.servicio.RolService;
import ufps.ahp.security.servicio.UsuarioService;
import ufps.ahp.services.PasswordResetTokenService;
import ufps.ahp.services.imp.EmailServiceImp;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author santi
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@Slf4j
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    EmailServiceImp emailServiceImp;


    @Autowired
    PasswordResetTokenService passwordResetTokenService;


    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(Rol.RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(Rol.RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuario.setCelular(nuevoUsuario.getCelular());
        usuario.setEmail(nuevoUsuario.getEmail());
        usuario.setEmpresa(nuevoUsuario.getEmpresa());
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setProfesion(nuevoUsuario.getProfesion());
        usuario.setEmail(nuevoUsuario.getEmail());
        usuario.setConfirmationToken(UUID.randomUUID().toString());
        usuarioService.guardar(usuario);


        emailServiceImp.enviarEmail("Confirmación de cuenta",
                "Te has registrado en la plataforma, por favor confirma que eres tú ingresando al siguiente enlace:"
                        +"http://localhost:8082/auth/confirmacion/"+usuario.getConfirmationToken(),
                usuario.getEmail()
        );


        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/solicitudPassword/{email}")
    public ResponseEntity<?> recuperarPassword(@PathVariable String email){
        Usuario u = usuarioService.findByEmail(email);

        if(u==null)
            return new ResponseEntity(("El email no existe"), HttpStatus.NOT_FOUND);


        PasswordResetToken passwordResetToken = new PasswordResetToken(u);
        passwordResetTokenService.guardar(passwordResetToken);

        emailServiceImp.enviarEmail("Recuperación de contraseña",
                "Hola "+u.getNombre()+", Para cambiar tu contraseña ingresa al siguiente link:"
                        +"http://localhost:8082/auth/recuperar/"+passwordResetToken.getToken()+
                        "\n Recuerda que este link expirará en 24 horas.",
                u.getEmail());

        return ResponseEntity.ok("Mensaje de recuperación enviado al correo");
    }

    @GetMapping("/recuperar/{token}")
    public ResponseEntity<?>confirmarRecuperarPassword(@PathVariable String token){

        PasswordResetToken passwordResetToken = passwordResetTokenService.buscarToken(token);

        if(passwordResetToken == null)
            return new ResponseEntity(("El token no existe"), HttpStatus.NOT_FOUND);

        if(passwordResetToken.getFechaExpiracion().before(new Date()))
            return new ResponseEntity(("El token ha expirado"), HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/recuperar/{token}")
    public ResponseEntity<?>cambiarPassword(@PathVariable String token, @RequestBody LoginUsuario loginUsuario){

        PasswordResetToken passwordResetToken = passwordResetTokenService.buscarToken(token);

        if(passwordResetToken == null)
            return new ResponseEntity(("El token no existe"), HttpStatus.NOT_FOUND);

        if(passwordResetToken.getFechaExpiracion().before(new Date()))
            return new ResponseEntity(("El token ha expirado"), HttpStatus.BAD_REQUEST);


        Usuario u = usuarioService.findByEmail(loginUsuario.getEmail());
        u.setPassword(passwordEncoder.encode(loginUsuario.getPassword()));
        usuarioService.guardar(u);

        passwordResetTokenService.eliminar(passwordResetToken);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/confirmacion/{token}")
    public ResponseEntity<?> confirmarToken(@PathVariable String token){
        Usuario usuario = usuarioService.findByConfirmationToken(token);

        if(usuario==null){
            return new ResponseEntity("Error, Token no encontrado", HttpStatus.NOT_FOUND);
        }

        usuario.setEstado(true);
        usuarioService.guardar(usuario);

        return ResponseEntity.ok("Usuario verificado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(("campos mal puestos"), HttpStatus.BAD_REQUEST);

        Usuario usuario = usuarioService.getByEmail(loginUsuario.getEmail()).orElse(null);

        if(usuario == null){
            return new ResponseEntity(("El nombre de usuario no existe"), HttpStatus.NOT_FOUND);
        }

        if(!usuario.isEstado()){
            return new ResponseEntity(("El usuario se encuentra deshabilitado"), HttpStatus.NOT_FOUND);
        }

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
