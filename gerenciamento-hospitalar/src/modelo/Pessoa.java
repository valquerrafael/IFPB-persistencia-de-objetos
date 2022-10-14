package modelo;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private String endereco;
    private String cpf;
    private ArrayList<String> telefones = new ArrayList<>();
    private ArrayList<Atendimento> atendimentos = new ArrayList<>();

    public Pessoa(String nome, String endereco, String cpf, String telefone) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.telefones.add(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<String> getTelefones() {
        return telefones;
    }

    public void adicionarTelefone(String telefone) {
        this.telefones.add(telefone);
    }

    public void removerTelefone(String telefone) {
        this.telefones.remove(telefone);
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
    }

    public void removerAtendimento(Atendimento atendimento) {
        this.atendimentos.remove(atendimento);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", CPF: " + this.cpf;
    }
}
