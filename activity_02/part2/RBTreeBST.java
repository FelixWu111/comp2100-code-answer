public class RBTreeBST<K,V> {
    RBTree rbTree = new RBTree();

    public class KVPair{
        K key;
        V value;
        KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "("+key+", "+value+")";
        }
    }

    public class Node {
        boolean c;          // Color of the node
        KVPair kv;                // Value
        Node l, r, p;    // Children
        public Node(KVPair kv) {
            this.kv = kv;
            this.c = true; // true is red
            p = l = r = null;
        }
        public KVPair getPair() {
            return kv;
        }
    }

    public class RBTree {
        private Node _r;
        public RBTree() {
            _r=null;
        }
        public Node get_r() {
            return _r;
        }

        private void preOrder(Node tree) {
            if(tree != null) {
                System.out.print(tree.kv +" ");
                preOrder(tree.l);
                preOrder(tree.r);
            }
        }

        public void preOrder() {
            preOrder(_r);
            System.out.println();
        }

        public Node search(K key) {
            return find(_r, key);
        }

        private Node find(Node x,K key) {
            if (x==null)
                return x;

            if (key.hashCode() < x.kv.key.hashCode())
                return find(x.l, key);
            else if (key.hashCode() > x.kv.key.hashCode())
                return find(x.r, key);
            else
                return x;
        }

        public int maxHeight(Node top) {
            if(top == null) { return -1; }
            else if(top.l == null && top.r == null){ return 0; }
            else {
                if (top.l != null & top.r != null) { return 1 + Math.max(maxHeight(top.l), maxHeight(top.r)); }
                if(top.l == null) { return 1 + maxHeight(top.r); }
                if(top.r == null) { return 1 + maxHeight(top.l); }
                return 0;
            }
        }

        private void insert(Node n) {
            int max_height = maxHeight(this._r);
            System.out.println("before insertion Height: "+maxHeight(_r));
            boolean triggered = false;
            if(max_height == -1) { // Means the tree is null, simply create a root
                _r = n;
                _r.c = false;
            }else if(max_height == 0) { // There is a root, but left and right are both null
                BSTInsert(n, _r);
            }else if(max_height == 1) {
                BSTInsert(n, _r);
                int h = maxHeight(_r);
                if(h != 1) { triggered = true;}
            }else {
                BSTInsert(n, _r);
                triggered = true;
            }
            System.out.println("After insertion Height: "+maxHeight(_r));

            if(triggered) {
                boolean uncle_color = false;
                if(getUncle(n) == null) {
                }else { uncle_color = getUncle(n).c; }
                if(uncle_color) {
                    System.out.println("recolouring");
                    recursiveRecolour(n);
                }
                else {
                    reshape(n);
                    System.out.println("Height after reshaping:" + maxHeight(_r));
                }
            }
        }

        public void recursiveRecolour(Node node) {
            if(node.kv.key.hashCode() == _r.kv.key.hashCode()) {
                node.c = false;
                return;
            }else {
                Node uncle = getUncle(node);
                Node parent = getParent(node);
                Node grandparent = getParent(getParent(node));
                if(parent.c) {
                    if(uncle.c) {
                        uncle.c = !uncle.c;
                        grandparent.c = !grandparent.c;
                        parent.c = !parent.c;
                        recursiveRecolour(grandparent);
                    }else {reshape(node);}
                }
            }
        }

        public void reshape(Node n) {
            System.out.println("reshaping");
            Node uncle = getUncle(n);
            Node parent = getParent(n);
            Node grandparent = getParent(getParent(n));
            int cmp_pg = parent.kv.key.hashCode() - grandparent.kv.key.hashCode();
            int cmp_np = n.kv.key.hashCode() - parent.kv.key.hashCode();
            if(cmp_pg < 0 && cmp_np < 0) { // n < parent < grandparent
                System.out.println("LL");
                LLRotation(uncle, parent, grandparent, n);
            }
            else if(cmp_pg < 0 && cmp_np > 0) { // parent < n < grandparent
                System.out.println("LR");
                LRRotation(uncle, parent, grandparent, n);
            }
            else if(cmp_pg > 0 && cmp_np < 0) { // parent > n > grandparent
                System.out.println("RL");
                RLRotation(uncle, parent, grandparent, n);
            }
            else { // parent > n > grandparent
                System.out.println("RR");
                RRRotation(uncle, parent, grandparent, n);
            }
        }

        public void LLRotation(Node uncle, Node parent, Node grandparent, Node node) {
            parent.c = !parent.c;
            grandparent.c = !grandparent.c;
            if(grandparent.kv.key.hashCode() < _r.kv.key.hashCode()) {
                Node temp_p_r = parent.r;
                parent.r = grandparent;
                grandparent.l = temp_p_r;
                if(temp_p_r != null) {
                    temp_p_r.p = grandparent;
                }
                grandparent.p = parent;
                _r = parent;
            }
            else{
                Node greatgrandparent = getParent(grandparent);
                Node temp_p_r = parent.r;
                parent.r = grandparent;
                grandparent.l = temp_p_r;
                if(temp_p_r != null) {
                    temp_p_r.p = grandparent;
                }
                parent.p = greatgrandparent;
                grandparent.p = parent;
                if(grandparent.kv.key.hashCode() < greatgrandparent.kv.key.hashCode()) {
                    greatgrandparent.l = parent;
                }else {
                    greatgrandparent.r = parent;
                }

            }
        }

        public void LRRotation(Node uncle, Node parent, Node grandparent, Node node) {
            Node temp_n_l = node.l;
            parent.r = temp_n_l;
            parent.p = node;
            node.l = parent;
            node.p = grandparent;
            grandparent.l = node;
            LLRotation(uncle, node, grandparent, parent); // Note the order has changed.
        }

        public void RRRotation(Node uncle, Node parent, Node grandparent, Node node) {
            parent.c = !parent.c;
            grandparent.c = !grandparent.c;

            if(grandparent.kv.key.hashCode() == _r.kv.key.hashCode()) {
                Node temp_p_l = parent.l;
                parent.l = grandparent;
                grandparent.r = temp_p_l;
                if(temp_p_l != null) {
                    temp_p_l.p = grandparent;
                }
                grandparent.p = parent;
                _r = parent;
            }
            else{
                Node greatgrandparent = getParent(grandparent);
                Node temp_p_l = parent.l;
                parent.l = grandparent;
                grandparent.r = temp_p_l;
                if(temp_p_l != null) {
                    temp_p_l.p = grandparent;
                }
                parent.p = greatgrandparent;
                grandparent.p = parent;
                if(grandparent.kv.key.hashCode() < greatgrandparent.kv.key.hashCode()) {
                    greatgrandparent.l = parent;
                }else {
                    greatgrandparent.r = parent;
                }
            }
        }

        public void RLRotation(Node uncle, Node parent, Node grandparent, Node node) {
            Node temp_n_r = node.r;
            parent.l = temp_n_r;
            parent.p = node;
            node.r = parent;
            node.p = grandparent;
            grandparent.r = node;
            RRRotation(uncle, node, grandparent, parent);
        }

        public Node getParent(Node node){
            if(node.kv.key.hashCode() == _r.kv.key.hashCode()) { // Means the given node is actually the root
                return null;
            }
            return node.p;
        }

        public Node getUncle(Node node){
            if(node == null) {
                return null;
            }
            if (node.kv.key.hashCode() < _r.kv.key.hashCode()) { // The given node is the root
                return null;
            }
            else if (getParent(node).kv.key.hashCode() == _r.kv.key.hashCode()) { // The second level
                return null;
            }else {
                Node grandparent = getParent(getParent(node));
                Node uncle = null;
                if(node.kv.key.hashCode() < grandparent.kv.key.hashCode()) { // The given node is on the left of grandparent
                    uncle = grandparent.r;
                }else {
                    uncle = grandparent.l;
                }
                return uncle;
            }
        }

        public void BSTInsert(Node node, Node top) {
            if (top == null) { top = node; }
            if(node.kv.key.hashCode() == top.kv.key.hashCode()) { return; }
            else if(node.kv.key.hashCode() < top.kv.key.hashCode()) {
                if (top.l == null) {
                    top.l = node;
                    node.p = top;
                }else {
                    BSTInsert(node, top.l);
                }
            }else {
                if (top.r == null) {
                    top.r = node;
                    node.p = top;
                }else {
                    BSTInsert(node, top.r);
                }
            }
        }

        public void removeWithTempFix(K key, Node top) {

            if(top == null) {
                return;
            }

            if(key.hashCode() == top.kv.key.hashCode()) {
                System.out.println("We found the key");
                if(top.l == null && top.r == null) {
                    if(top.kv.key.hashCode() == _r.kv.key.hashCode()) { // The one being removed is root
                        System.out.println("Root of the tree is being removed");
                        _r = null; // We don't care about the color
                    }else { // The one being removed is not root
                        Node topParent = top.p;
                        if(top.kv.key.hashCode() < topParent.kv.key.hashCode()) { // On the left of parent
                            if(!top.c) { // The node is black
                                System.out.println("Left: we are in case 3");
                                topParent.l = null;
                                evaluateAndFix_Left(topParent);
                            }else {
                                System.out.println("Left: we are in case 1");
                                top.p = null;
                                topParent.l = null;
                            }
                        }else {
                            if(!top.c) { // The node is black
                                System.out.println("Right: we are in case 2");
                                topParent.r = null;
                                evaluateAndFix_Right(topParent);
                            }else {
                                System.out.println("Right: we are in case 1");
                                top.p = null;
                                topParent.r = null;
                            }
                        }
                    }
                }

                else if(top.r == null) {
                    System.out.println("the right of the node with given key is null");
                    Node max_left = findBiggest(top.l); // Node with max value on the left
                    KVPair temp_kv = max_left.kv; // Swap the key
                    Node max_left_p = max_left.p;
                    Node max_left_left = max_left.l; // left node of the max

                    if(max_left_left == null) {
                        if(!max_left.c) { // The actual one being deleted is black (Case 3, further fix needed)
                            max_left_p.r = null;
                            evaluateAndFix_Right(max_left_p);
                        }else { // Red otherwise (Case 1)
                            if(max_left.kv.key.hashCode() > max_left_p.kv.key.hashCode()) { // The left of the top(target) is a leaf
                                max_left_p.r = null;
                                max_left.p = null;
                            }else {
                                max_left_p.l = null;
                                max_left.p = null;
                            }
                        }
                    }else {
                        max_left_p.r = max_left_left;
                        max_left_left.p = max_left_p;
                        max_left.p = null;
                        max_left.l = null; // Probably not necessary
                        max_left_left.c = false; // Recolor to black
                    }
                    top.kv = temp_kv;


                }else {
                    System.out.println("the left of the node with given key is null");
                    Node min_right = findSmallest(top.r); // Node with min value on the right
                    KVPair temp_kv = min_right.kv; // Swap the key
                    Node min_right_p = min_right.p;
                    Node min_right_right = min_right.r; // right node of the min

                    if(min_right_right == null) {
                        if(!min_right.c) { // The actual one being deleted is black (Case 3, further fix needed)
                            min_right_p.l = null;
                            evaluateAndFix_Left(min_right_p);
                        }else { // Red otherwise (Case 1)
                            if(min_right.kv.key.hashCode() > min_right_p.kv.key.hashCode()) { // The right of the top(target) is a leaf
                                min_right_p.r = null;
                                min_right.p = null;
                            }else {
                                min_right_p.l = null;
                                min_right.p = null;
                            }
                        }
                    }else { // If min_right_right is not null, it can only be a red leaf (Case 2)
                        min_right_p.l = min_right_right;
                        min_right_right.p = min_right_p;
                        min_right.p = null;
                        min_right.r = null; // Probably not necessary
                        min_right_right.c = false; // Recolor to black
                    }

                    top.kv = temp_kv;
                }

            }else if(key.hashCode() < top.kv.key.hashCode()){
                removeWithTempFix(key, top.l);
            }else {
                removeWithTempFix(key, top.r);
            }
        }

        // Here we have a new concept 'double black', which is used to maintain the black height temporarily
        // We need to remove the 'double black' by either coloring some possible red node to black
        // Or push the double black up until the root, where we can simply make the 'double' a 'single'

        public void evaluateAndFix_Left(Node parent) {
            // Note that the right of the parent cannot be null
            Node sibling = parent.r;

            // Case 3.1.1  P's color doesn't matter
            if(sibling.r != null && sibling.r.c) {
                System.out.println("Left: we are in case 3.1.1");
                sibling.p = null;
                parent.r = sibling.l;
                if(sibling.l != null) {
                    sibling.l.p = parent;
                }
                parent.p = sibling;
                sibling.l = parent;
                sibling.r.c = false;
                sibling.c = parent.c;
                parent.c = false;
                if(parent.kv.key.hashCode() != _r.kv.key.hashCode()) { // Parent is not root
                    Node grandParent = parent.p;
                    sibling.p = grandParent;

                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        grandParent.l = sibling;
                    }else {
                        grandParent.r = sibling;
                    }
                }else {
                    _r = sibling;
                }
            }
            // Case 3.1.2  P's color doesn't matter
            else if(sibling.l != null && sibling.l.c) {
                System.out.println("Left: we are in case 3.1.2");
                Node nephew = sibling.l;
                nephew.p = null;
                parent.r = nephew.l;
                if(nephew.l != null) {
                    nephew.l.p = parent;
                }
                sibling.l = nephew.r;
                if(nephew.r != null) {
                    nephew.r.p = sibling;
                }
                nephew.l = parent;
                nephew.r = sibling;
                parent.p = nephew;
                sibling.p = nephew;
                nephew.c = parent.c;
                parent.c = false;
                if(parent.kv.key.hashCode() != _r.kv.key.hashCode()) { // Parent is not root
                    Node grandParent = parent.p;
                    nephew.p = grandParent;

                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        grandParent.l = nephew;
                    }else {
                        grandParent.r = nephew;
                    }
                }else {
                    _r = nephew;
                }
            }
            else if((sibling.l == null && sibling.r == null && parent.c) || (sibling.l != null && sibling.r != null && !sibling.l.c && !sibling.r.c && parent.c)) {
                System.out.println("Left: we are in case 3.2.1");
                parent.c = false;
                sibling.c = true;
            }
            else if((sibling.l == null && sibling.r == null && !parent.c && !sibling.c) || (sibling.l != null && sibling.r != null && !sibling.l.c && !sibling.r.c && !parent.c && !sibling.c)){
                System.out.println("Left: we are in case 3.2.2");
                sibling.c = true;
                Node grandParent = parent.p;
                if(parent.kv.key.hashCode() == _r.kv.key.hashCode()) {
                    return;
                }else {
                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        evaluateAndFix_Left(grandParent);
                    }else {
                        evaluateAndFix_Right(grandParent);
                    }
                }
            }
            else {
                System.out.println("Left: we are in case 3.3");
                sibling.p = null;
                parent.r = sibling.l;
                sibling.l.p = parent;
                sibling.l = parent;
                parent.r.c = true;
                sibling.c = false;
                if(parent.kv.key.hashCode() != _r.kv.key.hashCode()) { // Parent is not root
                    Node grandParent = parent.p;
                    sibling.p = grandParent;
                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        grandParent.l = sibling;
                    }else {
                        grandParent.r = sibling;
                    }
                }else {
                    _r = sibling;
                }
                parent.p = sibling;
            }
        }


        public void evaluateAndFix_Right(Node parent) {
            Node sibling = parent.l;
            if(sibling.l != null && sibling.l.c) {
                System.out.println("Right: we are in case 3.1.1");
                sibling.p = null;
                parent.l = sibling.r;
                if(sibling.r != null) {
                    sibling.r.p = parent;
                }
                parent.p = sibling;
                sibling.r = parent;
                sibling.l.c = false;
                sibling.c = parent.c;
                parent.c = false;
                if(parent.kv.key.hashCode() != _r.kv.key.hashCode()) { // Parent is not root
                    Node grandParent = parent.p;
                    sibling.p = grandParent;

                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        grandParent.l = sibling;
                    }else {
                        grandParent.r = sibling;
                    }
                }else {
                    _r = sibling;
                }
            }
            else if(sibling.r != null && sibling.r.c) {
                System.out.println("Right: we are in case 3.1.2");
                Node nephew = sibling.r;
                nephew.p = null;
                parent.l = sibling.r;
                if(sibling.r != null) {
                    sibling.r.p = parent;
                }
                sibling.r = sibling.l;
                if(sibling.l != null) {
                    sibling.l.p = parent;
                }
                nephew.r = parent;
                nephew.l = sibling;
                parent.p = nephew;
                sibling.p = nephew;
                nephew.c = parent.c;
                parent.c = false;
                if(parent.kv.key.hashCode() != _r.kv.key.hashCode()) { // Parent is not root
                    Node grandParent = parent.p;
                    nephew.p = grandParent;

                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        grandParent.l = nephew;
                    }else {
                        grandParent.r = nephew;
                    }
                }else {
                    _r = nephew;
                }
            }
            // Case 3.2.1
            else if((sibling.l == null && sibling.r == null && parent.c) || (sibling.l != null && sibling.r != null && !sibling.l.c && !sibling.r.c && parent.c)) {
                System.out.println("Right: we are in case 3.2.1");
                parent.c = false;
                sibling.c = true;
            }
            // Case 3.2.2
            else if((sibling.l == null && sibling.r == null && !parent.c && !sibling.c) || (sibling.l != null && sibling.r != null && !sibling.l.c && !sibling.r.c && !parent.c && !sibling.c)){
                System.out.println("Right: we are in case 3.2.2");
                sibling.c = true;
                Node grandParent = parent.p;

                if(parent.kv.key.hashCode() == _r.kv.key.hashCode()) {
                    return;
                }else {
                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        evaluateAndFix_Left(grandParent);
                    }else {
                        evaluateAndFix_Right(grandParent);
                    }
                }
            }

            // Case 3.3
            else { // Parent is black, sibling is red with two black child
                System.out.println("Right: we are in case 3.3");
                sibling.p = null;
                parent.l = sibling.r;
                // sibling.l is nevey null
                sibling.r.p = parent;

                sibling.r = parent;

                parent.l.c = true;
                sibling.c = false;

                if(parent.kv.key.hashCode() != _r.kv.key.hashCode()) { // Parent is not root
                    Node grandParent = parent.p;
                    sibling.p = grandParent;

                    if(parent.kv.key.hashCode() < grandParent.kv.key.hashCode()) {
                        grandParent.l = sibling;
                    }else {
                        grandParent.r = sibling;
                    }
                }else {
                    _r = sibling;
                }
                parent.p = sibling;

            }
        }


        // Given a node, find the Node with biggest hashCode among its offspring
        public Node findBiggest(Node node) { // Left biggest
            if(node.r == null) {
                return node;
            }else {
                return findBiggest(node.r);
            }
        }

        public Node findSmallest(Node node) { // Right biggest
            if(node.l == null) {
                return node;
            }else {
                return findSmallest(node.l);
            }
        }
    }


    public void put(K key, V value) {
        KVPair kv = new KVPair(key, value);
        Node node = new Node(kv);
        rbTree.insert(node);
    }


    public void remove(K key) {
        Node result = rbTree.search(key);
        if(result != null) {
            rbTree.removeWithTempFix(key, rbTree._r);
        }
    }


}
