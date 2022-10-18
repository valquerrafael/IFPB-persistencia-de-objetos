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

public class TelaMedico {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel rodapeLabel;
	private JLabel enderecoLabel;
	private JLabel pesquisaLabel;
	private JLabel nomeLabel;
	private JLabel telefoneLabel;
	private JLabel cpfLabel;
	private JLabel crmLabel;
	private JLabel especialidadeLabel;
	private JTextField pesquisaText;
	private JTextField enderecoText;
	private JTextField nomeText;
	private JTextField crmText;
	private JTextField especialidadeText;
	private JTextField cpfText;
	private JTextField telefoneText;
	private JButton listarButton;
	private JButton registrarButton;
	private JButton deletarButton;
	private JButton alterarButton;
	
	public TelaMedico() {
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

		pesquisaLabel = new JLabel("Digite parte do CRM: ");
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
							crmText.getText().isEmpty() ||
							especialidadeText.getText().isEmpty()) 
					
					{
						rodapeLabel.setText("campo vazio");
						return;
					}

					String nome = nomeText.getText();
					String endereco = enderecoText.getText();
					String CRM = crmText.getText();
					String CPF = cpfText.getText();
					String telefone = telefoneText.getText();
					String especialidade = especialidadeText.getText(); 
					
					try {
						Fachada.consultarMedico(CRM);
						throw new Exception ("medico ja esta registrado");
					} catch (Exception ex) {
						Medico m = Fachada.cadastrarMedico(nome, endereco, CPF, telefone, CRM, especialidade);
						
						rodapeLabel.setText("Medico Registrado: " + m.getCrm());
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

		crmLabel = new JLabel("CRM: ");
		crmLabel.setHorizontalAlignment(SwingConstants.LEFT);
		crmLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		crmLabel.setBounds(21, 315, 500, 14);
		frame.getContentPane().add(crmLabel);

		crmText = new JTextField();
		crmText.setFont(new Font("Dialog", Font.PLAIN, 12));
		crmText.setColumns(10);
		crmText.setBounds(80, 315, 110, 20);
		frame.getContentPane().add(crmText);
	
		especialidadeLabel = new JLabel("Especialidade: ");
		especialidadeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		especialidadeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		especialidadeLabel.setBounds(200, 315, 119, 14);
		frame.getContentPane().add(especialidadeLabel);

		especialidadeText = new JTextField();
		especialidadeText.setFont(new Font("Dialog", Font.PLAIN, 12));
		especialidadeText.setColumns(10);
		especialidadeText.setBounds(290, 315, 110, 20);
		frame.getContentPane().add(especialidadeText);
		
		deletarButton = new JButton("Deletar");
		deletarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String crm = (String) table.getValueAt( table.getSelectedRow(), 4);

						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma o deletar medico "+crm, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.excluirMedico(crm);
							rodapeLabel.setText("Medico foi deletado "+ crm);
							listagem();
						}
						else
							rodapeLabel.setText("Medico não foi deletado " +crm );
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
						String crm = (String) table.getValueAt( table.getSelectedRow(), 4);
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 2);
						String novoNome = JOptionPane.showInputDialog(frame,"Digite o novo nome:");
						String novoEndereco = JOptionPane.showInputDialog(frame,"Digite o novo endereco:");
						String novoTelefone = JOptionPane.showInputDialog(frame,"Digite o novo telefone:");
						String telefoneRemover = JOptionPane.showInputDialog(frame,"Digite o telefone a remover:");
						String novaEspecialidade = JOptionPane.showInputDialog(frame,"Digite a nova especialidade:");
						
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
						if (!novaEspecialidade.equals("")) {
							Fachada.alterarEspecialidade(crm, novaEspecialidade);
						}

						rodapeLabel.setText("Medico atualizado : "+crm);
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
			List<Medico> lista = Fachada.listarMedicos();
	
			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("nome");
			model.addColumn("endereço");
			model.addColumn("cpf");
			model.addColumn("telefone");
			model.addColumn("crm");
			model.addColumn("especialidade");

			//linhas
			String crmPesquisar = this.pesquisaText.getText();
			if (crmPesquisar == null) {
				for(Medico m: lista) {
					model.addRow(new Object[]{m.getNome(), m.getEndereco(), m.getCpf(), m.getTelefones(), m.getCrm(), m.getEspecialidade(), m.getAtendimentos()});
				}
			}
			else {
				for(Medico m : lista) {
					if (m.getCrm().contains(crmPesquisar)) {
						model.addRow(new Object[]{m.getNome(), m.getEndereco(), m.getCpf(), m.getTelefones(), m.getCrm(), m.getEspecialidade(), m.getAtendimentos()});
					}
				}
			}
				
			table.setModel(model);
			rodapeLabel.setText("resultados: "+lista.size()+ " Medicos  - selecione uma linha");
		}
		catch(Exception erro){
			rodapeLabel.setText(erro.getMessage());
		}
		}
	
}