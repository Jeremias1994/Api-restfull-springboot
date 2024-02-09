package com.example.carros.domain.DTO;


import com.example.carros.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class CarroDto {
    private long id;
    private String nome;
    private String tipo;

    public static CarroDto create(Carro c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c,CarroDto.class);
    }

}
