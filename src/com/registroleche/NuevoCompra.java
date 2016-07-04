package com.registroleche;

import java.util.Calendar;
import java.util.GregorianCalendar;

import models.Compra;
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

public class NuevoCompra extends ActionBarActivity {

	EditText fecha, detalle, cantidad, valorU, valorT, subtotal, iva, total;

	// administracion
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;
	
	//fecha
	Calendar fechaSistema = new GregorianCalendar();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_compra);
		
		this.fecha = (EditText)findViewById(R.id.txtfechacom);
		this.detalle= (EditText)findViewById(R.id.txtdetallecom);
		this.cantidad= (EditText)findViewById(R.id.txtcantidadcom);
		this.valorU= (EditText)findViewById(R.id.txtvalorucom);
		this.valorT= (EditText)findViewById(R.id.txtvalortotalcom);
		this.subtotal= (EditText)findViewById(R.id.txtsubtotalcom);
		this.iva= (EditText)findViewById(R.id.txtivacom);
		this.total = (EditText)findViewById(R.id.txttotalcom);
		
		
		//obtener fecha del sistema
		int dia = this.fechaSistema.get(Calendar.DAY_OF_MONTH), 
				mes = this.fechaSistema.get(Calendar.MONTH) + 1,
				anio = this.fechaSistema.get(Calendar.YEAR);
		
		this.fecha.setText(dia + "-" + mes + "-" + anio);
		
		
	}

	
	public void ProcesoGuardar(View view) {

		Compra com;
		boolean respuesta = false;

		if (this.ValidarRegistro()) {
			// abrir base de datos
			this.CrearAbrirBDD();

			com = this.CapturarDatos();

			respuesta = admin.insertarC(base, com);

			if (respuesta) {
				Toast.makeText(getApplicationContext(),
						"Los datos se han guardado exitosamente.",
						Toast.LENGTH_SHORT).show();
				Intent listaProve = new Intent(this, ConsultarProveedores.class);
				startActivity(listaProve);

			}

			else {
				Toast.makeText(getApplicationContext(),
						"Error al guardar la compra.", Toast.LENGTH_SHORT)
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
		Intent cancelar = new Intent(this, ConsultarCompras.class);
		startActivity(cancelar);
	}

	public boolean ValidarRegistro() {

		// comprobar si los datos fueron llenados
		if (!this.fecha.getText().toString().equals("")
				&& !this.detalle.getText().toString().equals("")
				&& !this.cantidad.getText().toString().equals("")
				&& !this.valorU.getText().toString().equals("")
				&& !this.valorT.getText().toString().equals("")
				&& !this.subtotal.getText().toString().equals("")
				&& !this.iva.getText().toString().equals("")
				&& !this.total.getText().toString().equals("")) {

			return true;

		} else {
			return false;
		}
	}

	public Compra CapturarDatos() {
		Compra com = new Compra(this.fecha.getText().toString(),
				this.detalle.getText().toString(), 
				Integer.parseInt(this.cantidad.getText().toString()), 
				Float.parseFloat(this.valorU.getText().toString()),
				Float.parseFloat(this.valorT.getText().toString()),
				Float.parseFloat(this.subtotal.getText().toString()),
				Float.parseFloat(this.iva.getText().toString()),
				Float.parseFloat(this.total.getText().toString()));
		return com;
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
		getMenuInflater().inflate(R.menu.nuevo_compra, menu);
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
