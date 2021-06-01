package tad_dicion�rio;

// Esta classe implementa a interface do TAD Dicionario MultiMap<K, V>
// As outras interfaces utilizadas s�o do pr�prio Java.

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class HashTableMultiMap<K, V> implements MultiMap<K, V> {
	Map<K, LinkedList<Map.Entry<K, V>>> m; // mapa de chaves para listas de entradas
	int nSize; // tamanho deste MapDictionary
	// Construtor default que usa um HashMap
	public HashTableMultiMap() {
		m = new HashMap<K, LinkedList<Map.Entry<K, V>>>(); // mapa default
		nSize = 0;
	}

	// Retorna a quantidade de entradas no dicion�rio
	public int size() { return nSize; }
	// Retorna se o dicion�rio est� vazio
	public boolean isEmpty() { return nSize == 0; }
	
	public static void interface_Dicionario() {
		HashTableMultiMap<Integer, String> Dicionario = new HashTableMultiMap<Integer, String>();
		boolean exit = false;
		Scanner input = new Scanner(System.in);;
		
		while (!exit) {
			System.out.print("\n --- Interface de Usu�rio ---:\n"
						   + "[0] Voltar para o Menu (Estrutura atual ser� limpa)\n"
						   + "[1] Adicionar\n"
						   + "[2] Remover\n"
						   + "[3] Visualizar\n"
						   + "Digite a op��o: ");
			String opc = input.nextLine();
			switch(opc) {
				case "0":
					exit = true;
					break;
	
				case "1":
					System.out.print("\nDigite o valor da chave (Valor inteiro): ");
					Integer key = null;
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
					
					String valorAdd = Dicionario.put(key, value).toString();
					System.out.println("\nElemento adicionado: " + valorAdd);
					break;
				
				case "2":
					System.out.print("\nDigite o valor da chave que ser� removida (Valor inteiro): ");
					Integer rkey = null;
					try {
						rkey = input.nextInt();
						String rvalor = Dicionario.remove(Dicionario.get(rkey)).toString();
						System.out.println("\nElemento removido: " + rvalor);
					} catch (InputMismatchException e) {
						System.out.println("\n****Valor de tipo inv�lido****");
						input.nextLine();
						break;
					} catch (IllegalArgumentException e) {
						System.out.println("\n****A chave '" + rkey + "' n�o existe no Dicion�rio****");
						input.nextLine();
						break;
					}
					input.nextLine();
					break;
				
				case "3":
					System.out.println("\nDicion�rio atual: " + Dicionario.entrySet().toString());
					break;
				
				default:
					System.out.println("\n****Op��o inv�lida****");
			}
		}
	}
	// Insere um item em um dicion�rio. Retorna uma entrada recentemente criada
	public Map.Entry<K, V> put(K key, V value) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) throw new IllegalArgumentException();
		if ((ll = m.get(key)) == null) { // ainda nada aqui
			ll = new LinkedList<Map.Entry<K, V>>();
			m.put(key, ll);
		}
		Map.Entry<K, V> e = new AbstractMap.SimpleEntry<K, V>(key, value);
		ll.add(e); // acrescenta uma nova entrada na lista desta chave
		nSize++;
		return e;
	}
	// Retorna uma entrada contendo uma dada chave ou null se n�o existe a entrada
	public Map.Entry<K, V> get(K key) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) throw new IllegalArgumentException();
		if ((ll = m.get(key)) == null) return null; // nada aqui ainda
		return ll.peekFirst(); // o primeiro elemento � t�o bom como qualquer um
	}
	// Retorna um iterador contendo todas as entradas contendo uma certa chave ou um iterador
	// vazio se a entrada n�o existir.
	public Iterable<Map.Entry<K, V>> getAll(K key) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) throw new IllegalArgumentException();
		if ((ll = m.get(key)) == null) return null; // nada ainda
		return ll;
	}
	// Remove e retorna uma dada entrada do dicion�rio.
	public Map.Entry<K, V> remove(Map.Entry<K, V> e) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (e == null) throw new IllegalArgumentException();
		K key = e.getKey();
		ll = m.get(key);
		if (ll == null) throw new IllegalArgumentException(); // chave n�o est� em m
		if (ll.remove(e)) {
			nSize--;
			if (ll.isEmpty()) m.remove(key); // neste caso, remove a lista vazia
			return e; // e estava em ll, ent�o retorna a entrada removida
		} else throw new IllegalArgumentException(); // e n�o estava em ll
	}
	// Retorna um iterador contendo todas as entradas do dicion�rio.
	public Iterable<Map.Entry<K, V>> entrySet() {
		LinkedList<Map.Entry<K, V>> ll = new LinkedList<Map.Entry<K, V>>();
		// todas as entradas desta lista para ll
		for (LinkedList<Map.Entry<K, V>> sub : m.values()) ll.addAll(sub);
		return ll;
	}
}