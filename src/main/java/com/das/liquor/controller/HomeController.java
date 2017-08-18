package com.das.liquor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value = "/home")
	@ResponseBody
	public ResponseEntity<String> home()
	{
		return new ResponseEntity<String>("My Liquor App" , HttpStatus.OK);
	}

}
