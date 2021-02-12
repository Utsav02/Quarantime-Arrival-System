package model;


import java.util.ArrayList;

public class University {
    private final ArrayList<Student> domestic;
    private final ArrayList<Student> international;

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

    public ArrayList<Student> getDomestic() {
        return domestic;
    }

    public ArrayList<Student> getInternational() {
        return international;
    }

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

}
