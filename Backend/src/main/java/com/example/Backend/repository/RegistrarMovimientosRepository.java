package com.example.Backend.repository;


import com.example.Backend.domain.RegistrarMovimientos;
import com.example.Backend.domain.Tablero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrarMovimientosRepository  extends JpaRepository<RegistrarMovimientos, Long> {

       List<RegistrarMovimientos> findByTablero(Tablero tablero);
}
