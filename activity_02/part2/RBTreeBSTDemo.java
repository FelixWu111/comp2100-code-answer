public class RBTreeBSTDemo {
    public static void main(String[] args) {
        RBTreeBST<Integer, String> testMap = new RBTreeBST<Integer, String>();
        String[] names = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        // Test put
        System.out.println();
        System.out.println("Starting Putting:");
        for(int i = 0; i < 15; i++) {
            //System.out.println("Before insertion: "+ testMap.rbTree == null ? "Nothing" : testMap.rbTree);
            testMap.put(i, names[i]);
            testMap.rbTree.preOrder();
            //System.out.println("After insertion: "+ testMap.rbTree);
        }

        System.out.println("Pre-order traversal result of the tree inside the map:");
        testMap.rbTree.preOrder();

        System.out.println();

        System.out.println("Starting Removal:");

        // Test remove
        for(int i = 0; i < 15; i++) {
            System.out.println("Before deletion: ");
            testMap.rbTree.preOrder();
            testMap.rbTree.removeWithTempFix(i, testMap.rbTree.get_r());
            System.out.println("After deletion: ");
            testMap.rbTree.preOrder();
        }


    }
}
