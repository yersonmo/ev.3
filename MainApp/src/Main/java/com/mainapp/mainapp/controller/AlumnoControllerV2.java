package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.service.AlumnoService;
import com.mainapp.mainapp.assemblers.AlumnoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/alumnos")
public class AlumnoControllerV2 {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AlumnoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Alumno>> getAllAlumnos() {
        List<EntityModel<Alumno>> alumnos = alumnoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(alumnos,
                linkTo(methodOn(AlumnoControllerV2.class).getAllAlumnos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Alumno> getAlumnoById(@PathVariable Integer id) {
        Alumno alumno = alumnoService.findById(id);
        return assembler.toModel(alumno);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Alumno>> createAlumno(@RequestBody Alumno alumno) {
        Alumno newAlumno = alumnoService.save(alumno);
        return ResponseEntity
                .created(linkTo(methodOn(AlumnoControllerV2.class).getAlumnoById(newAlumno.getId())).toUri())
                .body(assembler.toModel(newAlumno));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Alumno>> updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        alumno.setId(id);
        Alumno updatedAlumno = alumnoService.save(alumno);
        return ResponseEntity.ok(assembler.toModel(updatedAlumno));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAlumno(@PathVariable Integer id) {
        alumnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
