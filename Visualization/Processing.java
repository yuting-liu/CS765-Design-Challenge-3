/*
 * Author: Shixuan Fan, Yuting Liu
 * Main class for user visualization
 */

import processing.core.PApplet;
import java.io.*;

public class Processing extends PApplet {
	static Cell[][] grid;
	static int cols, rows;
	public static void main(String[] args) {
		Parser parser = new Parser();
		rows = parser.get_rows();
		cols = parser.get_discussion_num() + parser.get_seek_finds_num();
		System.out.println(rows);
		System.out.println(cols);
		PApplet.main("Processing");
	}
	public void settings() {
		//size(500, 500);
		//surface.setResizable(true);
		// fullScreen();
		size(480, 240);
		
	}
	public void setup() {
		grid = new Cell[rows][cols];
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				grid[i][j] = new Cell(i * 20, j * 20, 20, 20);
			}
		}
		
	}
	public void draw() {
		background(0);
		  // The counter variables i and j are also the column and row numbers and 
		  // are used as arguments to the constructor for each object in the grid.  
		  for (int i = 0; i < rows; i++) {
		    for (int j = 0; j < cols; j++) {
		      // Oscillate and display each object
		      grid[i][j].display();
		    }
		  }
	}
	
	public void read() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("../Data/output.txt"));
		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}
	}
	
	class Cell {
		float x, y, w, h;
	
		// Constructor
		public Cell(float x, float y, float w, float h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
	
		void display() {
			stroke(255);
			fill(127+x);
			rect(x,y,w,h);
		}
	}
	
}

