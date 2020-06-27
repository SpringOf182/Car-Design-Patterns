package car;

public class ReceivedState extends State {
	public Boolean doAction(int OID,Context context) {
		context.setState(new CompletedState()); 
		return OrderDAO.alterOrderState(OID, 2);
	} 
}
