import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * The dictionary class, containing the binary search tree with all the words.
 * 
 * Each dictionary is a one way mapping from a language to another language and
 * doesn't work the other way. It contains methods from translating words and
 * phrases.
 * 
 * Alongside the binary tree with the collection of phrases it also contains a
 * list of all verb suffixes and prefixes.
 *
 * @author Vojtěch Loskot, Kamil Krauze
 * @version 31.03.2021
 */
public class Dictionary {
    BalancedBinaryTree<DictionaryEntry> tree;
    String fileName;
    String[] verbPrefixes;
    String[] verbSuffixes = { "ed", "s", "en" }; // english

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.loadDictionaryFromCSV("es-en.csv");
        dictionary.verbPrefixes = {};
        dictionary.verbSuffixes = {};
        dictionary.fileName = "dictionary-ens-to-en.txt";
        dictionary.saveToFile();
        
    }

    public Dictionary() {
        tree = new BalancedBinaryTree<DictionaryEntry>();
    }

    /**
     * @param file filename of
     */
    public Dictionary(String file) {
        tree = new BalancedBinaryTree<DictionaryEntry>();
        fileName = file;
        loadFromFile();
    }

    /**
     * Loads a dictionary from a csv file generated from the internet. Is
     * inefficient because it accounts for messy, repeating and inconsistent data
     *
     * 
     * @param filePath
     */
    public void loadDictionaryFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                try { // NOSONAR
                    if (tokens.length < 3) {
                        addEntry(new DictionaryEntry(tokens[0], tokens[1], WordType.NONE));
                    } else {
                        addEntry(new DictionaryEntry(tokens[0], tokens[1], tokens[2]));
                    }
                } catch (IDExistsException e) {
                    // System.out.printf("Error: %s when parsing line: %s%n", e, line);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: \s when parsing line: \s\n" + e + line,
                            "Added translation", JOptionPane.WARNING_MESSAGE);
                    System.out.printf("Error: \s when parsing line: \s\n", e, line);
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Error: \s when parsing file: \s\n "+ e +
            // filePath, "Added translation" , JOptionPane.INFORMATION_MESSAGE);// NOSONAR
            System.out.printf("Error: \s when parsing file: \s\n", e, filePath);
        }
    }

    /**
     * Saves the dictionary to a file so that it can be loaded later
     */
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(this.fileName)) {
            writer.println(String.join(", ", verbPrefixes));
            writer.println(String.join(", ", verbSuffixes));
            tree.traverseTreePreorder(tree.getRoot(),
                    s -> writer.printf("%s,%s%n", s.getid(), s.getValue().toString()));
            JOptionPane.showMessageDialog(null, "SAVED",
                    "SAVED!", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            System.out.printf("Error: %s when trying to save file: %s%n", e, "");
        }
    }

    /**
     * Loads the dictionary from a file to which it was saved using the saveToFile
     * method.
     */
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            verbPrefixes = reader.readLine().split(", ");
            verbSuffixes = reader.readLine().split(", ");
            String line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split(",");
                try { // NOSONAR
                    WordType wt = WordType.valueOf(tokens[3]);
                    if (wt == null)
                        wt = WordType.NONE;
                    DictionaryEntry dictEnt = new DictionaryEntry(tokens[1], tokens[2], wt);

                    int id = Integer.parseInt(tokens[0]);
                    tree.addUnbalanced(new TreeNode<DictionaryEntry>(dictEnt, id));
                } catch (Exception e) { // NOSONAR
                    System.out.printf("Error: %s when trying to parse word: %s . Word: %n", e, line);
                    // JOptionPane.showMessageDialog(null, "Error: \s when trying to read file:
                    // \s\n"+e, "Warning" , JOptionPane.WARNING_MESSAGE);

                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.printf("Error: %s when trying to read file: %s%n", e, this.fileName);
            // JOptionPane.showMessageDialog(null,"Error: \s when trying to read file:
            // \s\n"+e,"Warning",JOptionPane.WARNING_MESSAGE);

        }
        tree.balanceTree();

    }

    /**
     * Adds a new entry to the dictionary. Uses the getHashCode method for the ids
     * of the n des.
     * 
     * @param entry to add
     * @throws IDExistsException if the entry already exists
     */
    public void addEntry(DictionaryEntry entry) throws IDExistsException {
        tree.add(new TreeNode<DictionaryEntry>(entry, getHashCode(entry.getWord())));
        saveToFile();
    }

    /**
     * Changes the translation of the word from its current translation to the new
     * translation
     * 
     * @param word        the word used to lookup the dictionary entry
     * @param translation the new translation of the word
     * @throws NodeDoesntExistException if No node with such id exists
     * @return the updated dictionary entry
     */
    public DictionaryEntry modifyEntry(String word, String translation) throws NodeDoesntExistException {
        int id = getHashCode(word);
        DictionaryEntry entry = tree.getNodeById(id);
        entry.setTranslation(translation);
        tree.updateNode(id, entry);
        saveToFile();
        return entry;
    }

    /**
     * Removes the entry from the dictionary
     * 
     * @param word the word to remove
     * @throws NodeDoesntExistException if No node with such id exists
     * @return the removed dictionary entry
     */
    public DictionaryEntry deleteEntry(String word) throws NodeDoesntExistException {
        int id = getHashCode(word);
        DictionaryEntry entry = tree.getNodeById(id);
        tree.deleteNode(id);
        saveToFile();
        return entry;
    }

    /**
     * Returns an integer hash of the given word. Uses the String.hashCode default
     * java method
     * 
     * @param word the word to hash
     * @return int the unique hash code
     */
    private int getHashCode(String word) {
        return word.hashCode();
    }

    /**
     * Returns String if the exact phrase exists in the dictionary Returns null if
     * doesnt
     * 
     * @param phrase to translate
     * @return translated or null
     */
    public String translatePhrase(String phrase) {
        phrase = phrase.trim().toLowerCase();
        try {
            DictionaryEntry translation = tree.getNodeById(phrase.hashCode());
            return translation.getTranslation();
        } catch (NodeDoesntExistException e) {
            return translateVerb(phrase);
            // return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Trims the phrase of all verb suffixes and then tries to translate it. Returns
     * the translation or null if no translation exists.
     * 
     * Using this we can translate even conjugated verbs (walked, drowned) while
     * only havign a record of their infinitives (walk, drown).
     * 
     * @param phrase
     * @return String
     */
    public String translateVerb(String phrase) {
        String original = phrase;
        System.out.printf("Original phrase: %s", phrase);
        try {
            for (String s : verbPrefixes) {
                phrase = phrase.replaceAll("\\b(" + s + ")", "");
            }
        } catch (Exception e) {
            System.out.printf("%nError trying to modify verbs. %s%n", e);
        }
        try {
            for (String s : verbSuffixes) {
                phrase = phrase.replaceAll("(" + s + ")\\b", "");
            }
        } catch (Exception e) {
            System.out.printf("%nError trying to modify verbs. %s%n", e);
        }
        System.out.printf(" Without verb modifiers: %s%n", phrase);
        try {
            DictionaryEntry translation = tree.getNodeById(phrase.hashCode());
            return translation.getTranslation();
        } catch (NodeDoesntExistException e) {
            System.out.println(phrase + phrase.hashCode());
            return null;
        }
    }

    /**
     * The binary tree which contains all the dictionary entries used to perform
     * translations
     * 
     * @return BalancedBinaryTree<DictionaryEntry>
     */
    public BalancedBinaryTree<DictionaryEntry> getTree() {
        return this.tree;
    }

    /**
     * The binary tree which contains all the dictionary entries used to perform
     * translations
     * 
     * @param tree
     */
    public void setTree(BalancedBinaryTree<DictionaryEntry> tree) {
        this.tree = tree;
    }

    /**
     * The filename from which the dictionary is loaded and to which it is saved
     * 
     * @return String
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * The filename from which the dictionary is loaded and to which it is saved
     * 
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * The recognized verb prefixes for this language
     * 
     * @return String[]
     */
    public String[] getVerbPrefixes() {
        return this.verbPrefixes;
    }

    /**
     * The recognized verb prefixes for this language
     * 
     * @param verbPrefixes
     */
    public void setVerbPrefixes(String[] verbPrefixes) {
        this.verbPrefixes = verbPrefixes;
    }

    /**
     * The recognized verb suffixes for this language
     * 
     * @return String[]
     */
    public String[] getVerbSuffixes() {
        return this.verbSuffixes;
    }

    /**
     * The recognized verb suffixes for this language
     * 
     * @param verbSuffixes
     */
    public void setVerbSuffixes(String[] verbSuffixes) {
        this.verbSuffixes = verbSuffixes;
    }

}
