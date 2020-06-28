
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

public class ProcessIdentifier {
	UserList userlist = UserList.getUserList();
	public ProcessIdentifier() {}
	
	public Boolean alterIdentifier(int UID) {
       return UserDAO.alterIdentifier(UID);
	}
	
	public Boolean queryIdentifier(int UID){
    	
        JSONObject user = userlist.getUserInfo(UID);
        if( user != null){
        	System.out.println("yes");
        	if(user.getInt("identifier") == 1){
        		System.out.println("yes yes yes yes");
        		System.out.println(user.getInt("identifier"));
        		return true;
        	}
        }
        System.out.println("false");
        return false;
    }
}
