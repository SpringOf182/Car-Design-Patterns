

public class CanceledState extends State{
	public Boolean doAction(Context context) {
		System.out.println("The Order was canceled!");
		return false;
	} 
}
