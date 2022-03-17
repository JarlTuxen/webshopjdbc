import java.sql.*;

public class JdbcTest {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/webshop";
    private final static String UID = "root";
    private final static String PWD = "qJiw03K2zwJD";

    private static Connection connection;

    public static void main(String[] args) {
        getAll();
        insertRow();
        getAll();
    }

    public static void getAll(){
        //create connection
        Connection con = getConnection();
        try{

            Statement s = con.createStatement();
            final String SQL_QUERY = "SELECT * FROM product"; //" WHERE id = 1 OR 1=1; --";
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

    public static void insertRow(){
        final String INSERT_SQL ="INSERT INTO product(name, price) VALUES (?, ?)";
        connection = getConnection();
        try {
            PreparedStatement psInsertProduct = connection.prepareStatement(INSERT_SQL);
            psInsertProduct.setString(1, "Slikkepind");
            psInsertProduct.setInt(2, 10);
            psInsertProduct.executeUpdate();
            System.out.println("Product inserted");
        }
        catch (SQLException e){
            System.out.println("Could not insert row");
            e.printStackTrace();
        }

    }
    
    public static Connection getConnection(){
        //connection er en singleton
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
