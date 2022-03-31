package ufps.ahp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
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

    @PostMapping("/descisor/{idProblema}")
    public ResponseEntity<?> agregarDescisor(@RequestBody DescisorDTO descisorDTO, @RequestParam String idProblema){

        Problema p = problemaService.buscar(idProblema);

        Usuario u = usuarioService.findByEmail(descisorDTO.getEmail());

        if(u!=null){
            u.setDecisor(new Decisor(descisorDTO.getNombre(), descisorDTO.getEmail()));
        }

        emailServiceImp.enviarEmail("Inscripción descisor problema", "Hola, "+descisorDTO.getNombre()+
                "has sido seleccionado para participar en la votación del problema, ingresa al siguiente link para acceder al problema: "+
                p.getDescripcion(),descisorDTO.getEmail());
        return ResponseEntity.ok("");
    }
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }


}
