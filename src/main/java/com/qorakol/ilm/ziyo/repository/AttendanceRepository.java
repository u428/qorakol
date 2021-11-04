package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Attendances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendances, Long>, JpaSpecificationExecutor<Attendances> {

    List<Attendances> findAllByCountedIsFalseOrderById();

    List<Attendances> findAllByCountedIsTrueAndActivationId(Long id);


    @Query(value = "SELECT * FROM student_attendance a WHERE a.counted = false AND a.activation_id = :id ORDER BY date LIMIT :r", nativeQuery = true)
    List<Attendances> findAllByCountedIsTrueAndActivationIdAndCounted(Long id, int r);


    Attendances findByActivationId(Long id);


}
