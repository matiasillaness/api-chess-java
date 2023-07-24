package com.example.Backend.repository;

import com.example.Backend.domain.Tablero;
import com.example.Backend.domain.pieces.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiezaRepository extends JpaRepository<Pieza, Long> {
    List<Pieza> findByTablero(Tablero tablero);
}
