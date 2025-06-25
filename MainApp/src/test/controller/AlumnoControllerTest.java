package com.mainapp.mainapp.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.AlumnoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(AlumnoController.class)
public class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Alumno alumno;

    @BeforeEach
    void setUp() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Matemáticas");

        alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombre("Pedro");
        alumno.setApellido("González");
        alumno.setEdad(20);
        alumno.setCurso(curso);
    }

    @Test
    public void testGetAllAlumnos() throws Exception {
        when(alumnoService.findAll()).thenReturn(List.of(alumno));

        mockMvc.perform(get("/api/alumnos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Pedro"))
                .andExpect(jsonPath("$[0].apellido").value("González"))
                .andExpect(jsonPath("$[0].edad").value(20));
    }

    @Test
    public void testGetAlumnoById() throws Exception {
        when(alumnoService.findById(1)).thenReturn(alumno);

        mockMvc.perform(get("/api/alumnos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.apellido").value("González"))
                .andExpect(jsonPath("$.edad").value(20));
    }

    @Test
    public void testCreateAlumno() throws Exception {
        when(alumnoService.save(any(Alumno.class))).thenReturn(alumno);

        mockMvc.perform(post("/api/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumno)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.apellido").value("González"))
                .andExpect(jsonPath("$.edad").value(20));
    }

    @Test
    public void testUpdateAlumno() throws Exception {
        when(alumnoService.save(any(Alumno.class))).thenReturn(alumno);

        mockMvc.perform(put("/api/alumnos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumno)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.apellido").value("González"))
                .andExpect(jsonPath("$.edad").value(20));
    }

    @Test
    public void testDeleteAlumno() throws Exception {
        doNothing().when(alumnoService).deleteById(1);

        mockMvc.perform(delete("/api/alumnos/1"))
                .andExpect(status().isOk());

        verify(alumnoService, times(1)).deleteById(1);
    }
}
