package Chairman;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Trainer.*;

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
    private ArrayList<Competition> competitions = new ArrayList<>();
    private ArrayList<Member> nonPayingMemberList = new ArrayList<>();

    // List of non paying members
    //private final ArrayList<Member> nonPayingMemberList = new ArrayList<Member>();
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
    // Metode til at skrive alt hvad der er på ArrayListen ind på .json filen
    public void writeCompetitions() {
        String toJson = gson.toJson(competitions);
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
    }

    // Metode til at tilføje Competitions til ArrayListen
    public void addToCompetitionsList(Competition competitionToAdd) {
        competitions.add(competitionToAdd);
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
    /*

    public void deleteMember(int id){
        for (Member member : memberList) {
            if(member.getID() == id){
                memberList.remove(member);
                writeMembers();
                break;
            }
        }
    }
    * */

    public void printMemberList(){
        System.out.println(memberList);
    }

    // Metode til at reade alt hvad der er på .json ned på ArrayListen(Den her køres som det første)
    public void initCompetitionsJson() {
        try {
            Reader reader = null;
            try {
                reader = Files.newBufferedReader(Paths.get("Competitions/Competitions.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Competition[] fromJson = gson.fromJson(reader, Competition[].class);
            competitions.addAll(Arrays.asList(fromJson));

        } catch (NullPointerException e) {
            System.out.println("No competitions found");
        }
    }
    public void initMemberJson(){
        if (memberList != null && memberList.size() > 0)
            return;
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


