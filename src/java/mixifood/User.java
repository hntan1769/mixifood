package mixifood;

public class User implements Comparable{

    //declare variables
    private int User_id;
    private String User_phone;
    private String User_fullname;
    private String User_username;
    private String User_password;
    private String User_email;
    private int User_status;
    private Boolean IsAdmin;

    //output
    @Override
    public String toString() {
        String str = "";
        str += User_id + " | " + User_phone + " | " + User_fullname + " | " + User_username + " | " + User_email + " | " + User_status + " | " + IsAdmin;        
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        User o = (User)obj;
        return this.User_username == o.getUser_username();
    }

    @Override
    public int compareTo(Object obj) {
        User o = (User)obj;
        int result = this.User_fullname.compareToIgnoreCase(o.getUser_fullname());
        if (result == 0) {
            result = this.User_phone.compareToIgnoreCase(o.getUser_phone());
        }
        if (result == 0) {
            result = this.User_email.compareToIgnoreCase(o.getUser_email());
        }
        return result;
    }   
    
    //constructor
    public User(int User_id, String User_phone, String User_fullname, String User_username, String User_password, String User_email, int User_status, Boolean IsAdmin) {
        this.User_id = User_id;
        this.User_phone = User_phone;
        this.User_fullname = User_fullname;
        this.User_username = User_username;
        this.User_password = User_password;
        this.User_email = User_email;
        this.User_status = User_status;
        this.IsAdmin = IsAdmin;
    }
    
    public void UserCopy(int User_id, String User_phone, String User_fullname, String User_username, String User_password, String User_email, int User_status, Boolean IsAdmin) {
        this.User_id = User_id;
        this.User_phone = User_phone;
        this.User_fullname = User_fullname;
        this.User_username = User_username;
        this.User_password = User_password;
        this.User_email = User_email;
        this.User_status = User_status;
        this.IsAdmin = IsAdmin;
    }

    //getter setter
    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getUser_phone() {
        return User_phone;
    }

    public void setUser_phone(String User_phone) {
        this.User_phone = User_phone;
    }

    public String getUser_fullname() {
        return User_fullname;
    }

    public void setUser_fullname(String User_fullname) {
        this.User_fullname = User_fullname;
    }

    public String getUser_username() {
        return User_username;
    }

    public void setUser_username(String User_username) {
        this.User_username = User_username;
    }

    public String getUser_password() {
        return User_password;
    }

    public void setUser_password(String User_password) {
        this.User_password = User_password;
    }

    public String getUser_email() {
        return User_email;
    }

    public void setUser_email(String User_email) {
        this.User_email = User_email;
    }

    public int getUser_status() {
        return User_status;
    }

    public void setUser_status(int User_status) {
        this.User_status = User_status;
    }

    public Boolean getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(Boolean IsAdmin) {
        this.IsAdmin = IsAdmin;
    }

}