package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.operator.HwTagCardOperator;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class jln {
    private static boolean c = false;
    private static final Object d = new Object();
    private static jln e;

    /* renamed from: a, reason: collision with root package name */
    private Context f13940a;
    private SmartClipManager.SmartLoadPluginCallback b = new SmartClipManager.SmartLoadPluginCallback() { // from class: jln.2
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartLoadPluginCallback
        public void onLoadPluginResult(int i) {
            LogUtil.a("HwTagCardManager", "on load plugin result:", Integer.valueOf(i));
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartLoadPluginCallback
        public void onLoadPluginProgress(int i) {
            LogUtil.c("HwTagCardManager", "load plugin progress:", Integer.valueOf(i));
        }
    };

    private jln(Context context) {
        this.f13940a = context;
    }

    public static jln c(Context context) {
        jln jlnVar;
        synchronized (d) {
            if (e == null && context != null) {
                e = new jln(context);
            }
            jlnVar = e;
        }
        return jlnVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.a("HwTagCardManager", "errorCode:", Integer.valueOf(i));
        if (i == -7) {
            a(139004);
        } else if (i == -6) {
            a(139003);
        } else if (i == -2) {
            a(139001);
        } else if (i == 0) {
            c(2);
            c(1);
            a(100000);
            b();
        } else {
            LogUtil.h("HwTagCardManager", "unknown error");
        }
        c = false;
        String value = AnalyticsValue.TOUCH_TRANSFER_2180006.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", Integer.valueOf(i));
        DeviceInfo a2 = jpt.a("HwTagCardManager");
        if (a2 != null) {
            hashMap.put("deviceModel", a2.getDeviceModel());
        }
        ixx.d().d(this.f13940a, value, hashMap, 0);
        HwWatchFaceUtil.b(hashMap, value);
    }

    private void b() {
        if (Utils.o() || CommonUtil.bf()) {
            return;
        }
        new Handler(this.f13940a.getMainLooper()).post(new Runnable() { // from class: jln.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwTagCardManager", "startLoadSmartPlugin message");
                SmartClipManager.e(jln.this.f13940a).a(jln.this.b);
            }
        });
    }

    private void c(int i) {
        jlo.d(this.f13940a).d(i, new IBaseResponseCallback() { // from class: jln.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("HwTagCardManager", "setTagStatus errorCode is:", Integer.valueOf(i2));
            }
        });
    }

    private void a(int i) {
        LogUtil.a("HwTagCardManager", "setOpenCardResult:", Integer.valueOf(i));
        jlo.d(this.f13940a).e(i);
    }

    public void d() {
        if (c) {
            LogUtil.a("HwTagCardManager", "opening card is true");
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(this.f13940a);
        if (!loginInit.getIsLogined() || loginInit.getAccountInfo(1008) == null || TextUtils.isEmpty(loginInit.getAccountInfo(1008).trim())) {
            LogUtil.b("R_HwTagCardManager", "user not login");
            a(139002);
        } else {
            jdx.b(new Runnable() { // from class: jln.4
                @Override // java.lang.Runnable
                public void run() {
                    boolean unused = jln.c = true;
                    LogUtil.a("HwTagCardManager", "open Card");
                    int d2 = new HwTagCardOperator(jln.this.f13940a).d();
                    LogUtil.a("R_HwTagCardManager", "tagCardOperator errorCode:", Integer.valueOf(d2));
                    jln.this.e(d2);
                }
            });
        }
    }
}
