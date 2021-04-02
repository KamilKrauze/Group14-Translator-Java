import java.util.Arrays;
import java.util.stream.Stream;

/**
 * The main translator class
 *
 * @author Vojtěch Loskot
 * @version 31.03.2021
 */
public class Translator {
    Dictionary dictionary;
    int maxNumberOfWordInASentence = 50;

    /**
     * @param args
     */
//    public static void main(String[] args) {
//        Translator t = new Translator();
//        t.translateText("Lorem ipsum dolor sit amet. Kurwa do pici.");
//    }

    public Translator() {
        dictionary = new Dictionary();
    }

    public Translator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * The dictionary used to translate
     * 
     * @return Dictionary
     */
    public Dictionary getDictionary() {
        return this.dictionary;
    }

    /**
     * The dictionary used to translate
     * 
     * @param dictionary
     */
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * The maximum number of words in sentences. This is used to improve the
     * performance of the algorithm. If a sentence in the text is bigger than this
     * number, the algorithm will translate it word by word, rather then recursively
     * translate the phrases
     * 
     * @return
     */
    public int getMaxNumberOfWordInASentence() {
        return this.maxNumberOfWordInASentence;
    }

    /**
     * The maximum number of words in sentences. This is used to improve the
     * performance of the algorithm. If a sentence in the text is bigger than this
     * number, the algorithm will translate it word by word, rather then recursively
     * translate the phrases
     * 
     * @param maxNumberOfWordInASentence
     */
    public void setMaxNumberOfWordInASentence(int maxNumberOfWordInASentence) {
        this.maxNumberOfWordInASentence = maxNumberOfWordInASentence;
    }

    /**
     * Translates the given text.
     * 
     * Splits the text into sentences and uses the sentenseTranslation recursive
     * algorithm to smartly translate each one. Uses streams and paralelization to
     * translate each sentence simultaneously.
     * 
     * @param text to translate
     */
    public String translateText(String text) {
        String[] sentences = text.split("[/\\\n(){}[/].,;]");
        Stream<String> sentenceStream = Arrays.stream(sentences);
        String[] result = sentenceStream.parallel().map(sentence -> {
            // * Check if the sentence is too long and would take too long to translate into
            // phrases recursively
            String[] sentArr = sentence.split(" ");
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
        return finalString;
    }

}
