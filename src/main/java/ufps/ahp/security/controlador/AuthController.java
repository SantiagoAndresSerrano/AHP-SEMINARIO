/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.ahp.security.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import ufps.ahp.model.Decisor;
import ufps.ahp.model.PasswordResetToken;
import ufps.ahp.security.dto.JwtDto;
import ufps.ahp.security.dto.LoginUsuario;
import ufps.ahp.security.dto.NuevoUsuario;
import ufps.ahp.security.jwt.JwtProvider;
import ufps.ahp.security.model.Rol;
import ufps.ahp.security.model.Usuario;
import ufps.ahp.security.servicio.RolService;
import ufps.ahp.security.servicio.UsuarioService;
import ufps.ahp.services.DecisorService;
import ufps.ahp.services.PasswordResetTokenService;
import ufps.ahp.services.imp.EmailServiceImp;

import javax.mail.MessagingException;
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

    @Autowired
    DecisorService decisorService;

    @Value("${uribackend}")
    private String urlBackend;

    @Value("${urifrontend}")
    private String urlFrontend;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) throws MessagingException {
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos mal puestos o email inv??lido", HttpStatus.BAD_REQUEST);
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

        Decisor decisor = decisorService.buscarPorEmail(nuevoUsuario.getEmail()); // Busco si antes de ser usuario participo como decisor
        if(decisor!=null){
            usuario.setDecisor(decisor);
        }

        usuarioService.guardar(usuario);
        emailServiceImp.enviarEmail("Confirmaci??n de cuenta ",
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body style=\"width: 800px\">\n" +
                        "    <div style=\"background-color: #a5b4fc; width: 100%; padding: 3rem 0;\">\n" +
                        "        <div style=\"text-align: center; background-color: #ffffff; margin: 0 auto; width: 80%; border-radius: 8px;\">\n" +
                        "            <img style=\"margin-top: 3rem; width: 190px\"\n" +
                        "                src=\"https://master.d1oc2nyuhwk984.amplifyapp.com/assets/images/logo.png\" alt=\"logo\">\n" +
                        "            <p style=\"margin: 1rem 0; font-size: 25px;\">Confirmaci??n de cuenta</p>\n" +
                        "            <p style=\"color: #424242;\">Hola, <b>"+usuario.getNombre()+"</b>, Te has registrado en la plataforma, por favor confirma que <br> eres\n" +
                        "                t?? ingresando al siguiente bot??n.\n" +
                        "            </p>\n" +
                        "            <div style=\"margin: 2rem auto; width: 120px; background-color: #4f46e5; padding: 8px; border-radius: 6px; \">\n" +
                        "                <a style=\"color: #ffffff; text-decoration: none\" href=\""+urlFrontend+"/confirmation/"+usuario.getConfirmationToken()+"\">Continuar</a>\n" +
                        "            </div>\n" +
                        "            <div style=\"width: 100%; border-top: 2px solid #a5b4fc; padding: 1rem 0\">\n" +
                        "                <p>Copyright ?? 2022 Analytic Hierarchy Process <br> Todos los derechos reservados.</p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>"
                ,
                usuario.getEmail()
        );


        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/solicitudPassword/{email}")
    public ResponseEntity<?> recuperarPassword(@PathVariable String email) throws MessagingException {
        Usuario u = usuarioService.findByEmail(email);

        if(u==null)
            return new ResponseEntity(("El email no existe"), HttpStatus.NOT_FOUND);


        PasswordResetToken passwordResetToken = new PasswordResetToken(u);
        passwordResetTokenService.guardar(passwordResetToken);

        emailServiceImp.enviarEmail("Cambio de contrase??a",
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body style=\"width: 800px\">\n" +
                        "    <div style=\"background-color: #a5b4fc; width: 100%; padding: 3rem 0;\">\n" +
                        "        <div style=\"text-align: center; background-color: #ffffff; margin: 0 auto; width: 80%; border-radius: 8px;\">\n" +
                        "            <img style=\"margin-top: 3rem; width: 190px\"\n" +
                        "                src=\"https://master.d1oc2nyuhwk984.amplifyapp.com/assets/images/logo.png\" alt=\"logo\">\n" +
                        "            <p style=\"margin: 1rem 0; font-size: 25px;\">Cambio de contrase??a</p>\n" +
                        "            <p style=\"color: #424242;\">Hola, <b>"+u.getNombre()+"</b>, has solicitado cambiar tu contrase??a, <br> para cambiar tu contrase??a ingresa al siguiente link:  \n" +
                        "            </p>\n" +
                        "            <div style=\"margin: 2rem auto; width: 120px; background-color: #4f46e5; padding: 8px; border-radius: 6px; \">\n" +
                        "                <a style=\"color: #ffffff; text-decoration: none\" href=\""+urlFrontend+"/password-reset/confirmation"+passwordResetToken.getToken()+"\">Continuar</a>\n" +
                        "            </div>\n" +
                        "            <div style=\"width: 100%; border-top: 2px solid #a5b4fc; padding: 1rem 0\">\n" +
                        "                <p>Copyright ?? 2022 Analytic Hierarchy Process <br> Todos los derechos reservados.</p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>"

                ,
                u.getEmail());

        return ResponseEntity.ok("Mensaje de recuperaci??n enviado al correo");
    }

    @GetMapping("/recuperar/{token}") //petici??n que recibe el backend de parte del frontend, recordar cambiar el link de la linea 131 a un URL del frontend
    public ResponseEntity<?>confirmarRecuperarPassword(@PathVariable String token){

        PasswordResetToken passwordResetToken = passwordResetTokenService.buscarToken(token);

        if(passwordResetToken == null)
            return new ResponseEntity(("El token no existe"), HttpStatus.NOT_FOUND);

        if(passwordResetToken.getFechaExpiracion().before(new Date()))
            return new ResponseEntity(("El token ha expirado"), HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/recuperar/{token}")
    public ResponseEntity<?>cambiarPassword(@PathVariable String token, @RequestBody LoginUsuario loginUsuario) throws MessagingException {

        PasswordResetToken passwordResetToken = passwordResetTokenService.buscarToken(token);

        if(passwordResetToken == null)
            return new ResponseEntity(("El token no existe"), HttpStatus.NOT_FOUND);

        if(passwordResetToken.getFechaExpiracion().before(new Date()))
            return new ResponseEntity(("El token ha expirado"), HttpStatus.BAD_REQUEST);


        Usuario u = usuarioService.findByEmail(loginUsuario.getEmail());

        if(u==null){
            return new ResponseEntity(("El correo no existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario uToken = usuarioService.findByResetPassword(token);

        if(uToken==null){
            return new ResponseEntity(("El token no est?? asociado a ningun usuario"), HttpStatus.BAD_REQUEST);
        }

        if(!u.getEmail().equals(uToken.getEmail())){
            return new ResponseEntity(("El token se encuentra asociado a otro usuario"), HttpStatus.BAD_REQUEST);
        }

        emailServiceImp.enviarEmail("Contrase??a actualizada",
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body style=\"width: 800px\">\n" +
                        "    <div style=\"background-color: #a5b4fc; width: 100%; padding: 3rem 0;\">\n" +
                        "        <div style=\"text-align: center; background-color: #ffffff; margin: 0 auto; width: 80%; border-radius: 8px;\">\n" +
                        "            <img style=\"margin-top: 3rem; width: 190px\"\n" +
                        "                src=\"https://master.d1oc2nyuhwk984.amplifyapp.com/assets/images/logo.png\" alt=\"logo\">\n" +
                        "            <p style=\"margin: 1rem 0; font-size: 25px;\">Cambio de contrase??a</p>\n" +
                        "            <p style=\"color: #424242;\">Hola, <b>"+u.getNombre()+"</b>, se ha cambiado tu contrase??a en el sistema.  \n" +
                        "            </p>\n" +
                        "            <div style=\"width: 100%; border-top: 2px solid #a5b4fc; padding: 1rem 0\">\n" +
                        "                <p>Copyright ?? 2022 Analytic Hierarchy Process <br> Todos los derechos reservados.</p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>"

                ,
                u.getEmail());

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
