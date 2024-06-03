public class Bus extends Vehicle{
    private boolean isAC;

    public Bus(String id, String brand, String model, Driver driver, double basePricePerHour, boolean isAC) {
        super(id, brand, model, basePricePerHour,driver);
        this.isAC = isAC;
    }

    public void setAC(boolean AC) {
        isAC = AC;
    }

    public boolean isAC() {
        return isAC;
    }

}