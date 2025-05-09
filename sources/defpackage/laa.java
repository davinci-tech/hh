package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class laa extends lag {
    @Override // defpackage.lag, com.huawei.health.device.base.AbstractFitnessClient
    public void connectByMac(boolean z, String str) {
        LogUtil.a("Track_IDEQ_BackgroundFitnessClient", "mac connectByMac:address", str);
        this.f14721a = str;
        try {
            this.e = this.d.getRemoteDevice(str);
            bUl_(this.e);
        } catch (IllegalArgumentException unused) {
            ReleaseLogUtil.c("Track_IDEQ_BackgroundFitnessClient", "mac IllegalArgumentException");
        }
    }
}
