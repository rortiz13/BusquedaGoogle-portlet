package la.netco.entities;

import java.io.Serializable;

public class DatosPersonas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombres;
	private String nacion; 
	private String ciudad;
	private String tipoInterv;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getNacion() {
		return nacion;
	}
	public void setNacion(String nacion) {
		this.nacion = nacion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTipoInterv() {
		return tipoInterv;
	}
	public void setTipoInterv(String tipoInterv) {
		this.tipoInterv = tipoInterv;
	}
	public DatosPersonas(String cedula,String nombres, String nacion, String ciudad, String tipoInterv){
		this.cedula=cedula;
		this.nombres=nombres;
		this.nacion=nacion;
		this.ciudad=ciudad;
		this.tipoInterv=tipoInterv;
		
	}

}
