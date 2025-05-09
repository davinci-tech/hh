package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ktw {
    private String c() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("_id integer primary key autoincrement,Package_Name NVARCHAR(300) not null,User_ID NVARCHAR(300) not null,Device_ID NVARCHAR(300) not null,Auth_Time NVARCHAR(300) not null");
        return String.valueOf(stringBuffer);
    }

    public void d(ktx ktxVar) {
        if (ktxVar == null) {
            LogUtil.h("HwMultiSimAuthNewDb", "createDbTable hwMultiSimMgr is null");
        } else {
            ktxVar.createStorageDataTable("HWMultiSimAuthNewDB", 1, c());
        }
    }

    private ContentValues bRl_(String str, String str2, String str3, String str4) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Package_Name", str);
        contentValues.put("User_ID", str2);
        contentValues.put("Device_ID", str3);
        contentValues.put("Auth_Time", str4);
        return contentValues;
    }

    public void d(ktx ktxVar, List<String> list, String str, String str2) {
        if (ktxVar == null) {
            LogUtil.h("HwMultiSimAuthNewDb", "updateAuthStatus hwMultiSimMgr is null");
            return;
        }
        LogUtil.a("HwMultiSimAuthNewDb", "updateAuthStatus applist enter");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            ktxVar.insertStorageData("HWMultiSimAuthNewDB", 1, bRl_(it.next(), str, str2, "NA"));
        }
    }

    public void a(ktx ktxVar, String str, String str2, String str3) {
        if (ktxVar == null) {
            LogUtil.h("HwMultiSimAuthNewDb", "updateAuthStatus hwMultiSimMgr is null");
            return;
        }
        LogUtil.a("HwMultiSimAuthNewDb", "updateAuthStatus packageName:", str, " userId:", CommonUtil.l(str2), " deviceId:", CommonUtil.l(str3));
        ContentValues bRl_ = bRl_(str, str2, str3, String.valueOf(System.currentTimeMillis()));
        Cursor queryStorageData = ktxVar.queryStorageData("HWMultiSimAuthNewDB", 1, "Package_Name='" + str + "' AND User_ID='" + str2 + "' AND Device_ID='" + str3 + "'");
        if (queryStorageData == null) {
            LogUtil.h("HwMultiSimAuthNewDb", "updateAuthStatus query database failure");
            return;
        }
        if (queryStorageData.moveToFirst()) {
            ktxVar.updateStorageData("HWMultiSimAuthNewDB", 1, bRl_, "Package_Name='" + str + "' AND User_ID='" + str2 + "' AND Device_ID='" + str3 + "'");
        } else {
            ktxVar.insertStorageData("HWMultiSimAuthNewDB", 1, bRl_);
        }
        queryStorageData.close();
    }

    public boolean d(ktx ktxVar, String str, String str2, String str3) {
        if (ktxVar == null) {
            LogUtil.h("HwMultiSimAuthNewDb", "getAuthStatus hwMultiSimMgr is null");
            return false;
        }
        Cursor queryStorageData = ktxVar.queryStorageData("HWMultiSimAuthNewDB", 1, "Package_Name='" + str + "' AND User_ID='" + str2 + "' AND Device_ID='" + str3 + "'");
        if (queryStorageData == null) {
            LogUtil.h("HwMultiSimAuthNewDb", "getAuthStatus query database failure");
            return false;
        }
        boolean moveToFirst = queryStorageData.moveToFirst();
        queryStorageData.close();
        return moveToFirst;
    }
}
