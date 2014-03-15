package com.pixlexia.pokelexia;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ItemList extends Activity {
	ListView listView;
	EditText search;
	ItemAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
		
//		ActionBar actionbar = getActionBar();
//		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e12f2f")));
//		actionbar.setTitle("Items");
				
		List<Item> items = new ArrayList<Item>();
		
		items = ItemsDB.list;
		
		adapter = new ItemAdapter(items, this);
		
		listView = (ListView) findViewById(R.id.itemlist);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
				Item i = ItemList.this.adapter.getCurrentList().get(position);
				
				AlertDialog.Builder box = new AlertDialog.Builder(ItemList.this);
				
				String buy = (i.buy > 0)? i.buy + "" : "--";
				String sell =  (i.sell > 0)? i.sell + "" : "--";
				
				box.setTitle(i.name);
				box.setMessage(i.description + "\n\nType: " + i.type.toString().toUpperCase() + "\nBuy for: " + buy + "\nSell for: " + sell).setPositiveButton("Ok", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.cancel();
					}
						
				});
				box.show();
			}
		});
		
		search = (EditText) findViewById(R.id.search);
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
					ItemList.this.adapter.resetData();
				}
				ItemList.this.adapter.getFilter().filter(s);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_item_list, menu);
		return true;
	}

}
