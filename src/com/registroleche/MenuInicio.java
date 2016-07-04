package com.registroleche;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import baseDatos.AdministrarBD;
import baseDatos.Conexion;

public class MenuInicio extends ActionBarActivity {

	// administracion
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_inicio);
	}

	public void Perfil(View view) {
		Intent perfil = new Intent(this, PerfilEmpleado.class);
		startActivity(perfil);
	}

	public void Proveedores(View view) {
		Intent proveedores = new Intent(this, ConsultarProveedores.class);
		startActivity(proveedores);
	}
	
	public void Compras(View view) {
		Intent compras = new Intent(this, ConsultarCompras.class);
		startActivity(compras);
	}

	public void MostrarReportes(View view)
	{
		Intent reportes = new Intent(this, ReportesCompras.class);
		startActivity(reportes);
	}
	
	public void Salir(View view)
	{
		//base.execSQL("UPDATE tbl_empleado SET activo_emp=0 WHERE activo_emp=1 ");
		Intent login = new Intent(this, MainActivity.class);
		startActivity(login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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
