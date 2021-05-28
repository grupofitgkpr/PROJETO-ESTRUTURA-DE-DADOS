package tad_fila;

public class NodeQueue<E> implements Queue<E>{
	protected Node<E> head; // refer�ncia para o nodo cabe�a
	protected Node<E> tail;
	protected int size; // quantidade de elementos na pilha
	
	public NodeQueue() { // constr�i uma pilha vazia
		head = tail = null;
		size = 0;
	}

	public int size() {return size; }

	public boolean isEmpty() {
		if (head == null) return true;
		return false;
	}

	public void enqueue(E elem) {
		Node<E> node = new Node<E>();

		node.setElement(elem);
		node.setNext(null); // nodo ser� o novo nodo do final
		if (size == 0)
			head = node; // caso especial de uma lista previamente vazia
		else
			tail.setNext(node); // adiciona nodo no final da lista
		tail = node; // atualiza refer�ncia ao nodo do final
		size++;
	}
	
	public E front() throws EmptyQueueException {
		if (isEmpty()) throw new EmptyQueueException("Stack is empty.");
		return head.getElement();
	}

	public E dequeue() throws EmptyQueueException {
		if (size == 0) throw new EmptyQueueException("Queue is empty.");
		E tmp = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0)
			tail = null;// a fila est� vazia agora
		return tmp;
	}
	
	public String toString() {
		String s;
		Node<E> p = head;
	
		s = "[";
		while (p != null) {
			s += p.getElement();
			s += ", ";
			p = p.getNext();
		}
		if (s.length() > 1)
			s = s.substring(0, s.length() - 2);
		return s + "]";
	}

}
