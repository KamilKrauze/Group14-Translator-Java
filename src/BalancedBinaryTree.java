import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void add(TreeNode<T> node) throws IDExistsException {
        super.add(node);
        balanceTree();
    }

    public void addUnbalanced(TreeNode<T> node) throws IDExistsException {
        super.add(node);
    }

    @Override
    public void deleteNode(int id) throws NodeDoesntExistException {
        super.deleteNode(id);
        balanceTree();
    }

    public void balanceTree() {
        List<TreeNode<T>> inorderList = new ArrayList<TreeNode<T>>();
        super.traverseTreeInorder(super.root, x -> inorderList.add(x));
        super.root = this.recreateTreeFromInorderList(inorderList);
    }

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

    /**
     * Gets the height of the tree at the node
     * 
     * @param node the node to check
     * @return int height
     */
    private int getHeight(TreeNode<T> node) {
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
