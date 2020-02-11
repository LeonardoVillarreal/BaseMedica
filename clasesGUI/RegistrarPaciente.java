package clasesGui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clasesBean.PacienteBean;
import servicios.ServiciosGenerales;
import servicios.VariablesGlobales;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;

public class RegistrarPaciente extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIDPaciente;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JLabel lblCI;
	private JLabel lblCorreo;
	private JLabel lblTelefono;
	private JTextField txtCI;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JLabel lblDireccion;
	private JLabel lblFechaNac;
	private JTextField txtDireccion;
	private JTextField txtFechaNac;
	private JLabel lblSexo;
	private JLabel lblLugGeoNace;
	private JTextField txtLugGeoNace;
	private JLabel lblLugGeoReside;
	private JTextField txtLugGeoReside;
	private JLabel lblhcl;
	private JTextField txthcl;
	private JLabel lblEstadoCivil;
	private JLabel lblPacRegistrados;
	private JRadioButton rdbtnMasculino,rdbtnFemenino, rdbtnCasado,rdbtnSoltero,rdbtnDivorciado,rdbtnViudo,rdbtnUnionLibre;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable tabla= new JTable(modelo);
	private Object arrDatos[] = new Object[16];
	String  provincias = "SELECT descripcionLugGeo FROM lugargeo WHERE idLugGeoPadre is null";
	String consulta ="SELECT pac.idPaciente, pe.nombre1Per, pe.nombre2Per, pe.apellido1Per, pe.apellido2Per, pe.cedulaPer,pe.correoPer, pe.telefonoPer, pac.direccionPac, pac.fechaNacPac, pac.idLugGeoNacePac,pac.idLugGeoResidePac,pac.hclPac, pac.sexoPac, pac.estadoCivilPac,pac.idPersona" + 
			" FROM paciente pac, persona pe where pac.idPersona=pe.idPersona";
	ArrayList<String> listprovincias, listcantones, listparro;
	ArrayList<PacienteBean> lstArrRegistrarPac = new ArrayList<PacienteBean>();
	private PacienteBean objPaciente;
	SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	public static ButtonGroup grdSexo;
	public static ButtonGroup grdEstadCiv;
	JComboBox cmbProvN, cmbCantN, cmbParrN, cmbProvR, cmbCantR, cmbParrR;
	private String arrProvincias[];
	private JButton btnInsertar, btnCancelar, btnSalir;


	public RegistrarPaciente() {

		super("Registrar Paciente");

		setBounds(100, 100, 1170, 666);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Tree.editorBorder"));
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setLayout(null);

		JLabel lblIDPaciente = new JLabel("ID Paciente :");
		lblIDPaciente.setBounds(10, 11, 73, 14);
		contentPane.add(lblIDPaciente);

		JLabel lblNombre1 = new JLabel("Primer nombre : ");
		lblNombre1.setBounds(10, 52, 96, 14);
		contentPane.add(lblNombre1);

		JLabel lblNombre2 = new JLabel("Segundo nombre : ");
		lblNombre2.setBounds(10, 90, 107, 14);
		contentPane.add(lblNombre2);

		JLabel lblApellido1 = new JLabel("Primer apellido :");
		lblApellido1.setBounds(10, 130, 106, 14);
		contentPane.add(lblApellido1);

		txtIDPaciente = new JTextField();
		txtIDPaciente.setBounds(127, 8, 127, 20);
		contentPane.add(txtIDPaciente);
		txtIDPaciente.setColumns(10);
		txtIDPaciente.setEditable(false);

		txtNombre1 = new JTextField();
		txtNombre1.setBounds(127, 49, 127, 20);
		contentPane.add(txtNombre1);
		txtNombre1.setColumns(10);

		txtNombre2 = new JTextField();
		txtNombre2.setBounds(127, 87, 127, 20);
		contentPane.add(txtNombre2);
		txtNombre2.setColumns(10);

		txtApellido1 = new JTextField();
		txtApellido1.setBounds(127, 127, 127, 20);
		contentPane.add(txtApellido1);
		txtApellido1.setColumns(10);

		JLabel lblNombre2_1 = new JLabel("Segundo apellido : ");
		lblNombre2_1.setBounds(264, 11, 110, 14);
		contentPane.add(lblNombre2_1);

		txtApellido2 = new JTextField();
		txtApellido2.setBounds(378, 8, 127, 20);
		contentPane.add(txtApellido2);
		txtApellido2.setColumns(10);

		lblCI = new JLabel("CI :");
		lblCI.setBounds(264, 52, 46, 14);
		contentPane.add(lblCI);

		lblCorreo = new JLabel("Correo : ");
		lblCorreo.setBounds(264, 90, 86, 14);
		contentPane.add(lblCorreo);

		lblTelefono = new JLabel("Telefono : ");
		lblTelefono.setBounds(264, 130, 97, 14);
		contentPane.add(lblTelefono);

		txtCI = new JTextField();
		txtCI.setBounds(378, 49, 127, 20);
		contentPane.add(txtCI);
		txtCI.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(378, 87, 127, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(378, 127, 127, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

		lblDireccion = new JLabel("Direccion :");
		lblDireccion.setBounds(536, 11, 69, 14);
		contentPane.add(lblDireccion);

		lblFechaNac = new JLabel("Fecha nacimiento :");
		lblFechaNac.setBounds(536, 52, 117, 14);
		contentPane.add(lblFechaNac);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(657, 8, 115, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(657, 49, 115, 20);
		contentPane.add(txtFechaNac);
		txtFechaNac.setColumns(10);

		lblSexo = new JLabel("Sexo :");
		lblSexo.setBounds(536, 90, 46, 14);
		contentPane.add(lblSexo);
		rdbtnMasculino = new JRadioButton("M",false);
		rdbtnMasculino.setBounds(659, 86, 54, 23);
		rdbtnMasculino.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnMasculino);

		rdbtnFemenino = new JRadioButton("F");
		rdbtnFemenino.setBounds(715, 86, 63, 23);
		rdbtnFemenino.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnFemenino);

		grdSexo = new ButtonGroup();
		grdSexo.add(rdbtnFemenino);
		grdSexo.add(rdbtnMasculino);

		lblLugGeoNace = new JLabel("LugGeoNace :");
		lblLugGeoNace.setBounds(813, 11, 79, 14);
		contentPane.add(lblLugGeoNace);

		txtLugGeoNace = new JTextField();
		txtLugGeoNace.setBounds(954, 8, 115, 20);
		contentPane.add(txtLugGeoNace);
		txtLugGeoNace.setColumns(10);
		txtLugGeoNace.setEditable(false);

		lblLugGeoReside = new JLabel("LugGeoReside :");
		lblLugGeoReside.setBounds(813, 52, 96, 14);
		contentPane.add(lblLugGeoReside);

		txtLugGeoReside = new JTextField();
		txtLugGeoReside.setBounds(954, 49, 115, 20);
		contentPane.add(txtLugGeoReside);
		txtLugGeoReside.setColumns(10);
		txtLugGeoReside.setEditable(false);



		lblhcl = new JLabel("Historia clinica :");
		lblhcl.setBounds(536, 130, 96, 14);
		contentPane.add(lblhcl);

		txthcl = new JTextField();
		txthcl.setBounds(657, 127, 115, 20);
		contentPane.add(txthcl);
		txthcl.setColumns(10);

		lblEstadoCivil = new JLabel("Estado civil :");
		lblEstadoCivil.setBounds(819, 90, 86, 14);
		contentPane.add(lblEstadoCivil);

		rdbtnCasado = new JRadioButton("Casado/a");
		rdbtnCasado.setBounds(954, 86, 109, 23);
		rdbtnCasado.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnCasado);

		rdbtnSoltero = new JRadioButton("Soltero/a");
		rdbtnSoltero.setBounds(1065, 86, 83, 23);
		rdbtnSoltero.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnSoltero);

		rdbtnDivorciado = new JRadioButton("Divorciado/a");
		rdbtnDivorciado.setBounds(954, 112, 109, 23);
		rdbtnDivorciado.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnDivorciado);

		rdbtnViudo = new JRadioButton("Viudo/a");
		rdbtnViudo.setBounds(1065, 112, 69, 23);
		rdbtnViudo.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnViudo);

		rdbtnUnionLibre = new JRadioButton("Union libre");
		rdbtnUnionLibre.setBounds(1018, 138, 109, 23);
		rdbtnUnionLibre.setBackground(SystemColor.textHighlight);
		contentPane.add(rdbtnUnionLibre);

		grdEstadCiv = new ButtonGroup();
		grdEstadCiv.add(rdbtnCasado);
		grdEstadCiv.add(rdbtnDivorciado);
		grdEstadCiv.add(rdbtnSoltero);
		grdEstadCiv.add(rdbtnViudo);
		grdEstadCiv.add(rdbtnUnionLibre);

		Container contenedor = getContentPane();
		contenedor.add(contentPane);

		lblPacRegistrados = new JLabel("Pacientes registrados");
		lblPacRegistrados.setBounds(479, 338, 159, 14);
		contentPane.add(lblPacRegistrados);

		for(int i=1; i<=16;i++){
			modelo.addColumn("");
		}
		arrDatos[0]="ID PACIENTE";
		arrDatos[1]="PRIMER NOMBRE";
		arrDatos[2]="SEGUNDO NOMBRE";
		arrDatos[3]="PRIMER APELLIDO";
		arrDatos[4]="SEGUNDO APELLIDO";
		arrDatos[5]="CEDULA";
		arrDatos[6]="CORREO";
		arrDatos[7]="TELEFONO";
		arrDatos[8]="DIRECCION";
		arrDatos[9]="FECHA NACIMIENTO";
		arrDatos[10]="NACE";
		arrDatos[11]="RESIDE ";
		arrDatos[12]="HISTORIA CLINICA";
		arrDatos[13]="SEXO";
		arrDatos[14]="ESTADO CIVIL";
		arrDatos[15]="ID Persona";
		modelo.addRow(arrDatos);
		
		cmbProvN = new JComboBox();
		cmbProvN.setBounds(112, 217, 159, 20);
		llenarProvCombo(cmbProvN);
		cmbProvN.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cmbProvN.getSelectedIndex()>0) {
					llenarCantonCombo(consultaIdPadre(cmbProvN), cmbCantN);
				}else {
					cmbCantN.removeAllItems();
				}
			}
		});
		contentPane.add(cmbProvN);

		cmbCantN = new JComboBox();
		cmbCantN.setBounds(112, 260, 159, 20);
		cmbCantN.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cmbCantN.getSelectedIndex()>0) {
					llenarParrCombo(consultaIdPadre(cmbCantN), cmbParrN);
				}else {
					cmbParrN.removeAllItems();
				}
			}
		});
		contentPane.add(cmbCantN);

		cmbParrN = new JComboBox();
		cmbParrN.setBounds(112, 298, 159, 20);
		cmbParrN.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cmbParrN.getSelectedIndex()>0) {
					txtLugGeoNace.setText(String.valueOf(consultaIdPadre(cmbParrN)));
				}else {
					txtLugGeoNace.setText("");
				}
			}
		});
		contentPane.add(cmbParrN);

		cmbProvR = new JComboBox();
		cmbProvR.setBounds(494, 218, 159, 20);
		llenarProvCombo(cmbProvR);
		cmbProvR.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cmbProvR.getSelectedIndex()>0) {
					llenarCantonCombo(consultaIdPadre(cmbProvR), cmbCantR);
				}else {
					cmbCantR.removeAllItems();
				}
			}
		});
		contentPane.add(cmbProvR);

		cmbCantR = new JComboBox();
		cmbCantR.setBounds(494, 260, 159, 20);
		cmbCantR.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cmbCantR.getSelectedIndex()>0) {
					llenarParrCombo(consultaIdPadre(cmbCantR), cmbParrR);
				}else {
					cmbParrR.removeAllItems();
				}
			}
		});
		contentPane.add(cmbCantR);

		cmbParrR = new JComboBox();
		cmbParrR.setBounds(494, 298, 159, 20);
		cmbParrR.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(cmbParrR.getSelectedIndex()>0) {
					txtLugGeoReside.setText(String.valueOf(consultaIdPadre(cmbParrR)));
				}else {
					txtLugGeoReside.setText("");
				}
			}
		});
		contentPane.add(cmbParrR);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 356, 1113, 193);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(1113, 193));
		tabla.setFillsViewportHeight(true);
		tabla.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if(tabla.getSelectedRow()>0) {
					txtIDPaciente.setText(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
					txtNombre1.setText(tabla.getValueAt(tabla.getSelectedRow(),1).toString());
					txtNombre2.setText(tabla.getValueAt(tabla.getSelectedRow(),2).toString());
					txtApellido1.setText(tabla.getValueAt(tabla.getSelectedRow(),3).toString());;
					txtApellido2.setText(tabla.getValueAt(tabla.getSelectedRow(),4).toString());;
					txtCI.setText(tabla.getValueAt(tabla.getSelectedRow(),5).toString());;
					txtCorreo.setText(tabla.getValueAt(tabla.getSelectedRow(),6).toString());
					txtTelefono.setText(tabla.getValueAt(tabla.getSelectedRow(),7).toString());
					txtDireccion.setText(tabla.getValueAt(tabla.getSelectedRow(),8).toString());
					txtFechaNac.setText(tabla.getValueAt(tabla.getSelectedRow(),9).toString());
					txtLugGeoNace.setText(tabla.getValueAt(tabla.getSelectedRow(),10).toString());
					txtLugGeoReside.setText(tabla.getValueAt(tabla.getSelectedRow(),11).toString());
					txthcl.setText(tabla.getValueAt(tabla.getSelectedRow(),12).toString());

					if(tabla.getValueAt(tabla.getSelectedRow(), 13).toString().equals("M"))
						rdbtnMasculino.setSelected(true);
					else
						rdbtnFemenino.setSelected(true);

					if(tabla.getValueAt(tabla.getSelectedRow(), 14).toString().equals("C")) 
						rdbtnCasado.setSelected(true);
					else if(tabla.getValueAt(tabla.getSelectedRow(), 14).toString().equals("S")) 
						rdbtnSoltero.setSelected(true);

					else if(tabla.getValueAt(tabla.getSelectedRow(), 14).toString().equals("D")) 
						rdbtnDivorciado.setSelected(true);

					else if(tabla.getValueAt(tabla.getSelectedRow(), 14).toString().equals("V"))
						rdbtnViudo.setSelected(true);

					else 
						rdbtnUnionLibre.setSelected(true);
					
					llenarCombosClick(cmbProvN, cmbCantN, cmbParrN, txtLugGeoNace);
					llenarCombosClick(cmbProvR, cmbCantR, cmbParrR, txtLugGeoReside);
				}else
					btnCancelar.doClick();
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}


		});



		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(450, 560, 89, 23);
		contentPane.add(btnInsertar);
		btnInsertar.setActionCommand("insertar");
		btnInsertar.addActionListener(this);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(549, 560, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(493, 594, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(this);

		JLabel lblLugarDeNacimiento = new JLabel("Lugar de nacimiento");
		lblLugarDeNacimiento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLugarDeNacimiento.setBounds(32, 192, 159, 14);
		contentPane.add(lblLugarDeNacimiento);

		JLabel lblLugarDeResidencia = new JLabel("Lugar de residencia");
		lblLugarDeResidencia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLugarDeResidencia.setBounds(409, 193, 159, 14);
		contentPane.add(lblLugarDeResidencia);

		

		JLabel lblProvinciaN = new JLabel("Provincia");
		lblProvinciaN.setBounds(42, 225, 75, 14);
		contentPane.add(lblProvinciaN);

		JLabel lblCantonN = new JLabel("Canton");
		lblCantonN.setBounds(42, 263, 75, 14);
		contentPane.add(lblCantonN);

		JLabel lblParroquiaN = new JLabel("Parroquia");
		lblParroquiaN.setBounds(42, 301, 75, 14);
		contentPane.add(lblParroquiaN);

		JLabel lblProvinciaR = new JLabel("Provincia");
		lblProvinciaR.setBounds(419, 225, 75, 14);
		contentPane.add(lblProvinciaR);

		JLabel lblCantonR = new JLabel("Canton");
		lblCantonR.setBounds(419, 263, 75, 14);
		contentPane.add(lblCantonR);

		JLabel lblParroquiaR = new JLabel("Parroquia");
		lblParroquiaR.setBounds(419, 301, 75, 14);
		contentPane.add(lblParroquiaR);

		llenarTabla(modelo);

		this.setVisible(true);
	}
	/*public static void main(String[] args) {
		RegistrarPaciente application = new RegistrarPaciente();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean indBien = false;

		String a = e.getActionCommand();
		String cd;
		if(a.equals("salir")) {
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e1) {
			
			}
		}
		if(a.equals("cancelar")) {

			txtIDPaciente.setText("");
			txtNombre1.setText("");
			txtNombre2.setText("");
			txtApellido1.setText("");
			txtApellido2.setText("");
			txtCI.setText("");
			txtCorreo.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtFechaNac.setText("");
			txtLugGeoNace.setText("");
			txtLugGeoReside.setText("");
			txthcl.setText("");
			grdSexo.clearSelection();
			grdEstadCiv.clearSelection();
			cmbProvN.setSelectedIndex(0);
			cmbProvR.setSelectedIndex(0);

		}
		if(a.equals("insertar")) {
			while (indBien==false) {
				indBien=true;
				String error = ServiciosGenerales.validacionTotal(txtCI.getText().trim(), txtNombre1.getText().trim(), txtNombre2.getText().trim(),txtApellido1.getText().trim(), txtApellido2.getText().trim(), txtTelefono.getText().trim(), txtCorreo.getText().trim(), txthcl.getText().trim(), txtFechaNac.getText().trim());
				try {
					Integer.parseInt(txtLugGeoNace.getText().trim());
					Integer.parseInt(txtLugGeoReside.getText().trim());
				}catch(Exception de) {
					error+= "Debe ingresar el codigo numerico en los id de nacimiento y residencia\n";
					indBien=false;
				}
				if(!error.equals("")) {
					indBien=false;
					JOptionPane.showMessageDialog(null,error , "Mensaje de error al digitar", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}


			objPaciente = new PacienteBean();
			objPaciente.setNombre1Per(txtNombre1.getText().trim().toUpperCase());
			objPaciente.setNombre2Per(txtNombre2.getText().trim().toUpperCase());
			objPaciente.setApellido1Per(txtApellido1.getText().trim().toUpperCase());
			objPaciente.setApellido2Per(txtApellido2.getText().trim().toUpperCase());
			objPaciente.setCedulaPer(txtCI.getText().trim());
			objPaciente.setTelefonoPer(txtTelefono.getText().trim());
			objPaciente.setCorreoelectronicoPer(txtCorreo.getText().trim());
			objPaciente.setHclPac(txthcl.getText().trim());
			objPaciente.setDireccionPac(txtDireccion.getText().trim().toUpperCase());
			try {
				objPaciente.setFechaNacPac(formatoFecha.parse(txtFechaNac.getText().trim()));
			} catch (ParseException e1) {
			}
			objPaciente.setIdLugGeoNacePac(Integer.parseInt(txtLugGeoNace.getText().trim()));
			objPaciente.setIdLugGeoResidePac(Integer.parseInt(txtLugGeoReside.getText().trim()));

			if(rdbtnCasado.isSelected()) 
				objPaciente.setEstadoCivil("C");

			else if(rdbtnSoltero.isSelected()) 
				objPaciente.setEstadoCivil("S");

			else if(rdbtnDivorciado.isSelected()) 
				objPaciente.setEstadoCivil("D");

			else if(rdbtnViudo.isSelected())
				objPaciente.setEstadoCivil("V");

			else 
				objPaciente.setEstadoCivil("U");


			if(rdbtnMasculino.isSelected())
				objPaciente.setSexo("M");

			else
				objPaciente.setSexo("F");


			if(txtIDPaciente.getText().trim().length()==0) {

				cd = "INSERT INTO persona (nombre1Per, nombre2Per, apellido1Per,apellido2Per,correoPer,cedulaPer,telefonoPer) VALUES ('"+objPaciente.getNombre1Per()+"','"+objPaciente.getNombre2Per()+"','"+objPaciente.getApellido1Per()+"','"+objPaciente.getApellido2Per()+"','"+objPaciente.getCorreoelectronicoPer()+"','"+objPaciente.getCedulaPer()+"','"+objPaciente.getTelefonoPer()+"')";
				System.out.println(cd);
				VariablesGlobales.conexionBaseMySql.insertar(cd);
				cd ="SELECT idPersona FROM persona WHERE cedulaPer = '"+objPaciente.getCedulaPer()+"'";
				ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(cd);
				while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
					objPaciente.setIdPersona(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
				}

				cd = "INSERT INTO paciente (hclPac, sexoPac, direccionPac, estadoCivilPac, fechaNacPac, idLugGeoNacePac, "
						+ "idLugGeoResidePac, idPersona) VALUES ('"+objPaciente.getHclPac()+"','"+objPaciente.getSexo()+"','"+objPaciente.getDireccionPac()+
						"','"+objPaciente.getEstadoCivil()+"','"+formatoFecha.format(objPaciente.getFechaNacPac())+"',"+objPaciente.getIdLugGeoNacePac()+","+objPaciente.getIdLugGeoResidePac()+","+objPaciente.getIdPersona()+")";
				System.out.println(cd);
				VariablesGlobales.conexionBaseMySql.insertar(cd);
				actualizarLista();
				insertarTabla(modelo, lstArrRegistrarPac);
			}else {
				cd = "UPDATE persona SET nombre1Per = '"+objPaciente.getNombre1Per()+"' , nombre2Per ='"+objPaciente.getNombre2Per()+"' , apellido1Per = '"+objPaciente.getApellido1Per()+
						"',apellido2Per= '"+objPaciente.getApellido2Per()+"',correoPer = '"+objPaciente.getCorreoelectronicoPer()+"',cedulaPer = 	'"+objPaciente.getCedulaPer()+"',"
						+ "telefonoPer= '"+objPaciente.getTelefonoPer()+"' WHERE idPersona = "+Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(),15).toString())+"";
				System.out.println(cd);
				VariablesGlobales.conexionBaseMySql.insertar(cd);
				cd ="SELECT idPersona FROM persona WHERE cedulaPer = '"+objPaciente.getCedulaPer()+"'";
				ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(cd);
				while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
					objPaciente.setIdPersona(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
				}
				cd= "UPDATE paciente SET  hclPac= '"+objPaciente.getHclPac()+"', sexoPac= '"+objPaciente.getSexo()+"', direccionPac= '"+objPaciente.getDireccionPac()+"',estadoCivilPac = '"+objPaciente.getEstadoCivil()+"', fechaNacPac= '"+formatoFecha.format(objPaciente.getFechaNacPac())+"',"
						+ "idLugGeoNacePac= "+objPaciente.getIdLugGeoNacePac()+" ,idLugGeoResidePac= "+objPaciente.getIdLugGeoResidePac()+"  WHERE idPersona = "+objPaciente.getIdPersona()+"";
				System.out.println(cd);
				VariablesGlobales.conexionBaseMySql.insertar(cd);
				actualizarLista();
				actualizarTabla(modelo, lstArrRegistrarPac, tabla);
			}
		}

	}



	public void llenarProvCombo(JComboBox combo) {
		listprovincias= new ArrayList<String>();
		listprovincias.add("Escoja la provincia");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(provincias);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listprovincias.add(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo"));
		}

		for(String item: listprovincias) {
			combo.addItem(item);
		}
	}

	public void llenarCantonCombo(Integer idpadre, JComboBox combo) {
		combo.removeAllItems();
		listcantones= new ArrayList<String>();
		listcantones.add("Escoja el canton");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT descripcionLugGeo FROM lugargeo WHERE idLugGeoPadre = "+idpadre+"");
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listcantones.add(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo"));
		}
		for(String item: listcantones) {
			System.out.println(item);
			combo.addItem(item);
		}
	}

	public void llenarParrCombo(Integer idpadre, JComboBox combo) {
		combo.removeAllItems();
		listparro= new ArrayList<String>();
		listparro.add("Escoja la parroquia");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT descripcionLugGeo FROM lugargeo WHERE idLugGeoPadre = "+idpadre+"");
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listparro.add(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo"));
		}

		for(String item: listparro) {
			combo.addItem(item);
		}
	}

	public Integer consultaIdPadre(JComboBox combo) {

		int id =0;
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT idLugarGeo FROM lugargeo WHERE descripcionLugGeo ='"+combo.getSelectedItem().toString()+"'");
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			id = Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo"));
		}
		return id;
	}

	private void insertarTabla(DefaultTableModel modelo, ArrayList<PacienteBean> lstArrRegistrarPac) {
		Object arr[]= new Object[16];
		arr[0]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getIdPaciente();
		arr[1]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getNombre1Per();
		arr[2]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getNombre2Per();
		arr[3]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getApellido1Per();
		arr[4]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getApellido2Per();
		arr[5]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getCedulaPer();
		arr[6]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getCorreoelectronicoPer();
		arr[7]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getTelefonoPer();
		arr[8]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getDireccionPac();
		arr[9]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getFechaNacPac();
		arr[10]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getIdLugGeoNacePac();
		arr[11]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getIdLugGeoResidePac();
		arr[12]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getHclPac();
		arr[13]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getSexoPac();
		arr[14]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getEstadoCivil();
		arr[15]=lstArrRegistrarPac.get(lstArrRegistrarPac.size()-1).getIdPersona();
		modelo.addRow(arr);

	}

	private void llenarTabla(DefaultTableModel modelo) {
		Object arrDatos[] = new Object[16];
		
		ResultSet res = VariablesGlobales.conexionBaseMySql.consulta(consulta);
		while(VariablesGlobales.conexionBaseMySql.siguiente(res)) {
			objPaciente = new PacienteBean();
			objPaciente.setIdPaciente(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(res, "idPaciente").toString()));
			objPaciente.setNombre1Per(VariablesGlobales.conexionBaseMySql.getString(res, "nombre1Per").toString());
			objPaciente.setNombre2Per(VariablesGlobales.conexionBaseMySql.getString(res, "nombre2Per").toString());
			objPaciente.setApellido1Per(VariablesGlobales.conexionBaseMySql.getString(res, "apellido1Per").toString());
			objPaciente.setApellido2Per(VariablesGlobales.conexionBaseMySql.getString(res, "apellido2Per").toString());
			objPaciente.setCedulaPer(VariablesGlobales.conexionBaseMySql.getString(res, "cedulaPer").toString());
			try {
				objPaciente.setCorreoelectronicoPer(VariablesGlobales.conexionBaseMySql.getString(res, "correoPer").toString());
			}catch(Exception e) {
				objPaciente.setCorreoelectronicoPer("");
			}
			objPaciente.setTelefonoPer(VariablesGlobales.conexionBaseMySql.getString(res, "telefonoPer").toString());
			objPaciente.setDireccionPac(VariablesGlobales.conexionBaseMySql.getString(res, "direccionPac").toString());	
			try {
				objPaciente.setFechaNacPac(formatoFecha.parse(VariablesGlobales.conexionBaseMySql.getString(res, "fechaNacPac")));
			} catch (ParseException e) {

			}	
			objPaciente.setIdLugGeoNacePac(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(res, "idLugGeoNacePac").toString()));
			objPaciente.setIdLugGeoResidePac(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(res, "idLugGeoResidePac").toString()));
			objPaciente.setHclPac(VariablesGlobales.conexionBaseMySql.getString(res, "hclPac").toString());
			objPaciente.setSexoPac(VariablesGlobales.conexionBaseMySql.getString(res, "sexoPac").toString());
			objPaciente.setEstadoCivil(VariablesGlobales.conexionBaseMySql.getString(res, "estadoCivilPac").toString());
			objPaciente.setIdPersona(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(res, "idPersona").toString()));


			lstArrRegistrarPac.add(objPaciente);

		}
		
		for(PacienteBean objpaciente: lstArrRegistrarPac) {

			arrDatos[0]=objpaciente.getIdPaciente();
			arrDatos[1]=objpaciente.getNombre1Per();
			arrDatos[2]=objpaciente.getNombre2Per();
			arrDatos[3]=objpaciente.getApellido1Per();
			arrDatos[4]=objpaciente.getApellido2Per();
			arrDatos[5]=objpaciente.getCedulaPer();
			arrDatos[6]=objpaciente.getCorreoelectronicoPer();
			arrDatos[7]=objpaciente.getTelefonoPer();
			arrDatos[8]=objpaciente.getDireccionPac();
			arrDatos[9]=formatoFecha.format(objpaciente.getFechaNacPac());
			arrDatos[10]=objpaciente.getIdLugGeoNacePac();
			arrDatos[11]=objpaciente.getIdLugGeoResidePac();
			arrDatos[12]=objpaciente.getHclPac();
			arrDatos[13]=objpaciente.getSexo();
			arrDatos[14]=objpaciente.getEstadoCivil();
			arrDatos[15]=objpaciente.getIdPersona();
			modelo.addRow(arrDatos);
		}
	}

	private void actualizarTabla(DefaultTableModel modelo, ArrayList<PacienteBean> lstArrRegistrarPac,	JTable tabla) {
		for(int i = 1; i<tabla.getRowCount();i++) {
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getIdPaciente(), i, 0);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getNombre1Per(), i, 1);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getNombre2Per(), i, 2);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getApellido1Per(), i, 3);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getApellido2Per(), i, 4);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getCedulaPer(), i, 5);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getCorreoelectronicoPer(), i, 6);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getTelefonoPer(), i, 7);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getDireccionPac(), i, 8);
			modelo.setValueAt(formatoFecha.format(lstArrRegistrarPac.get(i-1).getFechaNacPac()), i, 9);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getIdLugGeoNacePac(), i, 10);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getIdLugGeoResidePac(), i, 11);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getHclPac(), i, 12);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getSexoPac(), i, 13);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getEstadoCivil(), i, 14);
			modelo.setValueAt(lstArrRegistrarPac.get(i-1).getIdPersona(), i, 15);
		}
	}
	private void actualizarLista() {
		lstArrRegistrarPac.clear();
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(consulta);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			objPaciente = new PacienteBean();
			objPaciente.setIdPaciente(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idPaciente").toString()));
			objPaciente.setNombre1Per(VariablesGlobales.conexionBaseMySql.getString(rs, "nombre1Per").toString());
			try {
				objPaciente.setNombre2Per(VariablesGlobales.conexionBaseMySql.getString(rs, "nombre2Per").toString());
			}catch(Exception e) {
				objPaciente.setNombre2Per("");
			}
			objPaciente.setApellido1Per(VariablesGlobales.conexionBaseMySql.getString(rs, "apellido1Per").toString());
			try {
				objPaciente.setApellido2Per(VariablesGlobales.conexionBaseMySql.getString(rs, "apellido2Per").toString());
			}catch(Exception e) {
				objPaciente.setApellido2Per("");
			}
			objPaciente.setCedulaPer(VariablesGlobales.conexionBaseMySql.getString(rs, "cedulaPer").toString());
			try {
				objPaciente.setCorreoelectronicoPer(VariablesGlobales.conexionBaseMySql.getString(rs, "correoPer").toString());
			}catch(Exception e) {
				objPaciente.setCorreoelectronicoPer("");
			}
			objPaciente.setTelefonoPer(VariablesGlobales.conexionBaseMySql.getString(rs, "telefonoPer").toString());
			objPaciente.setDireccionPac(VariablesGlobales.conexionBaseMySql.getString(rs, "direccionPac").toString());
			try {
				objPaciente.setFechaNacPac(formatoFecha.parse(VariablesGlobales.conexionBaseMySql.getString(rs, "fechaNacPac").toString()));
			} catch (ParseException e) {
			}
			objPaciente.setIdLugGeoNacePac(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idLugGeoNacePac").toString()));
			objPaciente.setIdLugGeoResidePac(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idLugGeoResidePac").toString()));
			objPaciente.setHclPac(VariablesGlobales.conexionBaseMySql.getString(rs, "hclPac").toString());
			objPaciente.setSexoPac(VariablesGlobales.conexionBaseMySql.getString(rs, "sexoPac").toString());
			objPaciente.setEstadoCivil(VariablesGlobales.conexionBaseMySql.getString(rs, "estadoCivilPac").toString());
			objPaciente.setIdPersona(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idPersona").toString()));

			lstArrRegistrarPac.add(objPaciente);	
		}
		
	

	}
	
	public void llenarCombosClick(JComboBox comboProvi, JComboBox comboCanton, JComboBox comboParr, JTextField texto) {
		int idpadreParroquia=0, idpadreCanton=0;
		String descripcionParr="", descripcionCanton="", descripcionProv="";
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT idLugGeoPadre, descripcionLugGeo FROM lugargeo WHERE idLugarGeo = "+Integer.parseInt(texto.getText().trim()));
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			idpadreParroquia = Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idLugGeoPadre"));
			descripcionParr = (VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo"));
		}
		ResultSet rs1 = VariablesGlobales.conexionBaseMySql.consulta("SELECT idLugGeoPadre, descripcionLugGeo FROM lugargeo WHERE idLugarGeo = "+idpadreParroquia);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs1)) {
			idpadreCanton = Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs1, "idLugGeoPadre"));
			descripcionCanton = (VariablesGlobales.conexionBaseMySql.getString(rs1, "descripcionLugGeo"));
		}
		ResultSet rs2 = VariablesGlobales.conexionBaseMySql.consulta("SELECT descripcionLugGeo FROM lugargeo WHERE idLugarGeo = "+idpadreCanton);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs2)) {
			
			descripcionProv = (VariablesGlobales.conexionBaseMySql.getString(rs2, "descripcionLugGeo"));
		}
		System.out.println(descripcionProv);
		for(int i=0;i<listprovincias.size();i++) {
			if(listprovincias.get(i).equals(descripcionProv)) {
				comboProvi.setSelectedIndex(i);
			}
		}
		for(int i=0;i<listcantones.size();i++) {
			if(listcantones.get(i).equals(descripcionCanton)) {
				comboCanton.setSelectedIndex(i);
			}
		}
		for(int i=0;i<listparro.size();i++) {
			if(listparro.get(i).equals(descripcionParr)) {
				comboParr.setSelectedIndex(i);
			}
		}
	}
}


	