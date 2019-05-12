package test.haha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestFile {
	public static void main (String[] args) {
		Scanner input;
		try {
			input = new Scanner(new File("test.txt"));
			while (input.hasNext()) {
				String word = input.next();
				System.out.println(word);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
