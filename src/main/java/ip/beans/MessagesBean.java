package ip.beans;

import java.util.List;

import ip.dao.MessageDAO;
import ip.dto.Message;

public class MessagesBean {
	
	public List<Message> getAllMessages() {
		return MessageDAO.getAllMessages();
	}
	
	public List<Message> getUnreadMessages() {
		return MessageDAO.getUnreadMessages();
	}
	
	public List<Message> getAllMessagesWithContent(String content) {
		return MessageDAO.getAllMessagesWithContent(content);
	}
	
	public boolean markAsRead(Integer messageId) {
		return MessageDAO.updateMessage(messageId);
	}
	
	public Message getMessageById(Integer id) {
		return MessageDAO.getMessageById(id);
	}
}
