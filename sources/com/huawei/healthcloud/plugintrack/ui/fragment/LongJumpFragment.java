package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.animation.AnimatorSet;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.collection.SimpleArrayMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpFragment;
import com.huawei.healthcloud.plugintrack.ui.view.RippleView;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.LongJumpViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import com.huawei.healthcloud.plugintrack.util.AiSportExamDialogUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.gxd;
import defpackage.gyr;
import defpackage.hjx;
import defpackage.hka;
import defpackage.hmk;
import defpackage.koq;
import defpackage.kxa;
import defpackage.mwo;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class LongJumpFragment extends BaseSportExamFragment implements IOnBackPressed {

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f3718a;
    private LinearLayout c;
    private kxa d;
    private HealthTextView e;
    private RelativeLayout g;
    private HealthImageView h;
    private HealthImageView i;
    private ViewGroup j;
    private LinearLayout k;
    private hmk l;
    private HealthImageView o;
    private RippleView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthImageView s;
    private ViewGroup t;
    private HealthImageView u;
    private LongJumpViewModel w;
    private RelativeLayout x;
    private final Handler f = new a(Looper.getMainLooper(), this);
    private int b = R.string._2130839976_res_0x7f0209a8;
    private boolean n = false;
    private boolean m = false;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.long_jump_fragment;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected boolean isSupportLandScreen() {
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        super.initView(view);
        beD_(view);
        beE_(view);
        l();
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(4);
        }
    }

    private void l() {
        hmk hmkVar = new hmk(this.j);
        this.l = hmkVar;
        hmkVar.b(true);
    }

    private void beD_(View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.left_first_data);
        this.r = healthTextView;
        healthTextView.setText(UnitUtil.e(0.0d, 1, 0));
        this.q = (HealthTextView) view.findViewById(R.id.right_first_data);
        this.e = (HealthTextView) view.findViewById(R.id.body_status_tips);
        this.h = (HealthImageView) view.findViewById(R.id.encourage_img);
        this.j = (ViewGroup) view.findViewById(R.id.distance_layout);
        this.p = (RippleView) view.findViewById(R.id.rippleImgView);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.right_second_data);
        healthTextView2.setText(getResources().getString(R.string._2130850516_res_0x7f0232d4, 3));
        this.x = (RelativeLayout) view.findViewById(R.id.start_signal_layout);
        this.s = (HealthImageView) view.findViewById(R.id.start_position_person);
        this.o = (HealthImageView) view.findViewById(R.id.write_light_beam);
        this.u = (HealthImageView) view.findViewById(R.id.test_line);
        this.i = (HealthImageView) view.findViewById(R.id.complete_img);
        this.f3718a = (HealthImageView) view.findViewById(R.id.camera_bg);
        k();
        this.c = (LinearLayout) view.findViewById(R.id.arrow_layout);
        this.k = (LinearLayout) view.findViewById(R.id.out_range_layout);
        this.g = (RelativeLayout) view.findViewById(R.id.complete_layout);
        b(this.r);
        b(this.q);
        b(healthTextView2);
    }

    private void b(HealthTextView healthTextView) {
        if (healthTextView == null) {
            return;
        }
        healthTextView.setTypeface(nsk.cKN_());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.m = true;
        beC_(this.g);
        q();
        this.f.postDelayed(new Runnable() { // from class: hhs
            @Override // java.lang.Runnable
            public final void run() {
                LongJumpFragment.this.c();
            }
        }, 1000L);
    }

    public /* synthetic */ void c() {
        AiSportExamDialogUtils.d(getActivity(), g(), new AiSportExamDialogUtils.DialogResultCallback() { // from class: hho
            @Override // com.huawei.healthcloud.plugintrack.util.AiSportExamDialogUtils.DialogResultCallback
            public final void executeResult(float f) {
                LongJumpFragment.this.d(f);
            }
        });
    }

    public /* synthetic */ void d(float f) {
        sendSportResult((long) f);
        finish();
    }

    private void q() {
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(1);
        }
    }

    private void k() {
        this.s.setImageBitmap(gyr.e().aWO_("public_orange_signaling"));
        this.o.setImageBitmap(gyr.e().aWO_("public_write_beam"));
        AnimatorSet bhw_ = hka.bhw_();
        bhw_.setTarget(this.o);
        bhw_.start();
        this.u.setImageBitmap(gyr.e().aWO_("public_test_line"));
        this.u.setAlpha(0.5f);
        this.i.setImageBitmap(gyr.e().aWO_("pic_complete"));
        this.f3718a.setImageBitmap(gyr.e().aWO_("public_mask"));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected SportExamViewModel getViewModel() {
        LongJumpViewModel longJumpViewModel = (LongJumpViewModel) new ViewModelProvider(requireActivity()).get(LongJumpViewModel.class);
        this.w = longJumpViewModel;
        return longJumpViewModel;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected void dealProgressEnd() {
        this.mSportPreView.setVisibility(8);
        this.t.setVisibility(8);
        this.w.onCountDown();
    }

    private void beE_(View view) {
        this.t = (ViewGroup) view.findViewById(R.id.pre_sport_tips);
        this.mTitleBar.setVisibility(8);
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.signaling);
        healthImageView.setImageResource(R.drawable._2131430727_res_0x7f0b0d47);
        b(healthImageView);
        this.mBackImg.setVisibility(0);
    }

    private void b(HealthImageView healthImageView) {
        if (healthImageView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) healthImageView.getLayoutParams();
            layoutParams.width = nsn.c(getActivity(), 200.0f);
            layoutParams.height = nsn.c(getActivity(), 280.0f);
            layoutParams.startToStart = 0;
            layoutParams.topToTop = 0;
            layoutParams.bottomToBottom = 0;
            layoutParams.endToEnd = -1;
            layoutParams.topMargin = nsn.c(getActivity(), 50.0f);
            healthImageView.setLayoutParams(layoutParams);
            healthImageView.requestLayout();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(getLogTag(), "onResume()");
        super.onResume();
        setSurfaceRotation();
        if (!getUserVisibleHint() || this.n) {
            return;
        }
        AiSportExamDialogUtils.a(getActivity(), new AiSportExamDialogUtils.DialogResultCallback() { // from class: hhq
            @Override // com.huawei.healthcloud.plugintrack.util.AiSportExamDialogUtils.DialogResultCallback
            public final void executeResult(float f) {
                LongJumpFragment.this.a(f);
            }
        });
    }

    public /* synthetic */ void a(float f) {
        SimpleArrayMap<String, Object> simpleArrayMap = new SimpleArrayMap<>(1);
        simpleArrayMap.put("height", Integer.valueOf((int) f));
        mwo.d().setValue(simpleArrayMap);
        LongJumpViewModel longJumpViewModel = this.w;
        if (longJumpViewModel != null) {
            longJumpViewModel.c(true);
        }
        Object[] objArr = new Object[4];
        objArr[0] = "showSetHeightDialog mViewModel == null :";
        objArr[1] = Boolean.valueOf(this.w == null);
        objArr[2] = "mIsAlreadyShowHeightDialog:";
        objArr[3] = true;
        ReleaseLogUtil.e("Track_LongJumpFragment", objArr);
        this.n = true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        n();
        switchScreenTips();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        s();
    }

    private void s() {
        LogUtil.a("Track_LongJumpFragment", "playLandScreenVoice");
        if (getActivity() == null || !getUserVisibleHint()) {
            LogUtil.a("Track_LongJumpFragment", "playLandScreenVoice getUserVisibleHint");
            return;
        }
        if (getResources().getConfiguration().orientation == 2) {
            b("L001");
        } else if (!this.m) {
            b("L211");
        }
        LogUtil.a("Track_LongJumpFragment", "playLandScreenVoice isCompleteTest", Boolean.valueOf(this.m));
    }

    private void b(String str) {
        String c = gyr.e().c(str, "mp3");
        gxd.a().b("assert" + c);
    }

    private void n() {
        m();
        r();
        o();
    }

    private void o() {
        this.w.observeSportingData("bodyDetectionError", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpFragment$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LongJumpFragment.this.a(obj);
            }
        });
    }

    /* synthetic */ void a(Object obj) {
        if (obj instanceof Integer) {
            d(((Integer) obj).intValue());
        }
    }

    private void m() {
        this.w.observeSportingData("preSportProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LongJumpFragment.this.d(obj);
            }
        });
    }

    /* synthetic */ void d(Object obj) {
        if (obj instanceof Integer) {
            dealPreProgress(((Integer) obj).intValue());
        }
    }

    private void r() {
        this.w.observeSportingData("sportExamScore", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LongJumpFragment.this.c(obj);
            }
        });
        this.w.observeSportingData("bodyPosition", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LongJumpFragment.this.e(obj);
            }
        });
    }

    /* synthetic */ void c(Object obj) {
        LogUtil.a("Track_LongJumpFragment", "observeScore", obj);
        if (obj instanceof Integer) {
            e(((Integer) obj).intValue());
        }
    }

    /* synthetic */ void e(Object obj) {
        if (obj instanceof kxa) {
            this.d = (kxa) obj;
            d();
            h();
        }
    }

    private void e(int i) {
        this.u.setAlpha(0.5f);
        beC_(this.c);
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            linearLayout.clearAnimation();
        }
        if (i < 50) {
            this.e.setVisibility(0);
            this.e.setText(R.string._2130840196_res_0x7f020a84);
            a(2);
            return;
        }
        this.l.startAnimation(String.valueOf(i));
        f();
        x();
        b(i);
        if (g().size() == 3) {
            this.f.postDelayed(new Runnable() { // from class: hhr
                @Override // java.lang.Runnable
                public final void run() {
                    LongJumpFragment.this.e();
                }
            }, 2000L);
        }
    }

    public /* synthetic */ void e() {
        LongJumpViewModel longJumpViewModel = this.w;
        if (longJumpViewModel != null) {
            longJumpViewModel.m134x32b3e3a1();
        }
    }

    private void t() {
        if (!koq.c(g()) || this.g.getVisibility() == 0) {
            return;
        }
        RelativeLayout relativeLayout = this.g;
        if (relativeLayout == null) {
            LogUtil.h("Track_LongJumpFragment", "showCompleteView mCompleteLayout == null");
        } else {
            relativeLayout.setVisibility(0);
            e(6, 2000L);
        }
    }

    private void f() {
        if (g().size() == 1) {
            this.h.setImageBitmap(gyr.e().aWO_("pic_great"));
            u();
        } else {
            if (this.w.e()) {
                this.h.setImageBitmap(gyr.e().aWO_("pic_perfect"));
            } else {
                this.h.setImageBitmap(gyr.e().aWO_("pic_keepitup"));
            }
            u();
        }
    }

    private void u() {
        this.h.setVisibility(0);
        AnimatorSet bhi_ = hjx.bhi_();
        bhi_.setTarget(this.h);
        bhi_.start();
        e(3, 2000L);
    }

    private void b(int i) {
        if (i > 0) {
            this.r.startAnimation(hjx.bhl_());
            this.p.setVisibility(0);
            this.p.a();
            this.f.sendMessageDelayed(this.f.obtainMessage(4), 1000L);
        }
    }

    private void d() {
        if (this.p == null) {
            LogUtil.h("Track_LongJumpFragment", "changePointPosition mRippleImgView == null");
        } else if (this.d.c() > 1.0f) {
            this.p.post(new Runnable() { // from class: hhl
                @Override // java.lang.Runnable
                public final void run() {
                    LongJumpFragment.this.b();
                }
            });
        }
    }

    public /* synthetic */ void b() {
        this.p.setTranslationX(this.d.c() - ((this.p.getWidth() / 2) + this.p.getLeft()));
        this.p.setTranslationY(this.d.d() - ((this.p.getHeight() / 2) + this.p.getTop()));
    }

    private void h() {
        LogUtil.a("Track_LongJumpFragment", "changeTextLinePosition ", Float.valueOf(this.d.f()));
        if (this.u == null) {
            LogUtil.h("Track_LongJumpFragment", "changePointPosition mRippleImgView == null");
        } else if (this.d.f() > 1.0f) {
            this.u.setVisibility(0);
            this.u.post(new Runnable() { // from class: hhp
                @Override // java.lang.Runnable
                public final void run() {
                    LongJumpFragment.this.a();
                }
            });
        }
    }

    public /* synthetic */ void a() {
        this.u.setTranslationX(this.d.f() - this.u.getLeft());
    }

    private void d(int i) {
        if (this.t.getVisibility() == 0) {
            this.e.setVisibility(8);
            dealPreStartSportTips(i, R.string._2130840193_res_0x7f020a81);
            return;
        }
        if (i == -11) {
            this.b = R.string._2130847755_res_0x7f02280b;
        } else if (i == -3 || i == -2 || i == -1) {
            this.b = R.string._2130839976_res_0x7f0209a8;
            this.u.setAlpha(0.5f);
        } else {
            switch (i) {
                case -3006:
                    this.k.setVisibility(0);
                    this.x.setVisibility(0);
                    e(5, 2000L);
                    return;
                case -3005:
                    this.b = R.string._2130840195_res_0x7f020a83;
                    break;
                case -3004:
                    this.b = R.string._2130840194_res_0x7f020a82;
                    break;
            }
        }
        if (i == -3000 || i == -3007) {
            this.t.setVisibility(8);
            this.e.setVisibility(8);
            this.x.setVisibility(8);
            i();
            this.u.setAlpha(1.0f);
            return;
        }
        this.e.setText(this.b);
        this.e.setVisibility(0);
        a(2);
        HealthImageView healthImageView = this.h;
        if (healthImageView != null && healthImageView.getVisibility() == 0) {
            e(8, 2000L);
        } else {
            this.x.setVisibility(0);
        }
    }

    private void a(int i) {
        e(i, 3000L);
    }

    private void e(int i, long j) {
        this.f.removeMessages(i);
        this.f.sendMessageDelayed(this.f.obtainMessage(i), j);
    }

    private void i() {
        HealthImageView healthImageView = this.h;
        if (healthImageView != null && healthImageView.getVisibility() == 0) {
            e(7, 2000L);
        } else {
            p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.c.getVisibility() != 0) {
            this.c.setVisibility(0);
            if (AnimationUtils.loadAnimation(getActivity(), R.anim._2130771982_res_0x7f01000e) instanceof AnimationSet) {
                this.c.startAnimation((AnimationSet) AnimationUtils.loadAnimation(getActivity(), R.anim._2130771982_res_0x7f01000e));
            }
            a(1);
        }
    }

    private void x() {
        if (this.r == null || this.q == null || koq.b(g())) {
            LogUtil.h("Track_LongJumpFragment", "updateScore mScoreText is null or scoreList null");
            return;
        }
        ReleaseLogUtil.e("Track_LongJumpFragment", "updateScore the testNum", Integer.valueOf(g().size()));
        this.q.setText(UnitUtil.e(g().size(), 1, 0));
        this.r.setText(UnitUtil.e(((Integer) Collections.max(g())).intValue(), 1, 0));
    }

    private List<Integer> g() {
        LongJumpViewModel longJumpViewModel = this.w;
        if (longJumpViewModel == null) {
            LogUtil.h("Track_LongJumpFragment", "getScoreList mViewModel == null");
            return new ArrayList(3);
        }
        return longJumpViewModel.b();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a("Track_LongJumpFragment", "onResumeSport");
        startDataSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (koq.b(g())) {
            finish();
        } else {
            t();
        }
    }

    class a extends BaseHandler<LongJumpFragment> {
        a(Looper looper, LongJumpFragment longJumpFragment) {
            super(looper, longJumpFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: beF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(LongJumpFragment longJumpFragment, Message message) {
            switch (message.what) {
                case 1:
                    LongJumpFragment.beC_(longJumpFragment.c);
                    if (longJumpFragment.c != null) {
                        LongJumpFragment.this.c.clearAnimation();
                        break;
                    }
                    break;
                case 2:
                    LongJumpFragment.beC_(longJumpFragment.e);
                    break;
                case 3:
                    LongJumpFragment.beC_(longJumpFragment.h);
                    if (LongJumpFragment.this.x != null) {
                        LongJumpFragment.this.x.setVisibility(0);
                        break;
                    }
                    break;
                case 4:
                    LongJumpFragment.beC_(longJumpFragment.p);
                    break;
                case 5:
                    LongJumpFragment.beC_(longJumpFragment.k);
                    break;
                case 6:
                    LongJumpFragment.this.j();
                    break;
                case 7:
                    LongJumpFragment.this.p();
                    break;
                case 8:
                    if (LongJumpFragment.this.x != null) {
                        LongJumpFragment.this.x.setVisibility(0);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void beC_(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_LongJumpFragment";
    }
}
