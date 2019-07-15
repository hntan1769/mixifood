package mixifood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Food_img_Model {

    static ArrayList<Food_img> lstFoodImg;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;

    public static ArrayList<Food_img> getLstFoodImg() {
        return lstFoodImg;
    }

    public Food_img_Model() {
//        throw new UnsupportedOperationException();
    }

    public void LoadFoodImg() throws SQLException {
        String query = "SELECT * FROM `food_img`";
        mConnection = DatabaseConnection.getMySQLConnection();
        mStatement = mConnection.createStatement();
        mResultSet = mStatement.executeQuery(query);
        lstFoodImg = new ArrayList<>();
        while (mResultSet.next()) {
            int Food_img_id = mResultSet.getInt(1);
            int post_id = mResultSet.getInt(2);
            String Food_img_link = mResultSet.getString(3);
            lstFoodImg.add(new Food_img(Food_img_id, post_id, Food_img_link));
        }
    }

    /**
     *
     * @param post_id
     * @param Food_img_link
     */
    public Boolean UpdateFoodImg(int post_id, String Food_img_link, int foodImg_id) {
        String update = "UPDATE `food_img` SET `post_id`= ? ,`Food_img_link`=? WHERE `Food_img_id`=? ";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setInt(1, post_id);
            mPreparedStatement.setString(2, Food_img_link);
            mPreparedStatement.setInt(3, foodImg_id);
            mPreparedStatement.executeUpdate();
            lstFoodImg.get(foodImg_id).setFood_img_link(Food_img_link);
            lstFoodImg.get(foodImg_id).setPost_id(post_id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Food_img FindImgById(int foodImg_id) {
        for (Food_img food_img : lstFoodImg) {
            if (food_img.getFood_img_id() == foodImg_id) {  // check food img in list of Foodimg equal with foodImg_id ? return FoodImg : null
                return food_img;
            }
        }
        return null;
    }

    /**
     *
     * @param post_id
     * @param Food_img_link
     */
    public Boolean AddFoodImg(int post_id, String Food_img_link) {
        String insert = "INSERT INTO `food_img`( `post_id`, `Food_img_link`) VALUES (?,?)";
        try {
            mPreparedStatement = mConnection.prepareStatement(insert);
            mPreparedStatement.setInt(1, post_id);
            mPreparedStatement.setString(2, Food_img_link);
            mPreparedStatement.executeUpdate();
            lstFoodImg.add(new Food_img(lstFoodImg.size() + 1, post_id, Food_img_link));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
