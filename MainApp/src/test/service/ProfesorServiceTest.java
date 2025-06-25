package com.mainapp.mainapp.service;

import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    private Profesor profesor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profesor = new Profesor(1, "Carlos", "Sánchez", 45, null);
    }

    @Test
    public void testFindAll() {
        when(profesorRepository.findAll()).thenReturn(Arrays.asList(profesor));
        assertEquals(1, profesorService.findAll().size());
    }

    @Test
    public void testFindById() {
        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));
        assertEquals("Carlos", profesorService.findById(1).getNombre());
    }

    @Test
    public void testSave() {
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);
        assertNotNull(profesorService.save(profesor));
    }

    @Test
    public void testDeleteById() {
        profesorService.deleteById(1);
        verify(profesorRepository, times(1)).deleteById(1);
    }
}