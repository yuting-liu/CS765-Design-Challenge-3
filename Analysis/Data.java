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

import java.util.List;

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
		CSVParser parser = null;
		
		try {
			br = new BufferedReader(new FileReader(file_path));
			parser = new CSVParser(br);
		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}
		
		
		// write the result
		String output_path = "./Data/output.txt";
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
	
	public static String getContent(CSVParser parser) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number of students:\n").append(parser.firstnames.size()).append("\n");
		
		sb.append("Number of discussions:\n").append(parser.get_discussion_num()).append("\n");
		
		sb.append("Number of seek-and-finds:\n").append(parser.get_seekandfind_num()).append("\n");
		
		for(int id : parser.firstnames.keySet()) {
			sb.append(parser.firstnames.get(id));
			sb.append(" ").append(parser.lastnames.get(id));
			List<Assignment> list_disc = parser.discussions.get(id);
			for(Assignment a : list_disc) {
				sb.append(" ").append(a.get_grade());
			}
			List<Assignment> list_sf = parser.seekfinds.get(id);
			for(Assignment a : list_sf) {
				sb.append(" ").append(a.get_grade());
			}
			sb.append("\n");
		}
		
		return sb.toString();
		
	}
}
