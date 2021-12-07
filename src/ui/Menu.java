package ui;

import Chairman.*;
import java.util.Scanner;

class Application{
    Chairman ch = new Chairman();


    public void run(){
        ch.getDataHandler().initMemberJson(); // Init existing members

        Member memberToCreate = new Member(12, "john", "male", "jw@loma.dk", TypeOfSwimmer.COMPETITIVE, Team.JUNIOR);
        Member memberToCreate1 = new Member(12, "weeb", "male", "lowlw@loma.dk", TypeOfSwimmer.CASUAL, Team.JUNIOR);


        ch.createNewMember(memberToCreate);
        ch.createNewMember(memberToCreate1);


        ch.getDataHandler().printMemberList();

        ch.getDataHandler().deleteMember(1);

        ch.getDataHandler().printMemberList();


        System.out.println(ch.getDataHandler().findMembersByName("john"));

    }
}


public class Menu {
    public static void main(String[] args) {
        new Application().run();
    }
}
