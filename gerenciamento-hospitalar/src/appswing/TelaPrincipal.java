package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

import regras_negocio.Fachada;

public class TelaPrincipal {
	private JFrame frame;
	private JMenu Atendimentos;
	private JMenu Medicos;
	private JMenu Pacientes;
	private JLabel label;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		initialize();
	}

  private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gerenciamento Hospitalar");
		frame.setBounds(100, 100, 450, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("Inicializando...");
		label.setBounds(0, 0, 450, 313);
		ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/imagem1.jpg"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));//		label.setIcon(fotos);
		label.setIcon(imagem);
		frame.getContentPane().add(label);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		Atendimentos = new JMenu("Atendimentos");
		Atendimentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaAtendimento tela = new TelaAtendimento();
			}
		});
		menuBar.add(Atendimentos);

		Medicos = new JMenu("Medicos");
		Medicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaMedico tela = new TelaMedico();
			}
		});
		menuBar.add(Medicos);

    Pacientes = new JMenu("Pacientes");
		Pacientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaPaciente tela = new TelaPaciente();
			}
		});
		menuBar.add(Pacientes);
	}
}