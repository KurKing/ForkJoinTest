package words;

import java.util.Objects;

public class Word {

    private final String string;
    private final int length;
    private int count = 1;

    public Word(String string) {

        this.string = string;
        this.length = string.length();
    }

    public synchronized void updateCount() {
        count += 1;
    }

    public synchronized void addToCount(int value) {
        count += value;
    }

    public int getCount() {
        return count;
    }

    public int getLength() {
        return length;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string + ": length - " + length + "; count - " + count;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }
}
