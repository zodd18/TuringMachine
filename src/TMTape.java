// import sun.text.normalizer.UCharacter;
import java.util.ArrayList;
import java.util.Arrays;

public class TMTape {
    char[] negative;
    char[] positive;

    char blankSymbol;

    // Constructor with Tape Expression as parameter
    public TMTape(String E, char blankSymbol) {
        negative = new char[100];
        positive = new char[100];

        this.blankSymbol = blankSymbol;

        for(int i = 0; i < E.length(); i++) {
            setSymbol(E.charAt(i), i);
        }
    }

    public TMTape(String E, char blankSymbol, int index) {
        negative = new char[10];
        positive = new char[10];

        for(int i = 0; i < E.length(); i++) {
            setSymbol(E.charAt(i), index + i);
        }
    }

    public void setSymbol(char symbol, int cursor) {

        if(cursor >= 0) {
            if(cursor >= positive.length) positive = Arrays.copyOf(positive, cursor + 1);

            int i = cursor - 1;
            while (i >= 0 && positive[i] == '\0') {
                positive[i] = blankSymbol;
                i--;
            }

            positive[cursor] = symbol;
        }
         else {
            if(cursor >= negative.length) negative = Arrays.copyOf(negative, cursor + 2);

            int i = Math.abs(cursor);
            while (i >= 0 && negative[i] == '\0') {
                negative[i] = blankSymbol;
                i--;
            }

            negative[Math.abs(cursor + 1)] = symbol;
        }
    }

    public char get(int cursor) {
        char res = blankSymbol;
        if(cursor >= 0 && cursor < positive.length) res = positive[cursor];
        else if(cursor < 0 && Math.abs(cursor) < negative.length) {
            res = negative[Math.abs(cursor + 1)];
            if(res == '\0') {
                negative[Math.abs(cursor + 1)] = blankSymbol;
                res = blankSymbol;
            }
        }
        return res;
    }

    public String getExpression(int cursor) {
        String res = "";

        if(cursor >= 0) {
            int i = cursor;
            while (i >= 0) {
                if(positive[i] == '\0') positive[i] = blankSymbol;
                i--;
            }
        } else {
            int i = -(cursor + 1);
            while (i >= 0) {
                if(negative[i] == '\0') negative[i] = blankSymbol;
                i--;
            }
        }

        for(char s : negative) {
            if(s != '\0') res += s;
        }

        res = new StringBuilder(res).reverse().toString();

        for(char s : positive) {
            if(s != '\0') res += s;
        }

        if(cursor >= 0) {
//            if(emptyBetweenPositive(res, cursor)) res = res.substring(0, cursor);
//            if(emptyRangeNegative(res) != -1) res = res.substring(emptyRangeNegative(res), res.length() - 1);
        } else {
//            if(emptyBetweenNegative(res, -cursor)) {
//                res = res.substring(-cursor, res.length() - 1);
//            }
        }

        if(res.charAt(0) != blankSymbol) res = blankSymbol + res;
        if(res.charAt(res.length() - 1) != blankSymbol) res += blankSymbol;
        return res;
    }

    public boolean emptyBetweenNegative(String str, int cursor) {
        int i = cursor;
        while (i >= 0 && str.charAt(i) == blankSymbol) {
            i--;
        }
        return i == -1;
    }

    public boolean emptyBetweenPositive(String str, int cursor) {
        boolean res = true;
        int i = cursor + 1;
        while (i < str.length() && str.charAt(i) != blankSymbol) {
            i++;
        }
        return i == str.length();
    }

    public int emptyRangeNegative(String str) {
        int res = -1;
        int i = 0;
        while (i < str.length() && str.charAt(i) != blankSymbol) {
            i++;
        }
        return res;
    }
}
