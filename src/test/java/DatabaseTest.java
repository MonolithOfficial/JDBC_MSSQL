import ge.tbc.tbcacademy.data.DBSteps;
import ge.tbc.tbcacademy.data.DataProvider;
import ge.tbc.tbcacademy.data.MSSQLConnection;
import ge.tbc.tbcacademy.data.RegistrationModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseTest {
    DBSteps dbSteps;

    @BeforeClass
    public void setUp() {
        dbSteps = new DBSteps();
    }

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

    @Test
    public void testDB2() throws SQLException {
        List<RegistrationModel> registrations = dbSteps.getSpecificRegistrationRecord(1002);
        for (RegistrationModel registration : registrations){
            System.out.println(registration);
        }

//        int affectedRows = dbSteps.insertRegistration();
        int affectedRows = dbSteps.deleteRecordThatMatches("v");
        System.out.println(affectedRows + " rows have been affected");
    }

    @Test(dataProvider = "databaseProvider", dataProviderClass = DataProvider.class)
    public void testDataProviderFromDb(String firstName, String lastName, String username) {
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(username);
    }
}
