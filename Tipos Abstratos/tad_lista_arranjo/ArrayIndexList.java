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

	public static void interface_Lista_Arranjo() {
		ArrayIndexList<Integer> Arranjo = new ArrayIndexList<Integer>();
		
		boolean exit = false;
		Scanner input = new Scanner(System.in);
		
		while (!exit) {
			System.out.print("\n --- Interface de Usuário ---:\n"
						   + "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
						   + "[1] Adicionar\n"
						   + "[2] Remover\n"
						   + "[3] Visualizar\n"
						   + "Digite a opção: ");
			int opc = input.nextInt();
			switch(opc) {
				case 0:
					exit = true;
					break;
	
				case 1:
					Integer indice = null;
					Integer valor = null;
	
					try {
						System.out.print("\nDigite o indice onde deseja inserir: ");
						indice = input.nextInt();
						
						System.out.print("Digite o valor que será armazenado no indice: ");
						valor = input.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("	****Valor inválido****");
						input.nextLine();
						break;
					} 
					
					try{ Arranjo.add(indice, valor); }
					catch (IndexOutOfBoundsException e) {
						System.out.println("****Indice fora do limite do arranjo****");
						input.nextLine();
						break;
					}
					System.out.println("\nValor adicionado: "+ Arranjo.get(indice));
					break;
					
				case 2:
					System.out.print("\nDigite o índice que deseja remover: ");
					Integer indiceRemover = null;
	
					try {
						indiceRemover = input.nextInt();
						System.out.println("\nO indice " + indiceRemover + " de valor " + Arranjo.get(indiceRemover) + " foi removido.");
						Arranjo.remove(indiceRemover);
					} catch (IndexOutOfBoundsException e) {
						System.out.println("****Indice fora do limite do arranjo****");
						input.nextLine();
						break;
					}
					break;
	
				case 3:
					System.out.print("\nLista Arranjo Atual: ");
					System.out.println(Arranjo.toString());
					break;
				
				default:
					System.out.println("Opção inválida");
			}
		}
	}
}