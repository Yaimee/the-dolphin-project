package ui;

import Chairman.*;
//import Trainer.Trainer;
import accounting.Accountant;

import java.util.Scanner;

class Application{

    static Scanner scan = new Scanner(System.in);

    Chairman ch = new Chairman();
    Accountant ac = new Accountant();
    //Trainer tr = new Trainer();

    public boolean doesStringRepresentInt(String string) {

        if(string == null) {
            return false;
        }

        int length = string.length();
        if(length == 0) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public int chooseFromOptions(int numberOfAvailableOptions) {

        while(true) {
            String choiceAsString = scan.nextLine();
            boolean isInt = doesStringRepresentInt(choiceAsString);

            while (isInt) {
                int choiceAsInt = Integer.parseInt(choiceAsString);
                if (choiceAsInt < 1 || choiceAsInt > numberOfAvailableOptions) {
                    System.out.println("Invalid input");
                    choiceAsString = scan.nextLine();
                    isInt = doesStringRepresentInt(choiceAsString);
                } else {
                    for (int i = 1; i <= numberOfAvailableOptions; i++) {
                        if (choiceAsInt == i) {
                            return choiceAsInt;
                        }
                    }
                }
            }
            if (!isInt) {
                System.out.println("Invalid input");
            }
        }
    }

    public void login(int oneTwoOrThree) {

        String username = "";
        String password = "";
        boolean wrongLogin = true;

            while(wrongLogin) {
                System.out.print("Username: ");
                String usernameInput = scan.nextLine();
                System.out.print("Password: ");
                String passwordInput = scan.nextLine();

                switch (oneTwoOrThree) {
                    case 1:
                        username = ch.getUsername();
                        password = ch.getPassword();
                        break;
                    case 2:
                        username = ac.getUsername();
                        password = ac.getPassword();
                        break;
                    case 3:
                        //username = tr.getUsername();
                        //password = tr.getPassword();
                        break;
                }

                if(usernameInput.equals(username) && passwordInput.equals(password)) {
                    wrongLogin = false;
                    switch (oneTwoOrThree) {
                        case 1 -> chairmanMenu();
                        case 2 -> accountantMenu();
                        case 3 -> trainerMenu();
                    }
                } else {
                    System.out.println("Invalid input\n");
                }
            }
    }

    public void chairmanMenu() {
        System.out.println("\nThis is the Chairman's menu");
        displayChairmanMenu();
        int choiceOfOption = chooseFromOptions(2);

        if (choiceOfOption == 1) {
            //Member member = new Member(scan.nextInt(), scan.nextLine(), scan.nextLine(), scan.nextLine(), TypeOfSwimmer.CASUAL, Team.JUNIOR);
            Member member = new Member(15, "Sarah", "female", "john@gmail.com", TypeOfSwimmer.CASUAL, Team.JUNIOR);
            ch.createNewMember(member);
            ac.initiateCreateSubscription(member);
        } else if (choiceOfOption == 2) {
            run();
        }
    }
    public void accountantMenu() {
        System.out.println("\nThis is the Accountant's menu");
        displayAccountantMenu();
        int choiceOfOption = chooseFromOptions(5);

        //System.out.println("Code for changing subscription of member to passive");
        switch (choiceOfOption) {
            case 1:
                ac.InitiateAddMemberToList();

                break;
            case 2:
                System.out.println("Code for adding member to paying list");
                break;
            case 3:
                ac.InitiateChangeMembershipToPassive();
                break;
            case 4:
                ac.initiateGetProjectedYearlyRevenue();
                break;
            case 5:
                run();
                break;
        }
    }
    public void trainerMenu() {
        System.out.println("\nThis is the Trainer's menu");
        displayTrainerMenu();
        int choiceOfOption = chooseFromOptions(4);

        switch (choiceOfOption) {
            case 1 -> System.out.println("Code for ?");
            case 2 -> System.out.println("Code for ?");
            case 3 -> System.out.println("Code for ?");
            case 4 -> run();
        }
    }

    public void displayChairmanMenu() {
        System.out.println("Choose from the following two options");
        System.out.println("Press 1: Create new member");
        System.out.println("Press 2: Log out");
    }
    public void displayAccountantMenu() {
        System.out.println("Choose from the following four options");
        System.out.println("Press 1: Add member to non-paying members list");
        System.out.println("Press 2: Add member to paying members list");
        System.out.println("Press 3: Change subscription of member to passive");
        System.out.println("Press 4: Show projected yearly revenue");
        System.out.println("Press 5: Log out");
    }
    public void displayTrainerMenu() {
        System.out.println("Choose from the following five options");
        System.out.println("Press 1: Create competition");
        System.out.println("Press 2: Competition list");
        System.out.println("Press 3: Log daily performance");
        System.out.println("Press 4: See daily top five");
        System.out.println("Press 5: Log out");
    }

    public void run() {
        //ac.InitiateMembershipSweep();

        System.out.println("\nChoose from the following three privileges");
        System.out.println("Press 1: Chairman");
        System.out.println("Press 2: Accountant");
        System.out.println("Press 3: Trainer");
        System.out.println("Press 4: Quit");

        int choiceOfPrivilege = chooseFromOptions(4);

        switch (choiceOfPrivilege) {
            case 1 -> login(1);
            case 2 -> login(2);
            case 3 -> login(3);
            case 4 -> System.out.println();
        }
    }
}

public class Menu {
    public static void main(String[] args) {
        new Application().run();
    }
}