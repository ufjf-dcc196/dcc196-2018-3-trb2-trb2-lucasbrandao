package ufjf.dcc196.trb01;

import java.io.Serializable;

public class Evento implements Serializable {
    private String titulo;
    private String dia;
    private String hora;
    private String facilitador;
    private String descricao;


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
}
