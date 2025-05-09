package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.baseapi.hiaiengine.HiAiLoadSmartPluginListener;
import com.huawei.health.baseapi.hiaiengine.HiAiSmartClipApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.WatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import defpackage.bzo;
import defpackage.cun;
import defpackage.jdx;
import defpackage.jez;
import defpackage.jfq;
import defpackage.jlo;
import defpackage.jlq;
import defpackage.jls;
import defpackage.jlx;
import defpackage.jlz;
import defpackage.jpr;
import defpackage.jpt;
import defpackage.jqj;
import defpackage.jrt;
import defpackage.knl;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SmartClipManager {

    /* renamed from: a, reason: collision with root package name */
    private static SmartClipManager f6313a;
    private static boolean b;
    private static final Object c = new Object();
    private SmartClipCallback d;
    private Context f;
    private int g;
    private jlz i;
    private boolean n;
    private int o;
    private SmartLoadPluginCallback p;
    private int q;
    private ClipTransferCallback r;
    private int s;
    private int t;
    private jlo v;
    private int w;
    private Timer k = null;
    private final ArrayList<String> j = new ArrayList<>(16);
    private boolean m = false;
    private volatile boolean l = false;
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("SmartClipManager", "SmartClipManager onReceive enter");
            if (context == null || intent == null) {
                LogUtil.h("SmartClipManager", "context or intent is null");
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo == null) {
                    LogUtil.h("SmartClipManager", "deviceInfo is null");
                    return;
                }
                if (deviceInfo.getRelationship() == null || !"followed_relationship".equals(deviceInfo.getRelationship())) {
                    LogUtil.a("SmartClipManager", "mConnectStateChangedReceiver() status = ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    if (deviceInfo.getDeviceConnectState() == 3) {
                        if (!SmartClipManager.this.l || SmartClipManager.this.d == null) {
                            return;
                        }
                        LogUtil.a("SmartClipManager", "onConnectStateChange disconnect");
                        SmartClipManager.this.d.onClipResult(-2, null);
                        SmartClipManager.this.l = false;
                        return;
                    }
                    LogUtil.h("SmartClipManager", "onConnectStateChange state:", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    return;
                }
                LogUtil.a("SmartClipManager", "This device does not have the correspond capability.");
            }
        }
    };
    private IAppTransferFileResultAIDLCallback.Stub e = new IAppTransferFileResultAIDLCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.5
        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) {
            if (!c(str)) {
                SmartClipManager.this.o = i;
                LogUtil.a("SmartClipManager", "onFileTransferState percentage = ", Integer.valueOf(i), "completeCount = ", Integer.valueOf(SmartClipManager.this.s), ", totalCount = ", Integer.valueOf(SmartClipManager.this.w));
                SmartClipManager.this.p();
                return;
            }
            LogUtil.h("SmartClipManager", "onFileTransferState filterResult");
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            if (!c(str)) {
                SmartClipManager.this.d(i);
            } else {
                LogUtil.h("SmartClipManager", "onUpgradeFailed filterResult");
            }
        }

        @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) {
            if (c(str)) {
                LogUtil.h("SmartClipManager", "onFileRespond filterResult");
                return;
            }
            if (i != 1) {
                SmartClipManager.this.d(i);
                return;
            }
            SmartClipManager.o(SmartClipManager.this);
            SmartClipManager.s(SmartClipManager.this);
            SmartClipManager.this.o = 0;
            LogUtil.a("SmartClipManager", "one file transfer successful: ", Integer.valueOf(i), "completedCount = ", Integer.valueOf(SmartClipManager.this.s), ", totalCounts = ", Integer.valueOf(SmartClipManager.this.w));
            SmartClipManager.this.p();
        }

        private boolean c(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (SmartClipManager.this.j == null || SmartClipManager.this.j.isEmpty()) {
                    LogUtil.h("SmartClipManager", "filterResult mClipResultPaths is null or is empty");
                    return true;
                }
                try {
                    String string = new JSONObject(str).getString(ContentResource.FILE_NAME);
                    Iterator it = SmartClipManager.this.j.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).endsWith(string)) {
                            LogUtil.a("SmartClipManager", "filterResult is belongs to mClipResultPath");
                            return false;
                        }
                    }
                    return true;
                } catch (JSONException unused) {
                    LogUtil.b("SmartClipManager", "filterResult JSONException");
                    return true;
                }
            }
            LogUtil.h("SmartClipManager", "filterResult des is empty");
            return true;
        }
    };

    public interface ClipTransferCallback {
        void onTransferProgress(int i, int i2, int i3);

        void onTransferResult(int i, int i2);
    }

    public interface SmartClipCallback {
        void onClipResult(int i, List<String> list);
    }

    public interface SmartLoadPluginCallback {
        void onLoadPluginProgress(int i);

        void onLoadPluginResult(int i);
    }

    static /* synthetic */ int j(SmartClipManager smartClipManager) {
        int i = smartClipManager.g;
        smartClipManager.g = i + 1;
        return i;
    }

    static /* synthetic */ int o(SmartClipManager smartClipManager) {
        int i = smartClipManager.s;
        smartClipManager.s = i + 1;
        return i;
    }

    static /* synthetic */ int s(SmartClipManager smartClipManager) {
        int i = smartClipManager.t;
        smartClipManager.t = i + 1;
        return i;
    }

    private SmartClipManager(Context context) {
        this.f = context;
        this.v = jlo.d(context);
        jez.b(new IBaseResponseCallback() { // from class: jlr
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SmartClipManager.this.a(i, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (i == -1) {
            d(false);
            this.r = null;
            this.d = null;
            this.l = false;
            jfq.c().d(40);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("SmartClipManager", "TransferImagesFromDeviceFailed : ", Integer.valueOf(i));
        int i2 = this.s + 1;
        this.s = i2;
        this.q++;
        if (i == 141000 || i == 141001) {
            r();
            return;
        }
        this.o = 0;
        LogUtil.a("SmartClipManager", "completeCount = ", Integer.valueOf(i2), ", totalCount = ", Integer.valueOf(this.w));
        p();
    }

    public static SmartClipManager e(Context context) {
        SmartClipManager smartClipManager;
        synchronized (c) {
            if (f6313a == null && context != null) {
                f6313a = new SmartClipManager(context);
            }
            smartClipManager = f6313a;
        }
        return smartClipManager;
    }

    public boolean f() {
        HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
        if (hiAiSmartClip == null) {
            return false;
        }
        return hiAiSmartClip.isSupportSmartClip();
    }

    public boolean h() {
        HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
        if (hiAiSmartClip == null) {
            LogUtil.h("SmartClipManager", "hiAiSmartClip is null.");
            return false;
        }
        int smartClipAbility = hiAiSmartClip.getSmartClipAbility(this.f);
        LogUtil.a("SmartClipManager", "SmartClipAbility = ", Integer.valueOf(smartClipAbility));
        return smartClipAbility == 0;
    }

    public void j() {
        LogUtil.a("SmartClipManager", "enter saveDownloadFlag");
        if (CommonUtil.bh() && CommonUtil.ar()) {
            jpr.a("bumpPass");
        }
    }

    public boolean b() {
        return this.l;
    }

    public static boolean e() {
        return b;
    }

    public static void d(boolean z) {
        b = z;
    }

    public void a(SmartLoadPluginCallback smartLoadPluginCallback) {
        final float f;
        if (!f()) {
            LogUtil.h("SmartClipManager", "CallStartLoadPlugin, but not support");
            return;
        }
        if (h()) {
            LogUtil.a("SmartClipManager", "SmartPlugin already exist, don't need download");
            if (smartLoadPluginCallback != null) {
                smartLoadPluginCallback.onLoadPluginResult(0);
                return;
            }
        }
        this.p = smartLoadPluginCallback;
        WatchFaceInfo d = this.v.d();
        if (d == null || d.getWatchFaceWidth() <= 0 || d.getWatchFaceHeight() <= 0) {
            f = 1.0f;
        } else {
            LogUtil.a("SmartClipManager", "GetWatchFaceSizeFromLocal");
            f = d.getWatchFaceWidth() / d.getWatchFaceHeight();
        }
        LogUtil.a("SmartClipManager", "CallLoadPlugin...");
        if (!this.m) {
            HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
            if (hiAiSmartClip != null) {
                hiAiSmartClip.init(BaseApplication.getContext());
            }
            new Handler().postDelayed(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.2
                @Override // java.lang.Runnable
                public void run() {
                    jdx.b(SmartClipManager.this.new a(f));
                }
            }, 500L);
            return;
        }
        LogUtil.h("SmartClipManager", "Already in downloading process");
    }

    public void b(List<String> list, boolean z, boolean z2, SmartClipCallback smartClipCallback) {
        if (list == null) {
            ReleaseLogUtil.d("R_SmartClipManager", "CallSmartClip sourcePath is null");
            if (smartClipCallback != null) {
                smartClipCallback.onClipResult(-1, null);
                return;
            }
            return;
        }
        LogUtil.a("SmartClipManager", "CallSmartClip : anyway = ", Boolean.valueOf(z2), "Receive smart clip request, paths : ", list.toString());
        if (!f() && !z2) {
            ReleaseLogUtil.e("R_SmartClipManager", "PhoneNotSupportSmartClip");
            if (smartClipCallback != null) {
                smartClipCallback.onClipResult(-2, null);
                return;
            }
            return;
        }
        if (this.l) {
            ReleaseLogUtil.e("R_SmartClipManager", "SmartClip task already in process");
            if (smartClipCallback != null) {
                smartClipCallback.onClipResult(-1, null);
                return;
            }
            return;
        }
        if (!h()) {
            ReleaseLogUtil.e("R_SmartClipManager", "SmartClipPlugin not prepared");
        }
        ReleaseLogUtil.e("R_SmartClipManager", "ClipAnyway, begin");
        this.l = true;
        BroadcastManagerUtil.bFC_(this.f, this.h, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        LogUtil.a("SmartClipManager", "smartClip registerReceiver mConnectStateChangedReceiver");
        this.d = smartClipCallback;
        this.n = z2;
        jlz jlzVar = new jlz();
        this.i = jlzVar;
        jlzVar.a(list);
        this.i.e(z);
        m();
    }

    public void e(ArrayList<String> arrayList, ClipTransferCallback clipTransferCallback) {
        ReleaseLogUtil.e("R_SmartClipManager", "Call transferClipResultToDevice");
        if (arrayList == null || arrayList.size() == 0) {
            ReleaseLogUtil.d("R_SmartClipManager", "Call transfer paths is null");
            this.l = false;
            if (clipTransferCallback != null) {
                clipTransferCallback.onTransferResult(-1, 0);
                return;
            }
            return;
        }
        if (!l()) {
            ReleaseLogUtil.e("R_SmartClipManager", "Device disconnect, can not transfer");
            this.l = false;
            w();
            LogUtil.a("SmartClipManager", "transferClipResultToDevice unregisterReceiver mConnectStateChangedReceiver");
            if (clipTransferCallback != null) {
                clipTransferCallback.onTransferResult(-1, arrayList.size());
                return;
            }
            return;
        }
        if (this.l) {
            ReleaseLogUtil.e("R_SmartClipManager", "Already in saving progress!");
            return;
        }
        this.l = true;
        this.r = clipTransferCallback;
        ArrayList<String> arrayList2 = new ArrayList<>(arrayList.size());
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            File file = new File(next);
            if (file.exists() && !file.isDirectory()) {
                arrayList2.add(file.getName());
            } else {
                LogUtil.a("SmartClipManager", "TransferFileNotFound : " + next);
            }
        }
        b(arrayList2, arrayList);
    }

    private void b(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        jlq jlqVar = new jlq();
        jlqVar.b(arrayList);
        jlqVar.b(0);
        jlqVar.c(0);
        jlqVar.e(arrayList.size());
        int e = jlo.d(BaseApplication.getContext()).e();
        int b2 = jlo.d(BaseApplication.getContext()).b();
        ReleaseLogUtil.e("R_SmartClipManager", "imageType : ", Integer.valueOf(e), " , imageOption：", Integer.valueOf(b2));
        if (e == 2) {
            if (b2 == 2) {
                a(arrayList2);
            } else {
                b(arrayList2);
            }
        }
        this.w = arrayList.size();
        this.s = 0;
        this.t = 0;
        this.q = 0;
        this.o = 0;
        b(jlqVar, arrayList.size());
    }

    private void a(ArrayList<String> arrayList) {
        try {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next.lastIndexOf(".") != -1) {
                    String str = next.substring(0, next.lastIndexOf(".")) + WatchFaceConstant.BIN_SUFFIX;
                    LogUtil.c("SmartClipManager", "create16BitBinBackground, bin path:", str);
                    jls.b(next, str, 2);
                }
            }
        } catch (ConcurrentModificationException unused) {
            LogUtil.b("SmartClipManager", "create16BitBinBackground, ConcurrentModificationException");
        }
    }

    private void b(ArrayList<String> arrayList) {
        try {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next.lastIndexOf(".") != -1) {
                    String str = next.substring(0, next.lastIndexOf(".")) + WatchFaceConstant.BIN_SUFFIX;
                    LogUtil.c("SmartClipManager", "bin path:", str);
                    jrt.b().e(next, str);
                }
            }
        } catch (ConcurrentModificationException unused) {
            LogUtil.b("SmartClipManager", "ConcurrentModificationException");
        }
    }

    private boolean l() {
        DeviceInfo a2 = jpt.a("SmartClipManager");
        if (a2 != null && a2.getDeviceConnectState() == 2) {
            return true;
        }
        LogUtil.h("SmartClipManager", "device disconnected");
        return false;
    }

    private void b(jlq jlqVar, final int i) {
        ReleaseLogUtil.e("R_SmartClipManager", "Call savePhotoInfo");
        b(TransferFileReqType.DEVICE_REGISTRATION);
        this.v.b(jlqVar, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_SmartClipManager", "OnSaveResponse: code = ", Integer.valueOf(i2));
                if (i2 == 101) {
                    SmartClipManager.this.b(TransferFileReqType.DEVICE_UNREGISTRATION);
                    LogUtil.a("SmartClipManager", "TransferFileToDeviceSuccess");
                    SmartClipManager.this.w = i;
                    SmartClipManager smartClipManager = SmartClipManager.this;
                    smartClipManager.s = smartClipManager.w;
                    SmartClipManager.this.o = 0;
                    SmartClipManager.this.b(obj instanceof Integer ? ((Integer) obj).intValue() : 0);
                    return;
                }
                if (i2 != 111) {
                    SmartClipManager.this.b(TransferFileReqType.DEVICE_UNREGISTRATION);
                    LogUtil.a("SmartClipManager", "TransferFileToDeviceFailed");
                    SmartClipManager.this.r();
                    SmartClipManager.this.l = false;
                    SmartClipManager.this.w();
                    LogUtil.a("SmartClipManager", "savePhotoInfo unregisterReceiver mConnectStateChangedReceiver");
                    return;
                }
                LogUtil.a("SmartClipManager", "SaveToDevice, should transfer file...");
                SmartClipManager.this.b(TransferFileReqType.DEVICE_REGISTRATION);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(TransferFileReqType transferFileReqType) {
        jqj jqjVar = new jqj();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(3);
        jqjVar.e(arrayList);
        jqjVar.c(transferFileReqType);
        jfq.c().d(TransferBusinessType.FIVE_FORTY, jqjVar, this.e);
    }

    private void m() {
        WatchFaceInfo d = this.v.d();
        boolean c2 = jlo.d(BaseApplication.getContext()).c();
        if (d != null && d.getWatchFaceWidth() > 0 && d.getWatchFaceHeight() > 0 && c2) {
            LogUtil.a("SmartClipManager", "LocalHasDeviceInfo");
            this.i.e(d.getWatchFaceWidth(), d.getWatchFaceHeight());
            i();
        } else {
            ReleaseLogUtil.e("R_SmartClipManager", "GetDeviceInfoFromDeviceFirst");
            g();
            this.v.a(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("R_SmartClipManager", "OnResponseDevice code = ", Integer.valueOf(i));
                    if (SmartClipManager.this.k != null) {
                        SmartClipManager.this.k.cancel();
                        SmartClipManager.this.k = null;
                    }
                    if (i != 101) {
                        if (SmartClipManager.this.d != null) {
                            LogUtil.a("SmartClipManager", "FailedToGetDeviceInfo,CallbackClipFailed");
                            SmartClipManager.this.d.onClipResult(-1, null);
                        }
                        SmartClipManager.this.l = false;
                        SmartClipManager.this.w();
                        LogUtil.a("SmartClipManager", "getOutputSize unregisterReceiver mConnectStateChangedReceiver");
                        return;
                    }
                    LogUtil.a("SmartClipManager", "GetDeviceInfoFromDeviceSuccess");
                    WatchFaceInfo d2 = SmartClipManager.this.v.d();
                    SmartClipManager.this.i.e(d2.getWatchFaceWidth(), d2.getWatchFaceHeight());
                    SmartClipManager.this.i();
                }
            });
        }
    }

    private void g() {
        synchronized (this) {
            LogUtil.a("SmartClipManager", "getDeviceInfoTimeTask enter");
            Timer timer = this.k;
            if (timer != null) {
                timer.cancel();
                this.k = null;
            }
            Timer timer2 = new Timer("SmartClipManager");
            this.k = timer2;
            timer2.schedule(new TimerTask() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.10
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (SmartClipManager.this.d != null) {
                        ReleaseLogUtil.e("R_SmartClipManager", "FailedToGetDeviceInfo timeout");
                        SmartClipManager.this.d.onClipResult(-3, null);
                    }
                    SmartClipManager.this.l = false;
                    SmartClipManager.this.w();
                }
            }, 5000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("SmartClipManager", "DidGetOutputSize : {", Integer.valueOf(this.i.e()), ", ", Integer.valueOf(this.i.a()), "}", "isPrepared = ", Boolean.valueOf(h()), ", clipAnyway = ", Boolean.valueOf(this.n));
        this.j.clear();
        HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
        if (hiAiSmartClip != null) {
            hiAiSmartClip.getSmartClipAbility(this.f);
        }
        s();
        if (hiAiSmartClip != null && hiAiSmartClip.isSupportSmartClip()) {
            this.v.c(1);
            x();
            return;
        }
        if (!this.n) {
            Object[] objArr = new Object[2];
            objArr[0] = "CallbackFailed = ";
            objArr[1] = Boolean.valueOf(this.d != null);
            LogUtil.a("SmartClipManager", objArr);
            SmartClipCallback smartClipCallback = this.d;
            if (smartClipCallback != null) {
                smartClipCallback.onClipResult(1, null);
            }
            this.l = false;
            w();
            LogUtil.a("SmartClipManager", "didGetOutputSize unregisterReceiver mConnectStateChangedReceiver");
            return;
        }
        this.v.c(1);
        t();
    }

    private void s() {
        long currentTimeMillis = System.currentTimeMillis();
        for (String str : this.i.h()) {
            currentTimeMillis++;
            String str2 = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date(currentTimeMillis)) + ".png";
            this.i.c().add(this.i.d() + str2);
            LogUtil.a("SmartClipManager", "preset for src:", str, "output:", str2);
        }
    }

    private void x() {
        ReleaseLogUtil.e("R_SmartClipManager", "Call runSmartClipTask");
        jdx.b(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.9
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SmartClipManager", "smart ClipRunnable");
                float e = (SmartClipManager.this.i.e() <= 0 || SmartClipManager.this.i.a() <= 0) ? 1.0f : SmartClipManager.this.i.e() / SmartClipManager.this.i.a();
                LogUtil.a("SmartClipManager", "Begin smart clip task...");
                synchronized (SmartClipManager.c) {
                    SmartClipManager.this.g = 0;
                }
                HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
                hiAiSmartClip.prepare(SmartClipManager.this.f, e);
                if (SmartClipManager.this.i.h() != null) {
                    for (int i = 0; i < SmartClipManager.this.i.h().size(); i++) {
                        SmartClipManager.this.a(SmartClipManager.this.i.h().get(i), SmartClipManager.this.i.c().get(i));
                    }
                }
                while (true) {
                    synchronized (SmartClipManager.c) {
                        if (SmartClipManager.this.i.h() != null) {
                            if (SmartClipManager.this.g >= SmartClipManager.this.i.h().size()) {
                            }
                        } else {
                            LogUtil.h("SmartClipManager", "mClipOptions.getSourcePaths() == null");
                        }
                    }
                }
                SmartClipManager.this.l = false;
                SmartClipManager.this.q();
                hiAiSmartClip.release();
            }
        });
    }

    private void t() {
        ReleaseLogUtil.e("R_SmartClipManager", "runCommonClipTask");
        synchronized (c) {
            this.g = 0;
        }
        jdx.b(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.6
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SmartClipManager", "common clip runnable");
                for (int i = 0; i < SmartClipManager.this.i.h().size(); i++) {
                    String str = SmartClipManager.this.i.h().get(i);
                    String str2 = SmartClipManager.this.i.c().get(i);
                    LogUtil.a("SmartClipManager", "common clip one image");
                    SmartClipManager.this.e(str, str2);
                    synchronized (SmartClipManager.c) {
                        SmartClipManager.j(SmartClipManager.this);
                    }
                    LogUtil.a("SmartClipManager", "common clip one image done");
                }
                while (true) {
                    synchronized (SmartClipManager.c) {
                        if (SmartClipManager.this.g >= SmartClipManager.this.i.h().size()) {
                            ReleaseLogUtil.e("R_SmartClipManager", "All common task clip done");
                            SmartClipManager.this.l = false;
                            SmartClipManager.this.w();
                            LogUtil.a("SmartClipManager", "runCommonClipTask unregisterReceiver mConnectStateChangedReceiver");
                            SmartClipManager.this.q();
                            return;
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        try {
            this.f.unregisterReceiver(this.h);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("SmartClipManager", "unregisterReceiver IllegalArgumentException");
        } catch (IllegalStateException unused2) {
            LogUtil.b("SmartClipManager", "unregisterReceiver IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        final Bitmap bIv_ = jlx.bIv_(str, null);
        if (bIv_ == null) {
            ReleaseLogUtil.d("R_SmartClipManager", "prepared bitmap is null");
            synchronized (c) {
                this.g++;
            }
            return;
        }
        final jlz jlzVar = new jlz();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        jlzVar.a(arrayList);
        jlzVar.d(this.i.d());
        jlzVar.e(this.i.e(), this.i.a());
        jlzVar.c().add(str2);
        ReleaseLogUtil.e("R_SmartClipManager", "Do smart clip");
        int[] iArr = {this.i.e(), this.i.a()};
        HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
        if (hiAiSmartClip != null) {
            Rect smartCropRect = hiAiSmartClip.getSmartCropRect(bIv_, iArr);
            LogUtil.a("SmartClipManager", "getSmart box : {", Integer.valueOf(smartCropRect.left), ", ", Integer.valueOf(smartCropRect.top), ", ", Integer.valueOf(smartCropRect.right), ", ", Integer.valueOf(smartCropRect.bottom), "}");
            jlzVar.bIk_(smartCropRect);
        }
        jdx.b(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.8
            @Override // java.lang.Runnable
            public void run() {
                jlx.bIo_(bIv_, jlzVar);
                ReleaseLogUtil.e("R_SmartClipManager", "Clip one image done");
                if (jlzVar.c().size() > 0) {
                    synchronized (SmartClipManager.c) {
                        LogUtil.a("SmartClipManager", "AddClipped path to Result : ", jlzVar.c().get(0));
                        SmartClipManager.this.b(jlzVar.c().get(0));
                        SmartClipManager.this.j.add(jlzVar.c().get(0));
                    }
                }
                synchronized (SmartClipManager.c) {
                    SmartClipManager.j(SmartClipManager.this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        File file = new File(o() + str.substring(str.lastIndexOf("/") + 1));
        LogUtil.a("SmartClipManager", "transferFilePath is, ", file);
        try {
            FileUtils.d(new File(str), file);
        } catch (IOException e) {
            ReleaseLogUtil.c("R_SmartClipManager", "copyOneHopImageToTransferDir copy file IOException:", ExceptionUtils.d(e));
        }
    }

    private String o() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "R_SmartClipManager");
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_SmartClipManager", "getSourceFilePath currentDevice is null");
            return "";
        }
        String d = knl.d(deviceInfo.getDeviceIdentify());
        String replaceAll = d.replaceAll("[^a-zA-Z0-9]", "");
        ReleaseLogUtil.e("R_SmartClipManager", "getSourceFilePath encryptedDeviceId:", d, ", replacedDeviceId:", replaceAll);
        return WatchFaceUtil.getFileDirPath(n() + replaceAll + File.separator);
    }

    private String n() {
        try {
            return com.huawei.haf.application.BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + "myWatchFace" + File.separator + "watchfacePhoto" + File.separator + WatchFaceProvider.BACKGROUND_LABEL + File.separator;
        } catch (IOException e) {
            ReleaseLogUtil.c("R_SmartClipManager", "DiyWatchFaceBaseManager mWatchFaceRootDir IOException:", ExceptionUtils.d(e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        Bitmap bIv_ = jlx.bIv_(str, null);
        if (bIv_ == null) {
            ReleaseLogUtil.d("R_SmartClipManager", "prepared bitmap is null");
            return;
        }
        jlz jlzVar = new jlz();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        jlzVar.a(arrayList);
        jlzVar.d(this.i.d());
        jlzVar.c().add(str2);
        jlzVar.e(this.i.e(), this.i.a());
        jlx.bIm_(jlzVar, bIv_);
        jlx.bIo_(bIv_, jlzVar);
        if (jlzVar.c().size() > 0) {
            synchronized (c) {
                LogUtil.a("SmartClipManager", "AddClipped path to Result : ", jlzVar.c().get(0));
                this.j.add(jlzVar.c().get(0));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        Object[] array = this.j.toArray();
        LogUtil.a("SmartClipManager", "before sort : ", Arrays.asList(array));
        for (int i = 0; i < array.length - 1; i++) {
            for (int i2 = 0; i2 < (array.length - i) - 1; i2++) {
                Object obj = array[i2];
                if (obj instanceof String) {
                    int i3 = i2 + 1;
                    Object obj2 = array[i3];
                    if (obj2 instanceof String) {
                        String str = (String) obj;
                        String str2 = (String) obj2;
                        String substring = str.length() >= str.lastIndexOf(".") ? str.substring(str.lastIndexOf(File.separator) + 1, str.lastIndexOf(".")) : "";
                        String substring2 = str2.length() >= str2.lastIndexOf(".") ? str2.substring(str2.lastIndexOf(File.separator) + 1, str2.lastIndexOf(".")) : "";
                        LogUtil.a("SmartClipManager", "preName : ", substring, ", nextName : ", substring2);
                        if (substring.length() > 0 && substring2.length() > 0 && substring.compareTo(substring2) > 0) {
                            LogUtil.a("SmartClipManager", "exchange path");
                            array[i2] = array[i3];
                            array[i3] = str;
                        }
                    }
                }
            }
        }
        Object obj3 = c;
        synchronized (obj3) {
            this.j.clear();
        }
        synchronized (obj3) {
            for (Object obj4 : array) {
                if (obj4 instanceof String) {
                    this.j.add((String) obj4);
                }
            }
        }
        LogUtil.a("SmartClipManager", "after sort : ", this.j.toString());
        SmartClipCallback smartClipCallback = this.d;
        if (smartClipCallback != null) {
            smartClipCallback.onClipResult(0, new ArrayList(this.j));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = "Call onTransferClipResultSuccess callback : ";
        objArr[1] = Boolean.valueOf(this.r != null);
        LogUtil.a("SmartClipManager", objArr);
        this.s = this.w;
        this.o = 0;
        ClipTransferCallback clipTransferCallback = this.r;
        if (clipTransferCallback != null) {
            clipTransferCallback.onTransferResult(0, i);
            this.r = null;
        }
        this.l = false;
        w();
        LogUtil.a("SmartClipManager", "onTransferClipResultSuccess unregisterReceiver mConnectStateChangedReceiver");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        Object[] objArr = new Object[2];
        objArr[0] = "Call onTransferClipResultFailed callback : ";
        objArr[1] = Boolean.valueOf(this.r != null);
        LogUtil.a("SmartClipManager", objArr);
        if (this.r != null) {
            int i = this.s;
            int i2 = i - this.q;
            int i3 = this.w - i2;
            LogUtil.a("SmartClipManager", "mTransferCompleteCount = ", Integer.valueOf(i), ", mTransferFailedCount = ", Integer.valueOf(this.q), ", mTransferTotalCount = ", Integer.valueOf(this.w), "success = ", Integer.valueOf(i2), ", failedCount = ", Integer.valueOf(i3));
            this.r.onTransferResult(-1, i3);
            this.r = null;
        }
        this.l = false;
        w();
        LogUtil.a("SmartClipManager", "onTransferClipResultFailed unregisterReceiver mConnectStateChangedReceiver");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (!this.l) {
            LogUtil.h("SmartClipManager", "CallTransferProgress bu task is over");
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "Call onTransferClipResultProgress, callback : ";
        objArr[1] = Boolean.valueOf(this.r != null);
        LogUtil.a("SmartClipManager", objArr);
        if (this.r != null) {
            int k = k();
            int i = this.s;
            int i2 = this.w;
            if (i > i2) {
                this.s = i2;
            }
            this.r.onTransferProgress(i2, this.s, k);
        }
    }

    private int k() {
        int i = this.w;
        if (i > 0) {
            double d = 100.0d / i;
            int ceil = (int) Math.ceil((this.s * d) + ((this.o * d) / 100.0d));
            if (ceil > 100) {
                return 100;
            }
            if (ceil >= 0) {
                LogUtil.h("SmartClipManager", "getTransferProgress default.");
                return ceil;
            }
        }
        return 0;
    }

    public int d() {
        LogUtil.a("SmartClipManager", "getTransferSuccessCount mTransferSuccessCount : ", Integer.valueOf(this.t));
        return this.t;
    }

    public int c() {
        int i = this.w - this.t;
        LogUtil.a("SmartClipManager", "getTransferFailedCount failedCount : ", Integer.valueOf(i), ", mTransferTotalCount : ", Integer.valueOf(this.w), ", mTransferSuccessCount : ", Integer.valueOf(this.t));
        return i;
    }

    class a implements Runnable {
        private float d;

        a(float f) {
            this.d = f;
        }

        @Override // java.lang.Runnable
        public void run() {
            HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
            if (hiAiSmartClip == null) {
                LogUtil.h("SmartClipManager", "LoadPluginRunnable hiAiSmartClip is null");
                return;
            }
            LogUtil.a("SmartClipManager", "run download plugin thread");
            SmartClipManager.this.m = true;
            hiAiSmartClip.startLoadPlugin(SmartClipManager.this.f, this.d, new HiAiLoadSmartPluginListener() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.a.3
                @Override // com.huawei.health.baseapi.hiaiengine.HiAiLoadSmartPluginListener
                public void onLoadResult(int i) {
                    LogUtil.a("R_SmartClipManager", "OnLoadPluginResult, code:", Integer.valueOf(i));
                    if (i == 0) {
                        if (SmartClipManager.this.p != null) {
                            SmartClipManager.this.p.onLoadPluginResult(0);
                        }
                    } else if (SmartClipManager.this.p != null) {
                        SmartClipManager.this.p.onLoadPluginResult(-1);
                    }
                    SmartClipManager.this.m = false;
                }

                @Override // com.huawei.health.baseapi.hiaiengine.HiAiLoadSmartPluginListener
                public void onLoadProgress(int i) {
                    LogUtil.a("SmartClipManager", "OnLoadPluginProgress : ", Integer.valueOf(i));
                    if (SmartClipManager.this.p != null) {
                        SmartClipManager.this.p.onLoadPluginProgress(i);
                    }
                }
            });
        }
    }
}
