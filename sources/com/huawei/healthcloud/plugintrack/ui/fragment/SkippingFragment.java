package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.basesport.controls.ControlButtonLayout;
import com.huawei.health.basesport.sportui.OnEndCountdownListener;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment;
import com.huawei.healthcloud.plugintrack.ui.view.CanvasChangeView;
import com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton;
import com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout;
import com.huawei.healthcloud.plugintrack.ui.view.SquareProgress;
import com.huawei.healthcloud.plugintrack.ui.view.ToolsLayout;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.CameraGlView;
import com.huawei.healthcloud.plugintrack.ui.viewholder.CountdownDialog;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gso;
import defpackage.gwg;
import defpackage.gww;
import defpackage.hcn;
import defpackage.hjl;
import defpackage.hjx;
import defpackage.hmz;
import defpackage.hna;
import defpackage.hnh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;

/* loaded from: classes4.dex */
public class SkippingFragment extends BaseSportingFragment implements IOnBackPressed {

    /* renamed from: a, reason: collision with root package name */
    private static final int f3726a = hcn.a(BaseApplication.getContext(), 16.0f);
    private hmz aa;
    private HealthTextView ab;
    private View ac;
    private CameraGlView ad;
    private hnh ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private SkippingViewModel aj;
    private ViewGroup ak;
    private SquareProgress al;
    private CanvasChangeView am;
    private ImageButton an;
    private ToolsLayout ao;
    private ImageButton ap;
    private ViewGroup aq;
    private ImageButton ar;
    private HealthTextView as;
    private ImageView b;
    private HealthTextView c;
    private HealthTextView d;
    private View f;
    private CountdownDialog g;
    private View h;
    private ControlButtonLayout i;
    private ViewGroup j;
    private View k;
    private hna l;
    private ViewGroup n;
    private ImageView o;
    private HealthProgressBar p;
    private HealthTextView q;
    private ViewGroup r;
    private HealthColumnLinearLayout s;
    private RelativeLayout u;
    private NoTitleCustomAlertDialog x;
    private MusicControlLayout y;
    private HealthTextView z;
    private boolean w = false;
    private boolean t = false;
    private Handler m = new b(Looper.getMainLooper(), this);
    private int e = R.string._2130839976_res_0x7f0209a8;
    private int ai = 0;
    private ImageButton v = null;
    private int au = -1;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.skipping_sport_fragment;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        BaseActivity.cancelLayoutById(view);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        view.setPaddingRelative(((Integer) safeRegionWidth.first).intValue(), view.getPaddingTop(), ((Integer) safeRegionWidth.second).intValue(), view.getPaddingBottom());
        this.ad = (CameraGlView) view.findViewById(R.id.camera_pre_view);
        bfi_(view);
        bfj_(view);
        CountdownDialog countdownDialog = new CountdownDialog(getContext(), view);
        this.g = countdownDialog;
        countdownDialog.setTimeStart(3);
        this.g.enableGo();
        this.g.addEndCountdown(new OnEndCountdownListener() { // from class: hid
            @Override // com.huawei.health.basesport.sportui.OnEndCountdownListener
            public final void endCountdown() {
                SkippingFragment.this.e();
            }
        });
        View findViewById = view.findViewById(R.id.track_main_page_perm_bg);
        this.h = findViewById;
        findViewById.setBackgroundResource(R.color._2131299296_res_0x7f090be0);
        this.ao = (ToolsLayout) view.findViewById(R.id.sport_control_layout);
        j();
    }

    private void j() {
        this.aa = new hmz(this.j);
        this.ae = new hnh(this.ac);
        this.l = new hna(this.o, this.n);
    }

    private void bfi_(View view) {
        this.am = (CanvasChangeView) view.findViewById(R.id.sport_data_view);
        View findViewById = view.findViewById(R.id.data_panel_layout);
        this.f = findViewById;
        findViewById.setPaddingRelative(findViewById.getPaddingStart(), nsn.r(view.getContext()), this.f.getPaddingEnd(), this.f.getPaddingBottom());
        this.k = view.findViewById(R.id.left_data);
        this.ah = (HealthTextView) view.findViewById(R.id.left_first_data);
        this.af = (HealthTextView) view.findViewById(R.id.left_second_data);
        this.z = (HealthTextView) view.findViewById(R.id.right_first_data);
        this.p = (HealthProgressBar) view.findViewById(R.id.target_progress);
        this.d = (HealthTextView) view.findViewById(R.id.body_status_tips);
        this.ab = (HealthTextView) view.findViewById(R.id.skip_data_source_tips);
        this.b = (ImageView) view.findViewById(R.id.right_top_image);
        if (LanguageUtil.h(getContext())) {
            this.d.setTextSize(0, getResources().getDimension(R.dimen._2131365071_res_0x7f0a0ccf));
        } else {
            this.d.setTextSize(0, getResources().getDimension(R.dimen._2131365072_res_0x7f0a0cd0));
        }
        this.ag = (HealthTextView) view.findViewById(R.id.skip_interrupt_times);
        this.o = (ImageView) view.findViewById(R.id.skip_encourage_img);
        this.j = (ViewGroup) view.findViewById(R.id.countinues_skip_layout);
        this.ac = view.findViewById(R.id.skip_animation_layout);
        this.n = (ViewGroup) view.findViewById(R.id.explode_animation);
        this.v = (ImageButton) view.findViewById(R.id.track_main_page_sport_music);
        this.u = (RelativeLayout) view.findViewById(R.id.music_and_ai_icon_layout);
        this.v.setOnClickListener(new View.OnClickListener() { // from class: hig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkippingFragment.this.bfn_(view2);
            }
        });
        bfk_(view);
        o();
    }

    public /* synthetic */ void bfn_(View view) {
        if (nsn.a(600)) {
            LogUtil.b("Track_SkippingSportingFragment", "CLICK IS FAST");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            try {
                LogUtil.a("Track_SkippingSportingFragment", "startActivity(intent);");
                this.aj.i();
            } catch (ActivityNotFoundException unused) {
                LogUtil.a("Track_SkippingSportingFragment", "music ActivityNotFoundException ");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void bfk_(View view) {
        MusicControlLayout musicControlLayout = (MusicControlLayout) view.findViewById(R.id.music_content_layout);
        this.y = musicControlLayout;
        musicControlLayout.setIsExpandAnimation(true);
        this.s = (HealthColumnLinearLayout) view.findViewById(R.id.music_column_layout);
        this.y.setSportTypeDrawable(false, 283);
        this.y.c();
        this.ap = (ImageButton) view.findViewById(R.id.switch_camera_pre_sport_button);
        this.ar = (ImageButton) view.findViewById(R.id.switch_camera_in_sport_button);
        this.an = (ImageButton) view.findViewById(R.id.switch_camera_ecology_rope_button);
        bfg_(view);
    }

    private void bfg_(View view) {
        this.as = (HealthTextView) view.findViewById(R.id.switch_camera_pre_sport_pop);
        if (!SportSupportUtil.c() || !SportSupportUtil.b()) {
            this.as.setVisibility(8);
            return;
        }
        final gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        if (gwwVar.p()) {
            this.as.setVisibility(0);
            view.setOnClickListener(new View.OnClickListener() { // from class: hih
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SkippingFragment.this.bfm_(gwwVar, view2);
                }
            });
        } else {
            this.as.setVisibility(8);
        }
    }

    public /* synthetic */ void bfm_(gww gwwVar, View view) {
        this.as.setVisibility(8);
        gwwVar.e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002)).e(false);
        this.as.setVisibility(8);
    }

    private void ae() {
        boolean c = SportSupportUtil.c();
        boolean b2 = SportSupportUtil.b();
        if (c && b2) {
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SkippingFragment.this.ab();
                    ViewClickInstrumentation.clickOnView(view);
                }
            };
            this.ap.setOnClickListener(onClickListener);
            this.ar.setOnClickListener(onClickListener);
            this.an.setOnClickListener(onClickListener);
            return;
        }
        this.ar.setVisibility(8);
        this.ap.setVisibility(8);
        this.an.setVisibility(8);
        if (c) {
            ag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        if (nsn.a(600)) {
            LogUtil.b("Track_SkippingSportingFragment", "CLICK IS FAST");
        } else {
            ag();
            h();
        }
    }

    private void ag() {
        boolean z = !this.ad.c();
        this.ad.d(z);
        this.aj.c(z);
    }

    private void bfh_(View view) {
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.heart_rate_calorie_container);
        this.r = viewGroup;
        if (viewGroup == null) {
            LogUtil.a("Track_SkippingSportingFragment", "initHeartRateView: mHeartRateContainer is null");
            return;
        }
        this.q = (HealthTextView) viewGroup.findViewById(R.id.left_heart_rate_value);
        this.c = (HealthTextView) this.r.findViewById(R.id.right_calorie_value);
        ThreadPoolManager.d().execute(new Runnable() { // from class: hhz
            @Override // java.lang.Runnable
            public final void run() {
                SkippingFragment.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        final boolean n = gso.e().n();
        this.m.post(new Runnable() { // from class: hia
            @Override // java.lang.Runnable
            public final void run() {
                SkippingFragment.this.a(n);
            }
        });
    }

    public /* synthetic */ void a(boolean z) {
        if (z) {
            this.r.setVisibility(0);
        } else {
            LogUtil.a("Track_SkippingSportingFragment", "initHeartRateView: not connected wear device");
            this.r.setVisibility(8);
        }
    }

    private void n() {
        this.u.setPadding(0, this.f.getHeight(), 0, 0);
        if (this.aj.e()) {
            this.s.setVisibility(8);
            this.v.setVisibility(8);
            LogUtil.a("Track_SkippingSportingFragment", "button is gone");
        } else if (this.aj.d().f(283) == 1 && gwg.a(getContext())) {
            this.s.setVisibility(0);
            this.v.setVisibility(8);
            LogUtil.a("Track_SkippingSportingFragment", "mini is VISIBLE");
        } else {
            this.s.setVisibility(8);
            this.v.setVisibility(0);
            LogUtil.a("Track_SkippingSportingFragment", "button is VISIBLE");
        }
    }

    public void a() {
        if (this.y != null) {
            if (!SportMusicController.a().d()) {
                LogUtil.a("Track_SkippingSportingFragment", "refreshMusicLayout  tryBindService");
                this.y.e(283);
            }
            this.y.c();
            SkippingViewModel skippingViewModel = this.aj;
            if (skippingViewModel == null) {
                LogUtil.h("Track_SkippingSportingFragment", "mSkippingViewModel == null");
                this.s.setVisibility(8);
                this.v.setVisibility(0);
            } else if (skippingViewModel.e()) {
                LogUtil.a("Track_SkippingSportingFragment", "refreshMusicLayout is not support");
                this.s.setVisibility(8);
                this.v.setVisibility(8);
            } else if (this.aj.d().f(283) == 1 && gwg.a(getContext())) {
                this.s.setVisibility(0);
                this.y.a(true);
                this.v.setVisibility(8);
                LogUtil.a("Track_SkippingSportingFragment", "refreshMusicLayout icon is gone");
            }
        }
    }

    private void bfj_(View view) {
        this.ak = (ViewGroup) view.findViewById(R.id.pre_sport_layout);
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.title_bar);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hif
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkippingFragment.this.bfo_(view2);
            }
        });
        customTitleBar.setLeftButtonDrawable(bff_(R.drawable._2131429877_res_0x7f0b09f5), nsf.h(R.string._2130850617_res_0x7f023339));
        hjl.bgL_(this.ak);
        SquareProgress squareProgress = (SquareProgress) view.findViewById(R.id.pre_progress);
        this.al = squareProgress;
        squareProgress.setPaddingRelative(squareProgress.getPaddingStart(), nsn.r(view.getContext()), this.al.getPaddingEnd(), this.al.getPaddingBottom());
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.how_to_use_layout);
        this.aq = viewGroup;
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: hie
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkippingFragment.this.bfp_(view2);
            }
        });
    }

    public /* synthetic */ void bfo_(View view) {
        ad();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bfp_(View view) {
        FragmentActivity activity = getActivity();
        if (activity instanceof SportBaseActivity) {
            ((SportBaseActivity) activity).e(1);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ai() {
        boolean l = l();
        ViewGroup viewGroup = this.aq;
        if (viewGroup != null) {
            viewGroup.setVisibility(l ? 0 : 8);
        }
        ImageButton imageButton = this.ap;
        if (imageButton != null && !l) {
            imageButton.setVisibility(8);
        }
        HealthTextView healthTextView = this.as;
        if (healthTextView == null || l) {
            return;
        }
        healthTextView.setVisibility(8);
    }

    private boolean l() {
        SkippingViewModel skippingViewModel = this.aj;
        if (skippingViewModel != null) {
            return skippingViewModel.getDataSource() == 7;
        }
        LogUtil.b("Track_SkippingSportingFragment", "isAiSkip skip viewModel is null");
        return false;
    }

    private void ad() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b("Track_SkippingSportingFragment", "onTitleBarBackBtnClicked activity is null");
        } else {
            onBackPressed();
            activity.finish();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed
    public void onBackPressed() {
        ac();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(getLogTag(), "onResume()");
        super.onResume();
        if (getActivity() != null) {
            getActivity().getWindow().addFlags(128);
        }
        if (l()) {
            this.aj.onResumeSport();
        }
        v();
        ai();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a(getLogTag(), "onPause()");
        super.onPause();
        if (getActivity() != null) {
            getActivity().getWindow().clearFlags(128);
        }
        if (l()) {
            this.aj.onPauseSport();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        if (z && getActivity() != null) {
            getActivity().getWindow().setFlags(16778240, 16778240);
        }
        super.setUserVisibleHint(z);
        SkippingViewModel skippingViewModel = this.aj;
        if (skippingViewModel != null) {
            skippingViewModel.e(z);
        }
    }

    private void o() {
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.ah.setTypeface(createFromAsset);
        this.af.setTypeface(createFromAsset);
        this.z.setTypeface(createFromAsset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ah, reason: merged with bridge method [inline-methods] */
    public void e() {
        this.aj.onStartSport();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        SkippingViewModel skippingViewModel = (SkippingViewModel) new ViewModelProvider(requireActivity()).get(SkippingViewModel.class);
        this.aj = skippingViewModel;
        if (!skippingViewModel.a()) {
            this.aj.e(true);
        }
        m();
        this.aj.c();
        this.au = ((Integer) this.aj.getSportLaunchParams().getExtra("trackFrom", Integer.class, -1)).intValue();
        LogUtil.a(getLogTag(), "initViewModel mTrackFrom = ", Integer.valueOf(this.au));
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        aa();
    }

    private void aa() {
        ViewGroup viewGroup = this.ak;
        if (viewGroup != null) {
            hjl.bgL_(viewGroup);
            ((CustomTitleBar) this.ak.findViewById(R.id.title_bar)).setLeftButtonDrawable(bff_(R.drawable._2131429877_res_0x7f0b09f5), nsf.h(R.string._2130850617_res_0x7f023339));
            ((ImageView) this.ak.findViewById(R.id.how_to_use_icon)).setImageDrawable(bff_(R.drawable._2131430436_res_0x7f0b0c24));
        }
        HealthTextView healthTextView = this.d;
        if (healthTextView != null) {
            healthTextView.setText(getString(this.e));
            if (LanguageUtil.h(getContext())) {
                this.d.setTextSize(0, getResources().getDimension(R.dimen._2131365071_res_0x7f0a0ccf));
            } else {
                this.d.setTextSize(0, getResources().getDimension(R.dimen._2131365072_res_0x7f0a0cd0));
            }
        }
        al();
        x();
    }

    private Drawable bff_(int i) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            return nrz.cKn_(BaseApplication.getContext(), i);
        }
        return getResources().getDrawable(i);
    }

    private void m() {
        u();
        r();
        q();
        x();
        t();
        y();
        p();
        s();
        k();
    }

    private void x() {
        SkippingViewModel skippingViewModel = this.aj;
        if (skippingViewModel == null) {
            LogUtil.h("Track_SkippingSportingFragment", "observeSportTarget, mSkippingViewModel is null");
        } else if (skippingViewModel.getSportTarget() == 5) {
            this.af.setText(getResources().getString(R.string._2130850516_res_0x7f0232d4, Integer.valueOf((int) this.aj.getTargetValue())));
            this.aj.observeSportingData("targetProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda10
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    SkippingFragment.this.i(obj);
                }
            });
        } else {
            this.af.setVisibility(8);
            this.p.setVisibility(8);
        }
    }

    /* synthetic */ void i(Object obj) {
        if (obj instanceof Integer) {
            this.p.setProgress(((Integer) obj).intValue());
        }
    }

    private void t() {
        this.aj.observeSportingData("bodyDetectionError", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.c(obj);
            }
        });
    }

    /* synthetic */ void c(Object obj) {
        if (obj instanceof Integer) {
            e(((Integer) obj).intValue());
        }
    }

    private void r() {
        this.aj.observeSportingData("preSportProgress", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda16
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.d(obj);
            }
        });
    }

    /* synthetic */ void d(Object obj) {
        if (obj instanceof Integer) {
            c(((Integer) obj).intValue());
        }
    }

    private void ac() {
        SkippingViewModel skippingViewModel = this.aj;
        if (skippingViewModel == null) {
            LogUtil.h("Track_SkippingSportingFragment", "removePreProgressObservers, mSkippingViewModel is null");
        } else {
            skippingViewModel.removeSportDataObservers("preSportProgress", this);
        }
    }

    private void p() {
        this.aj.observeSportingData("encourageType", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda19
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.a(obj);
            }
        });
    }

    /* synthetic */ void a(Object obj) {
        this.l.startAnimation(obj);
    }

    private void y() {
        this.aj.observeSportingData("showSportTime", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.o(obj);
            }
        });
        if (this.aj.getSportTarget() == 0) {
            this.aj.observeSportingData("sportTimeCountDown", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda5
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    SkippingFragment.this.n(obj);
                }
            });
        }
    }

    /* synthetic */ void o(Object obj) {
        if (obj != null) {
            this.z.setText(obj.toString());
            hjl.bgJ_(this.k, this.z, this.ah, this.af);
        }
    }

    /* synthetic */ void n(Object obj) {
        this.z.setTextColor(getResources().getColor(R.color._2131296671_res_0x7f09019f));
    }

    private void q() {
        this.aj.observeSportingData("skipTimes", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda21
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.h(obj);
            }
        });
        this.aj.observeSportingData("skipContinuouslyTimes", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda22
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.f(obj);
            }
        });
        this.aj.observeSportingData("footPosition", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.j(obj);
            }
        });
        this.aj.observeSportingData("interruptTimes", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.g(obj);
            }
        });
    }

    /* synthetic */ void h(Object obj) {
        LogUtil.h("Track_SkippingSportingFragment", "SKIPPING_TIMES update");
        if (obj instanceof Integer) {
            this.ai = ((Integer) obj).intValue();
            al();
            this.ah.startAnimation(hjx.bhl_());
        }
    }

    /* synthetic */ void f(Object obj) {
        LogUtil.h("Track_SkippingSportingFragment", "SKIP_CONTINUOUSLY_TIMES update");
        if (!(obj instanceof Integer) || ((Integer) obj).intValue() <= 0) {
            return;
        }
        this.aa.startAnimation(String.valueOf(obj));
    }

    /* synthetic */ void j(Object obj) {
        this.ae.startAnimation(obj);
    }

    /* synthetic */ void g(Object obj) {
        Message obtainMessage = this.m.obtainMessage(1);
        obtainMessage.obj = obj;
        obtainMessage.sendToTarget();
    }

    private void s() {
        this.aj.observeSportingData(IndoorEquipManagerApi.KEY_HEART_RATE, this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda18
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.e(obj);
            }
        });
    }

    /* synthetic */ void e(Object obj) {
        if (this.q != null && (obj instanceof Integer)) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 255 || intValue <= 0) {
                this.q.setText("--");
            } else {
                this.q.setText(UnitUtil.e(intValue, 1, 0));
            }
        }
    }

    private void k() {
        this.aj.observeSportingData("calorie", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment$$ExternalSyntheticLambda20
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SkippingFragment.this.b(obj);
            }
        });
    }

    /* synthetic */ void b(Object obj) {
        HealthTextView healthTextView = this.c;
        if (healthTextView != null && (obj instanceof Integer)) {
            healthTextView.setText(UnitUtil.e(((Integer) obj).intValue(), 1, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        HealthTextView healthTextView = this.q;
        if (healthTextView != null) {
            healthTextView.setText("--");
        }
    }

    private void c(int i) {
        this.al.setProgress(i);
        if (i >= 100) {
            AnimationSet bhk_ = hjx.bhk_();
            bhk_.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    SkippingFragment.this.ak.setVisibility(8);
                    SkippingFragment.this.aj.onCountDown();
                    SportMusicController.a().b(283);
                }
            });
            this.ak.startAnimation(bhk_);
        }
    }

    private void e(int i) {
        if (i == 1001 || i == 1002 || i == 1003) {
            this.e = R.string._2130839976_res_0x7f0209a8;
            this.d.setText(getString(R.string._2130839976_res_0x7f0209a8));
        } else if (i == 1004) {
            this.e = R.string._2130839998_res_0x7f0209be;
            this.d.setText(getString(R.string._2130839998_res_0x7f0209be));
        } else {
            i();
            return;
        }
        af();
    }

    private void u() {
        this.aj.observeSportLifeCycle(getLogTag(), this);
    }

    private void al() {
        HealthTextView healthTextView = this.ah;
        if (healthTextView == null) {
            LogUtil.h("Track_SkippingSportingFragment", "updateSkipTimes mSkippingTimesText is null.");
        } else {
            healthTextView.setText(UnitUtil.e(this.ai, 1, 0));
            hjl.bgJ_(this.k, this.z, this.ah, this.af);
        }
    }

    private void af() {
        this.w = true;
        if (this.ag.getVisibility() != 0) {
            this.d.setVisibility(0);
        }
    }

    private void i() {
        this.w = false;
        this.d.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.ag.setText(R.string._2130840017_res_0x7f0209d1);
        this.ag.setVisibility(0);
        this.d.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.ag.setVisibility(8);
        if (this.w) {
            this.d.setVisibility(0);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        this.h.setVisibility(0);
        this.g.startCountdown();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.h.setVisibility(8);
        this.am.setVisibility(0);
        this.f.startAnimation(hjx.bhc_());
        this.f.setVisibility(0);
        if (this.ab != null && this.aj.getDataSource() == 5) {
            this.ab.setVisibility(0);
            this.b.setVisibility(0);
            this.ab.setText(getResources().getString(R.string.IDS_rope_device_tip));
            this.m.sendEmptyMessageDelayed(3, 3000L);
        }
        this.f.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hib
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                SkippingFragment.this.d();
            }
        });
        this.ao.setVisibility(0);
        f();
    }

    public /* synthetic */ void d() {
        n();
        bfh_(this.f);
    }

    private void f() {
        SkippingViewModel skippingViewModel;
        if (gso.e().o() && (skippingViewModel = this.aj) != null && skippingViewModel.getSportTarget() != 5 && this.aj.getSportTarget() != 0) {
            ControlButtonLayout controlButtonLayout = this.ao.getControlButtonLayout();
            this.i = controlButtonLayout;
            controlButtonLayout.setClickCallback(new IBaseResponseCallback() { // from class: hic
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SkippingFragment.this.d(i, obj);
                }
            });
            ToolsLayout toolsLayout = this.ao;
            toolsLayout.setShowOrHideView(toolsLayout.getEcologyRopeControlPanelLayout());
        } else {
            CircleProgressButton circleProgressButton = this.ao.getCircleProgressButton();
            ToolsLayout toolsLayout2 = this.ao;
            toolsLayout2.setShowOrHideView(toolsLayout2.getBottomPanel());
            circleProgressButton.a(new CircleProgressButton.CircleProcessListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment.1
                @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
                public void onCancel() {
                }

                @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
                public void onStarted() {
                }

                @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
                public void onFinished() {
                    SkippingFragment.this.c();
                }
            });
        }
        this.ao.c();
    }

    public /* synthetic */ void d(int i, Object obj) {
        SkippingViewModel skippingViewModel = this.aj;
        if (skippingViewModel == null) {
            LogUtil.a("Track_SkippingSportingFragment", "mViewModel == null ", Integer.valueOf(i));
            return;
        }
        if (i == 101) {
            skippingViewModel.onPauseSport();
            this.ao.getAnimateCallback().d(101, 101);
        } else if (i == 100) {
            skippingViewModel.onResumeSport();
            this.ao.getAnimateCallback().d(100, 100);
        } else if (i == 102) {
            this.ao.getAnimateCallback().d(102, 102);
            c();
        } else {
            LogUtil.h("Track_SkippingSportingFragment", "error code ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.aj.isToSave()) {
            this.aj.m134x32b3e3a1();
        } else {
            c(true);
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.x;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.h("Track_SkippingSportingFragment", "LittleDataDialog is showing");
            return;
        }
        this.t = true;
        NoTitleCustomAlertDialog.Builder czC_ = new NoTitleCustomAlertDialog.Builder(getContext()).e(z ? R.string._2130839999_res_0x7f0209bf : R.string._2130843725_res_0x7f02184d).czC_(z ? R.string._2130839724_res_0x7f0208ac : R.string._2130839808_res_0x7f020900, new View.OnClickListener() { // from class: hhv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SkippingFragment.this.bfq_(view);
            }
        });
        if (z) {
            czC_.czz_(R.string._2130839723_res_0x7f0208ab, new View.OnClickListener() { // from class: hhy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SkippingFragment.this.bfr_(view);
                }
            });
        }
        NoTitleCustomAlertDialog e = czC_.e();
        this.x = e;
        e.setCancelable(false);
        this.x.show();
    }

    public /* synthetic */ void bfq_(View view) {
        this.aj.m134x32b3e3a1();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bfr_(View view) {
        this.t = false;
        LogUtil.h("Track_SkippingSportingFragment", "continue to sport");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void w() {
        MusicControlLayout musicControlLayout = this.y;
        if (musicControlLayout != null) {
            musicControlLayout.setMusicPause();
        }
    }

    private void v() {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment.4
            @Override // java.lang.Runnable
            public void run() {
                if (SkippingFragment.this.y != null && SkippingFragment.this.aj.d().f(283) == 1 && SkippingFragment.this.aj.getSportStatus() == 1) {
                    SkippingFragment.this.y.setMusicPlay();
                }
                if (SkippingFragment.this.f == null || SkippingFragment.this.f.getVisibility() != 0) {
                    return;
                }
                SkippingFragment.this.a();
            }
        });
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a("Track_SkippingSportingFragment", "onPauseSport");
        if (this.i != null) {
            this.m.sendEmptyMessage(5);
        }
        w();
        this.m.sendEmptyMessage(7);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a("Track_SkippingSportingFragment", "onResumeSport");
        if (this.i != null) {
            this.m.sendEmptyMessage(6);
        }
        v();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        Bundle aTo_;
        if (!this.aj.isToSave()) {
            LogUtil.h("Track_SkippingSportingFragment", "data can't save with little data");
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.x;
            if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
                LogUtil.h("Track_SkippingSportingFragment", "data can't save with little data is showing return");
                return;
            } else {
                if (!this.t) {
                    this.m.sendEmptyMessage(4);
                    return;
                }
                gso.e().b(283, -1L);
                this.aj.b();
                getActivity().finish();
                return;
            }
        }
        MotionPath motionPath = this.aj.getMotionPath();
        MotionPathSimplify motionPathSimplify = this.aj.getMotionPathSimplify();
        if (motionPath != null && motionPathSimplify != null && (aTo_ = gso.e().aTo_("motion_path2.txt", motionPathSimplify, Collections.EMPTY_LIST, true, false)) != null) {
            aTo_.putSerializable("entrance", "fromTrackMainMap");
            bfl_(motionPathSimplify, aTo_);
        }
        this.aj.b();
        getActivity().finish();
    }

    private void bfl_(MotionPathSimplify motionPathSimplify, Bundle bundle) {
        ReleaseLogUtil.e("Track_SkippingSportingFragment", "sendSportResult trackFrom = ", Integer.valueOf(this.au));
        if (this.au == 1) {
            gso.e().b(283, motionPathSimplify.getExtendDataInt("skipNum", 0));
        } else {
            gso.e().aTt_(bundle);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        if (l()) {
            this.ad.setOnPreviewListener(this.aj);
            this.ad.c(true);
        } else {
            this.ad.c(false);
            SkippingViewModel skippingViewModel = this.aj;
            if (skippingViewModel != null) {
                skippingViewModel.onPreSport();
            }
        }
        ae();
    }

    static class b extends BaseHandler<SkippingFragment> {
        b(Looper looper, SkippingFragment skippingFragment) {
            super(looper, skippingFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bfs_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SkippingFragment skippingFragment, Message message) {
            switch (message.what) {
                case 1:
                    Object obj = message.obj;
                    if (obj instanceof Integer) {
                        skippingFragment.b(((Integer) obj).intValue());
                        removeMessages(2);
                        sendMessageDelayed(obtainMessage(2), 2000L);
                        break;
                    }
                    break;
                case 2:
                    skippingFragment.g();
                    break;
                case 3:
                    LogUtil.a("Track_SkippingSportingFragment", "MSG_HIDE_ECOLOGY_TIPS enter");
                    skippingFragment.ab.setVisibility(8);
                    break;
                case 4:
                    skippingFragment.c(false);
                    break;
                case 5:
                    skippingFragment.i.showBtnStopAndPlay();
                    skippingFragment.ao.getAnimateCallback().d(101, 101);
                    break;
                case 6:
                    skippingFragment.i.e();
                    skippingFragment.ao.getAnimateCallback().d(100, 100);
                    break;
                case 7:
                    skippingFragment.z();
                    break;
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_SkippingSportingFragment";
    }
}
