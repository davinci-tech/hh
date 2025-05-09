package defpackage;

import com.huawei.ads.adsrec.IEventReportCallback;
import com.huawei.ads.fund.util.JsonUtil;
import com.huawei.openplatform.abl.log.HiAdLog;

/* loaded from: classes2.dex */
public class wf {
    public void c(vf vfVar) {
        IEventReportCallback b = uw.b();
        if (b == null) {
            return;
        }
        if (HiAdLog.isDebugEnable()) {
            JsonUtil.toJsonNoException(vfVar);
        }
        b.eventProcess(vfVar);
    }
}
