import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SocialConnect {


    private Graph<Profile> graph;

    /**
     * constructor - initializes the social networking app.
     */
    public SocialConnect() {
        graph = new Graph<>();
    }

    /**
     * Adds a new user to the social network
     *
     * @param P profile to add
     */
    public void addUser(Profile P) {
        graph.addVertex(P);
    }

    /**
     * Removes a new user to the social network
     *
     * @param P profile to remove
     */
    public Profile removeUser(Profile P) {
        if(graph.removeVertex(P)==null){
            return null;
        }else{
            return P;
        }
    }

    /**
     * Creates a friendship between two users on MasonConnect. If the friendship is
     * created successfully, it returns true, false otherwise.
     *
     * @param a a profile
     * @param b another profile
     * @return true if the friendship is
     *   created successfully, false otherwise.
     */
    public boolean createFriendship(Profile a, Profile b) {
        boolean successful=graph.addEdge(a, b);
        if(successful){
            a.addFriend(b);
            b.addFriend(a);
        }
        return successful;
    }

    /**
     * removes a friendship between two users on MasonConnect. If the friendship is
     * discontinued successfully, it returns true, false otherwise.
     *
     * @param a a profile
     * @param b another profile
     * @return true if the friendship is
     *   created successfully, false otherwise.
     */
    public boolean removeFriendship(Profile a, Profile b) {
        boolean successful=graph.removeEdge(a, b);
        if(successful){
            a.unFriend(b);
            b.unFriend(a);
        }
        return successful;

    }

    /**
     * Returns true if there is friendship between Profiles a and b, false
     * otherwise.
     *
     * @param a a profile
     * @param b another profile
     * @return true if there is a friendship between them, false otherwise.
     */
    public boolean hasFriendship(Profile a, Profile b) {
        return graph.hasEdge(a, b);

    }

    /**
     * this method displays each profile's information and friends, starting from
     * the startPoint profile. See the sample run on the format of the display.
     *
     * @param startPoint source vertex
     */
    public void traverse(Profile startPoint) {
        for (Profile profile : graph.getBreadthFirstTraversal(startPoint)) {
            profile.display();
        }
    }

    /**
     * this returns true if a user with the given profile exists in MasonConnect,
     * false otherwise.
     *
     * @param user userProfile
     * @return true if exist, false otherwise.
     */
    public boolean exists(Profile user) {
        //if adding a user is unsuccessful that mean we already have it
        boolean successful = graph.addVertex(user);
        if (!successful) {
            return true;
        } else {
            graph.removeVertex(user);
            return false;
        }
    }

    /**
     * Returns a list of Profiles, who are friends with one or more of the profile's
     * friends (but not currently the profile's friend). It returns null, if the
     * user does not exist or if it does not have any friend suggestions. Take a
     * look at the sample run for an example.
     *
     * @param user user
     * @return null, if the user does not exist or if it does not have any friend suggestions,
     *      else a list of profile.
     */
    public List<Profile> friendSuggestion(Profile user) {

        //if the profile exist and profile has some friend
        if (exists(user) && !user.getFriendProfiles().isEmpty()) {

            //then iterate over all its friends of friends and create a set of friends for suggestion
            List<Profile> suggestionProfiles = new ArrayList<>();
            for (Profile friendProfile : user.getFriendProfiles()) {
                for (Profile profile : friendProfile.getFriendProfiles()) {
                    //don't add itself  and  add a profile only once and don't  add  profile who are already friend
                    if (!profile.equals(user) && !suggestionProfiles.contains(profile) && !user.getFriendProfiles().contains(profile)) {
                        suggestionProfiles.add(profile);
                    }
                }
            }

            return suggestionProfiles;
        }

        return null;

    }

    /**
     * Returns the friendship
     * distance between two profiles. A friendship distance is simply how many
     * profiles away the two profiles are. For example, if a and b are friends
     * their friendship distance is 1. If they have a common friend but they
     * are not friends, their friendship distance is 2. If either of the
     * profiles are not in the social networking app, the method returns -1.
     *
     * @param a a profile
     * @param b another profile
     * @return -1 If either of the profiles are not in the social networking app, else distance between them.
     */
    public int friendshipDistance(Profile a, Profile b) {
        //both vertex exist
        if(exists(a) && exists(b)){

            //get path and distance
            Stack<Profile> path=new Stack<>();
            int result=graph.getShortestPath(a,b,path);

            // if distance is infinity return -1
            if(result==Integer.MAX_VALUE)result=-1;
            return result;
        }
        return -1;
    }
}
