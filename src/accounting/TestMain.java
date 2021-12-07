package accounting;

import Chairman.Chairman;
import Chairman.Member;
import Chairman.Team;
import Chairman.TypeOfSwimmer;

import java.util.ArrayList;

public class TestMain {

    public void run() {

        Chairman ch = new Chairman();
        Accountant ac = new Accountant();

        ch.getDataHandler().initMemberJson(); // Init existing members

        //ac.InitiateMembershipSweep();

        Member nonPayingMember = ch.getDataHandler().findMemberByID(3);

        ac.InitiateAddMemberToList(nonPayingMember);


        /*Member memberToCreate = new Member(100, "john", "male", "jw@loma.dk", TypeOfSwimmer.COMPETITIVE, Team.JUNIOR);
        ac.initiateCreateSubscription(memberToCreate);*/

        /*
        Member memberToCreate1 = new Member(18, "weeb", "male", "lowlw@loma.dk", TypeOfSwimmer.CASUAL, Team.JUNIOR);
        ac.initiateCreateSubscription(memberToCreate1);

        ac.initiateGetProjectedYearlyRevenue();
        */

        /*ch.createNewMember(memberToCreate);
        ch.createNewMember(memberToCreate1);


        ch.getDataHandler().printMemberList();

        ch.getDataHandler().deleteMember(1);

        ch.getDataHandler().printMemberList();


        System.out.println(ch.getDataHandler().findMembersByName("john"));*/

    }

    public static void main(String[] args) {
        new TestMain().run();

    }
}
