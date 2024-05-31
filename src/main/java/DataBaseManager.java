import java.sql.*;
import java.util.Properties;

public class DataBaseManager {
    Integer PORT = 64327;
    Connection connection;
    public DataBaseManager(String user, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:45345/studs";
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            props.setProperty("ssl", "false");
            connection = DriverManager.getConnection(url, props);
            Main.logger.info("Connection established");
        } catch (ClassNotFoundException e) {
            Main.logger.severe("Problem with DB driver");
        }
    }

    public void getKeys() throws SQLException {
        String query = "select jsonb_object_keys(conditionforresult) from condition";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }

    public void addKey(String key, String value) throws SQLException {
        String keyforcondition = "'{\"" + key + "\": " + "\"" + value + "\"}'";
        System.out.println(keyforcondition);
        String query = "UPDATE condition SET conditionforresult = conditionforresult|| " + keyforcondition + "::jsonb; ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            if(rs.next()) System.out.println("key: value added");
        }
    }


    public String getCondition(String key) throws SQLException {
        String query = "SELECT conditionforresult->>'" + key + "' AS condition_a FROM condition;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return "Error in database";
    }

    public String reformatCondition(String key) throws SQLException {
        String condition = getCondition(key);
        condition = "WHERE " + condition;
        return condition;
    }

    public void queryWithCondition(String key) throws SQLException {
        String condition = reformatCondition(key);
        String query = "SELECT * FROM research " + condition + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (!rs.isBeforeFirst() ) {
                System.out.println("No data or key is invalid");
            } else {
                int columncount = rsmd.getColumnCount();
                for (int i = 1; i <= columncount; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    System.out.print(rsmd.getColumnName(i));
                }
                System.out.print("\n");
                while (rs.next()) {
                    for (int i = 1; i <= columncount; i++) {
                        if (i > 1) {
                            System.out.print(",  ");
                        }
                        String columnValue = rs.getString(i);
                        System.out.print(columnValue);
                    }
                    System.out.println("");
                }
            }
        }
    }

}
