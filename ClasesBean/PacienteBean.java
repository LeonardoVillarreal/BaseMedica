package clasesBean;

import java.util.Date;

public class PacienteBean extends PersonaBean{
	
	private int idPaciente;
	private Date fechaNacPac;
	private String sexoPac;
	private int idLugGeoNacePac;
	private int idLugGeoResidePac;
	private String hclPac;
	private String direccionPac;
	private int idPersona;
	private String estadoCivil;
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Date getFechaNacPac() {
		return fechaNacPac;
	}
	public void setFechaNacPac(Date fechaNacPac) {
		this.fechaNacPac = fechaNacPac;
	}
	public String getSexoPac() {
		return sexoPac;
	}
	public void setSexoPac(String sexoPac) {
		this.sexoPac = sexoPac;
	}
	public int getIdLugGeoNacePac() {
		return idLugGeoNacePac;
	}
	public void setIdLugGeoNacePac(int idLugarGeografico) {
		this.idLugGeoNacePac = idLugarGeografico;
	}
	public int getIdLugGeoResidePac() {
		return idLugGeoResidePac;
	}
	public void setIdLugGeoResidePac(int idLugGeoResidePac) {
		this.idLugGeoResidePac = idLugGeoResidePac;
	}
	public String getDireccionPac() {
		return direccionPac;
	}
	public void setDireccionPac(String direccionPac) {
		this.direccionPac = direccionPac;
	}
	public String getSexo() {
		return sexoPac;
	}
	public void setSexo(String sexo) {
		this.sexoPac = sexo;
	}
	public String getHclPac() {
		return hclPac;
	}
	public void setHclPac(String hclPac) {
		this.hclPac = hclPac;
	}
	public String mayuscula(String nombre) {
		return nombre.toUpperCase();
	}
}
