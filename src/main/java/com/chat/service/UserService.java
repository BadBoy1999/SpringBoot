package com.chat.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chat.model.*;
import com.chat.repository.ChatRepository;
import com.chat.repository.RoleRepository;
import com.chat.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	private ChatRepository chatRepository;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired 
	RoleRepository roleRepository;
	
	public void save(UserDTO userdto){
		Role role = roleRepository.findByRoleName("ROLE_USER");
		System.out.println(role.getRoleName());	
		User user = new User();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
		user.setUsername(userdto.getUsername());
		userRepository.save(user);
	
	
	}
	 public void saveComment(Chat chat) {
		 User user = getCurrentUser();
		 chat.setUser(user);
		 user.addComment(chat);
		 chatRepository.save(chat);
		 
	 }
	 public User getCurrentUser() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String username =  auth.getName();
	      User user = userRepository.findByUsername(username);
	      return user;
	 }
}
