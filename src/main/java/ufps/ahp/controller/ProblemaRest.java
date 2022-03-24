package ufps.ahp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufps.ahp.model.Criterio;
import ufps.ahp.model.Problema;
import ufps.ahp.services.ProblemaService;

import java.util.List;

@RestController
@RequestMapping("/problema")
public class ProblemaRest {

    @Autowired
    ProblemaService problemaService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(problemaService.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Problema problema){

        if(problema == null){
            return new ResponseEntity("Datos incorrectos",HttpStatus.BAD_REQUEST);
        }
        problemaService.guardar(problema);
        return ResponseEntity.ok("Problema creado");
    }

    @GetMapping(path ="/criterios/{idProblema}")
    public ResponseEntity<List<Criterio>> criteriorPorProblema(@RequestParam(required = false, name = "idProblema") String idProblema){
        return ResponseEntity.ok(problemaService.criteriosPorProblema(idProblema));
    }

}
