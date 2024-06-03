import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalSystem {
    ArrayList <Car> cars = new ArrayList<>();
    ArrayList <Bus> buses = new ArrayList<>();
    ArrayList <MotorCycle> motorCycles = new ArrayList<>();
    ArrayList <Rental> rentals = new ArrayList<>();
    ArrayList <Customer> customers = new ArrayList<>();
    ArrayList <Driver> drivers = new ArrayList<>();
    private String passWord;

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void addCar(Car car){
        cars.add(car);
    }
    public void addBus(Bus bus){
        buses.add(bus);
    }
    public void addMotorCycle(MotorCycle motorCycle){
        motorCycles.add(motorCycle);
    }
    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void addDriver(Driver driver){
        drivers.add(driver);
    }

    public void rentCar(Car car, Customer customer, int hours){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, hours));
        }
        else
            System.out.println("Car is not available for rent.");

    }
    public void rentBus(Bus bus, Customer customer, int hours){
        if(bus.isAvailable()){
            bus.rent();
            rentals.add(new Rental(bus, customer, hours));
        }
        else
            System.out.println("Bus is not available for rent.");

    }
    public void rentMotorCycle(MotorCycle motorCycle, Customer customer, int hours){
        if(motorCycle.isAvailable()){
            motorCycle.rent();
            rentals.add(new Rental(motorCycle, customer, hours));
        }
        else
            System.out.println("Motorcycle is not available for rent.");

    }
    public void returnCar(Car car) {
        car.returnVehicle();
        Rental rentalToRemove = null;
        for(Rental rental:rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
        }
        else
            System.out.println("Car was not rented");


    }
    public void returnBus(Bus bus) {
        bus.returnVehicle();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getBus() == bus) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
        } else
            System.out.println("Bus was not rented");
    }
    public void returnMotorCycle(MotorCycle motorCycle)
    {
        motorCycle.returnVehicle();
        Rental rentalToRemove = null;
        for(Rental rental:rentals){
            if(rental.getMotorCycle() == motorCycle){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
        }
        else
            System.out.println("Motorcycle was not rented");

    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("***** Vehicle Rental System *****");
            System.out.println("Enter your choice:");
            System.out.println("1. Rent vehicle");
            System.out.println("2. Return vehicle");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            //If they choose to rent
            if (choice == 1) {

                System.out.println("Choose what do you want to rent?");
                System.out.println("1. Car");
                System.out.println("2. Bus");
                System.out.println("3. Motorcycle");
                int choice1 = scanner.nextInt();
                //If they choose to rent a car
                if (choice1 == 1) {
                    System.out.println("Select number of seats");
                    System.out.println("1. 5 seats");
                    System.out.println("2. 8 seats");
                    int choice2 = scanner.nextInt();

                    if (choice2 == 1) {
                        System.out.println("Available 5 seater cars:");
                        for (Car car : cars) {
                            if (car.isAvailable()) {
                                if (car.getSeatNumber() == 5) {
                                    car.displayVehicleInfo();
                                }
                            }
                        }
                    }
                    else if (choice2 == 2) {
                        System.out.println("Available 8 seater cars:");
                        for (Car car : cars) {
                            if (car.isAvailable()) {
                                if (car.getSeatNumber() ==8) {
                                    car.displayVehicleInfo();
                                }
                            }
                        }
                    }

                    System.out.println("Enter the car ID you want to rent:");
                    String carID = scanner.next();

                    System.out.println("Enter the number of hours for rental:");
                    int rentalHours = scanner.nextInt();

                    System.out.println("Enter your name:");
                    String customerName = scanner.next();

                    System.out.println("Enter your phone number:");
                    String customerPhoneNumber = scanner.next();


                    Customer customer = new Customer("CUS" + customers.size() + 1, customerName, customerPhoneNumber);

                    Car selectedCar = null;
                    for (Car car : cars) {
                        if (car.getId().equals(carID) && car.isAvailable()) {
                            selectedCar = car;
                            break;
                        }
                    }
                    if (selectedCar != null) {
                        double totalPrice = selectedCar.calculatePrice(rentalHours);
                        System.out.println("===Rental Information===");
                        System.out.println("Customer ID:" + customer.getCustomerId());
                        System.out.println("Customer Name:" + customer.getName());
                        System.out.println("Customer phone number:" + customer.getPhoneNumber());
                        System.out.println("Car: " + selectedCar.getBrand() +" "+selectedCar.getModel());
                        System.out.println("Driver: "+selectedCar.getDriver().getName()+"("+selectedCar.getDriver().getId()+")");
                        System.out.println("Driver's contact number: "+selectedCar.getDriver().getPhoneNumber());
                        System.out.println("Rental hours: " + rentalHours);
                        System.out.println("Total price:" + totalPrice + " taka");

                        System.out.println("Confirm rental(Y/N):");

                        String confirm = scanner.next();

                        if (confirm.equals("Y")) {
                            rentCar(selectedCar, customer, rentalHours);
                            System.out.println("Car rented successfully");
                        } else
                            System.out.println("Rental cancelled.");
                    }
                    else
                        System.out.println("Invalid car selected or car not available for rent.");

                }
                //If chose to rent a bus
                else if (choice1 == 2) {
                    System.out.println("/n What type of bus do you want? /n");
                    System.out.println("1. AC Bus");
                    System.out.println("2. Non AC Bus");
                    int choice2 = scanner.nextInt();
                    if (choice2 == 1) {
                        System.out.println("Available AC Buses:");
                        for (Bus bus : buses) {
                            if (bus.isAvailable()) {
                                if (bus.isAC()) {
                                    bus.displayVehicleInfo();
                                }
                            }
                        }
                    } else if (choice2 == 2) {
                        System.out.println("Available Non AC Buses:");
                        for (Bus bus : buses) {
                            if (bus.isAvailable()) {
                                if (!bus.isAC()) {
                                    bus.displayVehicleInfo();
                                }
                            }
                        }
                    }
                    System.out.println("Enter the bus ID you want to rent:");
                    String busID = scanner.next();

                    System.out.println("Enter the number of hours for rental:");
                    int rentalHours = scanner.nextInt();
                    System.out.println("Enter your name:");
                    String customerName = scanner.next();

                    System.out.println("Enter your phone number:");
                    String customerPhoneNumber = scanner.next();


                    Customer customer = new Customer("CUS" + customers.size() + 1, customerName, customerPhoneNumber);

                    Bus selectedBus = null;
                    for (Bus bus : buses) {
                        if (bus.getId().equals(busID) && bus.isAvailable()) {
                            selectedBus = bus;
                            break;
                        }
                    }
                    if (selectedBus != null) {
                        double totalPrice = selectedBus.calculatePrice(rentalHours);
                        System.out.println("===Rental Information===");
                        System.out.println("Customer ID:" + customer.getCustomerId());
                        System.out.println("Customer Name:" + customer.getName());
                        System.out.println("Customer phone number:" + customer.getPhoneNumber());

                        System.out.println("Bus: " + selectedBus.getBrand() +" "+ selectedBus.getModel());
                        System.out.println("Driver: "+selectedBus.getDriver().getName()+"("+selectedBus.getDriver().getId()+")");
                        System.out.println("Rider's contact number: "+selectedBus.getDriver().getPhoneNumber());
                        System.out.println("Rental hours: " + rentalHours);
                        System.out.println("Total price:" + totalPrice + " taka");

                        System.out.println("Confirm rental(Y/N)");

                        String confirm = scanner.next();

                        if (confirm.equals("Y")) {
                            rentBus(selectedBus, customer, rentalHours);
                            System.out.println("Bus rented successfully");
                        } else
                            System.out.println("Rental cancelled.");
                    } else
                        System.out.println("Invalid bus selected or bus not available for rent.");

                }
                // If chose to rent a motorcycle
                else if (choice1 == 3) {
                    System.out.println(" What do you want to be your rider?");
                    System.out.println("1. Male");
                    System.out.println("2. Female");
                    int choice2 = scanner.nextInt();

                    if (choice2 == 1) {
                        System.out.println("Available motorcycle with male rider:");
                        for (MotorCycle motorCycle : motorCycles) {
                            if (motorCycle.isAvailable()) {
                                if (motorCycle.getGenderOfDriver().equals("male")) {
                                    motorCycle.displayVehicleInfo();
                                }
                            }
                        }
                    }
                    else if (choice2 == 2) {
                        System.out.println("Available motorcycle with female rider:");
                        for (MotorCycle motorCycle : motorCycles) {
                            if (motorCycle.isAvailable()) {
                                if (motorCycle.getGenderOfDriver().equals("female")) {
                                    motorCycle.displayVehicleInfo();
                                }
                            }
                        }
                    }
                    else
                        System.out.println("Invalid choice. Please enter a valid option");

                    System.out.println("Enter the motorcycle ID you want to rent:");
                    String motorCycleID = scanner.next();

                    System.out.println("Enter the number of hours for rental:");
                    int rentalHours = scanner.nextInt();

                    System.out.println("Enter your first name:");
                    String customerName = scanner.next();


                    System.out.println("Enter your phone number:");
                    String customerPhoneNumber = scanner.next();


                    Customer customer = new Customer("CUS" + customers.size() + 1, customerName, customerPhoneNumber);

                    MotorCycle selectedMotorycle = null;
                    for (MotorCycle motorCycle : motorCycles) {
                        if (motorCycle.getId().equals(motorCycleID) && motorCycle.isAvailable()) {
                            selectedMotorycle = motorCycle;
                            break;
                        }
                    }
                    if (selectedMotorycle != null) {
                        double totalPrice = selectedMotorycle.calculatePrice(rentalHours);
                        System.out.println("===Rental Information===");
                        System.out.println("Customer ID:" + customer.getCustomerId());
                        System.out.println("Customer Name:" + customer.getName());
                        System.out.println("Customer phone number:" + customer.getPhoneNumber());

                        System.out.println("Motorcycle: " + selectedMotorycle.getBrand() +" "+ selectedMotorycle.getModel());
                        System.out.println("Driver: "+selectedMotorycle.getDriver().getName()+"("+selectedMotorycle.getDriver().getId()+")");
                        System.out.println("Driver's contact number: "+selectedMotorycle.getDriver().getPhoneNumber());
                        System.out.println("Rental hours: " + rentalHours);
                        System.out.println("Total price:" + totalPrice + "Taka");

                        System.out.println("Confirm rental(Y/N)");
                        String confirm = scanner.next();

                        if (confirm.equals("Y")) {
                            rentMotorCycle(selectedMotorycle, customer, rentalHours);
                            System.out.println("Motorcycle rented successfully");
                        } else
                            System.out.println("Rental cancelled.");
                    }
                    else
                        System.out.println("Invalid car selected or car not available for rent.");
                }
            }


            //Chose return
            else if (choice == 2) {
                System.out.println("/n Choose what do you want to return? /n");
                System.out.println("1. Car");
                System.out.println("2. Bus");
                System.out.println("3. Motorcycle");
                int choice1 = scanner.nextInt();

                //Selected car
                if (choice1 == 1) {
                    System.out.println("=== Return a car===");
                    System.out.println("Enter the carID you want to return");
                    String carID = scanner.next();

                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getId().equals(carID) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }

                    if (carToReturn != null) {
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getCar() == carToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnCar(carToReturn);
                            System.out.println("Car returned successfully by " + customer.getName());
                        } else {
                            System.out.println("Car was not rented or rental information is missing.");
                        }
                    } else {
                        System.out.println("Invalid car ID or car is not rented.");


                    }
                }

                //Selected bus
                else if (choice1 == 2) {
                    System.out.println("=== Return a bus===");
                    System.out.println("Enter the busID you want to return");
                    String busID = scanner.next();

                    Bus busToReturn = null;
                    for (Bus bus : buses) {
                        if (bus.getId().equals(busID) && !bus.isAvailable()) {
                            busToReturn = bus;
                            break;
                        }
                    }

                    if (busToReturn != null) {
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getBus() == busToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnBus(busToReturn);
                            System.out.println("Bus returned successfully by " + customer.getName());
                        } else {
                            System.out.println("Car was not rented or rental information is missing.");
                        }
                    } else {
                        System.out.println("Invalid bus ID or bus is not rented.");
                    }
                }


                //Selected motorcycle
                else if (choice1 == 3) {
                    System.out.println("=== Return a motorcycle===");
                    System.out.println("Enter the motorcycleID you want to return");
                    String motorcycleID = scanner.next();

                    MotorCycle motorCycleToReturn = null;
                    for (MotorCycle motorCycle : motorCycles) {
                        if (motorCycle.getId().equals(motorcycleID) && !motorCycle.isAvailable()) {
                            motorCycleToReturn = motorCycle;
                            break;
                        }
                    }

                    if (motorCycleToReturn != null) {
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getMotorCycle() == motorCycleToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnMotorCycle(motorCycleToReturn);
                            System.out.println("Motorcycle returned successfully by " + customer.getName());
                        } else {
                            System.out.println("Car was not rented or rental information is missing.");
                        }
                    } else {
                        System.out.println("Invalid motorcycle ID or motorcycle is not rented.");
                    }
                } else
                    System.out.println("Invalid choice. Please enter a valid option");
            }
            //Chose to exit
            else if (choice == 3) {
                break;
            }
            else {
                System.out.println("Invalid choice. Please enter a valid option");
            }
        }
    }
    public void fiveSeaterCar(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available five seater cars"))){
            for(Car car: cars){
                if(car.isAvailable() && car.getSeatNumber()==5){
                    writer.write(car.getId() + " " + car.getBrand() + " " + car.getModel());
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void eightSeaterCar(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available eight seater cars"))){
            for(Car car: cars){
                if(car.isAvailable() && car.getSeatNumber()==8){
                    writer.write(car.getId() + " " + car.getBrand() + " " + car.getModel());
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void ACBus(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available AC Buses"))){
            for(Bus bus: buses){
                if(bus.isAvailable() && bus.isAC()){
                    writer.write(bus.getId() + " " + bus.getBrand() + " " + bus.getModel());
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void nonACBus(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available non-AC Buses"))){
            for(Bus bus: buses){
                if(bus.isAvailable() && !bus.isAC()){
                    writer.write(bus.getId() + " " + bus.getBrand() + " " + bus.getModel());
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void motorCycleWithFemaleDriver(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available motorcycle with female driver"))){
            for(MotorCycle motorCycle: motorCycles){
                if(motorCycle.isAvailable() && motorCycle.getGenderOfDriver().equals("female")){
                    writer.write(motorCycle.getId() + " " + motorCycle.getBrand() + " " + motorCycle.getModel());
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void motorCycleWithMaleDriver(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available motorcycle with male driver"))){
            for(MotorCycle motorCycle: motorCycles){
                if(motorCycle.isAvailable() && motorCycle.getGenderOfDriver().equals("male")){
                    writer.write(motorCycle.getId() + " " + motorCycle.getBrand() + " " + motorCycle.getModel());
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void rentalToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Rental Details"))){
            for(Rental rental:rentals){
                if(rental.getCar()!= null)
                    writer.write(rental.getCustomer().getCustomerId() + " rents " + rental.getCar().getId() + " car for " + rental.getHours() + " hours.");
                else if(rental.getBus()!=null)
                    writer.write(rental.getCustomer().getCustomerId() + " rents " + rental.getBus().getId() + " bus for " + rental.getHours() + " hours.");
                else if(rental.getMotorCycle()!= null)
                    writer.write(rental.getCustomer().getCustomerId() + " rents " + rental.getMotorCycle().getId() + " motorcycle for " + rental.getHours() + " hours.");

            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void addCarToRentalSystem(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter carID:");
        String newCarID = scanner.next();

        System.out.println("Enter brand of the car:");
        String newBrand = scanner.next();

        System.out.println("Enter model of the car:");
        String newModel = scanner.next();

        System.out.println("Enter seat number:");
        int seatNumber = scanner.nextInt();

        System.out.println("Enter base price of car:");
        double basePrice = scanner.nextDouble();

        System.out.println("Enter driver's name:");
        String driverName = scanner.next();

        System.out.println("Enter driver's phone number:");
        String driverPhoneNumber = scanner.next();

        System.out.println("Enter driver's gender");
        String driverGender = scanner.next();
        Driver driver = new Driver(driverName, driverPhoneNumber, "R00" + drivers.size() + 1,driverGender);
        Car car = new Car(seatNumber, newCarID , newBrand, newModel, driver, basePrice);
        drivers.add(driver);
        cars.add(car);
    }

    public void addBusToRentalSystem(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter busID:");
        String newBusID = scanner.next();

        System.out.println("Enter brand of the bus:");
        String newBrand = scanner.next();

        System.out.println("Enter model of the bus:");
        String newModel = scanner.next();

        System.out.println("Is AC available?");
        boolean isAC= scanner.nextBoolean();

        System.out.println("Enter base price of bus:");
        double basePrice = scanner.nextDouble();

        System.out.println("Enter driver's name:");
        String driverName = scanner.next();

        System.out.println("Enter driver's phone number:");
        String driverPhoneNumber = scanner.next();

        System.out.println("Enter driver's gender");
        String driverGender = scanner.next();
        Driver driver = new Driver(driverName, driverPhoneNumber, "R00" + drivers.size() + 1,driverGender);
        Bus bus = new Bus(newBusID , newBrand, newModel, driver, basePrice, isAC);
        drivers.add(driver);
        buses.add(bus);
    }

    public void addMotorCycleToRentalSystem(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter motorcycleID:");
        String newMotorCycleID = scanner.next();

        System.out.println("Enter brand of the motorcycle:");
        String newBrand = scanner.next();

        System.out.println("Enter model of the motorcycle:");
        String newModel = scanner.next();

        System.out.println("Enter base price of motorcycle:");
        double basePrice = scanner.nextDouble();

        System.out.println("Enter driver's name:");
        String driverName = scanner.next();

        System.out.println("Enter driver's phone number:");
        String driverPhoneNumber = scanner.next();

        System.out.println("Enter driver's gender:");
        String driverGender = scanner.next();

        Driver driver = new Driver(driverName, driverPhoneNumber, "R00" + drivers.size() + 1,driverGender);
        MotorCycle motorCycle = new MotorCycle(newMotorCycleID,newBrand,newModel,driver,basePrice,driverGender);
        drivers.add(driver);
        motorCycles.add(motorCycle);
    }

    public void removeCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter carID:");
        String carID = scanner.next();
        Car carToBeRemoved = null;
        for(Car car: cars){
            if(car.getId().equals(carID)) {
                carToBeRemoved = car;
                break;
            }
        }
        if(carToBeRemoved != null){
            cars.remove(carToBeRemoved);
        }
        else
            System.out.println("Invalid carID");
    }

    public void removeBus(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter busID:");
        String busID = scanner.next();
        Bus busToBeRemoved = null;
        for(Bus bus: buses){
            if(bus.getId().equals(busID)){
                busToBeRemoved = bus;
                break;
            }
        }
        if(busToBeRemoved != null){
            buses.remove(busToBeRemoved);
        }
        else
            System.out.println("Invalid busID.");
    }
    public void removeMotorcycle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter motorcycleID:");
        String motorcycleID = scanner.next();
        MotorCycle motorCycleToBeRemoved = null;
        for(MotorCycle motorCycle: motorCycles) {
            if (motorCycle.getId().equals(motorcycleID)) {
                motorCycleToBeRemoved = motorCycle;
            }
        }
        if(motorCycleToBeRemoved != null){
            motorCycles.remove(motorCycleToBeRemoved);
        }
        else
            System.out.println("Invalid motorcycleID");
    }
    public void displayCars(String filename) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (FileNotFoundException ex) {
            sb.append("No data available yet.\n");
        } catch (IOException ex) {
            sb.append("Error reading file: ").append(ex.getMessage()).append("\n");
        }
    }

}




