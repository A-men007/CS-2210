public class Join {

    public boolean contains(int v, int[]c){
        for(int i=0;i<c.length;i++){
            if(c[i]==v) return true;
        }
        return false;
    }
    public int union(int[] A, int[] B, int[] C, int n) {
	/* Input: Arrays A and B each storing n different integers; empty array C of size 2n
           Output: Number of values that were stored in C
	*/
    int[] c = new int[2*n];
    int index = 0;
    for(int i=0; i<n; i++){
        c[i] = A[i];
        index++;
    }

    for(int j=0;j<n;j++){
        if(contains(B[j], c)==false){
            c[index] = B[j];
            index++;
        }
    }

    return c.length;



	}
}