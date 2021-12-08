package accounting;

import Chairman.Member;

import java.util.Scanner;

public class Accountant {
    //private String username = "accountant";
    //private String password = "password";
    private final Subscription sub = new Subscription();

    static Scanner scan = new Scanner(System.in);


    public void InitiateMembershipSweep() {
        sub.membershipSweep();
    }

    public void InitiateAddMemberToList(Member member) {
        sub.addMemberToList(member);
    }

    public void InitiateChangeMembershipToPassive() {
        System.out.print("Membership ID: ");
        int membershipId = scan.nextInt();

        sub.changeMembershipToPassive(membershipId);
    }

    public void initiateCreateSubscription(Member member) {
        sub.createSubscription(member);
    }

    public void initiateGetProjectedYearlyRevenue() {
        System.out.println(sub.getProjectedYearlyRevenue());
    }
}
