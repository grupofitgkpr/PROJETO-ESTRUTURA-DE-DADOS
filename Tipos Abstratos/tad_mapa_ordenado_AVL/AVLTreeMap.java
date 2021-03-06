package tad_mapa_ordenado_AVL;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import interfaces.Entry;
import interfaces.Map;
import interfaces.Position;
import exceptions.InvalidKeyException;
import tad_arvore_binaria.BTNode;
import tad_arvore_binaria.BTPosition;
import tad_mapa_ordenado_ABB.BinarySearchTree;

// Implementa??o da ?rvore AVL
public class AVLTreeMap<K, V> extends BinarySearchTree<K, V> implements Map<K, V> {
	public AVLTreeMap(Comparator<K> c) { super(c); }
	public AVLTreeMap() { super(); }
	
	public static void interface_Mapa_AVL() { // Interface - Projeto Estrutura de Dados
		AVLTreeMap<Integer, String> mapaAVL = new AVLTreeMap<Integer, String>(); // Inicializa a estrutura
		boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner

        while (!exit) { // Loop de Interface
			System.out.print("\n --- Interface de Usu?rio ---:\n"
					+ "[0] Voltar para o Menu (Estrutura atual ser? limpa)\n"
					+ "[1] Adicionar\n"
					+ "[2] Remover\n"
					+ "[3] Visualizar\n"
					+ "Digite a op??o: ");
			String opc = input.nextLine();
			switch(opc) {
				case "0": // VOLTAR
					exit = true;
					break;

				case "1": // ADICIONAR
					System.out.print("\nDigite o valor da chave que ser? adicionada (Valor inteiro): ");
					Integer key = null;
					
					// Verifica se o valor da chave digitada ? um Inteiro, se n?o for lan?a mensagem de erro
					try {
						key = input.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\n****Valor de tipo inv?lido****");
						input.nextLine();
						break;
					}

					input.nextLine();
					System.out.print("Digite a String que ser? armazenada na chave: ");
					String value = input.nextLine();
					
					// Adiciona o par de chave e valor no Mapa Ordenado AVL
					mapaAVL.put(key, value);
					System.out.println("\nChave " + key + " com valor '" + value + "' foi adicionada.");
					break;

				case "2": // REMOVER
					System.out.print("\nDigite o valor da chave que ser? removida (Valor inteiro): ");
					Integer keyRemover = null;
					
					// Verifica se o valor da chave digitada ? um Inteiro, se n?o for lan?a mensagem de erro
					try {
						keyRemover = input.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\n****Valor de tipo inv?lido****");
						input.nextLine();
						break;
					}
					
					// Verifica se a chave existe no Mapa Ordenado AVL
					boolean verifica = false;
					for (int keyVerifica : mapaAVL.keySet())
						if (keyVerifica == keyRemover) {
							verifica = true;
							break;
						}
					
					// Se existir remove do Mapa Ordenado AVL e se n?o existe, lan?a mensagem de erro
					if (verifica) {
						mapaAVL.remove(keyRemover);
						System.out.println("\nO elemento '" + keyRemover + "' foi removido da ?rvore");
					} else
						System.out.println("\n****Chave '" + keyRemover + "' n?o existe na ?rvore****");
					input.nextLine();
					break;

				case "3":
					// Printa na tela todas as chaves, strings associadas as chaves e o Mapa Ordenado AVL
					System.out.println("\nConjunto de  chaves atual: " + mapaAVL.keySet().toString());
					System.out.println("Conjunto de strings atual: " + mapaAVL.values().toString());
					System.out.println("?rvore Bin?ria atual: " + mapaAVL.printExpression(mapaAVL.root()));
					break;

				default:
					System.out.println("\n****Op??o inv?lida****");
			}
		}
	}
	
	// Classe aninhada para os nodos da ?rvore AVL
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
		// assume que o pai ir? revisar a altura se necess?rio
		public void setHeight(int h) { height = h; }
		public int getHeight() { return height; }
	}
	// Cria um novo nodo da ?rvore bin?ria de busca (sobrescreve a vers?o herdada)
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
		// alturas dos filhos s?o iguais - retorna o filho conforme o tipo do seu pai
		if (isRoot(p)) return left(p); // Se p for raiz, retorna o filho esquerdo.
		if (p == left(parent(p))) return left(p); // se p ? filho esquerdo , retorna filho esquerdo de p.
		else return right(p); // caso contr?rio, o filho direito.
	}
	// Chamado para inserir e remover. Percorre de zPos at? a raiz.
	// Para cada nodo encontrado, recomputamos a sua altura e
	// executamos a reestrutura??o trinode se desbalanceado
	protected void rebalance(Position<Entry<K, V>> zPos) {
		if (isInternal(zPos)) setHeight(zPos);
		while (!isRoot(zPos)) { // percorre a ?rvore em dire??o ? raizt
			zPos = parent(zPos);
			setHeight(zPos);
			if (!isBalanced(zPos)) {
				// realiza a reestrutura??o trinode no filho mais alto de zPos
				Position<Entry<K, V>> xPos = tallerChild(tallerChild(zPos));
				zPos = restructure(xPos); // reestrutura (a partir da classe pai
				// recomputa alturas
				setHeight(left(zPos));
				setHeight(right(zPos));
				setHeight(zPos);
			}
		}
	}
	// Sobrescreve os m?todos do TAD Mapa
	// Insere um item no mapa e retorna numa nova entrada criada
	public V put(K k, V v) throws InvalidKeyException {
		V toReturn = super.put(k, v); // Chama nosso m?todo createNode se k ? novo
		rebalance(actionPos); // rebalanceia a partir do ponto de inser??o
		return toReturn;
	}
	// Remove e returna uma entrada do mapa
	public V remove(K k) throws InvalidKeyException {
	V toReturn = super.remove(k);
	if (toReturn != null) // n?s realmente removemos algo
	rebalance(actionPos); // rebalanceia a ?rvore
	return toReturn;
	}
}