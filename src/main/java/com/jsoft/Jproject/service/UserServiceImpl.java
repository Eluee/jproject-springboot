package com.jsoft.Jproject.service;

import com.jsoft.Jproject.model.User;
import com.jsoft.Jproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // 생성자 주입 (권장 방식)
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    

    @Override
    @Transactional
    public User createUser(User user) {
        // 비즈니스 로직: 이메일 중복 체크
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"이미 존재하는 이메일입니다.");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user); // 사용자 저장
    }


    public User updateUser(String email, User userUpdates) {
        User existingUser = userRepository.findByEmail(email).orElseThrow(() -> 
            new RuntimeException("이메일로 찾는 사용자 없음: " + email));

        // 사용자 필드 업데이트
        existingUser.setName(userUpdates.getName());
        existingUser.setDifflevel(userUpdates.getDifflevel());
        existingUser.setCorrect_rate(userUpdates.getCorrect_rate());

        return userRepository.save(existingUser);
    }
}
