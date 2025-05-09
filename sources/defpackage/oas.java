package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.log.ILogHealth;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class oas implements ILogHealth {
    @Override // com.huawei.maps.offlinedata.health.log.ILogHealth
    public void d(String str, String str2) {
        LogUtil.c("OfflineDataMap", str, ": ", str2);
    }

    @Override // com.huawei.maps.offlinedata.health.log.ILogHealth
    public void i(String str, String str2) {
        LogUtil.a("OfflineDataMap", str, ": ", str2);
    }

    @Override // com.huawei.maps.offlinedata.health.log.ILogHealth
    public void w(String str, String str2) {
        LogUtil.h("OfflineDataMap", str, ": ", str2);
    }

    @Override // com.huawei.maps.offlinedata.health.log.ILogHealth
    public void e(String str, String str2) {
        ReleaseLogUtil.c("R_OfflineDataMap", str, ": ", str2);
    }
}
