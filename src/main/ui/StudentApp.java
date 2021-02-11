package ui;


import model.Student;
import model.University;

import java.time.LocalDate;
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
        String name =  userInput.nextLine();


        System.out.println("Staying at:");
        String location = userInput.nextLine();


        System.out.println("Student's Country:");
        String country = userInput.nextLine();


        System.out.println("Report (true/false)");
        boolean report = userInput.nextBoolean();

        LocalDate  date =  LocalDate.now();


        Student j = new Student(name,report,location,date,country);

        if (country.equals("Canada")) {
            ubc.addToDomestic(j);
        } else {
            ubc.addToInternational(j);
        }
    }

    public void displayStudent() {
        System.out.println("Please enter the name of the student:");
        Scanner userInput = new Scanner(System.in);
        String name =  userInput.next();
        ubc.searchName(name);
    }

    public void displayTotal() {
        System.out.println("Total Domestic Students: " + ubc.totalStudents());

    }

    public void displayTotalDomestic() {
        System.out.println("Total Domestic Students: " + ubc.getDomesticSize());
    }

    public void displayTotalInternational() {
        System.out.println("Total Domestic Students: " + ubc.getInternationalSize());
    }

    public void displayPositive() {
        ubc.displayPositive();

    }

    public void displayAll() {
        ubc.allStudents();
    }

}