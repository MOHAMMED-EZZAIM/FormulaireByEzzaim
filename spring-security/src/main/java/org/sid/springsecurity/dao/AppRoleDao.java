package org.sid.springsecurity.dao;

import org.sid.springsecurity.bean.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleDao extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
