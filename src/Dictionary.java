import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Dictionary {
    BalancedBinaryTree<DictionaryEntry> tree;
    String[] verbPrefixes;
    String[] verbSuffixes = { "ed", "s", "en" }; // english

    public static void main(String[] args) {
        Dictionary d = new Dictionary("EN-ES-WC.csv");
    }

    public Dictionary() {
        tree = new BalancedBinaryTree<DictionaryEntry>();
    }

    public Dictionary(String file) {
        tree = new BalancedBinaryTree<DictionaryEntry>();
        // loadDictionaryFromCSV(file);
        loadFromFile();
        saveToFile();
    }

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
                } catch (Exception e) { // NOSONAR
                    System.out.printf("Error: %s when parsing line: %s%n", e, line);
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.printf("Error: %s when parsing file: %s%n", e, filePath);
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter("dictionary.txt")) {
            writer.println(String.join(", ", verbPrefixes));
            writer.println(String.join(", ", verbSuffixes));
            tree.traverseTreeInorder(tree.getRoot(), s -> writer.printf("%s,%s%n", s.getid(), s.getValue().toString()));
        } catch (Exception e) {
            System.out.printf("Error: %s when trying to save file: %s%n", e, "");
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))) {
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
                    System.out.printf("Error: %s when trying to read file: %s%n", e, "");

                }
                tree.balanceTree();
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.printf("Error: %s when trying to read file: %s%n", e, "");

        }
    }

    public void addEntry(DictionaryEntry entry) throws IDExistsException {
        tree.add(new TreeNode<DictionaryEntry>(entry, getHashCode(entry.getWord())));
    }

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
        }
    }

    public String translateVerb(String phrase) {
        for (String s : verbPrefixes) {
            phrase = phrase.replaceAll(s, "");
        }
        for (String s : verbSuffixes) {
            phrase = phrase.replaceAll(s, "");
        }
        try {
            DictionaryEntry translation = tree.getNodeById(phrase.hashCode());
            return translation.getTranslation();
        } catch (NodeDoesntExistException er) {
            return null;
        }
    }
}
