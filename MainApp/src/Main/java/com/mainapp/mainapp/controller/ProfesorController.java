package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.service.ProfesorService;
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
@RequestMapping("/api/profesores")
@Tag(name = "Profesores", description = "Operaciones relacionadas con los profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    @Operation(summary = "Obtener todos los profesores", description = "Obtiene una lista de todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class)))
    })
    public List<Profesor> getAllProfesores() {
        return profesorService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un profesor por ID", description = "Obtiene un profesor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    public Profesor getProfesorById(@PathVariable Integer id) {
        return profesorService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo profesor", description = "Crea un nuevo profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class)))
    })
    public Profesor createProfesor(@RequestBody Profesor profesor) {
        return profesorService.save(profesor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un profesor", description = "Actualiza los datos de un profesor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    public Profesor updateProfesor(@PathVariable Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        return profesorService.save(profesor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un profesor", description = "Elimina un profesor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    public void deleteProfesor(@PathVariable Integer id) {
        profesorService.deleteById(id);
    }
}
