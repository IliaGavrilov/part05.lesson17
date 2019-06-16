package part05.lesson17.task01;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    /* Параметры подключения как константы класса */
    static String url = "jdbc:postgresql://localhost:5432/postgres_v3";
    static String driver = "org.postgresql.Driver";
    static String user = "postgres";
    static String password = "jU3y3gIV";

    public ConnectionFactory() {
    }

    /* Основной метод класса для установки связи с БД */
    public static Connection getConnection() throws SQLException {
        BasicDataSource dataSource;
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource.getConnection();
    }
}