package ui;

import model.Student;
import model.University;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class StudentAppUi implements ActionListener {

    private static final String JSON_LOC = "./data/ubc.json";

    University ubc = new University("University of British Columbia");
    JsonWriter jsonWriter = new JsonWriter(JSON_LOC);
    JsonReader jsonReader = new JsonReader(JSON_LOC);


    JFrame window = new JFrame();

    JButton addStudentButton = new JButton("Add Student");
    JButton totalStudentsButton = new JButton("Display Total Students");
    JButton totalInternationalButton = new JButton("Display Total International Students");
    JButton displayPositiveButton = new JButton("COVID-19 Positive List");
    JButton displayAllButton = new JButton("Display All Students");
    JButton saveButton = new JButton("Save Data");
    JButton loadButton = new JButton("Load Data");

    JLabel totalStudentsLabel = new JLabel();
    JLabel totalInternationalLabel = new JLabel();

    JLabel name = new JLabel();
    JLabel location = new JLabel();
    JLabel country = new JLabel();
    JLabel test = new JLabel();

    JTextField nameText = new JTextField();
    JTextField locationText = new JTextField();
    JTextField countryText = new JTextField();
    JTextField reportText = new JTextField();


    StudentAppUi() {
        prepareGUI();
        buttonProperties();
        textFieldProperties();
        labelProperties();
        displayLogo();


    }

    public void prepareGUI() {

        window.setTitle("Student Arrival System");
        window.getContentPane().setLayout(null);
        window.setVisible(true);
        window.setSize(1000, 1000);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void buttonProperties() {
        addStudentButton.setBounds(20, 450, 200, 50);
        totalStudentsButton.setBounds(600, 700, 200, 50);
        totalInternationalButton.setBounds(300, 700, 250, 50);
        displayPositiveButton.setBounds(300, 800, 250, 50);
        displayAllButton.setBounds(600, 800, 200, 50);
        saveButton.setBounds(20, 700, 200, 50);
        loadButton.setBounds(20, 800, 200, 50);

        window.add(totalStudentsButton);
        window.add(addStudentButton);
        window.add(totalInternationalButton);
        window.add(displayPositiveButton);
        window.add(displayAllButton);
        window.add(saveButton);
        window.add(loadButton);

        addStudentButton.addActionListener(this);//Registering ActionListener to the button
        totalStudentsButton.addActionListener(this);
        totalInternationalButton.addActionListener(this);
        displayPositiveButton.addActionListener(this);
        displayAllButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

    }

    public void textFieldProperties() {

        nameText.setBounds(20,250,150,20);
        locationText.setBounds(20,300,150,20);
        countryText.setBounds(20,350,150,20);
        reportText.setBounds(20,400,150,20);

        window.add(nameText);
        window.add(locationText);
        window.add(countryText);
        window.add(reportText);
    }

    public void labelProperties() {
        name.setText("Name of Student");
        location.setText("Student's Quarantine Residence");
        country.setText("Student's Home Country");
        test.setText("Test Report: (True/False)");

        totalStudentsLabel.setBounds(600, 650, 150, 50);
        totalInternationalLabel.setBounds(300, 650, 300, 50);

        name.setBounds(200, 250, 100, 20);
        location.setBounds(200, 300, 200, 20);
        country.setBounds(200, 350, 200, 20);
        test.setBounds(200, 400, 200, 20);



        window.add(totalStudentsLabel);
        window.add(totalInternationalLabel);
        window.add(name);
        window.add(location);
        window.add(country);
        window.add(test);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addStudentButton) {
            addStudent();
        } else if (e.getSource() == totalStudentsButton) {
            totalStudentsLabel.setText("Total Students:  " + ubc.totalStudents());

        } else if (e.getSource() == totalInternationalButton) {
            totalInternationalLabel.setText("Total International Students   " + ubc.getInternationalSize());

        } else if (e.getSource() == displayPositiveButton) {
            displayPositive();

        } else if (e.getSource() == displayAllButton) {
            displayAll();

        } else if (e.getSource() == saveButton) {
            saveData();

        } else if (e.getSource() == loadButton) {
            loadData();
        }

    }

    public void addStudent() {
        String name = nameText.getText();
        String location = locationText.getText();
        String country = countryText.getText();
        String r = reportText.getText();
        Boolean report = Boolean.parseBoolean(r);
        LocalDate date = LocalDate.now();

        Student j = new Student(name, report, location, date, country);

        ubc.addStudent(j);
        clearTextFields();
        JOptionPane.showMessageDialog(window,"Student Added Successfully.");

        totalStudentsLabel.setText("Total Students:  " + ubc.totalStudents());
        totalInternationalLabel.setText("Total International Students   " + ubc.getInternationalSize());

    }

    public void clearTextFields() {
        nameText.setText("");
        locationText.setText("");
        countryText.setText("");
        reportText.setText("");
    }

    public void displayPositive() {
        new PositiveWindow(ubc);
    }

    public void displayAll() {
        new AllStudentsWindow(ubc);
    }

    public void displayLogo() {
        ImageIcon imageIcon = new ImageIcon("./data/logo2.png");
        Image icon = Toolkit.getDefaultToolkit().getImage("./data/download.png");
        JLabel imageLabel = new JLabel(imageIcon);

//        imageLabel.setBounds(100,100,800,500);
        imageLabel.setBounds(new Rectangle(1000,200));
        window.add(imageLabel,BorderLayout.NORTH);
        window.setIconImage(icon);
        SwingUtilities.updateComponentTreeUI(window);

    }

    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(ubc);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_LOC);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOC);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads University from file
    private void loadData() {
        try {
            ubc = jsonReader.read();
            System.out.println("Loaded " + "from " + JSON_LOC);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOC);
        }
    }


}
