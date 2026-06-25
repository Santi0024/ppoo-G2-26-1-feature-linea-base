package com.example.tutoria002.services;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tutoria002.exceptions.StudentAlreadyExistsException;
import com.example.tutoria002.exceptions.StudentNotFoundException;
import com.example.tutoria002.models.Student;
import com.example.tutoria002.repositories.IStudentRepository;

@Service
public class StudentServices {
    
    @Autowired
    private IStudentRepository studentRepository;

    public ArrayList<Student> getAllStudents() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    public Student save(Student student) {
        // Validación: No se puede crear un estudiante con el mismo email
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentAlreadyExistsException(
                "Ya existe un estudiante con el email: " + student.getEmail()
            );
        }
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        // Validación: Verificar que exista previamente por ID
        if (!studentRepository.existsById(student.getId())) {
            throw new StudentNotFoundException(
                "No se encontró el estudiante con ID: " + student.getId()
            );
        }
        return studentRepository.save(student);
    }

    public void delete(UUID id) {
        // Validación: Verificar que exista previamente por ID
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                "No se encontró el estudiante con ID: " + id
            );
        }
        studentRepository.deleteById(id);
    }
}