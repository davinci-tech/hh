package com.huawei.ui.thirdpartservice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.main.api.CloudAuthApi;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.health.main.model.AppLangItem;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.ThirdAuthTokenO;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.thirdpartservice.activity.ThirdPartServiceActivity;
import com.huawei.ui.thirdpartservice.activity.alisport.AliSportActivity;
import com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback;
import com.huawei.ui.thirdpartservice.activity.googlefit.GoogleFitAuthActivity;
import com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAccountAppAdapter;
import com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAppAdapter;
import com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthActivity;
import com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity;
import com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceActivity;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqAuthorizeTokenResult;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqHealthInteractors;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthDb;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthTable;
import com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceCheckConnectCallback;
import com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig;
import defpackage.ivv;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.sfj;
import defpackage.sfy;
import defpackage.sgd;
import defpackage.shn;
import defpackage.shu;
import defpackage.sid;
import defpackage.sir;
import defpackage.sjd;
import defpackage.sjo;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class ThirdPartServiceActivity extends BaseActivity implements CheckConnectCallback.Interrupt {
    private static String d;
    private StravaConfig aa;
    private HealthRecycleView ab;
    private LinearLayout ac;
    private HealthTextView ad;
    private ThirdPartAccountAppAdapter ae;
    private HealthTextView af;
    private LinearLayout ag;
    private HealthTextView ah;
    private StravaConfig.StravaCountryCallback ai;
    private LinearLayout aj;
    private NestedScrollView ak;
    private ThirdPartAppAdapter al;
    private HealthCardView am;
    private HealthCardView an;
    private HealthTextView ao;
    private String ap;
    private WeChatApi aq;
    private HealthTextView ar;
    private LinearLayout as;
    private HealthTextView at;
    private ImageView au;
    private LinearLayout ax;
    private LinearLayout b;
    private HealthRecycleView c;
    private HealthTextView e;
    private CloudAuthApi f;
    private HealthCardView g;
    private HealthTextView h;
    private Context i;
    private HealthTextView k;
    private HealthSubHeader l;
    private LinearLayout o;
    private HealthTextView p;
    private HealthSubHeader q;
    private LinearLayout r;
    private HealthTextView s;
    private HealthSubHeader t;
    private HealthTextView u;
    private CommonDialog21 v;
    private RelativeLayout w;
    private RelativeLayout x;
    private LinearLayout y;
    private HealthTextView z;
    private List<HiAppInfo> j = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private List<AppLangItem> f10539a = new ArrayList(10);
    private e m = new e(this);
    private boolean n = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("ThirdPartServiceActivity", "enter onCreate():");
        super.onCreate(bundle);
        this.i = this;
        WeChatApi weChatApi = (WeChatApi) Services.c("Main", WeChatApi.class);
        this.aq = weChatApi;
        weChatApi.setWeChatHandler(this.m);
        ThreadPoolManager.d().execute(new Runnable() { // from class: sen
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.g();
            }
        });
        ad();
        d();
        if (r()) {
            ag();
        } else {
            ap();
        }
    }

    public /* synthetic */ void g() {
        this.ap = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        CloudAuthApi cloudAuthApi = (CloudAuthApi) Services.c("Main", CloudAuthApi.class);
        this.f = cloudAuthApi;
        cloudAuthApi.constructInstance(BaseApplication.getContext(), this.ap);
        LogUtil.a("ThirdPartServiceActivity", "Get UserAt in ThirdPartServiceActivity: ", this.ap);
        z();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("ThirdPartServiceActivity", "onActivityResult.");
        if (intent == null || !"auth".equals(intent.getStringExtra("activity"))) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: sea
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.f();
            }
        });
    }

    public /* synthetic */ void f() {
        LogUtil.a("ThirdPartServiceActivity", "onActivityResult: getThirdPartyAccountAppInfo()");
        z();
    }

    private void ad() {
        HiHealthNativeApi.a(BaseApplication.getContext()).queryKitAppInfo(new c(this));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback.Interrupt
    public void onStatusGot() {
        x();
    }

    /* loaded from: classes8.dex */
    public static class c implements HiDataOperateListener {
        private WeakReference<ThirdPartServiceActivity> b;

        c(ThirdPartServiceActivity thirdPartServiceActivity) {
            this.b = new WeakReference<>(thirdPartServiceActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a("ThirdPartServiceActivity", "onResult invoke");
            final ThirdPartServiceActivity thirdPartServiceActivity = this.b.get();
            if (thirdPartServiceActivity == null) {
                LogUtil.h("ThirdPartServiceActivity", "onResult thirdPartServiceActivity is null");
                return;
            }
            if (!(obj instanceof List)) {
                LogUtil.h("ThirdPartServiceActivity", "getThirdPartyAppInfo onResult data not list");
                return;
            }
            List list = (List) obj;
            if (!koq.b(list)) {
                boolean b = thirdPartServiceActivity.b(thirdPartServiceActivity, Constants.FAST_APP_PKG);
                for (Object obj2 : list) {
                    if (obj2 instanceof HiAppInfo) {
                        HiAppInfo hiAppInfo = (HiAppInfo) obj2;
                        if (thirdPartServiceActivity.b(BaseApplication.getContext(), hiAppInfo.getPackageName()) && thirdPartServiceActivity.c(BaseApplication.getContext(), hiAppInfo)) {
                            thirdPartServiceActivity.j.add(hiAppInfo);
                        }
                        if (b && hiAppInfo.getAppName() != null && hiAppInfo.getAppName().startsWith("QuickApp_")) {
                            thirdPartServiceActivity.j.add(hiAppInfo);
                        }
                    }
                }
                if (koq.b(thirdPartServiceActivity.j)) {
                    return;
                }
                LogUtil.a("ThirdPartServiceActivity", "getThirdPartyAppInfo onResult mAppInfoList size =", Integer.valueOf(thirdPartServiceActivity.j.size()));
                e eVar = thirdPartServiceActivity.m;
                Objects.requireNonNull(thirdPartServiceActivity);
                eVar.post(new Runnable() { // from class: sey
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThirdPartServiceActivity.this.m();
                    }
                });
                return;
            }
            LogUtil.h("ThirdPartServiceActivity", "getThirdPartyAppInfo onResult tempAppInfoList empty");
        }
    }

    private void z() {
        String lang = this.f.getLang();
        LogUtil.a("ThirdPartServiceActivity", "getUsetId: ", LoginInit.getInstance(this.i).getAccountInfo(1011));
        String appLangItemUrl = this.f.getAppLangItemUrl(lang, "2");
        LogUtil.a("ThirdPartServiceActivity", "Get appLangItemUrl in ThirdPartServiceActivity: ", appLangItemUrl);
        this.f10539a = this.f.getAppLangItem(this.ap, "GET", appLangItemUrl);
        List<AppLangItem> appLangItem = this.f.getAppLangItem(this.ap, "GET", this.f.getAppLangItemUrl(lang, "3"));
        if (appLangItem != null) {
            LogUtil.a("ThirdPartServiceActivity", "mHealthAccountAppInfoList length: ", Integer.valueOf(appLangItem.size()));
            this.f10539a = d(this.f10539a, appLangItem);
        }
        List<AppLangItem> list = this.f10539a;
        if (list != null) {
            LogUtil.a("ThirdPartServiceActivity", "mAccountAppInfoList length: ", Integer.valueOf(list.size()));
            if (this.f10539a.size() > 0) {
                LogUtil.a("ThirdPartServiceActivity", "getThirdPartyAccountAppInfo onResult mAppInfoList size =", Integer.valueOf(this.f10539a.size()));
                this.m.post(new Runnable() { // from class: sed
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThirdPartServiceActivity.this.i();
                    }
                });
                return;
            } else {
                this.m.post(new Runnable() { // from class: seg
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThirdPartServiceActivity.this.ap();
                    }
                });
                return;
            }
        }
        LogUtil.c("ThirdPartServiceActivity", "mAccountAppInfoList is null.");
        this.m.post(new Runnable() { // from class: seg
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.ap();
            }
        });
    }

    private List<AppLangItem> d(List<AppLangItem> list, List<AppLangItem> list2) {
        if (list == null) {
            return list2;
        }
        for (AppLangItem appLangItem : list2) {
            Iterator<AppLangItem> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getAppId().equals(appLangItem.getAppId())) {
                        break;
                    }
                } else {
                    list.add(appLangItem);
                    break;
                }
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Context context, HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            LogUtil.h("ThirdPartServiceActivity", "checkApkValid() hiAppInfo is null");
            return false;
        }
        String packageName = hiAppInfo.getPackageName();
        String signature = hiAppInfo.getSignature();
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(signature)) {
            LogUtil.h("ThirdPartServiceActivity", "checkApkValid() packageName or signature is null");
            return false;
        }
        try {
            return HsfSignValidator.e(context, packageName).equals(ivv.d(signature)) && ivv.b(signature) == context.getPackageManager().getApplicationInfo(packageName, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("ThirdPartServiceActivity", "checkApkValid() NameNotFoundException");
            return false;
        }
    }

    public void d() {
        setContentView(R.layout.activity_third_party_service);
        k();
        LogUtil.a("ThirdPartServiceActivity", "isMobileAppEngine", Boolean.valueOf(EnvironmentInfo.k()));
        if (EnvironmentInfo.k()) {
            nsy.cMc_(this, R.id.third_party_service_subheader).setVisibility(8);
            nsy.cMc_(this, R.id.third_party_service_cardview_layout).setVisibility(8);
        }
        ag();
        if (Utils.o()) {
            this.y.setVisibility(8);
        }
        if (Utils.o()) {
            this.ax.setVisibility(8);
        }
        this.as.setVisibility(8);
        y();
        cancelAdaptRingRegion();
        if (Utils.o()) {
            this.aa = new StravaConfig();
            this.ai = new StravaConfig.StravaCountryCallback() { // from class: sek
                @Override // com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig.StravaCountryCallback
                public final void onStravaCountryCallback(boolean z) {
                    ThirdPartServiceActivity.this.d(z);
                }
            };
            ac();
        } else {
            setViewSafeRegion(false, this.y, this.ax, this.as, this.b);
            this.b.setOnClickListener(new View.OnClickListener() { // from class: sej
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ThirdPartServiceActivity.this.dWA_(view);
                }
            });
        }
        l();
    }

    public /* synthetic */ void d(boolean z) {
        LogUtil.a("ThirdPartServiceActivity", "mStravaConfig isSupport ", Boolean.valueOf(z));
        if (Utils.h() && z) {
            w();
        }
    }

    public /* synthetic */ void dWA_(View view) {
        af();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ac() {
        this.b.setVisibility(8);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: sdy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWz_(view);
            }
        });
        this.o.setVisibility(8);
        setViewSafeRegion(false, this.o);
        this.ad.setText(getString(R.string._2130844408_res_0x7f021af8, new Object[]{getString(R.string._2130850493_res_0x7f0232bd)}));
        setViewSafeRegion(true, this.ac);
        this.p.setText(getString(R.string._2130844408_res_0x7f021af8, new Object[]{getString(R.string._2130850492_res_0x7f0232bc)}));
        setViewSafeRegion(true, this.r);
        this.af.setText(getString(R.string._2130844408_res_0x7f021af8, new Object[]{getString(R.string._2130850494_res_0x7f0232be)}));
        setViewSafeRegion(true, this.ag);
        if (!Utils.h()) {
            this.ac.setVisibility(8);
            this.r.setVisibility(8);
            return;
        }
        String accountInfo = LoginInit.getInstance(this).getAccountInfo(1009);
        if (String.valueOf(7).equals(accountInfo)) {
            this.r.setVisibility(0);
            sir.c().refreshConnectStatus();
        }
        if (String.valueOf(5).equals(accountInfo) || String.valueOf(7).equals(accountInfo)) {
            this.ac.setVisibility(0);
            sjd.d().refreshConnectStatus();
        }
        LogUtil.a("ThirdPartServiceActivity", "countryCode：", LoginInit.getInstance(this).getAccountInfo(1010));
    }

    public /* synthetic */ void dWz_(View view) {
        aj();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (ab()) {
            this.t.setVisibility(8);
            this.g.setVisibility(8);
            return;
        }
        int childCount = this.aj.getChildCount() - 1;
        int i = 0;
        while (true) {
            if (i > childCount) {
                break;
            }
            if (this.aj.getChildAt(i).getVisibility() == 0) {
                childCount = i;
                break;
            }
            i++;
        }
        LogUtil.a("ThirdPartServiceActivity", "hide divide line：", Integer.valueOf(childCount));
        switch (childCount) {
            case 0:
                this.ax.findViewById(R.id.divide_line_0).setVisibility(8);
                break;
            case 1:
                this.as.findViewById(R.id.divide_line_1).setVisibility(8);
                break;
            case 2:
                this.y.findViewById(R.id.divide_line_2).setVisibility(8);
                break;
            case 3:
                this.b.findViewById(R.id.divide_line_3).setVisibility(8);
                break;
            case 4:
                this.o.findViewById(R.id.divide_line_4).setVisibility(8);
                break;
            case 5:
                this.ac.findViewById(R.id.divide_line_5).setVisibility(8);
                break;
            case 6:
                this.r.findViewById(R.id.divide_line_6).setVisibility(8);
                break;
            case 7:
                this.ag.findViewById(R.id.divide_line_7).setVisibility(8);
                break;
        }
    }

    private boolean ab() {
        return this.ax.getVisibility() == 8 && this.as.getVisibility() == 8 && this.y.getVisibility() == 8 && this.b.getVisibility() == 8 && this.o.getVisibility() == 8 && this.ac.getVisibility() == 8 && this.r.getVisibility() == 8 && this.ag.getVisibility() == 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (koq.b(this.j)) {
            return;
        }
        this.q.setVisibility(0);
        this.ab.setVisibility(0);
        this.am.setVisibility(0);
        this.w.setVisibility(8);
        this.ab.setLayoutManager(new LinearLayoutManager(this));
        this.al = new ThirdPartAppAdapter(this.j);
        LogUtil.c("ThirdPartServiceActivity", "appinfo size = ", Integer.valueOf(this.j.size()));
        this.al.d(new ThirdPartAppAdapter.ItemClickListener() { // from class: sel
            @Override // com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAppAdapter.ItemClickListener
            public final void onItemClick(View view, int i) {
                ThirdPartServiceActivity.this.dWs_(view, i);
            }
        });
        this.ab.setNestedScrollingEnabled(false);
        this.ab.setAdapter(this.al);
        this.al.notifyDataSetChanged();
    }

    public /* synthetic */ void dWs_(View view, int i) {
        LogUtil.c("ThirdPartServiceActivity", "appinfo onItemClick position = ", Integer.valueOf(i));
        Intent intent = new Intent();
        intent.setClassName(this.i, "com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity");
        if (koq.d(this.j, i)) {
            HiAppInfo hiAppInfo = this.j.get(i);
            intent.putExtra("app_id", hiAppInfo.getAppId());
            intent.putExtra(MapKeyNames.APP_INFO, hiAppInfo);
        } else {
            LogUtil.h("ThirdPartServiceActivity", "addThirdPartyAppsInView onItemClick isInBounds is false");
        }
        this.i.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        aa();
        this.ak.setVisibility(0);
        if (!koq.b(this.f10539a)) {
            this.l.setVisibility(0);
            this.c.setVisibility(0);
            this.an.setVisibility(0);
            this.c.setLayoutManager(new LinearLayoutManager(this));
            this.ae = new ThirdPartAccountAppAdapter(this.f10539a);
            LogUtil.c("ThirdPartServiceActivity", "account appinfo size = ", Integer.valueOf(this.f10539a.size()));
            this.ae.a(new ThirdPartAccountAppAdapter.ItemClickListener() { // from class: seo
                @Override // com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAccountAppAdapter.ItemClickListener
                public final void onItemClick(View view, int i) {
                    ThirdPartServiceActivity.this.dWr_(view, i);
                }
            });
            this.c.setNestedScrollingEnabled(false);
            this.c.setAdapter(this.ae);
        }
        ah();
    }

    public /* synthetic */ void dWr_(View view, int i) {
        LogUtil.c("ThirdPartServiceActivity", "account appinfo onItemClick position = ", Integer.valueOf(i));
        if (r()) {
            Intent intent = new Intent();
            intent.setClassName(this.i, "com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAccountAuthActivity");
            if (koq.d(this.f10539a, i)) {
                AppLangItem appLangItem = this.f10539a.get(i);
                intent.putExtra("app_id", appLangItem.getAppId());
                intent.putExtra(MapKeyNames.APP_INFO, appLangItem);
                LogUtil.h("ThirdPartServiceActivity", "addThirdPartyAccountAppsInView putExtra app_info");
            } else {
                LogUtil.h("ThirdPartServiceActivity", "addThirdPartyAccountAppsInView onItemClick isInBounds is false");
            }
            startActivityForResult(intent, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ap() {
        aa();
        this.ak.setVisibility(0);
        this.l.setVisibility(8);
        this.c.setVisibility(8);
        this.an.setVisibility(8);
        ah();
    }

    private void k() {
        this.x = (RelativeLayout) nsy.cMc_(this, R.id.third_party_list_load_progress);
        this.w = (RelativeLayout) nsy.cMc_(this, R.id.third_party_no_data_list);
        this.ak = (NestedScrollView) nsy.cMc_(this, R.id.third_party_scroll_view);
        n();
        this.y.setOnClickListener(new View.OnClickListener() { // from class: sei
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWw_(view);
            }
        });
        this.ax.setOnClickListener(new View.OnClickListener() { // from class: ses
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWx_(view);
            }
        });
        this.as.setOnClickListener(new View.OnClickListener() { // from class: sew
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWy_(view);
            }
        });
        this.ac.setOnClickListener(new View.OnClickListener() { // from class: sev
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWt_(view);
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() { // from class: set
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWu_(view);
            }
        });
        this.ag.setOnClickListener(new View.OnClickListener() { // from class: seu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartServiceActivity.this.dWv_(view);
            }
        });
    }

    public /* synthetic */ void dWw_(View view) {
        an();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dWx_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("ThirdPartServiceActivity", "mWeChatConnect onClick");
        SharedPreferenceManager.e(this.i, Integer.toString(10000), "wechat_red_dot_show", "true", new StorageParams());
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("type", "0");
        ixx.d().d(this.i.getApplicationContext(), AnalyticsValue.HEALTH_MINE_SHARE_DATA_2040034.value(), hashMap, 0);
        this.aq.go2WeChatHandle(this.i);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dWy_(View view) {
        ar();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dWt_(View view) {
        ak();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dWu_(View view) {
        am();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dWv_(View view) {
        al();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        HealthSubHeader healthSubHeader = (HealthSubHeader) nsy.cMc_(this, R.id.third_party_service_subheader);
        this.t = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
        this.g = (HealthCardView) nsy.cMc_(this, R.id.third_party_service_cardview_layout);
        this.aj = (LinearLayout) nsy.cMc_(this, R.id.third_party_service_layout);
        this.y = (LinearLayout) nsy.cMc_(this, R.id.layout_qq_health);
        this.u = (HealthTextView) nsy.cMc_(this, R.id.textView_data_share_qq_status);
        this.ax = (LinearLayout) nsy.cMc_(this, R.id.layout_wechat);
        this.at = (HealthTextView) nsy.cMc_(this, R.id.tv_data_share_wechat_tips);
        this.ao = (HealthTextView) nsy.cMc_(this, R.id.tv_data_share_wechat_status);
        this.au = (ImageView) nsy.cMc_(this, R.id.iv_wechat_connect_dot);
        this.as = (LinearLayout) nsy.cMc_(this, R.id.layout_wechat_devices);
        this.ar = (HealthTextView) nsy.cMc_(this, R.id.tv_data_share_wechat_devices_status);
        this.b = (LinearLayout) nsy.cMc_(this, R.id.layout_alisport);
        this.h = (HealthTextView) nsy.cMc_(this, R.id.tv_ali_subtitle);
        this.e = (HealthTextView) nsy.cMc_(this, R.id.textView_data_share_alisport_status);
        this.o = (LinearLayout) nsy.cMc_(this, R.id.layout_google_fit);
        this.k = (HealthTextView) nsy.cMc_(this, R.id.textView_data_share_google_fit_status);
        this.ac = (LinearLayout) nsy.cMc_(this, R.id.layout_runtastic);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.textView_data_share_runtastic_status);
        this.ad = (HealthTextView) nsy.cMc_(this, R.id.textView_runtastic_summary);
        this.r = (LinearLayout) nsy.cMc_(this, R.id.layout_komoot);
        this.s = (HealthTextView) nsy.cMc_(this, R.id.textView_data_share_komoot_status);
        this.p = (HealthTextView) nsy.cMc_(this, R.id.textView_komoot_summary);
        this.ag = (LinearLayout) nsy.cMc_(this, R.id.layout_strava);
        this.ah = (HealthTextView) nsy.cMc_(this, R.id.textView_data_share_strava_status);
        this.af = (HealthTextView) nsy.cMc_(this, R.id.textView_strava_summary);
        HealthSubHeader healthSubHeader2 = (HealthSubHeader) nsy.cMc_(this, R.id.third_party_activity_subheader);
        this.q = healthSubHeader2;
        healthSubHeader2.setSubHeaderBackgroundColor(0);
        this.ab = (HealthRecycleView) nsy.cMc_(this, R.id.rv_third_part_app);
        this.am = (HealthCardView) nsy.cMc_(this, R.id.third_party_activity_cardview);
        HealthSubHeader healthSubHeader3 = (HealthSubHeader) nsy.cMc_(this, R.id.third_party_account_activity_subheader);
        this.l = healthSubHeader3;
        healthSubHeader3.setSubHeaderBackgroundColor(0);
        this.c = (HealthRecycleView) nsy.cMc_(this, R.id.rv_third_part_account_app);
        this.an = (HealthCardView) nsy.cMc_(this, R.id.third_party_account_activity_cardview);
    }

    private void an() {
        LogUtil.a("ThirdPartServiceActivity", "startQqHealthActivity()");
        a(AnalyticsValue.HEALTH_MINE_SHARE_DATA_2040034.value(), "1");
        if (nsn.o() || !r()) {
            return;
        }
        b(R.string._2130841528_res_0x7f020fb8);
        QqHealthInteractors.getInstance().getAuthorizeToken(new ICloudOperationResult() { // from class: ser
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                ThirdPartServiceActivity.this.e((QqAuthorizeTokenResult) obj, str, z);
            }
        });
    }

    public /* synthetic */ void e(QqAuthorizeTokenResult qqAuthorizeTokenResult, String str, boolean z) {
        boolean z2;
        boolean z3;
        QqAuthorizeTokenResult.ThirdUserToken thirdUserToken;
        long parseLong;
        x();
        if (!z || qqAuthorizeTokenResult == null || (thirdUserToken = qqAuthorizeTokenResult.getThirdUserToken()) == null || !thirdUserToken.isValid()) {
            z2 = false;
            z3 = false;
        } else {
            String lastModifyTime = thirdUserToken.getLastModifyTime();
            if (lastModifyTime != null) {
                try {
                    parseLong = Long.parseLong(lastModifyTime);
                } catch (NumberFormatException unused) {
                    LogUtil.a("ThirdPartServiceActivity", "getAuthorizeToken() NumberFormatException");
                }
                z2 = e(System.currentTimeMillis(), parseLong, thirdUserToken.getExpireTime());
                z3 = q();
            }
            parseLong = 0;
            z2 = e(System.currentTimeMillis(), parseLong, thirdUserToken.getExpireTime());
            z3 = q();
        }
        LogUtil.a("ThirdPartServiceActivity", "getAuthorizeToken() isSuccess=", Boolean.valueOf(z), " isQqTokenValid=", Boolean.valueOf(z2));
        a(z2, z3);
    }

    private void a(boolean z, boolean z2) {
        if (z && z2) {
            startActivityForResult(new Intent(this.i, (Class<?>) QqHealthActivity.class), 4);
            return;
        }
        Intent intent = new Intent(this.i, (Class<?>) QqHealthConnectActivity.class);
        intent.putExtra("isQqTokenValid", z);
        startActivityForResult(intent, 4);
    }

    private boolean e(long j, long j2, int i) {
        long j3 = (j - j2) / 1000;
        if (j3 > i) {
            return false;
        }
        LogUtil.a("ThirdPartServiceActivity", "getAuthorizeToken() usedTime=", Long.valueOf(j3), ", expireTime=", Integer.valueOf(i));
        return true;
    }

    private boolean q() {
        QqHealthTable qqHealthTable = new QqHealthDb().get();
        return (qqHealthTable == null || TextUtils.isEmpty(qqHealthTable.getNickName())) ? false : true;
    }

    private void ar() {
        if (nsn.o() || !r()) {
            return;
        }
        startActivity(new Intent(this.i, (Class<?>) WeChatDeviceActivity.class));
    }

    private void ak() {
        if (nsn.o() || !r()) {
            return;
        }
        b(R.string._2130841528_res_0x7f020fb8);
        sjd.d().checkConnectStatus(new sfy(this));
    }

    private void am() {
        if (nsn.o() || !r()) {
            return;
        }
        b(R.string._2130841528_res_0x7f020fb8);
        sir.c().checkConnectStatus(new sfj(this));
    }

    private void al() {
        if (nsn.o() || !r()) {
            return;
        }
        b(R.string._2130841528_res_0x7f020fb8);
        sjo.d().b(false, this.aa, new sgd(this));
    }

    private void af() {
        LogUtil.a("ThirdPartServiceActivity", "startAliSportActivity");
        a(AnalyticsValue.HEALTH_MINE_SHARE_DATA_2040034.value(), "5");
        if (nsn.o() || !r()) {
            return;
        }
        b(R.string._2130841528_res_0x7f020fb8);
        new shn(ThirdAuthTokenO.class, "/dataOpen/third/getThirdAuthorization").getAuthorizeToken(new ICloudOperationResult() { // from class: seb
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                ThirdPartServiceActivity.this.a((ThirdAuthTokenO) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(ThirdAuthTokenO thirdAuthTokenO, String str, boolean z) {
        LogUtil.a("ThirdPartServiceActivity", "startAliSportActivity getThirdAuthorization isSuccess = ", Boolean.valueOf(z));
        x();
        if (z) {
            Intent intent = new Intent(this.i, (Class<?>) AliSportActivity.class);
            intent.putExtra("AUTH_STATUS", false);
            List<ThirdAuthTokenO.ThirdAuthToken> thirdAuthTokenList = thirdAuthTokenO.getThirdAuthTokenList();
            if (thirdAuthTokenList != null) {
                Iterator<ThirdAuthTokenO.ThirdAuthToken> it = thirdAuthTokenList.iterator();
                while (it.hasNext()) {
                    if (it.next().getThirdAccountType() == 24) {
                        intent.putExtra("AUTH_STATUS", true);
                    }
                }
            }
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", "1");
            hashMap.put("click_ali_entrance", "1");
            String value = AnalyticsValue.HEALTH_MINE_SHARE_DATA_ALI_ENTRANCE_2140012.value();
            ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
            LogUtil.a("ThirdPartServiceActivity", "BI save notification click event finish, value = ", value);
            startActivity(intent);
            return;
        }
        this.m.post(new Runnable() { // from class: sep
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.j();
            }
        });
    }

    public /* synthetic */ void j() {
        nrh.b(this, R.string._2130841551_res_0x7f020fcf);
    }

    private void aj() {
        LogUtil.a("ThirdPartServiceActivity", "startGoogleFitActivity");
        if (nsn.o() || !r()) {
            return;
        }
        startActivity(new Intent(this.i, (Class<?>) GoogleFitAuthActivity.class));
    }

    private boolean r() {
        if (CommonUtil.aa(this.i.getApplicationContext())) {
            return true;
        }
        this.m.post(new Runnable() { // from class: seh
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.c();
            }
        });
        return false;
    }

    public /* synthetic */ void c() {
        nrh.b(this, R.string._2130841393_res_0x7f020f31);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("ThirdPartServiceActivity", "enter onDestroy():");
        super.onDestroy();
        x();
        StravaConfig stravaConfig = this.aa;
        if (stravaConfig != null) {
            stravaConfig.b();
        }
        e eVar = this.m;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("ThirdPartServiceActivity", "enter onResume():");
        jdx.b(new Runnable() { // from class: sdz
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.h();
            }
        });
        if (Utils.o()) {
            aq();
        } else {
            o();
            t();
            u();
        }
        if (this.n) {
            this.n = false;
        }
        super.onResume();
    }

    public /* synthetic */ void h() {
        if (Utils.o()) {
            return;
        }
        String isUserBinded = this.aq.isUserBinded(BaseApplication.getContext());
        Message obtainMessage = this.m.obtainMessage(500);
        obtainMessage.obj = isUserBinded;
        this.m.sendMessage(obtainMessage);
        if ("true".equals(isUserBinded)) {
            LogUtil.a("ThirdPartServiceActivity", "judgeWechatIsBind done");
            ((AchieveDataApi) Services.c("PluginAchievement", AchieveDataApi.class)).dealWeChatTask();
        }
    }

    private void aq() {
        s();
        if (Utils.h()) {
            String accountInfo = LoginInit.getInstance(this).getAccountInfo(1009);
            if (String.valueOf(7).equals(accountInfo)) {
                p();
            }
            if (String.valueOf(5).equals(accountInfo) || String.valueOf(7).equals(accountInfo)) {
                v();
            }
            this.aa.dYg_(this.m, LoginInit.getInstance(this).getAccountInfo(1010), this.ai);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        nsn.a();
    }

    private void b(int i) {
        if (this.v == null) {
            CommonDialog21 a2 = CommonDialog21.a(this.i);
            this.v = a2;
            a2.e(this.i.getString(i));
            this.v.setCancelable(false);
            this.v.a();
        }
        if (isFinishing()) {
            return;
        }
        this.v.show();
        LogUtil.a("ThirdPartServiceActivity", "mLoadingDialog.show()");
    }

    private void x() {
        CommonDialog21 commonDialog21;
        if (isFinishing() || (commonDialog21 = this.v) == null) {
            return;
        }
        commonDialog21.cancel();
        LogUtil.a("ThirdPartServiceActivity", "destroy mLoadingDialog");
    }

    private void a(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("type", str2);
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        if ("true".equals(SharedPreferenceManager.b(this.i, Integer.toString(10000), "wechat_red_dot_show"))) {
            this.au.setVisibility(8);
        } else {
            this.au.setVisibility(0);
        }
    }

    private void o() {
        LogUtil.a("ThirdPartServiceActivity", "checkAliAuth");
        if (CommonUtil.aa(this.i.getApplicationContext())) {
            new shn(ThirdAuthTokenO.class, "/dataOpen/third/getThirdAuthorization").getAuthorizeToken(new ICloudOperationResult() { // from class: see
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    ThirdPartServiceActivity.this.e((ThirdAuthTokenO) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void e(ThirdAuthTokenO thirdAuthTokenO, String str, boolean z) {
        List<ThirdAuthTokenO.ThirdAuthToken> thirdAuthTokenList;
        LogUtil.a("ThirdPartServiceActivity", "checkAliAuth getThirdAuthorization isSuccess = ", Boolean.valueOf(z));
        if (!z || TextUtils.isEmpty(str) || (thirdAuthTokenList = thirdAuthTokenO.getThirdAuthTokenList()) == null) {
            return;
        }
        Iterator<ThirdAuthTokenO.ThirdAuthToken> it = thirdAuthTokenList.iterator();
        while (it.hasNext()) {
            if (it.next().getThirdAccountType() == 24) {
                LogUtil.a("ThirdPartServiceActivity", "checkAliAuth bound");
                this.m.post(new Runnable() { // from class: sez
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThirdPartServiceActivity.this.e();
                    }
                });
                return;
            }
        }
        LogUtil.a("ThirdPartServiceActivity", "checkAliAuth unbound");
        this.m.post(new Runnable() { // from class: sex
            @Override // java.lang.Runnable
            public final void run() {
                ThirdPartServiceActivity.this.a();
            }
        });
    }

    public /* synthetic */ void e() {
        this.h.setText(R.string._2130843176_res_0x7f021628);
        this.e.setText(R.string._2130841450_res_0x7f020f6a);
    }

    public /* synthetic */ void a() {
        this.h.setText(R.string._2130842769_res_0x7f021491);
        this.e.setText(R.string._2130841449_res_0x7f020f69);
    }

    private void t() {
        LogUtil.a("ThirdPartServiceActivity", "checkQqStatus()");
        if (CommonUtil.aa(this.i.getApplicationContext())) {
            QqHealthInteractors.getInstance().getAuthorizeToken(new ICloudOperationResult() { // from class: seq
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    ThirdPartServiceActivity.this.d((QqAuthorizeTokenResult) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void d(QqAuthorizeTokenResult qqAuthorizeTokenResult, String str, boolean z) {
        boolean z2;
        QqAuthorizeTokenResult.ThirdUserToken thirdUserToken;
        long parseLong;
        LogUtil.a("ThirdPartServiceActivity", "checkQqStatus getAuthorizeToken() isSuccess=", Boolean.valueOf(z));
        x();
        if (!z || qqAuthorizeTokenResult == null || (thirdUserToken = qqAuthorizeTokenResult.getThirdUserToken()) == null || !thirdUserToken.isValid()) {
            z2 = false;
        } else {
            String lastModifyTime = thirdUserToken.getLastModifyTime();
            if (lastModifyTime != null) {
                try {
                    parseLong = Long.parseLong(lastModifyTime);
                } catch (NumberFormatException unused) {
                    LogUtil.a("ThirdPartServiceActivity", "getAuthorizeToken() NumberFormatException");
                }
                z2 = e(System.currentTimeMillis(), parseLong, thirdUserToken.getExpireTime());
            }
            parseLong = 0;
            z2 = e(System.currentTimeMillis(), parseLong, thirdUserToken.getExpireTime());
        }
        a(z2);
    }

    private void y() {
        if (Utils.o()) {
            this.as.setVisibility(8);
        } else {
            if (ae()) {
                this.as.setVisibility(0);
                u();
                LogUtil.a("ThirdPartServiceActivity", "checkWechatDeviceIsSupported true and is not expired.");
                return;
            }
            sid.b(new ICloudOperationResult() { // from class: sem
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    ThirdPartServiceActivity.this.a((List) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void a(List list, String str, boolean z) {
        LogUtil.a("ThirdPartServiceActivity", "getSupportedProductIdsAsync ids = ", list);
        if (!z || koq.b(list)) {
            return;
        }
        this.m.sendMessage(this.m.obtainMessage(TypedValues.PositionType.TYPE_DRAWPATH));
    }

    private boolean ae() {
        String b2 = SharedPreferenceManager.b(this.i, Integer.toString(10000), "wx_device_entrance_supported");
        String b3 = SharedPreferenceManager.b(this.i, Integer.toString(10000), "wx_device_entrance_supported_ts");
        return "true".equalsIgnoreCase(b2) && !(TextUtils.isEmpty(b3) || ((System.currentTimeMillis() - Long.parseLong(b3)) > 43200000L ? 1 : ((System.currentTimeMillis() - Long.parseLong(b3)) == 43200000L ? 0 : -1)) > 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        sid.c(new ICloudOperationResult() { // from class: sef
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                ThirdPartServiceActivity.this.a((Boolean) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(Boolean bool, String str, boolean z) {
        Message obtainMessage = this.m.obtainMessage(501);
        obtainMessage.obj = Boolean.valueOf(z && bool.booleanValue());
        this.m.sendMessage(obtainMessage);
    }

    private void a(boolean z) {
        Message obtainMessage = this.m.obtainMessage(503);
        if (z) {
            obtainMessage.obj = "true";
            this.m.sendMessage(obtainMessage);
        } else {
            obtainMessage.obj = "false";
            this.m.sendMessage(obtainMessage);
        }
    }

    private void s() {
        LogUtil.a("ThirdPartServiceActivity", "checkGoogleFitStatus()");
        if (CommonUtil.aa(this.i.getApplicationContext())) {
            boolean c2 = shu.b().c();
            Message obtainMessage = this.m.obtainMessage(504);
            if (c2) {
                obtainMessage.obj = "true";
            } else {
                obtainMessage.obj = "false";
            }
            this.m.sendMessage(obtainMessage);
        }
    }

    private void v() {
        if (this.ac.getVisibility() == 8 || !CommonUtil.aa(this.i.getApplicationContext())) {
            return;
        }
        sjd.d().checkConnectStatus(true, new d(this.m));
    }

    private void p() {
        if (this.r.getVisibility() == 8 || !CommonUtil.aa(this.i.getApplicationContext())) {
            return;
        }
        sir.c().checkConnectStatus(true, new b(this.m));
    }

    private void w() {
        if (CommonUtil.aa(this.i.getApplicationContext())) {
            sjo.d().b(true, this.aa, new a(this.m));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("ThirdPartServiceActivity", "checkApkExist NameNotFoundException");
            return false;
        }
    }

    /* loaded from: classes8.dex */
    static class d extends ReferenceCheckConnectCallback<e> {
        d(e eVar) {
            super(eVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceCheckConnectCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void connectResultWhenReferenceNotNull(e eVar, boolean z, boolean z2) {
            Message obtainMessage = eVar.obtainMessage(TypedValues.PositionType.TYPE_PERCENT_X);
            obtainMessage.obj = Boolean.valueOf(z2);
            eVar.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes8.dex */
    static class a extends ReferenceCheckConnectCallback<e> {
        a(e eVar) {
            super(eVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceCheckConnectCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void connectResultWhenReferenceNotNull(e eVar, boolean z, boolean z2) {
            Message obtainMessage = eVar.obtainMessage(TypedValues.PositionType.TYPE_CURVE_FIT);
            obtainMessage.obj = Boolean.valueOf(z2);
            eVar.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes8.dex */
    static class b extends ReferenceCheckConnectCallback<e> {
        b(e eVar) {
            super(eVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceCheckConnectCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void connectResultWhenReferenceNotNull(e eVar, boolean z, boolean z2) {
            Message obtainMessage = eVar.obtainMessage(TypedValues.PositionType.TYPE_PERCENT_Y);
            obtainMessage.obj = Boolean.valueOf(z2);
            eVar.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes8.dex */
    public static class e extends BaseHandler<ThirdPartServiceActivity> {
        e(ThirdPartServiceActivity thirdPartServiceActivity) {
            super(thirdPartServiceActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dWJ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ThirdPartServiceActivity thirdPartServiceActivity, Message message) {
            int i = message.what;
            if (i == 201) {
                dWB_(message, thirdPartServiceActivity);
            }
            if (i == 205) {
                thirdPartServiceActivity.aq.bindTitle(thirdPartServiceActivity, message.obj.toString());
                return;
            }
            switch (i) {
                case 500:
                    dWH_(message, thirdPartServiceActivity);
                    break;
                case 501:
                    dWI_(message, thirdPartServiceActivity);
                    break;
                case TypedValues.PositionType.TYPE_DRAWPATH /* 502 */:
                    c(thirdPartServiceActivity);
                    break;
                case 503:
                    dWE_(message, thirdPartServiceActivity);
                    break;
                case 504:
                    dWC_(message, thirdPartServiceActivity);
                    break;
                default:
                    switch (i) {
                        case TypedValues.PositionType.TYPE_PERCENT_X /* 506 */:
                            dWF_(thirdPartServiceActivity, message);
                            break;
                        case TypedValues.PositionType.TYPE_PERCENT_Y /* 507 */:
                            dWD_(thirdPartServiceActivity, message);
                            break;
                        case TypedValues.PositionType.TYPE_CURVE_FIT /* 508 */:
                            dWG_(thirdPartServiceActivity, message);
                            break;
                        default:
                            b(thirdPartServiceActivity, message.what);
                            break;
                    }
            }
        }

        private void b(ThirdPartServiceActivity thirdPartServiceActivity, int i) {
            if (i == 202) {
                thirdPartServiceActivity.aq.dismissJumpToHwPublicDialog();
                nrh.b(thirdPartServiceActivity, R.string._2130841588_res_0x7f020ff4);
            } else {
                if (i != 203) {
                    return;
                }
                thirdPartServiceActivity.aq.dismissJumpToHwPublicDialog();
                nrh.b(thirdPartServiceActivity, R.string._2130841551_res_0x7f020fcf);
            }
        }

        private void dWB_(Message message, final ThirdPartServiceActivity thirdPartServiceActivity) {
            String unused = ThirdPartServiceActivity.d = LoginInit.getInstance(thirdPartServiceActivity.getApplicationContext()).getAccountInfo(1011);
            if (TextUtils.isEmpty(ThirdPartServiceActivity.d) && message.obj == null) {
                thirdPartServiceActivity.aq.dismissJumpToHwPublicDialog();
                LogUtil.a("ThirdPartServiceActivity", "getQrCodeTicket userId=null or msg.obj = null");
            } else {
                final String obj = message.obj.toString();
                ((HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class)).getTodayFitnessTotalData(new IBaseResponseCallback() { // from class: sfa
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj2) {
                        ThirdPartServiceActivity.e.a(obj, thirdPartServiceActivity, i, obj2);
                    }
                });
            }
        }

        public static /* synthetic */ void a(String str, ThirdPartServiceActivity thirdPartServiceActivity, int i, Object obj) {
            int i2;
            if (i == 0 && (obj instanceof List)) {
                for (FitnessTotalData fitnessTotalData : (List) obj) {
                    if (fitnessTotalData.getSportType() == 221) {
                        i2 = fitnessTotalData.getSteps();
                        break;
                    }
                }
            }
            i2 = 0;
            thirdPartServiceActivity.aq.jumpToHwPublic(str + "#" + ThirdPartServiceActivity.d + "#" + i2);
            thirdPartServiceActivity.aq.dismissJumpToHwPublicDialog();
        }

        private void dWH_(Message message, ThirdPartServiceActivity thirdPartServiceActivity) {
            String str = message.obj instanceof String ? (String) message.obj : "";
            if ("false".equals(str)) {
                thirdPartServiceActivity.at.setText(R.string._2130841452_res_0x7f020f6c);
                thirdPartServiceActivity.ao.setText(R.string._2130841449_res_0x7f020f69);
                KeyValDbManager.b(BaseApplication.getContext()).d("third_part_service_we_chat_status", "false", null);
            } else if ("true".equals(str)) {
                thirdPartServiceActivity.at.setText(R.string._2130843117_res_0x7f0215ed);
                thirdPartServiceActivity.ao.setText(R.string._2130841450_res_0x7f020f6a);
                KeyValDbManager.b(BaseApplication.getContext()).d("third_part_service_we_chat_status", "true", null);
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "wechat_red_dot_show", "true", new StorageParams());
            } else {
                LogUtil.a("ThirdPartServiceActivity", "updateWeChatBindStatus isBound = ", str);
            }
            thirdPartServiceActivity.ai();
        }

        private void c(ThirdPartServiceActivity thirdPartServiceActivity) {
            thirdPartServiceActivity.as.setVisibility(0);
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "wx_device_entrance_supported", "true", new StorageParams());
            Context context = BaseApplication.getContext();
            String num = Integer.toString(10000);
            long currentTimeMillis = System.currentTimeMillis();
            SharedPreferenceManager.e(context, num, "wx_device_entrance_supported_ts", String.valueOf(currentTimeMillis), new StorageParams());
            LogUtil.a("ThirdPartServiceActivity", "updateWeChatDeviceSupportedStatus update status and timestamp ");
            thirdPartServiceActivity.u();
        }

        private void dWI_(Message message, ThirdPartServiceActivity thirdPartServiceActivity) {
            boolean booleanValue = message.obj instanceof Boolean ? ((Boolean) message.obj).booleanValue() : false;
            LogUtil.a("ThirdPartServiceActivity", "updateWeChatDeviceBindStatus hasBound = " + booleanValue);
            thirdPartServiceActivity.ar.setText(booleanValue ? R.string._2130841450_res_0x7f020f6a : R.string._2130841449_res_0x7f020f69);
        }

        private void dWE_(Message message, ThirdPartServiceActivity thirdPartServiceActivity) {
            String str = message.obj instanceof String ? (String) message.obj : "";
            if ("false".equals(str)) {
                thirdPartServiceActivity.u.setText(R.string._2130841449_res_0x7f020f69);
            } else if ("true".equals(str)) {
                thirdPartServiceActivity.u.setText(R.string._2130841450_res_0x7f020f6a);
            } else {
                LogUtil.a("ThirdPartServiceActivity", "updateQqBindStatus isBound = ", str);
            }
        }

        private void dWC_(Message message, ThirdPartServiceActivity thirdPartServiceActivity) {
            String str = message.obj instanceof String ? (String) message.obj : "";
            if ("false".equals(str)) {
                thirdPartServiceActivity.k.setText(R.string._2130841449_res_0x7f020f69);
            } else if ("true".equals(str)) {
                thirdPartServiceActivity.k.setText(R.string._2130841450_res_0x7f020f6a);
            } else {
                LogUtil.a("ThirdPartServiceActivity", "updateGoogleFitBindStatus isBound = ", str);
            }
        }

        private void dWF_(ThirdPartServiceActivity thirdPartServiceActivity, Message message) {
            if (message.obj instanceof Boolean) {
                if (((Boolean) message.obj).booleanValue()) {
                    thirdPartServiceActivity.z.setText(R.string._2130841450_res_0x7f020f6a);
                } else {
                    thirdPartServiceActivity.z.setText(R.string._2130841449_res_0x7f020f69);
                }
            }
        }

        private void dWD_(ThirdPartServiceActivity thirdPartServiceActivity, Message message) {
            if (message.obj instanceof Boolean) {
                if (((Boolean) message.obj).booleanValue()) {
                    thirdPartServiceActivity.s.setText(R.string._2130841450_res_0x7f020f6a);
                } else {
                    thirdPartServiceActivity.s.setText(R.string._2130841449_res_0x7f020f69);
                }
            }
        }

        private void dWG_(ThirdPartServiceActivity thirdPartServiceActivity, Message message) {
            LogUtil.a("ThirdPartServiceActivity", "strava updateStravaBindStatus ");
            thirdPartServiceActivity.t.setVisibility(0);
            thirdPartServiceActivity.g.setVisibility(0);
            thirdPartServiceActivity.ag.setVisibility(0);
            thirdPartServiceActivity.l();
            thirdPartServiceActivity.ah();
            if (message.obj instanceof Boolean) {
                if (((Boolean) message.obj).booleanValue()) {
                    thirdPartServiceActivity.ah.setText(R.string._2130841450_res_0x7f020f6a);
                } else {
                    thirdPartServiceActivity.ah.setText(R.string._2130841449_res_0x7f020f69);
                }
            }
        }
    }

    private void ag() {
        RelativeLayout relativeLayout;
        if (isFinishing() || (relativeLayout = this.x) == null) {
            return;
        }
        relativeLayout.setVisibility(0);
        LogUtil.a("ThirdPartServiceActivity", "mLoadingDialog.show()");
    }

    private void aa() {
        RelativeLayout relativeLayout = this.x;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
            LogUtil.a("ThirdPartServiceActivity", "destroy mLoadingDialog");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        if (this.ab.getVisibility() == 8 && this.c.getVisibility() == 8 && this.ax.getVisibility() == 8 && this.as.getVisibility() == 8 && this.y.getVisibility() == 8 && this.b.getVisibility() == 8 && this.o.getVisibility() == 8 && this.ac.getVisibility() == 8 && this.r.getVisibility() == 8 && this.ag.getVisibility() == 8) {
            this.w.setVisibility(0);
        } else {
            this.w.setVisibility(8);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
