package com.example.carros;


import com.example.carros.domain.CarroService;
import com.example.carros.domain.DTO.CarroDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosApplicationTests {

    @Autowired
    private CarroService service;

    @Before
    public void setUp() {
        Carro carro1 = new Carro();
        carro1.setNome("Tucker 1948");
        carro1.setTipo("classicos");
        carro1.setDescricao("Descrição Tucker 1948");
        carro1.setUrlFoto("http://www.livroandroid.com.br/livro/carros/classicos/Tucker.png");
        carro1.setUrlVideo("http://www.livroandroid.com.br/livro/carros/classicos/tucker.mp4");
        carro1.setLatitude("-23.564224");
        carro1.setLongitude("-46.653156");
        service.insert(carro1);

        Carro carro2 = new Carro();
        carro2.setNome("Chevrolet Corvette");
        carro2.setTipo("classicos");
        carro2.setDescricao("Descrição Chevrolet Corvette");
        carro2.setUrlFoto("http://www.livroandroid.com.br/livro/carros/classicos/Chevrolet_Corvette.png");
        carro2.setUrlVideo("http://www.livroandroid.com.br/livro/carros/classicos/corvette.mp4");
        carro2.setLatitude("-23.564224");
        carro2.setLongitude("-46.653156");
        service.insert(carro2);

        Carro carro3 = new Carro();
        carro3.setNome("Chevrolet Impala Coupe");
        carro3.setTipo("classicos");
        carro3.setDescricao("Descrição Chevrolet Impala Coupe");
        carro3.setUrlFoto("http://www.livroandroid.com.br/livro/carros/classicos/Chevrolet_Impala_Coupe.png");
        carro3.setUrlVideo("http://www.livroandroid.com.br/livro/carros/classicos/chevrolet_impala.mp4");
        carro3.setLatitude("-23.564224");
        carro3.setLongitude("-46.653156");
        service.insert(carro3);
    }

    @Test
    public void testsave() {
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");

        CarroDto c = service.insert(carro);
        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);
        //buscar objeto
        Optional<CarroDto> op = service.getCarrosById(id);
        assertTrue(op.isPresent());
        c = op.get();
        assertEquals("Ferrari",c.getNome());
        assertEquals("esportivos",c.getTipo() );
        //deletar o objeto

        service.delete(id);

        //verificar se deletou
        assertFalse(service.getCarrosById(id).isPresent());

    }

    @Test
    public void testLista() {
        List<CarroDto> carros = service.getCarros();
        assertEquals(3,carros.size());
    }

}