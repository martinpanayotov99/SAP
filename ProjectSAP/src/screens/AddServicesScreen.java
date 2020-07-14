package screens;

import DataBase.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddServicesScreen extends Frame {

    public AddServicesScreen() {
        super();
    }

    public void createScreen() {
        createFrame();

        JLabel phone = new JLabel("CLIENT PHONE");
        phone.setBounds(15, 20, 160, 25);
        getPanel().add(phone);

        JTextField phoneText = new JTextField();
        phoneText.setBounds(15, 20, 160, 25);
        getPanel().add(phoneText);

        JLabel minutes = new JLabel("Minutes");
        minutes.setBounds(15, 50, 160, 25);
        getPanel().add(minutes);

        JTextField minutesTxt = new JTextField();
        minutesTxt.setBounds(100, 50, 160, 25);
        getPanel().add(minutesTxt);

        JLabel sms = new JLabel("SMS");
        sms.setBounds(15, 80, 160, 25);
        getPanel().add(sms);

        JTextField smsTxt = new JTextField();
        smsTxt.setBounds(100, 80, 160, 25);
        getPanel().add(smsTxt);

        JLabel mb = new JLabel("MegaBytes");
        mb.setBounds(15, 110, 160, 25);
        getPanel().add(mb);

        JTextField mbTxt = new JTextField();
        mbTxt.setBounds(100, 110, 160, 25);
        getPanel().add(mbTxt);

        JButton addButton = new JButton("ADD SERVICES");
        addButton.setBounds(15, 140, 160, 25);
        addButton.addActionListener(e -> {
            addServices(minutesTxt, smsTxt, mbTxt, phoneText);
        });
        getPanel().add(addButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(15, 170, 160, 25);
        backButton.addActionListener(e -> {
            getFrame().dispose();
            new AddServicesScreen().createScreen();
        });
        getPanel().add(backButton);

    }

    public static void addServices(JTextField minutes, JTextField sms, JTextField mb, JTextField phone) {
        int min = Integer.parseInt(minutes.getText());
        int esmes = Integer.parseInt(sms.getText());
        int megabyte = Integer.parseInt(mb.getText());
        String number = phone.getText();


        Connection connection = new Connection();
        try {
            PreparedStatement preparedStatement = connection.getConnection().
                    prepareStatement("UPDATE payment " +
                            "SET Mb_remaining = Mb_remaining + ?,"+
                            "minutes_remaining = minutes_remaining + ?,"+
                            " SMS_remaining = SMS_remaining + ?" +
                            "WHERE (SELECT phone FROM  users " +
                            "JOIN dataplans ON users.id = dataplans.user_id " +
                            "JOIN payment ON dataplans.id = payment.dataPlan_id " +
                            "WHERE phone = ?)");
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(1, esmes);
            preparedStatement.setInt(1, megabyte);
            preparedStatement.setString(4,number);

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
