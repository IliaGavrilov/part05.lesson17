package part05.lesson17.task01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* Класс для проектирования базы с основными таблицами */
public class TablesCreator {
    static List<String> tablesSQL = new ArrayList<String>();
    static String user = "CREATE TABLE IF NOT EXISTS \"USER\"(\n" +
            "user_id serial PRIMARY KEY,\n" +
            "name varchar(50) UNIQUE NOT NULL,\n" +
            "birthday TIMESTAMP NOT NULL,\n" +
            "login_id TIMESTAMP NOT NULL,\n" +
            "city varchar(355) NOT NULL,\n" +
            "email varchar(355) UNIQUE NOT NULL,\n" +
            "description TEXT NOT NULL);";
    static String role = "CREATE TABLE IF NOT EXISTS \"ROLE\"(\n" +
            "role_id serial PRIMARY KEY,\n" +
            "name varchar(50) UNIQUE NOT NULL,\n" +
            "CHECK (name = 'Administration' OR name = 'Clients' OR name = 'Billing'),\n" +
            "description TEXT NOT NULL);";
    static String userRole = "CREATE TABLE IF NOT EXISTS \"USER_ROLE\"(\n" +
            "id INTEGER UNIQUE NOT NULL,\n" +
            "role_id integer NOT NULL,\n" +
            "user_id integer NOT NULL,\n" +
            "PRIMARY KEY (role_id, user_id),\t\n" +
            "CONSTRAINT USER_ROLE_user_id_fkey FOREIGN KEY (user_id)\n" +
            "\tREFERENCES \"USER\" (user_id) MATCH SIMPLE\n" +
            "\tON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
            "CONSTRAINT ROLE_role_id_fkey FOREIGN KEY (role_id)\n" +
            "\tREFERENCES \"ROLE\" (role_id) MATCH SIMPLE\n" +
            "\tON UPDATE NO ACTION ON DELETE NO ACTION);";

    static String appLogs = "create table IF NOT EXISTS APP_LOGS(\n" +
            "LOG_ID varchar(100) primary key,\n" +
            "ENTRY_DATE timestamp,\n" +
            "LOG_LEVEL varchar(100),\n" +
            "MESSAGE TEXT,\n" +
            "EXCEPTION TEXT\n" +
            ");";

    public TablesCreator(){}

    /* Основной метод класса TablesCreator для наполнения базы */
    public static void createTables(Connection cn) throws SQLException {
        PreparedStatement ps = null;
        tablesSQL.add(user);
        tablesSQL.add(role);
        tablesSQL.add(userRole);
        tablesSQL.add(appLogs);
        for (String elem : tablesSQL){
            ps = cn.prepareStatement(elem);
            ps.executeUpdate();
        }
        ps.close();
    }
}