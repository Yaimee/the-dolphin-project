/*
package Trainer;
import Chairman.Team;
import Trainer.Analysis;
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
        analyse.setCompetitionTopFive(new Performance(memberList.get(selectMember - 1).getName(),memberList.get(selectMember - 1).getAge(),minutes,seconds,milliseconds));
    }

    public void printCompetitorList() {
        if (memberList.size() > 0) {
            System.out.println("Name:\t\t\tAge:");
            for (int i = 0; i < memberList.size(); i++) {
                System.out.println((i + 1) + ". " + memberList.get(i).getName() + "\t\t\t" + memberList.get(i).getAge());
            }
        } else {
            System.out.println("No participants has been registered");
        }
    }

    public void printTopFiveList() {
        System.out.println("Name:\t\t\tTime(mm/ss/msmsms):");
        for (int i = 0; i < analyse.getCompetitionTopFive().size(); i++) {
            int listNumber = (i + 1);
            String name = analyse.getCompetitionTopFive().get(i).getName();
            String time = analyse.getCompetitionTopFive().get(i).timeFormatted();
            System.out.printf("%d. %s\t\t\t%s\n",listNumber,name,time);
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

    public void addCompetitors(ArrayList<Performance> memberList) {
        ArrayList<Performance> membersToAdd = new ArrayList<>();
        boolean run = true;
        System.out.println("Select a competitor for the competition");
        do {
            for (int i = 0; i < memberList.size(); i++) {
                System.out.println((i + 1) + ". " + memberList.get(i).getName());

            }
            int selectOption = sc.nextInt();
            sc.nextLine();
            if (selectOption == 0) {
                run = false;
            } else {
                membersToAdd.add(memberList.get(selectOption - 1));
                memberList.remove(selectOption - 1);
            }
        } while (run);
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

    public String getName() {
        return name;
    }
}
