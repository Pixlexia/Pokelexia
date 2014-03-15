package com.pixlexia.pokelexia;

import java.util.ArrayList;

public class ItemsDB {
	static ArrayList<Item> list;
	
	public ItemsDB(){
		list = new ArrayList<Item>();
		
		list.add(new Item("Antidote", "A spray-type medicine. Heals the effect of poison from one Pokemon.", 100, IType.medical));
		list.add(new Item("Acro Bike", "An awesome kind of bike! Run two times as fast as running shoes.", 0, IType.key));
		list.add(new Item("Oran Berry", "Heals 10 HP.", 20, IType.berry));
		list.add(new Item("Super Potion", "Heals 50 HP.", 100, IType.medical));
		list.add(new Item("Pokeball", "Used to catch Pokemon.", 50, IType.ball));
		list.add(new Item("Great Ball", "Used to catch Pokemon. Better than an ordinary Pokeball", 150, IType.ball));
		list.add(new Item("Exp. Share", "Shares exp to the holder.", 0, IType.hold));
		list.add(new Item("Amulet Coin", "Doubles money earned from trainer battles.", 0, IType.hold));
		list.add(new Item("Nugget", "Sell for a high price!", 5000, IType.misc));
	}
}
