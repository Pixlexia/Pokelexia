package com.pixlexia.pokelexia;

import java.io.Serializable;

public class Move implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name, desc;
	Type type;
	MType mtype;
	int pp, power, accuracy;
	
	public Move(String n, String d, Type t, MType mc, int pp, int p, int a){
		name = n;
		desc = d;
		type = t;
		mtype = mc;
		
		this.pp = pp;
		power = p;
		accuracy = a;
	}
}