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

public class TVAActivity extends Activity 
{
	private static final String CHAINE_VIDE= "";
	private Button calculer;
	private Button reinitialiser;
	private EditText toutesTaxes;
	private EditText horsTaxes;
	private TextView montantTVA;
	private float tauxTVA;
	private float mttTVA;
	private float ht;
	private float ttc;
	
	private ActionBar actionBar;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tva);

		tauxTVA = (float) 19.6;
		calculer = (Button)findViewById(R.id.calculer);
		reinitialiser = (Button)findViewById(R.id.reinitialiser);
		toutesTaxes = (EditText)findViewById(R.id.editTextTTC);
		montantTVA = (TextView)findViewById(R.id.editTextMontantTva);
		horsTaxes = (EditText)findViewById(R.id.editTextHorsTaxes);

		addListenerOnButton();
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void onRadioButtonClicked(View view) 
	{
		RadioButton cinqcinq= (RadioButton)findViewById(R.id.cinqCinq);
		RadioButton dixneufsix = (RadioButton)findViewById(R.id.dixneufsix);
		RadioButton sept = (RadioButton)findViewById(R.id.sept);
		RadioButton deuxun = (RadioButton)findViewById(R.id.deuxUn);
		
	/**TODO : trouver un moyen pour que les checks et unchecks se fassent seuls
		peut être un relative layout pour le radiogroup**/
		
		//On assigne le taux demandé par l'utilisateur
		switch(view.getId()) {
		case R.id.cinqCinq:
			tauxTVA =  (float) 5.5;
			dixneufsix.setChecked(false);
			sept.setChecked(false);
			deuxun.setChecked(false);
			blanckFields();
			break;
		case R.id.dixneufsix:
			tauxTVA =  (float) 19.6;
			cinqcinq.setChecked(false);
			sept.setChecked(false);
			deuxun.setChecked(false);
			blanckFields();
			break;
		case R.id.sept:
			tauxTVA = (float) 7.0;
			dixneufsix.setChecked(false);
			cinqcinq.setChecked(false);
			deuxun.setChecked(false);
			blanckFields();
			break;
		case R.id.deuxUn:
			tauxTVA = (float) 2.1;
			dixneufsix.setChecked(false);
			sept.setChecked(false);
			cinqcinq.setChecked(false);
			blanckFields();
			break;
		}
	}

	public void blanckFields()
	{
		toutesTaxes.setText("");
		horsTaxes.setText("");
		montantTVA.setText("");
	}

	public void addListenerOnButton()
	{	
		//Bouton de reinitialisation
		reinitialiser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				blanckFields();
			}
		});

		calculer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				String horsTaxes_string = horsTaxes.getText().toString();
				String toutesTaxes_string = toutesTaxes.getText().toString();

				//permet d'arrondir à 2 chiffres après la virgule
				DecimalFormat df = new DecimalFormat("###.##");

				//On vérifie qu'au moins un champ est renseigné
				if(!horsTaxes_string.equals(CHAINE_VIDE) || !toutesTaxes_string.equals(CHAINE_VIDE))
				{
					//On verifie qu'une seule valeur est renseignée
					if(!horsTaxes_string.equals(CHAINE_VIDE) && !toutesTaxes_string.equals(CHAINE_VIDE))
					{
						Toast.makeText(TVAActivity.this, 
								"Vous ne devez remplir qu'un seul champ", Toast.LENGTH_SHORT).show();
						blanckFields();
					}

					else if(!horsTaxes_string.equals(CHAINE_VIDE)) // Si c'est le champ HT qui est renseigné...
					{
						ht = Float.parseFloat(horsTaxes_string);

						ttc = ht * (1 + (tauxTVA / 100));
						mttTVA = ttc - ht;

						toutesTaxes.setText(df.format(ttc));
						montantTVA.setText(df.format(mttTVA));
					}

					else //sinon c'est que c'est le champ TTC qui est renseigné
					{
						ttc = Float.parseFloat(toutesTaxes_string);
						ht = ttc / (1 + (tauxTVA / 100));
						mttTVA = ttc - ht;

						horsTaxes.setText(df.format(ht));
						montantTVA.setText(df.format(mttTVA));
					}
				}
				//Aucune valeur n'est renseignée
				else
				{
					Toast.makeText(TVAActivity.this, 
							"Vous devez entrer une valeur", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tva, menu);
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
