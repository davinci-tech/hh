package com.huawei.hianalytics.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DBUtil;
import com.huawei.hianalytics.core.storage.Property;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class e extends a<String> {
    public static final Property e;
    public static final Property f;
    public static final Property g;
    public static final Property h;
    public static final Property i;
    public static final Property j;
    public static final String k;

    @Override // com.huawei.hianalytics.core.a
    public String a(Cursor cursor) {
        Property property = e;
        int i2 = cursor.getInt(property.index);
        Property property2 = f;
        String string = cursor.getString(property2.index);
        Property property3 = g;
        String string2 = cursor.getString(property3.index);
        Property property4 = h;
        String string3 = cursor.getString(property4.index);
        Property property5 = i;
        String string4 = cursor.getString(property5.index);
        Property property6 = j;
        String string5 = cursor.getString(property6.index);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(property.columnName, i2);
            jSONObject.put(property2.columnName, string);
            jSONObject.put(property3.columnName, AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Public_Sp_Key", string2));
            jSONObject.put(property4.columnName, string3);
            jSONObject.put(property5.columnName, string4);
            jSONObject.put(property6.columnName, string5);
        } catch (JSONException e2) {
            HiLog.e("MetricMcTagDao", e2.getMessage());
        }
        return jSONObject.toString();
    }

    @Override // com.huawei.hianalytics.core.a
    public void a(ContentValues contentValues, String str) {
        String str2 = str;
        contentValues.clear();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            Property property = f;
            String optString = jSONObject.optString(property.columnName, "");
            Property property2 = g;
            String encrypt = AesGcmKsWrapper.encrypt("HiAnalytics_Sdk_Public_Sp_Key", jSONObject.optString(property2.columnName, ""));
            Property property3 = h;
            String optString2 = jSONObject.optString(property3.columnName, String.valueOf(System.currentTimeMillis()));
            Property property4 = i;
            String optString3 = jSONObject.optString(property4.columnName, "MC");
            Property property5 = j;
            String optString4 = jSONObject.optString(property5.columnName, "");
            contentValues.put(property.columnName, optString);
            contentValues.put(property2.columnName, encrypt);
            contentValues.put(property3.columnName, optString2);
            contentValues.put(property4.columnName, optString3);
            contentValues.put(property5.columnName, optString4);
        } catch (JSONException unused) {
            HiLog.w("MetricMcTagDao", "parse mcTag failed");
            contentValues.clear();
        }
    }

    public int a(List<String> list, String str) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = new String[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            strArr[i2] = list.get(i2);
            sb.append("?");
            if (i2 != list.size() - 1) {
                sb.append(",");
            }
        }
        return b(str + " in( " + ((Object) sb) + " )", strArr);
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        DBUtil.dropTable(sQLiteDatabase, "METRIC_MC_TAG");
        HiLog.w("MetricMcTagDao", "dropTable");
    }

    public e(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, "METRIC_MC_TAG");
    }

    static {
        Property property = new Property(Integer.TYPE, "_id", 0);
        e = property;
        Property property2 = new Property(String.class, "item_id", 1);
        f = property2;
        Property property3 = new Property(String.class, "metric_tag", 2);
        g = property3;
        Property property4 = new Property(String.class, "event_time", 3);
        h = property4;
        Property property5 = new Property(String.class, "content_type", 4);
        i = property5;
        Property property6 = new Property(String.class, "content_code", 5);
        j = property6;
        k = "CREATE TABLE IF NOT EXISTS METRIC_MC_TAG (" + property.columnName + " INTEGER PRIMARY KEY NOT NULL," + property2.columnName + " TEXT," + property3.columnName + " TEXT," + property4.columnName + " TEXT," + property5.columnName + " TEXT," + property6.columnName + " TEXT);";
    }
}
