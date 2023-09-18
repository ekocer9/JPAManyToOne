package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getregioner")
    public List<Region> getRegioner() {
        List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
        return lstRegioner;
    }

    @GetMapping("/regioner")
    public List<Region> getRegionerRepos() {
        return regionRepository.findAll();
    }

    @GetMapping("/kommunenavne/{kode}")
    public List<String> getKommuneNavne(@PathVariable String kode) {
        return apiServiceGetRegioner.getKommuneNavne(kode);
    }

    @DeleteMapping("/region/{kode}")
    public ResponseEntity<String> deleteRegion(@PathVariable String kode) {
        if (!regionRepository.existsById(kode)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        regionRepository.deleteById(kode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/region")
    public ResponseEntity<Region> postRegion(@RequestBody Region region) {
        System.out.println("Indsætter ny Region");
        System.out.println(region);
        Region savedRegion = regionRepository.save(region);
        if (savedRegion == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedRegion, HttpStatus.CREATED);
        }
    }

    @PutMapping("/region/{kode}")
    public ResponseEntity<Region> putRegion(@PathVariable String kode, @RequestBody Region region) {
        Optional<Region> orgRegion = regionRepository.findById(kode);
        if (orgRegion.isPresent()) {
            region.setKode(kode);
            regionRepository.save(region);
            return ResponseEntity.ok(region);
            //return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
            //return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
        }
    }

}
