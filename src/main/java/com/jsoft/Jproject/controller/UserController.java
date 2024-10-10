package com.jsoft.Jproject.controller;

import com.jsoft.Jproject.model.User;
import com.jsoft.Jproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // React 앱의 주소
public class UserController {

    private final UserService userService;

    // 생성자 주입함
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    
    

    // 사용자 이메일로 조회
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 사용자 생성
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PatchMapping("/{email}")
    public ResponseEntity<User> updateUserPartially(@PathVariable String email, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userService.findByEmail(email);
        
        if (!existingUserOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // 사용자가 존재하지 않으면 404 반환
        }

        User existingUser = existingUserOptional.get();

        // difflevel과 correct_rate만 업데이트
        if (updatedUser.getDifflevel() != null) {
            existingUser.setDifflevel(updatedUser.getDifflevel());
        }
        if (updatedUser.getCorrect_rate() != 0) { // 기본값이 0인 경우를 피하기 위해 확인
            existingUser.setCorrect_rate(updatedUser.getCorrect_rate());
        }

        userService.save(existingUser); // 업데이트된 사용자 저장
        return ResponseEntity.ok(existingUser); // 업데이트된 사용자 반환
    }

    


}
