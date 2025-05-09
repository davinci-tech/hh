package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.view.EsimCardStatusGetActivity;
import com.huawei.sim.esim.view.EsimManagerActivity;
import com.huawei.sim.esim.view.WirelessManagerActivity;

/* loaded from: classes6.dex */
public class nbq extends mml {

    /* renamed from: a, reason: collision with root package name */
    private static nbq f15238a;
    private boolean d = false;

    private nbq() {
    }

    public static nbq e(Context context) {
        nbq nbqVar;
        synchronized (nbq.class) {
            if (f15238a == null) {
                f15238a = new nbq();
            }
            nbqVar = f15238a;
        }
        return nbqVar;
    }

    public void d(Context context) {
        try {
            context.startActivity(new Intent(context, (Class<?>) WirelessManagerActivity.class));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginSim", "openWirelessActivity ActivityNotFoundException");
        }
    }

    public void b(Context context) {
        if (context instanceof Activity) {
            try {
                context.startActivity(new Intent(context, (Class<?>) EsimManagerActivity.class));
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("PluginSim", "openEsimManagerActivity ActivityNotFoundException");
                return;
            }
        }
        LogUtil.h("PluginSim", "context is not instanceof Activity");
    }

    public void a(Context context) {
        if (context instanceof Activity) {
            try {
                context.startActivity(new Intent(context, (Class<?>) EsimCardStatusGetActivity.class));
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("PluginSim", "openEsimCardStatusGetActivity ActivityNotFoundException");
                return;
            }
        }
        LogUtil.h("PluginSim", "openEsimCardStatusGetActivity context is not instanceof Activity");
    }

    public void c(boolean z) {
        this.d = z;
    }

    public boolean b() {
        return this.d;
    }
}
