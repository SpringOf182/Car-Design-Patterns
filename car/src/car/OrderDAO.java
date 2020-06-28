
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class OrderDAO{
	
	public static Boolean alterOrderState(int OID,int state){
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;

        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE order_list SET state = ? WHERE OID = ?");

        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setInt(1, state);
            preparedStatement.setInt(2, OID);

            if(preparedStatement.executeUpdate() > 0)
            	return true;
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement);
        }
	}
	
	public static Boolean alterOrderRecipent(int OID,int recipentUID,String recipentPhoneNumber){
		
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;

        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE order_list SET recipentUID = ?,recipentPhoneNumber = ? WHERE OID = ?");

        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setInt(1, recipentUID);
            preparedStatement.setString(2, recipentPhoneNumber);
            preparedStatement.setInt(3, OID);

            if(preparedStatement.executeUpdate() > 0)
            	return true;
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement);
        }
	}
	
	public static JSONObject getOrderInfo(int UID){
		JSONObject info = new JSONObject();
		Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * From user_list WHERE UID = ?");
        try{
        	preparedStatement = connection.prepareStatement(sqlStatement.toString());
        	preparedStatement.setInt(1, UID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            	info.put("UID", resultSet.getInt("UID"));
            	info.put("nickName", resultSet.getString("nickName"));
            	info.put("image",resultSet.getString("image"));
            }
            return info;
        }catch (Exception ex) {
			// TODO: handle exception
        	Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
		}
	}
	
	public static JSONArray queryUserAcceptOrder(int recipentUID){
		JSONArray orderList = new JSONArray();
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM order_list WHERE recipentUID = ? ORDER BY state");
        try {
        	preparedStatement = connection.prepareStatement(sqlStatement.toString());
        	preparedStatement.setInt(1, recipentUID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	JSONObject order = new JSONObject();
            	JSONObject info = new JSONObject();
            	info = OrderDAO.getOrderInfo(resultSet.getInt("UID"));
            	order.put("OID",resultSet.getInt("OID"));
                order.put("UID",resultSet.getInt("UID"));
                order.put("image", info.getString("image"));
                order.put("nickName", info.getString("nickName"));
                order.put("pickUp",resultSet.getString("pickUp"));
                order.put("delivery",resultSet.getString("delivery"));
                order.put("price",resultSet.getDouble("price"));
                order.put("weight",resultSet.getDouble("weight"));
                order.put("verificationCode",resultSet.getString("verificationCode"));
                order.put("contactNumber",resultSet.getString("contactNumber"));
                order.put("recipentUID",resultSet.getInt("recipentUID"));
                order.put("recipentPhoneNumber",resultSet.getString("recipentPhoneNumber"));
                order.put("memo",resultSet.getString("memo"));
                order.put("latestTime",resultSet.getString("latestTime"));
                order.put("state",resultSet.getString("state"));
                orderList.add(order);
            }
            return orderList;
		} catch (Exception ex) {
			// TODO: handle exception
			Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
		}finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
		}
	}
	
	public static JSONArray queryUserPublishOrder(int UID){
		JSONArray orderList = new JSONArray();
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM order_list WHERE UID = ? ORDER BY state");
        try {
        	preparedStatement = connection.prepareStatement(sqlStatement.toString());
        	preparedStatement.setInt(1, UID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	JSONObject order = new JSONObject();
            	JSONObject info = new JSONObject();
            	info = OrderDAO.getOrderInfo(resultSet.getInt("UID"));
            	order.put("OID",resultSet.getInt("OID"));
                order.put("UID",resultSet.getInt("UID"));
                order.put("image", info.getString("image"));
                order.put("nickName", info.getString("nickName"));
                order.put("pickUp",resultSet.getString("pickUp"));
                order.put("delivery",resultSet.getString("delivery"));
                order.put("company",resultSet.getString("company"));
                order.put("price",resultSet.getDouble("price"));
                order.put("weight",resultSet.getDouble("weight"));
                order.put("verificationCode",resultSet.getString("verificationCode"));
                order.put("contactNumber",resultSet.getString("contactNumber"));
                order.put("recipentUID",resultSet.getInt("recipentUID"));
                order.put("recipentPhoneNumber",resultSet.getString("recipentPhoneNumber"));
                order.put("memo",resultSet.getString("memo"));
                order.put("latestTime",resultSet.getString("latestTime"));
                order.put("state",resultSet.getString("state"));
                orderList.add(order);
            }
            return orderList;
		} catch (Exception ex) {
			Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
		}finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
		}
	}
	
    public static JSONArray queryNotAcceptedOrder(SortStrategy sortStrategy) {
    	JSONArray orderList = new JSONArray();
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sqlStatement = sortStrategy.sortOrder();
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	System.out.println("enter OK");
            	JSONObject order = new JSONObject();
            	JSONObject info = new JSONObject();
            	info = OrderDAO.getOrderInfo(resultSet.getInt("UID"));
            	System.out.println("enter OK info ends");
            	order.put("OID",resultSet.getInt("OID"));
                order.put("UID",resultSet.getInt("UID"));
                order.put("image", info.getString("image"));
                order.put("nickName", info.getString("nickName"));
                order.put("pickUp",resultSet.getString("pickUp"));
                order.put("delivery",resultSet.getString("delivery"));
                order.put("company",resultSet.getString("company"));
                order.put("price",resultSet.getDouble("price"));
                order.put("weight",resultSet.getDouble("weight"));
                order.put("verificationCode",resultSet.getString("verificationCode"));
                order.put("contactNumber",resultSet.getString("contactNumber"));
                order.put("recipentUID",resultSet.getInt("recipentUID"));
                order.put("recipentPhoneNumber",resultSet.getString("recipentPhoneNumber"));
                order.put("memo",resultSet.getString("memo"));
                order.put("latestTime",resultSet.getString("latestTime"));
                order.put("state",resultSet.getString("state"));
                System.out.println("enter OK ends");
                orderList.add(order);
            }
            System.out.println(orderList.toString());
            if(orderList.isEmpty())
            	System.out.println("list is empty!");
            return orderList;
        } catch (SQLException ex) {
        	System.out.println("list is wrong!!");
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static Boolean insertOrder(int UID,
    								  String pickUp,
    								  String delivery,
    								  String company,
    								  double price,
    								  double weight,
    								  String verificationCode,
    								  String contactNumber,
    								  String memo,
    								  String latestTime){
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;

        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO order_list(UID,pickUp,delivery,company,price,weight,verificationCode,contactNumber,memo,latestTime) VALUES(?,?,?,?,?,?,?,?,?,?)");

        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setInt(1, UID);
            preparedStatement.setString(2, pickUp);
            preparedStatement.setString(3, delivery);
            preparedStatement.setString(4, company);
            preparedStatement.setDouble(5, price);
            preparedStatement.setDouble(6, weight);
            preparedStatement.setString(7, verificationCode);
            preparedStatement.setString(8, contactNumber);
            preparedStatement.setString(9, memo);
            preparedStatement.setString(10, latestTime);
            if(preparedStatement.executeUpdate() > 0)
            	return true;
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            DBManager.closeAll(connection, preparedStatement);
        }
    }
    public static JSONArray querySelectedOrder(String pickUp,
    										   String delivery,
    										   String company
    											){
    	JSONArray orderList = new JSONArray();
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM order_list WHERE pickUp LIKE ? AND delivery LIKE ? AND company LIKE ? AND state = 0 ORDER BY latestTime");
        try {
        	preparedStatement = connection.prepareStatement(sqlStatement.toString());
        	preparedStatement.setString(1, pickUp);
        	preparedStatement.setString(2, delivery);
        	preparedStatement.setString(3, company);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            	JSONObject order = new JSONObject();
            	JSONObject info = new JSONObject();
            	info = OrderDAO.getOrderInfo(resultSet.getInt("UID"));
            	
            	order.put("OID",resultSet.getInt("OID"));
                order.put("UID",resultSet.getInt("UID"));
                order.put("image", info.getString("image"));
                order.put("nickName", info.getString("nickName"));
                order.put("pickUp",resultSet.getString("pickUp"));
                order.put("delivery",resultSet.getString("delivery"));
                order.put("company",resultSet.getString("company"));
                order.put("price",resultSet.getDouble("price"));
                order.put("weight",resultSet.getDouble("weight"));
                order.put("verificationCode",resultSet.getString("verificationCode"));
                order.put("contactNumber",resultSet.getString("contactNumber"));
                order.put("recipentUID",resultSet.getInt("recipentUID"));
                order.put("recipentPhoneNumber",resultSet.getString("recipentPhoneNumber"));
                order.put("memo",resultSet.getString("memo"));
                order.put("latestTime",resultSet.getString("latestTime"));
                order.put("state",resultSet.getString("state"));
                orderList.add(order);
            }
            return orderList;
		} catch (Exception ex) {
			// TODO: handle exception
			Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
		}finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
		}
    }
}