package org.sid.springsecurity.service;

import org.sid.springsecurity.bean.AppRole;
import org.sid.springsecurity.bean.AppUser;
import org.sid.springsecurity.dao.AppRoleDao;
import org.sid.springsecurity.dao.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AppUserDao appUserDao, AppRoleDao appRoleDao) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDao = appUserDao;
        this.appRoleDao = appRoleDao;
    }

    private AppUserDao appUserDao;
    private AppRoleDao appRoleDao;
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserDao.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleDao.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
      AppUser appUser=appUserDao.findByUsername(username);
      AppRole appRole=appRoleDao.findByRoleName(roleName);
      appUser.getAppRols().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserDao.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserDao.findAll();
    }
}
