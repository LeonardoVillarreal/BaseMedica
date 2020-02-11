package clasesBean;

import java.util.Date;

public class Atencion {

	private int idAtencion;
	private Date fechaAte;
	private String diagnosticoDescAte;
	private String prescripcionAte;
	private Double valorCobradoAte;
	private int numeroFacturaAte;
	private String signosVitalesAte;
	private int idEmpleado;
	private int idPaciente;

	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public Date getFechaAte() {
		return fechaAte;
	}
	public void setFechaAte(Date fechaAte) {
		this.fechaAte = fechaAte;
	}
	public String getDiagnosticoDescAte() {
		return diagnosticoDescAte;
	}
	public void setDiagnosticoDescAte(String diagnosticoDescAte) {
		this.diagnosticoDescAte = diagnosticoDescAte;
	}
	public String getPrescripcionAte() {
		return prescripcionAte;
	}
	public void setPrescripcionAte(String prescripcionAte) {
		this.prescripcionAte = prescripcionAte;
	}
	public Double getValorCobradoAte() {
		return valorCobradoAte;
	}
	public void setValorCobradoAte(Double valorCobradoAte) {
		this.valorCobradoAte = valorCobradoAte;
	}
	public int getNumeroFacturaAte() {
		return numeroFacturaAte;
	}
	public void setNumeroFacturaAte(int numeroFacturaAte) {
		this.numeroFacturaAte = numeroFacturaAte;
	}
	public String getSignosVitalesAte() {
		return signosVitalesAte;
	}
	public void setSignosVitalesAte(String signosVitalesAte) {
		this.signosVitalesAte = signosVitalesAte;
	}
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
	
}
