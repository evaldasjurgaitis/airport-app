package ej.airport.controller;

import ej.airport.dto.CountryDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCountries_Status200AndOrderedListByName() throws Exception {
        List<CountryDetail> orderedCountries = Arrays.asList(
                createCountryDetail("US", "America"),
                createCountryDetail("LT", "Lithuania"),
                createCountryDetail("RU", "Russia")
        );
        ResultActions resultActions = mockMvc.perform(get("/countries")
                .contentType(MediaType.APPLICATION_JSON));


        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$[0].code", is(orderedCountries.get(0).getCode())));
        resultActions.andExpect(jsonPath("$[0].name", is(orderedCountries.get(0).getName())));
        resultActions.andExpect(jsonPath("$[1].code", is(orderedCountries.get(1).getCode())));
        resultActions.andExpect(jsonPath("$[1].name", is(orderedCountries.get(1).getName())));
        resultActions.andExpect(jsonPath("$[2].code", is(orderedCountries.get(2).getCode())));
        resultActions.andExpect(jsonPath("$[2].name", is(orderedCountries.get(2).getName())));
    }

    private CountryDetail createCountryDetail(String code, String name) {
        return new CountryDetail(code, name);
    }

}
