package aa.tulybaev.user.console;

import aa.tulybaev.user.dto.UserDTO;
import aa.tulybaev.user.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserConsole {

    private final Scanner scanner;

    private final UserService userService;

    public UserConsole(UserService userService) {
        this.userService = userService;
        scanner = new Scanner(System.in);
    }

    public void signUp() {
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        userService.signUp(name, password);
    }

    public void printUsers() {
        List<UserDTO> users = userService.getUsers();

        for (UserDTO user : users) {
            System.out.println(user.getUuid() + "|" + user.getName());
        }
    }

    public void updatePassword() {
        printUsers();
        System.out.println();
        System.out.println("Введите id пользователя для обновления пароля: ");
        String uuid = scanner.nextLine();
        System.out.println("Введите новый пароль: ");
        String password = scanner.nextLine();
        System.out.println("Пароль успешно обновлен.");
        System.out.println();
        try {
            userService.updatePassword(uuid, password);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
