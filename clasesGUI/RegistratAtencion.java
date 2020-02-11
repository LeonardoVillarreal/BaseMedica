package clasesGui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clasesBean.Atencion;
import clasesBean.Cie;
import clasesBean.EmpleadoBean;
import clasesBean.Enfermedad_Atencion;
import servicios.ServiciosGenerales;
import servicios.VariablesGlobales;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class RegistratAtencion extends JInternalFrame implements ActionListener{
	private JTextField txtFecha;
	private JTextField txtIdEmpleado;
	private JTextField txtIdPaciente;
	private JTextField txtIdAtencion;
	private JTextField txtCedula;
	private JTextField txtHcl;
	private JTextField txtNombres;
	private JTextField txtSignos;
	private JTextArea txtaDiagnostico, txtaPrescripcion;
	DefaultTableModel modelo = new DefaultTableModel(), modeloCompleto = new DefaultTableModel();
	private JTable tablaCIE= new JTable(modelo);
	private Object arrDatos[] = new Object[4], arrDatos2[] = new Object[7], arrDatos3[] = new Object[7]; 
	private JLabel lblDinamica;
	private JButton btnMedico, btnEnfermedad, btnGuardar, btnCancelar, btnSalir;
	private JComboBox cmbMedico, cmbGrupo, cmbEspecifico;
	private JTable tableCompleta = new JTable(modeloCompleto);
	SimpleDateFormat formatoFechas = new SimpleDateFormat("yyyy-MM-dd");
	private Date fechaHoy = new Date();
	private ArrayList<String> listmedicos,listgrupo,listespecifico;
	String consultaM = "SELECT e.idEmpleado, p.nombre1Per, p.apellido1Per, p.cedulaPer FROM persona p, empleado e WHERE p.idPersona = e.idPersona ";
	private ArrayList<Atencion> lstAtencion = new ArrayList<Atencion>();
	private ArrayList<Cie> lstCie = new ArrayList<Cie>();
	private ArrayList<Enfermedad_Atencion> lstEnfAte = new ArrayList<Enfermedad_Atencion>();
	private Enfermedad_Atencion objEnfAte;
	private Atencion objAtencion;
	private Cie objCie;
	private int row;
	
	public RegistratAtencion() {
		
		super("Registrar Paciente");
		getContentPane().setLayout(null);
		

		setBounds(50, 50, 800, 676);
		JPanel panelMedico = new JPanel();
		panelMedico.setBounds(0, 0, 774, 96);
		getContentPane().add(panelMedico);
		panelMedico.setLayout(null);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(615, 11, 108, 20);
		panelMedico.add(txtFecha);
		txtFecha.setColumns(10);
		txtFecha.setText(formatoFechas.format(fechaHoy));
		
		JLabel lblMedico = new JLabel("Medico que atiende");
		lblMedico.setBounds(128, 40, 117, 14);
		panelMedico.add(lblMedico);
		
		cmbMedico = new JComboBox();
		cmbMedico.setBounds(253, 37, 141, 20);
		panelMedico.add(cmbMedico);
		llenarMedCombo(cmbMedico);
		
		btnMedico = new JButton("Aceptar");
		btnMedico.setBounds(400, 62, 89, 23);
		btnMedico.setActionCommand("medico");
		btnMedico.addActionListener(this);
		panelMedico.add(btnMedico);
		
		JLabel lblPanelNorte = new JLabel("Seleccione el medico");
		lblPanelNorte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPanelNorte.setBounds(10, 14, 187, 14);
		panelMedico.add(lblPanelNorte);
		
		JPanel panelDiagnostico = new JPanel();
		panelDiagnostico.setBounds(0, 96, 774, 204);
		getContentPane().add(panelDiagnostico);
		panelDiagnostico.setLayout(null);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico");
		lblDiagnostico.setBounds(10, 35, 78, 14);
		panelDiagnostico.add(lblDiagnostico);
		
		JLabel lblPrescripcion = new JLabel("Prescripcion");
		lblPrescripcion.setBounds(392, 35, 78, 14);
		panelDiagnostico.add(lblPrescripcion);
		
		JLabel lblAtencion = new JLabel("Datos de la Atencion");
		lblAtencion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAtencion.setBounds(10, 11, 169, 14);
		panelDiagnostico.add(lblAtencion);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setColumns(10);
		txtIdEmpleado.setBounds(80, 113, 86, 20);
		panelDiagnostico.add(txtIdEmpleado);
		txtIdEmpleado.setEditable(false);
		
		JLabel lblIdEmpleado = new JLabel("idEmpleado");
		lblIdEmpleado.setBounds(10, 119, 78, 14);
		panelDiagnostico.add(lblIdEmpleado);
		
		JLabel lblIdPAciente = new JLabel("idPaciente");
		lblIdPAciente.setBounds(10, 147, 78, 14);
		panelDiagnostico.add(lblIdPAciente);
		
		txtIdPaciente = new JTextField();
		txtIdPaciente.setColumns(10);
		txtIdPaciente.setBounds(80, 144, 86, 20);
		panelDiagnostico.add(txtIdPaciente);
		txtIdPaciente.setEditable(false);
		
		JLabel lblIdAtencion = new JLabel("idAtencion");
		lblIdAtencion.setBounds(10, 176, 78, 14);
		panelDiagnostico.add(lblIdAtencion);
		
		txtIdAtencion = new JTextField();
		txtIdAtencion.setColumns(10);
		txtIdAtencion.setBounds(80, 173, 86, 20);
		panelDiagnostico.add(txtIdAtencion);
		txtIdAtencion.setEditable(false);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(217, 176, 68, 14);
		panelDiagnostico.add(lblNombres);
		
		JLabel lblHcl = new JLabel("Historial");
		lblHcl.setBounds(217, 147, 68, 14);
		panelDiagnostico.add(lblHcl);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(217, 119, 68, 14);
		panelDiagnostico.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(287, 113, 86, 20);
		panelDiagnostico.add(txtCedula);
		txtCedula.setEditable(false);
		
		txtHcl = new JTextField();
		txtHcl.setColumns(10);
		txtHcl.setBounds(287, 144, 86, 20);
		panelDiagnostico.add(txtHcl);
		txtHcl.setEditable(false);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(287, 173, 231, 20);
		panelDiagnostico.add(txtNombres);
		txtNombres.setEditable(false);
		
		JLabel lblSignos = new JLabel("Signos Vitales");
		lblSignos.setBounds(426, 119, 92, 14);
		panelDiagnostico.add(lblSignos);
		
		txtSignos = new JTextField();
		txtSignos.setColumns(10);
		txtSignos.setBounds(533, 113, 218, 20);
		panelDiagnostico.add(txtSignos);
		txtSignos.setEditable(false);
		
		txtaDiagnostico = new JTextArea();
		txtaDiagnostico.setEditable(false);
		txtaDiagnostico.setBounds(80, 40, 276, 67);
		panelDiagnostico.add(txtaDiagnostico);
		
		txtaPrescripcion = new JTextArea();
		txtaPrescripcion.setEditable(false);
		txtaPrescripcion.setBounds(475, 35, 276, 67);
		panelDiagnostico.add(txtaPrescripcion);
		
		JPanel panelCIE = new JPanel();
		panelCIE.setBounds(0, 300, 774, 146);
		getContentPane().add(panelCIE);
		panelCIE.setLayout(null);
		
		JScrollPane scrollPaneCIE = new JScrollPane();
		scrollPaneCIE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneCIE.setBounds(433, 49, 305, 58);
		panelCIE.add(scrollPaneCIE);
		
		for(int i=1; i<=4;i++){
			modelo.addColumn("");
		}
		arrDatos[0]="ID CIE";
		arrDatos[1]="CODIGO CIE";
		arrDatos[2]="DESCRIPCION CIE";
		arrDatos[3]="ID CIE PADRE";
		modelo.addRow(arrDatos);
		
		scrollPaneCIE.setViewportView(tablaCIE);
		tablaCIE.setPreferredScrollableViewportSize(new Dimension(215, 58));
		tablaCIE.setFillsViewportHeight(true);
		
		JLabel lblDiagnosticoCie = new JLabel("Diagnostico CIE");
		lblDiagnosticoCie.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDiagnosticoCie.setBounds(10, 11, 169, 20);
		panelCIE.add(lblDiagnosticoCie);
		
		cmbGrupo = new JComboBox();
		cmbGrupo.setBounds(153, 47, 169, 20);
		panelCIE.add(cmbGrupo);
		llenarcmbGrupo(cmbGrupo);
		
		cmbGrupo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cmbGrupo.getSelectedIndex()>0) {
					llenarcmbEspecifico(cmbEspecifico);
				}
			}
		});
		cmbGrupo.setEnabled(false);
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(57, 50, 68, 14);
		panelCIE.add(lblGrupo);
		
		JLabel lblEspecifico = new JLabel("Especifico");
		lblEspecifico.setBounds(57, 89, 68, 14);
		panelCIE.add(lblEspecifico);
		
		
		cmbEspecifico = new JComboBox();
		cmbEspecifico.setBounds(153, 86, 169, 20);
		panelCIE.add(cmbEspecifico);
		cmbEspecifico.setEnabled(false);
		cmbEspecifico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(cmbEspecifico.getSelectedIndex()>0) {
					btnEnfermedad.setEnabled(true);
				}
			}
		});
		//llenarcmbEspecifico(cmbEspecifico);
		
		
		JLabel lblEnfermdedadesEnEsta = new JLabel("Enfermededades en esta atencion");
		lblEnfermdedadesEnEsta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnfermdedadesEnEsta.setBounds(489, 24, 209, 14);
		panelCIE.add(lblEnfermdedadesEnEsta);
		
		btnEnfermedad = new JButton("Aceptar");
		btnEnfermedad.setBounds(339, 112, 89, 23);
		btnEnfermedad.setActionCommand("enfermedad");
		btnEnfermedad.addActionListener(this);
		panelCIE.add(btnEnfermedad);
		btnEnfermedad.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 445, 774, 204);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblDinamica = new JLabel("");
		lblDinamica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDinamica.setBounds(10, 11, 754, 22);
		panel.add(lblDinamica);
		
		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setBounds(32, 44, 708, 108);
		panel.add(scrollPaneTabla);
		
		for(int i=1; i<=7;i++){
			modeloCompleto.addColumn("");
		}
		arrDatos2[0]="ID ATENCION";
		arrDatos2[1]="ID PACIENTE";
		arrDatos2[2]="ID EMPLEADO";
		arrDatos2[3]="NOMBRE";
		arrDatos2[4]="CEDULA";
		arrDatos2[5]="HISTORIAL";
		arrDatos2[6]="SIGNOS VITALES";
		modeloCompleto.addRow(arrDatos2);
		
		scrollPaneTabla.setViewportView(tableCompleta);
		tableCompleta.setPreferredScrollableViewportSize(new Dimension(708, 108));
		tableCompleta.setFillsViewportHeight(true);
		tableCompleta.addMouseListener(new MouseListener() {
			
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
			
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tableCompleta.getSelectedRow();
				if(tableCompleta.getSelectedRow()>0) {
					
				txtIdAtencion.setText(tableCompleta.getValueAt(row, 0).toString());
				txtIdPaciente.setText(tableCompleta.getValueAt(row, 1).toString());
				txtIdEmpleado.setText(tableCompleta.getValueAt(row, 2).toString());
				txtNombres.setText(tableCompleta.getValueAt(row, 3).toString());
				txtCedula.setText(tableCompleta.getValueAt(row, 4).toString());
				txtHcl.setText(tableCompleta.getValueAt(row, 5).toString());
				txtSignos.setText(tableCompleta.getValueAt(row, 6).toString());
				txtaDiagnostico.setEditable(true);
				txtaPrescripcion.setEditable(true);
				cmbGrupo.setEnabled(true);
				cmbEspecifico.setEnabled(true);
				
				
				objAtencion= new Atencion();
				objAtencion.setIdAtencion(Integer.parseInt(txtIdAtencion.getText().trim()));
				objAtencion.setIdEmpleado(Integer.parseInt(txtIdEmpleado.getText().trim()));
				objAtencion.setIdPaciente(Integer.parseInt(txtIdPaciente.getText().trim()));
				try {
					objAtencion.setFechaAte(formatoFechas.parse(txtFecha.getText()));
				} catch (ParseException e1) {
				}
				objAtencion.setSignosVitalesAte(txtSignos.getText());
				
				lstAtencion.add(objAtencion);
			}
			}
			});
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(241, 172, 89, 23);
		btnGuardar.setActionCommand("guardar");
		btnGuardar.addActionListener(this);
		panel.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(354, 172, 89, 23);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		panel.add(btnCancelar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(464, 172, 89, 23);
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(this);
		panel.add(btnSalir);
		
		
		this.setVisible(true);
	}
	
	/*public static void main(String[] args) {
		RegistratAtencion application = new RegistratAtencion();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean indBien = false;
		String a = e.getActionCommand();
		
		if (a.equals("salir")) {
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e1) {
			
			}
		}
		if(a.equals("cancelar")) {
			cmbMedico.setSelectedIndex(0);
			txtaDiagnostico.setText("");
			txtaDiagnostico.setEditable(false);
			txtaPrescripcion.setText("");
			txtaPrescripcion.setEditable(false);
			txtIdEmpleado.setText("");
			txtIdAtencion.setText("");
			txtIdPaciente.setText("");
			txtNombres.setText("");
			txtCedula.setText("");
			txtHcl.setText("");
			txtSignos.setText("");
			cmbGrupo.setSelectedIndex(0);
			cmbGrupo.setEnabled(false);
			cmbEspecifico.removeAllItems();
			cmbEspecifico.setEnabled(false);
			modelo.setRowCount(1);
			modeloCompleto.setRowCount(1);
			lblDinamica.setText("");
			cmbMedico.setEnabled(true);
			btnEnfermedad.setEnabled(false);
			lstCie.clear();
		}
		
		if(a.equals("medico")) {
			if(cmbMedico.getSelectedIndex()>0) {
			cmbMedico.setEnabled(false);
			lblDinamica.setText("Atenciones pendientes para medico "+cmbMedico.getSelectedItem().toString()+" en la fecha "+txtFecha.getText());
			llenarTablaAte(modeloCompleto);
			}
		}
		
		if(a.equals("enfermedad")) {
			ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT * FROM cie WHERE descripcionCIE ='"+cmbEspecifico.getSelectedItem().toString()+"'");
			while (VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
				objCie = new Cie();
				objCie.setIdCie(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idCIE")));
				objCie.setCodigoEnf(VariablesGlobales.conexionBaseMySql.getString(rs, "codigoCIE"));
				objCie.setDescripcionEnf(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionCIE"));
				objCie.setIdCiePadre(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idCIEPadre")));
				
				lstCie.add(objCie);
			}
			
				arrDatos[0]=objCie.getIdCie();
				arrDatos[1]=objCie.getCodigoEnf();
				arrDatos[2]=objCie.getDescripcionEnf();
				arrDatos[3]=objCie.getIdCiePadre();
				
				modelo.addRow(arrDatos);
			
			cmbGrupo.setSelectedIndex(0);
			cmbEspecifico.removeAllItems();
		}
		
		if(a.equals("guardar")) {
			while (indBien==false) {
				indBien=true;
				String error = "";
				if(txtaDiagnostico.getText().length()==0) {
					error+="Debe dar un diagnostico al paciente\n";
					indBien=false;
				}
				if(txtaPrescripcion.getText().length()==0) {
					error+="Debe llenar la prescripcion\n";
					indBien=false;
				}
				if(indBien==false) {
					JOptionPane.showMessageDialog(null,error , "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			VariablesGlobales.conexionBaseMySql.insertar("UPDATE atencion SET diagnosticoDescAte='"+txtaDiagnostico.getText()+"', prescripcionAte='"+txtaPrescripcion.getText()+"' WHERE idAtencion="+objAtencion.getIdAtencion()+"");
			for(Cie enfermedad:lstCie) {
				objEnfAte = new Enfermedad_Atencion();
				objEnfAte.setIdAtencion(objAtencion.getIdAtencion());
				objEnfAte.setIdCie(enfermedad.getIdCie());
				lstEnfAte.add(objEnfAte);
			}
			
			for(Enfermedad_Atencion ea: lstEnfAte) {
				VariablesGlobales.conexionBaseMySql.insertar("INSERT INTO enfermedad_atencion (idAtencion, idCie) VALUES("+ea.getIdAtencion()+","+ea.getIdCie()+")");
			}
			
			txtaDiagnostico.setText("");
			txtaDiagnostico.setEditable(false);
			txtaPrescripcion.setText("");
			txtaPrescripcion.setEditable(false);
			txtIdEmpleado.setText("");
			txtIdAtencion.setText("");
			txtIdPaciente.setText("");
			txtNombres.setText("");
			txtCedula.setText("");
			txtHcl.setText("");
			txtSignos.setText("");
			cmbGrupo.setSelectedIndex(0);
			cmbGrupo.setEnabled(false);
			cmbEspecifico.removeAllItems();
			cmbEspecifico.setEnabled(false);
			modelo.setRowCount(1);
			btnEnfermedad.setEnabled(false);
			lstCie.clear();
			modeloCompleto.removeRow(tableCompleta.getSelectedRow());
		}
	}
	
	private void llenarMedCombo(JComboBox cmbMed) {
		listmedicos= new ArrayList<String>();
		listmedicos.add("Escoja medico");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(consultaM);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listmedicos.add(VariablesGlobales.conexionBaseMySql.getString(rs, "nombre1Per")+" "+ VariablesGlobales.conexionBaseMySql.getString(rs, "apellido1Per"));
		}

		for(String item: listmedicos) {

			cmbMed.addItem(item);
		}
	}
	public void llenarcmbGrupo (JComboBox combo) {
		listgrupo= new ArrayList<String>();
		listgrupo.add("Escoja grupo de enfermedad");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT descripcionCIE from cie where idCIEPadre is null");
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listgrupo.add(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionCIE"));
		}

		for(String item: listgrupo) {
			combo.addItem(item);
		}
	}
	public void llenarcmbEspecifico (JComboBox combo) {
		combo.removeAllItems();
		listespecifico= new ArrayList<String>();
		listespecifico.add("Escoja enfermedad especifica");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT idCIE FROM cie WHERE descripcionCIE ='"+cmbGrupo.getSelectedItem().toString()+"'");
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)){
			ResultSet rs1 = VariablesGlobales.conexionBaseMySql.consulta("SELECT descripcionCIE from cie where idCIEPadre="+Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idCIE")));
			while(VariablesGlobales.conexionBaseMySql.siguiente(rs1)) {
				listespecifico.add(VariablesGlobales.conexionBaseMySql.getString(rs1, "descripcionCIE"));
			}

			for(String item: listespecifico) {
				combo.addItem(item);
			}
		}
		
	}
	private void llenarTablaAte(DefaultTableModel modelo) {
		
		StringTokenizer separador = new StringTokenizer(cmbMedico.getSelectedItem().toString()," ");
		String con = "SELECT p.idPersona FROM persona p WHERE p.nombre1Per='"+separador.nextToken()+"' and p.apellido1Per='"+separador.nextToken()+"'";
		ResultSet rsidPer = VariablesGlobales.conexionBaseMySql.consulta(con);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rsidPer)) {
			ResultSet rsidEmp = VariablesGlobales.conexionBaseMySql.consulta("SElECT idEmpleado FROM empleado WHERE idPersona="+Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rsidPer, "idPersona")));
			while(VariablesGlobales.conexionBaseMySql.siguiente(rsidEmp)) {
				ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT a.idAtencion, a.idPaciente, a.idEmpleado, concat(p.nombre1Per,' ',p.apellido1Per) as nombre, p.cedulaPer, pa.hclPac, a.signosVitalesAte" + 
						" FROM atencion a, paciente pa, persona p " + 
						"where a.idPaciente=pa.idPaciente " + 
						"and pa.idPersona=p.idPersona " +
						"and a.fechaAte = '"+txtFecha.getText()+"'"+
						"and a.diagnosticoDescAte=''"+
						"and a.idEmpleado="+Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rsidEmp, "idEmpleado"))+
						" ORDER BY a.idAtencion");
				while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
					arrDatos3[0]=(VariablesGlobales.conexionBaseMySql.getString(rs, "idAtencion"));
					arrDatos3[1]=(VariablesGlobales.conexionBaseMySql.getString(rs, "idPaciente"));
					arrDatos3[2]=(VariablesGlobales.conexionBaseMySql.getString(rs, "idEmpleado"));
					arrDatos3[3]=(VariablesGlobales.conexionBaseMySql.getString(rs, "nombre"));
					arrDatos3[4]=(VariablesGlobales.conexionBaseMySql.getString(rs, "cedulaPer"));
					arrDatos3[5]=(VariablesGlobales.conexionBaseMySql.getString(rs, "hclPac"));
					arrDatos3[6]=(VariablesGlobales.conexionBaseMySql.getString(rs, "signosVitalesAte"));
					modelo.addRow(arrDatos3);
				}
			}
		} 
		
		
		
		
	}
}
