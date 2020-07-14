package screens;

import javax.swing.*;

public class AdminScreen extends Frame {
    private JButton backButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton addServices;


    public AdminScreen() {
        super();
        backButton = new JButton("BACK");
        addButton = new JButton("ADD CLIENT");
        searchButton = new JButton("SEARCH");
        addServices = new JButton("ADD SERVICES");

    }

    public void createScreen() {
        createFrame();

        addButton.setBounds(15,20,160,25);
        addButton.addActionListener(e -> {
            AddScreen addScreen = new AddScreen();
            addScreen.createScreen();
            getFrame().dispose();
        });
        getPanel().add(addButton);

        searchButton.setBounds(15,50,160,25);
        searchButton.addActionListener(e -> {
                SearchScreen searchScreen = new SearchScreen();
                searchScreen.creteScreen();
                getFrame().dispose();
        });
        getPanel().add(searchButton);

        addServices.setBounds(15,80,160,25);
        addServices.addActionListener(e -> {
           AddServicesScreen servicesScreen = new AddServicesScreen();
           servicesScreen.createScreen();
           getFrame().dispose();
        });
        getPanel().add(addServices);

        backButton.setBounds(15,110,160,25);
        backButton.addActionListener(e -> {
            getFrame().dispose();
        });
        getPanel().add(backButton);

        getFrame().setVisible(true);

    }
}
