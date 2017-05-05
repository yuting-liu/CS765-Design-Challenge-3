/*
 * Author: Shixuan Fan, Yuting Liu
 * Design Challenge 3 for Course CS765
 * Data analysis methods for course grade
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DC3 {
	public static void main(String[] args) {
		
		// argument error
		if(args.length != 1) {
			System.err.println("Error usage: Java DC3 <file_path>");
			System.exit(1);
		}
		
		// read in file
		String file_path = args[0];
		BufferedReader buff = null;
		
		// Parser
		Parser parser = null;
		
		try {
			buff = new BufferedReader(new FileReader(file_path));
			parser = new Parser(buff);
		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}
	}
}