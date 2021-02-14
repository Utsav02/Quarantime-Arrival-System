package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

// Unit tests for Student and University class
public class StudentAppTest {

    Student s1;
    Student s2;
    Student s3;
    Student s4;
    University u1;
    ArrayList<Student> a1;
    ArrayList<Student> a2;

    @BeforeEach
    void runBefore() {
        s1 = new Student("Max", false, "Marine Drive", LocalDate.now(), "Canada");
        s2 = new Student("Earl", true, "Ponds", LocalDate.now(), "USA");
        s3 = new Student("Han", false, "Exchange", LocalDate.now(), "Mexico");
        s4 = new Student("Oleg", true, "Marine Drive", LocalDate.now(), "Canada");
        a1 = new ArrayList<>();
        a2 = new ArrayList<>();
        u1 = new University();
    }

    @Test
    void testGetName() {
        assertEquals("Max",s1.getName());
        assertEquals("Han",s3.getName());
        assertEquals("Earl",s2.getName());
        assertEquals("Oleg",s4.getName());
    }

    @Test
    void testGetLocation() {
        assertEquals("Marine Drive",s1.getLocation());
        assertEquals("Exchange",s3.getLocation());
        assertEquals("Ponds",s2.getLocation());
        assertEquals("Marine Drive",s4.getLocation());
    }

    @Test
    void testGetTestReport() {
        assertFalse(s1.getTestReport());
        assertFalse(s3.getTestReport());
        assertTrue(s2.getTestReport());
        assertTrue(s4.getTestReport());
    }
    @Test
    void testGetCountry() {
        assertEquals("Canada",s1.getCountry());
        assertEquals("Mexico",s3.getCountry());
        assertEquals("USA",s2.getCountry());
        assertEquals("Canada",s4.getCountry());
    }
    @Test
    void testGetDate() {
        assertEquals(LocalDate.now(),s1.getArrivalDate());
        assertEquals(LocalDate.now(),s2.getArrivalDate());
        assertEquals(LocalDate.now(),s3.getArrivalDate());
        assertEquals(LocalDate.now(),s4.getArrivalDate());
    }

    @Test
    void testGetDomesticSize() {
        u1.addToDomestic(s1);
        u1.addToDomestic(s4);
        assertEquals(2,u1.getDomesticSize());
    }

    @Test
    void testGetInternationalSize() {
        u1.addToInternational(s2);
        u1.addToInternational(s3);
        assertEquals(2,u1.getInternationalSize());
    }

    @Test
    void testTotalStudents() {
        u1.addToInternational(s2);
        u1.addToInternational(s3);
        u1.addToDomestic(s1);
        u1.addToDomestic(s4);
        assertEquals(4,u1.totalStudents());
    }

    @Test
    void testGetDomestic() {
        u1.addToDomestic(s1);
        u1.addToDomestic(s4);
        a1.add(s1);
        a1.add(s4);
        assertEquals(a1,u1.getDomestic());

    }
    @Test
    void testGetInternational() {
        u1.addToInternational(s2);
        u1.addToInternational(s3);
        a2.add(s2);
        a2.add(s3);
        assertEquals(a2,u1.getInternational());
    }


    @Test
    void testSearchStudent() {
        u1.addToInternational(s3);
        a2.add(s3);
        u1.addToDomestic(s1);
        a1.add(s1);
        assertEquals(a1,u1.searchStudent("Max"));
        assertEquals(a2,u1.searchStudent("Han"));
        assertEquals(a1,u1.searchStudent("Max"));



    }

}











