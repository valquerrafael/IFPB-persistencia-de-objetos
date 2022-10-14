package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOPessoa;
import modelo.Atendimento;
import modelo.Pessoa;

class FachadaPessoa {
    private static DAOPessoa daoPessoa = new DAOPessoa();

    static void alterarNome(String cpf, String nome) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        pessoa.setNome(nome);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static void alterarEndereco(String cpf, String endereco) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        pessoa.setEndereco(endereco);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static void alterarTelefone(String cpf, String telefoneAnterior, String telefoneNovo) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }

        int indiceTelefoneAnterior = pessoa.getTelefones().indexOf(telefoneAnterior);
        pessoa.getTelefones().add(indiceTelefoneAnterior, telefoneNovo);
        pessoa.removerTelefone(telefoneAnterior);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static void adicionarTelefone(String cpf, String telefone) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        pessoa.adicionarTelefone(telefone);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static void removerTelefone(String cpf, String telefone) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        pessoa.removerTelefone(telefone);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static void adicionarAtendimento(String cpf, Atendimento atendimento) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        pessoa.adicionarAtendimento(atendimento);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static void removerAtendimento(String cpf, Atendimento atendimento) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        pessoa.removerAtendimento(atendimento);

        daoPessoa.update(pessoa);
        DAO.commit();
    }

    static Pessoa excluirPessoa(String cpf) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }

        daoPessoa.delete(pessoa);
        DAO.commit();
        return pessoa;
    }

    static Pessoa consultarPessoa(String cpf) throws Exception {
        DAO.begin();
        Pessoa pessoa = daoPessoa.read(cpf);

        if (pessoa == null) {
            DAO.rollback();
            throw new Exception("Pessoa nao esta cadastrada!");
        }
        
        DAO.commit();
        return pessoa;
    }

    static List<Pessoa> consultarPessoasPorNome(String nome) {
        DAO.begin();
        List<Pessoa> pessoas = daoPessoa.getPessoasByNome(nome);
        DAO.commit();
        return pessoas;
    }

    static List<Pessoa> consultarPessoasPorEndereco(String endereco) {
        DAO.begin();
        List<Pessoa> pessoas = daoPessoa.getPessoasByEndereco(endereco);
        DAO.commit();
        return pessoas;
    }

    static Pessoa consultarPessoaPorTelefone(String telefone) {
        DAO.begin();
        Pessoa pessoa = daoPessoa.getPessoaByTelefone(telefone);
        DAO.commit();
        return pessoa;
    }

    static Pessoa consultarPessoaPorAtendimento(String protocolo) {
        DAO.begin();
        Pessoa pessoa = daoPessoa.getPessoaByAtendimento(protocolo);
        DAO.commit();
        return pessoa;
    }
}
