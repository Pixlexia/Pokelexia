package com.pixlexia.pokelexia;

import java.util.ArrayList;

import android.graphics.Color;

@SuppressWarnings("serial")
public class PokemonDB extends ArrayList<Pokemon>{
	
	public static ArrayList<Pokemon> list;
	public static ArrayList<Pokemon> faves;

	public PokemonDB(){
		list = new ArrayList<Pokemon>();
		faves = new ArrayList<Pokemon>();
		
		list.add(new Pokemon("Bulbasaur", Type.grass, Type.poison, list.size()+1, 0, 16));
		list.add(new Pokemon("Ivysaur", Type.grass, Type.poison, list.size()+1, 16, 36 ));
		list.add(new Pokemon("Venusaur", Type.grass, Type.poison, list.size()+1, 36, 0));
		list.add(new Pokemon("Charmander", Type.fire, list.size()+1, 0, 16));
		list.add(new Pokemon("Charmeleon", Type.fire, list.size()+1, 16, 36));
		list.add(new Pokemon("Charizard", Type.fire, Type.flying, list.size()+1, 36, 0));
		list.add(new Pokemon("Squirtle", Type.water, list.size()+1, 0, 16));
		list.add(new Pokemon("Wartortle", Type.water, list.size()+1, 16, 36));
		list.add(new Pokemon("Blastoise", Type.water, list.size()+1, 36, 0));
		list.add(new Pokemon("Caterpie", Type.bug, list.size()+1));
		list.add(new Pokemon("Metapod", Type.bug, list.size()+1));
		list.add(new Pokemon("Butterfree", Type.bug, Type.flying, list.size()+1));
		list.add(new Pokemon("Weedle", Type.bug, Type.poison, list.size()+1));
		list.add(new Pokemon("Kakuna", Type.bug, Type.poison, list.size()+1));
		list.add(new Pokemon("Beedrill", Type.bug, Type.poison, list.size()+1));
		list.add(new Pokemon("Pidgey", Type.normal, Type.flying, list.size()+1, 10, 0));
		list.add(new Pokemon("Pidgeotto", Type.normal, Type.flying, list.size()+1, 10, 30));
		list.add(new Pokemon("Pidgeot", Type.normal, Type.flying, list.size()+1, 30, 0));
		
		faves.add(list.get(1));
//		faves.add(list.get(4));
//		faves.add(list.get(7));
	}
	
	public static int getTypeColor(Type type){
		String color;
		switch(type){
		case grass: color = "#a5e65f"; break;
		case fire: color = "#ffa518"; break;
		case fight: color = "#e12f2f"; break;
		case ground: color = "#e0c068"; break;
		case psych: color = "#f386a7"; break;
		case rock: color = "#bfaf6a"; break;
		case ice: color = "#94e2e2"; break;
		case water: color = "#81b3ea"; break;
		case bug: color = "#bcc477"; break;
		case dragon: color = "#8456f5"; break;
		case poison: color = "#ad68ad"; break;
		case ghost: color = "#7a669a"; break;
		case dark: color = "#634d3e"; break;
		case steel: color = "#b8b8d0"; break;
		case normal: color = "#a4a481"; break;
		case flying: color = "#aba1f4"; break;
		case electr: color = "#000000"; break;
		
		default: color = "#000000"; break;
		}
		
		return Color.parseColor(color);
	}
}
