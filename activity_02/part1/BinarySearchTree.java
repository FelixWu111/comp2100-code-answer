public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> _root; //Root node

    public class Node<T extends Comparable<T>> {
        T _value;           // Node value
        Node<T> _left;      // Left child
        Node<T> _right;     // Right child
        Node<T> _parent;    // Parent node

        public Node(T key, Node<T> parent, Node<T> left, Node<T> right) {
            _value = key;
            _left = left;
            _right = right;
            _parent = parent;
        }

        public T getKey() {
            return _value;
        }

        public String toString() {
            return _value.toString();
        }
    }

    public BinarySearchTree() {
        _root=null;
    }
    
    private static int max(int a, int b) {
        return a>b ? a : b;
    }
    
    private int height(Node<T> tree) {
        if (tree != null)
            return 1 + max(height(tree._left), height(tree._right));

        return 0;
    }

    public int height() {
        return height(_root);
    }

    // Pre-Order Traversal
    private String nlr(Node<T> tree) {
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

    // Find item in the tree
    private Node<T> find(Node<T> x, T key) {
        //! TODO: Add your implementation here.
        
    }

    public Node<T> find(T key) {
        return find(_root, key);
    }
    
    // Find the minimum value of the BST.
    private Node<T> minimum(Node<T> tree) {
        if (tree == null)
            return null;

        while(tree._left != null)
            tree = tree._left;
        return tree;
    }

    public T minimum() {
        Node<T> p = minimum(_root);
        if (p != null)
            return p._value;

        return null;
    }
     
    // Find the maximum value of BST.
    private Node<T> maximum(Node<T> tree) {
        //! TODO: Add your implementation here.
        
    }

    public T maximum() {
        Node<T> p = maximum(_root);
        if (p != null)
            return p._value;

        return null;
    }

    // Insert the value to the BST.
    private void insert(BinarySearchTree<T> bst, Node<T> z) {
        //! TODO: Add your implementation here.
        
    }

    public void insert(T key) {
        insert(this, new Node<T>(key,null,null,null));
    }
}