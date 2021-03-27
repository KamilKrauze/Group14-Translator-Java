import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceTranslator {
    String sentence;
    String result = "";
    Dictionary dictionary;
    String wordDelimiter = " ";

    public SentenceTranslator(String s, Dictionary d) {
        sentence = s;
        dictionary = d;
    }

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

    public void translateSentenceHelper(List<String> sentenceArr, int startingSize) {
        for (int phraseLength = startingSize; phraseLength > 0; phraseLength--) {
            for (int i = 0; i < sentenceArr.size() - phraseLength + 1; i++) {

                // System.out.printf("Phrase length: %d, position in phrase %d %n",
                // phraseLength, i);

                List<String> phrase = sentenceArr.subList(i, phraseLength + i);
                String translatedPhrase = dictionary.translatePhrase(String.join(" ", phrase));

                if (translatedPhrase != null) {
                    // If translation exists
                    if (!result.matches("")) {
                        result += " ";
                    }
                    result +=  translatedPhrase;

                    // Continue translating the remainder of the sentence
                    sentenceArr.removeAll(phrase);
                    translateSentenceHelper(sentenceArr, phraseLength);
                }
            }
        }
    }
}
