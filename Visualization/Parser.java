/*
 * Author: Shixuan Fan and Yuting Liu
 * class for read the file of data of 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	private int rows, num_disc = 0, num_seek_finds = 0;
	private int[][] array;
	public Parser() {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("../Data/output.txt"));
			String line = null;
			int count = 0;
			while((line = br.readLine()) != null) {
				count++;
				
				if(count == 2) {
					rows = Integer.parseInt(line);
				} else if(count == 4) {
					num_disc = Integer.parseInt(line);
				} else if(count == 6) {
					num_seek_finds = Integer.parseInt(line);
					array = new int[rows][num_disc + num_seek_finds];
				} else if(count > 6){
					String[] words = line.split(" ");
					for(int i = 2; i < words.length; ++i) {
						array[count - 7][i - 2] = Integer.parseInt(words[i]);
					}
				}
				
			}
		} catch (IOException e) {
			System.err.println("File not found!");
			System.exit(1);
		}
	}
	
	public int get_rows() {
		return rows;
	}
	
	public int get_discussion_num() {
		return num_disc;
	}
	
	public int get_seek_finds_num() {
		return num_seek_finds;
	}
	
	public int[][] get_array() {
		return array;
	}
}