import java.util.Objects;

public class Instruction {
    int currentState;
    char symbol;
    char result;
    public Instruction(int currentState, char symbol, char result) {
        this.currentState = currentState;
        this.symbol = symbol;
        this.result = result;
    }

    @Override
    public String toString() {
        return "γ(q" + currentState + ", " + symbol + ") = " + result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return currentState == that.currentState &&
                symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState, symbol);
    }
}
