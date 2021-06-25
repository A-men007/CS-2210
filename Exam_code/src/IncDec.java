public class IncDec {

    public int[] mySort(int[] A, int n, int t) {
	/* Input: Array A storing n different integers and values n, t, where 0 < t < n-1. Values A[0] ... A[t]
	          are sorted in decreasing order. The remaining values are sorted in increasing order.
       Output: Array B storing all values of A in increasing order
	*/

		int[] B = new int[n];
		int i = n;
		int j = t+1;
		int c=0;
		while (i<=n && j<=t) {
			if(A[i] <= A[j]) {
				B[c] = A[i];
				c++; i++;
			}
			else {
				B[c] = A[j];
				c++; j++;
			}
			while(i<=t) {
				B[c] = A[i];
				c++; i++;
			}
			while(j<=n) {
				B[c] = A[j];
				c++; j++;
		}

	}
		return B;
}
}