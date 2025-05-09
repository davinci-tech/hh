package com.huawei.ui.device.activity.update;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressindicator.HealthProgressIndicator;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.cuz;
import defpackage.cvs;
import defpackage.cwc;
import defpackage.cwi;
import defpackage.jgs;
import defpackage.jkj;
import defpackage.jkk;
import defpackage.jkn;
import defpackage.jkv;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.kxz;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nyi;
import defpackage.oab;
import defpackage.oaf;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class DeviceOtaActivity extends BaseActivity implements View.OnClickListener {
    private static int c = 50;
    private static int e = 99;
    private HealthTextView aa;
    private LinearLayout ab;
    private RelativeLayout ac;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private c ah;
    private HealthTextView ai;
    private CustomTextAlertDialog f;
    private long g;
    private DeviceInfo h;
    private HealthProgressIndicator i;
    private ImageView j;
    private LayoutInflater k;
    private String m;
    private HealthTextView p;
    private oaf r;
    private HealthTextView u;
    private long x;
    private LinearLayout y;
    private HealthButton z;
    private int w = 0;

    /* renamed from: a, reason: collision with root package name */
    private Context f9247a = null;
    private int v = -1;
    private BroadcastReceiver b = null;
    private boolean n = true;
    private boolean l = false;
    private int q = 0;
    private CustomAlertDialog s = null;
    private boolean o = false;
    private String ad = "";
    private final BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExtra;
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "mConnectStateChangedReceiver(): mUpgradeHandler = ", DeviceOtaActivity.this.ah, ",intent = ", intent.getAction());
            if (context == null || DeviceOtaActivity.this.ah == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (parcelableExtra = intent.getParcelableExtra("deviceinfo")) == null) {
                return;
            }
            if (!(parcelableExtra instanceof DeviceInfo)) {
                LogUtil.a("DeviceOtaActivity", "! parcelableExtra instanceof DeviceInfo ");
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
            if (!TextUtils.equals(deviceInfo.getDeviceIdentify(), DeviceOtaActivity.this.m)) {
                LogUtil.h("DeviceOtaActivity", "!TextUtils.equals(deviceIdentify, mMacAddress)");
                return;
            }
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "mConnectStateChangedReceiver(): state = ", Integer.valueOf(deviceConnectState), ",deviceInfo = ", CommonUtil.l(deviceInfo.toString()));
            DeviceOtaActivity.this.e(deviceConnectState);
        }
    };
    private IOTAResultAIDLCallback.Stub t = new IOTAResultAIDLCallback.Stub() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.2
        @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
        public void onFileTransferState(int i) {
            if (DeviceOtaActivity.this.ah != null) {
                Message obtainMessage = DeviceOtaActivity.this.ah.obtainMessage();
                if (DeviceOtaActivity.this.r != null) {
                    obtainMessage.what = 4;
                    obtainMessage.arg1 = i;
                    DeviceOtaActivity.this.ah.sendMessage(obtainMessage);
                }
            }
        }

        @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onUpgradeFailed: onUpgradeFailed = ", Integer.valueOf(i), " errorMessage = ", str, " mRequestCount ", Integer.valueOf(DeviceOtaActivity.this.w));
            if (i == 109020) {
                jkj.d().a(DeviceOtaActivity.this.m, DeviceOtaActivity.this.t);
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "handleMessage registerBackgroundCallBack");
                DeviceOtaActivity.this.q = 0;
                return;
            }
            if (i == 109019) {
                jkj.d().d(DeviceOtaActivity.this.m, 6);
                jkj.d().c(DeviceOtaActivity.this.t);
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "handleMessage registerOTACallBack");
                DeviceOtaActivity.this.q = 0;
                return;
            }
            LogUtil.a("DeviceOtaActivity", "onUpgradeFailed default");
            if (i != 1 || DeviceOtaActivity.this.q != 1) {
                if (DeviceOtaActivity.this.ah != null) {
                    Message obtainMessage = DeviceOtaActivity.this.ah.obtainMessage();
                    obtainMessage.what = 6;
                    obtainMessage.arg1 = i;
                    if (DeviceOtaActivity.this.o) {
                        obtainMessage.arg2 = 1;
                        DeviceOtaActivity.this.o = false;
                    } else {
                        obtainMessage.arg2 = 0;
                    }
                    if (i == 109002 && !TextUtils.isEmpty(str)) {
                        try {
                            int unused = DeviceOtaActivity.c = Integer.parseInt(str);
                        } catch (NumberFormatException unused2) {
                            LogUtil.b("DeviceOtaActivity", "NumberFormatException");
                            sqo.as("NumberFormatException");
                        }
                        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "battery low value ：", Integer.valueOf(DeviceOtaActivity.c));
                    }
                    DeviceOtaActivity.this.ah.sendMessage(obtainMessage);
                } else {
                    LogUtil.h("DeviceOtaActivity", "onUpgradeFailed default.");
                }
            } else {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "device has already failed");
            }
            DeviceOtaActivity.this.d(6, i);
            DeviceOtaActivity.this.l();
        }

        @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
        public void onFileRespond(int i) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onFileRespond: checkResult = ", Integer.valueOf(i));
            if (DeviceOtaActivity.this.ah != null) {
                c cVar = DeviceOtaActivity.this.ah;
                if (i != 0) {
                    DeviceOtaActivity.this.y();
                    Message obtainMessage = cVar.obtainMessage();
                    obtainMessage.what = 5;
                    cVar.sendMessage(obtainMessage);
                    DeviceOtaActivity.this.g = System.currentTimeMillis();
                    DeviceOtaActivity.this.d(5, i);
                } else {
                    LogUtil.a("DeviceOtaActivity", "onFileRespond DeviceUpgradeCallback");
                    Message obtainMessage2 = cVar.obtainMessage();
                    obtainMessage2.what = 6;
                    obtainMessage2.arg1 = 1002;
                    cVar.sendMessage(obtainMessage2);
                    DeviceOtaActivity.this.d(6, 1002);
                }
                DeviceOtaActivity.this.l();
            }
        }

        @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
        public void onFileTransferReport(String str) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "mOtaResultAidlCallback，transferWay: ", str);
            try {
                String optString = new JSONObject(str).optString("transferWay");
                if (TextUtils.isEmpty(optString) || optString.equals(DeviceOtaActivity.this.ad)) {
                    return;
                }
                DeviceOtaActivity.this.ad = optString;
                if (DeviceOtaActivity.this.ah != null) {
                    DeviceOtaActivity.this.ah.sendEmptyMessage(9);
                }
            } catch (JSONException e2) {
                LogUtil.b("DeviceOtaActivity", "onFileTransferReport JSONException e:", ExceptionUtils.d(e2));
                sqo.as("onFileTransferReport JSONException");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        SharedPreferenceManager.d(BaseApplication.getContext(), System.currentTimeMillis(), "EXCE_OTA_APP_UPG_TRANS_STOP_TIME");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "handleConnectChange state is,", Integer.valueOf(i));
        if (i == 2) {
            LogUtil.a("DeviceOtaActivity", "device connected");
            this.n = true;
            return;
        }
        if (i == 3) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "mConnectStateChangedReceiver V2 mUpdateStatus = ", Integer.valueOf(h()));
            if (h() != 12) {
                a(this.f9247a.getString(R.string._2130841439_res_0x7f020f5f));
                jkj.d().d(this.m, 7);
                jkj.d().i();
                jkj.d().g();
            }
            this.n = false;
            return;
        }
        if (i == 4) {
            if (h() != 12) {
                a(this.f9247a.getString(R.string.IDS_device_switch_device_connect_fail));
                jkj.d().d(this.m, 7);
                jkj.d().i();
                jkj.d().g();
                return;
            }
            return;
        }
        LogUtil.a("DeviceOtaActivity", "handleConnectChange default");
    }

    private int h() {
        return jkj.d().c(this.m);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9247a = this;
        if (getSystemService("layout_inflater") instanceof LayoutInflater) {
            this.k = (LayoutInflater) getSystemService("layout_inflater");
        }
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onCreate()");
        this.r = oaf.b(this.f9247a);
        if (getIntent() != null) {
            boolean booleanExtra = getIntent().getBooleanExtra("is_package_already_exists", false);
            this.l = booleanExtra;
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onCreate mIsPackageAlreadyExists = ", Boolean.valueOf(booleanExtra));
            this.m = getIntent().getStringExtra("device_id");
        }
        this.ah = new c(this);
        setContentView(R.layout.activity_device_ota_new);
        boolean isEmpty = TextUtils.isEmpty(this.m);
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onCreate isEmpty = ", Boolean.valueOf(isEmpty));
        if (isEmpty) {
            finish();
            return;
        }
        this.h = jkj.d().e(this.m);
        i();
        k();
        x();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onNewIntent enter");
        x();
    }

    private void i() {
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "Enter initView!");
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.update_error_layout);
        this.ac = relativeLayout;
        relativeLayout.setVisibility(0);
        this.aa = (HealthTextView) nsy.cMc_(this, R.id.update_error_text);
        HealthProgressIndicator healthProgressIndicator = (HealthProgressIndicator) nsy.cMc_(this, R.id.download_ota_animation);
        this.i = healthProgressIndicator;
        healthProgressIndicator.setWaitingAnimationEnabled(false);
        this.i.setProgress(0);
        this.p = (HealthTextView) nsy.cMc_(this, R.id.text_percent);
        this.u = (HealthTextView) nsy.cMc_(this, R.id.text_per_sign);
        ImageView imageView = (ImageView) nsy.cMc_(this, R.id.image_check_logo);
        this.j = imageView;
        imageView.setVisibility(8);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.progress_circle_download);
        this.y = linearLayout;
        linearLayout.setVisibility(0);
        if (LanguageUtil.ac(this.f9247a)) {
            this.y.setLayoutDirection(0);
        }
        this.u.setText(String.valueOf(new DecimalFormatSymbols(Locale.getDefault()).getPercent()));
        this.af = (HealthTextView) nsy.cMc_(this, R.id.version_text);
        this.ag = (HealthTextView) nsy.cMc_(this, R.id.version_download);
        this.ai = (HealthTextView) nsy.cMc_(this, R.id.version_size_text);
        this.ae = (HealthTextView) nsy.cMc_(this, R.id.update_log_content);
        this.ab = (LinearLayout) nsy.cMc_(this, R.id.update_button_layout);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.update_button);
        this.z = healthButton;
        healthButton.setOnClickListener(this);
        ((CustomTitleBar) nsy.cMc_(this, R.id.update_title)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceOtaActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        g();
    }

    private void g() {
        HwVersionManager c2 = HwVersionManager.c(BaseApplication.getContext());
        this.af.setText(c2.j(this.m));
        DeviceInfo e2 = jkj.d().e(this.m);
        if (e2 == null) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "initView deviceInfo is null");
            this.ae.setText(R.string._2130842256_res_0x7f021290);
        } else {
            s();
            String e3 = kxz.e(BaseApplication.getContext(), c2.j(this.m), e2.getDeviceIdentify());
            if (!TextUtils.isEmpty(e3)) {
                this.ai.setVisibility(0);
            }
            String b = kxz.b(BaseApplication.getContext(), c2.j(this.m), e2.getDeviceIdentify());
            if (LanguageUtil.ac(this.f9247a)) {
                e3 = Constants.LRM_STR + e3;
            }
            this.ai.setText(this.f9247a.getString(R.string._2130844010_res_0x7f02196a, e3));
            if (TextUtils.isEmpty(b)) {
                this.ae.setText(R.string._2130842256_res_0x7f021290);
            } else {
                this.ae.setText(b);
            }
        }
        r();
        oab.cTB_(this.z, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        String format;
        DeviceInfo e2 = jkj.d().e(this.m);
        if (e2 == null) {
            LogUtil.h("DeviceOtaActivity", "setTransferTips deviceInfo is null");
            return;
        }
        String format2 = String.format(Locale.ENGLISH, this.f9247a.getString(R.string._2130844879_res_0x7f021ccf), e2.getDeviceName());
        if (nsn.ae(BaseApplication.getContext())) {
            format2 = String.format(Locale.ENGLISH, this.f9247a.getString(R.string._2130844880_res_0x7f021cd0), e2.getDeviceName());
        }
        if ("wifi".equals(this.ad)) {
            boolean o = Utils.o();
            if (o) {
                format = String.format(Locale.ENGLISH, this.f9247a.getString(R.string._2130846345_res_0x7f022289), e2.getDeviceName());
            } else {
                format = String.format(Locale.ENGLISH, this.f9247a.getString(R.string._2130846293_res_0x7f022255), e2.getDeviceName());
            }
            if (!nsn.ae(BaseApplication.getContext())) {
                format2 = format;
            } else if (o) {
                format2 = String.format(Locale.ENGLISH, this.f9247a.getString(R.string._2130846346_res_0x7f02228a), e2.getDeviceName());
            } else {
                format2 = String.format(Locale.ENGLISH, this.f9247a.getString(R.string._2130846294_res_0x7f022256), e2.getDeviceName());
            }
        }
        this.aa.setText(format2);
        LogUtil.a("DeviceOtaActivity", "setTransferTips end");
    }

    private void r() {
        this.ac.setVisibility(0);
        this.ab.setVisibility(8);
        this.ag.setText(R.string._2130842348_res_0x7f0212ec);
        d(0);
    }

    static class c extends Handler {
        WeakReference<DeviceOtaActivity> d;

        c(DeviceOtaActivity deviceOtaActivity) {
            this.d = new WeakReference<>(deviceOtaActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                ReleaseLogUtil.d("DEVMGR_DeviceOtaActivity", "UpgradeHandler message is null");
                return;
            }
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "UpgradeHandler message is,", Integer.valueOf(message.what));
            DeviceOtaActivity deviceOtaActivity = this.d.get();
            if (deviceOtaActivity != null) {
                switch (message.what) {
                    case 4:
                        deviceOtaActivity.d(message.arg1);
                        jkj.d().a(false);
                        break;
                    case 5:
                        if (deviceOtaActivity.q != 1) {
                            deviceOtaActivity.q();
                            jkj.d().a(true);
                            LogUtil.a("DeviceOtaActivity", "MSG_UPGRADE_SUCCESS");
                            break;
                        } else {
                            LogUtil.a("DeviceOtaActivity", "is already failed");
                            break;
                        }
                    case 6:
                    case 7:
                        LogUtil.a("DeviceOtaActivity", "MSG message.what:", Integer.valueOf(message.what), "message.arg1:", Integer.valueOf(message.arg1));
                        jkv.b().a();
                        deviceOtaActivity.a(cSR_(message, deviceOtaActivity));
                        break;
                    case 8:
                        deviceOtaActivity.c(R.string.IDS_device_ear_state_error);
                        break;
                    case 9:
                        deviceOtaActivity.s();
                        break;
                    default:
                        LogUtil.a("DeviceOtaActivity", "UpgradeHandler default");
                        break;
                }
                return;
            }
            ReleaseLogUtil.d("DEVMGR_DeviceOtaActivity", "UpgradeHandler activity is null");
        }

        private String cSR_(Message message, DeviceOtaActivity deviceOtaActivity) {
            DeviceInfo e = jkj.d().e(deviceOtaActivity.m);
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "handFailed msg is,", Integer.valueOf(message.arg1));
            switch (message.arg1) {
                case 1:
                    if (e == null) {
                        return String.format(Locale.ENGLISH, deviceOtaActivity.f9247a.getString(R.string._2130841489_res_0x7f020f91), deviceOtaActivity.f9247a.getString(R.string.IDS_scan_device));
                    }
                    return String.format(Locale.ENGLISH, deviceOtaActivity.f9247a.getString(R.string._2130841489_res_0x7f020f91), e.getDeviceName());
                case 104002:
                case 109002:
                    String e2 = UnitUtil.e(DeviceOtaActivity.c, 2, 0);
                    LogUtil.a("DeviceOtaActivity", "battery : ", e2);
                    if (e == null) {
                        return String.format(Locale.ENGLISH, deviceOtaActivity.f9247a.getString(R.string._2130841494_res_0x7f020f96), deviceOtaActivity.f9247a.getString(R.string.IDS_scan_device), e2);
                    }
                    return String.format(Locale.ENGLISH, deviceOtaActivity.f9247a.getString(R.string._2130841494_res_0x7f020f96), e.getDeviceName(), e2);
                case 104003:
                    return deviceOtaActivity.f9247a.getString(R.string._2130841493_res_0x7f020f95);
                case 109025:
                    String e3 = UnitUtil.e(20.0d, 2, 0);
                    LogUtil.a("DeviceOtaActivity", "earBattery : ", e3);
                    return String.format(Locale.ENGLISH, deviceOtaActivity.f9247a.getString(R.string.IDS_device_ear_low_battery), e3);
                default:
                    return cSS_(message, deviceOtaActivity, e);
            }
        }

        private String cSS_(Message message, DeviceOtaActivity deviceOtaActivity, DeviceInfo deviceInfo) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "handleFailedMessage msg is,", Integer.valueOf(message.arg1));
            sqo.as("handleFailedMessage msg is," + message.arg1);
            int i = message.arg1;
            if (i == 1002) {
                return deviceOtaActivity.f9247a.getString(R.string._2130841490_res_0x7f020f92);
            }
            if (i == 104007) {
                return deviceOtaActivity.f9247a.getString(R.string._2130841439_res_0x7f020f5f);
            }
            if (i == 109018) {
                return deviceOtaActivity.f9247a.getString(R.string._2130843761_res_0x7f021871);
            }
            if (deviceInfo == null) {
                return String.format(Locale.ENGLISH, deviceOtaActivity.f9247a.getString(R.string._2130846681_res_0x7f0223d9), deviceOtaActivity.f9247a.getString(R.string.IDS_scan_device));
            }
            return deviceOtaActivity.f9247a.getResources().getString(R.string._2130846681_res_0x7f0223d9, deviceInfo.getDeviceName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.v == i) {
            return;
        }
        this.v = i;
        if (i >= e) {
            this.ag.setText(R.string._2130843901_res_0x7f0218fd);
        } else {
            this.ag.setText(R.string._2130842348_res_0x7f0212ec);
        }
        this.p.setText(UnitUtil.e(i, 1, 0));
        this.i.setProgress(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.f9247a == null) {
            return;
        }
        m();
        c();
        c(str);
        this.q = 1;
        d(0);
        this.ac.setVisibility(8);
        this.ab.setVisibility(0);
        this.j.setVisibility(0);
        this.y.setVisibility(8);
        this.ac.setVisibility(0);
        this.ag.setText(R.string.IDS_device_ota_send_failed);
        this.z.getBackground().setAlpha(255);
        this.z.setClickable(true);
        this.z.setText(R.string._2130841467_res_0x7f020f7b);
        oab.cTB_(this.z, this);
    }

    private void c(String str) {
        CustomTextAlertDialog customTextAlertDialog = this.f;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R.string.IDS_device_ota_send_failed).e(str).cyU_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "showOtaErrorDialog click");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.f = a2;
            if (a2 == null || a2.isShowing()) {
                return;
            }
            this.f.setCancelable(false);
            this.f.show();
        }
    }

    private void x() {
        if (jkj.d().h(this.m)) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "has Transfer() device");
            a(this.f9247a.getString(R.string.IDS_main_device_ota_error_message));
        } else {
            t();
        }
    }

    private void t() {
        DeviceInfo e2 = jkj.d().e(this.m);
        if (e2 != null && e2.getDeviceConnectState() == 2) {
            if (h() == 6) {
                jkj.d().c(this.t);
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "startTransferForWear registerOTACallBack");
                this.q = 0;
                d();
                return;
            }
            if (h() == 4) {
                jkj.d().a(this.m, this.t);
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "startTransferForWear registerBackgroundCallBack");
                this.q = 0;
                d();
                return;
            }
            if (h() == 5 && !TextUtils.isEmpty(jkj.d().a())) {
                a(jkj.d().a());
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "startTransferForWear show transfer error");
                sqo.as("startTransferForWear show transfer error");
                return;
            }
            c(e2);
            return;
        }
        boolean a2 = SharedPreferenceManager.a(String.valueOf(1003), "flag_ota_update_success", false);
        if (e2 == null) {
            if (a2) {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "startTransferForWear OTA update succeed");
                finish();
                return;
            }
        } else if (a2 && e2.getDeviceConnectState() == 4) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "startTransferForWear OTA update succeed and device reboot");
            this.ag.setText(R.string._2130841488_res_0x7f020f90);
            finish();
            return;
        }
        a(this.f9247a.getString(R.string._2130841439_res_0x7f020f5f));
    }

    private void c(DeviceInfo deviceInfo) {
        if (jkk.d().e()) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "not isAllowUpgrade");
            a(jkk.d().e(4));
        } else if (cwi.c(deviceInfo, 112)) {
            e();
        } else {
            jkv.b().d(false);
            n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        DeviceInfo e2 = jkj.d().e(this.m);
        if (e2 == null || e2.getDeviceConnectState() != 2) {
            a(this.f9247a.getString(R.string._2130841439_res_0x7f020f5f));
            return;
        }
        c cVar = this.ah;
        if (cVar != null) {
            cVar.sendEmptyMessageDelayed(8, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        jgs.c().e(e2, new EarPhoneResponseCallback() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.7
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
            public void onResponse(int i, cuz cuzVar) {
                if (DeviceOtaActivity.this.ah != null) {
                    DeviceOtaActivity.this.ah.removeMessages(8);
                }
                if (cuzVar == null) {
                    DeviceOtaActivity.this.c(R.string.IDS_device_ear_state_error);
                    LogUtil.h("DeviceOtaActivity", "checkEarPhoneState onResponse earPhoneInfo is null");
                    return;
                }
                LogUtil.a("DeviceOtaActivity", "checkEarPhoneState() earPhoneInfo.getDeviceStatus() is ", Integer.valueOf(cuzVar.a()));
                if (cuzVar.a() != 3) {
                    DeviceOtaActivity.this.c(R.string.IDS_device_ear_state);
                } else {
                    jkv.b().d(false);
                    DeviceOtaActivity.this.n();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        LogUtil.a("DeviceOtaActivity", "showEarphoneDialog enter");
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.10
            @Override // java.lang.Runnable
            public void run() {
                if (DeviceOtaActivity.this.f9247a != null) {
                    if (DeviceOtaActivity.this.k != null) {
                        View inflate = DeviceOtaActivity.this.k.inflate(R.layout.activity_device_ota_transmission_dialog, (ViewGroup) null);
                        if (inflate == null) {
                            LogUtil.h("DeviceOtaActivity", "showEarphoneDialog autoItemView is null.");
                            return;
                        }
                        ((HealthTextView) inflate.findViewById(R.id.band_transmission_title)).setText(DeviceOtaActivity.this.f9247a.getString(i));
                        CustomAlertDialog c2 = new CustomAlertDialog.Builder(DeviceOtaActivity.this.f9247a).cyp_(inflate).cyn_(R.string.IDS_device_ignore, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.10.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                LogUtil.a("DeviceOtaActivity", "showEarphoneDialog setNegativeButton");
                                jkv.b().d(true);
                                DeviceOtaActivity.this.n();
                                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
                            }
                        }).cyo_(R.string._2130841467_res_0x7f020f7b, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.10.4
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                LogUtil.a("DeviceOtaActivity", "showEarphoneDialog setPositiveButton");
                                DeviceOtaActivity.this.e();
                                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
                            }
                        }).c();
                        if (c2 == null || c2.isShowing()) {
                            return;
                        }
                        LogUtil.a("DeviceOtaActivity", "showEarphoneDialog show");
                        c2.setCancelable(false);
                        c2.show();
                        return;
                    }
                    LogUtil.h("DeviceOtaActivity", "showEarphoneDialog mInflater == null");
                    return;
                }
                LogUtil.h("DeviceOtaActivity", "showEarphoneDialog mContext == null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        String b = oaf.b(BaseApplication.getContext()).b(this.m);
        if (TextUtils.isEmpty(b)) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "band new version is not exist");
            finish();
            return;
        }
        if (this.l) {
            jkj.d().c(this.m, b, "is_package_already_exists", this.t);
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "requestTransferForWear mIsPackageAlreadyExists");
        } else {
            String c2 = oaf.b(BaseApplication.getContext()).c(this.m);
            if (this.h != null && !HwVersionManager.c(this.f9247a).c(this.m, this.h.getSecurityDeviceId())) {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "band new package is not exist");
                finish();
                return;
            } else {
                if (TextUtils.equals(b, HwVersionManager.c(this.f9247a).b(this.m))) {
                    ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "requestTransferForWear current Device is latest");
                    jkj.d().d(this.m, 0);
                    jkn.a().e();
                    HwVersionManager.c(this.f9247a).m(this.m);
                    finish();
                    return;
                }
                c(b, c2);
            }
        }
        this.x = System.currentTimeMillis();
        this.q = 0;
    }

    private void c(final String str, final String str2) {
        DeviceCapability e2 = cvs.e(this.m);
        if (e2 != null && e2.getIsSupportDeviceRequestCheck()) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "manualStartTransfer queryDeviceTransmitMode");
            if (this.ah != null) {
                Message obtain = Message.obtain();
                obtain.arg1 = 1;
                obtain.what = 7;
                this.ah.sendMessageDelayed(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
            }
            jkv.b().b(this.m, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "queryDeviceTransmitMode() errorCode = ", Integer.valueOf(i));
                    if (DeviceOtaActivity.this.ah != null) {
                        DeviceOtaActivity.this.ah.removeMessages(7);
                    }
                    jkj.d().c(DeviceOtaActivity.this.m, str, str2, DeviceOtaActivity.this.t);
                    DeviceOtaActivity.this.d();
                }
            });
        } else {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "manualStartTransfer");
            jkj.d().c(this.m, str, str2, this.t);
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!cwi.c(this.h, 147)) {
            ReleaseLogUtil.d("DEVMGR_DeviceOtaActivity", "checkWifiState device not supportAutoChangeWifiP2P");
            return;
        }
        if (!o()) {
            ReleaseLogUtil.d("DEVMGR_DeviceOtaActivity", "isShowDialog is false");
            return;
        }
        if (nyi.d(this.h)) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "checkWlanState isSupportWlanTransmit");
            f();
        } else {
            if (nyi.a(this.f9247a)) {
                return;
            }
            p();
        }
    }

    private void m() {
        SharedPreferenceManager.b(String.valueOf(1003), "show_open_wlan_dialog_count", 0);
    }

    private boolean o() {
        int a2 = SharedPreferenceManager.a(String.valueOf(1003), "show_open_wlan_dialog_count", 0);
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "isShowDialog showOpenWlanDialogCount:", Integer.valueOf(a2));
        return a2 < 2;
    }

    private void f() {
        jqi.a().getSwitchSetting("auto_open_wlan_status", this.h.getDeviceIdentify(), new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "getWlanTransmitSwitch errorCode:", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof String)) {
                    LogUtil.h("DeviceOtaActivity", "getWlanTransmitSwitch fail");
                    return;
                }
                String valueOf = String.valueOf(obj);
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "getWlanTransmitSwitch info:", valueOf);
                if (!"1".equals(valueOf)) {
                    if (nyi.a(DeviceOtaActivity.this.f9247a)) {
                        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "getWlanTransmitSwitch WifiEnabled is true");
                        return;
                    } else {
                        DeviceOtaActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.6.2
                            @Override // java.lang.Runnable
                            public void run() {
                                DeviceOtaActivity.this.p();
                            }
                        });
                        return;
                    }
                }
                LogUtil.h("DeviceOtaActivity", "getWlanTransmitSwitch is AUTO_OPEN_WLAN_OPEN");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        String string;
        String string2;
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "showOpenWlanDialog enter");
        if (this.f9247a == null) {
            LogUtil.h("DeviceOtaActivity", "showOpenWlanDialog mContext == null");
            return;
        }
        if (Utils.o()) {
            string = this.f9247a.getResources().getString(R.string._2130846352_res_0x7f022290);
            string2 = this.f9247a.getResources().getString(R.string._2130846353_res_0x7f022291);
        } else {
            string = this.f9247a.getResources().getString(R.string._2130846291_res_0x7f022253);
            string2 = this.f9247a.getResources().getString(R.string._2130846292_res_0x7f022254);
        }
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(this.f9247a).a(string).c(string2).cyn_(R.string._2130843956_res_0x7f021934, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "showOpenWlanDialog click later");
                SharedPreferenceManager.b(String.valueOf(1003), "show_open_wlan_dialog_count", 2);
                DeviceOtaActivity.this.c();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130844420_res_0x7f021b04, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.DeviceOtaActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "showOpenWlanDialog click ok");
                DeviceOtaActivity.this.c();
                try {
                    DeviceOtaActivity.this.startActivityForResult(new Intent("android.settings.WIFI_SETTINGS"), 100);
                } catch (ActivityNotFoundException e2) {
                    LogUtil.b("DeviceOtaActivity", "showOpenWlanDialog ActivityNotFoundException:", ExceptionUtils.d(e2));
                    sqo.as("showOpenWlanDialog ActivityNotFoundException");
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).e(false).c();
        this.s = c2;
        c2.show();
        a();
    }

    private void a() {
        int a2 = SharedPreferenceManager.a(String.valueOf(1003), "show_open_wlan_dialog_count", 0) + 1;
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "addShowDialogCount count:", Integer.valueOf(a2));
        SharedPreferenceManager.b(String.valueOf(1003), "show_open_wlan_dialog_count", a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        CustomAlertDialog customAlertDialog = this.s;
        if (customAlertDialog != null) {
            customAlertDialog.cancel();
            this.s.dismiss();
            this.s = null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.q = 2;
        this.ac.setVisibility(8);
        this.ab.setVisibility(0);
        d(100);
        this.z.setClickable(true);
        this.z.setText(R.string._2130841772_res_0x7f0210ac);
        this.ag.setText(R.string._2130841488_res_0x7f020f90);
        c cVar = this.ah;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
            this.ah = null;
        }
        m();
        c();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.r != null && h() == 4) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "onBackPressed show notification");
            jkn.a().c(this.m);
            jkn.a().c(1, jkk.d().f(this.m));
        }
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.r != null) {
            jkj.d().i();
            jkj.d().g();
        }
        this.t = null;
        c cVar = this.ah;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
            this.ah = null;
        }
        y();
        this.f9247a = null;
        LogUtil.a("DeviceOtaActivity", "onDestroy()");
        CommonUtil.a(this.f9247a);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("DeviceOtaActivity", "onclick ");
        if (view.getId() == R.id.update_button) {
            if (this.q == 1) {
                ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "mIsConnected", Boolean.valueOf(this.n));
                if (!this.n) {
                    a(this.f9247a.getString(R.string.IDS_device_switch_device_connect_fail));
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                j();
            } else {
                finish();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j() {
        if (this.r.i(this.r.c(this.m))) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "re startTransferOtaFile");
            i();
            this.o = true;
            this.w = 0;
            jkj.d().d(this.m, 0);
            x();
            return;
        }
        LogUtil.a("DeviceOtaActivity", "ota file is not exist");
        finish();
    }

    private void k() {
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "enter registerConnectStateBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastReceiver broadcastReceiver = this.d;
        this.b = broadcastReceiver;
        BroadcastManagerUtil.bFC_(this.f9247a, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.b == null) {
            return;
        }
        try {
            unregisterReceiver(this.d);
            this.b = null;
        } catch (IllegalArgumentException e2) {
            LogUtil.a("DeviceOtaActivity", e2.getMessage());
        }
    }

    private void b(LinkedHashMap<String, String> linkedHashMap, int i) {
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "enter setEventErrorMsg errorMsg = ", Integer.valueOf(i));
        if (linkedHashMap == null) {
            LogUtil.a("DeviceOtaActivity", "setEventErrorMsg map is null.");
            return;
        }
        if (i == 1) {
            linkedHashMap.put("errorID", "106");
            return;
        }
        if (i == 1002) {
            linkedHashMap.put("errorID", "107");
        } else if (i == 104007) {
            linkedHashMap.put("errorID", "104");
        } else {
            linkedHashMap.put("errorID", "105");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "enter sentOtaTransferEvent. errorMsg=", i2 + "state is,", Integer.valueOf(i));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        DeviceInfo e2 = jpt.e(this.m, "DeviceOtaActivity");
        if (e2 == null) {
            ReleaseLogUtil.e("DEVMGR_DeviceOtaActivity", "enter sentOtaTransferEvent device is null.");
            return;
        }
        int productType = e2.getProductType();
        if (productType != -1) {
            linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(productType));
            linkedHashMap.put("device_classfication", String.valueOf(cwc.b(productType)));
        }
        if (i == 6) {
            linkedHashMap.put("operationID", "8");
            b(linkedHashMap, i2);
        } else {
            linkedHashMap.put("operationID", "7");
            long j = this.x;
            if (j > 0) {
                long j2 = this.g;
                if (j2 > 0) {
                    long j3 = j2 - j;
                    if (j3 > 0) {
                        linkedHashMap.put("OTATime", String.valueOf(j3));
                        jkj.d().c(this.m, linkedHashMap, this.g - this.x);
                    }
                }
            }
        }
        linkedHashMap.put("deviceNewVersion", this.r.b(this.m));
        linkedHashMap.put("versionID", HwVersionManager.c(this.f9247a).i(this.m));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEVICE_OTA_UPDATE_80020004.value(), linkedHashMap);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
