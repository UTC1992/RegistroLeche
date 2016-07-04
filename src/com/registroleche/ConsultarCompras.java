package com.registroleche;

import java.util.ArrayList;

import models.Compra;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import baseDatos.Conexion;

public class ConsultarCompras extends ActionBarActivity {

	ListView lstcompras;
	ArrayList<Compra> listaCom = new ArrayList<Compra>();

	// Conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	ListView listacompras;

	EditText id;

	String idCompra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_compras);

		this.listacompras = (ListView) findViewById(R.id.lstcompras);

		// objeto escritor
		Compra com;

		this.CrearAbrirBDD();

		Cursor c = base.rawQuery("SELECT * FROM tbl_compra", null);

		if (c.moveToFirst()) {
			do {
				com = new Compra(c.getInt(0), c.getString(1));
				listaCom.add(com);
			} while (c.moveToNext());
		}

		ArrayAdapter<Compra> adaptador = new ArrayAdapter<Compra>(this,
				android.R.layout.simple_list_item_1, listaCom);
		listacompras.setAdapter(adaptador);

		listacompras.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String datosAutor = listacompras
						.getItemAtPosition(position).toString();
				String[] vector = datosAutor.split(" ");

				// mostrar el id del autor
				Toast.makeText(getApplicationContext(), "ID ==> " + vector[0],
						Toast.LENGTH_SHORT).show();
				idCompra = vector[0];

				// declarar intent para pasar datos de un activity a otro
				//Intent mostrarAutor = new Intent(ConsultarProveedores.this,EditarProveedor.class);
				//mostrarAutor.putExtra("id", vector[0]);
				//startActivity(mostrarAutor);

			}
		});

	}

	public void Nuevo(View view) {
		Intent nuevo = new Intent(this, NuevoCompra.class);
		startActivity(nuevo);
	}

	public void Menu(View view) {
		Intent nuevo = new Intent(this, MenuInicio.class);
		startActivity(nuevo);
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
		getMenuInflater().inflate(R.menu.consultar_compras, menu);
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
