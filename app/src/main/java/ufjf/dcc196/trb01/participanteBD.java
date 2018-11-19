package ufjf.dcc196.trb01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class participanteBD extends SQLiteOpenHelper {

    public participanteBD(Context context) {
        super(context, "ParticipanteBD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(scriptSQL.getCreateTableParticipante());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
