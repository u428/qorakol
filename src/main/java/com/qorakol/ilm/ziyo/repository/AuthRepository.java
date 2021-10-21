package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.dto.AuthDto;
import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

}
