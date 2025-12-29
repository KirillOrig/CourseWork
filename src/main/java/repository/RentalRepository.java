package repository;

import model.Rental;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository {

    private String file = "data/rentals.txt";

    public void save(Rental rental) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(rental + "\n");
            fw.close();
        } catch (IOException e) {}
    }

    public List<Rental> findAll() {
        List<Rental> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                Rental r = new Rental(Integer.parseInt(p[0]), p[1], Integer.parseInt(p[2]), p[3], p[4]);
                r.setReturned(Boolean.parseBoolean(p[5]));
                list.add(r);
            }
            br.close();
        } catch (IOException e) {}
        return list;
    }

    public boolean hasOverdueRentals(String phone) { return false; }

    public boolean isDiskInRental(int diskId) {
        List<Rental> list = findAll();
        for (Rental r : list) {
            if (r.getDiskId() == diskId && !r.isReturned()) return true;
        }
        return false;
    }

    public boolean returnDisk(int diskId) {
        List<Rental> list = findAll();
        boolean found = false;
        for (Rental r : list) {
            if (r.getDiskId() == diskId && !r.isReturned()) {
                r.setReturned(true);
                found = true;
            }
        }
        if (found) rewrite(list);
        return found;
    }

    public int generateId() { return findAll().size() + 1; }

    private void rewrite(List<Rental> list) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (Rental r : list) pw.println(r);
            pw.close();
        } catch (IOException e) {}
    }
}
