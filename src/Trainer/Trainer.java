/*package Trainer;
import java.util.ArrayList;
import java.util.Scanner;

public class Trainer {
    Analysis analyse = new Analysis();
    Scanner sc = new Scanner(System.in);
    ArrayList<Competition> competitions = new ArrayList<>();
    String username;
    String password;
    /*
    Trainer (String username, String password) {
            this.username = username;
            this.password = password;
    }*/

    public void displayCasualTopFive(Discipline discipline, Team team) {
        System.out.println("Name:\t\t\tTime:\t\t\tDiscipline: " + discipline + "\tTeam: " + team);
        for (int i = 0; i < analyse.getDailyTopFive(discipline, team).size(); i++) {
            System.out.println((i + 1) + ". " + analyse.getDailyTopFive(discipline, team).get(i).getName() + "\t\t\t" + analyse.getDailyTopFive(discipline, team).get(i).timeFormatted());
        }

    }

    public void createNewCompetition() {
        Discipline discipline;
        int selectOption;
        Team team;
        String name;
        int hours;
        int minutes;
        String time;
        System.out.print("Write the competition name: ");
        name = sc.nextLine();
        System.out.println("What kind of swimming discipline is the event?");
        System.out.println("1. crawl");
        System.out.println("2. butterfly");
        System.out.println("3. breaststroke");
        System.out.println("4. backstroke");
        selectOption = sc.nextInt();
        sc.nextLine();
        switch(selectOption) {
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
        System.out.println("Is it a junior or senior event? Insert \"1\" for junior or \"2\" for senior: ");
        selectOption = sc.nextInt();
        sc.nextLine();
        if (selectOption == 1) {
            team = Team.JUNIOR;
        } else {
            team = Team.SENIOR;
        }
        competitions.add(new Competition(name,discipline,time,place,team,addCompetitors(team, discipline)));
    }

    public ArrayList<Performance> addCompetitors(Team team, Discipline discipline) {
        System.out.println("Would you like to add competitors from the top five list now? (y/n)");
        String selectOption = sc.nextLine();
        selectOption.toUpperCase();
        if(selectOption.equals("N")) {
            return null;
        } else {
            System.out.println("Competitors: ");
            System.out.println("Name:\t\t\tAge:");
            for(int i = 0; i < analyse.getDailyTopFive(discipline,team).size(); i++) {
                System.out.println(analyse.getDailyTopFive(discipline,team));
            }
            return analyse.getDailyTopFive(discipline,team);
        }
    }

    public void addCompetitiorsLater() {
        System.out.println("To which competition do you wish to add competitors?");
        for ( int i = 0; i < competitions.size(); i++) {
            System.out.println((i + 1)+ ". " + competitions.get(i).getName());
        }
        int selectOption = sc.nextInt();
        sc.nextLine();
        Discipline discipline = competitions.get(selectOption - 1).getDiscipline();
        Team team = competitions.get(selectOption).getTeam();
        competitions.get(selectOption - 1).addCompetitors(analyse.getDailyTopFive(discipline,team));
        System.out.println("added");
    }

    public void getCompetitionList(){
        if (competitions.size() == 0) {
            System.out.println("No competitions registered");
        } else {
            System.out.println("Competitions registered:");
            for (int i = 0; i < competitions.size(); i++) {
                System.out.println((i + 1) + ". " + competitions.get(i).getName());
            }
        }

    }

    public void logDailyPerformance() {
        Discipline discipline;
        System.out.println("Members available for logging: ");
        for(int i = 0; i < Subscription.payingMembers.size(); i++) {
            System.out.println((i + 1) + ". " + Subscription.payingMembers.get(i).getName());
        }
        System.out.println("To select a member, insert the corresponding value");
        int memberSelect = sc.nextInt();
        sc.nextLine();
        System.out.println(Subscription.payingMembers.get(memberSelect - 1).getName() + " selected.");
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
        analyse.setDailyTopFive(new Performance(Subscription.payingMembers.get(memberSelect - 1).getName(), Subscription.payingMembers.get(memberSelect - 1).getAge(), minutes, seconds, milliseconds), discipline,Subscription.payingMembers.get(memberSelect - 1).getTeam());
    }
}
