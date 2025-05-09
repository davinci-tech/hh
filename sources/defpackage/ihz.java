package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ihz extends DBCommon {
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

    private ihz() {
    }

    /* loaded from: classes7.dex */
    static class c {
        private static final ihz e = new ihz();
    }

    public static ihz d() {
        return c.e;
    }

    public static String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_user_permission(_id integer primary key not null,app_id integer not null,scope_id integer not null,allow_read integer ,allow_write integer ,modified_time text)");
        return sb.toString();
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX UserPermissionIndex ON hihealth_user_permission(app_id)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "app_id", "scope_id", "allow_read", "allow_write", "modified_time"};
    }

    public static ContentValues bzd_(HiHealthUserPermission hiHealthUserPermission) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", Integer.valueOf(hiHealthUserPermission.getAppId()));
        contentValues.put("scope_id", Integer.valueOf(hiHealthUserPermission.getScopeId()));
        contentValues.put("allow_read", Integer.valueOf(hiHealthUserPermission.getAllowRead()));
        contentValues.put("allow_write", Integer.valueOf(hiHealthUserPermission.getAllowWrite()));
        contentValues.put("modified_time", hiHealthUserPermission.getModifiedTime());
        return contentValues;
    }

    public static List<HiHealthUserPermission> bze_(Cursor cursor) {
        ArrayList arrayList = new ArrayList(10);
        if (cursor == null) {
            LogUtil.h("DbHiHealthUserPermission", "parsePermissionCursor query is null ");
            return arrayList;
        }
        LogUtil.a("DbHiHealthUserPermission", "parsePermissionCursor query is not null ");
        int[] iArr = null;
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"app_id", "scope_id", "allow_read", "allow_write"};
                    int[] iArr2 = new int[4];
                    for (int i = 0; i < 4; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr = iArr2;
                }
                HiHealthUserPermission hiHealthUserPermission = new HiHealthUserPermission();
                hiHealthUserPermission.setAppId(cursor.getInt(iArr[0]));
                hiHealthUserPermission.setScopeId(cursor.getInt(iArr[1]));
                hiHealthUserPermission.setAllowRead(cursor.getInt(iArr[2]));
                hiHealthUserPermission.setAllowWrite(cursor.getInt(iArr[3]));
                arrayList.add(hiHealthUserPermission);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_user_permission";
    }
}
