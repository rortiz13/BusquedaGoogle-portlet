package la.netco.entities;

import java.io.Serializable;

public class Solicitante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomApe;
	private String ident;
	private String nacionalidad;
	private String telefono;
	private String correo;
	private String direccion;
	private String ciudad;
	
	public Solicitante(String nomApe,String ident,String nacionalidad, String telefono, String correo, String direccion, String ciudad){
		this.nomApe=nomApe;
		this.ident=ident;
		this.nacionalidad=nacionalidad;
		this.telefono=telefono;
		this.correo=correo;
		this.direccion=direccion;
		this.ciudad=ciudad;				
		
	}
	
	public String getNomApe() {
		return nomApe;
	}
	public void setNomApe(String nomApe) {
		this.nomApe = nomApe;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	

}
