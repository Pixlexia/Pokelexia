package com.pixlexia.pokelexia;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class PokemonPage extends Activity {
	
	Pokemon p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pokemon_page);

//		ActionBar actionbar = getActionBar();
//		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e12f2f")));
//		actionbar.setTitle("Pokemon");
		
		p = (Pokemon) getIntent().getSerializableExtra("pokemon");
		TextView tvname = (TextView) findViewById(R.id.pokename);
		TextView tvnum = (TextView) findViewById(R.id.pokenum);
		TextView tvtype1 = (TextView) findViewById(R.id.t1);
		TextView tvtype2 = (TextView) findViewById(R.id.t2);

		TextView lheight = (TextView) findViewById(R.id.labelheight);
		TextView lweight = (TextView) findViewById(R.id.labelweight);
		TextView levs = (TextView) findViewById(R.id.labelevs);
		TextView labilities = (TextView) findViewById(R.id.labelabilities);
		TextView ldamages = (TextView) findViewById(R.id.labelweaknesses);
		TextView levolution = (TextView) findViewById(R.id.labelevolution);
		TextView evo2 = (TextView) findViewById(R.id.levelto);
		TextView evo1 = (TextView) findViewById(R.id.levelfrom);
		TextView evolabel1 = (TextView) findViewById(R.id.labelevoto);
		TextView evolabel2 = (TextView) findViewById(R.id.labelevofrom);
		
		// image
		ImageView img = (ImageView) findViewById(R.id.pokemonimg);
		int imgResource = getResources().getIdentifier("p" + p.num, "drawable", getPackageName());
		img.setImageResource(imgResource);
//		img.setImageDrawable(getResources().getDrawable(imgResource));
		
		// evolution
		if(p.evolveFromLvl != 0){
			Pokemon poke = PokemonDB.list.get(p.num-2);
			ImageView imgFrom = (ImageView) findViewById(R.id.imgEvofrom);
			evo1.setText(poke.name + " at level " + p.evolveFromLvl);
			imgFrom.setImageResource(getResources().getIdentifier("p" + poke.num, "drawable", getPackageName()));
		}
		
		if(p.evolveToLvl != 0){
			Pokemon poke = PokemonDB.list.get(p.num);
			ImageView imgFrom = (ImageView) findViewById(R.id.imgEvoto);
			evo2.setText(poke.name + " at level " + p.evolveToLvl);
			imgFrom.setImageResource(getResources().getIdentifier("p" + poke.num, "drawable", getPackageName()));
		}
		
		tvname.setText(p.name);

        if(p.num <= 9)
        	tvnum.setText("#00" + p.num);
        else if(p.num > 9 && p.num < 99){
        	tvnum.setText("#0" + p.num);
        }

        tvtype1.setText(p.t1.toString().toUpperCase(Locale.US));
        tvtype1.setBackgroundColor(PokemonDB.getTypeColor(p.t1));
        
        if(p.t2 != Type.blank){
        	tvtype2.setVisibility(View.VISIBLE);
        	tvtype2.setText(p.t2.toString().toUpperCase(Locale.US));
        	tvtype2.setBackgroundColor(PokemonDB.getTypeColor(p.t2));	        	
        }
        else{
        	tvtype2.setVisibility(View.INVISIBLE);
        }
        
        initStrengths();
        initWeaknesses();
        
        // fave button
		for(Pokemon poketemp : PokemonDB.faves){
			if(poketemp.num == p.num){
	        	ImageButton fave = (ImageButton) findViewById(R.id.favebutton);
	        	fave.setImageResource(getResources().getIdentifier("star", "drawable", getPackageName()));
	        }
		}
        
        tvname.setTypeface(Home.bold);
        tvnum.setTypeface(Home.book);
        tvtype1.setTypeface(Home.book);
        tvtype2.setTypeface(Home.book);
        lheight.setTypeface(Home.book);
        lweight.setTypeface(Home.book);
        levs.setTypeface(Home.book);
        labilities.setTypeface(Home.book);
        evolabel1.setTypeface(Home.book);
        evolabel2.setTypeface(Home.book);
        ldamages.setTypeface(Home.bold);
        levolution.setTypeface(Home.bold);
        evo1.setTypeface(Home.bold);
        evo2.setTypeface(Home.bold);
	}

	public void favorite(View v){
		boolean exists = false;
		for(Pokemon poketemp : PokemonDB.faves){
			if(poketemp.num == p.num){
				exists = true;
				PokemonDB.faves.remove(poketemp);
				Toast.makeText(this, poketemp.name + " is removed from Favorites.", Toast.LENGTH_SHORT).show();
	        	ImageButton fave = (ImageButton) findViewById(R.id.favebutton);
	        	fave.setImageResource(getResources().getIdentifier("graystar", "drawable", getPackageName()));
			}
		}
		
		if(!exists){
			PokemonDB.faves.add(p);
			Toast.makeText(this, p.name + " is added to Favorites!", Toast.LENGTH_SHORT).show();	
        	ImageButton fave = (ImageButton) findViewById(R.id.favebutton);
        	fave.setImageResource(getResources().getIdentifier("star", "drawable", getPackageName()));
		}
		else{
		}
	}
	
	public void gotoEvoFrom(View v){
		Intent intent = new Intent(PokemonPage.this, PokemonPage.class);
		intent.putExtra("pokemon", PokemonDB.list.get(p.num-2));
		startActivity(intent);
	}
	
	public void gotoEvoTo(View v){
		Intent intent = new Intent(PokemonPage.this, PokemonPage.class);
		intent.putExtra("pokemon", PokemonDB.list.get(p.num));
		startActivity(intent);
	}
	
	public void initStrengths(){
        TextView prevTv = null;
        RelativeLayout r1 = (RelativeLayout) this.findViewById(R.id.column1);
        Type[] strengths = { Type.blank, Type.grass, Type.poison, Type.psych, Type.flying };
        for(int i = 0 ; i < strengths.length; i++){
            LayoutParams rparams = new LayoutParams(60,24);
            LayoutParams rparams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
        	TextView tempTv = (TextView) View.inflate(this, R.layout.typetextview, null);
        	TextView tempTv2 = new TextView(this);
        	tempTv2.setText("0.5x");
        	tempTv2.setTypeface(Home.book);
        	
        	tempTv.setText(strengths[i].toString().toUpperCase());
        	tempTv.setBackgroundColor(PokemonDB.getTypeColor(strengths[i]));
        	tempTv.setTypeface(Home.book);        	
        	tempTv.setId(i);
        	tempTv.setLayoutParams(rparams);

        	if(i > 0){
        		rparams.addRule(RelativeLayout.BELOW, prevTv.getId());
        	}
        	
        	rparams.setMargins(0, 10, 0, 0);
        	
        	r1.addView(tempTv);
        	
        	prevTv = tempTv;

        	tempTv2.setLayoutParams(rparams2);
        	rparams2.addRule(RelativeLayout.RIGHT_OF, tempTv.getId());
        	rparams2.addRule(RelativeLayout.ALIGN_TOP, tempTv.getId());
        	rparams2.setMargins(5, 3, 0, 0);
        	
        	if(i != 0)
        		r1.addView(tempTv2);
        }
	}
	
	
	public void initWeaknesses(){
        TextView prevTv = null;
        RelativeLayout r2 = (RelativeLayout) this.findViewById(R.id.column2);
        Type[] weaks = { Type.blank, Type.fire, Type.psych };
        for(int i = 0 ; i < weaks.length; i++){
            LayoutParams rparams = new LayoutParams(60,24);
            LayoutParams rparams2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
        	TextView tempTv = (TextView) View.inflate(this, R.layout.typetextview, null);
        	TextView tempTv2 = new TextView(this);
        	tempTv2.setText("0.5x");
        	tempTv2.setTypeface(Home.book);
        	
        	tempTv.setText(weaks[i].toString().toUpperCase());
        	tempTv.setBackgroundColor(PokemonDB.getTypeColor(weaks[i]));
        	tempTv.setTypeface(Home.book);        	
        	tempTv.setId(i);
        	tempTv.setLayoutParams(rparams);

        	if(i > 0){
        		rparams.addRule(RelativeLayout.BELOW, prevTv.getId());
        	}
        	
        	rparams.setMargins(0, 10, 0, 0);
        	
        	r2.addView(tempTv);
        	
        	prevTv = tempTv;

        	tempTv2.setLayoutParams(rparams2);
        	rparams2.addRule(RelativeLayout.RIGHT_OF, tempTv.getId());
        	rparams2.addRule(RelativeLayout.ALIGN_TOP, tempTv.getId());
        	rparams2.setMargins(6, 3, 0, 0);
        	
        	if(i != 0)
        		r2.addView(tempTv2);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pokemon_page, menu);
		return true;
	}

}
