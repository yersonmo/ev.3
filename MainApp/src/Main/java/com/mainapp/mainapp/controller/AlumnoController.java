package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Alumnos", description = "Operaciones relacionadas con los alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    @Operation(summary = "Obtener todos los alumnos", description = "Obtiene una lista de todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class)))
    })
    public List<Alumno> getAllAlumnos() {
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un alumno por ID", description = "Obtiene un alumno específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public Alumno getAlumnoById(@PathVariable Integer id) {
        return alumnoService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo alumno", description = "Crea un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class)))
    })
    public Alumno createAlumno(@RequestBody Alumno alumno) {
        return alumnoService.save(alumno);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un alumno", description = "Actualiza los datos de un alumno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public Alumno updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        alumno.setId(id);
        return alumnoService.save(alumno);
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
