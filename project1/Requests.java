package project1;

public class Requests {
	
	private int user_id;
	private long request_id;
	private boolean pending;
	private long amount;
	
	
	/*
	 * the request constructor only takes a user ID and an amout that is being requested
	 */
	public Requests(long amt) {//a request is created with a predefined user as the requester, an id, and an amount, a request is pending when the request is created
		this.amount = amt;
		this.pending = true;
	}
	
	/*
	 * Below is all getters and setters
	 */
	
	public long getUser() {
		return this.user_id;
	}
	
	public long getId() {
		return this.request_id;
	}
	
	public boolean getPendingStatus() {
		return this.pending;
	}
	
	public long getAmount() {
		return this.amount;
	}
	
	public void setPendingStatus(boolean status) {
		this.pending = status;
	}
	public void setId(long id) {
		this.request_id = id;
	}
	public void setUserId(int id) {
		this.user_id = id;
	}
}
