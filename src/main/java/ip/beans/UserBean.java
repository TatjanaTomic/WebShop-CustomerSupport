package ip.beans;

import java.io.Serializable;
import java.util.Objects;

public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5705416795064044995L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean isLoggedIn = false;
	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public UserBean(Integer id, String firstName, String lastName, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, isLoggedIn, lastName, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& isLoggedIn == other.isLoggedIn && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
}
