package Trainer;
import Chairman.*;
import accounting.*;
import java.util.ArrayList;

public class Analysis {

    private ArrayList<Performance> dailyTopFiveJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveSenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveCrawlJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveCrawlSenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveButterflyJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveButterflySenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBreaststrokeJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBreaststrokeSenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBackstrokeJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBackstrokeSenior = new ArrayList<>();
    private ArrayList<Performance> competitionTopFive = new ArrayList<>();
    private int[] checkList = new int[0];

    public ArrayList<Performance> getCompetitionTopFive() {
        return competitionTopFive;
    }

    public void setCompetitionTopFive(Performance performance) {
        competitionTopFive.add(performance);
        this.competitionTopFive = calculateTopFive(competitionTopFive);
    }

    public ArrayList<Performance> getDailyTopFive(Discipline discipline, Team team) {
        switch (discipline) {
            case CRAWL:
                if (team == Team.JUNIOR) {
                    return dailyTopFiveCrawlJunior;
                } else {
                    return dailyTopFiveCrawlSenior;
                }
            case BUTTERFLY:
                if (team == Team.JUNIOR) {
                    return dailyTopFiveButterflyJunior;
                } else {
                    return dailyTopFiveButterflySenior;
                }
            case BREASTSTROKE:
                if (team == Team.JUNIOR) {
                    return dailyTopFiveBreaststrokeJunior;
                } else {
                    return dailyTopFiveBreaststrokeSenior;
                }
            case BACKSTROKE:
                if (team == Team.JUNIOR) {
                    return dailyTopFiveBackstrokeJunior;
                } else {
                    return dailyTopFiveBackstrokeSenior;
                }
            default:
                if (team == Team.JUNIOR) {
                    return dailyTopFiveJunior;
                } else {
                    return dailyTopFiveSenior;
                }
        }
    }

    public void setDailyTopFive(Performance performance, Discipline discipline, Team team) {
        switch (discipline) {
            case CRAWL:
                if (team == Team.JUNIOR) {
                    dailyTopFiveCrawlJunior.add(performance);
                    this.dailyTopFiveCrawlJunior = calculateTopFive(dailyTopFiveCrawlJunior);
                } else {
                    dailyTopFiveCrawlSenior.add(performance);
                    this.dailyTopFiveCrawlSenior = calculateTopFive(dailyTopFiveCrawlSenior);
                }
                break;
            case BUTTERFLY:
                if (team == Team.JUNIOR) {
                    dailyTopFiveButterflyJunior.add(performance);
                    this.dailyTopFiveButterflyJunior = calculateTopFive(dailyTopFiveButterflyJunior);
                } else {
                    dailyTopFiveButterflySenior.add(performance);
                    this.dailyTopFiveButterflySenior = calculateTopFive(dailyTopFiveButterflySenior);
                }
                break;
            case BACKSTROKE:
                if (team == Team.JUNIOR) {
                    dailyTopFiveBackstrokeJunior.add(performance);
                    this.dailyTopFiveBackstrokeJunior = calculateTopFive(dailyTopFiveBackstrokeJunior);
                } else {
                    dailyTopFiveBackstrokeSenior.add(performance);
                    this.dailyTopFiveBackstrokeSenior = calculateTopFive(dailyTopFiveBackstrokeSenior);
                }
                break;
            case BREASTSTROKE:
                if (team == Team.JUNIOR) {
                    dailyTopFiveBreaststrokeJunior.add(performance);
                    this.dailyTopFiveBreaststrokeJunior = calculateTopFive(dailyTopFiveBreaststrokeJunior);
                } else {
                    dailyTopFiveBreaststrokeSenior.add(performance);
                    this.dailyTopFiveBreaststrokeJunior = calculateTopFive(dailyTopFiveBreaststrokeSenior);
                }
                break;
            default:
                if (team == Team.JUNIOR) {
                    dailyTopFiveJunior.add(performance);
                    this.dailyTopFiveJunior = calculateTopFive(dailyTopFiveJunior);
                } else {
                    dailyTopFiveSenior.add(performance);
                    this.dailyTopFiveSenior = calculateTopFive(dailyTopFiveSenior);
                }
        }
    }

    private ArrayList<Performance> calculateTopFive(ArrayList<Performance> topFive) {
        Performance[] buffer = new Performance[topFive.size()];
        ArrayList<Performance> newTopFive = new ArrayList<>();

        for (int i = 0; i < topFive.size(); i++) {
            buffer[i] = topFive.get(i);
        }
        for (int i = 0; i < buffer.length; i++) {
            for (int u = i + 1; u < buffer.length; u++) {
                if (buffer[u].getTime() < buffer[i].getTime()) {
                    Performance buffer2 = buffer[i];
                    buffer[i] = buffer[u];
                    buffer[u] = buffer2;

                }
            }
        }
        for (int i = 0; i < buffer.length; i++) {
            newTopFive.add(buffer[i]);
        }
        return newTopFive;
    }

    //plagieret fra https://www.baeldung.com/java-pad-string
    public String padRightSpaces(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(' ');
        }
        sb.append(inputString);

        return String.format("%1$-" + length + "s", inputString);
    }

    public void printDailyTopFive(Discipline discipline, Team team) {
        for (int i = 0; i < getDailyTopFive(discipline, team).size(); i++) {
            System.out.printf((i + 1) + ". " + padRightSpaces(getDailyTopFive(discipline, team).get(i).getName(), 13));
            System.out.print(getDailyTopFive(discipline, team).get(i).timeFormatted() + "\n");
        }
    }

    public int[] getCheckList() {
        return checkList;
    }

    public void memberAdded(int ID) {
        checkList = new int[checkList.length + 1];
        checkList[checkList.length - 1] = ID;
    }
}
