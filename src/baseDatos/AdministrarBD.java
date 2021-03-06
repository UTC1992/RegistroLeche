package baseDatos;

import models.Compra;
import models.Empleado;
import models.Proveedor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AdministrarBD {

	
	/*======================EMPLEADOS===============================*/
	
	public boolean insertarE(SQLiteDatabase base, Empleado empleado) {
		ContentValues valores = this.llenarValoresE(empleado);

		// insert ==> la respuesta puede ser 1 o 0... 1=true 0=false
		long respuestaInsert = base.insert("tbl_empleado", null, valores);

		return respuestaInsert > 0;
	}
	
	public ContentValues llenarValoresE(Empleado empleado) {
		ContentValues valores = new ContentValues();

		valores.put("nombres_emp", empleado.nombres_emp);
		valores.put("apellidos_emp", empleado.apellidos_emp);
		valores.put("telefono_emp", empleado.telefono_emp);
		valores.put("correo_emp", empleado.correo_emp);
		valores.put("username_emp", empleado.username_emp);
		valores.put("password_emp", empleado.password_emp);

		return valores;
	}
	
	public boolean actualizarE(SQLiteDatabase base, Empleado empleado, int id) {
		ContentValues valores = this.llenarValoresE(empleado);

		// Actualizamos el registro en la base de datos
		long respuestaUpdate = base.update("tbl_empleado", valores, "_idemp=" + id, null);

		return respuestaUpdate > 0;
	}
	
	public boolean eliminarE(SQLiteDatabase base, int id)
	{
		//eliminamos el resgitro 
		long respuestaDelet = base.delete("tbl_empleado", "_idemp =" + id, null);
		
		return respuestaDelet > 0;
	}
	
/*======================PROVEEDORES===============================*/
	
	public boolean insertarP(SQLiteDatabase base, Proveedor proveedor) {
		ContentValues valores = this.llenarValoresP(proveedor);

		// insert ==> la respuesta puede ser 1 o 0... 1=true 0=false
		long respuestaInsert = base.insert("tbl_proveedor", null, valores);

		return respuestaInsert > 0;
	}
	
	public ContentValues llenarValoresP(Proveedor proveedor) {
		ContentValues valores = new ContentValues();

		valores.put("nombres_pro", proveedor.nombre);
		valores.put("apellidos_pro", proveedor.apellido);
		valores.put("telefono_pro", proveedor.telefono);
		valores.put("direccion_pro", proveedor.direccion);
		valores.put("id_emp", proveedor.id_emp);

		return valores;
	}
	
	public boolean actualizarP(SQLiteDatabase base, Proveedor proveedor, int id) {
		ContentValues valores = this.llenarValoresP(proveedor);

		// Actualizamos el registro en la base de datos
		long respuestaUpdate = base.update("tbl_proveedor", valores, "_idpro=" + id, null);

		return respuestaUpdate > 0;
	}
	
	public boolean eliminarP(SQLiteDatabase base, int id)
	{
		//eliminamos el resgitro 
		long respuestaDelet = base.delete("tbl_proveedor", "_idpro =" + id, null);
		
		return respuestaDelet > 0;
	}


	/*======================COMPRA===============================*/
	
	public boolean insertarC(SQLiteDatabase base, Compra compra) {
		ContentValues valores = this.llenarValoresC(compra);

		// insert ==> la respuesta puede ser 1 o 0... 1=true 0=false
		long respuestaInsert = base.insert("tbl_compra", null, valores);

		return respuestaInsert > 0;
	}
	
	public ContentValues llenarValoresC(Compra compra) {
		ContentValues valores = new ContentValues();

		valores.put("id_pro", compra.id_prov);
		valores.put("id_emp", compra.id_emp);
		valores.put("fecha_com", compra.fecha_com);
		valores.put("detalle_com", compra.detalle_com);
		valores.put("cantidad_com", compra.cantidad_com);
		valores.put("valor_unitario_com", compra.valorUnitario_com);
		valores.put("valor_total_com", compra.valorTotal_com);
		
		return valores;
		
	}
	
	public boolean actualizarC(SQLiteDatabase base, Compra compra, int id) {
		ContentValues valores = this.llenarValoresC(compra);

		// Actualizamos el registro en la base de datos
		long respuestaUpdate = base.update("tbl_compra", valores, "_idcom=" + id, null);

		return respuestaUpdate > 0;
	}
	
	public boolean eliminarC(SQLiteDatabase base, int id)
	{
		//eliminamos el resgitro 
		long respuestaDelet = base.delete("tbl_compra", "_idcom =" + id, null);
		
		return respuestaDelet > 0;
	}

	
	
}
