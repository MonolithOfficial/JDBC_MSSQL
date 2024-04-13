import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
    @Test
    public void testDb() {
        try(Connection connection = MSSQLConnection.connect()){
            String SQL = "SELECT * FROM Users";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                System.out.println(resultSet.getString("Username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
