package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealthservice.db.table.DBCommon;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ihj extends DBCommon {
    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int delete(String str, String[] strArr) {
        return super.delete(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ void execSQL(String str, Object[] objArr) {
        super.execSQL(str, objArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ long insert(ContentValues contentValues) {
        return super.insert(contentValues);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor query(String str, String[] strArr, String str2, String str3, String str4) {
        return super.query(str, strArr, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor queryEX(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return super.queryEX(strArr, str, strArr2, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor rawQuery(String str, String[] strArr) {
        return super.rawQuery(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int update(ContentValues contentValues, String str, String[] strArr) {
        return super.update(contentValues, str, strArr);
    }

    public static ihj b() {
        return d.b;
    }

    /* loaded from: classes7.dex */
    static class d {
        private static final ihj b = new ihj();
    }

    private ihj() {
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS sample_config(_id integer primary key not null,type text,config_id text,scope_app text,scope_device_type text,config_data text,config_description text,meta_data text,client_id integer not null,sync_status integer not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String d() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX SampleConfigIndex ON sample_config(type,config_id,scope_app,scope_device_type)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "type", "config_id", "scope_app", "scope_device_type", "config_data", "config_description", "meta_data", "client_id", "sync_status", "modified_time"};
    }

    public static ContentValues bxN_(HiSampleConfig hiSampleConfig) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", hiSampleConfig.getType());
        contentValues.put("config_id", hiSampleConfig.getConfigId());
        contentValues.put("scope_app", hiSampleConfig.getScopeApp());
        contentValues.put("scope_device_type", hiSampleConfig.getScopeDeviceType());
        contentValues.put("config_data", hiSampleConfig.getConfigData());
        contentValues.put("config_description", hiSampleConfig.getConfigDescription());
        contentValues.put("meta_data", hiSampleConfig.getMetaData());
        contentValues.put("client_id", Integer.valueOf(hiSampleConfig.getClientId()));
        contentValues.put("sync_status", Integer.valueOf(hiSampleConfig.getSyncStatus()));
        contentValues.put("modified_time", Long.valueOf(hiSampleConfig.getModifiedTime()));
        return contentValues;
    }

    public static ContentValues bxO_(HiSampleConfig hiSampleConfig) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("config_data", hiSampleConfig.getConfigData());
        contentValues.put("config_description", hiSampleConfig.getConfigDescription());
        contentValues.put("meta_data", hiSampleConfig.getMetaData());
        contentValues.put("client_id", Integer.valueOf(hiSampleConfig.getClientId()));
        contentValues.put("sync_status", Integer.valueOf(hiSampleConfig.getSyncStatus()));
        contentValues.put("modified_time", Long.valueOf(hiSampleConfig.getModifiedTime()));
        return contentValues;
    }

    public static ContentValues bxP_(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i));
        return contentValues;
    }

    public static List<HiSampleConfig> bxR_(Cursor cursor) {
        if (cursor == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                arrayList.add(bxQ_(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private static HiSampleConfig bxQ_(Cursor cursor) {
        return new HiSampleConfig.Builder().d(cursor.getInt(cursor.getColumnIndex("_id"))).j(cursor.getString(cursor.getColumnIndex("type"))).d(cursor.getString(cursor.getColumnIndex("config_id"))).h(cursor.getString(cursor.getColumnIndex("scope_app"))).g(cursor.getString(cursor.getColumnIndex("scope_device_type"))).b(cursor.getString(cursor.getColumnIndex("config_data"))).a(cursor.getString(cursor.getColumnIndex("config_description"))).e(cursor.getString(cursor.getColumnIndex("meta_data"))).a(cursor.getInt(cursor.getColumnIndex("client_id"))).e(cursor.getInt(cursor.getColumnIndex("sync_status"))).c(cursor.getLong(cursor.getColumnIndex("modified_time"))).c();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "sample_config";
    }
}
