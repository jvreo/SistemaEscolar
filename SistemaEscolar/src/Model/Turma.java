package Model;

import java.util.ArrayList;

public class Turma {
    private String nome;

    private String periodo;

    private int capacidadeMax;

    private int anoIngresso;

    private int semestre;

    private ArrayList<Aluno> alunos;

    private Professor professor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        if (alunos == null){
            alunos =  new ArrayList<>();
        }

        this.alunos.remove(aluno);
    }

    public String listarAlunos(){
        StringBuilder stringBuilder = new StringBuilder();

        if (alunos != null && !alunos.isEmpty()){
            for(int i = 0; i < alunos.size(); i++){
                stringBuilder.append("  Aluno ").append((i+1)).append(": ").append(alunos.get(i).toString()).append("\n\n");
            }
            return stringBuilder.toString();
        } else {
            return "Nao ha alunos nesta turma";
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        if (professor == null){
            stringBuilder.append("Professor responsavel: Nenhum");
        } else {
            stringBuilder.append("Professor responsavel: ").append(professor);
        }
        return "Nome: " + nome + "\n" +
                "Periodo: " + periodo + "\n" +
                "Capacidade Maxima de Alunos: " + capacidadeMax + "\n" +
                "Ano de Ingresso: " + anoIngresso + "\n" +
                "Semestre: " + semestre + "\n" +
                stringBuilder + "\n" +
                "Alunos:  \n " + listarAlunos();
    }

    public Turma(String nome, String periodo, int capacidadeMax, int anoIngresso, int semestre, ArrayList<Aluno> alunos, Professor professor) {
        this.nome = nome;
        this.periodo = periodo;
        this.capacidadeMax = capacidadeMax;
        this.anoIngresso = anoIngresso;
        this.semestre = semestre;
        this.alunos = alunos;
        this.professor = professor;

    }
}
