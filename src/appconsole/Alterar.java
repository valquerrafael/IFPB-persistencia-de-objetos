package appconsole;

import modelo.Atendimento;
import modelo.Medico;
import regras_negocio.Fachada;

public class Alterar {
	public Alterar() {
		try {
			Fachada.inicializar();

			String nomeMedico = "Rodrigo";
			Medico medicoAnterior = (Medico) Fachada.consultarPessoasPorNome(nomeMedico).get(0);
			Atendimento atendimento = Fachada.consultarAtendimentosPorMedico(medicoAnterior.getCrm()).get(0);
			Medico medicoNovo = Fachada.cadastrarMedico("Marcos", "Rua Adalberto Moura, 234", "112.865.958-96","(83)99989-7500", "142536", "Clinico Geral");
			
			Fachada.alterarMedicoAtendimento(atendimento.getProtocolo(), medicoNovo);

			Fachada.finalizar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Alterar();
	}
}

