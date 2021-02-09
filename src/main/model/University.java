package model;


import java.util.ArrayList;

public class University {


    ArrayList<Student> domestic = new ArrayList<Student>();
    ArrayList<Student> international = new ArrayList<Student>();

    public String searchName(String name) {

        if (domestic.contains(name) || international.contains(name)) {
            String studentName  = name;
            return studentName;
        }
        String message = "Student not found";
        return message;
    }



}
