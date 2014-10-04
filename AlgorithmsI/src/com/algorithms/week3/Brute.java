package com.algorithms.week3;

import com.algorithms.std.*;

public class Brute {
	public static boolean IsAscendingOrder(Point p, Point q){
		return p.compareTo(q) > 0;
	}
	
	public static void main(String[] args) {
		In inputFile = new In(args[0]);
		int N = inputFile.readInt();
		Point [] points = new Point[N];

		//Scale requirements
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		for (int i = 0; i < N ; i++) {
			points[i] = new Point(inputFile.readInt(), inputFile.readInt());
		}

		//Define our 4 points and 4 slopes
		Point p, q, r, s;
		double slopepq, slopepr, slopeps;
		
		for (int a = 0; a < N; a++) {
			p = points[a];
			p.draw();

			for (int b = 0; b < N; b++) {				
				//skip comparing the same point
				if (b == a){
					continue;
				}

				q = points[b];
				if(!IsAscendingOrder(p, q)){
					continue;
				}	
				
				slopepq = p.slopeTo(q);
				
				for (int c = 0; c < N; c++) {
					//skip comparing the same point
					if (c == b){
						continue;
					}

					r = points[c];
					if(!IsAscendingOrder(q, r)){
						continue;
					}

					//Dont do extra work if we already aren't collinear
					slopepr = p.slopeTo(r);
					if (slopepq != slopepr){
						continue;
					}

					for (int d = 0; d < N; d++) {
						//skip comparing the same point
						if (d == c){
							continue;
						}

						s = points[d];
						if(!IsAscendingOrder(r, s)){
							continue;
						}

						//Check collinear 
						slopeps = p.slopeTo(s);
						if (slopepq != slopeps){
							continue;
						}

						p.drawTo(s);
						StdOut.println(p + " -> " + q + " -> " + r + " -> " + s);
					}
				}
			}
		}
	}
}
