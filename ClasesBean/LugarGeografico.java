package clasesBean;

public class LugarGeografico {
	
	private int idLugarGeografico;
	private String codigoLugGeo;
	private String descripcionLugGeo;
	private int idLugarGeograficoPadre;
	
	public int getIdLugarGeograficoPadre() {
		return idLugarGeograficoPadre;
	}
	public void setIdLugarGeograficoPadre(int idLugarGeograficoPadre) {
		this.idLugarGeograficoPadre = idLugarGeograficoPadre;
	}
	public int getIdLugarGeografico() {
		return idLugarGeografico;
	}
	public void setIdLugarGeografico(int idLugarGeografico) {
		this.idLugarGeografico = idLugarGeografico;
	}
	public String getCodigoLugGeo() {
		return codigoLugGeo;
	}
	public void setCodigoLugGeo(String codigoLugGeo) {
		this.codigoLugGeo = codigoLugGeo;
	}
	public String getDescripcionLugGeo() {
		return descripcionLugGeo;
	}
	public void setDescripcionLugGeo(String descripcionLugGeo) {
		this.descripcionLugGeo = descripcionLugGeo;
	}
}
