package clasesGui;

import java.util.StringTokenizer;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesBean.Atencion;
import clasesBean.PacienteBean;
import servicios.VariablesGlobales;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class RegistrarSigVitales extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtFecha,txtSigVit;

	private JLabel lblFecha, lblSigVitales;
	JComboBox cmbPac,cmbMed;
	JButton btnGuardar,btnCancelar,btnSalir;
	private JLabel lblPaciente, lblMedicoAt;
	String consultaP = "SELECT pa.idPaciente, p.nombre1Per, p.apellido1Per, p.cedulaPer FROM persona p, paciente pa WHERE p.idPersona = pa.idPersona";
	String consultaM = "SELECT e.idEmpleado, p.nombre1Per, p.apellido1Per, p.cedulaPer FROM persona p, empleado e WHERE p.idPersona = e.idPersona ";
	ArrayList<String> listpacientes,listmedicos;
	ArrayList<Atencion> lstAtencion = new ArrayList<Atencion>();
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	private Date fechaActual = new Date();
	private Atencion objAtencion;
	public RegistrarSigVitales() {
		super("Registrar signos vitales");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFecha = new JLabel("Fecha :");
		lblFecha.setBounds(28, 25, 46, 14);
		contentPane.add(lblFecha);
		txtFecha = new JTextField();
		txtFecha.setBounds(146, 22, 176, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		txtFecha.setEditable(false);
		txtFecha.setText(formatoFecha.format(fechaActual));


		lblSigVitales = new JLabel("Signos vitales :");
		lblSigVitales.setBounds(28, 185, 106, 14);
		contentPane.add(lblSigVitales);



		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(45, 239, 89, 23);
		btnGuardar.setActionCommand("guardar");
		btnGuardar.addActionListener(this);
		contentPane.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(166, 239, 89, 23);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(289, 239, 89, 23);
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);

		txtSigVit = new JTextField();
		txtSigVit.setBounds(146, 182, 176, 20);
		contentPane.add(txtSigVit);
		txtSigVit.setColumns(10);

		cmbPac = new JComboBox();
		cmbPac.setBounds(146, 78, 176, 20);
		contentPane.add(cmbPac);
		llenarPacCombo (cmbPac);



		cmbMed = new JComboBox();
		cmbMed.setBounds(146, 131, 176, 20);
		contentPane.add(cmbMed);
		llenarMedCombo(cmbMed);

		lblPaciente = new JLabel("Paciente :");
		lblPaciente.setBounds(28, 81, 77, 14);
		contentPane.add(lblPaciente);

		lblMedicoAt = new JLabel("Medico Atiende :");
		lblMedicoAt.setBounds(28, 134, 106, 14);
		contentPane.add(lblMedicoAt);
		this.setVisible(true);
	}





	private void llenarMedCombo(JComboBox cmbMed) {
		listmedicos= new ArrayList<String>();
		listmedicos.add("Escoja medico");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(consultaM);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listmedicos.add(VariablesGlobales.conexionBaseMySql.getString(rs, "idEmpleado")+" - "
					+VariablesGlobales.conexionBaseMySql.getString(rs, "nombre1Per")+" - "+ VariablesGlobales.conexionBaseMySql.getString(rs, "apellido1Per")+" - "
					+ VariablesGlobales.conexionBaseMySql.getString(rs, "cedulaPer"));
		}

		for(String item: listmedicos) {

			cmbMed.addItem(item);
		}
	}




	private void llenarPacCombo(JComboBox cmbPac) {
		listpacientes= new ArrayList<String>();
		listpacientes.add("Escoja paciente");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(consultaP);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listpacientes.add(VariablesGlobales.conexionBaseMySql.getString(rs, "idPaciente")+" - "+VariablesGlobales.conexionBaseMySql.getString(rs, "nombre1Per")+" - "+ VariablesGlobales.conexionBaseMySql.getString(rs, "apellido1Per")+" - "
					+ VariablesGlobales.conexionBaseMySql.getString(rs, "cedulaPer"));
		}

		for(String item: listpacientes) {

			cmbPac.addItem(item);
		}
	}




	/*public static void main(String[] args) {
		RegistrarSigVitales application = new RegistrarSigVitales();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}*/





	@Override
	public void actionPerformed(ActionEvent e) {
		String cad;
		String a= e.getActionCommand();
		if(a.equals("salir")) {
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e1) {
			
			}

		}
		if(a.equals("cancelar")) {
			cmbPac.setSelectedIndex(0);
			cmbMed.setSelectedIndex(0);
			txtSigVit.setText("");

		}
		if(a.equals("guardar")) {
			if(txtSigVit.getText().length()==0) {
				JOptionPane.showConfirmDialog(null, "Debe ingresar los signos vitales del paciente","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(cmbMed.getSelectedIndex()<=0) {
				JOptionPane.showConfirmDialog(null, "Debe seleccionar un medico","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(cmbPac.getSelectedIndex()<=0) {
				JOptionPane.showConfirmDialog(null, "Debe seleccionar un paciente","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			objAtencion = new Atencion();
			StringTokenizer separador = new StringTokenizer(cmbPac.getSelectedItem().toString()," ");
			StringTokenizer separador1 = new StringTokenizer(cmbMed.getSelectedItem().toString()," ");
			objAtencion.setIdPaciente(Integer.parseInt(separador.nextToken()));
			try {
				objAtencion.setFechaAte(formatoFecha.parse(txtFecha.getText()));
			} catch (ParseException e1) {
			}
			objAtencion.setIdEmpleado(Integer.parseInt(separador1.nextToken()));
			objAtencion.setSignosVitalesAte(txtSigVit.getText().trim());
			lstAtencion.add(objAtencion);

			cad =" INSERT INTO atencion (signosVitalesAte, fechaAte,idPaciente,idEmpleado, diagnosticoDescAte, prescripcionAte)  VALUES ('"+objAtencion.getSignosVitalesAte()+"', '"+formatoFecha.format(objAtencion.getFechaAte())+"',"+objAtencion.getIdPaciente()+","+objAtencion.getIdEmpleado()+",'"+""+"','"+""+"')";
			System.out.println(cad);
			VariablesGlobales.conexionBaseMySql.insertar(cad);
		}
	}
}

