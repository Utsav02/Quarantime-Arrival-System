package persistence;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Student;
import model.University;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public University read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUniversity(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses University from JSON object and returns it
    public University parseUniversity(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        University uni = new University(name);
        addStudents(uni, jsonObject);
        return uni;
    }

    //MODIFIES uni
    //EFFECTS: parses Students from JSON object and adds them to University
    private void addStudents(University uni, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("domestic");
        JSONArray jsonArray1 = jsonObject.getJSONArray("international");

        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(uni, nextStudent);
        }

        for (Object json : jsonArray1) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(uni, nextStudent);
        }
    }


    // MODIFIES: uni
    // EFFECTS: parses Student from JSON object and adds it to University
    private void addStudent(University uni, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean result = jsonObject.getBoolean("covidTest");
        String location = jsonObject.getString("quarantineLocation");
        String country = jsonObject.getString("country");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String stringDate = jsonObject.getString("date");
        LocalDate date = LocalDate.parse(stringDate,formatter);
        Student student = new Student(name,result,location,date,country);
        uni.addStudent(student);

    }

}
