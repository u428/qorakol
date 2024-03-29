package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {


    Roles findByName(RoleContants name);
}
