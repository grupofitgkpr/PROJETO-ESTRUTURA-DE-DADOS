package tad_fila_prioridade;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import interfaces.Entry;
import exceptions.DefaultComparator;
import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;
import interfaces.Position;
import tad_lista_de_nodos.NodePositionList;
import interfaces.PositionList;


public class SortedListPriorityQueue<K, V> implements PriorityQueue<K, V> {
    protected PositionList<Entry<K, V>> entries;
    protected Comparator<K> compa;
    protected Position<Entry<K, V>> actionPos; // variable used by subclasses

    // Classe Entradas interna
    protected static class MyEntry<K, V> implements Entry<K, V> {
        protected K k; // key
        protected V v; // value

        public MyEntry(K key, V value) {
            k = key;
            v = value;
        }

        // methods of the Entry interface
        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // sobrescreve toString, �lti para depura��o
        public String toString() {
            return "(" + k + "," + v + ")";
        }
    }
    
    public static void interface_Fila_Prioridades() { // Interface - Projeto Estrutura de Dados
        SortedListPriorityQueue<Integer, String> filaPrioritaria = new SortedListPriorityQueue<>(); // Inicializa a estrutura
        boolean exit = false; // Flags
		Scanner input = new Scanner(System.in); // Inicializa Scanner

        while (!exit) { // Loop de Interface
            System.out.print("\n --- Interface de Usu�rio ---:\n"
                    + "[0] Voltar para o Menu (Estrutura atual ser� limpa)\n"
                    + "[1] Adicionar\n"
                    + "[2] Remover elemento de menor chave\n"
                    + "[3] Visualizar\n"
                    + "Digite a op��o: ");
            String opc = input.nextLine();
            switch (opc) {
                case "0": // VOLTAR
                    exit = true;
                    break;

                case "1": // ADICIONAR
                    System.out.print("\nDigite o valor da chave (Valor inteiro): ");
                    Integer key = null;
                    
                    // Verifica se o valor da chave digitada � um Inteiro, se n�o for lan�a mensagem de erro
                    try {
                        key = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n****Valor de tipo inv�lido****");
                        input.nextLine();
                        break;
                    }

                    input.nextLine();
                    System.out.print("Digite a String que ser� armazenada na chave: ");
                    String value = input.nextLine();
                    
                    // Adiciona na Fila Prioritaria o par de Key e Value
                    String valorAdd = filaPrioritaria.insert(key, value).toString();
                    System.out.println("\nElemento adicionado: " + valorAdd);
                    break;

                case "2": // REMOVER
                	
                	// Verifica e remove o elemento de menor chave da Fila Priorit�ria
                	// Caso a Fila esteja vazia, lan�a mensagem de erro
                    try {
                        System.out.println("\nO valor " + filaPrioritaria.removeMin() + " foi removido.");
                    } catch (EmptyPriorityQueueException e) {
                        System.out.println("\n****Fila de Prioridades vazia****");
                        break;
                    }
                    break;

                case "3": // VISUALIZAR
                	// Printa a Fila de Prioridades na tela
                    System.out.println("\nFila de Prioridade atual: " + filaPrioritaria.toString());
                    break;
                    
                default:
                    System.out.println("\n****Op��o inv�lida****");
            }
        }
    }

    // Cria a fila de prioridades com o comparador padr�o
    public SortedListPriorityQueue() {
        entries = new NodePositionList<Entry<K, V>>();
        compa = new DefaultComparator<K>();
    }

    // Cria a fila de prioridades com um comparador informado
    public SortedListPriorityQueue(Comparator<K> comp) {
        entries = new NodePositionList<Entry<K, V>>();
        compa = comp;
    }

    // Cria a fila de prioridade com um comparador informado e lista.
    // Assume-se que a lista � ordenada em ordem n�o-decrescente
    public SortedListPriorityQueue(PositionList<Entry<K, V>> list, Comparator<K> comp) {
        entries = list;
        compa = comp;
    }

    // Configura o comparador para a fila de prioridade.
    //* @throws IllegalStateException se fila de prioridade estiver vazia
    public void setComparator(Comparator<K> comp) throws IllegalStateException {
        // s� � permitido se a fila de prioridade estiver vazia
        if (!isEmpty()) throw new IllegalStateException("Priority queue is not empty");
        compa = comp;
    }

    // Retorna o n�mero de elementos da fila de propridade
    public int size() {
        return entries.size();
    }

    // Retorna se a fila de prioridade est� vazia.
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    // Retorna mas n�o remove uma entrada com a chave m�nima.
    public Entry<K, V> min() throws EmptyPriorityQueueException {
        if (entries.isEmpty()) throw new EmptyPriorityQueueException("priority queue is empty");
        else return entries.first().element();
    }

    // Insere um par chave-valor e retorna a entrada criada.
    public Entry<K, V> insert(K k, V v) throws InvalidKeyException {
        checkKey(k); // auxiliary key-checking method (could throw exception)
        Entry<K, V> entry = new MyEntry<K, V>(k, v);
        insertEntry(entry); // auxiliary insertion method
        return entry;
    }

    // M�todo auxiliar usado para inser��o.
    protected void insertEntry(Entry<K, V> e) {
        if (entries.isEmpty()) {
            entries.addFirst(e); // insere na lista vazia
            actionPos = entries.first(); // posi��o de inser��o
        } else if (compa.compare(e.getKey(), entries.last().element().getKey()) > 0) {
            entries.addLast(e); // insere no final da lista
            actionPos = entries.last(); // posi��o de inser��o
        } else {
            Position<Entry<K, V>> curr = entries.first();
            while (compa.compare(e.getKey(), curr.element().getKey()) > 0) {
                curr = entries.next(curr); // avan�a para encontrar a posi��o de inser��o
            }
            entries.addBefore(curr, e);
            actionPos = entries.prev(curr); // posi��o de inser��o
        }
    }

    // Remove e returna uma entrada com a a menor chave.
    public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
        if (entries.isEmpty()) throw new EmptyPriorityQueueException("priority queue is empty");
        else return entries.remove(entries.first());
    }

    // Determina se a chave � v�lida.
    protected boolean checkKey(K key) throws InvalidKeyException {
        boolean result;
        try { // verifica se a chave pode ser comparada
            result = (compa.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new InvalidKeyException("key cannot be compared");
        }
        return result;
    }

    // Sebrescreve toString, �til para a depura��o.
    public String toString() {
        return entries.toString();
    }
}