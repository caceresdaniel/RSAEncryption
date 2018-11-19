import java.math.*;

public class test {
	public static void main (String[] args) {
		
		BigInteger bi1 = new BigInteger("8311");
		BigInteger bi2 = new BigInteger("30994600");
		
		BigInteger bi3 = bi1.modInverse(bi2);
		
		
		
		System.out.println(bi3);
		
		
    }
	
	public static int ModInverse(int a, int m) {
		a = a % m; 
        for (int x = 1; x < m; x++) 
           if ((a * x) % m == 1) 
              return x; 
        return 1; 
	}
}
