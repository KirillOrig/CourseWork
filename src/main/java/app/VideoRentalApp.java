package app;

import service.DiskService;
import service.RentalService;
import service.UserService;

import java.util.Scanner;

public class VideoRentalApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        DiskService diskService = new DiskService();
        RentalService rentalService = new RentalService();

        boolean work = true;

        while (work) {
            System.out.println();
            System.out.println("1 - Добавить пользователя");
            System.out.println("2 - Изменить пользователя");
            System.out.println("3 - Удалить пользователя");
            System.out.println("4 - Добавить диск");
            System.out.println("5 - Удалить диск");
            System.out.println("6 - Выдать диск");
            System.out.println("7 - Вернуть диск");
            System.out.println("8 - Найти пользователя");
            System.out.println("9 - Найти диск");
            System.out.println("0 - Выход");
            System.out.println("Введите цифру (от 0 до 9) для выбора действия:");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                userService.addUser(scanner);
            } else if (choice.equals("2")) {
                userService.updateUser(scanner);
            } else if (choice.equals("3")) {
                userService.deleteUser(scanner);
            } else if (choice.equals("4")) {
                diskService.addDisk(scanner);
            } else if (choice.equals("5")) {
                diskService.deleteDisk(scanner);
            } else if (choice.equals("6")) {
                rentalService.issueDisk(scanner);
            } else if (choice.equals("7")) {
                rentalService.returnDisk(scanner);
            } else if (choice.equals("8")) {
                userService.findUser(scanner);
            } else if (choice.equals("9")) {
                diskService.findDisk(scanner);
            } else if (choice.equals("0")) {
                work = false;
            } else {
                System.out.println("Неверный ввод");
            }
        }
    }
}
