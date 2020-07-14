package screens;

import DataBase.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserScreen extends Frame {

    public UserScreen(){
        super();
    }

    public void createScreen(){
        createFrame();

        JLabel label = new JLabel();
        label.setBounds(500,15,300, 150);
        getPanel().add(label);

        JButton payButton = new JButton("PAY BIll");
        payButton.setBounds(15,20,160,25);
        payButton.addActionListener(e -> {
            label.setText("");
            String sql = " ";
        });
        getPanel().add(payButton);

        JButton checkButton = new JButton("CHECK STATUS");
        checkButton.setBounds(15,50,160,25);
        checkButton.addActionListener(e -> {
            label.setText("");
            String sql = "";
        });
        getPanel().add(checkButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(15,80,80,25);
        backButton.addActionListener(e -> {
            getFrame().dispose();
        });
        getPanel().add(backButton);



        getFrame().setVisible(true);
    }

    public void action(String sql, JLabel label, String message){

        try {
            Connection connection = new Connection();
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);

            label.setText(message + "\n<html> </html>");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
