package words;

import java.util.HashMap;

public record ProcessedFile(String name, HashMap<String, Word> words) {
}
