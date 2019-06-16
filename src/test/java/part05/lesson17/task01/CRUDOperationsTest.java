package part05.lesson17.task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CRUDOperationsTest {
  static Connection connection;
  String insertQuery;
  List<String> names;
  List<String> descriptions;
  String insertBatchQuery;
  private static final Logger logger = LogManager.getLogger(CRUDOperationsTest.class);
  String query;

  /* Инициализация соединения с БД перед началом тестов */
  @BeforeAll
  static void initAll() throws SQLException {
    connection = ConnectionFactory.getConnection();
  }

  /* Подготовка данных для CRUD операций в методе init используя @Before */
  @BeforeEach
  void init(){
    insertQuery = "INSERT INTO \"USER\" (user_id, name, birthday, login_id, city, email, description)\n" +
            "VALUES (?, ?, '1987-09-06 21:45:00', '2019-05-28 21:09:00', 'Innopolis', 'iluha_gia@mail.ru', 'любит вареники');";
    names = Arrays.asList("Administration", "Clients", "Billing");
    descriptions = Arrays.asList("умеет готовить вареники", "не умеет готовить", "продает вареники");
    insertBatchQuery = "INSERT INTO \"ROLE\" (role_id, name, description)\n" +
            "VALUES (?, ?, ?); ";
    query = "SELECT * FROM \"USER\";";
  }

  /* Пример использования JUnit5 на одну проверку */
  @Test
  void aTestConnection(){
    assertNotNull(connection, "Не сделали тест!");
  }

  /* Метод для осздания таблиц */
  @Test
  void bCreateTables() throws SQLException {
    TablesCreator.createTables(connection);
  }

  /* Параметризированный запрос INSERT */
  @Test
  void cGenericInsert() throws SQLException {
    logger.info("Сообщение JDBC номер #{} уровня INFO.", 1);
    PreparedStatement insert = connection.prepareStatement(insertQuery);
    insert.setInt(1, 0);
    insert.setString(2, "Илья");
    insert.execute();
    connection.commit();
    insert.close();
  }

  /* Параметризированный запрос INSERT, используя Batch процесс */
  @Test
  void dGenericInsert() throws SQLException {
    try{
      logger.info("Сообщение JDBC номер #{} уровня INFO.", 2);
      PreparedStatement insertBatch = connection.prepareStatement(insertBatchQuery);
      connection.setAutoCommit(false);
      for(int i=0; i<= 2;i++){
        insertBatch.setInt(1, i);
        insertBatch.setString(2, names.get(i));
        insertBatch.setString(3, descriptions.get(i));
        insertBatch.addBatch();
      }
      insertBatch.executeBatch();
      connection.commit();
      insertBatch.close();
    }catch(Exception e){
      logger.error("Сообщение JDBC номер #{} уровня ERROR.", 1);
      e.printStackTrace();
      connection.rollback();
    }
  }

  @AfterEach
  @Disabled("for demonstration purposes")
  void tearDown() {
      // not executed
  }

  /* Закрываем соединие с БД после завершения всех тестов */
  @AfterAll
  static void tearDownAll() throws SQLException {
    connection.close();
  }
}