package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Groups;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {

    List<Groups> findAllByTeacherIdAndDeleteIsFalse(Long id);
    Page<Groups> findAllByDeleteIsFalse(Pageable pageable);

    Groups findByActivationIdAndDeleteIsFalse(Long id);

    List<Groups> findAllByNameContainingAndDeleteFalse(String name);

}
