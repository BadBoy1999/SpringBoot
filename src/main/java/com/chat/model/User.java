package com.chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="user_id")
private Long id;

@Column(name ="username")
private String username;

@Column(name ="password")
private String password;

 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Chat>chat;
 
 public void addComment(Chat com) {
	 chat.add(com);
 }
  public List <Chat> getChat(){
	  return chat;
  }
  
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles;

public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}
public User() {
	
}
public Set<Role> getRoles() {
	return roles;
}
public void setRoles(Set<Role> roles) {
	this.roles = roles;
}
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


}
