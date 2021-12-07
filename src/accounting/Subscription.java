package accounting;

import Chairman.DataHandler;
import Chairman.Member;
import Chairman.TypeOfSwimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Subscription {

    static int numberOfJuniorCasual = 0;
    static int numberOfSeniorCasual = 0;
    static int numberOfJuniorCompetitor = 0;
    static int numberOfSeniorCompetitor = 0;
    static int numberOfSeniorRetired = 0;
    static int numberOfPassiveMemberships = 0;


    public void createSubscription(Member member) {
        getTypeOfSubscription(member);
    }

    public int lengthOfNonPayingMembersDirectory() {
        File directory = new File("members/nonPayingMembers/");
        return directory.list().length;
    }

    public void membershipSweep(){
        int length = lengthOfNonPayingMembersDirectory(); //referring to the length of the nonPayingMembers directory
        for (int i = 1; i <= length; i++) {
            File file = new File("members/nonPayingMembers/nonPayingMember" + "#" + i + ".json");
            if(file.exists()) {
                //inspired by https://stackoverflow.com/questions/15042855/delete-files-older-than-x-days
                int maximumDifference = 28;
                long difference = new Date().getTime() - file.lastModified();
                if (difference > (long) maximumDifference * 24 * 60 * 60 * 1000) {
                    file.delete();
                }
            }
        }
    }

    public void addMemberToList(Member member) {
        int nonPayingMemberId = generateId();
        DataHandler dh = new DataHandler("members/nonPayingMembers/nonPayingMember" + "#" + nonPayingMemberId + ".json");
        dh.addMemberToList(member);
        dh.writeMembers();
        //dh.deleteMember(member.getID());
    }

    public int generateId() {
        int numberOfFiles = lengthOfNonPayingMembersDirectory();
        for (int i = 1; i <= numberOfFiles; i++) {
            File file = new File("members/nonPayingMembers/nonPayingMember" + "#" + i + ".json");
            if(!file.exists()) {
                return i;
            }
        }
        return numberOfFiles + 1;
    }

    public void changeMembershipToPassive(Member member) {
        int age = member.getAge();
        TypeOfSwimmer swimmer = member.getSwimmer();

        if (age < 18 || age >= 60) {
            if (age >= 60) {
                numberOfSeniorRetired --;
            } else if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfJuniorCasual --;
            } else {
                numberOfJuniorCompetitor --;
            }
        } else {
            if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfSeniorCasual --;
            } else {
                numberOfSeniorCompetitor --;
            }
        }

        numberOfPassiveMemberships ++;
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
