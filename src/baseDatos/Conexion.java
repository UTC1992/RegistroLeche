package baseDatos;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Conexion extends Activity{
	
	//atributpos o tablas
	private String tbl_empleado;
	private String tbl_proveedor;
	/* private String tbl_estudiante;
	private String tbl_producto;
	private String tbl_nota;
	*/
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
				+ "nombres_pro TEXT NOT NULL,"
				+ "apellidos_pro TEXT NOT NULL,"
				+ "telefono_pro TEXT NOT NULL,"
				+ "direccion_pro TEXT NOT NULL"
				+ ")";
		
	}
	
	public void CrearBaseDatos(SQLiteDatabase base)
	{
		try {
			//ejecucion y creacion de tablas
        	base.execSQL(tbl_empleado);
        	base.execSQL(tbl_proveedor);
        	//base.execSQL(tbl_estudiante);
        	//base.execSQL(tbl_producto);
        	//base.execSQL(tbl_nota);
        	
        System.out.println("Tablas creadas");
        	
		} catch (Exception e) {
			//mensaje de error
			Log.i(null, "Error al crear o abrir base de datos" + e);
		}
	}

}
