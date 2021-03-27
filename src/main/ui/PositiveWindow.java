package ui;

import model.Student;
import model.University;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PositiveWindow {
    University uni;
    JFrame positiveWindow = new JFrame();

    public PositiveWindow(University uni) {
        this.uni = uni;
        prepareGUI();
        labelProperties();
        panelProperties();
    }

    public void prepareGUI() {
        positiveWindow.setTitle("List of Covid-19 Positive Students");
        positiveWindow.getContentPane().setLayout(null);
        positiveWindow.setVisible(true);
        positiveWindow.setSize(1000, 1000);

    }

    public void labelProperties() {
        ArrayList<Student> resultList = uni.positiveList();
        JLabel heading = new JLabel();
        heading.setText("Total Number of Positive Cases:  " + resultList.size());
        heading.setBounds(50, 50, 200, 50);
        positiveWindow.add(heading);

    }


    public void panelProperties() {
        ArrayList<Student> resultList = uni.positiveList();

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
        scrollableTextArea.setBounds(100,100,250,100);

        positiveWindow.getContentPane().add(scrollableTextArea);


    }

}
