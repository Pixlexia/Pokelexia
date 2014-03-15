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

public class ItemAdapter extends ArrayAdapter<Item> implements Filterable{
	private List<Item> itemList;
	private Context context;
	
	public ItemAdapter(List<Item> itemList, Context c){
		super(c, R.layout.itemlistitem, itemList);
		this.itemList = itemList;
		this.context = c;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		 // verify the convertView is not null
	    if (convertView == null) {
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.itemlistitem, parent, false);
	    }
	        // fill the layout with the right values
	        TextView t1 = (TextView) convertView.findViewById(R.id.name);
	        TextView t2 = (TextView) convertView.findViewById(R.id.type);
	        Item i = itemList.get(position);
	        
	        t1.setText(i.name);
	        t2.setText(i.type.toString().toUpperCase(Locale.US));
	 
	        t1.setTypeface(Home.book);
	        t2.setTypeface(Home.book);
	        t2.setTextColor(getItemTypeColor(i.type));
	    
	    return convertView;
	}
	
	public int getCount(){
		return itemList.size();
	}
	
	public int getItemTypeColor(IType type){
		String color;
		switch(type){
		case medical: color = "#8456f5"; break;
		case berry: color = "#f386a7"; break;
		case key: color = "#ffa518"; break;
		case hold: color = "#81b3ea"; break;
		case misc: color = "#a4a481"; break;
		case ball: color = "#e12f2f"; break;
		default: color = "#222222"; break;
		}
		
		return Color.parseColor(color);
	}
	
	public void resetData(){
    	itemList = ItemsDB.list;
	}
	
	public List<Item> getCurrentList(){
		return itemList;
	}
	
	@Override
	public Filter getFilter(){

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
			@Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemList = (List<Item>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Item> FilteredArrayNames = new ArrayList<Item>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                
                if(constraint == null || constraint.length() == 0){
                	results.values = itemList;
                	results.count = itemList.size();
                }
                else{
                	for (int i = 0; i < itemList.size(); i++) {
                		Item item = itemList.get(i);
                		if (item.name.toLowerCase().contains(constraint.toString()) || item.type.toString().toLowerCase().contains(constraint.toString()))  {
                			FilteredArrayNames.add(item);
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
