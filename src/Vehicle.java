public class Vehicle {
    private String id;
    private String brand;
    private String model;
    double basePricePerHour;
    private Driver driver;
    private boolean isAvailable = true;

    Vehicle(String id,String brand,String model,double basePricePerHour,Driver driver){
        this.id=id;
        this.brand=brand;
        this.model=model;
        this.basePricePerHour=basePricePerHour;
        this.driver=driver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBasePricePerHour() {
        return basePricePerHour;
    }

    public void setBasePricePerHour(double basePricePerHour) {
        this.basePricePerHour = basePricePerHour;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public double calculatePrice(int rentalHour){
        return basePricePerHour*rentalHour;
    }
    public void rent(){
        isAvailable=false;
    }
    public void returnVehicle(){
        isAvailable=true;
    }
    public void displayVehicleInfo() {
        System.out.println(getId()+" - "+getBrand()+" "+getModel()+" "+"(Base price per hour: "+getBasePricePerHour()+")");
    }

}