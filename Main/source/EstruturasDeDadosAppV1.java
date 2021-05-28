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
                    System.exit(-1);
                    break;
                case 1:
                	System.out.println("**Texto Explicativo**");
                	ArrayIndexList.interface_Lista_Arranjo();
                    break;
                case 2:
                	System.out.println("**Texto Explicativo**");
                	ArrayStack.interface_Pilha();
                    break;
                case 3:
                	System.out.println("**Texto Explicativo**");
                	NodeQueue.interface_Fila();
                    break;
                case 4:
                	System.out.println("**Texto Explicativo**");
                	NodePositionList.interface_Lista_Nodos();
                    break;
                case 5:
                	System.out.println("**Texto Explicativo**");
                    LinkedTree.interface_Arvore_Generica();
                    break;
                case 6:
                	System.out.println("**Texto Explicativo**");
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
                	BinarySearchTree.interface_Mapa_ABB();
                    break;
                case 11:
                	System.out.println("**Texto Explicativo**");
                	AVLTreeMap.interface_Mapa_AVL();
                    break;
                case 12:
                	System.out.println("**Texto Explicativo**"
                			+ "Esse aqui é só explicar, não tem a implementação");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
            System.out.println("");
        }
	}
}
