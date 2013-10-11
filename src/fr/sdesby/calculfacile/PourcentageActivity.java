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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PourcentageActivity extends Activity 
{
	private Button reinitialiser;
	private Button calculer;
	private EditText pourcentage;
	private EditText nb;
	private TextView resultat;

	private LinearLayout linearLayoutPlus;
	private TextView pourcentagePlus;
	private TextView pourcentagePlusRes;
	private LinearLayout linearLayoutMoins;
	private TextView pourcentageMoins;
	private TextView pourcentageMoinsRes;
	
	private ActionBar actionBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pourcentage);

		reinitialiser = (Button)findViewById(R.id.reinitialiser);
		calculer = (Button)findViewById(R.id.calculer);
		pourcentage = (EditText)findViewById(R.id.editTextPourcentage);
		nb = (EditText)findViewById(R.id.editTextNbPourcentage);
		resultat = (TextView)findViewById(R.id.txtViewPourcentageRes);

		linearLayoutPlus = (LinearLayout)findViewById(R.id.linearLayoutPlus);
		pourcentagePlus = (TextView)findViewById(R.id.txtViewPourcentPlus);
		pourcentagePlusRes = (TextView)findViewById(R.id.txtViewPourcentagePlusRes);

		linearLayoutMoins = (LinearLayout)findViewById(R.id.linearLayoutMoins);
		pourcentageMoins = (TextView)findViewById(R.id.txtViewPourcentMoins);
		pourcentageMoinsRes = (TextView)findViewById(R.id.txtViewPourcentageMoinsRes);


		addListenerOnButton();
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void blanckFields()
	{
		pourcentage.setText("");
		nb.setText("");
		resultat.setText("");

		if(linearLayoutPlus.getVisibility() == View.VISIBLE)
		{
			pourcentagePlus.setText("");
			pourcentagePlusRes.setText("");
			linearLayoutPlus.setVisibility(View.INVISIBLE);
		}


		if(linearLayoutMoins.getVisibility() == View.VISIBLE)
		{
			pourcentageMoins.setText("");
			pourcentageMoinsRes.setText("");
			linearLayoutMoins.setVisibility(View.INVISIBLE);
		}

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
				if(pourcentage.length() > 0 && nb.length() > 0)
				{
					//permet d'arrondir à 2 chiffres après la virgule
					DecimalFormat df = new DecimalFormat("###.##");

					float pourcent = Float.parseFloat(pourcentage.getText().toString());
					float nombre = Float.parseFloat(nb.getText().toString());

					float res = pourcent*nombre / 100;

					float pourcentPlus = nombre + res;
					float pourcentMoins = nombre - res;

					// On rend visible et assigne les valeurs :
					linearLayoutPlus.setVisibility(View.VISIBLE);
					pourcentagePlus.setText(pourcentage.getText().toString());
					pourcentagePlusRes.setText(df.format(pourcentPlus));

					linearLayoutMoins.setVisibility(View.VISIBLE);
					pourcentageMoins.setText(pourcentage.getText().toString());
					pourcentageMoinsRes.setText(df.format(pourcentMoins));

					resultat.setText(df.format(res));
				}
				
				else
					Toast.makeText(PourcentageActivity.this, "Vous devez renseigner tous les champs", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pourcentage, menu);
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
