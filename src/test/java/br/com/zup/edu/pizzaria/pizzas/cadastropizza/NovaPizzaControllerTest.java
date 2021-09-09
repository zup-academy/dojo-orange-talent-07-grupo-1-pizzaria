package br.com.zup.edu.pizzaria.pizzas.cadastropizza;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.ingredientes.IngredienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NovaPizzaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IngredienteRepository repository;

    @BeforeEach
    public void init() {
        Ingrediente um = new Ingrediente("Queijo muçarela", 200, new BigDecimal("2.0"));
        Ingrediente dois = new Ingrediente("Tomate", 200, new BigDecimal("1.5"));
        Ingrediente tres = new Ingrediente("Rúcula", 200, new BigDecimal("1.0"));
        
        List<Ingrediente> ingredientes = List.of(um, dois, tres);
        when(repository.findAllById(List.of(1L, 2L, 3L))).thenReturn(ingredientes);
    }

    @Test
    void deveCadastrarNovaPizza() throws Exception {

        NovaPizzaRequest body = new NovaPizzaRequest("Brócolis", List.of(1L, 2L, 3L));
        MockHttpServletRequestBuilder request = post("/api/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(redirectedUrlPattern("/api/pizzas/{\\d*}"));

    }

}