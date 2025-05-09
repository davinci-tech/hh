package com.huawei.ui.homehealth.device;

import android.animation.LayoutTransition;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.views.SportBannerLayout;
import com.huawei.health.marketing.views.TopBannerLayout;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetMainUserAuth;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceSubuserAuthorize;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.scrollview.HealthBottomView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.homehealth.device.DeviceFragment;
import com.huawei.ui.homehealth.device.callback.WaterMarkCallback;
import com.huawei.ui.homehealth.device.view.NestedDeviceScrollView;
import com.huawei.ui.homehealth.device.view.VmallWebview;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.bme;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.cpa;
import defpackage.cpl;
import defpackage.cpm;
import defpackage.cpp;
import defpackage.crj;
import defpackage.csf;
import defpackage.ctk;
import defpackage.cun;
import defpackage.cwi;
import defpackage.dbp;
import defpackage.dcz;
import defpackage.dpf;
import defpackage.eil;
import defpackage.fbh;
import defpackage.gmz;
import defpackage.jbs;
import defpackage.jdx;
import defpackage.jph;
import defpackage.koq;
import defpackage.kxy;
import defpackage.nrh;
import defpackage.nrs;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nwy;
import defpackage.oaz;
import defpackage.obb;
import defpackage.ogj;
import defpackage.ogr;
import defpackage.ogx;
import defpackage.pfe;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class DeviceFragment extends BaseFragment implements View.OnClickListener {
    private static final String[] c = {"device_main_share_to_sub_user"};

    /* renamed from: a, reason: collision with root package name */
    private HealthBottomView f9375a;
    private LinearLayout aa;
    private long ab;
    private Map<Integer, ResourceResultInfo> ac;
    private LinearLayout ad;
    private String ae;
    private String ai;
    private NestedDeviceScrollView aj;
    private int ak;
    private HealthSearchView al;
    private String am;
    private LinearLayout an;
    private HealthTextView ao;
    private HealthTextView ap;
    private RelativeLayout aq;
    private HealthTextView ar;
    private VmallWebview av;
    private UserProfileMgrApi ax;
    private CardDeviceFragment b;
    private CustomAlertDialog d;
    private InputBoxTemplate f;
    private LinearLayout g;
    private String h;
    private HealthColumnSystem i;
    private Context j;
    private Map<Integer, ResourceResultInfo> k;
    private CustomTitleBar l;
    private View m;
    private LinearLayout n;
    private boolean p;
    private InputBoxTemplate t;
    private boolean y;
    private LinearLayout z;
    private PermissionsResultAction ah = null;
    private boolean x = false;
    private boolean w = false;
    private boolean q = false;
    private int e = 0;
    private boolean v = false;
    private boolean u = true;
    private List<ResourceBriefInfo> ag = new ArrayList(16);
    private int r = 3;
    private Handler s = new Handler() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("PluginDeviceDeviceFragment", "message is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                DeviceFragment deviceFragment = DeviceFragment.this;
                deviceFragment.a((List<ResourceBriefInfo>) deviceFragment.ag);
            } else if (i == 1) {
                if (DeviceFragment.this.ac()) {
                    DeviceFragment.this.ad.setVisibility(0);
                }
            } else if (i == 42) {
                DeviceFragment.this.cYe_(message);
            } else {
                LogUtil.h("PluginDeviceDeviceFragment", "default branch");
            }
        }
    };
    private EventBus.ICallback o = new EventBus.ICallback() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.12
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar == null) {
                LogUtil.h("PluginDeviceDeviceFragment", "event is null");
                return;
            }
            if ("device_main_share_to_sub_user".equals(bVar.e())) {
                String string = bVar.Kl_().getString("pushContent");
                LogUtil.c("PluginDeviceDeviceFragment", "EVEBUS_WIFI_DEVICE_SHARE_TO_SUB_USER pushContent=", string);
                if (!TextUtils.isEmpty(string)) {
                    try {
                        String string2 = new JSONObject(string).getString("prodId");
                        DeviceFragment.this.ae = cpa.t(string2);
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "productIdForConfigWifi", DeviceFragment.this.ae, (StorageParams) null);
                    } catch (JSONException unused) {
                        LogUtil.b("PluginDeviceDeviceFragment", "EVEBUS_WIFI_DEVICE_SHARE_TO_SUB_USER JSONException");
                    }
                }
                DeviceFragment.this.k();
            }
        }
    };
    private final BroadcastReceiver af = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("PluginDeviceDeviceFragment", "intent is null");
                return;
            }
            if ("com.huawei.bone.action.DEVICE_UPGRADING".equals(intent.getAction()) || "action_ota_check_new_version_state".equals(intent.getAction()) || "com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS".equals(intent.getAction())) {
                LogUtil.a("PluginDeviceDeviceFragment", "mNonLocalBroadcastReceiver() ACTION_DEVICE_UPGRADING or ACTION_OTA_CHECK_NEW_VERSION_STATE");
                DeviceFragment.this.ab();
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                try {
                    LogUtil.a("PluginDeviceDeviceFragment", "mNonLocalBroadcastReceiver() ACTION_CONNECTION_STATE_CHANGED");
                    DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                    if (deviceInfo != null) {
                        if (deviceInfo.getDeviceConnectState() == 3 || deviceInfo.getDeviceConnectState() == 2) {
                            DeviceFragment.this.ab();
                            return;
                        }
                        return;
                    }
                    return;
                } catch (BadParcelableException unused) {
                    LogUtil.b("PluginDeviceDeviceFragment", "BadParcelableException");
                    return;
                }
            }
            LogUtil.h("PluginDeviceDeviceFragment", "mNonLocalBroadcastReceiver()  intent:", intent.getAction());
        }
    };
    private final BroadcastReceiver as = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.15
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("PluginDeviceDeviceFragment", "login receive.intent null.");
                return;
            }
            if ("com.huawei.plugin.account.login".equals(intent.getAction()) || "com.huawei.plugin.account.logout".equals(intent.getAction())) {
                DeviceFragment.this.d();
                if (DeviceFragment.this.g != null) {
                    DeviceFragment.this.e();
                }
            }
        }
    };

    public DeviceFragment() {
    }

    public DeviceFragment(HealthBottomView healthBottomView) {
        this.f9375a = healthBottomView;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        this.ax = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "productIdForConfigWifi");
        this.ae = b;
        if (TextUtils.isEmpty(b)) {
            this.ae = "e835d102-af95-48a6-ae13-2983bc06f5c0";
        }
        EventBus.d(this.o, 0, c);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.14
            @Override // java.lang.Runnable
            public void run() {
                HwDeviceReplyPhraseEngineManager.e();
            }
        });
        final DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
        if (downloadManagerApi.isHtyVersion()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.18
                @Override // java.lang.Runnable
                public void run() {
                    boolean a2 = SharedPreferenceManager.a(Integer.toString(10008), "resource_Preset_Hty", false);
                    ReleaseLogUtil.e("PluginDeviceDeviceFragment", "resourcePresetHty isPreset: ", Boolean.valueOf(a2));
                    if (a2) {
                        return;
                    }
                    downloadManagerApi.resourcePresetHty();
                }
            });
        }
        ReleaseLogUtil.e("PluginDeviceDeviceFragment", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        View inflate = layoutInflater.inflate(R.layout.fragment_device, viewGroup, false);
        this.m = inflate;
        this.g = (LinearLayout) inflate.findViewById(R.id.optimization_card);
        this.ad = (LinearLayout) this.m.findViewById(R.id.marketing_banner);
        this.z = (LinearLayout) this.m.findViewById(R.id.marketing_top_column_layout);
        this.n = (LinearLayout) this.m.findViewById(R.id.general_marketing_layout);
        this.aj = (NestedDeviceScrollView) this.m.findViewById(R.id.device_health_scrollview);
        this.an = (LinearLayout) this.m.findViewById(R.id.device_scrollview_content);
        HealthSearchView healthSearchView = (HealthSearchView) this.m.findViewById(R.id.device_global_search_view);
        this.al = healthSearchView;
        nsn.cLD_(healthSearchView);
        if (ogx.e()) {
            this.aj.setOverScrollMode(2);
            this.av = new VmallWebview(getContext(), this.m, this);
            this.aj.setHasNest(true);
        } else {
            this.m.findViewById(R.id.loading_progress).setVisibility(8);
            this.aj.setHasNest(false);
        }
        this.j = getActivity();
        ah();
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.j, 1);
        this.i = healthColumnSystem;
        this.e = healthColumnSystem.f();
        v();
        cYa_(this.m);
        z();
        ab();
        nwy.e();
        af();
        if (this.j != null) {
            jdx.b(new Runnable() { // from class: ofd
                @Override // java.lang.Runnable
                public final void run() {
                    HwNpsManager.getInstance().startEcologyDeviceNps();
                }
            });
        }
        ReleaseLogUtil.e("PluginDeviceDeviceFragment", "onCreateView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYe_(Message message) {
        if (this.al == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mSearchView is null");
            return;
        }
        Object obj = message.obj;
        if (obj instanceof InputBoxTemplate) {
            InputBoxTemplate inputBoxTemplate = (InputBoxTemplate) obj;
            this.f = inputBoxTemplate;
            if (TextUtils.isEmpty(this.al.getQueryHint())) {
                dpf.b(this.al, inputBoxTemplate);
                if (this.v) {
                    fbh.e(this.j, 4002, inputBoxTemplate);
                }
            }
            dpf.e(this.f.getTheme());
        }
    }

    private void v() {
        this.ah = new CustomPermissionAction(this.j) { // from class: com.huawei.ui.homehealth.device.DeviceFragment.16
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("PluginDeviceDeviceFragment", "initQrCodeAction() mQrCodeAction onGranted");
                DeviceFragment.this.p();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("PluginDeviceDeviceFragment", "initQrCodeAction() mQrCodeAction onGranted");
                DeviceFragment.this.p();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("PluginDeviceDeviceFragment", "initQrCodeAction() mQrCodeAction onGranted");
                DeviceFragment.this.p();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.j == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "gotoScan: mContext is null");
        } else {
            ogj.cZC_(this.j, new Intent(this.j, (Class<?>) QrCodeScanningActivity.class), "QrCodeScanningActivity");
        }
    }

    private void cYa_(View view) {
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.device_tab_titlebar);
        this.l = customTitleBar;
        customTitleBar.setLeftButtonVisibility(8);
        this.l.setTitleText(this.j.getString(R.string.IDS_device_title_use));
        if (nrs.a(this.j)) {
            this.l.setTitleSize(this.j.getResources().getDimension(R.dimen._2131362673_res_0x7f0a0371));
        } else {
            this.l.setTitleSize(this.j.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        if (nsn.s()) {
            this.l.setTitleSize(this.j.getResources().getDimension(R.dimen._2131363048_res_0x7f0a04e8));
        }
        this.l.setTitleTextColor(this.j.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.l.setRightButtonVisibility(0);
        this.l.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131429707_res_0x7f0b094b), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.l.setTitleBarBackgroundColor(this.j.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.l.setRightButtonOnClickListener(new View.OnClickListener() { // from class: ofe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DeviceFragment.this.cYf_(view2);
            }
        });
    }

    public /* synthetic */ void cYf_(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add));
        arrayList.add(getResources().getString(R.string.IDS_hw_device_manager_add_device));
        if (f()) {
            arrayList.add(getResources().getString(R.string.IDS_device_auto_switch));
            oaz.b("autoSwitch");
        }
        new PopViewList(this.j, this.l, arrayList).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.17
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public void setOnClick(int i) {
                if (i == 0) {
                    DeviceFragment.this.j();
                    return;
                }
                if (i == 1) {
                    DeviceFragment.this.g();
                } else if (i == 2) {
                    DeviceFragment.this.am();
                } else {
                    LogUtil.h("PluginDeviceDeviceFragment", "position is null");
                }
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean f() {
        if (bme.b()) {
            LogUtil.h("PluginDeviceDeviceFragment", "checkAutoSwitch is not debug version.");
            return false;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "PluginDeviceDeviceFragment");
        if (deviceList == null) {
            LogUtil.a("PluginDeviceDeviceFragment", "getDeviceList is null.");
            return false;
        }
        int i = 0;
        int i2 = 0;
        for (DeviceInfo deviceInfo : deviceList) {
            if (a(deviceInfo)) {
                i2++;
            }
            if (!"followed_relationship".equals(deviceInfo.getRelationship())) {
                i++;
            }
        }
        LogUtil.a("PluginDeviceDeviceFragment", "getDeviceList: ", Integer.valueOf(deviceList.size()), ",nonFollowDeviceCounter: ", Integer.valueOf(i), ",supportedAutoSwitchCounter: ", Integer.valueOf(i2));
        return i >= 2 && i2 >= 1;
    }

    private boolean a(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 109);
        LogUtil.a("PluginDeviceDeviceFragment", "isSupportDeviceAutoSwitch is:", Boolean.valueOf(c2));
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LoginInit.getInstance(this.j).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.20
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    PermissionUtil.b(DeviceFragment.this.j, PermissionUtil.PermissionType.CAMERA, DeviceFragment.this.ah);
                }
            }
        }, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.j == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mContext is null");
        } else {
            obb.c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        if (this.j == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mContext is null");
        } else {
            obb.b(this);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (getUserVisibleHint()) {
            nsn.e(this.j, 3);
        }
        VmallWebview vmallWebview = this.av;
        if (vmallWebview != null) {
            vmallWebview.onResume();
        }
        o();
        ReleaseLogUtil.e("PluginDeviceDeviceFragment", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void a() {
        LogUtil.a("PluginDeviceDeviceFragment", "onMainActivityResume");
        super.onResume();
        o();
    }

    private void o() {
        if (this.u && this.v) {
            d();
            e();
            s();
            this.u = false;
        }
        i();
        ab();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("PluginDeviceDeviceFragment", "enter notifyRed");
                List r = DeviceFragment.this.r();
                if (!koq.b(r)) {
                    DeviceFragment.this.b((List<cjv>) r);
                } else {
                    DeviceFragment.this.d(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<cjv> list) {
        Iterator<cjv> it = list.iterator();
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            cjv next = it.next();
            if (next.a() == 1) {
                Object i = next.i();
                if (!(i instanceof cpm)) {
                    LogUtil.h("PluginDeviceDeviceFragment", "notifyRed DeviceInfoForWear is null");
                } else {
                    cpm cpmVar = (cpm) i;
                    z = HwVersionManager.c(this.j).n(cpmVar.a()) && !HwVersionManager.c(BaseApplication.getContext()).o(cpmVar.a());
                    if (z) {
                        LogUtil.h("PluginDeviceDeviceFragment", "notifyRed WEAR_DEVICE_TYPE");
                        break;
                    }
                }
            }
            if (next.a() == 0) {
                Object i2 = next.i();
                if (!(i2 instanceof dcz)) {
                    LogUtil.h("PluginDeviceDeviceFragment", "notifyRed ProductInfo is null");
                } else if (cpa.ab(((dcz) i2).t())) {
                    ContentValues FT_ = next.FT_();
                    if (FT_ == null) {
                        LogUtil.h("PluginDeviceDeviceFragment", "notifyRed ContentValues is null");
                    } else {
                        z = a(FT_.getAsString("productId"), FT_.getAsString("uniqueId"));
                        if (z) {
                            LogUtil.h("PluginDeviceDeviceFragment", "notifyRed OTHER_DEVICE_TYPE");
                            break;
                        }
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        d(z);
    }

    private boolean a(String str, String str2) {
        if (!cpa.v(str)) {
            LogUtil.h("PluginDeviceDeviceFragment", "processNotifyRed isNewUpdate not new hag");
            return false;
        }
        if (TextUtils.isEmpty(kxy.e(this.j, str2))) {
            String k = cpa.k(str2);
            LogUtil.a("PluginDeviceDeviceFragment", "processNotifyRed currentVersion:", k);
            kxy.b(k, this.j, str2);
        }
        if (TextUtils.isEmpty(kxy.i(this.j, str2))) {
            String a2 = kxy.a(this.j, str);
            LogUtil.a("PluginDeviceDeviceFragment", "processNotifyRed scaleCheckNewVersion:", a2);
            kxy.j(a2, this.j, str2);
        }
        boolean j = kxy.j(this.j, str2);
        LogUtil.a("PluginDeviceDeviceFragment", "processNotifyRed isNewUpdate isNewVersion :", Boolean.valueOf(j));
        if (!a(str2) && j) {
            LogUtil.h("PluginDeviceDeviceFragment", "processNotifyRed isNewUpdate not Manager && isNewVersion");
            return j;
        }
        MeasurableDevice d = ceo.d().d(str2, false);
        if (d != null) {
            if (d instanceof ctk) {
                boolean z = ((ctk) d).b().k() == 1;
                LogUtil.a("PluginDeviceDeviceFragment", "notifyRed is new isMainUser:", Boolean.valueOf(z));
                return j && z;
            }
            LogUtil.h("PluginDeviceDeviceFragment", "notifyRed device not WiFiDevice");
            return false;
        }
        LogUtil.h("PluginDeviceDeviceFragment", "notifyRed device is null");
        return false;
    }

    private boolean a(String str) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("device_has_manager");
        stringBuffer.append(str);
        return deviceCloudSharePreferencesManager.e(stringBuffer.toString());
    }

    public void d(final boolean z) {
        if (getActivity() == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "getActivity() == null");
        } else {
            getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    if (DeviceFragment.this.f9375a == null) {
                        LogUtil.h("PluginDeviceDeviceFragment", "mBottomNavigationView is null");
                    } else if (z) {
                        DeviceFragment.this.f9375a.c(DeviceFragment.this.r, true);
                    } else {
                        DeviceFragment.this.f9375a.c(DeviceFragment.this.r, false);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<cjv> r() {
        LogUtil.a("PluginDeviceDeviceFragment", "enter getProductInfoList");
        ArrayList arrayList = new ArrayList(16);
        Iterator<cpm> it = cpl.c().j().iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            cjv cjvVar = new cjv();
            if (next.b() == 1 && next.e() == 2) {
                cjvVar.a(1);
                cjvVar.c(next);
                arrayList.add(cjvVar);
            }
        }
        Iterator<ContentValues> it2 = ceo.d().f().iterator();
        while (it2.hasNext()) {
            ContentValues next2 = it2.next();
            String asString = next2.getAsString("productId");
            String asString2 = next2.getAsString("uniqueId");
            if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                LogUtil.h("PluginDeviceDeviceFragment", "getProductInfoList : productId or deviceIdentify is empty");
            } else {
                dcz d = ResourceManager.e().d(asString);
                if (d != null && d.n() != null && d.n().d() != null && !d.n().d().trim().isEmpty()) {
                    cjv b = dbp.b(d);
                    b.FU_(next2);
                    arrayList.add(b);
                }
            }
        }
        LogUtil.a("PluginDeviceDeviceFragment", "productInfoList size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private void ah() {
        LogUtil.a("PluginDeviceDeviceFragment", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_UPGRADING");
        intentFilter.addAction("action_ota_check_new_version_state");
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.af, intentFilter, LocalBroadcast.c, null);
    }

    private void ak() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.af);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PluginDeviceDeviceFragment", "unRegisterNonLocalBroadcastReceiver IllegalArgumentException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!(this.j.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            LogUtil.a("PluginDeviceDeviceFragment", "not instanceof WindowManager");
            return;
        }
        ((WindowManager) this.j.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        if (this.v) {
            LogUtil.a("PluginDeviceDeviceFragment", "onConfigurationChanged doOnConfigureChanged");
            h();
        }
        if (this.g != null && !ac()) {
            pfe.dol_(15, this.g, true);
            LinearLayout linearLayout = this.g;
            nsy.cMA_(linearLayout, linearLayout.getChildCount() != 0 ? 0 : 8);
        }
        if (ac()) {
            ai();
            if (this.ad != null) {
                a(this.ag);
            }
        }
        ag();
        VmallWebview vmallWebview = this.av;
        if (vmallWebview != null) {
            vmallWebview.b();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.v = z;
        CardDeviceFragment cardDeviceFragment = this.b;
        if (cardDeviceFragment != null) {
            cardDeviceFragment.setUserVisibleHint(z);
        }
        if (this.v) {
            LogUtil.a("PluginDeviceDeviceFragment", "setUserVisibleHint doOnConfigureChanged");
            h();
            nsn.e(this.j, 3);
            if (this.u && this.m != null) {
                d();
                e();
                s();
                InputBoxTemplate m = m();
                if (m != null) {
                    fbh.e(this.j, 4002, m);
                }
                this.u = false;
                ScrollUtil.cKx_(this.aj, getActivity().getWindow().getDecorView(), 3003);
            }
            CardDeviceFragment cardDeviceFragment2 = this.b;
            if (cardDeviceFragment2 != null && (this.m instanceof ViewGroup)) {
                cardDeviceFragment2.b(new WaterMarkCallback() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.2
                    @Override // com.huawei.ui.homehealth.device.callback.WaterMarkCallback
                    public void refreshWaterMark() {
                        jph.bIN_(DeviceFragment.this.getActivity(), (ViewGroup) DeviceFragment.this.m);
                    }
                });
            }
        }
        if (getView() != null) {
            eil.alV_(getActivity().getWindow().getDecorView(), z, 3003, "Device");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        LogUtil.a("PluginDeviceDeviceFragment", "onHiddenChanged");
        super.onHiddenChanged(z);
        VmallWebview vmallWebview = this.av;
        if (vmallWebview != null) {
            vmallWebview.onHiddenChanged(z);
        }
    }

    private void i() {
        if (LoginInit.getInstance(this.j).isKidAccount()) {
            nsy.cMA_(this.g, 8);
            nsy.cMA_(this.aa, 8);
        } else if (Utils.o()) {
            nsy.cMA_(this.g, 0);
        }
    }

    public void d() {
        if (this.p) {
            LogUtil.a("PluginDeviceDeviceFragment", "DeviceFragment has init Marketing");
            return;
        }
        this.p = true;
        if (this.m == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mDeviceView is null");
            return;
        }
        LogUtil.a("PluginDeviceDeviceFragment", "enter initMarketing");
        LinearLayout linearLayout = (LinearLayout) this.m.findViewById(R.id.marketing_layout);
        this.aa = linearLayout;
        if (linearLayout == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mMarketingLayout is null.");
            return;
        }
        if (!ac()) {
            LogUtil.a("PluginDeviceDeviceFragment", "initMarketing but marketing two grid is not used");
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            b(marketingApi);
        } else {
            LogUtil.h("PluginDeviceDeviceFragment", "marketingApi is null");
        }
    }

    private void b(final MarketingApi marketingApi) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(4002);
        arrayList.add(1003);
        arrayList.add(4123);
        arrayList.add(4157);
        marketingApi.getResourceResultInfo(arrayList).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.4
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                DeviceFragment.this.ac = marketingApi.filterMarketingRules(map);
                DeviceFragment.this.c(marketingApi);
                DeviceFragment.this.aj();
                DeviceFragment.this.d(marketingApi);
                DeviceFragment.this.a(marketingApi);
            }
        });
    }

    public void b() {
        if (!ac()) {
            LogUtil.a("PluginDeviceDeviceFragment", "updateVmallMarketingData but marketing two grid is not used");
            return;
        }
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "marketingApi is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(4002);
        marketingApi.getResourceResultInfo(arrayList).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.6
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                LogUtil.a("PluginDeviceDeviceFragment", "updateVmallMarketingData onSuccess");
                if (marketingApi.filterMarketingRules(map) == null) {
                    LogUtil.h("PluginDeviceDeviceFragment", "filterMarketingRules with resultInfoMap is null");
                    return;
                }
                ResourceResultInfo resourceResultInfo = marketingApi.filterMarketingRules(map).get(4002);
                if (resourceResultInfo != null) {
                    DeviceFragment.this.ac.put(4002, resourceResultInfo);
                    DeviceFragment.this.c(marketingApi);
                } else {
                    LogUtil.h("PluginDeviceDeviceFragment", "vmall marketing data is null");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ac() {
        return !Utils.o() || OperationUtils.switchToMarketingTwo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MarketingApi marketingApi) {
        InputBoxTemplate inputBoxTemplate;
        boolean z = this.t == null;
        InputBoxTemplate b = dpf.b(this.ac.get(4002));
        if (b != null) {
            this.t = b;
            dpf.b(this.al, b);
        }
        if (z && (inputBoxTemplate = this.t) != null && this.v) {
            fbh.e(this.j, 4002, inputBoxTemplate);
        }
        if (this.aa == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "showVmallLayout mMarketingLayout is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(4002, this.ac.get(4002));
        List<View> marketingViewList = marketingApi.getMarketingViewList(getActivity(), hashMap);
        this.aa.removeAllViews();
        if (!koq.b(marketingViewList)) {
            Iterator<View> it = marketingViewList.iterator();
            while (it.hasNext()) {
                this.aa.addView(it.next());
            }
        }
        LinearLayout linearLayout = this.aa;
        nsy.cMA_(linearLayout, linearLayout.getChildCount() == 0 ? 8 : 0);
        if (this.av != null) {
            LogUtil.a("PluginDeviceDeviceFragment", "load vmall");
            this.av.c(nsn.j() - this.l.getHeight());
            this.av.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        if (this.ad == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "showMarketingBanner mMarketingBanner is null.");
            return;
        }
        if (!this.ac.containsKey(1003)) {
            LogUtil.h("PluginDeviceDeviceFragment", "showMarketingBanner resource is null.");
            return;
        }
        ResourceResultInfo resourceResultInfo = this.ac.get(1003);
        if (resourceResultInfo == null) {
            this.ag = new ArrayList(0);
            this.ad.removeAllViews();
            LogUtil.h("PluginDeviceDeviceFragment", "resourceResultInfo = null");
            return;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            this.ag = new ArrayList(0);
            this.ad.removeAllViews();
            LogUtil.a("PluginDeviceDeviceFragment", "resourceBriefInfoList is empty");
        } else {
            LogUtil.a("PluginDeviceDeviceFragment", "resourceBriefInfoList size:", Integer.valueOf(resources.size()));
            this.ag = resources;
            this.s.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MarketingApi marketingApi) {
        if (this.n == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "showGeneralBanner mGeneralMarketingLayout is null.");
            return;
        }
        if (!this.ac.containsKey(4123)) {
            LogUtil.h("PluginDeviceDeviceFragment", "showGeneralBanner resource is null.");
            return;
        }
        HashMap hashMap = new HashMap();
        this.k = hashMap;
        hashMap.put(4123, this.ac.get(4123));
        List<View> marketingViewList = marketingApi.getMarketingViewList(this.j, this.k);
        LogUtil.a("PluginDeviceDeviceFragment", "showGeneralBanner viewList.size = ", Integer.valueOf(marketingViewList.size()));
        this.n.removeAllViews();
        if (koq.b(marketingViewList)) {
            this.n.setVisibility(8);
            return;
        }
        this.n.setVisibility(0);
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            this.n.addView(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MarketingApi marketingApi) {
        if (this.z == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "showTopColumn mMarketTopColumnLayout is null.");
            return;
        }
        if (!this.ac.containsKey(4157)) {
            LogUtil.h("PluginDeviceDeviceFragment", "showGeneralBanner resource is null.");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(4157, this.ac.get(4157));
        List<View> marketingViewList = marketingApi.getMarketingViewList(this.j, hashMap);
        LogUtil.a("PluginDeviceDeviceFragment", "showTopColumn viewList.size = ", Integer.valueOf(marketingViewList.size()));
        this.z.removeAllViews();
        if (koq.b(marketingViewList)) {
            this.z.setVisibility(8);
            return;
        }
        this.z.setVisibility(0);
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            this.z.addView(it.next());
        }
    }

    private void s() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            MarketingOption.Builder builder = new MarketingOption.Builder();
            builder.setContext(this.j);
            builder.setPageId(15);
            marketingApi.requestMarketingResource(builder.build());
            HashMap hashMap = new HashMap(16);
            hashMap.put("open_specific_page", "Device");
            builder.setTriggerEventParams(hashMap);
            builder.setTypeId(49);
            marketingApi.triggerMarketingResourceEvent(builder.build());
            d(marketingApi, builder);
        }
    }

    private void d(MarketingApi marketingApi, MarketingOption.Builder builder) {
        builder.setTypeId(51);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        builder.setTypeId(52);
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    private void ai() {
        if (this.m == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mDeviceView is null");
            return;
        }
        LogUtil.a("PluginDeviceDeviceFragment", "enter refreshMarketing");
        this.aa = (LinearLayout) this.m.findViewById(R.id.marketing_layout);
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "refreshMarketingUi marketingApi is null");
            return;
        }
        Map<Integer, ResourceResultInfo> map = this.ac;
        if (map != null && map.size() > 0) {
            if (this.aa == null) {
                LogUtil.h("PluginDeviceDeviceFragment", "mMarketingLayout is null");
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(4002, this.ac.get(4002));
            List<View> marketingViewList = marketingApi.getMarketingViewList(getActivity(), hashMap);
            this.aa.removeAllViews();
            if (!koq.b(marketingViewList)) {
                Iterator<View> it = marketingViewList.iterator();
                while (it.hasNext()) {
                    this.aa.addView(it.next());
                }
            }
            LinearLayout linearLayout = this.aa;
            nsy.cMA_(linearLayout, linearLayout.getChildCount() != 0 ? 0 : 8);
            return;
        }
        d();
        e();
    }

    private void ag() {
        if (this.m == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "refreshVipMarketingUi mDeviceView is null");
            return;
        }
        LogUtil.a("PluginDeviceDeviceFragment", "enter refreshVipMarketingUi");
        this.n = (LinearLayout) this.m.findViewById(R.id.general_marketing_layout);
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "refreshMarketingUi marketingApi is null");
            return;
        }
        Map<Integer, ResourceResultInfo> map = this.k;
        if (map == null || map.size() <= 0) {
            return;
        }
        List<View> marketingViewList = marketingApi.getMarketingViewList(getActivity(), this.k);
        LinearLayout linearLayout = this.n;
        if (linearLayout == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mCommercializationLayout is null");
            return;
        }
        linearLayout.removeAllViews();
        if (koq.b(marketingViewList)) {
            this.n.setVisibility(8);
            return;
        }
        this.n.setVisibility(0);
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            this.n.addView(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<ResourceBriefInfo> list) {
        if (this.j == null || this.ad == null || koq.b(list)) {
            LogUtil.h("PluginDeviceDeviceFragment", "mContext = null or mMarketingBanner = null");
            return;
        }
        this.ad.removeAllViews();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ResourceBriefInfo resourceBriefInfo = list.get(0);
        if (resourceBriefInfo != null) {
            if (a(resourceBriefInfo)) {
                arrayList.add(resourceBriefInfo);
            } else {
                arrayList2.add(resourceBriefInfo);
            }
        }
        LogUtil.a("PluginDeviceDeviceFragment", "topBannerResources size: ", Integer.valueOf(arrayList.size()), ", otherBannerResources size: ", Integer.valueOf(arrayList2.size()));
        if (!koq.b(arrayList)) {
            TopBannerLayout topBannerLayout = new TopBannerLayout(this.j);
            topBannerLayout.c(arrayList);
            this.ad.addView(topBannerLayout);
        } else if (!koq.b(arrayList2)) {
            HashMap hashMap = new HashMap();
            hashMap.put(1003, new ResourceResultInfo.Builder().setResources(arrayList2).setTotalNum(arrayList2.size()).setResourcesLatestModifyTime(System.currentTimeMillis()).build());
            List<View> marketingViewList = ((MarketingApi) Services.c("FeatureMarketing", MarketingApi.class)).getMarketingViewList(this.j, hashMap);
            for (int i = 0; i < marketingViewList.size(); i++) {
                View view = marketingViewList.get(i);
                if (i == marketingViewList.size() - 1) {
                    cYd_(view);
                }
                this.ad.addView(view);
            }
        }
        Message obtainMessage = this.s.obtainMessage();
        obtainMessage.what = 1;
        this.s.sendMessageDelayed(obtainMessage, 1000L);
    }

    private void cYd_(View view) {
        if (view instanceof SportBannerLayout) {
            LogUtil.a("PluginDeviceDeviceFragment", "last sport banner no bottom margin");
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.view_sport_banner_root);
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.bottomMargin = 0;
                frameLayout.setLayoutParams(layoutParams2);
            }
        }
    }

    private boolean a(ResourceBriefInfo resourceBriefInfo) {
        int contentType = resourceBriefInfo.getContentType();
        return contentType == 5 || contentType == 14 || contentType == 41 || contentType == 37 || contentType == 38;
    }

    private void h() {
        Context context = this.j;
        if (context != null) {
            this.i.e(context);
            if (this.e != this.i.f()) {
                this.e = this.i.f();
                q();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        VmallWebview vmallWebview = this.av;
        if (vmallWebview != null) {
            vmallWebview.onDestroy();
        }
        super.onDestroy();
        LogUtil.a("PluginDeviceDeviceFragment", "onDestroy ", this);
        EventBus.a(this.o, c);
        this.f9375a = null;
        this.b = null;
        ak();
        an();
        Handler handler = this.s;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.j = null;
    }

    private void z() {
        LogUtil.a("PluginDeviceDeviceFragment", "init View start");
        q();
        if (!ac()) {
            e();
        } else {
            d();
        }
        w();
        u();
        ThreadPoolManager.d().execute(new Runnable() { // from class: ofj
            @Override // java.lang.Runnable
            public final void run() {
                DeviceFragment.this.k();
            }
        });
        if (dpf.e()) {
            x();
        } else {
            this.y = false;
            nsy.cMA_(this.al, 8);
            this.an.setPadding(0, 0, 0, 0);
        }
        LogUtil.a("PluginDeviceDeviceFragment", "initView end");
    }

    private void w() {
        this.aq = (RelativeLayout) this.m.findViewById(R.id.rl_share_device);
        this.ar = (HealthTextView) this.m.findViewById(R.id.tv_share_content);
        this.ao = (HealthTextView) this.m.findViewById(R.id.tv_share_join);
        this.ap = (HealthTextView) this.m.findViewById(R.id.tv_share_not_join);
    }

    private void u() {
        this.ao.setOnClickListener(this);
        this.ap.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        jbs.a(this.j).e(new WifiDeviceGetMainUserAuth(), new ICloudOperationResult<CloudCommonReponse>() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.7
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
                if (!z || TextUtils.isEmpty(str)) {
                    LogUtil.h("PluginDeviceDeviceFragment", "getMainUserShareDevice, isSuccess is false or text is null");
                    return;
                }
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("authMsgs");
                    if (jSONArray != null && jSONArray.length() > 0) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            if (jSONObject.getInt("status") == 101) {
                                DeviceFragment.this.ab = jSONObject.getLong("mainHuid");
                                DeviceFragment.this.h = jSONObject.getString("devId");
                                DeviceFragment.this.e(jSONObject);
                                DeviceFragment.this.c(jSONObject.getString("nickname"));
                                return;
                            }
                        }
                        return;
                    }
                    LogUtil.h("PluginDeviceDeviceFragment", "getMainUserShareDevice, jsonArray is null or jsonArray size<=0");
                } catch (JSONException e) {
                    LogUtil.b("PluginDeviceDeviceFragment", "getMainUserShareDevice, JSONException", e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(JSONObject jSONObject) {
        String optString = jSONObject.optString("prodId");
        this.ai = optString;
        LogUtil.a("PluginDeviceDeviceFragment", "mProdId: ", optString);
        if (!TextUtils.isEmpty(this.ai)) {
            String str = cpa.j.get(this.ai);
            this.ae = str;
            LogUtil.a("PluginDeviceDeviceFragment", "getMainUserShareDevice-mProduct: ", str);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "productIdForConfigWifi", this.ae, (StorageParams) null);
            return;
        }
        LogUtil.h("PluginDeviceDeviceFragment", "mProdId is an empty string.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        String string = getResources().getString(R.string.IDS_device_herm_name);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "productIdForConfigWifi");
        this.ae = b;
        if ("b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(b)) {
            string = getResources().getString(R.string.IDS_device_hag2021_name);
            LogUtil.a("PluginDeviceDeviceFragment", "showShareDeviceCard mProductId scalesName = ", string);
        }
        LogUtil.a("PluginDeviceDeviceFragment", "showShareDeviceCard mProductId scalesName = ", string);
        Locale locale = Locale.ENGLISH;
        String string2 = getResources().getString(R.string.IDS_device_share_subuser_authorize_2);
        Object[] objArr = new Object[2];
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        objArr[0] = str;
        objArr[1] = string;
        this.am = String.format(locale, string2, objArr);
        getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.8
            @Override // java.lang.Runnable
            public void run() {
                DeviceFragment.this.ar.setText(DeviceFragment.this.am);
                DeviceFragment.this.aq.setVisibility(0);
                if (DeviceFragment.this.q) {
                    DeviceFragment.this.ao.setText(R.string.IDS_main_no_device_click);
                    DeviceFragment.this.ap.setText(R.string._2130841424_res_0x7f020f50);
                }
            }
        });
    }

    private void x() {
        if (this.al == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mSearchView is null");
            return;
        }
        y();
        this.al.setInputType(0);
        this.al.setIconifiedByDefault(false);
        this.y = true;
        dpf.c(this.al);
        this.aj.setScrollViewListener(new NestedDeviceScrollView.ScrollViewListener() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.9
            @Override // com.huawei.ui.homehealth.device.view.NestedDeviceScrollView.ScrollViewListener
            public void onScrollChanged(ScrollView scrollView, int i, int i2, int i3, int i4) {
                if (DeviceFragment.this.y) {
                    if (DeviceFragment.this.ak <= 0) {
                        DeviceFragment deviceFragment = DeviceFragment.this;
                        deviceFragment.ak = deviceFragment.al.getHeight();
                    }
                    if (DeviceFragment.this.ak > 0 && i2 >= DeviceFragment.this.ak) {
                        dpf.Ys_(DeviceFragment.this.m);
                        dpf.d(DeviceFragment.this.l, true);
                        dpf.d(DeviceFragment.this.al);
                    }
                    if (i2 <= 0) {
                        dpf.Ys_(DeviceFragment.this.m);
                        LayoutTransition Yq_ = dpf.Yq_(DeviceFragment.this.aj);
                        dpf.e(DeviceFragment.this.al);
                        dpf.c(DeviceFragment.this.l, true);
                        dpf.Yo_(DeviceFragment.this.s, DeviceFragment.this.aj, Yq_);
                    }
                }
            }
        });
        dpf.Yv_(this.al, new View.OnClickListener() { // from class: ofa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.this.cYh_(view);
            }
        });
        dpf.Yn_(this.s, 4036);
        this.al.setSearchBarContentDescription();
    }

    public /* synthetic */ void cYh_(View view) {
        InputBoxTemplate m = m();
        fbh.d(this.j, 4002, m);
        dpf.a(this.j, m);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void y() {
        CustomTitleBar customTitleBar = this.l;
        if (customTitleBar == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "mDeviceTitleBar is null");
            return;
        }
        customTitleBar.setRightSoftkeyVisibility(8);
        nsy.cMh_(this.l.getRightSoftKey(), 0.0f);
        this.l.setRightSoftkeyBackground(ContextCompat.getDrawable(this.j, R.drawable._2131431370_res_0x7f0b0fca), nsf.h(R.string._2130847322_res_0x7f02265a));
        this.l.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: ofi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.this.cYg_(view);
            }
        });
    }

    public /* synthetic */ void cYg_(View view) {
        InputBoxTemplate m = m();
        fbh.d(this.j, 4002, m);
        dpf.a(this.j, m);
        ViewClickInstrumentation.clickOnView(view);
    }

    private InputBoxTemplate m() {
        InputBoxTemplate inputBoxTemplate = this.t;
        return inputBoxTemplate != null ? inputBoxTemplate : this.f;
    }

    private String l() {
        UserInfomation userInfo = this.ax.getUserInfo();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getName())) {
            return userInfo.getName();
        }
        String accountInfo = LoginInit.getInstance(this.j).getAccountInfo(1002);
        if (!TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        String legalAccountName = new UpApi(this.j).getLegalAccountName();
        return !TextUtils.isEmpty(legalAccountName) ? legalAccountName : MultiUsersManager.INSTANCE.getCurrentUser().h();
    }

    private void e(final boolean z) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "productIdForConfigWifi", (String) null, (StorageParams) null);
        WifiDeviceSubuserAuthorize wifiDeviceSubuserAuthorize = new WifiDeviceSubuserAuthorize();
        wifiDeviceSubuserAuthorize.setDevId(this.h);
        wifiDeviceSubuserAuthorize.setMainHuid(String.valueOf(this.ab));
        wifiDeviceSubuserAuthorize.setNickname(l());
        wifiDeviceSubuserAuthorize.setIntent(z ? 1 : 2);
        ReleaseLogUtil.e("PluginDeviceDeviceFragment", "acceptMainUserShareDevice isAgree ", Boolean.valueOf(z));
        jbs.a(this.j).a(wifiDeviceSubuserAuthorize, new ICloudOperationResult<CloudCommonReponse>() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.10
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str, boolean z2) {
                if (!z2) {
                    if (cloudCommonReponse == null || 112000010 != cloudCommonReponse.getResultCode().intValue()) {
                        nrh.e(DeviceFragment.this.j, R.string._2130841440_res_0x7f020f60);
                        DeviceFragment.this.k();
                        return;
                    } else {
                        LogUtil.a("PluginDeviceDeviceFragment", "acceptMainUserShareDevice", cloudCommonReponse.getResultCode());
                        nrh.e(DeviceFragment.this.j, R.string.IDS_device_wifi_my_qrcode_add_member_exceeding_limit);
                    }
                }
                DeviceFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.10.3
                    @Override // java.lang.Runnable
                    public void run() {
                        DeviceFragment.this.aq.setVisibility(8);
                    }
                });
                DeviceFragment.this.b(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(this.ae);
            String str = this.ae;
            if (str != null && isResourcesAvailable && cpa.ad(str) && !crj.c(this.ae, "HDK_WEIGHT")) {
                LogUtil.a("PluginDeviceDeviceFragment", "updateResourceFile: isPluginAvaiable is true and mProductId = ", CommonUtil.l(this.ae));
                crj.Lu_(this.j, getActivity());
                return;
            } else {
                csf.c(this.h, false);
                new ogr().c(this.j, this.ae);
                return;
            }
        }
        LogUtil.h("PluginDeviceDeviceFragment", "acceptMainUserShareDevice, hide share card");
    }

    private void ae() {
        CustomAlertDialog customAlertDialog = this.d;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.h("PluginDeviceDeviceFragment", "mAuthDialog isShowing");
            return;
        }
        View inflate = View.inflate(this.j, R.layout.dialog_user_auth_message, null);
        cXZ_(inflate);
        cYc_(inflate);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(getActivity());
        builder.e(R.string.IDS_device_wifi_user_permission_dialog_title);
        builder.cyp_(inflate);
        builder.b().setAllCaps(true);
        builder.cyo_(R.string._2130841555_res_0x7f020fd3, new DialogInterface.OnClickListener() { // from class: ofb
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceFragment.this.cYi_(dialogInterface, i);
            }
        });
        builder.cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: ofc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceFragment.cYb_(dialogInterface, i);
            }
        });
        CustomAlertDialog c2 = builder.c();
        this.d = c2;
        c2.setCancelable(false);
        this.d.show();
    }

    public /* synthetic */ void cYi_(DialogInterface dialogInterface, int i) {
        if (!this.x) {
            gmz.d().c(7, true, "DeviceFragment", (IBaseResponseCallback) null);
        }
        if (!this.w) {
            gmz.d().c(2, true, "DeviceFragment", (IBaseResponseCallback) null);
        }
        e(true);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void cYb_(DialogInterface dialogInterface, int i) {
        LogUtil.h("PluginDeviceDeviceFragment", "onClick view");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void cYc_(final View view) {
        if (view == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "onDialogSizeChanged, dialogView is null");
        } else {
            view.post(new Runnable() { // from class: com.huawei.ui.homehealth.device.DeviceFragment.13
                @Override // java.lang.Runnable
                public void run() {
                    int d = (int) (nrs.d(DeviceFragment.this.j) * 0.8f);
                    if (view.getMeasuredHeight() > d) {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        layoutParams.height = d;
                        view.setLayoutParams(layoutParams);
                        view.requestLayout();
                    }
                }
            });
        }
    }

    private void cXZ_(View view) {
        String string = getResources().getString(R.string.IDS_device_haige_user_permission_message, "");
        boolean z = aa() || ad();
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.dialog_text_alert_message);
        if (z) {
            StringBuffer stringBuffer = new StringBuffer(16);
            if (aa() && ad()) {
                stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string.IDS_device_haige_user_permission_message_health_item));
                stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_personal_infomation_message));
            } else if (aa()) {
                stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string.IDS_device_haige_user_permission_message_health_item));
            } else if (ad()) {
                stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_personal_infomation_message));
            } else {
                LogUtil.h("PluginDeviceDeviceFragment", "get HealthData And Personal Info fail");
            }
            SpannableString spannableString = new SpannableString(getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_message, getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_params_message_new, stringBuffer)));
            nsi.cKH_(spannableString, stringBuffer.toString(), new AbsoluteSizeSpan(13, true));
            healthTextView.setText(spannableString);
            return;
        }
        healthTextView.setText(string);
    }

    private boolean aa() {
        return n() && Utils.i();
    }

    private boolean ad() {
        return t() && Utils.i();
    }

    private boolean n() {
        if ("true".equals(gmz.d().c(7))) {
            this.x = true;
        } else {
            this.x = false;
        }
        return !this.x;
    }

    private boolean t() {
        if ("true".equals(gmz.d().c(2))) {
            this.w = true;
        } else {
            this.w = false;
        }
        return !this.w;
    }

    private void q() {
        if (this.b == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "enter initDeviceFragment");
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            CardDeviceFragment cardDeviceFragment = new CardDeviceFragment();
            this.b = cardDeviceFragment;
            beginTransaction.replace(R.id.device_card, cardDeviceFragment);
            beginTransaction.commitAllowingStateLoss();
            return;
        }
        LogUtil.h("PluginDeviceDeviceFragment", "enter initDeviceFragment mCardDeviceFragment is not null");
    }

    public void e() {
        if (this.g == null) {
            LogUtil.a("PluginDeviceDeviceFragment", "initOptimization mConfiguredLayout is null.");
            return;
        }
        LogUtil.a("PluginDeviceDeviceFragment", "enter initOptimization");
        String accountInfo = LoginInit.getInstance(this.j).getAccountInfo(1010);
        if (!ac() && OperationUtils.isOperation(accountInfo)) {
            this.g.setVisibility(0);
            this.g.removeAllViews();
            pfe.doi_(15, this.g, true, null);
        } else {
            LogUtil.h("PluginDeviceDeviceFragment", "initOptimization isOversea or is not operation or switch to marketing 2.0.");
            if (Utils.o() && !OperationUtils.isOperation(accountInfo)) {
                this.r = 2;
            }
            this.g.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("PluginDeviceDeviceFragment", "view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.tv_share_join) {
            ReleaseLogUtil.e("PluginDeviceDeviceFragment", "onClick tv_share_join");
            ae();
        } else if (view.getId() == R.id.tv_share_not_join) {
            ReleaseLogUtil.e("PluginDeviceDeviceFragment", "onClick tv_share_not_join");
            e(false);
            this.aq.setVisibility(8);
        } else {
            LogUtil.h("PluginDeviceDeviceFragment", "onClick else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void af() {
        LogUtil.a("PluginDeviceDeviceFragment", "registerSwitchAccountReceiver enter");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.as, intentFilter);
        LogUtil.a("PluginDeviceDeviceFragment", "registerSwitchAccountReceiver end");
    }

    private void an() {
        try {
            LogUtil.a("PluginDeviceDeviceFragment", "unregisterSwitchAccountReceiver mSwitchAccountReceiver != null");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.as);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PluginDeviceDeviceFragment", "unregisterSwitchAccountReceiver IllegalArgumentException");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            LogUtil.h("PluginDeviceDeviceFragment", "requestCode BLUETOOTH_CODE");
            obb.d(this.j);
        }
    }
}
