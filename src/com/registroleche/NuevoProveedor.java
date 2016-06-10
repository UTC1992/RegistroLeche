package com.registroleche;

import models.Empleado;
import models.Proveedor;
import android.content.Intent;
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

public class NuevoProveedor extends ActionBarActivity {

	EditText nombres, apellidos, telefono, direccion;

	// administracion
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_proveedor);

		this.nombres = (EditText) findViewById(R.id.nombre_pro);
		this.apellidos = (EditText) findViewById(R.id.apellido_pro);
		this.telefono = (EditText) findViewById(R.id.telefono_pro);
		this.direccion = (EditText) findViewById(R.id.direccion_pro);
	}

	public void ProcesoGuardar(View view) {

		Proveedor prov;
		boolean respuesta = false;

		if (this.ValidarRegistro()) {
			// abrir base de datos
			this.CrearAbrirBDD();

			prov = this.CapturarDatos();

			respuesta = admin.insertarP(base, prov);

			if (respuesta) {
				Toast.makeText(getApplicationContext(),
						"Sus datos se han guardado exitosamente.",
						Toast.LENGTH_SHORT).show();
				Intent listaProve = new Intent(this, ConsultarProveedores.class);
				startActivity(listaProve);

			}

			else {
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
	
	public void CancelarNuevo(View view)
	{
		Intent cancelar = new Intent(this, ConsultarProveedores.class);
		startActivity(cancelar);
	}

	public boolean ValidarRegistro() {

		// comprobar si los datos fueron llenados
		if (!this.nombres.getText().toString().equals("")
				&& !this.apellidos.getText().toString().equals("")
				&& !this.telefono.getText().toString().equals("")
				&& !this.direccion.getText().toString().equals("")) {

			return true;

		} else {
			return false;
		}
	}

	public Proveedor CapturarDatos() {
		Proveedor prov = new Proveedor(this.nombres.getText().toString(),
				this.apellidos.getText().toString(), this.telefono.getText()
						.toString(), this.direccion.getText().toString());
		return prov;
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
		getMenuInflater().inflate(R.menu.nuevo_proveedor, menu);
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
