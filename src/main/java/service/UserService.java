package service;

import repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void addUser(Scanner scanner) {
        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Отчество: ");
        String middleName = scanner.nextLine();
        System.out.print("Адрес: ");
        String address = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();

        if (userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь с таким телефоном уже существует");
            return;
        }

        userRepository.save(lastName, firstName, middleName, address, phone);
        System.out.println("Пользователь добавлен");
    }

    public void updateUser(Scanner scanner) {
        System.out.print("Введите телефон пользователя для изменения: ");
        String phone = scanner.nextLine();

        if (!userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь не найден");
            return;
        }

        userRepository.updateByPhone(phone, scanner);
        System.out.println("Информация обновлена");
    }

    public void deleteUser(Scanner scanner) {
        System.out.print("Введите телефон пользователя для удаления: ");
        String phone = scanner.nextLine();

        if (!userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь не найден");
            return;
        }

        userRepository.deleteByPhone(phone);
        System.out.println("Пользователь удалён");
    }

    public void findUser(Scanner scanner) {
        System.out.print("Введите фамилию пользователя для поиска: ");
        String lastName = scanner.nextLine();

        List<String> users = userRepository.findAll();
        boolean found = false;
        for (String u : users) {
            if (u.startsWith(lastName + ";")) {
                System.out.println(u);
                found = true;
            }
        }
        if (!found) System.out.println("Пользователь не найден");
    }
}
