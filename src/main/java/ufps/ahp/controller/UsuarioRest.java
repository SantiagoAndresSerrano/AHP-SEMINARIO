package ufps.ahp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufps.ahp.model.Decisor;
import ufps.ahp.model.Problema;
import ufps.ahp.model.dto.DescisorDTO;
import ufps.ahp.security.model.Usuario;
import ufps.ahp.security.servicio.UsuarioService;
import ufps.ahp.services.DecisorService;
import ufps.ahp.services.EmailSenderService;
import ufps.ahp.services.ProblemaService;
import ufps.ahp.services.imp.EmailServiceImp;

import javax.mail.MessagingException;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioRest {

    @Autowired
    UsuarioService usuarioService;


    @Autowired
    EmailServiceImp emailServiceImp;

    @Autowired
    ProblemaService problemaService;

    @Autowired
    DecisorService decisorService;

    @Value("${uribackend}")
    private String urlBackend;

    @Value("${urifrontend}")
    private String urlFrontend;


    @GetMapping("/usuarioPorEmail/{email}")
    public ResponseEntity<?> getUsuarioByEmail(@PathVariable String email){

        Usuario u = usuarioService.findByEmail(email);

        if(u==null){
            return new ResponseEntity("Email no encontrado",HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(u);
    }

    @PostMapping("/descisor/{idProblema}")
    public ResponseEntity<?> agregarDescisor(@RequestBody DescisorDTO descisorDTO, @RequestParam String idProblema) throws MessagingException {

        Problema p = problemaService.buscar(idProblema);

        Usuario u = usuarioService.findByEmail(descisorDTO.getEmail());

        if(u!=null){
            u.setDecisor(new Decisor(descisorDTO.getNombre(), descisorDTO.getEmail()));
        }

        emailServiceImp.enviarEmail("Inscripción descisor problema",

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
                        "            <p style=\"margin: 1rem 0; font-size: 25px;\">Cambio de contraseña</p>\n" +
                        "            <p style=\"color: #424242;\">Hola, <b>"+descisorDTO.getNombre()+"</b>, has sido seleccionado para participar como decisor del problema," +p.getDescripcion()+
                        " <br> ingresa al siguiente link para acceder al intrumento:  \n" +
                        "            </p>\n" +
                        "            <div style=\"margin: 2rem auto; width: 120px; background-color: #4f46e5; padding: 8px; border-radius: 6px; \">\n" +
                        "                <a style=\"color: #ffffff; text-decoration: none\" href=\""+urlFrontend+"problem/access/"+p.getIdProblema()+"\">Continuar</a>\n" +
                        "            </div>\n" +
                        "            <div style=\"width: 100%; border-top: 2px solid #a5b4fc; padding: 1rem 0\">\n" +
                        "                <p>Copyright © 2022 Analytic Hierarchy Process <br> Todos los derechos reservados.</p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>"

                ,descisorDTO.getEmail());
        return ResponseEntity.ok("");
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }


}
