package clasesBean;

import java.util.Date;

public class Turno {

	private int idTurno;
	private Date fechaTur;
	private Date horaTur;
	private int idEmpleado;
	private int idPaciente;
	
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public Date getFechaTur() {
		return fechaTur;
	}
	public void setFechaTur(Date fechaTur) {
		this.fechaTur = fechaTur;
	}
	public Date getHoraTur() {
		return horaTur;
	}
	public void setHoraTur(Date horaTur) {
		this.horaTur = horaTur;
	}
	
}
