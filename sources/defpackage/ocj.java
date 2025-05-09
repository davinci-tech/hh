package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.nfc.PluginPay;
import com.huawei.wear.healthadapter.HealthAdapter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class ocj extends cuj implements HealthAdapter {
    private static final Object b = new Object();
    private static volatile ocj e;

    private ocj() {
    }

    public static ocj e() {
        ocj ocjVar;
        synchronized (b) {
            if (e == null) {
                e = new ocj();
            }
            ReleaseLogUtil.e("R_HealthAdapterImpl", "getInstance");
            ocjVar = e;
        }
        return ocjVar;
    }

    @Override // defpackage.cuj, com.huawei.wear.healthadapter.HealthAdapter
    public void launchActivity(Context context, Intent intent) {
        ReleaseLogUtil.e("R_HealthAdapterImpl", "launchActivity enter");
        PluginPay.getInstance(context).setAdapter(ixb.p());
        bzu.b().initHealthPayAdapter(BaseApplication.getContext());
        bzu.b().launchActivity(context, intent);
    }
}
