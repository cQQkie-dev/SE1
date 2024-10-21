package org.hbrs.se1.ws24.tests.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.*;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container container;
    private Member m1;
    private Member m2;

    @BeforeEach
    public void setUp() {
        container = Container.getInstance();
        container.setPersistenceStrategy(null);
        m1 = new ConcreteMember(1);
        m2 = new ConcreteMember(2);
    }

    @AfterEach
    public void tearDown() {
        // Liste leeren, um saubere Tests zu gewährleisten
        container.getCurrentList().clear();
        container.setPersistenceStrategy(null);
    }


    @Test
    public void testNoStrategySet(){
        assertThrows(PersistenceException.class, () -> {
            container.store();
        }, "Keine Persistenz-Strategie festgelegt!");

        assertThrows(PersistenceException.class, () -> {
            container.load();
        }, "Keine Persistenz-Strategie festgelegt!");
    }

    @Test
    public void testStrategyNotImplemented(){
        PersistenceStrategy<Member> mongoDBStrategy = new PersistenceStrategyMongoDB<>();
        container.setPersistenceStrategy(mongoDBStrategy);
        assertThrows(UnsupportedOperationException.class, () -> {
            container.store();
        }, "Not implemented!");

        assertThrows(UnsupportedOperationException.class, () -> {
            container.load();
        }, "Not implemented!");

        // reset Persistenz Strategie
    }

    @Test
    public void testFileLocation(){
        PersistenceStrategyStream<Member> streamStrategy = new PersistenceStrategyStream<>();
        streamStrategy.setLocation("/invalid/path/to/file");
        container.setPersistenceStrategy(streamStrategy);

        assertThrows(PersistenceException.class, () -> {
            container.store();
        }, "Fehler beim Speichern: " + new FileNotFoundException().getMessage());
        // reset Persistenz Strategie

        assertThrows(PersistenceException.class, () -> {
            container.load();
        }, "Fehler beim Speichern: " + new FileNotFoundException().getMessage());
        // reset Persistenz Strategie
    }

    @Test
    public void testRoundTrip() throws ContainerException, PersistenceException {
        // Strategie und Speicherort festlegen
        PersistenceStrategyStream<Member> fileStrategy = new PersistenceStrategyStream<>();
        fileStrategy.setLocation("members.ser");
        container.setPersistenceStrategy(fileStrategy);

        container.addMember(m1);
        container.addMember(m2);
        assertEquals(container.size(), 2);
        // Speichern und dann löschen
        container.store();
        container.deleteMember(1);
        assertEquals(container.size(),1);
        // laden und IDs vergleichen
        container.load();
        assertEquals(container.size(),2);
        List<Member> members = container.getCurrentList();
        assertEquals(members.get(0).getID(),1);
        assertEquals(members.get(1).getID(),2);
    }
}
