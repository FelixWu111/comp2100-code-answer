
public class RBTreeDemo {
    public static void main(String[] args) {
        System.out.println("Construct a red-black tree.");
        RBTree<Integer> tree = new RBTree<Integer>();
        for(int i=0; i<20; ++i) {
            tree.insert(i);
        }

        System.out.println("Pre-order traversal result of the tree:");
        tree.preOrder();

        System.out.println("Search result:");
        System.out.println("Find 17, the answer should be YES,  your result is " + (tree.search(17) != null ? "YES" : "NO"));
        System.out.println("Find 5,  the answer should be YES,  your result is " + (tree.search(5) != null ? "YES" : "NO"));
        System.out.println("Find -3, the answer should be NO, your result is " + (tree.search(-3) != null ? "YES" : "NO"));
        System.out.println("Find 26, the answer should be NO, your result is " + (tree.search(26) != null ? "YES" : "NO"));

    }
}
