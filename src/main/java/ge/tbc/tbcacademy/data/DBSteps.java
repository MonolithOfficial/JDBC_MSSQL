package ge.tbc.tbcacademy.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSteps {
    public List<RegistrationModel> getSpecificRegistrationRecord(Integer id){
        List<RegistrationModel> registrations = new ArrayList<>();
        try (Connection conn = MSSQLConnection.connect()){
            String SQL = """
                    USE Registration
                    SELECT * FROM dbo.RegistrationData
                    WHERE id = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                registrations.add(new RegistrationModel(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender")
                ));
            }
            return registrations;
        } catch (SQLException e) {
            e.printStackTrace();
            return registrations;
        }
    }

    public int insertRegistration(){
        try (Connection conn = MSSQLConnection.connect()){
            String SQL = """
                    INSERT INTO dbo.RegistrationData VALUES(?,?,?,?,?,?,?,?,?,?)
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, 1003);
            preparedStatement.setString(2, "Gela");
            preparedStatement.setString(3, "Geladze");
            preparedStatement.setString(4, "male");
            preparedStatement.setString(5, "Mega 123 Large screen");
            preparedStatement.setString(6, "addr1");
            preparedStatement.setString(7, "addr2");
            preparedStatement.setString(8, "kutaisi");
            preparedStatement.setString(9, "con1");
            preparedStatement.setString(10, "con2");

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " rows have been affected.");

            return affectedRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteRecordThatMatches(String match){
        try (Connection conn = MSSQLConnection.connect()){
            String SQL = """
                    DELETE FROM dbo.RegistrationData WHERE lastName = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, "Khelashvili");
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
}
