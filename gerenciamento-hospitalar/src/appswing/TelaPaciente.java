package appswing;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;
import regras_negocio.Fachada;

public class TelaPaciente {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel rodapeLabel;
	private JLabel enderecoLabel;
	private JLabel pesquisaLabel;
	private JLabel nomeLabel;
	private JLabel telefoneLabel;
	private JLabel cpfLabel;
	private JLabel dataLabel;
	private JLabel convenioLabel;
	private JTextField pesquisaText;
	private JTextField enderecoText;
	private JTextField nomeText;
	private JTextField dataText;
	private JTextField convenioText;
	private JTextField cpfText;
	private JTextField telefoneText;
	private JButton listarButton;
	private JButton registrarButton;
	private JButton deletarButton;
	private JButton alterarButton;
	
	public TelaPaciente() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Medicos");
		frame.setBounds(100, 100, 729, 405);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		rodapeLabel = new JLabel("");
		rodapeLabel.setForeground(Color.BLUE);
		rodapeLabel.setBounds(21, 350, 688, 14);
		frame.getContentPane().add(rodapeLabel);

		pesquisaLabel = new JLabel("Digite parte do CPF: ");
		pesquisaLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pesquisaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pesquisaLabel.setBounds(21, 14, 128, 14);
		frame.getContentPane().add(pesquisaLabel);

		pesquisaText = new JTextField();
		pesquisaText.setFont(new Font("Dialog", Font.PLAIN, 12));
		pesquisaText.setColumns(10);
		pesquisaText.setBackground(Color.WHITE);
		pesquisaText.setBounds(140, 11, 139, 20);
		frame.getContentPane().add(pesquisaText);
		
		nomeLabel = new JLabel("Nome: ");
		nomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nomeLabel.setBounds(21, 269, 71, 14);
		frame.getContentPane().add(nomeLabel);

		nomeText = new JTextField();
		nomeText.setFont(new Font("Dialog", Font.PLAIN, 12));
		nomeText.setColumns(10);
		              //inicio,    ,tamanho, largura
		nomeText.setBounds(65, 264, 120, 20);
		frame.getContentPane().add(nomeText);
		
		telefoneLabel = new JLabel("Telefone: ");
		telefoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		telefoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telefoneLabel.setBounds(200, 269, 71, 14);
		frame.getContentPane().add(telefoneLabel);

		telefoneText = new JTextField();
		telefoneText.setFont(new Font("Dialog", Font.PLAIN, 12));
		telefoneText.setColumns(10);
		              //inicio,    ,tamanho, largura
		telefoneText.setBounds(260, 264, 120, 20);
		frame.getContentPane().add(telefoneText);
		
		registrarButton = new JButton("Registrar");
		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nomeText.getText().isEmpty() || 
							enderecoText.getText().isEmpty() ||
							cpfText.getText().isEmpty() ||
							telefoneText.getText().isEmpty() ||
							dataText.getText().isEmpty() ||
							convenioText.getText().isEmpty()) 
					
					{
						rodapeLabel.setText("campo vazio");
						return;
					}

					String nome = nomeText.getText();
					String endereco = enderecoText.getText();
					String data = dataText.getText();
					String CPF = cpfText.getText();
					String telefone = telefoneText.getText();
					String convenio = convenioText.getText(); 
					
					try {
						Fachada.consultarPaciente(CPF);
						throw new Exception ("medico ja esta registrado");
					} catch (Exception ex) {
						Paciente m = Fachada.cadastrarPaciente(nome, endereco, CPF, telefone, data, convenio);
						
						rodapeLabel.setText("Medico Registrado: " + m.getCpf());
						listagem();
					}
				}
				catch(Exception ex) {
					rodapeLabel.setText(ex.getMessage());
				}
			}
		});
		registrarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registrarButton.setBounds(535, 273, 86, 23);
		frame.getContentPane().add(registrarButton);

		listarButton = new JButton("Listar");
		listarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		listarButton.setBounds(306, 9, 89, 23);
		frame.getContentPane().add(listarButton);
		
		enderecoLabel = new JLabel("Endereço: ");
		enderecoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		enderecoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		enderecoLabel.setBounds(21, 293, 95, 14);
		frame.getContentPane().add(enderecoLabel);

		enderecoText = new JTextField();
		enderecoText.setFont(new Font("Dialog", Font.PLAIN, 12));
		enderecoText.setColumns(10);
		enderecoText.setBounds(82, 290, 120, 20);
		frame.getContentPane().add(enderecoText);
		
		cpfLabel = new JLabel("CPF: ");
		cpfLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cpfLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cpfLabel.setBounds(252, 295, 119, 14);
		frame.getContentPane().add(cpfLabel);

		cpfText = new JTextField();
		cpfText.setFont(new Font("Dialog", Font.PLAIN, 12));
		cpfText.setColumns(10);
		cpfText.setBounds(285, 290, 110, 20);
		frame.getContentPane().add(cpfText);

		dataLabel = new JLabel("Data: ");
		dataLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dataLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataLabel.setBounds(21, 315, 500, 14);
		frame.getContentPane().add(dataLabel);

		dataText = new JTextField();
		dataText.setFont(new Font("Dialog", Font.PLAIN, 12));
		dataText.setColumns(10);
		dataText.setBounds(80, 315, 110, 20);
		frame.getContentPane().add(dataText);
	
		convenioLabel = new JLabel("Convenio Médico: ");
		convenioLabel.setHorizontalAlignment(SwingConstants.LEFT);
		convenioLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		convenioLabel.setBounds(200, 315, 119, 14);
		frame.getContentPane().add(convenioLabel);

		convenioText = new JTextField();
		convenioText.setFont(new Font("Dialog", Font.PLAIN, 12));
		convenioText.setColumns(10);
		convenioText.setBounds(310, 315, 110, 20);
		frame.getContentPane().add(convenioText);
		
		deletarButton = new JButton("Deletar");
		deletarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 2);

						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma o deletar paciente "+cpf, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.excluirPaciente(cpf);
							rodapeLabel.setText("Paciente foi deletado "+ cpf);
							listagem();
						}
						else
							rodapeLabel.setText("Paciente não foi deletado " +cpf );
					}
					else
						rodapeLabel.setText("selecione uma linha");
				}
				catch(Exception ex) {
					rodapeLabel.setText(ex.getMessage());
				}
			}
		});
		deletarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deletarButton.setBounds(400, 213, 110, 23);
		frame.getContentPane().add(deletarButton);
		
		alterarButton = new JButton("Alterar");
		alterarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 2);
						String novoConvenio  = JOptionPane.showInputDialog(frame,"Digite o novo convenio:");
						String novoNome = JOptionPane.showInputDialog(frame,"Digite o novo nome:");
						String novoEndereco = JOptionPane.showInputDialog(frame,"Digite o novo endereco:");
						String novoTelefone = JOptionPane.showInputDialog(frame,"Digite o novo telefone:");
						String telefoneRemover = JOptionPane.showInputDialog(frame,"Digite o telefone a remover:");
						
						if (!novoNome.equals("")) {
							Fachada.alterarNome(cpf, novoNome);
						}
						if (!novoEndereco.equals("")) {
							Fachada.alterarEndereco(cpf, novoEndereco);
						}
						if (!novoTelefone.equals("")) {
							Fachada.adicionarTelefone(cpf, novoTelefone);
						}
						if (!telefoneRemover.equals("")) {
							Fachada.removerTelefone(cpf, telefoneRemover);
						}
						if (!novoConvenio.equals("")) {
							Fachada.alterarConvenio(cpf, novoConvenio);
						}

						rodapeLabel.setText("Paciente atualizado : "+cpf);
						listagem();
					}
					else
						rodapeLabel.setText("selecione uma linha");
				}
				catch(Exception erro) {
					rodapeLabel.setText(erro.getMessage());
				}
			}
		});
		alterarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		alterarButton.setBounds(520, 213, 110, 23);
		frame.getContentPane().add(alterarButton);
		
		
	}

	public void listagem() {
		try{
			List<Paciente> lista = Fachada.listarPacientes();
	
			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("nome");
			model.addColumn("endereço");
			model.addColumn("cpf");
			model.addColumn("telefone");
			model.addColumn("data de nascimento");
			model.addColumn("convenio medico");

			//linhas
			String cpfPesquisar = this.pesquisaText.getText();
			if (cpfPesquisar == null) {
				for(Paciente p: lista) {
					model.addRow(new Object[]{p.getNome(), p.getEndereco(), p.getCpf(), p.getTelefones(), p.getDataDeNascimento(),p.getConvenio()});
				}
			}
			else {
				for(Paciente p : lista) {
					if (p.getCpf().contains(cpfPesquisar)) {
						model.addRow(new Object[]{p.getNome(), p.getEndereco(), p.getCpf(), p.getTelefones(), p.getDataDeNascimento(),p.getConvenio()});
					}
				}
			}
				
			table.setModel(model);
			rodapeLabel.setText("resultados: "+lista.size()+ " Pacientes  - selecione uma linha");
		}
		catch(Exception erro){
			rodapeLabel.setText(erro.getMessage());
		}
		}
}
	
