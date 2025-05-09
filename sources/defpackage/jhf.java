package defpackage;

import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;

/* loaded from: classes5.dex */
public class jhf {

    /* renamed from: a, reason: collision with root package name */
    private static jhf f13848a;
    private jhg e;

    private jhf(jhg jhgVar) {
        this.e = jhgVar;
    }

    public static jhf d() {
        jhf jhfVar;
        synchronized (jhf.class) {
            if (f13848a == null) {
                f13848a = new jhf(jhg.c(BaseApplication.getContext()));
            }
            jhfVar = f13848a;
        }
        return jhfVar;
    }

    private long bHj_(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("Time_Stamp"));
    }

    public long d(String str) {
        String b = b();
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Cursor queryStorageData = this.e.queryStorageData(b, 1, "Device_ID='" + e + str + "'");
        long j = 0;
        if (queryStorageData == null) {
            LogUtil.h("LastSyncDetailTimestampDb", "get lastTimestamp query error");
            return 0L;
        }
        if (queryStorageData.moveToNext()) {
            LogUtil.c("LastSyncDetailTimestampDb", "getLastTimestamp moveToNext() is not null");
            j = bHj_(queryStorageData);
        }
        queryStorageData.close();
        LogUtil.c("LastSyncDetailTimestampDb", "getLastTimestamp: ", Long.valueOf(j));
        return j;
    }

    private String b() {
        return "LastSyncDetailTimeStampDB";
    }
}
