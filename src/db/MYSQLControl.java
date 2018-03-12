package db;

import model.SchoolScore;

import java.sql.*;

public class MYSQLControl {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://wanyuhui.top:3306/education_system";

    private static final String USER = "root";
    private static final String PASS = "541075babY";

    private static Connection connection = null;

    public static boolean getConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean close(){
        try {
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertSchool(String school){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("INSERT  INTO schoollist(schoolname) " +
                    "VALUES (?)");
            preparedStatement.setString(1, school);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static int selectSchool(String school){
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from schoollist where schoolname = '" + school + "';";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int id = 0;
            if(resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
            statement.close();
            return id;
        } catch (Exception e){
            return -1;
        }
    }

    public static boolean insertProvince(String province){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("INSERT  INTO provincelist(province) " +
                    "VALUES (?)");
            preparedStatement.setString(1, province);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static int selectProvince(String province){
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from provincelist where province = '" + province + "';";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int id = 0;
            if(resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
            statement.close();
            return id;
        } catch (Exception e){
            return -1;
        }
    }

    public static boolean insertClassical(String classical){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("INSERT  INTO classicallist(classical) " +
                    "VALUES (?)");
            preparedStatement.setString(1, classical);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static int selectClassical(String classical){
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from classicallist where classical = '" + classical + "';";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int id = 0;
            if(resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
            statement.close();
            return id;
        } catch (Exception e){
            return -1;
        }
    }

    public static boolean insertScore(SchoolScore schoolScore){
        PreparedStatement preparedStatement = null;
        try{
            String schoolName = schoolScore.getName();
            String province = schoolScore.getProvince();
            String classical = schoolScore.getClassical();
            if(selectSchool(schoolName) == 0 || selectSchool(schoolName) == -1){
                insertSchool(schoolName);
            }
            if(selectProvince(province) == 0 || selectProvince(province) == -1){
                insertProvince(province);
            }
            if(selectClassical(classical) == 0 || selectClassical(classical) == -1){
                insertClassical(classical);
            }
            preparedStatement = connection.prepareStatement("INSERT  INTO score(schoolid, provinceid, classical, year, lowscore, highscore, averagescore, batch) " +
                    "VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, selectSchool(schoolName));
            preparedStatement.setInt(2, selectProvince(province));
            preparedStatement.setInt(3, selectClassical(classical));
            preparedStatement.setInt(4, Integer.parseInt(schoolScore.getYear()));
            preparedStatement.setInt(5, Integer.parseInt(schoolScore.getLowScore()));
            preparedStatement.setInt(6, Integer.parseInt(schoolScore.getHigtScore()));
            preparedStatement.setDouble(7, Double.parseDouble(schoolScore.getAverageScore()));
            preparedStatement.setString(8, schoolScore.getBatch());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
