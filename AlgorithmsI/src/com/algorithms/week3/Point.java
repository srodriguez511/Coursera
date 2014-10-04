package com.algorithms.week3;
import com.algorithms.std.*;

import java.util.Comparator;

public class Point implements Comparable<Point> {
	// compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    private final class BySlope implements Comparator<Point>{
    	public int compare(Point p1, Point p2){
    		double slope1 = slopeTo(p1);
    		double slope2 = slopeTo(p2);
    		
    		return Double.compare(slope1, slope2);
    	}
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	if (that.x == x && that.y == y){
            return Double.NEGATIVE_INFINITY;
    	}
    		
       double top = (that.y - this.y);
       double bot = (that.x-this.x);
       
       if(bot == 0){
    	   return 0;
       }
       
       if(top == 0){
    	   return Double.POSITIVE_INFINITY;
       }
       
       return top / bot;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if(this.y < that.y){
        	return -1;
        }
        else if(this.y > that.y){
        	return 1;
        }
        
        if(this.x < that.x){
        	return -1;
        }
        
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
