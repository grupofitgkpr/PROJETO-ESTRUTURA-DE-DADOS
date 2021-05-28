package interfaces;

public interface CompleteBinaryTree<T> extends BinaryTree<T> {
	// Adiciona um elemento � �rvore ap�s o �ltimo nodo. Retorna a nova posi��o criada.
	public Position<T> add(T elem);
	// Remove e retorna o elemento armazenado no �ltimo nodo da �rvoere.
	public T remove();
}
