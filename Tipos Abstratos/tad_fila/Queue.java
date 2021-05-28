package tad_fila;

public interface Queue<E> {

	public int size();
	
	public boolean isEmpty();
	
	public E front() throws EmptyQueueException;
	
	public void enqueue(E element);
	
	public E dequeue() throws EmptyQueueException;
	
	@SuppressWarnings("serial")
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String err) {
			super(err); }
	}
	
	@SuppressWarnings("serial")
	public class FullQueueException extends RuntimeException {
		public FullQueueException(String err) {
			super(err); }
	}
}
