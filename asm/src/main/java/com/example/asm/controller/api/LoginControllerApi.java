package com.example.asm.controller.api;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class Message{
	String message;

	public Message(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}

@RestController
@RequestMapping("api/login")
public class LoginControllerApi {
	@GetMapping("")
	public ResponseEntity<Message> index (){
		Message reponse = new Message("message data");
		return ResponseEntity.ok().body(reponse);
	}
}
