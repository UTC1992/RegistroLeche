package baseDatos;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Conexion extends Activity{
	
	//atributpos o tablas
	private String tbl_empleado;
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
				+ "password_emp TEXT NOT NULL"
				+ ")";
		/* this.tbl_estudiante = "CREATE TABLE IF NOT EXISTS tbl_estudiante"
				+ "(_ide INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "_id_cur INTEGER NOT NULL,"
				+ "nombre_est TEXT NOT NULL,"
				+ "apellido_est TEXT NOT NULL,"
				+ "FOREIGN KEY(_id_cur) REFERENCES tbl_curso(_idc) ON DELETE CASCADE"
				+ ")";
		
		this.tbl_producto = "CREATE TABLE IF NOT EXISTS tbl_producto"
				+ "(_idp INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "_id_est INTEGER NOT NULL,"
				+ "nombre_pro TEXT NOT NULL,"
				+ "FOREIGN KEY(_id_est) REFERENCES tbl_estudiante(_ide) ON DELETE CASCADE"
				+ ")";
		this.tbl_nota = "CREATE TABLE IF NOT EXISTS tbl_nota"
				+ "(_idn INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "_id_pro INTEGER NOT NULL,"
				+ "nombre_not TEXT NOT NULL,"
				+ "puntaje_not TEXT NOT NULL,"
				+ "FOREIGN KEY(_id_pro) REFERENCES tbl_producto(_idp) ON DELETE CASCADE"
				+ ")";
		*/
	}
	
	public void CrearBaseDatos(SQLiteDatabase base)
	{
		try {
			//ejecucion y creacion de tablas
        	base.execSQL(tbl_empleado);
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
