import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MyFrame2 extends JFrame implements ActionListener {
    MyFrame2() {

    }

    static ArrayList<Bus> buses = new ArrayList<>();
    ArrayList<Driver> drivers = new ArrayList<>();
    JTextField textField;

    public static void addBus(Bus bus) {
        buses.add(bus);
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentBus(Bus bus, Customer customer, int hours) {
        if (bus.isAvailable()) {
            bus.rent();
            rentals.add(new Rental(bus, customer, hours));
        } else
            System.out.println("Bus is not available for rent.");

    }

    ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    JButton submitButton;
    JButton submitButton2;
    JButton submitButton3;

    JCheckBox ACBus;
    JCheckBox NonACBus;

    public void busRentalMethod() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("What type of you do you want?");
        ACBus = new JCheckBox("AC BUS");
        NonACBus = new JCheckBox("Non-AC");
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        this.add(label);
        this.add(ACBus);
        this.add(NonACBus);
        this.add(submitButton);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            this.getContentPane().removeAll();
            String filename;

            if (ACBus.isSelected() && !NonACBus.isSelected()) {
                JLabel label = new JLabel("Available AC buses:");
                this.add(label);
                filename = "Available AC Buses";
            } else if (NonACBus.isSelected() && !ACBus.isSelected()) {
                JLabel label = new JLabel("Available non-AC buses:");
                this.add(label);
                filename = "Available non-AC Buses";
            } else {
                JLabel label = new JLabel("No car type selected");
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

        JLabel label1 = new JLabel("Enter the busID of the bus you want to rent:");
        JTextField busID = new JTextField();
        this.add(label1);
        this.add(busID);

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
                String busIdText = busID.getText();
                int rentalHoursText = Integer.parseInt(rentalHours.getText());
                String customerNameText = customerName.getText();
                String customerPhoneNumberText = customerPhoneNumber.getText();

                Bus selectedBus = null;
                for (Bus bus : buses) {
                    if (bus.getId().equals(busIdText)) {
                        selectedBus = bus;
                        break;
                    }
                }
                try{
                    if(!customerPhoneNumberText.matches("\\d{11}")){
                        throw new PhoneNumberException2("InvalidMobileNumberException occured");
                    }
                }
                catch (PhoneNumberException2 g){
                    System.out.println(g.getMessage());
                    g.printStackTrace();
                }

                if (selectedBus != null) {
                    Customer customer = new Customer("CUS" + (customers.size() + 1), customerNameText, customerPhoneNumberText);
                    customers.add(customer);

                    double totalPrice = selectedBus.calculatePrice(rentalHoursText);

                    JTextArea textArea = new JTextArea("Rental System");
                    MyFrame2.this.add(textArea);
                    MyFrame2.this.add(new JTextArea("Customer ID: " + customer.getCustomerId()));
                    MyFrame2.this.add(new JTextArea("Customer Name: " + customer.getName()));
                    MyFrame2.this.add(new JTextArea("Customer phone number: " + customer.getPhoneNumber()));
                    MyFrame2.this.add(new JTextArea("Bus: " + selectedBus.getBrand() + " " + selectedBus.getModel()));
                    MyFrame2.this.add(new JTextArea("Driver: " + selectedBus.getDriver().getName() + " (" + selectedBus.getDriver().getId() + ")"));
                    MyFrame2.this.add(new JTextArea("Driver's contact number: " + selectedBus.getDriver().getPhoneNumber()));
                    MyFrame2.this.add(new JTextArea("Rental hours: " + rentalHoursText));
                    MyFrame2.this.add(new JTextArea("Total price: " + totalPrice + " taka"));
                    JLabel confirm = new JLabel("Confirm(Y/N)");
                    JTextField confirm1 = new JTextField();
                    MyFrame2.this.add(confirm);
                    MyFrame2.this.add(confirm1);
                    submitButton3 = new JButton("Submit");
                    MyFrame2.this.add(submitButton3);

                    Bus finalSelectedBus = selectedBus;
                    submitButton3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String confirmed = confirm1.getText();
                            if (confirmed.equals("Y")) {
                                rentBus(finalSelectedBus, customer, rentalHoursText);
                                MyFrame2.this.add(new JTextArea("Bus rented successfully."));
                                JButton returnB = new JButton("Return To Homepage");
                                MyFrame2.this.add(returnB);
                                returnB.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new HomePage();
                                    }
                                });
                            } else{
                                MyFrame2.this.add(new JTextArea("Rental cancelled."));
                                JButton returnB = new JButton("Return To Homepage");
                                MyFrame2.this.add(returnB);
                                returnB.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new HomePage();
                                    }
                                });
                            }
                            ACBus();
                            nonACBus();
                            rentalToFile();


                            MyFrame2.this.revalidate();
                            MyFrame2.this.repaint();

                        }

                    });
                    MyFrame2.this.revalidate();
                    MyFrame2.this.repaint();
                } else {
                    JTextArea showError = new JTextArea("Invalid busID selected or bus not available for rent.");
                    MyFrame2.this.add(showError);
                    JButton okButton = new JButton("Return to homepage");
                    MyFrame2.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });

                    MyFrame2.this.revalidate();
                    MyFrame2.this.repaint();
                }
            }
        });

        this.revalidate();
        this.repaint();

    }

    public static void ACBus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Available AC Buses"))) {
            writer.write("Available AC Buses\n");
            for (Bus bus : buses) {
                if (bus.isAvailable() && bus.isAC()) {
                    writer.write(bus.getId() + " " + bus.getBrand() + " " + bus.getModel() + "(base price per hour" + bus.getBasePricePerHour() + ")");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void nonACBus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Available non-AC Buses"))) {
            writer.write("Available non-AC Buses\n");
            for (Bus bus : buses) {
                if (bus.isAvailable() && !bus.isAC()) {
                    writer.write(bus.getId() + " " + bus.getBrand() + " " + bus.getModel() + "(base price per hour" + bus.getBasePricePerHour() + ")");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void busReturnMethod() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JLabel label = new JLabel("Enter busID of the car you want to return:");
        label.setAlignmentX(JLabel.CENTER);
        this.setVisible(true);

        JTextField busID = new JTextField();
        busID.setMaximumSize(new java.awt.Dimension(500, 30));
        JButton submitButton5 = new JButton("Submit");

        // Add components to the frame
        this.add(label);
        this.add(busID);
        this.add(submitButton5);

        // Action listener for the submit button
        submitButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the car ID entered by the user
                String busIDText = busID.getText();

                // Find the car object corresponding to the entered car ID
                Bus busToReturn = null;
                for (Bus bus : buses) {
                    if (bus.getId().equals(busIDText)) {
                        busToReturn = bus;
                        break;
                    }
                }

                // Check if the car was found
                if (busToReturn != null) {
                    // Find the customer who rented the car
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getBus() == busToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    // If customer is found, return the car
                    if (customer != null) {
                        returnBus(busToReturn);
                        JTextArea returnMessage = new JTextArea("Bus returned successfully by " + customer.getName());
                        MyFrame2.this.add(returnMessage);
                        JButton returnB = new JButton("Return To Homepage");
                        MyFrame2.this.add(returnB);
                        returnB.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new HomePage();
                            }
                        });
                    } else {
                        JTextArea errorMessage = new JTextArea("Bus was not rented or rental information is missing.");
                        MyFrame2.this.add(errorMessage);
                        JButton returnB = new JButton("Return To Homepage");
                        MyFrame2.this.add(returnB);
                        returnB.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new HomePage();
                            }
                        });
                    }


                } else {
                    JTextArea errorMessage = new JTextArea("Invalid car ID or car is not rented.");
                    MyFrame2.this.add(errorMessage);
                    JButton okButton = new JButton("Return to homepgae");
                    MyFrame2.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });

                }

                // Refresh the frame to display the messages
                MyFrame2.this.revalidate();
                MyFrame2.this.repaint();
            }
        });
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

    public static void rentalToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Bus Rental Details"))) {
            writer.write("Bus Rental Details:\n");
            for (Rental rental : rentals) {
                if (rental.getCar() != null)
                    writer.write(rental.getCustomer().getCustomerId() + " rents " + rental.getCar().getId() + " car for " + rental.getHours() + " hours.");
                else if (rental.getBus() != null)
                    writer.write(rental.getCustomer().getCustomerId() + " rents " + rental.getBus().getId() + " bus for " + rental.getHours() + " hours.");
                else if (rental.getMotorCycle() != null)
                    writer.write(rental.getCustomer().getCustomerId() + " rents " + rental.getMotorCycle().getId() + " motorcycle for " + rental.getHours() + " hours.");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addBusToRentalSystem() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setVisible(true);

        this.add(new JLabel("Enter busID:"));
        JTextField busID = new JTextField();
        this.add(busID);

        this.add(new JLabel("Enter brand of the bus:"));
        JTextField brand = new JTextField();
        this.add(brand);

        this.add(new JLabel("Enter model of the bus:"));
        JTextField model = new JTextField();
        this.add(model);

        this.add(new JLabel("Is AC Available(true/false"));
        JTextField ACBus = new JTextField();
        this.add(ACBus);

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
                    String busIDText = busID.getText();
                    String brandText = brand.getText();
                    String modelText = model.getText();
                    boolean ACText = Boolean.parseBoolean(ACBus.getText());
                    int basePriceText = Integer.parseInt(basePrice.getText());
                    String driverNameText = driverName.getText();
                    String driverPhoneNumberText = driverPhoneNumber.getText();
                    String driverGenderText = driverGender.getText();

                    Driver driver = new Driver(driverNameText, driverPhoneNumberText, "BD00" + (drivers.size() + 1), driverGenderText);
                    Bus bus = new Bus(busIDText, brandText, modelText, driver, basePriceText, ACText);
                    drivers.add(driver);
                    buses.add(bus);

                    MyFrame2.this.add(new JTextArea("Bus added successfully"));

                    JButton okButton = new JButton("Return to homepage");
                    MyFrame2.this.add(okButton);
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new HomePage();
                        }
                    });

                    ACBus();
                    nonACBus();

                } catch (NumberFormatException ex) {
                    MyFrame2.this.add(new JTextArea("Invalid input. Please enter valid numbers for seat number and base price."));
                }

                MyFrame2.this.revalidate();
                MyFrame2.this.repaint();
            }
        });
    }
    public void RemoveBusSwing() {
        this.setTitle("EasyRent");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        this.add(panel1);
        panel1.setLayout(null);

        textField = new JTextField();
        textField.setBounds(100, 20, 150, 25);
        panel1.add(textField);

        JButton button = new JButton("Remove Bus");
        button.setBounds(100, 60, 165, 25);
        panel1.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busID = textField.getText();
                Bus busToBeRemoved = null;
                for (Bus bus : buses) {
                    if (bus.getId().equals(busID)) {
                        busToBeRemoved = bus;
                        break;
                    }
                }

                JTextArea textArea;
                JButton okButton = new JButton("Return to homepage");
                okButton.setBounds(100, 200, 165, 25);
                panel1.add(okButton);

                if (busToBeRemoved != null) {
                    buses.remove(busToBeRemoved);
                    textArea = new JTextArea("Bus removed successfully");
                    ACBus();
                    nonACBus();

                } else {
                    textArea = new JTextArea("Invalid BusID");
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
class PhoneNumberException2 extends Exception {
    public PhoneNumberException2(String message) {
        super(message);
    }
}



