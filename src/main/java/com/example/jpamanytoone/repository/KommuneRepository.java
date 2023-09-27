package com.example.jpamanytoone.repository;

import com.example.jpamanytoone.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    //Vi bruger JPQL til at oprette bla find metoder
    Optional<Kommune> findKommuneByNavn(String navn);
}
