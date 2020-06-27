package car;

import net.sf.json.JSONObject;

public class ProcessUser {
	UserList userlist = UserList.getUserList();
	public ProcessUser() {}
	
	public JSONObject queryUser(int UID) {

        JSONObject user = userlist.getUserInfo(UID);
        return user;
        
    }
	
	public JSONObject queryUser(String nickName) {
		
		JSONObject user = userlist.getUserInfo(nickName);
        return user;
    }
	
	public Boolean insertUser(String nickName,String image){
    	
		if(!queryUser(nickName).isEmpty()){
        	System.out.println("not null");
        	return true;
        }
		return UserDAO.insertUser(nickName, image);
    }
}