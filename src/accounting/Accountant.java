//coded by Jacob
//this class is mainly a gateway to the functionality of the Subscription class
package accounting;

import Chairman.Member;

import java.util.Scanner;

public class Accountant {
    private final String username = ""; //both username and password are hardcoded into the program
    private final String password = ""; //currently, there is non however, as we wouldn't want to discourage our dear teacher
    //from trying it out
    private final Subscription sub = new Subscription(); //an instance of the Subscription class

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

    public void InitiateAddMemberToNonPayingList() {
        System.out.print("Membership ID: ");
        int membershipId = scan.nextInt();
        sub.addMemberToNonPayingList(membershipId);
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
