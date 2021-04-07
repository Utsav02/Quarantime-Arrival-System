package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a university with domestic and international students arrival list
public class University implements Writable {
    private String name;
    private final ArrayList<Student> domestic;
    private final ArrayList<Student> international;

    //constructs university with empty domestic and international student Arraylist.
    public University(String name) {
        this.name = name;
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
    EFFECTS : adds the student name to the respective list and main list
     */
    public void addToInternational(Student student) {
        international.add(student);
    }

    /*
   REQUIRES: Student input name should not be same to ones already present
   MODIFIES: this
   EFFECTS : adds the student name to the respective list
    */
    public void addStudent(Student student) {
        if (this.searchStudent(student.getName()) == null) {
            if (!student.getCountry().equals("Canada")) {
                addToInternational(student);
            } else {
                addToDomestic(student);
            }
            student.assignToUni(this);
        }
    }

    //EFFECTS returns domestic student list
    public ArrayList<Student> getDomestic() {
        return domestic;
    }

    //EFFECTS returns international student list
    public ArrayList<Student> getInternational() {
        return international;
    }


    //EFFECTS: returns a list of student with positive test report
    public ArrayList<Student> positiveList() {
        ArrayList<Student> list1 = new ArrayList<>();

        for (Student student : this.getDomestic()) {
            if (student.getTestReport()) {
                list1.add(student);
            }
        }
        for (Student student : this.getInternational()) {
            if (student.getTestReport()) {
                list1.add(student);
            }

        }
        return list1;
    }

    /*
    REQUIRES: Name of Student, no duplicates
    EFFECTS: if student name is there, returns it
     */
    public Student searchStudent(String name) {
        Student found = null;

        for (Student value : domestic) {
            if (value.getName().equals(name)) {
                found = value;
            }
        }
        for (Student value : international) {
            if (value.getName().equals(name)) {
                found = value;
            }
        }

        return found;

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

    //EFFECTS: returns name of University
    public String getName() {
        return name;
    }

    // MODIFIES: this, student
    // EFFECTS: removes student from this university
    public void removeStudent(Student student) {
        if (domestic.contains(student)) {
            domestic.remove(student);
            student.removeFromUni();
        }
        if (international.contains(student)) {
            international.remove(student);
            student.removeFromUni();
        }

    }

    //EFFECTS: returns json object with University details in it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("domestic", domesticToJson());
        json.put("international", internationalToJson());
        return json;
    }

    // EFFECTS: returns domestic students in this university as a JSON array
    private JSONArray domesticToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : domestic) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns international students in this university as a JSON array
    private JSONArray internationalToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : international) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}

