package com.huawei.ui.homehealth.device.devicelist;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.adddevice.OneKeyScanActivity;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.activity.pairing.EarphonePairActivity;
import com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource;
import com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter;
import com.huawei.ui.homehealth.device.callback.HonorPrivacyCallback;
import com.huawei.ui.homehealth.device.callback.ReconnectCallback;
import com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import com.huawei.ui.homehealth.device.view.DeviceItemDecoration;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import com.huawei.ui.homehealth.view.CloudReconnectFailDialog;
import com.huawei.ui.main.stories.userprofile.activity.WorkModeConflictDialogActivity;
import defpackage.cjv;
import defpackage.cjx;
import defpackage.cpl;
import defpackage.cpm;
import defpackage.cun;
import defpackage.cvs;
import defpackage.cvt;
import defpackage.cwi;
import defpackage.cww;
import defpackage.dcp;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.dks;
import defpackage.iyl;
import defpackage.jdi;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jgi;
import defpackage.jgs;
import defpackage.jkj;
import defpackage.jph;
import defpackage.jri;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nue;
import defpackage.oae;
import defpackage.oaf;
import defpackage.oau;
import defpackage.obb;
import defpackage.obi;
import defpackage.obq;
import defpackage.ofr;
import defpackage.ogj;
import defpackage.ogq;
import defpackage.omz;
import defpackage.oxa;
import defpackage.pep;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class DeviceMoreListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    protected ActivityResultLauncher<IntentSenderRequest> f9392a;
    private UserLabelServiceApi aa;
    private CardDeviceAdapter b;
    private Context e;
    private CustomTitleBar g;
    private HealthButton s;
    private String u;
    private HealthRecycleView w;
    private List<cjv> v = new ArrayList(16);
    private List<cjv> x = new ArrayList(16);
    private CustomTextAlertDialog j = null;
    private PermissionsResultAction y = null;
    private Handler i = new e(this);
    private ExecutorService h = Executors.newCachedThreadPool();
    private HashMap<String, Integer> ad = new HashMap<>(16);
    private boolean n = true;
    private boolean p = false;
    private boolean k = false;
    private boolean m = false;
    private int d = 0;
    private boolean r = false;
    private boolean l = true;
    private NoTitleCustomAlertDialog c = null;
    private final BroadcastReceiver t = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler;
            if (context == null || intent == null || (handler = DeviceMoreListActivity.this.i) == null) {
                return;
            }
            if ("com.huawei.bone.action.DEVICE_THIRD_DELETE".equals(intent.getAction())) {
                handler.sendEmptyMessage(35);
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || "com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                try {
                    if (intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo) {
                        DeviceMoreListActivity.this.cYZ_((DeviceInfo) intent.getParcelableExtra("deviceinfo"), handler);
                        return;
                    }
                    return;
                } catch (ClassCastException unused) {
                    LogUtil.b("DeviceMoreListActivity", "DeviceInfo deviceInfo error ClassCastException");
                    return;
                }
            }
            if ("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS".equals(intent.getAction())) {
                DeviceMoreListActivity.this.d();
                return;
            }
            if ("broadcast_receiver_user_setting".equals(intent.getAction()) && CommonUtil.h(context, "com.huawei.health.MainActivity")) {
                String stringExtra = intent.getStringExtra("pairGuideSelectAddress");
                LogUtil.a("DeviceMoreListActivity", "mNonLocalBroadcastReceiver() device Pair failed");
                DeviceMoreListActivity.this.b(stringExtra);
                return;
            }
            LogUtil.h("DeviceMoreListActivity", "mNonLocalBroadcastReceiver()  intent : ", intent.getAction());
        }
    };
    private BroadcastReceiver q = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DeviceMoreListActivity", "mPairBroadcastReceiver intent is null");
            } else if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                LogUtil.a("DeviceMoreListActivity", "pair device success");
                DeviceMoreListActivity.this.finish();
            }
        }
    };
    private final BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler = DeviceMoreListActivity.this.i;
            if (handler == null || intent == null || !"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || intent.getExtras() == null) {
                return;
            }
            handler.sendEmptyMessage(34);
        }
    };
    private HonorPrivacyCallback o = new HonorPrivacyCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.12
        @Override // com.huawei.ui.homehealth.device.callback.HonorPrivacyCallback
        public void signResult(ogq.c cVar) {
            if (cVar == null) {
                LogUtil.h("DeviceMoreListActivity", "mHonorPrivacyCallback parameter is null.");
                return;
            }
            if (!cVar.b()) {
                LogUtil.h("DeviceMoreListActivity", "mHonorPrivacyCallback refresh page after delete device.");
                if (DeviceMoreListActivity.this.b != null) {
                    DeviceMoreListActivity.this.b.a(false);
                }
                DeviceMoreListActivity.this.d();
                return;
            }
            int a2 = cVar.a();
            int c = cVar.c();
            LogUtil.a("DeviceMoreListActivity", "mHonorPrivacyCallback position: ", Integer.valueOf(a2), ", clickType: ", Integer.valueOf(c));
            if (c == 3) {
                DeviceMoreListActivity.this.cYQ_(a2, null);
                return;
            }
            if (c == 4) {
                if (cVar.d() == null || !(cVar.d().i() instanceof cpm)) {
                    return;
                }
                DeviceMoreListActivity.this.d((cpm) cVar.d().i());
                return;
            }
            LogUtil.h("DeviceMoreListActivity", "mHonorPrivacyCallback clickType is unknown.");
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        return this.u;
    }

    public void c(String str) {
        this.u = str;
    }

    private void n() {
        LogUtil.a("DeviceMoreListActivity", "enter registerBatteryBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.f, intentFilter, LocalBroadcast.c, null);
    }

    private void q() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.f);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceMoreListActivity", "unRegisterBatteryBroadcast IllegalArgumentException");
        }
    }

    private void m() {
        LogUtil.a("DeviceMoreListActivity", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        intentFilter.addAction("broadcast_receiver_user_setting");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.t, intentFilter, LocalBroadcast.c, null);
    }

    private void v() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.t);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceMoreListActivity", "unRegisterNonLocalBroadcastReceiver IllegalArgumentException");
        }
    }

    private void r() {
        LogUtil.a("DeviceMoreListActivity", "showBandUnavailableDialog");
        if (CommonUtil.h(this.e, "com.huawei.health.MainActivity")) {
            CustomTextAlertDialog customTextAlertDialog = this.j;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                LogUtil.h("DeviceMoreListActivity", "showBandUnavailableDialog Already show!");
                return;
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.e).b(R.string.IDS_service_area_notice_title).e(this.e.getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: ogd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceMoreListActivity.cYV_(view);
                }
            }).a();
            this.j = a2;
            a2.setCancelable(false);
            if (this.j.isShowing() || isFinishing()) {
                return;
            }
            this.j.show();
        }
    }

    public static /* synthetic */ void cYV_(View view) {
        LogUtil.h("DeviceMoreListActivity", "view onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.l = false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        nsn.a();
    }

    /* loaded from: classes6.dex */
    static class e extends BaseHandler<DeviceMoreListActivity> {
        e(DeviceMoreListActivity deviceMoreListActivity) {
            super(deviceMoreListActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cZi_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(DeviceMoreListActivity deviceMoreListActivity, Message message) {
            switch (message.what) {
                case 30:
                    LogUtil.a("DeviceMoreListActivity", "connect device", Integer.valueOf(message.what));
                    if (message.obj instanceof String) {
                        String str = (String) message.obj;
                        if (!TextUtils.isEmpty(str) && str.equals(deviceMoreListActivity.a())) {
                            removeMessages(31);
                            deviceMoreListActivity.b.a(false);
                            deviceMoreListActivity.c((String) null);
                        }
                        deviceMoreListActivity.b.a(false);
                        deviceMoreListActivity.b.e();
                        deviceMoreListActivity.d();
                        DeviceCapability d = cvs.d();
                        DeviceInfo f = oxa.a().f();
                        if (d != null && d.isSupportWatchFace() && f != null && (f.getPowerSaveModel() != 1 || !pep.d(f.getProductType()))) {
                            pep.g(BaseApplication.getContext());
                        }
                        if (d != null && d.isSupportMenstrual()) {
                            e(f);
                            break;
                        }
                    }
                    break;
                case 31:
                    LogUtil.a("DeviceMoreListActivity", "handleMessageWhenReferenceNotNull device connect time out");
                    removeMessages(31);
                    cZf_(deviceMoreListActivity, message);
                    break;
                case 32:
                    cZh_(deviceMoreListActivity, message);
                    break;
                case 33:
                default:
                    cZg_(message, deviceMoreListActivity);
                    break;
                case 34:
                    deviceMoreListActivity.b.notifyDataSetChanged();
                    break;
            }
        }

        private void cZf_(DeviceMoreListActivity deviceMoreListActivity, Message message) {
            deviceMoreListActivity.b.a(false);
            deviceMoreListActivity.d();
            if (message.arg1 == 1 && deviceMoreListActivity.l) {
                cZe_(message, deviceMoreListActivity);
            }
        }

        private void cZe_(Message message, final DeviceMoreListActivity deviceMoreListActivity) {
            if (!(message.obj instanceof cpm)) {
                LogUtil.h("DeviceMoreListActivity", "cloudDeviceDialog message.obj instanceof DeviceInfoForWear");
                return;
            }
            final cpm cpmVar = (cpm) message.obj;
            if (message.arg2 == 1) {
                LogUtil.a("DeviceMoreListActivity", "cloudDeviceDialog clickFrom = 1");
                ogj.cZy_(deviceMoreListActivity.e, cpl.c().Kj_(cpmVar.i()));
                return;
            }
            CloudReconnectFailDialog.Builder builder = new CloudReconnectFailDialog.Builder(deviceMoreListActivity.e);
            builder.c(cpmVar.i(), jfu.c(cpmVar.i()).d()).e(new ReconnectCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.e.5
                @Override // com.huawei.ui.homehealth.device.callback.ReconnectCallback
                public void reconnect() {
                    deviceMoreListActivity.c(cpmVar.a());
                    deviceMoreListActivity.b.a(true);
                    deviceMoreListActivity.d();
                    deviceMoreListActivity.d(cpmVar, 1);
                }
            });
            CloudReconnectFailDialog b = builder.b();
            b.setCanceledOnTouchOutside(false);
            if (b.isShowing() || deviceMoreListActivity.isFinishing()) {
                return;
            }
            b.show();
        }

        private void cZh_(DeviceMoreListActivity deviceMoreListActivity, Message message) {
            if (message.obj instanceof String) {
                String str = (String) message.obj;
                if (!TextUtils.isEmpty(str) && str.equals(deviceMoreListActivity.a())) {
                    removeMessages(31);
                    deviceMoreListActivity.b.a(false);
                    deviceMoreListActivity.c((String) null);
                }
                deviceMoreListActivity.d();
                if (deviceMoreListActivity.l && deviceMoreListActivity.b.b() == 3) {
                    deviceMoreListActivity.b.e();
                    ogj.cZy_(deviceMoreListActivity.e, cpl.c().Kj_(message.arg1));
                }
            }
        }

        private void e(DeviceInfo deviceInfo) {
            new jri().c(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.e.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("DeviceMoreListActivity", "queryMenstrualSwitch errorCode:", Integer.valueOf(i));
                    if (i == 0 && (obj instanceof MenstrualSwitchStatus)) {
                        LogUtil.a("DeviceMoreListActivity", "device connected sendMenstrualSwitchInMain");
                        omz.a().b((MenstrualSwitchStatus) obj);
                    }
                }
            });
            omz.a().e(deviceInfo);
        }

        private void cZg_(Message message, DeviceMoreListActivity deviceMoreListActivity) {
            int i = message.what;
            if (i == 12) {
                LogUtil.a("DeviceMoreListActivity", "DEVICE_GET_CURRENT_INFO_LIST message");
                if (message.obj instanceof List) {
                    deviceMoreListActivity.v.clear();
                    deviceMoreListActivity.v.addAll((List) message.obj);
                }
                deviceMoreListActivity.l();
                return;
            }
            if (i == 33) {
                LogUtil.a("DeviceMoreListActivity", "msg_connect_change state:", Boolean.valueOf(deviceMoreListActivity.b.a()), "message: ", Integer.valueOf(message.what));
                if (!(message.obj instanceof String)) {
                    LogUtil.h("DeviceMoreListActivity", "!(message.obj instanceof String)");
                    return;
                } else {
                    if (!ogj.d((String) message.obj)) {
                        LogUtil.h("DeviceMoreListActivity", "isContinueConnecting false");
                        return;
                    }
                    deviceMoreListActivity.c((String) message.obj);
                    deviceMoreListActivity.b.a(true);
                    deviceMoreListActivity.d();
                    return;
                }
            }
            if (i == 45 || i == 35) {
                LogUtil.a("DeviceMoreListActivity", "MSG_DEVICE_DELETE or MSG_UPDATE_DEVICE_LIST message: ", Integer.valueOf(message.what));
                deviceMoreListActivity.d();
            } else {
                if (i == 36) {
                    removeMessages(31);
                    deviceMoreListActivity.b.a(false);
                    deviceMoreListActivity.d();
                    deviceMoreListActivity.e(message.arg1);
                    return;
                }
                LogUtil.h("DeviceMoreListActivity", "message default");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        LogUtil.a("DeviceMoreListActivity", "onCreate()");
        setContentView(R.layout.more_device_all_list);
        this.e = this;
        h();
        if (CommonUtil.ce()) {
            m();
            n();
        }
        Handler handler = this.i;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(40, PreConnectManager.CONNECT_INTERNAL);
        }
        jph.bIM_(this, -1);
        UserLabelServiceApi userLabelServiceApi = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        this.aa = userLabelServiceApi;
        userLabelServiceApi.registerCallback(4);
        this.aa.registerCallback(5);
        f();
    }

    private void h() {
        this.f9392a = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DeviceMoreListActivity.this.d((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void d(ActivityResult activityResult) {
        obi.a().e(String.valueOf(activityResult.getResultCode()));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            obi.a().c();
        } else {
            obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.15
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == -1) {
                        obi.a().b(DeviceMoreListActivity.this.e);
                    } else {
                        obi.a().c();
                    }
                }
            }, this.e);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.m = false;
        this.l = true;
        if (this.n) {
            e();
        }
        d();
        g();
        ExecutorService executorService = this.h;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.13
                @Override // java.lang.Runnable
                public void run() {
                    List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "DeviceMoreListActivity");
                    if (deviceList != null && !deviceList.isEmpty()) {
                        oxa.a().d();
                    }
                    DeviceMoreListActivity.this.b();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.m && this.l) {
            jgi.e().b(new d(this), "DeviceMoreListActivity");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        k();
    }

    private void j() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.select_device_detail_title_bar);
        this.g = customTitleBar;
        customTitleBar.setTitleText(this.e.getString(R.string.IDS_hw_common_ui_xlistview_footer_hint_moredevice));
        this.g.setTitleTextColor(this.e.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.g.setRightButtonVisibility(0);
        this.g.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131429707_res_0x7f0b094b), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.g.setTitleBarBackgroundColor(this.e.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.g.setRightButtonOnClickListener(new View.OnClickListener() { // from class: ofx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceMoreListActivity.this.cZa_(view);
            }
        });
    }

    public /* synthetic */ void cZa_(View view) {
        new PopViewList(this.e, this.g, new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add), getResources().getString(R.string.IDS_hw_device_manager_add_device)))).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.11
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public void setOnClick(int i) {
                if (i == 0) {
                    LoginInit.getInstance(DeviceMoreListActivity.this.e).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.11.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj) {
                            if (i2 == 0) {
                                PermissionUtil.b(DeviceMoreListActivity.this.e, PermissionUtil.PermissionType.CAMERA, DeviceMoreListActivity.this.y);
                            }
                        }
                    }, "");
                } else if (i == 1) {
                    DeviceMoreListActivity.this.s();
                } else {
                    LogUtil.h("DeviceMoreListActivity", "position is null");
                }
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        this.y = new CustomPermissionAction(this.e) { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.16
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("DeviceMoreListActivity", "mQrCodeAction onGranted");
                DeviceMoreListActivity.this.i();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("DeviceMoreListActivity", "mQrCodeAction onDenied");
                DeviceMoreListActivity.this.i();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("DeviceMoreListActivity", "mQrCodeAction onForeverDenied");
                DeviceMoreListActivity.this.i();
            }
        };
        j();
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.add_device_click);
        this.w = (HealthRecycleView) nsy.cMc_(this, R.id.card_device_list);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: ofw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceMoreListActivity.this.cZb_(view);
            }
        });
        HealthButton healthButton2 = (HealthButton) nsy.cMc_(this, R.id.more_device_click);
        this.s = healthButton2;
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: ofz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceMoreListActivity.this.cZc_(view);
            }
        });
        t();
    }

    public /* synthetic */ void cZb_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            s();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void cZc_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!this.p) {
            this.p = true;
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.e == null) {
            LogUtil.h("DeviceMoreListActivity", "gotoScan mContext is null");
            return;
        }
        ogj.cZC_(this.e, new Intent(this.e, (Class<?>) QrCodeScanningActivity.class), "QrCodeScanningActivity");
        o();
    }

    private void o() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.q, intentFilter);
    }

    private void t() {
        CardDeviceAdapter cardDeviceAdapter = new CardDeviceAdapter(this.e, this, new CardDeviceAdapter.PersonalItemReconnectListener() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.19
            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onReconnect(cpm cpmVar) {
                LogUtil.a("DeviceMoreListActivity", "setRecyclerView onReconnect");
                DeviceMoreListActivity.this.a(cpmVar);
            }

            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onPersonalEquipment(int i, View view) {
                LogUtil.a("DeviceMoreListActivity", "setRecyclerView onPersonalEquipment");
                DeviceMoreListActivity.this.cYS_(i, view);
            }

            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onMoreEquipment() {
                LogUtil.a("DeviceMoreListActivity", "setRecyclerView onMoreEquipment");
                if (nsn.o()) {
                    return;
                }
                DeviceMoreListActivity.this.s();
            }
        });
        this.b = cardDeviceAdapter;
        cardDeviceAdapter.c(this.x);
        this.w.setAdapter(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(cpm cpmVar) {
        cjv cjvVar = new cjv();
        cjvVar.a(1);
        cjvVar.c(cpmVar);
        if (ogq.b(this.e, cjvVar)) {
            ogq.c cVar = new ogq.c();
            cVar.b(cjvVar);
            cVar.e(new RecommendedItem());
            cVar.c(-1);
            cVar.b(4);
            ogq.b(this.e, cVar, this.o);
            return;
        }
        d(cpmVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYS_(int i, View view) {
        if (!nsn.o() && i >= 0 && i < this.v.size()) {
            cjv cjvVar = this.v.get(i);
            if (ogq.b(this.e, cjvVar)) {
                ogq.c cVar = new ogq.c();
                cVar.b(cjvVar);
                cVar.e(new RecommendedItem());
                cVar.c(i);
                cVar.b(3);
                ogq.b(this.e, cVar, this.o);
                return;
            }
            cYQ_(i, view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cpm cpmVar) {
        oaf.b(this.e).h(cpmVar.a());
        d(cpmVar, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYQ_(int i, View view) {
        cjv cjvVar = this.v.get(i);
        boolean a2 = this.b.a();
        if (cjvVar.a() == 0) {
            LogUtil.a("DeviceMoreListActivity", "DeviceManagerCardNoDeviceValueViewHolder onItemClick postion ", Integer.valueOf(i));
            final dcz dczVar = (dcz) cjvVar.i();
            ContentValues FT_ = cjvVar.FT_();
            final String asString = FT_ != null ? FT_.getAsString("uniqueId") : "";
            final String t = dczVar.t();
            if (TextUtils.isEmpty(t)) {
                t = cjvVar.FT_().getAsString("productId");
                dczVar.n(t);
            }
            if (dczVar.e().size() <= 0) {
                cYY_(i, dczVar, FT_);
                return;
            } else if ("1".equals(dczVar.j()) || BleConstants.BLE_THIRD_DEVICE_H5.equals(dczVar.m().d())) {
                PermissionDialogHelper.Vx_(this, new PermissionDialogHelper.OpenBlueToothAction() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.2
                    @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                    public void onPermissionGranted() {
                        if ("1".equals(dczVar.j())) {
                            LogUtil.a("DeviceMoreListActivity", "deviceConnect onPermissionGranted switchToH5ProIntro");
                            dks.d(DeviceMoreListActivity.this.e, dczVar, t, asString);
                        } else {
                            LogUtil.a("DeviceMoreListActivity", "deviceConnect onPermissionGranted startWebViewActivity");
                            DeviceMoreListActivity.this.c(t, asString, dczVar.m().d());
                        }
                    }

                    @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                    public void onPermissionDenied() {
                        LogUtil.a("DeviceMoreListActivity", "Permission onPermissionDenied");
                    }
                });
                return;
            } else {
                cYT_(dczVar, FT_, asString);
                return;
            }
        }
        if (cjvVar.a() == 1) {
            d(cjvVar, a2);
            return;
        }
        if (cjvVar.a() == 3) {
            startActivity(cww.QP_(cjvVar));
            return;
        }
        if (cjvVar.a() == 4) {
            cYU_(cjvVar, view);
        } else if (cjvVar.a() == 6) {
            new dcp().d(this, cjvVar.FT_().getAsString("mac"));
        } else {
            LogUtil.h("DeviceMoreListActivity", "other device");
        }
    }

    private void cYU_(cjv cjvVar, View view) {
        Bundle bundle = view != null ? ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.create(view, TitleBean.RIGHT_BTN_TYPE_SHARE)).toBundle() : null;
        Intent intent = new Intent();
        intent.putExtra("common_device_name", cjvVar.e());
        intent.setClass(this.e, MoreSportDeviceActivity.class);
        try {
            if (bundle != null) {
                startActivity(intent, bundle);
            } else {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("DeviceMoreListActivity", "MoreSportDeviceActivity ActivityNotFoundException");
        }
    }

    private void cYT_(dcz dczVar, ContentValues contentValues, String str) {
        if ("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(dczVar.t())) {
            cYR_(contentValues);
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setAction("SWITCH_PLUGINDEVICE");
        bundle.putString("arg1", "DeviceInfoList");
        bundle.putString("productId", dczVar.t());
        bundle.putString("uniqueId", str);
        intent.setPackage(ofr.d);
        intent.setClassName(ofr.d, "com.huawei.health.device.ui.DeviceMainActivity");
        bundle.putParcelable("commonDeviceInfo", contentValues);
        intent.putExtras(bundle);
        ogj.cZC_(this.e, intent, "com.huawei.health.device.ui.DeviceMainActivity");
    }

    private void cYR_(ContentValues contentValues) {
        LogUtil.a("DeviceMoreListActivity", "enter doJumpNemoActivity...");
        String asString = contentValues.getAsString("uniqueId");
        if (dij.Vb_(contentValues)) {
            Intent intent = new Intent(this, (Class<?>) DeviceMainActivity.class);
            intent.putExtra("PID_FROM_QRCODE", "ZAA6");
            intent.putExtra("productId", contentValues.getAsString("productId"));
            intent.putExtra("Device_Type", "082");
            intent.putExtra("macAddress", asString);
            startActivity(intent);
        } else {
            LogUtil.a("DeviceMoreListActivity", "enter enmo");
            new dcp().d(this, asString);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setClassName(BaseApplication.getContext().getPackageName(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str));
        intent.putExtra("productId", str);
        dcz d2 = ResourceManager.e().d(str);
        intent.putExtra("uniqueId", str2);
        if (d2 != null) {
            if (d2.n() != null) {
                intent.putExtra("name", dcx.d(str, d2.n().b()));
            }
            if (d2.l() != null) {
                intent.putExtra("deviceType", d2.l().name());
            }
            if (d2.x() != null) {
                intent.putExtra(Constants.KEY_BLE_SCAN_MODE, d2.x().c());
            }
        }
        intent.putExtra("bleIntroductionType", str3);
        startActivity(intent);
    }

    private void cYY_(final int i, final dcz dczVar, final ContentValues contentValues) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        builder.e(R.string.IDS_device_selection_cancel_unbind_device);
        builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: ofy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceMoreListActivity.this.cZd_(contentValues, dczVar, i, view);
            }
        });
        builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: ofu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceMoreListActivity.cYW_(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    public /* synthetic */ void cZd_(ContentValues contentValues, dcz dczVar, int i, View view) {
        if (contentValues == null || dczVar == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        String asString = contentValues.getAsString("uniqueId");
        if (cjx.e().o(asString)) {
            cjx.e().d(dczVar.t(), asString);
            this.v.remove(i);
            this.b.notifyDataSetChanged();
            Intent intent = new Intent();
            intent.setAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
            this.e.sendBroadcast(intent, LocalBroadcast.c);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void cYW_(View view) {
        LogUtil.h("DeviceMoreListActivity", "onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(cjv cjvVar, boolean z) {
        String str;
        LogUtil.a("DeviceMoreListActivity", "onItemClick wear");
        cpm cpmVar = (cpm) cjvVar.i();
        if (z && (str = this.u) != null && !str.equals(cpmVar.a()) && cpmVar.e() != 2) {
            LogUtil.h("DeviceMoreListActivity", "other devices is connection,can not start activity.");
            return;
        }
        if (cpmVar.e() == 2) {
            ogj.c(this.e, cpmVar.a());
        } else if (cpmVar.g()) {
            oau.d(4);
            ogj.d(this.e, cpmVar, cjvVar.j());
        } else {
            ogj.c(this.e, cpmVar.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cpm cpmVar, int i) {
        if (jkj.d().j()) {
            LogUtil.a("DeviceMoreListActivity", "user choose connect other wear device is OTAing");
            d(R.string.IDS_main_device_ota_error_message);
            return;
        }
        final List<DeviceInfo> b = jfv.b();
        e(cpmVar, b);
        String a2 = cpmVar.a();
        final DownloadCloudDeviceResource downloadCloudDeviceResource = new DownloadCloudDeviceResource();
        if (cpmVar.g()) {
            LogUtil.a("DeviceMoreListActivity", "deviceInfoForWear.getIsCloudDevice()");
            obi.a().b(true);
            obi.a().cUG_(cpmVar.i(), cpmVar.d(), this, this.f9392a);
            a2 = cpmVar.i() + com.huawei.openalliance.ad.constant.Constants.LINK + cpmVar.a();
        } else {
            downloadCloudDeviceResource.c();
        }
        final String str = a2;
        final boolean g = cpmVar.g();
        final String c = cpmVar.c();
        this.h.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.5
            @Override // java.lang.Runnable
            public void run() {
                jfv.a(b, str);
                new obq().d(downloadCloudDeviceResource, c, g, str, DeviceMoreListActivity.this.e);
            }
        });
        this.i.sendMessageDelayed(ogj.cZA_(g, cpmVar, i), 20000L);
        LogUtil.a("DeviceMoreListActivity", "device connect time out");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        DeviceInfo deviceInfo;
        LogUtil.a("DeviceMoreListActivity", "gotoConflictDialogActivity enter");
        if (this.e == null) {
            LogUtil.h("DeviceMoreListActivity", "gotoConflictDialogActivity mContext is null");
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h != null) {
            Iterator<DeviceInfo> it = h.iterator();
            while (it.hasNext()) {
                deviceInfo = it.next();
                if (deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("DeviceMoreListActivity", "gotoConflictDialogActivity find connected device");
                    break;
                }
            }
        }
        deviceInfo = null;
        if (deviceInfo == null) {
            LogUtil.h("DeviceMoreListActivity", "gotoConflictDialogActivity connectedDevice is null");
            return;
        }
        String format = String.format(Locale.ROOT, this.e.getString(R.string._2130842682_res_0x7f02143a), c(deviceInfo.getProductType()), c(i));
        Intent intent = new Intent();
        intent.putExtra("content", format);
        intent.setClass(this.e, WorkModeConflictDialogActivity.class);
        ogj.cZC_(this.e, intent, "WorkModeConflictDialogActivity");
    }

    private String c(int i) {
        DeviceInfo d2 = cpl.c().d();
        if (d2 == null) {
            return com.huawei.hms.hihealth.data.DeviceInfo.STR_TYPE_UNKNOWN;
        }
        String str = "PORSCHE DESIGN";
        if ((TextUtils.isEmpty(d2.getDeviceName()) || !"PORSCHE DESIGN".equals(d2.getDeviceName())) && (TextUtils.isEmpty(d2.getDeviceModel()) || !"PORSCHE DESIGN".equals(d2.getDeviceModel()))) {
            str = jfu.c(i).f();
        }
        LogUtil.a("DeviceMoreListActivity", "transDeviceProductTypeIntToStr: mDeviceProductType ", str);
        return str;
    }

    private void e(cpm cpmVar, List<DeviceInfo> list) {
        if (cpmVar == null || list == null) {
            return;
        }
        if (cvt.c(cpmVar.i())) {
            LogUtil.a("DeviceMoreListActivity", "handleWorkMode goingReConnected == AW70");
            for (DeviceInfo deviceInfo : list) {
                if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    LogUtil.a("DeviceMoreListActivity", "Reconnected AW70 set device enable");
                    deviceInfo.setDeviceActiveState(1);
                    deviceInfo.setDeviceConnectState(1);
                } else if (cvt.c(deviceInfo.getProductType())) {
                    LogUtil.a("DeviceMoreListActivity", "Connected AW70 target device disable");
                    deviceInfo.setDeviceActiveState(0);
                    deviceInfo.setDeviceConnectState(3);
                } else {
                    LogUtil.h("DeviceMoreListActivity", "handleDeviceState is other");
                }
            }
            return;
        }
        LogUtil.a("DeviceMoreListActivity", "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
        for (DeviceInfo deviceInfo2 : list) {
            if (cpmVar.a().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                LogUtil.a("DeviceMoreListActivity", "handleWorkMode set device enable");
                deviceInfo2.setDeviceActiveState(1);
                deviceInfo2.setDeviceConnectState(1);
            }
            if (!cpmVar.a().equalsIgnoreCase(deviceInfo2.getDeviceIdentify()) && deviceInfo2.getAutoDetectSwitchStatus() != 1 && !obb.e(cpmVar.i()) && deviceInfo2.getDeviceActiveState() == 1) {
                LogUtil.a("DeviceMoreListActivity", "handleWorkMode target device disable");
                deviceInfo2.setDeviceActiveState(0);
                deviceInfo2.setDeviceConnectState(3);
            }
        }
        cpl.c().f();
    }

    private void d(int i) {
        Context context = this.e;
        if (context == null) {
            LogUtil.h("DeviceMoreListActivity", "showTipDialog mContext is null");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(this.e.getResources().getString(i)).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: ofv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceMoreListActivity.cYX_(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public static /* synthetic */ void cYX_(View view) {
        LogUtil.h("DeviceMoreListActivity", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d() {
        LogUtil.a("DeviceMoreListActivity", "enter initList");
        this.v.clear();
        ExecutorService executorService = this.h;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList(16);
                    ArrayList arrayList2 = new ArrayList(16);
                    DeviceMoreListActivity.this.d((List<cjv>) arrayList, (List<cjv>) arrayList2);
                    arrayList.addAll(cww.b());
                    ArrayList<cjv> c = ogj.c();
                    if (koq.c(c)) {
                        DeviceMoreListActivity.this.d((List<cjv>) arrayList, c);
                        arrayList.addAll(c);
                    }
                    Collections.sort(arrayList2);
                    Collections.sort(arrayList);
                    arrayList.addAll(0, arrayList2);
                    Handler handler = DeviceMoreListActivity.this.i;
                    if (handler == null) {
                        return;
                    }
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.obj = arrayList;
                    obtainMessage.what = 12;
                    handler.sendMessage(obtainMessage);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<cjv> list, ArrayList<cjv> arrayList) {
        Iterator<cjv> it = list.iterator();
        while (it.hasNext()) {
            cjv next = it.next();
            if (next.FT_() != null) {
                String asString = next.FT_().getAsString("mac");
                if (!TextUtils.isEmpty(asString)) {
                    Iterator<cjv> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        cjv next2 = it2.next();
                        if (next2.FT_() != null && asString.equals(next2.FT_().getAsString("uniqueId"))) {
                            it.remove();
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<cjv> list, List<cjv> list2) {
        int i;
        ArrayList<cpm> a2 = jfv.a();
        int d2 = d(a2);
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(16);
        int i2 = 0;
        this.d = 0;
        this.r = false;
        Iterator<cpm> it = a2.iterator();
        while (it.hasNext()) {
            e(it.next(), d2, currentTimeMillis, arrayList);
        }
        Iterator<cpm> it2 = a2.iterator();
        while (it2.hasNext()) {
            cpm next = it2.next();
            cjv cjvVar = new cjv();
            cjvVar.a(1);
            cjvVar.e(1);
            cjvVar.b(next.l());
            cjvVar.c(next);
            if (next.b() == 1 && next.e() == 2 && !obb.e(next.i())) {
                b(currentTimeMillis, d2, next, cjvVar);
                a(list2, cjvVar);
                i = d2;
            } else {
                if (!this.p && next.g() && jfv.c() > 3 && !obb.e(next.i())) {
                    i2++;
                    i = d2;
                    LogUtil.a("DeviceMoreListActivity", "WearDeviceUtils.getCloudDeviceCount():", Integer.valueOf(jfv.c()), "cloudCount:", Integer.valueOf(i2));
                    if (this.d == 1) {
                        if (i2 > 2) {
                            break;
                        }
                    }
                    if (i2 > 3) {
                        break;
                    }
                } else {
                    i = d2;
                }
                a(list, arrayList, next, cjvVar);
            }
            d2 = i;
        }
        LogUtil.a("DeviceMoreListActivity", "has wear deviceInfoForWears : ", a2.toArray());
        d(list, list2, arrayList);
    }

    private void e(cpm cpmVar, int i, long j, List<cjv> list) {
        if (obb.e(cpmVar.i())) {
            cjv cjvVar = new cjv();
            cjvVar.a(1);
            cjvVar.e(1);
            cjvVar.b(cpmVar.l());
            cjvVar.c(cpmVar);
            if (cpmVar.g()) {
                this.d++;
                cjvVar.a(cpmVar.h());
                LogUtil.a("DeviceMoreListActivity", "deviceInfoForWear name: ", cpmVar.d(), "deviceInfoForWear last connected time:", Long.valueOf(cpmVar.h()));
            } else if (cpmVar.b() == 1 && cpmVar.e() == 2) {
                this.m = true;
                b(j, i, cpmVar, cjvVar);
            } else {
                cjvVar.a(cpmVar.h());
                LogUtil.a("DeviceMoreListActivity", "deviceInfoForWear name: ", cpmVar.d(), "deviceInfoForWear last connected time:", Long.valueOf(cpmVar.h()));
            }
            list.add(cjvVar);
        }
    }

    private void d(List<cjv> list, List<cjv> list2, List<cjv> list3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (cjv cjvVar : list3) {
            if (cjvVar.i() instanceof cpm) {
                String d2 = ((cpm) cjvVar.i()).d();
                String substring = d2.contains(com.huawei.openalliance.ad.constant.Constants.LINK) ? d2.substring(0, d2.lastIndexOf(com.huawei.openalliance.ad.constant.Constants.LINK)) : "";
                if (linkedHashMap.containsKey(substring)) {
                    ((List) linkedHashMap.get(substring)).add(cjvVar);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(cjvVar);
                    linkedHashMap.put(substring, arrayList);
                }
            }
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            cjv cjvVar2 = new cjv();
            List<cjv> list4 = (List) entry.getValue();
            if (koq.c(list4)) {
                Collections.sort(list4);
                cjv cjvVar3 = list4.get(0);
                if (list4.size() == 1) {
                    this.r = true;
                    cjvVar2 = cjvVar3;
                } else {
                    this.r = false;
                    this.m = false;
                    cjvVar2.a(4);
                    cjvVar2.a(cjvVar3.d());
                    cjvVar2.c((String) entry.getKey());
                    cjvVar2.e(list4);
                }
                if (cjvVar3.i() instanceof cpm) {
                    d(list, list2, cjvVar3, cjvVar2);
                }
            }
        }
        b();
    }

    private void d(List<cjv> list, List<cjv> list2, cjv cjvVar, cjv cjvVar2) {
        if (((cpm) cjvVar.i()).e() == 2) {
            ogj.a(list2, cjvVar2);
        } else {
            list.add(cjvVar2);
        }
    }

    private void a(List<cjv> list, List<cjv> list2, cpm cpmVar, cjv cjvVar) {
        cjvVar.a(cpmVar.h());
        LogUtil.a("DeviceMoreListActivity", "deviceInfoForWear name: ", cpmVar.d(), "deviceInfoForWear last connected time:", Long.valueOf(cpmVar.h()));
        if (obb.e(cpmVar.i())) {
            return;
        }
        list.add(cjvVar);
    }

    private void b(long j, int i, cpm cpmVar, cjv cjvVar) {
        if (i > 1) {
            cjvVar.a(this.ad.get(cpmVar.a()).intValue() + j);
            LogUtil.a("DeviceMoreListActivity", "DEFAULT_CONNECTED_DEVICE_NUMBER : ", cpmVar.d(), this.ad.get(cpmVar.a()));
        } else {
            cjvVar.a(j);
        }
        LogUtil.a("DeviceMoreListActivity", "deviceInfoForWear name: ", cpmVar.d(), "deviceInfoForWear current connected time:", Long.valueOf(j), ",compareTime:", Long.valueOf(cjvVar.d()));
    }

    private void a(List<cjv> list, cjv cjvVar) {
        if (!list.isEmpty()) {
            Object i = list.get(0).i();
            if (i instanceof cpm) {
                if (((cpm) i).e() == 2) {
                    LogUtil.a("DeviceMoreListActivity", "add to normal");
                    b(list, cjvVar);
                    return;
                } else {
                    LogUtil.a("DeviceMoreListActivity", "add to first");
                    list.add(0, cjvVar);
                    return;
                }
            }
            return;
        }
        list.add(cjvVar);
    }

    private void b(List<cjv> list, cjv cjvVar) {
        if (list.size() > 1) {
            list.add(1, cjvVar);
        } else {
            list.add(cjvVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        String str;
        for (cjv cjvVar : this.v) {
            if (cjvVar.i() instanceof cpm) {
                cpm cpmVar = (cpm) cjvVar.i();
                CardDeviceAdapter cardDeviceAdapter = this.b;
                if (cardDeviceAdapter != null && cardDeviceAdapter.a() && (str = this.u) != null && str.equals(cpmVar.a())) {
                    cpmVar.a(1);
                }
            }
        }
        k();
    }

    private void k() {
        int i;
        this.x.clear();
        if (nsn.ag(this.e)) {
            if (this.v.size() == 1) {
                this.x.addAll(this.v);
                cjv cjvVar = new cjv();
                cjvVar.a(2);
                this.x.add(cjvVar);
            } else {
                this.x.addAll(this.v);
            }
        } else {
            this.x.addAll(this.v);
        }
        this.b.c(this.x);
        if (nsn.ag(this.e)) {
            if (this.w.getItemDecorationCount() > 0 && this.w.getItemDecorationAt(0) != null) {
                this.w.removeItemDecorationAt(0);
            }
            this.w.addItemDecoration(new DeviceItemDecoration(nrr.e(this.e, 12.0f), 0));
            i = 2;
        } else {
            i = 1;
        }
        this.w.setLayoutManager(new GridLayoutManager(this.e, i, 1, false) { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        boolean z = this.d == 1 && this.r && jfv.c() - this.d > 2;
        boolean z2 = jfv.c() - this.d > 3;
        if ((z || z2) && !this.p) {
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYZ_(DeviceInfo deviceInfo, Handler handler) {
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = deviceInfo.getDeviceIdentify();
        LogUtil.a("DeviceMoreListActivity", "deviceInfo.getDeviceConnectState():", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        if (deviceInfo.getDeviceConnectState() == 2) {
            obtainMessage.what = 30;
            handler.sendMessage(obtainMessage);
            d(deviceInfo);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 3 || deviceInfo.getDeviceConnectState() == 4) {
            obtainMessage.what = 32;
            obtainMessage.arg1 = deviceInfo.getProductType();
            handler.sendMessage(obtainMessage);
            cpl.c().f(-1);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 1 || deviceInfo.getDeviceConnectState() == 9 || deviceInfo.getDeviceConnectState() == 10) {
            obtainMessage.what = 33;
            obtainMessage.arg1 = deviceInfo.getDeviceConnectState();
            handler.sendMessage(obtainMessage);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 6 || deviceInfo.getDeviceConnectState() == 5) {
            handler.removeMessages(36);
            Message obtain = Message.obtain();
            obtain.arg1 = deviceInfo.getProductType();
            obtain.what = 36;
            obtain.obj = obtainMessage.obj;
            handler.sendMessage(obtain);
            if (deviceInfo.getDeviceConnectState() == 5) {
                r();
                return;
            }
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 11) {
            b(deviceInfo);
        } else if (deviceInfo.getDeviceConnectState() == 12) {
            a(deviceInfo);
        } else {
            LogUtil.h("DeviceMoreListActivity", "mNonLocalBroadcastReceiver() other state");
        }
    }

    private void b(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceFactoryReset() == 1) {
            if (AgreementDeclarationActivity.b || BasePairManagerJsApi.isOpen) {
                LogUtil.h("DeviceMoreListActivity", "oobe already is open");
                return;
            }
            Intent intent = new Intent();
            intent.setClass(this.e, AgreementDeclarationActivity.class);
            intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
            Bundle bundle = new Bundle();
            bundle.putParcelable("deviceInfo", deviceInfo);
            intent.putExtras(bundle);
            intent.putExtra("device_country_code", deviceInfo.getCountryCode());
            intent.putExtra("device_emui_version", deviceInfo.getEmuiVersion());
            ogj.cZC_(this.e, intent, "AgreementDeclarationActivity");
        }
    }

    private void a(DeviceInfo deviceInfo) {
        c(deviceInfo);
    }

    private void c(DeviceInfo deviceInfo) {
        if (!CommonUtil.h(this.e, "com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity")) {
            LogUtil.h("DeviceMoreListActivity", "activity is not isForeground");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.c;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.c.cancel();
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(String.format(Locale.ENGLISH, getString(R.string._2130845618_res_0x7f021fb2), deviceInfo.getDeviceName())).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DeviceMoreListActivity", "showKidAccountNotSupportPairDialog, click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.c = e2;
        e2.setCancelable(false);
        if (this.c.isShowing() || isFinishing()) {
            return;
        }
        this.c.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        UserLabelServiceApi userLabelServiceApi = this.aa;
        if (userLabelServiceApi != null) {
            userLabelServiceApi.unRegisterCallback(4);
            this.aa.unRegisterCallback(5);
        }
        this.e = null;
        if (CommonUtil.ce()) {
            q();
            v();
            x();
        }
    }

    private void d(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 112);
        LogUtil.a("DeviceMoreListActivity", "afterConnectedProcess. isSupportEarPhone: ", Boolean.valueOf(c));
        if (c && CommonUtil.h(this.e, "com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity")) {
            LogUtil.a("DeviceMoreListActivity", "afterConnectedProcess. in");
            jgs.c().c(deviceInfo, new AnonymousClass9(deviceInfo));
        }
    }

    /* renamed from: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements IBaseResponseCallback {
        final /* synthetic */ DeviceInfo c;

        AnonymousClass9(DeviceInfo deviceInfo) {
            this.c = deviceInfo;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            DeviceMoreListActivity deviceMoreListActivity = DeviceMoreListActivity.this;
            final DeviceInfo deviceInfo = this.c;
            deviceMoreListActivity.runOnUiThread(new Runnable() { // from class: oga
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceMoreListActivity.AnonymousClass9.this.b(deviceInfo);
                }
            });
        }

        public /* synthetic */ void b(DeviceInfo deviceInfo) {
            Intent intent = new Intent();
            intent.setClass(DeviceMoreListActivity.this.e, EarphonePairActivity.class);
            intent.putExtra("device_identify", deviceInfo.getDeviceIdentify());
            DeviceMoreListActivity.this.startActivity(intent);
        }
    }

    private void x() {
        obi.a().b();
        try {
            if (this.q != null) {
                LogUtil.a("DeviceMoreListActivity", "mPairBroadcastReceiver mReceiver != null");
                LocalBroadcastManager.getInstance(this.e).unregisterReceiver(this.q);
                this.q = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceMoreListActivity", "unregisterPairBroadcastReceiver is error");
        }
    }

    private int d(ArrayList<cpm> arrayList) {
        ArrayList arrayList2 = new ArrayList(16);
        Iterator<cpm> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            cpm next = it.next();
            if (next.b() == 1 && next.e() == 2) {
                i++;
                cjv cjvVar = new cjv();
                cjvVar.c(next);
                cjvVar.a(next.h());
                arrayList2.add(cjvVar);
            }
        }
        if (i > 1) {
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((cjv) arrayList2.get(i2)).i() instanceof cpm) {
                    cpm cpmVar = (cpm) ((cjv) arrayList2.get(i2)).i();
                    LogUtil.c("DeviceMoreListActivity", "getConnectedDevices connectedDevice name:", cpmVar.d());
                    this.ad.put(cpmVar.a(), Integer.valueOf((size - i2) * 10));
                }
            }
        }
        LogUtil.a("DeviceMoreListActivity", "getConnectedDevices connectedCount:", Integer.valueOf(i));
        return i;
    }

    private void e() {
        LogUtil.a("DeviceMoreListActivity", "enter checkEnableDevice");
        ExecutorService executorService = this.h;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfo deviceInfo;
                    if (iyl.d().g() != 3) {
                        LogUtil.h("DeviceMoreListActivity", "checkEnableDevice bluetooth off");
                        return;
                    }
                    List<DeviceInfo> h = cpl.c().h();
                    if (h == null || h.size() <= 0) {
                        LogUtil.h("DeviceMoreListActivity", "checkEnableDevice return");
                        return;
                    }
                    Iterator<cpm> it = jfv.a().iterator();
                    int i = 0;
                    while (it.hasNext() && (!obb.e(it.next().i()) || (i = i + 1) <= 1)) {
                    }
                    Iterator<DeviceInfo> it2 = h.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            deviceInfo = null;
                            break;
                        }
                        deviceInfo = it2.next();
                        if (i != 2 || !obb.e(deviceInfo.getProductType())) {
                            if (deviceInfo.getDeviceBluetoothType() == 2) {
                                if (deviceInfo.getDeviceActiveState() == 1) {
                                    if (deviceInfo.getDeviceConnectState() == 3 || deviceInfo.getDeviceConnectState() == 4) {
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    DeviceMoreListActivity.this.d(deviceInfo, h);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        if (deviceInfo != null) {
            LogUtil.a("DeviceMoreListActivity", "reconnect active device ", deviceInfo);
            deviceInfo.setDeviceConnectState(1);
            oxa.a().e(list, deviceInfo.getDeviceIdentify());
            c(deviceInfo.getDeviceIdentify());
            this.b.a(true);
            this.n = false;
            Handler handler = this.i;
            if (handler == null) {
                return;
            }
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 45;
            handler.sendMessage(obtainMessage);
            handler.sendMessageDelayed(ogj.cZB_(0, deviceInfo.getProductType()), 20000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(this.e).e(arrayList, true);
    }

    private void g() {
        ExecutorService executorService = this.h;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    if (DeviceMoreListActivity.this.k) {
                        return;
                    }
                    List<DeviceInfo> h = cpl.c().h();
                    if (h == null || h.isEmpty()) {
                        LogUtil.h("DeviceMoreListActivity", "enter applyLocationPermission deviceList is null");
                        return;
                    }
                    for (DeviceInfo deviceInfo : h) {
                        if (deviceInfo != null && deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() == 2) {
                            DeviceMoreListActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity.7.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    DeviceMoreListActivity.this.c();
                                    DeviceMoreListActivity.this.k = true;
                                }
                            });
                            return;
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("DeviceMoreListActivity", "in add Location Permissions.");
        String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        if (jdi.c(this.e, strArr)) {
            return;
        }
        jdi.bFL_(this, strArr, null);
        CommonUtil.k(this.e, "android.permission.ACCESS_COARSE_LOCATION");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == 2) {
            nue.cNU_(intent, this, nue.e(i2, true, intent.getIntExtra("product_type", -1), true));
        }
    }

    private void p() {
        ogj.cZC_(this.e, new Intent(this.e, (Class<?>) OneKeyScanActivity.class), "OneKeyScanActivity");
        o();
    }

    /* loaded from: classes6.dex */
    static class d implements WearPlaceCallback {
        private WeakReference<DeviceMoreListActivity> b;

        d(DeviceMoreListActivity deviceMoreListActivity) {
            this.b = new WeakReference<>(deviceMoreListActivity);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback
        public void onResponse(DeviceInfo deviceInfo, int i) {
            DeviceMoreListActivity deviceMoreListActivity = this.b.get();
            if (deviceInfo != null) {
                Handler handler = deviceMoreListActivity.i;
                if (handler == null) {
                    return;
                }
                handler.sendEmptyMessage(34);
                return;
            }
            LogUtil.h("DeviceMoreListActivity", "WearHomePlaceCallback enter deviceInfo = null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
