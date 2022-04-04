package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Attendances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendances, Long> {

    List<Attendances> findAllByCountedIsFalseOrderById();

    List<Attendances> findAllByCountedIsTrueAndActivationId(Long id);

    List<Attendances> findAllByActivationIdAndDeleteIsFalse(Long id);

    long countAllByActivationIdAndDeleteIsFalse(Long id);



}
