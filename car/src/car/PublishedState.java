package car;

public class PublishedState extends State{
	public Boolean doAction(int OID,int recipentUID,String recipentPhoneNumber,Context context) {
		context.setState(new ReceivedState()); 
		return OrderDAO.alterOrderRecipent(OID, recipentUID, recipentPhoneNumber) 
				&& OrderDAO.alterOrderState(OID, 1);
	} 
}
