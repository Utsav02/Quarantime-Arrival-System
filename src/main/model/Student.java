package model;

import java.time.LocalDate;
import java.util.Date;

//represents a Student with their name, result of covidTest, quarantineLocation
public class Student {

    private String name; // name of the student
    private boolean covidTest; // result of student's covid test
    private String quarantineLocation; // where student is quarantining themselves in
    private LocalDate arrivalDate; //date of arrival


    /*
     * REQUIRES: studentName should be non-zero
     * EFFECTS: name of student is set to studentName;
     *          arrival is set to arrivalDate
     * Covid test is in boolean True = positive, False = negative
     * location is set to quarantine location of student
     */
    public Student(String studentName, boolean report, String location, LocalDate arrival) {
        name = studentName;
        arrivalDate = arrival;
        quarantineLocation = location;
        covidTest = report;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return quarantineLocation;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public Boolean getTestReport() {
        return covidTest;
    }


}