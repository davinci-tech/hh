package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class kgw {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, SampleEventProducer> f14362a;
    private static final Map<Integer, SampleEventProducer> b;

    static {
        HashMap hashMap = new HashMap();
        f14362a = hashMap;
        HashMap hashMap2 = new HashMap();
        b = hashMap2;
        hashMap.put(102, new kgz());
        hashMap.put(203, new kgy());
        hashMap.put(204, new kha());
        hashMap.put(202, new kgx());
        hashMap2.put(100, new kgs());
        hashMap2.put(101, new kgu());
        hashMap2.put(102, new kgv());
    }

    public static SampleEventProducer b(int i) {
        ReleaseLogUtil.e("R_LINKAGE_SampleEventFactory", "getProducer operationType ", Integer.valueOf(i));
        Map<Integer, SampleEventProducer> map = f14362a;
        if (map.get(Integer.valueOf(i)) != null) {
            return map.get(Integer.valueOf(i));
        }
        return new kgt();
    }

    public static SampleEventProducer a(int i, String str) {
        ReleaseLogUtil.e("R_LINKAGE_SampleEventFactory", "getProducer operationType ", Integer.valueOf(i), ", replyCode ", str);
        Map<Integer, SampleEventProducer> map = b;
        if (map.get(Integer.valueOf(i)) != null) {
            return map.get(Integer.valueOf(i));
        }
        return new kgt();
    }
}
