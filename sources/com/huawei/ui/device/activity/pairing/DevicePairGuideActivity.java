package com.huawei.ui.device.activity.pairing;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;
import com.huawei.callback.DeviceStatusCallback;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.datatype.GoogleDeviceCache;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.cvc;
import defpackage.cvt;
import defpackage.dwo;
import defpackage.ixx;
import defpackage.iyl;
import defpackage.jad;
import defpackage.jdx;
import defpackage.jeg;
import defpackage.jfq;
import defpackage.jfr;
import defpackage.jfu;
import defpackage.nrh;
import defpackage.nrj;
import defpackage.nsn;
import defpackage.ntt;
import defpackage.nxe;
import defpackage.nxf;
import defpackage.oac;
import defpackage.oae;
import defpackage.oaf;
import defpackage.oam;
import defpackage.oau;
import defpackage.obb;
import defpackage.obi;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class DevicePairGuideActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: do, reason: not valid java name */
    private static final String[] f120do = {"android.permission.READ_PHONE_STATE"};
    public static volatile boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    public HealthCheckBox f9180a;
    public CustomTitleBar aa;
    public HealthTextView ab;
    public LinearLayout ac;
    public String ae;
    public oae af;
    public cvc ah;
    public RelativeLayout ai;
    public String aj;
    public DeviceSettingsInteractors ak;
    public HealthScrollView al;
    public RelativeLayout am;
    public int an;
    public HealthButton ao;
    public HealthProgressBar ap;
    public HealthButton aq;
    public HealthTextView at;
    public HealthButton au;
    public HealthColumnLinearLayout av;
    public HealthColumnLinearLayout aw;
    public nxe ay;
    public boolean az;
    public Context b;
    public LinearLayout ba;
    public boolean bb;
    protected boolean bc;
    public boolean bf;
    public boolean bg;
    public boolean bh;
    public String bj;
    public String bl;
    public LinearLayout bn;
    public HealthTextView bp;
    public ImageView bq;
    public ImageView bs;
    public HealthTextView bt;
    public ImageView bu;
    public LinearLayout bv;
    public HealthTextView bw;
    public LinearLayout bx;
    public LinearLayout by;
    public HealthTextView bz;
    public AnimationDrawable c;
    public LinearLayout ca;
    public ImageView cb;
    public HealthTextView cc;
    public HealthTextView cd;
    public HealthTextView ce;
    public HealthTextView cf;
    public HealthTextView cg;
    public HealthTextView ch;
    public RelativeLayout ci;
    public RelativeLayout cj;
    public HealthTextView ck;
    public RelativeLayout cl;
    public RelativeLayout cm;
    public LinearLayout cn;
    public HealthTextView co;
    public LinearLayout cp;
    public RelativeLayout cq;
    public RelativeLayout cr;
    public RelativeLayout cs;
    public ImageView ct;
    public HealthTextView cu;
    public ImageView cv;
    public HealthTextView cw;
    public HealthTextView cx;
    public HealthTextView cy;
    public boolean d;
    protected String da;
    public LinearLayout db;
    public HealthTextView dc;
    public HealthTextView dd;
    public int de;
    public HealthButton df;
    public HealthTextView dh;
    public String dj;
    public HealthButton dk;
    public LinearLayout dl;
    private CustomPermissionAction dm;
    public HealthCheckBox dn;
    public HealthTextView dp;
    private e dq;
    private Context dy;
    private boolean ea;
    private NotificationPushInteractor ec;
    private boolean ed;
    private h eg;
    private int ej;
    public LinearLayout f;
    public HealthTextView g;
    public LinearLayout h;
    public HealthTextView i;
    public HealthCheckBox j;
    public ImageView k;
    public RelativeLayout l;
    public ImageView m;
    public RelativeLayout n;
    public AnimationDrawable p;
    public AnimationDrawable q;
    public AnimationDrawable r;
    public HealthTextView s;
    public LinearLayout t;
    public HealthButton u;
    public HealthButton v;
    public HealthButton w;
    public HealthColumnLinearLayout x;
    public ImageView y;
    public CompatibilityInteractor z;
    public String di = "";
    public boolean be = false;
    public boolean bk = false;
    public Handler ax = new g(this);
    public CustomAlertDialog ad = null;
    public DeviceCapability ag = null;
    public boolean bd = false;
    public DeviceCapability as = null;
    public boolean bi = false;
    public final int[] cz = {R.string.IDS_device_paring_tip_des_info_21};
    public final int[] o = {R.string._2130842747_res_0x7f02147b};
    public boolean bm = false;
    public List<String> dg = new ArrayList(16);
    public BroadcastReceiver bo = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "NetBroadcastReceiver intent is null");
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (!CommonUtil.aa(DevicePairGuideActivity.this.dy)) {
                    LogUtil.h("DEVMGR_DevicePairGuideActivity", "net work is error");
                } else {
                    DevicePairGuideActivity.this.ay.a(DevicePairGuideActivity.this);
                    DevicePairGuideActivity.this.ac();
                }
            }
        }
    };
    public CompoundButton.OnCheckedChangeListener br = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.7
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "mWlanAutoDownloadCheckBox ,isChecked is ", Boolean.valueOf(z));
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private boolean dv = false;
    private IAddDeviceStateAIDLCallback.Stub dr = new d(this.ax);
    private boolean eb = false;
    private boolean dz = false;
    private final int[] dt = {R.string.IDS_device_pair_guide_b3_tip1_ex, R.string.IDS_device_pair_guide_b3_tip2_ex, R.string.IDS_device_pair_guide_b3_tip3};
    private final int[] du = {R.string.IDS_device_paring_type_le_des_info_21};
    private final int[] dw = {R.string.IDS_select_device_connect_grus_change_tip_1, R.string.IDS_select_device_connect_grus_change_tip_2, R.string.IDS_device_pair_guide_b3_tip3};
    private DialogInterface.OnKeyListener ee = new DialogInterface.OnKeyListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.9
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
            if (i2 != 4 || keyEvent.getAction() != 0 || DevicePairGuideActivity.this.ad == null || !DevicePairGuideActivity.this.ad.isShowing()) {
                return false;
            }
            DevicePairGuideActivity.this.ad.dismiss();
            DevicePairGuideActivity.this.ad = null;
            DevicePairGuideActivity.this.finish();
            return false;
        }
    };
    private final BroadcastReceiver ds = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (DevicePairGuideActivity.this.ay != null && DevicePairGuideActivity.this.ay.j()) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver isShowMidWare is false");
                return;
            }
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver : intent is ", intent.getAction());
            if (context != null) {
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    if (!DevicePairGuideActivity.this.bk) {
                        DevicePairGuideActivity.this.cRz_(intent);
                        return;
                    } else {
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "The watch is being restored to factory.");
                        return;
                    }
                }
                if ("com.huawei.bone.action.REQUEST_BIND_DEVICE".equals(intent.getAction()) && !TextUtils.isEmpty(intent.getStringExtra("connect_status"))) {
                    DevicePairGuideActivity.this.cRy_(intent);
                    return;
                }
                if ("com.huawei.health.action.AddDeviceStatusChanged".equals(intent.getAction())) {
                    try {
                        DevicePairGuideActivity.this.dr.onAddDeviceState(intent.getIntExtra("status", 0));
                        return;
                    } catch (RemoteException e2) {
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "mAddDeviceStateAidlCallback exception：", e2.getMessage());
                        return;
                    }
                }
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver other state");
            }
        }
    };
    protected final DeviceStatusCallback ar = new DeviceStatusCallback() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.10
        @Override // com.huawei.callback.DeviceStatusCallback
        public void onDeviceConnectedChanged(DeviceInfo deviceInfo, int i2, DeviceStatusParam deviceStatusParam) {
            if (deviceInfo == null || deviceStatusParam == null) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "onDeviceConnectedChanged device == null");
                return;
            }
            if (DevicePairGuideActivity.this.bk) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "The watch is being restored to factory.");
            } else if (!deviceStatusParam.isDeviceConnectDirectly()) {
                DevicePairGuideActivity.this.e(deviceInfo, deviceStatusParam.getErrorCode());
            } else {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "dealStateChange isDeviceConnectDirectly is true.");
                DevicePairGuideActivity.this.m();
            }
        }
    };
    private final BroadcastReceiver dx = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"broadcast_receiver_user_setting".equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("error_code", 0);
            if (DevicePairGuideActivity.this.ay == null) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "mHelper is null");
                return;
            }
            if (intExtra == 100000) {
                DevicePairGuideActivity.this.ay.r();
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "on Receive showPair Success Result");
                return;
            }
            sqo.ak("oobe error");
            DevicePairGuideActivity.this.ay.c(0, intExtra);
            if (intExtra == -1) {
                DevicePairGuideActivity.this.ea = true;
            }
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "on Receive showPair failure Result", Integer.valueOf(intExtra));
            if (TextUtils.isEmpty(DevicePairGuideActivity.this.di)) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "mTempMac is empty");
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(DevicePairGuideActivity.this.di);
            DevicePairGuideActivity.this.af.e(arrayList, true);
        }
    };

    static class a implements IBaseResponseCallback {
        private WeakReference<DevicePairGuideActivity> c;
        private Map<String, Object> d;

        public a(DevicePairGuideActivity devicePairGuideActivity, Map<String, Object> map) {
            this.c = new WeakReference<>(devicePairGuideActivity);
            this.d = map;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            DevicePairGuideActivity devicePairGuideActivity;
            if (i != 0 || obj == null || (devicePairGuideActivity = this.c.get()) == null || devicePairGuideActivity.isFinishing()) {
                return;
            }
            oau.b(this.d, devicePairGuideActivity.ej);
            if (devicePairGuideActivity.ay != null) {
                oau.d(devicePairGuideActivity.bb);
            }
        }
    }

    static class g extends Handler {
        WeakReference<DevicePairGuideActivity> c;

        g(DevicePairGuideActivity devicePairGuideActivity) {
            this.c = new WeakReference<>(devicePairGuideActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            DevicePairGuideActivity devicePairGuideActivity = this.c.get();
            if (devicePairGuideActivity == null || message == null) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "handleMessage activity or msg is null.");
                return;
            }
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter handleMessage(): ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                devicePairGuideActivity.ay.s();
                return;
            }
            if (i == 1) {
                cRB_(message, devicePairGuideActivity);
                return;
            }
            if (i == 2) {
                removeMessages(12);
                if (devicePairGuideActivity.ay != null) {
                    devicePairGuideActivity.ay.r();
                    return;
                }
                return;
            }
            if (i == 3) {
                cRA_(message, devicePairGuideActivity);
                return;
            }
            if (i == 4) {
                devicePairGuideActivity.b(false);
                return;
            }
            if (i == 5) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "MSG_ENABLE_RIGHT_BTN");
                devicePairGuideActivity.c(true);
                return;
            }
            if (i == 7) {
                b(devicePairGuideActivity);
                return;
            }
            if (i == 8) {
                devicePairGuideActivity.e(message.arg1);
                return;
            }
            if (i == 14) {
                ntt.cNK_(devicePairGuideActivity.dy, devicePairGuideActivity);
            } else if (i == 15) {
                a(devicePairGuideActivity);
            } else {
                cRC_(message);
            }
        }

        private void a(DevicePairGuideActivity devicePairGuideActivity) {
            if (devicePairGuideActivity.ay != null) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "MemberAndViewHelper: Entering");
                devicePairGuideActivity.ay.e(devicePairGuideActivity);
            }
        }

        private void b(DevicePairGuideActivity devicePairGuideActivity) {
            removeMessages(12);
            if (devicePairGuideActivity.ay != null) {
                devicePairGuideActivity.ay.c(1, 1001002);
            }
        }

        private void cRA_(Message message, DevicePairGuideActivity devicePairGuideActivity) {
            removeMessages(12);
            int i = message.arg1;
            if (devicePairGuideActivity.ay != null) {
                devicePairGuideActivity.ay.c(0, i);
            }
        }

        private void cRB_(Message message, DevicePairGuideActivity devicePairGuideActivity) {
            devicePairGuideActivity.y();
            if (devicePairGuideActivity.ay == null) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "helper is null.");
            } else {
                devicePairGuideActivity.ay.cRW_(message);
            }
        }

        private void cRC_(Message message) {
            DevicePairGuideActivity devicePairGuideActivity = this.c.get();
            switch (message.what) {
                case 9:
                    devicePairGuideActivity.z.b("getDeviceList");
                    devicePairGuideActivity.z.b("unbindDevicesByTypes");
                    devicePairGuideActivity.z.b("getWearData");
                    devicePairGuideActivity.d(false);
                    break;
                case 10:
                    devicePairGuideActivity.b(true);
                    break;
                case 11:
                    devicePairGuideActivity.b();
                    break;
                case 12:
                    ReleaseLogUtil.d("DEVMGR_DevicePairGuideActivity", "pair device time out");
                    sqo.m("pair device time out");
                    if (devicePairGuideActivity.ay != null) {
                        devicePairGuideActivity.ay.c(0, 1000000);
                        break;
                    }
                    break;
                case 13:
                    if (devicePairGuideActivity.ay != null && (message.obj instanceof Boolean)) {
                        boolean booleanValue = ((Boolean) message.obj).booleanValue();
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "MemberAndViewHelper.MSG_CONNECT_SCAN_BLUETOOTH isOpenTestButton:", Boolean.valueOf(booleanValue));
                        devicePairGuideActivity.ay.c(booleanValue);
                        break;
                    }
                    break;
                default:
                    LogUtil.h("DEVMGR_DevicePairGuideActivity", "no support message: ", Integer.valueOf(message.what));
                    break;
            }
        }
    }

    private void t() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter registerPairBroadcast");
        try {
            BroadcastManagerUtil.bFC_(this, this.dx, new IntentFilter("broadcast_receiver_user_setting"), LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "registerPairBroadcast IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cRy_(Intent intent) {
        ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "enter dealInitiatedConnectionState");
        if (this.eb) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "dealInitiatedConnectionState pair success done");
            return;
        }
        String stringExtra = intent.getStringExtra("deviceName");
        if (TextUtils.isEmpty(this.dj) || !this.dj.equals(stringExtra)) {
            LogUtil.h("DEVMGR_DevicePairGuideActivity", "dealInitiatedConnectionState mSelectDeviceName:", this.dj);
            return;
        }
        if (this.ab == null || TextUtils.isEmpty(stringExtra)) {
            return;
        }
        HealthColumnLinearLayout healthColumnLinearLayout = this.x;
        if (healthColumnLinearLayout != null) {
            healthColumnLinearLayout.setVisibility(0);
        }
        LinearLayout linearLayout = this.ac;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        try {
            this.ab.setText(String.format(Locale.ENGLISH, this.dy.getString(R.string.IDS_blite_guide_paire_current_device), stringExtra));
            this.ab.setVisibility(0);
        } catch (IllegalFormatConversionException unused) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "string format is exception");
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "dealInitiatedConnectionState deviceName:", stringExtra);
    }

    private boolean cRx_(Parcelable parcelable) {
        if (parcelable == null) {
            return false;
        }
        if (!(parcelable instanceof DeviceInfo)) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "! parcelableExtra instanceof DeviceInfo ");
            return false;
        }
        if (!this.eb) {
            return true;
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "pair success done ");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cRz_(Intent intent) {
        if (intent.getBooleanExtra("isDeviceConnectDirectly", false)) {
            LogUtil.h("DEVMGR_DevicePairGuideActivity", "dealStateChange isDeviceConnectDirectly is true.");
            m();
            return;
        }
        try {
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            if (cRx_(parcelableExtra)) {
                e((DeviceInfo) parcelableExtra, intent.getIntExtra("connect_error_code", 0));
            }
        } catch (ClassCastException unused) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "dealStateChange ClassCastException");
        } catch (Exception unused2) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "dealStateChange Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo, int i2) {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "deviceInfo.getDeviceFactoryReset():", Integer.valueOf(deviceInfo.getDeviceFactoryReset()));
        if (deviceInfo.getProductType() != -1) {
            if (10 != deviceInfo.getProductType() && (TextUtils.isEmpty(this.ae) || !this.ae.equals(deviceInfo.getDeviceIdentify()))) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "This device does not have the correspond capability");
                return;
            } else if (!CommonUtil.bv() && !CommonUtil.bu() && this.an != deviceInfo.getProductType()) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "mDeviceProductType is ", Integer.valueOf(this.an), "getProductType() is : ", Integer.valueOf(deviceInfo.getProductType()), "getDeviceName is ", deviceInfo.getDeviceName());
                Context context = this.b;
                nrh.c(context, context.getResources().getString(R.string.IDS_device_type_error));
            }
        }
        if (this.bb) {
            this.di = this.ae;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "mDeviceProductType is ", Integer.valueOf(this.an), "getProductType() is : ", Integer.valueOf(deviceInfo.getProductType()));
        ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "dealStateChange deviceName :", CommonUtil.l(deviceInfo.getDeviceName()), "state : ", Integer.valueOf(deviceConnectState), " errorCode:" + i2);
        if (this.dq != null) {
            ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "dealStateChange ConnectTimeoutHandler connectState:", Integer.valueOf(deviceConnectState));
            if (deviceConnectState != 1) {
                return;
            }
            this.dq.b();
            this.dq = null;
        }
        c(deviceConnectState, i2, deviceInfo);
    }

    private void c(int i2, int i3, DeviceInfo deviceInfo) {
        nxe nxeVar = this.ay;
        if (nxeVar == null || this.ax == null) {
            LogUtil.h("DEVMGR_DevicePairGuideActivity", "parseConnectState mHelper or mHandler is null");
            return;
        }
        switch (i2) {
            case 1:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                nxeVar.a(deviceInfo);
                break;
            case 2:
                c(deviceInfo);
                break;
            case 3:
                a(deviceInfo, i3);
                break;
            case 4:
                e(deviceInfo, i3, i2);
                break;
            case 5:
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver : state is ", Integer.valueOf(i2));
                this.ax.sendEmptyMessage(5);
                this.ax.sendEmptyMessage(7);
                this.di = "";
                break;
            case 6:
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver : DEVICE_WORK_MODE_CONFLICT is ", Integer.valueOf(i2));
                Message obtainMessage = this.ax.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.arg1 = 6;
                this.ax.sendMessage(obtainMessage);
                this.di = "";
                break;
            case 7:
            case 8:
            default:
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "unknown connectState : ", Integer.valueOf(i2));
                break;
            case 12:
                e(deviceInfo);
                break;
        }
    }

    private void e(DeviceInfo deviceInfo) {
        this.ay.c(deviceInfo);
        this.ay.c(this);
    }

    private void c(DeviceInfo deviceInfo) {
        if (!CompileParameterUtil.a("IS_SUPPORT_NEW_ADD_MODE", false) && this.an != deviceInfo.getProductType() && !CommonUtil.bv()) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "device connect state not match wanted device");
            Context context = this.b;
            nrh.c(context, context.getResources().getString(R.string.IDS_device_type_error));
        }
        if (this.eb) {
            LogUtil.h("DEVMGR_DevicePairGuideActivity", "pair success");
            return;
        }
        this.eb = true;
        this.di = deviceInfo.getDeviceIdentify();
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "DeviceConnectState : DEVICE_CONNECTED");
        Message obtainMessage = this.ax.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = deviceInfo;
        this.ax.sendMessage(obtainMessage);
        this.ax.sendEmptyMessage(5);
    }

    private void a(DeviceInfo deviceInfo, int i2) {
        if (oae.d(this.an) != deviceInfo.getDeviceBluetoothType() && deviceInfo.getDeviceBluetoothType() != -1) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "receive connect disconnect: device protocal is not equal");
            return;
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "DEVICE_DISCONNECTED ,mac is ", iyl.d().e(deviceInfo.getDeviceIdentify()), ",mTempMac:", iyl.d().e(this.di));
        this.ax.sendEmptyMessage(5);
        if (deviceInfo.getProductType() == 10 && this.an == 10) {
            Message obtainMessage = this.ax.obtainMessage();
            obtainMessage.what = 3;
            obtainMessage.arg1 = i2;
            this.ax.sendMessage(obtainMessage);
            return;
        }
        if (this.ea && this.di.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "oobe time out do not fresh msg");
            return;
        }
        if (this.di.equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && !this.dv) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "discovery and scan msg");
            Message obtainMessage2 = this.ax.obtainMessage();
            obtainMessage2.what = 3;
            obtainMessage2.arg1 = i2;
            this.ax.sendMessage(obtainMessage2);
            this.di = "";
            return;
        }
        if (this.dv) {
            this.dv = false;
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "state is DeviceConnectState.DEVICE_DISCONNECTED");
    }

    private void e(DeviceInfo deviceInfo, int i2, int i3) {
        if (oae.d(this.an) != deviceInfo.getDeviceBluetoothType() && deviceInfo.getDeviceBluetoothType() != -1) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "receive connect failed: device protocal is not equal");
            return;
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver : state is ", Integer.valueOf(i3), ",mTempMac:", iyl.d().e(this.di));
        if (!TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) && !deviceInfo.getDeviceIdentify().equals(this.di)) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "not the current device state, ignore");
        } else {
            b(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        this.ax.sendEmptyMessage(5);
        Message obtainMessage = this.ax.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.arg1 = i2;
        this.ax.sendMessage(obtainMessage);
        this.di = "";
    }

    static class h extends BaseHandler<DevicePairGuideActivity> {
        h(Looper looper, DevicePairGuideActivity devicePairGuideActivity) {
            super(looper, devicePairGuideActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cRD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(DevicePairGuideActivity devicePairGuideActivity, Message message) {
            int i = message.what;
            if (i == 100) {
                ((IBaseResponseCallback) message.obj).d(-1, "getbinddevice from timmeout");
            } else {
                if (i == 101) {
                    if (message.obj instanceof IBaseResponseCallback) {
                        ((IBaseResponseCallback) message.obj).d(0, true);
                        return;
                    }
                    return;
                }
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "no support msg : ", Integer.valueOf(message.what));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onCreate():");
        super.onCreate(bundle);
        nxe nxeVar = new nxe();
        this.ay = nxeVar;
        nxeVar.d(this);
        this.dy = BaseApplication.getContext();
        this.b = this;
        this.eg = new h(Looper.getMainLooper(), this);
        this.af = oae.c(BaseApplication.getContext());
        p();
        jfr.d("DEVMGR_DevicePairGuideActivity", this.ar);
        t();
        s();
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "onCreate mDeviceProductType is ", Integer.valueOf(this.an), ", mSaveDeviceProductType is ", Integer.valueOf(this.de), ", mDeviceProductName is ", this.aj, ", mIsPairSuccessFlag is ", Boolean.valueOf(this.bh), ", mName is ", this.bl, ", mPin is ", this.da, ", mIsCloudDevice ", Boolean.valueOf(this.bc), ", mIsFromNearDiscovery is", Boolean.valueOf(this.az));
        setContentView(R.layout.activity_device_pairing_guide_black);
        if (this.bb) {
            this.dj = this.bl;
        }
        this.ec = new NotificationPushInteractor(this.dy);
        this.ay.f();
        this.ay.g();
        this.ay.i();
        BaseActivity.cancelLayoutById(this.cr, this.t);
    }

    private void s() {
        if (getIntent() != null) {
            int intExtra = getIntent().getIntExtra("pairGuideProductType", -1);
            this.an = intExtra;
            this.de = intExtra;
            this.aj = getIntent().getStringExtra("pairGuideProductName");
            this.bh = getIntent().getBooleanExtra("pairGuideW1Success", false);
            this.bb = getIntent().getBooleanExtra("pairGuideFromScanList", false);
            this.az = getIntent().getBooleanExtra("pairGuideFromNearDiscovery", false);
            this.bf = getIntent().getBooleanExtra("IS_PROC", false);
            this.bl = getIntent().getStringExtra("pairGuideSelectName");
            this.da = getIntent().getStringExtra("pairGuideProductPin");
            this.ae = getIntent().getStringExtra("pairGuideSelectAddress");
            g();
            this.bg = getIntent().getBooleanExtra("DOWNLOAD_RESOURCE", false);
            this.bc = getIntent().getBooleanExtra("cloudDevicePair", false);
            this.bj = getIntent().getStringExtra("kind_id");
            try {
                this.dg = getIntent().getStringArrayListExtra("uuid_list");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("DEVMGR_DevicePairGuideActivity", "ArrayIndexOutOfBoundsException get uuid list");
            }
            this.ed = getIntent().getIntExtra("isHeartRateDevice", 0) == 1;
            int intExtra2 = getIntent().getIntExtra("pairGuideDeviceMode", 0);
            this.ej = intExtra2;
            oau.c(intExtra2, this.bl);
            return;
        }
        LogUtil.h("DEVMGR_DevicePairGuideActivity", "initListView() intent is null");
    }

    public void c() {
        if (jfu.m(this.an)) {
            this.ah = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(this.an));
        }
    }

    private void g() {
        if (this.bb) {
            List<DeviceInfo> c2 = oae.c(BaseApplication.getContext()).c();
            if (c2 == null || c2.size() == 0) {
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "dealIsConnectedAgainPair connectedDeviceList is null or size is 0");
                return;
            }
            for (DeviceInfo deviceInfo : c2) {
                LogUtil.c("DEVMGR_DevicePairGuideActivity", "dealIsConnectedAgainPair connectState: ", Integer.valueOf(deviceInfo.getDeviceConnectState()), " deviceInfo.getDeviceIdentify(): ", CommonUtil.l(deviceInfo.getDeviceIdentify()), " mDeviceAddress", CommonUtil.l(this.ae));
                if (deviceInfo.getDeviceConnectState() == 2 && deviceInfo.getDeviceIdentify().equals(this.ae)) {
                    this.dv = true;
                    return;
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        Context context;
        RelativeLayout relativeLayout = this.cr;
        if (relativeLayout == null || (context = this.dy) == null) {
            return;
        }
        relativeLayout.setPadding((int) context.getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a), 0, (int) this.dy.getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a), 0);
    }

    static class j implements IBaseResponseCallback {
        WeakReference<DevicePairGuideActivity> c;

        j(DevicePairGuideActivity devicePairGuideActivity) {
            this.c = new WeakReference<>(devicePairGuideActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            final DevicePairGuideActivity devicePairGuideActivity = this.c.get();
            if (devicePairGuideActivity == null) {
                return;
            }
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "WearBindCallBack isHuaweiWearBinded errCode:", Integer.valueOf(i), " objData:", obj);
            devicePairGuideActivity.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.j.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i != 0) {
                        devicePairGuideActivity.d(false);
                        return;
                    }
                    CompatibilityInteractor.WearDeviceState wearDeviceState = (CompatibilityInteractor.WearDeviceState) obj;
                    LogUtil.a("DEVMGR_DevicePairGuideActivity", "WearBindCallBack state:", wearDeviceState);
                    if (wearDeviceState == CompatibilityInteractor.WearDeviceState.NO_BIND_DEVICE) {
                        devicePairGuideActivity.d(false);
                    } else {
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "WearBindCallBack isHuaweiWearBinded Enter else");
                        devicePairGuideActivity.u();
                    }
                }
            });
        }
    }

    public void e() {
        this.bd = this.ec.b();
    }

    public void b() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter checkCompatibility");
        this.z.a(this.eg, this.an, new j(this));
    }

    public void f() {
        e eVar = new e(this);
        this.dq = eVar;
        eVar.e();
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static int f9190a = 60000;
        private final String b;
        private final Runnable c = new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.e.3
            @Override // java.lang.Runnable
            public void run() {
                DevicePairGuideActivity devicePairGuideActivity = (DevicePairGuideActivity) e.this.e.get();
                if (devicePairGuideActivity != null) {
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(e.this.b);
                    devicePairGuideActivity.af.e(arrayList, true);
                    ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "ConnectTimeoutHandler timeoutRunnable unPair");
                    devicePairGuideActivity.b(-1);
                }
            }
        };
        private final WeakReference<DevicePairGuideActivity> e;

        public e(DevicePairGuideActivity devicePairGuideActivity) {
            this.e = new WeakReference<>(devicePairGuideActivity);
            this.b = devicePairGuideActivity.ae;
        }

        public void e() {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "ConnectTimeoutHandler startCheck");
            DevicePairGuideActivity devicePairGuideActivity = this.e.get();
            if (devicePairGuideActivity != null) {
                devicePairGuideActivity.ax.postDelayed(this.c, f9190a);
            }
        }

        public void b() {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "ConnectTimeoutHandler stopCheck");
            DevicePairGuideActivity devicePairGuideActivity = this.e.get();
            if (devicePairGuideActivity != null) {
                devicePairGuideActivity.ax.removeCallbacks(this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        Integer valueOf = Integer.valueOf(oae.d(oae.c(this.bl)));
        String str = this.bl;
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "AddDevice, device.Type is ", valueOf, ", mName is ", str, "deviceType is", Integer.valueOf(oae.c(str)), "mDeviceAddress is", iyl.d().e(this.ae));
        c(this.af.c(), oac.a(this.an), true);
        oaf.b(this.dy).h(this.ae);
    }

    static class b implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<DevicePairGuideActivity> f9188a;

        b(DevicePairGuideActivity devicePairGuideActivity) {
            this.f9188a = new WeakReference<>(devicePairGuideActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            DevicePairGuideActivity devicePairGuideActivity = this.f9188a.get();
            if (devicePairGuideActivity == null) {
                return;
            }
            if (i == 0 && "SURE".equals(obj)) {
                CommonUtil.ak(BaseApplication.getContext());
            }
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "Enter showDialogToMigrate finish");
            devicePairGuideActivity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogUtil.b("DEVMGR_DevicePairGuideActivity", "Enter showLowVersionDialog");
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.11
            @Override // java.lang.Runnable
            public void run() {
                List<DeviceInfo> h2 = dwo.d().h();
                if (h2 == null || h2.size() <= 0 || !CompatibilityInteractor.c(BaseApplication.getContext())) {
                    DevicePairGuideActivity.this.d(false);
                    return;
                }
                if (!DevicePairGuideActivity.this.z.b(h2)) {
                    DevicePairGuideActivity.this.d(false);
                } else if (DevicePairGuideActivity.this.z.a(h2) == null) {
                    DevicePairGuideActivity.this.d(false);
                } else {
                    DevicePairGuideActivity.this.z.a(DevicePairGuideActivity.this.b, (DeviceInfo) null, new b(DevicePairGuideActivity.this));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (this.z.a(BaseApplication.getContext())) {
            i();
        } else if (CompatibilityInteractor.c(BaseApplication.getContext())) {
            x();
        } else {
            d(false);
        }
    }

    public static class i implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<DevicePairGuideActivity> f9191a;

        public i(DevicePairGuideActivity devicePairGuideActivity) {
            this.f9191a = null;
            this.f9191a = new WeakReference<>(devicePairGuideActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            DevicePairGuideActivity devicePairGuideActivity = this.f9191a.get();
            if (devicePairGuideActivity == null) {
                return;
            }
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "unbindDeviceList Enter callback errCode:", Integer.valueOf(i));
            devicePairGuideActivity.ax.removeMessages(9);
            devicePairGuideActivity.d(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final List<DeviceInfo> list) {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter showunBindDialog");
        this.ax.post(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.12
            @Override // java.lang.Runnable
            public void run() {
                List list2 = list;
                if (list2 == null || list2.isEmpty()) {
                    LogUtil.a("DEVMGR_DevicePairGuideActivity", "showDialogToMigrate deviceInfoList is null ");
                    DevicePairGuideActivity.this.ax.sendEmptyMessage(4);
                    return;
                }
                String string = BaseApplication.getContext().getResources().getString(R.string._2130842360_res_0x7f0212f8);
                String string2 = BaseApplication.getContext().getResources().getString(R.string._2130841377_res_0x7f020f21);
                StringBuilder sb = new StringBuilder(16);
                sb.append(String.format(Locale.ENGLISH, string, string2));
                for (DeviceInfo deviceInfo : list) {
                    if (deviceInfo != null && jfu.l(deviceInfo.getProductType())) {
                        sb.append(nxe.c);
                        sb.append(deviceInfo.getDeviceName());
                    }
                }
                View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.dialog_migrate_low_version, (ViewGroup) null);
                ((HealthCheckBox) inflate.findViewById(R.id.compatibility_remind)).setVisibility(8);
                ((HealthTextView) inflate.findViewById(R.id.compatibility_content)).setText(sb.toString());
                if (DevicePairGuideActivity.this.ay != null) {
                    CustomAlertDialog.Builder c2 = DevicePairGuideActivity.this.ay.c();
                    if (c2 == null) {
                        LogUtil.h("DEVMGR_DevicePairGuideActivity", "showDialogToMigrate builder is null");
                        return;
                    }
                    c2.cyp_(inflate);
                    if (DevicePairGuideActivity.this.ad == null || !DevicePairGuideActivity.this.ad.isShowing()) {
                        DevicePairGuideActivity.this.ad = c2.c();
                        DevicePairGuideActivity.this.ad.setCanceledOnTouchOutside(false);
                        DevicePairGuideActivity.this.ad.setCancelable(false);
                        DevicePairGuideActivity.this.ad.show();
                        DevicePairGuideActivity.this.ad.setOnKeyListener(DevicePairGuideActivity.this.ee);
                        return;
                    }
                    return;
                }
                LogUtil.h("DEVMGR_DevicePairGuideActivity", "showUnBindDialog mHelper is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.c("DEVMGR_DevicePairGuideActivity", "enter checkWearAppBindDeviceList method");
        jdx.b(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.15
            @Override // java.lang.Runnable
            public void run() {
                List<DeviceInfo> j2 = DevicePairGuideActivity.this.af.j();
                if (j2 != null && !j2.isEmpty()) {
                    LogUtil.a("DEVMGR_DevicePairGuideActivity", "migrateWearDeviceList health has device");
                    DevicePairGuideActivity.this.ax.sendEmptyMessage(4);
                } else {
                    DevicePairGuideActivity.this.af.e(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.15.3
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj) {
                            LogUtil.a("DEVMGR_DevicePairGuideActivity", "checkWearAppBindDeviceList errorCode：", Integer.valueOf(i2));
                            if (i2 == 0 && (obj instanceof List)) {
                                DevicePairGuideActivity.this.c((List<DeviceInfo>) obj);
                            } else {
                                DevicePairGuideActivity.this.ax.sendEmptyMessage(4);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (this.bb) {
            this.ax.sendEmptyMessage(1);
            if (z) {
                this.ax.sendEmptyMessageDelayed(10, 2000L);
                return;
            } else {
                b(true);
                return;
            }
        }
        this.ax.sendEmptyMessage(0);
    }

    static class c implements IBaseResponseCallback {
        WeakReference<DevicePairGuideActivity> d;

        c(DevicePairGuideActivity devicePairGuideActivity) {
            this.d = null;
            this.d = new WeakReference<>(devicePairGuideActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            final DevicePairGuideActivity devicePairGuideActivity = this.d.get();
            if (devicePairGuideActivity == null) {
                return;
            }
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "ClickWearBindCallBack isHuaweiWearBinded errCode:", Integer.valueOf(i), " objData:", obj);
            devicePairGuideActivity.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.c.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 0) {
                        CompatibilityInteractor.WearDeviceState wearDeviceState = (CompatibilityInteractor.WearDeviceState) obj;
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "ClickWearBindCallBack state:", wearDeviceState);
                        if (wearDeviceState == CompatibilityInteractor.WearDeviceState.NO_BIND_DEVICE) {
                            devicePairGuideActivity.ax.sendEmptyMessage(4);
                            return;
                        }
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "ClickWearBindCallBack isHuaweiWearBinded Enter else.");
                        if (devicePairGuideActivity.z.a(BaseApplication.getContext())) {
                            devicePairGuideActivity.i();
                            return;
                        } else if (CompatibilityInteractor.c(BaseApplication.getContext())) {
                            devicePairGuideActivity.x();
                            return;
                        } else {
                            devicePairGuideActivity.ax.sendEmptyMessage(4);
                            return;
                        }
                    }
                    devicePairGuideActivity.ax.sendEmptyMessage(4);
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        nxe nxeVar;
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onClick():");
        int id = view.getId();
        if (id == R.id.pair_guide_left_cancel_layout) {
            h();
        } else if (id == R.id.pair_guide_right_btn_layout) {
            o();
            if (CompileParameterUtil.a("IS_SUPPORT_NEW_ADD_MODE", false) && (nxeVar = this.ay) != null) {
                nxeVar.t();
            }
        } else if (id != R.id.cancel_button) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "unknown view : ", Integer.valueOf(view.getId()));
        } else {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "R.id.cancel_button enter");
            j();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "onClick():cancel");
        if (!nsn.o()) {
            nxe nxeVar = this.ay;
            if (nxeVar != null) {
                nxeVar.q();
            }
            d();
            finish();
            return;
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "click too fast");
    }

    private void o() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "onClick():btn right");
        if (!nsn.o()) {
            int i2 = this.an;
            if ((i2 == 32 || i2 == 10) && this.dd.getText().toString().equalsIgnoreCase(this.dy.getResources().getString(R.string.IDS_device_fragment_pairing_btn_open_android_wear))) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "onClick():btn_right, W1 guide.");
                aa();
                return;
            }
            if (this.dd.getText().toString().equalsIgnoreCase(this.dy.getResources().getString(R.string._2130841524_res_0x7f020fb4).toUpperCase(Locale.ENGLISH)) || this.dd.getText().toString().equalsIgnoreCase(this.dy.getResources().getString(R.string._2130841128_res_0x7f020e28).toUpperCase(Locale.ENGLISH))) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "mConnectStateChangedReceiver(): setResult is ", 2);
                a();
                Intent intent = new Intent();
                intent.putExtra("device_id", this.di);
                intent.putExtra("product_type", this.an);
                setResult(2, intent);
                String b2 = SharedPreferenceManager.b(this.dy, String.valueOf(10008), "first_pair_success_status");
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "onClick() value is ", b2);
                if (!"true".equals(b2)) {
                    SharedPreferenceManager.e(this.dy, String.valueOf(10008), "first_pair_success_status", "true", (StorageParams) null);
                }
                k();
                finish();
                return;
            }
            this.z.a(this.eg, this.an, new c(this));
            return;
        }
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "click too fast");
    }

    private void k() {
        Intent intent = new Intent();
        intent.setPackage(this.dy.getPackageName());
        intent.setClassName(this, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent.putExtra("device_id", this.di);
        startActivity(intent);
    }

    private void j() {
        if (this.d) {
            this.ay.d();
            return;
        }
        b(4, 1);
        if (TextUtils.isEmpty(this.di) && !TextUtils.isEmpty(this.ae)) {
            LogUtil.h("DEVMGR_DevicePairGuideActivity", "mTempMac is empty");
            this.di = this.ae;
        }
        if (TextUtils.isEmpty(this.di)) {
            return;
        }
        ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "mTempMac is not empty.");
        sqo.m("user cancel pair device");
        d();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(this.di);
        this.af.e(arrayList, true);
        finish();
    }

    public void a() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("devicename", jfu.c(this.an, this.bl, this.bf));
        if (this.z.a(BaseApplication.getContext())) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "migrate_success");
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "getMessageCenterFromWear begin ", b2);
            if (!"true".equals(b2)) {
                jdx.b(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StorageParams storageParams = new StorageParams(0);
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "migrate_wear_time", "" + System.currentTimeMillis(), storageParams);
                        dwo.d().e(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.1.3
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i2, Object obj) {
                                LogUtil.a("DEVMGR_DevicePairGuideActivity", "getMessageCenterFromWear response");
                            }
                        });
                    }
                });
            }
        } else {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "getMessageCenterFromWear enter else:", SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "migrate_success"));
        }
        new oam().c(this.di, BaseApplication.getContext(), new a(this, hashMap));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        ReleaseLogUtil.e("DEVMGR_DevicePairGuideActivity", "enter startAddDevice():");
        this.ea = false;
        c(false);
        List<DeviceInfo> c2 = this.af.c();
        int i2 = this.an;
        if (i2 == 11) {
            ArrayList<String> arrayList = new ArrayList<>(16);
            if ("HUAWEI CM-R1P".equals(this.bl) || this.dy.getString(R.string._2130849807_res_0x7f02300f).equals(this.bl) || this.dy.getString(R.string.IDS_device_r1_pro_name_title).equals(this.bl)) {
                arrayList.add("HUAWEI CM-R1P");
            } else {
                arrayList.add("HUAWEI AM-R1");
            }
            c(c2, arrayList, false);
            return;
        }
        c(c2, oac.a(i2), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final boolean z) {
        this.dz = z;
        this.dm = new CustomPermissionAction(this.dy) { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.3
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                DevicePairGuideActivity.e = true;
                if (z) {
                    DevicePairGuideActivity.this.ab();
                } else {
                    DevicePairGuideActivity.this.v();
                }
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                super.onDenied(str);
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "requestPermissions Permission onDenied");
                DevicePairGuideActivity.this.b(5, 1);
                sqo.m("requestPermissions Permission onDenied");
                DevicePairGuideActivity.this.ax.sendEmptyMessage(12);
                DevicePairGuideActivity.this.w();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "requestPermissions Permission onForeverDenied");
                DevicePairGuideActivity.this.b(5, 3);
                sqo.m("requestPermissions Permission onForeverDenied");
                DevicePairGuideActivity.this.ax.sendEmptyMessage(12);
                DevicePairGuideActivity.this.w();
            }
        };
        PermissionUtil.b(this, PermissionUtil.PermissionType.PHONE_STATE, this.dm);
    }

    public void b(int i2, int i3) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Result", 2);
        hashMap.put("deviceType", "HDK_WEAR");
        hashMap.put("Action", Integer.valueOf(i3));
        hashMap.put("Page", Integer.valueOf(i2));
        hashMap.put("deviceName", this.bl);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.UNAUTHORIZED_DEVICE_PAIRING_FAILURE.value(), hashMap, 0);
    }

    private void c(List<DeviceInfo> list, ArrayList<String> arrayList, boolean z) {
        String str;
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "addDeviceForConnectedAndDisconnected isFromAutoFind is ", Boolean.valueOf(z));
        this.eb = false;
        if (TextUtils.isEmpty(this.ae)) {
            str = null;
        } else {
            str = this.ae;
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "addDeviceForConnectedAndDisconnected macAddress ", iyl.d().e(str));
        }
        nxf.c(this.dy).b(this.dr);
        if (obb.e(this.an) || list == null || list.isEmpty()) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "connectedDeviceInfo is null");
            DeviceParameter l = l();
            l.setNameFilter(arrayList);
            l.setMac(str);
            this.af.d(l, "", this.dr);
            return;
        }
        if (list.size() == 1) {
            e(list, arrayList, str);
        } else {
            c(list, arrayList, str);
        }
    }

    private void c(List<DeviceInfo> list, ArrayList<String> arrayList, String str) {
        if (cvt.c(this.an)) {
            for (DeviceInfo deviceInfo : list) {
                if (cvt.c(deviceInfo.getProductType())) {
                    LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected aw70 device and want another aw70 device");
                    DeviceParameter l = l();
                    l.setNameFilter(arrayList);
                    l.setMac(str);
                    this.af.d(l, deviceInfo.getDeviceIdentify(), this.dr);
                    return;
                }
            }
            return;
        }
        for (DeviceInfo deviceInfo2 : list) {
            if (!cvt.a(deviceInfo2.getProductType(), deviceInfo2.getAutoDetectSwitchStatus())) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected device is other device");
                DeviceParameter l2 = l();
                l2.setNameFilter(arrayList);
                l2.setMac(str);
                this.af.d(l2, deviceInfo2.getDeviceIdentify(), this.dr);
                return;
            }
        }
    }

    private void e(List<DeviceInfo> list, ArrayList<String> arrayList, String str) {
        DeviceInfo deviceInfo = list.get(0);
        boolean c2 = cvt.c(this.an);
        boolean c3 = cvt.c(deviceInfo.getProductType());
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        DeviceParameter l = l();
        l.setNameFilter(arrayList);
        l.setMac(str);
        if (!c3) {
            if (c2) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected other device and want another aw70 device");
                this.af.d(l, "", this.dr);
                return;
            } else {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected other device and want another other device");
                this.af.d(l, deviceIdentify, this.dr);
                return;
            }
        }
        if (c3 && deviceInfo.getAutoDetectSwitchStatus() == 1) {
            if (c2) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected aw70 device in run mode and want another aw70 device");
                this.af.d(l, deviceIdentify, this.dr);
                return;
            } else {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected aw70 device in run mode and want another other device");
                this.af.d(l, "", this.dr);
                return;
            }
        }
        if (c3 && deviceInfo.getAutoDetectSwitchStatus() == 0) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice has one connected aw70 device and want another other device/aw70 device");
            this.af.d(l, deviceIdentify, this.dr);
        } else {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "startAddDevice other condition");
        }
    }

    private void aa() {
        PackageInfo packageInfo;
        PackageManager packageManager = this.dy.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app.cn", 0);
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "onClick() androidWearNameCn, error is ", e2.getMessage());
            try {
                packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app", 0);
            } catch (PackageManager.NameNotFoundException e3) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "onClick() androidWearName, error is ", e3.getMessage());
                if (CommonUtil.aa(this.dy)) {
                    if (jad.d(55)) {
                        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter gotoChinese ");
                        b((Context) this);
                    } else {
                        Context context = this.dy;
                        nrh.c(context, context.getString(R.string.IDS_device_hauwei_watch_download_android_wear_tips));
                    }
                } else {
                    Context context2 = this.dy;
                    nrh.c(context2, context2.getString(R.string._2130841083_res_0x7f020dfb));
                }
                packageInfo = null;
            }
        }
        if (packageInfo == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        ResolveInfo next = this.dy.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next != null) {
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(new ComponentName(str, str2));
            startActivity(intent2);
        }
    }

    private void b(Context context) {
        LogUtil.b("DEVMGR_DevicePairGuideActivity", "enterHuaweiAppStore():");
        if (!CommonUtil.aa(this.dy)) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "Network is not Connected!");
            nrh.e(context, R.string._2130841083_res_0x7f020dfb);
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    DevicePairGuideActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.5.3
                        @Override // java.lang.Runnable
                        public void run() {
                            boolean n = DevicePairGuideActivity.this.n();
                            LogUtil.a("DEVMGR_DevicePairGuideActivity", "isInstallFlag is ", Boolean.valueOf(n));
                            if (n) {
                                LogUtil.a("DEVMGR_DevicePairGuideActivity", "openOemAppstore not install.");
                            } else {
                                nrh.c(DevicePairGuideActivity.this.dy, DevicePairGuideActivity.this.dy.getString(R.string.IDS_device_hauwei_watch_download_android_wear_tips));
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(this.dy.getString(R$string.IDS_hwh_home_other_permissions_title)).e(this.dy.getString(R.string._2130837673_res_0x7f0200a9)).cyV_(this.dy.getString(R.string._2130841425_res_0x7f020f51).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nsn.ak(DevicePairGuideActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(this.dy.getString(R.string._2130839505_res_0x7f0207d1).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DEVMGR_DevicePairGuideActivity", "handlePermission negative.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", " enter openOemAppstore()");
        if (!CommonUtil.b(this.dy)) {
            return false;
        }
        try {
            Intent intent = new Intent("com.huawei.appmarket.intent.action.AppDetail");
            intent.setPackage("com.huawei.appmarket");
            intent.addFlags(268435456);
            intent.putExtra("APP_PACKAGENAME", "com.google.android.wearable.app.cn");
            PackageManager packageManager = getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(packageManager, 65536);
            if (resolveActivityInfo == null || !resolveActivityInfo.exported) {
                return true;
            }
            nsn.cLM_(intent, resolveActivityInfo.packageName, this.dy, getString(R.string._2130844022_res_0x7f021976));
            return true;
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "Exception localActivityNotFoundException is ", e2.getMessage());
            return false;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onActivityResult()");
        super.onActivityResult(i2, i3, intent);
        nxe nxeVar = this.ay;
        if (nxeVar != null) {
            nxeVar.cRV_(i2, i3, intent);
        }
    }

    static class d extends IAddDeviceStateAIDLCallback.Stub {
        private Handler d;

        d(Handler handler) {
            this.d = handler;
        }

        @Override // com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback
        public void onAddDeviceState(int i) throws RemoteException {
            Handler handler = this.d;
            if (handler == null) {
                return;
            }
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 8;
            obtainMessage.arg1 = i;
            this.d.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "deviceStateChanged(): state:", Integer.valueOf(i2));
        if (i2 == 1) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "mAddDeviceStateAidlCallback(): user open swtich");
            this.ax.sendEmptyMessage(5);
            int i3 = this.an;
            if (i3 == 32 || i3 == 10) {
                finish();
            }
        } else if (i2 == 2) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "mAddDeviceStateAidlCallback(): open switch fail");
            Message obtainMessage = this.ax.obtainMessage();
            obtainMessage.what = 3;
            obtainMessage.arg1 = 0;
            this.ax.sendMessage(obtainMessage);
        } else if (i2 == 3) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "mAddDeviceStateAidlCallback(): cancel swtich open");
        } else if (i2 == 5) {
            try {
                SmartWatchInfo e2 = jfq.c().e();
                if (e2 != null) {
                    LogUtil.a("DEVMGR_DevicePairGuideActivity", "getPendingPairingSmartWatchInfo cacherssi:", Integer.valueOf(e2.getRssiValue()));
                    GoogleDeviceCache.QrBleCache qrBleCache = new GoogleDeviceCache.QrBleCache();
                    qrBleCache.setBluetoothDevice(e2.getBluetoothDevice());
                    qrBleCache.setRssi(e2.getRssiValue());
                    qrBleCache.setTime(e2.getTimeStampMs());
                    GoogleDeviceCache.getInstance().saveCache(qrBleCache);
                } else {
                    LogUtil.a("DEVMGR_DevicePairGuideActivity", "get smart watch info failed");
                }
            } catch (RemoteException e3) {
                LogUtil.b("DEVMGR_DevicePairGuideActivity", "get smart watch info error, msg:", e3.getMessage());
            }
            nrj.cKa_(this);
        } else {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "no support state : :", Integer.valueOf(i2));
        }
        if (i2 == 4) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "AddDeviceState.DEVICE_BT_ENABLE_SCAN MSG_ENABLE_RIGHT_BTN");
            this.ax.sendEmptyMessage(5);
        }
    }

    private void p() {
        try {
            IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            intentFilter.setPriority(1000);
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("com.huawei.bone.action.REQUEST_BIND_DEVICE");
            intentFilter.addAction("com.huawei.health.action.AddDeviceStatusChanged");
            BroadcastManagerUtil.bFC_(this, this.ds, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "registerConnectStateBroadcast IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "enter showPairProcess()");
        this.be = true;
        this.bu.setVisibility(8);
        this.bs.setVisibility(8);
        this.ai.setVisibility(8);
        this.t.setVisibility(8);
        this.cs.setVisibility(0);
        this.am.setVisibility(0);
        this.cb.setVisibility(0);
        this.cu.setVisibility(8);
        this.cy.setVisibility(8);
        this.dl.setVisibility(8);
        this.h.setVisibility(8);
        this.cv.setVisibility(8);
        if (cvt.c(this.an)) {
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(8);
            this.cw.setVisibility(8);
            this.cw.setText(this.dy.getString(R.string._2130841387_res_0x7f020f2b));
        }
        this.dh.setVisibility(8);
        a(this.an);
        if (cvt.c(this.an)) {
            return;
        }
        this.c.start();
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "showPairProcess start anim.");
    }

    private void a(int i2) {
        if (i2 != 1) {
            if (i2 != 21) {
                if (i2 != 7) {
                    if (i2 != 8) {
                        if (i2 != 18 && i2 != 19 && i2 != 44 && i2 != 45) {
                            switch (i2) {
                                case 12:
                                case 13:
                                case 15:
                                    break;
                                case 14:
                                    break;
                                default:
                                    d(i2);
                                    break;
                            }
                        }
                        q();
                        return;
                    }
                }
            }
            r();
            return;
        }
        this.cw.setVisibility(0);
        this.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
    }

    private void d(int i2) {
        if (i2 != 10) {
            if (i2 == 11) {
                this.cw.setVisibility(0);
                this.ct.setImageResource(R.drawable._2131430609_res_0x7f0b0cd1);
                return;
            }
            if (i2 == 23) {
                nxe nxeVar = this.ay;
                if (nxeVar != null) {
                    nxeVar.m();
                    return;
                }
                return;
            }
            if (i2 == 24) {
                nxe nxeVar2 = this.ay;
                if (nxeVar2 != null) {
                    nxeVar2.k();
                    return;
                }
                return;
            }
            if (i2 != 32) {
                if (i2 == 36) {
                    nxe nxeVar3 = this.ay;
                    if (nxeVar3 != null) {
                        nxeVar3.l();
                        return;
                    }
                    return;
                }
                if (i2 == 37) {
                    nxe nxeVar4 = this.ay;
                    if (nxeVar4 != null) {
                        nxeVar4.n();
                        return;
                    }
                    return;
                }
                nxe nxeVar5 = this.ay;
                if (nxeVar5 != null) {
                    nxeVar5.c(i2);
                    return;
                }
                return;
            }
        }
        this.cw.setVisibility(0);
        this.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
        this.bx.setVisibility(8);
    }

    private void q() {
        this.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
        this.cu.setVisibility(8);
        String string = this.dy.getString(this.cz[0], this.aj);
        this.cw.setVisibility(0);
        this.cw.setText(string);
    }

    private void r() {
        this.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
        this.cu.setVisibility(8);
        String string = this.dy.getString(this.cz[0], this.aj);
        this.cw.setVisibility(0);
        this.cw.setText(string);
    }

    private void z() {
        try {
            unregisterReceiver(this.ds);
            unregisterReceiver(this.dx);
            ac();
        } catch (IllegalArgumentException e2) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", e2.getMessage());
        } catch (IllegalStateException unused) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "unregisterBroadcast IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        try {
            BroadcastReceiver broadcastReceiver = this.bo;
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DEVMGR_DevicePairGuideActivity", "unregisterBroadcastReceiver is error");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onStart():");
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onRestart():");
        super.onRestart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onResume():");
        c(true);
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onPause():");
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onStop():");
        super.onStop();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onDestroy():");
        super.onDestroy();
        e = false;
        AgreementDeclarationActivity.b = false;
        this.be = false;
        e eVar = this.dq;
        if (eVar != null) {
            eVar.b();
        }
        jfr.b("DEVMGR_DevicePairGuideActivity");
        z();
        this.z.b("getDeviceList");
        this.z.b("unbindDevicesByTypes");
        this.z.b("getWearData");
        Handler handler = this.ax;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        nxe nxeVar = this.ay;
        if (nxeVar != null) {
            nxeVar.q();
            this.ay.e();
            this.ay.a();
            this.ay = null;
        }
        this.bh = false;
        this.bk = false;
        jeg.d().b(this.dm);
        this.dm = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && this.be) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "Enter onBackPressed():");
        nxe nxeVar = this.ay;
        if (nxeVar != null) {
            nxeVar.q();
        }
        d();
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "enter isEnableScanButton isEnable:", Boolean.valueOf(z));
        this.bx.setEnabled(z);
    }

    private DeviceParameter l() {
        DeviceParameter deviceParameter = new DeviceParameter();
        deviceParameter.setBluetoothType(oae.d(this.an));
        deviceParameter.setProductType(this.an);
        deviceParameter.setDeviceNameInfo(this.bl);
        deviceParameter.setIsSupportHeartRate(this.ed);
        deviceParameter.setProductPin(this.da);
        LogUtil.a("DEVMGR_DevicePairGuideActivity", "getAddDeviceParametere mDeviceProductType: ", Integer.valueOf(this.an), " DeviceInfoManager.isDeviceBand(mDeviceProductType): ", Boolean.valueOf(jfu.h(this.an)), " mIsOnlyShowHeartRate: ", Boolean.valueOf(this.ed));
        deviceParameter.setIsBand(jfu.h(this.an));
        return deviceParameter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        HealthColumnLinearLayout healthColumnLinearLayout = this.x;
        if (healthColumnLinearLayout != null) {
            healthColumnLinearLayout.setVisibility(8);
        }
        LinearLayout linearLayout = this.ac;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    public void d() {
        if (this.bc) {
            LogUtil.a("DEVMGR_DevicePairGuideActivity", "mDeviceProductType:", Integer.valueOf(this.an), "mName:", this.bl);
            obi.a().a(this.an, this.bl);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
