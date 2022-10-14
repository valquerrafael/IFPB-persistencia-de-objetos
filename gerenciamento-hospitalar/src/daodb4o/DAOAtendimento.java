package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Atendimento;

public class DAOAtendimento extends DAO<Atendimento> {
    @Override
    public void create(Atendimento atendimento) {
        atendimento.setId(super.gerarId());
        manager.store(atendimento);
    }

    public Atendimento read(Object chave) {
        String protocolo = (String) chave;
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("protocolo").constrain(protocolo);
        List<Atendimento> resultados = q.execute();
        
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

    public Atendimento getAtendimentoById(int id) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("id").constrain(id);
        List<Atendimento> resultados = q.execute();

        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

    public List<Atendimento> getAtendimentosByMedico(String crm) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("medico").descend("crm").constrain(crm);

        return q.execute();
    }

    public List<Atendimento> getAtendimentosByPaciente(String cpf) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("paciente").descend("cpf").constrain(cpf);

        return q.execute();
    }

    public List<Atendimento> getAtendimentosByData(String data) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("data").constrain(data);

        return q.execute();
    }

    public List<Atendimento> getAtendimentosByProcedimento(String procedimento) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("procedimentos").constrain(procedimento);

        return q.execute();
    }

    public List<Atendimento> getAtendimentosByDiagnostico(String diagnostico) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("diagnostico").constrain(diagnostico);

        return q.execute();
    }

    public List<Atendimento> getAtendimentosByReceituario(String receituario) {
        Query q = manager.query();
        q.constrain(Atendimento.class);
        q.descend("receituario").constrain(receituario);

        return q.execute();
    }
}
