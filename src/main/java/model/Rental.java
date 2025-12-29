package model;

public class Rental {

    private int rentalId;
    private String userPhone;
    private int diskId;
    private String issueDate;
    private String returnDate;
    private boolean returned;

    public Rental(int rentalId, String userPhone, int diskId,
                  String issueDate, String returnDate) {
        this.rentalId = rentalId;
        this.userPhone = userPhone;
        this.diskId = diskId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.returned = false;
    }

    public int getDiskId() {
        return diskId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String toString() {
        return rentalId + ";" + userPhone + ";" + diskId + ";" +
                issueDate + ";" + returnDate + ";" + returned;
    }
}
