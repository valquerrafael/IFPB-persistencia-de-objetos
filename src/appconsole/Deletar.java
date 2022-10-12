package appconsole;

import modelo.Atendimento;
import modelo.Paciente;
import regras_negocio.Fachada;



public class Deletar {
	public Deletar() {
		try {
			Fachada.inicializar();

			String nomePaciente = "Deolana";
			Paciente paciente = (Paciente) Fachada.consultarPessoasPorNome(nomePaciente).get(0);
			Atendimento atendimento = Fachada.consultarAtendimentosPorPaciente(paciente.getCpf()).get(0);

			Fachada.excluirAtendimento(atendimento.getProtocolo());

			Fachada.finalizar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Deletar();
	}
}