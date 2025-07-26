package aa.tulybaev.user.repository.impl.file;

import aa.tulybaev.user.entity.User;

public class UserFileUtil {
    public static String toList(User user) {
        return user.getUuid() + "|" + user.getName() + "|" + user.getPassword();
    }

    public static User fromList(String line) {
        String[] parts = line.split("\\|");

        return new User(parts[0], parts[1], parts[2]);
    }
}
