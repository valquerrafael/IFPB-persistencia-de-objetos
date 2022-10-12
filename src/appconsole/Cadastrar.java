package appconsole;

import modelo.Medico;
import modelo.Paciente;
import regras_negocio.Fachada;

public class Cadastrar {	
	public Cadastrar() {
		try {
			Fachada.inicializar();
			Medico medico;
			Paciente paciente;

			medico = Fachada.cadastrarMedico("Paulo", "Avenida Miramar, 155", "711.189.888-52", "(12)34567-8900", "123456", "Pediatria");
			paciente = Fachada.cadastrarPaciente("Maria", "Rua Olavo de Carvalho, 478", "456.897.589-86", "(87)89999-9999", "10/08/2000", "Unimed");
			
			Fachada.cadastrarAtendimento("1", "05/12/2021",paciente, medico);
			System.out.println("Cadastrou atendimento de Maria com Paulo");
			
			medico = Fachada.cadastrarMedico("Pedro", "Avenida Olhario, 511", "625.888.981-11", "(98)76543-2100", "654321", "Oftamologista");
			paciente = Fachada.cadastrarPaciente("Deolana", "Rua Carvalho de Olavo, 874", "689.857.986-54", "(98)76543-2100", "08/10/2001", "Smile");
			
			Fachada.cadastrarAtendimento("2","12/05/2020", paciente, medico);
			Fachada.adicionarTelefone(paciente.getCpf(), "(99)99999-9987");
			System.out.println("Cadastrou atendimento de Deolana com Pedro");
			
			medico = Fachada.cadastrarMedico("Daniel", "Gabriel Guerra, 870", "789.568.963-00", "(78)89636-9860", "456123", "Pediatria");
			paciente = Fachada.cadastrarPaciente("Soraya", "Rua Batos Holanda, 789","489.569.632-59", "(78)89636-9860", "12/12/1998", "Unimed");
			
			Fachada.cadastrarAtendimento("3", "25/01/2022",paciente, medico);
			Fachada.adicionarTelefone(paciente.getCpf(), "798965944548");
			System.out.println("Cadastrou atendimento de Soraya com Daniel");
			
			medico = Fachada.cadastrarMedico("Rodrigo", "Holana Borgens, 155", "748.755.489-12", "(98)89659-8945", "478965", "Urulogia");
			paciente = Fachada.cadastrarPaciente("Debora", "Rua Espedito neves, 126","558.696.458-45", "(78)87896-2356", "10/08/2000", "Unimed");
			
			Fachada.cadastrarAtendimento("4","31/04/2019",paciente, medico);
			Fachada.adicionarTelefone(paciente.getCpf(), "75965698547");
			System.out.println("Cadastrou atendimento de Debora com Rodrigo");
			
			medico = Fachada.cadastrarMedico("Emanuel", "Genral Pessoa, 145", "478.456.896-26", "(83)84589-5623", "458969", "Dermatologia");
			paciente = Fachada.cadastrarPaciente("Ingrid", "Avenida Guerra, 789","489.589.789-59", "(83)87896-5263", "15/08/2000", "Hapvida");
			
			Fachada.cadastrarAtendimento("5","25/01/2022",paciente, medico);
			Fachada.adicionarTelefone(paciente.getCpf(), "99965696546");
			System.out.println("Cadastrou atendimento de Ingrid com Emanuel");

			Fachada.finalizar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Cadastrar();
	}
}
