package appconsole;

import java.util.List;

import modelo.Medico;
import modelo.Paciente;
import regras_negocio.Fachada;

public class Consultar {	
	public Consultar() {
		try {
			Fachada.inicializar();

			String nomeMedico = "Marcos";
			Medico medico = (Medico) Fachada.consultarPessoasPorNome(nomeMedico).get(0);
			int quantidade = Fachada.consultarQuantidadeAtendimentosPorMedico(medico.getCrm());

			System.out.println("Quantidade de atendimentos do medico " + nomeMedico + ": " + quantidade);

			String especialidade = "Dermatologia";
			List<Paciente> pacientes = Fachada.consultarPacientesAtendidosPorEspecialidade(especialidade);
			System.out.println("Pacientes atendidos por medicos com a especialidade: " + especialidade);
			for (Paciente p : pacientes)
				System.out.println(p);

			especialidade = "Pediatria";
			List<Medico> medicos = Fachada.consultarMedicosPorEspecialidade(especialidade);
			System.out.println("Medicos com a especialidade: " + especialidade);
			for (Medico m : medicos)
				System.out.println(m);

			Fachada.finalizar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Consultar();
	}
}
