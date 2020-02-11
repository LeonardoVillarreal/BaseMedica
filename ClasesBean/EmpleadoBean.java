package clasesBean;

public class EmpleadoBean extends PersonaBean{
	
	private int idEmpleado;
	private String horarioEmp;
	private int idEspecialidad;
	private int idPersona;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getHorarioEmp() {
		return horarioEmp;
	}
	public void setHorarioEmp(String horarioEmp) {
		this.horarioEmp = horarioEmp;
	}
	public String mayuscula(String nombre) {
		return nombre.toUpperCase();
	}
}
