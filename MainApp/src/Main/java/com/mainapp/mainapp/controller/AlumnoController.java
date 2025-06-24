package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.service.AlumnoService;
import com.mainapp.mainapp.assembler.AlumnoModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Alumnos", description = "Operaciones relacionadas con los alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AlumnoModelAssembler assembler;

    @GetMapping
    @Operation(
        summary = "Obtener todos los alumnos",
        description = "Devuelve la lista completa de alumnos registrados"
    )
    public CollectionModel<EntityModel<Alumno>> getAllAlumnos() {
        List<EntityModel<Alumno>> alumnos = alumnoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(alumnos,
                linkTo(methodOn(AlumnoController.class).getAllAlumnos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener un alumno por ID",
        description = "Devuelve los datos de un alumno específico por su ID"
    )
    public EntityModel<Alumno> getAlumnoById(@PathVariable Integer id) {
        return assembler.toModel(alumnoService.findById(id));
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo alumno",
        description = "Registra un nuevo alumno en el sistema"
    )
    public EntityModel<Alumno> createAlumno(@Valid @RequestBody Alumno alumno) {
        Alumno saved = alumnoService.save(alumno);
        return assembler.toModel(saved);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un alumno",
        description = "Modifica los datos de un alumno existente"
    )
    public EntityModel<Alumno> updateAlumno(@PathVariable Integer id, @Valid @RequestBody Alumno alumno) {
        alumno.setId(id);
        return assembler.toModel(alumnoService.save(alumno));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un alumno",
        description = "Elimina un alumno por su ID"
    )
    public void deleteAlumno(@PathVariable Integer id) {
        alumnoService.deleteById(id);
    }
}
