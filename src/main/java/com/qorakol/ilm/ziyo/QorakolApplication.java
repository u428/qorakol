package com.qorakol.ilm.ziyo;

import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.model.entity.Roles;
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
	public BCryptPasswordEncoder encryp(){
		return new BCryptPasswordEncoder();
	}

	private void addRole(){
		Roles roles = new Roles();
		roles.setLevel(1);
		roles.setName(RoleContants.SUPER_ADMIN);

	}

}
