package aa.tulybaev.user.repository;

import aa.tulybaev.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    // обновляет запись в хранилище данных по id-пользователя, заменяя все старые данные новыми
    void update(User user);

    // метод для получения пользователя по id
    Optional<User> findById(String uuid);

}
