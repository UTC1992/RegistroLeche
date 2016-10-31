package com.registroleche;

import java.text.DecimalFormat;
import java.util.ArrayList;

import baseDatos.AdministrarBD;
import baseDatos.Conexion;
import models.Compra;
import models.Consulta1Compra;
import models.Empleado;
import models.Proveedor;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditarCompra extends ActionBarActivity {

	EditText fecha, detalle, cantidad, valorU, valorT, subtotal, iva, total;
	Spinner listaProveedor;
	String provElegido;
	
	ArrayList<Proveedor> listaPro = new ArrayList<Proveedor>();
	int idProveedor = 0;
	ArrayList<String> listaEmp = new ArrayList<String>();
	int idEmpleado;
	
	//case admin de BD
	AdministrarBD admin = new AdministrarBD();

	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_compra);
		
		this.fecha = (EditText)findViewById(R.id.txtfechacom);
		this.detalle= (EditText)findViewById(R.id.txtdetallecom);
		this.cantidad= (EditText)findViewById(R.id.txtcantidadcom);
		this.valorU= (EditText)findViewById(R.id.txtvalorucom);
		this.valorT= (EditText)findViewById(R.id.txtvalortotalcom);
		/*this.subtotal= (EditText)findViewById(R.id.txtsubtotalcom);
		this.iva= (EditText)findViewById(R.id.txtivacom);
		this.total = (EditText)findViewById(R.id.txttotalcom);
		*/
		//lista desplegable
		this.listaProveedor = (Spinner)findViewById(R.id.lstproveedores);
		
		
		/*
		 * ===============================CONSULTA COMPRAS======================================
		 */
		Consulta1Compra comp = new Consulta1Compra();

		// id del autor elegido
		int id = Integer.parseInt(getIntent().getStringExtra("idCompra"));

		// abrir la base de datos
		this.CrearAbrirBDD();

		Cursor c = base.rawQuery("SELECT * FROM tbl_compra WHERE _idcom="
				+ id, null);

		if (c.moveToFirst()) {
			do {
				comp = new Consulta1Compra(c.getInt(1), c.getInt(2), c.getString(3),
						c.getString(4), c.getInt(5), c.getDouble(6),
						c.getDouble(7), c.getDouble(8), c.getDouble(9),
						c.getDouble(8) + c.getDouble(9));
			} while (c.moveToNext());
		}

		/*
		 * ===============================CONSULTA COMPRA======================================
		 */
		
		//dar formato para numeros decimales
		DecimalFormat df = new DecimalFormat("0.00"); 

		this.fecha.setText(comp.fecha_com);
		this.detalle.setText(comp.detalle_com);
		this.cantidad.setText(comp.cantidad_com +"");
		this.valorU.setText(comp.valorUnitario_com+"");
		this.valorT.setText(comp.valorTotal_com+"");
		/*this.subtotal.setText(df.format(comp.subtotal_com)+"");
		this.iva.setText(df.format(comp.iva_com)+"");
		this.total.setText(df.format(comp.total_com)+"");
		*/
		this.fecha.setFocusable(false);
		this.valorT.setFocusable(false);
		/*
		this.subtotal.setFocusable(false);
		this.iva.setFocusable(false);
		this.total.setFocusable(false);
		*/
		
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
		
		//=================CONSULTAR PROVEEDOR
		// objeto escritor
		Proveedor prov;

		this.CrearAbrirBDD();

		Cursor cPro = base.rawQuery("SELECT * FROM tbl_proveedor WHERE id_emp='"+idEmpleado+"'", null);
		
		if (cPro.moveToFirst()) {
			do {
				prov = new Proveedor(cPro.getInt(0),cPro.getString(2), cPro.getString(3));
				listaPro.add(prov);
			} while (cPro.moveToNext());
		}

		startManagingCursor(c);

		ArrayAdapter<Proveedor> adaptador = new ArrayAdapter<Proveedor>(this,
				android.R.layout.simple_spinner_item, listaPro);
		listaProveedor.setAdapter(adaptador);
		
		
		//SPINNER con evento clic al seleccionar un item
		listaProveedor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String datosAutor = listaProveedor.getSelectedItem().toString();;
                String [] vector = datosAutor.split(" ");
				
				Toast.makeText(getApplicationContext(), "ID ==> " + vector[0], Toast.LENGTH_SHORT).show();
				idProveedor = Integer.parseInt(vector[0]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
				
		
	}

	public void ActualizarCompra(View view)
	{
		Compra com;
		boolean respuesta = false;
		// id del autor elegido
		int id = Integer.parseInt(getIntent().getStringExtra("idCompra"));

		if (this.ValidarRegistro()) {
			// abrir base de datos
			this.CrearAbrirBDD();

			com = this.CapturarDatos();

			respuesta = admin.actualizarC(base, com, id);

			if (respuesta) {
				Toast.makeText(getApplicationContext(),
						"Los datos se han actualizado exitosamente.",
						Toast.LENGTH_SHORT).show();
				Intent listaProve = new Intent(this, ConsultarCompras.class);
				startActivity(listaProve);

			}

			else {
				Toast.makeText(getApplicationContext(),
						"Error al actualizar la compra.", Toast.LENGTH_SHORT)
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
		if (!this.fecha.getText().toString().equals("")
				&& !this.detalle.getText().toString().equals("")
				&& !this.cantidad.getText().toString().equals("")
				&& !this.valorU.getText().toString().equals("")
				&& !this.valorT.getText().toString().equals("")
				&& this.idProveedor > 0) {

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
				Float.parseFloat(this.valorT.getText().toString()),idProveedor, idEmpleado);
		return com;
	}
	
	public void CalcularTotales(View view)
	{
		
		if (!this.fecha.getText().toString().equals("")
				&& !this.detalle.getText().toString().equals("")
				&& !this.cantidad.getText().toString().equals("")
				&& !this.valorU.getText().toString().equals("")) 
		{
			int cantidad = Integer.parseInt(this.cantidad.getText().toString()); 
			float vu = Float.parseFloat(this.valorU.getText().toString());
			float vt = 0;
			
			vt = cantidad * vu;
			this.valorT.setText(String.valueOf(vt));
			
			/*this.subtotal.setText(String.valueOf(vt));
			
			float iva = 0;
			iva = vt * Float.parseFloat("0.14");
			this.iva.setText(String.valueOf(iva));
			this.total.setText(String.valueOf(vt + iva));
			*/
			
		} else {
			Toast.makeText(getApplicationContext(),
					"Ingrese detalle, cantidad y valor por favor.", Toast.LENGTH_SHORT)
					.show();
		}
		
		
	}
	
	public void Eliminar(View view) {

		// id del autor elegido
		int id = Integer.parseInt(getIntent().getStringExtra("idCompra"));
		base.execSQL("DELETE FROM tbl_compra WHERE _idcom=" + id);
		Intent menu = new Intent(this, ConsultarCompras.class);
		startActivity(menu);
	}
	
	public void CancelarEdicion(View view)
	{
		Intent cancelar = new Intent(this, ConsultarCompras.class);
		startActivity(cancelar);
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
		getMenuInflater().inflate(R.menu.editar_compra, menu);
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
