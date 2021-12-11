package accounting;

import Chairman.Chairman;
import Chairman.DataHandler;
import Chairman.Member;
import Chairman.TypeOfSwimmer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
public class Subscription {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Chairman ch = new Chairman();

    static int numberOfJuniorCasual = 0;
    static int numberOfSeniorCasual = 0;
    static int numberOfJuniorCompetitor = 0;
    static int numberOfSeniorCompetitor = 0;
    static int numberOfSeniorRetired = 0;
    static int numberOfPassiveMemberships = 0;

    private ArrayList<Integer> numberOfSubscriptions = new ArrayList<>();

    public void createSubscription(Member member) {
        initiateNumberOfSubscriptions();
        getTypeOfSubscription(member);
        writeMembersSub();
    }

    public void writeMembersSub() {

        String toJson = gson.toJson(numberOfSubscriptions);
        try {
            FileWriter file = new FileWriter("members/numberOfSubscriptionsCounter.json");
            file.write(toJson);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initiateNumberOfSubscriptions(){
        try {
            Reader readerSub = null;
            try {
                readerSub = Files.newBufferedReader(Paths.get("members/numberOfSubscriptionsCounter.json"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            Integer[] mJson = gson.fromJson(readerSub, Integer[].class);
            numberOfSubscriptions.addAll(Arrays.asList(mJson));

        } catch (NullPointerException e) {
            System.out.println("Nothing is right");
        }

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

    public void addMemberToList(int membershipId) {
        int nonPayingMemberId = generateId();
        DataHandler dh = DataHandler.getInstance();
        dh.setFilePath("members/nonPayingMembers/nonPayingMember" + "#" + nonPayingMemberId + ".json");
        dh.addMemberToList(dh.findMemberByID(membershipId));
        dh.writeMembers();
        dh.deleteMember(membershipId);
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

    //added code for clarity - should be deleted upon completion
    public void changeMembershipToPassive(int membershipId) {
        Member member = DataHandler.getInstance().findMemberByID(membershipId);
        int age = member.getAge();
        TypeOfSwimmer swimmer = member.getSwimmer();

        if (age < 18 || age >= 60) {
            if (age >= 60) {
                System.out.println(numberOfPassiveMemberships);
                numberOfSeniorRetired --;
                System.out.println(numberOfPassiveMemberships);
            } else if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                System.out.println(numberOfJuniorCasual);
                numberOfJuniorCasual --;
                System.out.println(numberOfJuniorCasual);
            } else {
                System.out.println(numberOfJuniorCompetitor);
                numberOfJuniorCompetitor --;
                System.out.println(numberOfJuniorCompetitor);
            }
        } else {
            if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                System.out.println(numberOfSeniorCasual);
                numberOfSeniorCasual --;
                System.out.println(numberOfSeniorCasual);
            } else {
                System.out.println(numberOfSeniorCompetitor);
                numberOfSeniorCompetitor --;
                System.out.println(numberOfSeniorCompetitor);
            }
        }
        System.out.println(numberOfPassiveMemberships);
        numberOfPassiveMemberships ++;
        System.out.println(numberOfPassiveMemberships);
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
                numberOfSubscriptions.set(4, (numberOfSubscriptions.get(4) + 1));
                return 1200;
            } else if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfSubscriptions.set(0, (numberOfSubscriptions.get(0) + 1));
            } else {
                numberOfSubscriptions.set(2, (numberOfSubscriptions.get(2) + 1));
            }
            return 1000;
        } else {
            if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfSubscriptions.set(1, (numberOfSubscriptions.get(1) + 1));
            } else {
                numberOfSubscriptions.set(3, (numberOfSubscriptions.get(3) + 1));
            }
            return 1600;
        }
    }
}
