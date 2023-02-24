package ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.dto.Message;

public class MessageDAO {
	private static final String SQL_GET_ALL_MESSAGES = 
			"SELECT message.id, content, is_read, id_user, username, mail, date_time FROM message INNER JOIN user_account ON id_user=user_account.id ORDER BY message.id DESC";
	private static final String SQL_GET_UNREAD_MESSAGES =
			"SELECT message.id, content, is_read, id_user, username, mail, date_time FROM message INNER JOIN user_account ON id_user=user_account.id WHERE is_read=false ORDER BY message.id DESC";
	private static final String SQL_GET_BY_ID = 
			"SELECT message.id, content, is_read, id_user, username, mail, date_time FROM message INNER JOIN user_account ON id_user=user_account.id WHERE message.id=?";
	private static final String SQL_GET_ALL_MESSAGES_CONTENT =
			"SELECT message.id, content, is_read, id_user, username, mail, date_time FROM message INNER JOIN user_account ON id_user=user_account.id WHERE content LIKE CONCAT('%',?,'%') ORDER BY message.id DESC";
	private static final String SQL_UPDATE_MESSAGE =
			"UPDATE message SET is_read=true WHERE id=?";
	
	public static ArrayList<Message> getAllMessages() {
		ArrayList<Message> messages = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object values[] = {};
			
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_GET_ALL_MESSAGES, false, values);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Message message = new Message(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
				messages.add(message);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return messages;
	}
	
	public static ArrayList<Message> getUnreadMessages() {
		ArrayList<Message> messages = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object values[] = {};
			
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_GET_UNREAD_MESSAGES, false, values);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Message message = new Message(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
				messages.add(message);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return messages;
	}
	
	public static boolean updateMessage(int messageId) {
		boolean updated = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { messageId };
			
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_UPDATE_MESSAGE, false, values);
			if(ps.executeUpdate() == 1)
				updated = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return updated;
	}

	public static List<Message> getAllMessagesWithContent(String content) {
		ArrayList<Message> messages = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object values[] = { content };
			
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_GET_ALL_MESSAGES_CONTENT, false, values);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Message message = new Message(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
				messages.add(message);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return messages;
	}

	public static Message getMessageById(Integer id) {
		Message message = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object values[] = { id };
			
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_GET_BY_ID, false, values);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				message = new Message(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return message;
	}
	
}
