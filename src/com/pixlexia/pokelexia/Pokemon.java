package com.pixlexia.pokelexia;

import java.io.Serializable;

public class Pokemon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int num;
	public Type t1, t2;
	public String imgFilename;
	public int evolveFromLvl, evolveToLvl;
	
	public Pokemon(String name, Type t1, int num){
		this.name = name;
		this.num = num;
		this.t1 = t1;
		this.t2 = Type.blank;
		
		evolveFromLvl = 0;
		evolveToLvl = 0;
	}
	
	public Pokemon(String name, Type t1, Type t2, int num){
		this.name = name;
		this.num = num;
		this.t1 = t1;
		this.t2 = t2;

		evolveFromLvl = 0;
		evolveToLvl = 0;
	}
	
	public Pokemon(String name, Type t1, int num, int evFromLvl, int evToLvl){
		this.name = name;
		this.num = num;
		this.t1 = t1;
		this.t2 = Type.blank;
		
		evolveFromLvl = evFromLvl;
		evolveToLvl = evToLvl;
	}
	
	public Pokemon(String name, Type t1, Type t2, int num, int evFromLvl, int evToLvl){
		this.name = name;
		this.num = num;
		this.t1 = t1;
		this.t2 = t2;

		evolveFromLvl = evFromLvl;
		evolveToLvl = evToLvl;
	}

}
