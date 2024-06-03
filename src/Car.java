import java.io.BufferedWriter;
import java.io.FileWriter;

public class Car extends Vehicle{
    private int seatNumber;
    public Car(int seatNumber, String id, String brand, String model,Driver driver, double basePricePerHour) {
        super(id, brand, model, basePricePerHour,driver);
        this.seatNumber=seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }



}