package baseDatos;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Conexion extends Activity{
	
	//atributpos o tablas
	private String tbl_empleado;
	private String tbl_proveedor;
	private String tbl_compra;
	
	public Conexion()
	{
		this.tbl_empleado = "CREATE TABLE IF NOT EXISTS tbl_empleado"
				+ "(_idemp INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nombres_emp TEXT NOT NULL,"
				+ "apellidos_emp TEXT NOT NULL,"
				+ "telefono_emp TEXT NOT NULL,"
				+ "correo_emp TEXT NOT NULL,"
				+ "username_emp TEXT NOT NULL,"
				+ "password_emp TEXT NOT NULL,"
				+ "activo_emp TEXT NUll"
				+ ")";
		this.tbl_proveedor = "CREATE TABLE IF NOT EXISTS tbl_proveedor"
				+ "(_idpro INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "id_emp INTEGER NULL,"
				+ "nombres_pro TEXT NOT NULL,"
				+ "apellidos_pro TEXT NOT NULL,"
				+ "telefono_pro TEXT NOT NULL,"
				+ "direccion_pro TEXT NOT NULL"
				+ ")";
		this.tbl_compra = "CREATE TABLE IF NOT EXISTS tbl_compra"
				+ "(_idcom INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "id_emp INTEGER NULL,"
				+ "id_pro INTEGER NULL,"
				+ "fecha_com TEXT NOT NULL,"
				+ "detalle_com TEXT NOT NULL,"
				+ "cantidad_com INTEGER NOT NULL,"
				+ "valor_unitario_com REAL NOT NULL,"
				+ "valor_total_com REAL NOT NULL,"
				+ "subtotal_com REAL NULL,"
				+ "iva_com REAL NULL"
				+ ")";
		
		
	}
	//aqui se realiza la conexion a la base de datos
	public void CrearBaseDatos(SQLiteDatabase base)
	{
		try {
			//ejecucion y creacion de tablas
        	base.execSQL(tbl_empleado);
        	base.execSQL(tbl_proveedor);
        	base.execSQL(tbl_compra);
        	
        	
        System.out.println("Tablas creadas");
        	
		} catch (Exception e) {
			//mensaje de error
			Log.i(null, "Error al crear o abrir base de datos" + e);
		}
	}

}
