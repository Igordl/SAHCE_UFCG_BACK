package com.ufcg.sahce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufcg.sahce.domain.user.MyUser;
import com.ufcg.sahce.domain.user.MyUserLogin;
import com.ufcg.sahce.domain.user.UserReset;

@Service
public interface MyUserService {

        MyUser save(MyUser user);

        List<MyUser> findAll();

        MyUser findById(Long id);

        MyUser update(MyUser user);

        void deleteById(Long id);

        MyUserLogin login(MyUser user);

        void requestResetPassword(String email);

        MyUser resetPassword(UserReset userReset);

        MyUser findByEmail(String emailUser);

        MyUser activeUser(String emailUser);

        MyUser inactiveUser(String emailUser);
}
