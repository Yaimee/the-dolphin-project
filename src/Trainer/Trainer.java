package Trainer;
import Chairman.*;
import accounting.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Trainer {
    private Analysis analyse = new Analysis();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Competition> competitions = new ArrayList<>();
    private int[] checkList = new int[0];
    private DataHandler dh = DataHandler.getInstance();
    private final String username = "gucci";
    private final String password = "prada";
    /*
    Trainer (String username, String password) {
            this.username = username;
            this.password = password;
    }*/

    public void displayDailyTopFive(Discipline discipline, Team team) {
        System.out.println("Name:\t\t\tTime:\t\t\tDiscipline: " + discipline + "\tTeam: " + team);
        analyse.printDailyTopFive(discipline,team);
    }

    public void createNewCompetition() {
        Discipline discipline;
        Team team;
        String name;
        String time;
        boolean run;
        int selectOption;
        int hours;
        int minutes;

        do {
            run = false;
            try {
                System.out.print("Write the competition name: ");
                name = sc.nextLine();
                System.out.println("What kind of swimming discipline is the event?");
                System.out.println("Press 1: crawl");
                System.out.println("Press 2: butterfly");
                System.out.println("Press 3: breaststroke");
                System.out.println("Press 4: backstroke");
                selectOption = sc.nextInt();
                sc.nextLine();
                switch (selectOption) {
                    case 1 -> discipline = Discipline.CRAWL;
                    case 2 -> discipline = Discipline.BUTTERFLY;
                    case 3 -> discipline = Discipline.BREASTSTROKE;
                    case 4 -> discipline = Discipline.BACKSTROKE;
                    default -> {
                        System.out.println("Illegal value. Try again");
                        run = true;
                        continue;
                    }
                }
                System.out.println("When does the competition start?");
                System.out.print("Insert hour: ");
                hours = sc.nextInt();
                sc.nextLine();
                System.out.print("Insert minute: ");
                minutes = sc.nextInt();
                sc.nextLine();
                time = String.format("%02d:%02d", hours, minutes);
                System.out.println("Where is the competition held?");
                String place = sc.nextLine();
                System.out.println("Is it a junior or senior event?");
                System.out.println("Press 1: Junior");
                System.out.println("Press 2: Senior");
                selectOption = sc.nextInt();
                sc.nextLine();
                if (selectOption == 1) {
                    team = Team.JUNIOR;
                } else if (selectOption == 2){
                    team = Team.SENIOR;
                } else {
                    System.out.println("Illegal value. Try again");
                    run = true;
                    continue;
                }
                competitions.add(new Competition(name, discipline, time, place, team, addCompetitors(team, discipline)));
                dh.addToCompetitionsList(new Competition(name, discipline, time, place, team, addCompetitors(team, discipline)));
                dh.writeCompetitions();
            } catch (Exception e) {
                System.out.println("Illegal value. Try again");
                run = true;
            }
        } while(run);
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public Analysis getAnalyse() {
        return analyse;
    }

    public void addCompetitionList(Member member) {

    }

    public ArrayList<Performance> addCompetitors(Team team, Discipline discipline) {
        System.out.println("Would you like to add all competitors from the top five list now? (y/n)");
        System.out.println("To pick them one by one, add them later.");
        String selectOption = sc.nextLine();
        if(selectOption.equalsIgnoreCase("n")) {
            return new ArrayList<>();
        } else {
            System.out.println("Competitors: ");
            System.out.println("Name:\t\t\tAge:");
            analyse.printDailyTopFive(discipline, team);
            return analyse.getDailyTopFive(discipline,team);
        }
    }

    /*public void addCompetitiorsToCompetition() {
        System.out.println("To which competition would you like to add competitors?");
        for ( int i = 0; i < competitions.size(); i++) {
            System.out.println((i + 1)+ ". " + competitions.get(i).getName());
        }
        int selectOption = sc.nextInt();
        sc.nextLine();
        Discipline discipline = competitions.get(selectOption - 1).getDiscipline();
        Team team = competitions.get(selectOption - 1).getTeam();
        competitions.get(selectOption - 1).addCompetitors(analyse.getDailyTopFive(discipline,team));
        System.out.println("added");
    }*/

    public boolean getCompetitionList(){
        boolean trueOrFalse;
        if (competitions.size() == 0) {
            System.out.println("No competitions registered");
            trueOrFalse = false;
        } else {
            trueOrFalse = true;
            System.out.println("Competitions registered:");
            for (int i = 0; i < competitions.size(); i++) {
                System.out.println((i + 1) + ". " + competitions.get(i).getName());
            }
        }
        return trueOrFalse;
    }

    public void logDailyPerformance() {

        int checkListMatch;
        boolean memberAdded = false;

        dh.initMemberJson();
        if (!dh.getMemberList().isEmpty()) {
            Discipline discipline;
            System.out.println("Members available for logging: ");
            for (int i = 0; i < dh.getMemberList().size(); i++) {
                System.out.println((i + 1) + ". " + dh.getMemberList().get(i).getName());
            }
            System.out.println("To select a member, insert the corresponding value");
            int memberSelect = sc.nextInt();
            sc.nextLine();
            System.out.println(dh.getMemberList().get(memberSelect - 1).getName() + " selected.");
            System.out.println("Insert time for logging");
            System.out.println("minutes: ");
            int minutes = sc.nextInt();
            sc.nextLine();
            System.out.println("seconds: ");
            int seconds = sc.nextInt();
            sc.nextLine();
            System.out.println("milliseconds: ");
            int milliseconds = sc.nextInt();
            sc.nextLine();
            System.out.println("Insert discipline");
            System.out.println("1. crawl");
            System.out.println("2. butterfly");
            System.out.println("3. breaststroke");
            System.out.println("4. backstroke");
            int selectOption = sc.nextInt();
            sc.nextLine();
            switch ((selectOption)) {
                case 1:
                    discipline = Discipline.CRAWL;
                    break;
                case 2:
                    discipline = Discipline.BUTTERFLY;
                    break;
                case 3:
                    discipline = Discipline.BREASTSTROKE;
                    break;
                case 4:
                    discipline = Discipline.BACKSTROKE;
                    break;
                default:
                    discipline = null;
            }
            checkListMatch = dh.getMemberList().get(memberSelect - 1).getID();
            memberAddedToDailyPerformance(checkListMatch);
            for (int i = 0; i < analyse.getDailyTopFive(discipline,dh.getMemberList().get(memberSelect - 1).getTeam()).size(); i++) {
                for(int u = 0; u < checkList.length; u++) {
                    if (checkList[u] == analyse.getDailyTopFive(discipline, dh.getMemberList().get(memberSelect - 1).getTeam()).get(i).getID()) {
                        memberAdded = true;
                        analyse.getDailyTopFive(discipline,dh.getMemberList().get(memberSelect - 1).getTeam()).remove(i);
                        analyse.getDailyTopFive(discipline,dh.getMemberList().get(memberSelect - 1).getTeam()).add(i, new Performance(dh.getMemberList().get(memberSelect - 1).getName(), dh.getMemberList().get(memberSelect - 1).getAge(), minutes, seconds, milliseconds, dh.getMemberList().get(memberSelect - 1).getID()));
                    }
                }
            }
            if(!memberAdded) {
                analyse.setDailyTopFive(new Performance(dh.getMemberList().get(memberSelect - 1).getName(), dh.getMemberList().get(memberSelect - 1).getAge(), minutes, seconds, milliseconds, dh.getMemberList().get(memberSelect - 1).getID()), discipline, dh.getMemberList().get(memberSelect - 1).getTeam());
            }
        } else {
            System.out.println("No members for logging");
        }
    }

    public void memberAddedToDailyPerformance(int ID) {
        checkList = new int[checkList.length + 1];
        checkList[checkList.length - 1] = ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
