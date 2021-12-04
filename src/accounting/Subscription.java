package accounting;

import java.util.ArrayList;

public class Subscription {



    private final int juniorPrice = 1000;
    private final int seniorPrice = 1600;
    private final int seniorRetiredPrice = 1200;
    private final int passiveMembershipPrice = 500;

    int numberOfJuniorCasual = 0;
    int numberOfSeniorCasual = 0;
    int numberOfJuniorCompetitor = 0;
    int numberOfSeniorCompetitor = 0;
    int numberOfSeniorRetired = 0;

    static ArrayList<TestMember> payingMembers = new ArrayList<TestMember>();
    static ArrayList<TestMember> nonPayingMembers = new ArrayList<TestMember>();

    public Subscription(TestMember member) {
        payingMembers.add(member);
        getTypeOfSubscription(member);
        System.out.println(save);
    }

    public void changeMembershipToPassive


    public int getTypeOfSubscription(TestMember member) {
        int age = member.getAge();
        TestTypeOfSwimmer swimmer = member.getSwimmer();

        if (age < 18 || age >= 60) {
            if (age >= 60) {
                numberOfSeniorRetired ++;
                return seniorRetiredPrice;
            } else if (swimmer.equals(TestTypeOfSwimmer.CASUAL)) {
                numberOfJuniorCasual ++;
            } else {
                numberOfJuniorCompetitor ++;
            }
            return juniorPrice;
        } else {
            if (swimmer.equals(TestTypeOfSwimmer.CASUAL)) {
                numberOfSeniorCasual ++;
            } else {
                numberOfSeniorCompetitor ++;
            }
            return seniorPrice;
        }
    }
}
