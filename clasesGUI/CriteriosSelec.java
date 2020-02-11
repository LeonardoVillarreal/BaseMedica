package clasesGui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import servicios.LanzadorReportes;

import clasesBean.EmpleadoBean;
import clasesBean.PacienteBean;
import clasesBean.PersonaBean;
import servicios.ServiciosGenerales;
import servicios.VariablesGlobales;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class CriteriosSelec extends JInternalFrame implements ActionListener {
	private JPanel contentPane;
	JComboBox cmbEsp, cmbMed,cmbReporte;
	ArrayList<String> listespecialidad, listmedico, listreportes, listmedicos;
	String  especialidad = "SELECT descripcionEsp FROM especialidad ";
	String medico = "SELECT p.nombre1Per, p.apellido1Per    FROM persona p, empleado e WHERE p.idPersona=e.idPersona";
	JRadioButton rdbtnM,rdbtnF, rdbtnTodos;
	private JLabel lblEspecialidad,lblMedico, lblReporte, lblSexo;
	public static ButtonGroup grdSexo;
	JButton btnSalir, btnAceptar;
	String arrReportes[]= {"Escoja reporte","Numero de atenciones","Grafico de número de atenciones según sexo","Lista de pacientes atendidos","Tabla de atenciones segun especialidad por sexo"};
	//private String baseAtenciones = "SELECT count(a.idAtencion) as Atenciones FROM atencion a, paciente pac, persona p, empleado e, especialidad esp WHERE 1=1";
	private String subtitulo = "", cadena ="SELECT count(a.idAtencion) as Atenciones FROM atencion a";


	/**
	 * Create the frame.
	 */
	public CriteriosSelec() {
		super("Criterios de seleccion");
		setBounds(100, 100, 482, 358);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(33, 32, 79, 14);
		contentPane.add(lblEspecialidad);

		lblMedico = new JLabel("Medico:");
		lblMedico.setBounds(33, 92, 46, 14);
		contentPane.add(lblMedico);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(33, 151, 46, 14);
		contentPane.add(lblSexo);

		lblReporte = new JLabel("Reporte:");
		lblReporte.setBounds(33, 200, 79, 14);
		contentPane.add(lblReporte);

		rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(94, 147, 109, 23);
		contentPane.add(rdbtnM);

		rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(215, 147, 46, 23);
		contentPane.add(rdbtnF);

		cmbEsp = new JComboBox();
		cmbEsp.setBounds(122, 29, 165, 20);
		llenarEspCombo(cmbEsp);

		cmbEsp.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cmbEsp.getSelectedIndex()>0) {
					llenarMedcombo(consultaId(cmbEsp), cmbMed);
				}else {
					cmbMed.removeAllItems();
				}
			}


		});
		contentPane.add(cmbEsp);

		cmbMed = new JComboBox();
		cmbMed.setBounds(122, 89, 165, 20);

		contentPane.add(cmbMed);

		cmbReporte = new JComboBox(arrReportes);
		cmbReporte.setMaximumRowCount(4);
		cmbReporte.setBounds(122, 197, 320, 20);
		cmbReporte.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(cmbReporte.getSelectedIndex()>0) {
					cmbEsp.setEnabled(true);
					cmbMed.setEnabled(true);
					rdbtnF.setEnabled(true);
					rdbtnM.setEnabled(true);
					rdbtnTodos.setEnabled(true);
				}else {
					cmbEsp.setEnabled(false);
					cmbMed.setEnabled(false);
					rdbtnF.setEnabled(false);
					rdbtnM.setEnabled(false);
					rdbtnTodos.setEnabled(false);
				}
			}
		});
		contentPane.add(cmbReporte);

		btnAceptar = new JButton("Lanzar reporte");	
		btnAceptar.setBounds(82, 270, 121, 23);
		contentPane.add(btnAceptar);
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.addActionListener(this);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(262, 270, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(this);

		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setBounds(307, 147, 109, 23);
		contentPane.add(rdbtnTodos);

		grdSexo = new ButtonGroup();
		grdSexo.add(rdbtnF);
		grdSexo.add(rdbtnM);
		grdSexo.add(rdbtnTodos);

		cmbEsp.setEnabled(false);
		cmbMed.setEnabled(false);
		rdbtnF.setEnabled(false);
		rdbtnM.setEnabled(false);
		rdbtnTodos.setEnabled(false);



		this.setVisible(true);
	}

	private void llenarEspCombo(JComboBox cmbEsp) {
		listespecialidad= new ArrayList<String>();
		listespecialidad.add("Escoja especialidad");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(especialidad);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listespecialidad.add(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionEsp"));
		}

		for(String item: listespecialidad) {
			cmbEsp.addItem(item);
		}
	}


	private void llenarMedcombo(Integer idpadre, JComboBox cmbMed) {
		cmbMed.removeAllItems();
		listmedico= new ArrayList<String>();
		listmedico.add("Escoja medico");
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SElECT p.nombre1Per, p.apellido1Per    FROM persona p, empleado e WHERE p.idPersona = e.idPersona and e.idEspecialidad ="+ idpadre+"" );
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			listmedico.add(VariablesGlobales.conexionBaseMySql.getString(rs, "nombre1Per")+" "+ VariablesGlobales.conexionBaseMySql.getString(rs, "apellido1Per"));
		}

		for(String item: listmedico) {
			cmbMed.addItem(item);
		}

	}
	public Integer consultaId(JComboBox combo) {

		int id =0;
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta("SELECT idEspecialidad FROM especialidad WHERE  descripcionEsp='"+combo.getSelectedItem().toString()+"'");
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			id = Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idEspecialidad"));
		}
		return id;

	}
	public void actionPerformed(ActionEvent e) {

		String a = e.getActionCommand();
		String nombreReporte ="";
		String filtro ="";
		String subtitulo ="";

		if(a.equals("salir")) {

			try {
				this.setClosed(true);
			} catch (PropertyVetoException e1) {

			}
		}

		if(a.equals("aceptar")) {
            //FILTROS SIRVEN PARA TODOS LOS REPORTES
			//Filtro sexo
			if(rdbtnM.isSelected()) {
				filtro+= " and pac.sexoPac='M'";
				subtitulo+="Sexo: Masculino";
			}
			else if(rdbtnF.isSelected()) {
				filtro+= " and pac.sexoPac='F'";
				subtitulo+="Sexo: Femenino";
			}
            //Filtro especialidad
			if(cmbEsp.getSelectedIndex()>0) {
				filtro+= " and esp.descripcionEsp ='"+cmbEsp.getSelectedItem().toString()+"'";
				subtitulo+="Especialidad : "+cmbEsp.getSelectedItem().toString() ;
			}
            //filtro medico
			if(cmbMed.getSelectedIndex()>0) {
				StringTokenizer token = new StringTokenizer(cmbMed.getSelectedItem().toString(), " ");
				String nombre = token.nextToken(), apellido = token.nextToken();
				filtro+= " and per.nombre1Per ='"+nombre+"' and per.apellido1Per ='"+apellido+"'";
				subtitulo+="Medico : "+cmbMed.getSelectedItem().toString() ;
			}

		//SELECT Si el reporte es numero de atenciones
			if(cmbReporte.getSelectedIndex()==1) {
				nombreReporte= "reportes/NumAtenciones.jasper";

				cadena ="SELECT count(a.idAtencion) as Atenciones"
						+ " FROM atencion a, empleado e,  especialidad esp, paciente pac, persona per"
						+ " WHERE a.idPaciente = pac.idPaciente "
						+ " and a.idEmpleado = e.idEmpleado"
						+ " and e.idEspecialidad = esp.idEspecialidad"
						+ " and e.idPersona = per.idPersona"
						+ filtro;
			}


			//SELECT Si el reporte es el grafico de número de atenciones según sexo
			else if(cmbReporte.getSelectedIndex()==2) {
				nombreReporte= "reportes/graficoNumeroAtencionesSegunSexo.jasper";
				cadena = "SELECT  count(a.idAtencion) as contador, (case pac.sexoPac  when 'M' then 'Masculino' when 'F' then 'Femenino' end) as sexoPac" + 
						" FROM atencion a, paciente pac, empleado e, especialidad esp, persona p" + 
						" WHERE a.idPaciente= pac.idPaciente" + 
						" and a.idEmpleado = e.idEmpleado" + 
						" and e.idEspecialidad = esp.idEspecialidad" + 
						" and e.idPersona = p.idPersona"; 
						
				cadena+=filtro;
				cadena+=" group by pac.sexoPac";

			}
			// SELECT Si el reporte es lista de pacientes atendidos
			else if(cmbReporte.getSelectedIndex()==3) {


			}
			//SELECT Si el reporte es una tabla de numero de atenciones especialidad por sexo
			else if(cmbReporte.getSelectedIndex()==4) {


			}
			else if(cmbReporte.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un reporte");
				return;
			}
			
			
			
			System.out.println(cadena);
			HashMap parametros = new HashMap<>();
			parametros.put("seleccion", cadena);
			parametros.put("subtitulo", subtitulo);
			LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
			lanzadorReportes.cargarReporte(nombreReporte,parametros,VariablesGlobales.conexionBaseMySql.getConeccion() );
			lanzadorReportes.setSize(new Dimension(800,600));
			lanzadorReportes.show(true);
			subtitulo = "";
		}
	}
}

