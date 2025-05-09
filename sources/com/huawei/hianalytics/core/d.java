package com.huawei.hianalytics.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DBUtil;
import com.huawei.hianalytics.core.storage.Property;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d extends a<String> {
    public static final Property e;
    public static final Property f;
    public static final Property g;
    public static final String h;

    @Override // com.huawei.hianalytics.core.a
    public String a(Cursor cursor) {
        JSONObject jSONObject;
        Property property = e;
        int i = cursor.getInt(property.index);
        String string = cursor.getString(f.index);
        Property property2 = g;
        String string2 = cursor.getString(property2.index);
        try {
            jSONObject = new JSONObject(AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Public_Sp_Key", string));
            jSONObject.put(property.columnName, i);
            jSONObject.put(property2.columnName, string2);
        } catch (JSONException e2) {
            jSONObject = new JSONObject();
            HiLog.e("MetricMcInfoDao", e2.getMessage());
        }
        return jSONObject.toString();
    }

    @Override // com.huawei.hianalytics.core.a
    public void a(ContentValues contentValues, String str) {
        contentValues.clear();
        contentValues.put(f.columnName, AesGcmKsWrapper.encrypt("HiAnalytics_Sdk_Public_Sp_Key", str));
        contentValues.put(g.columnName, String.valueOf(System.currentTimeMillis()));
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        DBUtil.dropTable(sQLiteDatabase, "METRIC_MC_INFO");
        HiLog.w("MetricMcInfoDao", "dropTable");
    }

    public d(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, "METRIC_MC_INFO");
    }

    static {
        Property property = new Property(Integer.TYPE, "_id", 0);
        e = property;
        Property property2 = new Property(String.class, "content", 1);
        f = property2;
        Property property3 = new Property(String.class, "event_time", 2);
        g = property3;
        h = "CREATE TABLE IF NOT EXISTS METRIC_MC_INFO (" + property.columnName + " INTEGER PRIMARY KEY NOT NULL," + property2.columnName + " TEXT," + property3.columnName + " TEXT);";
    }
}
