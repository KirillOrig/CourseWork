package model;

public class VideoDisk {

    private int id;
    private String title;
    private String genre;
    private int year;
    private String director;

    public VideoDisk(int id, String title, String genre, int year, String director) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return id + ";" + title + ";" + genre + ";" + year + ";" + director;
    }
}
