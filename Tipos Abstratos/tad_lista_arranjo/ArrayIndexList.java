package tad_lista_arranjo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayIndexList<E> implements IndexList<E> {

	private E[] A;
	private int capacity = 16;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public ArrayIndexList() {
		A = (E[]) new Object[capacity];
	}
	
	public static void interface_Lista_Arranjo() { // Interface - Projeto Estrutura de Dados
		ArrayIndexList<String> Arranjo = new ArrayIndexList<String>(); // Inicializa a estrutura
		boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner

        while (!exit) { // Loop de Interface
			System.out.print("\n --- Interface de Usu�rio ---:\n"
						   + "[0] Voltar para o Menu (Estrutura atual ser� limpa)\n"
						   + "[1] Adicionar\n"
						   + "[2] Remover\n"
						   + "[3] Visualizar\n"
						   + "Digite a op��o: ");
			String opc = input.nextLine();
			switch(opc) {
				case "0": // VOLTAR
					exit = true;
					break;
	
				case "1": // ADICIONAR
					// Mostra na tela o intervalo disponivel para inser��o
					System.out.print("\nDigite o indice onde deseja inserir: intervalo entre [0-" + Arranjo.size() + "] ");
					Integer indice = null;
					
					// Verifica se o valor do �ndice digitado � um Inteiro, se n�o for lan�a mensagem de erro
					try {
						indice = input.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\n****Indice de tipo inv�lido****");
						input.nextLine();
						break;
					} 
					
					// Verifica se o �ndice digitado est� dentro do intervalo mencionado, se n�o estiver lan�a mensagem de erro
					if (indice < 0 || indice > Arranjo.size()) {
						System.out.println("\n****Indice fora do limite do arranjo****");
						input.nextLine();
						break;
					}
					
					input.nextLine();
					System.out.print("Digite o elemento que ser� armazenado no indice: ");
					String valor = input.nextLine();

					// Adiciona o valor no �ndice digitado
					Arranjo.add(indice, valor); 
					System.out.println("\nElemento " + valor + " adicionado no �ndice " + indice);
					break;
					
				case "2": // REMOVER
					// Verifica se o Arranjo est� vazio, se estiver lan�a mensagem de erro
					if (Arranjo.isEmpty()) {
						System.out.println("\n****O Arranjo est� vazio****");
						break;
					}
					
					// Mostra na tela o intervalo disponivel para remo��o
					System.out.print("\nDigite o �ndice que deseja remover: intervalo entre [0-" + (Arranjo.size() - 1) + "] ");
					Integer indiceRemover = null;
					
					// Verifica se o valor do �ndice digitado � um Inteiro, se n�o for lan�a mensagem de erro
					try {
						indiceRemover = input.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\n****Indice de tipo inv�lido****");
						input.nextLine();
						break;
					} 
					
					// Verifica se o �ndice digitado est� dentro do intervalo mencionado, se n�o estiver lan�a mensagem de erro
					if (indiceRemover < 0 || indiceRemover >= Arranjo.size()) {
						System.out.println("\n****Indice fora do limite do arranjo****");
						input.nextLine();
						break;
					}
					
					input.nextLine();
					String elemento = Arranjo.get(indiceRemover); // Salva o elemento antes de ser removido para printar na mensagem
					
					// Remove o elemento do �ndice digitado
					Arranjo.remove(indiceRemover);
					System.out.println("\nO indice " + indiceRemover + " de valor '" + elemento + "' foi removido.");
					break;
	
				case "3": // VISUALIZAR
					// Printa o Arranjo na tela
					System.out.println("\nLista Arranjo Atual: " + Arranjo.toString());
					break;
				
				default:
					System.out.println("\n****Op��o inv�lida****");
			}
		}
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public E get(int r) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		return A[r];
	}

	public E set(int r, E e) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		E temp = A[r];
		A[r] = e;
		return temp;
	}

	public void add(int r, E e) throws IndexOutOfBoundsException {
		checkIndex(r, size() + 1);

		if (size == capacity) {
			capacity *= 2;

			@SuppressWarnings("unchecked")
			E[] B = (E[]) new Object[capacity];

			for (int i = 0; i < size; i++)
				B[i] = A[i];

			A = B;
		}

		for (int i = size - 1; i >= r; i--)
			A[i + 1] = A[i];
		A[r] = e;
		size++;
	}

	public E remove(int r) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		E temp = A[r];

		for (int i = r; i < size - 1; i++)
			A[i] = A[i + 1];

		size--;
		return temp;
	}

	protected void checkIndex(int r, int n) throws IndexOutOfBoundsException {
		if (r < 0 || r >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + r);
	}

	public String toString() {
		String toReturn = "(";
		if (size() == 0) return "()";
		for (int i = 0; i < size(); i++) toReturn += A[i].toString() + ", ";
		
		return toReturn.substring(0, toReturn.length() - 2) + ")";
	}
}