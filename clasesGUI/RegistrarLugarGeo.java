package clasesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import clasesBean.LugarGeografico;
import servicios.ServiciosGenerales;
import servicios.VariablesGlobales;
import javax.swing.ScrollPaneConstants;

public class RegistrarLugarGeo extends JInternalFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdLugarGeo, txtIdLugGeoPadre, txtDescripcionLugGeo, txtCodigoLugGeo;
	private JLabel lblTabla, lblIdLugarGeo, lblCodigoLugGeo, lblDescripcionLugGeo, lblIdLugGeoPadre;
	private JButton btnSalir, btnInsertar, btnCancelar;
	private JPanel panelGui;
	private Object arrDatos[] = new Object[4];
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tabla = new JTable(modelo);
	String lugares = "SELECT * FROM lugargeo";
	private int row;
	private ArrayList<LugarGeografico> lstArrLugGeo = new ArrayList<LugarGeografico>();
	private LugarGeografico objLugGeo;




	/**
	 * Create the panel.
	 */
	public RegistrarLugarGeo() {
		super("Registrar Lugar Geografico");

		setBounds(100, 100, 485, 500);

		panelGui = new JPanel();
		panelGui.setBorder(UIManager.getBorder("Tree.editorBorder"));
		panelGui.setBackground(SystemColor.textHighlight);
		panelGui.setLayout(null);

		txtIdLugarGeo = new JTextField();
		txtIdLugarGeo.setBounds(248, 31, 159, 20);
		panelGui.add(txtIdLugarGeo);
		txtIdLugarGeo.setColumns(10);
		txtIdLugarGeo.setEditable(false);

		txtCodigoLugGeo = new JTextField();
		txtCodigoLugGeo.setBounds(248, 71, 159, 20);
		txtCodigoLugGeo.setToolTipText("Codigo de provincia/canton/parroquia");
		panelGui.add(txtCodigoLugGeo);
		txtCodigoLugGeo.setColumns(10);

		txtDescripcionLugGeo = new JTextField();
		txtDescripcionLugGeo.setBounds(248, 118, 159, 20);
		txtDescripcionLugGeo.setToolTipText("Nombre de provincia/canton/parroquia");
		panelGui.add(txtDescripcionLugGeo);
		txtDescripcionLugGeo.setColumns(10);

		txtIdLugGeoPadre = new JTextField();
		txtIdLugGeoPadre.setBounds(248, 158, 159, 20);
		txtIdLugGeoPadre.setToolTipText("Id de provincia/canton al que pertenece el canton/parroquia");
		panelGui.add(txtIdLugGeoPadre);
		txtIdLugGeoPadre.setColumns(10);

		lblIdLugarGeo = new JLabel("Id Lugar:");
		lblIdLugarGeo.setBounds(10, 34, 69, 14);
		panelGui.add(lblIdLugarGeo);

		lblCodigoLugGeo = new JLabel("Codigo:");
		lblCodigoLugGeo.setBounds(10, 74, 103, 14);
		panelGui.add(lblCodigoLugGeo);

		lblDescripcionLugGeo = new JLabel("Descripcion:");
		lblDescripcionLugGeo.setBounds(10, 121, 121, 14);
		panelGui.add(lblDescripcionLugGeo);

		lblIdLugGeoPadre = new JLabel("Id Lugar Padre:");
		lblIdLugGeoPadre.setBounds(10, 161, 103, 14);
		panelGui.add(lblIdLugGeoPadre);
		for(int i=1; i<=4;i++){
			modelo.addColumn("");
		}
		arrDatos[0]="ID LUGAR";
		arrDatos[1]="CODIGO";
		arrDatos[2]="DESCRIPCION";
		arrDatos[3]="ID LUGAR PADRE";
		modelo.addRow(arrDatos);

		lblTabla = new JLabel("Lugares registrados");
		lblTabla.setBounds(156, 193, 134, 14);
		panelGui.add(lblTabla);


		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(143, 364, 95, 23);
		btnInsertar.setToolTipText("Registrar un lugar nuevo");
		panelGui.add(btnInsertar);
		btnInsertar.setActionCommand("insertar");
		btnInsertar.addActionListener(this);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(242, 364, 95, 23);
		btnCancelar.setToolTipText("Borra los datos en los campos");
		panelGui.add(btnCancelar);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(195, 398, 95, 23);
		panelGui.add(btnSalir);
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(this);

		Container contenedor = getContentPane();
		contenedor.add(panelGui);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 218, 450, 135);
		panelGui.add(scrollPane);
		scrollPane.setViewportView(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(450, 135));
		tabla.setFillsViewportHeight(true);
		tabla.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				row = tabla.getSelectedRow();
				txtIdLugarGeo.setText(tabla.getValueAt(row, 0).toString());
				txtCodigoLugGeo.setText(tabla.getValueAt(row, 1).toString());
				txtDescripcionLugGeo.setText(tabla.getValueAt(row, 2).toString());
				if(tabla.getValueAt(row, 3)!=null) {
					txtIdLugGeoPadre.setText(tabla.getValueAt(row, 3).toString());
				}else
					txtIdLugGeoPadre.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


		this.setVisible(true);

		llenarTabla(modelo);


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		boolean indBien = false;
		String a = arg0.getActionCommand();
		String cad, strError;
		// TODO Auto-generated method stub
		if(a.equals("salir")) {
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e) {
			
			}
		}

		if(a.equals("cancelar")) {
			txtIdLugarGeo.setText("");
			txtIdLugGeoPadre.setText("");
			txtCodigoLugGeo.setText("");
			txtDescripcionLugGeo.setText("");

		}
		if(a.equals("insertar")) {
			while(indBien==false) {
				indBien=true;
				strError="";
				if(!ServiciosGenerales.validarCodigoLugGeo(txtCodigoLugGeo.getText().trim())) {
					strError+=ServiciosGenerales.errorCodigoLugGeo;
					indBien = false;
				}
				if(txtCodigoLugGeo.getText().length()==0 || txtDescripcionLugGeo.getText().length()==0) {
					strError+="Los campos descripcion y/o codigo deben ser llenados\n";
					indBien=false;
				}
				if(indBien==false) {
					JOptionPane.showMessageDialog(null,strError , "Mensaje de error al insertar", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if(txtIdLugarGeo.getText().trim().length()==0) {
				if(txtIdLugGeoPadre.getText().trim().length()==0) {
					cad ="INSERT INTO lugargeo (descripcionLugGeo, codigoLugGeo) VALUES ('"+txtDescripcionLugGeo.getText().trim().toUpperCase()+"','"+txtCodigoLugGeo.getText().trim()+"')";
				}else {
					int id= Integer.parseInt(txtIdLugGeoPadre.getText().trim());
					cad = "INSERT INTO lugargeo (descripcionLugGeo, codigoLugGeo, idLugGeoPadre) VALUES ('"+txtDescripcionLugGeo.getText().trim().toUpperCase()+"','"+txtCodigoLugGeo.getText().trim()+"',"+id+")";
				}
				VariablesGlobales.conexionBaseMySql.insertar(cad);
				actualizarLista();
				insertarTabla(modelo, lstArrLugGeo);
			}else {

				if(txtIdLugGeoPadre.getText().trim().length()==0) {
					cad = "UPDATE lugargeo SET codigoLugGeo ='"+txtCodigoLugGeo.getText().trim()+"', descripcionLugGeo ='"+txtDescripcionLugGeo.getText().trim().toUpperCase()+"', idLugGeoPadre="+null+" WHERE idLugarGeo="+Integer.parseInt(txtIdLugarGeo.getText())+"";
				}else
					cad = "UPDATE lugargeo SET codigoLugGeo ='"+txtCodigoLugGeo.getText().trim()+"', descripcionLugGeo ='"+txtDescripcionLugGeo.getText().trim().toUpperCase()+"', idLugGeoPadre="+Integer.parseInt(txtIdLugGeoPadre.getText().trim())+" WHERE idLugarGeo="+Integer.parseInt(txtIdLugarGeo.getText())+"";
				System.out.println(cad);
				VariablesGlobales.conexionBaseMySql.insertar(cad);
				actualizarLista();
				actualizarTabla(modelo, lstArrLugGeo, tabla);
			}
		}


		/*for(LugarGeografico lug:lstArrLugGeo) {
				System.out.println(lug.getDescripcionLugGeo());
			}*/

	}



	/*public static void main(String[] args) {
		RegistrarLugarGeo application = new RegistrarLugarGeo();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
*/
	public void llenarTabla(DefaultTableModel modelo) {
		Object arrDatos[] = new Object[4];
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(lugares);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			arrDatos[0]=VariablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo");
			arrDatos[1]=VariablesGlobales.conexionBaseMySql.getString(rs, "codigoLugGeo");
			arrDatos[2]=VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo");
			arrDatos[3]=VariablesGlobales.conexionBaseMySql.getString(rs, "idLugGeoPadre");
			modelo.addRow(arrDatos);
		}

		ResultSet res = VariablesGlobales.conexionBaseMySql.consulta(lugares);
		while(VariablesGlobales.conexionBaseMySql.siguiente(res)) {
			objLugGeo = new LugarGeografico();
			objLugGeo.setIdLugarGeografico(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(res, "idLugarGeo").toString()));
			objLugGeo.setCodigoLugGeo(VariablesGlobales.conexionBaseMySql.getString(res, "codigoLugGeo").toString());
			objLugGeo.setDescripcionLugGeo(VariablesGlobales.conexionBaseMySql.getString(res, "descripcionLugGeo").toString());
			try {
				objLugGeo.setIdLugarGeograficoPadre(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(res, "idLugGeoPadre").toString()));

			}catch(Exception e) {
				objLugGeo.setIdLugarGeograficoPadre(0);
			}

			lstArrLugGeo.add(objLugGeo);	
		}
	}
	public void actualizarTabla(DefaultTableModel modelo, ArrayList<LugarGeografico> lstArrLugGeo, JTable tabla) {

		for(int i = 1; i<tabla.getRowCount();i++) {
			modelo.setValueAt(lstArrLugGeo.get(i-1).getIdLugarGeografico(), i, 0);
			modelo.setValueAt(lstArrLugGeo.get(i-1).getCodigoLugGeo(), i, 1);
			modelo.setValueAt(lstArrLugGeo.get(i-1).getDescripcionLugGeo(), i, 2);
			if(lstArrLugGeo.get(i-1).getIdLugarGeograficoPadre()!=0) {
				modelo.setValueAt(lstArrLugGeo.get(i-1).getIdLugarGeograficoPadre(), i, 3);
			}else
				modelo.setValueAt(null, i, 3);	
		}
	}

	public void actualizarLista() {

		lstArrLugGeo.clear();
		ResultSet rs = VariablesGlobales.conexionBaseMySql.consulta(lugares);
		while(VariablesGlobales.conexionBaseMySql.siguiente(rs)) {
			objLugGeo = new LugarGeografico();
			objLugGeo.setIdLugarGeografico(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo").toString()));
			objLugGeo.setCodigoLugGeo(VariablesGlobales.conexionBaseMySql.getString(rs, "codigoLugGeo").toString());
			objLugGeo.setDescripcionLugGeo(VariablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo").toString());
			try {
				objLugGeo.setIdLugarGeograficoPadre(Integer.parseInt(VariablesGlobales.conexionBaseMySql.getString(rs, "idLugGeoPadre").toString()));

			}catch(Exception e) {
				objLugGeo.setIdLugarGeograficoPadre(0);
			}						
			lstArrLugGeo.add(objLugGeo);	
		}
	}

	public void insertarTabla(DefaultTableModel modelo, ArrayList<LugarGeografico> lstArrLugGeo) {
		Object arr[]= new Object[4];
		arr[0]=lstArrLugGeo.get(lstArrLugGeo.size()-1).getIdLugarGeografico();
		arr[1]=lstArrLugGeo.get(lstArrLugGeo.size()-1).getCodigoLugGeo();
		arr[2]=lstArrLugGeo.get(lstArrLugGeo.size()-1).getDescripcionLugGeo();
		if(lstArrLugGeo.get(lstArrLugGeo.size()-1).getIdLugarGeograficoPadre()!=0) {
			arr[3]=lstArrLugGeo.get(lstArrLugGeo.size()-1).getIdLugarGeograficoPadre();
		}else
			arr[3]=null;

		modelo.addRow(arr);
	}
}
