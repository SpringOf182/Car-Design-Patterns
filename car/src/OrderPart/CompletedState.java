

public class CompletedState extends State{
	public Boolean doAction(Context context) {
		context.setState(this);
		System.out.println("The Order was completed!");
		return false;
	} 
	public Boolean cancel(int OID,Context context) {
		context.setState(this);
		System.out.println("The Order was completed!");
		return false;
	}
}
