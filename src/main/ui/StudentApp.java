package ui;


import model.University;

import java.util.Scanner;



public class StudentApp {

    public StudentApp() {

        runStudentApp();
    }

    public void runStudentApp() {
        Scanner option = new Scanner(System.in);
        boolean proceed = true;

        while (proceed) {
            displayOptions();
            String choice = option.next();

            if (choice.equals("f")) {
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
        System.out.println("\nf. Do you wish to exit?");

    }

    public void addStudent() {


    }

    public String displayStudent() {
        System.out.println("Please enter the name of the student:");
        Scanner userInput = new Scanner(System.in);
        String name =  userInput.next();
        String result = University.searchName(name);
        return result;

    }

    public void displayTotal() {

    }

    public void displayTotalInternational() {

    }

    public void displayPositive() {

    }

}