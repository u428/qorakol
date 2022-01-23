package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.ActivationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationDetailsRepository extends JpaRepository<ActivationDetails, Long> {

    ActivationDetails findByActivationIdAndDeleteIsFalse(Long id);

    long countByStatusAndDeleteIsFalse(boolean status);
}
