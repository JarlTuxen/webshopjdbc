import java.sql.*;

public class JdbcTest {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/webshop";
    private final static String UID = "root";
    private final static String PWD = "qJiw03K2zwJD";

    private static Connection connection;

    public static void main(String[] args) {
        //create connection
        Connection con = getConnection();
        try{

            Statement s = con.createStatement();
            final String SQL_QUERY = "SELECT * FROM product";
            ResultSet rs = s.executeQuery(SQL_QUERY);

            //read data from resultset
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                System.out.println(id + " " + name + " " + price);
            }

            s.close();


        }
        catch (SQLException e){
            System.out.println("Could not create connection");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        //connection already initialized?
        if (connection!=null) return connection;

        //initialize connection
        try{
            connection = DriverManager.getConnection(DB_URL, UID, PWD);

        }
        catch (SQLException e){
            System.out.println("Could not connect");
            e.printStackTrace();
        }
        return connection;
        
    }
}
