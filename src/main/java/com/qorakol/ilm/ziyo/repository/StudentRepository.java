package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
