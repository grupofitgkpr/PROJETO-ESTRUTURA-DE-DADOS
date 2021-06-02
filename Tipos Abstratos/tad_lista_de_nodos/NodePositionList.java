package tad_lista_de_nodos;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.*;
import interfaces.Position;
import interfaces.PositionList;

public class NodePositionList<E> implements PositionList<E> {
	protected int numElts; // Número de elementos na lista

	protected DNode<E> header, trailer; // Sentinelas especiais

	// Construtor que cria uma lista vazia
	public NodePositionList() {
		numElts = 0;
		header = new DNode<E>(null, null, null); // cria a cabeça
		trailer = new DNode<E>(header, null, null); // cria a cauda
		header.setNext(trailer); // faz a cabeça e a cauda apontarem uma para a outra
	}
	
	public static void interface_Lista_Nodos() { // Interface - Projeto Estrutura de Dados
		NodePositionList<String> Nodo = new NodePositionList<String>(); // Inicializa a estrutura
		boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner

        while (!exit) { // Loop de Interface
			System.out.print("\n --- Interface de Usuário ---:\n"
                    + "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
                    + "[1] Adicionar na primeira posição\n"
                    + "[2] Adicionar na ultima posição\n"
                    + "[3] Remover\n"
                    + "[4] Visualizar\n"
                    + "Digite a opção: ");
			
			int opc = input.nextInt();
			switch (opc) {
				case 0: // VOLTAR
					exit = true;
					break;

				case 1: // ADICIONAR NA PRIMEIRA POSIÇÃO
					input.nextLine();
					System.out.print("\nDigite o valor que será armazenado na primeira posição: ");
					String valor = input.nextLine();
					
					// Adiciona o valor na primeira posição da Lista de Nodos
					Nodo.addFirst(valor);
					System.out.println("\nValor adicionado na primeira posição: " + valor);
					break;
						
				case 2: // ADICIONAR NA ULTIMA POSIÇÃO
					input.nextLine();
					System.out.print("\nDigite o valor que será armazenado na ultima posição: ");
					String valorUlt = input.nextLine();
					
					// Adiciona o valor na ultima posição da Lista de Nodos
					Nodo.addLast(valorUlt);
					System.out.println("\nValor adicionado na ultima posição: " + valorUlt);
					break;
					
				case 3: // REMOVER
					// Verifica se a Lista de Nodos está vazia, se estiver lança mensagem de erro
					if (Nodo.isEmpty()) {
						System.out.println("\n****A lista está vazia****");
						break;
					}
					
					input.nextLine();
					System.out.print("\nDigite o valor do elemento que deseja remover: ");
					String elementoRemover = input.nextLine();
					Position<String> nodo = Nodo.first();
					
					// Percorre a Lista de Nodos para encontrar o elemento digitado, se não encontrar lança mensagem de erro
					try {
						for (int x = 0; x < Nodo.size() + 1; x++)
							if (nodo.element().equals(elementoRemover))
								break;
							else 
								nodo = Nodo.next(nodo);
					} catch (BoundaryViolationException e) {
						System.out.println("\n****O elemento '" + elementoRemover + "' não existe na lista****");
						break;
					} 
					
					// Remove o elemento digitado da Lista de Nodos
					Nodo.remove(nodo);
					System.out.println("\nO elemento '" + elementoRemover + "' foi removido.");
					break;
					
				case 4: // VISUALIZAR
					// Printa a Lista de Nodos na tela
					System.out.println("\nLista de Nodos atual: " + Nodo.toString());
					break;
				
				default:
					System.out.println("\n****Opção inválida****");
			}
		}
	}

    // Verifica se a posição é válida para esta lista e a converte para DNode se for válida
	protected DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null) throw new InvalidPositionException("Null position passed to NodeList");
		if (p == header) throw new InvalidPositionException("The header node is not a valid position");
		if (p == trailer) throw new InvalidPositionException("The trailer node is not a valid position");
		try {
			DNode<E> temp = (DNode<E>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("Position does not belong to a valid NodeList");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Position is of wrong type for this list");
		}
	}

	// Retorna a quantidade de elementos na lista
	public int size() {return numElts;}

	// Retorna quando a lista esta vazia
	public boolean isEmpty() { return (numElts == 0); }

	// Retorna a primeira posição da lista
	public Position<E> first() throws EmptyListException {
		if (isEmpty()) throw new EmptyListException("List is empty");
		return header.getNext();
	}

	// Retorna a posição que antecede a fornecida
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> v = checkPosition(p);
		DNode<E> prev = v.getPrev();
		if (prev == header) throw new BoundaryViolationException("Cannot advance past the beginning of the list");
		return prev;
	}

	// Insere o elemento antes da posição fornecida, retornando a nova posição
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}

	// Insere o elemento dado no início da lista, retornando a nova posição
	public void addFirst(E element) {
		numElts++;
		DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
	}

	// Remove da lista a posição fornecida
	public E remove(Position<E> p) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts--;
		DNode<E> vPrev = v.getPrev();
		DNode<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		E vElem = v.element();
		// Desconecta a posição da lista e marca-a como inválida
		v.setNext(null);
		v.setPrev(null);
		return vElem;
	}

	// Substitui o elemento da posição fornecida por um novo e retorna o elemento velho
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		E oldElt = v.element();
		v.setElement(element);
		return oldElt;
	}

	// Retorna o último nodo da lista.
	public Position<E> last() {
		if (isEmpty()) throw new EmptyListException("List is empty");
		return trailer.getPrev();
	}

	// Retorna o nodo que segue um dado nodo da lista.
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> v = checkPosition(p);
		DNode<E> next = v.getNext();
		if (next == trailer) throw new BoundaryViolationException("Cannot advance past the finaling of the list");
		return next;
	}

	// Insere um elemento na última posição, retornando uma posição nova.
	public void addLast(E e) {
		numElts++;
		DNode<E> newNode = new DNode<E>(trailer.getPrev(), trailer, e);
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
	}

	// Insere um elemento após um dado elemento da lista.
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v, v.getNext(), e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}

	// Retorna a representação textual de uma lista de nodos
	public static <E> String toString(PositionList<E> l) {
		String s = "";
		for (E i: l) { s += ", " + i; }
		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
	}

	// Retorna o iterator a partir do ElemenIterator.
	public Iterator<E> iterator() { return new ElementIterator<E>(this); }
	public String toString() { return toString(this); }
}

