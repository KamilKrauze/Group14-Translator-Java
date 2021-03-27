
/**
 * A single Tree Node, containing no logic
 *
 * @author Vojtěch Loskot
 * @version 01.03.2021
 */
public class TreeNode<T> {
    TreeNode<T> parent;

    TreeNode<T> left;
    TreeNode<T> right;
    T value;
    int id;

    /**
     * @return the id of the node
     */
    public int getid() {
        return this.id;
    }

    /**
     * @param id The new id
     */
    public void setid(int id) {
        this.id = id;
    }

    /**
     * Empty constructor
     */
    public TreeNode(int id) {
        this.id = id;

    }

    /**
     * Constructs the node with the value
     */
    public TreeNode(T value, int id) {
        this.value = value;
        this.id = id;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructs the node with the children and the value
     */
    public TreeNode(TreeNode<T> left, TreeNode<T> right, T value, int id) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.id = id;
    }

    /**
     * Gets the Node
     * 
     * @return TreeNode<T>
     */
    public TreeNode<T> getLeft() {
        return this.left;
    }

    /**
     * Sets the node
     * 
     * @param left
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * Gets the Node
     * 
     * @return TreeNode<T>
     */
    public TreeNode<T> getRight() {
        return this.right;
    }

    /**
     * Sets the node
     * 
     * @param right
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    /**
     * Returns the value of the node
     * 
     * @return T
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Sets the value of the node
     * 
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * converts the node to string
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "{" + " left='" + getLeft() + "'" + ", right='" + getRight() + "'" + ", value='" + getValue() + "'"
                + "}";
    }

}
