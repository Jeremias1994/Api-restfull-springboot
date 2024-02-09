package com.example.carros.domain;

import com.example.carros.Carro;
import com.example.carros.domain.DTO.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository rep;


    public List<CarroDto> getCarros() {
        return rep.findAll().stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public List<CarroDto> getCarrosbyTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public CarroDto insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
        return CarroDto.create(rep.save(carro));
    }

    public CarroDto update(Carro carro, long id) {
        Assert.notNull(id, "Não foi possivel atualizar o registro");

        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id" + db.getId());

            rep.save(db);

            return CarroDto.create(db);
        }else {
            return null;
            //throw new RuntimeException("Não foi possivel atualizar o registro");

        }
    }


    public  boolean delete(long id){
       if (getCarrosById(id).isPresent()){
           rep.deleteById(id);
           return true;
       }
       return false;
    }

    public Optional<CarroDto> getCarrosById(long id) {
        return rep.findAllById(id).map(CarroDto::create);
    }
}












