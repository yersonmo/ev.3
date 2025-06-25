package com.mainapp.mainapp.service;

import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso curso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso(1, "Matemáticas", null, null);
    }

    @Test
    public void testFindAll() {
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso));
        assertEquals(1, cursoService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        assertEquals("Matemáticas", cursoService.findById(1).getNombre());
    }

    @Test
    public void testSave() {
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);
        assertNotNull(cursoService.save(curso));
    }

    @Test
    public void testDeleteById() {
        cursoService.deleteById(1);
        verify(cursoRepository, times(1)).deleteById(1);
    }
}