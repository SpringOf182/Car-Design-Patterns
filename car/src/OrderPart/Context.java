

public class Context {
	private State state;
	 
	   public Context(){
	      state = null;
	   }
	   
	   public Context(State state){
		   this.state = state;
	   }
	 
	   public void setState(State state){
	      this.state = state;     
	   }
	 
	   public State getState(){
	      return state;
	   }
	   
	   public Boolean doAction() {
		   return state.doAction(this);
	   }
	   
	   public Boolean cancel(int OID) {
		   return state.cancel(OID, this);
	   }
	   
	   public Boolean doAction(int OID) {
		   return state.doAction(OID, this);
	   }
	   
	   public Boolean doAction(int OID,int recipentUID,String recipentPhoneNumber) {
		   return state.doAction(OID, recipentUID, recipentPhoneNumber, this);
	   }
}
