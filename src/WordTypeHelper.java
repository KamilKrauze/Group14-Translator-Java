public class WordTypeHelper {


    public static WordType getWordType(String s) {
        switch (s) {
        case "n":
            return WordType.NOUN;
        case "v":
            return WordType.VERB;
        case "adj":
            return WordType.ADJECTIVE;
        case "prop":
            return WordType.PROPOSITIOIN;
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
