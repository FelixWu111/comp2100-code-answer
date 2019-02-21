public class RBTree<T extends Comparable<T>> {
    private Node<T> _r;

    public class Node<T extends Comparable<T>> {
        boolean c;          // Color of the node
        T v;                // Value
        Node<T> l, r, p;    // Children

        public Node(T value) {
            v = value;
            c = true;
            p = l = r = null;
        }

        public T getKey() {
            return v;
        }
    }

    public RBTree() {
        _r=null;
    }
    
    private void preOrder(Node<T> tree) {
        if(tree != null) {
            System.out.print(tree.v +" ");
            preOrder(tree.l);
            preOrder(tree.r);
        }
    }
    
    public void preOrder() {
        preOrder(_r);
        System.out.println();
    }
    
    private Node<T> find(Node<T> x, T v) {
        if (x==null)
            return x;

        int cmp = v.compareTo(x.v);
        if (cmp < 0)
            return find(x.l, v);
        else if (cmp > 0)
            return find(x.r, v);
        else
            return x;
    }

    public Node<T> search(T key) {
        return find(_r, key);
    }

    private void insert(Node<T> n) {
        //!TODO: Add your code here.
    }
    
    public void insert(T key) {
        Node<T> node = new Node<T>(key);
        if (node != null)
            insert(node);
    }
}