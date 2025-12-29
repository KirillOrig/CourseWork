package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {

    private String file = "data/users.txt";

    public void save(String ln, String fn, String mn, String addr, String phone) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(new User(ln, fn, mn, addr, phone) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи пользователей");
        }
    }

    public List<String> findAll() {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (IOException e) {}
        return list;
    }

    public boolean existsByPhone(String phone) {
        List<String> users = findAll();
        for (String u : users) {
            if (u.endsWith(phone)) return true;
        }
        return false;
    }

    public void deleteByPhone(String phone) {
        List<String> users = findAll();
        List<String> result = new ArrayList<>();
        for (String u : users) {
            if (!u.endsWith(phone)) result.add(u);
        }
        rewrite(result);
    }

    public void updateByPhone(String phone, Scanner scanner) {
        List<String> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).endsWith(phone)) {
                String[] p = users.get(i).split(";");
                System.out.print("Новый адрес: ");
                String address = scanner.nextLine();
                users.set(i, p[0] + ";" + p[1] + ";" + p[2] + ";" + address + ";" + phone);
            }
        }
        rewrite(users);
    }

    private void rewrite(List<String> list) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (String u : list) pw.println(u);
            pw.close();
        } catch (IOException e) {}
    }
}
