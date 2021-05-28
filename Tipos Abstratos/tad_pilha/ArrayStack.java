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

    public static void interface_Pilha() {
        ArrayStack<Integer> Pilha = new ArrayStack<Integer>();
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        while (!exit) {
            System.out.print("\n --- Interface de Usuário ---:\n"
                    + "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
                    + "[1] Adicionar\n"
                    + "[2] Remover\n"
                    + "[3] Topo da pilha\n"
                    + "[4] Tamanho\n"
                    + "Digite a opção: ");
            int opc = input.nextInt();
            switch (opc) {
                case 0:
                    exit = true;
                    break;

                case 1:
                    System.out.print("\nDigite o valor a ser inserido (Valor inteiro): ");
                    Integer valorAdicionar = 0;
                    try {
                        valorAdicionar = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("	****Valor inválido****");
                        input.nextLine();
                        break;
                    }
                    Pilha.push(valorAdicionar);
                    break;

                case 2:
                    System.out.println("\nO elemento " + Pilha.top() + " foi removido.");
                    try {
                        Pilha.pop();
                    } catch (EmptyStackException e) {
                        System.out.println("    ****Pilha vazia****");
                        break;
                    }
                    break;

                case 3:
                    try {
                        System.out.println("\nO elemento " + Pilha.top() + " esta no topo da lista.");
                    } catch (EmptyStackException e) {
                        System.out.println("    ****Pilha vazia****");
                        break;
                    }
                    break;

                case 4:
                    try {
                        System.out.println("\nO tamanho da lista é de  " + Pilha.size() + " elementos.");
                    } catch (EmptyStackException e) {
                        System.out.println("    ****Pilha vazia****");
                        break;
                    }
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
