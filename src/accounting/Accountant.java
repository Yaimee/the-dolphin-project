package accounting;

import Chairman.Member;

import java.util.Scanner;

public class Accountant {
    private final String username = "";
    private final String password = "";
    private final Subscription sub = new Subscription();

    static Scanner scan = new Scanner(System.in);

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void InitiateMembershipSweep() {
        sub.membershipSweep();
    }

    public void InitiateAddMemberToList() {
        System.out.print("Membership ID: ");
        int membershipId = scan.nextInt();

        sub.addMemberToList(membershipId);
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
