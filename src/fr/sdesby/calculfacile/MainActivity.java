package fr.sdesby.calculfacile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button tvaButton;
	Button regle3;
	Button pourcentage;
	Button perimetre;
	Button aire;
	Button volume;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvaButton = (Button)findViewById(R.id.buttonTVA);
		tvaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, TVAActivity.class);
				startActivity(intent);
				
			}
		});
		
		regle3 = (Button)findViewById(R.id.buttonRegle3);
		regle3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, RegleDeTroisActivity.class);
				startActivity(intent);
				
			}
		});
		
		pourcentage = (Button)findViewById(R.id.buttonPourcentage);
		pourcentage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PourcentageActivity.class);
				startActivity(intent);
			}
		});
		
		perimetre = (Button)findViewById(R.id.buttonPerimetre);
		perimetre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, PerimetreActivity.class);
				startActivity(intent);
			}
		});
		
		aire = (Button)findViewById(R.id.buttonAire);
		aire.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AireActivity.class);
				startActivity(intent);
			}
		});
		
		volume = (Button)findViewById(R.id.buttonVolume);
		volume.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VolumeActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
