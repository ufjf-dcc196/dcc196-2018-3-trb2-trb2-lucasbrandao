package ufjf.dcc196.trb01;

import java.io.Serializable;

public class Aluno implements Serializable{

    private String nome;
    private String email;
    private String matricula;


    Aluno(String name, String email, String registration) {
        this.nome = name;
        this.email = email;
        this.matricula = registration;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void alterarDados(String novoNome, String novoEmail) {
        this.nome = novoNome;
        this.email = novoEmail;
    }
}
