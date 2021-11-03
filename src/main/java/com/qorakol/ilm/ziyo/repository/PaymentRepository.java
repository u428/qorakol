package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

    @Query(value = "SELECT * FROM student_payment p WHERE p.id < :id AND ORDER BY p.date", nativeQuery = true)
    Payments findByIdAndOrderByDate(Long id);


}
