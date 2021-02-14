package model;


import java.util.ArrayList;

 // represents a university with domestic and international students arrival list
public class University {
    private final ArrayList<Student> domestic;
    private final ArrayList<Student> international;

    //constructs university with empty domestic and international student Arraylist.
    public University() {
        domestic = new ArrayList<>();
        international = new ArrayList<>();
    }

    /*
    REQUIRES: Student input
    MODIFIES: this
    EFFECTS : adds the student name to the list
     */
    public void addToDomestic(Student student) {
        domestic.add(student);
    }

     /*
     REQUIRES: Student input
     MODIFIES: this
     EFFECTS : adds the student name to the list
      */
    public void addToInternational(Student student) {
        international.add(student);
    }

    public ArrayList<Student> getDomestic() {
        return domestic;
    }

    public ArrayList<Student> getInternational() {
        return international;
    }

    /*
    REQUIRES: Name of Student
    EFFECTS: if student name is there, adds student to list
             checks for multiple similar student names
             returns list
     */
    public ArrayList<Student> searchStudent(String name) {
        ArrayList<Student> result = new ArrayList<>();

        for (Student value : domestic) {
            if (value.getName().equals(name)) {
                result.add(value);
            }
        }
        for (Student value : international) {
            if (value.getName().equals(name)) {
                result.add(value);
            }
        }
        return result;
    }

    // EFFECTS: returns total number of students

    public int totalStudents() {
        int canadians = domestic.size();
        int foreigners = international.size();
        return canadians + foreigners;
    }

    // EFFECTS: returns total number of domestic arrivals

    public int getDomesticSize() {
        return domestic.size();
    }

     // EFFECTS: returns total number of international arrivals

    public int getInternationalSize() {
        return international.size();
    }

}
