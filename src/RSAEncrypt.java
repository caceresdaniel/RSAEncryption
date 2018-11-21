import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {
	static Map<String, String> map = new HashMap<String, String>();
	static BigInteger e;
	static BigInteger n;

	public static void main(String[] args) throws IOException {
		mapInitializer();
		if (args.length == 2) {
			Encrypt(args[0], args[1]);
		} else {
			System.out.println("Incorrect amount of arguments!");
		}
	}

	public static void Encrypt(String ptPath, String keyPath) throws IOException {
		String pt = fileReader(ptPath);
		String key = fileReader(keyPath);
		keySplitter(key);
		String[] blocks = PlainTextConverter(pt);
		String cypherText = "";
		int count = 0;
		for (String a : blocks) {
			int p = Integer.parseInt(a);

			BigInteger c = BigInteger.valueOf(p).modPow(e, n);

			if (count != blocks.length) {
				cypherText = cypherText + String.valueOf(c.intValue()) + " ";
				count++;
			} else {
				cypherText = cypherText + String.valueOf(c.intValue());
			}
		}
		
		System.out.println(cypherText);
		
		fileWriter(cypherText);
	}

	public static String[] PlainTextConverter(String pt) {
		pt = stringCleaner(pt);

		String convertedPT = "";
		int count = 1;

		for (int i = 0; i < pt.length(); i++) {
			if (map.get(String.valueOf(pt.charAt(i))) != null) {
				if (count % 3 == 0) {
					convertedPT = convertedPT + map.get(String.valueOf(pt.charAt(i))) + " ";
					count++;
				} else {
					convertedPT = convertedPT + map.get(String.valueOf(pt.charAt(i)));
					count++;
				}
			}
		}

		String[] blocks = convertedPT.split(" ");
		
		for(String a : blocks) {
			System.out.println(a);
		}

//		while (Integer.parseInt(blocks[blocks.length - 1]) % 6 != 0) {
//			blocks[blocks.length - 1] = blocks[blocks.length - 1] + "26";
//		}

		return blocks;
	}

	public static String stringCleaner(String s) {
		s = s.toUpperCase();
//		s = s.replace(",", "");
//		s = s.replace(".", "");
//		s = s.replace("?", "");
//		s = s.replace("!", "");
//		s = s.replace("@", "");
//		s = s.replace("`", "");
//		s = s.replace("~", "");
//		s = s.replace("#", "");
//		s = s.replace("$", "");
//		s = s.replace("%", "");
		return s;
	}

	public static String fileReader(String File) throws IOException {
		String data = "";

		data = new String(Files.readAllBytes(Paths.get(File)));

		return data;
	}

	public static void fileWriter(String cipherText) throws IOException {
		File file = new File("text.enc");
		FileWriter writer = new FileWriter(file);

		writer.write(cipherText);
		writer.close();
	}

	public static void keySplitter(String data) {
		String[] datastr = data.split(" ");
		e = BigInteger.valueOf(Integer.parseInt(datastr[2]));
		n = BigInteger.valueOf(Integer.parseInt(datastr[5]));
	}

	public static void mapInitializer() {
		map.put("A", "00");
		map.put("B", "01");
		map.put("C", "02");
		map.put("D", "03");
		map.put("E", "04");
		map.put("F", "05");
		map.put("G", "06");
		map.put("H", "07");
		map.put("I", "08");
		map.put("J", "09");
		map.put("K", "10");
		map.put("L", "11");
		map.put("M", "12");
		map.put("N", "13");
		map.put("O", "14");
		map.put("P", "15");
		map.put("Q", "16");
		map.put("R", "17");
		map.put("S", "18");
		map.put("T", "19");
		map.put("U", "20");
		map.put("V", "21");
		map.put("W", "22");
		map.put("X", "23");
		map.put("Y", "24");
		map.put("Z", "25");
		map.put(" ", "26");
	}
}
