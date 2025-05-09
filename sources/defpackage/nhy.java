package defpackage;

import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes6.dex */
public class nhy {
    public static boolean d(long j) {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(11);
        LogUtil.a("CoreSleepNotifyUtil", "indexHour: ", Integer.valueOf(i));
        boolean z = false;
        boolean z2 = i < 0 || i >= 7;
        if (j < jec.d(System.currentTimeMillis()) - 14400000) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepNotifyUtil", "pushSleepNotifyMessage sleep endTime too early! endTime = ", Long.valueOf(j));
        } else {
            z = z2;
        }
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepNotifyUtil", "isTimeToNotify is", Boolean.valueOf(z));
        return z;
    }

    public static boolean d(int i, int i2, nhv nhvVar) {
        long c = nhvVar.c();
        long e = nhvVar.e();
        double doubleValue = new BigDecimal(c).subtract(new BigDecimal(e)).doubleValue() / 3600000.0d;
        boolean z = doubleValue > 3.0d;
        LogUtil.a("CoreSleepNotifyUtil", "saveCoreSleepData notify enter,the last data's fallAsleepTime :", Long.valueOf(e), "wakeTime :", Long.valueOf(c), "duration :", Double.valueOf(doubleValue));
        if (VersionControlUtil.isSupportSleepManagement()) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepNotifyUtil", "shouldSendSleepNotify isShouldNotify is ", Boolean.valueOf(z), ", insertCode is ", Integer.valueOf(i), ", SleepMgmtAlgorithmCode is ", Integer.valueOf(i2));
            return z && i == 0 && d(c) && i2 == 0;
        }
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepNotifyUtil", "shouldSendSleepNotify isShouldNotify is ", Boolean.valueOf(z), " insertCode is", Integer.valueOf(i));
        return z && i == 0 && d(c);
    }

    public static void e(nhv nhvVar) {
        Intent intent = new Intent("com.huawei.health.track.broadcast");
        intent.putExtra("fallAsleepTime", nhvVar.e());
        intent.putExtra("wakeTime", nhvVar.c());
        intent.putExtra(JsUtil.SCORE, nhvVar.d());
        intent.putExtra("command_type", "com.huawei.health.sync.coresleep");
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), intent);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepNotifyUtil", "sendSleepBroadcast end");
    }
}
