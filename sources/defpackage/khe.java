package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.MagicBuild;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class khe {

    /* renamed from: a, reason: collision with root package name */
    static int f14367a;
    private static khe c;
    private static final Object e = new Object();
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: khe.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 100) {
                LogUtil.a("HwMiddleWearHelper", "MSG_ACTION_CONNECTED_NEGOTIATE_SEND enter");
                if (message.obj instanceof DeviceInfo) {
                    khe.this.e((DeviceInfo) message.obj, true);
                    return;
                }
                return;
            }
            if (i != 101) {
                return;
            }
            boolean a2 = jrg.a();
            LogUtil.a("HwMiddleWearHelper", "handle MSG_ACTION_RETRY_NEGOTIATE_SEND isReceive=", Boolean.valueOf(a2));
            khe.this.bNQ_(message, a2);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void bNQ_(Message message, boolean z) {
        if (z) {
            LogUtil.a("HwMiddleWearHelper", "handle 101 2102 already receive");
            this.d.removeMessages(101);
            jrg.d(false);
            f14367a = 0;
            LogUtil.a("HwMiddleWearHelper", "handle 101 2102 already receive negotiateCount=", 0);
            return;
        }
        jrg.d(false);
        LogUtil.a("HwMiddleWearHelper", "handle 101 start negotiateCount=", Integer.valueOf(f14367a));
        if (f14367a >= 4) {
            LogUtil.a("HwMiddleWearHelper", "handle 101 start negotiateCount is 4");
            this.d.removeMessages(101);
            jrg.d(false);
            jrg.a(false);
            jrg.e(false);
            f14367a = 0;
            LogUtil.a("HwMiddleWearHelper", "handle 101 start negotiateCount negotiateCount=", 0);
            return;
        }
        LogUtil.a("HwMiddleWearHelper", "handle 101 middle negotiateCount is not 4");
        if (message.obj instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) message.obj;
            DeviceCapability deviceCapability = jtc.c().queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwMiddleWearHelper").get(deviceInfo.getDeviceIdentify());
            if (deviceCapability != null && deviceCapability.isSupportMidware() && cvz.c()) {
                int i = f14367a + 1;
                f14367a = i;
                LogUtil.a("HwMiddleWearHelper", "handle 101 send count negotiateCount=", Integer.valueOf(i));
                a(deviceInfo, false);
                LogUtil.a("HwMiddleWearHelper", "handle 101 center start send 101");
                Message obtain = Message.obtain();
                obtain.what = 101;
                obtain.obj = deviceInfo;
                this.d.sendMessageDelayed(obtain, PreConnectManager.CONNECT_INTERNAL);
                LogUtil.a("HwMiddleWearHelper", "handle 101 center end send 101");
            }
        }
    }

    private khe() {
    }

    public static khe b() {
        khe kheVar;
        synchronized (e) {
            if (c == null) {
                c = new khe();
            }
            kheVar = c;
        }
        return kheVar;
    }

    private void a(DeviceInfo deviceInfo, boolean z) {
        boolean e2 = e();
        int b = b(bfg.e);
        String d = knl.d(deviceInfo.getDeviceIdentify());
        int a2 = cvz.a(d);
        LogUtil.a("HwMiddleWearHelper", "enter middleware isClosed: ", Boolean.valueOf(e2), " intelligentStatus: ", Integer.valueOf(b), " clockState: ", Integer.valueOf(a2), " isSetDefaultChannel: ", Boolean.valueOf(z));
        if (a2 == -1) {
            cvz.b(d, true);
            a2 = 1;
        }
        if (z) {
            b(true);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("negotiateDefaultChannel", "true");
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
        }
        khu.a(BaseApplication.getContext()).e(e2, b == 1, a2 == 1, true);
    }

    private int b(String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10001), str);
        LogUtil.a("HwMiddleWearHelper", "getAppPushEnable packageName: ", str, ", value: ", b);
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        return CommonUtil.m(BaseApplication.getContext(), b);
    }

    private boolean e() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10035), "notificationStatus");
        if (!TextUtils.isEmpty(b)) {
            LogUtil.a("HwMiddleWearHelper", "isAuthorizeEnabled value is: ", b);
            try {
                return CommonUtil.h(b) == 1;
            } catch (NumberFormatException unused) {
                LogUtil.b("HwMiddleWearHelper", "isAuthorizeEnabled NumberFormatException");
                return false;
            }
        }
        boolean b2 = jrg.b();
        LogUtil.a("HwMiddleWearHelper", "isAuthorizeEnabled value is null, isEnabled:", Boolean.valueOf(b2));
        if (b2) {
            if (!CommonUtil.as()) {
                c("notificationStatus", 1, false);
            }
            return true;
        }
        if (!CommonUtil.as()) {
            c("notificationStatus", 0, false);
        }
        return false;
    }

    private void c(String str, int i, boolean z) {
        LogUtil.a("HwMiddleWearHelper", "setAuthorizeStatus: ", Integer.valueOf(i), ",isHarmonyOS30SendNotify:", Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10035), str, String.valueOf(i), new StorageParams(0));
        NotificationContentProviderUtil.d(i, z);
    }

    private void b(boolean z) {
        LogUtil.c("HwMiddleWearHelper", "enter setMiddleWearEnabled isEnabled ", Boolean.valueOf(z));
        jrg.a(z);
        jrg.e(z);
        jrg.b(z);
    }

    public void b(final DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_HwMiddleWearHelper", "initProcessAfterDeviceConnect delivered 2101 by phoneService process");
        ThreadPoolManager.d().execute(new Runnable() { // from class: khe.2
            @Override // java.lang.Runnable
            public void run() {
                Message obtain = Message.obtain();
                obtain.what = 100;
                obtain.obj = deviceInfo;
                khe.this.d.sendMessageDelayed(obtain, 1000L);
            }
        });
    }

    public void e(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("HwMiddleWearHelper", "autoSendSynergyCommand deviceInfo is null");
            return;
        }
        boolean z2 = true;
        Map<String, DeviceCapability> queryDeviceCapability = jtc.c().queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwMiddleWearHelper");
        DeviceCapability deviceCapability = (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) ? null : queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null) {
            LogUtil.h("HwMiddleWearHelper", "autoSendSynergyCommand() deviceCapability is null");
            return;
        }
        boolean y = CommonUtil.y(BaseApplication.getContext());
        LogUtil.a("HwMiddleWearHelper", "autoSetUp isSupportMidWare isGooglePlay:", false, ", isNewHonor:", Boolean.valueOf(MagicBuild.f13130a), ", isChinaRom:", Boolean.valueOf(y), ", deviceCapability isSupportMidware:", Boolean.valueOf(deviceCapability.isSupportMidware()));
        if (MagicBuild.f13130a && !y) {
            LogUtil.h("HwMiddleWearHelper", "autoSetUp isSupportMidWare isNewHonor oversea phone return");
            b().b(false);
            return;
        }
        if (deviceCapability.isSupportMidware() && cvz.c()) {
            ReleaseLogUtil.e("DEVMGR_HwMiddleWearHelper", "autoSendCommend() Support middle wear");
            if (z) {
                d();
                f14367a++;
            }
            LogUtil.a("HwMiddleWearHelper", "autoSendCommend support negotiateCount=", Integer.valueOf(f14367a));
            a(deviceInfo, z);
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.obj = deviceInfo;
            this.d.sendMessageDelayed(obtain, PreConnectManager.CONNECT_INTERNAL);
            LogUtil.a("HwMiddleWearHelper", "autoSendCommend support handle 101 sendMsg");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwMiddleWearHelper", "autoSendCommend() not Support middle wear");
        b(false);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("negotiateDefaultChannel", "false");
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
        boolean c2 = cvz.c();
        boolean a2 = khs.a();
        boolean c3 = c();
        LogUtil.a("HwMiddleWearHelper", " autoSetUp not Support midwareSupportHealth: ", Boolean.valueOf(c2), " deviceSupportClockStateDeal: ", Boolean.valueOf(a2), " isSupportSynergyEscape: ", Boolean.valueOf(c3));
        if (c2 && a2 && c3) {
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            String d = knl.d(deviceIdentify);
            int a3 = cvz.a(d);
            if (a3 == -1) {
                cvz.b(d, true);
            } else if (a3 != 1) {
                z2 = false;
            }
            LogUtil.a("HwMiddleWearHelper", "autoSetUp not Support middleWear deviceMac: ", blt.b(deviceIdentify), " encryptDeviceMac: ", d, " clockStateSend: ", Boolean.valueOf(z2));
            khu.a(BaseApplication.getContext()).e(false, false, z2, false);
        }
    }

    public void d() {
        LogUtil.a("HwMiddleWearHelper", "resetNegotiateData start");
        this.d.removeMessages(101);
        f14367a = 0;
        jrg.d(false);
    }

    private boolean c() {
        PackageInfo packageInfo;
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        try {
            if (MagicBuild.f13130a) {
                packageInfo = packageManager.getPackageInfo("com.hihonor.synergy", 128);
            } else {
                packageInfo = packageManager.getPackageInfo("com.huawei.synergy", 128);
            }
            if (packageInfo == null) {
                ReleaseLogUtil.d("DEVMGR_HwMiddleWearHelper", "isMiddleWearSupportHealth packageInfo is null");
                return false;
            }
            String str = packageInfo.versionName;
            ReleaseLogUtil.e("DEVMGR_HwMiddleWearHelper", "deviceVersion is,", str);
            String[] split = str.split("\\.");
            if (split.length < 4) {
                return false;
            }
            return CommonUtils.h(split[0]) == 14 && CommonUtils.h(split[1]) == 0 ? CommonUtils.h(split[3]) >= 11 : CommonUtils.h(split[0]) == 14 && CommonUtils.h(split[1]) == 1 ? CommonUtils.h(split[3]) >= 2 : (CommonUtils.h(split[0]) == 14 && CommonUtils.h(split[1]) >= 2) && CommonUtils.h(split[3]) >= 1;
        } catch (PackageManager.NameNotFoundException unused) {
            ReleaseLogUtil.c("DEVMGR_HwMiddleWearHelper", "isMiddleWearSupportHealth not exist");
            return false;
        }
    }
}
