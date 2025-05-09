package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.view.CanvasChangeView;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.StandingFlexionViewModel;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.tencent.open.apireq.BaseResp;
import defpackage.hcn;
import defpackage.hjl;
import defpackage.hjx;
import defpackage.hma;
import defpackage.hmd;
import defpackage.hmf;
import defpackage.hng;
import defpackage.kxa;
import defpackage.nrn;
import defpackage.nrz;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class StandingFlexionFragment extends BaseSportExamFragment implements IOnBackPressed {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3735a;
    private hmd b;
    private kxa c;
    private hma d;
    private ImageView e;
    private View f;
    private hng j;
    private View k;
    private View l;
    private ImageView m;
    private ImageView n;
    private HealthNumberPicker o;
    private RelativeLayout p;
    private hmf q;
    private HealthTextView r;
    private HealthTextView t;
    private StandingFlexionViewModel u;
    private HealthTextView v;
    private HealthTextView x;
    private int w = 0;
    private int g = R.string._2130839976_res_0x7f0209a8;
    private int h = 0;
    private int i = 1;
    private boolean s = false;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.standing_flexion_fragment;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected boolean isSupportLandScreen() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        super.initView(view);
        bfC_(view);
        bfD_(view);
        f();
    }

    private void bfC_(View view) {
        this.mSportDataView = (CanvasChangeView) view.findViewById(R.id.sport_data_view);
        this.f = view.findViewById(R.id.left_data);
        this.x = (HealthTextView) view.findViewById(R.id.left_first_data);
        this.t = (HealthTextView) view.findViewById(R.id.left_second_data);
        this.m = (ImageView) view.findViewById(R.id.flexion_encourage_img);
        this.l = view.findViewById(R.id.flexion_beam_animation_layout);
        this.n = (ImageView) view.findViewById(R.id.flexion_ware_animation_img);
        this.v = (HealthTextView) view.findViewById(R.id.right_first_data);
        this.o = (HealthNumberPicker) view.findViewById(R.id.score_number_picker);
        this.p = (RelativeLayout) view.findViewById(R.id.score_picker_layout);
        this.f3735a = (HealthTextView) view.findViewById(R.id.body_status_tips);
        this.e = (ImageView) view.findViewById(R.id.bend_img);
        this.k = view.findViewById(R.id.flexion_anim_layout);
        if (LanguageUtil.h(getContext())) {
            this.f3735a.setTextSize(0, getResources().getDimension(R.dimen._2131365073_res_0x7f0a0cd1));
        } else {
            this.f3735a.setTextSize(0, getResources().getDimension(R.dimen._2131365072_res_0x7f0a0cd0));
        }
        t();
        j();
        i();
    }

    private void i() {
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.x.setTypeface(createFromAsset);
        this.v.setTypeface(createFromAsset);
    }

    private void j() {
        String[] strArr = new String[101];
        for (int i = 0; i < 101; i++) {
            strArr[i] = String.valueOf(i);
        }
        this.o.setDisplayedValues(strArr);
        this.o.setMaxValue(100);
        this.o.setMinValue(0);
        this.o.setSecondaryPaintColor(nrn.d(R.color._2131299386_res_0x7f090c3a));
        this.o.setAuxiliarySelectedTextSize(60.0f);
        this.o.setAuxiliaryUnselectedTextSize(40.0f);
        float a2 = hcn.a(this.o.getContext(), 40.0f);
        this.o.a(a2, a2);
        int dimension = (int) getResources().getDimension(R.dimen._2131363098_res_0x7f0a051a);
        this.o.setSelectorElementAndTextGapHeight(dimension, dimension);
        this.o.setEnabled(false);
        this.o.setAlpha(1.0f);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
        p();
    }

    private void p() {
        LogUtil.a("Track_StandingFlexionFragment", "startBendAnim mCurrentScore:", Integer.valueOf(this.h), "mDirector", Integer.valueOf(this.i));
        if (this.h < 40) {
            this.b.startAnimation(new Pair(Integer.valueOf(this.i), Integer.valueOf(this.h)));
            this.g = R.string._2130840166_res_0x7f020a66;
            this.f3735a.setText(getString(R.string._2130840166_res_0x7f020a66));
            this.f3735a.setVisibility(0);
            this.f3735a.setTextSize(0, getResources().getDimension(R.dimen._2131363034_res_0x7f0a04da));
            return;
        }
        this.f3735a.setVisibility(8);
    }

    private void bfD_(View view) {
        this.r = (HealthTextView) view.findViewById(R.id.use_tips_2);
        hjl.bgL_(this.mSportPreView);
        if (this.mPreTips != null) {
            this.mPreTips.setText(R.string._2130840164_res_0x7f020a64);
        }
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.signaling);
        healthImageView.setVisibility(0);
        healthImageView.setImageResource(R.drawable._2131430491_res_0x7f0b0c5b);
        if (!(this.mSportPreView instanceof ConstraintLayout)) {
            LogUtil.h("Track_StandingFlexionFragment", "!(mSportPreView instanceof ConstraintLayout)");
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.constrainWidth(R.id.signaling, -2);
        constraintSet.constrainHeight(R.id.signaling, -2);
        constraintSet.clone((ConstraintLayout) this.mSportPreView);
        constraintSet.setHorizontalBias(R.id.signaling, 0.5f);
        constraintSet.setVerticalBias(R.id.signaling, 0.75f);
        constraintSet.applyTo((ConstraintLayout) this.mSportPreView);
    }

    private void r() {
        if (this.mSportPreView != null) {
            hjl.bgL_(this.mSportPreView);
            ((CustomTitleBar) this.mSportPreView.findViewById(R.id.title_bar)).setLeftButtonDrawable(bfB_(R.drawable._2131429877_res_0x7f0b09f5), nsf.h(R.string._2130850617_res_0x7f023339));
            ((HealthImageView) this.mSportPreView.findViewById(R.id.how_to_use_icon)).setImageDrawable(bfB_(R.drawable._2131430436_res_0x7f0b0c24));
        }
        t();
    }

    private void f() {
        this.j = new hng(this.m);
        this.q = new hmf(this.l, this.n);
        this.b = new hmd(this.e);
        this.d = new hma(this.k);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        hmd hmdVar = this.b;
        if (hmdVar != null) {
            hmdVar.a();
        }
        hma hmaVar = this.d;
        if (hmaVar != null) {
            hmaVar.a();
        }
        hmf hmfVar = this.q;
        if (hmfVar != null) {
            hmfVar.d();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        g();
    }

    private void g() {
        if (this.u == null) {
            LogUtil.a("Track_StandingFlexionFragment", "initViewModelObserves failed, mViewModel is null");
            return;
        }
        n();
        k();
        o();
        l();
        m();
        h();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        sendSportResult(this.w);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        super.initData();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected SportExamViewModel getViewModel() {
        StandingFlexionViewModel standingFlexionViewModel = (StandingFlexionViewModel) new ViewModelProvider(requireActivity()).get(StandingFlexionViewModel.class);
        this.u = standingFlexionViewModel;
        return standingFlexionViewModel;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected void dealProgressEnd() {
        this.mSportPreView.setVisibility(8);
        this.u.onCountDown();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
    }

    private Drawable bfB_(int i) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            return nrz.cKn_(BaseApplication.getContext(), i);
        }
        return getResources().getDrawable(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        r();
    }

    private void n() {
        this.u.observeSportingData("preSportProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.c(obj);
            }
        });
    }

    /* synthetic */ void c(Object obj) {
        if (obj instanceof Integer) {
            dealPreProgress(((Integer) obj).intValue());
        }
    }

    private void o() {
        this.u.observeSportingData("maxFlexionScore", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.a(obj);
            }
        });
    }

    /* synthetic */ void a(Object obj) {
        if (obj instanceof Integer) {
            LogUtil.a("Track_StandingFlexionFragment", "MAX_FLEXION_SCORE update mStandFlexionMaxScore:", Integer.valueOf(this.w), "data：", obj);
            Integer num = (Integer) obj;
            if (num.intValue() > this.w) {
                this.w = num.intValue();
                t();
                this.x.startAnimation(hjx.bhl_());
                this.j.startAnimation(obj);
            }
        }
    }

    private void k() {
        this.u.observeSportingData("standFlexionScore", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.j(obj);
            }
        });
        this.u.observeSportingData("bodyPosition", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.i(obj);
            }
        });
    }

    /* synthetic */ void j(Object obj) {
        LogUtil.a("Track_StandingFlexionFragment", "STAND_FLEXION_SCORE update");
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            LogUtil.a("Track_StandingFlexionFragment", "score:", Integer.valueOf(num.intValue()));
            int intValue = num.intValue();
            this.h = intValue;
            this.o.setValue(intValue);
            a();
            e();
            b();
        }
    }

    /* synthetic */ void i(Object obj) {
        if (obj instanceof kxa) {
            kxa kxaVar = (kxa) obj;
            this.c = kxaVar;
            int e = kxaVar.e();
            this.i = e;
            LogUtil.a("Track_StandingFlexionFragment", "mDirector:", Integer.valueOf(e));
            d();
        }
    }

    private void b() {
        if (this.f3735a == null) {
            LogUtil.h("Track_StandingFlexionFragment", "hideErrorStatusView mBodyStatusTips == null");
            return;
        }
        p();
        ImageView imageView = this.e;
        if (imageView != null && imageView.getVisibility() == 0) {
            this.f3735a.setText(R.string._2130840166_res_0x7f020a66);
            this.f3735a.setVisibility(0);
            this.f3735a.setTextSize(0, getResources().getDimension(R.dimen._2131363034_res_0x7f0a04da));
            return;
        }
        this.f3735a.setVisibility(8);
    }

    private void a() {
        q();
        c();
        s();
    }

    private void d() {
        if (this.e.getVisibility() == 0) {
            p();
        }
    }

    private void q() {
        this.q.startAnimation(new Pair(this.c, Integer.valueOf(this.h)));
    }

    private void e() {
        if (this.k.getVisibility() == 0) {
            this.f3735a.setVisibility(8);
            this.d.b();
        }
    }

    private void c() {
        if (this.h >= 40) {
            LogUtil.a("Track_StandingFlexionFragment", "hideBendAnim");
            this.f3735a.setVisibility(8);
            this.b.d();
        }
    }

    private void s() {
        if (this.h >= 40) {
            this.p.setVisibility(0);
            this.o.setValue(this.h);
        } else {
            this.p.setVisibility(8);
        }
    }

    private void t() {
        HealthTextView healthTextView = this.x;
        if (healthTextView != null) {
            healthTextView.setText(UnitUtil.e(this.w, 1, 0));
            hjl.bgJ_(this.f, this.v, this.x, this.t);
        }
    }

    private void l() {
        this.u.observeSportingData("bodyDetectionError", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.d(obj);
            }
        });
    }

    /* synthetic */ void d(Object obj) {
        if (obj instanceof Integer) {
            this.h = 0;
            a();
            d(((Integer) obj).intValue());
        }
    }

    private void d(int i) {
        dealPreStartSportTips(i, R.string._2130840164_res_0x7f020a64);
        if (i != -11) {
            if (i != -8 && i != 101) {
                if (i == -4) {
                    this.g = R.string._2130839998_res_0x7f0209be;
                } else if (i == -3 || i == -2 || i == -1) {
                    this.g = R.string._2130839975_res_0x7f0209a7;
                    this.b.d();
                } else {
                    switch (i) {
                        case BaseResp.CODE_PERMISSION_NOT_GRANTED /* -1003 */:
                        case BaseResp.CODE_UNSUPPORTED_BRANCH /* -1002 */:
                            this.g = R.string._2130840165_res_0x7f020a65;
                            this.d.startAnimation(this.c);
                            this.b.d();
                            break;
                        case BaseResp.CODE_QQ_LOW_VERSION /* -1001 */:
                            break;
                        default:
                            this.f3735a.setVisibility(8);
                            return;
                    }
                }
            }
            this.g = R.string._2130840164_res_0x7f020a64;
        } else {
            this.g = R.string._2130847755_res_0x7f02280b;
        }
        if (i != -1002 && i != -1003) {
            e();
        }
        this.f3735a.setTextSize(0, getResources().getDimension(R.dimen._2131363060_res_0x7f0a04f4));
        this.f3735a.setText(getString(this.g));
        this.f3735a.setVisibility(0);
    }

    private void m() {
        this.u.observeSportingData("showSportTime", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.b(obj);
            }
        });
    }

    /* synthetic */ void b(Object obj) {
        if (obj != null) {
            this.v.setText(obj.toString());
            hjl.bgJ_(this.f, this.v, this.x, this.t);
        }
    }

    private void h() {
        this.u.observeSportingData("isShowBend", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.StandingFlexionFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StandingFlexionFragment.this.e(obj);
            }
        });
    }

    /* synthetic */ void e(Object obj) {
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                p();
            }
            if (this.e.getVisibility() == 0) {
                e();
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_StandingFlexionFragment";
    }
}
