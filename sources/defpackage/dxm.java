package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dxm extends HwBaseManager {

    static class e {
        public static final dxm d = new dxm(BaseApplication.getContext());
    }

    private dxm(Context context) {
        super(context);
        LogUtil.a("UserLabelMgr", "create table error code =", Integer.valueOf(createStorageDataTable("health_user_label", 1, a())));
    }

    public static dxm b() {
        return e.d;
    }

    private static String aar_(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            return cursor.getString(columnIndex);
        }
        LogUtil.h("UserLabelMgr", "getStringColumn wrong columnName = ", str);
        return "";
    }

    private static ContentValues aap_(dxh dxhVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", dxhVar.a());
        contentValues.put("uriKey", dxhVar.d());
        contentValues.put("uriValue", dxhVar.c());
        contentValues.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public long b(dxh dxhVar) {
        LogUtil.a("UserLabelMgr", "UserLabelMgr insert data");
        return insertStorageData("health_user_label", 1, aap_(dxhVar));
    }

    public int a(dxh dxhVar) {
        if (dxhVar == null) {
            return -1;
        }
        LogUtil.a("UserLabelMgr", "UserLabelMgr update data");
        ContentValues contentValues = new ContentValues();
        contentValues.put("uriValue", dxhVar.c());
        contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        return updateStorageData("health_user_label", 1, contentValues, b(dxhVar.a(), dxhVar.d()));
    }

    public long e(dxh dxhVar) {
        if (dxhVar == null) {
            return -1L;
        }
        if (c(dxhVar.d(), dxhVar.a()) == null) {
            return b(dxhVar);
        }
        return a(dxhVar);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return Integer.valueOf(PointerIconCompat.TYPE_GRAB);
    }

    private String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,huid text,uriKey text not null,uriValue text,timestamp text");
        return sb.toString();
    }

    public dxh c(String str, String str2) {
        Throwable th;
        Cursor cursor = null;
        dxh dxhVar = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        LogUtil.a("UserLabelMgr", "UserLabelMgr queryUserLabel data");
        try {
            Cursor queryStorageData = queryStorageData("health_user_label", 1, b(str2, str));
            try {
                if (queryStorageData != null) {
                    if (queryStorageData.moveToFirst()) {
                        dxhVar = new dxh();
                        dxhVar.c(queryStorageData.getString(queryStorageData.getColumnIndex("huid")));
                        dxhVar.d(queryStorageData.getString(queryStorageData.getColumnIndex("uriKey")));
                        dxhVar.b(queryStorageData.getString(queryStorageData.getColumnIndex("uriValue")));
                        dxhVar.e(queryStorageData.getString(queryStorageData.getColumnIndex("timestamp")));
                    }
                    aaq_(queryStorageData);
                    return dxhVar;
                }
                LogUtil.a("UserLabelMgr", "cursor is null");
                aaq_(queryStorageData);
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = queryStorageData;
                aaq_(cursor);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private void aaq_(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    public String e(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("UserLabelMgr", "queryUriValueByKey text null");
            return null;
        }
        LogUtil.a("UserLabelMgr", "UserLabelMgr queryUriValueByKey data");
        try {
            Cursor queryStorageData = queryStorageData("health_user_label", 1, b(str2, str));
            if (queryStorageData != null) {
                if (queryStorageData.moveToFirst()) {
                    str3 = aar_(queryStorageData, "uriValue");
                    LogUtil.c("UserLabelMgr", "uriValue = ", str3);
                    aaq_(queryStorageData);
                    return str3;
                }
            } else {
                LogUtil.a("UserLabelMgr", "cursor is null");
            }
            str3 = "";
            aaq_(queryStorageData);
            return str3;
        } catch (Throwable th) {
            aaq_(null);
            throw th;
        }
    }

    public List<dxh> e(List<String> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("UserLabelMgr", "queryUserLabelList userLabelKeys is empty");
            return new ArrayList();
        }
        LogUtil.a("UserLabelMgr", "UserLabelMgr queryUserLabelList data");
        ArrayList arrayList = new ArrayList();
        try {
            Cursor queryStorageData = queryStorageData("health_user_label", 1, a(str));
            try {
                if (queryStorageData != null) {
                    while (queryStorageData.moveToNext()) {
                        String string = queryStorageData.getString(queryStorageData.getColumnIndex("uriKey"));
                        if (list.contains(string)) {
                            dxh dxhVar = new dxh();
                            dxhVar.c(queryStorageData.getString(queryStorageData.getColumnIndex("huid")));
                            dxhVar.d(string);
                            dxhVar.b(queryStorageData.getString(queryStorageData.getColumnIndex("uriValue")));
                            dxhVar.e(queryStorageData.getString(queryStorageData.getColumnIndex("timestamp")));
                            arrayList.add(dxhVar);
                        }
                    }
                } else {
                    LogUtil.a("UserLabelMgr", "cursor is null");
                }
                if (queryStorageData != null) {
                    queryStorageData.close();
                }
            } finally {
            }
        } catch (SQLException unused) {
            LogUtil.b("UserLabelMgr", "queryUserLabelList android SQLException");
        }
        return arrayList;
    }

    private String b(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("huid='").append(str).append("' AND uriKey='").append(str2).append("'");
        return stringBuffer.toString();
    }

    private String a(String str) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("huid='").append(str).append("'");
        return stringBuffer.toString();
    }
}
