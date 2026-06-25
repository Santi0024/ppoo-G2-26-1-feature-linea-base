package com.example.tutoria002.controllers;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tutoria002.models.Teacher;
import com.example.tutoria002.services.TeacherServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/teacher")
@Tag(name = "Teacher Controller", description = "API para gestión de docentes")
public class TeacherController {

    @Autowired
    private TeacherServices teacherServices;

    @GetMapping
    @Operation(summary = "Obtener todos los docentes", description = "Retorna una lista de todos los docentes registrados")
    public ResponseEntity<ArrayList<Teacher>> getAll() {
        return ResponseEntity.ok(teacherServices.getAllTeachers());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo docente", description = "Crea un nuevo docente en el sistema")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherServices.save(teacher));
    }

    @PutMapping
    @Operation(summary = "Actualizar un docente", description = "Actualiza los datos de un docente existente")
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherServices.update(teacher));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un docente", description = "Elimina un docente por su ID")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(teacherServices.delete(id));
    }
}