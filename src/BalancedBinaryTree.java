import java.util.ArrayList;
import java.util.List;

/**
 * A balanced binary tree. Replaces the add and remove operations with
 * operations that balance the tree right after. Uses the inorder list to
 * balance the tree and therefore mass imports should be done using the
 * addUnbalanced operation and balancing after.
 *
 * @extends BinaryTree
 * @author Vojtěch Loskot
 * @version 31.03.2021
 */
public class BalancedBinaryTree<T> extends BinaryTree<T> {

    public BalancedBinaryTree() {
        super();
    }

    /**
     * @param root The root node of the tree
     */
    public BalancedBinaryTree(TreeNode<T> root) {
        super(root);
        this.root = root;
    }

    /**
     * Adds a node to a tree and balances it
     * 
     * @param node
     * @throws IDExistsException
     */
    @Override
    public void add(TreeNode<T> node) throws IDExistsException {
        super.add(node);
        balanceTree();
    }

    /**
     * Adds a node to a tree without balancing it DANGEROUS! Be sure to call
     * balanceTree() after
     * 
     * @param node
     * @throws IDExistsException
     */
    public void addUnbalanced(TreeNode<T> node) throws IDExistsException {
        super.add(node);
    }

    /**
     * Removes a node from a tree and balances it
     * 
     * @param id
     * @throws NodeDoesntExistException
     */
    @Override
    public void deleteNode(int id) throws NodeDoesntExistException {
        super.deleteNode(id);
        balanceTree();
    }

    /**
     * Balances the tree by first creating an array of the tree nodes by traversing
     * in order and then recreating the tree from that array. This implementation is
     * in O(n) time
     */
    public void balanceTree() {
        List<TreeNode<T>> inorderList = new ArrayList<TreeNode<T>>();
        super.traverseTreeInorderNonRecursive(x -> inorderList.add(x)); // NOSONAR
        super.root = this.recreateTreeFromInorderList(inorderList);
    }

    /**
     * Recreates a tree from an inorder list of nodes. Uses recursion.
     * 
     * @param inorderList the inorder list of the nodes
     * @return TreeNode<T> the root node of the tree
     */
    private TreeNode<T> recreateTreeFromInorderList(List<TreeNode<T>> inorderList) {

        int middle = inorderList.size() / 2;
        TreeNode<T> node = inorderList.get(middle);

        node.setLeft(null);
        node.setRight(null);

        if (inorderList.size() == 1)
            return node;

        List<TreeNode<T>> rightList;
        List<TreeNode<T>> leftList = null;

        if (inorderList.size() >= 2) {
            leftList = inorderList.subList(0, middle);
            node.setLeft(recreateTreeFromInorderList(leftList));
        }
        if (inorderList.size() >= 3) {
            rightList = inorderList.subList(middle + 1, inorderList.size()); // NOSONAR
            node.setRight(recreateTreeFromInorderList(rightList));
        }
        return node;
    }
}
