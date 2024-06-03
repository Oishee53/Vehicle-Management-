import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {
    static ArrayList<Car> cars = new ArrayList<>();
    ArrayList <Driver> drivers = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    JButton submitButton;
    JButton submitButton2;
    JButton submitButton3;

    JCheckBox fiveSeaterCar;
    JCheckBox eightSeaterCar;
    JTextField textField;
    public static void addCar1(Car car){
        cars.add(car);
    }
    public static void rentalToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Car Rental Details"))){
            writer.write("Car Rental Details:\n");
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

    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int hours){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, hours));
        }
        else
            System.out.println("Car is not available for rent.");

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



    public void carRentalMethod() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);

        JLabel label = new JLabel("What type of car do you want?");
        fiveSeaterCar = new JCheckBox("5 seater car");
        eightSeaterCar = new JCheckBox("8 seater car");
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        this.add(label);
        this.add(fiveSeaterCar);
        this.add(eightSeaterCar);
        this.add(submitButton);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            this.getContentPane().removeAll();
            String filename = null;

            if (fiveSeaterCar.isSelected() && !eightSeaterCar.isSelected()) {
                JLabel label = new JLabel("Available 5-seater cars:");
                this.add(label);
                filename = "Available five seater cars";
            } else if (eightSeaterCar.isSelected() && !fiveSeaterCar.isSelected()) {
                JLabel label = new JLabel("Available 8-seater cars:");
                this.add(label);
                filename = "Available eight seater cars";
            } else if (fiveSeaterCar.isSelected() && eightSeaterCar.isSelected()) {
                JLabel label = new JLabel("Available 5-seater and 8-seater cars:");
                this.add(label);
                filename = "Available five and eight seater cars"; // Placeholder for combined data
            } else {
                JLabel label = new JLabel("No car type selected");
                this.add(label);
                this.revalidate();
                this.repaint();
                JButton okButton = new JButton("OK");
                MyFrame.this.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new HomePage();
                    }
                });
            }

            displayCars(filename);
        }
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

        JTextArea displayArea = new JTextArea(sb.toString());
        displayArea.setEditable(false);
        this.add(new JScrollPane(displayArea));

        JLabel label1 = new JLabel("Enter the carID of the car you want to rent:");
        JTextField carID = new JTextField();
        this.add(label1);
        this.add(carID);

        JLabel label2 = new JLabel("Enter the number of hours for rental:");
        JTextField rentalHours = new JTextField();
        this.add(label2);
        this.add(rentalHours);

        JLabel label3 = new JLabel("Enter customer's name:");
        JTextField customerName = new JTextField();
        this.add(label3);
        this.add(customerName);

        JLabel label4 = new JLabel("Enter customer's phone number:");
        JTextField customerPhoneNumber = new JTextField();
        this.add(label4);
        this.add(customerPhoneNumber);

        submitButton2 = new JButton("Submit");
        this.add(submitButton2);

        submitButton2.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                String carIdText = carID.getText();
                int rentalHoursText = Integer.parseInt(rentalHours.getText());
                String customerNameText = customerName.getText();

                String customerPhoneNumberText = customerPhoneNumber.getText();

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getId().equals(carIdText)) {
                        selectedCar = car;
                        break;
                    }
                }
               try{
                    if(!customerPhoneNumberText.matches("\\d{11}")){
                          throw new PhoneNumberException("InvalidMobileNumberException occured");
                    }
                }
                catch (PhoneNumberException g){
                    System.out.println(g.getMessage());
                    g.printStackTrace();
                }

                if (selectedCar != null) {
                    Customer customer = new Customer("CUS" + (customers.size() + 1), customerNameText, customerPhoneNumberText);
                    customers.add(customer);

                    double totalPrice = selectedCar.calculatePrice(rentalHoursText);

                    JTextArea textArea = new JTextArea("Rental System");
                    MyFrame.this.add(textArea);
                    MyFrame.this.add(new JTextArea("Customer ID: " + customer.getCustomerId()));
                    MyFrame.this.add(new JTextArea("Customer Name: " + customer.getName()));
                    MyFrame.this.add(new JTextArea("Customer phone number: " + customer.getPhoneNumber()));
                    MyFrame.this.add(new JTextArea("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel()));
                    MyFrame.this.add(new JTextArea("Driver: " + selectedCar.getDriver().getName() + " (" + selectedCar.getDriver().getId() + ")"));
                    MyFrame.this.add(new JTextArea("Driver's contact number: " + selectedCar.getDriver().getPhoneNumber()));
                    MyFrame.this.add(new JTextArea("Rental hours: " + rentalHoursText));
                    MyFrame.this.add(new JTextArea("Total price: " + totalPrice + " taka"));
                    JLabel confirm = new JLabel("Confirm(Y/N)");
                    JTextField confirm1 = new JTextField();
                    MyFrame.this.add(confirm);
                    MyFrame.this.add(confirm1);
                    submitButton3 = new JButton("Submit");
                    MyFrame.this.add(submitButton3);


                    Car finalSelectedCar = selectedCar;
                    submitButton3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String confirmed = confirm1.getText();
                            if (confirmed.equals("Y")) {
                                rentCar(finalSelectedCar, customer, rentalHoursText);
                                MyFrame.this.add(new JTextArea("Car rented successfully."));
                                JButton returnB = new JButton("Return To Homepage");
                                MyFrame.this.add(returnB);
                                returnB.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new HomePage();
                                    }
                                });
                            } else{
                                MyFrame.this.add(new JTextArea("Rental cancelled."));
                                JButton returnB = new JButton("Return To Homepage");
                                MyFrame.this.add(returnB);
                                returnB.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new HomePage();
                                    }
                                });
                            }

                            fiveSeaterCar();
                            eightSeaterCar();
                            rentalToFile();

                            MyFrame.this.revalidate();
                            MyFrame.this.repaint();


                        }});
                    MyFrame.this.revalidate();
                    MyFrame.this.repaint();
                } else {
                    JTextArea showError = new JTextArea("Invalid carID selected or car not available for rent.");
                    MyFrame.this.add(showError);
                    JButton okButton = new JButton("Return To HomePage");
                    MyFrame.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            new HomePage();
                        }
                    });
                    MyFrame.this.revalidate();
                    MyFrame.this.repaint();
                }
            }
        });

        this.revalidate();
        this.repaint();
    }


    public static void fiveSeaterCar(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available five seater cars"))){
            writer.write("Available five seater car:\n");
            for(Car car: cars){
                if(car.isAvailable() && car.getSeatNumber()==5){
                    writer.write(car.getId() + " " + car.getBrand() + " " + car.getModel() + "(base price per hour" + car.getBasePricePerHour() + ")");
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void eightSeaterCar(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available eight seater cars"))){
            writer.write("Available eight seater car:\n");
            for(Car car: cars){
                if(car.isAvailable() && car.getSeatNumber()==8){
                    writer.write(car.getId() + " " + car.getBrand() + " " + car.getModel() + "(base price per hour" + car.getBasePricePerHour() + ")");
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void carReturnMethod(){
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        JLabel label = new JLabel("Enter carID of the car you want to return:");
        label.setAlignmentX(JLabel.CENTER);
        this.setVisible(true);

        JTextField carID = new JTextField();
        carID.setMaximumSize(new Dimension(500, 30));
        JButton submitButton5 = new JButton("Submit");

        // Add components to the frame
        this.add(label);
        this.add(carID);
        this.add(submitButton5);

        // Action listener for the submit button
        submitButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the car ID entered by the user
                String carIDText = carID.getText();

                // Find the car object corresponding to the entered car ID
                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getId().equals(carIDText)) {
                        carToReturn = car;
                        break;
                    }
                }

                // Check if the car was found
                if (carToReturn != null) {
                    // Find the customer who rented the car
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    // If customer is found, return the car
                    if (customer != null) {
                        returnCar(carToReturn);
                        JTextArea returnMessage = new JTextArea("Car returned successfully by " + customer.getName());
                        MyFrame.this.add(returnMessage);
                        JButton okButton = new JButton("Return To Homepage");
                        MyFrame.this.add(okButton);
                        okButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new HomePage();
                            }
                        });
                    } else {
                        JTextArea errorMessage = new JTextArea("Car was not rented or rental information is missing.");
                        MyFrame.this.add(errorMessage);
                        JButton okButton = new JButton("Return To Homepage");
                        MyFrame.this.add(okButton);
                        okButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new HomePage();
                            }
                        });
                    }


                } else {
                    JTextArea errorMessage = new JTextArea("Invalid car ID or car is not rented.");
                    MyFrame.this.add(errorMessage);
                    JButton okButton = new JButton("Return to homepage");
                    MyFrame.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });
                }

                // Refresh the frame to display the messages
                MyFrame.this.revalidate();
                MyFrame.this.repaint();
            }
        });
    }

    public void addCarToRentalSystem() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setVisible(true);

        this.add(new JLabel("Enter carID:"));
        JTextField carID = new JTextField();
        this.add(carID);

        this.add(new JLabel("Enter brand of the car:"));
        JTextField brand = new JTextField();
        this.add(brand);

        this.add(new JLabel("Enter model of the car:"));
        JTextField model = new JTextField();
        this.add(model);

        this.add(new JLabel("Enter seat number:"));
        JTextField seatNumber = new JTextField();
        this.add(seatNumber);

        this.add(new JLabel("Enter base price of car:"));
        JTextField basePrice = new JTextField();
        this.add(basePrice);

        this.add(new JLabel("Enter driver's name:"));
        JTextField driverName = new JTextField();
        this.add(driverName);

        this.add(new JLabel("Enter driver's phone number:"));
        JTextField driverPhoneNumber = new JTextField();
        this.add(driverPhoneNumber);

        this.add(new JLabel("Enter driver's gender:"));
        JTextField driverGender = new JTextField();
        this.add(driverGender);

        JButton submitButton6 = new JButton("Submit");
        this.add(submitButton6);

        submitButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String carIDText = carID.getText();
                    String brandText = brand.getText();
                    String modelText = model.getText();
                    int seatNumberText = Integer.parseInt(seatNumber.getText());
                    int basePriceText = Integer.parseInt(basePrice.getText());
                    String driverNameText = driverName.getText();
                    String driverPhoneNumberText = driverPhoneNumber.getText();
                    String driverGenderText = driverGender.getText();

                    Driver driver = new Driver(driverNameText, driverPhoneNumberText, "CD00" + (drivers.size() + 1), driverGenderText);
                    Car car = new Car(seatNumberText, carIDText, brandText, modelText, driver, basePriceText);
                    drivers.add(driver);
                    cars.add(car);

                    MyFrame.this.add(new JTextArea("Car added successfully"));

                    JButton okButton = new JButton("OK");
                    MyFrame.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });

                    fiveSeaterCar();
                    eightSeaterCar();

                } catch (NumberFormatException ex) {
                    MyFrame.this.add(new JTextArea("Invalid input. Please enter valid numbers for seat number and base price."));
                }

                MyFrame.this.revalidate();
                MyFrame.this.repaint();
            }
        });

    }

    public void RemoveCarSwing() {
        this.setTitle("EasyRent");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        this.add(panel1);
        panel1.setLayout(null);

        textField = new JTextField();
        textField.setBounds(100, 20, 150, 25);
        panel1.add(textField);

        JButton button = new JButton("Remove Car");
        button.setBounds(100, 60, 165, 25);
        panel1.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carID = textField.getText();
                Car carToBeRemoved = null;
                for (Car car : cars) {
                    if (car.getId().equals(carID)) {
                        carToBeRemoved = car;
                        break;
                    }
                }

                JTextArea textArea;
                JButton okButton = new JButton("Return to homepage");
                okButton.setBounds(100, 200, 165, 25);
                panel1.add(okButton);

                if (carToBeRemoved != null) {
                    cars.remove(carToBeRemoved);
                    textArea = new JTextArea("Car removed successfully");
                    fiveSeaterCar();
                    eightSeaterCar();
                } else {
                    textArea = new JTextArea("Invalid CarID");
                }

                textArea.setBounds(100, 150, 200, 25);
                panel1.add(textArea);

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new HomePage();
                    }
                });

                panel1.revalidate();
                panel1.repaint();
            }
        });

        this.setVisible(true);
    }

    }
 class PhoneNumberException extends Exception {
    public PhoneNumberException(String message) {
        super(message);
    }
}

