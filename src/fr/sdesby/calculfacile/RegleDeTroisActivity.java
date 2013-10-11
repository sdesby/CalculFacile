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

public class RegleDeTroisActivity extends Activity 
{
	private EditText editTxtnb1;
	private EditText editTxtnb2;
	private EditText editTxtnb3;
	private TextView resultat;

	private Button calculer;
	private Button reinitialiser;

	private float nb1;
	private float nb2;
	private float nb3;
	
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regle_de_trois);

		editTxtnb1 = (EditText)findViewById(R.id.editTextNb1);
		editTxtnb2 = (EditText)findViewById(R.id.editTextNb2);
		editTxtnb3 = (EditText)findViewById(R.id.editTextNb3);

		calculer = (Button)findViewById(R.id.calculer);
		reinitialiser = (Button)findViewById(R.id.reinitialiser);

		resultat = (TextView)findViewById(R.id.textViewResultat);

		addListenerOnButton();
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void blanckFields()
	{
		editTxtnb1.setText("");
		editTxtnb2.setText("");
		editTxtnb3.setText("");
		resultat.setText(getString(R.string.interrogation));
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
				//Si tous les champs sont remplis :
				if(editTxtnb1.getText().length() > 0 
						&& editTxtnb2.getText().length() > 0 
						&& editTxtnb3.getText().length() > 0)
				{
					//permet d'arrondir à 2 chiffres après la virgule
					DecimalFormat df = new DecimalFormat("###.##");

					nb1 = Float.parseFloat(editTxtnb1.getText().toString());
					nb2 = Float.parseFloat(editTxtnb2.getText().toString());
					nb3 = Float.parseFloat(editTxtnb3.getText().toString());

					float res = (nb2 * nb3) / nb1;

					resultat.setText(df.format(res));
				}
				
				else
					Toast.makeText(RegleDeTroisActivity.this, 
							"Vous devez renseigner tous les champs", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_regle_de_trois, menu);
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
