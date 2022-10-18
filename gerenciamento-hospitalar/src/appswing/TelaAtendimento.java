package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;
import regras_negocio.Fachada;

public class TelaAtendimento {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel rodapeLabel;
	private JLabel dataLabel;
	private JLabel pesquisaLabel;
	private JLabel protocoloLabel;
	private JLabel formatoDataLabel;
	private JLabel crmLabel;
	private JLabel cpfLabel;
	private JTextField pesquisaText;
	private JTextField protocoloText;
	private JTextField crmText;
	private JTextField cpfText;
	private JFormattedTextField dataText;
	private JButton listarButton;
	private JButton registrarButton;
	private JButton deletarButton;
	private JButton alterarButton;
	
	public TelaAtendimento() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Atendimentos");
		frame.setBounds(100, 100, 729, 385);
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
		rodapeLabel.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(rodapeLabel);

		pesquisaLabel = new JLabel("Digite o protocolo: ");
		pesquisaLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pesquisaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pesquisaLabel.setBounds(21, 14, 128, 14);
		frame.getContentPane().add(pesquisaLabel);

		pesquisaText = new JTextField();
		pesquisaText.setFont(new Font("Dialog", Font.PLAIN, 12));
		pesquisaText.setColumns(10);
		pesquisaText.setBackground(Color.WHITE);
		pesquisaText.setBounds(128, 11, 139, 20);
		frame.getContentPane().add(pesquisaText);
		
		protocoloLabel = new JLabel("Protocolo: ");
		protocoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		protocoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		protocoloLabel.setBounds(21, 269, 71, 14);
		frame.getContentPane().add(protocoloLabel);

		protocoloText = new JTextField();
		protocoloText.setFont(new Font("Dialog", Font.PLAIN, 12));
		protocoloText.setColumns(10);
		protocoloText.setBounds(82, 264, 42, 20);
		frame.getContentPane().add(protocoloText);
		
		registrarButton = new JButton("Registrar");
		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dataText.getText().isEmpty() || 
							protocoloText.getText().isEmpty() ||
							crmText.getText().isEmpty() ||
							cpfText.getText().isEmpty()) 
					
					{
						rodapeLabel.setText("campo vazio");
						return;
					}

					String protocolo = protocoloText.getText();
					String data = dataText.getText();
					String CRM = crmText.getText();
					Medico m = Fachada.consultarMedico(CRM);
					String CPF = cpfText.getText();
					Paciente p = Fachada.consultarPaciente(CPF); 
					
					if (m == null || p == null) {
						throw new Exception ("medico ou paciente nao estão registrado");
					}	
					Atendimento a = Fachada.cadastrarAtendimento(protocolo, data, p, m);
					rodapeLabel.setText("Atendimento Registrado: " + a.getId());
					listagem();
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
		
		dataLabel = new JLabel("Data: ");
		dataLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dataLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		dataLabel.setBounds(21, 232, 71, 14);
		frame.getContentPane().add(dataLabel);
		
		
		try {
			dataText = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} 
		catch (ParseException e1) {}
		dataText.setBounds(67, 232, 80, 20);
		frame.getContentPane().add(dataText);

		formatoDataLabel = new JLabel("(dd/mm/aaaa)");
		formatoDataLabel.setBounds(158, 232, 88, 14);
		frame.getContentPane().add(formatoDataLabel);

		crmLabel = new JLabel("Médico - CRM: ");
		crmLabel.setHorizontalAlignment(SwingConstants.LEFT);
		crmLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		crmLabel.setBounds(21, 293, 95, 14);
		frame.getContentPane().add(crmLabel);

		crmText = new JTextField();
		crmText.setFont(new Font("Dialog", Font.PLAIN, 12));
		crmText.setColumns(10);
		crmText.setBounds(110, 290, 96, 20);
		frame.getContentPane().add(crmText);
				
		cpfLabel = new JLabel("Paciente - CPF: ");
		cpfLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cpfLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cpfLabel.setBounds(252, 295, 119, 14);
		frame.getContentPane().add(cpfLabel);

		cpfText = new JTextField();
		cpfText.setFont(new Font("Dialog", Font.PLAIN, 12));
		cpfText.setColumns(10);
		cpfText.setBounds(342, 290, 110, 20);
		frame.getContentPane().add(cpfText);
		
		deletarButton = new JButton("Deletar");
		deletarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String protocolo = (String) table.getValueAt( table.getSelectedRow(), 1);

						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma cancelamento do atendimento "+protocolo, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.excluirAtendimento(protocolo);
							rodapeLabel.setText("Atendimento foi cancelado "+ protocolo);
							listagem();
						}
						else
							rodapeLabel.setText("Atendimento não foi cancelado " +protocolo );
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
						String protocolo = (String) table.getValueAt( table.getSelectedRow(), 1);
						String novaData = JOptionPane.showInputDialog(frame,"Digite a nova data (dd/mm/aaaa)");
						String novoMedicoCRM = JOptionPane.showInputDialog(frame,"Digite o CRM do novo Medico");
						String novoDiagnostico = JOptionPane.showInputDialog(frame,"Digite o novo diagnostico");
						String novoReceituario = JOptionPane.showInputDialog(frame,"Digite o novo receituario");
						String novoProcedimento = JOptionPane.showInputDialog(frame,"Digite o novo procedimento");
						String procedimentoRemover = JOptionPane.showInputDialog(frame,"Digite o procedimento a remover");
						
						if (!novaData.equals("")) {
							Fachada.alterarDataAtendimento(protocolo, novaData);
						}
						if (!novoMedicoCRM.equals("")) {
							Medico m = Fachada.consultarMedico(novoMedicoCRM);
							Fachada.alterarMedicoAtendimento(protocolo, m);
						}
						if (!novoDiagnostico.equals("")) {
							Fachada.alterarDiagnostico(protocolo, novoDiagnostico);
						}
						if (!novoReceituario.equals("")) {
							Fachada.alterarReceituario(protocolo, novoReceituario);
						}
						if (!novoProcedimento.equals("")) {
							Fachada.adicionarProcedimento(protocolo, novoProcedimento);
						}
						if (!procedimentoRemover.equals("")) {
							Fachada.removerProcedimento(protocolo, procedimentoRemover);
						}
						
						


						rodapeLabel.setText("Atendimento atualizado : "+protocolo);
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
			List<Atendimento> lista = Fachada.listarAtendimentos();
	
			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("id");
			model.addColumn("protocolo");
			model.addColumn("data");
			model.addColumn("nome paciente");
			model.addColumn("nome médico");
			model.addColumn("diagnostico");
			model.addColumn("receituario");
			model.addColumn("procedimentos");

			//linhas
			String protocoloPesquisa = this.pesquisaText.getText();
			if (protocoloPesquisa == null) {
				for(Atendimento a : lista) {
					model.addRow(new Object[]{a.getId()+"", a.getProtocolo(), a.getData(), a.getPaciente().getNome(), a.getMedico().getNome(), a.getDiagnostico(), a.getReceituario(), a.getProcedimentos()});
				}
			}
			else {
				for(Atendimento a : lista) {
					if (a.getProtocolo().contains(protocoloPesquisa)) {
						model.addRow(new Object[]{a.getId()+"", a.getProtocolo(), a.getData(), a.getPaciente().getNome(), a.getMedico().getNome(), a.getDiagnostico(), a.getReceituario(), a.getProcedimentos()});
					}
				}
			}
				
			table.setModel(model);
			rodapeLabel.setText("resultados: "+lista.size()+ " atendimentos  - selecione uma linha");
		}
		catch(Exception erro){
			rodapeLabel.setText(erro.getMessage());
		}
		}
	
}

