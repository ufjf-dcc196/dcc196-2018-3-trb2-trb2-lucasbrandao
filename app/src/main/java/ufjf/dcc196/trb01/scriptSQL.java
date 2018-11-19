package ufjf.dcc196.trb01;

public class scriptSQL {

    public static String getCreateTableParticipante() {
        StringBuilder sql   = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS PARTICIPANTE ( ");
        sql.append("    MATRICULA   VARCHAR (20) PRIMARY KEY NOT NULL, ");
        sql.append("    NOME        VARCHAR (50) NOT NULL, ");
        sql.append("    EMAIL       VARCHAR (50) NOT NULL ) ");

        return sql.toString();

    }
}
