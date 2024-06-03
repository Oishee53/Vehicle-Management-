import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MyFrame3 extends JFrame implements ActionListener {

    MyFrame3(){

    }
    static ArrayList<MotorCycle> motorCycles = new ArrayList<>();
    ArrayList<Driver> drivers = new ArrayList<>();
    JTextField textField;
    public static void addMotorCycle(MotorCycle motorCycle){
        motorCycles.add(motorCycle);
    }
    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentMotorCycle(MotorCycle motorCycle, Customer customer, int hours){
        if(motorCycle.isAvailable()){
            motorCycle.rent();
            rentals.add(new Rental(motorCycle, customer, hours));
        }
        else
            System.out.println("Motorcycle is not available for rent.");

    }
    ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    JButton submitButton;
    JButton submitButton2;
    JButton submitButton3;

    JCheckBox maleDriverMotorCycle;
    JCheckBox femaleDriverMotorCycle;
    public void motorCycleRentalMethod() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Select your desired gender of driver:");
        maleDriverMotorCycle = new JCheckBox("Male");
        femaleDriverMotorCycle = new JCheckBox("Female");
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        this.add(label);
        this.add(maleDriverMotorCycle);
        this.add(femaleDriverMotorCycle);
        this.add(submitButton);
        this.setSize(500,500);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            this.getContentPane().removeAll();
            String filename;

            if (maleDriverMotorCycle.isSelected() && !femaleDriverMotorCycle.isSelected()) {
                JLabel label = new JLabel("Available motorcycle with male drivers:");
                this.add(label);
                filename = "Available motorcycle with male driver";
            } else if (femaleDriverMotorCycle.isSelected() && !maleDriverMotorCycle.isSelected()) {
                JLabel label = new JLabel("Available motorcycle with female drivers:");
                this.add(label);
                filename = "Available motorcycle with female driver";
            }
            else {
                JLabel label = new JLabel("No gender selected.");
                this.add(label);
                this.revalidate();
                this.repaint();
                return;
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

        JLabel label1 = new JLabel("Enter the motorcycleID of the motorcycle you want to rent:");
        JTextField motorCycleID = new JTextField();
        this.add(label1);
        this.add(motorCycleID);

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
                String motorCycleIDText = motorCycleID.getText();
                int rentalHoursText = Integer.parseInt(rentalHours.getText());
                String customerNameText = customerName.getText();
                String customerPhoneNumberText = customerPhoneNumber.getText();

                MotorCycle selectedMotorCycle = null;
                for (MotorCycle motorCycle: motorCycles) {
                    if (motorCycle.getId().equals(motorCycleIDText)) {
                        selectedMotorCycle = motorCycle;
                        break;
                    }
                }
                try{
                    if(!customerPhoneNumberText.matches("\\d{11}")){
                        throw new PhoneNumberException3("InvalidMobileNumberException occured");
                    }
                }
                catch (PhoneNumberException3 g){
                    System.out.println(g.getMessage());
                    g.printStackTrace();
                }

                if (selectedMotorCycle != null) {
                    Customer customer = new Customer("CUS" + (customers.size() + 1), customerNameText, customerPhoneNumberText);
                    customers.add(customer);

                    double totalPrice = selectedMotorCycle.calculatePrice(rentalHoursText);

                    JTextArea textArea = new JTextArea("Rental System");
                    MyFrame3.this.add(textArea);
                    MyFrame3.this.add(new JTextArea("Customer ID: " + customer.getCustomerId()));
                    MyFrame3.this.add(new JTextArea("Customer Name: " + customer.getName()));
                    MyFrame3.this.add(new JTextArea("Customer phone number: " + customer.getPhoneNumber()));
                    MyFrame3.this.add(new JTextArea("Motorcycle: " + selectedMotorCycle.getBrand() + " " + selectedMotorCycle.getModel()));
                    MyFrame3.this.add(new JTextArea("Driver: " + selectedMotorCycle.getDriver().getName() + " (" + selectedMotorCycle.getDriver().getId() + ")"));
                    MyFrame3.this.add(new JTextArea("Driver's contact number: " + selectedMotorCycle.getDriver().getPhoneNumber()));
                    MyFrame3.this.add(new JTextArea("Rental hours: " + rentalHoursText));
                    MyFrame3.this.add(new JTextArea("Total price: " + totalPrice + " taka"));
                    JLabel confirm = new JLabel("Confirm(Y/N)");
                    JTextField confirm1 = new JTextField();
                    MyFrame3.this.add(confirm);
                    MyFrame3.this.add(confirm1);
                    submitButton3 = new JButton("Submit");
                    MyFrame3.this.add(submitButton3);

                    MotorCycle finalSelectedMotorCycle = selectedMotorCycle;
                    submitButton3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String confirmed = confirm1.getText();
                            if(confirmed.equals("Y")){
                                rentMotorCycle(finalSelectedMotorCycle, customer, rentalHoursText);
                                MyFrame3.this.add(new JTextArea("Motorcycle rented successfully."));
                                JButton returnB = new JButton("Return To Homepage");
                                MyFrame3.this.add(returnB);
                                returnB.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new HomePage();
                                    }
                                });
                            } else{
                                MyFrame3.this.add(new JTextArea("Rental cancelled."));
                                JButton returnB = new JButton("Return To Homepage");
                                MyFrame3.this.add(returnB);
                                returnB.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new HomePage();
                                    }
                                });
                            }
                            motorCycleWithFemaleDriver();
                            motorCycleWithMaleDriver();
                            rentalToFile();

                            MyFrame3.this.revalidate();
                            MyFrame3.this.repaint();

                        }

                    });
                    MyFrame3.this.revalidate();
                    MyFrame3.this.repaint();
                } else {
                    JTextArea showError = new JTextArea("Invalid motorcycleID selected or motorcycle not available for rent.");
                    MyFrame3.this.add(showError);
                    JButton returnButton = new JButton();
                    MyFrame3.this.add(returnButton);
                    returnButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });

                    MyFrame3.this.revalidate();
                    MyFrame3.this.repaint();
                }
            }
        });

        this.revalidate();
        this.repaint();
    }

    public static void motorCycleWithFemaleDriver(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available motorcycle with female driver"))){
            writer.write("Available motorcycle with female driver:\n");
            for(MotorCycle motorCycle: motorCycles){
                if(motorCycle.isAvailable() && motorCycle.getGenderOfDriver().equals("female")){
                    writer.write(motorCycle.getId() + " " + motorCycle.getBrand() + " " + motorCycle.getModel() + "(base price per hour" + motorCycle.getBasePricePerHour() + ")");
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void motorCycleWithMaleDriver(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Available motorcycle with male driver"))){
            writer.write("Available motorcycle with male driver:\n");
            for(MotorCycle motorCycle: motorCycles){
                if(motorCycle.isAvailable() && motorCycle.getGenderOfDriver().equals("male")){
                    writer.write(motorCycle.getId() + " " + motorCycle.getBrand() + " " + motorCycle.getModel() + "(base price per hour" + motorCycle.getBasePricePerHour() + ")");
                    writer.newLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void rentalToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Motorcycle Rental Details"))){
            writer.write("Motorcycle Rental Details:\n");
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

    public void motorCycleReturnMethod(){
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        JLabel label = new JLabel("Enter motorcycleID of the car you want to return:");
        label.setAlignmentX(JLabel.CENTER);
        this.setVisible(true);

        JTextField motorcycleID = new JTextField();
        motorcycleID.setMaximumSize(new java.awt.Dimension(500, 30));
        JButton submitButton5 = new JButton("Submit");

        // Add components to the frame
        this.add(label);
        this.add(motorcycleID);
        this.add(submitButton5);

        // Action listener for the submit button
        submitButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the car ID entered by the user
                String motorcycleIDText = motorcycleID.getText();

                // Find the car object corresponding to the entered car ID
                MotorCycle motorCycleToReturn = null;
                for (MotorCycle motorCycle: motorCycles) {
                    if (motorCycle.getId().equals(motorcycleIDText)) {
                        motorCycleToReturn = motorCycle;
                        break;
                    }
                }

                // Check if the car was found
                if (motorCycleToReturn != null) {
                    // Find the customer who rented the car
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getMotorCycle() == motorCycleToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    // If customer is found, return the car
                    if (customer != null) {
                        returnMotorCycle(motorCycleToReturn);
                        JTextArea returnMessage = new JTextArea("Motorcycle returned successfully by " + customer.getName());
                        MyFrame3.this.add(returnMessage);
                        JButton returnB = new JButton("Return To Homepage");
                        MyFrame3.this.add(returnB);
                        returnB.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new HomePage();
                            }
                        });
                    } else {
                        JTextArea errorMessage = new JTextArea("Motorcycle was not rented or rental information is missing.");
                        MyFrame3.this.add(errorMessage);
                        JButton returnB = new JButton("Return To Homepage");
                        MyFrame3.this.add(returnB);
                        returnB.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new HomePage();
                            }
                        });
                    }
                } else {
                    JTextArea errorMessage = new JTextArea("Invalid motorcycle ID or motorcycle is not rented.");
                    MyFrame3.this.add(errorMessage);
                    JButton returnB = new JButton("Return To Homepage");
                    MyFrame3.this.add(returnB);
                    returnB.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });
                }

                // Refresh the frame to display the messages
                MyFrame3.this.revalidate();
                MyFrame3.this.repaint();
            }
        });
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
    public void addMotorCycleToRentalSystem() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setVisible(true);

        this.add(new JLabel("Enter motorcycleID:"));
        JTextField motorCycleID = new JTextField();
        this.add(motorCycleID);

        this.add(new JLabel("Enter brand of the bus:"));
        JTextField brand = new JTextField();
        this.add(brand);

        this.add(new JLabel("Enter model of the bus:"));
        JTextField model = new JTextField();
        this.add(model);


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
                    String motorCycleIDText = motorCycleID.getText();
                    String brandText = brand.getText();
                    String modelText = model.getText();
                    int basePriceText = Integer.parseInt(basePrice.getText());
                    String driverNameText = driverName.getText();
                    String driverPhoneNumberText = driverPhoneNumber.getText();
                    String driverGenderText = driverGender.getText();

                    Driver driver = new Driver(driverNameText, driverPhoneNumberText, "MD00" + (drivers.size() + 1), driverGenderText);
                    MotorCycle motorCycle = new MotorCycle(motorCycleIDText,brandText,modelText,driver,basePriceText,driverGenderText);
                    drivers.add(driver);
                    motorCycles.add(motorCycle);

                    MyFrame3.this.add(new JTextArea("Motorcycle added successfully"));

                    JButton okButton = new JButton("OK");
                    MyFrame3.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });

                    motorCycleWithFemaleDriver();
                    motorCycleWithMaleDriver();

                } catch (NumberFormatException ex) {
                    MyFrame3.this.add(new JTextArea("Invalid input. Please enter valid numbers for seat number and base price."));
                }
                JButton okButton = new JButton("Return to homepage");
                MyFrame3.this.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new HomePage();
                    }
                });

                MyFrame3.this.revalidate();
                MyFrame3.this.repaint();
            }
        });
    }

    public void RemoveMotorCycleSwing() {
        this.setTitle("EasyRent");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        this.add(panel1);
        panel1.setLayout(null);

        textField = new JTextField();
        textField.setBounds(100, 20, 150, 25);
        panel1.add(textField);

        JButton button = new JButton("Remove Motorcycle");
        button.setBounds(100, 60, 165, 25);
        panel1.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String motorcycleID = textField.getText();
                MotorCycle motorCycleToBeRemoved = null;
                for (MotorCycle motorCycle : motorCycles) {
                    if (motorCycle.getId().equals(motorcycleID)) {
                        motorCycleToBeRemoved = motorCycle;
                        break;
                    }
                }

                JTextArea textArea;
                JButton okButton = new JButton("Return To Homepage");
                okButton.setBounds(100, 200, 165, 25);
                panel1.add(okButton);

                if (motorCycleToBeRemoved != null) {
                    MyFrame2.buses.remove(motorCycleToBeRemoved);
                    textArea = new JTextArea("MotorCycle removed successfully");
                    motorCycleWithFemaleDriver();
                    motorCycleWithMaleDriver();
                } else {
                    textArea = new JTextArea("Invalid MotorCycle ID");
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
class PhoneNumberException3 extends Exception {
    public PhoneNumberException3(String message) {
        super(message);
    }
}