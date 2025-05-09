package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.intelligentlifemgr.IntellLifeProviderImpl;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.account.AccountProxy;

/* loaded from: classes5.dex */
public class job {
    private static volatile job b;
    private static final Object c = new Object();

    private job() {
    }

    public static job e() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new job();
                }
            }
        }
        return b;
    }

    public void b() {
        AccountProxy.getInstance(BaseApplication.getContext()).setActivated(true);
        IntellLifeProviderImpl intellLifeProviderImpl = new IntellLifeProviderImpl(BaseApplication.getContext());
        LogUtil.a("IntellLifeProviderTool", "registerProfileChannel setProvider");
        AccountProxy.getInstance(BaseApplication.getContext()).setProvider(intellLifeProviderImpl);
    }

    public void d() {
        AccountProxy.getInstance(BaseApplication.getContext()).accountLogout();
    }
}
