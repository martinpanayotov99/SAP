package screens;

import DataBase.Connection;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddScreen extends Frame {

    public AddScreen() {
        super();
    }

    public void createScreen() {
        createFrame();

        JLabel emailLabel = new JLabel("EMAIL");
        emailLabel.setBounds(15, 20, 80, 25);
        getPanel().add(emailLabel);

        JTextField emailText = new JTextField();
        emailText.setBounds(100, 20, 160, 25);
        getPanel().add(emailText);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setBounds(15, 50, 80, 25);
        getPanel().add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 50, 160, 25);
        getPanel().add(nameField);

        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(15, 80, 80, 25);
        getPanel().add(passLabel);

        JTextField passText = new JTextField();
        passText.setBounds(100, 80, 160, 25);
        getPanel().add(passText);

        JLabel phoneLabel = new JLabel("PHONE");
        phoneLabel.setBounds(15, 110, 80, 25);
        getPanel().add(phoneLabel);

        JTextField phoneText = new JTextField();
        phoneText.setBounds(100, 110, 160, 25);
        getPanel().add(phoneText);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(15, 170, 80, 25);
        backButton.addActionListener(e -> {
            getFrame().dispose();
            new AdminScreen().createScreen();
        });
        getPanel().add(backButton);


        JLabel plans = new JLabel("MOBILE PLANS");
        plans.setBounds(500, 15, 300, 25);
        getPanel().add(plans);

        JLabel plans1 = new JLabel("<html> <p>MEGA</p> <p>1000 minutes</p> <p>1000 Mb</p> <p>1000 SMS</p> </html>");
        plans1.setBounds(400, 45, 100, 75);
        getPanel().add(plans1);

        JLabel plans2 = new JLabel("<html> <p>GIGA</p> <p>10000 minutes</p> <p>10000 Mb</p> <p>10000 SMS</p> </html>");
        plans2.setBounds(500, 45, 100, 75);
        getPanel().add(plans2);

        JLabel plans3 = new JLabel("<html> <p>STANDARD</p> <p>500 minutes</p> <p>500 Mb</p> <p>500 SMS</p> </html>");
        plans3.setBounds(600, 45, 100, 75);
        getPanel().add(plans3);

        String[] plansData = new String[]{
                "STANDARD", "MEGA", "GIGA"
        };

        JComboBox planCombo = new JComboBox(plansData);
        planCombo.setBounds(160, 140, 80, 25);
        getPanel().add(planCombo);

        JButton addButton = new JButton("ADD CLIENT");
        addButton.setBounds(15, 140, 140, 25);
        addButton.addActionListener(e -> {
            register(emailText, nameField, passText, phoneText, planCombo);
        });
        getPanel().add(addButton);

        getFrame().setVisible(true);

    }

    public static void register(JTextField email, JTextField name, JTextField password, JTextField phone, JComboBox box) {
        String emailBD = email.getText();
        String nameBD = name.getText();
        String passwordBD = password.getText();
        String phoneBd = phone.getText();

        Connection connection = new Connection();
        try {
            PreparedStatement preparedStatement = connection.getConnection().
                    prepareStatement("INSERT INTO  users (email, name, password, phone)  VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, emailBD);
            preparedStatement.setString(2, nameBD);
            preparedStatement.setString(3, passwordBD);
            preparedStatement.setString(4, phoneBd);

            preparedStatement.execute();

            preparedStatement = connection.getConnection().prepareStatement("SELECT id FROM users WHERE phone = ?");
            preparedStatement.setString(1, phoneBd);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            int id = set.getInt("id");
            int remaining = 0;
            int planId = 0;
            Object selectedItem = box.getSelectedItem();
            if ("STANDARD".equals(selectedItem)) {
                planId = 1;
                remaining = 500;
            } else if ("MEGA".equals(selectedItem)) {
                planId = 2;
                remaining = 1000;
            } else if ("GIGA".equals(selectedItem)) {
                planId = 3;
                remaining = 10000;
            }
            preparedStatement = connection.getConnection().prepareStatement("INSERT INTO dataplans (USER_ID, PLAN_ID) " +
                    "VALUE (?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, planId);
            preparedStatement.execute();

            preparedStatement = connection.getConnection().prepareStatement("INSERT INTO payment " +
                    "(DATAPLAN_ID, LASTPAYMENT, NEXTPAYMENT, MB_REMAINING, MINUTES_REMAINING, SMS_REMAINING)" +
                    "VALUE (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();
            preparedStatement.setDate(2, Date.valueOf(dtf.format(date)));
            preparedStatement.setDate(3, Date.valueOf(dtf.format(date.plusMonths(1))));
            preparedStatement.setInt(4, remaining);
            preparedStatement.setInt(5, remaining);
            preparedStatement.setInt(6, remaining);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
