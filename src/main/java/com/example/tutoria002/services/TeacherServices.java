package com.example.tutoria002.services;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tutoria002.exceptions.TeacherAlreadyExistsException;
import com.example.tutoria002.exceptions.TeacherNotFoundException;
import com.example.tutoria002.models.Teacher;
import com.example.tutoria002.repositories.ITeacherRepository;

@Service
public class TeacherServices {
    
    @Autowired
    private ITeacherRepository teacherRepository;

    public ArrayList<Teacher> getAllTeachers() {
        return (ArrayList<Teacher>) teacherRepository.findAll();
    }

    public Teacher save(Teacher teacher) {
        // Validación: No se puede crear un Docente con el mismo tipo de documento y cédula
        if (teacherRepository.existsByTipoDocumentoAndNumeroDocumento(
                teacher.getTipoDocumento(), teacher.getNumeroDocumento())) {
            throw new TeacherAlreadyExistsException(
                "Ya existe un docente con tipo de documento: " + teacher.getTipoDocumento() + 
                " y número: " + teacher.getNumeroDocumento()
            );
        }
        return teacherRepository.save(teacher);
    }

    public Teacher update(Teacher teacher) {
        // Validación: Verificar que exista previamente por ID
        if (!teacherRepository.existsById(teacher.getId())) {
            throw new TeacherNotFoundException(
                "No se encontró el docente con ID: " + teacher.getId()
            );
        }
        
        // Validación adicional: verificar que no haya duplicados con otro docente
        teacherRepository.findByTipoDocumentoAndNumeroDocumento(
                teacher.getTipoDocumento(), teacher.getNumeroDocumento()
        ).ifPresent(existingTeacher -> {
            if (!existingTeacher.getId().equals(teacher.getId())) {
                throw new TeacherAlreadyExistsException(
                    "Ya existe otro docente con tipo de documento: " + teacher.getTipoDocumento() + 
                    " y número: " + teacher.getNumeroDocumento()
                );
            }
        });
        
        return teacherRepository.save(teacher);
    }

    public boolean delete(UUID id) {
        // Validación: Verificar que exista previamente por ID
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException(
                "No se encontró el docente con ID: " + id
            );
        }
        teacherRepository.deleteById(id);
        return true;
    }
}