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
        deleteRow();
        getAll();
        updateRow();
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

    public static void deleteRow(){
        final String DELETE_QUERY = "DELETE FROM product WHERE id=?";

        Connection con = getConnection();

        //id på den der skal slettes
        int id = 4;

        try{
            PreparedStatement psDeleteRow = connection.prepareStatement(DELETE_QUERY);
            psDeleteRow.setInt(1, id);
            psDeleteRow.executeUpdate();
            System.out.println("Row deleted");
        }
        catch (SQLException e){
            System.out.println("Could not delete");
            e.printStackTrace();
        }
    }

    public static void updateRow(){
        final String UPDATE_QUERY = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        int id = 3;
        String name = "Kiks";
        int price = 11;

        Connection con = getConnection();
        try{
            PreparedStatement psUpdateRow = con.prepareStatement(UPDATE_QUERY);
            psUpdateRow.setString(1, name);
            psUpdateRow.setInt(2, price);
            psUpdateRow.setInt(3, id);
            psUpdateRow.executeUpdate();
            System.out.println("Row updated");
        }
        catch(SQLException e){
            System.out.println("Could not update");
            e.printStackTrace();
        }

    }
}
