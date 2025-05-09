package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.DbManager;
import health.compact.a.EnvironmentInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class jrp {
    public static void e(String str, int i) {
        ReleaseLogUtil.e("SyncStatusUtil", "enter updateSyncStatus with isToSync:", Integer.valueOf(i));
        DbManager.c(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, "deviceSyncKey varchar primary key ,deviceSyncValue hasDeviceValue varchar");
        String e = bgv.e(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceSyncKey", e);
        contentValues.put("deviceSyncValue", Integer.valueOf(i));
        if (c("deviceSyncKey='syncStatus'")) {
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

    public static int a(String str) {
        LogUtil.a("SyncStatusUtil", "querySyncStatus");
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, "deviceSyncKey='" + bgv.e(str) + "'");
        if (bGE_ == null) {
            return 0;
        }
        if (bGE_.moveToFirst()) {
            int i = bGE_.getInt(bGE_.getColumnIndex("deviceSyncValue"));
            bGE_.close();
            LogUtil.a("SyncStatusUtil", "querySyncStatus:", Integer.valueOf(i));
            return i;
        }
        bGE_.close();
        return 0;
    }

    private static boolean c(String str) {
        LogUtil.a("SyncStatusUtil", "enter isHaveSyncStatus");
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(1000), "device_sync_table", 2, str);
        if (bGE_ != null) {
            r0 = bGE_.getCount() > 0;
            bGE_.close();
        }
        LogUtil.a("SyncStatusUtil", "isHaveSyncStatus:", Boolean.valueOf(r0));
        return r0;
    }

    public static void c() {
        if (EnvironmentInfo.j()) {
            try {
                Thread.sleep(BaseApplication.isRunningForeground() ? 50L : 100L);
            } catch (InterruptedException e) {
                ReleaseLogUtil.c("R_SyncStatusUtil", "threadSleep InterruptedException :", ExceptionUtils.d(e));
            }
        }
    }
}
