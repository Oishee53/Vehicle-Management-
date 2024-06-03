public class Rental {
    private Car car;
    private Bus bus;
    private MotorCycle motorCycle;
    private Customer customer;
    private int hours;

    public Rental(Car car, Customer customer,int hours) {
        this.car = car;
        this.customer = customer;
        this.hours = hours;
    }
    public Rental(Bus bus, Customer customer,int hours) {
        this.bus = bus;
        this.customer = customer;
        this.hours = hours;
    }
    public Rental(MotorCycle motorCycle, Customer customer,int hours) {
        this.motorCycle = motorCycle;
        this.customer = customer;
        this.hours = hours;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    public void setMotorCycle(MotorCycle motorCycle) {
        this.motorCycle = motorCycle;
    }

    public MotorCycle getMotorCycle() {
        return motorCycle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getHours() {
        return hours;
    }

    public void setDays(int days) {
        this.hours = hours;
    }
}
