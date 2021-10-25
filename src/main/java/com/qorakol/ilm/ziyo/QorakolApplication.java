package com.qorakol.ilm.ziyo;

import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.model.entity.Language;
import com.qorakol.ilm.ziyo.model.entity.Roles;
import com.qorakol.ilm.ziyo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QorakolApplication {

	public static void main(String[] args) {
		SpringApplication.run(QorakolApplication.class, args);
	}


	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}



}
