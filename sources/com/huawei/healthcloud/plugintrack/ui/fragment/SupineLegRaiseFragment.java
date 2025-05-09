package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment;
import com.huawei.healthcloud.plugintrack.ui.view.RippleView;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SupineLegRaiseModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SupineLegAchieveType;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.hjx;
import defpackage.hma;
import defpackage.hmk;
import defpackage.hne;
import defpackage.kxa;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class SupineLegRaiseFragment extends BaseSportExamFragment implements IOnBackPressed {

    /* renamed from: a, reason: collision with root package name */
    private hmk f3738a;
    private kxa b;
    private hma d;
    private HealthTextView e;
    private View f;
    private hne g;
    private ViewGroup h;
    private ImageView i;
    private HealthTextView k;
    private ViewGroup n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthImageView r;
    private RippleView s;
    private SupineLegRaiseModel v;
    private HealthImageView y;
    private Handler m = new d(Looper.getMainLooper(), this);
    private int c = R.string._2130839976_res_0x7f0209a8;
    private int t = 0;
    private int j = 0;
    private boolean l = false;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.supine_leg_raise_fragment;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected boolean isSupportLandScreen() {
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        super.initView(view);
        bfF_(view);
        bfG_(view);
        e();
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(4);
        }
        switchScreenTips();
    }

    private void e() {
        this.f3738a = new hmk(this.h);
        this.g = new hne(this.i);
        this.d = new hma(this.f);
    }

    private void bfF_(View view) {
        this.q = (HealthTextView) view.findViewById(R.id.left_first_data);
        this.o = (HealthTextView) view.findViewById(R.id.left_second_data);
        this.p = (HealthTextView) view.findViewById(R.id.right_first_data);
        this.e = (HealthTextView) view.findViewById(R.id.body_status_tips);
        this.k = (HealthTextView) view.findViewById(R.id.interrupt_times);
        this.i = (ImageView) view.findViewById(R.id.encourage_img);
        this.h = (ViewGroup) view.findViewById(R.id.countinues_layout);
        this.s = (RippleView) view.findViewById(R.id.rippleImgView);
        this.f = view.findViewById(R.id.flexion_anim_layout);
        this.r = (HealthImageView) view.findViewById(R.id.init_ripple_img);
        f();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected SportExamViewModel getViewModel() {
        SupineLegRaiseModel supineLegRaiseModel = (SupineLegRaiseModel) new ViewModelProvider(requireActivity()).get(SupineLegRaiseModel.class);
        this.v = supineLegRaiseModel;
        return supineLegRaiseModel;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected void dealProgressEnd() {
        this.mSportPreView.setVisibility(8);
        this.n.setVisibility(8);
        if (this.mIsStartTheTest) {
            this.v.onResumeSport();
        } else {
            this.v.onCountDown();
        }
    }

    private void bfG_(View view) {
        this.y = (HealthImageView) view.findViewById(R.id.signaling);
        this.n = (ViewGroup) view.findViewById(R.id.pre_sport_tips);
        this.mTitleBar.setVisibility(8);
        if (this.mPreTips != null) {
            this.mPreTips.setText(R.string._2130839976_res_0x7f0209a8);
        }
        this.mBackImg.setVisibility(0);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(getLogTag(), "onResume()");
        super.onResume();
        setSurfaceRotation();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        setSurfaceRotation();
    }

    private void f() {
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.q.setTypeface(createFromAsset);
        this.o.setTypeface(createFromAsset);
        this.p.setTypeface(createFromAsset);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        g();
        switchScreenTips();
    }

    private void g() {
        n();
        m();
        h();
        l();
        j();
        k();
    }

    private void k() {
        this.v.observeSportingData("reStartSport", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.i(obj);
            }
        });
    }

    /* synthetic */ void i(Object obj) {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            this.mSquareProgress.setProgress(0);
            this.v.resetProgress();
            this.n.setVisibility(0);
            this.e.setVisibility(8);
            this.mSportPreView.setVisibility(0);
            this.mSwitchCameraPreSportButton.setVisibility(8);
            this.mSwitchCameraPopText.setVisibility(8);
            this.mToIntroduceButtonLayout.setVisibility(8);
            this.mTitleBar.setVisibility(8);
            this.mDataPanelLayout.setVisibility(8);
        }
    }

    private void h() {
        this.v.observeSportingData("bodyDetectionError", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.c(obj);
            }
        });
    }

    /* synthetic */ void c(Object obj) {
        if (obj instanceof Integer) {
            d(((Integer) obj).intValue());
        }
    }

    private void n() {
        this.v.observeSportingData("preSportProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.b(obj);
            }
        });
    }

    /* synthetic */ void b(Object obj) {
        if (obj instanceof Integer) {
            dealPreProgress(((Integer) obj).intValue());
        }
    }

    private void j() {
        this.v.observeSportingData("encourageType", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.d(obj);
            }
        });
    }

    /* synthetic */ void d(Object obj) {
        p();
    }

    private void l() {
        this.v.observeSportingData("showSportTime", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.h(obj);
            }
        });
        if (this.v.getSportTarget() == 0) {
            this.v.observeSportingData("sportTimeCountDown", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    SupineLegRaiseFragment.this.g(obj);
                }
            });
        }
    }

    /* synthetic */ void h(Object obj) {
        if (obj != null) {
            this.p.setText(obj.toString());
        }
    }

    /* synthetic */ void g(Object obj) {
        this.p.setTextColor(getResources().getColor(R.color._2131299284_res_0x7f090bd4));
    }

    private void m() {
        this.v.observeSportingData("sportExamScore", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.e(obj);
            }
        });
        this.v.observeSportingData("continuouslyTimes", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.a(obj);
            }
        });
        this.v.observeSportingData("bodyPosition", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SupineLegRaiseFragment.this.f(obj);
            }
        });
    }

    /* synthetic */ void e(Object obj) {
        LogUtil.a("Track_SupineLegRaiseFragment", "observeRaiseTimes");
        if (obj instanceof Integer) {
            this.t = ((Integer) obj).intValue();
            p();
            o();
        }
    }

    /* synthetic */ void a(Object obj) {
        LogUtil.a("Track_SupineLegRaiseFragment", "CONTINUOUSLY_TIMES update");
        if (!(obj instanceof Integer) || ((Integer) obj).intValue() <= 0) {
            return;
        }
        this.f3738a.startAnimation(String.valueOf(obj));
        this.g.startAnimation(obj);
    }

    /* synthetic */ void f(Object obj) {
        if (obj instanceof kxa) {
            kxa kxaVar = (kxa) obj;
            this.b = kxaVar;
            int e = kxaVar.e();
            this.j = e;
            LogUtil.a("Track_SupineLegRaiseFragment", "mDirector:", Integer.valueOf(e));
            b();
            c();
        }
    }

    private void o() {
        if (this.t > 0) {
            this.q.startAnimation(hjx.bhl_());
            this.s.setVisibility(0);
            this.r.setVisibility(8);
            this.l = true;
            this.s.a();
            this.m.removeMessages(6);
            this.m.sendMessageDelayed(this.m.obtainMessage(6), 1000L);
        }
    }

    private void c() {
        if (this.j == 0) {
            this.y.setImageResource(R.drawable._2131431716_res_0x7f0b1124);
        } else {
            this.y.setImageResource(R.drawable._2131431717_res_0x7f0b1125);
        }
    }

    private void b() {
        if (this.s == null) {
            LogUtil.h("Track_SupineLegRaiseFragment", "changePointPosition mRippleImgView == null");
        } else if (this.b.o() > 1.0f) {
            this.s.post(new Runnable() { // from class: hip
                @Override // java.lang.Runnable
                public final void run() {
                    SupineLegRaiseFragment.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        this.s.setVisibility(0);
        if (this.l) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
        }
        bfE_(this.s, 0);
        bfE_(this.r, 0);
        bfE_(this.h, 100);
    }

    private void bfE_(View view, int i) {
        float f = i;
        view.setTranslationX((this.b.o() - ((view.getWidth() / 2) + view.getLeft())) + f);
        view.setTranslationY((this.b.m() - ((view.getHeight() / 2) + view.getTop())) - f);
    }

    private void r() {
        for (SupineLegAchieveType supineLegAchieveType : SupineLegAchieveType.values()) {
            if (supineLegAchieveType != null) {
                if (this.t == supineLegAchieveType.getLegRaiseTimes()) {
                    this.q.setTextColor(getResources().getColor(R.color._2131299284_res_0x7f090bd4));
                } else {
                    this.q.setTextColor(getResources().getColor(R.color._2131299238_res_0x7f090ba6));
                }
            }
        }
    }

    private void d(int i) {
        if (this.n.getVisibility() == 0) {
            this.e.setVisibility(8);
            dealPreStartSportTips(i, R.string._2130839976_res_0x7f0209a8);
            return;
        }
        if (i == -2007) {
            this.c = R.string._2130840174_res_0x7f020a6e;
        } else if (i == -11) {
            this.c = R.string._2130847755_res_0x7f02280b;
        } else if (i == -2004) {
            this.c = R.string._2130840165_res_0x7f020a65;
            q();
        } else if (i == -2003 || i == -3 || i == -2 || i == -1) {
            this.c = R.string._2130839976_res_0x7f0209a8;
            this.s.setVisibility(8);
            this.r.setVisibility(8);
        }
        if (i != -2004) {
            a();
        }
        if (i == 0 || i == -2001 || i == -2000) {
            this.n.setVisibility(8);
            this.e.setVisibility(8);
        } else {
            this.e.setText(this.c);
            this.e.setVisibility(0);
        }
    }

    private void p() {
        if (this.q == null) {
            LogUtil.h("Track_SupineLegRaiseFragment", "updateSkipTimes mScoreText is null.");
        } else {
            r();
            this.q.setText(UnitUtil.e(this.t, 1, 0));
        }
    }

    private void q() {
        LogUtil.a("Track_SupineLegRaiseFragment", "startKneeAnim");
        kxa kxaVar = new kxa();
        if (i()) {
            kxaVar.d(this.b.b());
            kxaVar.c(this.b.a());
            kxaVar.h(this.b.g());
            kxaVar.f(this.b.j());
            kxaVar.e(1);
        }
        this.d.d(true);
        this.d.startAnimation(kxaVar);
    }

    private boolean i() {
        kxa kxaVar = this.b;
        return kxaVar != null && kxaVar.a() > 1.0f && this.b.j() > 1.0f;
    }

    private void a() {
        if (this.f.getVisibility() == 0) {
            this.d.b();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a("Track_SupineLegRaiseFragment", "onResumeSport");
        startDataSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        sendSportResult(this.t);
        finish();
    }

    static class d extends BaseHandler<SupineLegRaiseFragment> {
        d(Looper looper, SupineLegRaiseFragment supineLegRaiseFragment) {
            super(looper, supineLegRaiseFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bfH_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SupineLegRaiseFragment supineLegRaiseFragment, Message message) {
            int i = message.what;
            if (i != 2) {
                if (i != 6 || supineLegRaiseFragment.s == null || supineLegRaiseFragment.r == null) {
                    return;
                }
                supineLegRaiseFragment.s.d();
                supineLegRaiseFragment.l = false;
                supineLegRaiseFragment.r.setVisibility(0);
                return;
            }
            supineLegRaiseFragment.k.setVisibility(8);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        hma hmaVar = this.d;
        if (hmaVar != null) {
            hmaVar.a();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_SupineLegRaiseFragment";
    }
}
