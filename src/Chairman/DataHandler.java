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

    //Singleton instance
    private static DataHandler single_instance = null;
    // Filepath
    private String filePath = "members/payingMembers.json";
    // List of paying members
    private final ArrayList<Member> memberList = new ArrayList<Member>();
    // List of non-paying members
    private final ArrayList<Member> nonPayingMemberList = new ArrayList<>();
    //  Du bliver nødt til at adde en ny arraylist, også lave metoder tilhørende den arraylist for at kunne tilføje/slette
    // Json writer object
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

    public void writeMembersToSub() {

        String toJson = gson.toJson(nonPayingMemberList);
        try {
            FileWriter file = new FileWriter(this.filePath);
            file.write(toJson);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMemberToNonPayingList(Member member) {
        nonPayingMemberList.add(member);
        //deleteMember(member.getID());
    }

    public void addMemberToList(Member memberToAdd){
        memberList.add(memberToAdd);
    }

    public void deleteMember(int id){
        for (int i = 0; i < memberList.size(); i++) {
            if(memberList.get(i).getID() == id){
                memberList.remove(i);
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
                reader = Files.newBufferedReader(Paths.get("members/payingMembers.json"));

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

    public ArrayList<Integer> getMemberIDsByName(String name){
        ArrayList<Integer> foundIDs = new ArrayList<>();

        for (Member member : memberList)
            if(member.getName().matches(name))
                foundIDs.add(member.getID());

        return foundIDs;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private DataHandler()
    {}

    public static DataHandler getInstance()
    {
        if (single_instance == null)
            single_instance = new DataHandler();

        return single_instance;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}


