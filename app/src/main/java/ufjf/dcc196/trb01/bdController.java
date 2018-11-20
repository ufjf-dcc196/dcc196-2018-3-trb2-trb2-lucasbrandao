package ufjf.dcc196.trb01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public void editParticipante(Aluno aluno) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRICULA", aluno.getMatricula());
        contentValues.put("NOME", aluno.getNome());
        contentValues.put("EMAIL", aluno.getEmail());

        String[] param = new String[1];
        param[0] = String.valueOf(aluno.getMatricula());

        conection.update("PARTICIPANTE", contentValues, "MATRICULA = ?", param);
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
                al.matricula = result.getInt(result.getColumnIndexOrThrow("MATRICULA"));
                al.nome = result.getString(result.getColumnIndexOrThrow("NOME"));
                al.email = result.getString(result.getColumnIndexOrThrow("EMAIL"));

                participantes.add(al);

            } while (result.moveToNext());

        }

        return participantes;
    }

    public void loadList() {

        ArrayList<Aluno> participantes = new ArrayList<Aluno>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MATRICULA, NOME, EMAIL ");
        sql.append(" FROM PARTICIPANTE ");

        Cursor result = conection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();

            do {
                Aluno al = new Aluno(null, null, null);
                al.matricula = result.getInt(result.getColumnIndexOrThrow("MATRICULA"));
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

            participante.matricula = result.getInt(result.getColumnIndexOrThrow("MATRICULA"));
            participante.nome = result.getString(result.getColumnIndexOrThrow("NOME"));
            participante.email = result.getString(result.getColumnIndexOrThrow("EMAIL"));

            return participante;
        }

        return null;
    }

}
