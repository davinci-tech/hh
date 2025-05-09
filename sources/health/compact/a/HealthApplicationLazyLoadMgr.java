package health.compact.a;

import android.content.Context;
import com.huawei.health.lazyload.LazyLoadingMgr;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.gor;

/* loaded from: classes.dex */
public class HealthApplicationLazyLoadMgr implements LazyLoadingMgr {
    @Override // com.huawei.health.lazyload.LazyLoadingMgr
    public void load(Context context) {
        CloudImpl.c(null);
        com.huawei.hwlogsmodel.LogUtil.d(context, c());
        b();
        KeyManager.c();
        HiHealthNativeApi.e(context, new LogApiImpl(), new gor());
    }

    private void b() {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_HealthApplicationLazyLoadMgr", "overseaBetaLogConfig begin");
        if (com.huawei.hwlogsmodel.common.LogConfig.d() && Utils.o()) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(2039), "beta_log_switch");
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_HealthApplicationLazyLoadMgr", "betaLogFlag =", b);
            if (health.compact.a.utils.StringUtils.g(b)) {
                return;
            }
            LogAllowListConfig.e(!Boolean.parseBoolean(b));
            com.huawei.hwlogsmodel.LogUtil.h();
        }
    }

    private com.huawei.hwlogsmodel.common.LogConfig c() {
        String d = EnvironmentUtils.d();
        if (EnvironmentUtils.e()) {
            return LogConfigCenter.b(d);
        }
        if (EnvironmentUtils.b()) {
            com.huawei.hwlogsmodel.LogUtil.a().add(LogConfigCenter.d(d));
            return LogConfigCenter.c(d);
        }
        return LogConfigCenter.e(d);
    }
}
