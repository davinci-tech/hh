package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class kgo {
    private static final Map<Long, EventProcessor> c;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put(800400014L, new kgq());
        hashMap.put(800400002L, new kgn());
        hashMap.put(800400003L, new kgr());
        hashMap.put(800400004L, new kgp());
        hashMap.put(800400005L, new kgm());
    }

    public static EventProcessor a(Long l) {
        ReleaseLogUtil.e("R_LINKAGE_Event_ProcessorFactory", "getProcessor eventId: ", l);
        Map<Long, EventProcessor> map = c;
        if (map.get(l) != null) {
            return map.get(l);
        }
        return new kgj();
    }
}
