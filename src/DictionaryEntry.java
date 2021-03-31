import java.util.Objects;

/**
 * A single word-translation mapping A single entry in the dictionary.
 * 
 * Contains no logic or interesting methods
 *
 * @author Vojtěch Loskot
 * @version 31.03.2021
 */
public class DictionaryEntry {
    private String word;
    private String translation;
    private WordType wordType;

    /**
     * Constructs the Dictionary entry. This is most commonly used for loading
     * DictionaryEntry from a dicitonary save file
     * 
     * @param word        the word to be translated
     * @param translation the translation
     * @param wordType    the word type
     */
    public DictionaryEntry(String word, String translation, WordType wordType) throws IllegalArgumentException {
        if (word == null)
            throw new IllegalArgumentException(word);
        else
            this.word = word.trim().toLowerCase();

        if (translation == null)
            throw new IllegalArgumentException(translation);
        else
            this.translation = translation.trim();

        if (wordType == null)
            throw new IllegalArgumentException();
        else
            this.wordType = wordType;
    }

    /**
     * Constructs the Dictionary entry. This is most commonly used for adding new
     * dicitonary entries that didnt exist before
     * 
     * @param word        the word to be translated
     * @param translation the translation
     * @param wordType    the word type - will be converted using WordTypeHelper
     */
    public DictionaryEntry(String word, String translation, String wordType) throws IllegalArgumentException {
        if (word == null)
            throw new IllegalArgumentException(word);
        else
            this.word = word.trim().toLowerCase();

        if (translation == null)
            throw new IllegalArgumentException(translation);
        else
            this.translation = translation.trim();

        if (wordType == null)
            throw new IllegalArgumentException(wordType);
        else
            this.wordType = WordTypeHelper.getWordType(wordType.trim());
    }

    /**
     * @return String the word in the original language
     */
    public String getWord() {
        return this.word;
    }

    /**
     * @param word the word in the original language
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return String the translated word
     */
    public String getTranslation() {
        return this.translation;
    }

    /**
     * @param translation the translated word
     */
    public void setTranslation(String translation) {
        this.translation = translation;
    }

    /**
     * @return WordType the grammatical type of the word (noun, verb, etc)
     */
    public WordType getWordType() {
        return this.wordType;
    }

    /**
     * @param wordType the grammatical type of the word (noun, verb, etc)
     */
    public void setWordType(WordType wordType) {
        this.wordType = wordType;
    }

    /**
     * Compares the word
     * 
     * @param o
     * @return boolean if the two words are identical
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DictionaryEntry)) {
            return false;
        }
        DictionaryEntry dictionaryEntry = (DictionaryEntry) o;
        return Objects.equals(word, dictionaryEntry.word) && Objects.equals(translation, dictionaryEntry.translation)
                && Objects.equals(wordType, dictionaryEntry.wordType);
    }

    /**
     * 
     * @return int unique hash of the dictionary entry
     */
    @Override
    public int hashCode() {
        return Objects.hash(word, translation, wordType);
    }

    /**
     * Stringifies the object
     * 
     * @return String
     */
    @Override
    public String toString() {
        return getWord() + "," + getTranslation() + "," + getWordType();
    }

}
