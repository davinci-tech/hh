package defpackage;

import com.huawei.health.hwhealthlinkage.interactors.BaseInteractor;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.hwbasemgr.IHeartRateCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class duj extends BaseInteractor {

    /* renamed from: a, reason: collision with root package name */
    private int f11843a;

    static class d {
        private static final duj b = new duj();
    }

    private duj() {
        this.f11843a = 0;
        this.mStatusCallback = new IHeartRateCallback() { // from class: duj.4
            @Override // com.huawei.hwbasemgr.IHeartRateCallback
            public void startHeartRateMeasure() {
                duj.this.start();
            }

            @Override // com.huawei.hwbasemgr.IHeartRateCallback
            public void stopHeartRateMeasure() {
                duj.this.stop();
            }
        };
    }

    public static final duj b() {
        return d.b;
    }

    @Override // com.huawei.health.hwhealthlinkage.interactors.BaseInteractor
    public void unRegisterStatusListener() {
        super.unRegisterStatusListener();
        LogUtil.a("HWhealthLinkage_PSInteractor", "unRegisterStatusListener enter");
    }

    @Override // com.huawei.health.hwhealthlinkage.interactors.BaseInteractor
    public void registerStatusListener() {
        LogUtil.a("HWhealthLinkage_PSInteractor", "registerStatusListener enter");
        HeartRateGetterUtil.a().a(this.mStatusCallback);
    }

    @Override // com.huawei.health.hwhealthlinkage.interactors.BaseInteractor
    public void start() {
        LogUtil.a("HWhealthLinkage_PSInteractor", "start enter");
        this.f11843a = 1;
        dum.d().c(true);
        dul.c().j();
    }

    @Override // com.huawei.health.hwhealthlinkage.interactors.BaseInteractor
    public void stop() {
        LogUtil.a("HWhealthLinkage_PSInteractor", "stop enter");
        this.f11843a = 0;
        dum.d().c(false);
        dul.c().b();
    }

    public int e() {
        return this.f11843a;
    }
}
