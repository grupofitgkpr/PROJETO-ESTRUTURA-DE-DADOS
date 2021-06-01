package tad_pilha;

import exceptions.EmptyStackException;
import exceptions.FullStackException;
import tad_dicionário.HashTableMultiMap;

import java.util.InputMismatchException;
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
    
    public static void interface_Pilha() {
        ArrayStack<String> Pilha = new ArrayStack<String>();
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        while (!exit) {
            System.out.print("\n --- Interface de Usuário ---:\n"
                    + "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
                    + "[1] Adicionar na pilha\n"
                    + "[2] Remover topo da pilha\n"
                    + "[3] Visualizar\n"
                    + "Digite a opção: ");
            int opc = input.nextInt();
            switch (opc) {
                case 0:
                    exit = true;
                    break;

                case 1:
                    System.out.print("\nDigite o valor a ser inserido no topo: ");
                    input.nextLine();
                    String valorAdicionar = input.nextLine();
                    
                    Pilha.push(valorAdicionar);
                    System.out.println("\nElemento adicionado ao topo: " + valorAdicionar);
                    break;

                case 2:
                	if (Pilha.isEmpty()) {
                		System.out.println("\n****Pilha vazia****");
                		break;
                	}
                    System.out.println("\nO elemento '" + Pilha.pop() + "' foi removido.");
                    break;

                case 3:
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
