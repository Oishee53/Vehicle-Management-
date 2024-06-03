import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JFrame {
    AddFrame(){
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);

        JLabel label = new JLabel("What do you want to return?");
        label.setAlignmentX(JLabel.CENTER);
        this.add(label);

        JCheckBox car = new JCheckBox("Car");
        JCheckBox bus = new JCheckBox("Bus");
        JCheckBox motorcycle = new JCheckBox("Motorcycle");
        JButton submitButton = new JButton("Submit");

        this.add(car);
        this.add(bus);
        this.add(motorcycle);
        this.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(car.isSelected()){
                    MyFrame frame1 = new MyFrame();
                    frame1.addCarToRentalSystem();
                }
                else if(bus.isSelected()){
                    MyFrame2 frame2 = new MyFrame2();
                    frame2.addBusToRentalSystem();
                }
                else if(motorcycle.isSelected()){
                    MyFrame3 frame3 = new MyFrame3();
                    frame3.addMotorCycleToRentalSystem();
                }
            }
        });


    }

}