package modelo;

public class Paciente extends Pessoa {
    private String dataDeNascimento;
    private String convenio;

    public Paciente(
        String nome,
        String endereco,
        String cpf,
        String telefone,
        String dataDeNascimento,
        String convenio
    ) {
        super(nome, endereco, cpf, telefone);
        this.dataDeNascimento = dataDeNascimento;
        this.convenio = convenio;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nascimento: " + this.dataDeNascimento + ", convenio: " + this.convenio;
    }
}
