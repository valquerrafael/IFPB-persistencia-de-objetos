package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOPaciente;
import modelo.Paciente;

class FachadaPaciente {
    private static DAOPaciente daoPaciente = new DAOPaciente();

    static Paciente cadastrarPaciente(
        String nome,
        String endereco,
        String cpf,
        String telefone,
        String dataDeNascimento,
        String convenio
    ) throws Exception {
        DAO.begin();
        Paciente paciente = daoPaciente.read(cpf);

        if (paciente != null) {
            DAO.rollback();
            throw new Exception("Paciente ja cadastrado!");
        }

        paciente = new Paciente(nome, endereco, cpf, telefone, dataDeNascimento, convenio);

        daoPaciente.create(paciente);
        DAO.commit();
        return paciente;
    }

    static void alterarDataDeNascimento(String cpf, String dataDeNascimento) throws Exception {
        DAO.begin();
        Paciente paciente = daoPaciente.read(cpf);

        if (paciente == null) {
            DAO.rollback();
            throw new Exception("Paciente nao esta cadastrado!");
        }

        paciente.setDataDeNascimento(dataDeNascimento);

        daoPaciente.update(paciente);
        DAO.commit();
    }

    static void alterarConvenio(String cpf, String convenio) throws Exception {
        DAO.begin();
        Paciente paciente = daoPaciente.read(cpf);

        if (paciente == null) {
            DAO.rollback();
            throw new Exception("Paciente nao esta cadastrado!");
        }

        paciente.setConvenio(convenio);

        daoPaciente.update(paciente);
        DAO.commit();
    }

    static List<Paciente> listarPacientes() {
        DAO.begin();
        List<Paciente> pacientes = daoPaciente.readAll();
        DAO.commit();
        return pacientes;
    }

    static List<Paciente> consultarPacientesPorDataDeNascimento(String dataDeNascimento) {
        DAO.begin();
        List<Paciente> pacientes = daoPaciente.getPacientesByDataDeNascimento(dataDeNascimento);
        DAO.commit();
        return pacientes;
    }

    static List<Paciente> consultarPacientesAtendidosPorEspecialidade(String especialidade) {
        DAO.begin();
        List<Paciente> pacientes = daoPaciente.getPacientesAtendidosByEspecialidade(especialidade);
        DAO.commit();
        return pacientes;
    }
}
