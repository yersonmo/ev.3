package com.mainapp.mainapp.service;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.repository.AlumnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    private Alumno alumno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alumno = new Alumno(1, "Juan", "Pérez", 20, null);
    }

    @Test
    public void testFindAll() {
        when(alumnoRepository.findAll()).thenReturn(Arrays.asList(alumno));
        assertEquals(1, alumnoService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(alumnoRepository.findById(1)).thenReturn(Optional.of(alumno));
        assertEquals("Juan", alumnoService.findById(1).getNombre());
    }

    @Test
    public void testSave() {
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);
        assertNotNull(alumnoService.save(alumno));
    }

    @Test
    public void testDeleteById() {
        alumnoService.deleteById(1);
        verify(alumnoRepository, times(1)).deleteById(1);
    }
}