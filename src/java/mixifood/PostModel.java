package mixifood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostModel {

    static ArrayList<Post> PostList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    
    ArrayList<Post> result_list;
    ArrayList<Post> paging_list;

    public void PostModel() {
        // throw new UnsupportedOperationException();
    }

    public ArrayList<Post> getPostList() {
        return PostList;
    }

    public void LoadPost() throws SQLException {
        String query = "SELECT * FROM `post`";
        mConnection = DatabaseConnection.getMySQLConnection();
        mStatement = mConnection.createStatement();
        mResultSet = mStatement.executeQuery(query);
        PostList = new ArrayList<>();
        while (mResultSet.next()) {
            int post_id = mResultSet.getInt(1);
            int User_id = mResultSet.getInt(2);
            int Admin_id = mResultSet.getInt(3);
            int Diner_id = mResultSet.getInt(4);
            String Title = mResultSet.getString(5);
            String post_content = mResultSet.getString(6);
            String Food_name = mResultSet.getString(7);
            long Price = mResultSet.getInt(8);
            int Status = mResultSet.getInt(9);
            float Rating = mResultSet.getFloat(10);
            long Rating_count = mResultSet.getInt(11);
            PostList.add(new Post(post_id, User_id, Admin_id, Diner_id, Title, post_content, Food_name, Price, Status, Rating, Rating_count));
        }
    }

    /**
     *
     * @param User_id
     * @param Admin_id
     * @param Diner_id
     * @param Title
     * @param post_content
     * @param Food_name
     * @param Price
     * @param Status
     * @param Rating
     * @param Rating_count
     */
    public boolean AddPost(int User_id, int Admin_id, int Diner_id, String Title, String post_content, String Food_name, long Price, int Status) {
        String insert = "INSERT INTO `post`( , `User_id`, `Admin_id`, `Diner_id`, `Title`, `post_content`, `Food_name`, `Price`, `Status`, `Rating`, `Rating_count`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {

            mPreparedStatement = mConnection.prepareStatement(insert);
            mPreparedStatement.setInt(1, User_id);
            mPreparedStatement.setInt(2, Admin_id);
            mPreparedStatement.setInt(3, Diner_id);
            mPreparedStatement.setString(4, Title);
            mPreparedStatement.setString(5, post_content);
            mPreparedStatement.setString(6, Food_name);
            mPreparedStatement.setLong(7, Price);
            mPreparedStatement.setInt(8, Status);
            mPreparedStatement.setFloat(9, 0);
            mPreparedStatement.setLong(10, 0);
            mPreparedStatement.executeUpdate();
            PostList.add(new Post(PostList.size() + 1, User_id, Admin_id, Diner_id, Title, post_content, Food_name, Price, Status, 0, 0));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param User_id
     * @param Admin_id
     * @param Diner_id
     * @param Title
     * @param post_content
     * @param Food_name
     * @param Price
     * @param Status
     * @param Rating
     * @param Rating_count
     */
    public boolean UpdatePost(int post_id, int User_id, int Admin_id, int Diner_id, String Title, String post_content, String Food_name, long Price, int Status, float Rating, long Rating_count) {
        String update = "UPDATE `post` SET `User_id`=?,`Admin_id`=?,`Diner_id`=?,`Title`=?,`post_content`=?,`Food_name`=?,`Price`=?,`Status`=?,`Rating`=?,`Rating_count`=? WHERE `post_id`=?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setInt(1, User_id);
            mPreparedStatement.setInt(2, Admin_id);
            mPreparedStatement.setInt(3, Diner_id);
            mPreparedStatement.setString(4, Title);
            mPreparedStatement.setString(5, post_content);
            mPreparedStatement.setString(6, Food_name);
            mPreparedStatement.setLong(7, Price);
            mPreparedStatement.setInt(8, Status);
            mPreparedStatement.setFloat(9, Rating);
            mPreparedStatement.setLong(10, Rating_count);
            mPreparedStatement.setInt(11, post_id);
            mPreparedStatement.executeUpdate();
            PostList.get(PostList.indexOf(FIndPostByPostID(post_id))).PostCopy(post_id, User_id, Admin_id, Diner_id, Title, post_content, Food_name, Price, Status, Rating, Rating_count);
                       return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean ValidationPost(int post_id, int stt) {
        String update = "UPDATE `post` SET `Status`=? WHERE ?";  //create query statement
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setInt(1, stt);
            mPreparedStatement.setInt(2, post_id);
            mPreparedStatement.executeUpdate();
            PostList.get(post_id).setStatus(stt);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * find post by dinerName
     *
     * @param Diner_name
     */
    public Post FindPostByDinnerName(int Diner_id) {
        for (Post post : PostList) {
            if (post.getDiner_id() == Diner_id) { // check condition
                return post;
            }
        }
        return null;
    }

    /**
     * find post by food name
     *
     * @param Food_name
     */
    public Post FIndPostByFoodName(String Food_name) {
        for (Post post : PostList) {
            if (post.getFood_name().equalsIgnoreCase(Food_name)) { // check condition
                return post;
            }
        }
        return null;
    }

    /**
     * find post by post ID
     *
     */
    
    public Post FIndPostByPostID(int post_id) {
        for (Post post : PostList) {
            if (post.getPost_id() == post_id) { // check condition
                return post;
            }
        }
        return null;
    }

    /**
     * find post of each reviewer by reviewer_id
     *
     */
    public Post FindPostByUserID(int User_id) {
        for (Post post : PostList) {
            if (post.getUser_id() == User_id) {
                return post;
            }
        }
        return null;
    }

    public void search(String query) {
        if (!query.equals("")) {
            result_list = new ArrayList<Post>();
            Post acc;
            query = query.toLowerCase();
            for (int i = 0; i < PostList.size(); i++) {
                acc = PostList.get(i);
                if (acc.getTitle().toLowerCase().contains(query) || 
                    acc.getFood_name().toLowerCase().contains(query))  {
                    result_list.add(acc);
                }
            }
        } else {
            result_list = new ArrayList<Post>(PostList);
        }
    }
    
    public ArrayList<Post> getPage(int pageNum, int rowPerPage) {
        paging_list = new ArrayList<Post>(
                result_list.subList((pageNum-1)*rowPerPage, 
                                    Math.min(result_list.size(), pageNum*rowPerPage)));
        return paging_list;
    }
    
    public int getTotalPages(int rowPerPage) {
        return (int)Math.ceil(result_list.size() / (float)rowPerPage);
    }
}
