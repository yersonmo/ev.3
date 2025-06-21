package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.assembler.AlumnoModelAssembler;
import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Obtener todos los alumnos", description = "Obtiene una lista de todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class)))
    })
    public CollectionModel<EntityModel<Alumno>> getAllAlumnos() {
        List<EntityModel<Alumno>> alumnos = alumnoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(alumnos,
                linkTo(methodOn(AlumnoController.class).getAllAlumnos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un alumno por ID", description = "Obtiene un alumno específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public EntityModel<Alumno> getAlumnoById(@PathVariable Integer id) {
        return assembler.toModel(alumnoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo alumno", description = "Crea un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class)))
    })
    public EntityModel<Alumno> createAlumno(@RequestBody Alumno alumno) {
        Alumno saved = alumnoService.save(alumno);
        return assembler.toModel(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un alumno", description = "Actualiza los datos de un alumno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public EntityModel<Alumno> updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        alumno.setId(id);
        return assembler.toModel(alumnoService.save(alumno));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un alumno", description = "Elimina un alumno por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public void deleteAlumno(@PathVariable Integer id) {
        alumnoService.deleteById(id);
    }
}
