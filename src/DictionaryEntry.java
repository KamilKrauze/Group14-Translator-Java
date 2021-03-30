public class DictionaryEntry {
    private String word;
    private String translation;
    private WordType wordType;


    public DictionaryEntry(String word, String translation, WordType wordType) {
        this.word = word;
        this.translation = translation;
        this.wordType = wordType;
    }

    public DictionaryEntry(String word, String translation, String wordType) throws {
        this.word = word;
        this.translation = translation;
        this.wordType = WordTypeHelper.get;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String gettranslation() {
        return this.translation;
    }

    public void settranslation(String translation) {
        this.translation = translation;
    }

    public WordType getWordType() {
        return this.wordType;
    }

    public void setWordType(WordType wordType) {
        this.wordType = wordType;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DictionaryEntry)) {
            return false;
        }
        DictionaryEntry dictionaryEntry = (DictionaryEntry) o;
        return Objects.equals(word, dictionaryEntry.word) && Objects.equals(translation, dictionaryEntry.translation) && Objects.equals(wordType, dictionaryEntry.wordType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, translation, wordType);
    }

    @Override
    public String toString() {
        return "{" +
            " word='" + getWord() + "'" +
            ", translation='" + gettranslation() + "'" +
            ", wordType='" + getWordType() + "'" +
            "}";
    }

}
