package act4;

import BinarySearchTree1;

public class BinarySearchTreeDemo {

    public static void main(String[] args) {
        BinarySearchTree1 tree = new BinarySearchTree1();
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
        int ar[] = {5, 1, 12, 6, 101}; 
        for (int val : ar){
            System.out.println("Finding number " + val);
            
            try{
                boolean rval = tree.find(val);

                if(rval){
                    System.out.println("Key " + val + " is in the tree!");
                }else{
                    System.out.println("Key " + val + " is not in the tree!");
                }

            }catch(IllegalArgumentException e){
                System.out.println("Key " + val + " cannot be searched");
            }
        }
        System.out.println("");
        
        System.out.println("BST height:");
        BinarySearchTree1 tree1 = new BinarySearchTree1();
        tree1.insert(4);
        tree1.insert(2);
        tree1.insert(6);
        tree1.insert(1);
        tree1.insert(3);
        tree1.insert(5);
        tree1.insert(7);
        System.out.println("BST 1 pre-order result is: " + tree1.nlr());
        System.out.println("          BST 1 height is: " + Integer.toString(tree1.getHeight()));
        BinarySearchTree1 tree2 = new BinarySearchTree1();
        for(int i=1; i<=7; ++i) {
            tree2.insert(i);
        }
        System.out.println("BST 2 pre-order result is: " + tree2.nlr());
        System.out.println("          BST 2 height is: " + Integer.toString(tree2.getHeight()));

    }

}
