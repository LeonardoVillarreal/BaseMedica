package clasesBean;

public abstract class PersonaBean {
	
	private int idPersona;
	private String nombre1Per;
	private String nombre2Per;
	private String apellido1Per;
	private String apellido2Per;
	private String cedulaPer;
	private String correoelectronicoPer;
	private String telefonoPer;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	
	public String getNombre1Per() {
		return nombre1Per;
	}
	public void setNombre1Per(String nombre1Per) {
		this.nombre1Per = nombre1Per;
	}
	public String getNombre2Per() {
		return nombre2Per;
	}
	public void setNombre2Per(String nombre2Per) {
		this.nombre2Per = nombre2Per;
	}
	public String getApellido1Per() {
		return apellido1Per;
	}
	public void setApellido1Per(String apellido1Per) {
		this.apellido1Per = apellido1Per;
	}
	public String getApellido2Per() {
		return apellido2Per;
	}
	public void setApellido2Per(String apellido2Per) {
		this.apellido2Per = apellido2Per;
	}
	public String getCedulaPer() {
		return cedulaPer;
	}
	public void setCedulaPer(String cedulaPer) {
		this.cedulaPer = cedulaPer;
	}
	public String getCorreoelectronicoPer() {
		return correoelectronicoPer;
	}
	public void setCorreoelectronicoPer(String correoelectronicoPer) {
		this.correoelectronicoPer = correoelectronicoPer;
	}
	public String getTelefonoPer() {
		return telefonoPer;
	}
	public void setTelefonoPer(String telefonoPer) {
		this.telefonoPer = telefonoPer;
	}
	public abstract String mayuscula(String nombre);
	
}
