package ufps.ahp.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufps.ahp.model.Alternativa;
import ufps.ahp.model.Criterio;
import ufps.ahp.model.Problema;
import ufps.ahp.model.PuntuacionCriterio;
import ufps.ahp.services.AlternativaService;
import ufps.ahp.services.CriterioService;
import ufps.ahp.services.ProblemaService;
import ufps.ahp.services.PuntuacionCriterioServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/problema")
@Slf4j
public class ProblemaRest {

    @Autowired
    ProblemaService problemaService;

    @Autowired
    CriterioService criterioService;

    @Autowired
    AlternativaService alternativaService;

    @Autowired
    PuntuacionCriterioServicio puntuacionCriterioServicio;



    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(problemaService.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Problema problema){

        if(problema == null){
            return new ResponseEntity("Datos incorrectos",HttpStatus.BAD_REQUEST);
        }

        problema.setIdProblema(UUID.randomUUID().toString());
        problemaService.guardar(problema);
        return ResponseEntity.ok("Problema creado");
    }

    @GetMapping(path ="/criterios/{idProblema}")
    public ResponseEntity<?> criteriorPorProblema(@PathVariable String idProblema){
        log.info(idProblema);
        return ResponseEntity.ok(problemaService.buscar(idProblema).criterio());
    }

    @GetMapping(path ="/alternativas/{idProblema}")
    public ResponseEntity<?> alternativaPorProblema(@PathVariable String idProblema){
        return ResponseEntity.ok(problemaService.buscar(idProblema).alternativa());
    }

    @PostMapping(path="/criterios/{idProblema}")
    public ResponseEntity<?> agregarCriteriosDeProblema(@PathVariable String idProblema, @RequestBody List<Criterio> criterios){
        Problema p = problemaService.buscar(idProblema);
        for(Criterio c: criterios){
            c.setProblema(p);
            criterioService.guardar(c);
        }
        return ResponseEntity.ok(criterios);
    }

    @GetMapping(path="/criteriosComparados/{idProblema}") // Metodo para obtener los pares a comparar en la puntuacion de criterios
    public ResponseEntity<?> obtenerParesCriterios(@PathVariable String idProblema){
        Problema p = problemaService.buscar(idProblema);
        
        return ResponseEntity.ok(puntuacionCriterioServicio.obtenerParesCriterios(idProblema));
    }

    @PostMapping(path="/alternativas/{idProblema}")
    public ResponseEntity<?> agregarAlternativasDeProblema(@PathVariable String idProblema, @RequestBody List<Alternativa> alternativas){
        Problema p = problemaService.buscar(idProblema);
        for(Alternativa alt: alternativas){
            alt.setProblema(p);
            alternativaService.guardar(alt);
        }
        return ResponseEntity.ok(alternativas);
    }
}
