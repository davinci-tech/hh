package com.huawei.ui.main.stories.health.activity.healthdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity;
import com.huawei.ui.main.stories.health.adapter.WeightBodyIndexRecycleAdapter;
import com.huawei.ui.main.stories.health.fragment.WeightBodyAnalysisReportFragment;
import com.huawei.ui.main.stories.health.fragment.WeightShareFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.WaistToHipRatioFragment;
import com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback;
import com.huawei.ui.main.stories.health.util.WeightCommonView;
import com.huawei.ui.main.stories.health.views.healthdata.BodyTypeCardView;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.doj;
import defpackage.dph;
import defpackage.gnm;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nmm;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.qkd;
import defpackage.qku;
import defpackage.qqv;
import defpackage.qqw;
import defpackage.qqy;
import defpackage.qrf;
import defpackage.qrp;
import defpackage.qsj;
import defpackage.rud;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class WeightDetailActivity extends BaseActivity implements View.OnClickListener, WaistToHipRatioFragment.WaistToHipCallback {

    /* renamed from: a, reason: collision with root package name */
    private WeightBodyIndexRecycleAdapter f10093a;
    private int aa;
    private CustomTitleBar ab;
    private HealthScrollView ac;
    private long ad;
    private HealthTextView ae;
    private int af;
    private LinearLayout ag;
    private String ah;
    private HealthTextView ai;
    private ConstraintLayout b;
    private BodyTypeCardView c;
    private Context f;
    private long g;
    private WeightCommonView h;
    private int i;
    private int j;
    private HealthLevelIndicator k;
    private boolean l;
    private b m;
    private Intent o;
    private boolean p;
    private boolean s;
    private WeightBodyAnalysisReportFragment u;
    private HealthRecycleView v;
    private cfi x;
    private HealthTextView y;
    private WeightShareFragment z;
    private final Context d = BaseApplication.e();
    private cfe t = new cfe();
    private boolean q = false;
    private List<qkd> n = new ArrayList(16);
    private List<qkd> w = new ArrayList(16);
    private List<cfe> e = new ArrayList(16);
    private boolean r = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_weight_body_index_detial);
        clearBackgroundDrawable();
        Intent intent = getIntent();
        this.o = intent;
        if (intent == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDetailActivity", "onCreate intent is null");
            finish();
            return;
        }
        if (intent.hasExtra("type")) {
            this.af = this.o.getIntExtra("type", 0);
        }
        ReleaseLogUtil.b("HealthWeight_WeightDetailActivity", "onCreate mType ", Integer.valueOf(this.af));
        this.p = this.af == 0;
        this.f = this;
        this.m = new b(this);
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        this.x = currentUser;
        if (currentUser == null || TextUtils.isEmpty(currentUser.i())) {
            MultiUsersManager.INSTANCE.setCurrentUser((cfi) this.o.getSerializableExtra("currentUser"));
            MultiUsersManager.INSTANCE.setMainUser((cfi) this.o.getSerializableExtra("mainUser"));
            this.x = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        r();
        n();
    }

    private void r() {
        this.h = (WeightCommonView) findViewById(R.id.hw_show_health_weight_detail_common_view);
        HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.weight_scrollView);
        this.ac = healthScrollView;
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        this.ac.smoothScrollTo(0, 0);
        this.ab = (CustomTitleBar) findViewById(R.id.health_healthdata_bodyindex_title_layout);
        this.b = (ConstraintLayout) findViewById(R.id.hw_show_body_score_layout);
        this.y = (HealthTextView) findViewById(R.id.hw_show_health_data_weight_bodyindex_mid_time);
        HealthLevelIndicator healthLevelIndicator = (HealthLevelIndicator) findViewById(R.id.weight_detail_specification_indicator);
        this.k = healthLevelIndicator;
        healthLevelIndicator.setOnClickListener(this);
        this.ae = (HealthTextView) findViewById(R.id.hw_show_health_data_weight_mid_weight_score);
        this.ai = (HealthTextView) findViewById(R.id.hw_show_body_fit_score_suggestion);
        o();
        this.c = (BodyTypeCardView) findViewById(R.id.hw_show_detail_body_type_card_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.health_healthdata_inputweight_top_tip);
        this.ag = linearLayout;
        linearLayout.setOnClickListener(this);
        this.c.setVisibility(8);
        this.c.setOnClickListener(this);
        CustomTitleBar customTitleBar = this.ab;
        Context context = this.f;
        customTitleBar.setRightSoftkeyBackground(ContextCompat.getDrawable(context, LanguageUtil.bc(context) ? R.drawable._2131430036_res_0x7f0b0a94 : R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R$string.accessibility_share));
    }

    private void o() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.hw_show_health_data_bodyindex_detail_recycle);
        this.v = healthRecycleView;
        healthRecycleView.setLayoutManager(new GridLayoutManager(this.f, 3) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.v.setIsScroll(false);
        if (this.v.getItemDecorationCount() <= 0) {
            int dimensionPixelSize = this.f.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(Integer.valueOf(dimensionPixelSize));
            arrayList.add(Integer.valueOf(dimensionPixelSize));
            arrayList.add(Integer.valueOf(dimensionPixelSize));
            arrayList.add(Integer.valueOf(this.f.getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a)));
            int dimensionPixelSize2 = this.f.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
            this.v.addItemDecoration(new RecyclerItemDecoration(dimensionPixelSize2, dimensionPixelSize2, arrayList));
        }
    }

    private void a() {
        long au = this.t.au();
        t();
        if (this.t.isVisible(31, this.q) && this.t.isVisible(1, this.q) && qsj.e(this.t, this.q)) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        if (this.t.ao() == 0.0d) {
            WaistToHipRatioFragment.b(this.f);
        }
        this.y.setText(DateFormatUtil.d(au, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD) + " " + nsj.c(this.f, au, 1));
        x();
        this.h.setWeightCommonView(this.t, this.q, true);
        m();
        u();
    }

    private void m() {
        double[] e = doj.e(Utils.o(), this.t.an(), this.t.e());
        if (!qsj.e(e, 2)) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "initSpecification, bodyMassIndexValue is out of bound.");
            return;
        }
        float f = (float) e[0];
        float f2 = (float) e[1];
        float f3 = (float) e[2];
        float b2 = b(f2, 0.1f, true);
        String a2 = qqy.a(0, 1);
        String a3 = qqy.a(0, 2);
        String a4 = qqy.a(0, 3);
        String a5 = qqy.a(0, 4);
        int color = getColor(qrf.a(1));
        int color2 = getColor(qrf.a(2));
        int color3 = getColor(qrf.a(3));
        int color4 = getColor(qrf.a(4));
        double[] d2 = doj.d(Utils.o());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm((float) d2[0], f, color, a2));
        arrayList.add(new nmm(f, b2, color2, a3));
        arrayList.add(new nmm(b2, f3, color3, a4));
        arrayList.add(new nmm(f3, (float) d2[1], color4, a5));
        qsj.a(this.k, arrayList, e, d2);
        dBo_(findViewById(R.id.weight_body_level_rang_low), a2, color);
        dBo_(findViewById(R.id.weight_body_level_rang_stand), a3, color2);
        dBo_(findViewById(R.id.weight_body_level_rang_high), a4, color3);
        dBo_(findViewById(R.id.weight_body_level_rang_super_high), a5, color4);
        String e2 = UnitUtil.e(UnitUtil.a(this.t.ax()), 1, this.t.getFractionDigitByType(0));
        SpannableString spannableString = new SpannableString(e2);
        spannableString.setSpan(new AbsoluteSizeSpan(38, true), 0, e2.length(), 18);
        this.k.setLevel((float) UnitUtil.a(this.t.j(), 1), spannableString);
        this.k.setLevelUnit(qsj.e());
        this.k.setVisibility(0);
    }

    private float b(float f, float f2, boolean z) {
        BigDecimal valueOf = BigDecimal.valueOf(f);
        BigDecimal valueOf2 = BigDecimal.valueOf(f2);
        if (z) {
            return valueOf.add(valueOf2).floatValue();
        }
        return valueOf.subtract(valueOf2).floatValue();
    }

    private void dBo_(View view, String str, int i) {
        view.setVisibility(0);
        ImageView imageView = (ImageView) view.findViewById(R.id.weight_body_level_rang_item_icon);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(i);
        imageView.setImageDrawable(gradientDrawable);
        ((HealthTextView) view.findViewById(R.id.weight_body_level_rang_item_name)).setText(str);
        view.findViewById(R.id.weight_body_level_rang_item_content).setVisibility(8);
    }

    private void n() {
        Serializable serializableExtra = this.o.getSerializableExtra("weightBean");
        this.i = this.o.getIntExtra("clientId", 0);
        if (serializableExtra instanceof cfe) {
            this.t = (cfe) serializableExtra;
            f();
            return;
        }
        this.ah = this.o.getStringExtra(JsbMapKeyNames.H5_USER_ID);
        long longExtra = this.o.getLongExtra("startTime", 0L);
        long longExtra2 = this.o.getLongExtra("endTime", 0L);
        LogUtil.a("HealthWeight_WeightDetailActivity", "initData startTime ", Long.valueOf(longExtra), " endTime ", Long.valueOf(longExtra2), " mType ", Integer.valueOf(this.af), JsbMapKeyNames.H5_USER_ID, this.ah);
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(longExtra, longExtra2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        qsj.e(MultiUsersManager.INSTANCE.getCurrentUser(), hiAggregateOption, (HiAggregateOption) null, new d(this));
    }

    /* loaded from: classes6.dex */
    static class d implements WeightAndAiBodyShapeCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<WeightDetailActivity> f10095a;

        d(WeightDetailActivity weightDetailActivity) {
            this.f10095a = new WeakReference<>(weightDetailActivity);
        }

        @Override // com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback
        public void getWeightAndAIBodyShape(ArrayList<cfe> arrayList, ArrayList<qku> arrayList2) {
            if (koq.b(arrayList)) {
                LogUtil.h("HealthWeight_WeightDetailActivity", "InnerWeightAndAiBodyShapeCallback list ", arrayList);
                return;
            }
            WeightDetailActivity weightDetailActivity = this.f10095a.get();
            if (weightDetailActivity != null && !weightDetailActivity.isFinishing() && !weightDetailActivity.isDestroyed()) {
                weightDetailActivity.t = arrayList.get(0);
                weightDetailActivity.f();
                weightDetailActivity.d();
                int i = weightDetailActivity.af;
                if (i == 1) {
                    weightDetailActivity.e();
                    return;
                } else {
                    if (i != 2) {
                        return;
                    }
                    weightDetailActivity.e(weightDetailActivity.f);
                    return;
                }
            }
            LogUtil.h("HealthWeight_WeightDetailActivity", "InnerWeightAndAiBodyShapeCallback getWeightAndAIBodyShape activity ", weightDetailActivity);
        }
    }

    private void t() {
        this.ab.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qfd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightDetailActivity.this.dBp_(view);
            }
        });
        this.ab.setRightSoftkeyVisibility(0);
        k();
        this.ab.setRightThirdKeyBackground(nsf.cKq_(R.drawable._2131430626_res_0x7f0b0ce2), nsf.h(R$string.IDS_read_report));
        this.ab.setRightThirdKeyVisibility(8);
        this.ab.setRightThirdKeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WeightDetailActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ab.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: qez
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightDetailActivity.this.dBq_(view);
            }
        });
    }

    public /* synthetic */ void dBp_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dBq_(View view) {
        e(view.getContext());
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: qfe
                @Override // java.lang.Runnable
                public final void run() {
                    WeightDetailActivity.this.f();
                }
            });
            return;
        }
        this.q = !qrp.d();
        boolean booleanExtra = this.o.getBooleanExtra("guest_measure", false);
        this.r = booleanExtra;
        LogUtil.a("HealthWeight_WeightDetailActivity", "initData() mIsGuestMeasure = ", Boolean.valueOf(booleanExtra));
        if (this.r) {
            this.t = qsj.d(this.t, this.q);
        }
        if (this.t == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "initData mLatestBean = null");
            finish();
        } else {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e(final Context context) {
        cfe cfeVar;
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: qey
                @Override // java.lang.Runnable
                public final void run() {
                    WeightDetailActivity.this.e(context);
                }
            });
            return;
        }
        if (!(context instanceof Activity) || (cfeVar = this.t) == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "parent is not a Activity or mLatestBean is null!");
            return;
        }
        if (TextUtils.isEmpty(cfeVar.n())) {
            i();
        } else {
            b();
        }
        qsj.e(AnalyticsValue.HEALTH_HEALTH_WEIGHT_DETAILS_SHARE_2030059.value(), this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public void e() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: qfh
                @Override // java.lang.Runnable
                public final void run() {
                    WeightDetailActivity.this.e();
                }
            });
            return;
        }
        Activity activity = (Activity) this.f;
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (this.t == null || currentUser == null) {
            return;
        }
        WeightBodyAnalysisReportFragment weightBodyAnalysisReportFragment = new WeightBodyAnalysisReportFragment();
        this.u = weightBodyAnalysisReportFragment;
        weightBodyAnalysisReportFragment.dEk_(activity, this.r);
        this.u.a(this.t);
        b bVar = this.m;
        if (bVar != null) {
            Message obtainMessage = bVar.obtainMessage();
            obtainMessage.what = 2000;
            this.m.sendMessage(obtainMessage);
            qsj.e(AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_DUAL_MODE_ADD_SUCCESS_2060037.value(), this.f);
        }
    }

    private void i() {
        cfe cfeVar;
        LogUtil.a("HealthWeight_WeightDetailActivity", "fetchDeviceInfoAndShare");
        if (this.f == null || (cfeVar = this.t) == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "fetchDeviceInfoAndShare mContext is null or mLatestBean is null");
        } else {
            cfeVar.c(j());
            b();
        }
    }

    private String j() {
        cfe cfeVar = this.t;
        if (cfeVar == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDetailActivity", "getDeviceName mLatestBean is null");
            return "";
        }
        String n = cfeVar.n();
        if (!TextUtils.isEmpty(n)) {
            ReleaseLogUtil.b("HealthWeight_WeightDetailActivity", "getDeviceName deviceName ", n);
            return n;
        }
        String weightScaleProductId = this.t.getWeightScaleProductId();
        dcz d2 = ResourceManager.e().d(weightScaleProductId);
        LogUtil.a("HealthWeight_WeightDetailActivity", "getDeviceName productId ", weightScaleProductId, " productInfo ", d2);
        if (d2 == null) {
            return "";
        }
        dcz.b n2 = d2.n();
        LogUtil.a("HealthWeight_WeightDetailActivity", "getDeviceName productManifest ", n2);
        if (n2 == null) {
            return "";
        }
        String d3 = dcx.d(weightScaleProductId, n2.b());
        ReleaseLogUtil.b("HealthWeight_WeightDetailActivity", "getDeviceName deviceName ", d3);
        return d3;
    }

    private void k() {
        Drawable cKq_ = nsf.cKq_(R.drawable._2131430626_res_0x7f0b0ce2);
        if (LanguageUtil.bc(this.d)) {
            BitmapDrawable cKm_ = nrz.cKm_(this.d, cKq_);
            if (cKm_ == null) {
                this.ab.setRightThirdKeyBackground(cKq_, nsf.h(R$string.IDS_read_report));
                return;
            } else {
                this.ab.setRightThirdKeyBackground(cKm_, nsf.h(R$string.IDS_read_report));
                return;
            }
        }
        this.ab.setRightThirdKeyBackground(cKq_, nsf.h(R$string.IDS_read_report));
    }

    public void b() {
        Context context = this.f;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
            if (this.t == null || currentUser == null) {
                return;
            }
            WeightShareFragment weightShareFragment = new WeightShareFragment();
            this.z = weightShareFragment;
            weightShareFragment.d(this.r);
            this.z.dEr_(activity, this.q);
            this.z.a(this.t, this.f);
            Message obtainMessage = this.m.obtainMessage();
            obtainMessage.what = 1000;
            this.m.sendMessageDelayed(obtainMessage, this.p ? 0L : 100L);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.weightbodydata.WaistToHipRatioFragment.WaistToHipCallback
    public void onWaistToHipRatioChanged(cfe cfeVar) {
        this.t = cfeVar;
        this.n.clear();
        this.n.addAll(qqv.e(this.t, false, this.q));
        this.f10093a.d(this.t);
        this.f10093a.e(this.n);
    }

    /* loaded from: classes6.dex */
    public static class b extends BaseHandler<WeightDetailActivity> {
        b(WeightDetailActivity weightDetailActivity) {
            super(weightDetailActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dBr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightDetailActivity weightDetailActivity, Message message) {
            if (!weightDetailActivity.isFinishing() && !weightDetailActivity.isDestroyed()) {
                weightDetailActivity.c(message.what);
            } else {
                ReleaseLogUtil.a("HealthWeight_WeightDetailActivity", "handleMessageWhenReferenceNotNull activity ", weightDetailActivity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 1000) {
            if (this.z == null) {
                ReleaseLogUtil.a("HealthWeight_WeightDetailActivity", "showShareDialog mShareFragment is null");
                return;
            }
            if (PermissionUtil.c()) {
                this.z.a();
            } else {
                PermissionUtil.b(this.f, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this.f) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity.4
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        WeightDetailActivity.this.z.a();
                    }
                });
            }
            this.l = !this.p;
            return;
        }
        if (i == 2000) {
            WeightBodyAnalysisReportFragment weightBodyAnalysisReportFragment = this.u;
            if (weightBodyAnalysisReportFragment == null) {
                ReleaseLogUtil.a("HealthWeight_WeightDetailActivity", "showShareDialog mReportFragment is null");
                return;
            } else {
                weightBodyAnalysisReportFragment.c();
                this.l = !this.p;
                return;
            }
        }
        ReleaseLogUtil.a("HealthWeight_WeightDetailActivity", "showShareDialog default shareType ", Integer.valueOf(i));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReleaseLogUtil.b("HealthWeight_WeightDetailActivity", "onResume mIsAutoDisplayShare ", Boolean.valueOf(this.l), " mType ", Integer.valueOf(this.af));
        if (this.l && this.af == 2) {
            finish();
        } else {
            d();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ReleaseLogUtil.b("HealthWeight_WeightDetailActivity", "onPause mIsAutoDisplayShare ", Boolean.valueOf(this.l), " mType ", Integer.valueOf(this.af));
        if (this.l && this.af == 1) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public void d() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: qfi
                @Override // java.lang.Runnable
                public final void run() {
                    WeightDetailActivity.this.d();
                }
            });
            return;
        }
        cfe cfeVar = this.t;
        if (cfeVar == null) {
            return;
        }
        boolean isVisible = cfeVar.isVisible(1, this.q);
        if (isVisible || Utils.o()) {
            LogUtil.a("HealthWeight_WeightDetailActivity", "has bodyFat or is oversea set topTip gone");
            this.ag.setVisibility(8);
        } else {
            LogUtil.h("HealthWeight_WeightDetailActivity", "no bodyFat and is not oversea set topTip visible");
            this.ag.setVisibility(0);
        }
        if (qsj.e(this.t, this.q) && isVisible) {
            this.ab.setRightSoftkeyVisibility(0);
            if (q()) {
                this.ab.setRightThirdKeyVisibility(0);
                return;
            } else {
                this.ab.setRightThirdKeyVisibility(8);
                return;
            }
        }
        this.ab.setRightThirdKeyVisibility(8);
        this.ab.setRightSoftkeyVisibility(8);
    }

    private void u() {
        PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        privacyDataModel.setClientId(this.i);
        privacyDataModel.setStartTime(this.t.au());
        privacyDataModel.setEndTime(this.t.av());
        privacyDataModel.setModifyTime(this.t.av());
        privacyDataModel.setDeviceName(j());
        double a2 = UnitUtil.a(this.t.ax());
        privacyDataModel.setDataTitle(UnitUtil.e(a2, 1, 1) + " " + qsj.e(a2, false));
        privacyDataModel.setDoubleValue(a2);
        rud.c(this.f, this.ab, 109, privacyDataModel);
    }

    private boolean q() {
        cfe cfeVar = this.t;
        if (cfeVar == null) {
            return false;
        }
        double[] ag = cfeVar.ag();
        if (ag == null || ag.length < 6 || this.t.aa() != 2) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "isShowTitle valid fail");
            return false;
        }
        for (double d2 : ag) {
            if (d2 <= 0.0d) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WaistToHipRatioFragment.b((Context) null);
    }

    private void x() {
        if (this.x == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "mainuser is null");
            return;
        }
        z();
        y();
        v();
        WeightBodyIndexRecycleAdapter weightBodyIndexRecycleAdapter = new WeightBodyIndexRecycleAdapter(this.f, this.n, this.t, this.q);
        this.f10093a = weightBodyIndexRecycleAdapter;
        weightBodyIndexRecycleAdapter.c(this.r);
        this.v.setAdapter(this.f10093a);
        if (this.x.d() > 0) {
            s();
        } else {
            g();
        }
    }

    private void s() {
        cfi cfiVar = this.x;
        if (cfiVar == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "processBodyTypeData mainuser is null");
            return;
        }
        this.e = qsj.b(cfiVar.i(), this.t.au(), this.x, this.q);
        p();
        int i = this.aa;
        boolean z = this.s && dph.d((double) i) && dph.d((double) this.j) && (i != 0 && this.j != 0);
        this.s = z;
        if (z && qsj.e(this.t, this.q) && !this.q) {
            if (CommonUtil.bv()) {
                this.c.setBodyTypeDatas(this.aa, this.j, this.ad, this.g);
                this.c.setVisibility(0);
                return;
            }
            return;
        }
        this.c.setVisibility(8);
    }

    private void g() {
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qff
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightDetailActivity.this.c(i, (cfi) obj);
            }
        });
    }

    public /* synthetic */ void c(final int i, final cfi cfiVar) {
        Context context = this.f;
        if (context == null || !(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "getUserWeightTwoWeekData mContext is null or mContext is not Activity");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: qfb
                @Override // java.lang.Runnable
                public final void run() {
                    WeightDetailActivity.this.c(cfiVar, i);
                }
            });
        }
    }

    public /* synthetic */ void c(cfi cfiVar, int i) {
        if (cfiVar == null || i != 0) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "getUserWeigh loadDataSuccess getCurrentUser: currentUser is null return");
            cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        this.x = cfiVar;
        s();
    }

    private void z() {
        this.ai.setText(qqw.b(this.t));
    }

    private void y() {
        double h = this.t.h();
        if (dph.a(h)) {
            this.ae.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf((int) h)));
        }
    }

    private boolean p() {
        this.s = false;
        if (this.t == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "isShowBadyTypeCard mLatestBean = null");
            return false;
        }
        this.aa = 0;
        this.j = 0;
        List<cfe> list = this.e;
        if (list == null || list.size() <= 1) {
            LogUtil.a("HealthWeight_WeightDetailActivity", "weightDetail isShowBadyTypeCard mBodyTypeDataList is null or size <= 1");
            return this.s;
        }
        LogUtil.a("HealthWeight_WeightDetailActivity", "isShowBadyTypeCard mBodyTypeDataList size == ", Integer.valueOf(this.e.size()));
        List<cfe> list2 = this.e;
        cfe cfeVar = list2.get(list2.size() - 1);
        if (jdl.f(cfeVar.au(), this.t.au())) {
            return this.s;
        }
        int size = this.e.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (jdl.f(cfeVar.au(), this.e.get(size).au())) {
                size--;
            } else if (this.e.get(size).au() == this.t.au() && cfeVar.t() > 0) {
                LogUtil.a("HealthWeight_WeightDetailActivity", "isShowBadyTypeCard, bodyType has chenged... ");
                this.aa = qsj.e(cfeVar);
                this.j = qsj.e(this.t);
                this.ad = cfeVar.au();
                this.g = this.t.au();
            }
        }
        LogUtil.a("HealthWeight_WeightDetailActivity", "isShowBadyTypeCard, mStartType = " + this.aa + "; mEndType=" + this.j + "; mStartTime=" + this.ad + "; mEndTime=" + this.g);
        if (this.aa != this.j) {
            this.s = true;
        }
        return this.s;
    }

    private void v() {
        if (this.t == null || this.n == null) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "mLatestBean is null or indexRecycleItems is null.");
            return;
        }
        this.w.clear();
        this.n.clear();
        this.n.addAll(qqv.e(this.t, false, this.q));
        if (koq.b(this.n)) {
            LogUtil.h("HealthWeight_WeightDetailActivity", "indexRecycleItems size == 0 .");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c) {
            LogUtil.a("HealthWeight_WeightDetailActivity", "onClick() mIsGuestMeasure = ", Boolean.valueOf(this.r));
            Intent intent = new Intent(this.f, (Class<?>) WeightBodyDataActivity.class);
            intent.putExtra("isBodyType", true);
            intent.putExtra("WeightBean", this.t);
            intent.putExtra("start_time", this.ad);
            intent.putExtra("start_type", this.aa);
            intent.putExtra("is_show_change", this.s);
            intent.putExtra("isGuestMeasure", this.r);
            gnm.aPB_(this.f, intent);
        } else if (view == this.k) {
            l();
        } else if (view == this.ag) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qfc
                @Override // java.lang.Runnable
                public final void run() {
                    WeightDetailActivity.this.c();
                }
            });
        } else {
            LogUtil.h("HealthWeight_WeightDetailActivity", "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void c() {
        final String d2 = qsj.d();
        runOnUiThread(new Runnable() { // from class: qfa
            @Override // java.lang.Runnable
            public final void run() {
                WeightDetailActivity.this.c(d2);
            }
        });
    }

    public /* synthetic */ void c(String str) {
        LogUtil.a("HealthWeight_WeightDetailActivity", "onClick runOnUiThread");
        a(str);
    }

    private void a(String str) {
        LogUtil.a("HealthWeight_WeightDetailActivity", "enter enterTipsDetail");
        Intent intent = new Intent(this.f, (Class<?>) WebViewActivity.class);
        intent.putExtra("title", this.f.getString(R$string.IDS_hw_health_show_healthdata_more_question));
        intent.putExtra("url", str);
        gnm.aPB_(this.f, intent);
    }

    private void l() {
        LogUtil.a("HealthWeight_WeightDetailActivity", "mDataLayout--- onClick... ");
        LogUtil.a("HealthWeight_WeightDetailActivity", "gotoData() mIsGuestMeasure = ", Boolean.valueOf(this.r));
        Intent intent = new Intent(this.f, (Class<?>) WeightBodyDataActivity.class);
        intent.putExtra("isWeight", true);
        intent.putExtra("position", 0);
        cfe cfeVar = this.t;
        if (cfeVar != null && this.f != null) {
            intent.putExtra("WeightBean", cfeVar);
            intent.putExtra("start_time", this.ad);
            intent.putExtra("start_type", this.aa);
            intent.putExtra("isGuestMeasure", this.r);
            gnm.aPB_(this.f, intent);
            return;
        }
        LogUtil.h("HealthWeight_WeightDetailActivity", "gotoData latestBean is null");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
