import java.util.ArrayList;


public class DFA {
    private ArrayList<String> states;
    private ArrayList<String> alphabet;
    private ArrayList<Transition> transitions;
    private String startState;
    private ArrayList<String> acceptStates;

    public DFA(ArrayList<String> states, ArrayList<String> alphabet, ArrayList<Transition> transitions, String startState, ArrayList<String> acceptStates) {
      this.states = states;
      this.alphabet = alphabet;
      this.transitions = transitions;
      this.startState = startState;
      this.acceptStates = acceptStates;
    }


public void display() {
		
		System.out.println(  "states"  );
		System.out.println(  this.states  );
		System.out.println(  "alphabet"  );
		System.out.println(  this.alphabet  );
		
		System.out.println(  "transition states"  );
		for( Transition trans : this.transitions) {
			trans.printTransition();
		}
		
		System.out.println(  "startState"  );
		System.out.println(  this.startState  );
		System.out.println(  "acceptStates"  );
		System.out.println(  this.acceptStates  );

		
		
	}	
	

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
	}

	public ArrayList<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public String getStartState() {
		return startState;
	}

	public void setStartState(String startState) {
		this.startState = startState;
	}

	public ArrayList<String> getAcceptStates() {
		return acceptStates;
	}

	public void setAcceptStates(ArrayList<String> acceptStates) {
		this.acceptStates = acceptStates;
	}
	
	private String NextState(String currentState , String symbol){
		for(Transition transition : this.getTransitions()){
			if(transition.getCurrentState().equals(currentState) && transition.getInput().equals(symbol)){
				return transition.getNextState();
			}
		}
		return null;
	}
	
	public String isLanguageDFA(String input){
		for(int i = 0 ; i < input.length() ; i++){
			if(!this.getAlphabet().contains(Character.toString(input.charAt(i))))
				return "InvalidString";
		}
		String currentState = this.getStartState();
		try {
			for(int i = 0 ; i < input.length() ; i++){
				String symbol = Character.toString(input.charAt(i));
				currentState = NextState(currentState, symbol);
			}

		} catch (IllegalArgumentException e) {
			
			return "rejected";
		}
		
		if(this.getAcceptStates().contains(currentState)){
			return "accepted";
		}
		return "rejected";
	}

	public DFA mergeDFA(DFA secondDFA, boolean isUnion) {
	    DFA dfaNew = new DFA(acceptStates, acceptStates, transitions, startState, acceptStates);

	    ArrayList<String> statesNew = new ArrayList<>();
	    ArrayList<String> acceptStatesNew = new ArrayList<>();
	    ArrayList<Transition> transitionsNew = new ArrayList<>();
	    

	    String startStateNew = "{" + this.getStartState() + "," + secondDFA.getStartState() + "}";
	    statesNew.add(startStateNew);

	    int i = 0;
	    while (i < statesNew.size()) {
	        String currentState = statesNew.get(i);
	        String state1 = currentState.substring(1, currentState.indexOf(","));
	        String state2 = currentState.substring(currentState.indexOf(",") + 1, currentState.length() - 1);

	        for (String symbol : this.getAlphabet()) {
	            String nextState1 = this.NextState(state1, symbol);
	            String nextState2 = secondDFA.NextState(state2, symbol);
	            String nextStateMerged = "{" + nextState1 + "," + nextState2 + "}";
	            Transition mergedTransition = new Transition(currentState, symbol, nextStateMerged);
	            transitionsNew.add(mergedTransition);

	            if (!statesNew.contains(nextStateMerged)) {
	            	statesNew.add(nextStateMerged);
	            }
	        }

	        if (!isUnion) {
	        	if (this.getAcceptStates().contains(state1) && secondDFA.getAcceptStates().contains(state2)) {
	        		acceptStatesNew.add(currentState);

	            }
	        } else {
	            if (this.getAcceptStates().contains(state1) || secondDFA.getAcceptStates().contains(state2)) {
	            	acceptStatesNew.add(currentState);
	            }
	        }

	        i++;
	    }

	    dfaNew.setStates(statesNew);
	    dfaNew.setAlphabet(this.getAlphabet());
	    dfaNew.setTransitions(transitionsNew);
	    dfaNew.setStartState(startStateNew);
	    dfaNew.setAcceptStates(acceptStatesNew);

	    return dfaNew;
	}

}