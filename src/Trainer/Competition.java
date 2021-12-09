package Trainer;
import Chairman.*;
import accounting.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Competition {
    private String name;
    private Discipline discipline;
    private String time;
    private String place;
    private Team team;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Performance> memberList;
    private Analysis analyse = new Analysis();

    public Competition(String name, Discipline discipline, String time, String place, Team team, ArrayList<Performance> memberList) {
        this.name = name;
        this.discipline = discipline;
        this.time = time;
        this.place = place;
        this.team = team;
        this.memberList = memberList;

    }

    public void printCompetitionDetails() {
        System.out.println("*******************************");
        System.out.println("Competition name: " + name);
        System.out.println("Place: " + place);
        System.out.println("Time: " + time);
        System.out.println("Team: " + team);
        System.out.println("Discipline: " + discipline);
        System.out.println("*******************************");
        System.out.println("Competitors: ");
        printCompetitorList();
        System.out.println("*******************************");
    }

    public void logPerformance() {
        int minutes;
        int seconds;
        int milliseconds;

        System.out.println("Choose a competitor, of which you want to add a performance.");
        printCompetitorList();
        int selectMember = sc.nextInt();
        sc.nextLine();
        System.out.println("Insert minutes: ");
        minutes = sc.nextInt();
        sc.nextLine();
        System.out.println("Insert seconds");
        seconds = sc.nextInt();
        sc.nextLine();
        System.out.println("Insert milliseconds");
        milliseconds = sc.nextInt();
        sc.nextLine();
        analyse.setCompetitionTopFive(new Performance(memberList.get(selectMember - 1).getName(),memberList.get(selectMember - 1).getAge(),minutes,seconds,milliseconds,memberList.get(selectMember - 1).getID()));
    }

    public void printCompetitorList() {
        if (!memberList.isEmpty()) {
            System.out.println("Name:\t\t\t\tAge:");
            for (int i = 0; i < memberList.size(); i++) {
                System.out.printf((i + 1) + ". " + analyse.padRightSpaces(memberList.get(i).getName(), 17));
                System.out.println((memberList.get(i).getAge()));
            }
        } else {
            System.out.println("No participants has been registered");
        }
    }

    public void printTopFiveList() {
        System.out.println("Name:\t\t\t\tTime(mm/ss/msmsms):");
        for (int i = 0; i < analyse.getCompetitionTopFive().size(); i++) {
            System.out.printf((i + 1) + ". " +analyse.padRightSpaces(analyse.getCompetitionTopFive().get(i).getName(), 17));
            System.out.println(analyse.getCompetitionTopFive().get(i).timeFormatted());
        }

    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setTime(int hour, int minute) {
        this.time = String.format("%02d:%02d", hour, minute);
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void addCompetitors(ArrayList<Performance> competitors) {
        boolean run = true;
        if (competitors.isEmpty()) {
            System.out.println("No competitors to add");
        } else {
            ArrayList<Performance> competitorsToAdd = new ArrayList<>();
            boolean added = false;
            for (int i = 0; i < competitors.size(); i++) {
                /*analyse.memberAdded(competitors.get(i).getID());*/
                for(int u = 0; u < analyse.getCheckList().length; u++) {
                    if (analyse.getCheckList()[u] == competitors.get(i).getID()) {
                        added = true;
                    }
                }
                if (!added) {
                    competitorsToAdd.add(competitors.get(i));
                    analyse.memberAdded(competitors.get(i).getID());
                }
            }
            System.out.println("Select a competitor for the competition. Insert \"0\" to brexit");
            do {
                System.out.println("Name:\t\t\t\tTime:");
                for (int i = 0; i < competitorsToAdd.size(); i++) {
                    System.out.printf((i + 1) +". " + analyse.padRightSpaces(competitorsToAdd.get(i).getName(), 17));
                    System.out.println(competitorsToAdd.get(i).timeFormatted());
                }
                int selectOption = sc.nextInt();
                sc.nextLine();
                if (selectOption == 0) {
                    run = false;
                } else {
                    memberList.add(competitorsToAdd.get(selectOption - 1));
                    System.out.println(competitorsToAdd.get(selectOption - 1).getName() + " added");
                    competitorsToAdd.remove(selectOption - 1);
                }
                if(competitorsToAdd.isEmpty()) {
                    System.out.println("All competitors has been added");
                    run = false;
                }
            } while (run);
        }
    }

    public void removeCompetitor() {
        boolean run = true;
        do {
            System.out.println("Choose which competitor you want to remove. Insert \"0\" to exit.");
            printCompetitorList();
            try {
                int selectOption = sc.nextInt();
                sc.nextLine();
                if (selectOption == 0) {
                    run = false;
                } else {
                    memberList.remove(selectOption - 1);
                }
            } catch (Exception e) {
                System.out.println("Illegal value. Try again");
            }
        } while (run);
    }

    public ArrayList<Performance> getMemberList() {
        return memberList;
    }

    public String getName() {
        return name;
    }
}
