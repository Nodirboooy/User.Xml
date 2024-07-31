package uz.pdp;



import uz.pdp.model.User;
import uz.pdp.service.UserService;

import java.util.Scanner;

public class Main {
    static UserService userService = new UserService();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) {

        int stepCode = 20;
        while (stepCode != 0) {
            System.out.println("1.register, 2.login");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1 -> {
                    register();
                }
                case 2 -> {
                    login();
                }
            }
        }
    }

    private static void register() {
        User user = new User();
        System.out.println("Enter name");
        String name = scannerStr.nextLine();
        System.out.println("Enter username");
        String username = scannerStr.nextLine();
        System.out.println("Enter password");
        String password = scannerStr.nextLine();
        System.out.println("Enter age");
        int age = scannerInt.nextInt();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        User user1 = userService.addUser(user);
        if (user1 == null) {
            System.out.println("User allaqachon qo'shilgan");
        } else {
            System.out.println("User muvaffaqiyatli qo'shildi");
        }
    }

    private static void login() {
        System.out.println("Enter username");
        String username = scannerStr.nextLine();
        System.out.println("Enter password");
        String password = scannerStr.nextLine();
        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("Username yoki password xato");
        } else {
            System.out.println("Muvaffaqiyatli login");
        }
    }
}