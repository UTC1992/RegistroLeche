package models;

public class Proveedor {

	public int id;
	public String nombre;
	public String apellido;
	public String telefono;
	public String direccion;

	public Proveedor()
	{
		
	}
	
	public Proveedor(String nombre, String apellido, String telefono,
			String direccion) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Proveedor(int id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return this.id + " " + this.nombre + " " + this.apellido;
	}
}
