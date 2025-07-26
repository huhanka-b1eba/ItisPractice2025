package aa.tulybaev.news.user.repository;

import aa.tulybaev.news.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    void update(User user);

    Optional<User> findById(Integer uuid);
}
