/*
 * Author: Shixuan Fan, Yuting Liu
 * Class for assignment grading statistics
 */

import java.util.ArrayList;
import java.lang.StringBuffer;

public class Assignment {
	
	// discussion or seek and find
	private String type = "";
	
	// grade of this assignment
	private int grade, time;
	
	ArrayList<Integer> lengths, link_images;
		
	/* Constructor */
	public Assignment(String type, int grade, int time) {
		this.type = type;
		this.grade = grade;
		this.time = time;
		lengths = new ArrayList<>();
		link_images = new ArrayList<>();
	}
	
	// add the pair of post lengths and number of link/image pair
	public void add_pair(int length, int link_image) {
		lengths.add(length);
		link_images.add(link_image);
	}
	
	public int get_grade() {
		return grade;
	}
	
	public int get_time() {
		return time;
	}
	
	public ArrayList<Integer> get_lengths() {
		return lengths;
	}
	
	public ArrayList<Integer> get_link_images() {
		return link_images;
	}
	
}