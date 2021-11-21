package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import com.qorakol.ilm.ziyo.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByAuthEntity(AuthEntity authEntity);

    List<Teacher> findAllByDeleteIsFalse();
}
