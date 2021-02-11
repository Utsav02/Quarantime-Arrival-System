package model;


import java.util.ArrayList;

public class University {
    private ArrayList<Student> domestic;
    private ArrayList<Student> international;

    public University() {
        domestic = new ArrayList<>();
        international = new ArrayList<>();
    }


    public void addToDomestic(Student student) {
        domestic.add(student);
    }

    public void addToInternational(Student student) {
        international.add(student);
    }

    public void searchName(String name) {
        for (Student value : domestic) {
            if (value.getName().equals(name)) {
                System.out.println("Name: " + value.getName());
                System.out.println("Covid Report: " + value.getTestReport());
                System.out.println("Staying at: " + value.getLocation());
                System.out.println("Reached Campus on: " + value.getArrivalDate());
                System.out.println("Flew in from: " + value.getCountry());
            }
        }
        for (Student student : international) {
            if (student.getName().equals(name)) {
                System.out.println("Name: " + student.getName());
                System.out.println("Covid Report: " + student.getTestReport());
                System.out.println("Staying at: " + student.getLocation());
                System.out.println("Reached Campus on: " + student.getArrivalDate());
                System.out.println("Flew in from: " + student.getCountry());
            }
        }

    }


    public int totalStudents() {
        int canadians = domestic.size();
        int foreigners = international.size();
        int total = canadians + foreigners;
        return total;
    }

    public int getDomesticSize() {
        return domestic.size();
    }

    public int getInternationalSize() {
        return international.size();
    }

    public void allStudents() {
        int count = 0;
        int counter = 0;
        while (domestic.size() > count) {
            System.out.println(domestic.get(count).getName());
            count++;
        }
        while (international.size() > counter) {
            System.out.println(international.get(counter).getName());
            counter++;
        }
    }

    public void displayPositive() {
        for (Student student : domestic) {
            if (student.getTestReport()) {
                System.out.println(student.getName());
            }
        }
        for (Student student : international) {
            if (student.getTestReport()) {
                System.out.println(student.getName());
            }

        }
    }
}
