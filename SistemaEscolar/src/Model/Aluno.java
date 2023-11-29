package Model;

import java.util.ArrayList;

public class Aluno extends Pessoa{
    private long matricula;

    private ArrayList<Double> notas = new ArrayList<>();

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public double calcularMedia(){
        double media = -1.0;
        if (notas != null && !notas.isEmpty()){
            media = 0;
            for (Double nota : notas) {
                media += nota;
            }
            if (media >= 0.000000001) media /= notas.size();
        }
        return media;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        String saidaNotas;

        if (notas != null && !notas.isEmpty()){
            for(int i = 0; i < notas.size(); i++){
                stringBuilder.append("  Nota ").append((i+1)).append(": ").append(notas.get(i)).append("\n");
            }
            saidaNotas = stringBuilder.toString();
        } else {
            saidaNotas  = "Nao ha notas cadastradas!";
        }

        return super.toString() +
               "Matricula: " + matricula + "\n" +
                "Notas: \n" + saidaNotas;
    }

    public Aluno(String nome, String data_nasc, long matricula, ArrayList<Double> notas) {
        super(nome, data_nasc);
        this.matricula = matricula;
        this.notas = notas;
    }
}
