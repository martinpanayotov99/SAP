package screens;

import DataBase.Connection;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainScreen extends Frame {


    private JButton button;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel pasLabel;
    private JTextField userPass;
    private JLabel imgLabel;
    private ImageIcon imageIcon;
    private JLabel error;

    public MainScreen() {
        super();
        userLabel = new JLabel("USERNAME");
        userText = new JTextField();
        userPass = new JPasswordField();
        pasLabel = new JLabel("PASSWORD");
        button = new JButton("LOGIN");
        error = new JLabel();

    }

    public void createScreen() {
        createFrame();
        userLabel.setBounds(10, 20, 80, 25);
        getPanel().add(userLabel);

        userText.setBounds(100, 20, 165, 25);
        getPanel().add(userText);

        pasLabel.setBounds(10, 60, 80, 25);
        getPanel().add(pasLabel);

        userPass.setBounds(100, 60, 165, 25);
        getPanel().add(userPass);

        error.setBounds(50, 130, 160, 25);
        getPanel().add(error);

        button.setBounds(75, 100, 100, 25);
        button.addActionListener(e -> {
            String username = userText.getText();
            String password = userPass.getText();

            Connection connection = new Connection();
            try {
                Statement statement = connection.getConnection().createStatement();
                ResultSet set = statement.executeQuery("SELECT email, password, isAdmin FROM users");
                while (set.next()) {
                    if (username.equals(set.getString("email")) &&
                            password.equals(set.getString("password"))) {
                        if (set.getBoolean("isAdmin")) {
                            getFrame().dispose();
                            AdminScreen adminScreen = new AdminScreen();
                            adminScreen.createScreen();
                        } else {
                            getFrame().dispose();
                            setUsername(username);
                            UserScreen userScreen = new UserScreen();
                            userScreen.createScreen();
                        }
                    }
                }
                error.setText("WRONG USERNAME OR PASSWORD");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        getPanel().add(button);

        getFrame().setVisible(true);
    }
}

