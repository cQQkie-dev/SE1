package org.hbrs.se1.ws24.exercises.uebung2;

public class Main {
    public static void main(String[] args) throws ContainerException {
        // Instanz des Containers erhalten
        Container container = Container.getInstance();

        // Erzeugen von Member-Objekten und Hinzufügen zum Container
        container.addMember(new ConcreteMember(1));
        container.addMember(new ConcreteMember(2));
        container.addMember(new ConcreteMember(3));

        // Auslesen der aktuellen Liste von Member-Objekten
        MemberView memberView = new MemberView();
        memberView.dump(container.getCurrentList());
    }
}

