package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

public class ProcessIdentifier {
	UserList userlist = UserList.getUserList();
	public ProcessIdentifier() {}
	
	public Boolean alterIdentifier(int UID) {
    	//鑾峰緱鏁版嵁搴撶殑杩炴帴瀵硅薄
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;

        //鐢熸垚SQL浠ｇ爜l
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE user_list SET identifier = 1 WHERE UID = ? ");
        
        try{
        	preparedStatement = connection.prepareStatement(sqlStatement.toString());
        	preparedStatement.setInt(1,UID);
        	userlist.updateList();
            if(preparedStatement.executeUpdate() > 0)
            	return true;
            return false;
        }
		catch (SQLException ex) {
			// TODO: handle exception
			Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}finally {
			DBManager.closeAll(connection, preparedStatement);
		}
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
