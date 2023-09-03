package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Region;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiServiceGetRegioner {
    List<Region> getRegioner();
}
