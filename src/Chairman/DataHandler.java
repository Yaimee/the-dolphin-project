package Chairman;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DataHandler {
    private final String filePath;
    private final ArrayList<Member> memberList = new ArrayList<Member>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public DataHandler(String filePath) {
        this.filePath = filePath;
    }

    public void writeMembers() {

        String toJson = gson.toJson(memberList);
        try {
            FileWriter file = new FileWriter(this.filePath);
            file.write(toJson);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addMemberToList(Member memberToAdd){
        memberList.add(memberToAdd);
    }

    public void deleteMember(int id){
        for (Member member : memberList) {
            if(member.getID() == id){
                memberList.remove(member);
                writeMembers();
                break;
            }
        }
    }

    public void printMemberList(){
        System.out.println(memberList);
    }

    public void initMemberJson(){
        try {
            Reader reader = null;
            try {
                reader = Files.newBufferedReader(Paths.get("members/members.json"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            Member[] fromJson = gson.fromJson(reader, Member[].class);
            memberList.addAll(Arrays.asList(fromJson));

        } catch (NullPointerException e) {
            System.out.println("No existing users found");
        }

    }

    public ArrayList<Member> findMembersByName(String name){
        ArrayList<Member> foundMembers = new ArrayList<>();

        for (Member member : memberList)
            if(member.getName().matches(name))
                foundMembers.add(member);

        return foundMembers;
    }

    public Member findMemberByID(int id){

        for(Member member : memberList)
            if(member.getID() == id)
                return member;
        return null;
    }

}
