package ufjf.dcc196.trb01;

public class scriptSQL {

    public static String getCreateTable() {
        StringBuilder sql   = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS PARTICIPANTE ( ");
        sql.append("    MATRICULA   VARCHAR (50) PRIMARY KEY NOT NULL, ");
        sql.append("    NOME        VARCHAR (50) NOT NULL, ");
        sql.append("    EMAIL       VARCHAR (50) NOT NULL ); ");

        return sql.toString();

    }

    public static String getCreateEventTable() {
        StringBuilder sql   = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS EVENTO ( ");
        sql.append("    TITULO      VARCHAR (50) PRIMARY KEY NOT NULL, ");
        sql.append("    DIA         VARCHAR (50) NOT NULL, ");
        sql.append("    HORA        VARCHAR (50) NOT NULL, ");
        sql.append("    FACILITADOR VARCHAR (50) NOT NULL, ");
        sql.append("    DESCRICAO   VARCHAR (50) NOT NULL ) ");

        return sql.toString();

    }


}
