package com.iljun.hateoasexample.api;

import com.iljun.hateoasexample.domains.user.User;
import com.iljun.hateoasexample.domains.user.UserService;
import com.iljun.hateoasexample.domains.user.dto.UserAssembler;
import com.iljun.hateoasexample.domains.user.dto.request.UserRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    public UserApi(UserService userService,
                   UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    private UserService userService;
    private UserAssembler userAssembler;

    @GetMapping
    public ResponseEntity getUsers(Pageable pageable) {
        Page<User> users = userService.getUsers(pageable);
        return ResponseEntity.ok(users.getContent()
                .stream()
                .map(user -> userAssembler.toModel(user))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.create(userRequestDto);
        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity putUser(@PathVariable long userId, @RequestBody UserRequestDto userRequestDto) {
        User user = userService.update(userId, userRequestDto);
        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
