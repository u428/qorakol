package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.dto.ChartDto;
import com.qorakol.ilm.ziyo.model.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

    @Query(value = "SELECT * FROM student_payment p WHERE p.id < :id AND ORDER BY p.date", nativeQuery = true)
    Payments findByIdAndOrderByDate(Long id);


    @Query(value="select new com.qorakol.ilm.ziyo.model.dto.ChartDto(" +
            "count(pay.id)," +
            "pay.time)" +
            "from Payments pay where pay.paymentStatus = com.qorakol.ilm.ziyo.constant.PaymentStatus.ALLOWED " +
            "group by pay.time")
    public List<ChartDto> findStatisticsChart();


}
