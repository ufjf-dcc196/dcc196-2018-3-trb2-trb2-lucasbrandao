package ufjf.dcc196.trb01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;


public class bdController {

    private SQLiteDatabase conection;


    bdController(SQLiteDatabase conection) {
        this.conection = conection;
    }


    public void insertParticipante(Aluno participante) {
       try {
           ContentValues contentValues = new ContentValues();

           contentValues.put("NOME", participante.nome);
           contentValues.put("EMAIL", participante.email);
           contentValues.put("MATRICULA", participante.matricula);

           conection.insertOrThrow("PARTICIPANTE", null, contentValues);
       }catch (SQLException e) {
           Log.d("insert", "insertParticipante: erro");
       }
    }

    public void editParticipante(Aluno participante) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRICULA", participante.matricula);
        contentValues.put("NOME", participante.nome);
        contentValues.put("EMAIL", participante.email);

        String[] args = {participante.matricula};
        try {
            conection.update("PARTICIPANTE", contentValues, "MATRICULA=?", args);
            Log.d("edit", "editado??? " + participante.matricula);
        } catch (SQLException e) {
            Log.d("edit", "editParticipante: erro ao editar");
        }

      //  String[] param = new String[1];
      //  param[0] = aluno.matricula;



    }

    public ArrayList<Aluno> getAll() {

        ArrayList<Aluno> participantes = new ArrayList<Aluno>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MATRICULA, NOME, EMAIL ");
        sql.append(" FROM PARTICIPANTE ");

        Cursor result = conection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();

            do {
                Aluno al = new Aluno(null, null, null);
                al.matricula = result.getString(result.getColumnIndexOrThrow("MATRICULA"));
                al.nome = result.getString(result.getColumnIndexOrThrow("NOME"));
                al.email = result.getString(result.getColumnIndexOrThrow("EMAIL"));

                participantes.add(al);

            } while (result.moveToNext());

        }

        return participantes;
    }

    public void loadParticipantesList() {

        ArrayList<Aluno> participantes = new ArrayList<Aluno>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MATRICULA, NOME, EMAIL ");
        sql.append(" FROM PARTICIPANTE ");

        Cursor result = conection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();

            do {
                Aluno al = new Aluno(null, null, null);
                al.matricula = result.getString(result.getColumnIndexOrThrow("MATRICULA"));
                al.nome = result.getString(result.getColumnIndexOrThrow("NOME"));
                al.email = result.getString(result.getColumnIndexOrThrow("EMAIL"));

                MainActivity.listaAlunos.add(al);

            } while (result.moveToNext());

        }
    }


    public Aluno getParticipante(int position) {
        Aluno participante = new Aluno(null, null, null);

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MATRICULA, NOME, EMAIL ");
        sql.append(" FROM PARTICIPANTE ");
        sql.append(" WHERE MATRICULA = ? ");

        String[] param = new String[1];
        param[0] = String.valueOf(position);

        Cursor result = conection.rawQuery(sql.toString(), param);

        if (result.getCount() > 0) {
            result.moveToFirst();

            participante.matricula = result.getString(result.getColumnIndexOrThrow("MATRICULA"));
            participante.nome = result.getString(result.getColumnIndexOrThrow("NOME"));
            participante.email = result.getString(result.getColumnIndexOrThrow("EMAIL"));

            return participante;
        }

        return null;
    }



    public void insertEvento(Evento event) {
        try {
            ContentValues contentValues = new ContentValues();

            contentValues.put("TITULO", event.titulo);
            contentValues.put("DIA", event.dia);
            contentValues.put("HORA", event.hora);
            contentValues.put("FACILITADOR", event.facilitador);
            contentValues.put("DESCRICAO", event.descricao);

            conection.insertOrThrow("EVENTO", null, contentValues);
        }catch (SQLException e) {
            Log.d("insert", "insertEvento: erro");
        }
    }
    public void loadEventosList() {

        ArrayList<Evento> eventos = new ArrayList<Evento>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT TITULO, DIA, HORA, FACILITADOR, DESCRICAO ");
        sql.append(" FROM EVENTO ");

        Cursor result = conection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();

            do {
                Evento ev = new Evento(null, null, null, null, null);
                ev.titulo = result.getString(result.getColumnIndexOrThrow("TITULO"));
                ev.dia = result.getString(result.getColumnIndexOrThrow("DIA"));
                ev.hora = result.getString(result.getColumnIndexOrThrow("HORA"));
                ev.facilitador = result.getString(result.getColumnIndexOrThrow("FACILITADOR"));
                ev.descricao = result.getString(result.getColumnIndexOrThrow("DESCRICAO"));

                MainActivity.listaEventos.add(ev);

            } while (result.moveToNext());

        }
    }

}
