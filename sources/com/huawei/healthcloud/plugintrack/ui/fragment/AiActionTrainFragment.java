package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.animation.AnimatorSet;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.AiActionTrainViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.bzs;
import defpackage.gyj;
import defpackage.gyl;
import defpackage.hjl;
import defpackage.hjx;
import defpackage.hpo;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class AiActionTrainFragment extends BaseSportExamFragment implements IOnBackPressed {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3708a;
    private HealthTextView b;
    private HealthImageView c;
    private RelativeLayout d;
    private View e;
    private ImageView h;
    private HealthTextView k;
    private HealthTextView m;
    private HealthTextView n;
    private HealthImageView o;
    private HealthTextView q;
    private AiActionTrainViewModel r;
    private int s;
    private NoTitleCustomAlertDialog j = null;
    private final Handler i = new c(Looper.getMainLooper(), this);
    private long g = 0;
    private long l = 0;
    private boolean f = false;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected boolean isSupportLandScreen() {
        return false;
    }

    public AiActionTrainFragment(int i) {
        LogUtil.a("Track_AiActionTrainFragment", "AiActionTrainFragment ", Integer.valueOf(i));
        this.s = i;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    public void startUserGuidance() {
        Bundle bundle = new Bundle();
        bundle.putInt("planType", 1);
        bundle.putBoolean("IS_AI_ACTION", true);
        if (gyj.c(this.r.d()) == 1) {
            bundle.putBoolean("AI_ACTION_ORIENTATION", true);
        }
        AppRouter.b("/PluginAiFitnessSportCommon/AIMotionGuidanceActivity").zF_(bundle).c(BaseApplication.getContext());
        hpo.b(3, this.r.b());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        if (gyj.c(this.s) == 0) {
            return R.layout.ai_action_train_fragment;
        }
        if (getActivity() == null) {
            return R.layout.ai_action_train_fragment_land;
        }
        getActivity().setRequestedOrientation(0);
        return R.layout.ai_action_train_fragment_land;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (gyj.c(this.s) == 1 && getActivity() != null) {
            getActivity().setRequestedOrientation(0);
        } else {
            e(this.o);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected SportExamViewModel getViewModel() {
        LogUtil.a("Track_AiActionTrainFragment", "getViewModel");
        AiActionTrainViewModel aiActionTrainViewModel = (AiActionTrainViewModel) new ViewModelProvider(requireActivity()).get(AiActionTrainViewModel.class);
        this.r = aiActionTrainViewModel;
        return aiActionTrainViewModel;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    protected void dealProgressEnd() {
        this.mSportPreView.setVisibility(8);
        this.r.onCountDown();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        super.initView(view);
        LogUtil.a("Track_AiActionTrainFragment", "initView");
        bdN_(view);
        bdP_(view);
        bdO_(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        LogUtil.a("Track_AiActionTrainFragment", "initViewModel");
        AiActionTrainViewModel aiActionTrainViewModel = this.r;
        if (aiActionTrainViewModel != null) {
            aiActionTrainViewModel.enableCanStart(true);
        }
        d();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        super.initData();
    }

    private void d() {
        g();
        j();
        e();
        h();
        f();
        i();
    }

    private void c(int i) {
        if (!this.f && this.r.getSportStatus() == 1) {
            if (i != 0 && this.g == 0) {
                this.g = this.l;
            }
            if (i == 0) {
                this.g = 0L;
            }
            long j = this.g;
            if (j != 0 && this.l - j > 60) {
                this.f = true;
                this.r.m134x32b3e3a1();
                hpo.d(6, this.r.b(), this.r.e());
            }
        }
    }

    private void g() {
        this.r.observeSportingData("bodyDetectionError", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.a(obj);
            }
        });
    }

    /* synthetic */ void a(Object obj) {
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            a(num.intValue());
            b(num.intValue());
            c(num.intValue());
        }
    }

    private void j() {
        this.r.observeSportingData("preSportProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.e(obj);
            }
        });
    }

    /* synthetic */ void e(Object obj) {
        if (obj instanceof Integer) {
            dealPreProgress(((Integer) obj).intValue());
        }
    }

    private void e() {
        this.r.observeSportingData("calorie", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.c(obj);
            }
        });
    }

    /* synthetic */ void c(Object obj) {
        HealthTextView healthTextView = this.b;
        if (healthTextView != null && (obj instanceof Integer)) {
            healthTextView.setText(UnitUtil.e(((Integer) obj).intValue(), 1, 0));
        }
    }

    private void f() {
        this.r.observeSportingData("result_code", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.b(obj);
            }
        });
    }

    /* synthetic */ void b(Object obj) {
        if (obj instanceof Integer) {
            LogUtil.a("Track_AiActionTrainFragment", "observeResultCode data is === ", obj);
            e(((Integer) obj).intValue());
        }
    }

    private void i() {
        if (this.r.getSportTarget() == 5 && this.r.c() == 10) {
            this.k.setText(getResources().getString(R.string._2130850516_res_0x7f0232d4, Integer.valueOf((int) this.r.getTargetValue())));
        }
        this.r.observeSportingData("valid_times", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.g(obj);
            }
        });
    }

    /* synthetic */ void g(Object obj) {
        if (obj instanceof Integer) {
            LogUtil.a("Track_AiActionTrainFragment", "observeValidTimes data is === ", obj);
            this.q.setText(String.valueOf(((Integer) obj).intValue()));
        }
    }

    private void h() {
        this.r.observeSportingData("showSportTime", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.d(obj);
            }
        });
        this.r.observeSportingData(ParsedFieldTag.NPES_SPORT_TIME, this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiActionTrainFragment.this.j(obj);
            }
        });
        if (this.r.getSportTarget() == 0) {
            this.r.observeSportingData("sportTimeCountDown", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment$$ExternalSyntheticLambda9
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    AiActionTrainFragment.this.i(obj);
                }
            });
        }
    }

    /* synthetic */ void d(Object obj) {
        if (obj != null) {
            this.m.setText(obj.toString());
            hjl.bgJ_(this.e, this.m, this.q, this.k);
        }
    }

    /* synthetic */ void j(Object obj) {
        if (obj instanceof Integer) {
            this.l = ((Integer) obj).intValue();
            LogUtil.a("Track_AiActionTrainFragment", "sport_time is ", obj);
        }
    }

    /* synthetic */ void i(Object obj) {
        this.m.setTextColor(getResources().getColor(R.color._2131299284_res_0x7f090bd4));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    public void endCountDown() {
        super.endCountDown();
        hpo.b(4, this.r.b());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment
    public void clickStopButton() {
        LogUtil.a("Track_AiActionTrainFragment", "clickStopButton");
        AiActionTrainViewModel aiActionTrainViewModel = this.r;
        if (aiActionTrainViewModel == null) {
            LogUtil.a("Track_AiActionTrainFragment", "clickStopButton, mViewModel == null");
        } else if (!aiActionTrainViewModel.isToSave()) {
            o();
        } else {
            this.r.m134x32b3e3a1();
            hpo.d(1, this.r.b(), this.r.e());
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed
    public void onBackPressed() {
        LogUtil.a("Track_AiActionTrainFragment", "onBackPressed");
        if (this.mIsStartTheTest) {
            o();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a("Track_AiActionTrainFragment", "onStopSport");
        hpo.b(5, this.r.b());
        if (!this.r.isToSave()) {
            LogUtil.a("Track_AiActionTrainFragment", "onStopSport, not save data");
            finish();
        } else {
            b(4, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("Track_AiActionTrainFragment", "enter showDataSaveDialog");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.j;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.a("Track_AiActionTrainFragment", "showDataSaveDialog is showing");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(getContext()).e(!this.r.isToSave() ? R.string._2130847810_res_0x7f022842 : R.string._2130847833_res_0x7f022859).czz_(R.string._2130839728_res_0x7f0208b0, new View.OnClickListener() { // from class: hgl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiActionTrainFragment.this.bdQ_(view);
            }
        }).czC_(R.string.IDS_device_nps_dialog_exit, new View.OnClickListener() { // from class: hgk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiActionTrainFragment.this.bdR_(view);
            }
        }).e();
        this.j = e;
        e.setCancelable(false);
        this.j.show();
    }

    public /* synthetic */ void bdQ_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.j;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
        }
        hpo.d(5, this.r.b(), this.r.e());
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bdR_(View view) {
        AiActionTrainViewModel aiActionTrainViewModel = this.r;
        if (aiActionTrainViewModel != null) {
            aiActionTrainViewModel.m134x32b3e3a1();
            if (!this.r.isToSave()) {
                hpo.d(3, this.r.b(), this.r.e());
            }
            hpo.d(4, this.r.b(), this.r.e());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bdN_(View view) {
        if (gyj.c(this.s) == 0) {
            this.n = (HealthTextView) view.findViewById(R.id.use_tips_2);
        } else {
            this.mSportPreView.setVisibility(0);
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        int i;
        if (gyj.c(this.s) == 0) {
            i = this.s == 1 ? R.string._2130847814_res_0x7f022846 : R.string._2130840164_res_0x7f020a64;
            if (this.mPreTips.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mPreTips.getLayoutParams();
                marginLayoutParams.topMargin = nsn.r(this.mSportPreView.getContext());
                this.mPreTips.setLayoutParams(marginLayoutParams);
            }
            this.n.setText(R.string._2130847754_res_0x7f02280a);
            this.n.setVisibility(0);
        } else {
            i = R.string._2130840193_res_0x7f020a81;
        }
        if (this.mPreTips != null) {
            this.mPreTips.setText(i);
        }
    }

    private void bdP_(View view) {
        Drawable drawable;
        Drawable drawable2;
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.signaling);
        this.o = healthImageView;
        healthImageView.setVisibility(0);
        this.o.setImageBitmap(bdL_());
        if (gyj.c(this.s) == 0) {
            CustomTitleBar customTitleBar = this.mTitleBar;
            if (LanguageUtil.bc(this.mSportPreView.getContext())) {
                drawable2 = nrz.cKn_(this.mSportPreView.getContext(), R.drawable._2131429877_res_0x7f0b09f5);
            } else {
                drawable2 = getResources().getDrawable(R.drawable._2131429877_res_0x7f0b09f5);
            }
            customTitleBar.setLeftButtonDrawable(drawable2, nsf.h(R.string._2130850617_res_0x7f023339));
            hjl.bgL_(this.mSportPreView);
            e(this.o);
            return;
        }
        a(this.o);
        this.mTitleBar.setVisibility(8);
        this.mBackImg.setVisibility(0);
        HealthImageView healthImageView2 = this.mBackImg;
        if (LanguageUtil.bc(this.mSportPreView.getContext())) {
            drawable = nrz.cKn_(this.mSportPreView.getContext(), R.drawable._2131429877_res_0x7f0b09f5);
        } else {
            drawable = getResources().getDrawable(R.drawable._2131429877_res_0x7f0b09f5);
        }
        healthImageView2.setImageDrawable(drawable);
        ViewGroup.LayoutParams layoutParams = this.mBackImg.getLayoutParams();
        layoutParams.height = nsn.c(this.mSportPreView.getContext(), 40.0f);
        layoutParams.width = nsn.c(this.mSportPreView.getContext(), 40.0f);
        this.mBackImg.setLayoutParams(layoutParams);
    }

    private Bitmap bdL_() {
        String str;
        if (gyj.c(this.s) == 0) {
            str = this.s == 1 ? "positive_signaling" : "side_signaling";
        } else {
            int i = this.s;
            str = (i == 4 || i == 7) ? "lie_signaling" : "lay_signaling";
        }
        return gyl.b().aWK_(str);
    }

    private void e(HealthImageView healthImageView) {
        if (healthImageView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) healthImageView.getLayoutParams();
            e(layoutParams);
            layoutParams.startToStart = 0;
            layoutParams.topToTop = 0;
            layoutParams.bottomToBottom = 0;
            layoutParams.endToEnd = 0;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) this.mSportPreView);
            constraintSet.setHorizontalBias(R.id.signaling, 0.5f);
            constraintSet.applyTo((ConstraintLayout) this.mSportPreView);
            healthImageView.setLayoutParams(layoutParams);
            healthImageView.requestLayout();
        }
    }

    private void e(ConstraintLayout.LayoutParams layoutParams) {
        layoutParams.width = nsn.c(getActivity(), 170.0f);
        layoutParams.height = nsn.c(getActivity(), 528.0f);
        if (nsn.ag(getContext()) && nsn.l() && c()) {
            layoutParams.width = nsn.c(getActivity(), 130.0f);
            layoutParams.height = nsn.c(getActivity(), 423.0f);
        }
    }

    private boolean c() {
        if (getContext() == null) {
            return false;
        }
        return getContext().getResources().getConfiguration().densityDpi > DisplayMetrics.DENSITY_DEVICE_STABLE;
    }

    private void a(HealthImageView healthImageView) {
        if (healthImageView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) healthImageView.getLayoutParams();
            layoutParams.width = nsn.c(getActivity(), 528.0f);
            layoutParams.height = nsn.c(getActivity(), 170.0f);
            layoutParams.startToStart = 0;
            layoutParams.topToTop = 0;
            layoutParams.bottomToBottom = 0;
            layoutParams.endToEnd = -1;
            layoutParams.bottomMargin = nsn.c(getActivity(), -16.0f);
            layoutParams.leftMargin = nsn.c(getActivity(), 126.0f);
            healthImageView.setLayoutParams(layoutParams);
            healthImageView.requestLayout();
        }
    }

    private void bdO_(View view) {
        this.f3708a = (HealthTextView) view.findViewById(R.id.body_status_tips);
        this.b = (HealthTextView) view.findViewById(R.id.right_calorie_value);
        this.m = (HealthTextView) view.findViewById(R.id.right_first_data);
        Typeface cKN_ = nsk.cKN_();
        this.b.setTypeface(cKN_);
        this.m.setTypeface(cKN_);
        this.e = view.findViewById(R.id.left_data);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.left_first_data);
        this.q = healthTextView;
        healthTextView.setTypeface(cKN_);
        this.h = (ImageView) view.findViewById(R.id.encourage_img);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.left_second_data);
        this.k = healthTextView2;
        healthTextView2.setTypeface(cKN_);
        this.d = (RelativeLayout) view.findViewById(R.id.complete_layout);
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.complete_img);
        this.c = healthImageView;
        healthImageView.setImageBitmap(gyl.b().aWK_("sport_complete"));
    }

    private void a(int i) {
        if (i == -7) {
            if (gyj.c(this.s) == 0) {
                this.n.setVisibility(8);
                this.mPreTips.setText(R.string._2130847816_res_0x7f022848);
                if (this.mPreTips.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mPreTips.getLayoutParams();
                    marginLayoutParams.topMargin = nsn.r(this.mSportPreView.getContext()) * 2;
                    this.mPreTips.setLayoutParams(marginLayoutParams);
                }
            } else {
                this.mPreTips.setText(R.string._2130847756_res_0x7f02280c);
            }
            b(1, 6000L);
        }
    }

    private void b(int i) {
        if (i == -5) {
            this.f3708a.setText(R.string._2130847755_res_0x7f02280b);
            this.f3708a.setVisibility(0);
        } else if (i == -1) {
            this.f3708a.setText(R.string._2130847754_res_0x7f02280a);
            this.f3708a.setVisibility(0);
        } else {
            if (i != 0) {
                return;
            }
            this.f3708a.setVisibility(8);
        }
    }

    private void e(int i) {
        if (i == 1 || i == 2 || i == 3) {
            this.h.setImageBitmap(gyl.b().aWK_("add_one"));
            n();
        }
        if (i == 4) {
            this.h.setImageBitmap(gyl.b().aWK_("perfect"));
            n();
        }
    }

    private void n() {
        this.h.setVisibility(0);
        AnimatorSet bhi_ = hjx.bhi_();
        bhi_.setTarget(this.h);
        bhi_.start();
        b(3, 2000L);
    }

    private void b(int i, long j) {
        this.i.removeMessages(i);
        this.i.sendMessageDelayed(this.i.obtainMessage(i), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bdM_(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("Track_AiActionTrainFragment", "showCompleteView");
        RelativeLayout relativeLayout = this.d;
        if (relativeLayout == null) {
            LogUtil.h("Track_AiActionTrainFragment", "showCompleteView mCompleteLayout == null");
        } else if (relativeLayout.getVisibility() != 0) {
            this.d.setVisibility(0);
            b(5, 3000L);
        } else {
            LogUtil.a("Track_AiActionTrainFragment", "mCompleteLayout is visible");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("Track_AiActionTrainFragment", "go to h5");
        H5ProLaunchOption.Builder addPath = new H5ProLaunchOption.Builder().addCustomizeArg("workoutRecord", new Gson().toJson(this.r.e())).addPath("#/SingleActionResultRecord");
        addPath.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).enableImageCache();
        bzs.e().loadH5ProApp(getActivity(), "com.huawei.health.h5.ai-fitness-course", addPath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.r.getTargetSportStatus() == 1) {
            hpo.d(2, this.r.b(), this.r.e());
        }
    }

    class c extends BaseHandler<AiActionTrainFragment> {
        c(Looper looper, AiActionTrainFragment aiActionTrainFragment) {
            super(looper, aiActionTrainFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bdS_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AiActionTrainFragment aiActionTrainFragment, Message message) {
            LogUtil.a("Track_AiActionTrainFragment", "message ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                AiActionTrainFragment.this.m();
                return;
            }
            if (i == 2) {
                AiActionTrainFragment.this.o();
                return;
            }
            if (i == 3) {
                AiActionTrainFragment.bdM_(aiActionTrainFragment.h);
                return;
            }
            if (i == 4) {
                AiActionTrainFragment.this.l();
            } else {
                if (i != 5) {
                    return;
                }
                AiActionTrainFragment.this.b();
                AiActionTrainFragment.this.k();
                AiActionTrainFragment.this.finish();
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_AiActionTrainFragment", " onResume");
        this.r.onResumeSport();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.a("Track_AiActionTrainFragment", " onPause");
        this.r.onPauseSport();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_AiActionTrainFragment";
    }
}
