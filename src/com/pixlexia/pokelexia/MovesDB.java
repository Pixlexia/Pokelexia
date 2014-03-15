package com.pixlexia.pokelexia;

import java.util.ArrayList;

public class MovesDB {
	static ArrayList<Move> list;
	
	public MovesDB(){
		list = new ArrayList<Move>();

		list.add(new Move("Pound", "Pounds the foe with forelegs or tail.", Type.normal, MType.physical, 35, 40, 100));
		list.add(new Move("Karate Chop", "Hits the foe with a karate chop.", Type.fight, MType.physical, 10, 60, 100));
		list.add(new Move("Fire Blast", "Hits the foe with blazing things.", Type.fire, MType.special, 5, 100, 50));
		list.add(new Move("Razor Leaf", "A sharp-edged leaf is launched to slash at the foe. It has a high critical-hit ratio.", Type.grass, MType.physical, 15, 50, 75));
		list.add(new Move("Solar Beam", "A two-turn attack. The user gathers light, then blasts a bundled beam on the second turn.", Type.grass, MType.special, 10, 120, 100));
		list.add(new Move("Toxic", "A move that leaves the target badly poisoned. Its poison damage worsens every turn.", Type.poison, MType.special, 10, 0, 90));
		list.add(new Move("Confuse Ray", "The foe is exposed to a sinister ray that triggers confusion.", Type.ghost, MType.special, 10, 0, 100));
		list.add(new Move("Will-o-Wisp", "The user shoots a sinister, bluish-white flame at the target to inflict a burn.", Type.fire, MType.special, 15, 0, 85));
	}
}
