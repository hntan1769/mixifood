package mixifood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;

public class CommentModel {
    
    //declare variables
    static ArrayList<Comment> sCommentArrayList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    public static ArrayList<Comment> getsCommentArrayList() {
        return sCommentArrayList;
    }
    
    /**
     * Connect database
     */
    public void CommentModel() {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * function load comment from database
     * @throws SQLException 
     */
    public void LoadComment() throws SQLException {
        mConnection = DatabaseConnection.getMySQLConnection();
        mStatement = mConnection.createStatement();
	String query = "SELECT * FROM `comment`";
        mResultSet = mStatement.executeQuery(query);
        sCommentArrayList = new ArrayList<>();
        while (mResultSet.next()) {
            int Comment_id = mResultSet.getInt("Comment_id");
            int post_id = mResultSet.getInt("post_id");
            String NickName = mResultSet.getString("NickName");
            String Comment_content = mResultSet.getString("Comment_content");
            Date Created_at  = mResultSet.getDate("Created_at");
            Boolean Comment_status  = mResultSet.getBoolean("Comment_status");
            sCommentArrayList.add(new Comment(Comment_id,post_id,NickName,Comment_content,Created_at,Comment_status));
        }
    }

	/**
         * function turn off comment status
         * @param Comment_id
         * @param index
         * @return 
         */
	public boolean TurnOffCommentStatus(int Comment_id,int index) {
            String update = "UPDATE `comment` SET `Comment_status` = ? WHERE `Comment_id` = ?";
            try {
                mPreparedStatement = mConnection.prepareStatement(update);
                mPreparedStatement.setInt(1, Comment_id);
                mPreparedStatement.setBoolean(2,false );
                mPreparedStatement.executeUpdate();
                sCommentArrayList.get(index).setComment_status(false);
                return true;
            } catch (SQLException e) {
                return false;
            }
	}

	/**
         * function add comment to database
         * @param post_id
         * @param NickName
         * @param Comment_content
         * @param Created_at
         * @return 
         */
	public boolean AddComment(int post_id, String NickName, String Comment_content, Date Created_at) {
            String insert = "INSERT INTO `comment`(`post_id`, `Comment_content`, `Created_at`, `NickName`) VALUES (?,?,?,?,?)";
            int idCommentIncreasement=0;
            try {
                mPreparedStatement = mConnection.prepareStatement(insert);
                mPreparedStatement.setInt(1, post_id);
                mPreparedStatement.setString(2, NickName);
                mPreparedStatement.setString(3, Comment_content);
                mPreparedStatement.setDate(4, (java.sql.Date) Created_at);
                mPreparedStatement.setBoolean(5, true);
                mPreparedStatement.executeUpdate();
                ResultSet rs=mPreparedStatement.getGeneratedKeys();
                if(rs.next()){
                    idCommentIncreasement=rs.getInt(1);
                    sCommentArrayList.add(new Comment(idCommentIncreasement,post_id,NickName,
                                Comment_content,Created_at,Boolean.TRUE));
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
	}

	/**
         * function update comment to database
         * @param Comment_id
         * @param post_id
         * @param NickName
         * @param Comment_content
         * @param Created_at
         * @param Comment_status
         * @return 
         */
	public boolean UpdateComment(int Comment_id, int post_id, String NickName, String Comment_content, Date Created_at, Boolean Comment_status) {
            String update = "UPDATE `comment` SET `post_id` = ?, `NickName` = ?, `Comment_content` = ?, `Created_at` = ?, `Comment_status` =? WHERE `Comment_id` = ?";
            try {
                mPreparedStatement = mConnection.prepareStatement(update);
                mPreparedStatement.setInt(1, post_id);
                mPreparedStatement.setString(2, NickName);
                mPreparedStatement.setString(3, Comment_content);
                mPreparedStatement.setDate(4, (java.sql.Date) Created_at);
                mPreparedStatement.setBoolean(5, Comment_status);
                mPreparedStatement.executeUpdate();
                sCommentArrayList.get(FindCommentById(Comment_id)).CommentCopy(Comment_id, post_id, NickName, Comment_content, Created_at, Comment_status);
                return true;
            } catch (SQLException e) {
                return false;
            }
	}

	/**
         * function find comment by nickname
         * @param NickName
         * @return 
         */
	public ArrayList<Comment> FindCommentByNickName(String NickName) {
            ArrayList<Comment> lstcmttempt =new ArrayList<>();
            for (Comment comment : sCommentArrayList) {
                if(comment.getNickName().equalsIgnoreCase(NickName)){
                    lstcmttempt.add(comment);
                }
            }
            if(lstcmttempt.isEmpty()){
                return null;
            }else{
                return lstcmttempt;
            }
	}
        
        /**
         * function find comment by id
         * @param Comment_id
         * @return 
         */
        public int FindCommentById(int Comment_id) {
            ArrayList<Comment> lstcommenttempt =new ArrayList<>();
            for (Comment cmt : sCommentArrayList) {
                if(cmt.getComment_id()==Comment_id){
                    return sCommentArrayList.indexOf(cmt);
                }
            }
            if(lstcommenttempt.isEmpty()){
                return -1;
            }
            return -1;
	}
        
}