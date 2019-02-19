public class BinarySearchTreeDemo {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(7);
        tree.insert(3);
        tree.insert(1);
        tree.insert(5);
        tree.insert(4);
        tree.insert(11);
        tree.insert(10);
        tree.insert(15);
        
        System.out.println("BST construction:");
        System.out.println("Your Pre-order result is: " + tree.nlr());
        System.out.println("    The answer should be: 7 3 1 5 4 11 10 15");
        System.out.println("");
        
        System.out.println("BST finding:");
        System.out.println("Finding number 5,  the answer is Yes, your result is " + (tree.find(5)!=null ? "Yes" : "No"));
        System.out.println("Finding number 1,  the answer is Yes, your result is " + (tree.find(1)!=null ? "Yes" : "No"));
        System.out.println("Finding number 12, the answer is No,  your result is " + (tree.find(12)!=null ? "Yes" : "No"));
        System.out.println("Finding number 6,  the answer is No,  your result is " + (tree.find(6)!=null ? "Yes" : "No"));
        System.out.println("");
        
        System.out.println("BST height:");
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
        tree1.insert(4);
        tree1.insert(2);
        tree1.insert(6);
        tree1.insert(1);
        tree1.insert(3);
        tree1.insert(5);
        tree1.insert(7);
        System.out.println("BST 1 pre-order result is: " + tree1.nlr());
        System.out.println("          BST 1 height is: " + Integer.toString(tree1.height()));
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
        for(int i=1; i<=7; ++i) {
            tree2.insert(i);
        }
        System.out.println("BST 2 pre-order result is: " + tree2.nlr());
        System.out.println("          BST 2 height is: " + Integer.toString(tree2.height()));

    }

}
