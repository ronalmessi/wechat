package com.ronaldong.messi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MESSAGES".
*/
public class MessageDao extends AbstractDao<Message, Long> {

    public static final String TABLENAME = "MESSAGES";

    /**
     * Properties of entity Message.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MessageId = new Property(1, Long.class, "messageId", false, "MESSAGE_ID");
        public final static Property SenderId = new Property(2, Long.class, "senderId", false, "SENDER_ID");
        public final static Property ConversationId = new Property(3, long.class, "conversationId", false, "CONVERSATION_ID");
        public final static Property Type = new Property(4, String.class, "type", false, "TYPE");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(6, String.class, "content", false, "CONTENT");
        public final static Property State = new Property(7, Integer.class, "state", false, "STATE");
        public final static Property Latitude = new Property(8, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(9, Double.class, "longitude", false, "LONGITUDE");
        public final static Property IsSend = new Property(10, Boolean.class, "isSend", false, "IS_SEND");
        public final static Property SendTime = new Property(11, java.util.Date.class, "sendTime", false, "SEND_TIME");
    };

    private DaoSession daoSession;

    private Query<Message> conversation_MessagesQuery;

    public MessageDao(DaoConfig config) {
        super(config);
    }
    
    public MessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MESSAGES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MESSAGE_ID\" INTEGER," + // 1: messageId
                "\"SENDER_ID\" INTEGER," + // 2: senderId
                "\"CONVERSATION_ID\" INTEGER NOT NULL ," + // 3: conversationId
                "\"TYPE\" TEXT," + // 4: type
                "\"TITLE\" TEXT," + // 5: title
                "\"CONTENT\" TEXT," + // 6: content
                "\"STATE\" INTEGER," + // 7: state
                "\"LATITUDE\" REAL," + // 8: latitude
                "\"LONGITUDE\" REAL," + // 9: longitude
                "\"IS_SEND\" INTEGER," + // 10: isSend
                "\"SEND_TIME\" INTEGER);"); // 11: sendTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGES\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Message entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long messageId = entity.getMessageId();
        if (messageId != null) {
            stmt.bindLong(2, messageId);
        }
 
        Long senderId = entity.getSenderId();
        if (senderId != null) {
            stmt.bindLong(3, senderId);
        }
        stmt.bindLong(4, entity.getConversationId());
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(5, type);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(8, state);
        }
 
        Double latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindDouble(9, latitude);
        }
 
        Double longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindDouble(10, longitude);
        }
 
        Boolean isSend = entity.getIsSend();
        if (isSend != null) {
            stmt.bindLong(11, isSend ? 1L: 0L);
        }
 
        java.util.Date sendTime = entity.getSendTime();
        if (sendTime != null) {
            stmt.bindLong(12, sendTime.getTime());
        }
    }

    @Override
    protected void attachEntity(Message entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Message readEntity(Cursor cursor, int offset) {
        Message entity = new Message( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // messageId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // senderId
            cursor.getLong(offset + 3), // conversationId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // type
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // title
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // content
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // state
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // latitude
            cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9), // longitude
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0, // isSend
            cursor.isNull(offset + 11) ? null : new java.util.Date(cursor.getLong(offset + 11)) // sendTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Message entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMessageId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setSenderId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setConversationId(cursor.getLong(offset + 3));
        entity.setType(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setState(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setLatitude(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setLongitude(cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9));
        entity.setIsSend(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
        entity.setSendTime(cursor.isNull(offset + 11) ? null : new java.util.Date(cursor.getLong(offset + 11)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Message entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Message entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "messages" to-many relationship of Conversation. */
    public List<Message> _queryConversation_Messages(long conversationId) {
        synchronized (this) {
            if (conversation_MessagesQuery == null) {
                QueryBuilder<Message> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ConversationId.eq(null));
                queryBuilder.orderRaw("T.'SEND_TIME' ASC");
                conversation_MessagesQuery = queryBuilder.build();
            }
        }
        Query<Message> query = conversation_MessagesQuery.forCurrentThread();
        query.setParameter(0, conversationId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getConversationDao().getAllColumns());
            builder.append(" FROM MESSAGES T");
            builder.append(" LEFT JOIN CONVERSATION T0 ON T.\"CONVERSATION_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Message loadCurrentDeep(Cursor cursor, boolean lock) {
        Message entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Conversation conversation = loadCurrentOther(daoSession.getConversationDao(), cursor, offset);
         if(conversation != null) {
            entity.setConversation(conversation);
        }

        return entity;    
    }

    public Message loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Message> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Message> list = new ArrayList<Message>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Message> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Message> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
