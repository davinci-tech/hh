package com.huawei.hianalytics.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.huawei.hianalytics.core.storage.DBUtil;
import com.huawei.hianalytics.core.storage.Event;

/* loaded from: classes4.dex */
public class c extends a<Event> {
    public static final String f = "CREATE TABLE EVENT ( " + Event.COLUMN_ID.columnName + " INTEGER PRIMARY KEY, " + Event.COLUMN_EVT_ID.columnName + " TEXT, " + Event.COLUMN_EVT_TYPE.columnName + " TEXT, " + Event.COLUMN_CONTENT.columnName + " TEXT, " + Event.COLUMN_EVT_TIME.columnName + " TEXT, " + Event.COLUMN_SERVICE_TAG.columnName + " TEXT, " + Event.COLUMN_SESSION_ID.columnName + " TEXT, " + Event.COLUMN_SESSION_NAME.columnName + " TEXT, " + Event.COLUMN_EVT_EX_HASH_CODE.columnName + " TEXT, " + Event.COLUMN_PROCESS_NAME.columnName + " TEXT, " + Event.COLUMN_IS_ENCRYPTED.columnName + " INTEGER NOT NULL, " + Event.COLUMN_SUB_COUNT.columnName + " INTEGER NOT NULL );";
    public static final String g;
    public SQLiteStatement e;

    @Override // com.huawei.hianalytics.core.a
    public Event a(Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(Event.COLUMN_ID.columnName));
        return new Event(Long.valueOf(j), cursor.getString(cursor.getColumnIndex(Event.COLUMN_EVT_ID.columnName)), cursor.getString(cursor.getColumnIndex(Event.COLUMN_EVT_TYPE.columnName)), cursor.getString(cursor.getColumnIndex(Event.COLUMN_CONTENT.columnName)), cursor.getString(cursor.getColumnIndex(Event.COLUMN_EVT_TIME.columnName)), cursor.getString(cursor.getColumnIndex(Event.COLUMN_SERVICE_TAG.columnName)), cursor.isNull(cursor.getColumnIndex(Event.COLUMN_SESSION_ID.columnName)) ? "" : cursor.getString(cursor.getColumnIndex(Event.COLUMN_SESSION_ID.columnName)), cursor.isNull(cursor.getColumnIndex(Event.COLUMN_SESSION_NAME.columnName)) ? "" : cursor.getString(cursor.getColumnIndex(Event.COLUMN_SESSION_NAME.columnName)), cursor.isNull(cursor.getColumnIndex(Event.COLUMN_EVT_EX_HASH_CODE.columnName)) ? "" : cursor.getString(cursor.getColumnIndex(Event.COLUMN_EVT_EX_HASH_CODE.columnName)), cursor.getString(cursor.getColumnIndex(Event.COLUMN_PROCESS_NAME.columnName)), cursor.getInt(cursor.getColumnIndex(Event.COLUMN_IS_ENCRYPTED.columnName)), cursor.isNull(cursor.getColumnIndex(Event.COLUMN_SUB_COUNT.columnName)) ? 1 : cursor.getInt(cursor.getColumnIndex(Event.COLUMN_SUB_COUNT.columnName)));
    }

    @Override // com.huawei.hianalytics.core.a
    public void a(ContentValues contentValues, Event event) {
        Event event2 = event;
        contentValues.clear();
        if (event2.getId() != null) {
            contentValues.put(Event.COLUMN_ID.columnName, event2.getId());
        }
        contentValues.put(Event.COLUMN_CONTENT.columnName, event2.getContent());
        contentValues.put(Event.COLUMN_EVT_EX_HASH_CODE.columnName, event2.getEvtExHashCode());
        contentValues.put(Event.COLUMN_EVT_ID.columnName, event2.getEvtid());
        contentValues.put(Event.COLUMN_EVT_TIME.columnName, event2.getEvttime());
        contentValues.put(Event.COLUMN_EVT_TYPE.columnName, event2.getEvttype());
        contentValues.put(Event.COLUMN_IS_ENCRYPTED.columnName, Integer.valueOf(event2.getIsEncrypted()));
        contentValues.put(Event.COLUMN_PROCESS_NAME.columnName, event2.getProcessname());
        contentValues.put(Event.COLUMN_SERVICE_TAG.columnName, event2.getServicetag());
        contentValues.put(Event.COLUMN_SESSION_ID.columnName, event2.getSessionid());
        contentValues.put(Event.COLUMN_SESSION_NAME.columnName, event2.getSessionname());
        contentValues.put(Event.COLUMN_SUB_COUNT.columnName, Integer.valueOf(event2.getSubCount()));
    }

    @Override // com.huawei.hianalytics.core.a
    public long b() {
        if (this.e == null) {
            this.e = this.b.compileStatement("SELECT COUNT(*) FROM \"EVENT\"");
        }
        return this.e.simpleQueryForLong();
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        DBUtil.dropTable(sQLiteDatabase, Event.TABLE_NAME);
    }

    public c(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, Event.TABLE_NAME);
    }

    static {
        StringBuilder sb = new StringBuilder("SELECT SUM(");
        sb.append(Event.COLUMN_SUB_COUNT.columnName);
        sb.append(") FROM EVENT WHERE ");
        sb.append(Event.COLUMN_SERVICE_TAG.columnName);
        sb.append("=? AND ");
        sb.append(Event.COLUMN_EVT_TYPE.columnName);
        sb.append("=? AND (");
        sb.append(Event.COLUMN_PROCESS_NAME.columnName);
        sb.append("=? OR ");
        sb.append(Event.COLUMN_PROCESS_NAME.columnName);
        sb.append("=?)");
        g = sb.toString();
    }
}
