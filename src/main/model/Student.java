package model;



import java.time.LocalDate;


//represents a Student with their name, result of covidTest, quarantineLocation
public class Student {

    private final String name; // name of the student
    private final Boolean covidTest; // result of student's covid test
    private final String quarantineLocation; // where student is quarantining themselves in
    private final LocalDate arrivalDate; //date of arrival
    private final String  country;

    /*
     * REQUIRES: studentName should be string, Date in YYYY-MM-DD format
     * EFFECTS: name of student is set to studentName;
     *          arrival is set to arrivalDate
     * Covid test is in boolean True = positive, False = negative
     * location is set to quarantine location of student
     */
    public Student(String studentName, Boolean report, String location, LocalDate arrival, String departureCountry) {
        name = studentName;
        arrivalDate = arrival;
        quarantineLocation = location;
        covidTest = report;
        country = departureCountry;
    }

    // EFFECTS: returns name of student

    public String getName() {
        return name;
    }

    // EFFECTS: returns location of student

    public String getLocation() {
        return quarantineLocation;
    }

    // EFFECTS: returns arrival date of student
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    // EFFECTS: returns result of void test of student
    public Boolean getTestReport() {
        return covidTest;
    }

    // EFFECTS: returns country of student
    public String getCountry() {
        return country;
    }


}
