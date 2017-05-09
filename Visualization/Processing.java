/*
 * Author: Shixuan Fan, Yuting Liu
 * Main class for user visualization
 */

import processing.core.PApplet;
import java.io.*;
import java.util.*;

public class Processing extends PApplet {
	static Cell[][] grid;
	static int cols, num_disc, num_sf, rows;
	static int[][] array;
	static String[] firstnames, lastnames;
	static int NAME_WIDTH = 100, CELL_WIDTH = 20, LABEL_HEIGHT = 20;
	static int TEXT_MARGIN_WIDTH = 5, TEXT_MARGIN_HEIGHT = 10;
	public static void main(String[] args) {
		MetadataParser parser = new MetadataParser();
		rows = parser.get_rows();
		num_disc = parser.get_discussion_num();
		num_sf = parser.get_seek_finds_num();
		cols = num_disc + num_sf;
		array = parser.get_array();
		firstnames = parser.get_firstnames();
		lastnames = parser.get_lastnames();
		PApplet.main("Processing");
	}
	public void settings() {
		//size(500, 500);
		// surface.setResizable(true);
		// fullScreen();
		size(CELL_WIDTH * cols + NAME_WIDTH, CELL_WIDTH * rows + LABEL_HEIGHT);
		
	}
	public void setup() {
		grid = new Cell[rows][cols];
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				grid[i][j] = new Cell(i * CELL_WIDTH, 
					j * CELL_WIDTH + NAME_WIDTH, CELL_WIDTH, CELL_WIDTH, array[i][j]);
			}
		}
		
	}
	public void draw() {
		frame.setTitle("Grade Heatmap");
		background(255);

		// Print students' names.
		for (int i = 0; i < cols; i++) {
			text(firstnames[i] + " " + lastnames[i],
					 0,
					 CELL_WIDTH * i + TEXT_MARGIN_HEIGHT);
		}

	  // The counter variables i and j are also the column and row numbers and 
	  // are used as arguments to the constructor for each object in the grid.  
	  for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
	      grid[i][j].display();
	    }

			textSize(8);
			fill(0);
			if(i < num_disc) {
				text("D" + Integer.toString(i + 1),
					i * CELL_WIDTH + TEXT_MARGIN_WIDTH + NAME_WIDTH, 
					CELL_WIDTH * rows + TEXT_MARGIN_HEIGHT);
			} else {
				text("A" + Integer.toString(i - num_disc + 1), 
					i * CELL_WIDTH + TEXT_MARGIN_WIDTH + NAME_WIDTH,
					CELL_WIDTH * rows + TEXT_MARGIN_HEIGHT);
			}
	  }
	}
	
	public void read() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Data/output.txt"));
		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}
	}
	
	class Cell {
		float x, y, w, h;
		int grade;
	
		// Constructor
		public Cell(float x, float y, float w, float h, int grade) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.grade = grade;
		}
	
		void display() {
			stroke(255);
			if(grade >= 60) {
				fill(0, 255, 0);
			} else if(grade >= 50) {
				fill(204, 255, 221);
			} else if(grade >= 40) {
				fill(102, 255, 153);
			} else if(grade >= 30) {
				fill(255, 204, 204);
			} else if(grade >= 20) {
				fill(255, 102, 102);
			} else if(grade >= 10) {
				fill(255, 26, 26);
			} else if(grade >= 5) {
				fill(230, 0, 0);
			} else if(grade >= 0) {
				fill(204, 0, 0);
			}
			rect(y,x,w,h);
		}
	}
	
}

