package com.mainapp.mainapp.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.service.ProfesorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ProfesorController.class)
public class ProfesorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfesorService profesorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Profesor profesor;

    @BeforeEach
    void setUp() {
        profesor = new Profesor();
        profesor.setId(1);
        profesor.setNombre("Ana");
        profesor.setApellido("Ramírez");
        profesor.setEspecialidad("Historia");
    }

    @Test
    public void testGetAllProfesores() throws Exception {
        when(profesorService.findAll()).thenReturn(List.of(profesor));

        mockMvc.perform(get("/api/profesores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Ana"))
                .andExpect(jsonPath("$[0].apellido").value("Ramírez"))
                .andExpect(jsonPath("$[0].especialidad").value("Historia"));
    }

    @Test
    public void testGetProfesorById() throws Exception {
        when(profesorService.findById(1)).thenReturn(profesor);

        mockMvc.perform(get("/api/profesores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.apellido").value("Ramírez"))
                .andExpect(jsonPath("$.especialidad").value("Historia"));
    }

    @Test
    public void testCreateProfesor() throws Exception {
        when(profesorService.save(any(Profesor.class))).thenReturn(profesor);

        mockMvc.perform(post("/api/profesores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profesor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ana"));
    }

    @Test
    public void testUpdateProfesor() throws Exception {
        when(profesorService.save(any(Profesor.class))).thenReturn(profesor);

        mockMvc.perform(put("/api/profesores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profesor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ana"));
    }

    @Test
    public void testDeleteProfesor() throws Exception {
        doNothing().when(profesorService).deleteById(1);

        mockMvc.perform(delete("/api/profesores/1"))
                .andExpect(status().isOk());

        verify(profesorService, times(1)).deleteById(1);
    }
}