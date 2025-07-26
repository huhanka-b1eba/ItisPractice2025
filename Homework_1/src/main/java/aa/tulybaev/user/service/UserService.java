package aa.tulybaev.user.service;

import aa.tulybaev.user.dto.UserDTO;
import aa.tulybaev.user.entity.User;
import aa.tulybaev.user.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    private final static int MIN_PASSWORD_LENGTH = 7;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String password) {

        extracted(password);

        User user = new User(name, password);

        userRepository.save(user);
    }

    private static void extracted(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) throw new IllegalArgumentException("Длинна пароля должна быть минимум " + MIN_PASSWORD_LENGTH);
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().
                map(user -> new UserDTO(user.getUuid(), user.getName())).
                toList();
    }

    public void updatePassword(String uuid, String newPassword) {
        extracted(newPassword);

        User user = userRepository
                .findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPassword(newPassword);

        userRepository.update(user);
    }

}
