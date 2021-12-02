package Trainer;

import java.util.ArrayList;

public class Analysis {
    ArrayList<Performance> competitionTopFive;
    ArrayList<Performance> dailyTopFive;

    public ArrayList<Performance> getCompetitionTopFive() {
        return competitionTopFive;
    }

    public void setCompetitionTopFive(ArrayList<Performance> competitionTopFive) {
        this.competitionTopFive = competitionTopFive;
    }

    public ArrayList<Performance> getDailyTopFive() {
        return dailyTopFive;
    }

    public void setDailyTopFive(ArrayList<Performance> dailyTopFive) {
        this.dailyTopFive = dailyTopFive;
    }
}
