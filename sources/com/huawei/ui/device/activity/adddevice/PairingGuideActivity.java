package com.huawei.ui.device.activity.adddevice;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollbar.HealthScrollbarView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.activity.adddevice.PairingGuideActivity;
import com.huawei.ui.device.adapter.PairGuideAdapter;
import defpackage.bkz;
import defpackage.cfl;
import defpackage.cpa;
import defpackage.cup;
import defpackage.cuw;
import defpackage.cuy;
import defpackage.cvc;
import defpackage.cvk;
import defpackage.cvy;
import defpackage.cwc;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.dkc;
import defpackage.dkd;
import defpackage.jfu;
import defpackage.jph;
import defpackage.msj;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nuc;
import defpackage.nyp;
import defpackage.oau;
import defpackage.obb;
import defpackage.smx;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class PairingGuideActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthViewPager f9032a;
    private HealthTextView aa;
    private HealthProgressBar ab;
    private LinearLayout ac;
    private HealthScrollbarView ad;
    private nuc af;
    private HealthButton ah;
    private dkd b;
    private int d;
    private HealthCheckBox e;
    private HealthDotsPageIndicator g;
    private DeviceDownloadSourceInfo h;
    private boolean i;
    private String j;
    private String k;
    private boolean l;
    private HealthDevice.HealthDeviceKind m;
    private FrameLayout n;
    private d o;
    private LinearLayout q;
    private String r;
    private HealthScrollView s;
    private HealthRecycleView v;
    private RelativeLayout w;
    private HealthTextView x;
    private dcz z;
    private ArrayList<Integer> f = new ArrayList<>(16);
    private List<nyp> u = new ArrayList(16);
    private List<String> ai = new ArrayList(16);
    private List<String> c = new ArrayList(16);
    private ArrayList<Bitmap> y = new ArrayList<>(16);
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("PairingGuideActivity", "NetBroadcastReceiver intent is null");
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (CommonUtil.aa(BaseApplication.getContext())) {
                    PairingGuideActivity.this.a();
                    PairingGuideActivity.this.ak();
                } else {
                    LogUtil.h("PairingGuideActivity", "net work is error");
                }
            }
        }
    };
    private BroadcastReceiver t = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("PairingGuideActivity", "mPairBroadcastReceiver intent is null");
            } else if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                LogUtil.a("PairingGuideActivity", "pair device success");
                if (PairingGuideActivity.this.isFinishing()) {
                    return;
                }
                PairingGuideActivity.this.finish();
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pairing_guide);
        l();
        h();
    }

    private void n() {
        dcz d2 = ResourceManager.e().d(this.ai.get(0));
        this.z = d2;
        if (d2 == null) {
            LogUtil.h("PairingGuideActivity", "mProductInfo is null");
        } else {
            oau.e(d2.t(), this.k, this.r);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        dkd dkdVar = this.b;
        if (dkdVar != null) {
            dkdVar.e();
        }
        am();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        try {
            if (this.p != null) {
                LogUtil.a("PairingGuideActivity", "unregister mNetBroadcastReceiver");
                unregisterReceiver(this.p);
                this.p = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PairingGuideActivity", "unregisterNetBroadcast is error");
        }
    }

    private void al() {
        try {
            if (this.t != null) {
                LogUtil.a("PairingGuideActivity", "unregisterBroadcastReceiver mReceiver is not null");
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.t);
                this.t = null;
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PairingGuideActivity", "unregisterPairBroadcast is error");
        }
    }

    private void am() {
        ak();
        al();
    }

    private void l() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.pairing_guide_title);
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: nuf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PairingGuideActivity.this.cOL_(view);
            }
        });
        this.f9032a = (HealthViewPager) nsy.cMc_(this, R.id.vp_device_device_img);
        this.v = (HealthRecycleView) nsy.cMc_(this, R.id.pairing_guide_list);
        this.e = (HealthCheckBox) nsy.cMc_(this, R.id.complete_operation_checkbox);
        this.ah = (HealthButton) nsy.cMc_(this, R.id.start_pairing_bt);
        this.g = (HealthDotsPageIndicator) nsy.cMc_(this, R.id.device_navigation_spot);
        this.x = (HealthTextView) nsy.cMc_(this, R.id.pair_guide_text);
        this.s = (HealthScrollView) nsy.cMc_(this, R.id.one_layout);
        this.n = (FrameLayout) nsy.cMc_(this, R.id.more_layout);
        HealthScrollbarView healthScrollbarView = (HealthScrollbarView) nsy.cMc_(this, R.id.scrollbar);
        this.ad = healthScrollbarView;
        smx.c(this.v, healthScrollbarView);
        this.q = (LinearLayout) nsy.cMc_(this, R.id.device_error_bad_layout);
        this.ac = (LinearLayout) nsy.cMc_(this, R.id.device_download_bad_layout);
        this.ab = (HealthProgressBar) nsy.cMc_(this, R.id.download_progress);
        this.w = (RelativeLayout) nsy.cMc_(this, R.id.pair_guide_layout);
        this.aa = (HealthTextView) nsy.cMc_(this, R.id.resource_error);
        q();
        this.ah.setOnClickListener(this);
        dij.UX_(this, (LinearLayout) nsy.cMc_(this, R.id.image_view_container), 0.8d, true);
        dij.UX_(this, this.f9032a, 0.7d, true);
        s();
        i();
    }

    public /* synthetic */ void cOL_(View view) {
        if (!isFinishing()) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        boolean ae = nsn.ae(this);
        LogUtil.a("PairingGuideActivity", "initPadView isPad: ", Boolean.valueOf(ae));
        if (ae) {
            ((HealthColumnRelativeLayout) nsy.cMc_(this, R.id.content_column_layout)).setColumnType(0);
            HealthScrollView healthScrollView = this.s;
            healthScrollView.setPadding(0, healthScrollView.getPaddingTop(), 0, this.s.getPaddingBottom());
            HealthRecycleView healthRecycleView = this.v;
            healthRecycleView.setPadding(0, healthRecycleView.getPaddingTop(), 0, this.v.getPaddingBottom());
            LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.start_pairing_layout);
            linearLayout.setPadding(0, linearLayout.getPaddingTop(), 0, linearLayout.getPaddingBottom());
            ViewGroup.LayoutParams layoutParams = this.ad.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.setMarginEnd(0);
                this.ad.setLayoutParams(layoutParams2);
            }
        }
    }

    private void h() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("PairingGuideActivity", "intent is null");
            return;
        }
        try {
            this.ai = intent.getStringArrayListExtra("uuid_list");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("PairingGuideActivity", "get intent is ArrayIndexOutOfBoundsException");
        }
        this.k = intent.getStringExtra("kind_id");
        this.r = intent.getStringExtra("pair_guide");
        this.l = intent.getBooleanExtra("is_scan_to_pair_guide", false);
        this.d = intent.getIntExtra("bluetooth_type", -1);
        this.i = intent.getBooleanExtra("is_invalidation", false);
        this.h = (DeviceDownloadSourceInfo) intent.getParcelableExtra(AdShowExtras.DOWNLOAD_SOURCE);
        this.j = intent.getStringExtra("uniqueId");
        List<String> list = this.ai;
        if (list == null || list.isEmpty()) {
            LogUtil.h("PairingGuideActivity", "device uuids is empty");
            return;
        }
        if (TextUtils.isEmpty(this.r)) {
            LogUtil.h("PairingGuideActivity", "mPairGuide is empty");
        } else {
            if (TextUtils.isEmpty(this.k)) {
                LogUtil.h("PairingGuideActivity", "mKindType is empty");
                return;
            }
            LogUtil.a("PairingGuideActivity", "device info is:", this.k, " ,", this.ai.toString());
            r();
            a();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        nsn.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.k.startsWith("HDK")) {
            x();
            return;
        }
        if (this.k.equals("SMART_HEADPHONES") && !cpa.z(this.ai.get(0)) && !e(this.ai)) {
            LogUtil.a("PairingGuideActivity", "SMART_HEADPHONES enter R1 or R1P");
            af();
            return;
        }
        this.ab.setVisibility(0);
        this.ac.setVisibility(8);
        this.q.setVisibility(8);
        this.w.setVisibility(4);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(this.ai, new e());
    }

    static class e implements DownloadDeviceInfoCallBack {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<PairingGuideActivity> f9038a;

        private e(PairingGuideActivity pairingGuideActivity) {
            this.f9038a = new WeakReference<>(pairingGuideActivity);
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onSuccess() {
            PairingGuideActivity pairingGuideActivity = this.f9038a.get();
            if (c(pairingGuideActivity)) {
                pairingGuideActivity.af();
            }
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onFailure(int i) {
            PairingGuideActivity pairingGuideActivity = this.f9038a.get();
            if (c(pairingGuideActivity)) {
                pairingGuideActivity.a(i);
            }
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void netWorkError() {
            PairingGuideActivity pairingGuideActivity = this.f9038a.get();
            if (c(pairingGuideActivity)) {
                pairingGuideActivity.u();
            }
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onDownload(int i) {
            PairingGuideActivity pairingGuideActivity = this.f9038a.get();
            if (c(pairingGuideActivity)) {
                pairingGuideActivity.c(i);
            }
        }

        private boolean c(PairingGuideActivity pairingGuideActivity) {
            if (pairingGuideActivity != null && !pairingGuideActivity.isFinishing() && !pairingGuideActivity.isDestroyed()) {
                return true;
            }
            LogUtil.a("PairingGuideActivity", "activity is null, finish or destroyed");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        this.ab.setVisibility(8);
        this.w.setVisibility(0);
        this.ac.setVisibility(8);
        this.q.setVisibility(8);
        n();
        r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.ab.setVisibility(8);
        this.ac.setVisibility(0);
        this.q.setVisibility(8);
        this.w.setVisibility(4);
        if (i == -3) {
            this.aa.setText(R.string._2130844862_res_0x7f021cbe);
        } else {
            this.aa.setText(R.string.IDS_no_device_res);
        }
        this.ac.setOnClickListener(new View.OnClickListener() { // from class: nuh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PairingGuideActivity.this.cOM_(view);
            }
        });
    }

    public /* synthetic */ void cOM_(View view) {
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BroadcastManagerUtil.bFB_(this, this.p, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PairingGuideActivity", "handleDownloadNetError register receiver is error");
        }
        this.ab.setVisibility(8);
        this.q.setVisibility(0);
        this.ac.setVisibility(8);
        this.w.setVisibility(4);
        this.q.setOnClickListener(new View.OnClickListener() { // from class: nui
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PairingGuideActivity.this.cOO_(view);
            }
        });
    }

    public /* synthetic */ void cOO_(View view) {
        if (this.af == null) {
            this.af = new nuc();
        }
        this.af.a(this);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        this.ab.setVisibility(0);
        this.q.setVisibility(8);
        this.ac.setVisibility(8);
        this.w.setVisibility(4);
        if (i > 0) {
            this.ab.setProgress(i);
        }
    }

    private boolean e(List<String> list) {
        if (bkz.e(list)) {
            return false;
        }
        return "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(list.get(0));
    }

    private void r() {
        this.f.clear();
        this.u.clear();
        this.y.clear();
        if (this.k.startsWith(PutDataRequest.WEAR_URI_SCHEME) || this.k.startsWith("SPORTS_GENIE") || this.k.startsWith("SMART_HEADPHONES")) {
            jph.bIM_(this, -1);
            this.w.setVisibility(0);
            p();
            if (e(this.ai)) {
                z();
                return;
            }
            if ("1".equals(this.r)) {
                this.s.setVisibility(8);
                this.n.setVisibility(0);
                w();
            } else if ("2".equals(this.r)) {
                ac();
            } else if ("5".equals(this.r)) {
                y();
            } else {
                LogUtil.h("PairingGuideActivity", "other status");
            }
        }
    }

    private void y() {
        LogUtil.a("PairingGuideActivity", "enter sports engine pairGuide");
        List<String> e2 = e();
        if (bkz.e(e2)) {
            LogUtil.h("PairingGuideActivity", "setFivePairGuide, deviceList is empty");
            this.w.setVisibility(4);
            return;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(e2.get(0));
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.h("PairingGuideActivity", "description file read error");
            return;
        }
        ArrayList<String> aq = pluginInfoByUuid.f().aq();
        ArrayList<String> aw = pluginInfoByUuid.f().aw();
        if (aq == null || aq.isEmpty()) {
            LogUtil.a("PairingGuideActivity", "guideDescList has no value");
            this.s.setVisibility(0);
            this.n.setVisibility(8);
            d(e2, "", R.string.IDS_device_paring_type_le_des_info_21);
            return;
        }
        if (aq.size() == 1) {
            e(e2, pluginInfoByUuid, aq, aw);
        } else {
            e(pluginInfoByUuid, aq, aw);
            aa();
        }
    }

    private void e(cvc cvcVar, ArrayList<String> arrayList, List<String> list) {
        int i = 0;
        while (i < arrayList.size()) {
            nyp nypVar = new nyp();
            int i2 = i + 1;
            nypVar.e(getResources().getString(R.string.IDS_device_pair_guide_step, Integer.valueOf(i2)));
            if (list != null && !list.isEmpty()) {
                LogUtil.a("PairingGuideActivity", "sports engine has image");
                nypVar.cTu_(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(cvcVar, list.get(i)));
            }
            nypVar.c(arrayList.get(i));
            this.u.add(nypVar);
            i = i2;
        }
    }

    private List<String> e() {
        this.ab.setVisibility(8);
        this.w.setVisibility(0);
        this.ac.setVisibility(8);
        this.q.setVisibility(8);
        this.s.setVisibility(8);
        this.n.setVisibility(0);
        ArrayList arrayList = new ArrayList(16);
        for (String str : this.ai) {
            if (!cup.c().containsKey(str) && !this.c.contains(str)) {
                LogUtil.h("PairingGuideActivity", "setFivePairGuide, this device not support: ", str);
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void e(List<String> list, cvc cvcVar, ArrayList<String> arrayList, List<String> list2) {
        this.s.setVisibility(0);
        this.n.setVisibility(8);
        this.g.setVisibility(4);
        if (list2 != null && !list2.isEmpty()) {
            LogUtil.h("PairingGuideActivity", "guideImgList has value");
            this.y.add(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(cvcVar, list2.get(0)));
            d dVar = new d(null, this.y, true);
            this.o = dVar;
            this.f9032a.setAdapter(dVar);
            k();
        }
        LogUtil.a("PairingGuideActivity", "sports engine has only description");
        d(list, arrayList.get(0), R.string.IDS_device_paring_type_le_des_info_21);
    }

    private void w() {
        for (String str : this.ai) {
            if (!cup.c().containsKey(str) && !this.c.contains(str)) {
                LogUtil.a("PairingGuideActivity", "this device is not support show");
            } else {
                nyp nypVar = new nyp();
                cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
                if (pluginInfoByUuid != null && pluginInfoByUuid.f() != null) {
                    nypVar.e(pluginInfoByUuid.f().ae());
                    nypVar.a(b(pluginInfoByUuid));
                    nypVar.c(pluginInfoByUuid.f().bh());
                    nypVar.d(str);
                    c(nypVar, pluginInfoByUuid);
                    this.u.add(nypVar);
                } else {
                    cuw c = jfu.c(str, false);
                    if (c == null) {
                        LogUtil.h("PairingGuideActivity", " deviceInfoNew is null");
                    } else {
                        int m = c.m();
                        int e2 = c.e();
                        if (7 == m) {
                            nypVar.a(R.drawable._2131427565_res_0x7f0b00ed);
                            e2 = R.mipmap._2131821245_res_0x7f1102bd;
                        }
                        nypVar.e(c.f());
                        nypVar.a(false);
                        nypVar.c(e2);
                        nypVar.c(c.y());
                        nypVar.d(str);
                        this.u.add(nypVar);
                    }
                }
            }
        }
        aa();
    }

    private void c(nyp nypVar, cvc cvcVar) {
        ArrayList<String> ap = cvcVar.f().ap();
        if (bkz.e(ap)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<String> it = ap.iterator();
        while (it.hasNext()) {
            arrayList.add(msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + it.next() + ".png");
        }
        nypVar.a(arrayList);
    }

    private void p() {
        this.c.clear();
        List<cvk> indexList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexList();
        if (indexList == null || indexList.size() <= 0) {
            LogUtil.h("PairingGuideActivity", "have no index info");
            return;
        }
        for (cvk cvkVar : indexList) {
            if (cvkVar.g() != null) {
                String e2 = cvkVar.e();
                if (this.ai.contains(e2)) {
                    if (!cwc.e(cvkVar.i())) {
                        LogUtil.h("PairingGuideActivity", "app version is not supported");
                    } else if (Utils.o()) {
                        if (TextUtils.equals(cvkVar.f(), "2") || TextUtils.equals(cvkVar.f(), "3")) {
                            this.c.add(e2);
                        }
                    } else if (TextUtils.equals(cvkVar.f(), "1") || TextUtils.equals(cvkVar.f(), "3")) {
                        this.c.add(e2);
                    }
                }
            }
        }
        LogUtil.a("PairingGuideActivity", "setDeviceShowUuid, mDeviceUuidList: ", Integer.valueOf(this.c.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        if ("2".equals(this.r)) {
            ag();
        }
        if ("5".equals(this.r)) {
            z();
        }
        n();
    }

    private void ag() {
        this.ab.setVisibility(8);
        this.w.setVisibility(0);
        this.ac.setVisibility(8);
        this.q.setVisibility(8);
        this.s.setVisibility(0);
        this.n.setVisibility(8);
        dcz d2 = ResourceManager.e().d(this.ai.get(0));
        this.z = d2;
        if (d2 == null) {
            LogUtil.h("PairingGuideActivity", "productInfo is null");
            return;
        }
        ArrayList<dcz.d> d3 = d2.d();
        if (d3 == null || d3.isEmpty()) {
            LogUtil.h("PairingGuideActivity", "pairGuideInfos is empty");
        } else {
            d(d3.get(0));
        }
    }

    private void x() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            an();
            return;
        }
        Iterator<String> it = this.ai.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(it.next())) {
                z = false;
            }
        }
        if (z) {
            ai();
        } else {
            u();
        }
    }

    private void an() {
        LogUtil.a("PairingGuideActivity", "deviceType: ", this.k, ", Uuid list size: ", Integer.valueOf(this.ai.size()), ", first Uuid: ", this.ai);
        dkd dkdVar = new dkd(this, this.k, 1, this.ai, new dkc() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.4
            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.a("PairingGuideActivity", "HadDownloadSingleDevice onSuccess");
                PairingGuideActivity.this.ai();
            }

            @Override // defpackage.dkb
            public void onDownload(int i) {
                PairingGuideActivity.this.c(i);
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                LogUtil.a("PairingGuideActivity", "HadDownloadSingleDevice onFailure");
                PairingGuideActivity.this.a(-1);
            }
        });
        this.b = dkdVar;
        DeviceDownloadSourceInfo deviceDownloadSourceInfo = this.h;
        if (deviceDownloadSourceInfo != null) {
            dkdVar.a(cvy.c(deviceDownloadSourceInfo));
        }
        this.b.b();
    }

    private void z() {
        this.ab.setVisibility(8);
        int i = 0;
        this.w.setVisibility(0);
        this.ac.setVisibility(8);
        this.q.setVisibility(8);
        this.s.setVisibility(8);
        this.n.setVisibility(0);
        dcz d2 = ResourceManager.e().d(this.ai.get(0));
        this.z = d2;
        if (d2 == null) {
            LogUtil.h("PairingGuideActivity", "productInfo is null");
            return;
        }
        ArrayList<dcz.d> d3 = d2.d();
        if (d3 == null || d3.isEmpty()) {
            LogUtil.h("PairingGuideActivity", "pairGuideInfos is empty");
            ae();
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        if (d3.size() == 1) {
            d(d3.get(0));
            return;
        }
        while (i < d3.size()) {
            nyp nypVar = new nyp();
            dcz.d dVar = d3.get(i);
            if (dVar == null) {
                LogUtil.h("PairingGuideActivity", "ProductInfo.ProductDescription info is null");
                return;
            }
            i++;
            nypVar.e(getResources().getString(R.string.IDS_device_pair_guide_step, Integer.valueOf(i)));
            nypVar.c(c(dVar.c(), this.z.t()));
            nypVar.a(e(dVar.e(), this.z.t()));
            this.u.add(nypVar);
        }
        aa();
    }

    private String e(String str, String str2) {
        return dcq.b().a(str2, str);
    }

    private String c(String str, String str2) {
        return dcx.d(str2, str);
    }

    private void ac() {
        this.s.setVisibility(0);
        this.n.setVisibility(8);
        this.g.setVisibility(0);
        if (cpa.z(this.ai.get(0))) {
            v();
        } else if (m()) {
            ad();
        } else {
            ab();
        }
    }

    private void ab() {
        ArrayList arrayList = new ArrayList(16);
        for (String str : this.ai) {
            if (!cup.c().containsKey(str)) {
                if (!this.c.contains(str)) {
                    LogUtil.h("PairingGuideActivity", "setOneWearDevice, this device not support: ", str);
                } else if ("ba50037c-f395-4a5d-907d-d98b90c73840".equals(str)) {
                    LogUtil.h("PairingGuideActivity", "setOneWearDevice, gt3: ", str);
                }
            }
            arrayList.add(str);
        }
        if (bkz.e(arrayList)) {
            LogUtil.h("PairingGuideActivity", "setOneWearDevice, deviceList is empty");
            this.w.setVisibility(4);
            return;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(arrayList.get(0));
        if (pluginInfoByUuid != null && pluginInfoByUuid.f() != null) {
            d(arrayList, pluginInfoByUuid.f().bh(), R.string.IDS_device_paring_type_le_des_info_21);
        } else {
            cuw c = jfu.c(arrayList.get(0), false);
            if (c == null) {
                LogUtil.h("PairingGuideActivity", "deviceInfoNew is null");
                return;
            }
            d(arrayList, c.y(), R.string.IDS_device_paring_type_le_des_info_21);
        }
        a(arrayList);
    }

    private void d(List<String> list, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            SpannableStringBuilder cTL_ = obb.cTL_(list.get(0), BaseApplication.getContext());
            if (cTL_ == null) {
                this.x.setText(getResources().getString(i));
                return;
            } else {
                this.x.setText(cTL_);
                return;
            }
        }
        this.x.setText(str);
    }

    private void a(List<String> list) {
        for (String str : list) {
            if (this.y.size() >= 6) {
                break;
            }
            cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
            if (pluginInfoByUuid != null && pluginInfoByUuid.f() != null) {
                ArrayList<String> aa = pluginInfoByUuid.f().aa();
                if (!bkz.e(aa)) {
                    int size = pluginInfoByUuid.f().aa().size();
                    c(str);
                    for (int i = 0; i < size && this.y.size() < 6; i++) {
                        this.y.add(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, aa.get(i)));
                    }
                }
            } else {
                cuw c = jfu.c(str, false);
                if (c == null) {
                    LogUtil.h("PairingGuideActivity", "setWearImage, deviceInfoNew is null");
                } else {
                    this.y.add(BitmapFactory.decodeResource(getResources(), c.e()));
                }
            }
        }
        d dVar = new d(null, this.y, true);
        this.o = dVar;
        this.f9032a.setAdapter(dVar);
        if (this.y.size() == 1) {
            this.g.setVisibility(4);
        }
        if (bkz.e(this.y)) {
            LogUtil.h("PairingGuideActivity", "setWearImage, mPluginImages is empty");
            this.w.setVisibility(4);
        }
        k();
    }

    private void c(String str) {
        cuy indexBean = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexBean();
        if (indexBean == null) {
            return;
        }
        String str2 = cvy.d() + indexBean.a() + File.separator + "IDS_icon_porsche_gt3.webp";
        if (new File(str2).exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inInputShareable = true;
            Bitmap decodeFile = BitmapFactory.decodeFile(str2, options);
            if (!"b81581e1-5c5a-4e30-b099-0a448ef56a24".equals(str) || decodeFile == null) {
                return;
            }
            this.y.add(decodeFile);
        }
    }

    private void ad() {
        cuw c = jfu.c(this.ai.get(0), false);
        if (c == null) {
            LogUtil.h("PairingGuideActivity", "deviceInfoNew is empty");
            return;
        }
        this.f.addAll(c.k());
        this.x.setText(getResources().getString(R.string.IDS_device_paring_type_r1_des_info_guide_2));
        d dVar = new d(this.f, null, false);
        this.o = dVar;
        this.f9032a.setAdapter(dVar);
        if (this.f.size() == 1) {
            this.g.setVisibility(4);
        }
        if (bkz.e(this.f)) {
            this.w.setVisibility(4);
        }
        k();
    }

    private boolean m() {
        if (!this.k.equals("SMART_HEADPHONES") || cpa.z(this.ai.get(0))) {
            return false;
        }
        LogUtil.a("PairingGuideActivity", "SMART_HEADPHONES enter R1 or R1P");
        return true;
    }

    private void d(dcz.d dVar) {
        if (dVar == null) {
            return;
        }
        this.s.setVisibility(0);
        this.n.setVisibility(8);
        this.g.setVisibility(4);
        this.y.add(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(e(dVar.e(), this.z.t())));
        this.x.setText(c(dVar.c(), this.z.t()));
        if ("PRO_GPRS".equals(this.z.y())) {
            this.ah.setText(getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add));
        }
        d dVar2 = new d(null, this.y, true);
        this.o = dVar2;
        this.f9032a.setAdapter(dVar2);
        k();
    }

    private String b(cvc cvcVar) {
        return msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + cvcVar.f().ao() + ".png";
    }

    private void aa() {
        if (bkz.e(this.u)) {
            LogUtil.h("PairingGuideActivity", "setPairAdapter, mPairGuideInfos is empty");
            this.w.setVisibility(4);
        } else {
            this.w.setVisibility(0);
            PairGuideAdapter pairGuideAdapter = new PairGuideAdapter(this.u);
            this.v.setLayoutManager(new LinearLayoutManager(this));
            this.v.setAdapter(pairGuideAdapter);
        }
        dcz dczVar = this.z;
        if (dczVar == null || !"PRO_GPRS".equals(dczVar.y())) {
            return;
        }
        this.ah.setText(getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add));
    }

    private void k() {
        this.g.setRtlEnable(true);
        this.g.setViewPager(this.f9032a);
    }

    private void s() {
        this.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    PairingGuideActivity.this.q();
                } else {
                    PairingGuideActivity.this.ah.setAlpha(1.0f);
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen._2131362645_res_0x7f0a0355, typedValue, true);
        this.ah.setAlpha(typedValue.getFloat());
    }

    static class d extends HealthPagerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private boolean f9037a;
        private List<Integer> b;
        private List<Bitmap> c;

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        d(List<Integer> list, List<Bitmap> list2, boolean z) {
            this.b = list;
            this.c = list2;
            this.f9037a = z;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            if (this.f9037a) {
                List<Bitmap> list = this.c;
                if (list != null) {
                    return list.size();
                }
                return 0;
            }
            List<Integer> list2 = this.b;
            if (list2 != null) {
                return list2.size();
            }
            return 0;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(BaseApplication.getContext());
            if (this.f9037a) {
                List<Bitmap> list = this.c;
                if (list != null) {
                    if (i < 0 || i >= list.size()) {
                        return imageView;
                    }
                    imageView.setImageBitmap(this.c.get(i));
                }
            } else {
                List<Integer> list2 = this.b;
                if (list2 != null) {
                    if (i < 0 || i >= list2.size()) {
                        return imageView;
                    }
                    imageView.setImageResource(this.b.get(i).intValue());
                }
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (viewGroup instanceof HealthViewPager) {
                ((HealthViewPager) viewGroup).addView(imageView, 0);
            }
            return imageView;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj != null && (obj instanceof ImageView)) {
                ImageView imageView = (ImageView) obj;
                if (viewGroup == null || imageView.getDrawable() == null) {
                    return;
                }
                viewGroup.removeView(imageView);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.start_pairing_bt) {
            if (!this.e.isChecked()) {
                LogUtil.h("PairingGuideActivity", "check box is not checked");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                if (nsn.o()) {
                    LogUtil.h("PairingGuideActivity", "onClick isFastDoubleClick");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                o();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void o() {
        t();
        if (cpa.z(this.ai.get(0))) {
            ah();
            return;
        }
        dcz dczVar = this.z;
        if (dczVar != null && "PRO_GPRS".equals(dczVar.y())) {
            f();
        } else {
            ae();
        }
    }

    private void f() {
        final CustomPermissionAction customPermissionAction = new CustomPermissionAction(this) { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.6
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("PairingGuideActivity", "gotoQrCodeScanningActivity() qrCodeAction onGranted");
                PairingGuideActivity.this.g();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("PairingGuideActivity", "gotoQrCodeScanningActivity() qrCodeAction onDenied");
                PairingGuideActivity.this.g();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("PairingGuideActivity", "gotoQrCodeScanningActivity() qrCodeAction onForeverDenied");
                PairingGuideActivity.this.g();
            }
        };
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    PermissionUtil.b(PairingGuideActivity.this, PermissionUtil.PermissionType.CAMERA, customPermissionAction);
                }
            }
        }, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ComponentName componentName = new ComponentName(getPackageName(), "com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
        finish();
    }

    private void t() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.t, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PairingGuideActivity", "resisterPairReceiver is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (bkz.e(this.ai)) {
            LogUtil.h("PairingGuideActivity", "uuidList is empty");
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        intent.setAction("SWITCH_PLUGINDEVICE");
                        bundle.putString("arg1", "DeviceBindWaitingUniversal");
                        bundle.putString("arg2", (String) PairingGuideActivity.this.ai.get(0));
                        intent.setPackage(BaseApplication.getContext().getPackageName());
                        intent.setClassName(BaseApplication.getContext().getPackageName(), "com.huawei.health.device.ui.DeviceMainActivity");
                        intent.putExtras(bundle);
                        PairingGuideActivity.this.startActivity(intent);
                    }
                }
            }, "");
        }
    }

    private void ae() {
        if (this.ai.contains("e4b0b1d5-2003-4d88-8b5f-c4f64542040b") || this.ai.contains("a8ba095d-4123-43c4-a30a-0240011c58de")) {
            j();
            return;
        }
        int i = this.d;
        if (i == 1 || i == 2 || i == 0) {
            try {
                if (!obb.e()) {
                    PermissionDialogHelper.VA_(this, 102);
                    return;
                }
            } catch (SecurityException unused) {
                LogUtil.b("PairingGuideActivity", "open bluetooth error");
            }
        }
        aj();
    }

    private void j() {
        LogUtil.a("PairingGuideActivity", "gotoWiFiInfo");
        Intent intent = new Intent(this, (Class<?>) DeviceMainActivity.class);
        intent.setAction("SWITCH_PLUGINDEVICE");
        List<String> list = this.ai;
        if (list == null || list.size() <= 0) {
            return;
        }
        intent.putExtra("productId", this.ai.get(0));
        intent.putExtra("arg1", "DeviceInfoList");
        intent.putExtra("arg4", "WiFiDevice");
        startActivity(intent);
        finish();
    }

    private void v() {
        ResourceManager.e().c();
        ResourceManager.e().a().c(HealthDevice.HealthDeviceKind.HDK_HEART_RATE);
        dcz d2 = ResourceManager.e().d(this.ai.get(0));
        this.z = d2;
        if (d2 == null) {
            return;
        }
        cfl.a().e(this.z.s(), ResourceManager.e().c(this.ai.get(0)) + File.separator + this.z.k());
        HealthDevice.HealthDeviceKind l = this.z.l();
        this.m = l;
        LogUtil.a("PairingGuideActivity", "ProductIntroductionFragment init mKind:", l);
        String str = this.ai.get(0);
        if (this.z.e().size() == 0) {
            LogUtil.a("PairingGuideActivity", "productInfo.descriptions.size()");
            return;
        }
        int size = this.z.e().size();
        for (int i = 0; i < size; i++) {
            this.y.add(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(dcq.b().a(str, this.z.e().get(i).e())));
        }
        d dVar = new d(null, this.y, true);
        this.o = dVar;
        this.f9032a.setAdapter(dVar);
        if (this.y.size() == 1) {
            this.g.setVisibility(4);
        }
        String d3 = dcx.d(str, this.z.e().get(0).c());
        if (TextUtils.isEmpty(d3)) {
            d3 = getResources().getString(R.string.IDS_device_paring_type_le_des_info_21);
        }
        this.x.setText(d3);
        if (bkz.e(this.y)) {
            this.w.setVisibility(4);
        }
        k();
    }

    private void ah() {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(String.format(Locale.ROOT, getResources().getString(R.string.IDS_plugin_device_am16_permission_tips), getResources().getString(R.string._2130848991_res_0x7f022cdf))).czA_(getResources().getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: nua
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PairingGuideActivity.cOK_(view);
            }
        }).czE_(getResources().getString(R.string._2130841555_res_0x7f020fd3), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                cpa.l();
                PairingGuideActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public static /* synthetic */ void cOK_(View view) {
        LogUtil.c("PairingGuideActivity", "onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("PairingGuideActivity", "checkHavePermission");
        PermissionUtil.b(this, PermissionUtil.PermissionType.PHONE_STATE, new CustomPermissionAction(this) { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.7
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                PairingGuideActivity.this.b();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                super.onForeverDenied(permissionType);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        PermissionUtil.b(this, PermissionUtil.PermissionType.AUDIO_CALLS, new PermissionsResultAction() { // from class: com.huawei.ui.device.activity.adddevice.PairingGuideActivity.2
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("PairingGuideActivity", "onGranted");
                PairingGuideActivity.this.d();
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("PairingGuideActivity", "onDenied: ", str);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                super.onForeverDenied(permissionType);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102 && obb.e()) {
            aj();
        }
    }

    private void aj() {
        LogUtil.a("PairingGuideActivity", "startIntent");
        try {
            Intent intent = new Intent(this, (Class<?>) OneKeyScanActivity.class);
            List<String> list = this.ai;
            if (list instanceof ArrayList) {
                intent.putStringArrayListExtra("uuid_list", (ArrayList) list);
                intent.putExtra("is_invalidation", this.i);
                intent.putExtra("uniqueId", this.j);
            }
            if (getIntent() != null) {
                int intExtra = getIntent().getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
                String stringExtra = getIntent().getStringExtra("KEY_INTENT_EQUIPMENT_TYPE");
                intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", intExtra);
                intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", stringExtra);
            }
            intent.putExtra("is_scan_to_pair_guide", this.l);
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PairingGuideActivity", "startIntent error");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
