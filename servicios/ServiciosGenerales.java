package servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import clasesGui.RegistrarPaciente;

public class ServiciosGenerales {

	private static final String patronEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static String errorCedula="";
	public static String errorNombreApellido="";
	public static String errorTelefono="";
	public static String errorHcl="";
	public static String errorFecha="", errorCodigoLugGeo="";
	static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	public static Date fecha = null;

	public static boolean validarCedula(String cedula) {
		String numeros="0123456789";
		int i, spares, simpares, rverificador;
		int a0, a2, a4, a6, a8, a1, a3, a5, a7;
		if(cedula.length()==10) {
			for(i=0; i<cedula.length(); i++) {
				if(numeros.indexOf(cedula.charAt(i), 0)==-1) {
					errorCedula="Ingrese dígitos únicamente en cédula\n";
					return false;
				}
			}

			if(Integer.parseInt(cedula.substring(0,2))>0 && Integer.parseInt(cedula.substring(0,2))<=24) {
				a0 = Integer.parseInt(cedula.substring(0,1))*2;
				if (a0>9) {
					a0-=9;
				}
				a1 = Integer.parseInt(cedula.substring(1,2));
				a2 = Integer.parseInt(cedula.substring(2,3))*2;
				if (a2>9) {
					a2-=9;
				}
				a3 = Integer.parseInt(cedula.substring(3,4));
				a4 = Integer.parseInt(cedula.substring(4,5))*2;
				if (a4>9) {
					a4-=9;
				}
				a5 = Integer.parseInt(cedula.substring(5,6));
				a6 = Integer.parseInt(cedula.substring(6,7))*2;
				if (a6>9) {
					a6-=9;
				}
				a7 = Integer.parseInt(cedula.substring(7,8));
				a8 = Integer.parseInt(cedula.substring(8,9))*2;
				if (a8>9) {
					a8-=9;
				}
				simpares = a0+a2+a4+a6+a8;
				spares = a1+a3+a5+a7;
				rverificador = Integer.parseInt(String.valueOf(simpares + spares + 10).substring(0, 1) + "0") - (simpares + spares);
				if(rverificador == 10) {
					rverificador = 0;
				}
				if (rverificador == Integer.parseInt(cedula.substring(9))) {
					return true;
				}
				errorCedula="El dígito verificador en cedula no concuerda\n";
				return false;
			}
			errorCedula="Los dos primeros dígitos no concuerdan con el código de una provincia\n";
			return false;

		}
		errorCedula="La cédula debe tener 10 dígitos\n";
		return false;
	}

	public static boolean validarNombreApellido(String porvalidar) {
		String letras="abcdefghijklmnñopqrstuvwxyz";
		int i;

		if((porvalidar.length()>=3) &&(porvalidar.length()<=15)) {
			porvalidar=porvalidar.toLowerCase();

			for(i=0; i<porvalidar.length(); i++) {
				if(letras.indexOf(porvalidar.charAt(i), 0)==-1) {
					errorNombreApellido="Debe ingresar letras únicamente en ";
					return false;
				}
			}
			return true;
		}	
		errorNombreApellido="Debe ingresar entre 3 y 15 letras en ";
		return false;
	}

	public static boolean validarTelefono(String numero) {
		String numeros="0123456789";
		int i;
		if(numero.length()==10) {
			for(i=0; i<numero.length(); i++) {
				if(numeros.indexOf(numero.charAt(i), 0)==-1) {
					errorTelefono="Digite números únicamente en teléfono\n";
					return false;
				}
			}
			return true;
		}
		errorTelefono="Ingrese un número de 10 dígitos en teléfono\n";
		return false;
	}

	public static boolean validarHcl(String numero) {
		String numeros="0123456789";
		int i;
		if(numero.length()==8) {
			for(i=0; i<numero.length(); i++) {
				if(numeros.indexOf(numero.charAt(i), 0)==-1) {
					errorHcl="Digite números únicamente en historia clinica\n";
					return false;
				}
			}
			return true;
		}
		errorHcl="Ingrese un número de 8 dígitos en historia clinica\n";
		return false;
	}
	
	public static boolean validarCodigoLugGeo(String numero) {
		String numeros="0123456789";
		int i;
		if(numero.length()>=2) {
			for(i=0; i<numero.length(); i++) {
				if(numeros.indexOf(numero.charAt(i), 0)==-1) {
					errorCodigoLugGeo="Digite números únicamente en codigo\n";
					return false;
				}
			}
			return true;
		}
		errorCodigoLugGeo="Ingrese un número de al menos 2 dígitos en codigo\n";
		return false;
	}

	public static boolean validarFecha(String fechaNac) {
		try {
			fecha = formatoFecha.parse(fechaNac);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			errorFecha="Escriba la fecha de nacimiento en el formato valido (yyyy-MM-dd)\n";
			return false;
		}
	}

	public static boolean validarCorreo(String email) {
		Pattern patronExpRegular = Pattern.compile(patronEmail);
		Matcher comparar = patronExpRegular.matcher(email);
		return comparar.matches();
	}

	public static String validacionTotal(String cedula, String nombre1, String nombre2, String apell1, String apell2, String telf, String mail, String hcl, String fechaNac) {
		String strError="";
		if(!validarCedula(cedula)) {
			strError+=ServiciosGenerales.errorCedula;
		}
		if(!validarNombreApellido(nombre1)) {
			strError+=ServiciosGenerales.errorNombreApellido+"el primer nombre\n";

		}
		if(!nombre2.equals("")) {
			if(!ServiciosGenerales.validarNombreApellido(nombre2)) {
				strError+=ServiciosGenerales.errorNombreApellido+"el segundo nombre\n";
			}
		}
		if(!validarNombreApellido(apell1)) {
			strError+=ServiciosGenerales.errorNombreApellido+"el primer apellido\n";
		}
		if(!apell2.equals("")) {
			if(!ServiciosGenerales.validarNombreApellido(apell2)) {
				strError+=ServiciosGenerales.errorNombreApellido+"el segundo apellido\n";
			}
		}
		if(!validarTelefono(telf)) {
			strError+=ServiciosGenerales.errorTelefono;
		}
		if(!validarHcl(hcl)) {
			strError+=ServiciosGenerales.errorHcl;
		}
		if(!mail.equals("")) {
			if(!validarCorreo(mail)) {
				strError+="Caracteres erróneos en el correo electrónico\n";
			}
		}	
		if(RegistrarPaciente.grdSexo.getSelection()==null) {
			strError+="Debe seleccionar el sexo\n";
		}
		if(RegistrarPaciente.grdEstadCiv.getSelection()==null) {
			strError+="Debe seleccionar el estado civil\n";
		}
		if(!validarFecha(fechaNac)) {
			strError+=errorFecha;
		}
		return strError;
	}
}
