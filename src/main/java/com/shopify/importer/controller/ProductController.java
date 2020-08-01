package com.shopify.importer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.importer.StudentRepository;
import com.shopify.importer.model.Student;


@RestController
@RequestMapping("/shopify/product")
@EnableScheduling
public class ProductController {


	@Autowired(required = false)
	private StudentRepository studentRepository;

	@GetMapping("/addProducts")
	private String demo() {

		studentRepository.save(new Student());
		return "response ayo bhai";
	}

}
