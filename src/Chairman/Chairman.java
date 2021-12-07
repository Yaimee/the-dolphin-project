package Chairman;
import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Chairman {
    private String username;
    private String password;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final DataHandler dh = new DataHandler("members/members.json"); // adding datahandler XD

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


        dh.addMemberToList(member);
        dh.writeMembers();
        System.out.println("Added member: " + member.getName());
    }

    public DataHandler getDataHandler() {
        return dh;
    }
}
