package ui;

import model.Student;
import model.University;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//New frame to display all students and search up a student

public class AllStudentsWindow implements ActionListener {
    University uni;
    JFrame allStudentsWindow = new JFrame();
    JButton searchButton = new JButton("Search");
    JTextField searchName = new JTextField();
    JLabel studentHeading = new JLabel();
    JLabel studentName = new JLabel();
    JLabel studentCountry = new JLabel();
    JLabel studentDate = new JLabel();
    JLabel studentTest = new JLabel();
    JLabel studentLocation = new JLabel();



    public AllStudentsWindow(University uni) {
        this.uni = uni;
        prepareGUI();
        textProperties();
        buttonProperties();
        labelProperties();
        panelProperties();

    }

    //EFFECTS prepares the main window
    public void prepareGUI() {
        allStudentsWindow.setTitle("List of All Students");
        allStudentsWindow.getContentPane().setLayout(null);
        allStudentsWindow.setVisible(true);
        allStudentsWindow.setSize(1000, 1000);
    }

    //EFFECTS prepares the text fields for the window
    public void textProperties() {

        searchName.setBounds(150,120,100,20);
        allStudentsWindow.add(searchName);
    }

    //EFFECTS prepares the buttons for the window
    public void buttonProperties() {

        searchButton.setBounds(270,120,100,20);

        allStudentsWindow.add(searchButton);

        searchButton.addActionListener(this);

    }

    //EFFECTS prepares the labels for the window
    public void labelProperties() {
        JLabel heading = new JLabel();
        JLabel search = new JLabel("Search Student");
        heading.setText("Total Number of Students:  " + uni.totalStudents());
        heading.setBounds(50, 50, 200, 50);
        search.setBounds(50,105,200,50);
        allStudentsWindow.add(heading);
        allStudentsWindow.add(search);
        studentHeading.setBounds(500,200,150,50);
        studentName.setBounds(500,250,300,50);
        studentLocation.setBounds(500,300,300,50);
        studentCountry.setBounds(500,350,300,50);
        studentDate.setBounds(500,400,300,50);
        studentTest.setBounds(500,450,300,50);
    }

    //EFFECTS: constructs a scroll panel with all students name in it
    public void panelProperties() {
        ArrayList<Student> resultList = new ArrayList<>();
        resultList.addAll(uni.getDomestic());
        resultList.addAll(uni.getInternational());

        ArrayList<String> stringList = new ArrayList<>();

        for (Student student : resultList) {
            stringList.add(student.getName());
        }

        JList data = new JList(stringList.toArray());
        data.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        data.setLayoutOrientation(JList.VERTICAL);
        data.setVisibleRowCount(-1);
        JScrollPane scrollableTextArea = new JScrollPane(data);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setBounds(100,200,250,400);
        allStudentsWindow.getContentPane().add(scrollableTextArea);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchStudent();
        }
    }

    //EFFECTS: returns details of student onto screen, message if student not found
    public void searchStudent() {
        String name = searchName.getText();
        Student found = uni.searchStudent(name);
        if (found == null) {
            JOptionPane.showMessageDialog(allStudentsWindow,"Student not found. Recheck entry");
            searchName.setText("");
        } else {
            String nameOfStudent = found.getName();
            String location = found.getLocation();
            String date = found.getArrivalDate();
            Boolean test = found.getTestReport();
            String country = found.getCountry();
            displayDetails(nameOfStudent,location,date,test,country);

        }
    }

    //EFFECTS: displays details on window
    public void displayDetails(String name, String loc, String date, Boolean value,String place) {
        studentHeading.setText("Details of entered Student:");
        studentName.setText("Name: " + name);
        studentCountry.setText("Country: " + place);
        studentLocation.setText("Quarantining at: " + loc);
        studentDate.setText("Date of Arrival: " + date);
        studentTest.setText("Test Report: " + value);
        allStudentsWindow.add(studentHeading);
        allStudentsWindow.add(studentName);
        allStudentsWindow.add(studentCountry);
        allStudentsWindow.add(studentLocation);
        allStudentsWindow.add(studentDate);
        allStudentsWindow.add(studentTest);
        SwingUtilities.updateComponentTreeUI(allStudentsWindow);

    }


}
