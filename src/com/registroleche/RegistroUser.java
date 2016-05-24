package com.registroleche;

import models.Empleado;
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

public class RegistroUser extends ActionBarActivity {

	EditText nombres, apellidos, telefono, correo, username, password;

	// administracion
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro_user);

		this.nombres = (EditText) findViewById(R.id.txtnombres);
		this.apellidos = (EditText) findViewById(R.id.txtapellidos);
		this.telefono = (EditText) findViewById(R.id.txttelefono);
		this.correo = (EditText) findViewById(R.id.txtcorreo);
		this.username = (EditText) findViewById(R.id.txtusername);
		this.password = (EditText) findViewById(R.id.txtpassword);

	}

	public void ProcesoGuardar(View view) {
		
		Empleado emp;
		boolean respuesta = false;


		if (this.ValidarRegistro()) {
			// abrir base de datos
			this.CrearAbrirBDD();
			
			emp = this.CapturarDatos();
			
			respuesta = admin.insertarE(base, emp);

			if (respuesta) {
				Toast.makeText(getApplicationContext(), "Sus datos se han guardado exitosamente.",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(),
						"Error al guardar al empleado.", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Debe llenar todos los campos porfavor", Toast.LENGTH_SHORT)
					.show();
		}
		
		
		
		
	}

	public boolean ValidarRegistro() {

		// comprobar si los datos fueron llenados
		if (!this.nombres.getText().toString().equals("")
				&& !this.apellidos.getText().toString().equals("")
				&& !this.telefono.getText().toString().equals("")
				&& !this.correo.getText().toString().equals("")
				&& !this.username.getText().toString().equals("")
				&& !this.password.getText().toString().equals("")) {

			return true;

		} else {
			return false;
		}
	}

	public Empleado CapturarDatos() {
		Empleado emp = new Empleado(this.nombres.getText().toString(),
				this.apellidos.getText().toString(), this.telefono.getText()
						.toString(), this.correo.getText().toString(),
				this.username.getText().toString(), this.password.getText()
						.toString());
		return emp;
	}

	public void CrearAbrirBDD() {
		// crear DB
		base = openOrCreateDatabase("registro_compras_db.sqlite", MODE_WORLD_WRITEABLE,
				null);
		this.conex.CrearBaseDatos(base);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registro_user, menu);
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
