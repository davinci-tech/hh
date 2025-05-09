package com.huawei.ui.openservice.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.openservice.db.model.ChildServiceTable;
import com.huawei.ui.openservice.db.model.SecurityUrlTable;
import com.huawei.ui.openservice.db.model.ServiceTable;
import com.huawei.ui.openservice.db.model.ServiceTypeTable;
import com.huawei.ui.openservice.db.model.ServiceVersionTable;
import com.huawei.ui.openservice.db.model.UserHomePageServiceTable;
import com.huawei.ui.openservice.db.model.UserServiceAuthTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class OpenServiceDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "open_service4.db";
    private static final String LOG_TAG = "Opera_OpenServiceDbHelper";
    private static final int TYPE_FOUR_SIZE = 4;
    private static final int TYPE_SEC = 2;
    private static final int TYPE_THREE_SIZE = 3;
    private static final int VERSION = 5;
    private static Context mContext;

    private OpenServiceDbHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 5);
    }

    static class Instance {
        public static final OpenServiceDbHelper DB = new OpenServiceDbHelper(OpenServiceDbHelper.mContext);

        private Instance() {
        }
    }

    public static OpenServiceDbHelper getInstance(Context context) {
        mContext = context.getApplicationContext();
        return Instance.DB;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a(LOG_TAG, "onCreate");
        sQLiteDatabase.execSQL(ServiceTable.getCreateTableSQL());
        sQLiteDatabase.execSQL(ServiceTypeTable.getCreateTableSQL());
        sQLiteDatabase.execSQL(ServiceVersionTable.getCreateTableSQL());
        sQLiteDatabase.execSQL(SecurityUrlTable.getCreateTableSQL());
        sQLiteDatabase.execSQL(UserHomePageServiceTable.getCreateTableSQL());
        sQLiteDatabase.execSQL(UserServiceAuthTable.getCreateTableSQL());
        sQLiteDatabase.execSQL(ChildServiceTable.getCreateTableSQL());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1) {
            upgrade1(sQLiteDatabase);
            i++;
        }
        if (i == 2) {
            upgrade2(sQLiteDatabase);
            i++;
        }
        if (i == 3) {
            upgrade3(sQLiteDatabase);
            i++;
        }
        if (i == 4) {
            upgrade4(sQLiteDatabase);
        }
        SpUtil.setUpgrade(mContext, true);
    }

    private void upgrade1(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a(LOG_TAG, "upgrade1 start");
        sQLiteDatabase.execSQL(" alter table service add label integer ");
        sQLiteDatabase.execSQL(" alter table service add is_service_card integer default 1");
        sQLiteDatabase.execSQL(" alter table service add need_recommend integer default 0");
        sQLiteDatabase.execSQL(" alter table service add is_plugin integer default 0");
        sQLiteDatabase.execSQL(" alter table service add plugin_url text ");
        sQLiteDatabase.execSQL(" alter table service add service_source text default THIRD_H5");
        sQLiteDatabase.execSQL(" alter table service add service_detail text ");
        LogUtil.a(LOG_TAG, "upgrade1 end");
    }

    private void upgrade2(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a(LOG_TAG, "upgrade2 start");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS child_service");
        sQLiteDatabase.execSQL(ChildServiceTable.getCreateTableSQL());
        LogUtil.a(LOG_TAG, "upgrade2 end");
    }

    private void upgrade3(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a(LOG_TAG, "upgrade3 start");
        List<ContentValues> initOldServiceForUp3 = initOldServiceForUp3(sQLiteDatabase.query(ChildServiceTable.TABLE_NAME, new String[]{"id", "service_id", "position", "location", "description", ChildServiceTable.COLUMN_IMAGE_URL, "url", "start_date", "end_date", ChildServiceTable.COLUMN_HMS_AUTH, ChildServiceTable.COLUMN_AUTH_TYPE, "modify_time", "service_name"}, null, null, null, null, null));
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS child_service");
        sQLiteDatabase.execSQL(ChildServiceTable.getCreateTableSQL());
        Iterator<ContentValues> it = initOldServiceForUp3.iterator();
        while (it.hasNext()) {
            sQLiteDatabase.insert(ChildServiceTable.TABLE_NAME, null, it.next());
        }
        LogUtil.a(LOG_TAG, "upgrade3 end");
    }

    private void upgrade4(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a(LOG_TAG, "upgrade4 start");
        sQLiteDatabase.execSQL(" alter table service add service_mid_icon text ");
        sQLiteDatabase.execSQL(" alter table service add service_keep_1 text ");
        sQLiteDatabase.execSQL(" alter table service add service_keep_2 text ");
        sQLiteDatabase.execSQL(" alter table service add service_keep_3 text ");
        sQLiteDatabase.execSQL(" alter table service add service_keep_4 text ");
        sQLiteDatabase.execSQL(" alter table service add service_keep_5 text ");
        LogUtil.a(LOG_TAG, "upgrade4 end");
    }

    private List<ContentValues> initOldServiceForUp3(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor == null) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("service_id", cursor.getString(cursor.getColumnIndex("service_id")));
                contentValues.put("location", cursor.getString(cursor.getColumnIndex("location")));
                contentValues.put("position", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("position"))));
                contentValues.put("description", cursor.getString(cursor.getColumnIndex("description")));
                contentValues.put(ChildServiceTable.COLUMN_IMAGE_URL, cursor.getString(cursor.getColumnIndex(ChildServiceTable.COLUMN_IMAGE_URL)));
                contentValues.put("url", cursor.getString(cursor.getColumnIndex("url")));
                contentValues.put("start_date", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("start_date"))));
                contentValues.put("end_date", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("end_date"))));
                contentValues.put(ChildServiceTable.COLUMN_HMS_AUTH, Integer.valueOf(cursor.getInt(cursor.getColumnIndex(ChildServiceTable.COLUMN_HMS_AUTH))));
                contentValues.put(ChildServiceTable.COLUMN_AUTH_TYPE, cursor.getString(cursor.getColumnIndex(ChildServiceTable.COLUMN_AUTH_TYPE)));
                contentValues.put("modify_time", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("modify_time"))));
                contentValues.put("service_name", cursor.getString(cursor.getColumnIndex("service_name")));
                arrayList.add(contentValues);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }
}
