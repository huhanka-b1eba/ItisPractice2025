package aa.tulybaev.news.user.console;

import aa.tulybaev.news.user.dto.UserDto;
import aa.tulybaev.news.user.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserConsole {

    private final Scanner scanner;
    private final UserService userService;

    public UserConsole(UserService userService) {
        this.scanner = new Scanner(System.in);
        this.userService = userService;
    }

    public void signUp() {
        System.out.println("Введите данные пользователя для регистрации:");
        System.out.println("Имя: ");
        String username = scanner.nextLine();
        System.out.println("Пароль: ");
        String password = scanner.nextLine();
        System.out.println("Возраст: ");
        Integer age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Почта: ");
        String email = scanner.nextLine();

        System.out.println("Регистрация прошла успешно");

        userService.signUp(username, password, email, age);
    }

    public void printUsers() {
        List<UserDto> users = userService.getUsers();

        for (UserDto user : users) {
            System.out.println(user.getId() + "|" + user.getUsername());
        }
    }

    public void updatePassword() {
        printUsers();
        System.out.println();
        System.out.println("Введите id пользователя для обновления пароля: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите новый пароль: ");
        String password = scanner.nextLine();
        System.out.println("Пароль успешно обновлен.");
        System.out.println();
        try {
            userService.updatePassword(id, password);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
