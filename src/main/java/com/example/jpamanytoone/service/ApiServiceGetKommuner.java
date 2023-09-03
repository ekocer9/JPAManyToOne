package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiServiceGetKommuner {
    List<Kommune> getKommuner();
}
