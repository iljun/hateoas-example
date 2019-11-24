package com.iljun.hateoasexample.support;

import com.iljun.hateoasexample.domains.user.User;
import com.iljun.hateoasexample.domains.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class CommandRunner implements CommandLineRunner {

    public CommandRunner(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = User
                .builder()
                .username("test01")
                .phoneNumber("1234566787")
                .email("test01@naver.com")
                .build();
        User user2 = User
                .builder()
                .username("test02")
                .phoneNumber("123456234")
                .email("test02@naver.com")
                .build();

        Collection<User> users = Arrays.asList(user1, user2);
        users.stream().forEach(user -> {
            userRepository.save(user);
        });
    }
}
