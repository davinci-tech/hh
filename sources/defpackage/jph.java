package defpackage;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.jpg;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class jph {

    /* renamed from: a, reason: collision with root package name */
    private static jpg.b f14014a;
    private static String b;
    private static boolean e;

    public static void bIM_(final Activity activity, final int i) {
        if (!CommonUtil.as() || activity == null) {
            return;
        }
        ThreadPoolManager.d().c("WaterMarkUtils", new Runnable() { // from class: jpj
            @Override // java.lang.Runnable
            public final void run() {
                jph.bIJ_(activity, i);
            }
        });
    }

    static /* synthetic */ void bIJ_(final Activity activity, final int i) {
        e();
        activity.runOnUiThread(new Runnable() { // from class: jpl
            @Override // java.lang.Runnable
            public final void run() {
                jpg.bIG_(jph.f14014a, activity, i);
            }
        });
    }

    public static void bIN_(final Activity activity, final ViewGroup viewGroup) {
        if (!CommonUtil.as() || activity == null) {
            return;
        }
        ThreadPoolManager.d().c("WaterMarkUtils", new Runnable() { // from class: jpm
            @Override // java.lang.Runnable
            public final void run() {
                jph.bIL_(activity, viewGroup);
            }
        });
    }

    static /* synthetic */ void bIL_(final Activity activity, final ViewGroup viewGroup) {
        e();
        activity.runOnUiThread(new Runnable() { // from class: jpk
            @Override // java.lang.Runnable
            public final void run() {
                jpg.bIH_(jph.f14014a, activity, viewGroup);
            }
        });
    }

    private static void e() {
        String b2 = jpi.b(d(), Utils.i() ? LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) : "");
        boolean v = nsn.v(BaseApplication.getContext());
        if (v != e || (!TextUtils.isEmpty(b2) && (TextUtils.isEmpty(b) || !b.equals(b2)))) {
            LogUtil.a("WaterMarkUtils", "sWaterTextString is change, Text: ", b2);
            b = b2;
            e = v;
            f14014a = jpg.d(b2, v);
            return;
        }
        LogUtil.h("WaterMarkUtils", "sWaterTextString is not change, Text: ", b2);
    }

    private static String d() {
        List<DeviceInfo> b2 = jfq.c().b(HwGetDevicesMode.ALL_DEVICES, (HwGetDevicesParameter) null, "WaterMarkUtils");
        if (b2 == null || b2.isEmpty()) {
            return "";
        }
        Collections.sort(b2, new Comparator<DeviceInfo>() { // from class: jph.1
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
                if (deviceInfo == null || deviceInfo2 == null) {
                    return 0;
                }
                if (deviceInfo2.getLastConnectedTime() > deviceInfo.getLastConnectedTime()) {
                    return 1;
                }
                return deviceInfo2.getLastConnectedTime() < deviceInfo.getLastConnectedTime() ? -1 : 0;
            }
        });
        String deviceIdentify = b2.get(0).getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            return deviceIdentify;
        }
        String replace = deviceIdentify.replace(":", "");
        int length = replace.length();
        return length > 4 ? replace.substring(length - 4, length) : replace;
    }
}
