import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A helper class to the Translator class. The algorithm is better described in
 * the report.
 * 
 * Used for wrapping of the translate sentence method and its helper method.
 * 
 * This abstraction is also useful for multithreading purposes.
 *
 * @author Vojtěch Loskot
 * @version 31.03.2021
 */
public class SentenceTranslator {
    String sentence;
    String result = "";
    Dictionary dictionary;
    String wordDelimiter = " ";

    /**
     * Constructs the class
     * 
     * @param String the sentence to translated
     * @param d      the dictionary to use for translating
     */
    public SentenceTranslator(String s, Dictionary d) {
        sentence = s;
        dictionary = d;
    }

    /**
     * The main method, uses our recursive pattern to translate sentences by first
     * checign if the whole sentence is a translateable phrase, then, if it isnt,
     * recursively breaking it down into smaller phrases and translating those.
     * 
     * @return String the translated phrase
     */
    public String translateSentence() {
        String translated = dictionary.translatePhrase(sentence);
        if (translated != null) {
            return translated;
        }

        List<String> sentenceArr = new ArrayList<String>(Arrays.asList(sentence.split(wordDelimiter)));
        sentenceArr.removeIf(x -> x == null || x.matches(" "));
        translateSentenceHelper(sentenceArr, sentenceArr.size() - 1);
        return result;
    }

    /**
     * Takes a list of words in a sentence and a size of the phrase to check.
     * 
     * The size of the phrase starts at the length of the sentence, but decreases as
     * the algorithm tries to translate smaller and smaller phrases.
     * 
     * @param sentenceArr  The sentence split into a list of words
     * @param startingSize The size of the phrases that the algorithm is currently
     *                     checking
     */
    public void translateSentenceHelper(List<String> sentenceArr, int startingSize) {
        for (int phraseLength = startingSize; phraseLength > 0; phraseLength--) {
            for (int i = 0; i < sentenceArr.size() - phraseLength + 1; i++) {

                // System.out.printf("Phrase length: %d, position in phrase %d %n",
                // phraseLength, i);

                List<String> phrase = sentenceArr.subList(i, phraseLength + i);
                String translatedPhrase = dictionary.translatePhrase(String.join(" ", phrase));
                if (translatedPhrase == null && phrase.size() == 1) {
                    translatedPhrase = phrase.get(0);
                }
                if (translatedPhrase != null) {
                    // If translation exists
                    if (!result.matches("")) {
                        result += " ";
                    }
                    result += translatedPhrase;

                    // Continue translating the remainder of the sentence
                    sentenceArr.removeAll(phrase);
                    translateSentenceHelper(sentenceArr, phraseLength);
                }
            }
        }
    }
}
