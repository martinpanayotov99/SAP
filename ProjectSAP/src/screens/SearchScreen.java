package screens;

import DataBase.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchScreen extends Frame {
    public SearchScreen() {
        super();
    }

    public void creteScreen() {
        createFrame();
        JLabel select_criteria = new JLabel("SELECT CRITERIA");
        select_criteria.setBounds(15, 20, 180, 25);
        getPanel().add(select_criteria);

        String[] criteria = new String[]{
                "EMAIL", "PLANS", "PAYMENTS"
        };

        JComboBox criteriaBox = new JComboBox(criteria);
        criteriaBox.setBounds(15, 50, 180, 25);
        getPanel().add(criteriaBox);

        String action = criteriaBox.getActionCommand();

        JButton searchButton = new JButton("SEARCH");
        searchButton.setBounds(100, 170, 120, 25);
        searchButton.addActionListener(e -> {
            search(action, getPanel());
        });
        getPanel().add(searchButton);


        JButton backButton = new JButton("BACK");
        backButton.setBounds(15, 170, 80, 25);
        backButton.addActionListener(e -> {
            getFrame().dispose();
            new AdminScreen().createScreen();
        });
        getPanel().add(backButton);
    }

    public static String createTextField(JPanel panel) {
        JLabel input = new JLabel("EMAIL OR PLAN");
        input.setBounds(15, 80, 160, 25);
        panel.add(input);

        JTextField textField = new JTextField();
        textField.setBounds(15, 110, 80, 25);
        panel.add(input);

        return textField.getText();
    }

    public static void search(String action, JPanel panel) {
        String sql = "";
        String criteria = "";
        switch (action) {
            case "PAYMENTS":
                sql = "SELECT name, phone, lastPayment \n" +
                        "FROM users\n" +
                        "JOIN dataplans d on users.id = d.user_id\n" +
                        "JOIN payment p on d.id = p.dataPlan_id\n" +
                        "WHERE paid = 0";
                break;
            case "EMAIL":
                sql = "SELECT email, name, phone FROM users\n" +
                        "WHERE email = ?;";
                criteria = createTextField(panel);
                break;
            case "PLANS":
                sql = "SELECT plan_name, phone, email FROM services\n" +
                        "JOIN dataPlans dP on services.id = dP.plan_id\n" +
                        "JOIN users u on dP.user_id = u.id\n" +
                        "WHERE plan_name = ?";
                criteria = createTextField(panel);
                break;
        }

        try {
            Connection connection = new Connection();
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, criteria);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                JLabel label = new JLabel();
                //label.setBounds(500);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
