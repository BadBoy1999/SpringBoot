package com.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chat.model.UserDTO;
import com.chat.repository.UserRepository;
import com.chat.service.UserService;

@Controller
public class MainController {
	
@Autowired
UserService userServive;

@Autowired
UserRepository userRepository;
	
@RequestMapping("/")
public String start() {
	return "hello";
}
@RequestMapping("/login")
public String login() {
	return "login";
}

@RequestMapping("/index")
public String index(@ModelAttribute("userdto") UserDTO userdto , BindingResult result) {
	return "index";
}
@RequestMapping( value="/reg", method = RequestMethod.GET)
public String reg(@ModelAttribute("userdto") UserDTO userdto , BindingResult result){
	return "reg";
}
@RequestMapping( value="/save", method = RequestMethod.POST)
public String save(@ModelAttribute("userdto") UserDTO userdto , BindingResult result){
	com.chat.model.User exist = userRepository.findByUsername(userdto.getUsername());
	if(exist != null){
		System.out.println("User now exist");
		return "reg";
	}
	else {
		userServive.save(userdto);
	}
	return "chat";
}
@RequestMapping("/chat")
public String chat() {
	return "chat";
}
}
