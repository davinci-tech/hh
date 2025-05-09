package com.huawei.ui.device.activity.adddevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.SearchView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.callback.VersionNoSupportCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.adapter.LeftKindAdapter;
import com.huawei.ui.device.adapter.RightListAdapter;
import com.huawei.ui.device.utlis.SearchDeviceThreadManager;
import defpackage.bkz;
import defpackage.crj;
import defpackage.cvy;
import defpackage.dcz;
import defpackage.dib;
import defpackage.dkc;
import defpackage.dkd;
import defpackage.dks;
import defpackage.fhw;
import defpackage.jcf;
import defpackage.jfu;
import defpackage.jph;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nuc;
import defpackage.nym;
import defpackage.nyq;
import defpackage.oae;
import defpackage.oba;
import defpackage.obb;
import defpackage.obe;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class AllDeviceListActivity extends BaseActivity implements SearchDeviceThreadManager.SearchCallback, DownloadResultCallBack {

    /* renamed from: a, reason: collision with root package name */
    private Context f9010a;
    private HealthRecycleView aa;
    private RightListAdapter ab;
    private HealthTextView ac;
    private SearchDeviceThreadManager ae;
    private LinearLayout af;
    private List<nyq> ag;
    private LinearLayout ah;
    private String ai;
    private HealthSearchView aj;
    private SystemLocaleChangeReceiver ak;
    private HealthRecycleView al;
    private LinearLayout am;
    private int an;
    private nuc ao;
    private LinearLayout b;
    private int e;
    private HealthProgressBar f;
    private String g;
    private RelativeLayout h;
    private oae i;
    private CustomTitleBar j;
    private String l;
    private dkd m;
    private e n;
    private BroadcastReceiver o;
    private LeftKindAdapter q;
    private LinearLayout s;
    private HealthRecycleView t;
    private HealthTextView u;
    private RightListAdapter w;
    private int x;
    private LinearLayout y;
    private ImageView z;
    private boolean d = false;
    private Map<Integer, Integer> k = new HashMap(16);
    private List<nym> p = new ArrayList(16);
    private List<nyq> ad = new ArrayList(16);
    private nyq c = null;
    private BroadcastReceiver r = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.c("AllDeviceListActivity", "NetBroadcastReceiver intent is null");
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (CommonUtil.aa(BaseApplication.getContext())) {
                    AllDeviceListActivity.this.d();
                    AllDeviceListActivity.this.ab();
                } else {
                    LogUtil.c("AllDeviceListActivity", "net work is error");
                }
            }
        }
    };
    private BroadcastReceiver v = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.c("AllDeviceListActivity", "mPairBroadcastReceiver intent is null");
            } else if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                LogUtil.d("AllDeviceListActivity", "pair device success");
                if (AllDeviceListActivity.this.isFinishing()) {
                    return;
                }
                AllDeviceListActivity.this.finish();
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
    }

    /* loaded from: classes6.dex */
    public class SystemLocaleChangeReceiver extends BroadcastReceiver {
        public SystemLocaleChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.e("AllDeviceListActivity", "SystemLocaleChangeReceiver intent is null");
                return;
            }
            LogUtil.d("AllDeviceListActivity", "mReceiver  onReceive  intent.getAction(): ", intent.getAction());
            if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                LogUtil.d("AllDeviceListActivity", "SystemLocaleChangeReceiver language change");
                jfu.n();
                AllDeviceListActivity.this.h();
                AllDeviceListActivity.this.n();
            }
        }
    }

    private void m() {
        if (this.ak == null) {
            LogUtil.e("AllDeviceListActivity", "Enter registerSystemLanguageChange()");
            this.ak = new SystemLocaleChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            registerReceiver(this.ak, intentFilter);
        }
    }

    private void ai() {
        if (this.ak != null) {
            LogUtil.d("AllDeviceListActivity", "Enter unregisterSystemLanguageChange()");
            try {
                unregisterReceiver(this.ak);
            } catch (IllegalArgumentException unused) {
                LogUtil.e("AllDeviceListActivity", "unregisterSystemLanguageChange，IllegalArgumentException");
            }
            this.ak = null;
        }
    }

    /* loaded from: classes6.dex */
    protected static class e extends Handler {
        WeakReference<AllDeviceListActivity> e;

        e(AllDeviceListActivity allDeviceListActivity) {
            this.e = new WeakReference<>(allDeviceListActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AllDeviceListActivity allDeviceListActivity = this.e.get();
            if (allDeviceListActivity == null || message == null) {
                LogUtil.e("AllDeviceListActivity", "handleMessage mActivity is null");
                return;
            }
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case 100:
                        allDeviceListActivity.u();
                        break;
                    case 101:
                        allDeviceListActivity.cOh_(message);
                        break;
                    case 102:
                        if (message.obj instanceof Integer) {
                            allDeviceListActivity.a(((Integer) message.obj).intValue());
                            break;
                        }
                        break;
                    case 103:
                        allDeviceListActivity.w();
                        break;
                    default:
                        LogUtil.c("AllDeviceListActivity", "other message");
                        break;
                }
            }
            if (message.obj instanceof List) {
                allDeviceListActivity.a((List<nyq>) message.obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        this.f.setVisibility(8);
        this.y.setVisibility(8);
        this.s.setVisibility(8);
        h();
        s();
        HashMap hashMap = new HashMap(16);
        hashMap.put("download_status", 1);
        this.ao.d(AnalyticsValue.ALL_DEVICE_LIST_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cOh_(Message message) {
        this.f.setVisibility(8);
        if (c()) {
            this.y.setVisibility(0);
            this.s.setVisibility(8);
            this.h.setVisibility(4);
            this.aj.setVisibility(4);
            if (message.obj instanceof Integer) {
                if (((Integer) message.obj).intValue() == -3) {
                    this.u.setText(R.string._2130844862_res_0x7f021cbe);
                } else {
                    this.u.setText(R.string.IDS_no_device_res);
                }
            }
            this.y.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.20
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AllDeviceListActivity.this.d();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (this.ao != null) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("download_status", 2);
                this.ao.d(AnalyticsValue.ALL_DEVICE_LIST_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        this.f.setVisibility(8);
        if (c()) {
            cOg_(this.r);
            this.s.setVisibility(0);
            this.y.setVisibility(8);
            this.h.setVisibility(4);
            this.aj.setVisibility(4);
            this.s.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.16
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AllDeviceListActivity.this.ao.a(AllDeviceListActivity.this);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (this.ao != null) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("download_status", 3);
                this.ao.d(AnalyticsValue.ALL_DEVICE_LIST_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
            }
        }
    }

    private void cOg_(BroadcastReceiver broadcastReceiver) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BroadcastManagerUtil.bFB_(this, broadcastReceiver, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("AllDeviceListActivity", "handleDownloadNetError register receiver is error");
        }
    }

    private boolean c() {
        return bkz.e(this.ad) || bkz.e(this.p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.f.setVisibility(0);
        this.s.setVisibility(8);
        this.y.setVisibility(8);
        if (i > 0) {
            this.f.setProgress(i);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d("AllDeviceListActivity", "onCreate");
        this.f9010a = this;
        this.n = new e(this);
        setContentView(R.layout.activity_all_device_list);
        jfu.n();
        this.ao = new nuc();
        this.ae = SearchDeviceThreadManager.b();
        m();
        h();
        n();
        a();
        t();
    }

    private void t() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.17
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    CustomTitleBar customTitleBar = AllDeviceListActivity.this.j;
                    HealthSearchView healthSearchView = AllDeviceListActivity.this.aj;
                    RelativeLayout relativeLayout = AllDeviceListActivity.this.h;
                    if (customTitleBar == null || healthSearchView == null || relativeLayout == null) {
                        return;
                    }
                    customTitleBar.setImportantForAccessibility(1);
                    healthSearchView.setImportantForAccessibility(1);
                    relativeLayout.setImportantForAccessibility(1);
                    accessibilityNodeInfo.removeChild(customTitleBar);
                    accessibilityNodeInfo.removeChild(relativeLayout);
                    accessibilityNodeInfo.removeChild(healthSearchView);
                    accessibilityNodeInfo.addChild(customTitleBar);
                    accessibilityNodeInfo.addChild(healthSearchView);
                    accessibilityNodeInfo.addChild(relativeLayout);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.p.clear();
        this.ad.clear();
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.c("AllDeviceListActivity", "getIntent is null in initData");
            return;
        }
        oba obaVar = new oba(("SWITCH_PLUGINDEVICE".equals(intent.getAction()) && intent.getBooleanExtra("isFromFitnessAdvice", false)) ? nuc.c : null);
        this.p.addAll(obaVar.a());
        this.ad.addAll(obaVar.b());
        this.ao.c(AnalyticsValue.ALL_DEVICE_LIST_ENTER.value());
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.c("AllDeviceListActivity", "getIntentData, intent is null");
            return;
        }
        this.an = intent.getIntExtra("SPORT_TYPE", 0);
        this.l = intent.getStringExtra("kind_id");
        this.x = intent.getIntExtra("progressbar", 0);
        this.e = intent.getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
        this.g = intent.getStringExtra("KEY_INTENT_EQUIPMENT_TYPE");
        int i = this.x;
        if (i == 0) {
            d();
        } else if (i == 100) {
            LogUtil.d("AllDeviceListActivity", "download resource is success");
        } else {
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).addDownloadIndexAllCallBack(this);
            this.f.setVisibility(0);
            this.f.setProgress(this.x);
        }
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f.setVisibility(0);
        this.y.setVisibility(8);
        this.s.setVisibility(8);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).addDownloadIndexAllCallBack(this);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
        if (this.ao != null) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("download_status", 0);
            this.ao.d(AnalyticsValue.ALL_DEVICE_LIST_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
        }
    }

    private void s() {
        if (bkz.e(this.p) || bkz.e(this.ad)) {
            LogUtil.c("AllDeviceListActivity", "mLeftList or mRightList is empty");
            this.h.setVisibility(4);
            this.aj.setVisibility(4);
            return;
        }
        this.f.setVisibility(8);
        e();
        this.i = oae.c(BaseApplication.getContext());
        this.h.setVisibility(0);
        this.aj.setVisibility(0);
        this.q.notifyDataSetChanged();
        this.w.notifyDataSetChanged();
        q();
    }

    private void q() {
        if (TextUtils.isEmpty(this.l)) {
            this.q.b(0);
            return;
        }
        if (bkz.e(this.p)) {
            return;
        }
        LogUtil.d("AllDeviceListActivity", "scrollToTargetKindType:", this.l);
        int i = 0;
        while (true) {
            if (i >= this.p.size()) {
                i = 0;
                break;
            }
            nym nymVar = this.p.get(i);
            if (this.l.equals(nymVar.d()) || (fhw.e.containsKey(Integer.valueOf(this.an)) && ((HealthDevice.HealthDeviceKind) fhw.e.get(Integer.valueOf(this.an))).name().equals(nymVar.d()))) {
                break;
            } else {
                i++;
            }
        }
        if (i == 0) {
            return;
        }
        Integer num = this.k.get(Integer.valueOf(i));
        if (num == null || num.intValue() == 0) {
            LogUtil.c("AllDeviceListActivity", "scrollToTargetKindType rightPosition is invalid");
            return;
        }
        this.q.b(i);
        if (this.t.getLayoutManager() instanceof LinearLayoutManager) {
            this.t.getLayoutManager().scrollToPosition(i);
        }
        RecyclerView.LayoutManager layoutManager = this.aa.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(num.intValue(), 0);
        }
    }

    private void e() {
        this.k.clear();
        for (int i = 0; i < this.ad.size(); i++) {
            if (this.ad.get(i).n() != -1 && !this.k.containsKey(Integer.valueOf(this.ad.get(i).n()))) {
                this.k.put(Integer.valueOf(this.ad.get(i).n()), Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.search_custom_title);
        this.j = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllDeviceListActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.aj = (HealthSearchView) nsy.cMc_(this, R.id.device_search_view);
        this.z = (ImageView) nsy.cMc_(this, R.id.iv_search_back);
        this.af = (LinearLayout) nsy.cMc_(this, R.id.search_mask_layer);
        this.ah = (LinearLayout) nsy.cMc_(this, R.id.search_container);
        this.am = (LinearLayout) nsy.cMc_(this, R.id.search_holder);
        this.t = (HealthRecycleView) nsy.cMc_(this, R.id.left_device_kind);
        this.aa = (HealthRecycleView) nsy.cMc_(this, R.id.right_device_list);
        this.ac = (HealthTextView) nsy.cMc_(this, R.id.search_result_header);
        this.b = (LinearLayout) nsy.cMc_(this, R.id.all_device_layout);
        this.al = (HealthRecycleView) nsy.cMc_(this, R.id.search_device_result_list);
        this.f = (HealthProgressBar) nsy.cMc_(this, R.id.download_progress);
        this.s = (LinearLayout) nsy.cMc_(this, R.id.device_error_bad_layout);
        this.y = (LinearLayout) nsy.cMc_(this, R.id.device_download_bad_layout);
        this.h = (RelativeLayout) nsy.cMc_(this, R.id.data_layout);
        this.u = (HealthTextView) nsy.cMc_(this, R.id.resource_error);
        this.al.setLayoutManager(new DeviceCustomLinearLayoutManager(this));
        this.ag = new ArrayList(16);
        int dimensionPixelOffset = BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363752_res_0x7f0a07a8);
        this.aj.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        j();
        i();
        y();
        jph.bIM_(this, -1);
        ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
        int c = nsn.c(BaseApplication.getContext(), 64.0f);
        if (!LanguageUtil.h(this.f9010a)) {
            c = nsn.c(BaseApplication.getContext(), 84.0f);
        }
        layoutParams.width = c;
        this.t.setLayoutParams(layoutParams);
    }

    private void j() {
        LogUtil.d("AllDeviceListActivity", "enter initLeftRecyclerView");
        this.t.setLayoutManager(new LinearLayoutManager(this));
        if (this.t.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.t.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        LogUtil.d("AllDeviceListActivity", "mLeftList.size ", Integer.valueOf(this.p.size()));
        LeftKindAdapter leftKindAdapter = new LeftKindAdapter(this.p);
        this.q = leftKindAdapter;
        this.t.setAdapter(leftKindAdapter);
        this.q.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.24
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (obj instanceof nym) {
                    nym nymVar = (nym) obj;
                    LogUtil.d("AllDeviceListActivity", "mLeftAdapter onItemClicked position: ", Integer.valueOf(i), " name: ", nymVar.b());
                    AllDeviceListActivity.this.q.b(i);
                    AllDeviceListActivity.this.d = true;
                    if (AllDeviceListActivity.this.aa.getLayoutManager() == null) {
                        return;
                    }
                    if (AllDeviceListActivity.this.aa.getLayoutManager() instanceof LinearLayoutManager) {
                        ((LinearLayoutManager) AllDeviceListActivity.this.aa.getLayoutManager()).scrollToPositionWithOffset(((Integer) AllDeviceListActivity.this.k.get(Integer.valueOf(i))).intValue(), 0);
                    }
                    if (AllDeviceListActivity.this.ao != null) {
                        HashMap hashMap = new HashMap(16);
                        hashMap.put("kind_name", nymVar.b());
                        AllDeviceListActivity.this.ao.d(AnalyticsValue.ALL_DEVICE_LIST_LEFT_KIND.value(), hashMap);
                    }
                }
            }
        });
    }

    private void i() {
        LogUtil.d("AllDeviceListActivity", "enter initRightRecyclerView");
        this.aa.setLayoutManager(new LinearLayoutManager(this));
        if (this.aa.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.aa.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        LogUtil.d("AllDeviceListActivity", "mLeftList.size ", Integer.valueOf(this.p.size()));
        this.aa.setLayoutManager(new DeviceCustomLinearLayoutManager(this));
        RightListAdapter rightListAdapter = new RightListAdapter(this.ad);
        this.w = rightListAdapter;
        this.aa.setAdapter(rightListAdapter);
        this.w.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.23
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (nsn.o()) {
                    LogUtil.c("AllDeviceListActivity", "onClick isFastDoubleClick");
                    return;
                }
                if (obj instanceof nyq) {
                    nyq nyqVar = (nyq) obj;
                    if (!dib.c().b(AllDeviceListActivity.this.e) || TextUtils.isEmpty(AllDeviceListActivity.this.l) || AllDeviceListActivity.this.l.equals(nyqVar.f())) {
                        if (!fhw.e.containsKey(Integer.valueOf(AllDeviceListActivity.this.an)) || ((HealthDevice.HealthDeviceKind) fhw.e.get(Integer.valueOf(AllDeviceListActivity.this.an))).name().equals(nyqVar.f())) {
                            AllDeviceListActivity.this.a(nyqVar);
                            if (AllDeviceListActivity.this.ao != null) {
                                HashMap hashMap = new HashMap(16);
                                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, nyqVar.h());
                                AllDeviceListActivity.this.ao.d(AnalyticsValue.ALL_DEVICE_LIST_RIGHT_ITEM.value(), hashMap);
                                return;
                            }
                            return;
                        }
                        LogUtil.d("AllDeviceListActivity", "select wrong device type");
                        return;
                    }
                    dib.c().b(AllDeviceListActivity.this.f9010a, AllDeviceListActivity.this.g);
                }
            }
        });
        this.aa.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.25
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                AllDeviceListActivity.this.o();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        int n;
        if (this.aa.getLayoutManager() instanceof LinearLayoutManager) {
            int findFirstVisibleItemPosition = ((LinearLayoutManager) this.aa.getLayoutManager()).findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition < 0 || findFirstVisibleItemPosition > this.ad.size()) {
                findFirstVisibleItemPosition = 0;
            }
            if (bkz.e(this.ad) || this.ad.get(findFirstVisibleItemPosition) == null || (n = this.ad.get(findFirstVisibleItemPosition).n()) == -1 || this.q.d() == n) {
                return;
            }
            LogUtil.d("AllDeviceListActivity", "onScrollRightListScrolled currentPosition:", Integer.valueOf(n));
            b(n);
            if (this.t.getLayoutManager() instanceof LinearLayoutManager) {
                this.t.getLayoutManager().scrollToPosition(n);
            }
        }
    }

    private void b(int i) {
        if (!this.d) {
            this.q.b(i);
        }
        this.d = false;
    }

    private void y() {
        this.z.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllDeviceListActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.j.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllDeviceListActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.af.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllDeviceListActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        v();
    }

    private void v() {
        this.aj.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.4
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                LogUtil.d("AllDeviceListActivity", "onFocusChange:", Boolean.valueOf(z));
                AllDeviceListActivity.this.f();
                if (!z) {
                    AllDeviceListActivity.this.j.setTitleBarBackgroundColor(AllDeviceListActivity.this.getResources().getColor(R.color._2131296657_res_0x7f090191));
                    AllDeviceListActivity.this.h.setBackgroundColor(AllDeviceListActivity.this.getResources().getColor(R.color._2131296657_res_0x7f090191));
                    AllDeviceListActivity.this.ah.setVisibility(8);
                    AllDeviceListActivity.this.b.setVisibility(0);
                    AllDeviceListActivity.this.r();
                    AllDeviceListActivity.this.ae.d();
                    AllDeviceListActivity.this.b(false);
                    ViewScrollInstrumentation.focusChangeOnView(view, z);
                    return;
                }
                AllDeviceListActivity.this.j.setTitleBarBackgroundColor(AllDeviceListActivity.this.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
                AllDeviceListActivity.this.h.setBackgroundColor(AllDeviceListActivity.this.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
                AllDeviceListActivity.this.b.setVisibility(8);
                AllDeviceListActivity.this.ah.setVisibility(0);
                AllDeviceListActivity.this.ad();
                AllDeviceListActivity.this.p();
                AllDeviceListActivity.this.b(true);
                ViewScrollInstrumentation.focusChangeOnView(view, z);
            }
        });
        this.aj.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.10
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                AllDeviceListActivity.this.ah.setVisibility(0);
                AllDeviceListActivity.this.d(str);
                AllDeviceListActivity.this.ao.c(AnalyticsValue.ALL_DEVICE_LIST_SEARCH.value());
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                AllDeviceListActivity.this.ah.setVisibility(0);
                AllDeviceListActivity.this.d(str);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final boolean z) {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.8
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    CustomTitleBar customTitleBar = AllDeviceListActivity.this.j;
                    HealthSearchView healthSearchView = AllDeviceListActivity.this.aj;
                    LinearLayout linearLayout = AllDeviceListActivity.this.b;
                    LinearLayout linearLayout2 = AllDeviceListActivity.this.ah;
                    ImageView imageView = AllDeviceListActivity.this.z;
                    RelativeLayout relativeLayout = AllDeviceListActivity.this.h;
                    if (customTitleBar == null || healthSearchView == null || linearLayout == null || relativeLayout == null || linearLayout2 == null || imageView == null) {
                        return;
                    }
                    customTitleBar.setImportantForAccessibility(1);
                    healthSearchView.setImportantForAccessibility(1);
                    linearLayout.setImportantForAccessibility(1);
                    linearLayout2.setImportantForAccessibility(1);
                    relativeLayout.setImportantForAccessibility(1);
                    if (z) {
                        jcf.bEE_(imageView, 1);
                        accessibilityNodeInfo.removeChild(customTitleBar);
                        accessibilityNodeInfo.removeChild(imageView);
                        accessibilityNodeInfo.removeChild(healthSearchView);
                        accessibilityNodeInfo.removeChild(relativeLayout);
                        accessibilityNodeInfo.removeChild(linearLayout);
                        accessibilityNodeInfo.addChild(imageView);
                        accessibilityNodeInfo.addChild(healthSearchView);
                        accessibilityNodeInfo.addChild(relativeLayout);
                        accessibilityNodeInfo.addChild(linearLayout2);
                        return;
                    }
                    jcf.bEE_(imageView, 2);
                    accessibilityNodeInfo.removeChild(customTitleBar);
                    accessibilityNodeInfo.removeChild(imageView);
                    accessibilityNodeInfo.removeChild(healthSearchView);
                    accessibilityNodeInfo.removeChild(linearLayout);
                    accessibilityNodeInfo.removeChild(relativeLayout);
                    accessibilityNodeInfo.removeChild(linearLayout2);
                    accessibilityNodeInfo.addChild(customTitleBar);
                    accessibilityNodeInfo.addChild(healthSearchView);
                    accessibilityNodeInfo.addChild(relativeLayout);
                    accessibilityNodeInfo.addChild(linearLayout);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        this.af.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Object systemService = getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 2);
            }
        }
        this.af.setVisibility(8);
        this.aj.setQuery("", false);
        this.aj.clearFocus();
        this.ah.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        LogUtil.d("AllDeviceListActivity", "start do search");
        f();
        if (TextUtils.isEmpty(str)) {
            this.ac.setText("");
            this.ag.clear();
            RightListAdapter rightListAdapter = this.ab;
            if (rightListAdapter != null) {
                rightListAdapter.notifyDataSetChanged();
            }
            this.al.setVisibility(8);
            return;
        }
        this.ai = str;
        this.ae.e(str, this.ad);
        this.ae.d(this);
    }

    private obe.c l() {
        obe.c cVar = new obe.c();
        cVar.a(this.j);
        cVar.cUn_(this.am);
        cVar.cUo_(this.af);
        cVar.a(this.aj);
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        obe.e(this, l());
        this.z.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        obe.c(this, l());
        this.z.setVisibility(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        nsn.a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SearchDeviceThreadManager searchDeviceThreadManager = this.ae;
        if (searchDeviceThreadManager != null) {
            searchDeviceThreadManager.d();
            this.ae.e();
            this.ae = null;
        }
        e eVar = this.n;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
            this.n = null;
        }
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        dkd dkdVar = this.m;
        if (dkdVar != null) {
            dkdVar.e();
        }
        aa();
        ai();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102 && obb.e()) {
            g(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        try {
            if (this.r != null) {
                LogUtil.d("AllDeviceListActivity", "unregister mNetBroadcastReceiver");
                unregisterReceiver(this.r);
                this.r = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("AllDeviceListActivity", "unregisterNetBroadcastReceiver is error");
        }
    }

    private void z() {
        try {
            if (this.v != null) {
                LogUtil.d("AllDeviceListActivity", "mPairBroadcastReceiver mReceiver != null");
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.v);
                this.v = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("AllDeviceListActivity", "unregisterPairBroadcast is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        try {
            BroadcastReceiver broadcastReceiver = this.o;
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
                this.o = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("AllDeviceListActivity", "unregisterH5NetBroadcast is error");
        }
    }

    private void aa() {
        ab();
        ac();
        z();
    }

    @Override // com.huawei.ui.device.utlis.SearchDeviceThreadManager.SearchCallback
    public void onSearchResult(List<nyq> list) {
        LogUtil.d("AllDeviceListActivity", "onSearchResult");
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = list;
        this.n.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<nyq> list) {
        this.ag.clear();
        if (list.isEmpty()) {
            LogUtil.d("AllDeviceListActivity", "deviceListData is empty");
            RightListAdapter rightListAdapter = this.ab;
            if (rightListAdapter != null) {
                rightListAdapter.notifyDataSetChanged();
            }
            this.ac.setText("");
            this.ac.setVisibility(8);
            this.al.setVisibility(8);
            return;
        }
        if (this.ab == null) {
            RightListAdapter rightListAdapter2 = new RightListAdapter(this.ag);
            this.ab = rightListAdapter2;
            this.al.setAdapter(rightListAdapter2);
        }
        this.ac.setVisibility(0);
        this.al.setVisibility(0);
        this.ag.addAll(list);
        this.ab.a(this.ai);
        this.ab.notifyDataSetChanged();
        this.ac.setText(getResources().getQuantityString(R.plurals._2130903293_res_0x7f0300fd, this.ag.size(), Integer.valueOf(this.ag.size())));
        this.ab.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.7
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (obj instanceof nyq) {
                    AllDeviceListActivity.this.a((nyq) obj);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(nyq nyqVar) {
        k();
        String j = nyqVar.j();
        LogUtil.d("AllDeviceListActivity", "pairGuide,:", j);
        if (TextUtils.isEmpty(j)) {
            LogUtil.c("AllDeviceListActivity", "pairGuid is empty");
            return;
        }
        if ("4".equals(j)) {
            if (nyqVar.f().startsWith("HDK")) {
                this.ao.a(this.f9010a, nyqVar);
                return;
            } else {
                if (c(nyqVar)) {
                    return;
                }
                this.ao.b(nyqVar, this.f9010a);
                return;
            }
        }
        if ("3".equals(j)) {
            LogUtil.c("AllDeviceListActivity", "this device is no pair guide");
            if (nyqVar.f().startsWith("HDK")) {
                j(nyqVar);
                return;
            } else {
                this.ao.e(nyqVar, this.f9010a, this.i);
                return;
            }
        }
        if ("6".equals(j)) {
            if (nyqVar.f().startsWith("HDK") && dks.i(nyqVar.s().get(0))) {
                this.c = nyqVar;
                i(nyqVar);
                return;
            }
            return;
        }
        if (nyqVar.f().startsWith("HDK")) {
            if (bkz.e(nyqVar.s())) {
                return;
            }
            j(nyqVar);
        } else if (nyqVar.f().startsWith(PutDataRequest.WEAR_URI_SCHEME) || nyqVar.f().toUpperCase(Locale.ENGLISH).equals("SPORTS_GENIE")) {
            LogUtil.d("AllDeviceListActivity", "allDeviceItem.getKindType：", nyqVar.f());
            f(nyqVar);
        } else if (!nyqVar.f().equals("SMART_HEADPHONES")) {
            LogUtil.c("AllDeviceListActivity", "invalid pairGuide:", nyqVar.f());
        } else {
            f(nyqVar);
        }
    }

    private boolean c(nyq nyqVar) {
        nyqVar.s();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final nyq nyqVar) {
        if (nyqVar == null) {
            return;
        }
        if (!CommonUtil.aa(this.f9010a)) {
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(nyqVar.s().get(0))) {
                f();
                e(nyqVar);
                return;
            } else {
                x();
                return;
            }
        }
        LogUtil.d("AllDeviceListActivity", "deviceType = ", nyqVar.f(), ", Uuid list size = ", Integer.valueOf(nyqVar.s().size()), ", first Uuid = ", nyqVar.s());
        dkd dkdVar = new dkd(this, nyqVar.f(), 1, nyqVar.s(), new dkc() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.9
            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.d("AllDeviceListActivity", "HadDownloadSingleDevice onSuccess");
                AllDeviceListActivity.this.d(false);
                AllDeviceListActivity.this.f();
                AllDeviceListActivity.this.e(nyqVar);
            }

            @Override // defpackage.dkb
            public void onDownload(int i) {
                AllDeviceListActivity.this.a(i);
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                LogUtil.d("AllDeviceListActivity", "HadDownloadSingleDevice onFailure");
                AllDeviceListActivity.this.d(false);
                if (!CommonUtil.aa(AllDeviceListActivity.this.f9010a)) {
                    AllDeviceListActivity.this.x();
                } else {
                    AllDeviceListActivity.this.d(nyqVar);
                }
            }
        });
        this.m = dkdVar;
        dkdVar.a(cvy.c(nyqVar.g()));
        f();
        this.m.b();
        d(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f.setVisibility(8);
        this.y.setVisibility(8);
        this.s.setVisibility(8);
        this.f.setProgress(0);
        d(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final nyq nyqVar) {
        this.f.setVisibility(8);
        this.y.setVisibility(0);
        this.s.setVisibility(8);
        if (this.ah.getVisibility() == 0) {
            c(R.color._2131296690_res_0x7f0901b2);
        } else {
            c(R.color._2131296657_res_0x7f090191);
        }
        this.y.post(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.6
            @Override // java.lang.Runnable
            public void run() {
                AllDeviceListActivity allDeviceListActivity = AllDeviceListActivity.this;
                allDeviceListActivity.d(allDeviceListActivity.cOf_(allDeviceListActivity.y));
            }
        });
        this.y.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllDeviceListActivity.this.i(nyqVar);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        this.f.setVisibility(8);
        this.y.setVisibility(8);
        this.s.setVisibility(0);
        if (this.ah.getVisibility() == 0) {
            c(R.color._2131296690_res_0x7f0901b2);
        } else {
            c(R.color._2131296657_res_0x7f090191);
        }
        this.s.post(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.13
            @Override // java.lang.Runnable
            public void run() {
                AllDeviceListActivity allDeviceListActivity = AllDeviceListActivity.this;
                allDeviceListActivity.d(allDeviceListActivity.cOf_(allDeviceListActivity.s));
            }
        });
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllDeviceListActivity.this.ao.a(AllDeviceListActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        b();
        cOg_(this.o);
    }

    private void c(int i) {
        View findViewById = findViewById(android.R.id.content);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) findViewById;
            if (viewGroup.getChildCount() <= 0) {
                return;
            }
            viewGroup.getChildAt(0).setBackgroundColor(getResources().getColor(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        HealthRecycleView healthRecycleView = this.t;
        if (healthRecycleView != null) {
            healthRecycleView.setIsIntercept(z);
        }
        HealthRecycleView healthRecycleView2 = this.aa;
        if (healthRecycleView2 != null) {
            healthRecycleView2.setIsIntercept(z);
        }
        HealthSearchView healthSearchView = this.aj;
        if (healthSearchView != null) {
            boolean z2 = !z;
            healthSearchView.setEnabled(z2);
            this.aj.setClickable(z2);
            this.aj.setActivated(z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final nyq nyqVar) {
        LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    AllDeviceListActivity.this.b(nyqVar);
                }
            }
        }, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        RelativeLayout relativeLayout = this.h;
        if (relativeLayout == null || relativeLayout.getVisibility() != 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).topMargin = i;
            this.h.setLayoutParams(layoutParams);
        }
        if (this.ah.getVisibility() != 0) {
            ViewGroup.LayoutParams layoutParams2 = this.aj.getLayoutParams();
            if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) layoutParams2).topMargin = i;
                this.aj.setLayoutParams(layoutParams2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int cOf_(View view) {
        int dimension;
        if (view == null || view.getMeasuredHeight() <= 0) {
            dimension = (int) getResources().getDimension(R.dimen._2131363551_res_0x7f0a06df);
        } else {
            dimension = view.getMeasuredHeight();
        }
        int dimension2 = dimension + ((int) getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306));
        LinearLayout linearLayout = this.ah;
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            dimension2 -= (int) getResources().getDimension(R.dimen._2131364426_res_0x7f0a0a4a);
        }
        LogUtil.b("AllDeviceListActivity", "getTopMargin:", Integer.valueOf(dimension2));
        return dimension2;
    }

    private void b() {
        if (this.o != null) {
            return;
        }
        this.o = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.18
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null || intent.getAction() == null) {
                    LogUtil.c("AllDeviceListActivity", "NetBroadcastReceiver intent is null");
                    return;
                }
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                    if (CommonUtil.aa(BaseApplication.getContext())) {
                        AllDeviceListActivity.this.d(0);
                        AllDeviceListActivity allDeviceListActivity = AllDeviceListActivity.this;
                        allDeviceListActivity.i(allDeviceListActivity.c);
                        AllDeviceListActivity.this.ac();
                        return;
                    }
                    LogUtil.c("AllDeviceListActivity", "net work is error");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(nyq nyqVar) {
        dcz d = ResourceManager.e().d(nyqVar.s().get(0));
        if (d == null) {
            LogUtil.c("AllDeviceListActivity", "productInfo is null");
            return;
        }
        if (!crj.c(d.t(), d.l() != null ? d.l().name() : "")) {
            LogUtil.d("AllDeviceListActivity", "setThirdPartyH5PairGuide version no support");
            crj.Lv_(this, this, new VersionNoSupportCallback() { // from class: com.huawei.ui.device.activity.adddevice.AllDeviceListActivity.19
                @Override // com.huawei.health.device.callback.VersionNoSupportCallback
                public void onDialogClose() {
                    LogUtil.d("AllDeviceListActivity", "setThirdPartyH5PairGuide VersionVerifyUtil noSupportDevice onDialogClose");
                }
            });
        } else {
            if (this.ao == null) {
                this.ao = new nuc();
            }
            this.ao.e(this, d);
        }
    }

    private void j(nyq nyqVar) {
        LogUtil.d("AllDeviceListActivity", "startThirdDevicePairGuide");
        if (nyqVar == null || TextUtils.isEmpty(nyqVar.j())) {
            LogUtil.d("AllDeviceListActivity", "startThirdDevicePairGuide allDeviceItem or pairGuid is null");
            return;
        }
        if (nyqVar.j().equals("3")) {
            if (!obb.e()) {
                this.c = nyqVar;
                PermissionDialogHelper.VA_(this, 102);
                return;
            } else {
                g(nyqVar);
                return;
            }
        }
        h(nyqVar);
    }

    private void g(nyq nyqVar) {
        if (nyqVar == null) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OneKeyScanActivity.class);
        List<String> s = nyqVar.s();
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        startActivity(intent);
    }

    private void h(nyq nyqVar) {
        if (nyqVar == null) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) PairingGuideActivity.class);
        intent.putExtra("kind_id", nyqVar.f());
        intent.putExtra("pair_guide", nyqVar.j());
        intent.putExtra("bluetooth_type", nyqVar.b());
        List<String> s = nyqVar.s();
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, nyqVar.g());
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", this.e);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", this.g);
        startActivity(intent);
    }

    private void f(nyq nyqVar) {
        String j = nyqVar.j();
        LogUtil.d("AllDeviceListActivity", "pairGuide,:", j);
        if (TextUtils.isEmpty(j)) {
            LogUtil.c("AllDeviceListActivity", "pairGuid is empty");
            return;
        }
        Intent intent = new Intent(this, (Class<?>) PairingGuideActivity.class);
        List<String> s = nyqVar.s();
        if (bkz.e(s)) {
            LogUtil.c("AllDeviceListActivity", "uuidList is empty");
            return;
        }
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        intent.putExtra("kind_id", nyqVar.f());
        intent.putExtra("pair_guide", j);
        intent.putExtra("bluetooth_type", nyqVar.b());
        intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, nyqVar.g());
        startActivity(intent);
    }

    @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
    public void setDownloadStatus(int i, int i2) {
        e eVar = this.n;
        if (eVar == null) {
            LogUtil.c("AllDeviceListActivity", "setDownloadStatus, mHandler is null");
            return;
        }
        if (i == 1) {
            eVar.sendEmptyMessage(100);
            return;
        }
        if (i == 0) {
            Message obtain = Message.obtain();
            obtain.what = 102;
            obtain.obj = Integer.valueOf(i2);
            e eVar2 = this.n;
            if (eVar2 != null) {
                eVar2.sendMessage(obtain);
                return;
            }
            return;
        }
        if (i == -2) {
            eVar.sendEmptyMessage(103);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 101;
        obtain2.obj = Integer.valueOf(i);
        this.n.sendMessage(obtain2);
    }

    private void k() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.v, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("AllDeviceListActivity", "resisterPairReceiver is error");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.d("AllDeviceListActivity", "cancel to select device");
        ObserverManagerUtil.c("DEVICE_ASSOCIATION", 152);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
