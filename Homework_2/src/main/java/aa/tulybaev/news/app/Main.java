package aa.tulybaev.news.app;

import aa.tulybaev.news.console.Menu;
import aa.tulybaev.news.user.console.UserConsole;
import aa.tulybaev.news.user.repository.UserRepository;
import aa.tulybaev.news.user.repository.impl.UserRepositoryJdbcImpl;
import aa.tulybaev.news.user.service.UserService;
import aa.tulybaev.news.util.jdbc.SimpleDataSource;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new SimpleDataSource("someData", "someData", "someData");
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);

        UserService userService = new UserService(userRepository);

        UserConsole userConsole = new UserConsole(userService);

        Menu menu = new Menu(userConsole);

        menu.run();
    }
}
