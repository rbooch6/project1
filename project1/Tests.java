package project1;

import java.sql.SQLException;

import org.junit.*;

public class Tests {
	static Main m = new Main();
	static User u = new User();
	static User uDefined = new User("username", "password", false, 0);
	
	@Test
	public void testGetUserCount() {
		m.getUserCount();
	}
	
	@Test
	public void testFindUserByUsernameFound() {
		m.findUserByUsername("Joe");
	}
	
	@Test
	public void testFindUserByUsernameNotFound() {
		m.findUserByUsername("");
	}
	
	@Test
	public void testAddNewUserSuccess() {
		m.addNewUser(u);
	}
	
	@Test//cannot add duplicate users
	public void testAddNewUserFailure() {
		m.addNewUser(u);
	}
	
	@Test
	public void testRequestsConstructor(){
		Requests r = new Requests(50);
	}
	
	@Test
	public void testLoginSuccess() {
		m.login("Joe", "password");
	}
	
	@Test
	public void testLoginFailure() {
		m.login("", "");
	}
	
	@Test
	public void testReviewRequestSuccess() {
		m.viewRequestById(u);
	}
	
	@Test
	public void testSubmitReimbursementRequest() {
		Requests r = new Requests(50);
		m.submitReimbursementRequest(r);
	}
	
	@Test
	public void testViewAllRequests() {
		m.viewAllRequests();
	}
	
	@Test
	public void testViewAllEmployees(){
		m.viewAllEmployees();
	}
	
	@Test
	public void testUserSelfView() {
		m.userSelfView();
	}
	
	@Test
	public void testUserLogout() {
		u.logout();
	}
}
