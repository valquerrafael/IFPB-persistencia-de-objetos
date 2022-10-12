package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOAtendimento;
import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;

class FachadaAtendimento {
    private static DAOAtendimento daoAtendimento = new DAOAtendimento();
    
    static Atendimento cadastrarAtendimento(
        String protocolo,
        String data,
        Paciente paciente,
        Medico medico
    ) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento != null) {
            DAO.rollback();
            throw new Exception("Atendimento ja cadastrado!");
        }
        
        atendimento = new Atendimento(protocolo, data, paciente, medico);

        daoAtendimento.create(atendimento);
        DAO.commit();
        return atendimento;
    }

    static void alterarDataAtendimento(String protocolo, String data) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }
        
        atendimento.setData(data);

        daoAtendimento.update(atendimento);
        DAO.commit();
    }

    static void alterarMedicoAtendimento(String protocolo, Medico medico) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }
        
        atendimento.setMedico(medico);
        
        daoAtendimento.update(atendimento);
        DAO.commit();
    }

    static void alterarDiagnostico(String protocolo, String diagnostico) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }
        
        atendimento.setDiagnostico(diagnostico);

        daoAtendimento.update(atendimento);
        DAO.commit();
    }

    static void alterarReceituario(String protocolo, String receituario) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }

        atendimento.setReceituario(receituario);

        daoAtendimento.update(atendimento);
        DAO.commit();
    }

    static void adicionarProcedimento(String protocolo, String procedimento) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }

        atendimento.adicionarProcedimento(procedimento);

        daoAtendimento.update(atendimento);
        DAO.commit();
    }

    static void removerProcedimento(String protocolo, String procedimento) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }

        atendimento.removerProcedimento(procedimento);

        daoAtendimento.update(atendimento);
        DAO.commit();
    }

    static Atendimento excluirAtendimento(String protocolo) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }

        daoAtendimento.delete(atendimento);
        DAO.commit();
        return atendimento;
    }

    static Atendimento consultarAtendimento(String protocolo) throws Exception {
        DAO.begin();
        Atendimento atendimento = daoAtendimento.read(protocolo);

        if (atendimento == null) {
            DAO.rollback();
            throw new Exception("Atendimento nao esta cadastrado!");
        }

        DAO.commit();
        return atendimento;
    }

    static List<Atendimento> listarAtendimentos() {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.readAll();
        DAO.commit();
        return atendimentos;
    }

    static List<Atendimento> consultarAtendimentosPorData(String data) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByData(data);
        DAO.commit();
        return atendimentos;
    }

    static List<Atendimento> consultarAtendimentosPorDiagnostico(String diagnostico) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByDiagnostico(diagnostico);
        DAO.commit();
        return atendimentos;
    }

    static List<Atendimento> consultarAtendimentosPorPaciente(String cpf) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByPaciente(cpf);
        DAO.commit();
        return atendimentos;
    }

    static List<Atendimento> consultarAtendimentosPorMedico(String crm) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByMedico(crm);
        DAO.commit();
        return atendimentos;
    }

    static List<Atendimento> consultarAtendimentosPorProcedimento(String procedimento) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByProcedimento(procedimento);
        DAO.commit();
        return atendimentos;
    }

    static List<Atendimento> consultarAtendimentosPorReceituario(String receituario) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByReceituario(receituario);
        DAO.commit();
        return atendimentos;
    }

    static int consultarQuantidadeAtendimentosPorMedico(String crm) {
        DAO.begin();
        List<Atendimento> atendimentos = daoAtendimento.getAtendimentosByMedico(crm);
        DAO.commit();
        return atendimentos.size();
    }
}
