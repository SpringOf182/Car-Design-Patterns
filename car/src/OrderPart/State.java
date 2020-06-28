


public class State {
	public Boolean doAction(Context context) {
		return null;
	} 
	public Boolean doAction(int OID,Context context) {
		return false;
	}
	public Boolean doAction(int OID,int recipentUID,String recipentPhoneNumber,Context context) {
		return false;
	}
	public Boolean cancel(int OID,Context context) {
	    context.setState(new CanceledState()); 
		return OrderDAO.alterOrderState(OID, 3);
	}
}
