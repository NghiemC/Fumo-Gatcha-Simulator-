import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLHandler {

    private Connection connection; // Represents the connection to the database

    // Constructor to establish a connection
    public SQLHandler(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            // Handle the exception appropriately (e.g., exit or retry)
        }
    }

    // Method to load Fumos from the database
    public ArrayList<Gacha> loadFumosFromDatabase() {
        ArrayList<Gacha> gachaPool = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Fumo"; // Assuming 'Fumo' is your table name
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("fumo_name"); 
                String rarity = rs.getString("rarity");
                gachaPool.add(new Gacha(name, rarity)); 
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error loading Fumos: " + e.getMessage());
        }
        return gachaPool;
    }

    // Method to add a new Fumo to the database
    public void addNewFumo(String name, String rarity) throws SQLException {
        String sql = "INSERT INTO Fumo (fumo_name, rarity) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, rarity);
        stmt.executeUpdate(); 
        stmt.close();
    }

    // Method to update an existing Fumo in the database
    public void updateFumo(int fumoId, String newName, String newRarity) throws SQLException {
        String sql = "UPDATE Fumo SET fumo_name = ?, rarity = ? WHERE fumo_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, newName);
        stmt.setString(2, newRarity);
        stmt.setInt(3, fumoId);
        stmt.executeUpdate(); 
        stmt.close();
    }
    
    // Close the connection when done
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close(); 
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}