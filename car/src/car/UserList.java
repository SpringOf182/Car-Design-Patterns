package car;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserList {
	private static UserList instance;
	private JSONArray list;
	private UserList() {
		updateList();
	}
	
	public static UserList getUserList() {
		if(instance==null) {
			instance = new UserList();
		}
		return instance;
	}
	
	public void updateList() {
        list = UserDAO.getUserList();
    }
	
	public JSONObject getUserInfo(int UID) {
		for(int i=0;i<list.size();i++){
			 // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			JSONObject user = list.getJSONObject(i); 
			// 得到 每个对象中的属性值
			if(user.getInt("UID")==UID) {
				return user;
			} 
		}
		return null;
	}
	
	public JSONObject getUserInfo(String nickName) {
		for(int i=0;i<list.size();i++){
			 // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			JSONObject user = list.getJSONObject(i); 
			// 得到 每个对象中的属性值
			if(user.getString("nickName")==nickName) {
				return user;
			} 
		}
		return null;
	}
}
