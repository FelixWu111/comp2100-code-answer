public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> _root;

    // AVL Tree nodes
    class AVLTreeNode<T extends Comparable<T>> {
        T _value;                 // Value stored in the node
        int _height;              // Height
        AVLTreeNode<T> _left;     // Left child
        AVLTreeNode<T> _right;    // Right child

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this._value = key;
            this._left = left;
            this._right = right;
            this._height = 0;
        }
    }

    public AVLTree() {
        _root = null;
    }

    private int height(AVLTreeNode<T> tree) {
        if (tree != null)
            return tree._height;

        return 0;
    }

    public int height() {
        return height(_root);
    }

    private static int max(int a, int b) {
        return a>b ? a : b;
    }

    // Traversal of the tree
    private String nlr(AVLTreeNode<T> tree) {
        if(tree != null) {
        	String result = tree._value.toString();
            String leftResult = nlr(tree._left);
            String rightResult = nlr(tree._right);
            if(!leftResult.equals("")) {
            	result += " " + leftResult;
            }
            if(!rightResult.equals("")) {
            	result += " " + rightResult;
            }
            return result;
        }
        return "";
    }

    public String nlr() {
    	return nlr(_root);
    }

    // Find value in the AVL tree.
    private AVLTreeNode<T> find(AVLTreeNode<T> x, T key) {
        //! TODO: add your codes here.
    }

    public AVLTreeNode<T> find(T key) {
        return find(_root, key);
    }

    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) {
        //! TODO: Add your implementation here.
    	
    }

    public void insert(T key) {
        _root = insert(_root, key);
    }
}