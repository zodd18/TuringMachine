import java.util.*;

public class TuringMachine {
    // STATES, INITIAL_STATE, ALPHABET, BLANK SYMBOL

    public static final char LEFT = 'l';
    public static final char RIGHT = 'r';
    public static final char HALT = 'h';

    private int states;
    private int initialState;

    private Set<Character> alphabet;
    private char blankSymbol;

    private Set<Instruction> instructions;
    private Set<Transition> transitions;

    private int cursor;
    private TMTape tape;

    public TuringMachine(int states, int initialState, Set<Character> alphabet, Set<Instruction> instructions, Set<Transition> transitions) {
        blankSymbol = '*';
        cursor = 0;

        if(states > 0)
            this.states = states;
        if(validState(initialState))
            this.initialState = initialState;
        else throw new TMException("unvalid initial state");

        if(checkAlphabet(alphabet))
            this.alphabet = alphabet;
        else throw new TMException("unvalid alphabet");

        if(checkInstructions(instructions))
            this.instructions = instructions;
        else throw new TMException("unvalid set of instructions");

        if(checkTransitions(transitions))
            this.transitions = transitions;
        else throw new TMException("unvalid set of transitions");

        tape = new TMTape("", blankSymbol);
    }

    public void run(Configuration c) {
        tape = new TMTape(c.E, blankSymbol);
        compute(c);
    }

    public void compute(Configuration c) {
        System.out.print(c.toString());
        if(validState(c.state) && validSymbols(c.E)) {
            int state = c.state;
            cursor = c.z;
            char ez = tape.get(cursor);
            System.out.print(" E(" + cursor + ") = " + ez + "\n");

            char instructionRes = instrRes(state, ez);
            int transitionRes = transRes(state, ez);

            if(instructionRes != HALT) {
                switch (instructionRes) {
                    case LEFT: cursor--; break;
                    case RIGHT: cursor++; break;
                    default: tape.setSymbol(instructionRes, cursor);
                }
                compute(new Configuration(transitionRes, tape.getExpression(cursor), cursor));
            }
            else if(instructionRes == HALT) System.out.println("HALT");
        }
    }

    // <Adds>
    public void addInstruction(int currentState, char symbol, char result) {
        if(validState(currentState) && (validSymbol(symbol) || instructionSymbol(result)))
            instructions.add(new Instruction(currentState, symbol, result));

        else throw new TMException("either symbol or result is unvalid");
    }

    public char instrRes(int currentState, char symbol) {
        Iterator<Instruction> it = instructions.iterator();
        boolean ok = false;
        char res = '*';

        while (it.hasNext() && !ok) {
            Instruction instr = it.next();
            if(instr.equals(new Instruction(currentState, symbol, ' '))) {
                res = instr.result;
                ok = true;
            }
        }
        return res;
    }

    public void addTransition(int currentState, char symbol, int nextState) {
        if(validState(currentState) && validState(nextState) && validSymbol(symbol))
            transitions.add(new Transition(currentState, symbol, nextState));

        else throw new TMException("either symbol or next state is unvalid");
    }

    public int transRes(int currentState, char symbol) {
        Iterator<Transition> it = transitions.iterator();
        boolean ok = false;
        int res = 0;

        while (it.hasNext() && !ok) {
            Transition trans = it.next();
            if(trans.equals(new Transition(currentState, symbol, 0))) {
                res = trans.nextState;
                ok = true;
            }
        }
        return res;
    }

    // <Sets>
    public void setBlankSymbol(char blankSymbol) {
        if(!alphabet.contains(blankSymbol) && !(blankSymbol == LEFT || blankSymbol == RIGHT || blankSymbol == HALT))
            this.blankSymbol = blankSymbol;
    }

    public void setCursor(int index) {
        cursor = index;
    }

    public void setTape(String E, int index) {
        tape = new TMTape(E, blankSymbol, index);
    }

    // <Conditions>
    private boolean validState(int state) {
        return state >= 0 && state < states;
    }

    private boolean validSymbol(char symbol) {
        return alphabet.contains(symbol) || symbol == blankSymbol;
    }

    private boolean validSymbols(String symbols) {
        boolean ok = true;
        int i = 0;
        while(i < symbols.length() && ok) {
            if(!validSymbol(symbols.charAt(i))) ok = false;
            i++;
        }
        return ok;
    }

    private boolean instructionSymbol(char symbol) {
        return symbol == LEFT || symbol == RIGHT || symbol == HALT || validSymbol(symbol);
    }

    private boolean checkAlphabet(Set<Character> alphabet) {
        return !alphabet.contains(blankSymbol) && !alphabet.contains(LEFT) && !alphabet.contains(RIGHT) && !alphabet.contains(HALT);
    }

    private boolean checkInstructions(Set<Instruction> instructions) {
        boolean ok = true;
        Iterator<Instruction> it = instructions.iterator();
        while (it.hasNext() && ok) {
            Instruction instr = it.next();
            if(!(validSymbol(instr.symbol) || instructionSymbol(instr.result) || validState(instr.currentState)))
                ok = false;
        }
        return ok;
    }

    private boolean checkTransitions(Set<Transition> transitions) {
        boolean ok = true;
        Iterator<Transition> it = transitions.iterator();
        while (it.hasNext() && ok) {
            Transition trans = it.next();
            if(!(validSymbol(trans.symbol) || validState(trans.currentState) || validState(trans.nextState)))
                ok = false;
        }
        return ok;
    }
}
