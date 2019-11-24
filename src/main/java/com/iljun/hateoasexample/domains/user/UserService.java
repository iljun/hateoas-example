package com.iljun.hateoasexample.domains.user;

import com.iljun.hateoasexample.domains.user.dto.request.UserRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public Page<User> getUsers(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUser(final long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
    }

    public User create(final UserRequestDto userRequestDto) {
        return userRepository.save(UserRequestDto.of(userRequestDto));
    }

    public User update(final long userId, final UserRequestDto userRequestDto) {
        User user = UserRequestDto.of(userRequestDto);
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        if (userId != userRequestDto.getUserId()) {
            throw new RuntimeException();
        }
        return userRepository.save(user);
    }

    public void delete(final long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
    }
}
