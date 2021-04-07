//package ui;
//
//
//import model.Student;
//import model.University;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//// Represents a Student Arrival Application
//public class StudentApp {
//    private static final String JSON_LOC = "./data/ubc.json";
//    private final JsonWriter jsonWriter;
//    private final JsonReader jsonReader;
//    private University ubc;
//
//    // EFFECTS: runs the student arrival application
//    public StudentApp() {
//        ubc = new University("University of British Columbia");
//        jsonWriter = new JsonWriter(JSON_LOC);
//        jsonReader = new JsonReader(JSON_LOC);
//        runStudentApp();
//    }
//
//    // MODIFIES: this
//    //EFFECTS : processes user's input
//    public void runStudentApp() {
//
//        Scanner option = new Scanner(System.in);
//        boolean proceed = true;
//
//        while (proceed) {
//            displayOptions();
//            String choice = option.next();
//
//            if (choice.equals("i")) {
//                proceed = false;
//            } else {
//                searchChoice(choice);
//            }
//
//        }
//        System.out.println("Thank you and have a nice day!");
//    }
//
//    // MODIFIES: this
//    //EFFECTS : processes user's input
//    public void searchChoice(String userInput) {
//        if (userInput.equals("a")) {
//            addStudent();
//        } else if (userInput.equals("b")) {
//            displayStudent();
//        } else if (userInput.equals("c")) {
//            displayTotal();
//        } else if (userInput.equals("d")) {
//            displayTotalInternational();
//        } else if (userInput.equals("e")) {
//            displayPositive();
//        } else if (userInput.equals("f")) {
//            displayAll();
//        } else if (userInput.equals("g")) {
//            saveUniversity();
//        } else if (userInput.equals("h")) {
//            loadUniversity();
//        } else {
//            System.out.println("Kindly input valid options only.");
//        }
//    }
//
//    //EFFECTS : displays options to user
//    public void displayOptions() {
//        System.out.println("What would you like to do today:");
//        System.out.println("Please choose an option:");
//        System.out.println("\na. Add a new student arrival");
//        System.out.println("\nb. Display a student's details");
//        System.out.println("\nc. Display total students in quarantine");
//        System.out.println("\nd. Display total international arrivals");
//        System.out.println("\ne. Display students with positive test report");
//        System.out.println("\nf. Display all students");
//        System.out.println("\ng. Save Data");
//        System.out.println("\nh. Load Data");
//        System.out.println("\ni. Do you wish to exit?");
//
//    }
//
//    //MODIFIES : this
//    //EFFECTS  : adds student details to appropriate list
//    public void addStudent() {
//        Scanner userInput = new Scanner(System.in);
//
//        System.out.println("Name:");
//        String name = userInput.nextLine();
//
//
//        System.out.println("Staying at:");
//        String location = userInput.nextLine();
//
//
//        System.out.println("Student's Country:");
//        String country = userInput.nextLine();
//
//
//        System.out.println("Report (true/false)");
//        boolean report = userInput.nextBoolean();
//
//        LocalDate date = LocalDate.now();
//
//
//        Student j = new Student(name, report, location, date, country);
//
//        ubc.addStudent(j);
//
//    }
//
//    //REQUIRES : name of Student
//    //EFFECTS  : returns Student Details
//    public void displayStudent() {
//        System.out.println("Please enter the name of the student:");
//        Scanner userInput = new Scanner(System.in);
//        String toFind = userInput.next();
//        searchName(toFind);
//    }
//
//    // Requires : name of Student
//    // Returns : list of student with the name
//    public void searchName(String name) {
//        Student result = ubc.searchStudent(name);
//        boolean proceed = true;
//        while (proceed) {
//            if (result == null) {
//                System.out.println("No student with such name was found.");
//                proceed = false;
//            } else {
//                System.out.println("Kindly confirm the name again :");
//                Scanner userInput = new Scanner(System.in);
//                String toFind = userInput.next();
//                printDetails(toFind);
//                proceed = false;
//            }
//        }
//    }
//
//    // REQUIRES: name of student
//    // EFFECTS : returns student's details
//    public void printDetails(String name) {
//        ArrayList<Student> mergedList = new ArrayList<>();
//        mergedList.addAll(ubc.getDomestic());
//        mergedList.addAll(ubc.getInternational());
//
//        for (Student student : mergedList) {
//            if (student.getName().equals(name)) {
//                System.out.println("Name: " + student.getName());
//                System.out.println("Covid Report: " + student.getTestReport());
//                System.out.println("Staying at: " + student.getLocation());
//                System.out.println("Reached Campus on: " + student.getArrivalDate());
//                System.out.println("Flew in from: " + student.getCountry());
//            }
//        }
//
//    }
//
//    // EFFECTS : displays total number of students
//    public void displayTotal() {
//        System.out.println("Total Students: " + ubc.totalStudents());
//
//    }
//
//    // EFFECTS : displays total number of international students
//    public void displayTotalInternational() {
//        System.out.println("Total International Students: " + ubc.getInternationalSize());
//    }
//
//    //// EFFECTS : displays names of all students
//    public void displayAll() {
//        int count = 0;
//        int counter = 0;
//        while (ubc.getDomestic().size() > count) {
//            System.out.println(ubc.getDomestic().get(count).getName());
//            count++;
//        }
//        while (ubc.getInternational().size() > counter) {
//            System.out.println(ubc.getInternational().get(counter).getName());
//            counter++;
//        }
//    }
//
//    // EFFECTS : displays names of students with positive covid-19 report
//    public void displayPositive() {
//        for (Student student : ubc.getDomestic()) {
//            if (student.getTestReport()) {
//                System.out.println(student.getName());
//            }
//        }
//        for (Student student : ubc.getInternational()) {
//            if (student.getTestReport()) {
//                System.out.println(student.getName());
//            }
//
//        }
//    }
//
//    // EFFECTS: saves the workroom to file
//    public void saveUniversity() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(ubc);
//            jsonWriter.close();
//            System.out.println("Saved " + " to " + JSON_LOC);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_LOC);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads workroom from file
//    private void loadUniversity() {
//        try {
//            ubc = jsonReader.read();
//            System.out.println("Loaded " + "from " + JSON_LOC);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_LOC);
//        }
//    }
//
//}
//
//
