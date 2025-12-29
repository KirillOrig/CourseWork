package service;

import model.Rental;
import repository.RentalRepository;
import repository.UserRepository;
import repository.DiskRepository;

import java.util.List;
import java.util.Scanner;

public class RentalService {

    private RentalRepository rentalRepository = new RentalRepository();
    private UserRepository userRepository = new UserRepository();
    private DiskRepository diskRepository = new DiskRepository();

    public void issueDisk(Scanner scanner) {
        System.out.print("Телефон пользователя: ");
        String phone = scanner.nextLine();

        if (!userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь не найден");
            return;
        }

        if (rentalRepository.hasOverdueRentals(phone)) {
            System.out.println("У пользователя есть просроченные диски");
            return;
        }

        System.out.print("ID диска: ");
        int diskId = Integer.parseInt(scanner.nextLine());

        if (diskRepository.isDiskInRental(diskId)) {
            System.out.println("Диск сейчас в прокате");
            return;
        }

        int rentalId = rentalRepository.generateId();
        System.out.print("Дата выдачи (YYYY-MM-DD): ");
        String issueDate = scanner.nextLine();
        System.out.print("Дата возврата (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        Rental rental = new Rental(rentalId, phone, diskId, issueDate, returnDate);
        rentalRepository.save(rental);
        System.out.println("Диск выдан");
    }

    public void returnDisk(Scanner scanner) {
        System.out.print("ID диска для возврата: ");
        int diskId = Integer.parseInt(scanner.nextLine());

        boolean ok = rentalRepository.returnDisk(diskId);
        if (ok) {
            System.out.println("Диск возвращён");
        } else {
            System.out.println("Диск не найден в прокате");
        }
    }
}
