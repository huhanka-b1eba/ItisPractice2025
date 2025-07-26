package aa.tulybaev.news.user.service;

import aa.tulybaev.news.user.dto.UserDto;
import aa.tulybaev.news.user.entity.User;
import aa.tulybaev.news.user.repository.UserRepository;

import java.util.List;

public class UserService {

    private final int MIN_PASSWORD_LENGTH = 7;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String username, String password, String email, Integer age) {

        validatePassword(password);

        User user = new User(username, password, email, age);

        userRepository.save(user);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getUsername()))
                .toList();
    }

    public void updatePassword(Integer id, String newPassword) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPassword(newPassword);

        userRepository.update(user);
    }

    private void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must be at least 7 characters");
        }
    }
}

