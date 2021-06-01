package source;

import java.util.Scanner;

import tad_arvore_binaria.LinkedBinaryTree;
import tad_arvore_genérica.LinkedTree;
import tad_dicionário.HashTableMultiMap;
import tad_fila.NodeQueue;
import tad_fila_prioridade.SortedListPriorityQueue;
import tad_lista_arranjo.ArrayIndexList;
import tad_lista_de_nodos.NodePositionList;
import tad_mapa.HashTableMap;
import tad_mapa_ordenado_ABB.BinarySearchTree;
import tad_mapa_ordenado_AVL.AVLTreeMap;
import tad_pilha.ArrayStack;

/*
Integrantes: 							   	 RA: 
* Giovanni Assis Lopes					|	 1903604
* Kaique Mantoanelli Silva				|	 1903168
* Pedro Henrique Oliveira Dantas Lopes	|	 1904007
* Rafael Serino Kiss					|	 1903107
*/

public class EstruturasDeDadosAppV1 {
	public static void main(String[] args) {
		while (true) { //Loop do Menu
            System.out.println("-------- Aplicação de Criação de Estruturas de Dados --------\n");
            Scanner input = new Scanner(System.in);
            System.out.println("[1] TAD Lista Arranjo		[2] TAD Pilha\n"
                              +"[3] TAD Fila			[4] TAD Lista de Nodos\n"
                              +"[5] TAD Árvore Genérica		[6] TAD Árvore Binária\n"
                              +"[7] TAD Fila de Prioridades	[8] TAD Mapa\n"
                              +"[9] TAD Dicionário		[10] TAD Mapa Ordenado ABB\n"
                              +"[11] TAD Mapa Ordenado AVL	[12] TAD Grafos\n"
                              +"		[0] Encerrar programa\n"
                              +"		[00] Integrantes\n");
            System.out.print("Qual estrutura de dados deseja utilizar?: ");
            String opc = input.nextLine();
            
            switch (opc){
                case "0":
                    System.out.println("----- Fim do Programa -----");
                    input.close();
                    System.exit(-1);
                    break;
                case "1":
                	System.out.println("\n --- Lista Arranjo ---:\n"
                			+ "A Lista Arranjo é uma coleção de diversos elementos que estão armanezados numa ordem linear \n"
                			+ "de forma com que os valores podem ser acessados pelos índices que vão de 0 até quanto o usuário querer.\n"
                			+ "\nCada nova inserção precisará de um índice e de um valor que será armazenado.\n"
                			+ "\nAo remover, só será necessário o índice do valor que será removido.");
                	ArrayIndexList.interface_Lista_Arranjo();
                    break;
                case "2":
                	System.out.println("\n --- Pilha ---:\n"
                			+ "A Pilha é uma coleção de diversos elementos que estão armazenados com base \n"
                			+ "na ORDEM DE INSERÇÃO, assim como numa pilha de pratos/copos.\n"
                			+ "\nCada novo elemento empilhado será colocado no 'topo' da lista. \n"
                			+ "\nAs remoções só desempilham deste topo.");
                	ArrayStack.interface_Pilha();
                    break;
                case "3":
                	System.out.println("\n --- Fila ---:\n"
                			+ "A Fila é uma coleção de diversos elementos que estão \n"
                			+ "armazenados com base na ORDEM DE 'CHEGADA', assim como numa fila de pessoas.\n"
                			+ "\nCada elemento acrescentado será colocado no final da lista. \n"
                			+ "\nAs remoções só ocorrem na frente da lista.");
                	NodeQueue.interface_Fila();
                    break;
                case "4":
                	System.out.println("\n --- Lista de Nodos ---:\n"
                			+ "A Lista de Nodos é uma coleção de diversos elementos que estão armazenados numa \n"
                			+ "ordem linear, mas diferente da Lista Arranjo essa lista não usa índices para \n"
                			+ "armazenar seus elementos e sim, referências de qual é o próximo nodo \n"
                			+ "e qual é o nodo anterior ao nodo que está sendo acessado. \n"
                			+ "\nInserções podem ser feitas no começo ou no fim da lista. \n"
                			+ "\nE remoções podem ser feitas colocando o elemento que deseja remover.");
                	NodePositionList.interface_Lista_Nodos();
                    break;
                case "5":
                	System.out.println("\n --- Árvore Genérica ---:\n"
                			+ "A Árvore Genérica é uma estrutura hierárquica, onde cada Nodo tem 1 'pai' \n"
                			+ "e pode ter INÚMEROS filhos, quantos o usuário desejar.\n"
                			+ "\nCada nova inserção precisará de uma string para ser armazenada e um nodo 'pai' (exceto a primeira inserção).\n"
                			+ "\nNão é possivel fazer remoções de Árvore Genérica nessa aplicação.");
                    LinkedTree.interface_Arvore_Generica();
                    break;
                case "6":
                	System.out.println("\n --- Árvore Binária ---:\n"
                			+ "A Árvore Binária é uma estrutura hierárquica, onde cada Nodo tem 1 'pai' \n"
                			+ "e pode ter NO MÁXIMO 2 'filhos' (filho esquerdo e filho direito).\n"
                			+ "\nCada nova inserção precisará de uma string para ser armazenada, um nodo 'pai' e se a \n"
                			+ "nova inserção vai ser o filho esquerdo ou direito (exceto a primeira inserção).\n"
                			+ "\nAo remover um Nodo, se o mesmo tiver 1 filho ele assumirá a posição, \n"
                			+ "mas não é possivel remover se o nodo tiver 2 filhos.");
                	LinkedBinaryTree.interface_Arvore_Binaria();
                    break;
                case "7":
                	System.out.println("\n --- Fila de Prioridades ---:\n"
                			+ "A Fila de Prioridades é uma coleção de diversos elementos que estão "
                			+ "armazenados com base na ordem de prioridade. \n"
                			+ "\nEssa ordem é definida pelo valor da chave que deve ser dada a cada inserção, \n"
                			+ "quanto menor for o valor, maior a prioridade que o elemento terá na fila. \n"
                			+ "\nA remoção só ocorre no elemento de maior prioridade dentre os elementos da fila.");
                	SortedListPriorityQueue.interface_Fila_Prioridades();
                    break;
                case "8":
                	System.out.println("\n --- Mapa ---:\n"
                			+ "O Mapa é uma coleção de diversos elementos que estão armazenados em pares de chave e valor.\n"
                			+ "\nEssa chave é UNICA para cada valor, mas chaves distintas podem ter valores iguais. \n"
                			+ "Caso adicionar um novo valor à uma chave existente, o valor antigo será SOBRESCRITO. \n"
                			+ "\nRemoções só necessitam da chave que o valor desejado está associado.");
                	HashTableMap.interface_Mapa();
                    break;
                case "9":
                	System.out.println("\n --- Dicionário ---:\n"
                			+ "O Mapa é uma coleção de diversos elementos que estão armazenados em pares de chave e valor.\n"
                			+ "\nEssa chave é MULTIPLA para cada valor, isso é, valores diferentes podem estar associados a uma \n"
                			+ "mesma chave. Assim como um Dicionário contém mais de um significado para cada palavra.\n"
                			+ "Caso adicionar um novo valor à uma chave existente, só será criado um NOVO ELEMENTO associado a chave. \n"
                			+ "\nRemoções só necessitam da chave que o valor desejado está associado.");
                    HashTableMultiMap.interface_Dicionario();
                    break;
                case "10":
                	System.out.println("\n --- Mapa Ordenado (ABB) ---:\n"
                			+ "O Mapa Ordenado ABB é uma estrutura hierárquica, onde cada Nodo tem 1 'pai' \n"
                			+ "e pode ter NO MÁXIMO 2 'filhos'. Seu diferencial da Árvore Binária convencional é que \n"
                			+ "a ABB permite uma busca de seus elementos muito mais otimizada. \n"
                			+ "\nNa sua inserção, só é pedido uma chave inteira e o valor que será adicionado e a estrutura decide onde \n"
                			+ "é o melhor local para armazená-lo na Árvore, com base em: Se a chave que deseja inserir for menor \n"
                			+ "que a chave de seu 'pai', será armazenado na esquerda e se maior, na direita. \n"
                			+ "\nRemoções só necessitam da chave que o valor desejado está associado, e com o mesmo principio \n"
                			+ "de menor = esquerda e maior = direita, a estrutura encontra a chave requerida e a remove.");
                	BinarySearchTree.interface_Mapa_ABB();
                    break;
                case "11":
                	System.out.println("\n --- Mapa Ordenado (AVL) ---:\n"
                			+ "O Mapa Ordenado AVL é uma estrutura hierárquica, onde cada Nodo tem 1 'pai' e pode ter NO MÁXIMO 2 'filhos'. \n"
                			+ "Seu diferencial do Mapa Ordenado ABB é que a AVL tem um MÉTODO PARA BALANCEAR a árvore gerada, \n"
                			+ "onde basicamente impede com que a árvore se resuma em um único ramo.\n"
                			+ "\nNa sua inserção, só é pedido uma chave inteira e o valor que será adicionado e a estrutura decide onde \n"
                			+ "é o melhor local para armazená-lo na Árvore, com base em: Se a chave que deseja inserir for menor \n"
                			+ "que a chave de seu 'pai', será armazenado na esquerda e se maior, na direita. \n"
                			+ "\nRemoções só necessitam da chave que o valor desejado está associado, e com o mesmo principio \n"
                			+ "de menor = esquerda e maior = direita, a estrutura encontra a chave requerida e a remove.");
                	AVLTreeMap.interface_Mapa_AVL();
                    break;
                case "12":
                	System.out.println("\n --- Grafos ---:\n"
                			+ "O Grafo busca o desenvolvimento de algoritmos mais eficientes, cada grafo possui dois conjuntos;\n"
                			+ "conjunto V de vértices que são itens representados em um grafo,e o conjunto A de arestas\n"
                			+ "que utiliza para conectar pares de vértices.");
                    break;
                    
                case "00":
                    System.out.println("\n--- Integrantes ---:");
                    System.out.println("Integrantes:			  	  RA: \n"
                    		+ "* Giovanni Assis Lopes			|  1903604\n"
                    		+ "* Kaique Mantoanelli Silva		|  1903168\n"
                    		+ "* Pedro Henrique Oliveira Dantas Lopes	|  1904007\n"
                    		+ "* Rafael Serino Kiss			|  1903107");
                    break;
                default:
                    System.out.println("\n****Opção inválida****");
                    break;
            }
            System.out.println("");
        }
	}
}
