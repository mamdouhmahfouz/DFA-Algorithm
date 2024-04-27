
import java.util.*;

import javax.annotation.processing.SupportedAnnotationTypes;

public class Main {
	 ArrayList<String>states;
	 ArrayList<String>alphabet;
	 ArrayList<Transition>transitions;
	String startState;
	 ArrayList<String>acceptStates;
	 String parse;
	public static DFA generateDFA(String parse){
		 ArrayList<String> states = new ArrayList<>();
		 ArrayList<String> alphabet = new ArrayList<>();
		  ArrayList<Transition> transitions = new ArrayList<>();
		  String startState = "";
		    ArrayList<String> acceptStates = new ArrayList<>();
		    String [] parseNew = parse.split("#");
		    states.addAll(Arrays.asList(parseNew[0].split(";")));
		    alphabet.addAll(Arrays.asList(parseNew[1].split(";")));
		    String [] preTransitions = parseNew[2].split(";");
		    for (String transitionString : preTransitions) {
		        String[] transitionValues = transitionString.split(",");
		            Transition transition = new Transition(transitionValues[0], transitionValues[1], transitionValues[2]);
		            transitions.add(transition);
		            } 
		    startState = parseNew[3];
		    acceptStates.addAll(Arrays.asList(parseNew[4].split(";")));
		startState = parseNew[3];
		 acceptStates = new ArrayList<>(Arrays.asList(parseNew[4].split(";")));
		 DFA dfa = new DFA(states, alphabet, transitions, startState, acceptStates);
		    return dfa;
	}
	public static void main(String[] args) {
		String parse = "q0;q1;d#0;1#q0,0,d;q0,1,q1;q1,0,q0;q1,1,q0;d,0,d;d,1,d#q0#q0;q1";
		DFA dfa1 = generateDFA(parse);
		parse = "s0;s1;s2;s3#0;1#s0,0,s2;s0,1,s1;s1,0,s3;s1,1,s3;s2,0,s1;s2,1,s1;s3,0,s1;s3,1,s1#s0#s2;s3";
		DFA dfa2 = generateDFA(parse);
		boolean isUnion = true;
		DFA merged = dfa1.mergeDFA(dfa2, isUnion);
		merged.display();
	}
}