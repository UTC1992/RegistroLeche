package com.registroleche;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuImagen extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_imagen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_imagen, menu);
		return true;
	}

	
	
	
	public void Barrio1(View view)
	{
		Intent barrio = new Intent(this, Imagen1.class);
		startActivity(barrio);
	}
	public void Barrio2(View view)
	{
		Intent barrio = new Intent(this, Imagen2.class);
		startActivity(barrio);
	}
	public void Barrio3(View view)
	{
		Intent barrio = new Intent(this, Imagen3.class);
		startActivity(barrio);
	}
	public void Barrio4(View view)
	{
		Intent barrio = new Intent(this, Imagen4.class);
		startActivity(barrio);
	}
	public void Barrio5(View view)
	{
		Intent barrio = new Intent(this, Imagen5.class);
		startActivity(barrio);
	}
	
	public void Principal(View view)
	{
		Intent inicio = new Intent(this, MenuInicio.class);
		startActivity(inicio);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
