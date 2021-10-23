package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

//    @Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from sys_auth_entity s where s.login = :login", nativeQuery = true)
//    boolean findByLoginExists(String login);

    AuthEntity findByLogin(String login);

    boolean existsByLogin(String login);
}
