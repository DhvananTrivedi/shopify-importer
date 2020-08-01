package com.shopify.importer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.shopify.importer.model.Student;

@Service
public interface StudentRepository extends JpaRepository<Student, Long>{

}
