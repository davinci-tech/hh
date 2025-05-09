package com.huawei.ui.homehealth.devicemanagercard;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btcommon.BluetoothSwitchStateUtil;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.device.views.animation.ActivityAnimationCallback;
import com.huawei.ui.homehealth.device.callback.ReconnectCallback;
import com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect;
import com.huawei.ui.homehealth.view.CloudReconnectFailDialog;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import defpackage.cpl;
import defpackage.cvc;
import defpackage.cvt;
import defpackage.cvw;
import defpackage.cwf;
import defpackage.ixx;
import defpackage.iyl;
import defpackage.jah;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jge;
import defpackage.jkj;
import defpackage.joj;
import defpackage.jpt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nue;
import defpackage.oad;
import defpackage.oae;
import defpackage.oaf;
import defpackage.oau;
import defpackage.obb;
import defpackage.obi;
import defpackage.obq;
import defpackage.obt;
import defpackage.obw;
import defpackage.ogj;
import defpackage.owp;
import defpackage.oxa;
import defpackage.pep;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class DeviceManagerWearNoConnect extends BaseActivity implements View.OnClickListener {
    private boolean aa;
    private LinearLayout ac;
    private HealthProgressBar ad;
    private LinearLayout ae;
    private NoTitleCustomAlertDialog ag;
    private PopViewList aj;
    private HealthButton ak;
    private Rect al;
    private HealthTextView an;
    private Context c;
    private obt d;
    protected ActivityResultLauncher<IntentSenderRequest> e;
    private BluetoothSwitchStateUtil h;
    private HealthButton j;
    private DeviceInfo k;
    private HealthTextView l;
    private CustomTitleBar m;
    private LinearLayout n;
    private Context o;
    private RelativeLayout p;
    private HealthTextView r;
    private ImageView t;
    private int u;
    private HealthTextView z;
    private String x = "";
    private String s = "";
    private String w = "";
    private int q = 0;
    private boolean v = false;
    private int af = 0;
    private Handler y = new d(this);
    private NoTitleCustomAlertDialog g = null;
    private CustomTextAlertDialog ai = null;
    private CustomTextAlertDialog b = null;
    private boolean ab = false;
    private String i = "";
    private final BroadcastReceiver ah = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DeviceManagerWearNoConnect", "mNonLocalBroadcastReceiver(): intent is null");
                return;
            }
            LogUtil.a("DeviceManagerWearNoConnect", "mNonLocalBroadcastReceiver(): intent :", intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || "com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                DeviceManagerWearNoConnect.this.dai_(intent);
                return;
            }
            if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
                LogUtil.a("DeviceManagerWearNoConnect", "mNonLocalBroadcastReceiver() device Pair failed");
                DeviceManagerWearNoConnect.this.ab = false;
                DeviceManagerWearNoConnect.this.y.removeMessages(101);
                Message obtainMessage = DeviceManagerWearNoConnect.this.y.obtainMessage();
                obtainMessage.what = 101;
                DeviceManagerWearNoConnect.this.y.sendMessage(obtainMessage);
                DeviceManagerWearNoConnect.this.e(intent.getStringExtra("pairGuideSelectAddress"));
                return;
            }
            LogUtil.a("DeviceManagerWearNoConnect", "onReceive Action else branch");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private ActivityAnimationCallback f9417a = new ActivityAnimationCallback() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.4
        @Override // com.huawei.ui.device.views.animation.ActivityAnimationCallback
        public void onProcessExit(boolean z) {
            LogUtil.a("DeviceManagerWearNoConnect", "mActivityAnimationCallback onProcessExit:", Boolean.valueOf(z));
            if (z) {
                DeviceManagerWearNoConnect.this.overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
                DeviceManagerWearNoConnect deviceManagerWearNoConnect = DeviceManagerWearNoConnect.this;
                deviceManagerWearNoConnect.moveTaskToBack(deviceManagerWearNoConnect.aa);
            }
            DeviceManagerWearNoConnect.this.finishAndRemoveTask();
        }
    };
    private final BtSwitchStateCallback f = new BtSwitchStateCallback() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.3
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            if (DeviceManagerWearNoConnect.this.h == null) {
                return;
            }
            DeviceManagerWearNoConnect.this.h.c(DeviceManagerWearNoConnect.this.f);
            LogUtil.a("DeviceManagerWearNoConnect", "onBtSwitchStateCallback bluetoothSwitchState:", Integer.valueOf(i));
            if (i == 3) {
                DeviceManagerWearNoConnect.this.p();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void dai_(Intent intent) {
        Parcelable parcelable;
        String str;
        String str2;
        try {
            try {
                parcelable = intent.getParcelableExtra("deviceinfo");
            } catch (BadParcelableException unused) {
                LogUtil.b("DeviceManagerWearNoConnect", "dealDeviceConnectState BadParcelableException");
                parcelable = null;
            }
            if (!(parcelable instanceof DeviceInfo)) {
                LogUtil.h("DeviceManagerWearNoConnect", "mNonLocalBroadcastReceiver objectData is null or not deviceInfo");
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) parcelable;
            Message obtainMessage = this.y.obtainMessage();
            obtainMessage.obj = deviceInfo.getDeviceIdentify();
            LogUtil.a("DeviceManagerWearNoConnect", "dealDeviceConnectState connect status: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
            if ((!this.v || (str2 = this.s) == null || !str2.equals(deviceInfo.getDeviceName())) && ((str = this.x) == null || !str.equals(obtainMessage.obj))) {
                LogUtil.h("DeviceManagerWearNoConnect", "other device state changed");
                return;
            }
            daj_(deviceInfo, obtainMessage);
        } catch (ClassCastException unused2) {
            LogUtil.b("DeviceManagerWearNoConnect", "DeviceInfo deviceInfo error ClassCastException");
        }
    }

    private void daj_(DeviceInfo deviceInfo, Message message) {
        switch (deviceInfo.getDeviceConnectState()) {
            case 1:
            case 9:
            case 10:
            case 13:
                LogUtil.a("DeviceManagerWearNoConnect", "mNonLocalBroadcastReceiver() DEVICE_CONNECTING");
                message.what = 1;
                message.arg1 = deviceInfo.getDeviceConnectState();
                this.y.sendMessage(message);
                break;
            case 2:
                message.what = 102;
                this.y.sendMessage(message);
                this.ab = false;
                LogUtil.a("DeviceManagerWearNoConnect", "device_connected");
                break;
            case 3:
                this.ab = false;
                message.what = 104;
                this.y.sendMessage(message);
                break;
            case 4:
                this.ab = false;
                break;
            case 5:
                message.obj = deviceInfo.getDeviceIdentify();
                message.what = 103;
                this.y.sendMessage(message);
                break;
            case 6:
            case 7:
            case 8:
            case 12:
            default:
                LogUtil.h("DeviceManagerWearNoConnect", "mNonLocalBroadcastReceiver default state");
                break;
            case 11:
                d(deviceInfo);
                break;
        }
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceFactoryReset() == 1) {
            this.y.removeMessages(101);
            if (AgreementDeclarationActivity.b) {
                LogUtil.h("DeviceManagerWearNoConnect", "oobe already is open");
                return;
            }
            Intent intent = new Intent(this, (Class<?>) AgreementDeclarationActivity.class);
            intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
            Bundle bundle = new Bundle();
            bundle.putParcelable("deviceInfo", deviceInfo);
            intent.putExtras(bundle);
            intent.putExtra("device_country_code", deviceInfo.getCountryCode());
            intent.putExtra("device_emui_version", deviceInfo.getEmuiVersion());
            startActivity(intent);
            LogUtil.a("DeviceManagerWearNoConnect", "device_connected and go to oobe");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = BaseApplication.getContext();
        this.c = this;
        LogUtil.a("DeviceManagerWearNoConnect", "onCreate()");
        j();
        h();
        b();
    }

    private void b() {
        this.e = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DeviceManagerWearNoConnect.this.d((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void d(ActivityResult activityResult) {
        obi.a().e(String.valueOf(activityResult.getResultCode()));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            obi.a().c();
        } else {
            obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == -1) {
                        obi.a().e();
                    } else {
                        obi.a().c();
                    }
                }
            }, this.c);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        Intent intent = getIntent();
        if (intent != null) {
            this.aa = intent.getBooleanExtra("FROM_SMART_LIFE", false);
            this.al = intent.getSourceBounds();
            if (this.aa) {
                LogUtil.a("DeviceManagerWearNoConnect", "loadApplicationTheme from smartLift");
                pep.dmW_(this, 2);
                return;
            } else {
                pep.dmW_(this, 1);
                return;
            }
        }
        pep.dmW_(this, 1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        q();
        obi.a().b();
        BluetoothSwitchStateUtil bluetoothSwitchStateUtil = this.h;
        if (bluetoothSwitchStateUtil != null) {
            bluetoothSwitchStateUtil.b();
        }
        Handler handler = this.y;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.y = null;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        e(true);
    }

    private void e(boolean z) {
        if (this.d != null && this.aa) {
            LogUtil.a("DeviceManagerWearNoConnect", "onBackPressed startExit");
            this.d.d(this.f9417a);
        } else if (z) {
            super.onBackPressed();
            pep.c(this);
        } else {
            finish();
            pep.c(this);
        }
    }

    private void h() {
        m();
        Intent intent = getIntent();
        if (intent != null) {
            dak_(intent);
            if (jfu.m(this.u)) {
                LogUtil.a("DeviceManagerWearNoConnect", "is plugin download");
                String j = jfu.j(this.u);
                LogUtil.a("DeviceManagerWearNoConnect", "is plugin download uuid:", j);
                boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j);
                LogUtil.a("DeviceManagerWearNoConnect", "is plugin download pluginAvailable:", Boolean.valueOf(isResourcesAvailable));
                a(j);
                b(isResourcesAvailable, j);
            } else {
                l();
            }
        }
        List<DeviceInfo> b = jfv.b();
        if (b == null || b.size() == 0 || this.x == null) {
            LogUtil.h("DeviceManagerWearNoConnect", "(deviceList == null) || (deviceList.size() == 0) || (mIdentify == null)");
            return;
        }
        Iterator<DeviceInfo> it = b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next != null && this.x.equalsIgnoreCase(next.getDeviceIdentify())) {
                a(next);
                f();
                this.k = next;
                if (next.getDeviceConnectState() == 1) {
                    this.y.sendEmptyMessage(100);
                    this.y.sendEmptyMessageDelayed(101, 20000L);
                } else if (next.getDeviceConnectState() == 2) {
                    this.ad.setVisibility(8);
                    b(this.x);
                    finish();
                } else {
                    LogUtil.h("DeviceManagerWearNoConnect", "deviceInfo.getDeviceConnectState() ", Integer.valueOf(next.getDeviceConnectState()));
                }
            }
        }
        dao_(intent);
    }

    private void dao_(Intent intent) {
        if (this.aa && this.al != null) {
            getWindow().setBackgroundDrawableResource(R.color._2131296971_res_0x7f0902cb);
            obt obtVar = new obt();
            this.d = obtVar;
            obtVar.b(getIntent().getBooleanExtra("isClick", false));
            this.d.cUX_(this, intent, this.aa);
            return;
        }
        getWindow().setBackgroundDrawableResource(R.color._2131296657_res_0x7f090191);
    }

    private void a(String str) {
        LogUtil.a("DeviceManagerWearNoConnect", "getConnectTip");
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            return;
        }
        String c = pluginInfoByUuid.f().c();
        this.i = c;
        LogUtil.a("DeviceManagerWearNoConnect", "mAwakenDevice = ", c);
    }

    private void a(DeviceInfo deviceInfo) {
        LogUtil.a("DeviceManagerWearNoConnect", "ProductType is ", Integer.valueOf(deviceInfo.getProductType()));
        if (deviceInfo.getProductType() < 34) {
            this.p.setVisibility(8);
            return;
        }
        String d2 = pep.d(deviceInfo);
        if (this.v || TextUtils.isEmpty(d2) || TextUtils.isEmpty(d2.trim()) || deviceInfo.getProductType() == 11) {
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
            this.r.setText(pep.d(deviceInfo));
        }
    }

    private void dak_(Intent intent) {
        this.v = intent.getBooleanExtra("is_cloud_device", false);
        this.s = intent.getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        boolean booleanExtra = intent.getBooleanExtra("device_only_one", false);
        this.x = intent.getStringExtra("device_identify");
        this.q = intent.getIntExtra("device_picID", 0);
        String e = pep.e(this.o, this.x, this.s, booleanExtra, false);
        this.w = e;
        LogUtil.a("DeviceManagerWearNoConnect", "initData() mDeviceName:", this.s, " mDeviceTitleName ", e, "mIdentify:", iyl.d().e(this.x), "mDevicePicture:", Integer.valueOf(this.q));
        this.m.setTitleText(this.w);
        this.u = intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -1);
        this.aa = intent.getBooleanExtra("FROM_SMART_LIFE", false);
        this.al = intent.getSourceBounds();
        LogUtil.a("DeviceManagerWearNoConnect", "current device Type :", Integer.valueOf(this.u));
    }

    private void l() {
        if (!TextUtils.isEmpty(this.s) && this.s.contains("HUAWEI AM-R1")) {
            this.t.setBackgroundResource(R.mipmap._2131821236_res_0x7f1102b4);
            return;
        }
        try {
            this.t.setBackgroundResource(this.q);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("DeviceManagerWearNoConnect", "Resources NotFoundException");
        }
    }

    private void b(boolean z, String str) {
        if (z) {
            cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
            if (pluginInfoByUuid != null) {
                String a2 = cwf.a(pluginInfoByUuid, 4, jpt.b(this.x, "DeviceManagerWearNoConnect"));
                LogUtil.a("DeviceManagerWearNoConnect", "is plugin download image:", a2);
                this.t.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, a2));
                return;
            }
            if (jfu.h(this.u)) {
                this.t.setImageResource(R.mipmap._2131820663_res_0x7f110077);
                return;
            } else {
                this.t.setImageResource(R.mipmap._2131820673_res_0x7f110081);
                return;
            }
        }
        this.t.setImageResource(R.mipmap._2131821251_res_0x7f1102c3);
    }

    private void j() {
        setContentView(R.layout.device_manager_wear_reconnect);
        this.m = (CustomTitleBar) nsy.cMc_(this, R.id.no_connect_detail_title_bar);
        this.t = (ImageView) nsy.cMc_(this, R.id.no_connect_device_img);
        this.ae = (LinearLayout) nsy.cMc_(this, R.id.no_connect_layout);
        this.an = (HealthTextView) nsy.cMc_(this, R.id.bolt_connect_awaken_tv);
        this.ae.setVisibility(0);
        this.p = (RelativeLayout) nsy.cMc_(this, R.id.device_sn_layout);
        this.r = (HealthTextView) nsy.cMc_(this, R.id.device_sn_value_text);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.no_connect_loading_layout);
        this.ac = linearLayout;
        linearLayout.setVisibility(8);
        this.an.setVisibility(8);
        HealthProgressBar healthProgressBar = (HealthProgressBar) nsy.cMc_(this, R.id.no_connect_loading_img);
        this.ad = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        this.j = (HealthButton) nsy.cMc_(this, R.id.no_connect_button_connect);
        this.ak = (HealthButton) nsy.cMc_(this, R.id.no_connect_button_delete);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.no_connect_text);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.device_detection_text);
        this.n = (LinearLayout) nsy.cMc_(this, R.id.device_detection_layout);
        if (LanguageUtil.h(this.o)) {
            this.n.setOrientation(0);
        } else {
            this.n.setOrientation(1);
        }
        this.j.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        this.l.setOnClickListener(this);
        if (nsn.s()) {
            nsn.b(this.z);
            nsn.b(this.z);
        }
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.no_connect_detail_title_bar);
        this.m = customTitleBar;
        customTitleBar.setTitleTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.m.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: ohf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceManagerWearNoConnect.this.dar_(view);
            }
        });
    }

    public /* synthetic */ void dar_(View view) {
        e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        if (pep.i()) {
            LogUtil.a("DeviceManagerWearNoConnect", "show detection entrance");
            this.m.setRightButtonVisibility(0);
            this.m.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430193_res_0x7f0b0b31), nsf.h(R.string._2130850635_res_0x7f02334b));
            this.m.setTitleBarBackgroundColor(this.o.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
            final ArrayList arrayList = new ArrayList(16);
            arrayList.add(BaseApplication.getContext().getString(R.string._2130846072_res_0x7f022178));
            this.m.setRightButtonOnClickListener(new View.OnClickListener() { // from class: ohe
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceManagerWearNoConnect.this.daq_(arrayList, view);
                }
            });
            this.n.setVisibility(0);
            return;
        }
        this.n.setVisibility(8);
        this.m.setRightButtonVisibility(8);
    }

    public /* synthetic */ void daq_(final ArrayList arrayList, View view) {
        PopViewList popViewList = new PopViewList(this.o, this.m, arrayList, true, false);
        this.aj = popViewList;
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: ohl
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                DeviceManagerWearNoConnect.this.e(arrayList, i);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void e(ArrayList arrayList, int i) {
        if (i < 0 || i >= arrayList.size()) {
            LogUtil.h("DeviceManagerWearNoConnect", "position out of size");
        } else if (TextUtils.isEmpty((String) arrayList.get(i))) {
            LogUtil.h("DeviceManagerWearNoConnect", "type is null");
        } else {
            d("REMOTE_TYPE");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("DeviceManagerWearNoConnect", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.no_connect_button_connect) {
            if (jkj.d().j()) {
                LogUtil.a("DeviceManagerWearNoConnect", "user choose connect, other wear device is Upgrading");
                r();
            } else {
                e();
            }
        } else if (id == R.id.no_connect_button_delete) {
            oau.d(2);
            a();
        } else if (id == R.id.device_detection_text) {
            d("LOCAL_TYPE");
        } else {
            LogUtil.h("DeviceManagerWearNoConnect", "onClick other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(String str) {
        int i;
        LogUtil.a("DeviceManagerWearNoConnect", "enter startDetection");
        if ("LOCAL_TYPE".equals(str)) {
            i = 1;
        } else if ("REMOTE_TYPE".equals(str)) {
            i = 2;
        } else {
            LogUtil.c("DeviceManagerWearNoConnect", "startConnectDetection else");
            i = -1;
        }
        jge.b().c(this.k, i);
    }

    private void e() {
        if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(this.c, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.c) { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.8
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("DeviceManagerWearNoConnect", "nearby permission granted");
                    DeviceManagerWearNoConnect.this.g();
                }
            });
        } else if (jfu.c(this.u).d() == 2) {
            PermissionUtil.b(this.c, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(this.c) { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.9
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("DeviceManagerWearNoConnect", "location permission ok.");
                    if (!oad.d(DeviceManagerWearNoConnect.this.c)) {
                        DeviceManagerWearNoConnect.this.n();
                    } else {
                        DeviceManagerWearNoConnect.this.g();
                    }
                }
            });
        } else {
            g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.7
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DeviceManagerWearNoConnect", "getHarmonyStatus getValue");
                String e = jah.c().e("scale_share_harmony_tips");
                DevicePairGuideUtil.e(e);
                LogUtil.a("DeviceManagerWearNoConnect", "getHarmonyStatus scale_share_harmony_tips: ", e);
                DeviceManagerWearNoConnect.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.7.4
                    @Override // java.lang.Runnable
                    public void run() {
                        String string;
                        if ("on".equals(DevicePairGuideUtil.d())) {
                            string = BaseApplication.getContext().getString(R.string._2130843755_res_0x7f02186b);
                        } else {
                            string = BaseApplication.getContext().getString(R.string._2130843258_res_0x7f02167a);
                        }
                        pep.d(DeviceManagerWearNoConnect.this.c, string);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (iyl.d().g() != 3) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(this.o.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: ohj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceManagerWearNoConnect.this.das_(view);
                }
            }).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: ohi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceManagerWearNoConnect.this.dat_(view);
                }
            }).e();
            this.g = e;
            e.setCancelable(false);
            this.g.show();
            return;
        }
        e(0);
    }

    public /* synthetic */ void das_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.g;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.g = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dat_(View view) {
        i();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        iyl.d().d(new BtSwitchStateCallback() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.10
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
            public void onBtSwitchStateCallback(int i) {
                LogUtil.a("DeviceManagerWearNoConnect", "openBluetooth switchState :", Integer.valueOf(i));
                if (i == 3) {
                    iyl.d().e(this);
                    DeviceManagerWearNoConnect.this.e(0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.ab = true;
        if (cpl.c().b(this.x)) {
            d(i);
            return;
        }
        b(c());
        Handler handler = this.y;
        if (handler != null) {
            handler.sendEmptyMessage(100);
            this.y.sendMessageDelayed(dah_(i), 20000L);
        }
    }

    private Message dah_(int i) {
        Message obtainMessage = this.y.obtainMessage();
        obtainMessage.what = 101;
        obtainMessage.arg1 = i;
        return obtainMessage;
    }

    private DeviceInfo c() {
        List<DeviceInfo> b = jfv.b();
        if (b == null) {
            LogUtil.h("DeviceManagerWearNoConnect", "findTargetDevice mDeviceInfoList is null");
            return null;
        }
        for (DeviceInfo deviceInfo : b) {
            if (deviceInfo instanceof CloudDeviceInfo) {
                CloudDeviceInfo cloudDeviceInfo = (CloudDeviceInfo) deviceInfo;
                if (cloudDeviceInfo.getIsCloudDevice() && cloudDeviceInfo.getDeviceName().equals(this.s)) {
                    LogUtil.a("DeviceManagerWearNoConnect", "getIsCloudDevice find");
                    return deviceInfo;
                }
            } else if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(this.x)) {
                LogUtil.a("DeviceManagerWearNoConnect", "findTargetDevice find");
                return deviceInfo;
            }
        }
        LogUtil.a("DeviceManagerWearNoConnect", "findTargetDevice not find");
        return null;
    }

    private void b(DeviceInfo deviceInfo) {
        final boolean z;
        final String str;
        final String str2;
        if (deviceInfo == null) {
            LogUtil.h("DeviceManagerWearNoConnect", "handleWorkMode goingConnectDevice is null");
            return;
        }
        final List<DeviceInfo> b = jfv.b();
        if (b == null) {
            LogUtil.h("DeviceManagerWearNoConnect", "handleWorkMode deviceInfoList is null");
            return;
        }
        boolean c = cvt.c(deviceInfo.getProductType());
        LogUtil.a("DeviceManagerWearNoConnect", "handleWorkMode goingReConnected is not AW70");
        Iterator<DeviceInfo> it = b.iterator();
        while (it.hasNext()) {
            d(deviceInfo, it.next(), c);
        }
        oaf.b(BaseApplication.getContext()).h(deviceInfo.getDeviceIdentify());
        String e = e(deviceInfo);
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (deviceInfo instanceof CloudDeviceInfo) {
            obi.a().b(true);
            CloudDeviceInfo cloudDeviceInfo = (CloudDeviceInfo) deviceInfo;
            obi.a().cUG_(deviceInfo.getProductType(), deviceInfo.getDeviceName(), this, this.e);
            LogUtil.a("DeviceManagerWearNoConnect", "goingConnectDevice getIsCloudDevice");
            str2 = cloudDeviceInfo.getProductType() + Constants.LINK + cloudDeviceInfo.getDeviceIdentify();
            str = cloudDeviceInfo.getDeviceProfileId();
            z = true;
        } else {
            z = false;
            str = e;
            str2 = deviceIdentify;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.6
            @Override // java.lang.Runnable
            public void run() {
                jfv.a(b, str2);
                new obq().d(null, str, z, str2, DeviceManagerWearNoConnect.this.o);
            }
        });
    }

    private String e(DeviceInfo deviceInfo) {
        if (!TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
            return deviceInfo.getDeviceUdid();
        }
        return deviceInfo.getSecurityUuid() + "#ANDROID21";
    }

    private void d(DeviceInfo deviceInfo, DeviceInfo deviceInfo2, boolean z) {
        if (deviceInfo2 == null) {
            LogUtil.h("DeviceManagerWearNoConnect", "handleWorkMode deviceInfoList info is null");
            return;
        }
        if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
            LogUtil.a("DeviceManagerWearNoConnect", "handleWorkMode set device enable identify ", iyl.d().e(deviceInfo.getDeviceIdentify()));
            deviceInfo2.setDeviceActiveState(1);
            deviceInfo2.setDeviceConnectState(1);
            return;
        }
        if (z) {
            if (cvt.c(deviceInfo2.getProductType()) && deviceInfo2.getDeviceActiveState() == 1) {
                LogUtil.a("DeviceManagerWearNoConnect", "handleWorkMode set aw70 device disable identify ", iyl.d().e(deviceInfo2.getDeviceIdentify()));
                deviceInfo2.setDeviceActiveState(0);
                deviceInfo2.setDeviceConnectState(3);
                return;
            }
            return;
        }
        if (deviceInfo2.getAutoDetectSwitchStatus() == 1 || obb.e(deviceInfo.getProductType()) || deviceInfo2.getDeviceActiveState() != 1) {
            return;
        }
        LogUtil.a("DeviceManagerWearNoConnect", "handleWorkMode set band mode device disable identify ", iyl.d().e(deviceInfo2.getDeviceIdentify()));
        deviceInfo2.setDeviceActiveState(0);
        deviceInfo2.setDeviceConnectState(3);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("DeviceManagerWearNoConnect", "requestCode：", Integer.valueOf(i), "resultCode：", Integer.valueOf(i2));
        if (i == 1 && i2 == 2) {
            nue.cNU_(intent, this, nue.e(i2, true, intent.getIntExtra("product_type", -1), true));
        }
    }

    private void d(int i) {
        if (this.af < 2) {
            jfv.a(oxa.a().i(), this.x);
            this.af++;
            Handler handler = this.y;
            if (handler != null) {
                handler.sendEmptyMessage(100);
                this.y.sendMessageDelayed(dah_(i), 20000L);
                return;
            }
            return;
        }
        DeviceInfo a2 = jpt.a("DeviceManagerWearNoConnect");
        if (a2 != null) {
            dag_(cpl.c().Kj_(a2.getProductType()));
            this.af = 0;
        }
    }

    private void dag_(View view) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.c);
        builder.a(this.o.getString(R.string.IDS_device_mgr_pair_note_can_not_connect)).cyp_(view).cyo_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: ohh
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceManagerWearNoConnect.dal_(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(true);
        if (isFinishing()) {
            return;
        }
        c.show();
    }

    public static /* synthetic */ void dal_(DialogInterface dialogInterface, int i) {
        LogUtil.a("DeviceManagerWearNoConnect", "showAlertDialog onclick PositiveButton");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void o() {
        LogUtil.a("DeviceManagerWearNoConnect", "Enter sendDeviceListChangeBroadcast()");
        if (CommonUtil.ce()) {
            cvw.c(oxa.a().i(), "DeviceManagerWearNoConnect");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        String str;
        String str2;
        LogUtil.a("DeviceManagerWearNoConnect", "Enter unbindDevice");
        List<DeviceInfo> b = jfv.b();
        DeviceInfo deviceInfo = null;
        if (b == null || b.isEmpty()) {
            str = "";
        } else {
            LogUtil.a("DeviceManagerWearNoConnect", "delete mac :", iyl.d().e(this.x));
            str = "";
            int i = -1;
            for (DeviceInfo deviceInfo2 : b) {
                if (deviceInfo2.getDeviceIdentify().equals(this.x)) {
                    if (deviceInfo2 instanceof CloudDeviceInfo) {
                        str2 = a(deviceInfo2, (CloudDeviceInfo) deviceInfo2);
                    } else {
                        i = b.indexOf(deviceInfo2);
                        str = deviceInfo2.getDeviceName();
                        str2 = deviceInfo2.getSecurityUuid() + "#ANDROID21";
                    }
                    oaf.b(BaseApplication.getContext()).a(deviceInfo2);
                    owp.c(str2);
                    deviceInfo = deviceInfo2;
                }
            }
            if (!this.v) {
                LogUtil.a("DeviceManagerWearNoConnect", "!mIsCloudDevice:");
                if (i != -1) {
                    joj.a().a(b.get(i));
                    b.remove(i);
                    jfq.c().e(str);
                    LogUtil.a("DeviceManagerWearNoConnect", "deleteDevice");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(this.x);
                    oxa.a().c(arrayList, true);
                }
            }
        }
        oau.c(str);
        ixx.d().a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        o();
        jfq.c().d("deleteDevice", deviceInfo, 0, "");
        finish();
        pep.c(this);
    }

    private String a(DeviceInfo deviceInfo, CloudDeviceInfo cloudDeviceInfo) {
        String smartDeviceUdid = cloudDeviceInfo.getSmartDeviceUdid();
        if (TextUtils.isEmpty(smartDeviceUdid) && deviceInfo.getSecurityUuid() != null) {
            smartDeviceUdid = deviceInfo.getSecurityUuid() + "#ANDROID21";
        }
        LogUtil.a("DeviceManagerWearNoConnect", "deviceUuid length:", Integer.valueOf(smartDeviceUdid.length()));
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(this.x);
        oxa.a().c(arrayList, true);
        jfv.c(deviceInfo);
        return smartDeviceUdid;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        LogUtil.a("DeviceManagerWearNoConnect", "Enter openWearHome");
        Intent intent = new Intent();
        intent.setClass(this.c, WearHomeActivity.class);
        intent.setClassName(this.c, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent.putExtra("device_id", str);
        if (this.aa && this.al != null) {
            intent.putExtra("FROM_SMART_LIFE", true);
            intent.putExtra("FROM_NO_CONNECT_SMART_LIFE", true);
            intent.setSourceBounds(this.al);
            obw.d(this.d);
        }
        ogj.cZC_(this.c, intent, "com.huawei.ui.homewear21.home.WearHomeActivity");
    }

    static class d extends Handler {
        WeakReference<DeviceManagerWearNoConnect> e;

        d(DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            this.e = new WeakReference<>(deviceManagerWearNoConnect);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            DeviceManagerWearNoConnect deviceManagerWearNoConnect = this.e.get();
            if (deviceManagerWearNoConnect == null || message == null) {
                return;
            }
            LogUtil.a("DeviceManagerWearNoConnect", "receive message is:", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case 100:
                        e(deviceManagerWearNoConnect);
                        break;
                    case 101:
                        d(deviceManagerWearNoConnect);
                        dax_(deviceManagerWearNoConnect, message);
                        break;
                    case 102:
                        daw_(message, deviceManagerWearNoConnect);
                        break;
                    case 103:
                        a(deviceManagerWearNoConnect);
                        break;
                    case 104:
                        b(deviceManagerWearNoConnect);
                        break;
                    default:
                        LogUtil.h("DeviceManagerWearNoConnect", "handleMessage default");
                        break;
                }
                return;
            }
            LogUtil.a("DeviceManagerWearNoConnect", "handleMessage connecting");
            if (deviceManagerWearNoConnect.ab) {
                deviceManagerWearNoConnect.a(message.arg1);
            }
        }

        private void b(DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            deviceManagerWearNoConnect.ad.setVisibility(8);
            deviceManagerWearNoConnect.ae.setVisibility(0);
            deviceManagerWearNoConnect.ac.setVisibility(8);
            deviceManagerWearNoConnect.an.setVisibility(8);
            deviceManagerWearNoConnect.j.setClickable(true);
            deviceManagerWearNoConnect.ak.setClickable(true);
        }

        private void daw_(Message message, DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            if (message.obj instanceof String) {
                deviceManagerWearNoConnect.ad.setVisibility(8);
                if (!deviceManagerWearNoConnect.v) {
                    deviceManagerWearNoConnect.b((String) message.obj);
                    deviceManagerWearNoConnect.finish();
                    return;
                } else {
                    LogUtil.h("DeviceManagerWearNoConnect", "is cloud device");
                    return;
                }
            }
            LogUtil.a("DeviceManagerWearNoConnect", "handleMessage connect success message info is null");
        }

        private void a(DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            deviceManagerWearNoConnect.ad.setVisibility(8);
            deviceManagerWearNoConnect.ae.setVisibility(0);
            deviceManagerWearNoConnect.ac.setVisibility(8);
            deviceManagerWearNoConnect.an.setVisibility(8);
            deviceManagerWearNoConnect.j.setClickable(true);
            deviceManagerWearNoConnect.ak.setClickable(true);
            deviceManagerWearNoConnect.k();
        }

        private void d(DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            deviceManagerWearNoConnect.ad.setVisibility(8);
            deviceManagerWearNoConnect.ae.setVisibility(0);
            deviceManagerWearNoConnect.ac.setVisibility(8);
            deviceManagerWearNoConnect.an.setVisibility(8);
            deviceManagerWearNoConnect.j.setClickable(true);
            deviceManagerWearNoConnect.ak.setClickable(true);
        }

        private void dax_(DeviceManagerWearNoConnect deviceManagerWearNoConnect, Message message) {
            if (deviceManagerWearNoConnect.c == null) {
                LogUtil.h("DeviceManagerWearNoConnect", "showConnectTimeoutHints activity.mActivityContext == null");
            } else {
                if (message.arg1 == 1) {
                    LogUtil.a("DeviceManagerWearNoConnect", "showConnectTimeoutHints clickFrom = 1");
                    ogj.cZy_(deviceManagerWearNoConnect.c, cpl.c().Kj_(deviceManagerWearNoConnect.u));
                    return;
                }
                c(deviceManagerWearNoConnect);
            }
        }

        private void c(final DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            CloudReconnectFailDialog.Builder builder = new CloudReconnectFailDialog.Builder(deviceManagerWearNoConnect.c);
            builder.c(deviceManagerWearNoConnect.u, jfu.c(deviceManagerWearNoConnect.u).d()).e(new ReconnectCallback() { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.d.4
                @Override // com.huawei.ui.homehealth.device.callback.ReconnectCallback
                public void reconnect() {
                    deviceManagerWearNoConnect.e(1);
                }
            });
            CloudReconnectFailDialog b = builder.b();
            b.setCanceledOnTouchOutside(false);
            if (b.isShowing()) {
                return;
            }
            b.show();
        }

        private void e(DeviceManagerWearNoConnect deviceManagerWearNoConnect) {
            deviceManagerWearNoConnect.ad.setVisibility(0);
            deviceManagerWearNoConnect.ae.setVisibility(8);
            deviceManagerWearNoConnect.ac.setVisibility(0);
            if (!TextUtils.isEmpty(deviceManagerWearNoConnect.i)) {
                deviceManagerWearNoConnect.an.setVisibility(0);
                deviceManagerWearNoConnect.an.setText(deviceManagerWearNoConnect.i);
            }
            deviceManagerWearNoConnect.j.setClickable(false);
            deviceManagerWearNoConnect.ak.setClickable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("DeviceManagerWearNoConnect", "accountSwitch status:", Integer.valueOf(i));
        if (i != 9) {
            if (i == 10) {
                p();
                return;
            } else if (i != 13) {
                LogUtil.h("DeviceManagerWearNoConnect", "handleMessage default");
                return;
            }
        }
        d();
    }

    private void d() {
        if (this.b == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.o).b(this.o.getResources().getString(R.string._2130843841_res_0x7f0218c1)).e(this.o.getResources().getString(R.string._2130843842_res_0x7f0218c2)).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: ogy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceManagerWearNoConnect.this.dap_(view);
                }
            }).a();
            this.b = a2;
            a2.setCancelable(false);
            if (this.b.isShowing() || isFinishing()) {
                return;
            }
            this.b.show();
        }
    }

    public /* synthetic */ void dap_(View view) {
        LogUtil.a("DeviceManagerWearNoConnect", "The user confirms know.");
        this.b.dismiss();
        this.b = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void m() {
        LogUtil.a("DeviceManagerWearNoConnect", "registerWearBroadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED");
        intentFilter.addAction("broadcast_receiver_user_setting");
        BroadcastManagerUtil.bFC_(this.o, this.ah, intentFilter, LocalBroadcast.c, null);
        this.h = new BluetoothSwitchStateUtil(this.c);
    }

    private void q() {
        try {
            LogUtil.a("DeviceManagerWearNoConnect", "unregisterWearBroadcast");
            this.o.unregisterReceiver(this.ah);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceManagerWearNoConnect", "unregisterWearBroadcast IllegalArgumentException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.c("DeviceManagerWearNoConnect", "showBandUnavailableDialog");
        boolean h = CommonUtil.h(this.c, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        LogUtil.c("DeviceManagerWearNoConnect", "isForeground : ", Boolean.valueOf(h));
        if (!h) {
            LogUtil.h("DeviceManagerWearNoConnect", "showBandUnavailableDialog isForeground is false");
            return;
        }
        CustomTextAlertDialog customTextAlertDialog = this.ai;
        if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
            LogUtil.c("DeviceManagerWearNoConnect", "showBandUnavailableDialog Already show!");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.c).b(R.string.IDS_service_area_notice_title).e(this.c.getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: ohm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceManagerWearNoConnect.dam_(view);
            }
        }).a();
        this.ai = a2;
        a2.setCancelable(false);
        if (this.ai.isShowing()) {
            return;
        }
        this.ai.show();
    }

    public static /* synthetic */ void dam_(View view) {
        LogUtil.a("DeviceManagerWearNoConnect", "showBandUnavailableDialog onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void r() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(this.o.getResources().getString(R.string.IDS_main_device_ota_error_message)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: ohd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceManagerWearNoConnect.dan_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static /* synthetic */ void dan_(View view) {
        LogUtil.a("DeviceManagerWearNoConnect", "showTipDialog,click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            if (Build.VERSION.SDK_INT > 30) {
                PermissionUtil.b(this.c, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.c) { // from class: com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect.5
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        LogUtil.a("DeviceManagerWearNoConnect", "checkBluetoothState nearby permission granted");
                        DeviceManagerWearNoConnect.this.t();
                    }
                });
                return;
            } else {
                t();
                return;
            }
        }
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(this.o.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: ohk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceManagerWearNoConnect.this.dau_(view);
            }
        }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: ohg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceManagerWearNoConnect.this.dav_(view);
            }
        }).e();
        this.ag = e;
        e.setCancelable(false);
        this.ag.show();
    }

    public /* synthetic */ void dau_(View view) {
        LogUtil.a("DeviceManagerWearNoConnect", "user choose open bluetooth");
        BluetoothSwitchStateUtil bluetoothSwitchStateUtil = this.h;
        if (bluetoothSwitchStateUtil == null) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            bluetoothSwitchStateUtil.d(this.f);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void dav_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.ag;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.ag = null;
        }
        LogUtil.a("DeviceManagerWearNoConnect", "user choose cancel open bluetooth");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(this.o).e(arrayList, true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
