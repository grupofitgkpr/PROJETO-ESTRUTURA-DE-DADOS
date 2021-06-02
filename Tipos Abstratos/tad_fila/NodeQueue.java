package tad_fila;

import java.util.Scanner;

public class NodeQueue<E> implements Queue<E> {
    protected Node<E> head; // referência para o nodo cabeça
    protected Node<E> tail;
    protected int size; // quantidade de elementos na pilha

    public NodeQueue() { // constrói uma pilha vazia
        head = tail = null;
        size = 0;
    }
    
    public static void interface_Fila() { // Interface - Projeto Estrutura de Dados
        NodeQueue<String> Fila = new NodeQueue<String>(); // Inicializa a estrutura
        boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner

        while (!exit) { // Loop de Interface
            System.out.print("\n --- Interface de Usuário ---:\n"
                    + "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
                    + "[1] Adicionar na fila\n"
                    + "[2] Remover primeiro da fila\n"
                    + "[3] Visualizar\n"
                    + "Digite a opção: ");
            String opc = input.nextLine();
            switch (opc) {
                case "0": // VOLTAR
                    exit = true;
                    break;

                case "1": // ADICIONAR
                    System.out.print("\nDigite o elemento a ser colocado na fila: ");
                    String elemento = input.nextLine();
                    
                    // Adiciona o elemento no fim da fila
                    Fila.enqueue(elemento);
                    System.out.println("\nElemento adicionado no fim da fila: " + elemento);
                    break;

                case "2": // REMOVER
                	// Verifica se a fila está vazia, se estiver lança uma mensagem de erro
                	if (Fila.isEmpty()) {
                		System.out.println("\n****A fila está vazia****");
                		break;
                	}
                	// Remove o elemento na frente da fila
                    System.out.println("\nO elemento '" + Fila.dequeue() + "' foi removido.");
                    break;

                case "3": // VISUALIZAR
                	// Printa a Fila na tela
                	System.out.println("\nFila atual: " + Fila.toString());
                    break;
                
                default:
                    System.out.println("\n****Opção inválida****");
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (head == null) return true;
        return false;
    }

    public void enqueue(E elem) {
        Node<E> node = new Node<E>();

        node.setElement(elem);
        node.setNext(null); // nodo será o novo nodo do final
        if (size == 0)
            head = node; // caso especial de uma lista previamente vazia
        else
            tail.setNext(node); // adiciona nodo no final da lista
        tail = node; // atualiza referência ao nodo do final
        size++;
    }

    public E front() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Stack is empty.");
        return head.getElement();
    }

    public E dequeue() throws EmptyQueueException {
        if (size == 0) throw new EmptyQueueException("Queue is empty.");
        E tmp = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
            tail = null;// a fila está vazia agora
        return tmp;
    }

    public String toString() {
        String s;
        Node<E> p = head;

        s = "[";
        while (p != null) {
            s += p.getElement();
            s += ", ";
            p = p.getNext();
        }
        if (s.length() > 1)
            s = s.substring(0, s.length() - 2);
        return s + "]";
    }
}
