package model;


import java.util.ArrayList;

public class University {
    private static String uniName = "University of British Columbia";

    public University() {

    }

    ArrayList<Student> domestic = new ArrayList<Student>();
    ArrayList<Student> international = new ArrayList<Student>();

    public void addToDomestic(Student student) {
        domestic.add(student);
    }

    public void addToInternational(Student student) {
        international.add(student);
    }

    public void searchName(String name) {
        for (int i = 0; i < domestic.size(); i++) {
            if (domestic.get(i).getName().equals(name)) {
                System.out.println("Name: " + domestic.get(i).getName());
                System.out.println("Covid Report: " + domestic.get(i).getTestReport());
                System.out.println("Staying at: " + domestic.get(i).getLocation());
                System.out.println("Reached Campus on: " + domestic.get(i).getArrivalDate());
                System.out.println("Flew in from: " + domestic.get(i).getCountry());

            }
        }

        for (int i = 0; i < international.size(); i++) {
            if (international.get(i).getName().equals(name)) {
                System.out.println("Name: " + international.get(i).getName());
                System.out.println("Covid Report: " + international.get(i).getTestReport());
                System.out.println("Staying at: " + international.get(i).getLocation());
                System.out.println("Reached Campus on: " + international.get(i).getArrivalDate());
                System.out.println("Flew in from: " + international.get(i).getCountry());
            } else {
                System.out.println("Student not found");
            }
        }

    }

    public int totalStudents() {
        int canadians = domestic.size();
        int foreigners = international.size();
        return canadians + foreigners;
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
            System.out.println(count + "." + domestic.get(count).getName());
            count++;
        }
        while (international.size() > counter) {
            System.out.println(counter + "." + international.get(counter).getName());
            counter++;
        }
    }

    public void displayPositive() {
        for (int i = 0; i < domestic.size(); i++) {
            if (domestic.get(i).getTestReport()) {
                System.out.println(domestic.get(i).getName());
            }
        }
        for (int i = 0; i < international.size(); i++) {
            if (international.get(i).getTestReport()) {
                System.out.println(international.get(i).getName());
            }

        }
    }
}
