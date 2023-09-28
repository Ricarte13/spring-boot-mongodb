package com.ricarte.workshopmongo.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ricarte.workshopmongo.domain.Post;
import com.ricarte.workshopmongo.domain.User;
import com.ricarte.workshopmongo.dto.AuthorDTO;
import com.ricarte.workshopmongo.dto.CommentsDTO;
import com.ricarte.workshopmongo.repository.PostRepository;
import com.ricarte.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, LocalDate.parse("21/03/2018", dtf), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, LocalDate.parse("23/03/2018", dtf), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentsDTO c1 = new CommentsDTO("Boa viagem mano!", LocalDate.parse("21/03/2018", dtf), new AuthorDTO(alex));
		CommentsDTO c2 = new CommentsDTO("Aproveite!", LocalDate.parse("22/03/2018", dtf), new AuthorDTO(bob));
		CommentsDTO c3 = new CommentsDTO("Tenha um ótimo dia!", LocalDate.parse("23/03/2018", dtf), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
