package com.vchanger;

import java.sql.*;
import java.util.HashMap;

public class SQLite {
    private String filePath;
    public SQLite(String filePath) {
        this.filePath=filePath;
    }
    private Connection getConnection() throws SQLException, ClassNotFoundException {
    String dbUrl = "jdbc:sqlite:"+this.filePath;
    Connection connection = DriverManager.getConnection(dbUrl);
    return connection;
    }
    public HashMap<String,HashMap<String,Double>> getResult(String group) throws SQLException, ClassNotFoundException {
        Connection connection = this.getConnection();
        HashMap<String,HashMap<String,Double>> result = new HashMap<>();
        String query = null;
        String[] titles = {group, "Год", "Объем_ежегодного_потребления"  };
        switch(group) {
            case "Страна":
                query = "SELECT country as Страна, year, " +
                        "SUM((ROUND(((thermalCapacity/burnup) * LoadFactor.loadFactor)/100, 2))) as yearLoadFactor " +
                        "FROM Reactors " +
                        "JOIN LoadFactor ON Reactors.name = LoadFactor.reactor " +
                        "JOIN ReactorsTypes ON Reactors.type = ReactorsTypes.type " +
                        "GROUP BY country, year " +
                        "ORDER BY country, year;";

                break;
            case "Компания":
                query = "SELECT operator as Компания, year, " +
                        "SUM((ROUND(((thermalCapacity/burnup) * LoadFactor.loadFactor)/100,2))) as yearLoadFactor " +
                        "FROM Reactors " +
                        "JOIN LoadFactor ON Reactors.name=LoadFactor.reactor " +
                        "JOIN ReactorsTypes ON Reactors.type=ReactorsTypes.type " +
                        "GROUP BY operator, year " +
                        "ORDER BY operator, year;";
                break;
            case "Регион":
                query = "SELECT region as Регион, year, " +
                        "SUM((ROUND(((thermalCapacity/burnup) * LoadFactor.loadFactor)/100,2))) as yearLoadFactor " +
                        "FROM Reactors " +
                        "JOIN Countries ON Reactors.country = Countries.country " +
                        "JOIN LoadFactor ON Reactors.name = LoadFactor.reactor " +
                        "JOIN ReactorsTypes ON Reactors.type = ReactorsTypes.type " +
                        "GROUP BY region, year " +
                        "ORDER BY region, year;";
                break;
        }
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            if (!result.containsKey(resultSet.getString(group))) {
                result.put(resultSet.getString(group), new HashMap<>());
            }
            if (!result.get(resultSet.getString(group)).containsKey(resultSet.getString("year"))) {
                result.get(resultSet.getString(group)).put(resultSet.getString("year"), resultSet.getDouble("yearLoadFactor"));
            }
        }
        connection.close();
        return result;
    }


}
