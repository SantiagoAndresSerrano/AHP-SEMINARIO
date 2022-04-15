package ufps.ahp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufps.ahp.services.CriterioService;

@RequestMapping("/criterio")
@RestController
@CrossOrigin

public class CriterioRest {
    @Autowired
    CriterioService criterioService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(criterioService.listar());
    }
}
