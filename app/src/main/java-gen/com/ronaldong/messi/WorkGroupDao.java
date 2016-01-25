package com.ronaldong.messi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORK_GROUP".
*/
public class WorkGroupDao extends AbstractDao<WorkGroup, Long> {

    public static final String TABLENAME = "WORK_GROUP";

    /**
     * Properties of entity WorkGroup.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Pinyin = new Property(2, String.class, "pinyin", false, "PINYIN");
        public final static Property Avatar = new Property(3, String.class, "avatar", false, "AVATAR");
        public final static Property IndexOrder = new Property(4, Integer.class, "indexOrder", false, "INDEX_ORDER");
        public final static Property GroupType = new Property(5, String.class, "groupType", false, "GROUP_TYPE");
        public final static Property Deleted = new Property(6, String.class, "deleted", false, "DELETED");
        public final static Property Lut = new Property(7, Long.class, "lut", false, "LUT");
        public final static Property IsPublic = new Property(8, String.class, "isPublic", false, "IS_PUBLIC");
        public final static Property IsTmp = new Property(9, String.class, "isTmp", false, "IS_TMP");
        public final static Property TagId = new Property(10, String.class, "tagId", false, "TAG_ID");
        public final static Property CanEdit = new Property(11, String.class, "canEdit", false, "CAN_EDIT");
    };


    public WorkGroupDao(DaoConfig config) {
        super(config);
    }
    
    public WorkGroupDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORK_GROUP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"PINYIN\" TEXT," + // 2: pinyin
                "\"AVATAR\" TEXT," + // 3: avatar
                "\"INDEX_ORDER\" INTEGER," + // 4: indexOrder
                "\"GROUP_TYPE\" TEXT," + // 5: groupType
                "\"DELETED\" TEXT," + // 6: deleted
                "\"LUT\" INTEGER," + // 7: lut
                "\"IS_PUBLIC\" TEXT," + // 8: isPublic
                "\"IS_TMP\" TEXT," + // 9: isTmp
                "\"TAG_ID\" TEXT," + // 10: tagId
                "\"CAN_EDIT\" TEXT);"); // 11: canEdit
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORK_GROUP\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, WorkGroup entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String pinyin = entity.getPinyin();
        if (pinyin != null) {
            stmt.bindString(3, pinyin);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(4, avatar);
        }
 
        Integer indexOrder = entity.getIndexOrder();
        if (indexOrder != null) {
            stmt.bindLong(5, indexOrder);
        }
 
        String groupType = entity.getGroupType();
        if (groupType != null) {
            stmt.bindString(6, groupType);
        }
 
        String deleted = entity.getDeleted();
        if (deleted != null) {
            stmt.bindString(7, deleted);
        }
 
        Long lut = entity.getLut();
        if (lut != null) {
            stmt.bindLong(8, lut);
        }
 
        String isPublic = entity.getIsPublic();
        if (isPublic != null) {
            stmt.bindString(9, isPublic);
        }
 
        String isTmp = entity.getIsTmp();
        if (isTmp != null) {
            stmt.bindString(10, isTmp);
        }
 
        String tagId = entity.getTagId();
        if (tagId != null) {
            stmt.bindString(11, tagId);
        }
 
        String canEdit = entity.getCanEdit();
        if (canEdit != null) {
            stmt.bindString(12, canEdit);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public WorkGroup readEntity(Cursor cursor, int offset) {
        WorkGroup entity = new WorkGroup( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // pinyin
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // avatar
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // indexOrder
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // groupType
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // deleted
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // lut
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // isPublic
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // isTmp
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // tagId
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // canEdit
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, WorkGroup entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPinyin(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAvatar(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIndexOrder(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setGroupType(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDeleted(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLut(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setIsPublic(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIsTmp(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTagId(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCanEdit(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(WorkGroup entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(WorkGroup entity) {
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