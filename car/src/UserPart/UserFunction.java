

import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class UserFunction{
	private static ProcessUser qu;
	private static ProcessIdentifier pi;
	
	public UserFunction() {}
	
	public static JSONObject queryUser(int arg) {
		return qu.queryUser(arg);
	}
	public static JSONObject queryUser(String arg) {
		return qu.queryUser(arg);
	}

	public static JSONObject insertUser(String nickName,String image) {
		JSONObject jsonObject = new JSONObject();
		JSONObject user = new JSONObject();
		if(qu.insertUser(nickName, image)){
        	jsonObject.put("Result", "success");
        	user = qu.queryUser(nickName);
        	jsonObject.put("UID", user.getInt("UID"));
        }else{
        	jsonObject.put("Result", "falied");
        }
		return jsonObject;
	}
    
    public static Boolean alterIdentifier(int UID) {
    	if(qu.queryUser(UID).isEmpty())
        	return false;
    	if(pi.queryIdentifier(UID)) 
    		return true;
    	return pi.alterIdentifier(UID);
	}
    
    public static Boolean queryIdentifier(int UID){
    	if(qu.queryUser(UID).isEmpty())
        	return false;
    	return pi.queryIdentifier(UID);
    }
}