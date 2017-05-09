/*
 * Author: Shixuan Fan and Yuting Liu
 * class for read the file of data of 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MetadataParser {
	private int rows, num_disc = 0, num_seek_finds = 0;
	private int[][] array;
	private String[] firstnames;
	private String[] lastnames;
	private final int HEADER_ROWS = 8;
	private String filename = "";
	public MetadataParser() {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("Data/output.txt"));
			String line = null;
			int count = 0;
			while((line = br.readLine()) != null) {
				
				if(count == 1) {
					filename = line;
				} else if(count == 3) {
					rows = Integer.parseInt(line);
				} else if(count == 5) {
					num_disc = Integer.parseInt(line);
				} else if(count == 7) {
					num_seek_finds = Integer.parseInt(line);
					array = new int[rows][num_disc + num_seek_finds];
					firstnames = new String[rows];
					lastnames = new String[rows];
				} else if(count >= HEADER_ROWS){
					String[] words = line.split(" ");
					firstnames[count - HEADER_ROWS] = words[0];
					lastnames[count - HEADER_ROWS] = words[1];
					for(int i = 2; i < words.length; ++i) {
						array[count - HEADER_ROWS][i - 2] = Integer.parseInt(words[i]);
					}
				}

				count++;
				
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

	public String[] get_firstnames() {
		return firstnames;
	}

	public String[] get_lastnames() {
		return lastnames;
	}
	
	public int[][] get_array() {
		return array;
	}
	
	public String get_filename() {
		return filename;
	}

}
