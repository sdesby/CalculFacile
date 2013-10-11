package fr.sdesby.calculfacile;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VolumeActivity extends Activity 
{
	private EditText editTxtLongueur;
	private EditText editTxtLargeur;
	private EditText editTxtProfondeur;

	private TextView txtViewResultat;

	private Button reinitialiser;
	private Button calculer;
	
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volume);

		editTxtLongueur = (EditText)findViewById(R.id.editTextLongueur);
		editTxtLargeur = (EditText)findViewById(R.id.editTextLargeur);
		editTxtProfondeur = (EditText)findViewById(R.id.editTextProfondeur);
		txtViewResultat = (TextView)findViewById(R.id.resVolume);

		reinitialiser = (Button)findViewById(R.id.reinitialiser);
		calculer = (Button)findViewById(R.id.calculer);

		addListenerButton();
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void blanckFields()
	{
		//tout remettre comme au dŽbut de l'activitŽ
		editTxtLongueur.setText("");
		editTxtLargeur.setText("");
		editTxtProfondeur.setText("");
		txtViewResultat.setText("");
	}

	public void addListenerButton()
	{
		//Bouton de reinitialisation
		reinitialiser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				blanckFields();
			}
		});

		calculer.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) 
			{
				//permet d'arrondir ˆ 2 chiffres aprs la virgule
				DecimalFormat df = new DecimalFormat("###.##");

				if(editTxtLongueur.length() > 0 && editTxtLargeur.length() > 0 && editTxtProfondeur.length() > 0)
				{
					float longueur = Float.parseFloat(editTxtLongueur.getText().toString());
					float largeur = Float.parseFloat(editTxtLargeur.getText().toString());
					float profondeur = Float.parseFloat(editTxtProfondeur.getText().toString());

					float volume = longueur * largeur * profondeur;

					txtViewResultat.setText(df.format(volume));
				}

				else
					Toast.makeText(VolumeActivity.this, 
							"Vous devez renseigner tous les champs", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_volume, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if(item.getItemId() == android.R.id.home) 
			this.finish();
		return true;
	}

}
