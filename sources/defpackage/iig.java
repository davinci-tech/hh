package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.WearKitPermission;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class iig extends DBCommon {
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

    private iig() {
    }

    /* loaded from: classes7.dex */
    static class d {
        private static final iig d = new iig();
    }

    public static iig a() {
        return d.d;
    }

    public static String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS wear_user_permission(_id integer primary key not null,app_id integer not null,scope_id integer,allow integer,modified_time text)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "app_id", "scope_id", "allow", "modified_time"};
    }

    public static ContentValues bzf_(WearKitPermission wearKitPermission) {
        if (wearKitPermission == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", Integer.valueOf(wearKitPermission.getAppId()));
        contentValues.put("scope_id", Integer.valueOf(wearKitPermission.getScopeId()));
        contentValues.put("allow", Integer.valueOf(wearKitPermission.getAllow()));
        contentValues.put("modified_time", wearKitPermission.getModifiedTime());
        return contentValues;
    }

    public static List<WearKitPermission> bzg_(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("DbWearKitPermission", "parsePermissionCursor cursor is null ");
            return null;
        }
        LogUtil.a("DbWearKitPermission", "parsePermissionCursor cursor is not null ");
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"app_id", "scope_id", "allow"};
                    int[] iArr2 = new int[3];
                    for (int i = 0; i < 3; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr = iArr2;
                }
                WearKitPermission wearKitPermission = new WearKitPermission();
                wearKitPermission.setAppId(cursor.getInt(iArr[0]));
                wearKitPermission.setScopeId(cursor.getInt(iArr[1]));
                wearKitPermission.setAllow(cursor.getInt(iArr[2]));
                arrayList.add(wearKitPermission);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "wear_user_permission";
    }
}
