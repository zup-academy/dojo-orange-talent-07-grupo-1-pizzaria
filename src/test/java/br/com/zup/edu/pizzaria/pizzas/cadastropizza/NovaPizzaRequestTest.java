package br.com.zup.edu.pizzaria.pizzas.cadastropizza;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NovaPizzaRequestTest {

    @Test
    void testaListaComTamanhoZero() {
        List<Long> listaIgredientes = new ArrayList<>();

        assertEquals(listaIgredientes.size(), 0);


    }

}