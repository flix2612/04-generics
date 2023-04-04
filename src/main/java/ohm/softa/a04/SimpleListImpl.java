package ohm.softa.a04;

import java.lang.annotation.ElementType;
import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl<T> implements SimpleList<T> {

	private ListElement head;
	private int size;

	public SimpleListImpl() {
		head = null;
	}

	/**
	 * Add an object to the end of the list
	 * @param item item to add
	 */
	public void add(T item){
		/* special case empty list */
		if(head == null){
			head = new ListElement(item);
		}else {
			/* any other list length */
			ListElement current = head;
			while (current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(new ListElement(item));
		}
		size++;
	}

	public void delete(T item) {
		ListElement current = head;
		ListElement last = null;

		while(current != null && !current.item.equals(item)) {
			last = current;
			current = current.next;
		}

		if(current != null) {
			if(last == null) {
				head = current.next;
			} else {
				last.next = current.next;
			}
			size--;
		}
	}

	/**
	 * @return size of the list
	 */
	public int size() {
		return size;
	}

	public SimpleList<T> copy() {
		SimpleList<T> copy = new SimpleListImpl<>();
		for(T item : this) {
			copy.add(item);
		}
		return copy;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Iterator<T> iterator() {
		return new SimpleIterator();
	}

	/**
	 * Helper class which implements the Iterator interface
	 * Has to be non static because otherwise it could not access the head of the list
	 */
	private class SimpleIterator implements Iterator<T> {

		private ListElement current = head;

		/**
		 * @inheritDoc
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public T next() {
			T tmp = current.getItem();
			current = current.getNext();
			return tmp;
		}
	}

	/**
	 * Helper class for the linked list
	 * can be static because the ListElement does not need to access the SimpleList instance
	 */
	private class ListElement {
		private final T item;
		private ListElement next;

		ListElement(T item) {
			this.item = item;
			this.next = null;
		}

		/**
		 * @return get object in the element
		 */
		public T getItem() {
			return item;
		}

		/**
		 * @return successor of the ListElement - may be NULL
		 */
		public ListElement getNext() {
			return next;
		}

		/**
		 * Sets the successor of the ListElement
		 * @param next ListElement
		 */
		public void setNext(ListElement next) {
			this.next = next;
		}
	}

}
