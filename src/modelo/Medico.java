package modelo;

public class Medico extends Pessoa {
    private String crm;
    private String especialidade;

    public Medico(
        String nome,
        String endereco,
        String cpf,
        String telefone,
        String crm,
        String especialidade
    ) {
        super(nome, endereco, cpf, telefone);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return super.toString() + ", crm: " + this.crm + ", especialidade: " + this.especialidade;
    }
}
