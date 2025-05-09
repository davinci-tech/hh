package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearmgr.phoneprocess.adapterapi.WearMgrAdapterApi;

/* loaded from: classes.dex */
public class kkr implements WearMgrAdapterApi {

    /* renamed from: a, reason: collision with root package name */
    private static kkr f14435a;
    private static final Object e = new Object();

    private kkr() {
    }

    public static kkr a() {
        kkr kkrVar;
        synchronized (e) {
            if (f14435a == null) {
                LogUtil.a("WearMgrAdapterImpl", "WearMgrAdapterImpl init.");
                f14435a = new kkr();
            }
            kkrVar = f14435a;
        }
        return kkrVar;
    }
}
