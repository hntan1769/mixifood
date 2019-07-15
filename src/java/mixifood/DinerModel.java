package mixifood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;


public class DinerModel {

    //declare variables
    static ArrayList<Diner> sDinerArrayList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;

    public static ArrayList<Diner> getsDinerArrayList() {
        return sDinerArrayList;
    }
    
    /**
     * connect to database
     */
    public void DinerModel() {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * function load diner from database
     * @throws SQLException 
     */
    public void LoadDiner() throws SQLException {
        mConnection = DatabaseConnection.getMySQLConnection();
        mStatement = mConnection.createStatement();
        String query = "SELECT * FROM `Diner`";
        mResultSet = mStatement.executeQuery(query);
        sDinerArrayList = new ArrayList<>();
        while (mResultSet.next()) {
            int LID = mResultSet.getInt("Location_id");
            int DID = mResultSet.getInt("Diner_id");
            String DName = mResultSet.getString("Diner_name");
            String Address = mResultSet.getString("Address");
            String DPhone = mResultSet.getString("Diner_phone");
            Time time_open = mResultSet.getTime("Time_open");
            Time time_close = mResultSet.getTime("Time_close");
            Boolean DStatus = mResultSet.getBoolean("Diner_status");
            sDinerArrayList.add(new Diner(DID, LID, DName, Address, DPhone, time_open, time_close, DStatus));
        }
    }
    
    /**
     * function add diner to database
     * @param Location_id
     * @param Diner_name
     * @param Address
     * @param Diner_phone
     * @param Time_open
     * @param Time_close
     * @param Diner_status
     * @return 
     */
    public boolean AddDiner(int Location_id, String Diner_name, String Address, String Diner_phone, Time Time_open, Time Time_close, Boolean Diner_status) {
        String add = "IINSERT INTO `diner`(`Location_id`, `Diner_name`, `Address`, `Diner_phone`, `Time_open`, `Time_close`, `Diner_status`) VALUES (?,?,?,?,?,?,?)";
        int idDinerIncreasement=0;
        try {
            mPreparedStatement = mConnection.prepareStatement(add);
            mPreparedStatement.setInt(1, Location_id);
            mPreparedStatement.setString(2, Diner_name);
            mPreparedStatement.setString(3, Address);
            mPreparedStatement.setString(4, Diner_phone);
            mPreparedStatement.setTime(5, Time_open);
            mPreparedStatement.setTime(6, Time_close);
            mPreparedStatement.setBoolean(7, true);
            mPreparedStatement.executeUpdate();
            ResultSet rs=mPreparedStatement.getGeneratedKeys();
                if(rs.next()){
                    idDinerIncreasement=rs.getInt(1);
                    sDinerArrayList.add(new Diner(Location_id,idDinerIncreasement,Diner_name,
                                Address,Diner_name,Time_open,Time_close,Boolean.TRUE));
                }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * function update diner to database
     * @param Location_id
     * @param Diner_id
     * @param Diner_name
     * @param Address
     * @param Diner_phone
     * @param Time_open
     * @param Time_close
     * @param Diner_status
     * @return 
     */
    public boolean UpdateDiner(int Location_id, int Diner_id, String Diner_name, String Address, String Diner_phone, Time Time_open, Time Time_close, Boolean Diner_status) {
        String update = "UPDATE `Diner` SET `Location_id` = ?, `Diner_name` = ?, `Address` = ?, `Diner_phone` = ?, `Time_open` = ?, `Time_close` = ?, `Diner_status` = ? WHERE `Diner_id` = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setInt(1, Location_id);
            mPreparedStatement.setString(2, Diner_name);
            mPreparedStatement.setString(3, Address);
            mPreparedStatement.setString(4, Diner_phone);
            mPreparedStatement.setTime(5, Time_open);
            mPreparedStatement.setTime(6, Time_close);
            mPreparedStatement.setBoolean(7, Diner_status);
            mPreparedStatement.executeUpdate();
            sDinerArrayList.get(FindDinerById(Diner_id)).DinerCopy(Diner_id, Location_id, Diner_name, Address, Diner_phone, Time_open, Time_close, Diner_status);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * function get diner from database
     * @param DID
     * @return 
     */
    Diner getDiner(int DID) {
        String query = "SELECT * FROM `Diner` WHERE `Diner_id` = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(query);
            mPreparedStatement.setInt(1, DID);
            mResultSet = mPreparedStatement.executeQuery();
            mResultSet.next();
            return new Diner(mResultSet.getInt("Location_id"), mResultSet.getInt("Diner_id"), mResultSet.getString("Diner_name"), mResultSet.getString("Address"), mResultSet.getString("Diner_phone"), mResultSet.getTime("Time_open"), mResultSet.getTime("Time_close"), mResultSet.getBoolean("Diner_status"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * function find diner by name
     * @param Diner_name
     */
    public Diner FindDinerByName(String Diner_name) {
        for (Diner diner : sDinerArrayList) {
            if (diner.getDiner_name().equalsIgnoreCase(Diner_name)) {
                return diner;
            }
        }
        return null;
    }
    
    /**
     * function find diner by id
     * @param Diner_id
     * @return 
     */
    public int FindDinerById(int Diner_id) {
            ArrayList<Diner> lstdinertempt =new ArrayList<>();
            for (Diner diner : sDinerArrayList) {
                if(diner.getDiner_id()==Diner_id){
                    return sDinerArrayList.indexOf(diner);
                }
            }
            if(lstdinertempt.isEmpty()){
                return -1;
            }
            return -1;
	}

}