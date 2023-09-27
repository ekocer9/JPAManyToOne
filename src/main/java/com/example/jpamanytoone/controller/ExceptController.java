package com.example.jpamanytoone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "exp/")
public class ExceptController {

    @GetMapping("/div/{divnum}")
    public int getDivNum(@PathVariable int divnum) {
        int i1 = 100;
        int i2 = -1;
        i2 = i1 / divnum;
        return i2;
    }



}
