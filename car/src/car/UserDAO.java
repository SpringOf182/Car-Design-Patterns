import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserDAO {
static UserList userlist = UserList.getUserList();
	
	public static Boolean insertUser(String nickName,String image){
    	
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;

        
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO user_list(nickName,image) VALUES(?,?)");
        
        
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, nickName);
            preparedStatement.setString(2, image);
            userlist.updateList();
            
            if(preparedStatement.executeUpdate() > 0){
            	System.out.println("update");
            	return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserFunction.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement);
        }
    }
	
	public static JSONArray getUserList() {
		JSONArray list = new JSONArray();
		Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM user_list");

        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	System.out.println("enter OK");
            	JSONObject user = new JSONObject();
            	System.out.println("enter OK info ends");
                user.put("UID",resultSet.getInt("UID"));
                user.put("nickName", resultSet.getString("nickName"));
                user.put("image",resultSet.getString("image"));
                user.put("balance",resultSet.getDouble("balance"));
                user.put("identifier",resultSet.getInt("identifier"));
                System.out.println("enter OK ends");
                list.add(user);
            }
            System.out.println(list.toString());
            if(list.isEmpty()){
            	System.out.println("list is empty!");
            }
            else {
            	System.out.println("list is ok!!");
			}
        } catch (SQLException ex) {
        	System.out.println("list is wrong!!");
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return list;
	}
	
	public Boolean alterIdentifier(int UID) {
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
}
