import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Translator {
    Dictionary dictionary;
    int maxNumberOfWordInASentence = 50;

    public static void main(String[] args) {
        Translator t = new Translator();
        t.translateText("Lorem ipsum dolor sit amet. Kurwa do pici.");
    }

    public Translator() {
        dictionary = new Dictionary();
    }

    public void translateText(String text) {
        StringBuilder s = new StringBuilder();
        String[] sentences = text.split("[/\\\n(){}[/].,;]");
        Stream<String> sentenceStream = Arrays.stream(sentences);
        String result[] = sentenceStream.parallel().map(sentence -> {
            // * Check if the sentence is too long and would take too long to translate into
            // phrases recursively
            String sentArr[] = sentence.split(" ");
            if (sentArr.length > maxNumberOfWordInASentence) {
                // Translate them word by word
                return String.join(" ", Arrays.stream(sentArr).parallel()
                        .map(phrase -> dictionary.translatePhrase(phrase)).toArray(String[]::new));

            } else {
                // use the sentence translator
                SentenceTranslator sentenceTranslator = new SentenceTranslator(sentence, dictionary);
                return sentenceTranslator.translateSentence();
            }

        }).toArray(String[]::new);
        String finalString = String.join(". ", result);
        System.out.println("");
        System.out.println("Translator finished");
        System.out.println(finalString);
    }

}
