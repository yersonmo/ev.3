package com.mainapp.mainapp.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Curso curso;

    @BeforeEach
    void setUp() {
        curso = new Curso();
        curso.setId(1);
        curso.setNombre("Matemáticas");
    }

    @Test
    public void testGetAllCursos() throws Exception {
        when(cursoService.findAll()).thenReturn(List.of(curso));

        mockMvc.perform(get("/api/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Matemáticas"));
    }

    @Test
    public void testGetCursoById() throws Exception {
        when(cursoService.findById(1)).thenReturn(curso);

        mockMvc.perform(get("/api/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Matemáticas"));
    }

    @Test
    public void testCreateCurso() throws Exception {
        when(cursoService.save(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Matemáticas"));
    }

    @Test
    public void testUpdateCurso() throws Exception {
        when(cursoService.save(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(put("/api/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Matemáticas"));
    }

    @Test
    public void testDeleteCurso() throws Exception {
        doNothing().when(cursoService).deleteById(1);

        mockMvc.perform(delete("/api/cursos/1"))
                .andExpect(status().isOk());

        verify(cursoService, times(1)).deleteById(1);
    }
}