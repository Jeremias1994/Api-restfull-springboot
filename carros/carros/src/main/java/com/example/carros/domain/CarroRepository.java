package com.example.carros.domain;

import com.example.carros.Carro;
import com.example.carros.domain.DTO.CarroDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro,Long>{

    Optional<Carro> findAllById(long id);

    List<Carro> findByTipo(String tipo);


}
