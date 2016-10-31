package com.registroleche;

import java.text.DecimalFormat;
import java.util.ArrayList;

import models.Consulta1Compra;
import models.Empleado;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import baseDatos.Conexion;

public class ReportesCompras extends ActionBarActivity {

	ArrayList<Consulta1Compra> listaCompras = new ArrayList<Consulta1Compra>();
	
	ArrayList<String> listaIdCompas = new ArrayList<String>();

	
	// conexion
	Conexion conex = new Conexion();
	SQLiteDatabase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reportes_compras);
		
		//CONSULTAR ID EMPLEADO
		//=================CONSULTAR ID EMPLEADO
		Empleado emp;

		this.CrearAbrirBDD();

		Cursor c1 = base.rawQuery("SELECT * FROM tbl_empleado WHERE activo_emp='1'", null);
		int idEmp=0;
		
		if (c1.moveToFirst()) {
			do {
				idEmp = c1.getInt(0);
			} while (c1.moveToNext());
		}

		startManagingCursor(c1);
		
		
		// ========================================================================================

		this.CrearAbrirBDD();

		Consulta1Compra consultaComp;

		Cursor c = base.rawQuery("SELECT * FROM tbl_compra WHERE id_emp='"+idEmp+"'", null);

		if (c.moveToFirst()) {
			do {
				consultaComp = new Consulta1Compra(c.getInt(0), c.getString(3),
						c.getString(4), c.getInt(5), c.getDouble(6),
						c.getDouble(7), c.getDouble(8), c.getDouble(9),c.getDouble(8) + c.getDouble(9));
				listaCompras.add(consultaComp);
				listaIdCompas.add(c.getInt(2)+"");
			} while (c.moveToNext());
		}

		// ========================================================================================

		Tabla tabla = new Tabla(this,
				(TableLayout) findViewById(R.id.TablaDatos));
		tabla.agregarCabecera(R.array.cabecera_tabla);
		
		//dar formato para numeros decimales
		DecimalFormat df = new DecimalFormat("0.00");
		
		//almacenar suma de totales
		Double FinalTotal = 0.0;
		
		for (int i = 0; i < listaCompras.size(); i++) {
			ArrayList<String> elementos = new ArrayList<String>();
			elementos.add(Integer.toString(i+1));
			
			 
			
			//soncultar nombres de proveedores
			Cursor cPro = base.rawQuery("SELECT * FROM tbl_proveedor WHERE _idpro=" + listaIdCompas.get(i), null);
			
			//variable para almacenar el nombre y apellido del proveedor
			String proveedor = "";
			
			if (cPro.moveToFirst()) {
				do {
					proveedor = cPro.getString(2)+" "+cPro.getString(3);
				} while (cPro.moveToNext());
			}
			
			
			String id = listaCompras.get(i).id + "";
			String fecha = listaCompras.get(i).fecha_com.toString();
			String detalle = listaCompras.get(i).detalle_com.toString();
			String cantidad = listaCompras.get(i).cantidad_com + "";
			String valorU = df.format(listaCompras.get(i).valorUnitario_com) + "";
			String valorT = df.format(listaCompras.get(i).valorTotal_com) + "";
			
			FinalTotal = FinalTotal + listaCompras.get(i).valorTotal_com;
			
			/*String subtotal = df.format(listaCompras.get(i).subtotal_com) + "";
			String iva = df.format(listaCompras.get(i).iva_com) + "";
			String total = df.format(listaCompras.get(i).total_com) + "";
			*/
			// cada uno de estos es una columna
			//elementos.add(id);
			elementos.add(proveedor);
			elementos.add(fecha);
			elementos.add(detalle);
			elementos.add(cantidad);
			elementos.add(valorU);
			elementos.add(valorT);
			/*elementos.add(subtotal);
			elementos.add(iva);
			elementos.add(total);
			*/
			tabla.agregarFilaTabla(elementos);
		}

		for (int i = 0; i < 1; i++) {
			ArrayList<String> elementos = new ArrayList<String>();
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			tabla.agregarFilaTabla(elementos);
		}
		
		for (int i = 0; i < 1; i++) {
			ArrayList<String> elementos = new ArrayList<String>();
			elementos.add("Total");
			elementos.add(df.format(FinalTotal)+"");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			tabla.agregarFilaTabla(elementos);
		}
		
		for (int i = 0; i < 1; i++) {
			ArrayList<String> elementos = new ArrayList<String>();
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			elementos.add("");
			tabla.agregarFilaTabla(elementos);
		}
		
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
		getMenuInflater().inflate(R.menu.reportes_ventas, menu);
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
