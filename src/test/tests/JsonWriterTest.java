package tests;

import model.Student;
import model.University;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest{

    @Test
    void testWriterInvalidFile() {
        try {
            University uni = new University("UBC");
            JsonWriter writer = new JsonWriter("./data/\0name.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyUniversity() {
        try {
            University uni = new University("UBC");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUniversity.json");
            writer.open();
            writer.write(uni);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUniversity.json");
            uni = reader.read();
            assertEquals("UBC", uni.getName());
            assertTrue( uni.getDomestic().isEmpty());
            assertTrue( uni.getInternational().isEmpty());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUniversity() {
        try {
            University uni = new University("UBC");
            LocalDate date = LocalDate.of(2021, 3, 6);
            Student inland = new Student("Rahat",true,"Ponds", date,
                    "Canada");

            Student outsider = new Student("Utsav",false,"Marine", date,
                    "India");

            uni.addToDomestic(inland);
            uni.addToInternational(outsider);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUniversity.json");
            writer.open();
            writer.write(uni);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUniversity.json");
            uni = reader.read();
            assertEquals("UBC", uni.getName());
            List<Student> domestic = uni.getDomestic();
            List<Student> international = uni.getInternational();
            assertEquals(1, domestic.size());
            assertEquals(1, international.size());


            assertEquals("Rahat", domestic.get(0).getName());
            assertEquals("Ponds", domestic.get(0).getLocation());
            assertEquals("Canada", domestic.get(0).getCountry());
            assertEquals("2021-03-06", domestic.get(0).getArrivalDate());
            assertTrue(domestic.get(0).getTestReport());

            assertEquals("Utsav", international.get(0).getName());
            assertEquals("Marine", international.get(0).getLocation());
            assertEquals("India", international.get(0).getCountry());
            assertEquals("2021-03-06", international.get(0).getArrivalDate());
            assertFalse(international.get(0).getTestReport());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




}
