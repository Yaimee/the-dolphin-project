package accounting;

import Chairman.Chairman;
import Chairman.DataHandler;
import Chairman.Member;
import Chairman.TypeOfSwimmer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
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
    DataHandler dh = DataHandler.getInstance();

    public void createSubscription(Member member) {
        initiateNumberOfSubscriptions();
        getTypeOfSubscription(member, true);
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

    private void initiateNumberOfSubscriptions() {
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
        return directory.list()==null?0:directory.list().length;
    }

    public void membershipSweep() {
        int length = lengthOfNonPayingMembersDirectory(); //referring to the length of the nonPayingMembers directory
        for (int i = 1; i <= length; i++) {
            File file = new File("members/nonPayingMembers/nonPayingMember" + "#" + i + ".json");
            if (file.exists()) {
                //inspired by https://stackoverflow.com/questions/15042855/delete-files-older-than-x-days
                int maximumDifference = 28;
                long difference = new Date().getTime() - file.lastModified();
                if (difference > (long) maximumDifference * 24 * 60 * 60 * 1000) {
                    file.delete();
                }
            }
        }
    }

    public void addMemberToNonPayingList(int membershipId) {
        int nonPayingMemberId = generateId();
        dh.setFilePath("members/nonPayingMembers/nonPayingMember" + "#" + nonPayingMemberId + ".json");
        dh.addMemberToNonPayingList(dh.findMemberByID(membershipId));
        dh.writeMembersToSub();
        dh.setFilePath("members/payingMembers.json");
        dh.deleteMember(membershipId);
    }

    public int generateId() {
        int numberOfFiles = lengthOfNonPayingMembersDirectory();
        for (int i = 1; i <= numberOfFiles; i++) {
            File file = new File("members/nonPayingMembers/nonPayingMember" + "#" + i + ".json");
            if (!file.exists()) {
                return i;
            }
        }
        return numberOfFiles + 1;
    }

    //added code for clarity - should be deleted upon completion
    public void changeMembershipToPassive(int membershipId) {
        initiateNumberOfSubscriptions();

        Member member = DataHandler.getInstance().findMemberByID(membershipId);
        int age = member.getAge();
        TypeOfSwimmer swimmer = member.getSwimmer();
        getTypeOfSubscription(member, false);
        numberOfSubscriptions.set(5, (numberOfSubscriptions.get(5) + 1));
        writeMembersSub();
    }


    public int getProjectedYearlyRevenue() {
        initiateNumberOfSubscriptions();
        int revenue = 0;
        revenue += 1000 * ((numberOfSubscriptions.get(0)) + (numberOfSubscriptions.get(2)));
        revenue += 1600 * ((numberOfSubscriptions.get(1)) + (numberOfSubscriptions.get(3)));
        revenue += 1200 * numberOfSubscriptions.get(4);
        revenue += 500 * numberOfSubscriptions.get(5);
        return revenue;
    }

    public void getTypeOfSubscription(Member member, boolean isPositiveOrNegative) {
        int age = member.getAge();
        TypeOfSwimmer swimmer = member.getSwimmer();

        int positiveOrNegative = 0;

        if (isPositiveOrNegative) {
            positiveOrNegative = 1;
        } else {
            positiveOrNegative = -1;
        }

        if (age < 18 || age >= 60) {
            if (age >= 60) {
                numberOfSubscriptions.set(4, (numberOfSubscriptions.get(4) + positiveOrNegative));
            } else if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfSubscriptions.set(0, (numberOfSubscriptions.get(0) + positiveOrNegative));
            } else {
                numberOfSubscriptions.set(2, (numberOfSubscriptions.get(2) + positiveOrNegative));
            }
        } else {
            if (swimmer.equals(TypeOfSwimmer.CASUAL)) {
                numberOfSubscriptions.set(1, (numberOfSubscriptions.get(1) + positiveOrNegative));
            } else {
                numberOfSubscriptions.set(3, (numberOfSubscriptions.get(3) + positiveOrNegative));
            }
        }
    }
}
