import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        int states = 1;
//        int initialState = 0;
//
//        Set<Character> alphabet = new HashSet<>();
//        alphabet.add('|');
//
//        Set<Instruction> instructions = new HashSet<>();
//        instructions.add(new Instruction(0, '*', '|'));
//        instructions.add(new Instruction(0, '|', 'h'));
//
//        Set<Transition> transitions = new HashSet<>();
//        transitions.add(new Transition(0, '*', 0));
//        transitions.add(new Transition(0, '|', 0));
//
//        TuringMachine tm = new TuringMachine(states, initialState, alphabet, instructions, transitions);
//        tm.run(new Configuration(0, "*", 0));

//        int states = 3;
//        int initialState = 0;
//
//        Set<Character> alphabet = new HashSet<>();
//        alphabet.add('|');
//
//        Set<Instruction> instructions = new HashSet<>();
//        instructions.add(new Instruction(0, '*', 'l'));
//        instructions.add(new Instruction(0, '|', 'r'));
//
//        instructions.add(new Instruction(1, '*', '|'));
//        instructions.add(new Instruction(1, '|', 'r'));
//
//        instructions.add(new Instruction(2, '*', '*'));
//        instructions.add(new Instruction(2, '|', 'r'));
//
//        instructions.add(new Instruction(3, '*', 'h'));
//        instructions.add(new Instruction(3, '|', 'r'));
//
//        Set<Transition> transitions = new HashSet<>();
//        transitions.add(new Transition(0, '*', 0));
//        transitions.add(new Transition(0, '|', 1));
//
//        transitions.add(new Transition(1, '*', 2));
//        transitions.add(new Transition(1, '|', 1));
//
//        transitions.add(new Transition(2, '*', 2));
//        transitions.add(new Transition(2, '|', 3));
//
//        transitions.add(new Transition(3, '*', 3));
//        transitions.add(new Transition(3, '|', 3));
//
//        TuringMachine tm = new TuringMachine(states, initialState, alphabet, instructions, transitions);
//        tm.run(new Configuration(0, "*|||****", 6));

//        int states = 9;
//        int initialState = 0;
//
//        Set<Character> alphabet = new HashSet<>();
//        alphabet.add('|');
//
//        Set<Instruction> instructions = new HashSet<>();
//        instructions.add(new Instruction(0, '*', 'l'));
//        instructions.add(new Instruction(0, '|', 'l'));
//
//        instructions.add(new Instruction(1, '*', 'l'));
//        instructions.add(new Instruction(1, '|', 'l'));
//
//        instructions.add(new Instruction(2, '*', 'l'));
//        instructions.add(new Instruction(2, '|', '*'));
//
//        instructions.add(new Instruction(3, '*', 'l'));
//        instructions.add(new Instruction(3, '|', '|'));
//
//        instructions.add(new Instruction(4, '*', 'h'));
//        instructions.add(new Instruction(4, '|', 'r'));
//
//        instructions.add(new Instruction(5, '*', 'r'));
//        instructions.add(new Instruction(5, '|', '*'));
//
//        instructions.add(new Instruction(6, '*', 'r'));
//        instructions.add(new Instruction(6, '|', '|'));
//
//        instructions.add(new Instruction(7, '*', '|'));
//        instructions.add(new Instruction(7, '|', 'l'));
//
//        instructions.add(new Instruction(8, '*', '*'));
//        instructions.add(new Instruction(8, '|', 'h'));
//
//        Set<Transition> transitions = new HashSet<>();
//        transitions.add(new Transition(0, '*', 1));
//        transitions.add(new Transition(0, '|', 0));
//
//        transitions.add(new Transition(1, '*', 2));
//        transitions.add(new Transition(1, '|', 1));
//
//        transitions.add(new Transition(2, '*', 2));
//        transitions.add(new Transition(2, '|', 3));
//
//        transitions.add(new Transition(3, '*', 4));
//        transitions.add(new Transition(3, '|', 3));
//
//        transitions.add(new Transition(4, '*', 4));
//        transitions.add(new Transition(4, '|', 5));
//
//        transitions.add(new Transition(5, '*', 5));
//        transitions.add(new Transition(5, '|', 6));
//
//        transitions.add(new Transition(6, '*', 7));
//        transitions.add(new Transition(6, '|', 6));
//
//        transitions.add(new Transition(7, '*', 8));
//        transitions.add(new Transition(7, '|', 2));
//
//        transitions.add(new Transition(8, '*', 8));
//        transitions.add(new Transition(8, '|', 8));
//
//        TuringMachine tm = new TuringMachine(states, initialState, alphabet, instructions, transitions);
//        tm.run(new Configuration(0, "*||*|*", 5));

        int states = 50;
        int initialState = 0;

        Set<Character> alphabet = new HashSet<>();
        alphabet.add('|');

//        Set<Instruction> instructions = new HashSet<>();
//        instructions.add(new Instruction(0, '*', 'l'));
//        instructions.add(new Instruction(0, '|', '|'));
//
//        instructions.add(new Instruction(1, '*', '*'));
//        instructions.add(new Instruction(1, '|', 'r'));
//
//        instructions.add(new Instruction(2, '*', 'l'));
//        instructions.add(new Instruction(2, '|', '|'));
//
//        Set<Transition> transitions = new HashSet<>();
//        transitions.add(new Transition(0, '*', 1));
//        transitions.add(new Transition(0, '|', 0));
//
//        transitions.add(new Transition(1, '*', 2));
//        transitions.add(new Transition(1, '|', 2));
//
//        transitions.add(new Transition(2, '*', 2));
//        transitions.add(new Transition(2, '|', 1));


        Set<Instruction> instructions = Generator.generateInstructions(states, alphabet);
        for (Instruction i  : instructions) {
            System.out.println(i.toString());
        }

        System.out.println("\n");

        Set<Transition> transitions = Generator.generateTransitions(states, alphabet);
        for(Transition t : transitions) {
            System.out.println(t.toString());
        }


        TuringMachine tm = new TuringMachine(states, initialState, alphabet, instructions, transitions);
        tm.run(new Configuration(0, "*|*|*", 0));
    }
}
