package clasesGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;

import servicios.VariablesGlobales;

import clasesBean.PacienteBean;
import clasesBean.PersonaBean;
import servicios.ServiciosGenerales;
import javax.swing.ScrollPaneConstants;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;


public class ImportarPacientesGui extends JInternalFrame implements ActionListener{

	private JPanel panelGui;
	private JScrollPane panelGui2;
	private JButton btnSalir,btnSeleccioneElArchivo,btnSubirDatos,btnCancelar, btnGrabar;
	DefaultTableModel modelo = new DefaultTableModel();
	private Object [] arrDatos = new Object[14];
	String texto;
	private PacienteBean objPaciente;
	JTextField textNombre;
	private static JTextArea txtSalida;
	Font fuenteLetra = new Font("COURIER", Font.BOLD, 15);
	SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	Date fecha = null;
	JTable tabla = new JTable(modelo);
	private ArrayList<PacienteBean> lstArrPacientes = new ArrayList<PacienteBean>();
    



	public ImportarPacientesGui() {

		super("Importar Paciente");
		setResizable(false);

		//PANEL
		setBounds(100, 100, 1140, 600);
		panelGui = new JPanel();
		panelGui.setBorder(UIManager.getBorder("Tree.editorBorder"));
		panelGui.setBackground(SystemColor.textHighlight);
		panelGui.setLayout(null);
		panelGui2 = new JScrollPane();
		panelGui2.setBounds(10, 290, 1113, 193);
		panelGui.add(panelGui2);

		//BOTON SELECCIONAR ARCHIVO
		btnSeleccioneElArchivo = new JButton("SELECCIONE EL ARCHIVO");
		btnSeleccioneElArchivo.setBounds(10, 11, 168, 39);
		btnSeleccioneElArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSeleccioneElArchivo.setBackground(SystemColor.inactiveCaption);
		btnSeleccioneElArchivo.setToolTipText("Seleccione un archivo .txt");
		panelGui.add(btnSeleccioneElArchivo);
		btnSeleccioneElArchivo.setActionCommand("leer");
		btnSeleccioneElArchivo.addActionListener(this);

		//NOMBRE ARCHIVO
		textNombre = new JTextField("");
		textNombre.setBounds(822, 11, 300, 39);
		textNombre.setEditable(false);
		textNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		textNombre.setBackground(SystemColor.inactiveCaption);
		textNombre.setToolTipText("Nombre del archivo seleccionado");
		panelGui.add(textNombre);

		//BOTON SUBIR DATOS
		btnSubirDatos = new JButton("SUBIR DATOS");
		btnSubirDatos.setBounds(10, 515, 142, 46);
		btnSubirDatos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubirDatos.setBackground(SystemColor.inactiveCaption);
		btnSubirDatos.setToolTipText("Insertar paciente desde la tabla");
		panelGui.add(btnSubirDatos);
		btnSubirDatos.setActionCommand("subir");
		btnSubirDatos.addActionListener(this);

		//BOTON CANCELAR
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(510, 515, 142, 46);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBackground(SystemColor.inactiveCaption);
		btnCancelar.setToolTipText("Borra los datos de pacientes en los campos");
		panelGui.add(btnCancelar);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);

		//BOTON SALIR
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(981, 515, 142, 46);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBackground(SystemColor.inactiveCaption);
		panelGui.add(btnSalir);
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(this);
		for(int i=1; i<=14;i++){
			modelo.addColumn("");
		}


		//TEXTSALIDA
		txtSalida = new JTextArea(100,100);
		panelGui2.setViewportView(txtSalida);
		txtSalida.setBackground(Color.WHITE);
		txtSalida.setFont(fuenteLetra);
		txtSalida.setEditable(false);
		txtSalida.setToolTipText("");

		//CONTENEDOR
		Container contenedor = getContentPane();
		contenedor.add(panelGui);

		JLabel lblPacientesValidados = new JLabel("Pacientes validados");
		lblPacientesValidados.setBounds(429, 37, 139, 14);
		panelGui.add(lblPacientesValidados);

		JLabel lblPacientesConDatos = new JLabel("Pacientes con datos erroneos");
		lblPacientesConDatos.setBounds(410, 265, 180, 14);
		panelGui.add(lblPacientesConDatos);

		btnGrabar = new JButton("GRABAR");
		btnGrabar.setToolTipText("Insertar paciente desde la lista");
		btnGrabar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGrabar.setBounds(182, 515, 142, 46);
		btnGrabar.setBackground(SystemColor.inactiveCaption);
		panelGui.add(btnGrabar);
		btnGrabar.setActionCommand("grabar");
		btnGrabar.addActionListener(this);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 61, 1113, 193);
		panelGui.add(scrollPane);
		scrollPane.setViewportView(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(1113, 193));
		tabla.setFillsViewportHeight(true);


		//contenedor.add(panelGui2);

		this.setVisible(true);





	}


	/*public static void main(String[] args) {
		ImportarPacientesGui application = new ImportarPacientesGui();

		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}*/

	public void actionPerformed(ActionEvent evento) {
		String a = evento.getActionCommand();

		if(a.equals("salir"))
		{
			btnSalir.setToolTipText("Salir del programa");
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e) {
			
			}
		}

		if(a.equals("cancelar")){
			for (int i = 0; i <tabla.getRowCount()+i; i++) {           
				modelo.removeRow(0); 
			} 
			/*String cad ="SELECT * FROM persona";
			ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(cad);
			while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) 
			{
				System.out.println("idPersona:  "+VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona")
				+" nombre1Per: "+VariablesGlobales.conexionBaseMySql.getString(rs, "nombre1Per")
				+" apellido1Per: "+VariablesGlobales.conexionBaseMySql.getString(rs, "apellido1Per"));
			}*/
		}


		if(a.equals("leer")){


			JFileChooser escogerArchivo = new JFileChooser();
			escogerArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY );  
			int opcionEscogida = escogerArchivo.showSaveDialog(this);
			if ( opcionEscogida == JFileChooser.CANCEL_OPTION ){
				return;
			}
			File nombreArchivo = escogerArchivo.getSelectedFile();
			textNombre.setText(nombreArchivo.getName());

			if ( nombreArchivo == null || nombreArchivo.getName().equals( "" ) ){
				JOptionPane.showMessageDialog( this,"nombre de archivo inválido", "Error",JOptionPane.ERROR_MESSAGE );
				return;
			}
			if (nombreArchivo.exists()) {

				for (int i = 0; i <tabla.getRowCount()+i; i++) {           
					modelo.removeRow(0); 
				} 
				System.out.println(nombreArchivo.getName()
						+ " existe\n"
						+ (nombreArchivo.isFile() ? "es un archivo\n"
								: "no es un archivo\n")
						+ (nombreArchivo.isDirectory() ? "es un directorio\n"
								: "no es un directorio\n")
						+ (nombreArchivo.isAbsolute() ? "es una ruta absoluta\n"
								: "no es una ruta absoluta\n")
						+ "Ultima modificacion: " + nombreArchivo.lastModified()
						+ "\nLongitud: " + nombreArchivo.length() + "\nRuta: "
						+ nombreArchivo.getPath() + "\nRuta Absoluta: "
						+ nombreArchivo.getAbsolutePath() + "\nPadre: "
						+ nombreArchivo.getParent());
			}

			arrDatos[0]= "NOMBRE_1";
			arrDatos[1]= "NOMBRE_2";
			arrDatos[2]= "APELLIDO_1";
			arrDatos[3]= "APELLIDO_2";
			arrDatos[4]= "CORREO";
			arrDatos[5]= "CEDULA";
			arrDatos[6]= "TELEFONO";
			arrDatos[7]= "HCL";
			arrDatos[8]= "SEXO";
			arrDatos[9]= "DIRECCION";
			arrDatos[10]= "ESTADO CIVIL";
			arrDatos[11]= "FECHA NACE";
			arrDatos[12]= "LUGAR NACE";
			arrDatos[13]= "LUGAR RESIDE";
			modelo.addRow(arrDatos);
			StringTokenizer trozosLinea = null;  
			try {
				BufferedReader datosEntrada = new BufferedReader(new FileReader( nombreArchivo ) );

				while(( texto = datosEntrada.readLine())  != null) {
					trozosLinea= new StringTokenizer(texto, ";");
					String strError="";

					arrDatos[0]= trozosLinea.nextToken();
					if(arrDatos[0].toString().trim().length()<3)
						strError+="Nombre1 debe tener minimo 3 letras\n";
					if(arrDatos[0].toString().trim().length()>15)
						strError+="Nombre1 debe tener maximo 15 letras\n";
					if(!Pattern.matches("[A-Za-z]{1,}",arrDatos[0].toString()))
						strError+="Nombre1 no debe tener numeros\n";

					arrDatos[1]= trozosLinea.nextToken();
					if(arrDatos[1].toString().trim().length()<3)
						strError+="Nombre2 debe tener minimo 3 letras\n";
					if(arrDatos[1].toString().trim().length()>15)
						strError+="Nombre2 debe tener maximo 15 letras\n";
					if(!Pattern.matches("[A-Za-z]{1,}",arrDatos[1].toString()))
						strError+="Nombre2 no debe tener numeros\n";

					arrDatos[2]= trozosLinea.nextToken();
					if(arrDatos[2].toString().trim().length()<3)
						strError+="Apellido1 debe tener minimo 3 letras\n";
					if(arrDatos[2].toString().trim().length()>15)
						strError+="Apellido1 debe tener maximo 15 letras\n";
					if(!Pattern.matches("[A-Za-z]{1,}",arrDatos[2].toString()))
						strError+="Apellido1 no debe tener numeros\n";


					arrDatos[3]= trozosLinea.nextToken();
					if(arrDatos[3].toString().trim().length()<3)
						strError+="Apellido2 debe tener minimo 3 letras\n";
					if(arrDatos[3].toString().trim().length()>15)
						strError+="Apellido2 debe tener maximo 15 letras\n";
					if(!Pattern.matches("[A-Za-z]{1,}",arrDatos[3].toString()))
						strError+="Apellido2 no debe tener numeros\n";



					arrDatos[4]= trozosLinea.nextToken();
					if(!ServiciosGenerales.validarCorreo(arrDatos[4].toString().trim()))
						strError+="Caracteres erróneos en el correo electrónico\n";

					arrDatos[5]= trozosLinea.nextToken();
					String cad="";
					cad= "SELECT * FROM persona WHERE cedulaPer = '" +arrDatos[5]+"'";
					ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(cad);
					while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) 
					{
						
						strError+="Cédula ya registrada\n";
						
					}
					
					
					if(!ServiciosGenerales.validarCedula(arrDatos[5].toString().trim())) 
						strError+=ServiciosGenerales.errorCedula;
           
					arrDatos[6]= trozosLinea.nextToken();
					if(!ServiciosGenerales.validarTelefono(arrDatos[6].toString().trim())) 
						strError+=ServiciosGenerales.errorTelefono;

					arrDatos[7]= trozosLinea.nextToken();

					arrDatos[8]= trozosLinea.nextToken();
					if(arrDatos[8].toString().trim().length()<1)
						strError+="Escriba una letra para indicar el sexo\n";

					arrDatos[9]= trozosLinea.nextToken();

					arrDatos[10]= trozosLinea.nextToken();
					if(arrDatos[10].toString().trim().length()<1)
						strError+="Escriba una letra para indicar el estado civil\n";

					arrDatos[11]= trozosLinea.nextToken();
					try {
						fecha = formatoFecha.parse(arrDatos[11].toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						strError+="Escriba la fecha de nacimiento en el formato valido (yyyy-MM-dd)\n";
					}

					arrDatos[12]= trozosLinea.nextToken();
					arrDatos[13]= trozosLinea.nextToken();



					//System.out.println("\n"+txtSalida.getText()+"\n");
					if(strError.equals("")){


						modelo.addRow(arrDatos);
					}else{
						for(int i=0; i<arrDatos.length;i++){
							txtSalida.append(arrDatos[i]+" ");
						}
						txtSalida.append("\n"+strError);	
					}
				}
			}
			catch ( IOException ioException ) {
				JOptionPane.showMessageDialog( this,"Error abriendo archivo", "Error",JOptionPane.ERROR_MESSAGE );
			}
		}
		if(a.equals("subir")){

			objPaciente = new PacienteBean();
			for(int i=1;i<tabla.getRowCount();i++) {

				objPaciente.setNombre1Per(tabla.getValueAt(i, 0).toString());
				objPaciente.setNombre2Per(tabla.getValueAt(i, 1).toString());
				objPaciente.setApellido1Per(tabla.getValueAt(i, 2).toString());
				objPaciente.setApellido2Per(tabla.getValueAt(i, 3).toString());
				objPaciente.setCorreoelectronicoPer(tabla.getValueAt(i, 4).toString());
				objPaciente.setCedulaPer(tabla.getValueAt(i, 5).toString());
				objPaciente.setTelefonoPer(tabla.getValueAt(i, 6).toString());
				objPaciente.setHclPac(tabla.getValueAt(i, 7).toString());
				objPaciente.setSexoPac(tabla.getValueAt(i, 8).toString());
				objPaciente.setDireccionPac(tabla.getValueAt(i, 9).toString());
				objPaciente.setEstadoCivil(tabla.getValueAt(i, 10).toString());
				try {
					objPaciente.setFechaNacPac(formatoFecha.parse(tabla.getValueAt(i, 11).toString()));
				} catch (ParseException e) {
				}
				objPaciente.setIdLugGeoNacePac(Integer.parseInt(tabla.getValueAt(i, 12).toString()));
				objPaciente.setIdLugGeoResidePac(Integer.parseInt(tabla.getValueAt(i, 13).toString()));

				String cad="";
				cad = "INSERT INTO persona (nombre1Per, nombre2Per, apellido1Per,apellido2Per,correoPer,cedulaPer,telefonoPer) VALUES ('"+objPaciente.getNombre1Per()+"','"+objPaciente.getNombre2Per()+"','"+objPaciente.getApellido1Per()+"','"+objPaciente.getApellido2Per()+"','"+objPaciente.getCorreoelectronicoPer()+"','"+objPaciente.getCedulaPer()+"','"+objPaciente.getTelefonoPer()+"')";
				System.out.println(cad);
				VariablesGlobales.conexionBaseMySql.insertar(cad);

				cad ="SELECT * FROM persona WHERE cedulaPer = '"+objPaciente.getCedulaPer()+"'";
				ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(cad);

				while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) 
				{
					//System.out.println("idPersona:  "+VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona"));
					objPaciente.setIdPersona(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
				}


				cad = "INSERT INTO paciente (hclPac, sexoPac, direccionPac, estadoCivilPac, fechaNacPac, idLugGeoNacePac, idLugGeoResidePac, idPersona) VALUES ('"+objPaciente.getHclPac()+"','"+objPaciente.getSexo()+"','"+objPaciente.getDireccionPac()+"','"+objPaciente.getEstadoCivil()+"','"+formatoFecha.format(objPaciente.getFechaNacPac())+"','"+objPaciente.getIdLugGeoNacePac()+"','"+objPaciente.getIdLugGeoResidePac()+"','"+objPaciente.getIdPersona()+"')";
				System.out.println(cad);
				VariablesGlobales.conexionBaseMySql.insertar(cad);
			}

		}

		if (a.equals("grabar")) {

			for(int i=1;i<tabla.getRowCount();i++) {

				objPaciente = new PacienteBean();
				objPaciente.setNombre1Per(tabla.getValueAt(i, 0).toString());
				objPaciente.setNombre2Per(tabla.getValueAt(i, 1).toString());
				objPaciente.setApellido1Per(tabla.getValueAt(i, 2).toString());
				objPaciente.setApellido2Per(tabla.getValueAt(i, 3).toString());
				objPaciente.setCorreoelectronicoPer(tabla.getValueAt(i, 4).toString());
				objPaciente.setCedulaPer(tabla.getValueAt(i, 5).toString());
				objPaciente.setTelefonoPer(tabla.getValueAt(i, 6).toString());
				objPaciente.setHclPac(tabla.getValueAt(i, 7).toString());
				objPaciente.setSexoPac(tabla.getValueAt(i, 8).toString());
				objPaciente.setDireccionPac(tabla.getValueAt(i, 9).toString());
				objPaciente.setEstadoCivil(tabla.getValueAt(i, 10).toString());
				try {
					objPaciente.setFechaNacPac(formatoFecha.parse(tabla.getValueAt(i, 11).toString()));
				} catch (ParseException e) {
				}
				objPaciente.setIdLugGeoNacePac(Integer.parseInt(tabla.getValueAt(i, 12).toString()));
				objPaciente.setIdLugGeoResidePac(Integer.parseInt(tabla.getValueAt(i, 13).toString()));

				lstArrPacientes.add(objPaciente);
			}

			for(PacienteBean paciente: lstArrPacientes) {
				
				String cad="";
				cad = "INSERT INTO persona (nombre1Per, nombre2Per, apellido1Per,apellido2Per,correoPer,cedulaPer,telefonoPer) VALUES ('"+paciente.getNombre1Per()+"','"+paciente.getNombre2Per()+"','"+paciente.getApellido1Per()+"','"+paciente.getApellido2Per()+"','"+paciente.getCorreoelectronicoPer()+"','"+paciente.getCedulaPer()+"','"+paciente.getTelefonoPer()+"')";
				System.out.println(cad);
				VariablesGlobales.conexionBaseMySql.insertar(cad);

				cad ="SELECT * FROM persona WHERE cedulaPer = '"+paciente.getCedulaPer()+"'";
				ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(cad);

				while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) 
				{
					//System.out.println("idPersona:  "+VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona"));
					paciente.setIdPersona(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
				}


				cad = "INSERT INTO paciente (hclPac, sexoPac, direccionPac, estadoCivilPac, fechaNacPac, idLugGeoNacePac, idLugGeoResidePac, idPersona) VALUES ('"+paciente.getHclPac()+"','"+paciente.getSexo()+"','"+paciente.getDireccionPac()+"','"+paciente.getEstadoCivil()+"','"+formatoFecha.format(paciente.getFechaNacPac())+"','"+paciente.getIdLugGeoNacePac()+"','"+paciente.getIdLugGeoResidePac()+"','"+paciente.getIdPersona()+"')";
				System.out.println(cad);
				VariablesGlobales.conexionBaseMySql.insertar(cad);
			}
			
		}


	}
}

