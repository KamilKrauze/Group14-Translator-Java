
public class Dictionary {
    public Dictionary() {

    }

    /**
     * Returns String if the exact phrase exists in the dictionary Returns null if
     * doesnt
     * 
     * @param phrase to translate
     * @return translated or null
     */
    public String translatePhrase(String phrase) {
        // System.out.println("Translating");
        System.out.println(phrase);
        if (phrase.split(" ").length > 1) {
            return null;
        }
        return phrase;
    }
}
