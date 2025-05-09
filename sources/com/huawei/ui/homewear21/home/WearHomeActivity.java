package com.huawei.ui.homewear21.home;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btcommon.BluetoothSwitchStateUtil;
import com.huawei.hwcloudmodel.model.ecgservice.EcgServiceActivationData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.activity.pairing.EarphonePairActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import com.huawei.ui.device.views.animation.ActivityAnimationCallback;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.adapter.WearHomeCardAdapter;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.card.WearHomeSyncCard;
import com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import defpackage.cof;
import defpackage.cpm;
import defpackage.cvn;
import defpackage.cvs;
import defpackage.cvt;
import defpackage.cvz;
import defpackage.cwi;
import defpackage.dis;
import defpackage.dks;
import defpackage.iyg;
import defpackage.jad;
import defpackage.jdi;
import defpackage.jez;
import defpackage.jfo;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jgc;
import defpackage.jgh;
import defpackage.jgs;
import defpackage.jjb;
import defpackage.jkj;
import defpackage.jnr;
import defpackage.jph;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.kch;
import defpackage.kdn;
import defpackage.nrk;
import defpackage.nsf;
import defpackage.nsy;
import defpackage.oae;
import defpackage.oam;
import defpackage.oau;
import defpackage.obb;
import defpackage.obt;
import defpackage.obw;
import defpackage.oxa;
import defpackage.oxd;
import defpackage.oyf;
import defpackage.pbg;
import defpackage.pbu;
import defpackage.pct;
import defpackage.pcw;
import defpackage.pdp;
import defpackage.pds;
import defpackage.pdw;
import defpackage.pdz;
import defpackage.pel;
import defpackage.pep;
import defpackage.pes;
import defpackage.pet;
import defpackage.rvo;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonLibUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class WearHomeActivity extends BaseActivity {
    private static final String[] aa = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
    private static final String[] z = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private WearHomeCardAdapter ab;
    public pdz ad;
    private Context ag;
    private String ah;
    private obt ai;
    private CustomTitleBar aj;
    private String ak;
    private Context am;
    private String an;
    private String ao;
    private String ap;
    private GuideInteractors ar;
    private boolean aw;
    private boolean ay;
    private String ba;
    private boolean bb;
    private boolean bc;
    private PopViewList be;
    private Rect bf;
    private HealthRecycleView bg;
    private WechatDeviceProviderHelper bk;
    private pdw bl;
    public BluetoothSwitchStateUtil c;
    public DeviceSettingsInteractors d;
    public ActivityResultLauncher<IntentSenderRequest> e;
    public jjb h;
    public jqi j;
    public boolean o;
    public pct p;
    public NotificationPushInteractor q;
    public pbg r;
    public pbu s;
    public pdp u;
    public WearHomeSyncCard v;
    public pds w;
    public pcw y;
    public String g = "";
    public String m = "";
    public DeviceInfo b = null;

    /* renamed from: a, reason: collision with root package name */
    public DeviceCapability f9644a = null;
    public boolean i = false;
    public boolean k = false;
    public boolean l = false;
    public int f = -1;
    public ExecutorService x = Executors.newCachedThreadPool();
    public boolean n = true;
    private rvo bh = null;
    private boolean ax = false;
    private boolean at = false;
    private jfq aq = null;
    private boolean au = false;
    private oam af = new oam();
    private ArrayList<String> ae = new ArrayList<>(16);
    private ArrayList<WearHomeBaseCard> bm = new ArrayList<>(16);
    private pel as = new pel(this);
    private List<EnumDialog> az = new ArrayList(0);
    private boolean av = true;
    public boolean t = false;
    private final BroadcastReceiver al = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.WearHomeActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            LogUtil.c("MainUI", 0, "WearHomeActivity", "mDeviceChangerBroadcastReceiver(): intent is ", intent.getAction());
            try {
                WearHomeActivity.this.djF_(intent);
            } catch (ClassCastException unused) {
                LogUtil.e("WearHomeActivity", "ClassCastException");
            }
        }
    };
    private final BroadcastReceiver bd = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.WearHomeActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            LogUtil.c("MainUI", 0, "WearHomeActivity", "mNonLocalBroadcastReceiver(): intent is ", intent.getAction());
            if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
                WearHomeActivity.this.a(intent.getStringExtra("pairGuideSelectAddress"));
                return;
            }
            if ("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS".equals(intent.getAction())) {
                LogUtil.c("MainUI", 0, "WearHomeActivity", "handlePhoneServiceBindSuccess()");
                WearHomeActivity.this.b = oxa.a().b(WearHomeActivity.this.g);
                if (WearHomeActivity.this.b != null) {
                    LogUtil.c("MainUI", 0, "WearHomeActivity", "mCurrentDeviceInfo not null");
                    WearHomeActivity.this.aa();
                    WearHomeActivity.this.ax = true;
                    if (WearHomeActivity.this.b.getDeviceConnectState() == 2) {
                        WearHomeActivity.this.i = true;
                    }
                    WearHomeActivity.this.bd();
                    WearHomeActivity wearHomeActivity = WearHomeActivity.this;
                    wearHomeActivity.g = wearHomeActivity.b.getDeviceIdentify();
                    WearHomeActivity.this.as();
                    WearHomeActivity.this.v();
                    return;
                }
                return;
            }
            if ("com.huawei.bone.action.DEVICE_UPGRADING".equals(intent.getAction())) {
                WearHomeActivity.this.ap();
                return;
            }
            if ("com.huawei.bone.action.open_gps".equals(intent.getAction())) {
                WearHomeActivity.this.av();
                abortBroadcast();
                return;
            }
            if ("com.huawei.bone.action.SYSTEM_BLUETOOTH_UNBIND_DEVICE".equals(intent.getAction())) {
                if (!WearHomeActivity.this.t) {
                    String stringExtra = intent.getStringExtra("DEVICE_SECURITY_UUID");
                    LogUtil.c("WearHomeActivity", "mNonLocalBroadcastReceiver SYSTEM_BLUETOOTH_UNBIND_DEVICE securityUuid: ", CommonUtil.l(stringExtra));
                    if (WearHomeActivity.this.b == null || stringExtra == null || !stringExtra.equals(WearHomeActivity.this.b.getUnConvertedUdid())) {
                        return;
                    }
                    ReleaseLogUtil.a("R_WearHomeActivity", "mNonLocalBroadcastReceiver SYSTEM_BLUETOOTH_UNBIND_DEVICE");
                    WearHomeActivity.this.finish();
                    return;
                }
                WearHomeActivity.this.t = false;
                if (WearHomeActivity.this.u != null) {
                    String str = WearHomeActivity.this.g;
                    WearHomeActivity wearHomeActivity2 = WearHomeActivity.this;
                    obb.c(str, wearHomeActivity2, wearHomeActivity2.e, WearHomeActivity.this.b.getDeviceBluetoothType());
                }
            }
        }
    };
    private ActivityAnimationCallback ac = new ActivityAnimationCallback() { // from class: oxq
        @Override // com.huawei.ui.device.views.animation.ActivityAnimationCallback
        public final void onProcessExit(boolean z2) {
            WearHomeActivity.this.d(z2);
        }
    };
    private BroadcastReceiver bj = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.WearHomeActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.c("WearHomeActivity", "TopClickReceiver onReceive()");
            if (WearHomeActivity.this.bg != null) {
                WearHomeActivity.this.bg.scrollToPosition(0);
            }
        }
    };

    /* loaded from: classes6.dex */
    public enum EnumDialog {
        RATE
    }

    public /* synthetic */ void d(boolean z2) {
        LogUtil.c("WearHomeActivity", "mAnimationHelper onExitAnimationEnd mIsOnBackPressed:", Boolean.valueOf(this.bc), "  mIsFromWear:" + this.au);
        if (this.bc && this.au) {
            ah();
        }
        this.ai = null;
        if (z2) {
            overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
            bh();
        }
        finishAndRemoveTask();
        LogUtil.c("WearHomeActivity", "mAnimationHelper onExitAnimationEnd");
    }

    public Handler djG_() {
        return this.as;
    }

    public WechatDeviceProviderHelper d() {
        return this.bk;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null && Build.VERSION.SDK_INT == 29) {
            bundle.setClassLoader(getClassLoader());
        }
        super.onCreate(bundle);
        al();
        LogUtil.c("MainUI", 0, "WearHomeActivity", "Enter onCreateView time:", Long.valueOf(System.currentTimeMillis()));
        setContentView(R.layout.wear_home_main_card_layout);
        ad();
        jph.bIM_(this, -1);
        if (Utils.i() && !LoginInit.getInstance(this.am).getIsLogined()) {
            CommonLibUtil.d(this.am);
        }
        if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.c("WearHomeActivity", "Action is not wanted and no confirm privacy.");
            ah();
        }
        try {
            af();
        } catch (Exception unused) {
            LogUtil.e("WearHomeActivity", "Exception");
            finish();
        }
        ab();
    }

    private void al() {
        this.e = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homewear21.home.WearHomeActivity$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WearHomeActivity.this.d((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void d(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            if (this.u != null) {
                SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
                ReleaseLogUtil.b("R_WearHomeActivity", "setString SENSITIVE_PERMISSION_STATUS restart");
                k();
                pdp pdpVar = this.u;
                if (pdpVar != null) {
                    pdpVar.e();
                    return;
                }
                return;
            }
            return;
        }
        pdp pdpVar2 = this.u;
        if (pdpVar2 != null) {
            pdpVar2.d();
        }
    }

    public void k() {
        pcw pcwVar = this.y;
        if (pcwVar != null) {
            pcwVar.a();
        }
    }

    private void ab() {
        ai();
        r();
        m();
        ac();
        this.ab = new WearHomeCardAdapter(this.am, this.bm);
        ag();
        this.bg.getRecycledViewPool().clear();
        w();
        pep.dmU_(this.am, this.al, 1000, "com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        pep.dmT_(this.am, this.bd, "com.huawei.bone.action.open_gps", "com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS", "broadcast_receiver_user_setting", "com.huawei.bone.action.DEVICE_UPGRADING", "com.huawei.bone.action.SYSTEM_BLUETOOTH_UNBIND_DEVICE");
        this.c = new BluetoothSwitchStateUtil(this.am);
        LogUtil.c("MainUI", 0, "WearHomeActivity", "Leave onCreateView time:", Long.valueOf(System.currentTimeMillis()));
        if (this.bb) {
            LogUtil.c("WearHomeActivity", "mIsSmartLifeOperationDelete is true");
            this.r.e();
        } else {
            LogUtil.c("WearHomeActivity", "mIsSmartLifeOperationDelete is false");
        }
        this.r.g();
        aq();
        this.v.dlT_(new View.OnClickListener() { // from class: oxr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WearHomeActivity.this.djI_(view);
            }
        });
        pep.dmR_(this.am, this.g, getIntent());
        pep.b(this.b);
    }

    public /* synthetic */ void djI_(View view) {
        this.v.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aq() {
        if (this.o && this.bf != null) {
            getWindow().setBackgroundDrawableResource(R.color._2131296971_res_0x7f0902cb);
        } else {
            getWindow().setBackgroundDrawableResource(R.color._2131296657_res_0x7f090191);
        }
        az();
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x008a, code lost:
    
        if (r6 == false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void az() {
        /*
            r12 = this;
            boolean r0 = health.compact.a.Utils.o()
            java.lang.String r1 = "WearHomeActivity"
            if (r0 == 0) goto L12
            java.lang.String r0 = "Oversea not down."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.c(r1, r0)
            return
        L12:
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r0 = " nowTime : "
            java.lang.Long r4 = java.lang.Long.valueOf(r2)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r4}
            health.compact.a.LogUtil.c(r1, r0)
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r0 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.String r4 = "DownloadDeviceResource"
            java.lang.Object r0 = health.compact.a.Services.c(r4, r0)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r0 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r0
            java.util.List r0 = r0.getIndexList()
            if (r0 == 0) goto Lc3
            int r0 = r0.size()
            if (r0 != 0) goto L3b
            goto Lc3
        L3b:
            android.content.Context r0 = r12.am
            r5 = 10008(0x2718, float:1.4024E-41)
            java.lang.String r6 = java.lang.String.valueOf(r5)
            java.lang.String r7 = "download_plugin_source"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r6, r7)
            java.lang.String r6 = " lastDownloadTime : "
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r0}
            health.compact.a.LogUtil.c(r1, r6)
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 != 0) goto L91
            r6 = 1
            long r8 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L7e
            long r8 = r2 - r8
            r10 = 86400000(0x5265c00, double:4.2687272E-316)
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            r8 = 0
            if (r0 >= 0) goto L74
            java.lang.Object[] r0 = new java.lang.Object[r6]     // Catch: java.lang.NumberFormatException -> L71
            java.lang.String r6 = "The interval was less than one days"
            r0[r8] = r6     // Catch: java.lang.NumberFormatException -> L71
            health.compact.a.LogUtil.c(r1, r0)     // Catch: java.lang.NumberFormatException -> L71
            goto L8d
        L71:
            r0 = move-exception
            r6 = r8
            goto L7f
        L74:
            java.lang.Object[] r0 = new java.lang.Object[r6]     // Catch: java.lang.NumberFormatException -> L7e
            java.lang.String r9 = "The interval was longer than one days"
            r0[r8] = r9     // Catch: java.lang.NumberFormatException -> L7e
            health.compact.a.LogUtil.c(r1, r0)     // Catch: java.lang.NumberFormatException -> L7e
            goto L91
        L7e:
            r0 = move-exception
        L7f:
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.e(r1, r0)
            if (r6 == 0) goto L8d
            goto L91
        L8d:
            r12.x()
            goto Lc2
        L91:
            java.lang.String r0 = "The interval was more than one days"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.c(r1, r0)
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            java.lang.String r1 = java.lang.String.valueOf(r5)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r3 = 0
            health.compact.a.SharedPreferenceManager.e(r0, r1, r7, r2, r3)
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r0 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.Object r0 = health.compact.a.Services.c(r4, r0)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r0 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r0
            r0.downloadIndexAll()
            java.lang.Class<com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi> r0 = com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi.class
            java.lang.Object r0 = health.compact.a.Services.c(r4, r0)
            com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi r0 = (com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi) r0
            java.lang.String r1 = "SMART_ALL"
            r0.downloadIndexByDeviceType(r1)
        Lc2:
            return
        Lc3:
            java.lang.String r0 = " startDownloadIndex indexList is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.a(r1, r0)
            java.util.concurrent.ExecutorService r0 = r12.x
            oxv r1 = new oxv
            r1.<init>()
            r0.execute(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homewear21.home.WearHomeActivity.az():void");
    }

    public static /* synthetic */ void c() {
        LogUtil.c("WearHomeActivity", " startDownloadIndex indexList");
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByDeviceType("SMART_ALL");
    }

    private void x() {
        DeviceInfo a2 = jpt.a("WearHomeActivity");
        if (a2 != null) {
            String d = jfu.d(a2.getProductType());
            LogUtil.c("WearHomeActivity", "download uuid：", d);
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(d)) {
                return;
            }
            LogUtil.c("WearHomeActivity", "downloadCurrentDeviceResource downloadIndexByUuidList");
            ArrayList arrayList = new ArrayList();
            arrayList.add(d);
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(arrayList, null);
        }
    }

    private void ai() {
        this.aj = (CustomTitleBar) nsy.cMc_(this, R.id.wear_home_titlebar);
        this.bg = (HealthRecycleView) nsy.cMc_(this, R.id.recyclerView);
        this.v = (WearHomeSyncCard) nsy.cMc_(this, R.id.sync_card_view);
        this.f9644a = this.d.e(this.g);
        this.aj.setTitleTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.aj.setRightButtonVisibility(0);
        this.aj.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430193_res_0x7f0b0b31), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.aj.setTitleBarBackgroundColor(this.am.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.aj.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: oxt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WearHomeActivity.this.djJ_(view);
            }
        });
    }

    public /* synthetic */ void djJ_(View view) {
        b(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(boolean z2) {
        LogUtil.c("WearHomeActivity", "exitActivity isBackPressed:", Boolean.valueOf(z2), ", mAnimationObject:", this.ai, ", mIsFromSmartLife:", Boolean.valueOf(this.o));
        this.n = true;
        this.bc = z2;
        if (this.ai != null && (this.o || this.aw)) {
            this.n = false;
            finish();
        } else {
            if (z2) {
                if (this.au) {
                    ah();
                }
                bh();
                super.onBackPressed();
                return;
            }
            bh();
            finish();
        }
    }

    private void bh() {
        if (this.o && this.bf == null) {
            LogUtil.a("WearHomeActivity", "toBackAndRemoveMap params valid");
        } else if (isTaskRoot()) {
            moveTaskToBack(this.o);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.c("WearHomeActivity", "onConfigurationChanged");
        pct pctVar = this.p;
        if (pctVar != null) {
            pctVar.g();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.o = intent.getBooleanExtra("FROM_SMART_LIFE", false);
                this.bf = intent.getSourceBounds();
            } catch (Exception unused) {
                LogUtil.e("WearHomeActivity", "loadApplicationTheme Exception");
            }
            if (this.o && this.bf != null) {
                LogUtil.c("WearHomeActivity", "loadApplicationTheme from smartLift");
                pep.dmW_(this, 2);
                return;
            } else {
                pep.dmW_(this, 1);
                return;
            }
        }
        pep.dmW_(this, 1);
    }

    private void ad() {
        this.am = this;
        jfu.f();
        pds.d();
        Context context = BaseApplication.getContext();
        this.ag = context;
        this.d = DeviceSettingsInteractors.d(context);
        this.q = new NotificationPushInteractor(this.ag);
        this.h = jjb.b();
        this.r = new pbg(this.am, this);
        this.j = jqi.a();
        this.ar = new GuideInteractors(this.ag);
        this.bh = rvo.e(this.am.getApplicationContext());
    }

    private void o() {
        if (jad.d(36) && pep.f()) {
            DeviceInfo deviceInfo = this.b;
            if (deviceInfo != null && !jfu.c(deviceInfo.getProductType()).ag()) {
                LogUtil.c("WearHomeActivity", "hide hotline");
            } else {
                String string = BaseApplication.getContext().getString(R.string._2130841514_res_0x7f020faa);
                this.ah = string;
                this.ae.add(string);
            }
        }
        if (jad.d(29)) {
            if (cvz.c(this.b)) {
                this.ak = BaseApplication.getContext().getString(R.string._2130844124_res_0x7f0219dc);
            } else {
                this.ak = BaseApplication.getContext().getString(R.string._2130847825_res_0x7f022851);
            }
            DeviceInfo deviceInfo2 = this.b;
            if (deviceInfo2 != null && !jfu.c(deviceInfo2.getProductType()).ai()) {
                LogUtil.c("WearHomeActivity", "hide huafen");
            } else {
                this.ae.add(this.ak);
            }
        }
    }

    private void n() {
        LogUtil.c("WearHomeActivity", "enter addDetectionItem");
        if (this.i || !pep.i()) {
            return;
        }
        LogUtil.c("WearHomeActivity", "show detection entrance");
        String string = BaseApplication.getContext().getString(R.string._2130846072_res_0x7f022178);
        this.ap = string;
        this.ae.add(string);
    }

    private void m() {
        this.an = BaseApplication.getContext().getString(R.string.IDS_device_wear_home_delete_device);
        this.ae.clear();
        this.ae.add(this.an);
        o();
        if (this.b != null && dks.d(BaseApplication.getContext()) && jfu.n(this.b.getProductType())) {
            Context context = this.am;
            if (SharedPreferenceManager.e(context, LoginInit.getInstance(context).getAccountInfo(1011))) {
                String string = BaseApplication.getContext().getString(R.string._2130844137_res_0x7f0219e9);
                this.ao = string;
                this.ae.add(string);
            }
        }
        l();
        n();
        this.aj.setRightButtonOnClickListener(new View.OnClickListener() { // from class: oxm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WearHomeActivity.this.djH_(view);
            }
        });
    }

    public /* synthetic */ void djH_(View view) {
        oau.d(1);
        PopViewList popViewList = new PopViewList(this.am, this.aj, this.ae, ae(), this.i);
        this.be = popViewList;
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: oxh
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                WearHomeActivity.this.b(i);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void b(int i) {
        if (i < 0 || i >= this.ae.size()) {
            LogUtil.a("WearHomeActivity", "position out of size");
            return;
        }
        String str = this.ae.get(i);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("WearHomeActivity", "type is null");
            return;
        }
        if (str.equals(this.an)) {
            SharedPreferenceManager.e(Integer.toString(1000), "wearable_unpair_reconnection", false);
            oau.d(2);
            this.r.b();
            return;
        }
        if (str.equals(this.ah)) {
            t();
            return;
        }
        if (str.equals(this.ak)) {
            LogUtil.c("WearHomeActivity", "hua fen");
            this.r.a();
            return;
        }
        if (str.equals(this.ba)) {
            LogUtil.c("WearHomeActivity", "legal information");
            this.r.c(this.g, this.aj.getViewTitle().getText().toString(), this.b);
        } else if (str.equals(this.ao)) {
            this.r.d();
        } else if (str.equals(this.ap)) {
            this.r.d("REMOTE_TYPE");
        } else {
            LogUtil.a("WearHomeActivity", "popViewList setOnClick other");
        }
    }

    private void t() {
        LogUtil.c("WearHomeActivity", "callHotline isHonorDevice: ", Boolean.valueOf(cvz.c(this.b)));
        LogUtil.c("MainUI", 0, "WearHomeActivity", "call hotline");
        this.r.c(cvz.c(this.b));
    }

    private void l() {
        if (this.f9644a == null || !ae()) {
            return;
        }
        String string = BaseApplication.getContext().getString(R.string._2130843844_res_0x7f0218c4);
        this.ba = string;
        this.ae.add(string);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.c("WearHomeActivity", "Enter onActivityResult()");
        super.onActivityResult(i, i2, intent);
        LogUtil.c("WearHomeActivity", "data:", intent);
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("revoke_honor_privacy", false);
            LogUtil.c("WearHomeActivity", "isRevokeDevicePrivacy:", Boolean.valueOf(booleanExtra));
            if (booleanExtra) {
                this.r.m();
                cof.d(false);
            }
        }
        if (i == 3001) {
            if (i2 == -1) {
                d(0);
                jfo.e();
                jfo.e("0");
                this.as.sendEmptyMessageDelayed(300005, 2000L);
                return;
            }
            d(1);
            jfo.e();
            jfo.e("1");
        }
    }

    private boolean ae() {
        DeviceCapability e = this.d.e(this.g);
        if (e != null) {
            return e.isSupportLegalOpenSource() || e.isSupportLegalUserAgreement() || e.isSupportLegalPrivacy() || e.isSupportLegalServiceStatement() || e.isSupportLegalSourceStatement() || e.isSupportLegalSystemWebView();
        }
        LogUtil.a("WearHomeActivity", "isSupportLegal deviceCapability is null");
        return false;
    }

    private void r() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo != null) {
            this.g = deviceInfo.getDeviceIdentify();
            this.m = this.b.getSecurityUuid();
            bd();
            this.i = this.b.getDeviceConnectState() == 2;
            this.f = this.b.getProductType();
            LogUtil.c("MainUI", 0, "WearHomeActivity", "Enter onCreateView mIsConnected:", Boolean.valueOf(this.i), " state:", Integer.valueOf(this.b.getDeviceConnectState()));
        }
    }

    private void ag() {
        this.bg.setLayoutManager(new LinearLayoutManager(this));
        this.bg.setAdapter(this.ab);
        this.bg.a(false);
        this.bg.d(false);
        this.bg.setLayerType(2, null);
        this.bg.setItemAnimator(new DefaultItemAnimator());
    }

    private void ac() {
        this.y = new pcw(this.am, this);
        this.bl = new pdw(this.am, this);
        this.ad = new pdz(this, this.g);
        this.s = new pbu(this.am, this);
        this.p = new pct(this.am, this);
        this.bm.add(this.y);
        this.bm.add(this.bl);
        this.bm.add(this.ad);
        this.bm.add(this.s);
        this.bm.add(this.p);
        LogUtil.c("WearHomeActivity", "initCardConstructor end, activity: ", this, ", mWearHomeGeneralCard: ", this.p);
    }

    public void d(int i) {
        this.bg.setAdapter(this.ab);
        this.v.setVisibility(0);
        this.v.e(i);
    }

    public void h() {
        LogUtil.c("WearHomeActivity", "refreshTipCard");
        if (this.u == null) {
            this.u = new pdp(this.am, this);
        }
        if (!this.bm.contains(this.u)) {
            this.bm.add(0, this.u);
        }
        b(this.u);
        this.bm.remove(this.w);
        this.ab.c(this.bm);
        this.ab.notifyDataSetChanged();
    }

    public void c(boolean z2) {
        LogUtil.c("WearHomeActivity", "setBRDevice:", Boolean.valueOf(z2));
        if (this.u == null) {
            this.u = new pdp(this.am, this);
        }
        this.u.b(z2);
    }

    private void an() {
        LogUtil.c("WearHomeActivity", "refreshPermissionTipCard");
        if (this.w == null) {
            this.w = new pds(this.am, this);
        }
        if (!this.bm.contains(this.w)) {
            this.bm.add(0, this.w);
        }
        b(this.w);
        this.bm.remove(this.u);
        this.ab.c(this.bm);
        this.ab.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        LogUtil.c("WearHomeActivity", "initOperationAdapter.");
        if (PluginOperation.getInstance(this.am).getAdapter() instanceof PluginOperationAdapter) {
            return;
        }
        LogUtil.c("WearHomeActivity", "initOperationAdapter, adapter is null.");
        PluginOperation pluginOperation = PluginOperation.getInstance(this.am);
        pluginOperation.setAdapter(PluginOperationAdapterImpl.getInstance(this.am));
        pluginOperation.init(this.am);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.c("WearHomeActivity", "onResume. activity: ", this);
        this.av = true;
        BroadcastManagerUtil.bFA_(this.am, this.bj, new IntentFilter(Constants.CLICK_STATUS_BAR_ACTION), Constants.SYSTEM_UI_PERMISSION, null);
        super.onResume();
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo != null) {
            this.i = deviceInfo.getDeviceConnectState() == 2;
        }
        as();
        y();
        u();
        v();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.av = false;
        unregisterReceiver(this.bj);
    }

    private void y() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2 && this.av && jkj.d().c(this.g) != 6 && this.l) {
            djG_().sendEmptyMessage(1004);
        }
    }

    private void u() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo == null) {
            LogUtil.a("WearHomeActivity", "getWheelSize mCurrentDeviceInfo is null.");
            return;
        }
        boolean z2 = deviceInfo.getDeviceConnectState() == 2;
        boolean c = cwi.c(this.b, 98);
        if (z2 && this.av && jkj.d().c(this.g) != 6 && c) {
            LogUtil.c("WearHomeActivity", "Support getWheelSize.");
            ak();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        this.f9644a = this.d.e(this.g);
        at();
        ap();
        s();
        ao();
        bc();
        ax();
        ay();
        ThreadPoolManager.d().execute(new Runnable() { // from class: oxs
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeActivity.this.i();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.c("MainUI", 0, "WearHomeActivity", "onBackPressed mIsFromWear", Boolean.valueOf(this.au));
        b(true);
    }

    @Override // android.app.Activity
    public void finish() {
        if (!this.n) {
            LogUtil.c("WearHomeActivity", "finish mAnimationObject:", this.ai, ", mIsFromSmartLife:", Boolean.valueOf(this.o), ",mIsFromDisConnect:", Boolean.valueOf(this.aw));
            obt obtVar = this.ai;
            if (obtVar != null && (this.o || this.aw)) {
                obtVar.d(this.ac);
            } else {
                ba();
            }
            this.n = true;
            return;
        }
        ba();
    }

    private void ba() {
        super.finish();
        pep.c(this.am);
    }

    public void b(Class<?> cls) {
        try {
            this.am.startActivity(new Intent(this.am, cls));
        } catch (ActivityNotFoundException unused) {
            LogUtil.e("WearHomeActivity", "ActivityNotFoundException classType");
        }
    }

    public void a(Class<?> cls, int i) {
        Intent intent = new Intent(this.am, cls);
        intent.putExtra("device_id", this.g);
        startActivityForResult(intent, i);
    }

    private void at() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo == null || !cwi.c(deviceInfo, 45)) {
            LogUtil.a("WearHomeActivity", "showFirstEcgServiceDialog not support.");
            return;
        }
        boolean c = this.ar.c();
        jfq jfqVar = this.aq;
        boolean z2 = !TextUtils.equals("-1", jfqVar != null ? jfqVar.getSharedPreference("KEY_SHOW_ECG_SERVICE_ACTIVATION_TIP_FLAG") : "");
        LogUtil.c("WearHomeActivity", "showFirstEcgServiceDialog isFromPairSuccess:", Boolean.valueOf(c), " isNeedShowEcgTip:", Boolean.valueOf(z2), " isOversea:", Boolean.valueOf(Utils.o()));
        if (c && z2 && !Utils.o()) {
            jgc.a().e(new IBaseResponseCallback() { // from class: oxo
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    WearHomeActivity.this.c(i, obj);
                }
            }, this.b);
        }
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (!(obj instanceof EcgServiceActivationData)) {
            LogUtil.c("WearHomeActivity", "handleShowEcgService object error.");
            return;
        }
        EcgServiceActivationData ecgServiceActivationData = (EcgServiceActivationData) obj;
        if (ecgServiceActivationData.getStatus() == 0 || (ecgServiceActivationData.getStatus() == 1 && ecgServiceActivationData.getProbationUser() == 0)) {
            LogUtil.c("WearHomeActivity", "handleShowEcgService show FirstEcgServiceDialog.");
            pel pelVar = this.as;
            if (pelVar != null) {
                pelVar.sendEmptyMessage(1034);
            }
        }
    }

    private void bc() {
        boolean c = this.ar.c();
        LogUtil.c("MainUI", 0, "WearHomeActivity", "Enter onResume private void showRateReminderDialog() is ", Boolean.valueOf(c));
        if (c) {
            d(EnumDialog.RATE);
        }
    }

    private void ax() {
        if (this.b == null) {
            LogUtil.c("MainUI", 0, "WearHomeActivity", "mCurrentDeviceInfo is null");
            return;
        }
        boolean a2 = this.ar.a();
        boolean k = jfu.k(this.b.getProductType());
        LogUtil.c("MainUI", 0, "WearHomeActivity", "showEarMuffReminderDialog isFromPairSuccess:", Boolean.valueOf(a2), "isSupportEarMuff:", Boolean.valueOf(k));
        if (a2) {
            this.ar.b(false);
            if (k) {
                this.r.i();
            }
        }
    }

    private void ay() {
        if (this.b == null) {
            return;
        }
        boolean j = this.ar.j();
        String b = dis.b(this.b.getDeviceIdentify());
        if (this.ar.e(b)) {
            LogUtil.c("WearHomeActivity", "transfer data to new version");
            this.ar.b(b);
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10005), b);
        }
        boolean e = this.ar.e("keyUserGuidePage" + b);
        LogUtil.c("WearHomeActivity", "Enter onResume private void showUserGuidDialog() is ", Boolean.valueOf(j), "showUserGuideFlagValue is", Boolean.valueOf(e));
        if (j || !e) {
            this.ar.a(false);
            this.r.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bb, reason: merged with bridge method [inline-methods] */
    public void i() {
        boolean d = this.ar.d(this.g);
        LogUtil.c("WearHomeActivity", "Enter onResume isShowSyncAccount is ", Boolean.valueOf(d));
        if (d) {
            runOnUiThread(new Runnable() { // from class: oxx
                @Override // java.lang.Runnable
                public final void run() {
                    WearHomeActivity.this.f();
                }
            });
        }
    }

    private void d(EnumDialog enumDialog) {
        LogUtil.c("MainUI", 0, "WearHomeActivity", "dialogMessage Enter addNewMessage:", enumDialog, " mMessageListEnum.size():", Integer.valueOf(this.az.size()));
        if (AnonymousClass6.d[enumDialog.ordinal()] == 1) {
            this.az.add(enumDialog);
            if (this.az.size() == 1) {
                au();
                return;
            }
            return;
        }
        LogUtil.a("WearHomeActivity", "no need Deal other msg.");
    }

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeActivity$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[EnumDialog.values().length];
            d = iArr;
            try {
                iArr[EnumDialog.RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void au() {
        this.ar.e(false);
        this.r.e(this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: aw, reason: merged with bridge method [inline-methods] */
    public void f() {
        this.r.f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        this.ad.b();
        this.s.l();
        this.p.b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("WearHomeActivity", "onDestroy. activity: ", this);
        this.as.removeCallbacksAndMessages(null);
        this.as = null;
        pep.dmY_(this.am, this.al);
        pep.dmY_(this.am, this.bd);
        BluetoothSwitchStateUtil bluetoothSwitchStateUtil = this.c;
        if (bluetoothSwitchStateUtil != null) {
            bluetoothSwitchStateUtil.b();
        }
        WechatDeviceProviderHelper wechatDeviceProviderHelper = this.bk;
        if (wechatDeviceProviderHelper != null) {
            wechatDeviceProviderHelper.e();
        }
        aj();
        this.y = null;
        this.bl = null;
        this.ad = null;
        this.s = null;
        this.p = null;
        this.u = null;
        this.w = null;
        this.r.n();
        this.r.k();
        this.x.shutdown();
        jnr.b().c();
    }

    private void aj() {
        e(this.y);
        e(this.bl);
        e(this.ad);
        e(this.s);
        e(this.p);
        e(this.u);
        e(this.w);
    }

    private void e(WearHomeBaseCard wearHomeBaseCard) {
        if (wearHomeBaseCard != null) {
            wearHomeBaseCard.onDestroy();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Intent intent2;
        super.onNewIntent(intent);
        if (intent == null) {
            return;
        }
        if ((this.ay || intent.getBooleanExtra("FROM_APP_HOME_PAGE_TYPE", false)) && (intent2 = getIntent()) != null) {
            String stringExtra = intent2.getStringExtra("device_id");
            ReleaseLogUtil.b("R_WearHomeActivity", "onNewIntent mIsRequestPermissionNow:", Boolean.valueOf(this.ay), " deviceId:", stringExtra);
            if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(intent.getStringExtra("device_id"))) {
                return;
            }
        }
        setIntent(intent);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public static boolean e() {
        return oyf.d();
    }

    public static void a(boolean z2) {
        oyf.e(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ap() {
        b(this.w);
        b(this.y);
        b(this.ad);
        b(this.s);
        b(this.p);
    }

    private void b(WearHomeBaseCard wearHomeBaseCard) {
        if (wearHomeBaseCard != null) {
            wearHomeBaseCard.onResume();
        }
    }

    private void a(int i) {
        d(this.y, i);
        d(this.ad, i);
        d(this.s, i);
        d(this.p, i);
        d(this.u, i);
        d(this.w, i);
    }

    private void d(WearHomeBaseCard wearHomeBaseCard, int i) {
        if (wearHomeBaseCard != null) {
            wearHomeBaseCard.deviceConnectionChange(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bd() {
        ReleaseLogUtil.b("R_WearHomeActivity", "enter updateTitle");
        ThreadPoolManager.d().execute(new Runnable() { // from class: oxu
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeActivity.this.g();
            }
        });
    }

    public /* synthetic */ void g() {
        final boolean z2;
        ReleaseLogUtil.b("R_WearHomeActivity", "updateTitle subThread");
        ArrayList<cpm> a2 = jfv.a();
        if (a2 != null && a2.size() > 0) {
            Iterator<cpm> it = a2.iterator();
            while (it.hasNext()) {
                cpm next = it.next();
                if (next.a().equals(this.g)) {
                    z2 = next.l();
                    break;
                }
            }
        }
        z2 = true;
        ReleaseLogUtil.b("R_WearHomeActivity", "isOnlyOneWearableDevice:", Boolean.valueOf(z2));
        runOnUiThread(new Runnable() { // from class: oxn
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeActivity.this.e(z2);
            }
        });
    }

    public /* synthetic */ void e(boolean z2) {
        ReleaseLogUtil.b("R_WearHomeActivity", "updateTitle MainThread");
        String b = pep.b(this.am, this.g, z2);
        ReleaseLogUtil.b("R_WearHomeActivity", "titleName:", b);
        this.aj.setTitleText(b);
    }

    private void af() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        if (intent.hasExtra("watch_face_service_flag")) {
            LogUtil.a("WearHomeActivity", "watch face service flag");
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        this.g = stringExtra;
        if (stringExtra == null) {
            String stringExtra2 = intent.getStringExtra("DEVICE_ID");
            this.g = stringExtra2;
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.g = iyg.c(this.g);
            } else {
                this.g = "";
            }
        }
        this.o = intent.getBooleanExtra("FROM_SMART_LIFE", false);
        this.bb = intent.getBooleanExtra("Delete", false);
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.a("WearHomeActivity", "mac is empty");
            this.b = oxa.a().f();
        } else {
            this.b = oxa.a().b(this.g);
        }
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo != null) {
            ReleaseLogUtil.b("R_WearHomeActivity", "initParams mCurrentDeviceInfo deviceName:", CommonUtil.l(deviceInfo.getDeviceName()), " Identify:", CommonUtil.l(this.b.getDeviceIdentify()), " ProductType:", Integer.valueOf(this.b.getProductType()));
            this.g = this.b.getDeviceIdentify();
            this.ax = true;
            this.k = this.b.getPowerSaveModel() == 1;
            this.l = cwi.c(this.b, 78);
            if (!Utils.o() && !LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
                this.bk = new WechatDeviceProviderHelper(this.b, this.as);
            }
        }
        this.aq = jfq.c();
        LogUtil.c("MainUI", 0, "WearHomeActivity", "isCheckEnableDevice:", Boolean.valueOf(oxa.a().e()));
        djE_(intent);
    }

    private void djE_(Intent intent) {
        DeviceInfo deviceInfo;
        try {
            this.au = intent.getBooleanExtra("mIsFromWear", false);
            if ("com.huawei.iconnect.action.SHOW_DEVICE".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("DEVICE_NAME");
                if (stringExtra != null && (deviceInfo = this.b) != null && !stringExtra.equals(deviceInfo.getDeviceName())) {
                    LogUtil.c("WearHomeActivity", "not the wanted device");
                    finish();
                } else if (stringExtra != null) {
                    if (!AuthorizationUtils.a(BaseApplication.getContext())) {
                        LogUtil.c("WearHomeActivity", "not confirm privacy");
                        ah();
                    }
                } else {
                    LogUtil.a("WearHomeActivity", "intent.getAction() is else,");
                }
            } else if (!AuthorizationUtils.a(BaseApplication.getContext())) {
                LogUtil.c("WearHomeActivity", "Action is not wanted and no confirm privacy.");
                ah();
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.e("WearHomeActivity", "ActivityNotFoundException");
            finish();
        }
        djD_(intent);
    }

    private void djD_(Intent intent) {
        Rect rect;
        this.aw = intent.getBooleanExtra("FROM_NO_CONNECT_SMART_LIFE", false);
        LogUtil.c("WearHomeActivity", "mIsFromSmartLife:", Boolean.valueOf(this.o), "mIsFromDisConnect", Boolean.valueOf(this.aw));
        boolean z2 = this.o;
        if (z2 && this.aw) {
            obt b = obw.b();
            this.ai = b;
            LogUtil.c("WearHomeActivity", "from no connect mAnimationObject:", b);
            obt obtVar = this.ai;
            if (obtVar != null) {
                obtVar.cUY_(this);
                return;
            }
            return;
        }
        if (z2 && (rect = this.bf) != null) {
            LogUtil.c("WearHomeActivity", "from smartLift sourceRect:", rect);
            obt obtVar2 = new obt();
            this.ai = obtVar2;
            obtVar2.b(intent.getBooleanExtra("isClick", false));
            this.ai.cUX_(this, intent, this.o);
            return;
        }
        LogUtil.c("WearHomeActivity", "no animation");
    }

    private void ah() {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), "com.huawei.health.MainActivity");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        startActivity(intent);
        finish();
    }

    private void s() {
        LogUtil.c("WearHomeActivity", "checkGpsSwitch");
        if (this.ax) {
            av();
        } else {
            h();
        }
        LogUtil.c("WearHomeActivity", "mWearHomeBaseCards.size() = ", Integer.valueOf(this.bm.size()), "contains is ", Boolean.valueOf(this.bm.contains(this.p)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x006f, code lost:
    
        an();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006d, code lost:
    
        if ("true".equals(r0) == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0048, code lost:
    
        if ((!"true".equals(r0)) != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void av() {
        /*
            r5 = this;
            java.lang.String r0 = "need show gps switch note"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "WearHomeActivity"
            health.compact.a.LogUtil.c(r1, r0)
            r0 = 0
            r5.ax = r0
            r5.at = r0
            android.content.Context r0 = r5.am
            boolean r0 = defpackage.kch.d(r0)
            if (r0 != 0) goto L1f
            pbu r0 = r5.s
            r0.k()
            goto L22
        L1f:
            r5.p()
        L22:
            boolean r0 = r5.at
            java.lang.String r2 = "true"
            r3 = 10006(0x2716, float:1.4021E-41)
            if (r0 == 0) goto L4b
            android.content.Context r0 = r5.am
            java.lang.String r3 = java.lang.Integer.toString(r3)
            java.lang.String r4 = "wear_activity_tip_no_againlocations_permissions_tip"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r3, r4)
            java.lang.String r3 = "mIsNeedLocationPermissionToast notRemind:"
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r0}
            health.compact.a.LogUtil.c(r1, r3)
            boolean r0 = r2.equals(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L73
            goto L6f
        L4b:
            android.content.Context r0 = r5.am
            boolean r0 = com.huawei.ui.device.utlis.BluetoothPermisionUtils.e(r0)
            if (r0 != 0) goto L73
            android.content.Context r0 = r5.am
            java.lang.String r3 = java.lang.Integer.toString(r3)
            java.lang.String r4 = "wear_activity_tip_no_againnearby_permissions_tip"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r3, r4)
            java.lang.String r3 = "checkBluetoothPermission notRemind:"
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r0}
            health.compact.a.LogUtil.c(r1, r3)
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L73
        L6f:
            r5.an()
            goto L76
        L73:
            r5.h()
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homewear21.home.WearHomeActivity.av():void");
    }

    private void p() {
        if (PermissionUtil.c()) {
            LogUtil.c("WearHomeActivity", "The SDK version is Q");
            if (jdi.c(this.ag, aa)) {
                return;
            }
            q();
            return;
        }
        LogUtil.c("WearHomeActivity", "The SDK version is lower than Q");
        Context context = this.ag;
        String[] strArr = z;
        if (jdi.c(context, strArr) || cvt.c(this.f)) {
            return;
        }
        c(strArr);
    }

    private void am() {
        ArrayList arrayList = new ArrayList(16);
        pep.b(this.b, arrayList);
        pep.f(this.b, arrayList);
        pep.d(this.b, arrayList);
        pep.c(this.b, arrayList);
        pep.i(this.b, arrayList);
        pep.a(this.b, arrayList);
        pep.h(this.b, arrayList);
        pep.e(this.b, arrayList);
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        ArrayList arrayList2 = new ArrayList(16);
        if (pep.a("wear_permission_time") && !jdi.c(this.am, strArr)) {
            arrayList2.addAll(arrayList);
            KeyValDbManager.b(BaseApplication.getContext()).e("wear_permission_time", String.valueOf(System.currentTimeMillis()));
        }
        ArrayList arrayList3 = new ArrayList(16);
        pep.d(this.f9644a, arrayList3);
        String[] strArr2 = new String[arrayList3.size()];
        arrayList3.toArray(strArr2);
        if (pep.a("wear_local_permission_time") && !jdi.c(this.am, strArr2)) {
            arrayList2.addAll(arrayList3);
            KeyValDbManager.b(BaseApplication.getContext()).e("wear_local_permission_time", String.valueOf(System.currentTimeMillis()));
        }
        LogUtil.c("WearHomeActivity", "requestPermissions permissionList is ", arrayList2);
        int size = arrayList2.size();
        String[] strArr3 = new String[size];
        arrayList2.toArray(strArr3);
        if (size > 0) {
            this.ay = true;
            jdi.bFL_(this, strArr3, new PermissionsResultAction() { // from class: com.huawei.ui.homewear21.home.WearHomeActivity.1
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.c("WearHomeActivity", "requestPermissions onGranted");
                    WearHomeActivity.this.ay = false;
                    if (WearHomeActivity.this.f9644a != null && WearHomeActivity.this.f9644a.isSupportSendSosSms()) {
                        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).registerContactChangeObserver();
                    }
                    pes.c();
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    LogUtil.c("WearHomeActivity", "requestPermissions onDenied");
                    WearHomeActivity.this.ay = false;
                    pes.c();
                }
            });
        } else if (nrk.d(BaseApplication.getContext())) {
            pes.c();
        } else {
            LogUtil.a("WearHomeActivity", "requestPermissions not hasCalendarPermission");
        }
    }

    private void c(String[] strArr) {
        pep.dmV_(this, strArr, new PermissionsResultAction() { // from class: com.huawei.ui.homewear21.home.WearHomeActivity.2
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.c("WearHomeActivity", "requestPermissions onGranted");
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.c("WearHomeActivity", "requestPermissions onDenied");
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 20192) {
            if (iArr == null || strArr == null || strArr.length == 0) {
                LogUtil.a("WearHomeActivity", "onRequestPermissionsResult permission or grantResult is null");
            } else if (c(strArr, iArr)) {
                be();
            }
        }
    }

    private boolean c(String[] strArr, int[] iArr) {
        DeviceCapability deviceCapability = this.f9644a;
        boolean z2 = deviceCapability != null && deviceCapability.isSupportSosTransmission();
        LogUtil.c("WearHomeActivity", "isSupportSosTransmission:" + z2);
        for (int i = 0; i < strArr.length; i++) {
            if (TextUtils.equals(strArr[i], "android.permission.READ_CONTACTS") && iArr[i] == 0 && z2) {
                return true;
            }
        }
        return false;
    }

    private void be() {
        DeviceCommand e = kdn.e(kdn.a(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getEmergencyInfo()));
        if (e != null) {
            jfq.c().b(e);
        }
    }

    private void q() {
        if (!pet.e(this)) {
            c(z);
            this.at = true;
            LogUtil.c("WearHomeActivity", "need request permission");
        } else {
            if (pet.c(this)) {
                return;
            }
            this.at = true;
            LogUtil.a("WearHomeActivity", "need background permission");
        }
    }

    public void e(EnumDialog enumDialog) {
        LogUtil.c("MainUI", 0, "WearHomeActivity", "removeMessage Enter:", enumDialog);
        Iterator<EnumDialog> it = this.az.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (enumDialog == it.next()) {
                i++;
            }
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.az.remove(enumDialog);
        }
        ar();
    }

    private void ar() {
        LogUtil.c("MainUI", 0, "WearHomeActivity", "dialogMessage Enter showDialog:", " mMessageList.size():", Integer.valueOf(this.az.size()));
        if (this.az.size() > 0) {
            LogUtil.c("MainUI", 0, "WearHomeActivity", "dialogMessage dialog type:", this.az.get(0));
            if (this.az.get(0) == EnumDialog.RATE) {
                au();
            } else {
                LogUtil.c("MainUI", 0, "WearHomeActivity", "dialogMessage dialog type enter else");
            }
        }
    }

    public boolean c(String str) {
        if (this.b == null) {
            LogUtil.c("getDeviceBluetoothType mCurrentDeviceInfo is null callMethod:", str);
            this.b = oxa.a().b(this.g);
        }
        if (this.b != null) {
            return false;
        }
        boolean z2 = jez.e() == null;
        OpAnalyticsUtil.getInstance().setRiskWarningEvent("SensitiveNotification", "isNullDeviceInfo DeviceInfo  is null,PhoneServiceBinder  is :" + z2 + ",callMethod " + str);
        ReleaseLogUtil.b("R_WearHomeActivity", "isNullDeviceInfo mCurrentDeviceInfo is null ", Boolean.valueOf(z2), ",callMethod ", str);
        return true;
    }

    public void b(DeviceInfo deviceInfo) {
        this.as.removeMessages(1013);
        this.as.sendEmptyMessageDelayed(1013, 1000L);
        if (deviceInfo == null) {
            LogUtil.a("WearHomeActivity", "deviceInfo is null!");
            return;
        }
        this.k = deviceInfo.getPowerSaveModel() == 1;
        if (!this.g.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.d("WearHomeActivity", "deviceConnectionChange return!");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.d("WearHomeActivity", "deviceConnectionChange(): ", deviceInfo.getDeviceName(), ",state = ", Integer.valueOf(deviceConnectState));
        DeviceInfo b = oxa.a().b(this.g);
        this.b = b;
        if (b != null) {
            this.g = b.getDeviceIdentify();
            LogUtil.c("MainUI", 0, "WearHomeActivity", "deviceInfo.getProductType():", Integer.valueOf(deviceInfo.getProductType()));
            if (c(deviceInfo, deviceConnectState)) {
                return;
            }
        } else {
            LogUtil.a("WearHomeActivity", "mCurrentDeviceInfo is null");
        }
        bd();
        b(deviceInfo, deviceConnectState);
        a(deviceConnectState);
        m();
        LogUtil.c("WearHomeActivity", "deviceConnectionChange() mIsConnected: ", Boolean.valueOf(this.i));
    }

    private void c(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 112);
        LogUtil.c("WearHomeActivity", "afterConnectedProcess. isSupportEarPhone: ", Boolean.valueOf(c));
        if (c && CommonUtil.h(this.am, "com.huawei.ui.homewear21.home.WearHomeActivity")) {
            LogUtil.c("WearHomeActivity", "afterConnectedProcess. in");
            jgs.c().c(deviceInfo, new AnonymousClass8(deviceInfo));
        }
    }

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements IBaseResponseCallback {
        final /* synthetic */ DeviceInfo d;

        AnonymousClass8(DeviceInfo deviceInfo) {
            this.d = deviceInfo;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WearHomeActivity wearHomeActivity = WearHomeActivity.this;
            final DeviceInfo deviceInfo = this.d;
            wearHomeActivity.runOnUiThread(new Runnable() { // from class: oxz
                @Override // java.lang.Runnable
                public final void run() {
                    WearHomeActivity.AnonymousClass8.this.b(deviceInfo);
                }
            });
        }

        public /* synthetic */ void b(DeviceInfo deviceInfo) {
            Intent intent = new Intent();
            intent.setClass(WearHomeActivity.this.am, EarphonePairActivity.class);
            intent.putExtra("device_identify", deviceInfo.getDeviceIdentify());
            WearHomeActivity.this.startActivity(intent);
        }
    }

    private void b(DeviceInfo deviceInfo, int i) {
        if (i == 2) {
            y();
            u();
            v();
            d(this.b);
            this.i = true;
            z();
            String securityUuid = deviceInfo.getSecurityUuid() == null ? "" : deviceInfo.getSecurityUuid();
            LogUtil.d("WearHomeActivity", "currentUuid:", securityUuid, "  mLastDeviceUuid:", this.m);
            if (!TextUtils.isEmpty(securityUuid) && !securityUuid.equals(this.m)) {
                this.m = securityUuid;
                am();
            } else {
                LogUtil.d("WearHomeActivity", "deviceConnectionChange() mac error or same mac");
            }
            oxd.a().d();
            c(this.b);
            return;
        }
        if (i != 11) {
            if (i == 12) {
                a(deviceInfo);
                return;
            } else {
                this.i = false;
                return;
            }
        }
        int deviceFactoryReset = deviceInfo.getDeviceFactoryReset();
        LogUtil.c("WearHomeActivity", "type value", Integer.valueOf(deviceFactoryReset));
        this.ar.d(this.g, true);
        if (deviceFactoryReset == 1) {
            Intent intent = new Intent();
            intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
            intent.putExtra("device_country_code", deviceInfo.getCountryCode());
            intent.putExtra("device_emui_version", deviceInfo.getEmuiVersion());
            Bundle bundle = new Bundle();
            bundle.putParcelable("deviceInfo", deviceInfo);
            intent.putExtras(bundle);
            intent.setClass(this.am, AgreementDeclarationActivity.class);
            startActivity(intent);
        }
    }

    private void a(DeviceInfo deviceInfo) {
        this.r.b(deviceInfo);
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("WearHomeActivity", "clearDeviceCapability deviceInfo is null");
            return;
        }
        DeviceCapability deviceCapability = this.f9644a;
        if (deviceCapability == null) {
            LogUtil.a("WearHomeActivity", "clearDeviceCapability mDeviceCapability is null");
        } else if (deviceCapability.isSupportFootWear() || this.f9644a.isSupportAutoDetectMode() || this.f9644a.isSupportRunPosture()) {
            LogUtil.c("WearHomeActivity", "clearDeviceCapability clearDeviceCapabilityById");
            cvs.b(deviceInfo.getDeviceIdentify());
        }
    }

    private void w() {
        am();
        LogUtil.c("MainUI", 0, "WearHomeActivity", "enterHomeFragmentLoggedProcess()!");
        this.af.c(this.g, this.ag, null);
        oau.e(this.b);
    }

    private void z() {
        LogUtil.d("WearHomeActivity", "dataSync handlerWhenDeviceConnected");
        this.af.c(this.g, this.ag, null);
        if (!kch.d(this.am)) {
            this.s.k();
        }
        this.bh.e();
    }

    private boolean c(DeviceInfo deviceInfo, int i) {
        if (!c(this.b.getProductType()) && c(deviceInfo.getProductType())) {
            if (this.b.getProductType() != deviceInfo.getProductType() && deviceInfo.getProductType() != -1 && i != 2) {
                return true;
            }
            LogUtil.a("WearHomeActivity", "mCurrentDeviceInfo.getProductType() is deviceInfo.getProductType()");
            return false;
        }
        LogUtil.a("WearHomeActivity", "!(isWatch(mCurrentDeviceInfo.getProductType())");
        return false;
    }

    private boolean c(int i) {
        boolean z2 = i == 3 || i == 10;
        LogUtil.d("WearHomeActivity", "Enter isWatch type:", Integer.valueOf(i), " isWatchDevice:", Boolean.valueOf(z2));
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.t = false;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(this.am).e(arrayList, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void djF_(Intent intent) {
        LogUtil.c("MainUI", 0, "WearHomeActivity", "handleConnectStateChanged() Process.myPid():", Integer.valueOf(Process.myPid()));
        Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
        if (parcelableExtra instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
            ReleaseLogUtil.b("R_WearHomeActivity", "sendMsgConnectChange deviceName:", CommonUtil.l(deviceInfo.getDeviceName()), " Identify:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " connectState:", Integer.valueOf(deviceInfo.getDeviceConnectState()));
            if (TextUtils.equals(this.g, deviceInfo.getDeviceIdentify())) {
                this.i = deviceInfo.getDeviceConnectState() == 2;
                this.b = deviceInfo;
            }
            if (CommonUtil.x(this.am)) {
                LogUtil.c("MainUI", 0, "WearHomeActivity", "APP isBackground");
                return;
            }
            Message obtainMessage = this.as.obtainMessage();
            obtainMessage.what = 1011;
            obtainMessage.obj = deviceInfo;
            if (TextUtils.isEmpty(this.g)) {
                if (!TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                    this.g = deviceInfo.getDeviceIdentify();
                }
                this.as.sendMessage(obtainMessage);
                LogUtil.c("WearHomeActivity", "mDeviceMac = null");
                return;
            }
            if (TextUtils.equals(this.g, deviceInfo.getDeviceIdentify())) {
                this.as.sendMessage(obtainMessage);
            } else {
                LogUtil.a("WearHomeActivity", "mDeviceMac != deviceInfo.getDeviceIdentify()");
            }
        }
    }

    private void ao() {
        if (this.ar.e()) {
            LogUtil.c("WearHomeActivity", "send Broadcast ACTION_BIND_DEVICE_SUCCESS");
            this.as.sendEmptyMessageDelayed(1039, 2000L);
        }
    }

    public void b() {
        ReleaseLogUtil.b("WearHomeActivity", "send broadcast: BIND_DEVICE_SUCCESS");
        Intent intent = new Intent("com.huawei.health.action.BIND_DEVICE_SUCCESS");
        intent.putExtra("deviceinfo", this.b);
        this.am.sendBroadcast(intent, LocalBroadcast.c);
        this.ar.d(false);
    }

    private void ak() {
        LogUtil.c("WearHomeActivity", "Enter GET_RIM_SIZE_CODE");
        jnr.b().c(this.b, new IBaseResponseCallback() { // from class: oxp
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeActivity.this.b(i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Object obj) {
        LogUtil.c("WearHomeActivity", "onCreate sendQueryDeviceCommand onResponse errorCode,", Integer.valueOf(i));
        if (i == 100000 && (obj instanceof cvn)) {
            cvn cvnVar = (cvn) obj;
            LogUtil.c("WearHomeActivity", "onCreate onResponse,", Integer.valueOf(cvnVar.e()), HEXUtils.a(cvnVar.b()));
            if (cvnVar.e() == 2) {
                LogUtil.c("WearHomeActivity", "sendQueryDeviceCommand success");
            }
            runOnUiThread(new Runnable() { // from class: oxw
                @Override // java.lang.Runnable
                public final void run() {
                    WearHomeActivity.this.j();
                }
            });
            return;
        }
        LogUtil.c("WearHomeActivity", "sendQueryDeviceCommand responseFailed");
    }

    public /* synthetic */ void j() {
        this.s.n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        DeviceCapability deviceCapability = this.f9644a;
        if (deviceCapability == null) {
            LogUtil.a("WearHomeActivity", "getAlarmNumber mDeviceCapability is null.");
            return;
        }
        if (!deviceCapability.isEventAlarm()) {
            LogUtil.a("WearHomeActivity", "Device is not support event alarm.");
        } else if (cwi.c(this.b, 123)) {
            LogUtil.c("WearHomeActivity", "Device is support obtain alarm number.");
            jgh.d(this.am).b(this.b);
        } else {
            jgh.d(this.am).d(5);
        }
    }
}
