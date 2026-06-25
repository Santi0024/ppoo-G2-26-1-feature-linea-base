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
import com.example.tutoria002.models.Student;
import com.example.tutoria002.services.StudentServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/student")
@Tag(name = "Student Controller", description = "API para gestión de estudiantes")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @GetMapping
    @Operation(summary = "Obtener todos los estudiantes", description = "Retorna una lista de todos los estudiantes registrados")
    public ResponseEntity<ArrayList<Student>> getAll() {
        return ResponseEntity.ok(studentServices.getAllStudents());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo estudiante", description = "Crea un nuevo estudiante en el sistema")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.ok(studentServices.save(student));
    }

    @PutMapping
    @Operation(summary = "Actualizar un estudiante", description = "Actualiza los datos de un estudiante existente")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return ResponseEntity.ok(studentServices.update(student));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un estudiante", description = "Elimina un estudiante por su ID")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}