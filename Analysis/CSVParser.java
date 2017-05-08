/*
 * Parser for CSV file
 *
 * Author: Shixuan Fan, Yuting Liu
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CSVParser {
	
	// attribute list
	ArrayList<String> attributes = new ArrayList<>();
	
	// info stored in HashMaps
	HashMap<Integer, String> firstnames = new HashMap<>();
	HashMap<Integer, String> lastnames = new HashMap<>();
	// Discussion information
	HashMap<Integer, List<Assignment>> discussions = new HashMap<>();
	HashMap<Integer, List<Assignment>> seekfinds = new HashMap<>();
	
	private int num_disc = 0, num_sf = 0;
	
	/* Constructor */
	public CSVParser(BufferedReader buff) {
		String line = null;
		int count = 0;
		
		try {
			// read the file line by line
			while((line = buff.readLine()) != null) {
				// comma separated
				// ignore commas between quotes
				String[] words = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
				// first line: attribute names
				if(count == 0) {
					attributes.addAll(Arrays.asList(words));
					for(String word : words) {
						if(word.startsWith("Disc")) {
							num_disc++;
						} else if(word.startsWith("Seek")) {
							num_sf++;
						}
					}
				} 
				// other lines: tuples
				else {
					// name
					String subword = words[0].substring(1, words[0].length() - 1);
					String[] names = subword.split(",");
					String firstName = names[1].substring(1);
					String lastName = names[0];
					
					// id
					int id = Integer.parseInt(words[1]);
					
					// add all information into maps
					firstnames.put(id, firstName);
					lastnames.put(id, lastName);
					discussions.put(id, new ArrayList<Assignment>());
					seekfinds.put(id, new ArrayList<Assignment>());
					
					// discussions and seek and finds
					for(int i = 2; i < words.length; ++i) {
						// Discussion assignment
						String type = attributes.get(i).startsWith("Disc") 
									? "discussion" : "seekfind";
						
						// split string with commas
						// ignore commas between []
						subword = words[i].substring(2, words[i].length() - 2);
						String[] numbers 
							= subword.split(",(?=(?:[^\\[\\]]*\\[[^\\[\\]]*\\])*[^\\[\\]]*$)", -1);
						// trim the spaces
						
						int grade = Integer.parseInt(numbers[0].trim());
						int time = Integer.parseInt(numbers[1].trim());
						
						// different posts are wrapped in pairs
						// split by commas
						// ignore commas between ()
						String trim_number = numbers[2].trim();
						trim_number = trim_number.substring(1, trim_number.length() - 1);
						String[] pairs = trim_number.split(",(?=(?:[^\\(\\)]*\\([^\\(\\)]*\\))*[^\\(\\)]*$)", -1);
						
						Assignment assignment = new Assignment(type, grade, time);
						
						// process every pair
						// add into the assignment 
						for(String pair : pairs) {
							pair = pair.trim();
							
							// empty
							if(pair.length() == 0) {
								continue;
							}
							
							// split the pair 
							String[] stats = pair.split(",");
							
							// trim spaces and remove '('
							String trim_stat0 = stats[0].trim();
							int length = Integer.parseInt(trim_stat0.substring(1, trim_stat0.length()));
							
							// trim spaces and remobe ')'
							String trim_stat1 = stats[1].trim();
							int link_image = Integer.parseInt(trim_stat1.substring(0, trim_stat1.length() - 1));
							
							assignment.add_pair(length, link_image);
						}
						
						if(type.equals("discussion")) {
							discussions.get(id).add(assignment);
						} else {
							seekfinds.get(id).add(assignment);
						}
					}
				}
				
				count++;
			}
		} catch (IOException e) {
			System.err.println("File reader fails!");
			System.exit(1);
		}
	}
	
	public int get_discussion_num() {
		return num_disc;
	}
	
	public int get_seekandfind_num() {
		return num_sf;
	}
	
	public void print() {
		for(int id : discussions.keySet()) {
			List<Assignment> list = discussions.get(id);
			for(int i = 0; i < list.size(); ++i) {
				System.out.println(list.get(i).get_link_images().toString());
			}
		}
		
	}
}
