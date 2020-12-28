package project1;

//the sql query code was pulled from this website and formatted to fit my needs
//https://www.postgresqltutorial.com/postgresql-jdbc/que

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.javalin.Javalin;

public class Main {
	
	final static String username = "postgres";
	final static String password = "Habata11Tara";
	final static String url = "jdbc:postgresql://java-1120-roger.cilblxk7zlgb.us-east-1.rds.amazonaws.com:5432/postgres";
	
	//creates a user object based on data input later on in the code
	static User currentUser = new User();
	
	/*
	 * Connects the other methods to the database in order to make changes and access the information
	 * stored within it
	 */
	private static Connection getConnection(){
		
	    Connection conn = null;

	    try {
	        conn = DriverManager.getConnection(url, username, password);
	    }
	    catch(SQLException e){
	    	System.out.println(e.getMessage());
	    }
	    
	    return conn;
	}
	
	/*
	 * returns the amount of users in the database, more of a test method to try out sql query
	 * using jdbc
	 * 
	 * @return int (amount of users)
	 */
	public int getUserCount() {
		String SQL = "SELECT count(*) FROM user";
		int count = 0;
		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)){
			rs.next();
			count = rs.getInt(1);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return count;
	}
	
	/*
	 * bascially a toString method for accessing a user, prints out user info straight to the console
	 * for now. mostly used to test if a user is successfully found
	 * 
	 * @param ResultSet
	 */
	private void displayUser(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("id") + "\t"
                    + rs.getString("username") + "\t"
                    + rs.getString("pass") + "\t"
                    + rs.getString("ismanager") + "\t"
                    + rs.getString("moneyinaccount"));

        }
    }
	
	/*
	 * displays a request. for use of the employee
	 * 
	 * @param ResultSet
	 */
	private void displayRequests(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("Request ID: " + rs.getString("id") + "\t"
                    + " |Pending (t/f): "+ rs.getString("pending") + "\t"
                    + " |amount $" + rs.getString("amount") + "\t");

        }
    }
	
	/*
	 * method used to find a user based on their username
	 */
	public void findUserByUsername(String user) {
        String SQL = "SELECT * "
                + "FROM public.user "
                + "WHERE username = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, user);
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	
	/*
	 * checks to see if the user exists and if they do it returns true, else it returns false
	 * 
	 * @param user
	 * @param pass
	 * @return boolean (is a user exists within a system)
	 */
	private static boolean loginCheck(String user, String pass) {
		String SQL = "SELECT * "
                + "FROM public.user "
                + "WHERE username = ? and pass = ?";
		
		try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/*
	 * checks the database to see if a user with a certain user name exists
	 * 
	 * this system does not allow duplicate usernames
	 * 
	 * @param user
	 * @return boolean (checks to see if user exists)
	 */
	private static boolean userCheck(String user) {
		String SQL = "SELECT * "
                + "FROM public.user "
                + "WHERE username = ?";
		
		try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			
            pstmt.setString(1, user);
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/*
	 * Logs the user in, once I have connected to the html
	 * 
	 * use sessions to confirm the views of the user, as well as checking if they
	 * should be logged in as a manager or an employee
	 * 
	 * @param user
	 * @param pass
	 */
	public void login(String user, String pass) {
		String SQL = "SELECT * "
                + "FROM public.user "
                + "WHERE username = ? and pass = ?";
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			if(loginCheck(user, pass)) {
				
				pstmt.setString(1, user);
				pstmt.setString(2, pass);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					currentUser.setUser(user);
					currentUser.setPassword(pass);
					currentUser.setId(rs.getInt(1));
					currentUser.setMoney(rs.getFloat(5));
					currentUser.setManager(rs.getBoolean(4));
					//login successful, creates a user object allowing them to access the methods
					System.out.println("User successfully logged in!");
				}
			}
			else {
				//login unsuccessful, user not found, inform user that a user with those credentials does not exist within the database
				System.out.println("User " + user + " not found");
			}
                
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/*
	 * method to insert new users into the database
	 * 
	 * @param user
	 * @return long (the new ID for the created user)
	 */
	public long addNewUser(User user) {
		String SQL = "INSERT INTO public.user(username, pass, ismanager, moneyinaccount) "
                + "VALUES(?,?,?,?)";
		
		long id = 0;
		
		try(Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL,
				Statement.RETURN_GENERATED_KEYS)){
			
			if(!(userCheck(user.getUsername()))) {
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setBoolean(3, user.getManager());
			pstmt.setFloat(4, user.getMoney());
			
			int affectedRows = pstmt.executeUpdate();
				//check the affected rows
				if(affectedRows > 0) {
					//get the ID generated for the new user back
					try(ResultSet rs = pstmt.getGeneratedKeys()){	
						if(rs.next()) {
							id = rs.getLong(1);
						}
						
						System.out.println("User created with ID of: " + id);
					}catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}else {
				System.out.println("Username already exists");
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	/*
	 * allows the user to make a new reimbursement request
	 * 
	 * @param Request
	 * @return long (ID of the reimbursement request)
	 */
	public long submitReimbursementRequest(Requests r) {
		String SQL = "INSERT INTO public.requests(pending, amount, userid) "
				+ "VALUES (true, ?, ?)";
		
		long id = 0;
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL,
				Statement.RETURN_GENERATED_KEYS)){ 
			
			pstmt.setLong(1, r.getAmount());
			pstmt.setInt(2, currentUser.getId());
			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				//get the ID generated for the new request back
				try(ResultSet rs = pstmt.getGeneratedKeys()){	
					if(rs.next()) {
						id = rs.getLong(1);
						System.out.println("Request created");
					}
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	/*
	 * for managers to view all of the requests from a single user
	 * 
	 * @param user
	 */
	public void viewRequestById(User user) {
		String SQL = "SELECT * "
                + "FROM public.requests "
                + "WHERE userid = ?";
		
		try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			
            pstmt.setLong(1, user.getId());
            
            ResultSet rs = pstmt.executeQuery();
            
            displayRequests(rs);
            
            
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/*
	 * Returns a string value with all user reimbursement requests
	 * 
	 * @return String (all requests within the database)
	 */
	public String viewAllRequests(){
		String SQL = "SELECT * "
				+ "FROM public.requests";
		
		String result = "";
		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)){
			while(rs.next()) {
				result = result + "Request ID: "+ rs.getString("id") 
				+ " |Pending: " + rs.getString("pending") 
				+ " |Amount $" + rs.getString("amount") 
				+ " |UserID associated with request: " + rs.getString("userid") + "\n";
			}
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	
	/*
	 * Returns a string value with all user reimbursement requests
	 * 
	 * @return String (returns a string of the every employee wihtin the database)
	 */
	public String viewAllEmployees(){
		String SQL = "SELECT * "
				+ "FROM public.user";
		
		String result = "";
		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)){
			while(rs.next()) {
				if(rs.getString("ismanager").equals("f")) {
				result = result + "UserID: " + rs.getString("id")
				+ " |Username: " + rs.getString("username") 
				+ " |Money in account: $" + rs.getString("moneyinaccount") + "\n";
				}
			}
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	
	/*
	 * returns a string of all of the reimbursements requests from one employee
	 *
	public String viewRequestsOfSingleEmployee(int userid){
		String SQL = "SELECT * "
				+ "FROM public.requests"
				+ "WHERE userid = ?";
		
		String result = "";
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL)){
				
			pstmt.setInt(1, userid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = result + "UserID: " + rs.getString("id")
				+ " |Amount Requested: " + rs.getString("amount");
			}
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	*/
	
	/*
	 * this is where an employee can view their information
	 * 
	 * @return String (Te user's view of their own information)
	 */
	public String userSelfView() {
        String SQL = "SELECT * "
                + "FROM public.user "
                + "WHERE id = ?";

        String result = "";
        
        try (Connection conn = getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			
        	pstmt.setInt(1, currentUser.getId());
            
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            result = "UserID: " + rs.getString("id")
			+ " |Username: " + rs.getString("username")
			+ " |password: " + rs.getString("pass")
			+ " |money in account $" + rs.getString("moneyinaccount");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
	
	/*
	 * allows user to update their information
	 *
	public void userUpdateInfo() {
		//not implemented
	}
	*/
	
	/*
	 * Showing the display for the presentation purposes
	 */
	public static void main(String[]args){
		Main start = new Main();
		
		//start.login("Chad", "password");
		//start.login("Joe", "password");
		//currentUser.logout();
		
		//User newGuy = new User("Joe", "differentpassword", false, 0);
		//start.addNewUser(newGuy);
		
		//User newGuy = new User("Chad", "password", false, 0);
		//start.addNewUser(newGuy);
		//start.login("Chad", "password");
		
		//Requests r = new Requests(100);
		//r.setUserId(currentUser.getId());
		//start.submitReimbursementRequest(r);
		
		//start.viewRequestById(currentUser);
		
		//System.out.println("=== All requests ===\n" + start.viewAllRequests());
		//System.out.println("=== All Employees (managers excluded) ===\n" + start.viewAllEmployees());
		
		//System.out.println(start.userSelfView());
		
		
		
		//Javalin app = Javalin.create();
		//app.start(5400);
		
	}
}
