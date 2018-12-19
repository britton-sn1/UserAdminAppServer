package com.exari.brinei;

public class User {
	private final Long id;
	private final String userName;
	
	public User(String userName, long id) {
		this.id = Long.valueOf(id);
		assert(userName != null && !"".equals(userName.trim()));
		this.userName = userName;
	}
	public Long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	
	@Override 
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(otherObject == null || !(otherObject instanceof User)) {		
			return false;
		}
		User otherUser = (User)otherObject;
		return id.equals(otherUser.id) && userName.equals(otherUser.userName);
	}
}
