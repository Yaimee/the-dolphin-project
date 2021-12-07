package ui;

import Chairman.*;
import Trainer.Trainer;
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

        boolean wrongLogin = true;

        if(oneTwoOrThree == 1) {
            while(wrongLogin) {
                System.out.print("Username: ");
                String username = scan.nextLine();
                System.out.print("Password: ");
                String password = scan.nextLine();

                if(username.equals("chairman") && password.equals("password1")) {
                    wrongLogin = false;
                    chairmanMenu();
                } else {
                    System.out.println("Invalid input\n");
                }
            }
        } else if(oneTwoOrThree == 2) {
            while(wrongLogin) {
                System.out.print("Username: ");
                String username = scan.nextLine();
                System.out.print("Password: ");
                String password = scan.nextLine();

                if(username.equals("accountant") && password.equals("password2")) {
                    wrongLogin = false;
                    accountantMenu();
                } else {
                    System.out.println("Invalid input\n");
                }
            }
        } else if(oneTwoOrThree == 3) {
            while(wrongLogin) {
                System.out.print("Username: ");
                String username = scan.nextLine();
                System.out.print("Password: ");
                String password = scan.nextLine();

                if(username.equals("trainer") && password.equals("password3")) {
                    wrongLogin = false;
                    trainerMenu();
                } else {
                    System.out.println("Invalid input\n");
                }
            }
        }
    }

    public void chairmanMenu() {
        System.out.println("\nThis is the Chairman's menu");
        displayChairmanMenu();
        int choiceOfOption = chooseFromOptions(2);

        switch (choiceOfOption) {
            case 1 -> System.out.println("Code for creating new member");
            case 2 -> run();
        }
    }
    public void accountantMenu() {
        System.out.println("\nThis is the Accountant's menu");
        displayAccountantMenu();
        int choiceOfOption = chooseFromOptions(5);

        switch (choiceOfOption) {
            case 1 -> System.out.println("Code for adding member to non-paying list");
            case 2 -> System.out.println("Code for adding member to paying list");
            case 3 -> ac.InitiateChangeMembershipToPassive(); //System.out.println("Code for changing subscription of member to passive");
            case 4 -> ac.initiateGetProjectedYearlyRevenue();
            case 5 -> run();
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
        System.out.println("Choose from the following Two options");
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
        System.out.println("Choose from the following four options");
        System.out.println("Press 1: ?");
        System.out.println("Press 2: ?");
        System.out.println("Press 3: ?");
        System.out.println("Press 4: Log out");
    }

    public void run() {
        ch.getDataHandler().initMemberJson(); // Init existing members
        ac.InitiateMembershipSweep();

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

        /*ch.getDataHandler().initMemberJson(); // Init existing members

        Member memberToCreate = new Member(12, "john", "male", "jw@loma.dk", TypeOfSwimmer.COMPETITIVE, Team.JUNIOR);
        Member memberToCreate1 = new Member(12, "weeb", "male", "lowlw@loma.dk", TypeOfSwimmer.CASUAL, Team.JUNIOR);


        ch.createNewMember(memberToCreate);
        ch.createNewMember(memberToCreate1);


        ch.getDataHandler().printMemberList();

        ch.getDataHandler().deleteMember(1);

        ch.getDataHandler().printMemberList();


        System.out.println(ch.getDataHandler().findMembersByName("john"));*/
    }
}

public class Menu {
    public static void main(String[] args) {
        new Application().run();
    }
}
