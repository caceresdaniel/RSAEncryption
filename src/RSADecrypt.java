import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RSADecrypt {
	static Map<String, String> map = new HashMap<String, String>();
	static BigInteger d;
	static BigInteger n;

	public static void main(String[] args) throws IOException {
		mapInitializer();
		if (args.length == 2) {
			Decrypt(args[0], args[1]);
		} else {
			System.out.println("Incorrect amount of arguments!");
		}
	}

	public static void Decrypt(String ctPath, String keyPath) throws IOException {
		String ct = fileReader(ctPath);
		String key = fileReader(keyPath);
		keySplitter(key);
		Integer[] blocks = cipherSplitter(ct);
		String plainText = "";

		int count = 0;
		for (int x : blocks) {
			BigInteger p = BigInteger.valueOf(x).modPow(d, n);

			if (count != blocks.length) {
				plainText = plainText + String.valueOf(p.intValue()) + " ";
				count++;
			} else {
				plainText = plainText + String.valueOf(p.intValue());
			}
		}

		plainTextFixer(plainText);
	}

	public static void plainTextFixer(String plainText) throws IOException {
		String[] pt = plainText.split(" ");
		String numberFormat = "";
		for (int i = 0; i < pt.length; i++) {
			if (pt[i].length() % 2 != 0) {
				pt[i] = "0" + pt[i];
			}
		}

		for (String a : pt) {
			numberFormat = numberFormat + a;
		}

		numberConverter(numberFormat);
	}

	public static void numberConverter(String pt) throws IOException {
		String plainText = "";
		String[] keys = pt.split("(?<=\\G..)");

		for (String a : keys) {
			plainText = plainText + map.get(a);
		}

		fileWriter(plainText);

	}

	public static Integer[] cipherSplitter(String ct) {
		String[] data = ct.split(" ");
		Integer[] ctToInt = new Integer[data.length];

		for (int i = 0; i < data.length; i++) {
			ctToInt[i] = Integer.valueOf(data[i]);
		}

		return ctToInt;
	}

	public static void fileWriter(String plainText) throws IOException {
		File file = new File("text.dec");
		FileWriter writer = new FileWriter(file);

		writer.write(plainText);
		writer.close();
	}

	public static void keySplitter(String data) {
		String[] datastr = data.split(" ");
		d = BigInteger.valueOf(Integer.parseInt(datastr[2]));
		n = BigInteger.valueOf(Integer.parseInt(datastr[5]));
	}

	public static String fileReader(String File) throws IOException {
		String data = "";

		data = new String(Files.readAllBytes(Paths.get(File)));

		return data;
	}

	public static void mapInitializer() {
		map.put("00", "A");
		map.put("01", "B");
		map.put("02", "C");
		map.put("03", "D");
		map.put("04", "E");
		map.put("05", "F");
		map.put("06", "G");
		map.put("07", "H");
		map.put("08", "I");
		map.put("09", "J");
		map.put("10", "K");
		map.put("11", "L");
		map.put("12", "M");
		map.put("13", "N");
		map.put("14", "O");
		map.put("15", "P");
		map.put("16", "Q");
		map.put("17", "R");
		map.put("18", "S");
		map.put("19", "T");
		map.put("20", "U");
		map.put("21", "V");
		map.put("22", "W");
		map.put("23", "X");
		map.put("24", "Y");
		map.put("25", "Z");
		map.put("26", " ");
	}

}
