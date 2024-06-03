
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSwing extends JFrame implements ActionListener {
    private JPasswordField jPasswordField;
    RentalSystem rs = new RentalSystem();
    JPanel panel;

    LoginSwing() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("EasyRent");
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        JLabel label = new JLabel("Password");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        jPasswordField = new JPasswordField(20);
        jPasswordField.setBounds(100, 20, 165, 25);
        panel.add(jPasswordField);
        JButton button = new JButton("Login");
        button.addActionListener(this);
        button.setBounds(100, 80, 80, 25);
        panel.add(button);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        rs.setPassWord("EasyRent");
        String passWord = jPasswordField.getText();
        int l=passWord.length();
        try {
            validatePassword(passWord);
        } catch (PasswordException f) {
            System.out.println("Password exception caught."+f.getMessage());
            f.printStackTrace();
        }
        if (passWord.equals(rs.getPassWord())) {
                HomePage homePage = new HomePage();
            }

    }
    private void validatePassword(String passWord) throws PasswordException{

        if (passWord.length()<5) {
            throw new PasswordException("Please Enter a Password of more than 5 character");
        }
    }
}
class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}