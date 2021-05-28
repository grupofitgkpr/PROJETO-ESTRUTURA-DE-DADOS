package interfaces;

public interface CompleteBinaryTree<T> extends BinaryTree<T> {
	// Adiciona um elemento à árvore após o último nodo. Retorna a nova posição criada.
	public Position<T> add(T elem);
	// Remove e retorna o elemento armazenado no último nodo da árvoere.
	public T remove();
}
