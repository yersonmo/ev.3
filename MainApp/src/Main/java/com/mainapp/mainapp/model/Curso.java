package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.CursoService;
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
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Obtener todos los cursos", description = "Obtiene una lista de todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class)))
    })
    public List<Curso> getAllCursos() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por ID", description = "Obtiene un curso específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso getCursoById(@PathVariable Integer id) {
        return cursoService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class)))
    })
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso", description = "Actualiza los datos de un curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso updateCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.save(curso);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public void deleteCurso(@PathVariable Integer id) {
        cursoService.deleteById(id);
    }
}
