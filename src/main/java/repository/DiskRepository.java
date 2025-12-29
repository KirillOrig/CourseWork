package repository;

import model.VideoDisk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DiskRepository {

    private String file = "data/disks.txt";
    private String rentalFile = "data/rentals.txt";

    public void save(VideoDisk disk) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(disk + "\n");
            fw.close();
        } catch (IOException e) {}
    }

    public List<VideoDisk> findAll() {
        List<VideoDisk> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                list.add(new VideoDisk(Integer.parseInt(p[0]), p[1], p[2],
                        Integer.parseInt(p[3]), p[4]));
            }
            br.close();
        } catch (IOException e) {}
        return list;
    }

    public boolean isDiskInRental(int id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(rentalFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(";" + id + ";") && line.endsWith("false")) return true;
            }
            br.close();
        } catch (IOException e) {}
        return false;
    }

    public void deleteById(int id) {
        List<VideoDisk> disks = findAll();
        List<VideoDisk> result = new ArrayList<>();
        for (VideoDisk d : disks) {
            if (d.getId() != id) result.add(d);
        }
        try {
            PrintWriter pw = new PrintWriter(file);
            for (VideoDisk d : result) pw.println(d);
            pw.close();
        } catch (IOException e) {}
    }
}
