package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.huawei.hwcommonmodel.application.RunningForegroundListener;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ThirdLoginFreshAccessToken;

/* loaded from: classes.dex */
public class bxh extends BroadcastReceiver {
    private RunningForegroundListener b;

    public void e(RunningForegroundListener runningForegroundListener) {
        this.b = runningForegroundListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean booleanExtra = intent.getBooleanExtra("isForeground", false);
        LogUtil.c("ForegroundSwitchReceiver", "foreground state changed, isForeground: " + booleanExtra);
        if (booleanExtra) {
            ThirdLoginDataStorageUtil.getAccessTokenFromDb();
            ixx.d(System.currentTimeMillis());
            ThirdLoginFreshAccessToken.a();
        }
        RunningForegroundListener runningForegroundListener = this.b;
        if (runningForegroundListener == null) {
            LogUtil.c("ForegroundSwitchReceiver", "runningForegroundListener is null, return");
            return;
        }
        long longExtra = intent.getLongExtra("time", 0L);
        if (booleanExtra) {
            runningForegroundListener.goForegroundTime(longExtra);
        } else {
            runningForegroundListener.goBackgroundTime(longExtra);
        }
        LogUtil.c("ForegroundSwitchReceiver", "onReceive finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }
}
