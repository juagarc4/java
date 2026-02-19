import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setupLoginForm();
        UIManager.put("PasswordField.showRevealButton", true);
        txtUser.putClientProperty("JTextField.placeholderText", "Username");
        txtPassword.putClientProperty("JTextField.placeholderText", "********");
        txtPassword.putClientProperty("JTextField.showClearButton", true);

        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.registerKeyboardAction(e -> loginAction(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        btnLogin.addActionListener(e -> loginAction());
    }

    private void setupLoginForm() {
        setContentPane(mainPanel);
        setTitle("ZoneFit - Access Control");
        try {
            java.net.URL iconURL = getClass().getResource("/logo.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            }
        } catch (Exception e) {
            System.err.println("Icon could not be loaded: " + e.getMessage());
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
    }

    private void loginAction() {
        String user = txtUser.getText();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User is required.", "User required", JOptionPane.WARNING_MESSAGE);
            txtUser.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required.", "User required", JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return;
        }
        checkAccess(user, pass);

    }

    private void checkAccess(String user, String pass) {
        if ("Admin".equals(user) && "admin123".equals(pass)) {
            JOptionPane.showMessageDialog(this, "Welcome " + user, "Login successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "User and/or password are wrong", "Wrong credentials", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }
}
