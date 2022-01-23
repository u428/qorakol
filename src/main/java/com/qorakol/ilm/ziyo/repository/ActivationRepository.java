package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Activation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivationRepository extends JpaRepository<Activation, Long> {

    List<Activation> findAllByGroupIdAndDeleteIsFalse(Long id);

    Activation findByStudentIdAndDeleteIsFalse(Long id);

    Activation findByStudentIdAndGroupId(Long ids, Long idg);
    Activation findByStudentIdAndGroupIdAndDeleteIsFalse(Long studentId, Long groupId);




}
