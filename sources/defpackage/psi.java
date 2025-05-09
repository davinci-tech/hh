package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwarkuix.oHBrigdePlugin.health.utils.DeviceUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class psi {
    private static final String[] b = {"e4b0b1d5-2003-4d88-8b5f-c4f64542040b", "a8ba095d-4123-43c4-a30a-0240011c58de"};

    public static void c(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: psq
            @Override // java.lang.Runnable
            public final void run() {
                psi.a(IBaseResponseCallback.this);
            }
        });
    }

    static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("PressureCalibrateUtil", "callback is null in getSupportPressureDevice");
            return;
        }
        ArrayList arrayList = new ArrayList();
        b(arrayList);
        a(arrayList);
        iBaseResponseCallback.d(0, nrv.a(arrayList));
    }

    private static void a(List<e> list) {
        DeviceInfo a2 = jpt.a("PressureCalibrateUtil");
        if (a2 == null || !DeviceUtil.isSupportPressAutoMonitor()) {
            return;
        }
        e eVar = new e();
        eVar.e(a2.getDeviceName());
        eVar.a("huaweischeme://healthapp/router/pressureCalibrate");
        list.add(eVar);
    }

    private static void b(List<e> list) {
        ArrayList<ctk> a2 = cjx.e().a();
        if (CollectionUtils.d(a2)) {
            return;
        }
        Iterator<ctk> it = a2.iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            if (next != null) {
                String productId = next.getProductId();
                if (e(productId)) {
                    e eVar = new e();
                    eVar.a(c(productId));
                    eVar.e(e(next));
                    list.add(eVar);
                }
            }
        }
    }

    private static String e(ctk ctkVar) {
        String m = ctkVar.b().m();
        String str = "";
        if (TextUtils.isEmpty(m)) {
            LogUtil.h("PressureCalibrateUtil", "wiFiDevice sn is null");
            m = "";
        } else if (m.length() < 3) {
            LogUtil.h("PressureCalibrateUtil", "device sn length less than 3");
        } else {
            m = Constants.LINK + m.substring(m.length() - 3);
        }
        dcz d = ResourceManager.e().d(ctkVar.getProductId());
        if (d != null) {
            if (d.e().size() <= 0) {
                str = d.n().b() + System.lineSeparator() + d.n().c();
            } else {
                str = dcx.d(d.t(), d.n().b());
            }
        }
        return str + m;
    }

    private static String c(String str) {
        return "huaweischeme://healthapp/router/wifiPressureCalibrate?productId=" + str + "&useId=" + MultiUsersManager.INSTANCE.getMainUser().i();
    }

    private static boolean e(String str) {
        for (String str2 : b) {
            if (str2.equals(str)) {
                LogUtil.a("PressureCalibrateUtil", "isSupportPressureDevcie()=true ", "productType = ", str);
                return true;
            }
        }
        LogUtil.a("PressureCalibrateUtil", "isSupportPressureDevcie()=false ", "productType = ", str);
        return false;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("deviceName")
        private String f16246a;

        @SerializedName("deepLink")
        private String c;

        private e() {
        }

        public void e(String str) {
            this.f16246a = str;
        }

        public void a(String str) {
            this.c = str;
        }
    }
}
