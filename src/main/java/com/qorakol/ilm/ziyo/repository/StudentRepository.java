package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.constant.StudentStatus;
import com.qorakol.ilm.ziyo.model.dto.ChartDto;
import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import com.qorakol.ilm.ziyo.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {

    Student findByAuthEntity(AuthEntity authEntity);
    Page<Student> findByStatusAndDeleteIsFalse(StudentStatus studentStatus, Pageable pageable);
    Student findByIdAndDeleteIsFalse(Long id);
    List<Student> findAllByFirstNameContainingAndDeleteIsFalse(String name);
    List<Student> findAllByStatusAndFirstNameContainingAndDeleteIsFalse(StudentStatus studentStatus,String name);
    long countByStatusAndDeleteIsFalse(StudentStatus studentStatus);
    long countByDeleteIsFalse();



    @Query(value="select new com.qorakol.ilm.ziyo.model.dto.ChartDto(" +
            "count(st.id)," +
            "st.createdAt)" +
            "from Student st where st.delete = false " +
            "group by DATEADD(MONTH, DATEDIFF(MONTH, 0, st.createdAt), 0)")
    public List<ChartDto> findStatisticsChart();


}
