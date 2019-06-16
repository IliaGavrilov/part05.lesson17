package part05.lesson17.task02;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/* Класс с тестами, который ставит заглушки для JDBC с использованием аннотацию @Mock */
@RunWith(MockitoJUnitRunner.class)
public class UserTestMock {
    @Mock
    private DataSource ds;

    @Mock
    private Connection c;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    private DummyUser u;

    /* Метод с определением возвращаемого значения */
    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(c);

        u = new DummyUser();
        u.setUserId(1);
        u.setName("Илья");
        u.setBirthday("1987-09-06 21:45:00");
        u.setLoginId("2019-05-28 21:09:00");
        u.setCity("Innopolis");
        u.setEmail("iluha_gia@mail.ru");
        u.setDescription("любит вареники");

        when(rs.getInt(1)).thenReturn(u.getUserId());
        when(rs.getString(2)).thenReturn(u.getName());
        when(rs.getString(3)).thenReturn(u.getBirthday());
        when(rs.getString(4)).thenReturn(u.getLoginId());
        when(rs.getString(5)).thenReturn(u.getCity());
        when(rs.getString(6)).thenReturn(u.getEmail());
        when(rs.getString(7)).thenReturn(u.getDescription());
        when(stmt.executeQuery()).thenReturn(rs);
    }

    /* Простой пример теста для проверки заглушки */
    @Test
    public void aTestConnection() {
        assertNotNull(c);
    }

    /* Простой пример теста для объекта Mock */
    @Test
    public void bTestResultSet() {
        assertNotNull(rs);
    }

    /* Метод, который не проходит тест */
    @Test
    public void cTestEquals() throws SQLException {
        assertEquals(rs.getString(2), "Вова");
    }
}