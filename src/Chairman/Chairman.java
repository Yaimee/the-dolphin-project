package Chairman;
import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class Chairman {
    private String username;
    private String password;
    private final Scanner sc = new Scanner(System.in);
    private final DataHandler dh = DataHandler.getInstance();

    public void createNewMember(Member member){
        String text = "";
        int id;

        try {
            text = Files.readString(Path.of("members/idCounter.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        id = Integer.parseInt(text);
        member.setID(id);
        try {
            Files.writeString(Path.of("members/idCounter.txt"),"" + (id+1), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dh.setFilePath("members/payingMembers.json");
        dh.addMemberToList(member);
        dh.writeMembers();
        System.out.println("Added member: " + member.getName());
    }

    public Member initMemberCreation(){
        System.out.println("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Gender: ");
        String gender = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();

        return new Member(age,name,gender,email,swimmerChooser(),teamChooser(age));
    }

    private TypeOfSwimmer swimmerChooser(){
        System.out.println("What type of swimmer is it?\n" +
                "1.\tCasual\n" +
                "2.\tCompetitive");
        do {
            String input = sc.nextLine();

            if (input.matches("1"))
                return TypeOfSwimmer.CASUAL;
            else if(input.matches("2"))
                return TypeOfSwimmer.COMPETITIVE;
            else
                System.out.println("Invalid input");

        }while(true);
    }

    private Team teamChooser(int age){
        if(age < 18)
            return Team.JUNIOR;
        else
            return Team.SENIOR;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
