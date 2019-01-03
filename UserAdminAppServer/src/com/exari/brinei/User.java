package com.exari.brinei;

public class User {
	private final Integer id;
	private final String userName;
	private String firstName;
	private String lastName;
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User(String userName, int id, String firstName, String lastName, String email) {
		this(userName, id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String userName, int id) {
		this.id = Integer.valueOf(id);
		assert (userName != null && !"".equals(userName.trim()));
		this.userName = userName;
	}

	public Integer getId() {
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
		if (otherObject == null || !(otherObject instanceof User)) {
			return false;
		}
		User otherUser = (User) otherObject;
		return id.equals(otherUser.id) && userName.equals(otherUser.userName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
