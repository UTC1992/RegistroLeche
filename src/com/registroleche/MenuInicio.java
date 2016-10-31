package com.registroleche;

import models.Empleado;
import android.content.Intent;
import android.database.Cursor;
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
	
	String idEmp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_inicio);
		
		
		//=================CONSULTAR ID EMPLEADO
		Empleado emp;

		this.CrearAbrirBDD();

		Cursor c1 = base.rawQuery("SELECT * FROM tbl_empleado WHERE activo_emp='1'", null);

		if (c1.moveToFirst()) {
			do {
				idEmp = c1.getInt(0)+ "";
			} while (c1.moveToNext());
		}

		startManagingCursor(c1);
		
		
		
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
		Intent reportes = new Intent(this, MenuReportes.class);
		startActivity(reportes);
	}
	
	public void Rutas(View view)
	{
		Intent rutas = new Intent(this, MenuImagen.class);
		startActivity(rutas);
	}
	
	public void Salir(View view)
	{
		this.CrearAbrirBDD();
		base.execSQL("UPDATE tbl_empleado SET activo_emp='0' WHERE _idemp='"+ idEmp + "'");
		
		Intent login = new Intent(this, MainActivity.class);
		startActivity(login);
	}

	public void CrearAbrirBDD() {
		// crear DB
		base = openOrCreateDatabase("registro_compras_db.sqlite",
				MODE_WORLD_WRITEABLE, null);
		this.conex.CrearBaseDatos(base);
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
