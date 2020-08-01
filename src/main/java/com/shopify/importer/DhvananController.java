package com.shopify.importer;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shopify.importer.model.Student;


@RestController
public class DhvananController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/dhvanan")
	private String demo() {
		
		
		String uri = "https://"+id+":"+password+api;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(id, password);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		ResponseEntity<Response> response = restTemplate.exchange(uri+ProductConstant.ALL_PRODUCT_API, HttpMethod.GET, entity, Response.class);
		
		Student dhvaman = new Student();
		dhvaman.setName("Dhvanan");
		studentRepository.save(dhvaman);
		return "response ayo bhai";
	}

}
