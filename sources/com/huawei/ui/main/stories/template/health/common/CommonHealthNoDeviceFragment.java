package com.huawei.ui.main.stories.template.health.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.widget.NestedScrollView;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.ui.commonui.activetips.ActiveTipsView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.SleepBootPagerHelper;
import com.huawei.ui.main.stories.template.ResourceParseHelper;
import com.huawei.ui.main.stories.template.data.PageDataObserver;
import com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment;
import com.huawei.ui.main.stories.template.health.config.HealthNoDeviceConfig;
import com.huawei.ui.main.stories.template.health.view.NoDataResolutionView;
import com.huawei.ui.main.stories.template.health.view.NoDataViewContainer;
import defpackage.cdy;
import defpackage.efb;
import defpackage.gge;
import defpackage.gnm;
import defpackage.gpn;
import defpackage.gpp;
import defpackage.koq;
import defpackage.mtp;
import defpackage.njn;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qqt;
import defpackage.rxx;
import defpackage.ryg;
import defpackage.ryk;
import defpackage.ryl;
import defpackage.ryn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class CommonHealthNoDeviceFragment extends BaseFragment implements PageDataObserver, ResourceParseHelper.ConfigInfoCallback {
    private CustomTitleBar ac;
    private View b;
    private ActiveTipsView c;
    private Activity e;
    private NoDataViewContainer f;
    private LinearLayout g;
    private Context h;
    private int i;
    private NoDataResolutionView j;
    private ImageView m;
    private HealthNoDeviceConfig n;
    private RelativeLayout o;
    private PayApi p;
    private int q;
    private ryk r;
    private ResourceParseHelper s;
    private NestedScrollView.OnScrollChangeListener t;
    private String u;
    private NestedScrollView v;
    private HealthTextView w;
    private Runnable x;
    private View y;
    private int z;

    /* renamed from: a, reason: collision with root package name */
    private int f10513a = -1;
    private final View.OnClickListener d = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment.4
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            hashMap.put("hasdate", "false");
            gge.e(AnalyticsValue.MANUALLY_MONITOR_SLEEP_21300044.value(), hashMap);
            if (view.getId() == R.id.section_root_view) {
                mtp.d().e(CommonHealthNoDeviceFragment.this.h, "0");
                ViewClickInstrumentation.clickOnView(view);
            } else {
                if (view.getId() == R.id.sleep_no_data_device_card) {
                    LogUtil.a("CommonHealth", "sleep no data device card is clicked!");
                    GRSManager.a(CommonHealthNoDeviceFragment.this.h).e("activityUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment.4.4
                        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                        public void onCallBackSuccess(String str) {
                            String str2;
                            if (TextUtils.isEmpty(str)) {
                                str2 = nsf.h(R$string.url_sleep_date_guide);
                                ReleaseLogUtil.a("CommonHealth", "onClick messageCenter ", str);
                            } else {
                                str2 = str + "/messageH5/sleephtml/sleepDateGuide.html";
                            }
                            if (CommonUtil.cc()) {
                                str2 = str + "/recommendH5/sleephtml/sleepDateGuide.html";
                            }
                            CommonHealthNoDeviceFragment.this.c(str2, true);
                        }

                        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                        public void onCallBackFail(int i) {
                            LogUtil.c("CommonHealth", "GRSManager onCallBackFail ACTIVITY_KEY code = ", Integer.valueOf(i));
                        }
                    });
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    };
    private boolean l = true;
    private boolean k = false;

    public static CommonHealthNoDeviceFragment b(String str, int i) {
        CommonHealthNoDeviceFragment commonHealthNoDeviceFragment = new CommonHealthNoDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("common_no_device_service_id", str);
        bundle.putInt("common_no_device_page_type", i);
        commonHealthNoDeviceFragment.setArguments(bundle);
        return commonHealthNoDeviceFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.h = getContext();
        View inflate = layoutInflater.inflate(R.layout.fragment_health_common_no_device, viewGroup, false);
        this.y = inflate;
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.u = arguments.getString("common_no_device_service_id");
            this.f10513a = arguments.getInt("common_no_device_bind_type");
        }
        if (this.f10513a == 1) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.common_no_bind_layout);
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.common_bind_no_record_layout);
            try {
                if (this.b == null) {
                    View inflate = viewStub.inflate();
                    this.b = inflate;
                    ImageView imageView = (ImageView) inflate.findViewById(R.id.common_bind_no_record_image);
                    HealthTextView healthTextView = (HealthTextView) this.b.findViewById(R.id.common_bind_no_record_text);
                    nsy.cMm_(imageView, dTX_(this.f10513a));
                    nsy.cMr_(healthTextView, d(this.f10513a));
                    linearLayout.setVisibility(8);
                    this.b.setVisibility(0);
                }
            } catch (InflateException unused) {
                LogUtil.b("CommonHealth", "inflate mBindNoRecordLayout fail");
            }
        }
        dTY_(view);
        c();
        c(this.u);
        this.f.setMarketingResource(this.e, this.u);
    }

    private void e() {
        new SleepBootPagerHelper(this.h).showBootPage((LinearLayout) this.y.findViewById(R.id.boot_page_layout_container));
    }

    private void c() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getInt("common_no_device_page_type") == 1 && efb.b(this.h)) {
            e();
            View inflate = LayoutInflater.from(this.h).inflate(R.layout.sleep_no_data_entry_layout, (ViewGroup) this.g, false);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.container_root_layout);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            if (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.setMarginStart(((Integer) safeRegionWidth.first).intValue());
                layoutParams.setMarginEnd(((Integer) safeRegionWidth.second).intValue());
                linearLayout.setLayoutParams(layoutParams);
            }
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.section_root_view);
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.btn_image);
            healthButton.setText(this.h.getResources().getString(R$string.IDS_hw_health_show_healthdata_know_detail));
            healthButton.setAutoTextInfo(13, 1, 1);
            if (LanguageUtil.bc(this.h)) {
                ((ImageView) inflate.findViewById(R.id.background_image)).setImageDrawable(nrz.cKm_(this.h, getResources().getDrawable(R.drawable.sleep_no_data_device_card_img)));
            }
            View.OnClickListener cwZ_ = nkx.cwZ_(this.d, (BaseActivity) this.h, true, "");
            linearLayout2.setOnClickListener(cwZ_);
            HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.sleep_no_data_device_card);
            healthCardView.setOnClickListener(cwZ_);
            if (healthCardView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) healthCardView.getLayoutParams();
                layoutParams2.width = nsn.p(this.h);
                layoutParams2.setMarginStart(nsn.d(this.h, 0, false));
                layoutParams2.setMarginEnd(nsn.c(this.h, 0, false));
                healthCardView.setLayoutParams(layoutParams2);
            }
            this.g.addView(inflate);
            this.g.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if ("BloodPressureCardConstructor".equals(this.u)) {
            LogUtil.a("CommonHealth", "reflushExclusiveGuardian");
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, boolean z) {
        Intent intent = new Intent(this.h, (Class<?>) WebViewActivity.class);
        intent.putExtra("EXTRA_BI_SOURCE", "SLEEPDETAIL");
        intent.putExtra("EXTRA_BI_NAME", "sleep_service");
        intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        intent.putExtra("url", str);
        if (z) {
            intent.putExtra(Constants.IS_GUIDE, true);
        }
        gnm.aPB_(this.h, intent);
    }

    private void dTY_(View view) {
        this.j = (NoDataResolutionView) view.findViewById(R.id.health_detail_resolution_view);
        this.g = (LinearLayout) view.findViewById(R.id.no_data_fragment_layout_for_sleep_page);
        this.f = (NoDataViewContainer) view.findViewById(R.id.no_data_fragment_view_container);
        ActiveTipsView activeTipsView = (ActiveTipsView) view.findViewById(R.id.active_tips_view);
        this.c = activeTipsView;
        activeTipsView.setSelected(true);
        this.m = (ImageView) view.findViewById(R.id.no_data_fragment_head_img);
        this.ac = (CustomTitleBar) view.findViewById(R.id.health_detail_no_data_title_layout);
        this.v = (NestedScrollView) view.findViewById(R.id.health_detail_no_data_scrollview);
        h();
        this.o = (RelativeLayout) view.findViewById(R.id.net_work_layout);
        this.w = (HealthTextView) view.findViewById(R.id.tips_net_work_down);
        b();
        this.ac.setTitleBarBackgroundColor(getContext().getResources().getColor(R.color._2131298874_res_0x7f090a3a));
        ResourceParseHelper.d(this);
        this.w.setOnClickListener(new View.OnClickListener() { // from class: ryj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CommonHealthNoDeviceFragment.this.dTZ_(view2);
            }
        });
    }

    public /* synthetic */ void dTZ_(View view) {
        if (!this.l && !this.k) {
            ryn.d().c();
            ryn.d().e(this.q);
            c(this.u);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void h() {
        char c2;
        String str = this.u;
        str.hashCode();
        int i = 0;
        switch (str.hashCode()) {
            case 331334480:
                if (str.equals("BloodOxygenCardConstructor")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 354423382:
                if (str.equals("StressCardConstructor")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1135464211:
                if (str.equals("SleepCardConstructor")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1698958836:
                if (str.equals("HeartRateConstructor")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1783641739:
                if (str.equals("BloodPressureCardConstructor")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            i = 3034;
        } else if (c2 == 1) {
            i = 3035;
        } else if (c2 == 2) {
            i = 3036;
        } else if (c2 == 3) {
            i = 3033;
        } else if (c2 == 4) {
            i = 3039;
        }
        ScrollUtil.cKx_(this.v, getActivity().getWindow().getDecorView(), i);
    }

    private Drawable dTX_(int i) {
        if (i == 1) {
            return ContextCompat.getDrawable(this.h, R.drawable._2131429990_res_0x7f0b0a66);
        }
        return null;
    }

    private String d(int i) {
        return i == 1 ? this.h.getString(R$string.IDS_hw_show_main_bloodpressure_detail_norecord) : "";
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        NoDataViewContainer noDataViewContainer = this.f;
        if (noDataViewContainer != null) {
            noDataViewContainer.refreshTahiti();
        }
        ryk rykVar = this.r;
        if (rykVar != null) {
            ResourceParseHelper.d(rykVar.a().e(), this.u, this);
        }
    }

    private void c(String str) {
        this.k = true;
        this.l = true;
        this.w.setVisibility(8);
        this.e = getActivity();
        ResourceParseHelper resourceParseHelper = new ResourceParseHelper();
        this.s = resourceParseHelper;
        if (!resourceParseHelper.e(this.u)) {
            rxx.b().e(this.u, new d(this));
        } else if (!CommonUtil.aa(getActivity())) {
            this.o.setVisibility(0);
            this.k = false;
        } else {
            this.s.a(str, new c(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ryk rykVar) {
        this.n = rykVar.e();
        e(rykVar);
        Runnable runnable = new Runnable() { // from class: rym
            @Override // java.lang.Runnable
            public final void run() {
                CommonHealthNoDeviceFragment.this.a();
            }
        };
        this.x = runnable;
        this.f.post(runnable);
        f();
    }

    public /* synthetic */ void a() {
        this.f.setConfigInfo(this.n, false);
    }

    private void e(ryk rykVar) {
        ResourceParseHelper.a(rykVar.a().a(), this.u, this);
        ResourceParseHelper.a(rykVar.a().b(), this.u, this);
        ResourceParseHelper.d(rykVar.a().e(), this.u, this);
    }

    private void f() {
        ryg.b().e(this.q, this);
        ryn.d().a(this.q);
    }

    @Override // com.huawei.ui.main.stories.template.data.PageDataObserver
    public void update(ryg rygVar, List<cdy> list) {
        if (this.l) {
            this.f.setAllViewData(list);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Runnable runnable;
        ryg.b().e(this);
        ryn.d().c();
        ResourceParseHelper.a();
        rxx.b().c();
        if (this.e != null) {
            this.e = null;
        }
        if (this.j != null) {
            this.j = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.s != null) {
            this.s = null;
        }
        NoDataViewContainer noDataViewContainer = this.f;
        if (noDataViewContainer != null && (runnable = this.x) != null) {
            noDataViewContainer.removeCallbacks(runnable);
            this.x = null;
        }
        if (this.r != null) {
            this.r = null;
        }
        if (this.t != null) {
            this.t = null;
        }
        super.onDestroy();
    }

    private void b() {
        NestedScrollView.OnScrollChangeListener onScrollChangeListener = new NestedScrollView.OnScrollChangeListener() { // from class: com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment$$ExternalSyntheticLambda1
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                CommonHealthNoDeviceFragment.this.a(nestedScrollView, i, i2, i3, i4);
            }
        };
        this.t = onScrollChangeListener;
        this.v.setOnScrollChangeListener(onScrollChangeListener);
    }

    /* synthetic */ void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        this.z = this.ac.getMeasuredHeight();
        this.i = this.m.getMeasuredHeight() - this.z;
        Resources resources = getContext().getResources();
        if (this.i > i2) {
            this.ac.setTitleBarBackgroundColor(resources.getColor(R.color._2131298874_res_0x7f090a3a));
        } else {
            this.ac.setTitleBarBackgroundColor(resources.getColor(R.color._2131298875_res_0x7f090a3b));
        }
    }

    private void j() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getInt("common_no_device_page_type") == 7) {
            d();
        }
    }

    private void d() {
        LogUtil.a("CommonHealth", "getExclusiveGuardianData() enter");
        List<DeviceBenefitQueryParam> e = njn.e("CommonHealth", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_INBOX);
        if (!koq.b(e)) {
            if (this.p == null) {
                this.p = (PayApi) Services.a("TradeService", PayApi.class);
            }
            this.p.getAllDeviceBenefits(e, new a(this));
            return;
        }
        LogUtil.h("CommonHealth", "getExclusiveGuardianData() queryParamList isEmpty");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CopyOnWriteArrayList<DeviceBenefits> copyOnWriteArrayList) {
        LogUtil.a("CommonHealth", "processingData() enter");
        if (!koq.b(copyOnWriteArrayList)) {
            Iterator<DeviceBenefits> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                List<DeviceInboxInfo> inboxInfos = it.next().getInboxInfos();
                LogUtil.a("CommonHealth", "inboxInfos  = ", inboxInfos);
                if (!koq.b(inboxInfos)) {
                    Iterator<DeviceInboxInfo> it2 = inboxInfos.iterator();
                    while (it2.hasNext()) {
                        d(it2.next());
                    }
                } else {
                    LogUtil.h("CommonHealth", "processingData() deviceBenefits.getInboxInfos() isEmpty");
                }
            }
            return;
        }
        LogUtil.h("CommonHealth", "processingData() deviceBenefitsList isEmpty");
    }

    private void d(final DeviceInboxInfo deviceInboxInfo) {
        boolean e = qqt.e(deviceInboxInfo);
        LogUtil.a("CommonHealth", "isShowTips  = ", Boolean.valueOf(e));
        if (!e || this.c == null) {
            return;
        }
        String e2 = gpp.e(deviceInboxInfo);
        if (TextUtils.isEmpty(e2)) {
            e2 = "P12M";
        }
        String a2 = gpn.a(e2);
        LogUtil.a("CommonHealth", "timeStr  = ", a2);
        final String d2 = qqt.d(R$string.IDS_user_service_has_pick_tip, a2);
        LogUtil.a("CommonHealth", "tipText  = ", d2);
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    if (CommonHealthNoDeviceFragment.this.c != null) {
                        CommonHealthNoDeviceFragment.this.c.setVisibility(0);
                        CommonHealthNoDeviceFragment.this.c.setTipsText(d2);
                        CommonHealthNoDeviceFragment.this.c.setActiveBtText(CommonHealthNoDeviceFragment.this.getResources().getString(R$string.IDS_h5_vip_active_now));
                        CommonHealthNoDeviceFragment.this.c.setClickBannerListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment.3.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                if (deviceInboxInfo == null) {
                                    LogUtil.h("CommonHealth", "deviceInboxInfo is null");
                                    ViewClickInstrumentation.clickOnView(view);
                                } else {
                                    qqt.e(CommonHealthNoDeviceFragment.this.h, deviceInboxInfo.getLinkType(), OperationUtils.newPathConcat(deviceInboxInfo.getLinkValue(), "from", "8"));
                                    ViewClickInstrumentation.clickOnView(view);
                                }
                            }
                        });
                        return;
                    }
                    LogUtil.h("CommonHealth", "mActiveTipsView is null");
                }
            });
        } else {
            LogUtil.h("CommonHealth", "getActivity() is null");
        }
    }

    static class a implements IBaseResponseCallback {
        private WeakReference<CommonHealthNoDeviceFragment> c;

        a(CommonHealthNoDeviceFragment commonHealthNoDeviceFragment) {
            this.c = new WeakReference<>(commonHealthNoDeviceFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("CommonHealth", "GetAllDeviceBenefitsCallback errorCode = ", Integer.valueOf(i));
            CommonHealthNoDeviceFragment commonHealthNoDeviceFragment = this.c.get();
            if (commonHealthNoDeviceFragment == null) {
                LogUtil.h("CommonHealth", "commonHealthNoDeviceFragment is null");
            } else if (i == 0 && (obj instanceof CopyOnWriteArrayList)) {
                commonHealthNoDeviceFragment.e((CopyOnWriteArrayList<DeviceBenefits>) obj);
            } else {
                LogUtil.h("CommonHealth", "errorCode not success");
            }
        }
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getTitleName(String str) {
        this.ac.setTitleText(str);
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getDescription(String str) {
        NoDataResolutionView noDataResolutionView = this.j;
        if (noDataResolutionView != null) {
            noDataResolutionView.setData(str);
        }
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getImagePath(String str) {
        if (getActivity() == null) {
            LogUtil.h("CommonHealth", "getImagePath getActivity is null");
            return;
        }
        Activity activity = this.e;
        if (activity == null || activity.isFinishing() || this.e.isDestroyed()) {
            return;
        }
        nrf.cIv_(str, new RequestOptions().skipMemoryCache(true), this.m);
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void showParseErrorAlert() {
        this.l = false;
        this.w.setVisibility(0);
    }

    static class d implements Consumer<ryk> {
        private final WeakReference<CommonHealthNoDeviceFragment> d;

        d(CommonHealthNoDeviceFragment commonHealthNoDeviceFragment) {
            this.d = new WeakReference<>(commonHealthNoDeviceFragment);
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void accept(ryk rykVar) {
            int e;
            CommonHealthNoDeviceFragment commonHealthNoDeviceFragment = this.d.get();
            if (commonHealthNoDeviceFragment == null || commonHealthNoDeviceFragment.e == null) {
                return;
            }
            if (rykVar == null) {
                LogUtil.h("CommonHealth", "getTemplateConfig result is null");
                return;
            }
            ryl operationData = rykVar.e().getOperationData();
            if (operationData != null) {
                e = operationData.e();
            } else {
                e = rykVar.b().e();
            }
            commonHealthNoDeviceFragment.q = e;
            commonHealthNoDeviceFragment.k = false;
            commonHealthNoDeviceFragment.r = rykVar;
            commonHealthNoDeviceFragment.c(commonHealthNoDeviceFragment.r);
        }
    }

    static class c implements ResourceParseHelper.JsonResult {
        private final WeakReference<CommonHealthNoDeviceFragment> c;

        c(CommonHealthNoDeviceFragment commonHealthNoDeviceFragment) {
            this.c = new WeakReference<>(commonHealthNoDeviceFragment);
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.JsonResult
        public void onResult(ryk rykVar) {
            CommonHealthNoDeviceFragment commonHealthNoDeviceFragment = this.c.get();
            if (commonHealthNoDeviceFragment == null || commonHealthNoDeviceFragment.e == null) {
                return;
            }
            if (rykVar != null) {
                commonHealthNoDeviceFragment.k = false;
                commonHealthNoDeviceFragment.r = rykVar;
                commonHealthNoDeviceFragment.c(commonHealthNoDeviceFragment.r);
                return;
            }
            LogUtil.h("CommonHealth", "getTemplateConfig result is null");
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.JsonResult
        public void onFail() {
            CommonHealthNoDeviceFragment commonHealthNoDeviceFragment = this.c.get();
            if (commonHealthNoDeviceFragment == null) {
                return;
            }
            commonHealthNoDeviceFragment.k = false;
            LogUtil.c("CommonHealth", "requestConfig onFail");
        }
    }
}
