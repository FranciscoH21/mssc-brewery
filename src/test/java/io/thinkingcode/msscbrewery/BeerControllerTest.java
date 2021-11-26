package io.thinkingcode.msscbrewery;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thinkingcode.msscbrewery.services.BeerService;
import io.thinkingcode.msscbrewery.web.model.BeerDto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto vadlidBeer;

    @BeforeEach
    public void setUp(){
        vadlidBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Beer1")
                .beerStyle("Best Beer Ever")
                .upc(123456789012L)
                .build();
    }

    @Test
    public void getBeer() throws Exception{
        BDDMockito.given(beerService.getBeerById(Mockito.any(UUID.class))).willReturn(vadlidBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + vadlidBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(vadlidBeer.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beerName", Is.is("Beer1")));
    }

    @Test
    public void handlePost() throws Exception{
        // given
        BeerDto beerDto = vadlidBeer;
        beerDto.setId(null);
        BeerDto saveDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("New Beer")
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        BDDMockito.given(beerService.saveNewBeer(Mockito.any())).willReturn(saveDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void handleUpdate() throws Exception {
        // given
        BeerDto beerDto = vadlidBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        // when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + vadlidBeer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        BDDMockito.then(beerService).should().updateBeer(Mockito.any(),Mockito.any());
    }
}
