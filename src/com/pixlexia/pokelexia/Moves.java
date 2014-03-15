package com.pixlexia.pokelexia;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Moves extends Activity {
	ListView listView;
	MovesAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moves);
		
//		ActionBar actionbar = getActionBar();
//		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e12f2f")));
//		actionbar.setTitle("Moves");
		
		List<Move> moves = new ArrayList<Move>();
		
		moves = MovesDB.list;
		
		adapter = new MovesAdapter(moves, this);
		
		listView = (ListView) findViewById(R.id.moveslist);
		listView.setAdapter(adapter);
	
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
				Move m = Moves.this.adapter.getCurrentList().get(position);
				
				AlertDialog.Builder box = new AlertDialog.Builder(Moves.this);
				
				String power = (m.power > 0)? m.power + "" : "--";
				String accuracy = (m.accuracy> 0)? m.accuracy+ "" : "--";
				
				box.setTitle(m.name);
				box.setMessage(m.desc + "\n\nPP: " + m.pp + "\nPower: " + power + "\nAccuracy: " + accuracy +"%").setPositiveButton("Ok", new DialogInterface.OnClickListener(){
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.cancel();
					}
						
				});
				box.show();
			}
		});

		EditText search = (EditText) findViewById(R.id.searchmove);
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
				if(count < before){
					Moves.this.adapter.resetData();
				}
				Moves.this.adapter.getFilter().filter(s);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_moves, menu);
		return true;
	}

}
