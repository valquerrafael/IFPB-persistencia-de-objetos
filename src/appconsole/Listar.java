package appconsole;

import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;
import regras_negocio.Fachada;

public class Listar {
	public Listar()  {
		try {
			Fachada.inicializar();
			System.out.println("\nListagem de atendimentos");
			for (Atendimento a : Fachada.listarAtendimentos())
				System.out.println(a);
			
				System.out.println("\nListagem de medicos");
			for (Medico m : Fachada.listarMedicos())
				System.out.println(m);
			
				System.out.println("\nListagem de pacientes");
			for (Paciente p : Fachada.listarPacientes())
				System.out.println(p);
			Fachada.finalizar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Listar();
	}
}
