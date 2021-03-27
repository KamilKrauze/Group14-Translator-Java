import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        for (String sentence : sentences) {

            // * Check if the sentence is too long and would take too long to translate into
            // phrases recursively
            String sentArr[] = sentence.split(" ");
            if (sentArr.length > maxNumberOfWordInASentence) {
                // Translate them word by word
                for (String phrase : sentArr) {
                    s.append(" " + dictionary.translatePhrase(phrase));
                }
            } else {
                // use the sentence translator
                SentenceTranslator sentenceTranslator = new SentenceTranslator(sentence, dictionary);
                s.append(sentenceTranslator.translateSentence());
            }
            s.append(". ");
        }
        System.out.println("");
        System.out.println("Translator finished");
        System.out.println(s);
    }

}
