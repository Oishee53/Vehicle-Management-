//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        RentalSystem easyRent = new RentalSystem();
        Driver driver1=new Driver("Rahim","01382753465","R001","male");
        Driver driver2=new Driver("karim","01682323460","R002","male");
        Driver driver3=new Driver("Charles","01282753461","R003","male");
        Driver driver4=new Driver("james","01982753462","R004","male");
        Driver driver5=new Driver("Sofia","01682753465","R005","female");
        Driver driver6=new Driver("amena","01482753469","R006","female");

        Car car1 = new Car(5,"C001","Toyota","Camry",driver1,500.0);
        Car car2 = new Car(5,"C002","Honda","Accord",driver2,500);
        Car car3 = new Car(8,"C003","Chevrolet","Suburban",driver3,1500);
        Car car4 = new Car(8,"C004","Toyota","Sienna",driver4,750);

        Bus bus1 = new Bus("B001", "Toyota","Coaster",driver1,1500,true);
        Bus bus2 = new Bus("B002", "Nissan", "Civilian",driver2,1800,true);
        Bus bus3 = new Bus("B003","Volvo","Economy Class",driver3,1000,false);
        Bus bus4 = new Bus("B004","Volvo","Business Class",driver4,1300,false);

        MotorCycle motorCycle1 = new MotorCycle("M001","TVS","Apache",driver1,250,"male");
        MotorCycle motorCycle2 = new MotorCycle("M002", "TVS","Wego",driver2,300,"male");
        MotorCycle motorCycle3 = new MotorCycle("M003", "Honda","Supra",driver5,300,"female");
        MotorCycle motorCycle4 = new MotorCycle("M004","Royal","Enfield",driver6,250,"female");



        MyFrame.addCar1(car1);
        MyFrame.addCar1(car2);
        MyFrame.addCar1(car3);
        MyFrame.addCar1(car4);
        MyFrame.rentalToFile();

        MyFrame2.addBus(bus1);
        MyFrame2.addBus(bus2);
        MyFrame2.addBus(bus3);
        MyFrame2.addBus(bus4);
        MyFrame2.rentalToFile();


        MyFrame3.addMotorCycle(motorCycle1);
        MyFrame3.addMotorCycle(motorCycle2);
        MyFrame3.addMotorCycle(motorCycle3);
        MyFrame3.addMotorCycle(motorCycle4);
        MyFrame3.rentalToFile();



        MyFrame.fiveSeaterCar();
        MyFrame.eightSeaterCar();


        MyFrame2.ACBus();
        MyFrame2.nonACBus();

        MyFrame3.motorCycleWithMaleDriver();
        MyFrame3.motorCycleWithFemaleDriver();

        easyRent.rentalToFile();

        LoginSwing ls = new LoginSwing();

    }
}