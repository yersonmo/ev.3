package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.CursoService;
import com.mainapp.mainapp.assembler.CursoModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los cursos", description = "Obtiene una lista de todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class)))
    })
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoController.class).getAllCursos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por ID", description = "Obtiene un curso específico por su ID")
    public EntityModel<Curso> getCursoById(@PathVariable Integer id) {
        return assembler.toModel(cursoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso")
    public EntityModel<Curso> createCurso(@Valid @RequestBody Curso curso) {
        return assembler.toModel(cursoService.save(curso));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso", description = "Actualiza los datos de un curso existente")
    public EntityModel<Curso> updateCurso(@PathVariable Integer id, @Valid @RequestBody Curso curso) {
        curso.setId(id);
        return assembler.toModel(cursoService.save(curso));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso por su ID")
    public void deleteCurso(@PathVariable Integer id) {
        cursoService.deleteById(id);
    }
}
