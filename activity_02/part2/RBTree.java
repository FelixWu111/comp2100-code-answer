// u6174243 Qingzheng Xu
// u6683369 Jinming Dong
// u6250866 Yu Wu
// u6250082 Xuguang Song

import java.util.Hashtable;

public class RBTree<T extends Comparable<T>> {
    private Node<T> root;

    public class Node<T extends Comparable<T>> {
        Colour colour;
        T value;
        Node<T> parent;
        Node<T> l, r;

        public Node(T value) {
            this.value = value;
            this.colour = Colour.RED;
            this.parent = null;

            this.l = new Node<>();
            this.r = new Node<>();
            this.l.parent = this;
            this.r.parent = this;
        }

        public Node() {
            this.value = null;
            this.colour = Colour.BLACK;
        }

        public void insert(Node<T> n) {
            // TODO: Implement this (1/3)

            // NOTE: To make implementing the RBTree easier, instead of having null
            // children, nodes with a null value are used instead so take this into
            // account if you are trying to copy over your part 1 solution

            // HINT: Remember to update the parent of the node being inserted!

            if (value == null) {
                if (n.value.compareTo(this.parent.value) > 0) {
                    this.parent.r = n;
                } else {
                    this.parent.l = n;
                }
                n.parent = this.parent;
                return;
            }

            if (value != n.value) {
                int key = value.compareTo(n.value);
                if (key < 0) {
                    r.insert(n);
                } else {
                    l.insert(n);
                }
            }


        }

        public void rotateLeft() {
            if (parent != null) {
                if (parent.l.value == value) {
                    parent.l = r;
                } else {
                    parent.r = r;
                }
            }
            r.parent = parent;

            parent = r;
            r = parent.l;
            r.parent = this;
            parent.l = this;
        }


        public void rotateRight() {
            // TODO: Implement this function (2/3)
            // HINT: It is the mirrored version of rotateLeft()

            // Make parent (if it exists) and right branch point to each other
            if (parent != null) {
                // Determine whether this node is the left or right child of its parent
                if (parent.r.value == value) {
                    parent.r = l;
                } else {
                    parent.l = l;
                }
            }
            l.parent = parent;

            parent = l;
            l = parent.r;
            l.parent = this;
            parent.r = this;
        }

    }

    public RBTree() {
        root = null;
    }


    private void insert(Node<T> x) {
        // TODO: Complete this function (3/3)

        if (root == null) {
            root = x;
        } else {
            root.insert(x);
        }

        while (x.value != root.value && x.parent.colour == Colour.RED) {
            boolean left = x.parent == x.parent.parent.l;
            Node<T> y = left ? x.parent.parent.r : x.parent.parent.l;

            if (y.colour == Colour.RED) {

                x.parent.colour = Colour.BLACK;
                y.colour = Colour.BLACK;
                x.parent.parent.colour = Colour.RED;

                x = x.parent.parent;
            } else {
                if (x.value == (left ? x.parent.r.value : x.parent.l.value)) {
                    x = x.parent;
                    if (left) {
                        if (x.value == root.value) root = x.r;
                        x.rotateLeft();
                    } else {
                        // TODO: Implement this
                        if (x.value == root.value) root = x.l;
                        x.rotateRight();
                    }

                }
                x.parent.colour = Colour.BLACK;
                x.parent.parent.colour = Colour.RED;
                // TODO: Complete this

                x = x.parent.parent;
                if (left) {
                    if (x.value == root.value) root = x.l;
                    x.rotateRight();
                } else {
                    if (x.value == root.value) root = x.r;
                    x.rotateLeft();
                }
            }
        }

        root.colour = Colour.BLACK;
    }


    public void insert(T value) {
        Node<T> node = new Node<T>(value);
        if (node != null)
            insert(node);
    }

    private void preOrder(Node<T> tree) {
        if(tree != null && tree.value != null) {
            System.out.print(tree.value +" ");
            preOrder(tree.l);
            preOrder(tree.r);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private Node<T> find(Node<T> x, T v) {
        if (x.value == null)
            return null;

        int cmp = v.compareTo(x.value);
        if (cmp < 0)
            return find(x.l, v);
        else if (cmp > 0)
            return find(x.r, v);
        else
            return x;
    }

    public Node<T> search(T key) {
        return find(root, key);
    }


    public enum Colour {
        RED,
        BLACK;
    }

}

