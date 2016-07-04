package models;

public class Compra {

	public int id;
	public int id_emp;
	public int id_pro;
	public String fecha_com;
	public String detalle_com;
	public int cantidad_com;
	public float valorUnitario_com;
	public float valorTotal_com;
	public float subtotal_com;
	public float iva_com;
	public float total_com;

	public Compra() {

	}

	public Compra(String fecha_com, String detalle_com, int cantidad_com,
			float valorUnitario_com, float valorTotal_com, float subtotal_com,
			float iva_com, float total_com) {

		this.fecha_com = fecha_com;
		this.detalle_com = detalle_com;
		this.cantidad_com = cantidad_com;
		this.valorUnitario_com = valorUnitario_com;
		this.valorTotal_com = valorTotal_com;
		this.subtotal_com = subtotal_com;
		this.iva_com = iva_com;
		this.total_com = total_com;
	}

	public Compra(int id, String detalle_com, String fecha_com) {

		this.id = id;
		this.detalle_com = detalle_com;
		this.fecha_com = fecha_com;
	}

	@Override
	public String toString() {
		return this.id + "   " + this.detalle_com + "   " + this.fecha_com;
	}

	
	
}
