package org.hbrs.se1.ws24.exercises.uebung2;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();
    private static Container instance = null;
    private PersistenceStrategy<Member> persistenceStrategy;

    // Überschreibe Sichtbarkeit von Konstruktor
    private Container(){}

    // Erzeuge per Klassenmethode einen neuen Container einmalig
    public static Container getInstance(){
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        this.persistenceStrategy = strategy;
    }

    public void addMember(Member member) throws ContainerException{
        for(Member m: members){
            if(m.getID().equals(member.getID())){
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
        members.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
                members.remove(m);
                return "Member mit ID " + id + " wurde gelöscht.";
            }
        }
        return "Member mit ID " + id + " nicht gefunden.";
    }

    public List<Member> getCurrentList(){
        return this.members;
    }
    public int size(){
        return getCurrentList().toArray().length;
    }

    public void store() throws PersistenceException{
        if(persistenceStrategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenz-Strategie festgelegt!");
        }
        this.persistenceStrategy.save(members);
    }

    public void load() throws PersistenceException{
        if(persistenceStrategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenz-Strategie festgelegt!");
        }
        members = this.persistenceStrategy.load();
    }
}
