package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DbDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.ronaldong.messi");
        addOrganize(schema);
        addWorkGroup(schema);
        addPerson(schema);
        addRelation(schema);
        addAttachment(schema);

        addConversation_Message(schema);
        new DaoGenerator().generateAll(schema, "E:\\android-project\\MyApplication\\app\\src\\main\\java-gen");
    }

    private static void addOrganize(Schema schema) {
        Entity organize = schema.addEntity("Organize");
        organize.addIdProperty().primaryKey();
        organize.addStringProperty("name");
        organize.addStringProperty("pinyin");
        organize.addStringProperty("avatar");
        organize.addIntProperty("indexOrder");
        organize.addStringProperty("organizeType");
        organize.addStringProperty("deleted");
        organize.addLongProperty("parentId");
        organize.addLongProperty("lut");
    }

    private static void addWorkGroup(Schema schema) {
        Entity workGroup = schema.addEntity("WorkGroup");
        workGroup.addIdProperty().primaryKey();
        workGroup.addStringProperty("name");
        workGroup.addStringProperty("pinyin");
        workGroup.addStringProperty("avatar");
        workGroup.addIntProperty("indexOrder");
        workGroup.addStringProperty("groupType");
        workGroup.addStringProperty("deleted");
        workGroup.addLongProperty("lut");
        workGroup.addStringProperty("isPublic");
        workGroup.addStringProperty("isTmp");
        workGroup.addStringProperty("tagId");
        workGroup.addStringProperty("canEdit");
    }

    private static void addConversation_Message(Schema schema) {
        Entity conversation = schema.addEntity("Conversation");
        conversation.addIdProperty().primaryKey();
        conversation.addStringProperty("name");
        conversation.addStringProperty("avatar");
        conversation.addStringProperty("title");
        conversation.addStringProperty("content");
        conversation.addStringProperty("deleted");
        conversation.addLongProperty("lut");
        conversation.addStringProperty("silent");
        conversation.addStringProperty("isTmp");
        conversation.addLongProperty("targetId");
        conversation.addStringProperty("targetType");


        Entity message = schema.addEntity("Message");
        message.setTableName("MESSAGES");
        message.addIdProperty().primaryKey();
        message.addLongProperty("messageId");
        message.addLongProperty("senderId");

        Property conversationId = message.addLongProperty("conversationId").notNull().getProperty();
        message.addToOne(conversation, conversationId);

        message.addStringProperty("type");
        message.addStringProperty("title");
        message.addStringProperty("content");
        message.addIntProperty("state");
        message.addDoubleProperty("latitude");
        message.addDoubleProperty("longitude");
        message.addBooleanProperty("isSend");

        Property sendTime = message.addDateProperty("sendTime").getProperty();



        ToMany conversationToMessages = conversation.addToMany(message, conversationId);
        conversationToMessages.setName("messages");
        conversationToMessages.orderAsc(sendTime);



    }

    private static void addPerson(Schema schema) {
        Entity person = schema.addEntity("Person");
        person.addIdProperty().primaryKey();
        person.addStringProperty("name");
        person.addStringProperty("pinyin");
        person.addStringProperty("avatar");
        person.addStringProperty("room");
        person.addStringProperty("hasInstalled");
        person.addIntProperty("personType");
        person.addStringProperty("mobileNum");
        person.addStringProperty("workNum");
        person.addStringProperty("shortNum");
        person.addStringProperty("jobNum");
        person.addStringProperty("email");
        person.addStringProperty("duty");
        person.addStringProperty("gender");
        person.addStringProperty("deleted");
        person.addLongProperty("lut");

    }

    private static void addRelation(Schema schema) {
        Entity relation = schema.addEntity("Relation");
        relation.addIdProperty().primaryKey();
        relation.addIntProperty("indexOrder");
        relation.addStringProperty("deleted");
        relation.addLongProperty("groupId");
        relation.addLongProperty("userId");
        relation.addStringProperty("role");
        relation.addStringProperty("jobNum");
        relation.addLongProperty("lut");
    }




    private static void addAttachment(Schema schema) {
        Entity attachment = schema.addEntity("Attachment");
        attachment.addIdProperty().primaryKey();
        attachment.addLongProperty("messageId");
        attachment.addStringProperty("type");
        attachment.addStringProperty("title");
        attachment.addStringProperty("content");
        attachment.addStringProperty("url");
        attachment.addStringProperty("duration");
        attachment.addStringProperty("localPath");

    }



}
