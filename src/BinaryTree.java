import java.util.function.Function;

/**
 * The class representing the binary tree and containing all the methods for
 * adding and manipulating the tree
 *
 * @author Vojtěch Loskot
 * @version 01.03.2021
 */
public class BinaryTree<T> {
    TreeNode<T> root = null;

    public BinaryTree() {
    }

    /**
     * @param root The root node of the tree
     */
    public BinaryTree(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * @return TreeNode<T> The root node of the tree
     */
    public TreeNode<T> getRoot() {
        return this.root;
    }

    /**
     * @param root The root node of the tree
     */
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * Adds the node to the tree
     * 
     * @param node
     * @exception IDExistsException if a node wit the same ID already exists in the
     *                              tree
     */
    public void add(TreeNode<T> node) throws IDExistsException {
        if (root == null) {
            root = node;
            return;
        }
        if (this.nodeExists(node.getid())) {
            throw new IDExistsException();
        }

        TreeNode<T> current = root;
        TreeNode<T> previous = current;
        while (current != null) {
            previous = current;
            if (current.getid() < node.getid()) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        if (previous.getid() < node.getid()) {
            previous.setRight(node);
        } else {
            previous.setLeft(node);
        }
    }

    /**
     * @param id to check
     * @return boolean true if item with the id exists
     */
    public boolean nodeExists(int id) {
        return nodeExistsInTree(root, id);
    }

    /**
     * Private utility function used for recursion purposes
     * 
     * @param current node currently being checked
     * @param id      to check
     * @return boolean true if item with the id exists
     */
    private boolean nodeExistsInTree(TreeNode<T> current, int id) {
        if (current == null) {
            return false;
        } else if (current.getid() == id) {
            return true;
        }

        if (current.getid() < id) {
            return nodeExistsInTree(current.getRight(), id);
        } else {
            return nodeExistsInTree(current.getLeft(), id);
        }
    }

    /**
     * @param id to check
     * @return T The value of the nod
     * @throws NodeDoesntExistException if No node with such id exists
     */
    public T getNodeById(int id) throws NodeDoesntExistException {
        return getNodeFromTree(root, id);
    }

    /**
     * Private utility function used for recursion purposes
     * 
     * @param current node currently being checked
     * @param id      to check
     * @return T The value of the nod
     * @throws NodeDoesntExistException if No node with such id exists
     */
    private T getNodeFromTree(TreeNode<T> current, int id) throws NodeDoesntExistException {
        if (current == null) {
            throw new NodeDoesntExistException();
        } else if (current.getid() == id) {
            return current.getValue();
        }

        if (current.getid() < id) {
            return getNodeFromTree(current.getRight(), id);
        } else {
            return getNodeFromTree(current.getLeft(), id);
        }
    }

    /**
     * Deletes the node with the given id from the tree
     * 
     * @param id of node to delete
     * @throws NodeDoesntExistException if No node with such id exists
     */
    public void deleteNode(int id) throws NodeDoesntExistException {
        if (root == null) {
            throw new NodeDoesntExistException();
        }
        TreeNode<T> current = root;
        TreeNode<T> previous = current;

        while (current.getid() != id) {
            previous = current;
            if (current.getid() < id) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
            if (current == null) {
                throw new NodeDoesntExistException();
            }
        }

        // Found the toDelete node
        // The deletion process
        TreeNode<T> toDelete = current;
        TreeNode<T> parent = previous;

        if (toDelete.getLeft() == null && toDelete.getRight() == null) {
            // Both children of toDelete are null
            if (root == toDelete) {
                // if so there will be no parent
                root = null;
            } else if (parent.getid() < toDelete.getid()) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }
        } else if (toDelete.getLeft() == null) {
            // Only left child of toDelet is null
            if (root == toDelete) {
                // if so there will be no parent
                root = root.getRight();
            } else if (parent.getid() < toDelete.getid()) {
                parent.setRight(toDelete.getRight());
            } else {
                parent.setLeft(toDelete.getRight());
            }
        } else if (toDelete.getRight() == null) {
            // Only right child of toDelet is null
            if (root == toDelete) {
                // if so there will be no parent
                root = root.getLeft();
            } else if (parent.getid() < toDelete.getid()) {
                parent.setRight(toDelete.getLeft());
            } else {
                parent.setLeft(toDelete.getLeft());
            }
        } else {
            // both child of todelete are null

            current = toDelete.getLeft();
            previous = null;
            while (current.getRight() != null) {
                previous = current;
                current = current.getRight();
            }
            // we found rightmost and its parent

            // remove current from its postition - it can only have left children
            if (previous == null) {
                toDelete.setLeft(current.getLeft());
            } else {
                previous.setRight(current.getLeft());
            }

            // copy all of toDelete's data to current
            current.setRight(toDelete.getRight());
            current.setLeft(toDelete.getLeft());

            // replace toDelete with current
            if (root == toDelete) {
                // if so there will be no parent
                root = current;
            } else if (parent.getid() < toDelete.getid()) {
                parent.setRight(current);
            } else {
                parent.setLeft(current);
            }

        }

    }

    /**
     * @return String representation of all the items in the tree seperated by
     *         spaces, inorder
     */
    public String getInorderString() {
        StringBuilder s = new StringBuilder();
        traverseTreeInorder(root, x -> s.append(x + " "));
        return s.toString();
    }

    /**
     * Traverses the tree inorder, executing f
     * 
     * @param current currently being checked
     * @param f       function to execute on each node
     */
    protected void traverseTreeInorder(TreeNode<T> current, Function f) {
        if (current != null) {
            traverseTreeInorder(current.getLeft(), f);
            f.apply(current.getValue());
            traverseTreeInorder(current.getRight(), f);
        }
    }

    /**
     * @return String representation of all the items in the tree seperated by
     *         spaces, postorder
     */
    public String getPostorderString() {
        StringBuilder s = new StringBuilder();
        traverseTreePostorder(root, x -> s.append(x + " "));
        return s.toString();
    }

    /**
     * Traverses the tree postorder, executing f
     * 
     * @param current currently being checked
     * @param f       function to execute on each node
     */
    protected void traverseTreePostorder(TreeNode<T> current, Function f) {
        if (current.getLeft() != null) {
            traverseTreeInorder(current.getLeft(), f);
        }
        if (current.getRight() != null) {
            traverseTreeInorder(current.getRight(), f);
        }
        f.apply(current.getValue());
    }


 /**
     * @return String representation of all the items in the tree seperated by
     *         spaces, preorder
     */
    public String getPreorderString() {
        StringBuilder s = new StringBuilder();
        traverseTreePostorder(root, x -> s.append(x + " "));
        return s.toString();
    }

    /**
     * Traverses the tree preorder, executing f
     * 
     * @param current currently being checked
     * @param f       function to execute on each node
     */
    protected void traverseTreePreorder(TreeNode<T> current, Function f) {
        f.apply(current.getValue());
        if (current.getLeft() != null) {
            traverseTreeInorder(current.getLeft(), f);
        }
        if (current.getRight() != null) {
            traverseTreeInorder(current.getRight(), f);
        }
    }



    /** 
     * Gets the height of the tree at the node
     * @param node the node to check
     * @return int height
     */
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftH = getHeight(node.getLeft());
        int rightH = getHeight(node.getRight());
        if (leftH > rightH) {
            return leftH;
        } else {
            return rightH;
        }
    }
}
