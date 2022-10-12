package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Pessoa;

public class DAOPessoa extends DAO<Pessoa> {
    public Pessoa read(Object chave) {
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(Pessoa.class);
        q.descend("cpf").constrain(cpf);
        List<Pessoa> resultados = q.execute();
        
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

    public List<Pessoa> getPessoasByNome(String nome) {
        Query q = manager.query();
        q.constrain(Pessoa.class);
        q.descend("nome").constrain(nome);

        return q.execute();
    }

    public List<Pessoa> getPessoasByEndereco(String endereco) {
        Query q = manager.query();
        q.constrain(Pessoa.class);
        q.descend("endereco").constrain(endereco);

        return q.execute();
    }

    public Pessoa getPessoaByTelefone(String telefone) {
        Query q = manager.query();
        q.constrain(Pessoa.class);
        q.descend("telefone").constrain(telefone);
        List<Pessoa> resultados = q.execute();

        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

    public Pessoa getPessoaByAtendimento(String protocolo) {
        Query q = manager.query();
        q.constrain(Pessoa.class);
        q.descend("atendimentos").descend("protocolo").constrain(protocolo);
        List<Pessoa> resultados = q.execute();

        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }
}
