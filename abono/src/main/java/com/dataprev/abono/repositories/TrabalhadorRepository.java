package com.dataprev.abono.repositories;

import com.dataprev.abono.entities.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {
}
