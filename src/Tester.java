
public class Tester {
    BinaryTree<Integer> tree;
    BinaryTree<Integer> tree2;
    BinaryTree<Integer> tree3;
    Dictionary dictionary;
    Translator translator;

    public Tester() {
        tree = new BinaryTree<Integer>();
        tree2 = new BinaryTree<Integer>();
        tree3 = new BinaryTree<Integer>();

        dictionary = new Dictionary("dictionary.txt");
        translator = new Translator(dictionary);

    }

    public void testTranslations() {
        System.out.println("Testing translations");
        System.out.println("Test 1");
        System.out.print("Expected output: estribor:");
        System.out.println(translator.translateText("Starboard"));

        System.out.println("Test 2");
        System.out.print("Expected output: null:");
        System.out.println(dictionary.translatePhrase("Aasdasdasdgd"));

    }

    public void testBinaryTrees() {
        System.out.println("Testing binary trees");
        // Test 1
        System.out.println("Test 1");

        try {
            tree.add(new TreeNode<Integer>(3, 3));
            tree.add(new TreeNode<Integer>(1, 1));
            tree.add(new TreeNode<Integer>(5, 5));
        } catch (Exception e) {
            // NOSONAR
        }
        System.out.println(tree);

        // Test 2
        System.out.println("Test 2");

        System.out.println("Expected true");
        System.out.println(tree.nodeExists(3));
        System.out.println("Expected false");
        System.out.println(tree.nodeExists(4));

        // Test 3
        System.out.println("Test 3");

        try {
            tree.add(new TreeNode<Integer>(3, 3));
        } catch (IDExistsException e) {
            System.out.println("Expected to get here");

        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        // Test 4
        System.out.println("Test 4");

        try {
            Integer x = tree.getNodeById(3);
            System.out.println("Expected 3");
            System.out.println(x);

        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        // expected to fail
        try {
            Integer x = tree.getNodeById(4);
            System.out.println(x);
            System.out.println("UnExpected");
        } catch (NodeDoesntExistException e) {
            System.out.println("Expected to get here");
        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        // Test 5
        System.out.println("Test 5");

        // Deleting

        try {
            tree.deleteNode(3);
            System.out.println(tree);
        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        try {
            tree2.add(new TreeNode<Integer>(3, 3));
            tree2.add(new TreeNode<Integer>(1, 1));
            tree2.add(new TreeNode<Integer>(5, 5));

            tree2.deleteNode(1);
            System.out.println(tree2);
        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        try {
            tree3.add(new TreeNode<Integer>(3, 3));
            tree3.add(new TreeNode<Integer>(1, 1));
            tree3.add(new TreeNode<Integer>(5, 5));
            tree3.add(new TreeNode<Integer>(2, 2));

            tree3.deleteNode(4);
            System.out.println(tree3);
        } catch (NodeDoesntExistException e) {
            System.out.println("Expected to get here");
        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        try {
            tree3.deleteNode(1);
            System.out.println(tree3);
        } catch (Exception e) {
            System.out.println("UnExpected");
        }

        // Test 6
        System.out.println("Test 6");
        System.out.println("Expected 1 5");
        System.out.println(tree.getInorderString());
        System.out.println("Expected 3 5");
        System.out.println(tree2.getInorderString());
        System.out.println("Expected 2 3 5");
        System.out.println(tree3.getInorderString());

        // Test 7
        System.out.println("Test 7");
        System.out.println("Expected 5 1");
        System.out.println(tree.getPostorderString());
        System.out.println("Expected 5 3");
        System.out.println(tree2.getPostorderString());
        System.out.println("Expected 2 5 3");
        System.out.println(tree3.getPostorderString());

        System.console().readLine();
    }
}
