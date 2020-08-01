package com.shopify.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.importer.model.Student;


@RestController
public class DhvananController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/dhvanan")
	private String demo() {
		
		

		
		Student ddt = new Student();
		ddt.setId(3445l);
		ddt.setName("Dhvanan Tri");
		studentRepository.save(ddt);
		return "response ayo bhai";
	}

}
