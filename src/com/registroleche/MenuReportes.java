package com.registroleche;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuReportes extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_reportes);
	}

	public void Total( View view)
	{
		Intent diaria = new Intent(this, ReportesCompras.class);
		startActivity(diaria);
	}
	
	public void Proveedor( View view)
	{
		Intent diaria = new Intent(this, ReporteProveedores.class);
		startActivity(diaria);
	}
	
	public void Menu( View view)
	{
		Intent diaria = new Intent(this, ReportesFecha.class);
		startActivity(diaria);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_reportes, menu);
		return true;
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
