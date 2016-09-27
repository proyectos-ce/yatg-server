package Malla;

import Structures.*;
import Troncycle.Troncycle;

public class LinkedMatrix {
	public Nodo head;
	public int numRows, numCols, numNodes;
	
	public LinkedMatrix(int numRows, int numCols){
		this.head = null;
		this.numRows = numRows;
		this.numCols = numCols;
		this.numNodes = numRows * numCols;

		simpleUnlinkedList();	
		return;
	}
	
	public Nodo simpleUnlinkedList(){
		int i, jj;
		int nodos =1;
		this.head = new Nodo();
		Nodo temp = this.head;
		while(nodos < this.numNodes) {
			for(i =0; i < this.numRows; i++){
				if(i==0)jj=1;
				else{jj=0;}
				for(int j = jj; j< this.numCols ; j++){
					Nodo nuevoNodo = new Nodo();
					nuevoNodo.setIndex(i, j);
					temp.setNext(nuevoNodo);
					temp = temp.getNext();
					nodos ++;
					if(nodos == this.numNodes)break;
			}
				if(nodos == this.numNodes)break;			
		}		
	}

		fourWayLinkedList();
		return this.head;}
	
	public Nodo getNodo(int indexI, int indexJ){
		Nodo temp = this.head;
		Nodo result = null;
		while(temp != null){
			if(temp.getIndexI() == indexI && temp.getIndexJ() == indexJ){
				result = temp;
				break;
			}
			temp = temp.getNext();		
		}
		if(result == null){
			System.out.println("No esta el indice");			
		}
		return result;		
	}
	
	public void fourWayLinkedList(){
		Nodo temp = this.head;
		// up
		while(temp != null){
			if(temp.getIndexI() == 0) {temp.setUp(getNodo(this.numRows-1, temp.getIndexJ()));		}
			else{temp.setUp(getNodo(temp.getIndexI()-1, temp.getIndexJ()));							}
			temp = temp.getNext();		
		}
		
		//down
		temp = this.head;
		while(temp != null){
			if(temp.getIndexI() == this.numRows-1) {temp.setDown(getNodo(0, temp.getIndexJ()));		}
			else{temp.setDown(getNodo(temp.getIndexI()+1, temp.getIndexJ()));						}

			temp = temp.getNext();		
		}
		
		//right
		temp = this.head;
		while(temp != null){
			if(temp.getIndexJ() == this.numCols-1) {temp.setRight(getNodo(temp.getIndexI(), 0));		}
			else{temp.setRight(getNodo(temp.getIndexI(), temp.getIndexJ()+1));							}

			temp = temp.getNext();		
		}
		
		//left
		temp = this.head;
		while(temp != null){
			if(temp.getIndexJ() == 0) {temp.setLeft(getNodo(temp.getIndexI(), this.numCols-1));		}
			else{temp.setLeft(getNodo(temp.getIndexI(), temp.getIndexJ()-1));							}

			temp = temp.getNext();		
		}


		}

	public void displayList(){
		//Nodo temp = this.head;
		//while(temp != null){
			for(int i =0; i<this.numRows; i++){
				for(int j=0; j<this.numCols; j++){
					getNodo(i, j).getIndex();
					if(getNodo(i,j).getItem()!=null){
						System.out.print(getNodo(i,j).getItem().getType());
					}
				}
				System.out.println();
			}		
		}

	public Nodo getIndexNode(int indexI, int indexJ){
		Nodo current = this.head;
		if (checkIndexI(indexI)&& checkIndexJ(indexJ)){
		while(indexI>0){
			current = current.getDown();
			indexI--;
		}
		while(indexJ>0){
			current = current.getRight();
			indexJ--;
		}
		}
		return current;

	}
	public void setNodeItem(Item item){
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getIndexNode(indexI, indexJ).setItem(item);
	}
	
	public void resetNodeItem(int indexI, int indexJ){
		getIndexNode(indexI, indexJ).setItem(null);
	}
	public void resetNodeItem(Item item){
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getIndexNode(indexI, indexJ).setItem(null);
	}
	
	public boolean checkIndexI(int indexI){
		boolean result = false;
		if(0<=indexI && indexI<this.numCols){
			result = true;
		}
		return result;
	}
	
	public boolean checkIndexJ(int indexJ){
		boolean result = false;
		if(0<=indexJ && indexJ<this.numCols){
			result = true;
		}
		return result;
	}
	public void updatePlayer(Troncycle player){
		GenericNode<Item> current = player.getTrail().getHead();
		while(current != null){
			this.resetNodeItem(current.getData());
			current=current.getNext();
		}
		
		player.move();
		current = player.getTrail().getHead();
		while(current != null){
			this.setNodeItem(current.getData());
			System.out.println("i  "+current.getData().getIndexI()+"  j  "+current.getData().getIndexI());
			current=current.getNext();
		}
	}
	
}
