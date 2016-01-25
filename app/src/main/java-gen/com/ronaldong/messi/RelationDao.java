package com.ronaldong.messi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RELATION".
*/
public class RelationDao extends AbstractDao<Relation, Long> {

    public static final String TABLENAME = "RELATION";

    /**
     * Properties of entity Relation.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property IndexOrder = new Property(1, Integer.class, "indexOrder", false, "INDEX_ORDER");
        public final static Property Deleted = new Property(2, String.class, "deleted", false, "DELETED");
        public final static Property GroupId = new Property(3, Long.class, "groupId", false, "GROUP_ID");
        public final static Property UserId = new Property(4, Long.class, "userId", false, "USER_ID");
        public final static Property Role = new Property(5, String.class, "role", false, "ROLE");
        public final static Property JobNum = new Property(6, String.class, "jobNum", false, "JOB_NUM");
        public final static Property Lut = new Property(7, Long.class, "lut", false, "LUT");
    };


    public RelationDao(DaoConfig config) {
        super(config);
    }
    
    public RelationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RELATION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"INDEX_ORDER\" INTEGER," + // 1: indexOrder
                "\"DELETED\" TEXT," + // 2: deleted
                "\"GROUP_ID\" INTEGER," + // 3: groupId
                "\"USER_ID\" INTEGER," + // 4: userId
                "\"ROLE\" TEXT," + // 5: role
                "\"JOB_NUM\" TEXT," + // 6: jobNum
                "\"LUT\" INTEGER);"); // 7: lut
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RELATION\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Relation entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer indexOrder = entity.getIndexOrder();
        if (indexOrder != null) {
            stmt.bindLong(2, indexOrder);
        }
 
        String deleted = entity.getDeleted();
        if (deleted != null) {
            stmt.bindString(3, deleted);
        }
 
        Long groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindLong(4, groupId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(6, role);
        }
 
        String jobNum = entity.getJobNum();
        if (jobNum != null) {
            stmt.bindString(7, jobNum);
        }
 
        Long lut = entity.getLut();
        if (lut != null) {
            stmt.bindLong(8, lut);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Relation readEntity(Cursor cursor, int offset) {
        Relation entity = new Relation( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // indexOrder
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // deleted
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // groupId
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // userId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // role
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // jobNum
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // lut
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Relation entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIndexOrder(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDeleted(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGroupId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setRole(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setJobNum(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLut(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Relation entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Relation entity) {
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
    
}
