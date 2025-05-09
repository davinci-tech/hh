package defpackage;

import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kgv implements SampleEventProducer {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer
    public cvp createSampleEvent(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            cvp cvpVar = new cvp();
            cvpVar.a(LocationRequestCompat.PASSIVE_INTERVAL);
            return cvpVar;
        }
        byte[] d = new bms().a(2, 1).a(4, Integer.parseInt(str)).d();
        LogUtil.a("LINKAGE_ReplyStopLinkageEvent", "ReplyStopLinkageEvent replyData: ", cvx.d(d));
        return sampleEventBulid(800400014L, d);
    }
}
