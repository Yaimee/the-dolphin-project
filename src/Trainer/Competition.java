package Trainer;

import java.util.ArrayList;

public class Competition {
    private Discipline discipline;
    private int time;
    private String place;
    private boolean juniorOrSenior;
    private ArrayList<Performance> memberList;

    public Competition(Discipline discipline, int time, String place, boolean juniorOrSenior) {

    }

    public void setDiscipline(Trainer.Discipline discipline) {
        this.discipline = discipline;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setJuniorOrSenior(int juniorOrSenior) {
        this.juniorOrSenior = juniorOrSenior;
    }

    public void setMemberList(ArrayList<Trainer.Performance> memberList) {
        this.memberList = memberList;
    }
}
