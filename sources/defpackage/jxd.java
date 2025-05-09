package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.DbManager;

/* loaded from: classes5.dex */
public class jxd {
    public static void c(final String str, final int i) {
        LogUtil.a("BasicSyncStatusUtil", "enter updateSyncStatus with isToSync:", Integer.valueOf(i));
        ThreadPoolManager.d().execute(new Runnable() { // from class: jxd.5
            @Override // java.lang.Runnable
            public void run() {
                DbManager.c(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, "deviceSyncKey varchar primary key ,deviceSyncValue hasDeviceValue varchar");
                String e = bgv.e(str);
                ContentValues contentValues = new ContentValues();
                contentValues.put("deviceSyncKey", e);
                contentValues.put("deviceSyncValue", Integer.valueOf(i));
                if (jxd.b("deviceSyncKey='syncStatus'")) {
                    DbManager.b bVar = new DbManager.b();
                    bVar.b(BaseApplication.getContext());
                    bVar.e(String.valueOf(1000));
                    bVar.c("device_sync_table");
                    bVar.a(2);
                    DbManager.bGH_(bVar, contentValues, "deviceSyncKey='syncStatus'");
                    return;
                }
                DbManager.bGC_(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, contentValues);
            }
        });
    }

    public static int c(String str) {
        LogUtil.a("BasicSyncStatusUtil", "querySyncStatus");
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, "deviceSyncKey='" + bgv.e(str) + "'");
        if (bGE_ == null) {
            return 0;
        }
        if (bGE_.moveToFirst()) {
            int i = bGE_.getInt(bGE_.getColumnIndex("deviceSyncValue"));
            bGE_.close();
            LogUtil.a("BasicSyncStatusUtil", "querySyncStatus:", Integer.valueOf(i));
            return i;
        }
        bGE_.close();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str) {
        LogUtil.a("BasicSyncStatusUtil", "enter isHaveSyncStatus");
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, str);
        if (bGE_ != null) {
            r0 = bGE_.getCount() > 0;
            bGE_.close();
        }
        LogUtil.a("BasicSyncStatusUtil", "isHaveSyncStatus:", Boolean.valueOf(r0));
        return r0;
    }
}
