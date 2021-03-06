package tad_arvore_gen?rica;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;
import exceptions.InvalidPositionException;
import exceptions.NonEmptyTreeException;
import tad_lista_de_nodos.NodePositionList;
import interfaces.Position;
import interfaces.PositionList;
import interfaces.Tree;

//Um classe para a ?rvore ligada onde os nodos t?m um n?mero arbitr?rio de filhos.
public class LinkedTree<E> implements Tree<E> {
	protected TreePosition<E> root; // Refer?ncia para a ra?z
	protected int size; // N?mero de Nodos
	
	// Cria uma ?rvore vazia
	public LinkedTree() {
		root = null; // Inicia uma ?rvore vazia
		size = 0;
	}
	
	// Retorna um n?mero de nodos da ?rvore
	public int size() {
		return size;
	}
	
	public static void interface_Arvore_Generica() { // Interface - Projeto Estrutura de Dados
		LinkedTree<String> arvoreGenerica = new LinkedTree<String>(); // Inicializa a estrutura
		boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner
		
		while (!exit) { // Loop de Interface
			System.out.print("\n --- Interface de Usu?rio ---:\n"
					+ "[0] Voltar para o Menu (Estrutura atual ser? limpa)\n"
					+ "[1] Adicionar\n"
					+ "[2] Visualizar\n"
					+ "Digite a op??o: ");
			String opc = input.nextLine();
			switch(opc) {
				case "0": // VOLTAR
					exit = true;
					break;

				case "1": // ADICIONAR
					System.out.print("\nDigite a String que ser? guardada na ?rvore: ");
					String key = input.nextLine();
					
					if (arvoreGenerica.isEmpty()) { //Se for a primeira inser??o cria uma raiz
						arvoreGenerica.addRoot(key);
						TreeNode<String> raiz = (TreeNode<String>) arvoreGenerica.root();
						raiz.setChildren(new NodePositionList<Position<String>>());
						System.out.println("\nRaiz adicionada: " + key);
					} else {
						// Pede com que o usu?rio digite um pai para a String
						System.out.print("A String precisa de um nodo pai para ser adicionada, "
									   + "digite a String de um nodo: ");
						String paiElem = input.nextLine();
						TreeNode<String> paiPos = null;
						
						// Procura a posi??o do pai
						for (Position<String> w : arvoreGenerica.positions()) {
							if (paiElem.equals(w.element())) {
								paiPos = (TreeNode<String>) w;
								break;
							}
						}
						
						// Se o 'for' anterior n?o encontrar um pai, lan?a mensagem de erro
						try {
							arvoreGenerica.checkPosition(paiPos);
						} catch (InvalidPositionException e) {
							System.out.println("\n****" + paiElem + " n?o existe na ?rvore****");
							break;
						}
						
						// Adiciona a String como filha do pai informado
						arvoreGenerica.criarFilho(paiPos, key);
						System.out.println("\nO elemento '" + key + "' foi adicionado como um filho de '" + paiElem + "'");
					}
					
					break;
					
				case "2": // VISUALIZAR
					// Tenta mostrar a ?rvore na tela, se a lista estiver vazia lan?a mensagem de erro
					try {
						System.out.println("\nArvore Gen?rica atual: " + arvoreGenerica.parentheticRepresentation(arvoreGenerica, arvoreGenerica.root()));
					} catch (EmptyTreeException e) {
						System.out.println("\n****?rvore Gen?rica vazia****");
						break;
					}
					break;

				default:
					System.out.println("\n****Op??o inv?lida****");
			}
		}
	}
	
	public TreeNode<String> criarFilho(TreeNode<String> p, String n) {
		PositionList<Position<String>> filhos;
		TreeNode<String> aux;
		
		// Obt?m os Filhos de p
		filhos = p.getChildren();
		// Cria um novo filho
		aux = new TreeNode<String>();
		aux.setElement(n);
		aux.setParent(p);
		aux.setChildren(new NodePositionList<Position<String>>());
		filhos.addLast(aux);
		return aux;
	}
	
	// EXERCICIO 3-A
	public String parentheticRepresentation (Tree<E> T, Position<E> v) {
		String s = v.element().toString(); // a??o principal de visita
		if (T.isInternal(v)) {
			Boolean firstTime = true;
			for (Position<E> w : T.children(v)) {
				if (firstTime) {	// primeiro filho
					s += "(" + parentheticRepresentation(T, w);
					firstTime = false; 
				} else {			// filhos seguintes
					s += "," + parentheticRepresentation(T, w);
				}
			}
			s += ")"; // fecha par?nteses
		}
		return s;
	}
	
	// EXERCICIO 3-B
	public String toStringPostorder(LinkedTree<E> t, Position<E> v) {
		String s = "";
			
		for (Position<E> w : children(v)) {
			if (isInternal(w)) {
				s += toStringPostorder(t, w);
				s += w.element().toString() + " - ";
			} else
				s += w.element().toString() + " - ";
		}
		if (v == root)
			return s + root.element();
		else
			return s;
	}
	
	// EXERCICIO 3-D
	public int depth(LinkedTree<E> t, Position<E> v) {
		if (v == root)
			return 0;
		else 
			return 1 + depth (t, parent(v));
	}

	// EXERCICIO 3-E
	public int height1(LinkedTree<E> t) {
		int h = 0;
		for (Position<E> w : positions())
			if (isExternal(w))
				h = Math.max(h, depth(t, w));
		return h;
	}
	
	// EXERCICIO 3-F
	public int height2(LinkedTree<E> t, Position<E> v) {
		int h;
		if (isExternal(v)) return 0;
		else {
			h = 0;
			for (Position<E> w : children(v))
				h = Math.max(h, height2(t, w));
			return 1 + h;
		}
	}
	
	// Retorna se a ?rvore est? vazia
	public boolean isEmpty() { return (size == 0); }
	// Retorna se um nodo ? interno
	public boolean isInternal(Position<E> v) throws InvalidPositionException { return !isExternal(v); }
	// Retorna se um nodo ? externo
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		return (vv.getChildren() == null) || vv.getChildren().isEmpty();
	}
	
	// Retorna se um nodo ? a ra?z
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}
	// Retorna a ra?z da ?rvore
	public TreePosition<E> root() throws EmptyTreeException {
		if (root == null) throw new EmptyTreeException("The tree is empty");
		return root;
	}
	// Retorna o pai de um nodo
	public TreePosition<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TreePosition<E> vv = checkPosition(v);
		TreePosition<E> parentPos = vv.getParent();
		if (parentPos == null) throw new BoundaryViolationException("No parent");
		return parentPos;
	}
	// Retorna uma cole??o iter?vel dos filhos de um nodo
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		return vv.getChildren();
	}
	// Retorna uma cole??o iter?vel dos nodos da ?rvore.
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if (size != 0) preorderPositions(root(), positions);
		return positions;
	}
	// Retorna um iterator dos elementos armazenados nos nodos
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions = positions();
		PositionList<E> elements = new NodePositionList<E>();
		for (Position<E> pos : positions) elements.addLast(pos.element());
		return elements.iterator();
	}
	// Troca o elemento de um nodo
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		E temp = v.element();
		vv.setElement(o);
		return temp;
	}
	// M?todos de atualiza??o adicionais
	// Adiciona um nodo ra?z para uma ?rvore vazia
	public TreePosition<E> addRoot(E e) throws NonEmptyTreeException {
		if (!isEmpty()) throw new NonEmptyTreeException("Tree already has a root");
		size = 1;
		root = createNode(e, null, null);
		return root;
	}
	// Troca os elementos de dos nodos
	public void swapElements(Position<E> v, Position<E> w) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		TreePosition<E> ww = checkPosition(w);
		E temp = w.element();
		ww.setElement(v.element());
		vv.setElement(temp);
	}
	// M?todos auxiliares
	// Se v ? um bom nodo da ?rvore, cast para TreePosition, caso contr?rio, lan?a exce??o
	protected TreePosition<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null || !(v instanceof TreePosition)) throw new InvalidPositionException("The position is invalid");
		return (TreePosition<E>) v;
	}
	// Cria um novo nodo da ?rvore
	protected TreePosition<E> createNode(E element, TreePosition<E> parent, PositionList<Position<E>> children) {
		return new TreeNode<E>(element, parent, children);
	}
	// Cria uma lista armazenando os nodos das sub?rvore de um nodo
	// ordenado de acordo com a travessia das sub?rvores
	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		pos.addLast(v);
		for (Position<E> w : children(v)) preorderPositions(w, pos);
	}
	
	public String toString() { return toString(this); }
	public static <E> String toString(LinkedTree<E> T) {
		String s = "";
		for (E i : T) { s += ", " + i; }
		// OU
		// for (Iterator<String> it = T.iterator(); it.hasNext();) {
		// s += ", " + it.next();
		// }
		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
	}
}
