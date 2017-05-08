/*
 * Author: Shixuan Fan, Yuting Liu
 * Main class for user visualization
 */

import processing.core.PApplet;

public class Processing extends PApplet {
	Cell[][] grid;
	public static void main(String[] args) {
		PApplet.main("Processing");
	}
	public void setting() {
		size(300, 300);
	}
	public void setup() {
		grid = new Cell[10][10];
		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				grid[i][j] = new Cell(i * 20, j * 20, 20, 20);
			}
		}
	}
	public void draw() {
		background(0);
		  // The counter variables i and j are also the column and row numbers and 
		  // are used as arguments to the constructor for each object in the grid.  
		  for (int i = 0; i < 10; i++) {
		    for (int j = 0; j < 10; j++) {
		      // Oscillate and display each object
		      grid[i][j].display();
		    }
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
			fill(127+127);
			rect(x,y,w,h);
		}
	}
	
}

