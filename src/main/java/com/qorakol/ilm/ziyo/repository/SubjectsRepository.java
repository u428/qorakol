package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

    Subjects findByIdAndDeleteIsFalse(Long id);

    List<Subjects> findAllByDeleteIsFalse();

    List<Subjects> findAllByIdIn(List<Long> ids);

}
