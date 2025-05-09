package defpackage;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.agconnect.apms.Agent;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.kit.devicelogmgr.FilePuller;
import com.huawei.health.device.kit.devicelogmgr.FilePullerCallback;
import com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcrowdtestapi.HealthFeedbackCallback;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.hwcrowdtestapi.HealthSendLogCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.up.utils.Constants;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cfq {
    private static String b;

    /* renamed from: a, reason: collision with root package name */
    private List<String> f683a;
    private WspFileTransferCallback d;
    private String h;
    private String i;
    private WeakReference<Activity> j;
    private WeakReference<NoTitleCustomAlertDialog> e = new WeakReference<>(null);
    private int c = 0;
    private FilePuller g = new cfm();
    private Handler f = new b();

    public cfq(Activity activity, String str, String str2) {
        this.i = str;
        this.h = str2;
        this.j = new WeakReference<>(activity);
    }

    public void c() {
        LogUtil.a("HwFileLogUpload", "start uploadDeviceLog Device Log");
        MeasurableDevice d2 = ceo.d().d(this.h, false);
        if (d2 instanceof ctk) {
            LogUtil.a("HwFileLogUpload", "upload log wifi device");
            a((ctk) d2);
        } else if (d2 instanceof cxh) {
            LogUtil.a("HwFileLogUpload", "upload log bluetooth device");
            e();
        } else {
            LogUtil.h("HwFileLogUpload", "No Device Log");
        }
    }

    private void a(ctk ctkVar) {
        Activity activity = this.j.get();
        if (!(activity instanceof DeviceMainActivity)) {
            LogUtil.h("HwFileLogUpload", "mMainActivity not instanceof DeviceMainActivity");
        } else if (activity.isDestroyed() || activity.isFinishing()) {
            LogUtil.h("HwFileLogUpload", "mMainActivity is destroyed or finishing");
        } else {
            EB_(activity, ctkVar);
        }
    }

    private void EB_(final Activity activity, ctk ctkVar) {
        LogUtil.a("HwFileLogUpload", "upload wifi device log two");
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.i);
        bundle.putString("deviceId", ctkVar.d());
        final cnz cnzVar = new cnz(activity);
        cnzVar.Js_(activity, R.string._2130847870_res_0x7f02287e, 2500);
        cnzVar.c();
        this.g.syncDeviceLog(bundle, new FilePullerCallback() { // from class: cfq.3
            @Override // com.huawei.health.device.kit.devicelogmgr.FilePullerCallback
            public void onFail(int i) {
                LogUtil.h("HwFileLogUpload", "File log upload is fail");
                HandlerExecutor.e(new Runnable() { // from class: cfq.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        cnzVar.Js_(activity, R.string.IDS_device_rope_beta_upload_failed, 2500);
                        cnzVar.c();
                    }
                });
            }

            @Override // com.huawei.health.device.kit.devicelogmgr.FilePullerCallback
            public void onSuccess(Object obj) {
                LogUtil.a("HwFileLogUpload", "File log upload is success");
                HandlerExecutor.e(new Runnable() { // from class: cfq.3.4
                    @Override // java.lang.Runnable
                    public void run() {
                        cnzVar.Js_(activity, R.string.IDS_device_rope_beta_upload_success, 2500);
                        cnzVar.c();
                    }
                });
            }

            @Override // com.huawei.health.device.kit.devicelogmgr.FilePullerCallback
            public void onProgress(int i, String str) {
                LogUtil.a("HwFileLogUpload", "File log upload progress status = ", Integer.valueOf(i), ",desc =", str);
            }
        });
    }

    private HealthFeedbackParams a() {
        HealthFeedbackParams healthFeedbackParams = new HealthFeedbackParams();
        healthFeedbackParams.setProductType(1);
        MeasurableDevice d2 = ceo.d().d(this.h, false);
        if (d2 != null) {
            healthFeedbackParams.setProductName(d2.getDeviceName());
            healthFeedbackParams.setDeviceModel(cpa.i(this.i));
            if (d2 instanceof ctk) {
                ctk ctkVar = (ctk) d2;
                healthFeedbackParams.setDeviceId(knl.a(ctkVar.b().m() + "000000000000").toUpperCase(Locale.ENGLISH));
                healthFeedbackParams.setDeviceSn(ctkVar.b().m());
                String k = cpa.k(this.h);
                if (TextUtils.isEmpty(k)) {
                    k = cpa.k(this.i);
                }
                if (!TextUtils.isEmpty(k)) {
                    healthFeedbackParams.setProductVersion(k);
                } else {
                    healthFeedbackParams.setProductVersion(ctkVar.b().e());
                }
            } else if (d2 instanceof cxh) {
                String k2 = cpa.k(this.h);
                if (TextUtils.isEmpty(k2)) {
                    k2 = cpa.k(this.i);
                }
                healthFeedbackParams.setProductVersion(k2);
                String l = cpa.l(this.h);
                if (TextUtils.isEmpty(l)) {
                    cpa.n(this.i);
                }
                healthFeedbackParams.setDeviceSn(l);
                healthFeedbackParams.setDeviceId(knl.a(healthFeedbackParams.getDeviceSn() + "000000000000").toUpperCase(Locale.ENGLISH));
            } else {
                LogUtil.h("HwFileLogUpload", "switchToSubUserWifiPage, nos bond device");
            }
        }
        return healthFeedbackParams;
    }

    private jep j() {
        jep jepVar = new jep();
        Context context = BaseApplication.getContext();
        jepVar.c(context.getPackageName());
        jepVar.e(LoginInit.getInstance(context).getAccountInfo(1009));
        jepVar.b(LoginInit.getInstance(context).getAccountInfo(1008));
        jepVar.h("at");
        int i = 0;
        String a2 = CommonUtil.a(context, false);
        if (TextUtils.isEmpty(a2)) {
            a2 = "000000000000000";
        }
        jepVar.a(a2);
        try {
            String deviceType = SharedPreferenceUtil.getInstance(context).getDeviceType();
            if (deviceType != null) {
                i = Integer.parseInt(deviceType);
            }
        } catch (NumberFormatException e) {
            LogUtil.b("HwFileLogUpload", "exception:", LogAnonymous.b((Throwable) e));
        }
        jepVar.a(Integer.valueOf(i));
        jepVar.g(LoginInit.getInstance(context).getAccountInfo(1001));
        jepVar.d(Agent.OS_NAME);
        jepVar.d(Integer.valueOf(Constants.HEALTH_APP_LOGIN_CHANNEL));
        return jepVar;
    }

    public void b() {
        LogUtil.a("HwFileLogUpload", "Enter gotoBetaFeedback!");
        HealthFeedbackParams a2 = a();
        jeq.e().gotoFeedback(BaseApplication.getContext(), j(), a2, new d(this.f, a2, this.j.get()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final HealthFeedbackParams healthFeedbackParams) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: cfq.2
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(16);
                String o = LogConfig.o();
                LogUtil.a("HwFileLogUpload", "root path ", o);
                arrayList.add(o + "com.huawei.health");
                arrayList.add(o + "com.huawei.health.h5pro");
                arrayList.add(o + "com.huawei.health_DaemonService");
                arrayList.add(o + "com.huawei.health_pushservice");
                arrayList.add(o + "huawei_crashLog_0.txt");
                arrayList.add(o + "leak.txt");
                arrayList.add(o + "LogAnalysis.txt");
                DfxBaseHandler.getAllDfxLogFileToPathList(new File(o), arrayList);
                String c = cfq.this.c(healthFeedbackParams, "_app_WearableBeta.zip");
                String h = cfq.this.h();
                if (!TextUtils.isEmpty(h)) {
                    c = c.replace("00000000", h);
                }
                String str = c;
                try {
                    cpk.a(cfq.this.b(arrayList), new File(str), "");
                } catch (IOException unused) {
                    LogUtil.b("HwFileLogUpload", "zip device log exception");
                }
                jeq.e().sendLog((Context) cfq.this.j.get(), healthFeedbackParams, str, true, new HealthSendLogCallback() { // from class: cfq.2.5
                    @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
                    public void onSuccess(String str2) {
                        LogUtil.a("HwFileLogUpload", "compressAndSendLog send log success!");
                    }

                    @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
                    public void onFailed(String str2) {
                        LogUtil.h("HwFileLogUpload", "compressAndSendLog send log fail!");
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final HealthFeedbackParams healthFeedbackParams, final List<String> list) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: cfq.1
            @Override // java.lang.Runnable
            public void run() {
                String unused = cfq.b = cfq.this.c(healthFeedbackParams, "_device_WearableBeta.zip");
                String h = cfq.this.h();
                if (!TextUtils.isEmpty(h)) {
                    String unused2 = cfq.b = cfq.b.replace("00000000", h);
                }
                File file = new File(cfq.b);
                try {
                    cpk.a(cfq.this.b((List<String>) list), file, "");
                } catch (IOException unused3) {
                    LogUtil.h("HwFileLogUpload", "zip device log exception");
                }
                if (!CommonUtil.aa(BaseApplication.getContext())) {
                    LogUtil.h("HwFileLogUpload", "netWork is fail");
                    HandlerExecutor.e(new Runnable() { // from class: cfq.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            nrh.b(BaseApplication.getContext(), R.string._2130842450_res_0x7f021352);
                        }
                    });
                    return;
                }
                double length = file.length();
                if (file.exists() && length != 0.0d) {
                    cfq.this.a(length, healthFeedbackParams);
                } else {
                    LogUtil.h("HwFileLogUpload", "Log file not exist");
                    HandlerExecutor.e(new Runnable() { // from class: cfq.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            nrh.b(BaseApplication.getContext(), R.string._2130847873_res_0x7f022881);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final double d2, final HealthFeedbackParams healthFeedbackParams) {
        if (c(BaseApplication.getContext())) {
            LogUtil.a("HwFileLogUpload", "netWork is mobile network");
            HandlerExecutor.e(new Runnable() { // from class: cfq.5
                @Override // java.lang.Runnable
                public void run() {
                    cfq cfqVar = cfq.this;
                    cfqVar.EA_((Context) cfqVar.j.get(), d2, new View.OnClickListener() { // from class: cfq.5.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            cfq.this.b(healthFeedbackParams, cfq.b);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                }
            });
        } else {
            LogUtil.a("HwFileLogUpload", "netWork is wifi");
            b(healthFeedbackParams, b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HealthFeedbackParams healthFeedbackParams, String str) {
        jeq.e().sendLog(BaseApplication.getContext(), healthFeedbackParams, str, true, new HealthSendLogCallback() { // from class: cfq.4
            @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
            public void onSuccess(String str2) {
                HandlerExecutor.e(new Runnable() { // from class: cfq.4.5
                    @Override // java.lang.Runnable
                    public void run() {
                        nrh.c(BaseApplication.getContext(), R.string._2130842453_res_0x7f021355);
                    }
                });
                LogUtil.a("HwFileLogUpload", "send log success!");
            }

            @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
            public void onFailed(String str2) {
                HandlerExecutor.e(new Runnable() { // from class: cfq.4.3
                    @Override // java.lang.Runnable
                    public void run() {
                        nrh.c(BaseApplication.getContext(), R.string._2130840671_res_0x7f020c5f);
                    }
                });
                LogUtil.h("HwFileLogUpload", "send log fail!");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EA_(Context context, double d2, View.OnClickListener onClickListener) {
        if (context == null) {
            LogUtil.h("HwFileLogUpload", "showLogUploadDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e.get();
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.h("HwFileLogUpload", "showLogUploadDialog already show");
            return;
        }
        double d3 = d2 / 1048576.0d;
        if (Double.compare(d3, 0.01d) < 0) {
            d3 = 0.01d;
        }
        String string = context.getString(R.string._2130842449_res_0x7f021351, UnitUtil.e(d3, 1, 2));
        LogUtil.a("HwFileLogUpload", "showLogUploadDialog uploadHint: ", string);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(string);
        builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cfq.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czC_(R.string._2130841131_res_0x7f020e2b, onClickListener);
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
        this.e = new WeakReference<>(e);
    }

    private boolean c(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        Object systemService = context.getSystemService("connectivity");
        return (systemService instanceof ConnectivityManager) && (activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<File> b(List<String> list) {
        ArrayList<File> arrayList = new ArrayList<>(10);
        if (koq.b(list)) {
            LogUtil.h("HwFileLogUpload", "getFileList pathList is empty");
            return arrayList;
        }
        for (String str : list) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("HwFileLogUpload", "getFileList pathList item is empty");
            } else {
                File file = new File(str);
                if (file.exists()) {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(HealthFeedbackParams healthFeedbackParams, String str) {
        if (healthFeedbackParams == null) {
            LogUtil.h("HwFileLogUpload", "feedbackParams is null");
            return "";
        }
        String upperCase = knl.a(healthFeedbackParams.getDeviceSn() + "000000000000").toUpperCase(Locale.ENGLISH);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(LogConfig.o());
        stringBuffer.append(healthFeedbackParams.getDeviceModel());
        stringBuffer.append("_");
        stringBuffer.append(healthFeedbackParams.getProductVersion());
        stringBuffer.append("_");
        stringBuffer.append(upperCase);
        stringBuffer.append("_");
        stringBuffer.append(new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date()));
        stringBuffer.append("_00000000");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h() {
        Context context = BaseApplication.getContext();
        if (System.currentTimeMillis() - SharedPreferenceManager.c(context, "BETA_CLUB_QUESTION_NUMBER_TIME") > 43200000) {
            SharedPreferenceManager.h(context, "");
        }
        return SharedPreferenceManager.c(context);
    }

    private void e() {
        LogUtil.a("HwFileLogUpload", "start getLogFileFromDevice mProductId:", this.i, ",mUniqueId:", this.h);
        if (cld.d(this.i, this.h).b()) {
            cfv.b().a(new WspFileTransferCallback() { // from class: cfq.8
                @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
                public void onStart(int i) {
                    LogUtil.a("HwFileLogUpload", "getLogFileFromDevice onStart fileListSize:", Integer.valueOf(i));
                    cfq.this.c = i;
                    cfq.this.f683a = new ArrayList(16);
                    if (cfq.this.d != null) {
                        cfq.this.d.onStart(i);
                    }
                }

                @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
                public void onSuccess(int i, int i2, String str) {
                    LogUtil.a("HwFileLogUpload", "getLogFileFromDevice onSuccess, fileIndex:", Integer.valueOf(i), " successCode:", Integer.valueOf(i2));
                    cfq.this.a(i, i2, str);
                }

                @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("HwFileLogUpload", "getLogFileFromDevice onFailure errorCode:", Integer.valueOf(i), str);
                    if (cfq.this.d != null) {
                        cfq.this.d.onFailure(i, str);
                    }
                }

                @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
                public void onProgress(int i, int i2, String str) {
                    LogUtil.a("HwFileLogUpload", "getLogFileFromDevice onProgress progress:", Integer.valueOf(i2));
                    if (cfq.this.d != null) {
                        cfq.this.d.onProgress(i, i2, str);
                    }
                }

                @Override // com.huawei.health.device.kit.devicelogmgr.ble.WspFileTransferCallback
                public String getFilePath() {
                    if (cfq.this.d != null) {
                        return cfq.this.d.getFilePath();
                    }
                    LogUtil.h("HwFileLogUpload", "get empty file path");
                    return "";
                }
            });
            String packageName = BaseApplication.getContext().getPackageName();
            new cft().c(packageName, packageName);
            nrh.c(BaseApplication.getContext(), a(R.string.IDS_device_rope_beta_upload_success));
            return;
        }
        nrh.c(BaseApplication.getContext(), a(R.string.IDS_device_rope_beta_upload_failed));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, String str) {
        List<String> list = this.f683a;
        if (list != null) {
            list.add(str);
            if (this.f683a.size() == this.c) {
                HealthFeedbackParams a2 = a();
                Message obtain = Message.obtain();
                obtain.what = 15;
                obtain.obj = a2;
                this.f.sendMessage(obtain);
            }
        }
        WspFileTransferCallback wspFileTransferCallback = this.d;
        if (wspFileTransferCallback != null) {
            wspFileTransferCallback.onSuccess(i, i2, str);
        }
    }

    public void b(WspFileTransferCallback wspFileTransferCallback) {
        this.d = wspFileTransferCallback;
    }

    private String a(int i) {
        return BaseApplication.getContext().getResources().getString(i);
    }

    static class d implements HealthFeedbackCallback {
        private WeakReference<Activity> b;
        private HealthFeedbackParams c;
        private Handler d;

        private d(Handler handler, HealthFeedbackParams healthFeedbackParams, Activity activity) {
            this.d = handler;
            this.c = healthFeedbackParams;
            this.b = new WeakReference<>(activity);
        }

        @Override // com.huawei.hwcrowdtestapi.HealthFeedbackCallback
        public void onFailed(String str) {
            Activity activity = this.b.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                LogUtil.h("HwFileLogUpload", "onFailed activity is null, finish or destroyed");
            } else {
                LogUtil.b("HwFileLogUpload", "goto BetaFeedback fail:", str);
            }
        }

        @Override // com.huawei.hwcrowdtestapi.HealthFeedbackCallback
        public jer collectLogs(int i, String str, String str2, boolean z) {
            Activity activity = this.b.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                LogUtil.h("HwFileLogUpload", "collectLogs activity is null, finish or destroyed");
                return null;
            }
            ArrayList arrayList = new ArrayList(16);
            String o = LogConfig.o();
            arrayList.add(o + "MaintenanceLog");
            arrayList.add(o + "com.huawei.health");
            arrayList.add(o + "com.huawei.health.h5pro");
            arrayList.add(o + "com.huawei.health_PhoneService");
            arrayList.add(o + "com.huawei.health_DaemonService");
            arrayList.add(o + "com.huawei.health_leakcanary");
            arrayList.add(o + "com.huawei.health_pushservice");
            arrayList.add(o + "com.huawei.version.json");
            arrayList.add(o + "huawei_crashLog_0.txt");
            arrayList.add(o + "huawei_crashLog_1.txt");
            arrayList.add(o + "huawei_crashLog_2.txt");
            arrayList.add(o + "app_config_value.txt");
            arrayList.add(o + "leak.txt");
            arrayList.add(o + "LogAnalysis.txt");
            DfxBaseHandler.getAllDfxLogFileToPathList(new File(o), arrayList);
            jer jerVar = new jer();
            jerVar.a(arrayList);
            Message obtain = Message.obtain();
            jerVar.c(200);
            obtain.what = 14;
            obtain.obj = this.c;
            this.d.sendMessage(obtain);
            return jerVar;
        }
    }

    static class b extends BaseHandler<cfq> {
        private b(cfq cfqVar) {
            super(cfqVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: EC_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(cfq cfqVar, Message message) {
            int i = message.what;
            if (i == 14) {
                if (message.obj instanceof HealthFeedbackParams) {
                    cfqVar.e((HealthFeedbackParams) message.obj);
                }
                cfqVar.c();
            } else if (i == 15 && (message.obj instanceof HealthFeedbackParams)) {
                cfqVar.c((HealthFeedbackParams) message.obj, (List<String>) cfqVar.f683a);
            }
        }
    }
}
