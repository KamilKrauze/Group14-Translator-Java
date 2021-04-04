import java.util.Arrays;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void main(String[] args) {
        Translator t = new Translator(new Dictionary("dictionary.txt"));
        try {
            System.out.println(t.translateFile("a.txt"));
            System.out.println(
                    t.translateText("Important thing happening here and I think to be. Importance starboard."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        String finalString = String.join(". ", translateParallel(sentenceStream));
        System.out.println("");
        System.out.println("Translator finished");
        System.out.println(finalString);
        return finalString;
    }

    /**
     * Translates the given file and returns the translation as a string
     * 
     * @param filename the name of the file to translate
     * @return String the translated string
     * @throws Exception
     */
    public String translateFile(String filename) throws IOException {
        // String newFileName = filename.substring(0, filename.lastIndexOf(".")) +
        // "_translated.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            String[] result = translateParallel(stream);
            return String.join(" ", result);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Helper fucntion. Takes a stream of sentences, or lines and translates them in
     * parallel, returning an array with the results.
     * 
     * @param Stream<String> sentenceStream
     * @return String[] result
     */
    private String[] translateParallel(Stream<String> stream) {
        return stream.parallel().map(sentence -> {
            // * Check if the sentence is too long and would take too long to translate into
            // phrases recursively
            String[] sentArr = sentence.split(" ");
            if (sentArr.length > maxNumberOfWordInASentence) {
                // Translate them word by word
                return String.join(" ", Arrays.stream(sentArr).parallel().map(phrase -> translateHelper(phrase))
                        .toArray(String[]::new));
            } else {
                // use the sentence translator
                SentenceTranslator sentenceTranslator = new SentenceTranslator(sentence, dictionary);
                return sentenceTranslator.translateSentence();
            }
        }).toArray(String[]::new);
    }

    /**
     * Tries to translate the word, using the dictionary translate word method. If
     * the dictionary doesnt contain the word, will return the original word
     * instead.
     * 
     * @param the word to be translated
     * @return the translated word or the original word if the dictionary doesn't
     *         contain the word
     */
    private String translateHelper(String phrase) {
        String answer = dictionary.translatePhrase(phrase);
        if (answer == null) {
            return phrase;
        } else {
            return answer;
        }
    }

    /**
     * Saves the text to a file
     * 
     * @param text     The text to save
     * @param fileName the filepath of the file
     * @throws IOException if theres an error writing
     */
    public void saveTextToAFile(String text, String fileName) throws IOException {
        try (PrintWriter printwriter = new PrintWriter(fileName)) {
            printwriter.println(text);
        } catch (IOException e) {
            throw e;
        }
    }

}
