package com.pixlexia.pokelexia;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.neopixl.pixlui.components.edittext.EditText;

public class Favorites extends Activity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pokelist);
		
//		ActionBar actionbar = getActionBar();
//		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e12f2f")));
//		actionbar.setTitle("Favorites");
				
		updateFavesList();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		updateFavesList();
	}
	
	public void updateFavesList(){
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		pokemon = PokemonDB.faves;
		
		ArrayAdapter<Pokemon> adapter = new PokemonAdapter(pokemon, this);

		listView = (ListView) findViewById(R.id.pokelist);
		listView.setAdapter(adapter);
		

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
				Intent intent = new Intent(Favorites.this, PokemonPage.class);
				intent.putExtra("pokemon", PokemonDB.faves.get(position));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_favorites, menu);
		return true;
	}

}
