//package com.algorithms.week2;
//import com.algorithms.std.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int _size; // size of the deque
	private Node _head; // top of deque
	private Node _tail; // end of deque

	// helper linked list class
	private class Node {
		private Item _item;
		private Node _next;
		private Node _prev;
	}

	// construct an empty deque
	public Deque() {
		_size = 0;
		_head = null;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return _size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return _size;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		Node previousHead = _head;
		_head = new Node();
		_head._item = item;
		_head._next = previousHead;

		if (isEmpty()) {
			_tail = _head;
		} else {
			previousHead._prev = _head;
		}

		_size++;
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		Node oldTail = _tail;
		_tail = new Node();
		_tail._item = item;
		_tail._prev = oldTail;

		if (isEmpty()) {
			_head = _tail;
		} else {
			oldTail._next = _tail;
		}

		_size++;
	}

	// delete and return the item at the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}

		Item item = _head._item;
		_head = _head._next;
		_size--;
		
		if (isEmpty()) {
			_tail = null;
		} else {
			_head._prev = null;
		}
		
		return item;
	}

	// delete and return the item at the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}

		Item item = _tail._item;
		_tail = _tail._prev;
		_size--;

		if (isEmpty()) {
			_head = null;
		} else {
			_tail._next = null;
		}
		
		return item;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeueIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class DequeueIterator implements Iterator<Item> {
		private Node _current;

		public DequeueIterator() {
			_current = _head;
		}

		public boolean hasNext() {
			return _current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			Item item = _current._item;
			_current = _current._next;
			return item;
		}
	}

	// unit testing
	public static void main(String[] args) {
		Deque<Integer> first = new Deque<Integer>();
		StdOut.println(first.isEmpty());
		StdOut.println(first.size());

		first.addFirst(5);
		StdOut.println(first.isEmpty());
		StdOut.println(first.size());

		first.addFirst(6);
		StdOut.println(first.isEmpty());
		StdOut.println(first.size());

		first.addFirst(7);
		StdOut.println(first.isEmpty());
		StdOut.println(first.size());

		first.addFirst(8);
		StdOut.println(first.isEmpty());
		StdOut.println(first.size());

		Iterator<Integer> itr = first.iterator();
		while (itr.hasNext()) {
			int element = itr.next();
			StdOut.print(element + ", ");
		}

		first.removeLast();
		StdOut.println(first.isEmpty());
		StdOut.println(first.size());

	}

}