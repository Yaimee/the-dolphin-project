package ui;

import Chairman.*;
import Chairman.DataHandler;
import java.util.Scanner;

class Application{
    Chairman ch = new Chairman();


    public void run(){
        /*
        Member memberToCreate = new Member(12, "john", "male", "jw@loma.dk", TypeOfSwimmer.COMPETITIVE, Team.JUNIOR);
        Member memberToCreate1 = new Member(12, "weeb", "male", "lowlw@loma.dk", TypeOfSwimmer.CASUAL, Team.JUNIOR);
        Member memberToCreate2 = new Member(12, "duub", "female", "wew@loma.dk", TypeOfSwimmer.COMPETITIVE, Team.JUNIOR);

        ch.createNewMember(memberToCreate);
        ch.createNewMember(memberToCreate1);
        ch.createNewMember(memberToCreate2);
         */
    ch.getDataHandler().initMemberJson();

    }
}


public class Menu {
    public static void main(String[] args) {
        new Application().run();
    }
}
