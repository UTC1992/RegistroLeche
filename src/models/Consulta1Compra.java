package models;

public class Consulta1Compra {

	public int id;
	public int id_emp;
	public int id_pro;
	public String fecha_com;
	public String detalle_com;
	public int cantidad_com;
	public double valorUnitario_com;
	public double valorTotal_com;
	public double subtotal_com;
	public double iva_com;
	public double total_com;
	
	public Consulta1Compra() 
	{
		
	}
	
	public Consulta1Compra(int id, String fecha_com,
			String detalle_com, int cantidad_com, double valorUnitario_com,
			double valorTotal_com, double subtotal_com, double iva_com,
			double total_com) 
	{
		
		this.id = id;
		this.fecha_com = fecha_com;
		this.detalle_com = detalle_com;
		this.cantidad_com = cantidad_com;
		this.valorUnitario_com = valorUnitario_com;
		this.valorTotal_com = valorTotal_com;
		this.subtotal_com = subtotal_com;
		this.iva_com = iva_com;
		this.total_com = total_com;
	}
	
	public Consulta1Compra(int id_emp, int id_pro, String fecha_com,
			String detalle_com, int cantidad_com, double valorUnitario_com,
			double valorTotal_com, double subtotal_com, double iva_com,
			double total_com) 
	{
		
		this.id_emp = id_emp;
		this.id_pro = id_pro;
		this.fecha_com = fecha_com;
		this.detalle_com = detalle_com;
		this.cantidad_com = cantidad_com;
		this.valorUnitario_com = valorUnitario_com;
		this.valorTotal_com = valorTotal_com;
		this.subtotal_com = subtotal_com;
		this.iva_com = iva_com;
		this.total_com = total_com;
	}
	

}
