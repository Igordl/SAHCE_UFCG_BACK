package com.ufcg.sahce.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sahce.domain.user.MyUser;
import com.ufcg.sahce.domain.user.MyUserLogin;
import com.ufcg.sahce.domain.user.UserReset;
import com.ufcg.sahce.repository.UserRepository;
import com.ufcg.sahce.service.EmailService;
import com.ufcg.sahce.service.MyUserService;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public MyUser save(MyUser user) {
        return userRepository.save(user);
    }

    @Override
    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public MyUser findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Não existe usuário cadastrado com esse id."));
    }

    @Override
    public MyUser update(MyUser user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public MyUserLogin login(MyUser user) {
        MyUser userReturn = userRepository.login(user.getEmail(), user.getPassword()).orElseThrow(
                () -> new RuntimeException("Não existe usuário cadastrado com esse e-mail."));
        return new MyUserLogin(userReturn.getName(),
                userReturn.getEmail(),
                userReturn.getUserType(),
                userReturn.getFlAtivo());
    }

    @Override
    public void requestResetPassword(String email) {
        MyUser user = findByEmail(email);
        String password = generateCommonLangPassword();

        user.setPassword(password);
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "[SAHCE-UFCG] - Recuperação de senha",
                "Foi solicitado o reset da senha para o seu email no nosso aplicativo.\n\n\nSeu token para recuperação de senha é: "
                        + password
                        + "\n\n\nPara continuar utilizando nosso sistema continue com o processo de recuperação de senha.\n\nObrigado.");

    }

    private String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(3);
        String specialChar = RandomStringUtils.random(1, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

    @Override
    public MyUser resetPassword(UserReset userReset) {
        MyUser userQuery = findByEmail(userReset.getEmail());
        if (userQuery.getPassword().equals(userReset.getToken())) {
            userQuery.setPassword(userReset.getPassword());
            userRepository.save(userQuery);
            return userQuery;
        }
        return null;

    }

    @Override
    public MyUser findByEmail(String emailUser) {
        return userRepository.findByEmail(emailUser).orElseThrow(
                () -> new RuntimeException("Não existe usuário cadastrado com esse e-mail."));
    }

    @Override
    public MyUser activeUser(String emailUser) {
        MyUser userQuery = findByEmail(emailUser);
        userQuery.setFlAtivo("S");
        return update(userQuery);
    }

    @Override
    public MyUser inactiveUser(String emailUser) {
        MyUser userQuery = findByEmail(emailUser);
        userQuery.setFlAtivo("N");
        return update(userQuery);
    }

}
