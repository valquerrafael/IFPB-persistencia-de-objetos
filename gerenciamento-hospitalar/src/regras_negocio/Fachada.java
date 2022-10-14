package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;
import modelo.Pessoa;

public class Fachada {
    public static void inicializar() {
        DAO.open();
    }

    public static void finalizar() {
        DAO.close();
    }

    // Pessoa

    public static void alterarNome(String cpf, String nome) throws Exception {
        FachadaPessoa.alterarNome(cpf, nome);
    }

    public static void alterarEndereco(String cpf, String endereco) throws Exception {
        FachadaPessoa.alterarEndereco(cpf, endereco);
    }

    public static void alterarTelefone(String cpf, String telefoneAnterior, String telefoneNovo) throws Exception {
        FachadaPessoa.alterarTelefone(cpf, telefoneAnterior, telefoneNovo);
    }

    public static void adicionarTelefone(String cpf, String telefone) throws Exception {
        FachadaPessoa.adicionarTelefone(cpf, telefone);
    }

    public static void removerTelefone(String cpf, String telefone) throws Exception {
        FachadaPessoa.removerTelefone(cpf, telefone);
    }

    public static void adicionarAtendimento(String cpf, Atendimento atendimento) throws Exception {
        FachadaPessoa.adicionarAtendimento(cpf, atendimento);
    }

    public static void removerAtendimento(String cpf, Atendimento atendimento) throws Exception {
        FachadaPessoa.removerAtendimento(cpf, atendimento);
    }

    public static Pessoa excluirPessoa(String cpf) throws Exception {
        return FachadaPessoa.excluirPessoa(cpf);
    }

    public static Pessoa consultarPessoa(String cpf) throws Exception {
        return FachadaPessoa.consultarPessoa(cpf);
    }

    public static List<Pessoa> consultarPessoasPorNome(String nome) {
        return FachadaPessoa.consultarPessoasPorNome(nome);
    }

    public static List<Pessoa> consultarPessoasPorEndereco(String endereco) {
        return FachadaPessoa.consultarPessoasPorEndereco(endereco);
    }

    public static Pessoa consultarPessoaPorTelefone(String telefone) {
        return FachadaPessoa.consultarPessoaPorTelefone(telefone);
    }

    public static Pessoa consultarPessoaPorAtendimento(String protocolo) {
        return FachadaPessoa.consultarPessoaPorAtendimento(protocolo);
    }

    // Medico

    public static Medico cadastrarMedico(
        String nome,
        String endereco,
        String cpf,
        String telefone,
        String crm,
        String especialidade
    ) throws Exception {
        return FachadaMedico.cadastrarMedico(nome, endereco, cpf, telefone, crm, especialidade);
    }

    public static void alterarEspecialidade(String crm, String especialidade) throws Exception {
        FachadaMedico.alterarEspecialidade(crm, especialidade);
    }

    public static Medico excluirMedico(String crm) throws Exception {
        return FachadaMedico.excluirMedico(crm);
    }

    public static Medico consultarMedico(String crm) throws Exception {
        return FachadaMedico.consultarMedico(crm);
    }

    public static List<Medico> listarMedicos() {
        return FachadaMedico.listarMedicos();
    }

    public static List<Medico> consultarMedicosPorEspecialidade(String especialidade) {
        return FachadaMedico.consultarMedicosPorEspecialidade(especialidade);
    }

    // Paciente

    public static Paciente cadastrarPaciente(
        String nome,
        String endereco,
        String cpf,
        String telefone,
        String dataDeNascimento,
        String convenio
    ) throws Exception {
        return FachadaPaciente.cadastrarPaciente(nome, endereco, cpf, telefone, dataDeNascimento, convenio);
    }

    public static void alterarDataDeNascimento(String cpf, String dataDeNascimento) throws Exception {
        FachadaPaciente.alterarDataDeNascimento(cpf, dataDeNascimento);
    }

    public static void alterarConvenio(String cpf, String convenio) throws Exception {
        FachadaPaciente.alterarConvenio(cpf, convenio);
    }

    public static Paciente excluirPaciente(String cpf) throws Exception {
        return (Paciente) FachadaPessoa.excluirPessoa(cpf);
    }

    public static Paciente consultarPaciente(String cpf) throws Exception {
        return (Paciente) FachadaPessoa.consultarPessoa(cpf);
    }

    public static List<Paciente> listarPacientes() {
        return FachadaPaciente.listarPacientes();
    }

    public static List<Paciente> consultarPacientesPorDataDeNascimento(String dataDeNascimento) {
        return FachadaPaciente.consultarPacientesPorDataDeNascimento(dataDeNascimento);
    }

    public static List<Paciente> consultarPacientesAtendidosPorEspecialidade(String especialidade) {
        return FachadaPaciente.consultarPacientesAtendidosPorEspecialidade(especialidade);
    }

    // Atendimento

    public static Atendimento cadastrarAtendimento(
        String protocolo,
        String data,
        Paciente paciente,
        Medico medico
    ) throws Exception {
        Atendimento atendimento = FachadaAtendimento.cadastrarAtendimento(protocolo, data, paciente, medico);
        
        Fachada.adicionarAtendimento(paciente.getCpf(), atendimento);
        Fachada.adicionarAtendimento(medico.getCpf(), atendimento);

        return atendimento;
    }

    public static void alterarDataAtendimento(String protocolo, String data) throws Exception {
        FachadaAtendimento.alterarDataAtendimento(protocolo, data);
    }

    public static void alterarMedicoAtendimento(String protocolo, Medico medico) throws Exception {
        Atendimento atendimento = FachadaAtendimento.consultarAtendimento(protocolo);
        Medico medicoAnterior = atendimento.getMedico();

        FachadaPessoa.removerAtendimento(medicoAnterior.getCpf(), atendimento);
        FachadaPessoa.adicionarAtendimento(medico.getCpf(), atendimento);
        
        FachadaAtendimento.alterarMedicoAtendimento(protocolo, medico);
    }

    public static void alterarDiagnostico(String protocolo, String diagnostico) throws Exception {
        FachadaAtendimento.alterarDiagnostico(protocolo, diagnostico);
    }

    public static void alterarReceituario(String protocolo, String receituario) throws Exception {
        FachadaAtendimento.alterarReceituario(protocolo, receituario);
    }

    public static void adicionarProcedimento(String protocolo, String procedimento) throws Exception {
        FachadaAtendimento.adicionarProcedimento(protocolo, procedimento);
    }

    public static void removerProcedimento(String protocolo, String procedimento) throws Exception {
        FachadaAtendimento.removerProcedimento(protocolo, procedimento);
    }

    public static Atendimento excluirAtendimento(String protocolo) throws Exception {
        Atendimento atendimento = FachadaAtendimento.consultarAtendimento(protocolo);
        
        FachadaPessoa.removerAtendimento(atendimento.getMedico().getCpf(), atendimento);
        FachadaPessoa.removerAtendimento(atendimento.getPaciente().getCpf(), atendimento);

        return FachadaAtendimento.excluirAtendimento(protocolo);
    }

    public static Atendimento consultarAtendimento(String protocolo) throws Exception {
        return FachadaAtendimento.consultarAtendimento(protocolo);
    }

    public static List<Atendimento> listarAtendimentos() {
        return FachadaAtendimento.listarAtendimentos();
    }

    public static List<Atendimento> consultarAtendimentosPorData(String data) {
        return FachadaAtendimento.consultarAtendimentosPorData(data);
    }

    public static List<Atendimento> consultarAtendimentosPorDiagnostico(String diagnostico) {
        return FachadaAtendimento.consultarAtendimentosPorDiagnostico(diagnostico);
    }

    public static List<Atendimento> consultarAtendimentosPorPaciente(String cpf) {
        return FachadaAtendimento.consultarAtendimentosPorPaciente(cpf);
    }

    public static List<Atendimento> consultarAtendimentosPorMedico(String crm) {
        return FachadaAtendimento.consultarAtendimentosPorMedico(crm);
    }

    public static List<Atendimento> consultarAtendimentosPorProcedimento(String procedimento) {
        return FachadaAtendimento.consultarAtendimentosPorProcedimento(procedimento);
    }

    public static List<Atendimento> consultarAtendimentosPorReceituario(String receituario) {
        return FachadaAtendimento.consultarAtendimentosPorReceituario(receituario);
    }

    public static int consultarQuantidadeAtendimentosPorMedico(String crm) {
        return FachadaAtendimento.consultarQuantidadeAtendimentosPorMedico(crm);
    }
}
