package Chairman;
import Trainer.Performance;

import java.util.ArrayList;


public class Analysis {

    private ArrayList<Performance> dailyTopFiveJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveSenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveCrawlJunior = new ArrayList <>();
    private ArrayList<Performance> dailyTopFiveCrawlSenior = new ArrayList <>();
    private ArrayList<Performance> dailyTopFiveButterflyJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveButterflySenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBreaststrokeJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBreaststrokeSenior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBackstrokeJunior = new ArrayList<>();
    private ArrayList<Performance> dailyTopFiveBackstrokeSenior = new ArrayList<>();
    private ArrayList<Performance> competitionTopFive = new ArrayList<>();
    int[] checkList = new int[0];

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
        switch(discipline) {
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
        ArrayList<Performance> performance = new ArrayList<>();
        ArrayList<Performance> topFive2 = new ArrayList<>();

        if (topFive.size() > 5) {
            for (int i = 0; i < 5; i++) {
                performance.add(topFive.get(i));
            }
        } else {
            for (int i = 0; i < topFive.size(); i++) {
                performance.add(topFive.get(i));
            }
        }
        if(performance.size() > 1) {
            for (int i = 0; i < topFive.size(); i++) {

                for (int u = i + 1; u < topFive.size(); u++) {
                    if (performance.get(i).getTime() > performance.get(u).getTime()) {
                        topFive2.add(i, performance.get(u));
                    }
                }
                if (i == performance.size() - 1) {
                    topFive2.add(performance.get(i));
                }
            }
        } else {
            topFive2.add(performance.get(0));
        }
        return topFive2;
    }

    public boolean memberAdded(int ID, ArrayList<Performance> memberList) {
        checkList = new int[checkList.length + 1];
        checkList[checkList.length - 1] = ID;
        boolean trueOrFalse = false;
        for (int i = 0; i < memberList.size(); i++) {
            if (checkList[i] == ID) {
                trueOrFalse = true;
            }
        }
        return trueOrFalse;
    }
}
