import java.util.function.Consumer;

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

        if (previous.getid() < node.getid()) { // NOSONAR this actually cant be null, but the linter is dumb
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
    TreeNode<T> toDelete;
    TreeNode<T> parent;

    public void deleteNode(int id) throws NodeDoesntExistException {
        if (root == null) {
            throw new NodeDoesntExistException();
        }

        this.getToDeleteNode(id);
        // Found the toDelete node

        // The deletion process
        if (toDelete.getLeft() == null && toDelete.getRight() == null) {
            // Both children of toDelete are null
            this.replaceNodeWith(null);
        } else if (toDelete.getLeft() == null) {
            // Only left child of toDelet is null
            this.replaceNodeWith(toDelete.getRight());

        } else if (toDelete.getRight() == null) {
            // Only right child of toDelet is null
            this.replaceNodeWith(toDelete.getLeft());

        } else {
            // both child of todelete exist

            TreeNode<T> current = toDelete.getLeft();
            TreeNode<T> previous = null;
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
            this.replaceNodeWith(current);

        }

    }

    /**
     * Helper function for node deletion. Gets the toDeleteNode and its parent.
     * 
     * @param id of the node to find
     * @throws NodeDoesntExistException if node with given id doesnt exist
     */
    private void getToDeleteNode(int id) throws NodeDoesntExistException {
        TreeNode<T> current = root;
        TreeNode<T> previous = root;

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
        toDelete = current;
        parent = previous;

    }

    /**
     * Helper function which deletes the node, replacing its parent's appropriate
     * child with the given value
     * 
     * @param replacer the node which will replace the node to be deleted
     */
    private void replaceNodeWith(TreeNode<T> replacer) {
        if (root == toDelete) {
            // if so there will be no parent
            root = replacer;
        } else if (parent.getid() < toDelete.getid()) {
            parent.setRight(replacer);
        } else {
            parent.setLeft(replacer);
        }
    }

    /**
     * @return String representation of all the items in the tree seperated by
     *         spaces, inorder
     */
    public String getInorderString() {
        StringBuilder s = new StringBuilder();
        traverseTreeInorder(root, x -> s.append(x.getValue() + " "));
        return s.toString();
    }

    /**
     * Traverses the tree inorder, executing f
     * 
     * @param current currently being checked
     * @param f       function to execute on each node
     */
    protected void traverseTreeInorder(TreeNode<T> current, Consumer<TreeNode<T>> f) {
        if (current != null) {
            traverseTreeInorder(current.getLeft(), f);
            f.accept(current);
            traverseTreeInorder(current.getRight(), f);
        }
    }

    /**
     * @return String representation of all the items in the tree seperated by
     *         spaces, postorder
     */
    public String getPostorderString() {
        StringBuilder s = new StringBuilder();
        traverseTreePostorder(root, x -> s.append(x.getValue() + " "));
        return s.toString();
    }

    /**
     * Traverses the tree postorder, executing f
     * 
     * @param current currently being checked
     * @param f       function to execute on each node
     */
    protected void traverseTreePostorder(TreeNode<T> current, Consumer<TreeNode<T>> f) {
        if (current.getLeft() != null) {
            traverseTreePostorder(current.getLeft(), f);
        }
        if (current.getRight() != null) {
            traverseTreePostorder(current.getRight(), f);
        }
        f.accept(current);
    }

    /**
     * @return String representation of all the items in the tree seperated by
     *         spaces, preorder
     */
    public String getPreorderString() {
        StringBuilder s = new StringBuilder();
        traverseTreePreorder(root, x -> s.append(x.getValue() + " "));
        return s.toString();
    }

    /**
     * Traverses the tree preorder, executing f
     * 
     * @param current currently being checked
     * @param f       function to execute on each node
     */
    protected void traverseTreePreorder(TreeNode<T> current, Consumer<TreeNode<T>> f) {
        f.accept(current);
        if (current.getLeft() != null) {
            traverseTreePreorder(current.getLeft(), f);
        }
        if (current.getRight() != null) {
            traverseTreePreorder(current.getRight(), f);
        }
    }
}
