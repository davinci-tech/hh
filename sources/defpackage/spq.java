package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterResultReceiver;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.ParserInterface;
import com.huawei.unitedevice.p2p.IdentityInfo;
import defpackage.spn;
import health.compact.a.LogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class spq implements ParserInterface {
    private static final spq d = new spq();
    private HandlerThread b;
    private Handler e;

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, SendCallback> f17208a = new ConcurrentHashMap(16);
    private Map<Integer, PingCallback> c = new ConcurrentHashMap(16);

    private spq() {
        this.b = null;
        HandlerThread handlerThread = new HandlerThread("P2pResponseManager");
        this.b = handlerThread;
        handlerThread.start();
        this.e = new Handler(this.b.getLooper());
    }

    public void b(int i, SendCallback sendCallback) {
        if (sendCallback != null) {
            this.f17208a.put(Integer.valueOf(i), sendCallback);
        }
    }

    public void d(int i, PingCallback pingCallback) {
        if (pingCallback != null) {
            this.c.put(Integer.valueOf(i), pingCallback);
        }
    }

    public void a(DeviceInfo deviceInfo, spp sppVar) {
        int e = sppVar.e();
        LogUtil.c("P2pResponseManager", "response getSubCmd: ", Integer.valueOf(sppVar.i()), " response getSrcPkgName: ", sppVar.j(), " response getDstPkgName: ", sppVar.d());
        if (sppVar.i() == 3) {
            c(sppVar.c(), e);
        } else if (sppVar.i() == 2) {
            b(deviceInfo, sppVar);
        } else {
            c(deviceInfo, sppVar);
        }
    }

    private void c(bmi bmiVar, int i) {
        if (bmiVar == null) {
            LogUtil.a("P2pResponseManager", "responseTlv is null");
            return;
        }
        if (i == -1) {
            LogUtil.a("P2pResponseManager", "result sequenceNum is error");
            return;
        }
        String c = bmiVar.c();
        LogUtil.c("P2pResponseManager", "handleResultCallback sequenceNum is ", Integer.valueOf(i), "handleResultCallback result int is ", 203);
        int e = TextUtils.isEmpty(c) ? 203 : bli.e(c);
        if (this.c.containsKey(Integer.valueOf(i))) {
            LogUtil.c("P2pResponseManager", "handleResultCallback is P2pPingCallback");
            this.c.remove(Integer.valueOf(i)).onPingResult(e);
        } else {
            if (this.f17208a.containsKey(Integer.valueOf(i))) {
                LogUtil.c("P2pResponseManager", "handleResultCallback is P2pSendCallback");
                SendCallback remove = this.f17208a.remove(Integer.valueOf(i));
                remove.onSendResult(e);
                if (e == 207) {
                    remove.onSendProgress(100L);
                    return;
                } else {
                    remove.onSendProgress(0L);
                    return;
                }
            }
            LogUtil.a("P2pResponseManager", "handleResultCallback is null");
        }
    }

    private void b(DeviceInfo deviceInfo, spp sppVar) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("P2pResponseManager", "handDeviceSendCommand device or device identify is null");
            return;
        }
        IdentityInfo identityInfo = new IdentityInfo(sppVar.j(), sppVar.h());
        IdentityInfo identityInfo2 = new IdentityInfo(sppVar.d(), sppVar.a());
        LogUtil.c("P2pResponseManager", "handDeviceSendCommand enter handDeviceSendCommand response.getSrcPkgName() ", sppVar.j(), "response.getSrcPkgCert(): ", sppVar.h(), "response.getDstPkgName(): ", sppVar.d(), "response.getDstPkgCert(): ", sppVar.a());
        List<spr> d2 = spo.a().d(deviceInfo.getDeviceMac(), identityInfo, identityInfo2);
        LogUtil.c("P2pResponseManager", "receivers size is ", Integer.valueOf(d2.size()));
        if (d2.isEmpty()) {
            LogUtil.a("P2pResponseManager", "handDeviceSendCommand receiver is null");
            a(deviceInfo, sppVar, 206);
            return;
        }
        byte[] b = sppVar.b();
        if (b == null || TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            LogUtil.a("P2pResponseManager", "handDeviceSendCommand message or package name is null");
            return;
        }
        if (d2.isEmpty()) {
            a(deviceInfo, sppVar, 206);
            return;
        }
        spn.b bVar = new spn.b();
        bVar.c(b);
        spn e = bVar.e();
        for (spr sprVar : d2) {
            a(deviceInfo, sppVar, a.w);
            if (sprVar.e() != null) {
                LogUtil.c("P2pResponseManager", "entry onReceiveMessage receiver processName is ", sprVar.b());
                try {
                    sprVar.e().onReceiveMessage(deviceInfo, e);
                    LogUtil.c("P2pResponseManager", "onReceiveMessage success! ");
                } catch (Exception e2) {
                    LogUtil.e("P2pResponseManager", "onReceiveMessage Exception: ", ExceptionUtils.d(e2));
                }
            }
        }
    }

    private void c(final DeviceInfo deviceInfo, final spp sppVar) {
        if (deviceInfo == null) {
            return;
        }
        LogUtil.c("P2pResponseManager", "handDevicePingCommand enter");
        if (!TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            ThreadPoolManager.d().c("P2pResponseManager", new Runnable() { // from class: spq.4
                @Override // java.lang.Runnable
                public void run() {
                    spq.this.a(deviceInfo, sppVar, 205);
                }
            });
        } else {
            LogUtil.a("P2pResponseManager", "handDevicePingCommand device identify is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo, spp sppVar, int i) {
        if (TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("P2pResponseManager", "sendDeviceCommand device identify is null");
            return;
        }
        int e = sppVar.e();
        int i2 = sppVar.i();
        IdentityInfo identityInfo = new IdentityInfo(sppVar.j(), sppVar.h());
        IdentityInfo identityInfo2 = new IdentityInfo(sppVar.d(), sppVar.a());
        if (sppVar.i() == 1) {
            i = a(identityInfo, sppVar, deviceInfo);
            LogUtil.c("P2pResponseManager", "pullUpHealthBusiness response: ", Integer.valueOf(i));
        }
        if ("com.huawei.health.btproxy".equals(identityInfo.getPackageName()) && "hw.unitedevice.btproxy".equals(identityInfo2.getPackageName())) {
            LogUtil.c("P2pResponseManager", "doDeviceResponse is not response for btProxy");
            return;
        }
        bir b = spl.b(e, identityInfo2, identityInfo, i2, i);
        if (b == null) {
            LogUtil.a("P2pResponseManager", "sendDeviceCommand message is null");
            return;
        }
        LogUtil.d("P2pResponseManager", "sendDeviceCommand message is ", b.toString());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(deviceInfo.getDeviceMac());
        uniteDevice.setDeviceInfo(deviceInfo);
        spk.b().b(uniteDevice, b);
    }

    private int a(IdentityInfo identityInfo, spp sppVar, DeviceInfo deviceInfo) {
        String d2 = sppVar.d();
        String packageName = identityInfo.getPackageName();
        LogUtil.c("P2pResponseManager", "pullUpHealthBusiness destPkgName: ", d2, ",getPackageName: ", packageName);
        String[] split = d2.split("\\.");
        if (split.length > 2 && split[2].equals("wakeapp")) {
            byte[] b = sppVar.b();
            if (b == null || b.length == 0) {
                return 204;
            }
            spm d3 = spi.d().d(b);
            LogUtil.c("P2pResponseManager", "p2pPingPayload openType: ", Integer.valueOf(d3.d()));
            String a2 = d3.a();
            if (!TextUtils.isEmpty(a2)) {
                if (a2.contains("from=")) {
                    return d(a2);
                }
                return d(a2 + (a2.contains("?") ? "&" : "?") + "from=watch");
            }
            if (bky.i()) {
                int e = e(d3.d(), d3.c());
                if (e == 0) {
                    LogUtil.c("P2pResponseManager", "p2pPingPayload open target in plugin not exist. ");
                } else {
                    if (e == 1) {
                        LogUtil.c("P2pResponseManager", "p2pPingPayload open target in plugin successful. ");
                        return a.A;
                    }
                    if (e == 2) {
                        LogUtil.c("P2pResponseManager", "p2pPingPayload open target in plugin not download. ");
                        return 204;
                    }
                }
            }
            return b(d3, deviceInfo);
        }
        if (TextUtils.isEmpty(packageName)) {
            return 205;
        }
        b(identityInfo.getPackageName(), deviceInfo);
        return 205;
    }

    private int b(spm spmVar, DeviceInfo deviceInfo) {
        LogUtil.c("P2pResponseManager", "pingActionDispenser: ", Integer.valueOf(spmVar.d()));
        int d2 = spmVar.d();
        if (d2 == 1) {
            if (b(spmVar.c(), spmVar.a(), spmVar.b())) {
                return 205;
            }
            return a.C;
        }
        if (d2 == 2) {
            d(spmVar.c(), spmVar.b(), deviceInfo);
            return 205;
        }
        if (d2 == 3) {
            if (c(spmVar.a())) {
                return 205;
            }
            return a.C;
        }
        if (d2 == 4) {
            return d(spmVar.a());
        }
        if (!TextUtils.isEmpty(spmVar.a())) {
            return d(spmVar.a());
        }
        LogUtil.a("P2pResponseManager", "Can't find the correspondent case. ");
        return 205;
    }

    private void b(final String str, final DeviceInfo deviceInfo) {
        if (this.e == null) {
            LogUtil.a("P2pResponseManager", "mCpHandler is null");
        } else if (TextUtils.isEmpty(str)) {
            LogUtil.a("P2pResponseManager", "pkgName is null");
        } else {
            this.e.post(new Runnable() { // from class: spq.5
                @Override // java.lang.Runnable
                public void run() {
                    String b = spv.b(str);
                    if (TextUtils.isEmpty(b)) {
                        return;
                    }
                    try {
                        Intent intent = new Intent();
                        intent.setAction(b);
                        intent.setPackage(BaseApplication.e().getPackageName());
                        intent.putExtra("packageName", str);
                        LogUtil.c("P2pResponseManager", "service action: ", b, " package: ", str);
                        BaseApplication.e().startService(intent);
                        bmw.e(100073, bmh.b(deviceInfo.getDeviceName()), "", "");
                    } catch (IllegalStateException | SecurityException e) {
                        LogUtil.e("P2pResponseManager", "startService exception ", ExceptionUtils.d(e));
                    }
                }
            });
        }
    }

    private void d(final String str, final String str2, final DeviceInfo deviceInfo) {
        LogUtil.c("P2pResponseManager", "startActivity with serviceName: [", str, "], pkgName: [", str2, "]. ");
        if (this.e == null) {
            LogUtil.a("P2pResponseManager", "mCpHandler is null");
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("P2pResponseManager", "serviceName or pkgName is null");
        } else {
            this.e.post(new Runnable() { // from class: spq.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(str);
                        intent.setClassName(str2, str);
                        BaseApplication.e().startService(intent);
                        bmw.e(100073, bmh.b(deviceInfo.getDeviceName()), "", "");
                    } catch (IllegalStateException e) {
                        LogUtil.e("P2pResponseManager", "startService pkgName IllegalStateException = ", ExceptionUtils.d(e));
                    }
                }
            });
        }
    }

    private boolean b(String str, String str2, String str3) {
        LogUtil.c("P2pResponseManager", "startActivity with activityName: [", str, "], deepLinkUrl: [", str2, "], pkgName: [", str3, "]. ");
        if (TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
                LogUtil.a("P2pResponseManager", "startActivity activityName or pkgName is null. ");
                return false;
            }
            if (str3.equals("com.huawei.health")) {
                LogUtil.c("P2pResponseManager", "startActivity inside activityName: [", str, "], pkgName: [", str3, "]. ");
                try {
                    Intent intent = new Intent();
                    intent.setClassName(str3, str);
                    intent.setFlags(268435456);
                    BaseApplication.e().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    LogUtil.e("P2pResponseManager", "startActivity startH5Page ActivityNotFoundException: ", e.getMessage());
                    return false;
                }
            }
            LogUtil.c("P2pResponseManager", "startActivity outside activityName: [", str, "], pkgName: [", str3, "]. ");
            try {
                Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent2.setComponent(new ComponentName(str3, str));
                intent2.setFlags(268435456);
                BaseApplication.e().startActivity(intent2);
                return true;
            } catch (ActivityNotFoundException e2) {
                LogUtil.e("P2pResponseManager", "startH5Page ActivityNotFoundException: ", e2.getMessage());
                return false;
            }
        }
        d(str2);
        return true;
    }

    private int d(String str) {
        LogUtil.c("P2pResponseManager", "startDeepLink with deepLink: [", str, "]. ");
        if (TextUtils.isEmpty(str) || !(str.startsWith("huaweischeme://healthapp/router/") || str.startsWith("huaweischeme://healthapp/home/"))) {
            return a.C;
        }
        int e = e(str);
        if (e == 0 || e == 1) {
            LogUtil.c("P2pResponseManager", "startDeepLink p2pPingPayload open target in plugin successful. ");
            return a.A;
        }
        if (e != 2) {
            return a.C;
        }
        LogUtil.c("P2pResponseManager", "startDeepLink p2pPingPayload open target in plugin not download. ");
        return 204;
    }

    private int e(String str) {
        final int[] iArr = {-1};
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final int[] iArr2 = {-1};
        AppRouter.zi_(Uri.parse(str)).b((Context) null, new AppRouterResultReceiver() { // from class: spq.1
            @Override // com.huawei.haf.router.AppRouterResultReceiver
            public void c(Guidepost guidepost, int i, String str2) {
                LogUtil.c("P2pResponseManager", "startDeepLink onPluginResult status:", Integer.valueOf(i), ", pluginName=", str2);
                iArr[0] = i;
                countDownLatch.countDown();
                if (i == 2) {
                    countDownLatch.countDown();
                }
            }

            @Override // com.huawei.haf.router.AppRouterResultReceiver
            public void a(Guidepost guidepost, int i) {
                LogUtil.c("P2pResponseManager", "startDeepLink onRouteResult status:", Integer.valueOf(i));
                iArr2[0] = i != 11 ? 1 : 11;
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(500L, TimeUnit.MILLISECONDS)) {
                LogUtil.e("P2pResponseManager", "startDeepLink time out");
            }
        } catch (InterruptedException unused) {
            LogUtil.e("P2pResponseManager", "startDeepLink await InterruptedException! ");
        }
        if (iArr[0] != 2) {
            iArr[0] = iArr2[0];
        }
        return iArr[0];
    }

    private boolean c(String str) {
        LogUtil.c("P2pResponseManager", "startH5Page with url: [", str, "]. ");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("P2pResponseManager", "startH5Page with url is empty. ");
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setFlags(268435456);
            BaseApplication.e().startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtil.e("P2pResponseManager", "startH5Page ActivityNotFoundException: ", e.getMessage());
            return false;
        }
    }

    private int e(int i, String str) {
        LogUtil.c("P2pResponseManager", "checkPluginAvailable in. ");
        int[] iArr = {0};
        if (!TextUtils.isEmpty(str) && (i == 1 || i == 2)) {
            StringBuilder sb = new StringBuilder(256);
            sb.append("huaweischeme://healthapp/HuaweiHealth/startComponent?type=");
            sb.append(i == 1 ? "activity" : "service");
            sb.append("&className=");
            sb.append(str);
            sb.append("&onlyPlugin=true");
            iArr[0] = e(sb.toString());
        }
        return iArr[0];
    }

    public static spq c() {
        return d;
    }

    @Override // com.huawei.unitedevice.hwcommonfilemgr.ParserInterface
    public boolean getResult(DeviceInfo deviceInfo, byte[] bArr) {
        boolean c = spi.d().c(deviceInfo, bArr);
        LogUtil.c("P2pResponseManager", "isP2pResult: ", Boolean.valueOf(c));
        if (!c) {
            return true;
        }
        spp c2 = spi.d().c(bArr);
        if (c2 == null || c2.i() == -1) {
            LogUtil.a("P2pResponseManager", "onDataReceived p2p response is invalid");
            return true;
        }
        boolean c3 = spi.d().c(bArr, c2, this.c);
        LogUtil.c("P2pResponseManager", "isHealthResult: ", Boolean.valueOf(c3));
        if (!c3) {
            return true;
        }
        if (bArr[0] == 57 && bArr[1] == 1) {
            LogUtil.c("P2pResponseManager", "getResult sport bt-proxy");
            c2.e("com.huawei.health.btproxy");
            c2.b("BtproxyDistributeNet");
            c2.c("hw.unitedevice.btproxy");
            c2.a("UniteDeviceManagement");
        }
        a(deviceInfo, c2);
        return false;
    }
}
