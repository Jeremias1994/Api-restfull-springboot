package com.example.carros.api;

import com.example.carros.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.DTO.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/Carros")
public class CarrosController {
    @Autowired
    private CarroService service;

    @GetMapping()
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getCarros());
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id) {
        Optional<CarroDto> carro = service.getCarrosById(id);

        return carro
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDto> carros = service.getCarrosbyTipo(tipo);

        return carros.isEmpty() ?
                ResponseEntity.noContent().build():
                ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Carro carro) {
        try {
            CarroDto c = service.insert(carro);
            URI location = getUri(c.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") long id, @RequestBody Carro carro) {
        carro.setId(id);

        CarroDto c = service.update(carro, id);

        return c != null ?
                ResponseEntity.ok(c):
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        boolean ok = service.delete(id);


        return ok?
                ResponseEntity.ok().build():
                ResponseEntity.notFound().build();

    }
}


