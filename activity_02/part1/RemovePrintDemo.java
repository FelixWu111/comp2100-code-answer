import act4.BinarySearchTree;

public class RemovePrintDemo {

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
        
        System.out.println("BST print:");
        tree.print();
        
        System.out.println("Remove items:");
        tree.remove(5);
        tree.remove(4);
        tree.remove(10);
        tree.remove(11);
        System.out.println("Your Pre-order result is: " + tree.nlr());
        System.out.println("    The answer should be: 7 3 1 15");
        System.out.println("");
    }

}
