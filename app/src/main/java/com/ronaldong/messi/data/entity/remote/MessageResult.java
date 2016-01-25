package com.ronaldong.messi.data.entity.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResult extends Result {

    public long id;

    public String content;

    public long sendTime;

    public MessageResult() {

    }

    public static class MResult1 extends Result {
        public long id;
        public long sendTime;

        public MResult1() {

        }

        public MResult1(long id, long sendTime) {
            this.id = id;
            this.sendTime = sendTime;
        }

    }


    private String type;
    private String creatorId;
    private String creatorName;
    private String creatorAvatarURL;
    private int receiverId;
    private int conversationId;
    private String conversationName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorAvatarURL() {
        return creatorAvatarURL;
    }

    public void setCreatorAvatarURL(String creatorAvatarURL) {
        this.creatorAvatarURL = creatorAvatarURL;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

}
