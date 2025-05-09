package com.huawei.ui.homehealth.device.devicelist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.device.utlis.clouddevice.DownloadCloudDeviceResource;
import com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter;
import com.huawei.ui.homehealth.device.callback.ReconnectCallback;
import com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity;
import com.huawei.ui.homehealth.device.view.DeviceItemDecoration;
import com.huawei.ui.homehealth.view.CloudReconnectFailDialog;
import com.huawei.ui.main.stories.userprofile.activity.WorkModeConflictDialogActivity;
import defpackage.cjv;
import defpackage.cpl;
import defpackage.cpm;
import defpackage.cvc;
import defpackage.iyl;
import defpackage.jah;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jgi;
import defpackage.jkj;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oad;
import defpackage.oaf;
import defpackage.obb;
import defpackage.obi;
import defpackage.obq;
import defpackage.ogj;
import defpackage.oxa;
import defpackage.pep;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class MoreSportDeviceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9401a;
    protected ActivityResultLauncher<IntentSenderRequest> b;
    private HealthTextView c;
    private CardDeviceAdapter d;
    private Context g;
    private HealthTextView i;
    private HealthTextView l;
    private HealthButton q;
    private String t;
    private HealthRecycleView v;
    private List<cjv> s = new ArrayList(16);
    private Handler k = new a(this);
    private CustomTextAlertDialog o = null;
    private String f = "";
    private boolean m = false;
    private NoTitleCustomAlertDialog h = null;
    private String e = "";
    private boolean r = false;
    private NoTitleCustomAlertDialog j = null;
    private final BroadcastReceiver p = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler;
            DeviceInfo deviceInfo;
            if (context == null || intent == null || (handler = MoreSportDeviceActivity.this.k) == null) {
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || "com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                try {
                    if (!(intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                        return;
                    }
                    MoreSportDeviceActivity.this.cZn_(deviceInfo, handler);
                    return;
                } catch (ClassCastException unused) {
                    LogUtil.b("MoreSportDeviceActivity", "DeviceInfo deviceInfo error ClassCastException");
                    return;
                }
            }
            if ("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS".equals(intent.getAction())) {
                MoreSportDeviceActivity.this.c(false);
            } else {
                LogUtil.h("MoreSportDeviceActivity", "mNonLocalBroadcastReceiver()  intent : ", intent.getAction());
            }
        }
    };
    private final BroadcastReceiver n = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler = MoreSportDeviceActivity.this.k;
            if (handler == null || intent == null || !"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || intent.getExtras() == null) {
                return;
            }
            handler.sendEmptyMessage(34);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        LogUtil.a("MoreSportDeviceActivity", "onCreate()");
        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable._2131430882_res_0x7f0b0de2));
        getWindow().setLayout(-1, -1);
        setContentView(R.layout.more_sport_device_layout);
        this.g = this;
        e();
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("MoreSportDeviceActivity", "intent == null");
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("common_device_name");
        this.f = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.h("MoreSportDeviceActivity", "TextUtils.isEmpty(mCommonName)");
            finish();
        } else {
            b();
        }
    }

    private void e() {
        this.b = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MoreSportDeviceActivity.this.c((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void c(ActivityResult activityResult) {
        obi.a().e(String.valueOf(activityResult.getResultCode()));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            obi.a().c();
        } else {
            obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == -1) {
                        obi.a().b(MoreSportDeviceActivity.this.g);
                    } else {
                        obi.a().c();
                    }
                }
            }, this.g);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LogUtil.a("MoreSportDeviceActivity", "onStart");
        if (CommonUtil.ce()) {
            h();
            f();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.m = false;
        n();
        c(true);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.8
            @Override // java.lang.Runnable
            public void run() {
                oxa.a().d();
                jgi.e().b(new d(MoreSportDeviceActivity.this), "MoreSportDeviceActivity");
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void h() {
        LogUtil.a("MoreSportDeviceActivity", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        intentFilter.addAction("broadcast_receiver_user_setting");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.p, intentFilter, LocalBroadcast.c, null);
    }

    private void k() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.p);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("MoreSportDeviceActivity", "unRegisterNonLocalBroadcastReceiver IllegalArgumentException");
        }
    }

    private void f() {
        LogUtil.a("MoreSportDeviceActivity", "enter registerBatteryBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.n, intentFilter, LocalBroadcast.c, null);
    }

    private void l() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.n);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("MoreSportDeviceActivity", "unRegisterBatteryBroadcast IllegalArgumentException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cZn_(DeviceInfo deviceInfo, Handler handler) {
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = deviceInfo.getDeviceIdentify();
        LogUtil.a("MoreSportDeviceActivity", "deviceInfo.getDeviceConnectState():", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        if (deviceInfo.getDeviceConnectState() == 2) {
            obtainMessage.what = 30;
            handler.sendMessage(obtainMessage);
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
                o();
                return;
            }
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 11) {
            if (deviceInfo.getDeviceFactoryReset() == 1 && CommonUtil.h(this.g, "com.huawei.health.MainActivity")) {
                Intent intent = new Intent();
                intent.setClass(this.g, AgreementDeclarationActivity.class);
                intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
                Bundle bundle = new Bundle();
                bundle.putParcelable("deviceInfo", deviceInfo);
                intent.putExtras(bundle);
                intent.putExtra("device_country_code", deviceInfo.getCountryCode());
                intent.putExtra("device_emui_version", deviceInfo.getEmuiVersion());
                ogj.cZC_(this.g, intent, "AgreementDeclarationActivity");
                return;
            }
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 12) {
            e(deviceInfo);
        } else {
            LogUtil.h("MoreSportDeviceActivity", "mNonLocalBroadcastReceiver() other state");
        }
    }

    private void e(DeviceInfo deviceInfo) {
        c(deviceInfo);
    }

    private void c(DeviceInfo deviceInfo) {
        if (!CommonUtil.h(this.g, "com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity")) {
            LogUtil.h("MoreSportDeviceActivity", "activity is not foreground");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.j;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.j.cancel();
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(String.format(Locale.ENGLISH, getString(R.string._2130845618_res_0x7f021fb2), deviceInfo.getDeviceName())).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("MoreSportDeviceActivity", "showKidAccountNotSupportPairDialog, click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.j = e;
        e.setCancelable(false);
        if (this.j.isShowing() || isFinishing()) {
            return;
        }
        this.j.show();
    }

    private void o() {
        LogUtil.a("MoreSportDeviceActivity", "showBandUnavailableDialog");
        if (CommonUtil.h(this.g, "com.huawei.health.MainActivity")) {
            CustomTextAlertDialog customTextAlertDialog = this.o;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                LogUtil.h("MoreSportDeviceActivity", "showBandUnavailableDialog Already show!");
                return;
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.g).b(R.string.IDS_service_area_notice_title).e(this.g.getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: oge
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoreSportDeviceActivity.cZl_(view);
                }
            }).a();
            this.o = a2;
            a2.setCancelable(false);
            if (this.o.isShowing() || isFinishing()) {
                return;
            }
            this.o.show();
        }
    }

    public static /* synthetic */ void cZl_(View view) {
        LogUtil.h("MoreSportDeviceActivity", "view onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        this.l = (HealthTextView) nsy.cMc_(this, R.id.empty_tv);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.common_name_tv);
        this.v = (HealthRecycleView) nsy.cMc_(this, R.id.sport_list);
        this.f9401a = (RelativeLayout) nsy.cMc_(this, R.id.more_sport_layout);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.awaken_tv);
        this.q = (HealthButton) nsy.cMc_(this, R.id.reconnect_all_device_button);
        this.f9401a.setOnClickListener(new View.OnClickListener() { // from class: ogg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSportDeviceActivity.this.cZo_(view);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() { // from class: ogi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSportDeviceActivity.this.cZp_(view);
            }
        });
        this.i.setText(this.f);
        m();
    }

    public /* synthetic */ void cZo_(View view) {
        onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cZp_(View view) {
        this.r = true;
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if (!oad.d(this.g)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("MoreSportDeviceActivity", "getHarmonyStatus getValue");
                    String e = jah.c().e("scale_share_harmony_tips");
                    DevicePairGuideUtil.e(e);
                    LogUtil.a("MoreSportDeviceActivity", "getHarmonyStatus scale_share_harmony_tips: ", e);
                    MoreSportDeviceActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String string;
                            if ("on".equals(DevicePairGuideUtil.d())) {
                                string = BaseApplication.getContext().getString(R.string._2130843755_res_0x7f02186b);
                            } else {
                                string = BaseApplication.getContext().getString(R.string._2130843258_res_0x7f02167a);
                            }
                            pep.d(MoreSportDeviceActivity.this.g, string);
                        }
                    });
                }
            });
        } else {
            a();
        }
    }

    private void a() {
        if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(this.g, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.g) { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.6
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("MoreSportDeviceActivity", "checkBluetoothPermission nearby permission granted");
                    MoreSportDeviceActivity.this.i();
                }
            });
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (iyl.d().g() != 3) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.g).e(this.g.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: ogf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoreSportDeviceActivity.this.cZq_(view);
                }
            }).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: ogh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoreSportDeviceActivity.this.cZr_(view);
                }
            }).e();
            this.h = e;
            e.setCancelable(false);
            this.h.show();
            return;
        }
        this.m = false;
        n();
        c(true);
    }

    public /* synthetic */ void cZq_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.h;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.h = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cZr_(View view) {
        j();
        this.m = false;
        n();
        c(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j() {
        startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1002);
    }

    private void m() {
        if (this.s.size() > 6) {
            this.l.setVisibility(4);
        } else {
            this.l.setVisibility(8);
        }
        CardDeviceAdapter cardDeviceAdapter = new CardDeviceAdapter(this.g, this, new CardDeviceAdapter.PersonalItemReconnectListener() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.14
            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onReconnect(cpm cpmVar) {
                LogUtil.a("MoreSportDeviceActivity", "setRecyclerView onReconnect");
                MoreSportDeviceActivity.this.e(cpmVar);
            }

            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onPersonalEquipment(int i, View view) {
                LogUtil.a("MoreSportDeviceActivity", "setRecyclerView onPersonalEquipment");
                MoreSportDeviceActivity.this.e(i);
            }

            @Override // com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.PersonalItemReconnectListener
            public void onMoreEquipment() {
                LogUtil.a("MoreSportDeviceActivity", "onMoreEquipment");
            }
        });
        this.d = cardDeviceAdapter;
        cardDeviceAdapter.c(this.s);
        this.v.setAdapter(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (!nsn.o() && i >= 0 && i < this.s.size()) {
            c(i);
        }
    }

    private void c(int i) {
        c(this.s.get(i), this.d.a());
    }

    private void c(cjv cjvVar, boolean z) {
        String str;
        LogUtil.a("MoreSportDeviceActivity", "onItemClick wear");
        cpm cpmVar = (cpm) cjvVar.i();
        if (z && (str = this.t) != null && !str.equals(cpmVar.a()) && cpmVar.e() != 2) {
            LogUtil.h("MoreSportDeviceActivity", "other devices is connection,can not start activity.");
            return;
        }
        if (cpmVar.e() == 2) {
            a(cpmVar.a());
        } else if (cpmVar.g()) {
            b(cpmVar);
        } else {
            a(cpmVar.a());
        }
    }

    private void b(cpm cpmVar) {
        Intent intent = new Intent();
        intent.setClassName(this.g, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        intent.putExtra("is_cloud_device", cpmVar.g());
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, cpmVar.d());
        intent.putExtra("device_identify", cpmVar.a());
        intent.putExtra("device_picID", cpmVar.m());
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, cpmVar.i());
        ogj.cZC_(this.g, intent, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        pep.d(this.g);
        LogUtil.a("MoreSportDeviceActivity", "onclick wear not connected name:", cpmVar.d(), "device_type :", Integer.valueOf(cpmVar.i()));
        finish();
    }

    private void a(String str) {
        LogUtil.a("MoreSportDeviceActivity", "Enter openWearHome");
        if (jkj.d().c(str) == 6) {
            LogUtil.a("MoreSportDeviceActivity", "wear device is OTAing");
            Intent intent = new Intent();
            intent.setClassName(this.g, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
            intent.putExtra("device_id", str);
            ogj.cZC_(this.g, intent, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
        } else {
            SharedPreferenceManager.e(this.g, String.valueOf(10099), "key_ui_nps_enter_wear_home", "true", (StorageParams) null);
            Intent intent2 = new Intent();
            intent2.setClassName(this.g, "com.huawei.ui.homewear21.home.WearHomeActivity");
            intent2.putExtra("device_id", str);
            ogj.cZC_(this.g, intent2, "com.huawei.ui.homewear21.home.WearHomeActivity");
            pep.d(this.g);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(cpm cpmVar) {
        oaf.b(this.g).h(cpmVar.a());
        c(cpmVar, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(cpm cpmVar, int i) {
        if (jkj.d().j()) {
            LogUtil.a("MoreSportDeviceActivity", "user choose connect other wear device is OTAing");
            d(R.string.IDS_main_device_ota_error_message);
            return;
        }
        final List<DeviceInfo> b = jfv.b();
        e(cpmVar, b);
        String a2 = cpmVar.a();
        final DownloadCloudDeviceResource downloadCloudDeviceResource = new DownloadCloudDeviceResource();
        if (cpmVar.g()) {
            LogUtil.a("MoreSportDeviceActivity", "deviceInfoForWear.getIsCloudDevice()");
            obi.a().cUG_(cpmVar.i(), cpmVar.d(), this, this.b);
            a2 = cpmVar.i() + Constants.LINK + cpmVar.a();
        } else {
            downloadCloudDeviceResource.c();
        }
        final String str = a2;
        final boolean g = cpmVar.g();
        final String c = cpmVar.c();
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.13
            @Override // java.lang.Runnable
            public void run() {
                jfv.a(b, str);
                new obq().d(downloadCloudDeviceResource, c, g, str, MoreSportDeviceActivity.this.g);
            }
        });
        this.k.removeMessages(31);
        this.k.sendMessageDelayed(ogj.cZA_(g, cpmVar, i), 20000L);
        LogUtil.a("MoreSportDeviceActivity", "device connect time out");
    }

    private void d(int i) {
        if (this.g == null) {
            LogUtil.h("MoreSportDeviceActivity", "showTipDialog mContext is null");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.g).e(this.g.getResources().getString(i)).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: ogb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSportDeviceActivity.cZm_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static /* synthetic */ void cZm_(View view) {
        LogUtil.h("MoreSportDeviceActivity", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(cpm cpmVar, List<DeviceInfo> list) {
        if (cpmVar == null || list == null) {
            return;
        }
        LogUtil.a("MoreSportDeviceActivity", "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
        for (DeviceInfo deviceInfo : list) {
            if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.a("MoreSportDeviceActivity", "handleWorkMode set device enable");
                deviceInfo.setDeviceActiveState(1);
                deviceInfo.setDeviceConnectState(1);
            }
        }
        cpl.c().f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        g();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.a("MoreSportDeviceActivity", "onStop");
        if (CommonUtil.ce()) {
            l();
            k();
        }
        Handler handler = this.k;
        if (handler != null) {
            handler.removeMessages(31);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002) {
            LogUtil.a("MoreSportDeviceActivity", "onActivityResult BLUETOOTH_CODE");
        }
    }

    public void c(final boolean z) {
        LogUtil.a("MoreSportDeviceActivity", "enter initList");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.5
            @Override // java.lang.Runnable
            public void run() {
                MoreSportDeviceActivity.this.b(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        LogUtil.a("MoreSportDeviceActivity", "enter initSportList");
        ArrayList<cpm> a2 = jfv.a();
        ArrayList arrayList = new ArrayList(16);
        Iterator<cpm> it = a2.iterator();
        int i = -1;
        while (it.hasNext()) {
            cpm next = it.next();
            String d2 = next.d();
            String substring = d2.contains(Constants.LINK) ? d2.substring(0, d2.lastIndexOf(Constants.LINK)) : "";
            LogUtil.a("MoreSportDeviceActivity", "initDeviceList deviceName:", d2);
            if (obb.e(next.i()) && TextUtils.equals(substring, this.f)) {
                i = next.i();
                if (TextUtils.isEmpty(this.e)) {
                    e(jfu.j(next.i()));
                }
                cjv cjvVar = new cjv();
                cjvVar.a(1);
                cjvVar.e(2);
                cjvVar.c(next);
                cjvVar.b(next.l());
                cjvVar.a(next.h());
                arrayList.add(cjvVar);
            }
        }
        LogUtil.a("MoreSportDeviceActivity", "has wear sportDeviceList.size:", Integer.valueOf(arrayList.size()), "; sportDeviceList:", arrayList);
        Collections.sort(arrayList);
        Handler handler = this.k;
        if (handler == null) {
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = arrayList;
        obtainMessage.what = 12;
        obtainMessage.arg1 = !z ? 1 : 0;
        handler.sendMessage(obtainMessage);
        if (z) {
            handler.sendMessageDelayed(ogj.cZB_(0, i), 20000L);
        }
    }

    private void e(String str) {
        LogUtil.a("MoreSportDeviceActivity", "getConnectTip");
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            return;
        }
        String c = pluginInfoByUuid.f().c();
        this.e = c;
        LogUtil.a("MoreSportDeviceActivity", "mAwakenDevice = ", c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        return this.t;
    }

    public void d(String str) {
        this.t = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        DeviceInfo deviceInfo;
        LogUtil.a("MoreSportDeviceActivity", "gotoConflictDialogActivity enter");
        if (this.g == null) {
            LogUtil.h("MoreSportDeviceActivity", "gotoConflictDialogActivity mContext is null");
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h != null) {
            Iterator<DeviceInfo> it = h.iterator();
            while (it.hasNext()) {
                deviceInfo = it.next();
                if (deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("MoreSportDeviceActivity", "gotoConflictDialogActivity find connected device");
                    break;
                }
            }
        }
        deviceInfo = null;
        if (deviceInfo == null) {
            LogUtil.h("MoreSportDeviceActivity", "gotoConflictDialogActivity connectedDevice is null");
            return;
        }
        String format = String.format(Locale.ENGLISH, this.g.getString(R.string._2130842682_res_0x7f02143a), f(deviceInfo.getProductType()), f(i));
        Intent intent = new Intent();
        intent.putExtra("content", format);
        intent.setClass(this.g, WorkModeConflictDialogActivity.class);
        ogj.cZC_(this.g, intent, "WorkModeConflictDialogActivity");
    }

    private String f(int i) {
        DeviceInfo d2 = cpl.c().d();
        if (d2 == null) {
            return com.huawei.hms.hihealth.data.DeviceInfo.STR_TYPE_UNKNOWN;
        }
        String str = "PORSCHE DESIGN";
        if ((TextUtils.isEmpty(d2.getDeviceName()) || !"PORSCHE DESIGN".equals(d2.getDeviceName())) && (TextUtils.isEmpty(d2.getDeviceModel()) || !"PORSCHE DESIGN".equals(d2.getDeviceModel()))) {
            str = jfu.c(i).f();
        }
        LogUtil.a("MoreSportDeviceActivity", "transDeviceProductTypeIntToStr: mDeviceProductType ", str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006b, code lost:
    
        r7.m = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(int r8) {
        /*
            r7 = this;
            r0 = 0
            r7.m = r0
            java.util.List<cjv> r1 = r7.s
            java.util.Iterator r1 = r1.iterator()
        L9:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L7a
            java.lang.Object r2 = r1.next()
            cjv r2 = (defpackage.cjv) r2
            java.lang.Object r3 = r2.i()
            boolean r3 = r3 instanceof defpackage.cpm
            if (r3 == 0) goto L9
            java.lang.Object r2 = r2.i()
            cpm r2 = (defpackage.cpm) r2
            int r3 = r2.e()
            r4 = 2
            r5 = 1
            if (r3 == r4) goto L39
            int r3 = r2.e()
            if (r3 == r5) goto L39
            boolean r3 = r2.g()
            if (r3 != 0) goto L39
            r3 = r5
            goto L3a
        L39:
            r3 = r0
        L3a:
            iyl r4 = defpackage.iyl.d()
            int r4 = r4.g()
            if (r8 != 0) goto L4d
            r6 = 3
            if (r4 != r6) goto L4d
            if (r3 == 0) goto L4d
            r7.c(r2)
            goto L9
        L4d:
            com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter r4 = r7.d
            if (r4 == 0) goto L69
            boolean r4 = r4.a()
            if (r4 == 0) goto L69
            java.lang.String r4 = r7.t
            if (r4 == 0) goto L69
            java.lang.String r6 = r2.a()
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L69
            r2.a(r5)
            goto L9
        L69:
            if (r3 == 0) goto L6e
            r7.m = r5
            goto L9
        L6e:
            java.lang.String r2 = "setDeviceList other mode"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "MoreSportDeviceActivity"
            com.huawei.hwlogsmodel.LogUtil.h(r3, r2)
            goto L9
        L7a:
            r7.r = r0
            r7.g()
            com.huawei.ui.commonui.healthtextview.HealthTextView r8 = r7.c
            java.lang.String r0 = r7.e
            r8.setText(r0)
            r7.n()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.b(int):void");
    }

    private void c(final cpm cpmVar) {
        this.m = false;
        final List<DeviceInfo> h = cpl.c().h();
        cpmVar.a(1);
        e(cpmVar, h);
        if (this.r) {
            final DownloadCloudDeviceResource downloadCloudDeviceResource = new DownloadCloudDeviceResource();
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    jfv.a(h, cpmVar.a());
                    new obq().d(downloadCloudDeviceResource, cpmVar.c(), false, cpmVar.a(), MoreSportDeviceActivity.this.g);
                }
            });
        } else {
            oxa.a().e(h, cpmVar.a());
        }
    }

    private void n() {
        this.q.setClickable(this.m);
        this.q.setEnabled(this.m);
        if (this.m) {
            this.q.setOnClickListener(new View.OnClickListener() { // from class: ogc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoreSportDeviceActivity.this.cZs_(view);
                }
            });
        } else {
            this.q.setOnClickListener(null);
        }
    }

    public /* synthetic */ void cZs_(View view) {
        this.r = true;
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        if (this.s.size() > 6) {
            this.l.setVisibility(4);
        } else {
            this.l.setVisibility(8);
        }
        this.d.c(this.s);
        if (this.v.getItemDecorationCount() > 0 && this.v.getItemDecorationAt(0) != null) {
            this.v.removeItemDecorationAt(0);
        }
        this.v.addItemDecoration(new DeviceItemDecoration(nrr.e(this.g, 12.0f), 0));
        this.v.setLayoutManager(new GridLayoutManager(this.g, 2, 1, false) { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    static class a extends BaseHandler<MoreSportDeviceActivity> {
        a(MoreSportDeviceActivity moreSportDeviceActivity) {
            super(moreSportDeviceActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cZw_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MoreSportDeviceActivity moreSportDeviceActivity, Message message) {
            switch (message.what) {
                case 30:
                    LogUtil.a("MoreSportDeviceActivity", "connect device", Integer.valueOf(message.what));
                    if (message.obj instanceof String) {
                        String str = (String) message.obj;
                        if (!TextUtils.isEmpty(str) && str.equals(moreSportDeviceActivity.c())) {
                            removeMessages(31);
                            moreSportDeviceActivity.d.a(false);
                            moreSportDeviceActivity.d((String) null);
                        }
                        moreSportDeviceActivity.d.a(false);
                        moreSportDeviceActivity.d.e();
                        moreSportDeviceActivity.c(false);
                        jgi.e().b(new d(moreSportDeviceActivity), "MoreSportDeviceActivity");
                        break;
                    }
                    break;
                case 31:
                    LogUtil.a("MoreSportDeviceActivity", "handleMessageWhenReferenceNotNull device connect time out");
                    removeMessages(31);
                    moreSportDeviceActivity.d.a(false);
                    moreSportDeviceActivity.c(false);
                    if (message.arg1 == 1) {
                        cZt_(message, moreSportDeviceActivity);
                        break;
                    }
                    break;
                case 32:
                    cZv_(message, moreSportDeviceActivity);
                    break;
                case 33:
                default:
                    cZu_(message, moreSportDeviceActivity);
                    break;
                case 34:
                    moreSportDeviceActivity.d.notifyDataSetChanged();
                    break;
            }
        }

        private void cZt_(Message message, final MoreSportDeviceActivity moreSportDeviceActivity) {
            if (!(message.obj instanceof cpm)) {
                LogUtil.h("MoreSportDeviceActivity", "cloudDeviceDialog message.obj instanceof DeviceInfoForWear");
                return;
            }
            final cpm cpmVar = (cpm) message.obj;
            if (message.arg2 == 1) {
                LogUtil.a("MoreSportDeviceActivity", "cloudDeviceDialog clickFrom = 1");
                ogj.cZy_(moreSportDeviceActivity.g, cpl.c().Kj_(cpmVar.i()));
                return;
            }
            CloudReconnectFailDialog.Builder builder = new CloudReconnectFailDialog.Builder(moreSportDeviceActivity.g);
            builder.c(cpmVar.i(), jfu.c(cpmVar.i()).d()).e(new ReconnectCallback() { // from class: com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity.a.1
                @Override // com.huawei.ui.homehealth.device.callback.ReconnectCallback
                public void reconnect() {
                    moreSportDeviceActivity.d(cpmVar.a());
                    moreSportDeviceActivity.d.a(true);
                    moreSportDeviceActivity.c(false);
                    moreSportDeviceActivity.c(cpmVar, 1);
                }
            });
            CloudReconnectFailDialog b = builder.b();
            b.setCanceledOnTouchOutside(false);
            if (b.isShowing() || moreSportDeviceActivity.isFinishing()) {
                return;
            }
            b.show();
        }

        private void cZu_(Message message, MoreSportDeviceActivity moreSportDeviceActivity) {
            int i = message.what;
            if (i == 12) {
                LogUtil.a("MoreSportDeviceActivity", "DEVICE_GET_CURRENT_INFO_LIST message");
                if (message.obj instanceof List) {
                    moreSportDeviceActivity.s.clear();
                    moreSportDeviceActivity.s.addAll((List) message.obj);
                }
                moreSportDeviceActivity.b(message.arg1);
                return;
            }
            if (i == 33) {
                LogUtil.a("MoreSportDeviceActivity", "msg_connect_change state:", Boolean.valueOf(moreSportDeviceActivity.d.a()), "message: ", Integer.valueOf(message.what));
                if (message.obj instanceof String) {
                    moreSportDeviceActivity.d((String) message.obj);
                }
                moreSportDeviceActivity.d.a(true);
                moreSportDeviceActivity.c(false);
                return;
            }
            if (i == 35) {
                LogUtil.a("MoreSportDeviceActivity", "MSG_DEVICE_DELETE message: ", Integer.valueOf(message.what));
                moreSportDeviceActivity.c(false);
            } else {
                if (i == 36) {
                    removeMessages(31);
                    moreSportDeviceActivity.d.a(false);
                    moreSportDeviceActivity.c(false);
                    moreSportDeviceActivity.a(message.arg1);
                    return;
                }
                LogUtil.h("MoreSportDeviceActivity", "message default");
            }
        }

        private void cZv_(Message message, MoreSportDeviceActivity moreSportDeviceActivity) {
            if (message.obj instanceof String) {
                String str = (String) message.obj;
                if (!TextUtils.isEmpty(str) && str.equals(moreSportDeviceActivity.c())) {
                    removeMessages(31);
                    moreSportDeviceActivity.d.a(false);
                    moreSportDeviceActivity.d((String) null);
                }
                moreSportDeviceActivity.c(false);
                if (moreSportDeviceActivity.d.b() == 3) {
                    moreSportDeviceActivity.d.e();
                    ogj.cZy_(moreSportDeviceActivity.g, cpl.c().Kj_(message.arg1));
                }
            }
        }
    }

    static class d implements WearPlaceCallback {
        private WeakReference<MoreSportDeviceActivity> d;

        d(MoreSportDeviceActivity moreSportDeviceActivity) {
            this.d = new WeakReference<>(moreSportDeviceActivity);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback
        public void onResponse(DeviceInfo deviceInfo, int i) {
            MoreSportDeviceActivity moreSportDeviceActivity = this.d.get();
            if (deviceInfo != null) {
                Handler handler = moreSportDeviceActivity.k;
                if (handler == null) {
                    return;
                }
                handler.sendEmptyMessage(34);
                return;
            }
            LogUtil.h("MoreSportDeviceActivity", "WearHomePlaceCallback enter deviceInfo = null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
