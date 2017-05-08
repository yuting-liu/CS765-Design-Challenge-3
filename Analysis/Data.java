/*
 * Author: Shixuan Fan, Yuting Liu
 * Design Challenge 3 for Course CS765
 * Data analysis methods for course grade
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Data {
	public static void main(String[] args) {
		// argument error
		if(args.length != 1) {
			System.err.println("Error usage: Java Data <file_path>");
			System.exit(1);
		}
		
		// read in file
		String file_path = args[0];
		BufferedReader br = null;
		
		// Parser
		Parser parser = null;
		
		try {
			br = new BufferedReader(new FileReader(file_path));
			parser = new Parser(br);
		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}
		
		
		// write the result
		String output_path = "../Data/output.txt";
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(output_path));
			bw.write(getContent(parser));
			
			bw.close();
			
		} catch (IOException e) {
			System.err.println("Output file not found!");
			e.printStackTrace();
		}
		
	}
	
	public Stirng getContent(Parser parser) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number of students:\n").append(parser.firstnames.size()).append("\n");
		
		sb.append("Number of discussions:\n").append()
		
	}
}
