package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class hqd extends hqc {

    /* renamed from: a, reason: collision with root package name */
    private static volatile hqd f13321a;
    private static final Object c = new Object();

    protected hqd(Context context) {
        super(context);
    }

    public static hqd a() {
        hqd hqdVar;
        synchronized (c) {
            LogUtil.a("Track_HiWearEngineHealthMgr", "getInstance()");
            if (f13321a == null) {
                f13321a = new hqd(BaseApplication.getContext());
            }
            hqdVar = f13321a;
        }
        return hqdVar;
    }

    @Override // defpackage.hqc, com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.health.p2p";
    }
}
