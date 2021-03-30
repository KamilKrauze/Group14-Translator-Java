public class WordTypeHelper {
    public static WordType getWordType(String s) {
        switch (s) {
            case "n":
                return WordType.NOUN;
                break;
            case "v":
                return WordType.VERB;
                break;
            case "adj":
                return WordType.ADJECTIVE;
                break;
            case "prop":
                return WordType.PROPSITIOIN;
                break;
            case "adv":
                return WordType.ADVERB;
                break;
            case "":
                return WordType.NONE;
                break;
        
            default:
                System.out.printf("Cannot find wordtype %s%n", s);
                break;
        }
    }
}
