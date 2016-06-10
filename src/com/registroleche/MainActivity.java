package com.registroleche;

import menu.IndexAdmin;
import models.Empleado;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import baseDatos.AdministrarBD;
import baseDatos.Conexion;

public class MainActivity extends ActionBarActivity {

	EditText nombre, contrasenia;

	// administracion
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nombre = (EditText) findViewById(R.id.username);
		contrasenia = (EditText) findViewById(R.id.password);
		
	}

	public void Ingresar(View v) {
		String usua = nombre.getText().toString();
		String pas = contrasenia.getText().toString();

		if (Login(usua, pas)) {
			
			nombre.setText("");
			contrasenia.setText("");
			
			Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
			Intent ingresar = new Intent(this, MenuInicio.class);
			startActivity(ingresar);
		} else {
			Toast.makeText(this, "El usuario o contraseña es incorrecto",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void Registro(View v) {
		Intent registro = new Intent(this, RegistroUser.class);
		startActivity(registro);
	}

	public boolean Login(String usu, String pas) {
		/*
		 * ==========CONSULTA EMPLEADO
		 */
		boolean login = false;

		Empleado empleado = new Empleado();

		// open database
		this.CrearAbrirBDD();

		if (usu.length()==0 && pas.length()==0) {
			login = false;
		} else {
			Cursor c = base.rawQuery(
					"SELECT * FROM tbl_empleado WHERE username_emp='" + usu
							+ "' AND password_emp='" + pas + "'", null);
			
			
			
			if (c.moveToFirst()) {
				do {
					empleado = new Empleado(c.getInt(0),c.getString(5), c.getString(6));
				} while (c.moveToNext());
			}

			//System.out.println("username del empleado" + empleado.username_emp + "--" + empleado.password_emp);
			//System.out.println("username del empleado" + usu + "--" + pas);
			
			if(empleado.username_emp == null && empleado.password_emp == null)
			{
				login = false;
			}else{
				if(empleado.username_emp.equals(usu) && empleado.password_emp.equals(pas))
				{
					//Actualizar un registro
					base.execSQL("UPDATE tbl_empleado SET activo_emp=1 WHERE _idemp="+empleado.id);
					login = true;
				}
			}
			
			
		}
		
		return login;
		

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
		getMenuInflater().inflate(R.menu.main, menu);
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
