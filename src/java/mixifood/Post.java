package mixifood;

public class Post {

	private int post_id;
	private int User_id;
	private int Admin_id;
	private int Diner_id;
	private String Title;
	private String post_content;
	private String Food_name;
	private long Price;
	private int Status;
	private float Rating;
	private long Rating_count;

 

    public Post(){
        
    }
        
    public Post(int post_id, int User_id, int Admin_id, int Diner_id, String Title, String post_content, String Food_name, long Price, int Status, float Rating, long Rating_count) {
        this.post_id = post_id;
        this.User_id = User_id;
        this.Admin_id = Admin_id;
        this.Diner_id = Diner_id;
        this.Title = Title;
        this.post_content = post_content;
        this.Food_name = Food_name;
        this.Price = Price;
        this.Status = Status;
        this.Rating = Rating;
        this.Rating_count = Rating_count;
    }
      public void PostCopy(int post_id, int User_id, int Admin_id, int Diner_id, String Title, String post_content, String Food_name, long Price, int Status, float Rating, long Rating_count) {
        this.post_id = post_id;
        this.User_id = User_id;
        this.Admin_id = Admin_id;
        this.Diner_id = Diner_id;
        this.Title = Title;
        this.post_content = post_content;
        this.Food_name = Food_name;
        this.Price = Price;
        this.Status = Status;
        this.Rating = Rating;
        this.Rating_count = Rating_count;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public int getAdmin_id() {
        return Admin_id;
    }

    public void setAdmin_id(int Admin_id) {
        this.Admin_id = Admin_id;
    }

    public int getDiner_id() {
        return Diner_id;
    }

    public void setDiner_id(int Diner_id) {
        this.Diner_id = Diner_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getFood_name() {
        return Food_name;
    }

    public void setFood_name(String Food_name) {
        this.Food_name = Food_name;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long Price) {
        this.Price = Price;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    public long getRating_count() {
        return Rating_count;
    }

    public void setRating_count(long Rating_count) {
        this.Rating_count = Rating_count;
    }

    

}