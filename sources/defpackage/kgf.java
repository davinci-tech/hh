package defpackage;

import android.content.ContentValues;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgf {
    public static void c(int i, int i2, int i3) {
        Intent intent = new Intent("com.huawei.health.track.broadcast");
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutTypeKey", Integer.valueOf(i));
        contentValues.put("currentSportStatusKey", Integer.valueOf(i2));
        contentValues.put("linkageOperationKey", Integer.valueOf(i3));
        intent.putExtra("key_broadcast_info", contentValues);
        intent.putExtra("command_type", "REVERSE_LINKAGE");
        ReleaseLogUtil.e("R_LINKAGE_LinkageUtils", "workoutType: ", Integer.valueOf(i), ", currentSportStatus: ", Integer.valueOf(i2), ", linkageOperation: ", Integer.valueOf(i3));
        BroadcastManagerUtil.bFG_(BaseApplication.e(), intent);
    }
}
