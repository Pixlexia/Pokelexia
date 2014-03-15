package com.pixlexia.pokelexia;

import java.io.Serializable;

public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name, description;
	int buy, sell;
	IType type;
	
	public Item(String name, String desc, int buy, IType it){
		this.name = name;
		this.description = desc;
		this.buy = buy;
		this.sell = buy/2;
		this.type = it;
	}
}
