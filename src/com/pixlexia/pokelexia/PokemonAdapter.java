package com.pixlexia.pokelexia;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class PokemonAdapter extends ArrayAdapter<Pokemon> implements Filterable{
	private List<Pokemon> pokemonList;
	private Context context;
	
	public PokemonAdapter(List<Pokemon> pokemonList, Context c){
		super(c, R.layout.pokelistitem, pokemonList);
		this.pokemonList = pokemonList;
		this.context = c;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		 // verify the convertView is not null
	    if (convertView == null) {
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.pokelistitem, parent, false);
	    }
	        // fill the layout with the right values
	        TextView tv = (TextView) convertView.findViewById(R.id.name);
	        TextView num = (TextView) convertView.findViewById(R.id.num);
	        TextView t1 = (TextView) convertView.findViewById(R.id.t1);
	        TextView t2 = (TextView) convertView.findViewById(R.id.t2);
	        Pokemon p = pokemonList.get(position);
	 
	        tv.setText(p.name);
	        
	        if(p.num <= 9)
	        	num.setText("00" + p.num);
	        else if(p.num > 9 && p.num < 99){
	        	num.setText("0" + p.num);
	        }
	        
	        t1.setText(p.t1.toString().toUpperCase(Locale.US));
	        t1.setBackgroundColor(PokemonDB.getTypeColor(p.t1));
	        
	        if(p.t2 != Type.blank){
	        	t2.setVisibility(View.VISIBLE);
	        	t2.setText(p.t2.toString().toUpperCase(Locale.US));
	        	t2.setBackgroundColor(PokemonDB.getTypeColor(p.t2));	 
	        }
	        else{
	        	t2.setVisibility(View.INVISIBLE);
	        }
	        
	        tv.setTypeface(Home.book);
	        num.setTypeface(Home.book);
	        t1.setTypeface(Home.book);
	        t2.setTypeface(Home.book);
	    
	    return convertView;
	}
	
	public int getCount(){
		return pokemonList.size();
	}
	
	public void resetData(){
        pokemonList = PokemonDB.list;
	}
	
	public List<Pokemon> getCurrentList(){
		return pokemonList;
	}
	
	@Override
	public Filter getFilter(){

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
			@Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                pokemonList = (List<Pokemon>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Pokemon> FilteredArrayNames = new ArrayList<Pokemon>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                
                if(constraint == null || constraint.length() == 0){
                	results.values = pokemonList;
                	results.count = pokemonList.size();
                }
                else{
                	for (int i = 0; i < pokemonList.size(); i++) {
                		Pokemon poke = pokemonList.get(i);
                		if (poke.name.toLowerCase().contains(constraint.toString()) || poke.t1.toString().toLowerCase().contains(constraint.toString()) || poke.t2.toString().toLowerCase().contains(constraint.toString()))  {
                			FilteredArrayNames.add(poke);
                		}
                	}                	
                	results.count = FilteredArrayNames.size();
                	results.values = FilteredArrayNames;
                }


                return results;
            }
        };

        return filter;
	}
}
