package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();

		if (element != null) {
			if (isEmpty())
				head++;

			tail++;

			int j = tail % this.array.length;
			this.array[j] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if (isEmpty())
			throw new QueueUnderflowException();

		T element = this.array[this.head];

		if (this.tail == this.head) {
			this.head = -1;
			this.tail = -1;

		} else {
			this.head = (this.head + 1) % this.array.length;
		}
		this.elements--;
		return element;

	}

	@Override
	public T head() {

		if (isEmpty())
			return null;

		return this.array[head];
	}

	@Override
	public boolean isEmpty() {
		return this.head == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == array.length - 1;
	}

}
