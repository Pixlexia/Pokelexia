package com.pixlexia.pokelexia;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Pokelist extends Activity {
	ListView listView;
	PokemonAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pokelist);
		
//		ActionBar actionbar = getActionBar();
//		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e12f2f")));
//		actionbar.setTitle("Pokelist");
		
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		pokemon = PokemonDB.list;
		
		adapter = new PokemonAdapter(pokemon, this);
		
		listView = (ListView) findViewById(R.id.pokelist);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
				Intent intent = new Intent(Pokelist.this, PokemonPage.class);
				intent.putExtra("pokemon", Pokelist.this.adapter.getCurrentList().get(position));
				startActivity(intent);
			}
		});
		
		EditText search = (EditText) findViewById(R.id.searchpoke);
		search.setTypeface(Home.book);
		search.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if(count < before)
					Pokelist.this.adapter.resetData();
				Pokelist.this.adapter.getFilter().filter(s);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pokelist, menu);
		return true;
	}
}