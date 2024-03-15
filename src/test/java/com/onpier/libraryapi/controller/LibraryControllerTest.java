package com.onpier.libraryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    //@Disabled
    void shouldReturnAllBooksWithoutDuplicates() throws Exception {
        var result = mvc.perform(get("/v1/api/library/borrowed-users")).andExpect(status().isOk()).andReturn();
        var jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());

        assertTrue(jsonNode.isArray());
        assertEquals(11, jsonNode.size());
    }
}
