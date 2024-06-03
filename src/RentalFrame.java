import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RentalFrame extends JFrame {
    RentalFrame() {
        this.setTitle("EasyRent");
        this.setSize(500,500);
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        display("Car Rental Details");
        display("Bus Rental Details");
        display("Motorcycle Rental Details");

        JButton okButton = new JButton("Return To Homepage");
        this.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
            }
        });

    }

    public void display(String filename){
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
    }

}
