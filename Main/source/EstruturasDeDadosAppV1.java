package source;

import java.util.Scanner;

import tad_arvore_binaria.LinkedBinaryTree;
import tad_arvore_genérica.LinkedTree;
import tad_dicionário.HashTableMultiMap;
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
            System.out.println("-------- Aplicação de Criação de Estruturas de Dados --------\n");
            Scanner input = new Scanner(System.in);
            System.out.println("[1] TAD Lista Arranjo		[2] TAD Pilha\n"
                              +"[3] TAD Fila			[4] TAD Lista de Nodos\n"
                              +"[5] TAD Árvore Genética		[6] TAD Árvore Binária\n"
                              +"[7] TAD Fila de Prioridade	[8] TAD Mapa\n"
                              +"[9] TAD Dicionário		[10] TAD Mapa Ordenado ABB\n"
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
                			+ "A Lista Arranjo é uma coleção de diversos elementos que estão armanezados numa ordem linear \n"
                			+ "de forma com que os valores podem ser acessados pelos índices que vão de 0 até quanto o usuário querer.\n"
                			+ "Cada nova inserção precisará de um índice e de um valor que será armazenado.\n"
                			+ "Ao remover, só será necessário o índice do valor que será removido.");
                	ArrayIndexList.interface_Lista_Arranjo();
                    break;
                case 2:
                	System.out.println("\n --- Pilha ---:\n"
                			+ "A Pilha é uma coleção de diversos elementos que estão \n"
                			+ "armazenados com base na ordem de inserção, assim como numa pilha de pratos/copos.\n"
                			+ "\nCada novo elemento empilhado será colocado no 'topo' da lista. \n"
                			+ "As remoções só desempilham deste topo.");
                	ArrayStack.interface_Pilha();
                    break;
                case 3:
                	System.out.println("\n --- Fila ---:\n"
                			+ "A Fila é uma coleção de diversos elementos que estão \n"
                			+ "armazenados com base na ordem de 'chegada', assim como numa fila de pessoas.\n"
                			+ "\nCada elemento acrescentado será colocado no final da lista. \n"
                			+ "As remoções só ocorrem na frente da lista.");
                	NodeQueue.interface_Fila();
                    break;
                case 4:
                	System.out.println("\n --- Lista de Nodos ---:\n"
                			+ "A Lista de Nodos é uma coleção de diversos elementos que estão \n"
                			+ "armazenados numa ordem linear, mas diferente da Lista Arranjo \n"
                			+ "essa lista não usa índices para armazenar seus elementos e sim, referências\n"
                			+ "de qual é o próximo nodo ou qual é o nodo anterior ao nodo que está sendo acessado.");
                	NodePositionList.interface_Lista_Nodos();
                    break;
                case 5:
                	System.out.println("\n --- Árvore Genérica ---:\n"
                			+ "A Árvore Genérica é uma estrutura hierarquica, onde cada Nodo tem 1 'pai' "
                			+ "e pode ter quantos filhos o usuário desejar.\n"
                			+ "\nCada nova inserção precisará de uma string para ser armazenada e um nodo 'pai' (exceto a raiz)\n"
                			+ "\nNão é possivel fazer remoções na Árvore Genérica");
                    //LinkedTree.interface_Arvore_Generica();
                    break;
                case 6:
                	System.out.println("\n --- Árvore Binária ---:\n"
                			+ "A Árvore Binária é uma estrutura hierarquica, onde cada Nodo tem 1 'pai' "
                			+ "e pode ter no máximo 2 'filhos'\n"
                			+ "\nCada nova inserção precisará de uma string para ser armazenada, \n"
                			+ "um nodo 'pai' e se a nova inserção vai ser o filho esquerdo ou direito (exceto a raiz)\n"
                			+ "\nAo remover um Nodo, se o mesmo tiver 1 filho ele assumirá a posição, \n"
                			+ "mas não é possivel remover se o nodo tiver 2 filhos");
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
                			+ "Esse aqui é só explicar, não tem a implementação");
                    break;
                default:
                    System.out.println("\n****Opção inválida****");
                    break;
            }
            System.out.println("");
        }
	}
}
