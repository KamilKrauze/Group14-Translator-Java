/**
 * 
 *
 * @author Vojtěch Loskot
 * @version 31.03.2021
 */
public class WordTypeHelper {

    /** 
     * @param s
     * @return WordType
     */
    public static WordType getWordType(String s) {
        switch (s) {
        case "n":
            return WordType.NOUN;
        case "v":
            return WordType.VERB;
        case "adj":
            return WordType.ADJECTIVE;
        case "prop":
            return WordType.PREPOSITION;
        case "adv":
            return WordType.ADVERB;
        case "prep":
            return WordType.PREPOSITION;
        case "":
            return WordType.NONE;

        default:
            System.out.printf("Cannot find wordtype %s%n", s);
            return null;
        }
    }
}
