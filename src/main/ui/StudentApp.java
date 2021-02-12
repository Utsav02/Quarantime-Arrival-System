package ui;


import model.Student;
import model.University;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentApp {
    University ubc = new University();

    public StudentApp() {

        runStudentApp();
    }

    public void runStudentApp() {

        Scanner option = new Scanner(System.in);
        boolean proceed = true;

        while (proceed) {
            displayOptions();
            String choice = option.next();

            if (choice.equals("g")) {
                proceed = false;
            } else {
                searchChoice(choice);
            }

        }
        System.out.println("Thank you and have a nice day!");
    }

    public void searchChoice(String userInput) {
        if (userInput.equals("a")) {
            addStudent();
        } else if (userInput.equals("b")) {
            displayStudent();
        } else if (userInput.equals("c")) {
            displayTotal();
        } else if (userInput.equals("d")) {
            displayTotalInternational();
        } else if (userInput.equals("e")) {
            displayPositive();
        } else if (userInput.equals("f")) {
            displayAll();
        } else {
            System.out.println("Kindly input valid options only.");
        }
    }

    public void displayOptions() {
        System.out.println("What would you like to do today:");
        System.out.println("Please choose an option:");
        System.out.println("\na. Add a new student arrival");
        System.out.println("\nb. Display a student's details");
        System.out.println("\nc. Display total students in quarantine");
        System.out.println("\nd. Display total international arrivals");
        System.out.println("\ne. Display students with positive test report");
        System.out.println("\nf. Display all students");
        System.out.println("\ng. Do you wish to exit?");

    }

    public void addStudent() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Name:");
        String name = userInput.nextLine();


        System.out.println("Staying at:");
        String location = userInput.nextLine();


        System.out.println("Student's Country:");
        String country = userInput.nextLine();


        System.out.println("Report (true/false)");
        boolean report = userInput.nextBoolean();

        LocalDate date = LocalDate.now();


        Student j = new Student(name, report, location, date, country);

        if (country.equals("Canada")) {
            ubc.addToDomestic(j);
        } else {
            ubc.addToInternational(j);
        }
    }

    public void displayStudent() {
        System.out.println("Please enter the name of the student:");
        Scanner userInput = new Scanner(System.in);
        String toFind = userInput.next();
        searchName(toFind);
    }

    public void searchName(String name) {
        ArrayList<Student> result = ubc.searchStudent(name);
        boolean proceed = true;
        while (proceed) {
            if (result.isEmpty()) {
                System.out.println("No student with such name was found.");
                proceed = false;
            } else {
                System.out.println("Following records with the name given were found");
                for (Student student : result) {
                    System.out.println(student.getName());
                }
                System.out.println("Kindly confirm the name again :");
                Scanner userInput = new Scanner(System.in);
                String toFind = userInput.next();
                printDetails(toFind);
                proceed = false;
            }
        }
    }


    public void printDetails(String name) {
        ArrayList<Student> mergedList = new ArrayList<>();
        mergedList.addAll(ubc.getDomestic());
        mergedList.addAll(ubc.getInternational());

        for (Student student : mergedList) {
            if (student.getName().equals(name)) {
                System.out.println("Name: " + student.getName());
                System.out.println("Covid Report: " + student.getTestReport());
                System.out.println("Staying at: " + student.getLocation());
                System.out.println("Reached Campus on: " + student.getArrivalDate());
                System.out.println("Flew in from: " + student.getCountry());
            }
        }

    }


    public void displayTotal() {
        System.out.println("Total Students: " + ubc.totalStudents());

    }

    public void displayTotalInternational() {
        System.out.println("Total International Students: " + ubc.getInternationalSize());
    }

    public void displayAll() {
        int count = 0;
        int counter = 0;
        while (ubc.getDomestic().size() > count) {
            System.out.println(ubc.getDomestic().get(count).getName());
            count++;
        }
        while (ubc.getInternational().size() > counter) {
            System.out.println(ubc.getInternational().get(counter).getName());
            counter++;
        }
    }

    public void displayPositive() {
        for (Student student : ubc.getDomestic()) {
            if (student.getTestReport()) {
                System.out.println(student.getName());
            }
        }
        for (Student student : ubc.getInternational()) {
            if (student.getTestReport()) {
                System.out.println(student.getName());
            }

        }
    }

}