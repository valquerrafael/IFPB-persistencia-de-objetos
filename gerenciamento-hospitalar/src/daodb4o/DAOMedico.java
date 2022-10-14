package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Medico;

public class DAOMedico extends DAO<Medico> {
    public Medico read(Object chave) {
        String crm = (String) chave;
        Query q = manager.query();
        q.constrain(Medico.class);
        q.descend("crm").constrain(crm);
        List<Medico> resultados = q.execute();
        
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

    public List<Medico> getMedicosByEspecialidade(String especialidade) {
        Query q = manager.query();
        q.constrain(Medico.class);
        q.descend("especialidade").constrain(especialidade);

        return q.execute();
    }
}
