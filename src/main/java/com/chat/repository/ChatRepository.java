package com.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.Chat;

public interface ChatRepository extends JpaRepository<Chat,Long>{

}
