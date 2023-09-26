package com.ricarte.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ricarte.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity <List<User>> findAll(){
		List<User> list = new ArrayList<>();
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User tadeu = new User("1", "Tadeu Brown", "tadeu@gmail.com");
		list.addAll(Arrays.asList(maria, tadeu));
		return ResponseEntity.ok().body(list);
	}

}
