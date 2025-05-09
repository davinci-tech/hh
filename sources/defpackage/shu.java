package defpackage;

import android.content.Context;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes7.dex */
public class shu extends HwBaseManager {
    private static volatile shu c;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f17063a;

    private shu(Context context) {
        super(context);
        this.f17063a = context;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10007;
    }

    public static shu b() {
        if (c == null) {
            synchronized (e) {
                if (c == null) {
                    c = new shu(BaseApplication.getContext());
                }
            }
        }
        return c;
    }

    public void c(boolean z) {
        LogUtil.a("UpToGoogleFitMgr", "setConnectGoogleStatus,isLogin: ", Boolean.valueOf(z));
        SharedPreferenceManager.e(this.f17063a, String.valueOf(10007), "key_google_fit_connect_status", String.valueOf(z), (StorageParams) null);
    }

    public boolean c() {
        return "true".equals(SharedPreferenceManager.b(this.f17063a, String.valueOf(10007), "key_google_fit_connect_status"));
    }
}
