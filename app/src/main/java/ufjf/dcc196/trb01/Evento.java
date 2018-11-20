package ufjf.dcc196.trb01;

import java.io.Serializable;
import java.util.ArrayList;

public class Evento implements Serializable {

    public String titulo;
    public String dia;
    public String hora;
    public String facilitador;
    public String descricao;

    private ArrayList<Aluno> participantesInscritos = new ArrayList<Aluno>();

    Evento(String title, String day, String time, String facilitator, String description) {
        this.titulo = title;
        this.dia = day;
        this.hora = time;
        this.facilitador = facilitator;
        this.descricao = description;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public String getDescricao() {
        return descricao;
    }

    public ArrayList<Aluno> getParticipantesInscritos() {
        return this.participantesInscritos;
    }

    public void inscreverParticipante(Aluno al) {
        this.participantesInscritos.add(al);
    }

    public void cancelaInscricaoParticipante(int position) {
        this.participantesInscritos.remove(position);
    }

}
