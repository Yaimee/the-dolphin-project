package accounting;

import Chairman.DataHandler;
import Chairman.Member;
import Chairman.TypeOfSwimmer;

import java.util.ArrayList;

public class Subscription {

    static int numberOfJuniorCasual = 0;
    static int numberOfSeniorCasual = 0;
    static int numberOfJuniorCompetitor = 0;
    static int numberOfSeniorCompetitor = 0;
    static int numberOfSeniorRetired = 0;
    static int numberOfPassiveMemberships = 0;

    static ArrayList<Member> payingMembers = new ArrayList<>();
    static ArrayList<Member> nonPayingMembers = new ArrayList<>();

    private final DataHandler dh = new DataHandler("members/nonPayingMembers.json");


    public void createSubscription(Member member) {
        payingMembers.add(member);
        getTypeOfSubscription(member);
    }

    public void addMemberToList(Member member) {
        dh.addMemberToList(member);
        dh.writeMembers();
        //dh.deleteMember(member.getID());
    }

    public int changeMembershipToPassive(Member member) {
        System.out.println("Code that changes membership to passive");
        numberOfPassiveMemberships ++;
        return 500;
    }

    public int getProjectedYearlyRevenue() {
        int revenue = 0;
        revenue += 1000 * (numberOfJuniorCasual + numberOfJuniorCompetitor);
        revenue += 1600 * (numberOfSeniorCasual + numberOfSeniorCompetitor);
        revenue += 1200 * numberOfSeniorRetired;
        revenue += 500 * numberOfPassiveMemberships;
        return revenue;
    }

    public int getTypeOfSubscription(Member member) {
        int age = member.getAge();
        TypeOfSwimmer swimmer = member.getSwimmer();

        if (age < 18 || age >= 60) {
            if (age >= 60) {
                numberOfSeniorRetired ++;
                return 1200;
            } else if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfJuniorCasual ++;
            } else {
                numberOfJuniorCompetitor ++;
            }
            return 1000;
        } else {
            if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfSeniorCasual ++;
            } else {
                numberOfSeniorCompetitor ++;
            }
            return 1600;
        }
    }
}
