package tad_mapa_ordenado_AVL;

import java.util.Comparator;
import interfaces.Entry;
import interfaces.Map;
import interfaces.Position;
import exceptions.InvalidKeyException;
import tad_arvore_binaria.BTNode;
import tad_arvore_binaria.BTPosition;
import tad_mapa_ordenado_ABB.BinarySearchTree;

// Implementa��o da �rvore AVL
public class AVLTreeMap<K, V> extends BinarySearchTree<K, V> implements Map<K, V> {
	public AVLTreeMap(Comparator<K> c) { super(c); }
	public AVLTreeMap() { super(); }
	// Classe aninhada para os nodos da �rvore AVL
	protected static class AVLNode<K, V> extends BTNode<Entry<K, V>> {
		protected int height; // campo altura para BTNode
		// Construtor preferido
		@SuppressWarnings("unchecked")
		AVLNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent, BTPosition<Entry<K, V>> left, BTPosition<Entry<K, V>> right) {
			super(element, parent, left, right);
			height = 0;
			if (left != null) height = Math.max(height, 1 + ((AVLNode<K, V>) left).getHeight());
			if (right != null) height = Math.max(height, 1 + ((AVLNode<K, V>) right).getHeight());
		}
		// assume que o pai ir� revisar a altura se necess�rio
		public void setHeight(int h) { height = h; }
		public int getHeight() { return height; }
	}
	// Cria um novo nodo da �rvore bin�ria de busca (sobrescreve a vers�o herdada)
	protected BTPosition<Entry<K, V>> createNode(Entry<K, V> element, BTPosition<Entry<K, V>> parent,
		BTPosition<Entry<K, V>> left, BTPosition<Entry<K, V>> right) {
		return new AVLNode<K, V>(element, parent, left, right); // agora usa nodos AVL
	}
	// Retorna a altura de um nodo
	@SuppressWarnings("unchecked")
	protected int height(Position<Entry<K, V>> p) { return ((AVLNode<K, V>) p).getHeight(); }
	// Define a altura de um nodo interno
	@SuppressWarnings("unchecked")
	protected void setHeight(Position<Entry<K, V>> p) {
		((AVLNode<K, V>) p).setHeight(1 + Math.max(height(left(p)), height(right(p))));
	}
	// Retorna se um nodo tem o fator de balanceamento entre -1 e 1.
	protected boolean isBalanced(Position<Entry<K, V>> p) {
		int bf = height(left(p)) - height(right(p));
		return ((-1 <= bf) && (bf <= 1));
	}
	// Retorna um filho de p com a altura maior que o outro filho
	protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (height(left(p)) > height(right(p))) return left(p);
		else if (height(left(p)) < height(right(p))) return right(p);
		// alturas dos filhos s�o iguais - retorna o filho conforme o tipo do seu pai
		if (isRoot(p)) return left(p); // Se p for raiz, retorna o filho esquerdo.
		if (p == left(parent(p))) return left(p); // se p � filho esquerdo , retorna filho esquerdo de p.
		else return right(p); // caso contr�rio, o filho direito.
	}
	// Chamado para inserir e remover. Percorre de zPos at� a raiz.
	// Para cada nodo encontrado, recomputamos a sua altura e
	// executamos a reestrutura��o trinode se desbalanceado
	protected void rebalance(Position<Entry<K, V>> zPos) {
		if (isInternal(zPos)) setHeight(zPos);
		while (!isRoot(zPos)) { // percorre a �rvore em dire��o � raizt
			zPos = parent(zPos);
			setHeight(zPos);
			if (!isBalanced(zPos)) {
				// realiza a reestrutura��o trinode no filho mais alto de zPos
				Position<Entry<K, V>> xPos = tallerChild(tallerChild(zPos));
				zPos = restructure(xPos); // reestrutura (a partir da classe pai
				// recomputa alturas
				setHeight(left(zPos));
				setHeight(right(zPos));
				setHeight(zPos);
			}
		}
	}
	// Sobrescreve os m�todos do TAD Mapa
	// Insere um item no mapa e retorna numa nova entrada criada
	public V put(K k, V v) throws InvalidKeyException {
		V toReturn = super.put(k, v); // Chama nosso m�todo createNode se k � novo
		rebalance(actionPos); // rebalanceia a partir do ponto de inser��o
		return toReturn;
	}
	// Remove e returna uma entrada do mapa
	public V remove(K k) throws InvalidKeyException {
	V toReturn = super.remove(k);
	if (toReturn != null) // n�s realmente removemos algo
	rebalance(actionPos); // rebalanceia a �rvore
	return toReturn;
	}
}