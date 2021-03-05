package tests;

import model.Student;
import model.University;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            University uni = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUniversity() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUniversity.json");
        try {
            University uni = reader.read();
            assertEquals("University of British Columbia", uni.getName());
            assertTrue(uni.getDomestic().isEmpty());
            assertTrue(uni.getInternational().isEmpty());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFilledUni() {
        JsonReader reader = new JsonReader("./data/testReaderFilled.json");
        try {
            University uni = reader.read();
            assertEquals("University of British Columbia", uni.getName());
            List<Student> domestic = uni.getDomestic();
            List<Student> international = uni.getInternational();
            assertEquals(1, domestic.size());
            assertEquals(1, international.size());

            assertEquals("Ben", domestic.get(0).getName());
            assertEquals("Ten", domestic.get(0).getLocation());
            assertEquals("Canada", domestic.get(0).getCountry());
            assertEquals("2021-03-06", domestic.get(0).getArrivalDate());
            assertTrue(domestic.get(0).getTestReport());

            assertEquals("Utsav", international.get(0).getName());
            assertEquals("Ponds", international.get(0).getLocation());
            assertEquals("India", international.get(0).getCountry());
            assertEquals("2021-03-02", international.get(0).getArrivalDate());
            assertFalse(international.get(0).getTestReport());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
