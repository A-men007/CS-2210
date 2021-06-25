public class TestMySort {

	public static void main(String[] args) {
		int[] A = {26,22,7,2,1,3,5,13,17,18};
		int[] result = {1,2,3,5,7,13,17,18,22,26};
		int n = 10;
		IncDec program = new IncDec();
		
		int[] B = program.mySort(A,n,3);
		int i = 0;
		while ((i < n) && (B[i] == result[i])) ++i;
		while (i < n) {
			System.out.println(B[i]); ++i;}
		if (i == 10) System.out.println("Test passed");
		else System.out.println("Test failed");
	}
}