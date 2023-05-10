package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	private int size;
	private int capacity;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
		this.size = size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try{
			stack1.push(element);
		}catch(StackOverflowException e){
			throw new QueueOverflowException();

		}


	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		try {
			transfereElementoPilha(stack1, stack2);
			T aux = stack2.pop();
			transfereElementoPilha(stack2, stack1);
			
			return aux;
		
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}

	}

	

	public void transfereElementoPilha(Stack<T> stack1, Stack<T> stack2) {
		try {
			while (!this.stack1.isEmpty()) {
				stack2.push(stack1.pop());

			}
		} catch (StackOverflowException e) {
		} catch (StackUnderflowException e) {
		}
	}

	@Override
	public T head() {
		transfereElementoPilha(stack1, stack2);
		T aux = stack2.top();
		transfereElementoPilha(stack2, stack1);
		return aux;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
