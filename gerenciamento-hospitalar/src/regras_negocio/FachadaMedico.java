package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOMedico;
import modelo.Medico;

class FachadaMedico {
    private static DAOMedico daoMedico = new DAOMedico();
    
    static Medico cadastrarMedico(
        String nome,
        String endereco,
        String cpf,
        String telefone,
        String crm,
        String especialidade
    ) throws Exception {
        DAO.begin();
        Medico medico = daoMedico.read(crm);

        if (medico != null) {
            DAO.rollback();
            throw new Exception("Médico ja cadastrado!");
        }

        medico = new Medico(nome, endereco, cpf, telefone, crm, especialidade);
        
        daoMedico.create(medico);
        DAO.commit();
        return medico;
    }

    static void alterarEspecialidade(String crm, String especialidade) throws Exception {
        DAO.begin();
        Medico medico = daoMedico.read(crm);

        if (medico == null) {
            DAO.rollback();
            throw new Exception("Médico nao esta cadastrado!");
        }
        
        medico.setEspecialidade(especialidade);

        daoMedico.update(medico);
        DAO.commit();
    }

    static Medico excluirMedico(String crm) throws Exception {
        DAO.begin();
        Medico medico = daoMedico.read(crm);

        if (medico == null) {
            DAO.rollback();
            throw new Exception("Médico nao esta cadastrado!");
        }

        daoMedico.delete(medico);
        DAO.commit();
        return medico;
    }

    static Medico consultarMedico(String crm) throws Exception {
        DAO.begin();
        Medico medico = daoMedico.read(crm);

        if (medico == null) {
            DAO.rollback();
            throw new Exception("Médico nao esta cadastrado!");
        }

        DAO.commit();
        return medico;
    }

    static List<Medico> listarMedicos() {
        DAO.begin();
        List<Medico> medicos = daoMedico.readAll();
        DAO.commit();
        return medicos;
    }

    static List<Medico> consultarMedicosPorEspecialidade(String especialidade) {
        DAO.begin();
        List<Medico> medicos = daoMedico.getMedicosByEspecialidade(especialidade);
        DAO.commit();
        return medicos;
    }
}
