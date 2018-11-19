import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.math.BigInteger;

public class RSAGenKey {
	public static void main(String[] args) throws NumberFormatException, IOException {
		if (args.length == 1) {
			BitKeyGen(Integer.parseInt(args[0]));
		} else if (args.length == 3) {
			KeyGen(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		} else {
			System.out.println("Invalid Arguments!");
		}
	}

	public static void BitKeyGen(int bits) throws IOException {
		Random rand = new Random();
		int p = 0;
		do {
			p = rand.nextInt((int) Math.pow(2, bits)) + 1;
		} while (isPrime(p));

		int q = 0;
		do {
			q = rand.nextInt((int) Math.pow(2, bits)) + 1;
		} while (isPrime(q));

		int n = p * q;
		int phiN = (p - 1) * (q - 1);

		int e = 0;
		do {
			e = rand.nextInt(phiN) + 1;
		} while (GCD(e, phiN) != 1);

		BigInteger ebi, pbi, d;
		ebi = BigInteger.valueOf(e);
		pbi = BigInteger.valueOf(phiN);
		
		d = ebi.modInverse(pbi);

		PubKeyFileWriter(e, n);
		PriKeyFileWriter(d, n);
	}

	public static void KeyGen(int p, int q, int e) throws IOException {
		int n = p * q;
		int phiN = (p - 1) * (q - 1);
		
		BigInteger ebi, pbi, d;
		ebi = BigInteger.valueOf(e);
		pbi = BigInteger.valueOf(phiN);
		
		d = ebi.modInverse(pbi);

		PubKeyFileWriter(e, n);
		PriKeyFileWriter(d, n);
	}

	public static void PubKeyFileWriter(int e, int n) throws IOException {
		File file = new File("pub_key.txt");

		String line1 = "e = " + e;
		String line2 = "n = " + n;

		FileWriter writer = new FileWriter(file);
		writer.write(line1);
		writer.write(System.getProperty("line.separator"));
		writer.write(line2);
		writer.close();

	}

	public static void PriKeyFileWriter(BigInteger d, int n) throws IOException {
		File file = new File("pri_key.txt");

		String line1 = "d = " + d;
		String line2 = "n = " + n;

		FileWriter writer = new FileWriter(file);
		writer.write(line1);
		writer.write(System.getProperty("line.separator"));
		writer.write(line2);
		writer.close();
	}

	public static Boolean isPrime(int num) {
		boolean prime = false;
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				prime = true;
				break;
			}
		}
		return prime;
	}

	public static int GCD(int e, int phiN) {
		int gcd = 0;
		for (int i = 1; i < e && i < phiN; i++) {
			if (e % i == 0 && phiN % i == 0) {
				gcd = i;
			}
		}
		return gcd;
	}
}
