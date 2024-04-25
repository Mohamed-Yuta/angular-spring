package com.allali.backend.repositories;

import com.allali.backend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByCode(String code);
}
