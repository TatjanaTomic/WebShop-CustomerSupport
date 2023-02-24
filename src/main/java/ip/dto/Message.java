package ip.dto;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848890171796726101L;
	private Integer id;
	private String content;
	private boolean isRead;
	private Integer IdUser;
	private String username;
	private String mail;
	private String dateTime;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(Integer id, String content, boolean isRead, Integer idUser, String username, String mail, String dateTime) {
		super();
		this.id = id;
		this.content = content;
		this.isRead = isRead;
		this.IdUser = idUser;
		this.username = username;
		this.mail = mail;
		this.dateTime = dateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getIdUser() {
		return IdUser;
	}

	public void setIdUser(Integer idUser) {
		IdUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IdUser, content, id, isRead, mail, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(IdUser, other.IdUser) && Objects.equals(content, other.content)
				&& Objects.equals(id, other.id) && isRead == other.isRead && Objects.equals(mail, other.mail)
				&& Objects.equals(username, other.username);
	}

	

}
