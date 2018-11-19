package ufjf.dcc196.trb01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class bdController {

    private SQLiteDatabase conection;


    bdController(SQLiteDatabase conection) {
        this.conection = conection;
    }


    public void insertParticipante(Aluno aluno) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRICULA", aluno.getMatricula());
        contentValues.put("NOME", aluno.getNome());
        contentValues.put("EMAIL", aluno.getEmail());

        conection.insertOrThrow("PARTICIPANTE", null, contentValues);
    }

    public void editParticipante(Aluno aluno) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRICULA", aluno.getMatricula());
        contentValues.put("NOME", aluno.getNome());
        contentValues.put("EMAIL", aluno.getEmail());

        String[] param = new String[1];
        param[0] = aluno.getMatricula();

        conection.update("PARTICIPANTE", contentValues, "MATRICULA = ?", param);
    }

    public List<Aluno> getAll() {

        List<Aluno> participantes = new ArrayList<Aluno>();

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

}
