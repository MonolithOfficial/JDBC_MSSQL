package ge.tbc.tbcacademy.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProvider {
    @org.testng.annotations.DataProvider
    public static Object[][] databaseProvider(){
        try (Connection connection = MSSQLConnection.connect()){
            String SQL = """
                    SELECT RegistrationData.firstName, RegistrationData.lastName, Accounts.username
                    FROM RegistrationData
                    INNER JOIN Accounts ON RegistrationData.id = Accounts.ownerId
                    """;
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(SQL);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            Object[][] data = new Object[rowCount][resultSet.getMetaData().getColumnCount()];

            int iter = 0;
            while (resultSet.next()){
                data[iter] = new Object[] {
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("username"),
                };
                iter++;
            }
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[][]{};
        }
    }
}
