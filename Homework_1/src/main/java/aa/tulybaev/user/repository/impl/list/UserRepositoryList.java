package aa.tulybaev.user.repository.impl.list;

import aa.tulybaev.user.entity.User;
import aa.tulybaev.user.repository.UserRepository;

import java.util.*;

public class UserRepositoryList implements UserRepository {

    List<User> userList;

    public UserRepositoryList() {
        this.userList = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        String uuid = UUID.randomUUID().toString();

        user.setUuid(uuid);

        userList.add(user);
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUuid().equals(user.getUuid())) {
                userList.set(i, user);
                return;
            }
        }
    }

    @Override
    public Optional<User> findById(String uuid) {
        for (User user : userList) {
            if (user.getUuid().equals(uuid)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
