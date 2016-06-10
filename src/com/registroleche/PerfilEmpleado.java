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
import android.widget.EditText;
import android.widget.Toast;
import baseDatos.AdministrarBD;
import baseDatos.Conexion;

public class PerfilEmpleado extends ActionBarActivity {

	EditText nombres, apellidos, telefono, correo, username, password;

	// administracion
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_empleado);

		this.nombres = (EditText) findViewById(R.id.nombre_pro);
		this.apellidos = (EditText) findViewById(R.id.apellido_pro);
		this.telefono = (EditText) findViewById(R.id.telefono_pro);
		this.correo = (EditText) findViewById(R.id.direccion_pro);
		this.username = (EditText) findViewById(R.id.username_emp);
		this.password = (EditText) findViewById(R.id.password_emp);

		Empleado empleado = new Empleado();

		// open database
		this.CrearAbrirBDD();

		Cursor c = base.rawQuery(
				"SELECT * FROM tbl_empleado WHERE activo_emp=1", null);

		if (c.moveToFirst()) {
			do {
				empleado = new Empleado(c.getInt(0), c.getString(1),
						c.getString(2), c.getString(3), c.getString(4),
						c.getString(5), c.getString(6));
			} while (c.moveToNext());
		}

		this.nombres.setText(empleado.nombres_emp.toString());
		this.apellidos.setText(empleado.apellidos_emp.toString());
		this.telefono.setText(empleado.telefono_emp.toString());
		this.correo.setText(empleado.correo_emp.toString());
		this.username.setText(empleado.username_emp.toString());
		this.password.setText(empleado.password_emp.toString());

		id = empleado.id;

	}

	public void ProcesoActualizar(View view) {

		Empleado emp;
		boolean respuesta = false;

		if (this.ValidarRegistro()) {
			// abrir base de datos
			this.CrearAbrirBDD();

			emp = this.CapturarDatos();

			respuesta = admin.actualizarE(base, emp, id);

			if (respuesta) {
				Toast.makeText(getApplicationContext(),
						"Sus datos se han actualizado exitosamente.",
						Toast.LENGTH_SHORT).show();
				finish();
			}

			else {
				Toast.makeText(getApplicationContext(),
						"Error al actualizar datos de proveedor.",
						Toast.LENGTH_SHORT).show();
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

	public void Eliminar(View view) {
		base.execSQL("DELETE FROM tbl_empleado WHERE username_emp='"
				+ this.username.getText().toString() + "'");
		Intent menu = new Intent(this, MainActivity.class);
		startActivity(menu);
	}

	public void Cancelar(View view) {
		Intent menu = new Intent(this, MenuInicio.class);
		startActivity(menu);
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
		getMenuInflater().inflate(R.menu.perfil_empleado, menu);
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
