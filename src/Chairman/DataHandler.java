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

    public void initMemberJson(){
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("members/members.json"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Member[] fromJson = gson.fromJson(reader, Member[].class);
        memberList.addAll(Arrays.asList(fromJson));
        System.out.println(memberList.get(1));
    }

}
