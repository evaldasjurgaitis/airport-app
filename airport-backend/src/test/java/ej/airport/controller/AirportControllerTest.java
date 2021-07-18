package ej.airport.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAirportDetail_Id_Status200AndAirportDetail() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/airports/{id}", 6525)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$.id", is(6525)));
        resultActions.andExpect(jsonPath("$.name", is("Epps Airpark")));
        resultActions.andExpect(jsonPath("$.availableProviders[0].name", is("wizzair")));
        resultActions.andExpect(jsonPath("$.availableProviders[0].price", is(3311)));
        resultActions.andExpect(jsonPath("$.availableProviders[1].name", is("wizzair")));
        resultActions.andExpect(jsonPath("$.availableProviders[1].price", is(3319)));
    }

    @Test
    public void getAirportsByRequestParams_IsoCountry_Status200AndOrderedListByCheapestProviderPrices() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/airports")
                .param("isoCountry","US")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$.content[0].id", is(6746)));
        resultActions.andExpect(jsonPath("$.content[0].name", is("M D K Field")));
        resultActions.andExpect(jsonPath("$.content[0].providerDetail.name", is("lufthansa")));
        resultActions.andExpect(jsonPath("$.content[0].providerDetail.price", is(131)));

        resultActions.andExpect(jsonPath("$.content[1].id", is(6525)));
        resultActions.andExpect(jsonPath("$.content[1].name", is("Epps Airpark")));
        resultActions.andExpect(jsonPath("$.content[1].providerDetail.name", is("wizzair")));
        resultActions.andExpect(jsonPath("$.content[1].providerDetail.price", is(3311)));
    }

    @Test
    public void getAirportsByRequestParams_IsoCountryAndIsoRegion_Status200AndOrderedListByCheapestProviderPrices() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/airports")
                .param("isoCountry","US")
                .param("isoRegion","US-TX")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$.content[0].id", is(6746)));
        resultActions.andExpect(jsonPath("$.content[0].name", is("M D K Field")));
        resultActions.andExpect(jsonPath("$.content[0].providerDetail.name", is("lufthansa")));
        resultActions.andExpect(jsonPath("$.content[0].providerDetail.price", is(131)));
    }

}
