package com.chat.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.*;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String rolename);
}
