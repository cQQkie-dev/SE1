package org.hbrs.se1.ws24.tests.uebung2;

import org.hbrs.se1.ws24.exercises.uebung2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container container;
    private Member m1;
    private Member m2;

    @BeforeEach
    public void setUp() {
        container = new Container();
        m1 = new ConcreteMember(1);
        m2 = new ConcreteMember(2);
    }

    @Test
    public void testAddMember() throws ContainerException {
        container.addMember(m1);
        assertEquals(1, container.size());

        container.addMember(m2);
        assertEquals(2, container.size());

        Exception exception = assertThrows(ContainerException.class, () -> {
            container.addMember(m1);
        });
        assertEquals("Das Member-Objekt mit der ID 1 ist bereits vorhanden!", exception.getMessage());
    }
    @Test
    public void testDeleteMember() throws ContainerException {
        container.addMember(m1);
        container.addMember(m2);

        assertEquals(container.deleteMember(1), "Member mit ID 1 wurde gelöscht.");
        assertEquals(1, container.size());

        assertEquals("Member mit ID 3 nicht gefunden.", container.deleteMember(3));
        assertEquals(1, container.size());
    }
    @Test
    public void testDump() throws ContainerException {
        container.addMember(m1);
        container.addMember(m2);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        container.dump();

        String expectedOutput = "Member (ID = 1)\nMember (ID = 2)\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testSize() throws ContainerException {
        assertEquals(0, container.size());

        container.addMember(m1);
        assertEquals(1, container.size());

        container.addMember(m2);
        assertEquals(2, container.size());

        container.deleteMember(1);
        assertEquals(1, container.size());
    }
}
