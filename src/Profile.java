import java.util.ArrayList;

public class Profile implements Comparable<Profile>{

	//this static counter will be used while generate unique ID
	private static int counter=0;

	//every profile will have a unique id
	private int ID;
	// a String value that represents the full name of the user.
	private String name;
	// a String that the user uses to specify their status.
	private String status;
	// an arraylist of profiles that stores friends of the user
	private ArrayList<Profile> friendProfiles;

	/**
	 * constructor - initializes all the String attributes to empty strings and a
	 * default arraylist.
	 */
	public Profile() {
		//from every profile object initialization counter will increment by one
		counter++;
		//and this counter number will be our unique ID
		ID=counter;

		//initializing rest of the attributes
		name="";
		status="";
		friendProfiles=new ArrayList<>();
	}

	/**
	 * initializes the attributes with the accepted valued.
	 */
	public Profile(String name, String status, ArrayList<Profile> friendProfiles) {
		this();
		this.name=name;
		this.status=status;
		this.friendProfiles=friendProfiles;
	}

	/**
	 * constructor - initializes the attributes with the accepted valued and the
	 * last attribute with a default arraylist object.
	 */
	public Profile(String name, String status) {
		this();
		this.name=name;
		this.status=status;
	}

	/**
	 * the setter method for the name attribute that accepts the first and last name
	 * of the user and set the name attribute with firstName +” “ +lastName (Note
	 * the space between the two names).
	 */
	public void setName(String firstName, String lastName) {
		name=firstName +" "+lastName;
	}

	/**
	 * the getter method for the name attribute.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for status
	 * @param status status
	 */
	public void setStatus(String status) {
		this.status=status;
	}

	/**
	 * Getter for status
	 * @return status
	 */
	public String getStatus() {
		return status;

	}

	/**
	 * returns the a string that represents the profile of the user. It displays the
	 * string in the following format. "Name: " + name + "\n\tStatus: " + status +
	 * \n\tNumber of friend profiles: " + friend’s number + "\n"
	 * 
	 */
	public String toString() {
		return "Name: " + name + "\n\tStatus: " + status +
	  "\n\tNumber of friend profiles: " + friendProfiles.size() + "\n";
	}

	/**
	 * displays the profile and the friends profiles. Take a look at the sample run
	 * to see the format of display.
	 */
	public void display() {

		//printing everything as it is in the example run

		System.out.println("Name: " + name + "\n\tStatus: " + status +
				"\n\tNumber of friend profiles: " + friendProfiles.size());
		System.out.println("Friends:");
		for (Profile friendProfile : friendProfiles) {
			System.out.println("\t"+friendProfile.getName());
		}
		System.out.println();
	}

	/**
	 * Getter for friendProfiles
	 */
	public ArrayList<Profile> getFriendProfiles() {
		return friendProfiles;
	}

	/**
	 * add a new friend to the friends list
	 */
	public void addFriend(Profile user) {
		friendProfiles.add(user);
	}

	/**
	 * removes an existing friend from the list of friends. returns true if the
	 * removal of the profile is successful, false otherwise.
	 */
	public boolean unFriend(Profile user) {
		//we itarate over all the friends and find the profile who we want to unfriend
		for (Profile friendProfile : friendProfiles) {

			if(friendProfile.getName().equals(user.getName())){
				//when found we'll remove him from friend list
				friendProfiles.remove(friendProfile);
				return true;
			}
		}

		return false;
	}

	@Override
	public int compareTo(Profile o) {
		return Integer.compare(ID,o.ID);
	}
}
