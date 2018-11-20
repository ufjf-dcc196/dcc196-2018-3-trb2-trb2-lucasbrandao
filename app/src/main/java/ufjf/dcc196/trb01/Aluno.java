package ufjf.dcc196.trb01;

import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable{

    public String nome;
    public String email;
    public Integer matricula;
    private ArrayList<Evento> eventosInscritos = new ArrayList<Evento>();

    Aluno(String name, String email, Integer matricula) {
        this.nome = name;
        this.email = email;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Integer getMatricula() {
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

    public Evento getEvento(int position) {
        return this.eventosInscritos.get(position);
    }
}
