
public class Driver
{
	public static void main(String[] args)
	{
		System.out.println("Creating profiles and the network.");
		SocialConnect m = new SocialConnect();

		Profile malcom = new Profile();
		malcom.setName("Malcom", "X");
		malcom.setStatus("My name is Malcom.");

		Profile fannie = new Profile();
		fannie.setName("Fannie-lou ", "Hamer");
		fannie.setStatus("My name is Fannie.");

		Profile brown = new Profile();
		brown.setName("John", "Brown");
		brown.setStatus("My name is John Brown!");
		
		Profile lewis = new Profile();
		lewis.setName("John", "Lewis");
		lewis.setStatus("My name is also John.");

		m.addUser(malcom);
		m.addUser(fannie);
		m.addUser(brown);
		m.addUser(lewis);
      
		malcom.display();
		fannie.display();
        brown.display();
        lewis.display();

		System.out.println("-------------------------------------\n");
		System.out.println("Creating friendships.\n");

		m.createFriendship(malcom, fannie);
		m.createFriendship(fannie, brown);
		m.createFriendship(lewis, fannie);
		m.createFriendship(brown, lewis);
     

		m.traverse(malcom);

		System.out.println("-------------------------------------\n");
		System.out.println("Changing statuses / names.\n");
		
		lewis.setStatus("Just got married!");
		fannie.setStatus("Now Mrs. Smith!");
		fannie.setName("Fannie", "Smith");

		fannie.display();
        lewis.display();
       
        System.out.println("-------------------------------------\n");
		System.out.println("Checking Friendships .\n");
		
		Profile friendless1 = new Profile();
		friendless1.setName("Nameless ", " 1");
		friendless1.setStatus("My name is nameless1!");
		System.out.println(m.exists(friendless1));
		m.addUser(friendless1);
		System.out.println(m.exists(brown));
		System.out.println(m.hasFriendship(friendless1, fannie));
		System.out.println(m.hasFriendship(fannie, brown));
		System.out.println(m.hasFriendship(malcom, lewis));
		System.out.println(m.hasFriendship(friendless1, fannie));
		System.out.println(m.hasFriendship(brown, lewis));
		System.out.println(m.exists(friendless1));
		System.out.println(m.hasFriendship(lewis, fannie));
		
		System.out.println("---------------Suggestion----------------------------------");
		Profile friendless2 = new Profile();
		friendless2.setName("Nameless ", " 2");
		friendless2.setStatus("My name is nameless2!");
		m.addUser(friendless2);
		m.createFriendship(friendless1, friendless2);
		m.createFriendship(friendless2, lewis);
		System.out.println(m.friendSuggestion(friendless2));
		System.out.println(m.friendSuggestion(brown));
		
		System.out.println(m.friendSuggestion(lewis));
		
		System.out.println("---------------Distance----------------------------------");
		System.out.println(m.friendshipDistance(fannie,brown));
		//System.out.println(m.exists(brown));
		System.out.println(m.friendshipDistance(fannie,friendless1));
		System.out.println(m.friendshipDistance(fannie,lewis));
		System.out.println(m.friendshipDistance(malcom,friendless2));
	  	m.removeUser(friendless2);
	  	m.removeFriendship( fannie, brown);
		System.out.println(m.friendshipDistance(malcom,friendless1));
		System.out.println(m.friendshipDistance(malcom,friendless2));
		System.out.println(m.friendshipDistance(fannie,brown));
		
		
	}
}


