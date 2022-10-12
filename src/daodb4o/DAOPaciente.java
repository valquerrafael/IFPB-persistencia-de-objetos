package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Paciente;

public class DAOPaciente extends DAO<Paciente> {
    public Paciente read(Object chave) {
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(Paciente.class);
        q.descend("cpf").constrain(cpf);
        List<Paciente> resultados = q.execute();
        
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

    public List<Paciente> getPacientesByDataDeNascimento(String dataDeNascimento) {
        Query q = manager.query();
        q.constrain(Paciente.class);
        q.descend("dataDeNascimento").constrain(dataDeNascimento);

        return q.execute();
    }

    public List<Paciente> getPacientesAtendidosByEspecialidade(String especialidade) {
        Query q = manager.query();
        q.constrain(Paciente.class);
        q.descend("atendimentos").descend("medico").descend("especialidade").constrain(especialidade);

        return q.execute();
    }

}
