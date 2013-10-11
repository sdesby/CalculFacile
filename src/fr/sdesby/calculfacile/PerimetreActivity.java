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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PerimetreActivity extends Activity {
	private RadioButton radioButtonRectangle;
	private RadioButton radioButtonCercle;
	private TextView txtViewLongueur;
	private TextView txtViewLargeur;
	private EditText editTxtLongueur;
	private EditText editTxtLargeur;

	private TextView txtViewResultat;

	private Button reinitialiser;
	private Button calculer;
	
	private ActionBar actionBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perimetre);

		radioButtonRectangle = (RadioButton)findViewById(R.id.radioButtonRectangle);
		radioButtonCercle= (RadioButton)findViewById(R.id.radioButtonCercle);
		txtViewLongueur = (TextView)findViewById(R.id.txtViewLongueur);
		editTxtLongueur = (EditText)findViewById(R.id.editTextLongueur);
		txtViewLargeur = (TextView)findViewById(R.id.txtViewLargeur);
		editTxtLargeur = (EditText)findViewById(R.id.editTextLargeur);
		txtViewResultat = (TextView)findViewById(R.id.resPerimetre);

		reinitialiser = (Button)findViewById(R.id.reinitialiser);
		calculer = (Button)findViewById(R.id.calculer);

		addListenerButton();
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void onRadioButtonClicked(View view) 
	{
		// Quel bouton a ŽtŽ cochŽ ?
		switch(view.getId()) {

		case R.id.radioButtonCercle:
				txtViewLongueur.setText(R.string.diametre);
				editTxtLongueur.setText("");
				txtViewLargeur.setVisibility(View.GONE);
				editTxtLargeur.setText("");
				editTxtLargeur.setVisibility(View.GONE);
				txtViewResultat.setText("");
			
			break;

		case R.id.radioButtonRectangle:
			txtViewLongueur.setText(R.string.longueur);
			editTxtLongueur.setText("");
			txtViewLargeur.setVisibility(View.VISIBLE);
			editTxtLargeur.setText("");
			editTxtLargeur.setVisibility(View.VISIBLE);
			txtViewResultat.setText("");
			break;
		}
	}

	public void blanckFields()
	{
		//tout remettre comme au dŽbut de l'activitŽ
		if (radioButtonCercle.isChecked())
		{
			editTxtLongueur.setText("");
			txtViewResultat.setText("");
		}

		else
		{
			editTxtLongueur.setText("");
			editTxtLargeur.setText("");
			txtViewResultat.setText("");
		}
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

				if(radioButtonRectangle.isChecked())
				{
					if(editTxtLongueur.length() > 0 && editTxtLargeur.length() > 0)
					{
						float longueur = Float.parseFloat(editTxtLongueur.getText().toString());
						float largeur = Float.parseFloat(editTxtLargeur.getText().toString());

						float perimetre = 2*longueur + 2*largeur;

						txtViewResultat.setText(df.format(perimetre));
					}

					else
						Toast.makeText(PerimetreActivity.this, 
								"Vous devez renseigner tous les champs", Toast.LENGTH_SHORT).show();
				}

				else
				{
					if(editTxtLongueur.length() > 0 )
					{
						float diametre = Float.parseFloat(editTxtLongueur.getText().toString());
						float perimetre = (float) (3.14*diametre);
						txtViewResultat.setText(df.format(perimetre));
					}

					else
						Toast.makeText(PerimetreActivity.this, 
								"Vous devez renseigner tous les champs", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_perimetre, menu);
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
