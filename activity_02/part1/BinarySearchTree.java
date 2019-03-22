// u6174243 Qingzheng Xu
// u6683369 Jinming Dong
// u6250866 Yu Wu
// u6250082 Xuguang Song

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
        if (x==null)
            return x;

        int compare = key.compareTo(x._value);
        if (compare < 0)
            return find(x._left, key);
        else if (compare > 0)
            return find(x._right, key);
        else
            return x;
    }

    public Node<T> find(T key) {
        return find(_root, key);
    }

    // Insert the node.
    private void insert(Node<T> bst, Node<T> z) {
        if (_root == null) _root = z;
        else {
            int index = bst._value.compareTo(z._value);
            if (index < 0){
                if (bst._right == null)bst._right = new Node<>(z._value, bst, null, null);
                else insert(bst._right, z);
            } else if (index > 0){
                if (bst._left == null) bst._left = new Node<>(z._value, bst, null, null);
                else insert(bst._left, z);
            }


        }

        //!TODO: Add your implementation here.
    }

    public void insert(T key) {
        insert(_root, new Node<T>(key,null,null,null));
    }

    // Remove a node from the BST
    private Node<T> remove(Node<T> bst, Node<T> z) {
        T key = z.getKey();
        Node<T> target = find(z, key);
        if (target != null) {
            Node<T> tParent = target._parent;
            if (tParent._right._value == target._value) tParent._right = null;
            else tParent._left = null;
            Node<T> tempL = target._left;
            Node<T> tempR = target._right;
            target._left = null;
            target._right = null;
            if (tempL != null) insert(tempL.getKey());
            if (tempR != null) insert(tempR.getKey());
        }

        //!TODO: Add your implementation here.
        return bst;
    }

    public void remove(T key) {
        Node<T> z = find(key);
        _root = remove(_root, z);
    }

    // Print the BST.
    private void print(Node<T> tree, T key, int direction) {
        //!TODO: Add your implementation here.
    }

    public void print() {
        if (_root != null)
            print(_root, _root._value, 0);
    }
}
