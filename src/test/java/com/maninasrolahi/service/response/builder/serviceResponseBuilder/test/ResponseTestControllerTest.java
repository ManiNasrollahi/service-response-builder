package com.maninasrolahi.service.response.builder.serviceResponseBuilder.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ResponseTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/test/ok → 200 OK with list data")
    void testGetOkResponse() throws Exception {
        mockMvc.perform(get("/api/test/ok"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.message", is("success")))
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0]", is("A")));
    }

    @Test
    @DisplayName("GET /api/test/fail → 400 Bad Request with fail message")
    void testGetFailResponse() throws Exception {
        mockMvc.perform(get("/api/test/fail").param("msg", "something failed"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(-1)))
                .andExpect(jsonPath("$.message", is("something failed")));
    }

    @Test
    @DisplayName("GET /api/test/logical → 422 Unprocessable Entity with logical error")
    void testGetLogicalError() throws Exception {
        mockMvc.perform(get("/api/test/logical"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.code", is(3)))
                .andExpect(jsonPath("$.message", is("Invalid logic")));
    }

    @Test
    @DisplayName("GET /api/test/map → 200 OK with map data")
    void testGetMapResponse() throws Exception {
        mockMvc.perform(get("/api/test/map"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.message", is("success")))
                .andExpect(jsonPath("$.data.name", is("Mani")));
    }
}
