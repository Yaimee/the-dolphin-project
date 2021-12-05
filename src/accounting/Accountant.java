package accounting;

import Chairman.DataHandler;
import Chairman.Member;

public class Accountant {
    private String username;
    private String password;
    private final Subscription sub = new Subscription();


    public void InitiateAddMemberToList(Member member) {
        sub.addMemberToList(member);
    }

    public void InitiateChangeMembershipToPassive(Member member) {
        sub.changeMembershipToPassive(member);
    }

    public void initiateCreateSubscription(Member member) {
        sub.createSubscription(member);
    }

    public void initiateGetProjectedYearlyRevenue() {
        System.out.println(sub.getProjectedYearlyRevenue());
    }
}
