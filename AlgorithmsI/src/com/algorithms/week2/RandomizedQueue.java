package com.algorithms.week2;

import com.algorithms.std.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] _array; // array of items
	private int _size; // number of elements on stack
	private int _lastItemIndex; // Index of the last actual filled item

	// construct an empty randomized queue
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		_array = (Item[]) new Object[2];
		_size = 0;
		_lastItemIndex = 0;
	}

	// resize the underlying array holding the elements
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		_lastItemIndex = 0;
		Item[] temp = (Item[]) new Object[capacity];

		for (int i = 0; i < _array.length; i++) {
			// don't copy null spaces into the new resized array
			// this is effectively a push items to front
			if (_array[i] == null) {
				continue;
			}

			temp[_lastItemIndex++] = _array[i];
		}

		_array = temp;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return _size == 0;
	}

	// return the number of items on the queue
	public int size() {
		return _size;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (_lastItemIndex == _array.length) {
			resize(2 * _array.length); // double size of array if necessary
		}

		_array[_size++] = item; // add item
	}

	// delete and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int randomIndex = StdRandom.uniform(_lastItemIndex);
		Item item = _array[randomIndex];

		while (item == null) {
			randomIndex = StdRandom.uniform(_lastItemIndex);
			item = _array[randomIndex];
		}

		_array[randomIndex] = null;

		if (_size > 0 && _size == _array.length / 4) {
			resize(_array.length / 2);
		}

		return item;
	}

	// return (but do not delete) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int randomIndex = StdRandom.uniform(_lastItemIndex);
		Item item = _array[randomIndex];

		while (item == null) {
			randomIndex = StdRandom.uniform(_lastItemIndex);
			item = _array[randomIndex];
		}

		return item;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class RandomQueueIterator implements Iterator<Item> {
		private int _iterSize;
		private Item[] _iterArray; 

		@SuppressWarnings("unchecked")
		public RandomQueueIterator() {
			_iterSize = 0;
			_iterArray = (Item[]) new Object[_size];			
			int j = 0;

			for (int i = 0; i < _array.length; i++) {

				if (_array[i] == null){
					continue;
				}

				_iterArray[j] = _array[i];

				int r = StdRandom.uniform(j + 1);

				Item tmp = _iterArray[j];
				_iterArray[j] = _iterArray[r];
				_iterArray[r] = tmp;

				j++;
			}
		}

		public boolean hasNext() {
			return _iterSize < _iterArray.length;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
                
            return _iterArray[_iterSize++];
		}
	}

	// unit testing
	public static void main(String[] args) {

	}
}
