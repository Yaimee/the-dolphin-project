package Chairman;
import com.google.gson.*;

public class Chairman {
    private String username;
    private String password;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final DataHandler dh = new DataHandler("members/members.json"); // adding datahandler XD

    public void createNewMember(Member member){

        dh.addMemberToList(member);
        dh.writeMembers();
        System.out.println("Added member: " + member.getName());
    }

    public DataHandler getDataHandler() {
        return dh;
    }
}
