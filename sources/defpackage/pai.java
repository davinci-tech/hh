package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcrowdtestapi.HealthFeedbackCallback;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxUploadCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import com.huawei.ui.device.interactors.UploadMaintLogInteractor;
import com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.WearHomeDeviceInfoActivity;
import com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity;
import defpackage.pai;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes6.dex */
public class pai {

    /* renamed from: a, reason: collision with root package name */
    private CustomTextAlertDialog f16024a;
    private CommonDialog21 aa;
    private RelativeLayout ab;
    private HealthRadioButton ac;
    private String af;
    private RelativeLayout ag;
    private String ah;
    private CustomTextAlertDialog ai;
    private UploadMaintLogInteractor ak;
    private CustomAlertDialog al;
    private CustomAlertDialog b;
    private WearHomeActivity d;
    private Context e;
    private CustomProgressDialog.Builder f;
    private CustomProgressDialog g;
    private CustomProgressDialog.Builder h;
    private CustomProgressDialog j;
    private CustomTextAlertDialog k;
    private CustomViewDialog l;
    private CustomTextAlertDialog m;
    private CustomTextAlertDialog n;
    private CustomViewDialog o;
    private HealthTextView q;
    private boolean w;
    private CommonDialog21 x;
    private Boolean y;
    private HealthRadioButton z;
    private volatile boolean r = false;
    private volatile boolean p = false;
    private volatile boolean t = false;
    private volatile boolean s = false;
    private volatile boolean u = false;
    private boolean v = false;
    private final DeviceDfxUploadCallback ae = new DeviceDfxUploadCallback() { // from class: pai.4
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxUploadCallback
        public void startUpload(double d) {
            LogUtil.a("WearHomeGeneralAction", "startUpload is ", Double.valueOf(d));
            pai.this.b();
            String string = pai.this.d.getString(R.string.IDS_device_log_upload_tips, new Object[]{UnitUtil.e((d / 1024.0d) / 1024.0d, 1, 2)});
            LogUtil.a("WearHomeGeneralAction", "startUpload string is ", string);
            if (pai.this.d.djG_() != null) {
                Message obtainMessage = pai.this.d.djG_().obtainMessage();
                obtainMessage.what = 1024;
                obtainMessage.obj = string;
                pai.this.d.djG_().sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("WearHomeGeneralAction", "mHandler is null");
        }
    };
    private final DeviceDfxBaseResponseCallback i = new DeviceDfxBaseResponseCallback() { // from class: pai.15
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onSuccess(int i, String str) {
            if (i == 2) {
                LogUtil.h("WearHomeGeneralAction", "start logging.");
                return;
            }
            if (i == 10000 && TextUtils.equals(str, "5.10.12 cancel timeout 7 minutes")) {
                LogUtil.a("WearHomeGeneralAction", "cancel 5.10.12 timeout tip");
                pai.this.d.djG_().removeMessages(1041);
                pai.this.c(false);
                return;
            }
            ReleaseLogUtil.e("Dfx_WearHomeGeneralAction", "mDfxCallback onSuccess errorCode = ", Integer.valueOf(i));
            pai paiVar = pai.this;
            paiVar.c(paiVar.j);
            SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
            pai.this.s = true;
            pai.this.p = false;
            if (pai.this.d != null && pai.this.d.djG_() != null) {
                pai.this.d.djG_().postDelayed(new Runnable() { // from class: pai.15.4
                    @Override // java.lang.Runnable
                    public void run() {
                        pai.this.c(pai.this.j);
                        if (!jsd.a(BaseApplication.getContext())) {
                            LogUtil.a("WearHomeGeneralAction", "wifi and network are all disconnected.");
                            pai.this.u();
                            return;
                        }
                        if (jsd.c(BaseApplication.getContext())) {
                            pai.this.r = true;
                            pai.this.v = true;
                            jsd.e(false);
                            pai.this.b();
                            if (!pai.this.g()) {
                                LogUtil.a("WearHomeGeneralAction", "wifi upload 5.10.12 not timeout");
                                nrh.e(pai.this.e, R.string._2130842452_res_0x7f021354);
                            }
                            LogUtil.a("WearHomeGeneralAction", "start logging wifi upload");
                            jgp.a(BaseApplication.getContext()).d(false, pai.this.d.g);
                            Message obtainMessage = pai.this.d.djG_().obtainMessage();
                            obtainMessage.what = 1021;
                            pai.this.d.djG_().sendMessageDelayed(obtainMessage, 600000L);
                            return;
                        }
                        if (!NetworkUtil.f()) {
                            LogUtil.a("WearHomeGeneralAction", "Mobile is also disconnected");
                            pai.this.u();
                        } else {
                            LogUtil.a("WearHomeGeneralAction", "wifi is dmIsConnected use network");
                            jsd.e(true);
                            jgp.a(BaseApplication.getContext()).d(false, pai.this.d.g);
                            jsd.e(pai.this.ae);
                        }
                    }
                }, 800L);
            } else {
                LogUtil.h("WearHomeGeneralAction", "catch null pointer exception");
            }
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Dfx_WearHomeGeneralAction", "mDfxCallback onFailure errorCode = ", Integer.valueOf(i), ", errorMessage = ", str);
            pai.this.e(i, str);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onProgress(int i, String str) {
            if (!pai.this.g()) {
                if (pai.this.d == null || pai.this.d.djG_() == null) {
                    return;
                }
                LogUtil.a("WearHomeGeneralAction", "update progress, progress: ", Integer.valueOf(i));
                Message obtainMessage = pai.this.d.djG_().obtainMessage();
                obtainMessage.what = 1026;
                obtainMessage.arg1 = i;
                obtainMessage.obj = str;
                pai.this.d.djG_().sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("WearHomeGeneralAction", "mDfxCallback onProgress 5.10.12 already timeout");
        }
    };
    private final BroadcastReceiver ad = new BroadcastReceiver() { // from class: pai.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("WearHomeGeneralAction", "intent or intent.getAction is null");
                return;
            }
            LogUtil.a("WearHomeGeneralAction", "phone service Died onReceive intent ", intent.getAction());
            if ("com.huawei.health.action.ACTION_CALLBACKSERVICE_PHONESERVICE_DEAD".equals(intent.getAction())) {
                sqo.p("WearHomeGeneralAction, PhoneServiceDied broadcast: com.huawei.health.action.ACTION_CALLBACKSERVICE_PHONESERVICE_DEAD");
                if (SharedPreferenceManager.a(String.valueOf(10), "KEY_IS_CLICKED_BETA_FEEDBACK", false)) {
                    sqo.p("WearHomeGeneralAction, PhoneServiceDied CLICKED_BETA_FEEDBACK");
                    DeviceInfo a2 = oxa.a().a(pai.this.d.g);
                    if (a2 == null) {
                        ReleaseLogUtil.c("Dfx_WearHomeGeneralAction", "currentDeviceInfo == null");
                        pai.this.b();
                        pai paiVar = pai.this;
                        paiVar.c(paiVar.j);
                        SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
                        return;
                    }
                    int a3 = SharedPreferenceManager.a(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
                    if (a3 < 1) {
                        ReleaseLogUtil.e("Dfx_WearHomeGeneralAction", "retry time = 1");
                        if (pai.this.u) {
                            pai.this.e(a2);
                        } else {
                            pai.this.v();
                        }
                        SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", a3 + 1);
                        return;
                    }
                    ReleaseLogUtil.c("Dfx_WearHomeGeneralAction", "has reach retry time limit");
                    pai.this.b();
                    pai paiVar2 = pai.this;
                    paiVar2.c(paiVar2.j);
                    SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
                }
            }
        }
    };
    private final BroadcastReceiver am = new BroadcastReceiver() { // from class: pai.17
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo;
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("WearHomeGeneralAction", "intent or intent.getAction is null");
                return;
            }
            LogUtil.a("WearHomeGeneralAction", "mWifiBroadcastReceiver onReceive intent ", intent.getAction(), " isAgree ", Boolean.valueOf(jsd.e()));
            if (pai.this.r && !pai.this.d.isFinishing() && "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                Object systemService = context.getSystemService("connectivity");
                if (systemService instanceof ConnectivityManager) {
                    LogUtil.a("WearHomeGeneralAction", "connectivity action");
                    networkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
                } else {
                    networkInfo = null;
                }
                if (networkInfo != null) {
                    pai.this.dkk_(networkInfo);
                    LogUtil.a("WearHomeGeneralAction", "info.getTypeName()", networkInfo.getTypeName());
                    return;
                }
                LogUtil.a("WearHomeGeneralAction", "has no network");
                pai.this.r = false;
                pai.this.v = false;
                jsd.e(false);
                if (pai.this.g()) {
                    return;
                }
                LogUtil.a("WearHomeGeneralAction", "has no network 5.10.12 not timeout");
                nrh.e(pai.this.e, R.string._2130842450_res_0x7f021352);
            }
        }
    };
    private final BroadcastReceiver an = new BroadcastReceiver() { // from class: pai.18
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || pai.this.p || !pai.this.r) {
                LogUtil.h("WearHomeGeneralAction", "intent is null,mIsManualLog:", Boolean.valueOf(pai.this.p), "mIsFeedBackShowToast:", Boolean.valueOf(pai.this.r));
                return;
            }
            if ("com.huawei.crowdtestsdk.LOG_UPLOAD_RESULT".equals(intent.getAction())) {
                pai.this.d.djG_().removeMessages(1021);
                int intExtra = intent.getIntExtra("logUploadResult", 1);
                LogUtil.a("WearHomeGeneralAction", "result：", Integer.valueOf(intExtra));
                if (intExtra != 16) {
                    pai.this.dkm_(intent);
                } else {
                    pai.this.d.djG_().sendEmptyMessageDelayed(1022, 1000L);
                }
            }
        }
    };
    private final NotifyPhoneServiceCallback c = new AnonymousClass16();

    /* renamed from: pai$16, reason: invalid class name */
    class AnonymousClass16 implements NotifyPhoneServiceCallback {
        AnonymousClass16() {
        }

        @Override // com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback
        public void executeResponse(int i, final DeviceInfo deviceInfo, int i2, String str) {
            if (pai.this.d == null) {
                LogUtil.h("WearHomeGeneralAction", "executeResponse mActivity == null ");
                return;
            }
            if (i == 0) {
                LogUtil.a("WearHomeGeneralAction", "executeResponse progress : ", Integer.valueOf(i2));
                if (i2 == 100) {
                    pai.this.d.runOnUiThread(new Runnable() { // from class: pba
                        @Override // java.lang.Runnable
                        public final void run() {
                            pai.AnonymousClass16.this.e();
                        }
                    });
                    pai.this.b(deviceInfo);
                    return;
                } else {
                    pai.this.b(i2, false);
                    return;
                }
            }
            if (i == 1) {
                LogUtil.a("WearHomeGeneralAction", "executeResponse timeout");
                jfq.c().a("earphoneLogPre");
                pai.this.d.runOnUiThread(new Runnable() { // from class: pbb
                    @Override // java.lang.Runnable
                    public final void run() {
                        pai.AnonymousClass16.this.c(deviceInfo);
                    }
                });
            } else {
                LogUtil.h("WearHomeGeneralAction", "others");
                jfq.c().a("earphoneLogPre");
                pai.this.g(deviceInfo);
            }
        }

        /* synthetic */ void e() {
            pai paiVar = pai.this;
            paiVar.c(paiVar.g);
            SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
            jfq.c().a("earphoneLogPre");
        }

        /* synthetic */ void c(DeviceInfo deviceInfo) {
            pai paiVar = pai.this;
            paiVar.c(paiVar.g);
            SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
            pai.this.g(deviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        b();
        this.r = false;
        jsd.e(false);
        jgp.a(BaseApplication.getContext()).d(false, this.d.g);
        if (g()) {
            return;
        }
        LogUtil.a("WearHomeGeneralAction", "dealNetworkIsDisconnect 5.10.12 not timeout");
        nrh.e(this.e, R.string._2130842450_res_0x7f021352);
    }

    public pai(Context context, WearHomeActivity wearHomeActivity) {
        this.y = null;
        this.d = wearHomeActivity;
        this.e = context;
        this.ak = new UploadMaintLogInteractor(context.getApplicationContext());
        this.y = Boolean.valueOf(Utils.l());
    }

    public void e(boolean z) {
        this.r = z;
    }

    public boolean d() {
        return this.r;
    }

    public boolean g() {
        return this.t;
    }

    public void c(boolean z) {
        this.t = z;
    }

    public void b(boolean z) {
        CommonDialog21 commonDialog21;
        if (z) {
            nrh.b(this.e, R.string._2130841760_res_0x7f0210a0);
        }
        if (this.d.isFinishing() || (commonDialog21 = this.x) == null || !commonDialog21.isShowing()) {
            return;
        }
        this.x.cancel();
        this.x = null;
        LogUtil.a("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "destroy mLoadingDialog21");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dkm_(Intent intent) {
        String stringExtra = intent.getStringExtra("LogUploadFilePath");
        LogUtil.c("WearHomeGeneralAction", "file：", stringExtra);
        if (stringExtra != null) {
            this.v = false;
            if (new File(jsd.b + stringExtra).exists()) {
                LogUtil.c("WearHomeGeneralAction", "mUploadLogResultBroadcastReceiver log upload failed");
                this.d.djG_().post(new Runnable() { // from class: pai.20
                    @Override // java.lang.Runnable
                    public void run() {
                        pai.this.d.djG_().removeMessages(1022);
                        if (pai.this.g()) {
                            return;
                        }
                        LogUtil.a("WearHomeGeneralAction", "dealWithLogUploadFail 5.10.12 not timeout");
                        nrh.e(pai.this.e, R.string._2130842450_res_0x7f021352);
                    }
                });
                return;
            }
        }
        this.d.djG_().sendEmptyMessageDelayed(1022, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dkk_(NetworkInfo networkInfo) {
        boolean b2 = PowerKitManager.e().b();
        if (!networkInfo.isConnected() || b2) {
            LogUtil.a("WearHomeGeneralAction", "network not connected ");
            this.r = false;
            jsd.e(false);
            if (g()) {
                return;
            }
            LogUtil.a("WearHomeGeneralAction", "dealWithLogUploadFail 5.10.12 not timeout");
            nrh.e(this.e, R.string._2130842450_res_0x7f021352);
            return;
        }
        if (networkInfo.getType() == 1) {
            LogUtil.a("WearHomeGeneralAction", "wifi available");
            return;
        }
        if (networkInfo.getType() == 0) {
            LogUtil.a("WearHomeGeneralAction", "mobile available");
            if (jsd.e()) {
                return;
            }
            this.r = false;
            this.v = false;
            jsd.e(false);
            if (g()) {
                return;
            }
            LogUtil.a("WearHomeGeneralAction", "mobile available 5.10.12 not timeout");
            nrh.e(this.e, R.string._2130842450_res_0x7f021352);
            return;
        }
        LogUtil.h("WearHomeGeneralAction", "activeNetwork else");
    }

    public void h() {
        Intent intent = new Intent();
        intent.putExtra("device_id", this.d.g);
        intent.setClass(this.e, WearHomeOtherSettingActivity.class);
        this.d.startActivity(intent);
        nsn.ai(this.e);
    }

    public void i() {
        Intent intent = new Intent();
        intent.putExtra("device_id", this.d.g);
        intent.setClass(this.e, WearHomeDeviceInfoActivity.class);
        this.d.startActivity(intent);
    }

    public void n() {
        LogUtil.a("WearHomeGeneralAction", "start AW70ModeSelectActivity");
        oau.e();
        this.d.a(Aw70ModeSelectActivity.class, 36);
    }

    public void m() {
        aj();
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010033.value(), new HashMap(16), 0);
    }

    private void aj() {
        LogUtil.a("WearHomeGeneralAction", 1, "WearHomeGeneralAction", "enter showRestoreFactoryDialog()");
        this.d.b = oxa.a().a(this.d.g);
        if (this.d.b == null) {
            return;
        }
        WearHomeActivity wearHomeActivity = this.d;
        wearHomeActivity.g = wearHomeActivity.b.getDeviceIdentify();
        int productType = this.d.b.getProductType();
        LogUtil.a("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "currentDeviceType is ", Integer.valueOf(productType));
        WearHomeActivity wearHomeActivity2 = this.d;
        String b2 = pep.b(wearHomeActivity2, wearHomeActivity2.g);
        String string = this.d.getString(R.string._2130841727_res_0x7f02107f, new Object[]{b2});
        if (!TextUtils.isEmpty(this.d.b.getDeviceName()) && productType == 11 && this.d.b.getDeviceName().contains("HUAWEI CM-R1P")) {
            LogUtil.a("WearHomeGeneralAction", "Entering All device names");
            WearHomeActivity wearHomeActivity3 = this.d;
            string = wearHomeActivity3.getString(R.string._2130841727_res_0x7f02107f, new Object[]{wearHomeActivity3.getString(R.string._2130849807_res_0x7f02300f)});
        }
        WearHomeActivity wearHomeActivity4 = this.d;
        wearHomeActivity4.f9644a = wearHomeActivity4.d.e(this.d.g);
        if (this.b == null && this.d.f9644a != null && this.d.f9644a.isSupportEsim()) {
            LogUtil.a("WearHomeGeneralAction", "Entering ResetFactoryGalileoDialog Galileo");
            c(b2);
        } else if (this.ai == null) {
            LogUtil.a("WearHomeGeneralAction", "Entering setResetFactoryDialog");
            a(string);
        }
    }

    private void c(String str) {
        View inflate = View.inflate(this.e, R.layout.dialog_galileo_reset_factory_rule, null);
        dkl_(str, inflate);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.reminder_button_all_restores);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.reminder_button_reserved_esim);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.reminder_button_cancel);
        healthTextView.setTextSize(1, 16.0f);
        healthTextView2.setTextSize(1, 16.0f);
        healthTextView3.setTextSize(1, 16.0f);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.d);
        builder.cyp_(inflate);
        healthTextView.setOnClickListener(dkr_());
        healthTextView2.setOnClickListener(dks_());
        healthTextView3.setOnClickListener(dkt_());
        CustomAlertDialog c = builder.c();
        this.b = c;
        c.setCancelable(false);
        this.b.show();
    }

    private View.OnClickListener dkt_() {
        return new View.OnClickListener() { // from class: pag
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkJ_(view);
            }
        };
    }

    /* synthetic */ void dkJ_(View view) {
        LogUtil.a("WearHomeGeneralAction", "setResetFactoryGalileoDialog cancel");
        this.b.dismiss();
        this.b = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private View.OnClickListener dks_() {
        return new View.OnClickListener() { // from class: par
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkx_(view);
            }
        };
    }

    /* synthetic */ void dkx_(View view) {
        LogUtil.a("WearHomeGeneralAction", "setResetFactoryGalileoDialog Retain Esim Card");
        oau.c(1);
        this.b.dismiss();
        this.b = null;
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("WearHomeGeneralAction", "setResetFactoryGalileoDialog Retain Esim Card Bluetooth Disconnected");
            nrh.b(this.e, R.string._2130843214_res_0x7f02164e);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            e(R.string._2130841513_res_0x7f020fa9);
            this.d.d.cTD_(this.d.g, this.d.djG_(), true);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private View.OnClickListener dkr_() {
        return new View.OnClickListener() { // from class: pbc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkw_(view);
            }
        };
    }

    /* synthetic */ void dkw_(View view) {
        LogUtil.a("WearHomeGeneralAction", "setResetFactoryGalileoDialog All Resume Response Events");
        oau.c(1);
        this.b.dismiss();
        this.b = null;
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("WearHomeGeneralAction", "setResetFactoryGalileoDialog Recover All Bluetooth Disconnected");
            nrh.b(this.e, R.string._2130843214_res_0x7f02164e);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            e(R.string._2130841513_res_0x7f020fa9);
            this.d.d.cTD_(this.d.g, this.d.djG_(), false);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void dkl_(String str, View view) {
        ((HealthTextView) view.findViewById(R.id.reminder_dialog_title)).setText(R.string._2130841511_res_0x7f020fa7);
        ((HealthTextView) view.findViewById(R.id.reminder_device_content_one)).setText(this.e.getResources().getString(R.string._2130844516_res_0x7f021b64, str));
        ((HealthTextView) view.findViewById(R.id.reminder_device_content_two)).setText(R.string._2130844517_res_0x7f021b65);
        ((HealthTextView) view.findViewById(R.id.reminder_device_content_three)).setText(R.string._2130844518_res_0x7f021b66);
    }

    private void a(String str) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.e).b(R.string._2130841511_res_0x7f020fa7).e(str).cyU_(R.string._2130842221_res_0x7f02126d, new View.OnClickListener() { // from class: pat
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dky_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: pax
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkz_(view);
            }
        }).a();
        this.ai = a2;
        a2.setCancelable(false);
        this.ai.show();
    }

    /* synthetic */ void dky_(View view) {
        LogUtil.a("WearHomeGeneralAction", "showLoginFail ok click");
        oau.c(1);
        this.ai.dismiss();
        this.ai = null;
        if (cwi.c(this.d.b, 112)) {
            y();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ab();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void dkz_(View view) {
        LogUtil.a("WearHomeGeneralAction", "showLoginFail cancel click");
        this.ai.dismiss();
        this.ai = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void y() {
        LogUtil.a("WearHomeGeneralAction", "checkEarphoneStateBeforeRestoreFactory in.");
        jgs.c().c(this.d.b, new EarPhoneResponseCallback() { // from class: pai.19
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
            public void onResponse(int i, final cuz cuzVar) {
                pai.this.d.runOnUiThread(new Runnable() { // from class: pai.19.3
                    @Override // java.lang.Runnable
                    public void run() {
                        cuz cuzVar2 = cuzVar;
                        if (cuzVar2 == null) {
                            LogUtil.h("WearHomeGeneralAction", "earPhoneInfo is null");
                            return;
                        }
                        boolean z = cuzVar2.e() == 1;
                        boolean z2 = cuzVar.a() == 3;
                        if (!z || !z2) {
                            pai.this.ai();
                        } else {
                            pai.this.ab();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        LogUtil.a("WearHomeGeneralAction", "restoreFactoryShowEarphoneDialog in.");
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.e).b(R.string._2130846318_res_0x7f02226e).d(R.string._2130846319_res_0x7f02226f).cyU_(R.string._2130841467_res_0x7f020f7b, new View.OnClickListener() { // from class: pal
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dku_(view);
            }
        }).cyR_(R.string._2130846274_res_0x7f022242, new View.OnClickListener() { // from class: pak
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkv_(view);
            }
        }).a();
        this.f16024a = a2;
        a2.setCancelable(false);
        this.f16024a.show();
    }

    /* synthetic */ void dku_(View view) {
        LogUtil.h("WearHomeGeneralAction", "onClick restoreFactoryShowEarphoneDialog PositiveButton");
        this.f16024a.dismiss();
        this.f16024a = null;
        y();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dkv_(View view) {
        LogUtil.h("WearHomeGeneralAction", "onClick restoreFactoryShowEarphoneDialog NegativeButton");
        this.f16024a.dismiss();
        this.f16024a = null;
        ab();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.h("WearHomeGeneralAction", "setResetFactoryDialog BT switch is false!");
            nrh.b(this.e, R.string._2130843214_res_0x7f02164e);
            return;
        }
        e(R.string._2130841513_res_0x7f020fa9);
        WearHomeActivity wearHomeActivity = this.d;
        LogUtil.a("WearHomeGeneralAction", "resetFactory, mActivity: ", wearHomeActivity, ", handler: ", wearHomeActivity.djG_());
        this.d.d.cTD_(this.d.g, this.d.djG_(), false);
        DeviceInfo deviceInfo = this.d.b;
        if (deviceInfo != null) {
            oau.b(deviceInfo);
            jqi.a().setSwitchSettingToDb(jrh.b(deviceInfo.getDeviceIdentify()), "-1");
        }
    }

    public void e(int i) {
        if (!this.d.isFinishing()) {
            if (this.x == null) {
                new CommonDialog21(this.e, R.style.app_update_dialogActivity);
                CommonDialog21 a2 = CommonDialog21.a(this.e);
                this.x = a2;
                a2.e(this.d.getString(i));
                this.x.a();
                Message obtainMessage = this.d.djG_().obtainMessage();
                obtainMessage.what = 11;
                obtainMessage.obj = false;
                this.d.djG_().sendMessageDelayed(obtainMessage, 2000L);
                return;
            }
            LogUtil.h("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "mLoadingDialog is not null");
            return;
        }
        LogUtil.h("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "activity is finish");
    }

    public void q() {
        final Intent intent = new Intent();
        intent.putExtra("device_id", this.d.g);
        boolean c = cwi.c(this.d.b, 108);
        boolean c2 = cwi.c(this.d.b, 58);
        if (c2 || c) {
            if (this.y == null) {
                this.y = Boolean.valueOf(Utils.l());
            }
            final boolean z = c2 || (c && this.y.booleanValue());
            intent.putExtra("moduleName", "PluginWearAbility");
            bwf.a().launchActivity(this.d, intent, new AppBundleLauncher.InstallCallback() { // from class: pai.21
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent2) {
                    HwOtaUpgradeManager.a().d(pai.this.d.b.getDeviceUdid(), (InitCallback) null);
                    if (z) {
                        intent.setClass(context, UpdateVersionActivity.class);
                    } else {
                        intent.putExtra("UNIQUE_ID", pai.this.d.b.getDeviceUdid());
                        intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginWearAbility_A_0);
                    }
                    lsp.d().e("PluginWearAbility");
                    LogUtil.a("WearHomeGeneralAction", "PluginWearOtaProxy launchActivity call");
                    try {
                        context.startActivity(intent);
                        return false;
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("WearHomeGeneralAction", "ActivityNotFoundException intentUpdate");
                        return false;
                    }
                }
            });
            return;
        }
        intent.setClass(this.e, UpdateVersionActivity.class);
        this.d.startActivity(intent);
        nsn.ai(this.e);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010032.value(), new HashMap(16), 0);
    }

    private void ah() {
        if (this.k == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.e).b(R.string._2130842276_res_0x7f0212a4).d(R.string._2130842448_res_0x7f021350).cyU_(R.string._2130841555_res_0x7f020fd3, new View.OnClickListener() { // from class: pap
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkE_(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: pao
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.dkq_(view);
                }
            }).a();
            this.k = a2;
            a2.setCancelable(false);
        }
        if (this.d.isFinishing()) {
            return;
        }
        this.k.show();
    }

    /* synthetic */ void dkE_(View view) {
        new Handler().post(new Runnable() { // from class: pai.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("WearHomeGeneralAction", "positive startFeedBackLog");
                pai.this.ad();
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dkq_(View view) {
        LogUtil.a("WearHomeGeneralAction", "negative startFeedBackLog");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void s() {
        boolean g = jsd.g();
        this.v = false;
        if (g) {
            LogUtil.a("WearHomeGeneralAction", "updateLogResult log upload success");
            if (!g()) {
                LogUtil.a("WearHomeGeneralAction", "updateLogResult 5.10.12 not timeout");
                nrh.e(this.e, R.string._2130842453_res_0x7f021355);
            }
            this.r = false;
            jsd.e(false);
            this.d.djG_().removeMessages(1022);
            return;
        }
        LogUtil.a("WearHomeGeneralAction", "File not uploaded, waiting for file upload");
        Message obtainMessage = this.d.djG_().obtainMessage();
        obtainMessage.what = 1021;
        this.d.djG_().sendMessageDelayed(obtainMessage, 600000L);
    }

    public void c() {
        LogUtil.a("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "Enter clearMessageCenterLocalDeviceMessage");
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.getMessages("device", "device_type_connected", new IBaseResponseCallback() { // from class: pai.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i != 0 || obj == null) {
                    return;
                }
                List list = (List) obj;
                LogUtil.a("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "clearMessageCenterLocalDeviceMessage, responseMessageList.size() is ", Integer.valueOf(list.size()));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    messageCenterApi.deleteMessage(((MessageObject) it.next()).getMsgId());
                }
            }
        });
        messageCenterApi.getMessages("device", "device_ota", new IBaseResponseCallback() { // from class: pai.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && obj != null && koq.e(obj, MessageObject.class)) {
                    List list = (List) obj;
                    LogUtil.a("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "clearMessageCenterLocalDeviceMessage otaMessageList.size() is ", Integer.valueOf(list.size()));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        messageCenterApi.deleteMessage(((MessageObject) it.next()).getMsgId());
                    }
                    LogUtil.a("WearHomeGeneralAction", 0, "WearHomeGeneralAction", "Leave clearMessageCenterLocalDeviceMessage");
                }
            }
        });
        if (this.d.b != null) {
            jfq.c().e(this.d.b.getDeviceName());
            oaf.b(BaseApplication.getContext()).a(this.d.b);
        } else {
            LogUtil.h("WearHomeGeneralAction", "mCurrentDeviceInfo is null");
        }
        this.d.finish();
    }

    public void k() {
        LogUtil.a("WearHomeGeneralAction", "showIsUseFlowDialogWith2Button enter. config");
        if (!this.w || TextUtils.isEmpty(this.af)) {
            return;
        }
        d(this.af);
    }

    public void d(String str) {
        LogUtil.a("WearHomeGeneralAction", "showIsUseFlowDialogWith2Button enter.");
        CustomViewDialog customViewDialog = this.l;
        if (customViewDialog != null) {
            this.w = false;
            customViewDialog.cancel();
            this.af = "";
        }
        if (g()) {
            LogUtil.a("WearHomeGeneralAction", "showIsUseFlowDialogWith2Button 5.10.12 already timeout");
            return;
        }
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.commonui_custom_view_tow_button, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_alert_message);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.dialog_btn_upload_now);
        HealthButton healthButton2 = (HealthButton) inflate.findViewById(R.id.dialog_btn_upload_by_wifi);
        healthButton.setText(this.d.getResources().getString(R.string.IDS_device_log_upload_now));
        healthButton2.setText(this.d.getResources().getString(R.string.IDS_device_log_upload_wifi));
        healthTextView.setText(str);
        this.af = str;
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.e);
        builder.czg_(inflate);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: pae
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkI_(view);
            }
        });
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: pau
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.this.dkH_(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.l = e2;
        e2.setCancelable(false);
        if (this.d.isFinishing()) {
            return;
        }
        this.l.show();
        this.w = true;
    }

    /* synthetic */ void dkI_(View view) {
        e();
        b();
        if (!jsd.a(this.e)) {
            nrh.e(this.e, R.string._2130842450_res_0x7f021352);
            LogUtil.a("WearHomeGeneralAction", "start showIsUseFlowDialogWith2Button showLongToast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!jsd.i(jsd.b) && !jsd.i(jsd.f14045a)) {
            this.v = false;
            this.r = false;
            jsd.e(false);
            LogUtil.h("WearHomeGeneralAction", "isHasZip is not wearable log");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.v = true;
        this.r = true;
        nrh.e(this.e, R.string._2130842452_res_0x7f021354);
        LogUtil.a("WearHomeGeneralAction", "start uploading log.");
        jsd.o(this.d.g);
        Message obtainMessage = this.d.djG_().obtainMessage();
        obtainMessage.what = 1021;
        this.d.djG_().sendMessageDelayed(obtainMessage, 600000L);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dkH_(View view) {
        e();
        b();
        nrh.e(this.e, R.string._2130847323_res_0x7f02265b);
        this.r = false;
        this.v = false;
        jsd.e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d(int i, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = knl.d(this.d.g) + "BETA_QUESTION";
        try {
            long parseLong = Long.parseLong(SharedPreferenceManager.b(this.e, String.valueOf(10), str3));
            LogUtil.a("WearHomeGeneralAction", "WearHomeActivity currentTime is: ", Long.valueOf(currentTimeMillis), ", lastTime is: ", Long.valueOf(parseLong));
            if (currentTimeMillis - parseLong < 300000) {
                LogUtil.h("WearHomeGeneralAction", "WearHomeActivity Less than five minutes");
                return;
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("WearHomeGeneralAction", "WearHomeActivity NumberFormatException");
        }
        SharedPreferenceManager.e(this.e, String.valueOf(10), str3, String.valueOf(currentTimeMillis), new StorageParams(0));
        jgp a2 = jgp.a(this.e);
        a2.d(i, str, str2);
        a2.c(0, this.d.g, new DeviceDfxBaseResponseCallback() { // from class: pai.5
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
            public void onSuccess(int i2, String str4) {
                ReleaseLogUtil.e("Dfx_WearHomeGeneralAction", "handlerGetDeviceLog onSuccess successCode = ", Integer.valueOf(i2));
                pai.this.ak.c(false, pai.this.d.g);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
            public void onFailure(int i2, String str4) {
                ReleaseLogUtil.c("Dfx_WearHomeGeneralAction", "handlerGetDeviceLog onFailure errorCode = ", Integer.valueOf(i2), ", errorMessage = ", str4);
                pai.this.ak.c(false, pai.this.d.g);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
            public void onProgress(int i2, String str4) {
                LogUtil.h("WearHomeGeneralAction", "onProgress");
            }
        });
    }

    private void a(final int i, final String str) {
        LogUtil.a("WearHomeGeneralAction", "startEarphoneDownLoadProgress progress: ", Integer.valueOf(i), " and progressDesc: ", str);
        WearHomeActivity wearHomeActivity = this.d;
        if (wearHomeActivity == null) {
            LogUtil.h("WearHomeGeneralAction", "startEarphoneDownLoadProgress mActivity == null ");
        } else {
            wearHomeActivity.runOnUiThread(new Runnable() { // from class: paz
                @Override // java.lang.Runnable
                public final void run() {
                    pai.this.d(i, str);
                }
            });
        }
    }

    /* synthetic */ void d(int i, String str) {
        CustomProgressDialog customProgressDialog = this.g;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.h.d(i);
            this.h.c(i);
            return;
        }
        this.g = new CustomProgressDialog(this.e);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.e);
        this.h = builder;
        builder.d(str);
        CustomProgressDialog e2 = this.h.e();
        this.g = e2;
        e2.setCanceledOnTouchOutside(false);
        this.g.setCancelable(false);
        if (this.d.isFinishing()) {
            return;
        }
        this.g.show();
        this.h.d(0);
        this.h.c(0);
    }

    private void j(final DeviceInfo deviceInfo) {
        if (this.d == null) {
            LogUtil.h("WearHomeGeneralAction", "startEarphoneDownLoadProgress mActivity == null ");
            return;
        }
        if (this.n == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.e).b(R.string._2130846273_res_0x7f022241).d(R.string._2130846265_res_0x7f022239).c(true).cyU_(R.string._2130841555_res_0x7f020fd3, new View.OnClickListener() { // from class: paq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkA_(deviceInfo, view);
                }
            }).cyR_(R.string._2130846274_res_0x7f022242, new View.OnClickListener() { // from class: pas
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkB_(deviceInfo, view);
                }
            }).a();
            this.n = a2;
            a2.setCancelable(false);
        }
        if (this.d.isFinishing()) {
            return;
        }
        this.n.show();
    }

    /* synthetic */ void dkA_(DeviceInfo deviceInfo, View view) {
        f(deviceInfo);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dkB_(DeviceInfo deviceInfo, View view) {
        LogUtil.a("WearHomeGeneralAction", "negative showEarphoneLogCollectDialog");
        d(deviceInfo);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final DeviceInfo deviceInfo) {
        if (this.m == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.e).b(R.string._2130846267_res_0x7f02223b).d(R.string._2130846313_res_0x7f022269).cyU_(R.string.IDS_notify_retry, new View.OnClickListener() { // from class: pan
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkC_(deviceInfo, view);
                }
            }).cyR_(R.string._2130846274_res_0x7f022242, new View.OnClickListener() { // from class: pam
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkD_(deviceInfo, view);
                }
            }).a();
            this.m = a2;
            a2.setCancelable(false);
        }
        if (this.d.isFinishing()) {
            return;
        }
        this.m.show();
    }

    /* synthetic */ void dkC_(DeviceInfo deviceInfo, View view) {
        j(deviceInfo);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dkD_(DeviceInfo deviceInfo, View view) {
        LogUtil.a("WearHomeGeneralAction", "negative showEarphoneLogCollectFailedDialog");
        b(deviceInfo);
        jfq.c().a("earphoneLogPre");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo) {
        WearHomeActivity wearHomeActivity = this.d;
        if (wearHomeActivity == null || wearHomeActivity.djG_() == null) {
            return;
        }
        this.d.djG_().post(new Runnable() { // from class: pai.10
            @Override // java.lang.Runnable
            public void run() {
                pai.this.an();
            }
        });
    }

    private void f(DeviceInfo deviceInfo) {
        a(0, this.e.getString(R.string._2130846266_res_0x7f02223a));
        jfq.c().e("earphoneLogPre", this.c);
        LogUtil.a("WearHomeGeneralAction", "start sendDeviceEvent");
        jfq.c().d("earphoneLogPre", deviceInfo, 0, "1");
    }

    private void d(DeviceInfo deviceInfo) {
        WearHomeActivity wearHomeActivity = this.d;
        if (wearHomeActivity == null || wearHomeActivity.djG_() == null) {
            return;
        }
        this.d.djG_().post(new Runnable() { // from class: pai.6
            @Override // java.lang.Runnable
            public void run() {
                pai.this.an();
                jfq.c().a("earphoneLogPre");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, boolean z) {
        LogUtil.a("WearHomeGeneralAction", "progress: ", Integer.valueOf(i), ",isTimeout: ", Boolean.valueOf(z));
        a(i, this.e.getString(R.string._2130846266_res_0x7f02223a));
    }

    private void x() {
        HealthFeedbackParams healthFeedbackParams = new HealthFeedbackParams();
        DeviceInfo a2 = oxa.a().a(this.d.g);
        if (a2 != null) {
            LogUtil.a("WearHomeGeneralAction", "goBetaFeedBack");
            String securityDeviceId = a2.getSecurityDeviceId();
            String deviceIdentify = a2.getDeviceIdentify();
            String udidFromDevice = a2.getUdidFromDevice();
            int productType = a2.getProductType();
            if (TextUtils.isEmpty(udidFromDevice)) {
                udidFromDevice = b(securityDeviceId, deviceIdentify, productType);
            }
            healthFeedbackParams.setDeviceId(udidFromDevice);
            healthFeedbackParams.setProductVersion(a2.getSoftVersion());
            a(healthFeedbackParams, a2);
            c(healthFeedbackParams);
            return;
        }
        LogUtil.c("WearHomeGeneralAction", "no device");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        LogUtil.a("WearHomeGeneralAction", "postStartFeedBackLog in. ");
        DeviceInfo a2 = oxa.a().a(this.d.g);
        boolean c = cwi.c(a2, 134);
        LogUtil.a("WearHomeGeneralAction", "handlerGetDeviceLog isSupportGet earPhone log isSupportEarphoneLogCollect: ", Boolean.valueOf(c));
        if (c) {
            j(a2);
        } else {
            an();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        LogUtil.a("WearHomeGeneralAction", "send startFeedBackLog");
        if (!jsd.a(this.e)) {
            LogUtil.a("WearHomeGeneralAction", "FeedBackLog is NoNetworkActive");
            nrh.e(this.e, R.string._2130842450_res_0x7f021352);
            return;
        }
        DeviceInfo a2 = oxa.a().a(this.d.g);
        if (a2 != null && a2.getDeviceConnectState() != 2) {
            LogUtil.h("WearHomeGeneralAction", "bt is dmIsConnected");
            c(R.string._2130847873_res_0x7f022881, "");
            return;
        }
        if (jgp.a(this.e).b(this.d.b)) {
            LogUtil.a("WearHomeGeneralAction", "mIsSelected: ", Boolean.valueOf(this.u));
            this.p = true;
            if (this.u) {
                c(a2);
                return;
            } else {
                w();
                return;
            }
        }
        LogUtil.a("WearHomeGeneralAction", "device is not supported");
        if (!jsd.a(this.e)) {
            b();
            this.r = false;
            jsd.e(false);
            nrh.e(this.e, R.string._2130842450_res_0x7f021352);
            return;
        }
        if (!jsd.c(this.e)) {
            LogUtil.a("WearHomeGeneralAction", "wifi is dmIsConnected , use network");
            jsd.e(true);
            jgp.a(BaseApplication.getContext()).d(false, this.d.g);
            jsd.e(this.ae);
            return;
        }
        this.r = true;
        b();
        nrh.e(this.e, R.string._2130842452_res_0x7f021354);
        LogUtil.a("WearHomeGeneralAction", "start logging wifi uploading");
        jgp.a(BaseApplication.getContext()).d(false, this.d.g);
        Message obtain = Message.obtain();
        obtain.what = 1021;
        this.d.djG_().sendMessageDelayed(obtain, 600000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        LogUtil.a("WearHomeGeneralAction", "collectDeviceLog");
        this.ah = this.e.getString(R.string._2130847869_res_0x7f02287d);
        am();
        DeviceInfo b2 = jpt.b(this.d.g, "WearHomeGeneralAction");
        if (b2 != null && jsd.g(b2.getDeviceIdentify()) && cwi.c(b2, 217)) {
            Message obtain = Message.obtain();
            obtain.what = 1041;
            this.d.djG_().sendMessageDelayed(obtain, 420000L);
            c(false);
        }
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pai.8
            @Override // java.lang.Runnable
            public void run() {
                jgp.a(pai.this.e).a(pai.this.d.g, pai.this.i);
            }
        });
    }

    private void c(DeviceInfo deviceInfo) {
        this.ah = this.e.getString(R.string.IDS_device_capture_tips);
        am();
        e(deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo) {
        jgp.a(this.e).c(deviceInfo, new IBaseResponseCallback() { // from class: pai.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeGeneralAction", "onResponse errorCode: ", Integer.valueOf(i));
                jgp.a(pai.this.e).a();
                pai.this.d.runOnUiThread(new Runnable() { // from class: pai.7.3
                    @Override // java.lang.Runnable
                    public void run() {
                        pai.this.b();
                        pai.this.w();
                    }
                });
            }
        });
    }

    public void a() {
        LogUtil.a("WearHomeGeneralAction", "goBetaFeedBack");
        x();
    }

    private String b(String str, String str2, int i) {
        String d;
        boolean z = !str2.contains(":") && str2.length() > 12;
        if (!Utils.o() && !z) {
            d = str2.replace(":", "");
        } else {
            d = knl.d(str);
            if (d.length() >= 24) {
                d = b(d);
            }
        }
        if (Utils.o() || i < 34) {
            return d;
        }
        if (!str.equals(str2)) {
            return knl.a(str + str2);
        }
        return knl.a(str);
    }

    private String b(String str) {
        return str == null ? "" : str.length() >= 24 ? str.replace(Marker.ANY_NON_NULL_MARKER, "A").replace("/", "A").replace("=", "A").substring(0, 24) : str;
    }

    private void c(HealthFeedbackParams healthFeedbackParams) {
        LogUtil.a("WearHomeGeneralAction", "sendFeedback");
        jgn c = jgn.c();
        Context context = this.e;
        WearHomeActivity wearHomeActivity = this.d;
        c.e(context, healthFeedbackParams, new e(wearHomeActivity, wearHomeActivity.djG_()));
    }

    public void f() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        builder.e(R.string._2130845232_res_0x7f021e30);
        builder.czC_(R.string._2130844176_res_0x7f021a10, new View.OnClickListener() { // from class: pay
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.dko_(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        if (e2.isShowing()) {
            return;
        }
        e2.show();
    }

    static /* synthetic */ void dko_(View view) {
        LogUtil.a("WearHomeGeneralAction", "factory reset no satisfation condition");
        ViewClickInstrumentation.clickOnView(view);
    }

    static class e implements HealthFeedbackCallback {
        private WeakReference<Activity> c;
        private Handler e;

        private e(Activity activity, Handler handler) {
            this.c = new WeakReference<>(activity);
            this.e = handler;
        }

        @Override // com.huawei.hwcrowdtestapi.HealthFeedbackCallback
        public void onFailed(String str) {
            Activity activity = this.c.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                LogUtil.h("WearHomeGeneralAction", "onFailed activity is null, finish or destroyed");
            } else {
                LogUtil.c("WearHomeGeneralAction", "gotoFeedBack failed");
            }
        }

        @Override // com.huawei.hwcrowdtestapi.HealthFeedbackCallback
        public jer collectLogs(int i, String str, String str2, boolean z) {
            LogUtil.a("WearHomeGeneralAction", "bugTypeId: ", Integer.valueOf(i), "tbdtsNo: ", str2, ";fileLogId: ", str);
            Activity activity = this.c.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                LogUtil.h("WearHomeGeneralAction", "onFailed activity is null, finish or destroyed");
                return null;
            }
            jsd.a(z);
            Object d = nse.d(913000005);
            nse.b(d, nse.b("E913000005_ERRCODE_TINYINT"), 0);
            nse.b(d, nse.b("E913000005_SUBERRCODE_TINYINT"), 1);
            LogUtil.a("WearHomeGeneralAction", "ReflectionIMonitor isSendEvent is", Boolean.valueOf(nse.e(d)));
            nse.d(d);
            ArrayList arrayList = new ArrayList(0);
            String m = LogConfig.m();
            if (!Utils.o() && !TextUtils.isEmpty(m)) {
                LogUtil.a("WearHomeGeneralAction", "add tracktest path");
                arrayList.add(m + "tracktest");
            }
            arrayList.add(jsd.c + "MaintenanceLog");
            arrayList.add(jsd.c + oyf.f16004a);
            arrayList.add(jsd.c + oyf.d);
            arrayList.add(jsd.c + "com.huawei.version.json");
            arrayList.add(jsd.c + "huawei_crashLog_0.txt");
            arrayList.add(jsd.c + "app_config_value.txt");
            arrayList.add(jsd.c + oyf.b);
            arrayList.add(oyf.e);
            DfxBaseHandler.getAllDfxLogFileToPathList(new File(jsd.c), arrayList);
            if (CommonUtil.as() && CommonUtil.bh()) {
                arrayList.add(LogConfig.d);
            }
            jer jerVar = new jer();
            jerVar.a(arrayList);
            jerVar.c(200);
            Message obtain = Message.obtain();
            obtain.what = 1023;
            Bundle bundle = new Bundle();
            bundle.putInt("bugTypeId", i);
            bundle.putString("fileLogId", str);
            bundle.putString("dtsNumber", str2);
            obtain.setData(bundle);
            this.e.sendMessageDelayed(obtain, 5000L);
            return jerVar;
        }
    }

    private void a(HealthFeedbackParams healthFeedbackParams, DeviceInfo deviceInfo) {
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        if (!TextUtils.isEmpty(securityDeviceId) && securityDeviceId.contains(":")) {
            securityDeviceId = securityDeviceId.replace(":", "");
        }
        int productType = deviceInfo.getProductType();
        String deviceModel = deviceInfo.getDeviceModel();
        String d = jrx.d().d(productType, jrx.d().b(productType, deviceModel), deviceModel);
        if (deviceModel != null && cvz.e(deviceModel)) {
            LogUtil.a("WearHomeGeneralAction", "addGoBetaFeedBackParams currentDevice is honor device");
            healthFeedbackParams.setIsHonorDevice(true);
        }
        healthFeedbackParams.setProductName(d);
        healthFeedbackParams.setDeviceModel(jsd.a(d));
        healthFeedbackParams.setDeviceSn(securityDeviceId);
        if (Utils.o()) {
            healthFeedbackParams.setProductType(20);
        } else {
            healthFeedbackParams.setProductType(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        if (this.d.isFinishing()) {
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.e);
        builder.b(R.string.IDS_service_area_notice_title);
        String string = this.d.getString(i);
        builder.e(string);
        ReleaseLogUtil.e("Dfx_WearHomeGeneralAction", "showErrDialog content = " + string + ", errorCode = " + str);
        sqo.p("Dfx_WearHomeGeneralActionshowErrDialog content = " + string + ", errorCode = " + str);
        builder.cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: paj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pai.dkp_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    static /* synthetic */ void dkp_(View view) {
        LogUtil.a("WearHomeGeneralAction", "showErrDialog onclick PositiveButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void am() {
        LogUtil.a("WearHomeGeneralAction", "showLoadingDialog()");
        if (this.d.isFinishing()) {
            return;
        }
        CommonDialog21 commonDialog21 = this.aa;
        if (commonDialog21 == null) {
            new CommonDialog21(this.e, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(this.e);
            this.aa = a2;
            a2.e(this.ah);
            this.aa.setCancelable(false);
        } else {
            commonDialog21.e(this.ah);
        }
        if (this.aa.isShowing()) {
            return;
        }
        this.aa.a();
        LogUtil.a("WearHomeGeneralAction", "mLoadingUserInformationDialog.show()");
    }

    public void b() {
        if (this.d.isFinishing()) {
            return;
        }
        LogUtil.a("WearHomeGeneralAction", "enter dismissLoadingDialog()");
        CommonDialog21 commonDialog21 = this.aa;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("WearHomeGeneralAction", "dismissLoadingDialog()!");
        this.aa.cancel();
        this.aa = null;
    }

    public void e() {
        if (this.d.isFinishing()) {
            return;
        }
        LogUtil.a("WearHomeGeneralAction", "enter dismissIsUseFlowDialogWith2Button()");
        CustomViewDialog customViewDialog = this.l;
        if (customViewDialog == null || !customViewDialog.isShowing()) {
            return;
        }
        LogUtil.a("WearHomeGeneralAction", "dismissIsUseFlowDialogWith2Button()!");
        this.w = false;
        this.l.dismiss();
        this.l = null;
        this.af = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(CustomProgressDialog customProgressDialog) {
        LogUtil.a("WearHomeGeneralAction", "enter closeProgress");
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.d.isFinishing()) {
            return;
        }
        customProgressDialog.cancel();
        LogUtil.a("WearHomeGeneralAction", "enter closeProgress cancel");
    }

    public void b(int i, String str) {
        CustomProgressDialog customProgressDialog = this.j;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.f.d(i);
            this.f.c(i);
            this.f.d(str);
            return;
        }
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.e);
        this.f = builder;
        builder.d(str);
        CustomProgressDialog e2 = this.f.e();
        this.j = e2;
        e2.setCanceledOnTouchOutside(false);
        this.j.setCancelable(false);
        if (this.d.isFinishing()) {
            return;
        }
        this.j.show();
        this.f.d(0);
        this.f.c(0);
    }

    public void e(final int i, final String str) {
        LogUtil.a("WearHomeGeneralAction", "get device log failed, errorMessage = ", str);
        c(this.j);
        SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
        this.p = false;
        WearHomeActivity wearHomeActivity = this.d;
        if (wearHomeActivity == null || wearHomeActivity.djG_() == null) {
            return;
        }
        this.d.djG_().post(new Runnable() { // from class: pai.9
            @Override // java.lang.Runnable
            public void run() {
                pai.this.b();
                if (!pai.this.g()) {
                    LogUtil.h("WearHomeGeneralAction", "doOnFailure 5.10.12 not timeout");
                    if (TextUtils.equals(str, "5.10.12 cancel timeout 7 minutes")) {
                        pai.this.c(R.string._2130847446_res_0x7f0226d6, i + "");
                        pai.this.c(true);
                    } else {
                        pai.this.c(R.string._2130847873_res_0x7f022881, i + "");
                    }
                }
                jgp.a(BaseApplication.getContext()).d(false, pai.this.d.g);
            }
        });
    }

    public void t() {
        LogUtil.a("WearHomeGeneralAction", "enter startDetection");
        DeviceInfo a2 = oxa.a().a(this.d.g);
        if (a2 == null) {
            LogUtil.h("WearHomeGeneralAction", "deviceInfo is null");
        } else {
            jge.b().c(a2, 0);
        }
    }

    public void r() {
        this.u = false;
        this.s = false;
        if (this.v) {
            ak();
            return;
        }
        DeviceInfo a2 = oxa.a().a(this.d.g);
        if (a2 != null) {
            a(a2);
        }
    }

    private void a(DeviceInfo deviceInfo) {
        DeviceInfo b2;
        if (deviceInfo.getExpandCapability() == null && (b2 = jpt.b(deviceInfo.getDeviceIdentify(), "WearHomeGeneralAction")) != null) {
            deviceInfo.setExpandCapability(b2.getExpandCapability());
        }
        boolean c = cwi.c(deviceInfo, 32);
        LogUtil.a("WearHomeGeneralAction", "isSupportCapture: ", Boolean.valueOf(c));
        if (c) {
            af();
        } else {
            ah();
        }
    }

    private void ak() {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.hw_show_uploading_view, (ViewGroup) null);
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.d).cyn_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: pai.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (pai.this.al != null) {
                    pai.this.al.dismiss();
                    pai.this.al = null;
                }
                pai.this.d.e(WearHomeActivity.EnumDialog.RATE);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        this.al = cyn_.c();
        cyn_.cyp_(inflate);
        this.al.setCancelable(false);
        if (this.al.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.al.show();
    }

    private void af() {
        if (this.o == null) {
            View inflate = LayoutInflater.from(this.e).inflate(R.layout.hw_show_select_option_view, (ViewGroup) null);
            CustomViewDialog e2 = new CustomViewDialog.Builder(this.e).d(R.string._2130842276_res_0x7f0212a4).czg_(inflate).cze_(R.string._2130841555_res_0x7f020fd3, new View.OnClickListener() { // from class: pav
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkF_(view);
                }
            }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: paw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pai.this.dkG_(view);
                }
            }).e();
            this.o = e2;
            e2.setCancelable(false);
            if (!dkn_(inflate)) {
                LogUtil.h("WearHomeGeneralAction", "showGenderPickerDialog() dialog layout fail");
                this.o = null;
                return;
            }
        }
        if (this.d.isFinishing()) {
            return;
        }
        aa();
        this.o.show();
    }

    /* synthetic */ void dkF_(View view) {
        new Handler().post(new Runnable() { // from class: pai.11
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("WearHomeGeneralAction", "positive startFeedBackLog: ", Boolean.valueOf(pai.this.u));
                SharedPreferenceManager.e(String.valueOf(10), "KEY_IS_CLICKED_BETA_FEEDBACK", true);
                pai.this.ad();
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dkG_(View view) {
        LogUtil.a("WearHomeGeneralAction", "negative startFeedBackLog: ", Boolean.valueOf(this.u));
        SharedPreferenceManager.e(String.valueOf(10), "KEY_IS_CLICKED_BETA_FEEDBACK", false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean dkn_(View view) {
        LogUtil.a("WearHomeGeneralAction", "initializeGenderDialogLayout()");
        if (view == null) {
            return false;
        }
        this.q = (HealthTextView) view.findViewById(R.id.textView_content);
        this.z = (HealthRadioButton) view.findViewById(R.id.radiobutton_log_include_capture);
        this.ac = (HealthRadioButton) view.findViewById(R.id.radiobutton_log_exclude_capture);
        this.ag = (RelativeLayout) view.findViewById(R.id.relativelayout_log_include_capture);
        this.ab = (RelativeLayout) view.findViewById(R.id.relativelayout_log_exclude_capture);
        this.ag.setOnClickListener(new b(true));
        this.ab.setOnClickListener(new b(false));
        this.q.setText(R.string._2130842448_res_0x7f021350);
        aa();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        if (this.u) {
            this.z.setChecked(true);
            this.ac.setChecked(false);
        } else {
            this.z.setChecked(false);
            this.ac.setChecked(true);
        }
    }

    class b implements View.OnClickListener {
        private boolean e;

        b(boolean z) {
            this.e = z;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("WearHomeGeneralAction", "MySelectionOptionOnClickListener onClick(), isSelect:", Boolean.valueOf(this.e));
            pai.this.u = this.e;
            pai.this.aa();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void l() {
        if (this.d.p != null) {
            LogUtil.a("WearHomeGeneralAction", "upload log DEVICE_GOTO_FEEDBACK");
            this.d.p.c();
        }
    }

    public void p() {
        z();
        GRSManager.a(this.e).e("helpCustomerUrl", new GrsQueryCallback() { // from class: pai.12
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    pai.this.e(str);
                } else {
                    LogUtil.h("WearHomeGeneralAction", "obtainTipsUrlDomain urlDomain is empty");
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("WearHomeGeneralAction", "obtainTipsUrlDomain onCallBackFail");
            }
        });
    }

    private void z() {
        if (CommonUtil.bv() && LoginInit.getInstance(this.e).getIsLogined() && !pep.d() && !ixj.b().a() && !ixj.b().h()) {
            LogUtil.a("WearHomeGeneralAction", "enter initFeedbackSdk");
            ixj.b().bCO_(false, null);
        } else {
            LogUtil.h("WearHomeGeneralAction", "initData , phoneservicesdk is not initialized");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008c A[Catch: ActivityNotFoundException -> 0x00a9, TryCatch #0 {ActivityNotFoundException -> 0x00a9, blocks: (B:6:0x0066, B:8:0x0077, B:12:0x0083, B:14:0x008c, B:15:0x0091, B:19:0x008f), top: B:5:0x0066 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008f A[Catch: ActivityNotFoundException -> 0x00a9, TryCatch #0 {ActivityNotFoundException -> 0x00a9, blocks: (B:6:0x0066, B:8:0x0077, B:12:0x0083, B:14:0x008c, B:15:0x0091, B:19:0x008f), top: B:5:0x0066 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "#/help?cid=11069"
            java.lang.String r6 = com.huawei.operation.utils.HelpCustomerOperate.getHelpCustomerUrl(r6, r0)
            boolean r0 = r5.ac()
            java.lang.String r1 = "Dfx_WearHomeGeneralAction"
            if (r0 == 0) goto L44
            com.huawei.ui.homewear21.home.WearHomeActivity r0 = r5.d
            com.huawei.health.devicemgr.business.entity.DeviceInfo r0 = r0.b
            r2 = 62
            boolean r0 = defpackage.cwi.c(r0, r2)
            java.lang.String r2 = "startWebViewActivity isEnhanceMode: "
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r2)
            com.huawei.ui.homewear21.home.WearHomeActivity r2 = r5.d
            com.huawei.health.devicemgr.business.entity.DeviceInfo r2 = r2.b
            java.lang.String r2 = r2.getDeviceIdentify()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "&isDevice=1&unitId="
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = "&isEnhancementMode="
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            goto L46
        L44:
            java.lang.String r0 = "&isDevice=1"
        L46:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            r2.append(r0)
            java.lang.String r6 = r2.toString()
            boolean r0 = r5.ac()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.String r2 = "startWebViewActivity isSupport: "
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            android.content.Intent r0 = new android.content.Intent     // Catch: android.content.ActivityNotFoundException -> La9
            android.content.Context r1 = r5.e     // Catch: android.content.ActivityNotFoundException -> La9
            java.lang.Class<com.huawei.operation.activity.WebViewActivity> r2 = com.huawei.operation.activity.WebViewActivity.class
            r0.<init>(r1, r2)     // Catch: android.content.ActivityNotFoundException -> La9
            android.content.Context r1 = r5.e     // Catch: android.content.ActivityNotFoundException -> La9
            boolean r1 = defpackage.njn.e(r1)     // Catch: android.content.ActivityNotFoundException -> La9
            if (r1 != 0) goto L82
            android.content.Context r1 = r5.e     // Catch: android.content.ActivityNotFoundException -> La9
            boolean r1 = com.huawei.operation.utils.Utils.isShowJapanCustomer(r1)     // Catch: android.content.ActivityNotFoundException -> La9
            if (r1 == 0) goto L80
            goto L82
        L80:
            r1 = 0
            goto L83
        L82:
            r1 = 1
        L83:
            java.lang.String r2 = "url"
            r0.putExtra(r2, r6)     // Catch: android.content.ActivityNotFoundException -> La9
            android.content.Context r6 = r5.e     // Catch: android.content.ActivityNotFoundException -> La9
            if (r1 == 0) goto L8f
            int r1 = com.huawei.ui.main.R$string.IDS_hw_personal_cetenr_help_customer_service     // Catch: android.content.ActivityNotFoundException -> La9
            goto L91
        L8f:
            int r1 = com.huawei.ui.main.R$string.IDS_hwh_health_vo2max_help     // Catch: android.content.ActivityNotFoundException -> La9
        L91:
            java.lang.String r2 = "title"
            java.lang.String r6 = r6.getString(r1)     // Catch: android.content.ActivityNotFoundException -> La9
            r0.putExtra(r2, r6)     // Catch: android.content.ActivityNotFoundException -> La9
            java.lang.String r6 = "deviceId"
            com.huawei.ui.homewear21.home.WearHomeActivity r1 = r5.d     // Catch: android.content.ActivityNotFoundException -> La9
            java.lang.String r1 = r1.g     // Catch: android.content.ActivityNotFoundException -> La9
            r0.putExtra(r6, r1)     // Catch: android.content.ActivityNotFoundException -> La9
            android.content.Context r6 = r5.e     // Catch: android.content.ActivityNotFoundException -> La9
            r6.startActivity(r0)     // Catch: android.content.ActivityNotFoundException -> La9
            goto Lb4
        La9:
            java.lang.String r6 = "startWebViewActivity ActivityNotFoundException"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r0 = "WearHomeGeneralAction"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r6)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pai.e(java.lang.String):void");
    }

    private boolean ac() {
        DeviceInfo deviceInfo = this.d.b;
        if (deviceInfo == null) {
            return false;
        }
        LogUtil.a("WearHomeGeneralAction", "isHonorDevice: ", Boolean.valueOf(cvz.c(deviceInfo)));
        if (cvz.c(deviceInfo)) {
            return false;
        }
        int versionType = this.d.b.getVersionType();
        ReleaseLogUtil.e("Dfx_WearHomeGeneralAction", "Device version type is ", Integer.valueOf(versionType));
        boolean z = versionType == 0;
        boolean i = Utils.i();
        boolean e2 = ixj.e();
        boolean o = Utils.o();
        boolean z2 = (!z || pep.d() || jsd.g(this.d.g)) ? false : true;
        boolean z3 = (o || !z2 || ixj.c()) ? false : true;
        boolean z4 = o && z2 && e2 && i;
        ReleaseLogUtil.e("Dfx_WearHomeGeneralAction", "isAllowLogin: ", Boolean.valueOf(i), ", isOpen: ", Boolean.valueOf(e2), ", isOverSea: ", Boolean.valueOf(o));
        return z3 || z4;
    }

    public void o() {
        LogUtil.a("WearHomeGeneralAction", "onDestory enter");
        pep.dmY_(this.e, this.am);
        pep.dmZ_(this.an);
        pep.dmY_(this.e, this.ad);
        jfq.c().a("earphoneLogPre");
        this.v = false;
        this.r = false;
        jsd.e(false);
        SharedPreferenceManager.e(String.valueOf(10), "KEY_IS_CLICKED_BETA_FEEDBACK", false);
        SharedPreferenceManager.b(String.valueOf(10), "KEY_RETRY_BETA_FEEDBACK_TIMES", 0);
        c(false);
    }

    public boolean j() {
        return this.s;
    }

    public void d(boolean z) {
        this.v = z;
    }
}
