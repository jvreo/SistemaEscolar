package Model;

import java.util.ArrayList;
import java.util.Date;

public class Professor extends Pessoa{
    private long registro;

    private ArrayList<String> disciplinas = new ArrayList<>();

    private String especialidade;


    public long getRegistro() {
        return registro;
    }

    public void setRegistro(long registro) {
        this.registro = registro;
    }

    public ArrayList<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        String saidaDisciplinas;

        if (disciplinas != null && !disciplinas.isEmpty()){
            for(int i = 0; i < disciplinas.size(); i++){
                stringBuilder.append("  Disciplina ").append((i+1)).append(": ").append(disciplinas.get(i)).append("\n");
            }
            saidaDisciplinas = stringBuilder.toString();
        } else {
            saidaDisciplinas  = "Nao ha disciplinas cadastradas!";
        }

        return super.toString() +
                "Registro: " + registro + "\n" +
                "Especialidade: " + especialidade + "\n" +
                "Disciplinas: \n" + saidaDisciplinas;

    }

    public Professor(String nome, String data_nasc, long registro, String especialidade, ArrayList<String> disciplinas) {
        super(nome, data_nasc);
        this.registro = registro;
        this.especialidade = especialidade;
        this.disciplinas = disciplinas;
    }
}
