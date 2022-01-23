package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.constant.StudentStatus;
import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import com.qorakol.ilm.ziyo.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByAuthEntity(AuthEntity authEntity);

    Page<Student> findByStatusAndDeleteIsFalse(StudentStatus studentStatus, Pageable pageable);

    long countByStatusAndDeleteIsFalse(StudentStatus studentStatus);
    long countByDeleteIsFalse();
}
