package com.ronaldong.messi.data.entity.remote;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ronaldong.messi.Message;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageVO {
	public Long messageId;
	public Long senderId;
	public Long conversationId;
	public String type;
	public String title;
	public String content;
	public Double latitude;
	public Double longitude;
	public Long sendTime;
	public MessageVO() {
	}

//	public MessageVO(long messageId, long senderId, long conversationId,
//			String type, String title, String content, Double latitude,
//			Double longitude, long sendTime, JsonData jsonData) {
//		this.messageId = messageId;
//		this.senderId = senderId;
//		this.conversationId = conversationId;
//		this.type = type;
//		this.title = title;
//		this.content = content;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.sendTime = sendTime;
//		this.jsonData=jsonData;
//		attachments = new ArrayList<AttachmentVO>();
//	}
	
	public MessageVO (Message message) {
		this.senderId = Long.valueOf(message.getSenderId());
		this.conversationId = Long.valueOf(message.getConversationId());
		this.type = message.getType();
		this.title = message.getTitle();
		this.content = message.getContent();
		this.latitude = message.getLatitude();
		this.longitude = message.getLongitude();
		this.sendTime = message.getSendTime().getTime();

}

	public MessageVO(long senderId, long conversationId, String type,
			String title, String content, Double latitude, Double longitude,
			long sendTime) {
		this.senderId = senderId;
		this.conversationId = conversationId;
		this.type = type;
		this.title = title;
		this.content = content;
		this.latitude = latitude;
		this.longitude = longitude;
		this.sendTime = sendTime;
	}

	public MessageVO(String type, long sendTime) {
		this.type = type;
		this.sendTime = sendTime;
	}


}
