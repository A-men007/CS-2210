//package conceptasn1;
public class Heavy
{
	
    public boolean isHeavy(int[] A, int n) {
    	int i;
        for (i=1; i<n ; i++) {   
        	int sum = 0;
        	for (int x=0; x<i; x++) {
        		sum = sum + A[x];
        	}
        	if (A[i] < sum) {
        		return false;
        	}
        }
        return true;
    }
    
    public static void main(String[] args) {
    	Heavy h = new Heavy();
    	boolean heavy;
        int arr[] = {-1,1,2,3,7,14,28,56,112,224,448};
	    int arr1[] = {0,1,2,3,5,20,36,78,120,300,500};
	    heavy = h.isHeavy(arr1, 11);
	    System.out.println(heavy);
    }

}
