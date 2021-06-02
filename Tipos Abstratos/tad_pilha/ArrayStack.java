package tad_pilha;

import exceptions.EmptyStackException;
import exceptions.FullStackException;
import java.util.Scanner;

public class ArrayStack<E> implements Stack<E> {
    protected int capacity;
    public static final int CAPACITY = 1000;
    protected E S[];
    protected int top = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int cap) {
        capacity = cap;
        S = (E[]) new Object[capacity];
    }
    
    public static void interface_Pilha() { // Interface - Projeto Estrutura de Dados
        ArrayStack<String> Pilha = new ArrayStack<String>(); // Inicializa a estrutura
        boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner

        while (!exit) { // Loop de Interface
            System.out.print("\n --- Interface de Usuário ---:\n"
                    + "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
                    + "[1] Adicionar na pilha\n"
                    + "[2] Remover topo da pilha\n"
                    + "[3] Visualizar\n"
                    + "Digite a opção: ");
            String opc = input.nextLine();
            switch (opc) {
                case "0": // VOLTAR
                    exit = true;
                    break;

                case "1": // ADICIONAR
                    System.out.print("\nDigite o valor a ser inserido no topo: ");
                    String valorAdicionar = input.nextLine();
                    
                    // Adiciona o elemento no topo da pilha
                    Pilha.push(valorAdicionar);
                    System.out.println("\nElemento adicionado ao topo: " + valorAdicionar);
                    break;

                case "2": // REMOVER
                	// Verifica se a pilha está vazia, se estiver lança uma mensagem de erro
                	if (Pilha.isEmpty()) {
                		System.out.println("\n****Pilha vazia****");
                		break;
                	}
                	// Remove o elemento no topo da pilha
                    System.out.println("\nO elemento '" + Pilha.pop() + "' foi removido.");
                    break;

                case "3": // VISUALIZAR
                	// Printa a Pilha na tela
                    System.out.println("\nPilha atual: " + Pilha.toString());
                    break;

                default:
                    System.out.println("\n****Opção inválida****");
            }
        }
    }

    public int size() {
        return (top + 1);
    }

    public boolean isEmpty() {
        return (top < 0);
    }

    public void push(E element) throws FullStackException {
        if (size() == capacity)
            throw new FullStackException("Stack is full.");
        S[++top] = element;
    }

    public E top() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException("Stack is empty.");
        return S[top];
    }

    public E pop() throws EmptyStackException {
        E element;
        if (isEmpty()) throw new EmptyStackException("Stack is empty.");
        element = S[top];
        S[top--] = null;
        return element;
    }

    public String toString() {
        String s;
        s = "[";
        if (size() > 0) s += S[0];
        if (size() > 1) {
            for (int i = 1; i <= size() - 1; i++) {
                s += ", " + S[i];
            }
        }
        return s + "]";
    }
}
