package mixifood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class LocationModel {

    static ArrayList<Location> LocationList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;

    public static ArrayList<Location> getLocationList() {
        return LocationList;
    }

    public void LocationModel() {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    public void LoadLocation() throws SQLException {
        mConnection = DatabaseConnection.getMySQLConnection();
        mStatement = mConnection.createStatement();
        String query = "SELECT * FROM `location`";
        mResultSet = mStatement.executeQuery(query);
        LocationList = new ArrayList<>();
        while (mResultSet.next()) {
            int LID = mResultSet.getInt("Location_id");
            String LName = mResultSet.getString("Location_name");
            LocationList.add(new Location(LID, LName));
        }
    }

    /**
     *
     * @param Location_name
     */
    public Boolean AddLocation(String Location_name) throws SQLException {
        String add = "INSERT INTO Location values(?)";
        try {
            mPreparedStatement = mConnection.prepareStatement(add);
            mPreparedStatement.setString(1, Location_name);
            mPreparedStatement.executeUpdate();
            String query = "SELECT * FROM Location ORDER BY Location_id DESC OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            LocationList.add(new Location(mResultSet.getInt("Location_id"), mResultSet.getString("Location_name")));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param Location_name
     */
    public boolean UpdateLocation(int Location_id, String Location_name) {
        String update = "UPDATE `location` SET `Location_name`=? WHERE `Location_id` = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setInt(2, Location_id);
            mPreparedStatement.setString(1, Location_name);
            mPreparedStatement.executeUpdate();
           LocationList.get(Location_id).LocationCopy(Location_id, Location_name);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    ArrayList<Location> getLocationList(String LName) {
        ArrayList<Location> lct = new ArrayList<>();
        for (Location location : LocationList) {
            if (location.getLocation_name() == LName) {
                lct.add(location);
            }
        }
        return lct;
    }

    /**
     *
     * @param Location_name
     */
    public Location FindLocationByName(String Location_name) {
        for (Location lct : LocationList) {
            if (lct.getLocation_name().equalsIgnoreCase(Location_name)) {
                return lct;
            }
        }
        return null;
    }
}
