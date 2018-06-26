package com.chat.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.*;

public interface UserRepository extends JpaRepository<User,Long>{
	 User findByUsername(String username);

}
