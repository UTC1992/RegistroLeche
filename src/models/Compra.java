package models;

public class Compra {

	public int id;
	
	public String fecha_com;
	public String detalle_com;
	public int cantidad_com;
	public float valorUnitario_com;
	public float valorTotal_com;
	public float subtotal_com;
	public float iva_com;
	public float total_com;
	
	public int id_emp;
	public int id_prov;

	//string
	public String totalString;
	
	public Compra() {

	}

	public Compra(String fecha_com, String detalle_com, int cantidad_com,
			float valorUnitario_com, float valorTotal_com,  int id_prov, int id_emp) {

		this.fecha_com = fecha_com;
		this.detalle_com = detalle_com;
		this.cantidad_com = cantidad_com;
		this.valorUnitario_com = valorUnitario_com;
		this.valorTotal_com = valorTotal_com;
		
		this.id_prov = id_prov;
		this.id_emp = id_emp;
	}

	public Compra(int id, String detalle_com, String fecha_com, String total) {

		this.id = id;
		this.detalle_com = detalle_com;
		this.fecha_com = fecha_com;
		this.totalString = total;
		
	}

	@Override
	public String toString() {
		return this.id + "   " + this.detalle_com + "   " + this.fecha_com + "   " + this.totalString;
	}

	
	
}
