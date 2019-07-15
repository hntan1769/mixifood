package mixifood;

public class Food_img {

    private int Food_img_id;
    private int post_id;
    private String Food_img_link;

    public void Food_img(){
        
    }
        
    public Food_img(int Food_img_id, int post_id, String Food_img_link) {
        this.Food_img_id = Food_img_id;
        this.post_id = post_id;
        this.Food_img_link = Food_img_link;
    }

    public Food_img() {
    }

    public int getFood_img_id() {
        return Food_img_id;
    }

    public void setFood_img_id(int Food_img_id) {
        this.Food_img_id = Food_img_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getFood_img_link() {
        return Food_img_link;
    }

    public void setFood_img_link(String Food_img_link) {
        this.Food_img_link = Food_img_link;
    }

    

}