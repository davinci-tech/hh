package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.animation.AnimatorSet;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.CrossJumpViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.gyr;
import defpackage.hjl;
import defpackage.hjx;
import defpackage.hka;
import defpackage.hmk;
import defpackage.mwo;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class CrossJumpFragment extends BaseSportExamFragment implements IOnBackPressed {

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f3713a;
    private RelativeLayout c;
    private HealthTextView d;
    private HealthImageView e;
    private ViewGroup g;
    private hmk h;
    private HealthTextView j;
    private int k;
    private HealthImageView l;
    private ConstraintLayout m;
    private HealthTextView n;
    private CrossJumpViewModel p;
    private int r;
    private HealthTextView s;
    private HealthImageView t;
    private final Handler i = new b(Looper.getMainLooper(), this);
    private int b = R.string._2130839976_res_0x7f0209a8;
    private boolean o = false;
    private boolean f = false;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.cross_jump_fragment;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected boolean isSupportLandScreen() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        super.initView(view);
        bdW_(view);
        bdX_(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        super.initData();
        b();
    }

    private void b() {
        this.mSwitchCameraPreSportButton.setVisibility(8);
        this.mSwitchCameraInSportButton.setVisibility(8);
        this.mSwitchCameraPopText.setVisibility(8);
    }

    private void bdW_(View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.left_first_data);
        this.j = healthTextView;
        healthTextView.setText(UnitUtil.e(0.0d, 1, 0));
        this.n = (HealthTextView) view.findViewById(R.id.right_first_data);
        this.d = (HealthTextView) view.findViewById(R.id.body_status_tips);
        this.e = (HealthImageView) view.findViewById(R.id.encourage_img);
        ((HealthTextView) view.findViewById(R.id.left_second_data)).setText(getResources().getString(R.string._2130850516_res_0x7f0232d4, 10));
        this.f3713a = (HealthImageView) view.findViewById(R.id.complete_img);
        d();
        this.m = (ConstraintLayout) view.findViewById(R.id.sport_tips_out_screen);
        this.c = (RelativeLayout) view.findViewById(R.id.complete_layout);
        this.l = (HealthImageView) view.findViewById(R.id.sport_signal);
        this.g = (ViewGroup) view.findViewById(R.id.group_layout);
        this.t = (HealthImageView) view.findViewById(R.id.square_img);
        this.s = (HealthTextView) view.findViewById(R.id.sport_use_tips_2);
    }

    private void d() {
        this.f3713a.setImageBitmap(gyr.e().aWO_("pic_complete_portrait"));
        this.e.setImageBitmap(gyr.e().aWO_("pic_keepitup"));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected SportExamViewModel getViewModel() {
        CrossJumpViewModel crossJumpViewModel = (CrossJumpViewModel) new ViewModelProvider(requireActivity()).get(CrossJumpViewModel.class);
        this.p = crossJumpViewModel;
        return crossJumpViewModel;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected void dealProgressEnd() {
        this.mSportPreView.setVisibility(8);
        this.p.onCountDown();
    }

    private void bdX_(View view) {
        ((HealthImageView) view.findViewById(R.id.signaling)).setVisibility(0);
        hjl.bgL_(this.mSportPreView);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        e();
        a();
    }

    private void a() {
        this.h = new hmk(this.g);
    }

    private void e() {
        f();
        k();
        i();
        j();
        g();
        h();
    }

    private void j() {
        this.p.observeSportingData("showSportTime", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.a(obj);
            }
        });
        this.p.observeSportingData(ParsedFieldTag.NPES_SPORT_TIME, this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.f(obj);
            }
        });
    }

    /* synthetic */ void a(Object obj) {
        if (obj == null || this.f) {
            return;
        }
        this.n.setText(obj.toString());
    }

    /* synthetic */ void f(Object obj) {
        if (!(obj instanceof Integer) || this.f) {
            return;
        }
        if (this.p.d()) {
            this.p.m134x32b3e3a1();
        }
        int intValue = ((Integer) obj).intValue();
        this.k = intValue;
        if (this.p.c(intValue)) {
            this.n.setTextColor(getResources().getColor(R.color._2131299284_res_0x7f090bd4));
        }
    }

    private void i() {
        this.p.observeSportingData("bodyDetectionError", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.b(obj);
            }
        });
    }

    /* synthetic */ void b(Object obj) {
        if (obj instanceof Integer) {
            a(((Integer) obj).intValue());
        }
    }

    private void f() {
        this.p.observeSportingData("preSportProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.e(obj);
            }
        });
    }

    /* synthetic */ void e(Object obj) {
        if (obj instanceof Integer) {
            dealPreProgress(((Integer) obj).intValue());
        }
    }

    private void k() {
        this.p.observeSportingData("sportExamScore", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.g(obj);
            }
        });
    }

    /* synthetic */ void g(Object obj) {
        LogUtil.a("Track_LongJumpFragment", "observeScore", obj);
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() != 0) {
                if (!this.mIsStartTheTest) {
                    this.mIsStartTheTest = true;
                    this.p.onStartSport();
                }
                if (this.p.getSportStatus() == 2) {
                    this.p.onResumeSport();
                }
                this.t.setVisibility(0);
                this.t.setImageBitmap(this.p.bnH_(num.intValue()));
            }
        }
    }

    private void g() {
        this.p.observeSportingData("COMPLETE_ONE_GROUP", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.d(obj);
            }
        });
    }

    /* synthetic */ void d(Object obj) {
        LogUtil.a("Track_LongJumpFragment", "observeGroup", obj);
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            this.j.setText(UnitUtil.e(num.intValue(), 1, 0));
            this.h.startAnimation("1");
            if (num.intValue() >= 10) {
                LogUtil.a("Track_LongJumpFragment", "observeGroup");
                this.f = true;
                this.p.m134x32b3e3a1();
            }
        }
    }

    private void n() {
        if (this.c.getVisibility() != 0) {
            RelativeLayout relativeLayout = this.c;
            if (relativeLayout == null) {
                LogUtil.h("Track_LongJumpFragment", "showCompleteView mCompleteLayout == null");
            } else {
                relativeLayout.setVisibility(0);
                e(4);
            }
        }
    }

    private void h() {
        this.p.observeSportingData("OVER_TIME_JUMP", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CrossJumpFragment.this.c(obj);
            }
        });
    }

    /* synthetic */ void c(Object obj) {
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() == 8) {
                this.d.setText(R.string._2130840205_res_0x7f020a8d);
                this.d.setVisibility(0);
                e(1);
            }
            if (num.intValue() == 3) {
                o();
            }
        }
    }

    private void o() {
        this.e.setVisibility(0);
        AnimatorSet bhi_ = hjx.bhi_();
        bhi_.setTarget(this.e);
        bhi_.start();
        e(2);
    }

    private void a(int i) {
        if (this.mSportPreView.getVisibility() == 0) {
            dealPreStartSportTips(i, R.string._2130839974_res_0x7f0209a6);
            return;
        }
        this.r = i;
        if (i != -11 && i != -3 && i != -2 && i != -1) {
            switch (i) {
                case -4003:
                    this.b = R.string._2130840207_res_0x7f020a8f;
                    c();
                    break;
                case -4002:
                    this.b = R.string._2130840206_res_0x7f020a8e;
                    c();
                    break;
            }
            if (i == -4000 || this.o) {
            }
            this.s.setText(R.string._2130839975_res_0x7f0209a7);
            mwo.d().setExamStage(1);
            l();
            return;
        }
        if (i == -11) {
            this.b = R.string._2130847755_res_0x7f02280b;
        } else {
            this.b = R.string._2130839975_res_0x7f0209a7;
        }
        this.s.setText(this.b);
        if (this.p.getSportStatus() != 2) {
            this.p.onPauseSport();
        }
        if (!this.o) {
            m();
        }
        if (i == -4000) {
        }
    }

    private void c() {
        this.p.onPauseSport();
        this.o = true;
        this.d.setText(this.b);
        this.d.setVisibility(0);
        this.t.setVisibility(0);
        this.t.setImageBitmap(this.p.bnH_(-4002));
        this.t.startAnimation(hka.bhv_());
        e(3);
    }

    private void e(int i) {
        this.i.removeMessages(i);
        this.i.sendMessageDelayed(this.i.obtainMessage(i), 2000L);
    }

    private void m() {
        this.mDataPanelLayout.setVisibility(8);
        this.m.setVisibility(0);
        this.l.setVisibility(0);
        this.t.setVisibility(8);
    }

    private void l() {
        this.d.setVisibility(8);
        this.mDataPanelLayout.setVisibility(0);
        this.m.setVisibility(8);
        this.l.setVisibility(8);
        this.t.setVisibility(0);
        this.t.setImageBitmap(this.p.bnH_(1));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        CrossJumpViewModel crossJumpViewModel = this.p;
        if (crossJumpViewModel != null && crossJumpViewModel.d()) {
            sendSportResult(0L);
            finish();
            return;
        }
        CrossJumpViewModel crossJumpViewModel2 = this.p;
        if (crossJumpViewModel2 != null && crossJumpViewModel2.b() < 10) {
            finish();
        } else {
            n();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    public void endCountDown() {
        mwo.d().setExamStage(1);
        startDataSport();
        this.l.setVisibility(8);
        this.t.setVisibility(0);
        this.t.setImageBitmap(this.p.bnH_(1));
        this.m.setVisibility(8);
    }

    static class b extends BaseHandler<CrossJumpFragment> {
        b(Looper looper, CrossJumpFragment crossJumpFragment) {
            super(looper, crossJumpFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bdY_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(CrossJumpFragment crossJumpFragment, Message message) {
            if (crossJumpFragment == null) {
                LogUtil.h("Track_LongJumpFragment", "UiHandler fragment == null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                CrossJumpFragment.bdV_(crossJumpFragment.d);
                return;
            }
            if (i == 2) {
                CrossJumpFragment.bdV_(crossJumpFragment.e);
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                LogUtil.a("Track_LongJumpFragment", "showCompleteView");
                crossJumpFragment.sendSportResult(crossJumpFragment.f ? crossJumpFragment.k : 0L);
                crossJumpFragment.finish();
                return;
            }
            crossJumpFragment.t.clearAnimation();
            CrossJumpFragment.bdV_(crossJumpFragment.t);
            crossJumpFragment.d.setVisibility(8);
            crossJumpFragment.o = false;
            LogUtil.a("Track_LongJumpFragment", "fragment.mStatus:", Integer.valueOf(crossJumpFragment.r));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bdV_(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_LongJumpFragment";
    }
}
