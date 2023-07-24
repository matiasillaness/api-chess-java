package com.example.Backend.repository;

import com.example.Backend.domain.Tablero;
import com.example.Backend.domain.pieces.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Backend.domain.Players;

import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Long> {
    List<Players> findByTablero(Tablero tablero);
}
