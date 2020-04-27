import java.util.*;

public abstract class Generator {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int HALT = 2;
    private static final int BLANK_SYMBOL = 3;

    public static Set<Instruction> generateInstructions(int states, Set<Character> alphabet) {
        HashSet<Instruction> instructions = new HashSet<>();
        int alphabetSize = alphabet.size();
        for(int i = 0; i < states; i++) {
            // Each state has alphabetSize + 1 instructions
            for(int j = 0; j < alphabetSize + 1; j++) {
                Random rand = new Random();
                int randNumber = rand.nextInt(4 + alphabetSize);
                char result = '\0';
//                System.out.println(randNumber);
                switch (randNumber) {
                    case LEFT: result = 'l'; break;
                    case RIGHT: result = 'r'; break;
                    case HALT: result = 'h'; break;
                    case BLANK_SYMBOL: result = '*'; break;
                    default: result = get(randNumber - 3, alphabet);
                }
                char symbol = j == 0? '*' : get(j, alphabet);
                instructions.add(new Instruction(i, symbol, result));
            }
        }
        return instructions;
    }

    public static Set<Transition> generateTransitions(int states, Set<Character> alphabet) {
        HashSet<Transition> transitions = new HashSet<>();
        int alphabetSize = alphabet.size();
        for(int i = 0; i < states; i++) {
            // Each state has alphabetSize + 1 transitions
            for(int j = 0; j < alphabetSize + 1; j++) {
                Random rand = new Random();
                int randNumber = rand.nextInt(states);
                char symbol = j == 0? '*' : get(j, alphabet);
                transitions.add(new Transition(i, symbol, randNumber));
            }
        }
        return transitions;
    }

    private static char get(int randNumber, Set<Character> alphabet) {
        char res = '\0';
        int i = 0;
        char c = '\0';
        Iterator<Character> it = alphabet.iterator();
        while (i < randNumber && it.hasNext()) {
            c = it.next();
            i++;
        }
        if(i == randNumber) res = c;
        return res;
    }
}