package mixifood;

import java.util.ArrayList;

public class Location {

	private int Location_id;
	private String Location_name;
    
    public void Location(){
            
    }

    public Location(int Location_id, String Location_name) {
            this.Location_id = Location_id;
            this.Location_name = Location_name;
    }
       public void LocationCopy(int Location_id, String Location_name) {
            this.Location_id = Location_id;
            this.Location_name = Location_name;
    }

    public int getLocation_id() {
        return Location_id;
    }

    public void setLocation_id(int Location_id) {
        this.Location_id = Location_id;
    }

    public String getLocation_name() {
        return Location_name;
    }

    public void setLocation_name(String Location_name) {
        this.Location_name = Location_name;
    }

        
	

	/**
	 * 
	 * @param Dinner_id
	 */
	public ArrayList getDinner(int Dinner_id) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Post_id
	 */
	public ArrayList getPost(int Post_id) {
		throw new UnsupportedOperationException();
	}

}