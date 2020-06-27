package car;

public class CanceledState extends State{
	public Boolean doAction(Context context) {
		context.setState(this);
		System.out.println("The Order was canceled!");
		return false;
	} 
	public Boolean cancel(int OID,Context context) {
		context.setState(this);
		System.out.println("The Order was canceled!");
		return false;
	}
}
