package models;

public class Empleado {
	
	//public int id;
	public String nombres_emp;
	public String apellidos_emp;
	public String telefono_emp;
	public String correo_emp;
	public String username_emp;
	public String password_emp;
	
	public Empleado(String nombres, String apellidos, String telefono,
			String correo, String username, String password) {
		super();
		this.nombres_emp = nombres;
		this.apellidos_emp = apellidos;
		this.telefono_emp = telefono;
		this.correo_emp = correo;
		this.username_emp = username;
		this.password_emp = password;
	}
	
	public Empleado()
	{
		
	}
	
}
