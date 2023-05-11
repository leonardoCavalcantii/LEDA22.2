package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {
	
	protected SingleLinkedListNode<T> head;
	
	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}
	
	@Override
	public boolean isEmpty() {
		return head == null;
	}
	
	@Override
	public int size() {
		int aux = 0;
		while(head.isNIL()){
			aux ++;
			head.getNext();
		}
		return aux;
		
	}
	
	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = this.head;
		while(!auxHead.isNIL() && auxHead.data!=element){
			auxHead = auxHead.next;
			
		}
		return auxHead.getData();
		
	}
	
	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = this.head;
		
		if(head.isNIL()){
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			this.head = newHead;
			
		}else{
			
			while(!auxHead.isNIL()){
				auxHead = auxHead.next;
			}
			auxHead.data = element;
			auxHead.next = new SingleLinkedListNode<>(element, head);
			head = auxHead;
			
		}
		
	}
	
	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> aux;
		
		if(!isEmpty()){
			
			if(head.data.equals(element)){
				head = head.next;
			}
		}else{
			aux = head;
			
			while(aux.next.data != element || aux.next.isNIL()){
				aux = aux.next;
			}
			if(aux.next.data.equals(element))
				aux.next = aux.next.next;
			
		}
		
		
		
	}
	
	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}
	
	public SingleLinkedListNode<T> getHead() {
		return head;
	}
	
	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	
}
