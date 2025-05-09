package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.SecurityContactsDbHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jzy {
    public static void c(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "setLastSyncedTime: deviceId is null or empty.");
            return;
        }
        LogUtil.a("CacheDatabaseUtils", "setLastSyncedTime: lastSyncedTime: ", Long.valueOf(j));
        jzu d = jzu.d(BaseApplication.getContext());
        List<jzv> e = d.e(kao.b(str));
        if (e.isEmpty()) {
            d.b(kao.b(new jzv(str, str, 16, 1, j)));
        } else {
            jzv jzvVar = e.get(0);
            jzvVar.d(j);
            d.c(kao.b(jzvVar));
        }
        LogUtil.a("CacheDatabaseUtils", "setLastSyncedTime: end, queried size: ", Integer.valueOf(e.size()));
    }

    public static long d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "getLastSyncedTime: deviceId is null or empty");
            return 0L;
        }
        List<jzv> e = jzu.d(BaseApplication.getContext()).e(kao.b(str));
        if (e.isEmpty()) {
            LogUtil.a("CacheDatabaseUtils", "getLastSyncedTime: queried result list is empty.");
            return 0L;
        }
        jzv jzvVar = e.get(0);
        if (jzvVar == null) {
            LogUtil.a("CacheDatabaseUtils", "getLastSyncedTime: queried result bean is empty.");
            return 0L;
        }
        return jzvVar.c();
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "setSyncAllContactsFlag: deviceId is null or empty.");
            return;
        }
        int i2 = i == 0 ? 0 : 16;
        jzu d = jzu.d(BaseApplication.getContext());
        List<jzv> e = d.e(kao.b(str));
        LogUtil.a("CacheDatabaseUtils", "setSyncAllContactsFlag: start, queried size: ", Integer.valueOf(e.size()));
        if (e.isEmpty()) {
            d.b(kao.b(new jzv(str, str, i2, 1, System.currentTimeMillis())));
            return;
        }
        jzv jzvVar = e.get(0);
        if (jzvVar == null) {
            LogUtil.h("CacheDatabaseUtils", "setSyncAllContactsFlag: deviceSyncStateBean is null.");
        } else {
            jzvVar.d(i);
            d.c(kao.b(jzvVar));
        }
    }

    public static void a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "setSyncContactState: deviceId is null or empty.");
            return;
        }
        LogUtil.a("CacheDatabaseUtils", "setSyncContactState: start. isSync: ", Boolean.valueOf(z));
        jzu.d(BaseApplication.getContext()).d(kao.b(new jzv(str, str, 0, z ? 1 : 0, -1L)));
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "isSynchronizedOnce: deviceId is null or empty.");
            return false;
        }
        LogUtil.a("CacheDatabaseUtils", "isSynchronizedOnce: enter");
        List<jzv> e = jzu.d(BaseApplication.getContext()).e(kao.b(str));
        return !e.isEmpty() && e.get(0).a() == 16;
    }

    public static void c(String str, List<jzt> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            LogUtil.h("CacheDatabaseUtils", "saveSyncedContacts: input parameters are invalid.");
            return;
        }
        jzw b = jzw.b(BaseApplication.getContext());
        if (c(str)) {
            b.e(list, str);
        } else {
            b.a(list, str);
        }
        c(str, System.currentTimeMillis());
        a(str, 16);
    }

    public static void d(String str, List<String> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            LogUtil.h("CacheDatabaseUtils", "saveDeletedContacts: input parameters are invalid.");
        } else {
            jzw.b(BaseApplication.getContext()).c(list, str);
            c(str, System.currentTimeMillis());
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "createSyncDataTable: deviceId is null or empty.");
        } else {
            jzw.b(BaseApplication.getContext()).a(str);
        }
    }

    public static List<jzt> b(String str) {
        return jzw.b(BaseApplication.getContext()).b(str);
    }

    public static void a(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("CacheDatabaseUtils", "clearDeviceFromDb: deviceIdList is null or empty.");
            return;
        }
        LogUtil.a("CacheDatabaseUtils", "clearDeviceFromDb: list size: ", Integer.valueOf(list.size()));
        jzu.d(BaseApplication.getContext()).a(list);
        SecurityContactsDbHelper c = SecurityContactsDbHelper.c(BaseApplication.getContext());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            c.e(it.next());
        }
    }

    public static void c() {
        jzw.b(BaseApplication.getContext());
        jzu.d(BaseApplication.getContext());
    }
}
