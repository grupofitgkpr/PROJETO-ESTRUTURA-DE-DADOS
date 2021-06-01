package source;

import java.util.Scanner;

import tad_arvore_binaria.LinkedBinaryTree;
import tad_arvore_gen�rica.LinkedTree;
import tad_dicion�rio.HashTableMultiMap;
import tad_fila.NodeQueue;
import tad_lista_arranjo.ArrayIndexList;
import tad_lista_de_nodos.NodePositionList;
import tad_lista_prioridade.SortedListPriorityQueue;
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
                              +"[5] TAD �rvore Gen�tica		[6] TAD �rvore Bin�ria\n"
                              +"[7] TAD Fila de Prioridade	[8] TAD Mapa\n"
                              +"[9] TAD Dicion�rio		[10] TAD Mapa Ordenado ABB\n"
                              +"[11] TAD Mapa Ordenado AVL	[12] TAD Grafos\n"
                              +"		[0] Encerrar programa\n");
            System.out.print("Qual estrutura de dados deseja utilizar?: ");
            int opc = input.nextInt();
            
            switch (opc){
                case 0:
                    System.out.println("----- Fim do Programa -----");
                    input.close();
                    System.exit(-1);
                    break;
                case 1:
                	System.out.println("\n --- Lista Arranjo ---:\n"
                			+ "A Lista Arranjo � uma cole��o de diversos elementos que est�o armanezados numa ordem linear \n"
                			+ "de forma com que os valores podem ser acessados pelos �ndices que v�o de 0 at� quanto o usu�rio querer.\n"
                			+ "Cada nova inser��o precisar� de um �ndice e de um valor que ser� armazenado.\n"
                			+ "Ao remover, s� ser� necess�rio o �ndice do valor que ser� removido.");
                	ArrayIndexList.interface_Lista_Arranjo();
                    break;
                case 2:
                	System.out.println("\n --- Pilha ---:\n"
                			+ "A Pilha � uma cole��o de diversos elementos que est�o \n"
                			+ "armazenados com base na ordem de inser��o, assim como numa pilha de pratos/copos.\n"
                			+ "\nCada novo elemento empilhado ser� colocado no 'topo' da lista. \n"
                			+ "As remo��es s� desempilham deste topo.");
                	ArrayStack.interface_Pilha();
                    break;
                case 3:
                	System.out.println("\n --- Fila ---:\n"
                			+ "A Fila � uma cole��o de diversos elementos que est�o \n"
                			+ "armazenados com base na ordem de 'chegada', assim como numa fila de pessoas.\n"
                			+ "\nCada elemento acrescentado ser� colocado no final da lista. \n"
                			+ "As remo��es s� ocorrem na frente da lista.");
                	NodeQueue.interface_Fila();
                    break;
                case 4:
                	System.out.println("\n --- Lista de Nodos ---:\n"
                			+ "A Lista de Nodos � uma cole��o de diversos elementos que est�o \n"
                			+ "armazenados numa ordem linear, mas diferente da Lista Arranjo \n"
                			+ "essa lista n�o usa �ndices para armazenar seus elementos e sim, refer�ncias\n"
                			+ "de qual � o pr�ximo nodo ou qual � o nodo anterior ao nodo que est� sendo acessado.");
                	NodePositionList.interface_Lista_Nodos();
                    break;
                case 5:
                	System.out.println("\n --- �rvore Gen�rica ---:\n"
                			+ "A �rvore Gen�rica � uma estrutura hierarquica, onde cada Nodo tem 1 'pai' "
                			+ "e pode ter quantos filhos o usu�rio desejar.\n"
                			+ "\nCada nova inser��o precisar� de uma string para ser armazenada e um nodo 'pai' (exceto a raiz)\n"
                			+ "\nN�o � possivel fazer remo��es na �rvore Gen�rica");
                    //LinkedTree.interface_Arvore_Generica();
                    break;
                case 6:
                	System.out.println("\n --- �rvore Bin�ria ---:\n"
                			+ "A �rvore Bin�ria � uma estrutura hierarquica, onde cada Nodo tem 1 'pai' "
                			+ "e pode ter no m�ximo 2 'filhos'\n"
                			+ "\nCada nova inser��o precisar� de uma string para ser armazenada, \n"
                			+ "um nodo 'pai' e se a nova inser��o vai ser o filho esquerdo ou direito (exceto a raiz)\n"
                			+ "\nAo remover um Nodo, se o mesmo tiver 1 filho ele assumir� a posi��o, \n"
                			+ "mas n�o � possivel remover se o nodo tiver 2 filhos");
                	LinkedBinaryTree.interface_Arvore_Binaria();
                    break;
                case 7:
                	System.out.println("**Texto Explicativo**");
                	SortedListPriorityQueue.interface_Lista_Prioridades();
                    break;
                case 8:
                	System.out.println("**Texto Explicativo**");
                	HashTableMap.interface_Mapa();
                    break;
                case 9:
                	System.out.println("**Texto Explicativo**");
                    HashTableMultiMap.interface_Dicionario();
                    break;
                case 10:
                	System.out.println("**Texto Explicativo**");
                	//BinarySearchTree.interface_Mapa_ABB();
                    break;
                case 11:
                	System.out.println("**Texto Explicativo**");
                	//AVLTreeMap.interface_Mapa_AVL();
                    break;
                case 12:
                	System.out.println("**Texto Explicativo**"
                			+ "Esse aqui � s� explicar, n�o tem a implementa��o");
                    break;
                default:
                    System.out.println("\n****Op��o inv�lida****");
                    break;
            }
            System.out.println("");
        }
	}
}
