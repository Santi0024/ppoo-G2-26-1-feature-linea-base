package com.example.tutoria002.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tutoria002.models.Teacher;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, UUID> {
    
    Optional<Teacher> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
    
    boolean existsByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}