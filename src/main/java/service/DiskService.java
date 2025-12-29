package service;

import model.VideoDisk;
import repository.DiskRepository;

import java.util.List;
import java.util.Scanner;

public class DiskService {

    private DiskRepository diskRepository = new DiskRepository();

    public void addDisk(Scanner scanner) {
        System.out.print("ID диска: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Название: ");
        String title = scanner.nextLine();
        System.out.print("Жанр: ");
        String genre = scanner.nextLine();
        System.out.print("Год выпуска: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Режиссёр: ");
        String director = scanner.nextLine();

        VideoDisk disk = new VideoDisk(id, title, genre, year, director);
        diskRepository.save(disk);
        System.out.println("Диск добавлен");
    }

    public void deleteDisk(Scanner scanner) {
        System.out.print("ID диска для удаления: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (diskRepository.isDiskInRental(id)) {
            System.out.println("Диск сейчас в прокате, нельзя удалить");
            return;
        }

        diskRepository.deleteById(id);
        System.out.println("Диск удалён");
    }

    public void findDisk(Scanner scanner) {
        System.out.print("Название (или Enter для пропуска): ");
        String title = scanner.nextLine();
        System.out.print("Жанр (или Enter для пропуска): ");
        String genre = scanner.nextLine();
        System.out.print("Год (или Enter для пропуска): ");
        String yearStr = scanner.nextLine();
        System.out.print("Режиссёр (или Enter для пропуска): ");
        String director = scanner.nextLine();

        List<VideoDisk> disks = diskRepository.findAll();
        boolean found = false;
        for (VideoDisk d : disks) {
            boolean ok = true;
            if (!title.isEmpty() && !d.toString().contains(title)) ok = false;
            if (!genre.isEmpty() && !d.toString().contains(genre)) ok = false;
            if (!yearStr.isEmpty() && !d.toString().contains(yearStr)) ok = false;
            if (!director.isEmpty() && !d.toString().contains(director)) ok = false;

            if (ok) {
                System.out.println(d);
                found = true;
            }
        }
        if (!found) System.out.println("Диск не найден");
    }
}
