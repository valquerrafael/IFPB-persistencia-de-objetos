package modelo;

import java.util.ArrayList;

public class Atendimento {
    private int id;
    private String protocolo;
    private String data;
    private Paciente paciente;
    private Medico medico;
    private String diagnostico;
    private String receituario;
    private ArrayList<String> procedimentos = new ArrayList<>();

    public Atendimento(String protocolo, String data, Paciente paciente, Medico medico) {
        this.protocolo = protocolo;
        this.data = data; 
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    public ArrayList<String> getProcedimentos() {
        return procedimentos;
    }

    public void adicionarProcedimento(String procedimento) {
        this.procedimentos.add(procedimento);
    }

    public void removerProcedimento(String procedimento) {
        this.procedimentos.remove(procedimento);
    }

    @Override
    public String toString() {
        return "Protocolo: " + this.protocolo +
                "\nPaciente: " + this.paciente +
                "\nMedico:" + this.medico;
    }
}
