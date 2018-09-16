package com.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chat.model.Chat;
import com.chat.model.User;
import com.chat.model.UserDTO;
import com.chat.repository.UserRepository;
import com.chat.service.UserService;

@Controller
public class MainController {
	
@Autowired
UserService userService;

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
public String save(@ModelAttribute("userdto") UserDTO userdto , BindingResult result , Model model){
	com.chat.model.User exist = userRepository.findByUsername(userdto.getUsername());
	if(exist != null){
		System.out.println("User now exist");
		model.addAttribute("user","User now exist");
		return "reg";
	}
	else {
		userService.save(userdto);
	}
	return "chat";
}
@RequestMapping("/chat")
public String chat(@ModelAttribute("chat")Chat chat, BindingResult result , Model model) {
	return "chat";
}
@RequestMapping("/comments")
public String comments(@ModelAttribute("chat")Chat chat, BindingResult result , Model model){
	userService.saveComment(chat);
	User user = userService.getCurrentUser(); 
	model.addAttribute("comments",user.getChat());
	return "chat";
}
}
