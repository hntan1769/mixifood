package mixifood;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel {

    //declare variables
    static ArrayList<User> sUserArrayList;
    ArrayList<User> result_list;
    ArrayList<User> paging_list;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;

    public ArrayList<User> getsUserList() {
        return sUserArrayList;
    }

    /**
     * connect to database
     */
    public void UserModel() {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * function load user from database
     *
     * @throws SQLException
     */
    public void LoadUser() throws SQLException {
        mConnection = DatabaseConnection.getMySQLConnection();
        mStatement = mConnection.createStatement();
        String query = "SELECT * FROM `user`";
        mResultSet = mStatement.executeQuery(query);
        sUserArrayList = new ArrayList<>();
        while (mResultSet.next()) {
            int user_id = mResultSet.getInt("User_id");
            String user_phone = mResultSet.getString("User_phone");
            String user_fullname = mResultSet.getString("User_fullname");
            String user_username = mResultSet.getString("User_username");
            String user_password = mResultSet.getString("User_password");
            String user_email = mResultSet.getString("User_email");
            int user_status = mResultSet.getInt("User_status");
            Boolean isAdmin = mResultSet.getBoolean("IsAdmin");
            sUserArrayList.add(new User(user_id, user_phone, user_fullname, user_username,
                    user_password, user_email, user_status, isAdmin));

        }
        result_list = new ArrayList<User>(sUserArrayList);
    }

    /**
     * function turn off user status
     *
     * @param User_id
     * @param index
     * @return
     */
    public boolean TurnOffUserStatus(int User_id, int index) {
        String update = "UPDATE `user` SET IsAdmin = ? WHERE User_id = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setInt(1, User_id);
            mPreparedStatement.setInt(2, 0);
            mPreparedStatement.executeUpdate();
            sUserArrayList.get(index).setIsAdmin(false);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * function add user to database
     *
     * @param User_phone
     * @param User_fullname
     * @param User_username
     * @param User_password
     * @param User_email
     * @param User_status
     * @return
     */
    public Boolean AddUser(String User_phone, String User_fullname, String User_username, String User_password, String User_email, int User_status) {
        String insert = "INSERT INTO `user`(`User_phone`, `User_fullname`, `User_username`, `User_password`, `User_email`, `User_status`, `IsAdmin`) VALUES (?,?,?,?,?,?,?)";
        int idUserIncreasement = 0;
        try {
            mPreparedStatement = mConnection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, User_phone);
            mPreparedStatement.setString(2, User_fullname);
            mPreparedStatement.setString(3, User_username);
            mPreparedStatement.setString(4, User_password);
            mPreparedStatement.setString(5, User_email);
            mPreparedStatement.setInt(6, User_status);
            mPreparedStatement.setBoolean(7, true);
            mPreparedStatement.executeUpdate();
            ResultSet rs = mPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idUserIncreasement = rs.getInt(1);
                sUserArrayList.add(new User(idUserIncreasement, User_phone, User_fullname,
                        User_username, User_password, User_email, User_status, Boolean.TRUE));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * function update user to database
     *
     * @param User_id
     * @param User_phone
     * @param User_fullname
     * @param User_username
     * @param User_password
     * @param User_email
     * @param User_status
     * @param IsAdmin
     * @return
     */
    public boolean UpdateUser(int User_id, String User_phone, String User_fullname, String User_username, String User_password, String User_email, int User_status, Boolean IsAdmin) {
        String update = "UPDATE `user` SET `User_phone` = ?, `User_fullname` = ?, `User_username` = ?, `User_password` = ?, `User_email` = ?, `User_status` = ?, `IsAdmin` = ? WHERE `User_id` = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setString(1, User_phone);
            mPreparedStatement.setString(2, User_fullname);
            mPreparedStatement.setString(3, User_username);
            mPreparedStatement.setString(4, User_password);
            mPreparedStatement.setString(5, User_email);
            mPreparedStatement.setInt(6, User_status);
            mPreparedStatement.setBoolean(7, IsAdmin);
            mPreparedStatement.executeUpdate();
            sUserArrayList.get(FindUserById(User_id)).UserCopy(User_id, User_phone, User_fullname, User_username, User_password, User_email, User_status, IsAdmin);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * function find user by name
     *
     * @param User_fullname
     * @return
     */
    public void search(String query) {
        if (!query.equals("")) {
            result_list = new ArrayList<User>();
            User user;
            query = query.toLowerCase();
            for (int i = 0; i < sUserArrayList.size(); i++) {
                user = sUserArrayList.get(i);
                if (user.getUser_fullname().contains(query)
                        || user.getUser_username().toLowerCase().contains(query) ) {

                    result_list.add(user);
                }
            }
        } else {
            result_list = new ArrayList<>(sUserArrayList);
        }
    }

    public ArrayList<User> getPage(int pageNum, int rowPerPage) {
        paging_list = new ArrayList<User>(
                result_list.subList((pageNum - 1) * rowPerPage,
                        Math.min(result_list.size(), pageNum * rowPerPage)));
        return paging_list;
    }

    public int getTotalPages(int rowPerPage) {
        return (int) Math.ceil(result_list.size() / (float) rowPerPage);
    }

    public ArrayList<User> FindUserByName(String User_fullname) {
        ArrayList<User> lstusertempt = new ArrayList<>();
        for (User user : sUserArrayList) {
            if (user.getUser_fullname().equalsIgnoreCase(User_fullname)) {
                lstusertempt.add(user);
            }
        }
        if (lstusertempt.isEmpty()) {
            return null;
        } else {
            return lstusertempt;
        }
    }

    /**
     * function find user by id
     *
     * @param User_id
     * @return
     */
    public int FindUserById(int User_id) {
        ArrayList<User> lstusertempt = new ArrayList<>();
        for (User user : sUserArrayList) {
            if (user.getUser_id() == User_id) {
                return sUserArrayList.indexOf(user);
            }
        }
        if (lstusertempt.isEmpty()) {
            return -1;
        }
        return -1;
    }

    /**
     * function MD5
     *
     * @param User_password
     */
    public String MD5(String User_password) {
        try {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(User_password.getBytes());
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
