package edu.alexander.greedy;

public class Item implements Comparable<Item> {
	 private String name;
	   private int weight;
	   private int cost;
	   public Item(String name, int weight, int cost) {
	       this.name = name;
	       this.weight = weight;
	       this.cost = cost;
	   }

	   public String getName() {
	       return name;
	   }

	   public int getWeight() {
	       return weight;
	   }

	   public int getCost() {
	       return cost;
	   }
	@Override
	public int compareTo(Item o) {
		 return this.cost > o.cost ? -1 : 1;
	}

}
