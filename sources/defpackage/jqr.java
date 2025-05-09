package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.syncmgr.SyncOption;
import health.compact.a.EnvironmentUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class jqr {
    public static SyncOption c(byte[] bArr) {
        DeviceInfo d;
        long j;
        if (EnvironmentUtils.e()) {
            d = kec.c().getDeviceInfo();
        } else {
            d = jpt.d("CoreSleepOptionUtil");
        }
        LogUtil.a("CoreSleepOptionUtil", "createSyncOption dataInfos: ", cvx.d(bArr));
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepOptionUtil", " support CORE_SLEEP_ALGORITHM:", Boolean.valueOf(cwi.c(d, 143)));
        if (cwi.c(d, 143)) {
            if (bArr.length < 11) {
                return null;
            }
            try {
                j = jro.e(bArr) * 1000;
            } catch (cwg e) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepOptionUtil", "createSyncOption getCoreSleepTimeStamps TlvException: ", ExceptionUtils.d(e));
                j = 0;
            }
            ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepOptionUtil", "createSyncOption endTime:", Long.valueOf(j));
            return SyncOption.builder().b(true).c("hasUpgradeTime", String.valueOf(j)).c();
        }
        return SyncOption.builder().b(true).c();
    }
}
