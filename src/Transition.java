import java.util.Objects;

public class Transition {
    int currentState;
    char symbol;
    int nextState;
    public Transition(int currentState, char symbol, int nextState) {
        this.currentState = currentState;
        this.symbol = symbol;
        this.nextState = nextState;
    }

    @Override
    public String toString() {
        return "δ(q" + currentState + ", " + symbol + ") = q" + nextState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return currentState == that.currentState &&
                symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState, symbol);
    }
}
