package com.pixlexia.pokelexia;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class MovesAdapter extends ArrayAdapter<Move> implements Filterable{
	private List<Move> moveList;
	private Context context;
	
	public MovesAdapter(List<Move> moveList, Context c){
		super(c, R.layout.movelistitem, moveList);
		this.moveList = moveList;
		this.context = c;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		 // verify the convertView is not null
	    if (convertView == null) {
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.movelistitem, parent, false);
	    }
	        // fill the layout with the right values
	        TextView tv = (TextView) convertView.findViewById(R.id.name);
	        TextView t1 = (TextView) convertView.findViewById(R.id.t1);
	        TextView t2 = (TextView) convertView.findViewById(R.id.t2);
	        Move m = moveList.get(position);
	 
	        tv.setText(m.name);
	        t1.setText(m.type.toString().toUpperCase(Locale.US));
	        t2.setText(m.mtype.toString().toUpperCase(Locale.US));
	        
	        t1.setBackgroundColor(PokemonDB.getTypeColor(m.type));
	        
	        int color = (m.mtype == MType.physical)? Color.parseColor("#ff2222") :Color.parseColor("#555555");
	        
	        t2.setBackgroundColor(color);
	        
	        tv.setTypeface(Home.book);
	        t1.setTypeface(Home.book);
	        t2.setTypeface(Home.book);
	    
	    return convertView;
	}
	
	public int getCount(){
		return moveList.size();
	}
	
	public void resetData(){
        moveList = MovesDB.list;
	}
	
	public List<Move> getCurrentList(){
		return moveList;
	}
	
	@Override
	public Filter getFilter(){

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
			@Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                moveList = (List<Move>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Move> FilteredArrayNames = new ArrayList<Move>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                
                if(constraint == null || constraint.length() == 0){
                	results.values = moveList;
                	results.count = moveList.size();
                }
                else{
                	for (int i = 0; i < moveList.size(); i++) {
                		Move move = moveList.get(i);
                		if (move.name.toLowerCase().contains(constraint.toString()) || move.mtype.toString().toLowerCase().contains(constraint.toString()) || move.type.toString().toLowerCase().contains(constraint.toString()))  {
                			FilteredArrayNames.add(move);
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
