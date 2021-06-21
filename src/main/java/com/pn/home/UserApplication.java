package com.pn.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pn.home.constants.AppConstants;

@SpringBootApplication
@RestController
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@RequestMapping(value = AppConstants.HEALTH_CHECK_URL, method = RequestMethod.GET)
	public String home() {
		return AppConstants.HEALTH_CHECK;
	}
}