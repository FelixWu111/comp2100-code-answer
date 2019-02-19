public class AVLTreeDemo {

	private static int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
	
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(7);
		tree.insert(3);
		tree.insert(1);
		tree.insert(5);
		tree.insert(4);
		tree.insert(11);
		tree.insert(10);
		tree.insert(15);
		
		System.out.println("AVL Tree construction:");
		System.out.println("Your Pre-order result is: " + tree.nlr());
		System.out.println("    The answer should be: 5 3 1 4 10 7 11 15");
		System.out.println("");
		
		System.out.println("BST finding:");
		System.out.println("Finding number 5,  the answer is Yes, your result is " + (tree.find(5)!=null ? "Yes" : "No"));
		System.out.println("Finding number 1,  the answer is Yes, your result is " + (tree.find(1)!=null ? "Yes" : "No"));
		System.out.println("Finding number 12, the answer is No,  your result is " + (tree.find(12)!=null ? "Yes" : "No"));
		System.out.println("Finding number 6,  the answer is No,  your result is " + (tree.find(6)!=null ? "Yes" : "No"));
		System.out.println("");
		
		System.out.println("AVL height:");
		AVLTree<Integer> tree1 = new AVLTree<Integer>();
		tree1.insert(4);
		tree1.insert(2);
		tree1.insert(6);
		tree1.insert(1);
		tree1.insert(3);
		tree1.insert(5);
		tree1.insert(7);
		System.out.println("AVL Tree 1 pre-order result is: " + tree1.nlr());
		System.out.println("          AVL Tree 1 height is: " + Integer.toString(tree1.height()));
		AVLTree<Integer> tree2 = new AVLTree<Integer>();
		for(int i=1; i<=7; ++i) {
			tree2.insert(i);
		}
		System.out.println("AVL Tree 2 pre-order result is: " + tree2.nlr());
		System.out.println("          AVL Tree 2 height is: " + Integer.toString(tree2.height()));
	}

}
