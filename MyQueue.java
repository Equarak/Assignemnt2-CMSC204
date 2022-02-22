import java.util.ArrayList;
/**
 * @author Equara Khan - CMSC204
 */
public class MyQueue<T> implements QueueInterface<T> {

	private int front, end;
	private T[] data;

	/**
	 * takes an int as the size of the queue
	 *
	 * @param size the size of the queue
	 */
	public MyQueue(int size) {
		data = (T[]) new Object[size];
		front = -1;
		end = -1;
	}

	/**
	 * default constructor - uses a default as the size of the queue
	 */
	public MyQueue() {
		data = (T[]) new Object[20];
		front = -1;
		end = -1;
	}

	/**
	 * takes an ArrayList as a parameter, and fills the Queue with the
	 *
	 * @param list an ArrayList
	 */
	public MyQueue(ArrayList list) {
		data = (T[]) new Object[list.size()];
		front = 0;
		end = -1;
		for (int i = 0; i < list.size(); i++) {
			data[i] = (T) list.get(i);
			end++;
		}
	}

	/**
	 * Determines if Queue is empty
	 *
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if (front > end)
			return true;
		else
			return false;
	}

	/**
	 * Determines of the Queue is empty
	 *
	 * @return
	 */
	@Override
	public boolean isFull() {

		if (front > end) {
			if (front - 1 == end)
				return true;
			else
				return false;
		}

		if (end - front == data.length - 1)
			return true;
		else
			return false;
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 *
	 * @return the element at the front of the Queue
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		T t;
		if (isEmpty())
			throw new QueueUnderflowException();
		else {
			t = data[front];
			data[front] = null;
			front = (front + 1) % data.length;
		}
		return t;
	}

	/**
	 * Number of elements in the Queue
	 *
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return end - front + 1;
	}

	/**
	 * Adds an element to the end of the Queue
	 *
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		else {
			data[end + 1] = e;
			end = (end + 1) % data.length;
			if (front == -1)
				front = 0;
			return true;
		}
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning
	 * of the string is the front of the queue
	 *
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String string = "";
		for (T t : data) {
			if (t == null)
				return string;
			string += t;
		}
		return string;
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning
	 * of the string is the front of the queue Place the delimiter between all
	 * elements of the Queue
	 *
	 * @return string representation of the Queue with elements separated with the
	 *         delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String string = "";
		for (int i = 0; i < size(); i++) {

			if (i == size() - 1)
				return string + data[(front + i) % size()];

			string += data[(front + i) % size()] + delimiter;

		}
		return string;
	}

	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the
	 * ArrayList is the first element in the Queue YOU MUST MAKE A COPY OF LIST AND
	 * ADD THOSE ELEMENTS TO THE QUEUE, if you use the list reference within your
	 * Queue, you will be allowing direct access to the data of your Queue causing a
	 * possible security breech.
	 *
	 * @param list elements to be added to the Queue
	 */
	@Override
	public void fill(ArrayList<T> list) {

	}
}
