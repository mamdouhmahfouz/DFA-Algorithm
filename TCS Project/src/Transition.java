public class Transition {
	
String currentState;
String nextState;
String input;

public Transition(String currentState , String input , String nextState ) {
	
	this.currentState = currentState;
	this.nextState = nextState;
	this.input = input;
	
}	

public String getCurrentState() {
	return currentState;
}

public void setCurrentState(String currentState) {
	this.currentState = currentState;
}

public String getNextState() {
	return nextState;
}

public void setNextState(String nextState) {
	this.nextState = nextState;
}

public String getInput() {
	return input;
}

public void setInput(String input) {
	this.input = input;
}

public void printTransition() {
	
	System.out.println(  "("+ this.currentState  + ","  + this.input   + "," + this.nextState + ")"  );

}
	
public static void main(String[] args) {
	
	Transition x = new Transition("s0" , "a" , "s1");
	System.out.println("DFA Transition");
	x.printTransition();

}

}
