package source;

import java.util.Scanner;

import tad_arvore_binaria.LinkedBinaryTree;
import tad_arvore_gen�rica.LinkedTree;
import tad_dicion�rio.HashTableMultiMap;
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
            System.out.println("-------- Aplica��o de Cria��o de Estruturas de Dados --------\n");
            Scanner input = new Scanner(System.in);
            System.out.println("[1] TAD Lista Arranjo		[2] TAD Pilha\n"
                              +"[3] TAD Fila			[4] TAD Lista de Nodos\n"
                              +"[5] TAD �rvore Gen�rica		[6] TAD �rvore Bin�ria\n"
                              +"[7] TAD Fila de Prioridades	[8] TAD Mapa\n"
                              +"[9] TAD Dicion�rio		[10] TAD Mapa Ordenado ABB\n"
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
                			+ "A Lista Arranjo � uma cole��o de diversos elementos que est�o armanezados numa ordem linear \n"
                			+ "de forma com que os valores podem ser acessados pelos �ndices que v�o de 0 at� quanto o usu�rio querer.\n"
                			+ "\nCada nova inser��o precisar� de um �ndice e de um valor que ser� armazenado.\n"
                			+ "\nAo remover, s� ser� necess�rio o �ndice do valor que ser� removido.");
                	ArrayIndexList.interface_Lista_Arranjo();
                    break;
                case "2":
                	System.out.println("\n --- Pilha ---:\n"
                			+ "A Pilha � uma cole��o de diversos elementos que est�o armazenados com base \n"
                			+ "na ORDEM DE INSER��O, assim como numa pilha de pratos/copos.\n"
                			+ "\nCada novo elemento empilhado ser� colocado no 'topo' da lista. \n"
                			+ "\nAs remo��es s� desempilham deste topo.");
                	ArrayStack.interface_Pilha();
                    break;
                case "3":
                	System.out.println("\n --- Fila ---:\n"
                			+ "A Fila � uma cole��o de diversos elementos que est�o \n"
                			+ "armazenados com base na ORDEM DE 'CHEGADA', assim como numa fila de pessoas.\n"
                			+ "\nCada elemento acrescentado ser� colocado no final da lista. \n"
                			+ "\nAs remo��es s� ocorrem na frente da lista.");
                	NodeQueue.interface_Fila();
                    break;
                case "4":
                	System.out.println("\n --- Lista de Nodos ---:\n"
                			+ "A Lista de Nodos � uma cole��o de diversos elementos que est�o armazenados numa \n"
                			+ "ordem linear, mas diferente da Lista Arranjo essa lista n�o usa �ndices para \n"
                			+ "armazenar seus elementos e sim, refer�ncias de qual � o pr�ximo nodo \n"
                			+ "e qual � o nodo anterior ao nodo que est� sendo acessado. \n"
                			+ "\nInser��es podem ser feitas no come�o ou no fim da lista. \n"
                			+ "\nE remo��es podem ser feitas colocando o elemento que deseja remover.");
                	NodePositionList.interface_Lista_Nodos();
                    break;
                case "5":
                	System.out.println("\n --- �rvore Gen�rica ---:\n"
                			+ "A �rvore Gen�rica � uma estrutura hier�rquica, onde cada Nodo tem 1 'pai' \n"
                			+ "e pode ter IN�MEROS filhos, quantos o usu�rio desejar.\n"
                			+ "\nCada nova inser��o precisar� de uma string para ser armazenada e um nodo 'pai' (exceto a primeira inser��o).\n"
                			+ "\nN�o � possivel fazer remo��es de �rvore Gen�rica nessa aplica��o.");
                    LinkedTree.interface_Arvore_Generica();
                    break;
                case "6":
                	System.out.println("\n --- �rvore Bin�ria ---:\n"
                			+ "A �rvore Bin�ria � uma estrutura hier�rquica, onde cada Nodo tem 1 'pai' \n"
                			+ "e pode ter NO M�XIMO 2 'filhos' (filho esquerdo e filho direito).\n"
                			+ "\nCada nova inser��o precisar� de uma string para ser armazenada, um nodo 'pai' e se a \n"
                			+ "nova inser��o vai ser o filho esquerdo ou direito (exceto a primeira inser��o).\n"
                			+ "\nAo remover um Nodo, se o mesmo tiver 1 filho ele assumir� a posi��o, \n"
                			+ "mas n�o � possivel remover se o nodo tiver 2 filhos.");
                	LinkedBinaryTree.interface_Arvore_Binaria();
                    break;
                case "7":
                	System.out.println("\n --- Fila de Prioridades ---:\n"
                			+ "A Fila de Prioridades � uma cole��o de diversos elementos que est�o "
                			+ "armazenados com base na ordem de prioridade. \n"
                			+ "\nEssa ordem � definida pelo valor da chave que deve ser dada a cada inser��o, \n"
                			+ "quanto menor for o valor, maior a prioridade que o elemento ter� na fila. \n"
                			+ "\nA remo��o s� ocorre no elemento de maior prioridade dentre os elementos da fila.");
                	SortedListPriorityQueue.interface_Fila_Prioridades();
                    break;
                case "8":
                	System.out.println("\n --- Mapa ---:\n"
                			+ "O Mapa � uma cole��o de diversos elementos que est�o armazenados em pares de chave e valor.\n"
                			+ "\nEssa chave � UNICA para cada valor, mas chaves distintas podem ter valores iguais. \n"
                			+ "Caso adicionar um novo valor � uma chave existente, o valor antigo ser� SOBRESCRITO. \n"
                			+ "\nRemo��es s� necessitam da chave que o valor desejado est� associado.");
                	HashTableMap.interface_Mapa();
                    break;
                case "9":
                	System.out.println("\n --- Dicion�rio ---:\n"
                			+ "O Mapa � uma cole��o de diversos elementos que est�o armazenados em pares de chave e valor.\n"
                			+ "\nEssa chave � MULTIPLA para cada valor, isso �, valores diferentes podem estar associados a uma \n"
                			+ "mesma chave. Assim como um Dicion�rio cont�m mais de um significado para cada palavra.\n"
                			+ "Caso adicionar um novo valor � uma chave existente, s� ser� criado um NOVO ELEMENTO associado a chave. \n"
                			+ "\nRemo��es s� necessitam da chave que o valor desejado est� associado.");
                    HashTableMultiMap.interface_Dicionario();
                    break;
                case "10":
                	System.out.println("\n --- Mapa Ordenado (ABB) ---:\n"
                			+ "O Mapa Ordenado ABB � uma estrutura hier�rquica, onde cada Nodo tem 1 'pai' \n"
                			+ "e pode ter NO M�XIMO 2 'filhos'. Seu diferencial da �rvore Bin�ria convencional � que \n"
                			+ "a ABB permite uma busca de seus elementos muito mais otimizada. \n"
                			+ "\nNa sua inser��o, s� � pedido uma chave inteira e o valor que ser� adicionado e a estrutura decide onde \n"
                			+ "� o melhor local para armazen�-lo na �rvore, com base em: Se a chave que deseja inserir for menor \n"
                			+ "que a chave de seu 'pai', ser� armazenado na esquerda e se maior, na direita. \n"
                			+ "\nRemo��es s� necessitam da chave que o valor desejado est� associado, e com o mesmo principio \n"
                			+ "de menor = esquerda e maior = direita, a estrutura encontra a chave requerida e a remove.");
                	BinarySearchTree.interface_Mapa_ABB();
                    break;
                case "11":
                	System.out.println("\n --- Mapa Ordenado (AVL) ---:\n"
                			+ "O Mapa Ordenado AVL � uma estrutura hier�rquica, onde cada Nodo tem 1 'pai' e pode ter NO M�XIMO 2 'filhos'. \n"
                			+ "Seu diferencial do Mapa Ordenado ABB � que a AVL tem um M�TODO PARA BALANCEAR a �rvore gerada, \n"
                			+ "onde basicamente impede com que a �rvore se resuma em um �nico ramo.\n"
                			+ "\nNa sua inser��o, s� � pedido uma chave inteira e o valor que ser� adicionado e a estrutura decide onde \n"
                			+ "� o melhor local para armazen�-lo na �rvore, com base em: Se a chave que deseja inserir for menor \n"
                			+ "que a chave de seu 'pai', ser� armazenado na esquerda e se maior, na direita. \n"
                			+ "\nRemo��es s� necessitam da chave que o valor desejado est� associado, e com o mesmo principio \n"
                			+ "de menor = esquerda e maior = direita, a estrutura encontra a chave requerida e a remove.");
                	AVLTreeMap.interface_Mapa_AVL();
                    break;
                case "12":
                	System.out.println("\n --- Grafos ---:\n"
                			+ "O Grafo busca o desenvolvimento de algoritmos mais eficientes, cada grafo possui dois conjuntos;\n"
                			+ "conjunto V de v�rtices que s�o itens representados em um grafo,e o conjunto A de arestas\n"
                			+ "que utiliza para conectar pares de v�rtices.");
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
                    System.out.println("\n****Op��o inv�lida****");
                    break;
            }
            System.out.println("");
        }
	}
}
