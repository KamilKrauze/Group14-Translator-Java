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

    @Override
    public void deleteNode(int id) throws NodeDoesntExistException {
        super.deleteNode(id);
        balanceTree();
    }

    private void balanceTree() {
        List<TreeNode<T>> preorderList = new ArrayList<TreeNode<T>>();
        super.traverseTreePreorder(super.root, x -> preorderList.add(x));
        super.root = this.recreateTreeFromPreorderList(preorderList);
    }

    private TreeNode<T> recreateTreeFromPreorderList(List<TreeNode<T>> preorderList) {
        int middle = preorderList.size() / 2;
        TreeNode<T> node = preorderList.get(middle);
        
        node.setLeft(null);
        node.setRight(null);

        List<TreeNode<T>> leftList;
        List<TreeNode<T>> rightList;

        if (preorderList.size() > 1) {
            leftList = preorderList.subList(0, middle);
            node.setLeft(recreateTreeFromPreorderList(leftList));
        }
        if (preorderList.size() > 2) {
            rightList = preorderList.subList(middle + 1, leftList.size() - 1); //NOLINT
            node.setRight(recreateTreeFromPreorderList(rightList));
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
