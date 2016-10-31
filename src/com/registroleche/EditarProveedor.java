package com.registroleche;

import java.util.ArrayList;

import models.Empleado;
import models.Proveedor;
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

public class EditarProveedor extends ActionBarActivity {

	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;
	
	
	EditText nombre, apellido, telefono, direccion;
	ArrayList<String> listaEmp = new ArrayList<String>();
	int idEmpleado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_proveedor);
		
		this.nombre = (EditText)findViewById(R.id.nombre_pro);
		this.apellido = (EditText)findViewById(R.id.apellido_pro);
		this.telefono = (EditText)findViewById(R.id.telefono_pro);
		this.direccion = (EditText)findViewById(R.id.direccion_pro);
		
		//=================CONSULTAR ID EMPLEADO
		Empleado emp;

		this.CrearAbrirBDD();

		Cursor c1 = base.rawQuery("SELECT * FROM tbl_empleado WHERE activo_emp='1'", null);

		if (c1.moveToFirst()) {
			do {
				String idEmp = c1.getInt(0)+ "";
				listaEmp.add(idEmp);
			} while (c1.moveToNext());
		}

		startManagingCursor(c1);
		idEmpleado = Integer.parseInt(listaEmp.get(0).toString());
		

		/*
		 * ===============================CONSULTA DE DATOS DE UN
		 * AUTOR======================================
		 */
		Proveedor prove = new Proveedor();

		// id del autor elegido
		int id = Integer.parseInt(getIntent().getStringExtra("id"));

		// abrir la base de datos
		this.CrearAbrirBDD();

		Cursor c = base.rawQuery("SELECT * FROM tbl_proveedor WHERE _idpro="
				+ id, null);

		if (c.moveToFirst()) {
			do {
				prove = new Proveedor(c.getString(2), c.getString(3),
						c.getString(4), c.getString(5));
			} while (c.moveToNext());
		}

		/*
		 * ===============================CONSULTA DE DATOS DE UN
		 * AUTOR======================================
		 */

		this.nombre.setText(prove.nombre);
		this.apellido.setText(prove.apellido);
		this.telefono.setText(prove.telefono);
		this.direccion.setText(prove.direccion);
		
		
	}
	
	
	public void ActualizarProv(View view) {

		Proveedor prov;
		boolean respuesta = false;
		// id del autor elegido
		int id = Integer.parseInt(getIntent().getStringExtra("id"));

		if (this.ValidarRegistro()) {
			// abrir base de datos
			this.CrearAbrirBDD();

			prov = this.CapturarDatos();

			respuesta = admin.actualizarP(base, prov, id);

			if (respuesta) {
				Toast.makeText(getApplicationContext(),
						"Sus datos se han actualizado exitosamente.",
						Toast.LENGTH_SHORT).show();
				Intent proveedores = new Intent(this, ConsultarProveedores.class);
				startActivity(proveedores);
			}

			else {
				Toast.makeText(getApplicationContext(),
						"Error al actualizar datos de empleado.", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Debe llenar todos los campos porfavor", Toast.LENGTH_SHORT)
					.show();
		}

	}
	
	public Proveedor CapturarDatos() {
		Proveedor prov = new Proveedor(this.nombre.getText().toString(),
				this.apellido.getText().toString(), this.telefono.getText()
						.toString(), this.direccion.getText().toString(),idEmpleado);
		return prov;
	}
	
	public boolean ValidarRegistro() {

		// comprobar si los datos fueron llenados
		if (!this.nombre.getText().toString().equals("")
				&& !this.apellido.getText().toString().equals("")
				&& !this.telefono.getText().toString().equals("")
				&& !this.direccion.getText().toString().equals("")
				&& idEmpleado > 0) {

			return true;

		} else {
			return false;
		}
	}

	
	public void Eliminar(View view) {
		
		// id del autor elegido
		int id = Integer.parseInt(getIntent().getStringExtra("id"));
		base.execSQL("DELETE FROM tbl_proveedor WHERE _idpro="+id);
		Intent menu = new Intent(this, ConsultarProveedores.class);
		startActivity(menu);
	}
	
	
	public void Cancelar(View view) {
		Intent menu = new Intent(this, ConsultarProveedores.class);
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
		getMenuInflater().inflate(R.menu.editar_proveedor, menu);
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
