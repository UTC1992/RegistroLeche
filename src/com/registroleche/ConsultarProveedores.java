package com.registroleche;

import java.util.ArrayList;

import models.Proveedor;
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

public class ConsultarProveedores extends ActionBarActivity {

	ListView lstautores;
	ArrayList<Proveedor> listaPro = new ArrayList<Proveedor>();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	ListView listaproveedores;
	
	EditText id;
	
	String idProveedor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_proveedores);

		this.listaproveedores = (ListView) findViewById(R.id.proveedores);
		
		
		// objeto escritor
		Proveedor prov;

		this.CrearAbrirBDD();

		Cursor c = base.rawQuery("SELECT * FROM tbl_proveedor", null);

		if (c.moveToFirst()) {
			do {
				prov = new Proveedor(c.getInt(0),c.getString(1), c.getString(2));
				listaPro.add(prov);
			} while (c.moveToNext());
		}

		startManagingCursor(c);

		ArrayAdapter<Proveedor> adaptador = new ArrayAdapter<Proveedor>(this,
				android.R.layout.simple_list_item_1, listaPro);
		listaproveedores.setAdapter(adaptador);
		
		listaproveedores.setOnItemClickListener(new OnItemClickListener() { 

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub
				
                String datosAutor = listaproveedores.getItemAtPosition(position).toString();
                String [] vector = datosAutor.split(" ");
                
                
                //mostrar el id del autor
                Toast.makeText(getApplicationContext(), "ID ==> " + vector[0], Toast.LENGTH_SHORT).show();
                idProveedor = vector[0];
                
                //declarar intent para pasar datos de un activity a otro
               Intent mostrarAutor = new Intent(ConsultarProveedores.this, EditarProveedor.class);
               mostrarAutor.putExtra("id", vector[0]);
               startActivity(mostrarAutor);
			}
        });
		
		//id.setText(idProveedor);

	}

	
	
	public void NuevoProveedor(View view) {
		Intent nuevo = new Intent(this, NuevoProveedor.class);
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
		getMenuInflater().inflate(R.menu.consultar_proveedores, menu);
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
