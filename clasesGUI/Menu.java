package clasesGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnRegistrar = new JMenu("Registrar");
		mnRegistrar.setForeground(Color.DARK_GRAY);
		menuBar.add(mnRegistrar);
		
		JMenuItem mntmPaciente = new JMenuItem(" Paciente");
		mntmPaciente.setActionCommand("paciente");
		mntmPaciente.addActionListener(this);	
		mnRegistrar.add(mntmPaciente);
		
		JMenuItem mntmSigVit= new JMenuItem("Signos vitales");
		mntmSigVit.setActionCommand("signos");
		mntmSigVit.addActionListener(this);
		mnRegistrar.add(mntmSigVit);
		
		JMenuItem mntmAtencion = new JMenuItem("Atencion");
		mntmAtencion.setActionCommand("atencion");
		mntmAtencion.addActionListener(this);
		mnRegistrar.add(mntmAtencion);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmImportPac = new JMenuItem("Importar Paciente");
		mntmImportPac.setActionCommand("importarpac");
		mntmImportPac.addActionListener(this);
		mnMantenimiento.add(mntmImportPac);
		
		JMenuItem mntmLugGeo = new JMenuItem("Registrar LugarGeo");
		mntmLugGeo.setActionCommand("LugGeo");
		mntmLugGeo.addActionListener(this);
		mnMantenimiento.add(mntmLugGeo);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmCriterios = new JMenuItem("Criterios de seleccion");
		mntmCriterios.setActionCommand("reportes");
		mntmCriterios.addActionListener(this);	
		mnReportes.add(mntmCriterios);
		
		
		
		
		
		JMenu mnSalir = new JMenu("Salir");
		menuBar.add(mnSalir);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setActionCommand("salir");
		mntmSalir.addActionListener(this);	
		mnSalir.add(mntmSalir);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if(a.equals("salir")) {
			System.exit(0);
		}
		
		if(a.equals("paciente")) {
		     RegistrarPaciente paciente = new RegistrarPaciente();
		     contentPane.add(paciente);
		     paciente.show();
		}
		if(a.equals("signos")) {
		     RegistrarSigVitales signos = new RegistrarSigVitales();
		     contentPane.add(signos);
		     signos.show();
		}
		if(a.equals("atencion")) {
		     RegistratAtencion atencion = new RegistratAtencion();
		     contentPane.add(atencion);
		     atencion.show();
		}
		if(a.equals("importPac")) {
		     ImportarPacientesGui pacientes = new ImportarPacientesGui();
		     contentPane.add(pacientes);
		     pacientes.show();
		}
		if(a.equals("reportes")) {
		     CriteriosSelec reportes = new CriteriosSelec();
		     contentPane.add(reportes);
		     reportes.show();
	
		}
		if(a.equals("LugGeo")) {
		     RegistrarLugarGeo lugar = new RegistrarLugarGeo();
		     contentPane.add(lugar);
		     lugar.show();
	
		}

}
}
