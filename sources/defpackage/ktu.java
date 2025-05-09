package defpackage;

import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.DbManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ktu {
    public List<String> e(ktx ktxVar, String str) {
        ArrayList arrayList = new ArrayList(16);
        LogUtil.a("HwMultiSimAuthDb", "getAuthAppList");
        Cursor queryStorageData = ktxVar.queryStorageData("HWMultiSimAuthDB", 1, "Device_ID='" + str + "'");
        if (queryStorageData == null) {
            LogUtil.h("HwMultiSimAuthDb", "getAuthStatus query DB failure");
            return arrayList;
        }
        if (queryStorageData.moveToFirst()) {
            arrayList.add(queryStorageData.getString(queryStorageData.getColumnIndex("Package_Name")));
        }
        queryStorageData.close();
        return arrayList;
    }

    public void a(ktx ktxVar) {
        DbManager.d(BaseApplication.getContext(), ktxVar.getModuleId().toString(), "HWMultiSimAuthDB", 1);
    }
}
