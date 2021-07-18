package ej.airport.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRegionsByIsoCountry_IsoCountry_Status200AndOrderedListByName() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/regions")
                .param("isoCountry", "LT")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        resultActions.andExpect(jsonPath("$[0].code", is("LT-AL")));
        resultActions.andExpect(jsonPath("$[0].name", is("Alytus County")));
        resultActions.andExpect(jsonPath("$[1].code", is("LT-SA")));
        resultActions.andExpect(jsonPath("$[1].name", is("Šiauliai County")));
    }

}
