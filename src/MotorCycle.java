public class MotorCycle extends Vehicle{
    private String genderOfDriver;

    public MotorCycle(String id, String brand, String model, Driver driver, double basePricePerHour, String genderOfDriver) {
        super(id, brand, model,basePricePerHour,driver);
        this.genderOfDriver = genderOfDriver;
    }

    public String getGenderOfDriver() {
        return genderOfDriver;
    }

    public void setGenderOfDriver(String genderOfDriver) {
        this.genderOfDriver = genderOfDriver;
    }
}