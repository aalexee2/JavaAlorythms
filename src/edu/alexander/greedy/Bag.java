package edu.alexander.greedy;

import java.util.ArrayList;
import java.util.List;

public class Bag {
	   private final int maxWeight;
	   private List<Item> items;
	   private int currentWeight;
	   private int currentCost;

	   public Bag(int maxWeight) {
	       this.maxWeight = maxWeight;
	       items = new ArrayList<>();
	       currentCost = 0;
	   }

	   public int getMaxWeight() {
	       return maxWeight;
	   }

	   public int getCurrentCost() {
	       return currentCost;
	   }

	   public int getCurrentWeight() {
	       return currentWeight;
	   }

	   public void addItem(Item item) {
	       items.add(item);
	       currentWeight += item.getWeight();
	       currentCost += item.getCost();
	   }
}
