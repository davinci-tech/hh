package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class quq extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile quq f16597a;
    private BroadcastReceiver d;
    private static final Object e = new Object();
    private static final List<String> b = Arrays.asList("CN", "DE", "IT");
    private static final qvc c = new qvc(k.b.m, 36000, 0);

    private quq() {
        super(BaseApplication.getContext());
        this.d = new BroadcastReceiver() { // from class: quq.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action == null) {
                    LogUtil.h("FastingLiteDeviceManager", "action == null");
                } else if ("android.intent.action.TIME_SET".equals(action)) {
                    LogUtil.a("FastingLiteDeviceManager", "curTime", Long.valueOf(System.currentTimeMillis()));
                    quq.this.c("fromTimeSet", null);
                }
            }
        };
        LogUtil.a("FastingLiteDeviceManager", "FastingLiteDeviceManager init");
        registerNotificationAction();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        BaseApplication.getContext().registerReceiver(this.d, intentFilter, LocalBroadcast.c, null);
        b("init", null);
    }

    public static quq a() {
        if (f16597a == null) {
            synchronized (e) {
                if (f16597a == null) {
                    f16597a = new quq();
                }
            }
        }
        return f16597a;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.FASTING_LITE_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        if (spnVar == null) {
            LogUtil.h("FastingLiteDeviceManager", "data is null");
        } else if (spnVar.d() == 2) {
            c(spnVar.a());
        } else {
            d(spnVar.b());
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("FastingLiteDeviceManager", "connectState ", connectState);
        if (connectState == ConnectState.CONNECTED) {
            LogUtil.a("FastingLiteDeviceManager", "onDeviceConnected");
            c("fromDeviceConnect", null);
        }
    }

    private void c(final File file) {
        LogUtil.a("FastingLiteDeviceManager", "batchRecordBiData enter");
        if (file == null) {
            LogUtil.a("FastingLiteDeviceManager", "batchRecordBiData file is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qut
                @Override // java.lang.Runnable
                public final void run() {
                    quq.a(file);
                }
            });
        }
    }

    static /* synthetic */ void a(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)), 1024);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        qur qurVar = (qur) HiJsonUtil.e(readLine, qur.class);
                        LogUtil.c("FastingLiteDeviceManager", "biMetaData：", readLine);
                        ixx.d().d(BaseApplication.getContext(), qurVar.c(), qurVar.a(), 0);
                    } else {
                        bufferedReader.close();
                        return;
                    }
                } finally {
                }
            }
        } catch (FileNotFoundException unused) {
            LogUtil.h("FastingLiteDeviceManager", "batchRecordBiData FileNotFoundException.");
        } catch (IOException unused2) {
            LogUtil.h("FastingLiteDeviceManager", "batchRecordBiData IOException.");
        }
    }

    private void d(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, StandardCharsets.UTF_8);
        } catch (UnsupportedOperationException unused) {
            LogUtil.h("FastingLiteDeviceManager", "handleMessage UnsupportedOperationException!");
            str = "";
        }
        LogUtil.a("FastingLiteDeviceManager", "handleMessage, json: ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("FastingLiteDeviceManager", "handleMessage, json is empty ");
            return;
        }
        if ("Sync".equals(str)) {
            c("fromWatch", null);
            return;
        }
        try {
            quu quuVar = (quu) HiJsonUtil.e(str, quu.class);
            int d = quuVar.d();
            boolean c2 = quuVar.c();
            LogUtil.a("FastingLiteDeviceManager", "version = ", Integer.valueOf(d), ",needSync = ", Boolean.valueOf(c2));
            if (c2) {
                c("fromWatch", null);
            }
            List<qur> e2 = quuVar.e();
            if (e2 == null) {
                LogUtil.h("FastingLiteDeviceManager", "biMetaDataList is empty");
                return;
            }
            for (qur qurVar : e2) {
                ixx.d().d(BaseApplication.getContext(), qurVar.c(), qurVar.a(), 0);
                LogUtil.c("FastingLiteDeviceManager", "eventId:", qurVar.c(), ",biMap:", qurVar.a());
            }
        } catch (JsonSyntaxException e3) {
            LogUtil.h("FastingLiteDeviceManager", "handleMessage JsonSyntaxException, e is ", e3.getMessage());
        }
    }

    private boolean e() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        LogUtil.a("FastingLiteDeviceManager", "country:", accountInfo);
        return b.contains(accountInfo);
    }

    public void c(String str, final SendCallback sendCallback) {
        if (!e()) {
            LogUtil.a("FastingLiteDeviceManager", "current country is not support fasting lite");
        } else {
            LogUtil.a("FastingLiteDeviceManager", "sendFastingLitePlan, fromType : ", str);
            pingComand(new PingCallback() { // from class: quw
                @Override // com.huawei.health.deviceconnect.callback.PingCallback
                public final void onPingResult(int i) {
                    quq.this.d(sendCallback, i);
                }
            }, "com.huawei.ohos.fastinglite");
        }
    }

    /* synthetic */ void d(SendCallback sendCallback, int i) {
        LogUtil.a("FastingLiteDeviceManager", "onPingResult code = ", Integer.valueOf(i));
        if (i == 202) {
            b("", sendCallback);
        }
    }

    public void b(final String str, final SendCallback sendCallback) {
        if (!e()) {
            LogUtil.a("FastingLiteDeviceManager", "current country is not support fasting lite");
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a("FastingLiteDeviceManager", "sendPlanWithoutPing, fromType : ", str);
        }
        ThreadPoolManager.d().c("FastingLiteDeviceManager", new Runnable() { // from class: qus
            @Override // java.lang.Runnable
            public final void run() {
                quq.this.d(sendCallback, str);
            }
        });
    }

    /* synthetic */ void d(SendCallback sendCallback, String str) {
        c(HiJsonUtil.e(b()), sendCallback, str);
    }

    private qvb b() {
        qvb qvbVar = new qvb();
        qui c2 = qve.c();
        if (c2 == null || !c2.c() || c2.d() == null) {
            qvbVar.e(c);
            return qvbVar;
        }
        qvbVar.e(c2.d().b());
        qvbVar.e(qlc.b().g());
        return qvbVar;
    }

    private void c(String str, SendCallback sendCallback, String str2) {
        spn e2 = new spn.b().c(str.getBytes(StandardCharsets.UTF_8)).e();
        LogUtil.a("FastingLiteDeviceManager", "sendCommand json = ", str, " ,fromType = ", str2);
        if (sendCallback != null) {
            sendComand(e2, sendCallback);
        } else {
            sendComand(e2, new SendCallback() { // from class: quq.5
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i) {
                    if (i == 206) {
                        LogUtil.h("FastingLiteDeviceManager", "sendCommand fail");
                    } else if (i == 207) {
                        LogUtil.a("FastingLiteDeviceManager", "sendCommand success");
                    } else {
                        LogUtil.h("FastingLiteDeviceManager", "sendCommand other Result =", Integer.valueOf(i));
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                    LogUtil.a("FastingLiteDeviceManager", "onSendProgress=", Long.valueOf(j));
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str3) {
                    LogUtil.a("FastingLiteDeviceManager", "onFileTransferReport transferWay: ", str3);
                }
            });
        }
    }

    public static void c() {
        ReleaseLogUtil.b("FastingLiteDeviceManager", "resetFastingLiteDeviceManager");
        synchronized (e) {
            if (f16597a == null) {
                ReleaseLogUtil.a("FastingLiteDeviceManager", "resetFastingLiteDeviceManager sInstance is null");
                return;
            }
            f16597a.onDestroy();
            BaseApplication.getContext().unregisterReceiver(f16597a.d);
            ThreadPoolManager.d().b("FastingLiteDeviceManager", null);
            f16597a = null;
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.ohos.fastinglite";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "com.huawei.ohos.fastinglite_BIb2XuytBs7qSFJ/NqCPA/GoxtGMY4FM9QgucHV2IAutlvlmEqDiiSvWhK2kJhF5904rA6WQv2BTrEh0BN5TUNA=";
    }
}
