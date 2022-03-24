package ufps.ahp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufps.ahp.model.dto.DescisorDTO;
import ufps.ahp.security.servicio.UsuarioService;
import ufps.ahp.services.EmailSenderService;
import ufps.ahp.services.imp.EmailServiceImp;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EmailServiceImp emailServiceImp;

    @PostMapping("/descisor/{idProblema}")
    public ResponseEntity<?> agregarDescisor(@RequestBody DescisorDTO descisorDTO, @RequestParam String idProblema){
        emailServiceImp.enviarEmail("Inscripción descisor problema", "Hola, "+descisorDTO.getNombre()+
                "has sido seleccionado para participar en la votación del problema, ingresa al siguiente link para acceder al problema: ","");
        return ResponseEntity.ok("");
    }


}
