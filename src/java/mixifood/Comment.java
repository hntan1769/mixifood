package mixifood;

import java.util.Date;

public class Comment {

    //declare variables
    private int Comment_id;
    private int post_id;
    private String NickName;
    private String Comment_content;
    private Date Created_at;
    private Boolean Comment_status;

    //output
    @Override
    public String toString() {
        String str = "";
        str += Comment_id + " | " + post_id + " | " + NickName + " | " + Comment_content + " | " + Created_at + " | " + Comment_status;        
        return str;
    }

    //constructor
    public Comment(int Comment_id, int post_id, String NickName, String Comment_content, Date Created_at, Boolean Comment_status) {
        this.Comment_id = Comment_id;
        this.post_id = post_id;
        this.NickName = NickName;
        this.Comment_content = Comment_content;
        this.Created_at = Created_at;
        this.Comment_status = Comment_status;
    }
    
    public void CommentCopy(int Comment_id, int post_id, String NickName, String Comment_content, Date Created_at, Boolean Comment_status) {
        this.Comment_id = Comment_id;
        this.post_id = post_id;
        this.NickName = NickName;
        this.Comment_content = Comment_content;
        this.Created_at = Created_at;
        this.Comment_status = Comment_status;
    }

    //getter setter
    public int getComment_id() {
        return Comment_id;
    }

    public void setComment_id(int Comment_id) {
        this.Comment_id = Comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getComment_content() {
        return Comment_content;
    }

    public void setComment_content(String Comment_content) {
        this.Comment_content = Comment_content;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date Created_at) {
        this.Created_at = Created_at;
    }

    public Boolean getComment_status() {
        return Comment_status;
    }

    public void setComment_status(Boolean Comment_status) {
        this.Comment_status = Comment_status;
    }

    
}