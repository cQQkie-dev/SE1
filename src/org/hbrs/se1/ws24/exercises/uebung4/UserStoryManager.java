package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung2.MemberView;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.util.Scanner;

public class UserStoryManager {
    private static Container container = Container.getInstance();

    public static void main(String[] args) throws ContainerException, PersistenceException {
        PersistenceStrategy<Member> strategyStream = new PersistenceStrategyStream<>();
        container.setPersistenceStrategy(strategyStream);
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Enter command (type 'help' for list of commands):");
        while (!(command = scanner.nextLine()).equals("exit")) {
            switch (command.split(" ")[0]) {
                case "enter":
                    enterUserStory(scanner);
                    break;
                case "store":
                    container.store();
                case "load":
                    container.load();
                case "dump":
                    MemberView memberView = new MemberView();
                    memberView.dump(container.getCurrentList());
                    break;
                case "help":
                    printHelp();
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for list of commands.");
            }
        }
        scanner.close();
    }
    private static void enterUserStory(Scanner scanner) throws ContainerException {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Acceptance Criteria: ");
        String criteria = scanner.nextLine();
        System.out.print("Enter relative added value: ");
        int value = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter relative penalty: ");
        int penalty = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter relative risk: ");
        int risk = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter relative effort: ");
        int effort = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Project: ");
        String project = scanner.nextLine();

        UserStory story = new UserStory(id, title, criteria, value, penalty, risk,
                effort, project);
        container.addMember(story);
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("enter - Enter a new user story");
        System.out.println("dump - Display all user stories");
        System.out.println("store - save user stories to disk");
        System.out.println("load - load user stories from disk");
        System.out.println("help - print this help menu");
        System.out.println("exit - Exit the application");
    }

}
