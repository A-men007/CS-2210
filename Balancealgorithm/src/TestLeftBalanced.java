public class TestLeftBalanced {

    public static void main(String[] args) {
    	BinaryNode A = new BinaryNode(6, new BinaryNode(6), new BinaryNode(7, new BinaryNode(7), new BinaryNode(9)));
    	BinaryNode B = new BinaryNode(4, new BinaryNode(4, new BinaryNode(5), new BinaryNode(6)),
    	                                 new BinaryNode(2, new BinaryNode(2), new BinaryNode(9)));
    	BinaryNode D = new BinaryNode(5);
    	
    	LeftBalanced program = new LeftBalanced();
    	if (program.isLeftBalanced(A)) System.out.println("Test 1 passed, Tree A is left balanced");
    	else System.out.println("Test 1 failed, Tree A is left balanced");
    	
    	if (program.isLeftBalanced(B) == false) System.out.println("Test 2 passed, Tree B is not left balanced");
    	else System.out.println("Test 2 failed, Tree B is not left balanced");
    	
    	if (program.isLeftBalanced(D)) System.out.println("Test 3 passed, Tree D is left balanced");
    	else System.out.println("Test 3 failed, Tree D is left balanced");
    }    	    	
}
