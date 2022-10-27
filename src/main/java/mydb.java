import java.sql.*;

/*
 * Usage:
 * Connection con = MyDB.getConnection();
 *
 * Optional, but recommended: Call the close function when you no longer need to work with the database
 * MyDB.close()
 */
public class mydb {
    public static Statement stmt;			//声明向数据库发送SQL语句的statement对象
    public static ResultSet rs;			//声明结果集，接受查询结果返回的对象

    private static final String MYSQL_USERNAME = "Herry";
    private static final String MYSQL_PASSWORD = "000000";
    private static final String MYSQL_DATABASE_SERVER = "localhost:3306";
    private static final String MYSQL_DATABASE_NAME = "airplan?serverTimezone=UTC";

    private static Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/" + MYSQL_DATABASE_NAME;
            con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("student: Update the MySQL constants to correct values!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("student: Add the MySQL jar file to your build path!");
        }
    }

    public static Connection getConnection() {
        return con;
    }
    public  Statement getstmt() throws SQLException {
        stmt = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        return stmt;
    }
    public  ResultSet getrs() throws SQLException {
        return rs;
    }
    public void display()
    {
        try{
            rs = stmt.executeQuery("select * from rentcar");	//Executes the given SQL statement
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
