package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.exception.ResourceNotFoundException;
import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getkommuner")
    public List<Kommune> getKommuner() {
        List<Kommune> lstKommuner = apiServiceGetKommuner.getKommuner();
        return lstKommuner;
    }

    @GetMapping("/kommunenavn{navn}")
    public ResponseEntity<Kommune> getKommuneByName(@PathVariable String name) {
        Kommune kommune = kommuneRepository.findKommuneByNavn(name).orElseThrow(() -> new ResourceNotFoundException("Kommune ikke fundet med navn = " + name ));
        return new ResponseEntity<>(kommune, HttpStatus.OK);
    }


    @GetMapping("/kommuner")
    public List<Kommune> getKommune() {
        return kommuneRepository.findAll();
    }

    @PostMapping("/kommune")
    public ResponseEntity<Kommune> postKommune(@RequestBody Kommune kommune) {
        System.out.println("Indsætter ny Kommune");
        System.out.println(kommune);
        Kommune savedKommune = kommuneRepository.save(kommune);
        if (savedKommune == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedKommune, HttpStatus.CREATED);
        }
    }

    @PutMapping("/kommune/{kode}")
    public ResponseEntity<Kommune> putKommune(@PathVariable String kode, @RequestBody Kommune kommune) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(kode);
        if (orgKommune.isPresent()) {
            kommune.setKode(kode);
            kommuneRepository.save(kommune);
            return ResponseEntity.ok(kommune);
            //return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
            //return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
        }
    }
}