import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    HomePage(){
        JFrame frame = new JFrame("EasyRent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setResizable(false);

        JLabel label = new JLabel("VEHICLE MANAGEMENT SYSTEM");
        label.setAlignmentX(JButton.CENTER_ALIGNMENT);
        label.setMaximumSize(new java.awt.Dimension(200, 30));

        JButton button1 =new JButton("Rent Vehicle");
        button1.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button1.setMaximumSize(new java.awt.Dimension(200, 30));


        JButton button2 =new JButton("Return Vehicle");
        button2.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button2.setMaximumSize(new java.awt.Dimension(200, 30));

        JButton button3 = new JButton("Rental List");
        button3.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button3.setMaximumSize(new java.awt.Dimension(200, 30));

        JButton button4 = new JButton("Add Vehicle");
        button4.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button4.setMaximumSize(new java.awt.Dimension(200, 30));

        JButton button5 = new JButton("Remove Vehicle");
        button5.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button5.setMaximumSize(new java.awt.Dimension(200, 30));

        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);

        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                RentFrame rentFrame =  new RentFrame();
                rentFrame.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                ReturnFrame returnFrame =  new ReturnFrame();
                returnFrame.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                RentalFrame addFrame =  new RentalFrame();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                AddFrame addFrame =  new AddFrame();
                addFrame.setVisible(true);
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                RemoveFrame removeFrame =  new RemoveFrame();
                removeFrame.setVisible(true);
            }
        });

    }
}