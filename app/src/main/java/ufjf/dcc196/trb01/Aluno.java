package ufjf.dcc196.trb01;

import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable{

    private String nome;
    private String email;
    private String matricula;
    private ArrayList<Evento> eventosInscritos = new ArrayList<Evento>();

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

    public void inscreverEvento(Evento ev) {
        this.eventosInscritos.add(ev);
    }

    public void cancelarInscricao(int position) {
        this.eventosInscritos.remove(position);
    }

    public ArrayList<Evento> getEventosInscritos() {

        return this.eventosInscritos;
    }
}
