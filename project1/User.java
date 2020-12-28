package project1;


public class User {
	
	private int user_id;
	private String username;
	private String password;
	private boolean is_manager;
	private float money_in_account;
	
	public User() {
		user_id = 0;
		username = "";
		password = "";
		is_manager = false;
		money_in_account = 0;
	}
	
	public User(String user, String pass, boolean manager, float money){
		username = user;
		password = pass;
		is_manager = manager;
		money_in_account = money;
	}
	
	public void logout() {
		user_id = 0;
		username = "";
		password = "";
		is_manager = false;
		money_in_account = 0;
		
		System.out.println("User has been logged out.");
	}
	
	/*
	 * Below is all getters and setters
	 */
	
	public int getId() {
		return this.user_id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getManager() {
		return this.is_manager;
	}
	
	public float getMoney() {
		return this.money_in_account;
	}
	
	public void setUser(String user) {
		this.username = user;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public boolean setMoney(float money) {
		if(money < 0) {
			return false;
		}
		else {
			this.money_in_account = money;
			return true;
		}
	}
	public void setId(int id) {
		this.user_id = id;
	}
	
	public void setManager(boolean m) {
		this.is_manager = m;
	}
}
