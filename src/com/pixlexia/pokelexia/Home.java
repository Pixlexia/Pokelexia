package com.pixlexia.pokelexia;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Activity {
	public static Typeface bold, light, book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		new PokemonDB();
		new ItemsDB();
		new MovesDB();
		
//		ActionBar actionbar = getActionBar();
//		actionbar.hide();
		
		bold = Typeface.createFromAsset(getAssets(), "fonts/gothambold.otf");
		light = Typeface.createFromAsset(getAssets(), "fonts/gothamlight.otf");
		book = Typeface.createFromAsset(getAssets(), "fonts/gothambook.otf");
		TextView tv = (TextView) findViewById(R.id.title);
		
		Button b1 = (Button) findViewById(R.id.gotopokelist);
		Button b2 = (Button) findViewById(R.id.gotoitems);
		Button b3 = (Button) findViewById(R.id.gotomoves);
		Button b4 = (Button) findViewById(R.id.gotofavorites);
		b1.setTypeface(light);
		b2.setTypeface(light);
		b3.setTypeface(light);
		b4.setTypeface(light);
		
		tv.setTypeface(bold);
	}
	
	public void gotoPokelist(View view){
		Intent intent = new Intent(getApplicationContext(), Pokelist.class);
		startActivity(intent);
	}
	
	public void gotoMoves(View view){
		Intent intent = new Intent(getApplicationContext(), Moves.class);
		startActivity(intent);
	}
	
	public void gotoItems(View view){
		Intent intent = new Intent(getApplicationContext(), ItemList.class);
		startActivity(intent);
	}
	
	public void gotoFavorites(View view){
		Intent intent = new Intent(getApplicationContext(), Favorites.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}