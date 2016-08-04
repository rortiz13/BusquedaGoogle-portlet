package la.netco.entities;

import java.io.Serializable;


public class DatosBusq implements Serializable {

	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	//private int codigo;
	private String nRegistro;
	private String tipoObra;
	private String fechaReg; 
	private String tituloObra;
	private String fechaCre;
	private String nomAutor;
	private String papAutor;
	private String sapAutor;
	private String descObra;
	private String regTor;
	private String obs;
	
	


	public String getRegTor() {
		return regTor;
	}

	public void setRegTor(String regTor) {
		this.regTor = regTor;
	}

	public String getDescObra() {
		return descObra;
	}

	public void setDescObra(String descObra) {
		this.descObra = descObra;
	}

	public String getNomAutor() {
		return nomAutor;
	}

	public void setNomAutor(String nomAutor) {
		this.nomAutor = nomAutor;
	}

	public String getPapAutor() {
		return papAutor;
	}

	public void setPapAutor(String papAutor) {
		this.papAutor = papAutor;
	}

	public String getSapAutor() {
		return sapAutor;
	}

	public void setSapAutor(String sapAutor) {
		this.sapAutor = sapAutor;
	}

	public DatosBusq(){
	}
	
	public DatosBusq( String nRegistro, String tipoObra,String fechaReg, String tituloObra, String fechaCre, String nomAutor,String papAutor,String sapAutor, String descObra,String regTor, String obs) {
	//this.codigo = codigo;
	this.nRegistro = nRegistro;
	this.tipoObra = tipoObra;
	this.fechaReg = fechaReg;
	this.tituloObra = tituloObra;
	this.fechaCre = fechaCre;
	this.nomAutor=nomAutor;
	this.papAutor=papAutor;
	this.sapAutor=sapAutor;
	this.descObra=descObra;
	this.regTor=regTor;
	this.obs=obs;
	
	}
	/*public int getCodigo() {
	return codigo;
	}
	public void setCodigo(int codigo) {
	this.codigo = codigo;
	}*/
	
	public String getnRegistro() {
		return nRegistro;
	}
	
	public void setnRegistro(String nRegistro) {
		this.nRegistro = nRegistro;
	}
	
	public String getTipoObra() {
		return tipoObra;
	}
	
	public void setTipoObra(String tipoObra) {
		this.tipoObra = tipoObra;
	}
	
	public String getFechaReg() {
		return fechaReg;
	}
	
	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}
	
	public String getTituloObra() {
		return tituloObra;
	}
	
	public void setTituloObra(String tituloObra) {
		this.tituloObra = tituloObra;
	}
	
	public String getFechaCre() {
		return fechaCre;
	}
	
	public void setFechaCre(String fechaCre) {
		this.fechaCre = fechaCre;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
}
