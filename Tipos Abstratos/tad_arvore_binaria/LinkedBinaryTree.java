package tad_arvore_binaria;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;
import exceptions.InvalidPositionException;
import exceptions.NonEmptyTreeException;
import interfaces.BinaryTree;
import interfaces.Position;
import interfaces.PositionList;
import tad_lista_de_nodos.NodePositionList;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
    protected BTPosition<E> root;

    protected int size;

    public LinkedBinaryTree() {
        root = null; 
        size = 0;
    }

    public int size() {
        return size;
    }
    
    public static void interface_Arvore_Binaria() {
		LinkedBinaryTree<String> ArvoreBinaria = new LinkedBinaryTree<String>();
		boolean exit = false, msg = false;
		Scanner input = new Scanner(System.in);
		
		while (!exit) {
			System.out.print("\n --- Interface de Usuário ---:\n"
					+ "[0] Voltar para o Menu (Estrutura atual será limpa)\n"
					+ "[1] Adicionar\n"
					+ "[2] Remover\n"
					+ "[3] Visualizar\n"
					+ "Digite a opção: ");
			int opc = input.nextInt();
			switch(opc) {
				case 0:
					exit = true;
					break;

				case 1:
					System.out.print("\nDigite a String que será guardada na árvore: ");
					input.nextLine();
					String key = input.nextLine();
					
					if (ArvoreBinaria.isEmpty()) {
						ArvoreBinaria.addRoot(key);
						System.out.println("\nRaiz adicionada: " + key);
					} else {
						System.out.print("\nA String precisa de um nodo pai para ser adicionada, "
									   + "digite a String de um nodo: ");
						String paiElem = input.nextLine();
						Position<String> paiPos = null;
						
						for (Position<String> w : ArvoreBinaria.positions()) {
							if (paiElem.equals(w.element()))
								paiPos = w;
						}
						
						try {
							ArvoreBinaria.checkPosition(paiPos);
						} catch (InvalidPositionException e) {
							System.out.println("****" + paiElem + " não existe na Árvore****");
							break;
						}
						
						if (ArvoreBinaria.hasRight(paiPos) && ArvoreBinaria.hasLeft(paiPos)) {
							System.out.print("****A posição ja tem definidos ambos os filhos****");
							break;
						}
						
						System.out.print("Deseja adicionar no filho da esquerda ou da direita? [E/D] ");
						String filho = input.nextLine();
						
						if (filho.equals("D") || filho.equals("d")) {
							if (ArvoreBinaria.hasRight(paiPos)) {
								System.out.println("\n****Filho direito de '" + paiElem + "' está ocupado pelo elemento '" 
												 + ArvoreBinaria.right(paiPos).element() + "'****");
								break;
							}
							ArvoreBinaria.insertRight(paiPos, key);
							System.out.println("\nA String '" + key + "' foi adicionada no filho da direita de '" + paiElem + "'");
						} 
						else if (filho.equals("E") || filho.equals("e")) {
							if (ArvoreBinaria.hasLeft(paiPos)) {
								System.out.println("\n****Filho esquerdo de '" + paiElem + "' está ocupado pelo elemento '" 
											     + ArvoreBinaria.left(paiPos).element() + "'****");
								break;
							}
							ArvoreBinaria.insertLeft(paiPos, key);
							System.out.println("\nA String '" + key + "' foi adicionada no filho da esquerda de '" + paiElem + "'");
						} else
							System.out.println("\n****Opção inválida****");
					}
					break;

				case 2:
					if (!msg) {
						System.out.print("\nCaso haja 2 ou mais Strings com o mesmo nome, será removido a ocorrência mais a direita da Árvore");
						msg = true;
					}
					System.out.print("\nDigite a String da posição que deseja remover: ");
					input.nextLine();
					String removeElem = input.nextLine();
					Position<String> removePos = null;
					
					for (Position<String> w : ArvoreBinaria.positions()) {
						if (removeElem.equals(w.element()))
							removePos = w;
					}
					
					try {
						ArvoreBinaria.checkPosition(removePos);
					} catch (InvalidPositionException e) {
						System.out.println("\n****" + removeElem + " não existe na Árvore****");
						break;
					}
					
					if (ArvoreBinaria.hasRight(removePos) && ArvoreBinaria.hasLeft(removePos)) {
						System.out.println("\n****Não é possivel remover um nodo que tenha 2 filhos****");
						break;
					}
					
					ArvoreBinaria.remove(removePos);
					System.out.println("\nO elemento " + removeElem + " foi removido da Árvore");
					break;

				case 3:
					try {
						System.out.print("\nArvore Binária atual: ");
						ArvoreBinaria.printExpression(ArvoreBinaria, ArvoreBinaria.root());
					} catch (EmptyTreeException e) {
						System.out.println("****Árvore Binária vazia****");
						break;
					}
					System.out.println("");
					break;

				default:
					System.out.println("\n****Opção inválida****");
			}
		}
	}
    
    public void printExpression(LinkedBinaryTree<E> T, Position<E> v) {
        if(T.isInternal(v)) System.out.print("(");
        if(T.hasLeft(v)) printExpression(T, T.left(v));
        if(T.isInternal(v)) {
            System.out.print("-" + v.element() + "-");
        }
        else {
            System.out.print(v.element());
        }
        if(T.hasRight(v)) printExpression(T, T.right(v));
        if(T.isInternal(v)) System.out.print(")");
	}

    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        checkPosition(v);
        return (hasLeft(v) || hasRight(v));
    }

    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        checkPosition(v);
        return (v == root());

    }

    public boolean hasLeft(Position<E> v) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getLeft() != null);
    }

    public Position<E> root() throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("The tree is empty");
        }
        return root;
    }

    public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = (Position<E>) vv.getLeft();
        if (leftPos == null) {
            throw new BoundaryViolationException("No left child");
        }
        return leftPos;
    }

    public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> parentPos = (Position<E>) vv.getParent();
        if (parentPos == null) {
            throw new BoundaryViolationException("No parent");
        }
        return parentPos;
    }

    public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
        PositionList<Position<E>> children = new NodePositionList<Position<E>>();
        if (hasLeft(v)) {
            children.addLast(left(v));
        }
        if (hasRight(v)) {
            children.addLast(right(v));
        }
        return children;
    }

    public Iterable<Position<E>> positionsInorder() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if (size != 0)
			inorderPositions(root(), positions); // atribui as posições usando caminhamento prefixado
		return positions;
    }

    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
        if (size != 0) {
            preorderPositions(root(), positions); 
        }
        return positions;
    }

    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = positions();
        PositionList<E> elements = new NodePositionList<E>();
        for (Position<E> pos : positions) {
            elements.addLast(pos.element());
        }
        return elements.iterator(); 
    }

    public E replace(Position<E> v, E o) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        E temp = v.element();
        vv.setElement(o);
        return temp;
    }

    public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> parentPos = vv.getParent();
        if (parentPos != null) {
            BTPosition<E> sibPos;
            BTPosition<E> leftPos = parentPos.getLeft();
            if (leftPos == vv) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new BoundaryViolationException("No sibling");
    }

    public Position<E> addRoot(E e) throws NonEmptyTreeException {
        if (!isEmpty()) {
            throw new NonEmptyTreeException("Tree already has a root");
        }
        size = 1;
        root = createNode(e, null, null, null);
        return root;
    }

    public Position<E> insertLeft(Position<E> v, E e) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = (Position<E>) vv.getLeft();
        if (leftPos != null) {
            throw new InvalidPositionException("Node already has a left child");
        }
        BTPosition<E> ww = createNode(e, vv, null, null);
        vv.setLeft(ww);
        size++;
        return ww;
    }
    
    public Position<E> insertRight(Position<E> v, E e) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = (Position<E>) vv.getRight();
        if (rightPos != null) {
            throw new InvalidPositionException("Node already has a right child");
        }
        BTPosition<E> ww = createNode(e, vv, null, null);
        vv.setRight(ww);
        size++;
        return ww;
    }

    public E remove(Position<E> v) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> leftPos = vv.getLeft();
        BTPosition<E> rightPos = vv.getRight();
        if (leftPos != null && rightPos != null) {
            throw new InvalidPositionException("Cannot remove node with two children");
        }
        BTPosition<E> ww; 
        if (leftPos != null) {
            ww = leftPos;
        } else if (rightPos != null) {
            ww = rightPos;
        } else
        {
            ww = null;
        }
        if (vv == root) {
            if (ww != null) {
                ww.setParent(null);
            }
            root = ww;
        } else { 
            BTPosition<E> uu = vv.getParent();
            if (vv == uu.getLeft()) {
                uu.setLeft(ww);
            } else {
                uu.setRight(ww);
            }
            if (ww != null) {
                ww.setParent(uu);
            }
        }
        size--;
        return v.element();
    }

    public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        if (isInternal(v)) {
            throw new InvalidPositionException("Cannot attach from internal node");
        }
        if (!T1.isEmpty()) {
            BTPosition<E> r1 = checkPosition(T1.root());
            vv.setLeft(r1);
            r1.setParent(vv);
        }
        if (!T2.isEmpty()) {
            BTPosition<E> r2 = checkPosition(T2.root());
            vv.setRight(r2);
            r2.setParent(vv);
        }
    }

    protected BTPosition<E> checkPosition(Position<E> v) throws InvalidPositionException {
        if (v == null || !(v instanceof BTPosition)) {
            throw new InvalidPositionException("The position is invalid");
        }
        return (BTPosition<E>) v;
    }

    protected BTPosition<E> createNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
        return new BTNode<E>(element, parent, left, right);
    }

    protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
        pos.addLast(v);
        if (hasLeft(v)) {
            preorderPositions(left(v), pos);
        }
        if (hasRight(v)) {
            preorderPositions(right(v), pos);
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isExternal(Position<E> v) throws InvalidPositionException {
        return !isInternal(v);
    }

    public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = (Position<E>) vv.getRight();
        if (rightPos == null) {
            throw new BoundaryViolationException("No right child");
        }
        return rightPos;
    }

    public boolean hasRight(Position<E> v) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getRight() != null);
    }

    public void inorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		if (hasLeft(v))
			inorderPositions(left(v), pos); // recursão sobre o filho da esquerda
		pos.addLast(v);
		if (hasRight(v))
			inorderPositions(right(v), pos); // recursão sobre o filho da direita
	}
}
