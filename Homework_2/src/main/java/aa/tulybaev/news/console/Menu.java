package aa.tulybaev.news.console;

import aa.tulybaev.news.user.console.UserConsole;

import java.util.Scanner;


public class Menu {

    private final Scanner scanner;

    private final UserConsole userConsole;

    public Menu(UserConsole userConsole) {
        this.scanner = new Scanner(System.in);
        this.userConsole = userConsole;
    }

    public void run() {

        while (true) {

            printMenu();

            String userResponse = scanner.nextLine();

            switch (userResponse) {
                case "1":
                    userConsole.signUp();
                    break;
                case "2":
                    userConsole.printUsers();
                    break;
                case "3":
                    userConsole.updatePassword();
                    break;
                case "0":
                    System.exit(0);
            }
        }
    }

    public void printMenu() {
        System.out.println("Работа с пользователем");
        System.out.println("1. Регистрация");
        System.out.println("2. Список всех пользователей");
        System.out.println("3. Обновить пароль");
        System.out.println("0. Завершение работы");
    }
}
