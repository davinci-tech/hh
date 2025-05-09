package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.RequestResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.ephemerismanager.callback.BtBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.receiver.NetworkConnectReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.IoUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes.dex */
public class jva implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static jva f14108a;
    private Handler g;
    private HandlerThread j;
    private static final byte[] e = new byte[0];
    private static final Object b = new Object();
    private Map<String, jvb> d = new HashMap(16);
    private BroadcastReceiver c = new BroadcastReceiver() { // from class: jva.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Object[] objArr = new Object[4];
            objArr[0] = "mConnectStateChangedReceiver context is null: ";
            objArr[1] = Boolean.valueOf(context == null);
            objArr[2] = " action is ";
            objArr[3] = intent == null ? Constants.NULL : intent.getAction();
            LogUtil.a("HwEphemerisManager", objArr);
            if (context == null || intent == null || TextUtils.isEmpty(intent.getAction()) || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
            if (deviceInfo == null) {
                LogUtil.h("HwEphemerisManager", "mConnectStateChangedReceiver deviceInfo is null");
                return;
            }
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            LogUtil.a("HwEphemerisManager", "mConnectStateChangedReceiver status is ", Integer.valueOf(deviceConnectState));
            if (deviceConnectState != 3) {
                return;
            }
            jva.this.a(deviceInfo);
        }
    };
    private BtBaseResponseCallback i = new BtBaseResponseCallback() { // from class: jva.4
        @Override // com.huawei.hwdevice.phoneprocess.mgr.ephemerismanager.callback.BtBaseResponseCallback
        public void onSuccess(Object obj, String str) {
            jvb jvbVar = (jvb) jva.this.d.get(str);
            if (jvbVar == null) {
                LogUtil.h("HwEphemerisManager", "FileCallback onSuccess EphemerisDownloadInfo is null");
                return;
            }
            LogUtil.a("HwEphemerisManager", "mFileCallback download success");
            jvbVar.e(3);
            jvbVar.a(false);
            jva.this.b(jvbVar);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.ephemerismanager.callback.BtBaseResponseCallback
        public void onFailure(int i, String str, String str2) {
            jvb jvbVar = (jvb) jva.this.d.get(str2);
            if (jvbVar == null) {
                LogUtil.h("HwEphemerisManager", "FileCallback onFailure EphemerisDownloadInfo is null");
                return;
            }
            if (jvbVar.f() >= 3) {
                LogUtil.a("HwEphemerisManager", "download retry finish");
                jva.this.e(jvbVar);
                return;
            }
            if (jvbVar.h() > jvbVar.e().size() - 1) {
                jva.this.e(jvbVar);
                return;
            }
            jvbVar.b(jvbVar.h() + 1);
            if (jvbVar.h() == jvbVar.e().size()) {
                jvbVar.b(0);
                jvbVar.e(jvbVar.f() + 1);
                if (jvbVar.f() >= 3) {
                    LogUtil.a("HwEphemerisManager", "download retry finish done");
                    jva.this.e(jvbVar);
                    return;
                }
            }
            try {
                jva.this.a(jvbVar.e().get(jvbVar.h()), jva.this.i, str2);
            } catch (IndexOutOfBoundsException e2) {
                LogUtil.b("HwEphemerisManager", "onFailure find IndexOutOfBoundsException,", ExceptionUtils.d(e2));
                sqo.n("onFailure find IndexOutOfBoundsException");
            }
        }
    };
    private cwl f = new cwl();

    private jva() {
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.c, intentFilter, LocalBroadcast.c, null);
        HandlerThread handlerThread = new HandlerThread("HwEphemerisManager");
        this.j = handlerThread;
        handlerThread.start();
        b bVar = new b(this.j.getLooper());
        this.g = bVar;
        NetworkConnectReceiver.bPg_(bVar);
    }

    private void d(jvb jvbVar) {
        jvbVar.d(false);
        jvbVar.a(false);
        jvbVar.a(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(jvb jvbVar) {
        jvbVar.d(true);
        jvbVar.a(true);
    }

    public static jva b() {
        jva jvaVar;
        synchronized (b) {
            if (f14108a == null) {
                f14108a = new jva();
            }
            jvaVar = f14108a;
        }
        return jvaVar;
    }

    public void c() {
        BaseApplication.getContext().unregisterReceiver(this.c);
        d();
        e();
    }

    private static void d() {
        synchronized (b) {
            f14108a = null;
        }
    }

    private void e() {
        HandlerThread handlerThread = this.j;
        if (handlerThread != null) {
            handlerThread.quit();
            this.j = null;
        }
    }

    /* loaded from: classes5.dex */
    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("HwEphemerisManager", "MyHandler but message is null");
                return;
            }
            super.handleMessage(message);
            jvb jvbVar = (jvb) jva.this.d.get(message.obj instanceof String ? (String) message.obj : "");
            if (jvbVar == null) {
                LogUtil.h("HwEphemerisManager", "handleMessage ephemerisInfo is null");
                return;
            }
            int i = message.what;
            LogUtil.a("HwEphemerisManager", "handleMessage message:", Integer.valueOf(i), " waitTime is ", Long.valueOf(jvbVar.k()));
            if (i == 1) {
                jva.this.c(jvbVar);
                return;
            }
            if (i == 2) {
                jva.this.h(jvbVar);
                jva.this.a(jvbVar);
                jvbVar.d(false);
            } else if (i == 4) {
                b(jvbVar);
            } else if (i == 100) {
                a(jvbVar);
            } else {
                if (i != 400) {
                    return;
                }
                jva.this.a(jvbVar.c());
            }
        }

        private void a(jvb jvbVar) {
            boolean n = jvbVar.n();
            boolean l = jvbVar.l();
            int a2 = jvbVar.a();
            LogUtil.a("HwEphemerisManager", "network connected, isDownloading:", Boolean.valueOf(n), ";mIsNeedDownload:", Boolean.valueOf(l), ";currentNetworkConnectedRetryTimes:", Integer.valueOf(a2));
            if (n) {
                jvbVar.d(2);
                return;
            }
            if (l && a2 < 3) {
                sendMessageDelayed(jva.this.bJI_(1, jvbVar.c().getDeviceIdentify()), jvbVar.k());
                jvbVar.d(true);
                jva.this.a(jvbVar);
                jvbVar.d(false);
                jvbVar.a(jvbVar.a() + 1);
            }
        }

        private void b(jvb jvbVar) {
            jvbVar.d(3);
            if (jva.this.g != null) {
                jva.this.g.removeMessages(1, jvbVar.c().getDeviceIdentify());
            }
            jvbVar.d(false);
            jva.this.c(jvbVar);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwEphemerisManager", "getResult data is null.");
            return;
        }
        byte b2 = bArr[1];
        blt.d("HwEphemerisManager", bArr, "getResult dataInfos[1]: ", Integer.valueOf(b2), "; dataInfos is ");
        if (b2 == 1) {
            e(bArr, deviceInfo);
        } else {
            if (b2 != 2) {
                return;
            }
            b(bArr, deviceInfo);
        }
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo) {
        jrp.c();
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("HwEphemerisManager", "parseOperatorRequest messageHex is null");
            return;
        }
        if (d.length() < 4) {
            LogUtil.h("HwEphemerisManager", "parseOperatorRequest messageHex.length() < SERVICE_AND_COMMAND_LENGTH");
            return;
        }
        jvb jvbVar = this.d.containsKey(deviceInfo.getDeviceIdentify()) ? this.d.get(deviceInfo.getDeviceIdentify()) : null;
        if (jvbVar == null) {
            jvbVar = new jvb();
        }
        jvbVar.b(deviceInfo);
        a(deviceInfo, jvbVar, d.substring(4, d.length()));
    }

    private void i(jvb jvbVar) {
        this.g.removeMessages(400, jvbVar.c().getDeviceIdentify());
        this.g.sendMessageDelayed(bJI_(400, jvbVar.c().getDeviceIdentify()), 120000L);
    }

    private void a(DeviceInfo deviceInfo, jvb jvbVar, String str) {
        try {
            Iterator<cwe> it = this.f.a(str).a().iterator();
            while (it.hasNext()) {
                String str2 = "";
                int i = 0;
                for (cwd cwdVar : it.next().e()) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 2) {
                        i = CommonUtil.w(cwdVar.c());
                        ReleaseLogUtil.e("Track_HwEphemerisManager", "operation info is ", Integer.valueOf(i));
                    } else if (w == 3) {
                        jvbVar.d(CommonUtil.y(cwdVar.c()) * 1000);
                        str2 = cwdVar.c();
                        LogUtil.a("HwEphemerisManager", "operation request time is ", Long.valueOf(jvbVar.i()));
                    }
                }
                if (i == 1) {
                    a(deviceInfo);
                    i(jvbVar);
                }
                if (i == 1 && !this.d.containsKey(deviceInfo.getDeviceIdentify())) {
                    if (!g()) {
                        a(100007, deviceInfo);
                        ReleaseLogUtil.d("Track_HwEphemerisManager", "can't download, no network");
                        return;
                    } else {
                        this.d.put(deviceInfo.getDeviceIdentify(), jvbVar);
                        a(100000, deviceInfo);
                        d(jvbVar);
                        c(deviceInfo);
                    }
                } else {
                    if (i != 2 && !this.d.containsKey(deviceInfo.getDeviceIdentify())) {
                        LogUtil.h("HwEphemerisManager", "parseEphemerisTlvs info:", Integer.valueOf(i));
                    }
                    ReleaseLogUtil.d("Track_HwEphemerisManager", "parseEphemerisTlvs The task already exists.");
                    a(str2, jvbVar);
                }
            }
        } catch (cwg unused) {
            LogUtil.b("HwEphemerisManager", "COMMAND_ID_OPERATOR_REQUEST find TlvException");
            sqo.n("COMMAND_ID_OPERATOR_REQUEST find TlvException");
        }
    }

    private boolean g() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            return false;
        }
        if (!PowerKitManager.e().b()) {
            return true;
        }
        ReleaseLogUtil.d("Track_HwEphemerisManager", "can't download! user is sleeping");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        LogUtil.a("HwEphemerisManager", "clearMapItem enter.");
        this.d.remove(deviceInfo.getDeviceIdentify());
        jvf.c().remove(deviceInfo.getDeviceIdentify());
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        jvb jvbVar = this.d.get(deviceInfo.getDeviceIdentify());
        if (jvbVar == null) {
            LogUtil.h("HwEphemerisManager", "parseEphemerisParam ephemerisInfo is null");
            return;
        }
        jvbVar.d(1);
        b(jvbVar);
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d)) {
            a(deviceInfo);
            LogUtil.h("HwEphemerisManager", "parseEphemerisParam messageHex is null");
            return;
        }
        if (d.length() < 4) {
            a(deviceInfo);
            LogUtil.h("HwEphemerisManager", "parseEphemerisParam messageHex.length() < SERVICE_AND_COMMAND_LENGTH");
            return;
        }
        String substring = d.substring(4, d.length());
        juv juvVar = new juv();
        try {
            Iterator<cwe> it = this.f.a(substring).a().iterator();
            while (it.hasNext()) {
                e(it.next(), juvVar, deviceInfo);
            }
            ReleaseLogUtil.e("Track_HwEphemerisManager", "start DownLoad.");
            e(juvVar, jvbVar, deviceInfo);
        } catch (cwg unused) {
            LogUtil.b("HwEphemerisManager", "COMMAND_ID_PARAMETER_CONSULT find TlvException");
            if (CommonUtil.as()) {
                sqo.n("COMMAND_ID_PARAMETER_CONSULT find TlvException");
            }
        }
    }

    private void e(cwe cweVar, juv juvVar, DeviceInfo deviceInfo) {
        String str;
        jvb jvbVar = this.d.get(deviceInfo.getDeviceIdentify());
        if (jvbVar == null) {
            LogUtil.h("HwEphemerisManager", "parseEphemerisTlv ephemerisInfo is null.");
            return;
        }
        for (cwd cwdVar : cweVar.e()) {
            try {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 4) {
                    long y = CommonUtil.y(cwdVar.c()) * 1000;
                    LogUtil.a("HwEphemerisManager", "Consultation deviceTime is", Long.valueOf(y));
                    if (jvbVar.k() >= OpAnalyticsConstants.H5_LOADING_DELAY && jvbVar.k() <= 250000) {
                        jvbVar.c(y);
                    }
                    a(100007, deviceInfo);
                } else if (w == 5) {
                    juvVar.e(CommonUtil.w(cwdVar.c()));
                    LogUtil.a("HwEphemerisManager", "DownloadVersion:", Integer.valueOf(juvVar.e()));
                } else if (w == 6) {
                    if (cwdVar.c() != null) {
                        str = cvx.e(cwdVar.c());
                        juvVar.d(c(str));
                    } else {
                        str = null;
                    }
                    LogUtil.a("HwEphemerisManager", "value(): ", cwdVar.c(), "SonyUrlConfigId :", str);
                } else {
                    LogUtil.h("HwEphemerisManager", "parseEphemerisTlv unknown command.");
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("HwEphemerisManager", "parseEphemerisTlv find NumberFormatException");
                sqo.n("parseEphemerisTlv find NumberFormatException");
            }
        }
        c(cweVar, jvbVar);
    }

    private void c(cwe cweVar, jvb jvbVar) {
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                try {
                    if (CommonUtil.w(cwdVar.e()) == 3) {
                        String e2 = cvx.e(cwdVar.c());
                        LogUtil.a("HwEphemerisManager", "Consultation is", e2);
                        jvbVar.e().add(e2);
                    } else {
                        LogUtil.h("HwEphemerisManager", "parserTlvFather tlv father default case");
                    }
                } catch (NumberFormatException unused) {
                    LogUtil.b("HwEphemerisManager", "parserTlvFather NumberFormatException");
                    sqo.n("parserTlvFather NumberFormatException");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Message bJI_(int i, String str) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = str;
        return obtain;
    }

    private void e(juv juvVar, jvb jvbVar, DeviceInfo deviceInfo) {
        jvbVar.c(juvVar.e());
        LogUtil.a("HwEphemerisManager", "mDownloadVersion:", Integer.valueOf(jvbVar.g()), " DownloadVersion:", Integer.valueOf(juvVar.e()), " mIsDownloading:", Boolean.valueOf(jvbVar.n()), " mUrls is Empty: ", Boolean.valueOf(jvbVar.e().isEmpty()));
        int e2 = juvVar.e();
        if (e2 == 0) {
            if (jvbVar.e().isEmpty()) {
                a(deviceInfo);
                return;
            }
            Handler handler = this.g;
            if (handler == null) {
                LogUtil.h("HwEphemerisManager", "dealParam mHandler is null");
                return;
            }
            handler.sendMessageDelayed(bJI_(1, jvbVar.c().getDeviceIdentify()), jvbVar.k());
            LogUtil.a("HwEphemerisManager", "downloadFile() send timeout timer sWaitTime is ", Long.valueOf(jvbVar.k()));
            if (jvbVar.n()) {
                jvbVar.d(2);
                return;
            } else {
                this.g.removeMessages(2, jvbVar.c().getDeviceIdentify());
                this.g.sendMessage(bJI_(2, jvbVar.c().getDeviceIdentify()));
                return;
            }
        }
        if (e2 == 1 || e2 == 2) {
            if (jvbVar.n()) {
                jvbVar.d(2);
                return;
            } else {
                jvbVar.d(true);
                c(juvVar, jvbVar);
                return;
            }
        }
        if (e2 == 3) {
            if (jvbVar.n()) {
                jvbVar.d(2);
                return;
            } else {
                jvbVar.d(true);
                d(juvVar, jvbVar);
                return;
            }
        }
        jvbVar.d(5);
        c(jvbVar);
    }

    private void d(juv juvVar, final jvb jvbVar) {
        final List<String> a2 = juvVar.a();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HwEphemerisManager", "dealEphDownloadExt but config id list is empty");
        } else {
            jrq.b("HwEphemerisManager", new Runnable() { // from class: jva.3
                @Override // java.lang.Runnable
                public void run() {
                    gkj.b().setContext(BaseApplication.getContext());
                    gkj.b().setSerCountry(GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
                    gki ephRequest = gkj.b().getEphRequest(a2);
                    if (ephRequest == null || !ephRequest.h()) {
                        LogUtil.h("HwEphemerisManager", "dealEphDownloadExt requestInfo is null or data is invalid.");
                        jva.this.b(1, jvbVar);
                        return;
                    }
                    synchronized (jvw.a()) {
                        jvbVar.d().clear();
                    }
                    jvbVar.d(2);
                    jvc.d(ephRequest, new IBaseResponseCallback() { // from class: jva.3.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            jvc.d();
                            jva.this.b(i, jvbVar);
                        }
                    }, jvbVar);
                }
            });
        }
    }

    private void c(juv juvVar, final jvb jvbVar) {
        final List<String> a2 = juvVar.a();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HwEphemerisManager", "dealEphDownload but config id list is empty");
        } else {
            jrq.b("HwEphemerisManager", new Runnable() { // from class: jva.5
                @Override // java.lang.Runnable
                public void run() {
                    String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("getBatchPluginUrl", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
                    LogUtil.a("HwEphemerisManager", "grsUrl:", noCheckUrl);
                    ArrayList arrayList = new ArrayList(16);
                    for (String str : a2) {
                        LogUtil.a("HwEphemerisManager", "dealEphDownload url:", str);
                        arrayList.add(noCheckUrl + str);
                    }
                    synchronized (jvw.a()) {
                        jvbVar.d().clear();
                    }
                    jvbVar.d(2);
                    jvc.b(arrayList, new IBaseResponseCallback() { // from class: jva.5.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            jva.this.b(i, jvbVar);
                        }
                    }, jvbVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, jvb jvbVar) {
        Object[] objArr = new Object[4];
        objArr[0] = "dealSonyDownload tag:";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = " mHandler is ";
        objArr[3] = Boolean.valueOf(this.g == null);
        LogUtil.a("HwEphemerisManager", objArr);
        if (i == 0) {
            if (jvbVar.g() == 2) {
                Handler handler = this.g;
                if (handler != null) {
                    handler.sendMessage(bJI_(4, jvbVar.c().getDeviceIdentify()));
                    return;
                }
                return;
            }
            jvbVar.d(3);
        } else {
            jvbVar.d(4);
        }
        Handler handler2 = this.g;
        if (handler2 != null) {
            handler2.removeMessages(1, jvbVar.c().getDeviceIdentify());
        }
        jvbVar.d(false);
        c(jvbVar);
        ReleaseLogUtil.e("Track_HwEphemerisManager", "DownLoad completed");
    }

    private void c(DeviceInfo deviceInfo) {
        String str = cvx.e(129) + cvx.e(0);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(31);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwEphemerisManager", "5.31.2 parameterConsult send");
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private List<String> c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwEphemerisManager", "sonyUrlConfigId is null.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(16);
        String[] split = str.split(";");
        if (split.length > 0) {
            arrayList.addAll(Arrays.asList(split));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(jvb jvbVar) {
        synchronized (e) {
            jvbVar.d(2);
            jvbVar.e(0);
            int size = jvbVar.e().size();
            LogUtil.a("HwEphemerisManager", "downloadFile mUrls size is:", Integer.valueOf(size), ";mIndex is:", Integer.valueOf(jvbVar.h()));
            if (jvbVar.h() < size) {
                try {
                    a(jvbVar.e().get(jvbVar.h()), this.i, jvbVar.c().getDeviceIdentify());
                } catch (IndexOutOfBoundsException unused) {
                    LogUtil.b("HwEphemerisManager", "downloadFile find IndexOutOfBoundsException");
                    sqo.n("downloadFile find IndexOutOfBoundsException");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(jvb jvbVar) {
        jvbVar.d(4);
        c(jvbVar);
    }

    private void a(String str, jvb jvbVar) {
        String str2 = cvx.e(2) + cvx.e(1) + cvx.e(2);
        String str3 = cvx.e(3) + cvx.e(str.length() / 2) + str;
        String str4 = cvx.e(4) + cvx.e(1) + cvx.e(jvbVar.b());
        String str5 = cvx.e(129) + cvx.d((str2.length() / 2) + (str3.length() / 2) + (str4.length() / 2)) + str2 + str3 + str4;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(31);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str5));
        deviceCommand.setDataLen(cvx.a(str5).length);
        deviceCommand.setmIdentify(jvbVar.c().getDeviceIdentify());
        ReleaseLogUtil.e("Track_HwEphemerisManager", "5.31.1,downloadState is ", Integer.valueOf(jvbVar.b()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(jvb jvbVar) {
        ReleaseLogUtil.e("Track_HwEphemerisManager", "sendFileStatus downloadState is ", Integer.valueOf(jvbVar.b()));
        String str = cvx.e(1) + cvx.e(1) + cvx.e(jvbVar.b());
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(31);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(jvbVar.c().getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        this.g.removeMessages(400, jvbVar.c().getDeviceIdentify());
        if (jvbVar.b() == 5 || jvbVar.b() == 4) {
            a(jvbVar.c());
        }
    }

    private void a(int i, DeviceInfo deviceInfo) {
        byte[] d = new bms().i(127, i).d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(31);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(jvb jvbVar) {
        jvbVar.c(OpAnalyticsConstants.H5_LOADING_DELAY);
        jvbVar.d(0L);
        jvbVar.b(0);
        jvbVar.e().clear();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeMessages(1, jvbVar.c().getDeviceIdentify());
        } else {
            LogUtil.h("HwEphemerisManager", "initMaintenance find handler is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [javax.net.ssl.HttpsURLConnection] */
    public void a(String str, BtBaseResponseCallback btBaseResponseCallback, String str2) {
        Throwable th;
        URISyntaxException e2;
        HttpsURLConnection httpsURLConnection;
        String str3;
        HttpsURLConnection httpsURLConnection2 = null;
        try {
            try {
                try {
                    boolean isEmpty = TextUtils.isEmpty(str);
                    str3 = str;
                    if (!isEmpty) {
                        boolean equals = str.equals(i());
                        str3 = str;
                        if (equals) {
                            str3 = j();
                        }
                    }
                    LogUtil.a("HwEphemerisManager", "requestFile fileUrl is ", str3);
                } catch (Throwable th2) {
                    th = th2;
                    if (str != 0) {
                        str.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException unused) {
                httpsURLConnection = null;
            } catch (IOException unused2) {
                httpsURLConnection = null;
            } catch (URISyntaxException e3) {
                e2 = e3;
            } catch (SSLHandshakeException unused3) {
            }
            if (new URI(str3).getHost() == null) {
                LogUtil.h("HwEphemerisManager", "requestFile url is error");
                return;
            }
            URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str3).openConnection());
            boolean z = openConnection instanceof HttpsURLConnection;
            LogUtil.a("HwEphemerisManager", "isInstance is ", Boolean.valueOf(z));
            if (z) {
                httpsURLConnection = (HttpsURLConnection) openConnection;
                try {
                    c(httpsURLConnection, btBaseResponseCallback, str2);
                    httpsURLConnection2 = httpsURLConnection;
                } catch (MalformedURLException unused4) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "requestFile find MalformedURLException callback:";
                    objArr[1] = Boolean.valueOf(btBaseResponseCallback == null);
                    LogUtil.b("HwEphemerisManager", objArr);
                    if (btBaseResponseCallback != null) {
                        btBaseResponseCallback.onFailure(0, null, str2);
                    }
                    if (httpsURLConnection == null) {
                        return;
                    }
                    httpsURLConnection.disconnect();
                    return;
                } catch (SSLHandshakeException unused5) {
                    httpsURLConnection2 = httpsURLConnection;
                    LogUtil.b("HwEphemerisManager", "requestFile find SSLHandshakeException");
                    if (httpsURLConnection2 == null) {
                        return;
                    }
                    httpsURLConnection2.disconnect();
                } catch (IOException unused6) {
                    Object[] objArr2 = new Object[2];
                    objArr2[0] = "requestFile find IOException callback: ";
                    objArr2[1] = Boolean.valueOf(btBaseResponseCallback == null);
                    LogUtil.b("HwEphemerisManager", objArr2);
                    if (btBaseResponseCallback != null) {
                        btBaseResponseCallback.onFailure(0, null, str2);
                    }
                    if (httpsURLConnection == null) {
                        return;
                    }
                    httpsURLConnection.disconnect();
                    return;
                } catch (URISyntaxException e4) {
                    e2 = e4;
                    httpsURLConnection2 = httpsURLConnection;
                    LogUtil.b("HwEphemerisManager", "requestFile find URISyntaxException:", ExceptionUtils.d(e2));
                    if (httpsURLConnection2 == null) {
                        return;
                    }
                    httpsURLConnection2.disconnect();
                }
            }
            if (httpsURLConnection2 == null) {
                return;
            }
            httpsURLConnection2.disconnect();
        } catch (Throwable th3) {
            th = th3;
            str = 0;
        }
    }

    private void c(HttpsURLConnection httpsURLConnection, BtBaseResponseCallback btBaseResponseCallback, String str) {
        FileOutputStream fileOutputStream;
        jvb jvbVar = this.d.get(str);
        if (jvbVar == null) {
            LogUtil.h("HwEphemerisManager", "broadcomNetConnect currentInfo is null");
            return;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                httpsURLConnection.setHostnameVerifier(jbx.c);
                httpsURLConnection.setRequestProperty("Charset", "UTF-8");
                httpsURLConnection.setConnectTimeout(300000);
                httpsURLConnection.setReadTimeout(300000);
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.connect();
                RequestResult requestResult = new RequestResult(httpsURLConnection);
                LogUtil.a("HwEphemerisManager", "broadcomNetConnect result：", Integer.valueOf(requestResult.getStatusCode()));
                if (requestResult.getStatusCode() == 200) {
                    byte[] asByte = requestResult.asByte();
                    String str2 = BaseApplication.getContext().getFilesDir() + File.separator + (knl.a(jvbVar.c().getDeviceIdentify()) + "gpslocation.dat");
                    blt.d("HwEphemerisManager", asByte, "requestFile path is ", str2, "; requestFile data: ");
                    File file = new File(str2);
                    if (!file.exists()) {
                        LogUtil.a("HwEphemerisManager", "requestFile flag:", Boolean.valueOf(file.createNewFile()));
                    }
                    fileOutputStream = FileUtils.openOutputStream(file);
                    try {
                        fileOutputStream.write(asByte, 0, asByte.length);
                        jvbVar.d(3);
                        fileOutputStream.close();
                        sqc.c(file.getPath(), "S2", 0);
                        c(jvbVar);
                        Object[] objArr = new Object[2];
                        objArr[0] = "requestFile send status callback:";
                        objArr[1] = Boolean.valueOf(btBaseResponseCallback == null);
                        LogUtil.a("HwEphemerisManager", objArr);
                        if (btBaseResponseCallback != null) {
                            btBaseResponseCallback.onSuccess(null, str);
                        }
                        fileOutputStream2 = fileOutputStream;
                    } catch (IOException unused) {
                        fileOutputStream2 = fileOutputStream;
                        LogUtil.b("HwEphemerisManager", "broadcomNetConnect find IOException");
                        IoUtils.e(fileOutputStream2);
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.e(fileOutputStream);
                        throw th;
                    }
                } else if (btBaseResponseCallback != null) {
                    btBaseResponseCallback.onFailure(0, null, str);
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (IOException unused2) {
        }
        IoUtils.e(fileOutputStream2);
    }

    public Map<String, jvb> a() {
        return this.d;
    }

    private String i() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainbroadoldurl") + "/7day/v6/latest/lto.dat";
    }

    private String j() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainbroadnewurl") + "/7day/v6/latest/lto.dat";
    }
}
