package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Pessoa {
    private String nome;

    private String data_nasc;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + "\n" +
                "Data de nascimento: " + data_nasc + "\n";
    }

    public Pessoa(String nome, String data_nasc){
        this.nome = nome.toLowerCase().trim();
        this.data_nasc = data_nasc;
    }
}
