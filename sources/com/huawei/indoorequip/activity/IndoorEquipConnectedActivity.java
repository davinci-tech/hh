package com.huawei.indoorequip.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.activity.IndoorEquipConnectedManager;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.indoorequip.util.DownloadUtil;
import com.huawei.indoorequip.viewmodel.IndoorConnectViewModel;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.dif;
import defpackage.dja;
import defpackage.dks;
import defpackage.drd;
import defpackage.fhw;
import defpackage.gsy;
import defpackage.gtb;
import defpackage.gxf;
import defpackage.jdi;
import defpackage.kza;
import defpackage.kzc;
import defpackage.lau;
import defpackage.lbq;
import defpackage.lbv;
import defpackage.lbx;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;

/* loaded from: classes5.dex */
public class IndoorEquipConnectedActivity extends BaseActivity implements View.OnClickListener {
    public static final Observer b = new Observer() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.3
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            Object obj = objArr[0];
            if (obj instanceof Integer) {
                int unused = IndoorEquipConnectedActivity.e = ((Integer) obj).intValue();
            }
            if (IndoorEquipConnectedActivity.b != null) {
                ObserverManagerUtil.e(IndoorEquipConnectedActivity.b, "SKIP_START_EXERCISE_UI");
            }
        }
    };
    private static int e = 0;

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f6419a;
    private LinearLayout am;
    private String ap;
    private a ar;
    private int av;
    private HealthTextView aw;
    private float ax;
    private CustomTitleBar c;
    private HealthTextView f;
    private BluetoothAdapter h;
    private Context i;
    private HealthTextView j;
    private String n;
    private HealthTextView p;
    private File q;
    private IndoorConnectViewModel s;
    private HealthProgressBar t;
    private IndoorEquipConnectedManager u;
    private kzc w;
    private HealthTextView x;
    private ImageView y;
    private boolean z;
    private boolean ag = false;
    private boolean ac = false;
    private boolean ad = false;
    private String v = "";
    private boolean ai = true;
    private boolean al = false;
    private boolean an = false;
    private boolean ab = false;
    private boolean ae = false;
    private boolean aa = false;
    private boolean ah = false;
    private boolean af = false;
    private boolean ak = false;
    private boolean aj = false;
    private long as = 0;
    private NoTitleCustomAlertDialog m = null;
    private CustomTextAlertDialog ao = null;
    private NoTitleCustomAlertDialog k = null;
    private NoTitleCustomAlertDialog r = null;
    private CustomTextAlertDialog l = null;
    private String o = "";
    private kza g = null;
    private Handler aq = new Handler() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
            }
            super.handleMessage(message);
            IndoorEquipConnectedActivity.this.c(message.what);
            IndoorEquipConnectedActivity.this.j(message.what);
            int i = message.what;
            if (i == 509) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is INIT_VARS_WHEN_START_THREAD");
                IndoorEquipConnectedActivity.this.al = false;
                return;
            }
            if (i == 510) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is INIT_VARS_WHEN_SERV_ON_CREATE");
                IndoorEquipConnectedActivity.this.r = null;
                return;
            }
            if (i == 617) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is ACTION_READ_SUCCESS");
                IndoorEquipConnectedActivity.this.ak = true;
                IndoorEquipConnectedActivity.this.ak();
                IndoorEquipConnectedActivity.this.b();
                IndoorEquipConnectedActivity.this.x();
                return;
            }
            if (i == 618) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is ACTION_SET_TARGET_SUCCESS");
                IndoorEquipConnectedActivity.this.z();
                return;
            }
            if (i == 910) {
                IndoorEquipConnectedActivity.this.af = false;
                return;
            }
            switch (i) {
                case 1007:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is UPDATE_DEVICE_INFO");
                    if (message.obj instanceof DeviceInformation) {
                        IndoorEquipConnectedActivity.this.a((DeviceInformation) message.obj);
                        break;
                    }
                    break;
                case 1008:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is START_EXERCISE");
                    IndoorEquipConnectedActivity.this.ax();
                    break;
                case 1009:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is DOWNLOAD_FINISH");
                    IndoorEquipConnectedActivity.this.v();
                    break;
            }
        }
    };
    private MessageOrStateCallback au = new MessageOrStateCallback() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.5
        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onNewMessage(int i, Bundle bundle) {
            if (IndoorEquipConnectedActivity.this.s != null) {
                IndoorEquipConnectedActivity.this.s.bVX_(i, bundle);
            } else if (i == 912) {
                IndoorEquipConnectedActivity.this.bTo_(bundle);
            }
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onStateChange(String str) {
            if (IndoorEquipConnectedActivity.this.s != null) {
                IndoorEquipConnectedActivity.this.s.c(str);
            }
        }
    };
    private DownloadUtil.DownloadListener d = new DownloadUtil.DownloadListener() { // from class: kzn
        @Override // com.huawei.indoorequip.util.DownloadUtil.DownloadListener
        public final void onDownloadFinish(boolean z) {
            IndoorEquipConnectedActivity.this.e(z);
        }
    };

    public /* synthetic */ void e(boolean z) {
        LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "onDownloadFinish");
        f(1009);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bTo_(Bundle bundle) {
        String string = bundle != null ? bundle.getString("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK") : "";
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "setTargetResponse result is null", string);
            return;
        }
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "control machine is ", string);
        if ("set_target_success".equals(string)) {
            this.aq.sendEmptyMessage(618);
        } else if ("set_target_failed".equals(string)) {
            this.aq.sendEmptyMessage(619);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        IndoorConnectViewModel indoorConnectViewModel = this.s;
        if (indoorConnectViewModel != null) {
            lbq.b(this.ap, indoorConnectViewModel.e(), this.s.c());
        }
        if (fhw.e.containsKey(Integer.valueOf(e))) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "sSportType is " + e);
            if (lbv.a(this.o) == e) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "mac address is " + lau.d().b());
                ObserverManagerUtil.c("DEVICE_ASSOCIATION", lau.d().b(), fhw.f12519a.get(Integer.valueOf(e)));
            } else {
                ObserverManagerUtil.c("DEVICE_ASSOCIATION", new Object[0]);
            }
            e = 0;
            this.aa = true;
            finish();
            return;
        }
        if (ai()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "Already sporting, START_EXERCISE");
            ax();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        if (i == 612) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is INVALID_DEVICE_INFO");
            d(getString(R.string.ie_invalid_device_info));
            return;
        }
        if (i == 613) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is FAILED_UNLOCK_BT_MODULE");
            d(getString(R.string._2130840245_res_0x7f020ab5));
            t();
            return;
        }
        if (i == 619) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is ACTION_SET_TARGET_FAILED");
            ap();
            c(getString(R.string._2130840280_res_0x7f020ad8));
            return;
        }
        if (i != 909) {
            switch (i) {
                case 1001:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_SHOW_INVALID_QR_CODE_TOAST");
                    c(getString(R.string._2130840249_res_0x7f020ab9));
                    break;
                case 1002:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_SHOW_INVALID_NFC_TOAST");
                    c(getString(R.string._2130840243_res_0x7f020ab3));
                    break;
                case 1003:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_SHOW_PLEASE_RESCAN_TOAST");
                    c(getString(R.string._2130840248_res_0x7f020ab8));
                    break;
                case 1004:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_SHOW_TIPS_WHEN_START_FROM_GYM_BTN");
                    p();
                    break;
                case 1005:
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_SHOW_TIPS_WHEN_START_HAS_CONNECTED_ALREADY");
                    kza kzaVar = this.g;
                    if (kzaVar != null) {
                        kzaVar.b();
                        break;
                    }
                    break;
            }
            return;
        }
        r();
    }

    private boolean ai() {
        if (this.s != null) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "isSportingOrPause" + this.s.getSportStatus());
        }
        IndoorConnectViewModel indoorConnectViewModel = this.s;
        return indoorConnectViewModel != null && (indoorConnectViewModel.getSportStatus() == 1 || this.s.getSportStatus() == 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 302) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_BT_CONNECTED_SHOW");
            i();
            return;
        }
        if (i == 304) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_BT_DISCONNECTED_SHOW");
            k();
            return;
        }
        if (i == 309) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_BT_SERVICE_REDISCOVER_SHOW");
            m();
            return;
        }
        if (i == 311) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is BT_WILL_CONNECTING_SHOW");
            n();
            return;
        }
        if (i == 313) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_DEVICE_UNSUPPORTED");
            e(getString(R.string.ie_device_unsupported));
            return;
        }
        if (i == 513) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_SHOW_TIPS_FOR_STATUS_POST_WORKOUT");
            if (this.al) {
                return;
            }
            this.al = true;
            e(getString(R.string._2130840313_res_0x7f020af9));
            return;
        }
        if (i == 908) {
            l();
            return;
        }
        if (i == 9004) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_CALL_FINISH_ACTIVITY_LATER");
            t();
        } else if (i == 306) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_BT_SERVICE_NOT_SUPPORT");
            k();
        } else {
            if (i != 307) {
                return;
            }
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is MSG_BT_RECONNECTING_SHOW");
            o();
        }
    }

    private void l() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "in handleMessage, case is FINISH_ACTIVITY");
        if (isFinishing()) {
            return;
        }
        this.aa = true;
        if (!this.z) {
            lau.d().o();
        }
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.connecting_hintText) {
            if (!this.an && (getString(R.string._2130840251_res_0x7f020abb).equals(this.f.getText()) || getString(R.string._2130840248_res_0x7f020ab8).equals(this.f.getText()) || getString(R.string._2130840249_res_0x7f020ab9).equals(this.f.getText()))) {
                if (this.w.y()) {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onClick of scan QRcode(red) button but isWillNotResponseNfcAndQrcode is true, return");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                an();
            }
        } else {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onClick viewId is ", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void an() {
        if (af()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onClick of scan QRcode(red) button");
            if (jdi.c(this, new String[]{"android.permission.CAMERA"})) {
                av();
                return;
            } else {
                jdi.bFL_(this, new String[]{"android.permission.CAMERA"}, new PermissionsResultAction() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.1
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onDenied(String str) {
                    }

                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        IndoorEquipConnectedActivity.this.av();
                    }
                });
                return;
            }
        }
        LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "onClick of scan QRcode(red) button but cannot response because state of BT is not allowed");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "enter onCreate");
        this.as = SystemClock.elapsedRealtime();
        kza kzaVar = new kza(this, null, null);
        this.g = kzaVar;
        kzaVar.bTd_(this.aq);
        setContentView(R.layout.connected_activity_layout);
        getWindow().clearFlags(AppRouterExtras.COLDSTART);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().getDecorView().setSystemUiVisibility(9216);
        getWindow().setStatusBarColor(0);
        this.q = new File(drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", (String) null) + File.separator + "done");
        ab();
        aa();
        ad();
        if (this.w.t()) {
            Intent intent = new Intent("com.huawei.health.FINISH_DISPLAY_ACTIVITY");
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "send LocalBroadcast action is BROADCAST_INTENT_FINISH_DISPLAY_ACTIVITY");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
        BluetoothManager bluetoothManager = getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) getSystemService("bluetooth") : null;
        if (bluetoothManager == null || bluetoothManager.getAdapter() == null) {
            ae();
            return;
        }
        this.h = bluetoothManager.getAdapter();
        q();
        if (!this.z) {
            d();
            ac();
        }
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "device type:", this.o);
        getWindow().addFlags(128);
        if (!this.q.exists() || this.q.length() <= 0) {
            return;
        }
        this.am.setVisibility(8);
        initViewTahiti();
    }

    private void ad() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initBoltConnect with not connected device");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.8
            @Override // java.lang.Runnable
            public void run() {
                gtb.c().b(gsy.b().a(true));
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "bolt status foot number is ", Integer.valueOf(gtb.c().d()));
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "bolt status waist number is ", Integer.valueOf(gtb.c().b()));
                gsy.b().c();
            }
        });
    }

    private void ae() {
        LogUtil.b("IDEQ_IndoorEquipConnectedActivity", "not supported bt");
        nrh.d(HuaweiHealth.a(), getString(this.ae ? R.string._2130840336_res_0x7f020b10 : R.string._2130840241_res_0x7f020ab1));
        t();
    }

    private void ac() {
        if (this.u == null) {
            IndoorEquipConnectedManager indoorEquipConnectedManager = new IndoorEquipConnectedManager(this.i, new IndoorEquipConnectedManager.OnNfcConnectListener() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.7
                @Override // com.huawei.indoorequip.activity.IndoorEquipConnectedManager.OnNfcConnectListener
                public void onStartTimeChange(long j) {
                    if (IndoorEquipConnectedActivity.this.s != null) {
                        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "mIndoorConnectViewModel set time");
                        IndoorEquipConnectedActivity.this.s.a(j);
                    }
                }

                @Override // com.huawei.indoorequip.activity.IndoorEquipConnectedManager.OnNfcConnectListener
                public void onQrCodeOrNfcInfoSet(QrCodeOrNfcInfo qrCodeOrNfcInfo) {
                    if (IndoorEquipConnectedActivity.this.s != null) {
                        IndoorEquipConnectedActivity.this.s.e(qrCodeOrNfcInfo);
                    }
                }
            }, this.aq);
            this.u = indoorEquipConnectedManager;
            indoorEquipConnectedManager.init();
            this.s.a(this.u);
        }
    }

    private void ab() {
        kzc n = kzc.n();
        this.w = n;
        n.a(false);
        this.an = false;
        this.al = false;
        this.ak = false;
        Context applicationContext = getApplicationContext();
        this.i = applicationContext;
        gxf.c(applicationContext);
        this.ae = nsn.ae(this.i);
        kza kzaVar = this.g;
        if (kzaVar != null) {
            kzaVar.a(this.i);
        }
        if (!lbv.a()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "The Emui version is too low");
            as();
            return;
        }
        boolean hasSystemFeature = getPackageManager().hasSystemFeature("android.hardware.nfc");
        this.an = hasSystemFeature;
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "this phone supports Nfc? ", Boolean.valueOf(hasSystemFeature));
        if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            nrh.d(HuaweiHealth.a(), getString(this.ae ? R.string._2130840335_res_0x7f020b0f : R.string._2130840240_res_0x7f020ab0));
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "no BLE in this phone");
            t();
        } else {
            if (getIntent() != null) {
                boolean booleanExtra = getIntent().getBooleanExtra("isFromHarmonyFa", false);
                this.ag = booleanExtra;
                this.ac = booleanExtra || getIntent().getBooleanExtra("ExitApp", false);
            }
            LogUtil.c("IDEQ_IndoorEquipConnectedActivity", "initParams ExitApp ", Boolean.valueOf(this.ac));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onDestroy");
        boolean af = af();
        DownloadUtil.b((DownloadUtil.DownloadListener) null);
        if (!this.aa) {
            if (!this.z) {
                lau.d().o();
            }
            if (!af) {
                LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "unnormal onDestroy");
                this.ar = new a();
                ThreadPoolManager.d().execute(this.ar);
            } else {
                t();
            }
        } else {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "normal onDestroy,isBtDisconnected is " + af);
            lau.d().b("IDEQ_IndoorEquipConnectedActivity", true);
            if (af) {
                t();
            }
        }
        this.aa = false;
        this.g = null;
        IndoorConnectViewModel indoorConnectViewModel = this.s;
        if (indoorConnectViewModel != null) {
            indoorConnectViewModel.m();
            this.s.unregisterAll();
            this.s.a(!this.w.q());
            this.s = null;
        }
        this.aq.removeCallbacksAndMessages(null);
        this.aq = null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onBackPressed BluetoothConnectState:", this.w.m(), ", IsReadCompleted:", Boolean.valueOf(this.ak));
        if (af()) {
            t();
            return;
        }
        if (AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING.equals(this.w.m()) || AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING.equals(this.w.m()) || AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED.equals(this.w.m())) {
            nrh.b(HuaweiHealth.a(), R.string._2130840246_res_0x7f020ab6);
            return;
        }
        if (AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED.equals(this.w.m()) && !this.ak) {
            nrh.b(HuaweiHealth.a(), R.string._2130840246_res_0x7f020ab6);
            return;
        }
        if (this.af) {
            nrh.b(HuaweiHealth.a(), R.string._2130840302_res_0x7f020aee);
        } else if (this.s != null && !this.aj) {
            ar();
            this.s.a(true, false);
        } else {
            LogUtil.b("IDEQ_IndoorEquipConnectedActivity", "mService is null");
        }
    }

    private void ar() {
        if (this.ag && "31".equals(this.o)) {
            LogUtil.c("IDEQ_IndoorEquipConnectedActivity", "return Fa,don't show toast");
        } else if (!this.z) {
            nrh.b(HuaweiHealth.a(), R.string._2130845060_res_0x7f021d84);
        }
        this.s.a(true, false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "enter onNewIntent");
        this.ah = true;
        if (!lbv.a()) {
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "The version of Emui is too low");
            as();
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onNewIntent, mDialogForDisconnect is showing, return");
            return;
        }
        if (this.ab) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onNewIntent, btEnable Is Showing, return");
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.as;
        if (intent != null && elapsedRealtime > 1000) {
            bTp_(intent);
        } else {
            LogUtil.b("IDEQ_IndoorEquipConnectedActivity", "onNewIntent but intent is null or time too short, timeGapBtwOnCreateAndNow:", Long.valueOf(elapsedRealtime));
        }
    }

    private void bTp_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kzi
            @Override // java.lang.Runnable
            public final void run() {
                IndoorEquipConnectedActivity.this.bTx_(intent);
            }
        });
    }

    public /* synthetic */ void bTx_(Intent intent) {
        String stringExtra = intent.getStringExtra("PAYLOAD_FROM_NFC");
        if (this.u == null) {
            d();
            ac();
        }
        if ("android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onNewIntent, action is ACTION_NDEF_DISCOVERED");
            this.u.bTC_(intent);
        } else if (stringExtra != null && !stringExtra.isEmpty()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onNewIntent, action is from MainActivity and payload from Main is ****", " and mBlueConnectState is ", this.w.m());
            this.u.e(stringExtra);
        } else {
            this.u.bTD_(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        ComponentName componentName = new ComponentName(this.i.getPackageName(), "com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
        this.aa = true;
        finish();
    }

    private void g(int i) {
        for (int i2 = 0; !this.w.q() && i2 < i; i2++) {
            SystemClock.sleep(100L);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onActivityResult, requestCode is ", Integer.valueOf(i), ", and resultCode is ", Integer.valueOf(i2));
        if (i2 == 0) {
            if (i == 3 || i == 2) {
                t();
            }
        } else if (i == 2) {
            this.ab = false;
            if (w()) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onActivityResult handlePositive");
                u();
            }
        } else if (i == 1) {
            if (this.h.isEnabled()) {
                y();
            }
        } else {
            LogUtil.c("IDEQ_IndoorEquipConnectedActivity", "else Code is" + i);
        }
        super.onActivityResult(i, i2, intent);
    }

    private void aa() {
        this.y = (ImageView) findViewById(R.id.background);
        this.f = (HealthTextView) findViewById(R.id.connecting_hintText);
        this.aw = (HealthTextView) findViewById(R.id.start_hintText);
        this.x = (HealthTextView) findViewById(R.id.instrumentInfoText);
        this.j = (HealthTextView) findViewById(R.id.connectState);
        this.c = (CustomTitleBar) findViewById(R.id.back_button);
        this.f.setOnClickListener(this);
        this.am = (LinearLayout) findViewById(R.id.loading_layout);
        this.t = (HealthProgressBar) findViewById(R.id.loading_iv);
        this.p = (HealthTextView) findViewById(R.id.progress_dialog_desc);
        if (!this.q.exists() || this.q.length() <= 0) {
            this.am.setVisibility(0);
            this.t.setVisibility(4);
        }
        this.c.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: kzk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndoorEquipConnectedActivity.this.bTt_(view);
            }
        });
        if (this.ag) {
            q();
            if (lbv.a(this.o) == 264) {
                this.f.setText(getString(R.string._2130840269_res_0x7f020acd));
            } else {
                this.f.setText(getString(R.string._2130840317_res_0x7f020afd));
            }
            c(this.aw);
            this.aw.setVisibility(0);
            c(false);
        }
        lau.d().c();
        lau.d().e(getClass().getSimpleName(), this.au);
    }

    public /* synthetic */ void bTt_(View view) {
        onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void d() {
        if (this.s == null) {
            int a2 = lbv.a(this.o);
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initViewModel with sport type: ", Integer.valueOf(a2));
            this.w.c("unknown");
            this.s = (IndoorConnectViewModel) new ViewModelProvider(this).get(IndoorConnectViewModel.class);
            Bundle bundle = new Bundle();
            bundle.putInt("map_tracking_sport_type_sportting", a2);
            bundle.putBoolean("ExitApp", this.ac);
            LogUtil.c("IDEQ_IndoorEquipConnectedActivity", "initViewModel ExitApp ", Boolean.valueOf(this.ac));
            bundle.putInt("sport_target_type_sportting", this.av);
            bundle.putFloat("sport_target_value_sportting", this.ax);
            bundle.putBoolean("isHrControlCourse", !TextUtils.isEmpty(this.n));
            this.s.d(this.z);
            boolean booleanExtra = getIntent() != null ? getIntent().getBooleanExtra("sendVoice", true) : true;
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initViewModel isSendVoice ", Boolean.valueOf(booleanExtra));
            if (booleanExtra) {
                this.s.i();
            }
            this.s.bVY_(bundle, this.aq);
            if (fhw.e.containsKey(Integer.valueOf(e))) {
                return;
            }
            this.s.observeSportLifeCycle("IDEQ_IndoorEquipConnectedActivity", new SportLifecycle() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.10
                @Override // com.huawei.health.sportservice.SportLifecycle
                public void onStartSport() {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initViewModel observeSportLifeCycle onStartSport");
                    IndoorEquipConnectedActivity.this.ax();
                }

                @Override // com.huawei.health.sportservice.SportLifecycle
                public void onResumeSport() {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initViewModel observeSportLifeCycle onResumeSport");
                    IndoorEquipConnectedActivity.this.ax();
                }

                @Override // com.huawei.health.sportservice.SportLifecycle
                public void onPauseSport() {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initViewModel observeSportLifeCycle onPauseSport");
                    IndoorEquipConnectedActivity.this.ax();
                    IndoorEquipConnectedActivity.this.ad = false;
                }

                @Override // com.huawei.health.sportservice.SportLifecycle
                /* renamed from: onStopSport */
                public void m134x32b3e3a1() {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "initViewModel observeSportLifeCycle onStopSport");
                    IndoorEquipConnectedActivity.this.s.a(false, false);
                    IndoorEquipConnectedActivity.this.ad = false;
                }
            });
            this.s.onPreSport();
        }
    }

    public static void e() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "enter resetSportType");
        e = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInformation deviceInformation) {
        IndoorConnectViewModel indoorConnectViewModel;
        if (deviceInformation == null || (indoorConnectViewModel = this.s) == null || indoorConnectViewModel.getSportStatus() == 3) {
            return;
        }
        String manufacturerString = deviceInformation.getManufacturerString();
        String modelString = deviceInformation.getModelString();
        String i = i(deviceInformation.getDeviceType());
        StringBuilder sb = new StringBuilder();
        sb.append(TextUtils.isEmpty(manufacturerString) ? "" : manufacturerString.trim());
        sb.append(TextUtils.isEmpty(modelString) ? "" : modelString.trim());
        String format = String.format(i, sb);
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "updateDeviceInfo, deviceInfo = ", format);
        if (format.length() > 15) {
            this.x.setTextSize(1, 13.0f);
        } else {
            this.x.setTextSize(1, 15.0f);
        }
        c(false);
        this.x.setText(format);
        this.x.setVisibility(0);
        if (lbv.a(this.o) == 264) {
            this.j.setVisibility(0);
        }
    }

    private String i(int i) {
        if (i == 264) {
            return getString(R.string.ie_device_type_attachable_treadmill_string);
        }
        if (i == 265) {
            return getString(R.string.ie_device_type_indoor_cycling_string);
        }
        if (i == 273) {
            return getString(R.string.ie_device_type_cross_trainer_string);
        }
        if (i != 274) {
            return i != 281 ? "" : getString(R.string.ie_device_type_walking_machine_string);
        }
        return getString(R.string.ie_device_type_row_machine_string);
    }

    public void d(int i) {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onCreate, connect to Service");
        this.w.j(false);
        if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING.equals(this.w.m())) {
            n();
        } else {
            initViewTahiti();
        }
        a(i);
    }

    private void a(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kzp
            @Override // java.lang.Runnable
            public final void run() {
                IndoorEquipConnectedActivity.this.b(i);
            }
        });
    }

    public /* synthetic */ void b(int i) {
        g(20);
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "sub thread");
        if (ai() && this.ak) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "Already sporting and ReadCompleted, send message START_EXERCISE");
            f(1008);
        } else {
            SystemClock.sleep(i);
            bTn_(getIntent());
        }
    }

    private void bTn_(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("PAYLOAD_FROM_NFC");
            if (af()) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onCreate, intent is not null");
                if (this.u == null) {
                    d();
                    ac();
                }
                if (stringExtra != null && !stringExtra.isEmpty()) {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onCreate, start from MainActivity and payload is not empty");
                    lbv.b(getApplicationContext(), "NFC", a(stringExtra));
                    this.u.b(stringExtra);
                    return;
                }
                this.u.bTF_(intent);
                return;
            }
            lbv.b(getApplicationContext(), "Connected", a(stringExtra));
            f(1005);
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onCreate, a session is running already");
            return;
        }
        lbv.b(getApplicationContext(), "Other", "");
        f(1004);
        LogUtil.b("IDEQ_IndoorEquipConnectedActivity", "onCreate, intent is null or a session is running already");
    }

    private String a(String str) {
        return TextUtils.isEmpty(str) ? "" : dks.a("ble", str);
    }

    private boolean af() {
        return AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED.equals(this.w.m()) || "unknown".equals(this.w.m());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "IndoorEquipConnectedActivity, onPause");
        super.onPause();
        this.ah = false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onStart");
        super.onStart();
        if (this.ah) {
            return;
        }
        aq();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onResume");
        super.onResume();
        if (!lbv.a()) {
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "The Emui version too low");
            as();
            return;
        }
        if (this.h == null) {
            ae();
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onResume, mDialogForDisconnect is showing, return");
            return;
        }
        if ((!this.h.isEnabled() && !this.ab) || !PermissionDialogHelper.Vy_(this)) {
            this.ab = true;
            ah();
            return;
        }
        getWindow().addFlags(128);
        if (!this.ah || ag()) {
            return;
        }
        aq();
    }

    private void ah() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "openBlueTooth");
        PermissionDialogHelper.Vx_(this, new PermissionDialogHelper.OpenBlueToothAction() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.9
            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
            public void onPermissionGranted() {
                try {
                    IndoorEquipConnectedActivity.this.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 2);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("IDEQ_IndoorEquipConnectedActivity", "openBlueTooth from activity ActivityNotFoundException!");
                }
            }

            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
            public void onPermissionDenied() {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "openBlueTooth finishFragment");
                IndoorEquipConnectedActivity.this.t();
            }
        });
    }

    private boolean ag() {
        return PermissionUtil.e(this, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED;
    }

    /* renamed from: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends CustomPermissionAction {
        AnonymousClass6(Context context) {
            super(context);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            if (!dja.VG_(IndoorEquipConnectedActivity.this)) {
                IndoorEquipConnectedActivity.this.am();
            } else {
                IndoorEquipConnectedActivity.this.al();
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            IndoorEquipConnectedActivity.this.t();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            nsn.cLK_(IndoorEquipConnectedActivity.this, permissionType, null, new View.OnClickListener() { // from class: kzv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IndoorEquipConnectedActivity.AnonymousClass6.this.bTy_(view);
                }
            }, new View.OnClickListener() { // from class: kzw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IndoorEquipConnectedActivity.AnonymousClass6.this.bTz_(view);
                }
            });
        }

        public /* synthetic */ void bTy_(View view) {
            IndoorEquipConnectedActivity.this.t();
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void bTz_(View view) {
            IndoorEquipConnectedActivity.this.t();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void y() {
        PermissionUtil.b(this, s(), new AnonymousClass6(this.i));
    }

    private PermissionUtil.PermissionType s() {
        if (lbv.a(this.o) == 264) {
            return PermissionUtil.PermissionType.STORAGE_LOCATION;
        }
        return PermissionUtil.PermissionType.LOCATION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        if (this.z) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "getSdPermission isFromDetailOrRateControl");
            int i = this.av;
            if (i == 6) {
                z();
            } else if (i == 2) {
                lau.d().d(2, new int[]{(int) this.ax});
                this.aq.sendEmptyMessageDelayed(618, 500L);
            } else {
                lau.d().d(TextUtils.isEmpty(this.n) ? this.av : 0, new int[]{(int) this.ax});
            }
            if (this.ad) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "permissionToGranted mIsDeviceStarted is true");
                ax();
                return;
            }
            return;
        }
        d(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        lau.d().k();
        d();
        ac();
        lau.d().n();
        this.ak = true;
        a(this.u.bTE_(getIntent()));
        x();
        this.w.c(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        dif.Vp_(this, true, new IdialogButtonClickCallback() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedActivity.2
            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onClick(View view) {
                try {
                    IndoorEquipConnectedActivity.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                } catch (ActivityNotFoundException unused) {
                    IndoorEquipConnectedActivity.this.ab = false;
                    nrh.d(HuaweiHealth.a(), HuaweiHealth.a().getResources().getString(R.string._2130842677_res_0x7f021435));
                    LogUtil.b("IDEQ_IndoorEquipConnectedActivity", "startActivity REQUEST_GPS_LOCATION fail ");
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onCancelClick(View view) {
                IndoorEquipConnectedActivity.this.t();
            }
        });
    }

    private void n() {
        if (!this.ag) {
            c(true);
            this.f.setText(getString(R.string._2130840267_res_0x7f020acb));
            this.f.setVisibility(0);
            if (lbv.a(this.o) == 264) {
                this.aw.setVisibility(4);
            } else {
                d(this.aw);
                this.aw.setVisibility(0);
            }
        }
        this.j.setVisibility(4);
        f();
    }

    private void d(HealthTextView healthTextView) {
        healthTextView.setText(getString(R.string.ie_tips_activation_device));
        int i = (int) ((getResources().getDisplayMetrics().widthPixels * 2) / 3.0f);
        if (healthTextView.getPaint().measureText(getString(R.string.ie_tips_activation_device)) >= i) {
            healthTextView.setWidth(i);
        }
    }

    private void i() {
        if (!this.ag) {
            this.f.setText(getString(R.string.ie_connected_success_getting_deviceinfo));
            c(true);
            this.f.setVisibility(0);
            this.aw.setVisibility(4);
        }
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "TIME connected time:", Double.valueOf((currentTimeMillis - (this.u == null ? 0L : r2.d())) / 1000.0f));
        if (lbv.a(this.o) == 264) {
            ao();
        }
    }

    private void o() {
        c(true);
        this.f.setText(getString(R.string._2130840268_res_0x7f020acc));
        this.f.setVisibility(0);
        this.aw.setVisibility(4);
        this.j.setVisibility(4);
    }

    private void m() {
        c(false);
        if (lbv.a(this.o) == 264) {
            this.j.setText(getString(R.string.ie_device_connected_hint));
            this.j.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "sendSportTypeToRower intent is null");
            return;
        }
        String stringExtra = intent.getStringExtra("PAYLOAD_FROM_NFC");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "sendSportTypeToRower payload is null");
            return;
        }
        String a2 = dks.a("stype", stringExtra);
        if ("290".equals(a2)) {
            lau.d().b(21, new int[]{1});
        } else if ("291".equals(a2)) {
            lau.d().b(21, new int[]{2});
        } else {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "modeType is other type ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ax() {
        if (this.u.b() && !this.ak) {
            ReleaseLogUtil.e("IDEQ_IndoorEquipConnectedActivity", "isFtmp but mIsReadCompleted is false");
            return;
        }
        if (this.aj) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "Already START_EXERCISE");
            return;
        }
        if (!ai()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "isSportingOrPause is not sporting or pause");
            return;
        }
        if (getIntent() != null) {
            int intExtra = getIntent().getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
            String stringExtra = getIntent().getStringExtra("KEY_INTENT_EQUIPMENT_TYPE");
            if (intExtra == 1) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "start jumpToLongCoach! equipmentType is ", stringExtra);
                if (HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.name().equals(stringExtra)) {
                    lau.d().a(true);
                }
                ((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)).jumpToLongCoach(this.i, intExtra);
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "start jumpToLongCoach exit");
            } else {
                au();
            }
            this.w.a(true);
            this.w.a(System.currentTimeMillis());
            this.aa = true;
            this.aj = true;
            if (isFinishing()) {
                return;
            }
            finish();
        }
    }

    private void au() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "start exercising!");
        Intent intent = new Intent();
        intent.setClass(this, TextUtils.isEmpty(this.n) ? IndoorEquipDisplayActivity.class : HeartRateControlSportActivity.class);
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        int a2 = lbv.a(this.o);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("PAYLOAD_FROM_NFC");
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "start exercising!  payload = ", stringExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                sportLaunchParams.addExtra("sportMode", dks.a("stype", stringExtra));
            }
        }
        sportLaunchParams.setSportType(a2);
        sportLaunchParams.setSportTarget(this.av);
        sportLaunchParams.setTargetValue(this.ax);
        IndoorConnectViewModel indoorConnectViewModel = this.s;
        if (indoorConnectViewModel != null) {
            sportLaunchParams.addExtra("supportDataRange", indoorConnectViewModel.a());
        }
        QrCodeOrNfcInfo e2 = this.u.e();
        if (e2 != null) {
            sportLaunchParams.addExtra("deviceMac", e2.getBtMac());
            sportLaunchParams.addExtra("deviceName", e2.getBtName());
        }
        sportLaunchParams.addExtra("HEART_RATE_CONTROL_COURSE_ID", this.n);
        sportLaunchParams.addExtra("isHrControlCourse", Boolean.valueOf(!TextUtils.isEmpty(this.n)));
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        intent.putExtra("ExitApp", this.ac);
        intent.putExtra("key_is_keep_connect", this.z);
        if (!TextUtils.isEmpty(this.n)) {
            intent.putExtra("productId", this.ap);
            intent.putExtra("deviceType", this.o);
            lbv.a(this.ap, this.n, this.o, 1);
        }
        startActivity(intent);
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "start exercising exit");
    }

    private void k() {
        Handler handler = this.aq;
        if (handler != null) {
            handler.removeMessages(1007);
        }
        if (!this.w.s()) {
            ap();
            c(getString(R.string._2130840280_res_0x7f020ad8));
        } else {
            c(getString(R.string._2130840303_res_0x7f020aef));
        }
    }

    private void c(String str) {
        c(true);
        this.f.setVisibility(0);
        this.f.setText(str);
        this.aw.setVisibility(4);
        this.j.setVisibility(4);
    }

    private void d(String str) {
        this.f.setVisibility(4);
        this.j.setVisibility(4);
        nrh.d(HuaweiHealth.a(), str);
    }

    private void r() {
        c(false);
        this.aw.setVisibility(4);
        this.f.setText(getString(R.string._2130840302_res_0x7f020aee));
        this.f.setVisibility(0);
        if (lbv.a(this.o) == 264) {
            this.j.setVisibility(0);
        }
        this.x.setVisibility(0);
        this.af = true;
    }

    private void p() {
        if (this.al) {
            return;
        }
        if (this.an) {
            c(getString(this.ae ? R.string._2130840333_res_0x7f020b0d : R.string._2130840250_res_0x7f020aba));
        } else {
            c(getString(R.string._2130840251_res_0x7f020abb));
        }
        this.x.setVisibility(4);
    }

    private void as() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "====enter showTipsForEmuiTooLow()=====");
        j();
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.m;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "DialogForEmuiTooLow is showing");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(getString(this.ae ? R.string._2130840337_res_0x7f020b11 : R.string._2130840261_res_0x7f020ac5)).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: kzr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndoorEquipConnectedActivity.this.bTv_(view);
            }
        }).e();
        this.m = e2;
        e2.setCancelable(false);
        this.m.show();
        this.aa = true;
    }

    public /* synthetic */ void bTv_(View view) {
        t();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean w() {
        return this.i.getSharedPreferences("IDEQ_IndoorEquipConnectedActivity" + CommonUtil.e(getApplicationContext()), 0).getBoolean("IDEQ_IndoorEquipConnectedActivityprivacyDialogConfirm" + CommonUtil.e(getApplicationContext()), false);
    }

    private void aq() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "====enter showPrivacyStatementDialog()=====");
        if (w()) {
            if (!this.q.exists()) {
                u();
                return;
            } else if (this.h.isEnabled()) {
                y();
                return;
            } else {
                LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "onCreate, BT is not enable");
                return;
            }
        }
        h();
        CustomTextAlertDialog customTextAlertDialog = this.ao;
        if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "showPrivacyStatementDialog is showing");
        } else {
            a();
        }
    }

    private void a() {
        CustomTextAlertDialog bVP_ = lbx.bVP_(this, new View.OnClickListener() { // from class: kzj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndoorEquipConnectedActivity.this.bTq_(view);
            }
        }, new View.OnClickListener() { // from class: kzm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndoorEquipConnectedActivity.this.bTr_(view);
            }
        }, new DialogInterface.OnKeyListener() { // from class: kzo
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return IndoorEquipConnectedActivity.this.bTs_(dialogInterface, i, keyEvent);
            }
        });
        this.ao = bVP_;
        bVP_.show();
    }

    public /* synthetic */ void bTq_(View view) {
        u();
        SharedPreferences.Editor edit = getSharedPreferences("IDEQ_IndoorEquipConnectedActivity" + CommonUtil.e(getApplicationContext()), 0).edit();
        edit.putBoolean("IDEQ_IndoorEquipConnectedActivityprivacyDialogConfirm" + CommonUtil.e(getApplicationContext()), true);
        edit.apply();
        lbv.a(this.i, true);
        c();
        IndoorConnectViewModel indoorConnectViewModel = this.s;
        if (indoorConnectViewModel != null && indoorConnectViewModel.getSportStatus() == 1) {
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "PrivacyDialog Positive and the device  is Sporting");
            this.ad = true;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bTr_(View view) {
        lbv.a(this.i, false);
        t();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ boolean bTs_(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0 || !this.ao.isShowing()) {
            return false;
        }
        this.ao.dismiss();
        t();
        return false;
    }

    private void c() {
        if (((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo() == null) {
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "userInfo == null");
        } else {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "isUserInvalid ", Boolean.valueOf(!r0.isGenderValid()), Boolean.valueOf(!r0.isWeightValid()), Boolean.valueOf(!r0.isHeightValid()), Boolean.valueOf(!r0.isBirthdayValid()));
        }
    }

    private void u() {
        if (this.h.isEnabled()) {
            if (!this.q.exists()) {
                LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "imgFile not exist");
                DownloadUtil.b(this.d).bVQ_(this, false);
                this.p.setText(getString(R.string._2130841415_res_0x7f020f47));
                this.t.setVisibility(0);
                this.p.setVisibility(0);
                LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "wait download finish");
                return;
            }
            LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "imgFile exist");
            v();
            return;
        }
        LogUtil.h("IDEQ_IndoorEquipConnectedActivity", "onCreate, BT is not enable");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "handleDownLoadResult");
        this.am.setVisibility(8);
        initViewTahiti();
        y();
    }

    private void ap() {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "====enter showTipsForDisconnect()=====");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                return;
            }
            this.k.show();
            this.aa = true;
            return;
        }
        NoTitleCustomAlertDialog bVO_ = lbx.bVO_(this, this.ae, new View.OnClickListener() { // from class: kzq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndoorEquipConnectedActivity.this.bTu_(view);
            }
        });
        this.k = bVO_;
        if (bVO_ != null) {
            bVO_.show();
        }
        this.aa = true;
    }

    public /* synthetic */ void bTu_(View view) {
        t();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(String str) {
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "====enter showTipsForTooShortOrPostWorkout()=====");
        g();
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.r;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "TipsForTooShortOneButton is showing");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(str).czE_(getString(R.string._2130840262_res_0x7f020ac6), new View.OnClickListener() { // from class: kzu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndoorEquipConnectedActivity.this.bTw_(view);
            }
        }).e();
        this.r = e2;
        e2.setCancelable(false);
        this.r.show();
        this.aa = true;
    }

    public /* synthetic */ void bTw_(View view) {
        t();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "mDialogForDisconnect is showing, dismiss it now");
        this.k.dismiss();
        this.aa = false;
    }

    private void f(int i) {
        Handler handler = this.aq;
        if (handler != null) {
            handler.sendEmptyMessage(i);
        }
    }

    private void g() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.k.dismiss();
            this.aa = false;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.m;
        if (noTitleCustomAlertDialog2 == null || !noTitleCustomAlertDialog2.isShowing()) {
            return;
        }
        this.m.dismiss();
        this.aa = false;
    }

    private void j() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.r;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.r.dismiss();
            this.aa = false;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.k;
        if (noTitleCustomAlertDialog2 == null || !noTitleCustomAlertDialog2.isShowing()) {
            return;
        }
        this.k.dismiss();
        this.aa = false;
    }

    private void h() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.r;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.r.dismiss();
            this.r = null;
            this.aa = false;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog2 = this.k;
        if (noTitleCustomAlertDialog2 != null && noTitleCustomAlertDialog2.isShowing()) {
            this.k.dismiss();
            this.k = null;
            this.aa = false;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog3 = this.m;
        if (noTitleCustomAlertDialog3 == null || !noTitleCustomAlertDialog3.isShowing()) {
            return;
        }
        this.m.dismiss();
        this.m = null;
        this.aa = false;
    }

    private void ao() {
        String quantityString;
        long currentTimeMillis = System.currentTimeMillis();
        double d = (currentTimeMillis - (this.u == null ? 0L : r2.d())) / 1000.0f;
        if (d >= 3.0d) {
            quantityString = getString(R.string.ie_device_connected_hint);
        } else if (d >= 0.1d) {
            quantityString = getResources().getQuantityString(R.plurals._2130903144_res_0x7f030068, (int) Math.floor(d), Double.valueOf(d));
        } else {
            quantityString = getResources().getQuantityString(R.plurals._2130903144_res_0x7f030068, 0, Double.valueOf(0.1d));
        }
        this.j.setText(quantityString);
        this.j.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.w.q()) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "finishActivity, service is running, stop it");
            this.w.b(true);
        }
        l();
    }

    private void a(boolean z, boolean z2) {
        String e2 = lbv.e(this.o, z, z2);
        this.v = e2;
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(this.v);
        this.f6419a = decodeFile;
        this.y.setImageBitmap(decodeFile);
    }

    private void c(boolean z) {
        this.ai = z;
        initViewTahiti();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        aj();
        boolean ag = nsn.ag(getApplicationContext());
        if (this.q.exists() && this.q.length() > 0) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "onConfigurationChanged isTahitiModel: ", Boolean.valueOf(ag));
            if (CommonUtil.bh()) {
                a(ag, this.ai);
                return;
            } else {
                a(false, this.ai);
                return;
            }
        }
        if (ag) {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "is TahitiModel default");
            this.y.setImageResource(R.drawable._2131430697_res_0x7f0b0d29);
        } else {
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "not TahitiModel default");
            this.y.setImageResource(R.drawable._2131430671_res_0x7f0b0d0f);
        }
    }

    private void aj() {
        if (this.y.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.y.getLayoutParams();
            if (nsn.cLh_(this)) {
                getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
                layoutParams.width = (int) (r1.widthPixels * 0.8f);
                layoutParams.height = -2;
                layoutParams.gravity = 1;
            } else {
                layoutParams.width = -1;
                layoutParams.height = -2;
            }
            this.y.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (CommonUtil.bh()) {
            if (lbv.a(this.o) == 264) {
                this.f.setText(this.ae ? R.string._2130840334_res_0x7f020b0e : R.string._2130840269_res_0x7f020acd);
            } else {
                this.f.setText(R.string._2130840317_res_0x7f020afd);
            }
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(4);
        }
        c(this.aw);
        this.aw.setVisibility(0);
    }

    private void c(HealthTextView healthTextView) {
        float measureText;
        if (lbv.a(this.o) == 264) {
            healthTextView.setText(getString(R.string._2130840270_res_0x7f020ace));
            measureText = healthTextView.getPaint().measureText(getString(R.string._2130840270_res_0x7f020ace));
        } else {
            healthTextView.setText(getString(R.string._2130840322_res_0x7f020b02));
            measureText = healthTextView.getPaint().measureText(getString(R.string._2130840322_res_0x7f020b02));
        }
        int i = (int) ((getResources().getDisplayMetrics().widthPixels * 2) / 3.0f);
        if (measureText >= i) {
            healthTextView.setWidth(i);
        }
    }

    private void q() {
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("PAYLOAD_FROM_NFC");
            if (!TextUtils.isEmpty(stringExtra)) {
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "payload is not empty");
                this.o = lbv.b(stringExtra);
            } else {
                this.o = intent.getStringExtra("DEVICE_TYPE_INDEX");
            }
            if (TextUtils.isEmpty(this.o)) {
                this.o = "31";
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "mDeviceType is empty, set default");
            }
            this.n = intent.getStringExtra("courseId");
            this.ap = intent.getStringExtra("productId");
            this.av = intent.getIntExtra(WorkoutRecord.Extend.COURSE_TARGET_TYPE, -1);
            this.ax = intent.getFloatExtra(WorkoutRecord.Extend.COURSE_TARGET_VALUE, 0.0f);
            LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "mCourseId is ", this.n, "mTargetType is ", Integer.valueOf(this.av), ", mTargetValue is ", Float.valueOf(this.ax));
            this.z = lau.d().j();
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemClock.sleep(2000L);
            Context context = IndoorEquipConnectedActivity.this.i;
            String str = "IndoorEquipServiceRunning" + CommonUtil.e(IndoorEquipConnectedActivity.this.i);
            Context unused = IndoorEquipConnectedActivity.this.i;
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            if (sharedPreferences != null) {
                if (!sharedPreferences.getBoolean("IsIndoorEquipServiceRunning" + CommonUtil.e(IndoorEquipConnectedActivity.this.i), false) || SystemClock.elapsedRealtime() - sharedPreferences.getLong("elapsedRealtime", 0L) >= ProfileExtendConstants.TIME_OUT || SystemClock.elapsedRealtime() - sharedPreferences.getLong("elapsedRealtime", 0L) <= 0) {
                    LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "inDelayRestartRunnable, isIndoorEquipSerRealRunning:", false);
                    return;
                }
                LogUtil.a("IDEQ_IndoorEquipConnectedActivity", "inDelayRestartRunnable, isIndoorEquipSerRealRunning:", true);
            }
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setClass(IndoorEquipConnectedActivity.this.getApplication(), IndoorEquipConnectedActivity.class);
            IndoorEquipConnectedActivity.this.getApplication().startActivity(intent);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
