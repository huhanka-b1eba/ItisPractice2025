package aa.tulybaev.app;

import aa.tulybaev.console.Menu;
import aa.tulybaev.user.console.UserConsole;
import aa.tulybaev.user.repository.impl.file.UserRepositoryFile;
import aa.tulybaev.user.repository.impl.list.UserRepositoryList;
import aa.tulybaev.user.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepositoryFile userRepositoryFile = new UserRepositoryFile("user.txt");
        UserRepositoryList userRepositoryList = new UserRepositoryList();

        UserService userService = new UserService(userRepositoryFile);

        UserConsole userConsole = new UserConsole(userService);

        Menu menu = new Menu(userConsole);

        menu.run();
    }
}
