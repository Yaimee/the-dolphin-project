package ui;

import Chairman.*;
import Trainer.*;
import accounting.Accountant;

import java.util.Scanner;

class  Application{

    static Scanner scan = new Scanner(System.in);
    Chairman ch = new Chairman();
    Accountant ac = new Accountant();
    Trainer tr = new Trainer();
    boolean run = true;

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
                        username = tr.getUsername();
                        password = tr.getPassword();
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
            Member member = new Member(scan.nextInt(), scan.nextLine(), scan.nextLine(), scan.nextLine(), TypeOfSwimmer.CASUAL, Team.JUNIOR);
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

        switch (choiceOfOption) {
            case 1 -> System.out.println("Code for adding member to non-paying list");
            case 2 -> System.out.println("Code for adding member to paying list");
            case 3 -> ac.InitiateChangeMembershipToPassive(); //System.out.println("Code for changing subscription of member to passive");
            case 4 -> ac.initiateGetProjectedYearlyRevenue();
            case 5 -> run();
        }
    }


    public void trainerMenu() {
        do {
            System.out.println("\nThis is the Trainer's menu");
            displayTrainerMenu();
            int choiceOfOption = chooseFromOptions(5);

            switch (choiceOfOption) {
                case 1 -> tr.createNewCompetition();
                case 2 -> competitionListMenu();
                case 3 -> tr.logDailyPerformance();
                case 4 -> displayDailyTopFiveMenu();
                case 5 -> {
                    run = false;
                    run();
                }
            }
        } while (run);
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
        System.out.println("Press 1: Create new competition");
        System.out.println("Press 2: Competition list");
        System.out.println("Press 3: Log daily performance");
        System.out.println("Press 4: Display daily top five");
        System.out.println("Press 5: Log out");
    }

    public void competitionListMenu() {
        System.out.println("Select a competition to preview or edit");
        if (tr.getCompetitionList()) {
            int selectOption = chooseFromOptions(11);
            competitionSelectedMenu(selectOption);
        }
    }

    public void competitionSelectedMenu(int option) {
        boolean run2 = true;
        do {
            try {
                System.out.println(tr.getCompetitions().get(option - 1).getName() + " selected");
                System.out.println("1. print competition details");
                System.out.println("2. log performance");
                System.out.println("3. print top five");
                System.out.println("4. add competitors");
                System.out.println("5. remove competitor");
                System.out.println("6. edit time of event");
                System.out.println("7. edit place of event");
                System.out.println("8. edit discipline");
                System.out.println("9. edit team");
                System.out.println("10. delete competition");
                System.out.println("11. return to menu");
                int selectOption = scan.nextInt();
                scan.nextLine();
                switch(selectOption) {
                    case 1:
                        tr.getCompetitions().get(option - 1).printCompetitionDetails();
                        break;
                    case 2:
                        if (tr.getCompetitions().get(option - 1).getMemberList().isEmpty()) {
                            System.out.println("No competitors registered in the competition");
                        } else {
                            tr.getCompetitions().get(option - 1).logPerformance();
                        }
                        break;
                    case 3:
                        tr.getCompetitions().get(option - 1).printTopFiveList();
                        break;
                    case 4:
                        Discipline discipline = tr.getCompetitions().get(option - 1).getDiscipline();
                        Team team = tr.getCompetitions().get(option - 1).getTeam();
                        tr.getCompetitions().get(option - 1).addCompetitors(tr.getAnalyse().getDailyTopFive(discipline, team));
                        break;
                    case 5:
                        tr.getCompetitions().get(option - 1).removeCompetitor();
                        break;
                    case 6:
                        int hour = scan.nextInt();
                        scan.nextLine();
                        int minute = scan.nextInt();
                        scan.nextLine();
                        tr.getCompetitions().get(option - 1).setTime(hour, minute);
                        break;
                    case 7:
                        String place = scan.nextLine();
                        tr.getCompetitions().get(option - 1).setPlace(place);
                        break;
                    case 8:
                        do {
                            run = false;
                            System.out.println("Current discipline is" + tr.getCompetitions().get(option - 1).getDiscipline());
                            int disciplineSelect = scan.nextInt();
                            scan.nextLine();
                            switch (disciplineSelect) {
                                case 1:
                                    tr.getCompetitions().get(option - 1).setDiscipline(Discipline.CRAWL);
                                    break;
                                case 2:
                                    tr.getCompetitions().get(option - 1).setDiscipline(Discipline.BUTTERFLY);
                                    break;
                                case 3:
                                    tr.getCompetitions().get(option - 1).setDiscipline(Discipline.BREASTSTROKE);
                                    break;
                                case 4:
                                    tr.getCompetitions().get(option - 1).setDiscipline(Discipline.BACKSTROKE);
                                    break;
                                default:
                                    System.out.println("Illegal value. Try again");
                                    run = true;
                            }
                        } while (run);
                        break;
                    case 9:
                        do {
                            run = false;
                            System.out.println("Current team is " + tr.getCompetitions().get(option - 1).getTeam());
                            System.out.println("Select team");
                            System.out.println("1. junior");
                            System.out.println("2. senior");
                            try {
                                int teamSelect = scan.nextInt();
                                scan.nextLine();
                                if (teamSelect == 1) {
                                    tr.getCompetitions().get(option - 1).setTeam(Team.JUNIOR);
                                } else if (teamSelect == 2) {
                                    tr.getCompetitions().get(option - 1).setTeam(Team.SENIOR);
                                } else {
                                    System.out.println("Illegal value. Try again");
                                    run = true;
                                }
                            } catch (Exception e) {
                                System.out.println("Illegal value. Try again");
                                run = true;
                            }
                        } while(run);
                        break;
                    case 10:
                        tr.getCompetitions().remove(option - 1);
                        run2 = false;
                    case 11:
                        run2 = false;
                }
            } catch (Exception e) {
                System.out.println("Illegal value! Try again.");
            }
        } while (run2);
    }


    public void displayDailyTopFiveMenu() {

        Discipline discipline = null;

        System.out.println("Display daily top five in the following disciplines");
        System.out.println("Press 1: Crawl");
        System.out.println("Press 2: Butterfly");
        System.out.println("Press 3: Breaststroke");
        System.out.println("Press 4: Backstroke");
        int choiceOfPrivilege = chooseFromOptions(4);
        switch(choiceOfPrivilege) {
            case 1 -> discipline = Discipline.CRAWL;
            case 2 -> discipline = Discipline.BUTTERFLY;
            case 3 -> discipline = Discipline.BREASTSTROKE;
            case 4 -> discipline = Discipline.BACKSTROKE;
        }
        tr.displayDailyTopFive(discipline, juniorOrSenior());
    }

    private Team juniorOrSenior() {

        Team team = null;

        System.out.println("Press 1: Junior");
        System.out.println("Press 2: Senior");
        int selectOption = chooseFromOptions(2);
        if (selectOption == 1) {
            team = Team.JUNIOR;
        } else if (selectOption == 2) {
            team = Team.SENIOR;
        }

        return team;
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

public class  Menu {
    public static void main(String[] args) {
        new Application().run();
    }
}