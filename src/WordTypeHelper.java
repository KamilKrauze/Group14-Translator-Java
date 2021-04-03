import javax.swing.JOptionPane;

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
        case "adj":
            return WordType.ADJECTIVE;
        case "n":
            return WordType.NOUN;
        case "v":
            return WordType.VERB;
        case "adv":
            return WordType.ADVERB;
        case "prep":
            return WordType.PREPOSITION;
        case "pron":
        	return WordType.PRONOUN;
        case "conj":
        	return WordType.CONJUNCTION;
        case "detr":
        	return WordType.DETERMINER;
        case "excl":
        	return WordType.EXCLAMATION;
        case "intj":
        	return WordType.INTERJECTION;
        case "":
            return WordType.NONE;

        default:
            System.out.printf("Cannot find wordtype %s%n", s);
			JOptionPane.showMessageDialog(null, "Cannot find wordtype \s\n" + s, "Cannot find word type" , JOptionPane.WARNING_MESSAGE);

            return null;
        }
    }
}
