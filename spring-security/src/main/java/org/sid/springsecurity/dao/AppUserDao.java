package org.sid.springsecurity.dao;

import org.sid.springsecurity.bean.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
