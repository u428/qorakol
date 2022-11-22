package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import com.qorakol.ilm.ziyo.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    Teacher findByAuthEntity(AuthEntity authEntity);

    Teacher findByAuthIdAndDeleteIsFalse(Long id);

    Page<Teacher> findAllByDeleteIsFalse(Pageable pageable);
    Page<Teacher> findAllByDeleteIsFalseOrderByIdDesc(Pageable pageable);

    List<Teacher> findAllByDeleteIsFalse();

    Optional<Teacher> findByIdAndDeleteIsFalse(Long id);

    List<Teacher> findAllByFirstNameContainsAndDeleteIsFalse(String name);
    List<Teacher> findAllByFirstNameContainingAndDeleteIsFalse(String name);

    Optional<Teacher> findById(Long id);

}
