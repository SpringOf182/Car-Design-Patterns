package car;

public class State {
	public Boolean doAction(Context context) {
		return null;
	} 
	public Boolean cancel(int OID,Context context) {
	    context.setState(new CanceledState()); 
		return OrderDAO.alterOrderState(OID, 3);
	}
}
