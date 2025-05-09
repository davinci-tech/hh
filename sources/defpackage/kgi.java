package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class kgi {
    private static final Map<String, SampelBaseHandler> b;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(cvu.class.getName(), new kgh());
        hashMap.put(cvq.class.getName(), new kgl());
        hashMap.put(cvp.class.getName(), new kgk());
    }

    public static SampelBaseHandler b(cvr cvrVar) {
        String name = cvrVar.getClass().getName();
        LogUtil.a("LINKAGE_SampleBaseHandlerFactory", "getHandler sampleBaseClassName: ", name);
        Map<String, SampelBaseHandler> map = b;
        if (map.get(name) != null) {
            return map.get(name);
        }
        return new kge();
    }
}
