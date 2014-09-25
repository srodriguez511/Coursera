package com.algorithms.week2;

import com.algorithms.std.*;

public class Subset {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		if (k < 0) {
            throw new IllegalArgumentException();
        }
		
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		
		String input = StdIn.readLine();
		String [] strings = input.split(" ");
		for(int i = 0; i < strings.length; i++){
			randomQueue.enqueue(strings[i]);
		}
		
		for(int j = 0; j < k; j++){
			StdOut.println(randomQueue.dequeue());
		}
	}
}
