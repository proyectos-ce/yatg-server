package Troncycle;

import Structures.*;

public class Troncycle {
	private Player owner;
	private int fuel, speed;
	private Direction currentDirection;
	private int extraTrail;
	// public PriorityQueue items;
	// public Stack powerUps;
	// public Nodo tail, head;
	private GenericLinkedList<Item> trail;
	private ItemsPriorityQueue itemsQueue = new ItemsPriorityQueue();

	public Troncycle(Player owner,int indexI, int indexJ) {
		this.owner = owner;
		this.fuel = 100;
		this.speed = 1;
		this.trail = new GenericLinkedList<>();
		this.extraTrail = 6;
		this.addHead(indexI, indexJ);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public void addFuel(int fuel) {
		this.fuel += fuel;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}
	

	public GenericLinkedList<Item> getTrail() {
		return trail;
	}

	public void setTrail(GenericLinkedList<Item> trail) {
		this.trail = trail;
	}

	public int getExtraTrail() {
		return extraTrail;
	}

	public void setExtraTrail(int extraTrail) {
		this.extraTrail = extraTrail;
	}

	public void addExtraTrail(int extraTrail) {
		this.extraTrail += extraTrail;
	}
/*
	public GenericNode<Item> getHead() {
		return trail.getHead();
	}*/

	public Item deleteTail() {
		Item deleted = null;

		if (trail.getSize() > 1) {
			
			deleted = trail.deleteAtPosition(trail.getSize() - 1);
		}
		return deleted;
	}

	
/*
	public void addHead() {
		Item first = this.getHead().getData();
		int indexI = first.getIndexI();
		int indexJ = first.getIndexJ();

		switch (currentDirection) {// necesario agregar casos especiales
		case down:
			indexI--;
			break;
		case up:
			indexI++;

			break;
		case left:
			indexJ--;

			break;
		case right:
			indexJ++;

			break;

		default:
			break;
		}

		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}*/

	public void addHead(int indexI, int indexJ) {
		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}

	public void addHead(Item item) {
		GenericNode<Item> newHead = new GenericNode<>(item);
		if(!trail.isEmpty()){
		trail.getHead().getData().setIsHead(false);}
		trail.setHead(newHead);
	}

	public Item move(int indexI, int indexJ) {
		Item deleted = null;
		addHead( indexI,  indexJ);
		if (getExtraTrail() > 0) {
			addExtraTrail(-1);
		} else {
			deleted = deleteTail();
		}
		return deleted;
	}
	public void addItem(Item newItem){
		itemsQueue.add(newItem);
	}

	public void useItem() {
		if (!itemsQueue.isEmpty()) {
			Item itemToUse = itemsQueue.poll();
			switch (itemToUse.getType()) {
			case bomb:

				break;
			case fuel:
				this.addFuel(itemToUse.getValue());

				break;
			case increaseTail:
				this.addExtraTrail(itemToUse.getValue());

				break;
			case shield:

				break;
			case turbo:

				break;

			default:
				break;
			}
		}

	}

}
